package com.cms.login.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cms.login.service.LoginService;
import com.cms.login.web.UserVo;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;
	
	@Override
	public String selectUserInfo(UserVo vo, HttpServletRequest request) {
		//int result = 0;
		String result = "";
		
		UserVo info = loginMapper.selectUserInfo(vo);
		
		// 결과값이 받아와 졌다면 로그인 성공
		if(info != null) {
			System.out.println(info);
			HttpSession session = request.getSession();
			session.setAttribute("userVo", info);
			result = info.getUserName();
		}
		//System.out.println(result);
		
		return result;
	}
}
