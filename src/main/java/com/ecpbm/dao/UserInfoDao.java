package com.ecpbm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.ecpbm.pojo.UserInfo;

public interface UserInfoDao {
	//根据姓名与密码登录
	@Select("select * from user_info where userName = #{userName} and password = #{password}")
	public UserInfo selectByNameAndPwd(UserInfo ui);
	
	//根据id获得对象
	@Select("select * from user_info where id = #{id}")
	public UserInfo getUserInfoById(int id);
	
	@Insert("insert into user_info(userName, password)values(#{userName}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int saveUserInfo(UserInfo ui);
}

