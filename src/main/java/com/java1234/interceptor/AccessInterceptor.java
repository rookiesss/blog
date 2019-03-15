package com.java1234.interceptor;

import com.java1234.entity.RequestIp;
import com.java1234.util.UserIpUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        String ip = UserIpUtil.getIp(request);
        RequestIp re = (RequestIp) request.getSession().getAttribute(ip);
        if (null == re) {
            RequestIp requestIp = new RequestIp();
            requestIp.setCreateTime(System.currentTimeMillis());
            requestIp.setReCount(1);
            request.getSession().setAttribute(ip, requestIp);
        } else {
            Long createTime = re.getCreateTime();
            if (null == createTime) {
                System.out.println("时间请求为空");
            } else {
                if (((System.currentTimeMillis() - createTime) / 1000) > 2) {
                    RequestIp requestIp = new RequestIp();
                    requestIp.setCreateTime(System.currentTimeMillis());
                    requestIp.setReCount(1);
                    request.getSession().setAttribute(ip, requestIp);
                } else {
                    if (re.getReCount() > 2) {
                        return false;
                    } else {
                        re.setCreateTime(System.currentTimeMillis());
                        re.setReCount(re.getReCount() + 1);
                        request.getSession().setAttribute(ip, re);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
