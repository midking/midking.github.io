package com.cms.login.web;

import lombok.Data;

@Data
public class UserVo {

	private String userId;
	private String password;
	private String userName;
	private String groupId;
	private String registerDate;
	private String useYn;
	
	private String groupName;
	
}
