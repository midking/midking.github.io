package com.cms.mng.login.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cms.mng.login.service.MngLoginService;

@Controller
public class MngLoginController {
	
	@Resource(name = "mngLoginService")
	private MngLoginService mngLoginService;
	
	@RequestMapping(value = "/index.do")
	public String loginPage() {
		
		return "cms/mng/login/MngLoginPage";
	}

}
