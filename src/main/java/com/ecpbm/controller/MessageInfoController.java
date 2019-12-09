package com.ecpbm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;
import com.ecpbm.pojo.Pager;
import com.ecpbm.pojo.module.MessagePoji;
import com.ecpbm.service.MessageInfoService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonParseException;

@Controller
@RequestMapping("/messageinfo")

public class MessageInfoController {

	@Autowired
	private MessageInfoService messageInfoService;
	//分页显示留言记录
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(String page, String rows, String title, String desc){
		MessageInfo messageInfo = new MessageInfo();
		
		messageInfo.setTitle(title);
		//封装分页对象
		Pager pager = new Pager();
		//封装查询条件
		Map<String, Object>params= new HashMap<String, Object>();
		params.put("messageInfo", messageInfo);
		//获得列表总数
		int totalCount = messageInfoService.count(params);
		System.out.println("totalCount:" + totalCount);
		try {
			//设置是否正序
			pager.setDesc(Integer.parseInt(desc));
			pager.setCurPager(Integer.parseInt(page));
			pager.setPerPageRows(Integer.parseInt(rows));	
		//蝴蝶满足条件的列表
		List<MessageInfo> messageinfos = messageInfoService.findMessageInfo(messageInfo, pager);
		//封装返回结果
		Map<String, Object>result = new HashMap<String, Object>();
		result.put("total", totalCount);
		result.put("rows", messageinfos);
		//列表显示
		
			System.out.println("列表显示");
			
			for(MessageInfo info: messageinfos) {
				System.out.println("title" + info.getTitle());
			}
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
		
		
	}
	
	
	@PostMapping("/commitmessage")
	@ResponseBody
//新建留言(保存留言)
	public String commitOrder(@RequestBody MessagePoji infos)throws JsonParseException, 
	JsonMappingException, IOException{
		try {
			MessageInfo mi = infos.getMessageinfo();
			System.out.println("mi.title " + mi.getTitle());
			messageInfoService.addMessageInfo(mi);
			
			MessageDetail md = infos.getMessagedetail();
			md.setMid(mi.getId());
			messageInfoService.addMessageDetaail(md);
			return "{\"flag\":\"1\"}";  
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return "{\"flag\":\"0\"}";
		}
	}
	
	//批量删除留言
	@RequestMapping(value = "/deletemessage")
	@ResponseBody
	public String deleteMessageInfo(String mids) {
		try {
			mids = mids.substring(0, mids.length() - 1);
			String[]ids = mids.split(",");
			for(String id :ids) {
				messageInfoService.deleteMessage(Integer.parseInt(id));
			}
			for(String id :ids) {
				messageInfoService.deleteMessageDetail(Integer.parseInt(id));
			}
			
			return "{\"flag\":\"1\"}";
		}catch(Exception e){
		
			e.printStackTrace();
			return "{\"flag\":\"0\"}";
		}
	}
	//根据留言ID获得留言对象
	@RequestMapping(value = "/getmessageinfo")
	@ResponseBody
	public MessageInfo  getMessageInfo(String mid) {
		MessageInfo mi = messageInfoService.getMessageInfoById(Integer.parseInt(mid));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("mi", mi);
		System.out.println("mi: "+ mi.getTitle());
		return mi;
		
	}
	//根据留言ID获得留言明细
	@RequestMapping(value = "/getmessagedetail")
	@ResponseBody
	public List<MessageDetail> getMessageDetail(String mid){
		List<MessageDetail> mds = messageInfoService.getMessageDetailById(Integer.parseInt(mid));
		if(mds != null) {
			System.out.println("返回成功");
			for(MessageDetail md : mds) {
				System.out.println("md: " + md.getMcontent());
			}
		}
		return mds;
	}
	//添加留言评论
	@RequestMapping(value = "/writecomment")
	@ResponseBody
	public String writeComment(String mid, String mcontent) {
		try {
			MessageDetail md = new MessageDetail();
			md.setMcontent(mcontent);
			md.setMid(Integer.parseInt(mid));
			messageInfoService.addMessageDetaail(md);
			return "{\"flag\": \"1\"}";
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return "{\"flag\":\"0\"}";
		}
	}
}










