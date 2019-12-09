package com.ecpbm.service;

import com.ecpbm.pojo.UserInfo;

public interface UserInfoService {
	//µÇÂ¼¼ì²â
	public UserInfo login(UserInfo ui);
	//×¢²á¼ì²â
	public int addUserInfo(UserInfo ui);
}
