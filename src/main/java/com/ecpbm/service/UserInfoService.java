package com.ecpbm.service;

import com.ecpbm.pojo.UserInfo;

public interface UserInfoService {
	//��¼���
	public UserInfo login(UserInfo ui);
	//ע����
	public int addUserInfo(UserInfo ui);
}
