package com.cms.login.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.login.service.LoginService;

@Controller
public class LoginController {
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	@RequestMapping(value = "/index.do")
	public String loginPage() {
		
		return "cms/login/login";
	}

}
