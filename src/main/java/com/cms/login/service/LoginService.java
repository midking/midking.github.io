package com.cms.login.service;

import javax.servlet.http.HttpServletRequest;

import com.cms.login.web.UserVo;

public interface LoginService {

	String selectUserInfo(UserVo vo, HttpServletRequest request);
	
}
