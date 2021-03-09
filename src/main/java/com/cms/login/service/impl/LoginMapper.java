package com.cms.login.service.impl;

import com.cms.login.web.UserVo;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("loginMapper")
public interface LoginMapper {

	UserVo selectUserInfo(UserVo vo);
	
}
