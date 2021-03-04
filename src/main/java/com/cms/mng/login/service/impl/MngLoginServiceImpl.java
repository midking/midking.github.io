package com.cms.mng.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cms.mng.login.service.MngLoginService;

@Service("mngLoginService")
public class MngLoginServiceImpl implements MngLoginService {

	@Resource(name = "mngLoginMapper")
	private MngLoginMapper mngLoginMapper;
}
