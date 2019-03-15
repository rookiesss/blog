package com.java1234.listener;

import com.java1234.entity.Visit;
import com.java1234.service.VisitService;
import com.java1234.util.AddressUtil;
import com.java1234.util.UserAgentUtil;
import com.java1234.util.UserIpUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class VisitTimesListener implements ServletRequestListener {

    private Log log = LogFactory.getLog(getClass());
    private ApplicationContext applicationContext = null;

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        Visit visit = null;
        VisitService visitServiceImpl = null;
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        try {
             visitServiceImpl = (VisitService) applicationContext.getBean("visitServiceImpl");
            if (session.isNew()) {
                log.debug("----------applicationContext-----------");
                log.debug("begin-" + applicationContext + "-end");
                log.debug("-----begin-----");
                log.debug(applicationContext.getBean("visitServiceImpl"));
                visit = new Visit();
                visit.setIp(UserIpUtil.getIp(request));
                System.err.println(visit.getIp());
                visit.setTime(new Date());
                String userAgent = request.getHeader("user-agent");
                visit.setUserAgent(userAgent);
                synchronized (this) {
                    if (visitServiceImpl.findVisitTimes(visit) == 0) {
                        visit.setPlatformType(UserAgentUtil.getUserAgent(userAgent).getPlatformType());
                        visit.setBrowserType(UserAgentUtil.getUserAgent(userAgent).getBrowserType());
                        visit.setUrl(request.getRequestURL().toString());
                        visit.setCity(new AddressUtil().getAddresses("ip=" + visit.getIp(), "utf-8"));
                        visitServiceImpl.insert(visit);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
