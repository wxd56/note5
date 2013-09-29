package com.wxd.note5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wxd.note5.model.WebConstants;

@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping("showLoginUI.do")
	public String loginUI(HttpServletRequest req){		
		return "login";
	}
	
	
	/**
	 * 进行登录验证
	 */
	@RequestMapping("doLogin.do")
	public String doLogin(HttpServletRequest req,HttpServletResponse resp){
		String psw = req.getParameter("password"); 
		//登陆成功
		if(psw.equals("jianingli274603")){
			req.getSession().setAttribute(WebConstants.ACCESS_TOKEN, "OK");
			return "index";
		}
		
		//登录失败，返回404
		resp.setStatus(404);
		return null;
	}
}
