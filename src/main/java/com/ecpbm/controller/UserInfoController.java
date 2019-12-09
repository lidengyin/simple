package com.ecpbm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ecpbm.pojo.UserInfo;
import com.ecpbm.service.UserInfoService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonParseException;

//@SessionAttributes(value = "admin")
@Controller
@RequestMapping(value = "admininfo")
public class UserInfoController {
	@Autowired
	private UserInfoService userInfoService;
	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String, Object> login(UserInfo ui, ModelMap model) {
		//UserInfo ui = new UserInfo();
		//ui.setUserName(userName);
		//ui.setPassword(password);
		System.out.println("ss");
		Map<String, Object> result = new HashMap<String, Object>();
		int flag = 0;
		UserInfo userinfo = userInfoService.login(ui);
		if (userinfo != null) {
			System.out.println("name = " + userinfo.getUserName() + " password =" + userinfo.getPassword());
			model.put("admin", userinfo);
			flag = 1;
			result.put("userinfo", userinfo);
			result.put("flag", flag);
		} else {
			result.put("flag", flag);
		}		
		return result;
	}
	@RequestMapping(value = "/register")
	@ResponseBody
	public String registr(UserInfo userinfo)
			throws JsonParseException, JsonMappingException, IOException, NullPointerException {
		try {
			userInfoService.addUserInfo(userinfo);
			return "{\"flag\": \"1\"}";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return "{\"flag\": \"0\"}";
		}
	}
}
