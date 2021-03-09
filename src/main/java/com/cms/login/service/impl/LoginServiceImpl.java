package com.cms.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cms.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;
}
