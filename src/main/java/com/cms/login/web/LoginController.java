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
	
	/**
	 * 메인 페이지 (로그인 화면)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String loginPage(HttpServletRequest request) {
		
		/**
		 *  로그인 세션값이 있으면 메인 페이지로 이동
		 *  세션값이 없는 경우 로그인 페이지로 이동
		 */
		if(request.getSession().getAttribute("userVo") != null) {
			System.out.println("로그인중..");
			return "cms/login/login";			// TODO: 추가 페이지 나올 시 해당 페이지로 이동하도록 변경
		} else {
			System.out.println("비로그인..");
			return "cms/login/login";			
		}
	}
	
	/**
	 * 로그인 처리 후 사용자 이름 비동기 리턴
	 * @author yoojinkim
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login.do")
	public @ResponseBody String doLogin(UserVo vo, HttpServletRequest request) {
		return loginService.selectUserInfo(vo, request);
	}
}
