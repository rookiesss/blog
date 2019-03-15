package com.java1234.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.java1234.annotation.SystemLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java1234.entity.Blogger;
import com.java1234.service.BloggerService;
import com.java1234.util.CryptographyUtil;

/**
 * ����Controller��
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	@SystemLog(description = "��¼��ϵͳ")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "java1234"));
		try{
			subject.login(token); // ��¼��֤		
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "�û��������������");
			return "login";
		}
	}
	
	/**
	 * ���ڲ���
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "���ڲ���_java��Դ����ϵͳ");
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}
}