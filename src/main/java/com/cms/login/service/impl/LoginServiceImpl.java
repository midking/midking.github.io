package com.cms.login.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cms.common.util.Sha512Util;
import com.cms.login.service.LoginService;
import com.cms.login.web.UserVo;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;
	
	/**
	 * 회원 로그인 처리
	 * - 입력한 아이디, 비밀번호를 파라미터로 하여 DB를 조회해서 결과값이 있다면 로그인 성공
	 * - 로그인 성공 시 세션에 userVo를 담고, 리턴값으로 사용자명을 넘겨 로그인 성공 메시지에 사용
	 * 
	 * @author yoojinkim 
	 * @param vo
	 * @param request
	 * @return userName
	 */
	@Override
	public String selectUserInfo(UserVo vo, HttpServletRequest request) {
		String result = "";
		
		// 입력한 패스워드를 암호화해 DB값과 일치하는지 확인
		String encPassword = Sha512Util.getSecurePassword(vo.getPassword(), vo.getUserId());
		vo.setPassword(encPassword);
		UserVo info = loginMapper.selectUserInfo(vo);
		
		/**
		 *  입력한 아이디, 비밀번호에 대한 결과값이 있다면 로그인 성공
		 *  세션에 userVo를 담고 리턴값에 사용자 이름을 담아줌
		 */
		if(info != null) {
			//System.out.println(info);
			HttpSession session = request.getSession();
			session.setAttribute("userVo", info);
			result = info.getUserName();
		}
		
		return result;
	}
}
