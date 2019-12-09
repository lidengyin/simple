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
	//��ҳ��ʾ���Լ�¼
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(String page, String rows, String title, String desc){
		MessageInfo messageInfo = new MessageInfo();
		
		messageInfo.setTitle(title);
		//��װ��ҳ����
		Pager pager = new Pager();
		//��װ��ѯ����
		Map<String, Object>params= new HashMap<String, Object>();
		params.put("messageInfo", messageInfo);
		//����б�����
		int totalCount = messageInfoService.count(params);
		System.out.println("totalCount:" + totalCount);
		try {
			//�����Ƿ�����
			pager.setDesc(Integer.parseInt(desc));
			pager.setCurPager(Integer.parseInt(page));
			pager.setPerPageRows(Integer.parseInt(rows));	
		//���������������б�
		List<MessageInfo> messageinfos = messageInfoService.findMessageInfo(messageInfo, pager);
		//��װ���ؽ��
		Map<String, Object>result = new HashMap<String, Object>();
		result.put("total", totalCount);
		result.put("rows", messageinfos);
		//�б���ʾ
		
			System.out.println("�б���ʾ");
			
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
//�½�����(��������)
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
	
	//����ɾ������
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
	//��������ID������Զ���
	@RequestMapping(value = "/getmessageinfo")
	@ResponseBody
	public MessageInfo  getMessageInfo(String mid) {
		MessageInfo mi = messageInfoService.getMessageInfoById(Integer.parseInt(mid));
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("mi", mi);
		System.out.println("mi: "+ mi.getTitle());
		return mi;
		
	}
	//��������ID���������ϸ
	@RequestMapping(value = "/getmessagedetail")
	@ResponseBody
	public List<MessageDetail> getMessageDetail(String mid){
		List<MessageDetail> mds = messageInfoService.getMessageDetailById(Integer.parseInt(mid));
		if(mds != null) {
			System.out.println("���سɹ�");
			for(MessageDetail md : mds) {
				System.out.println("md: " + md.getMcontent());
			}
		}
		return mds;
	}
	//�����������
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










