package com.cms.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.login.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@RequestMapping(value = "/index.do")
	public String loginPage(HttpServletRequest request) {
		
		// 기존 세션값 있는지 체크 후 있으면 페이지 이동
		if(request.getSession().getAttribute("userVo") != null) {
			// 메인페이지로 이동
			System.out.println("로그인중..");
			return "cms/login/login";			
		} else {
			System.out.println("비로그인..");
			return "cms/login/login";			
		}
	}
	
	@RequestMapping(value = "/login.do")
	public @ResponseBody String doLogin(UserVo vo, HttpServletRequest request) {
		return loginService.selectUserInfo(vo, request);
	}
}
