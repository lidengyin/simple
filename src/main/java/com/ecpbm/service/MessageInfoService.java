package com.ecpbm.service;

import java.util.List;
import java.util.Map;

import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;
import com.ecpbm.pojo.Pager;

public interface MessageInfoService {

	//分页显示留言
		List<MessageInfo> findMessageInfo(MessageInfo messageInfo, Pager pager);
	//liuyan计数
		Integer count(Map<String, Object> params);
	//添加留言主表
		 public int addMessageInfo(MessageInfo mi);
	//添加留言明细
		public int addMessageDetaail(MessageDetail md);
	//根据留言ID获得留言信息
		public MessageInfo getMessageInfoById(int id);
	//根据留言ID获得留言明细
		public List<MessageDetail> getMessageDetailById(int mid);
	//根据留言ID删除留言
		public int deleteMessage(int id);
	//根据留言ID删除评论
		public int deleteMessageDetail(int id);
	
			
}
