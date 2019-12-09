package com.ecpbm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecpbm.dao.UserInfoDao;
import com.ecpbm.pojo.UserInfo;
import com.ecpbm.service.UserInfoService;
@Service("userInfoService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public UserInfo login(UserInfo ui) {
		// TODO Auto-generated method stub
		return userInfoDao.selectByNameAndPwd(ui);
	}
	@Override
	public int addUserInfo(UserInfo ui) {
		// TODO Auto-generated method stub
		return userInfoDao.saveUserInfo(ui);
	}

}
