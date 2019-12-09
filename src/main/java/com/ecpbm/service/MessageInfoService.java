package com.ecpbm.service;

import java.util.List;
import java.util.Map;

import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;
import com.ecpbm.pojo.Pager;

public interface MessageInfoService {

	//��ҳ��ʾ����
		List<MessageInfo> findMessageInfo(MessageInfo messageInfo, Pager pager);
	//liuyan����
		Integer count(Map<String, Object> params);
	//�����������
		 public int addMessageInfo(MessageInfo mi);
	//���������ϸ
		public int addMessageDetaail(MessageDetail md);
	//��������ID���������Ϣ
		public MessageInfo getMessageInfoById(int id);
	//��������ID���������ϸ
		public List<MessageDetail> getMessageDetailById(int mid);
	//��������IDɾ������
		public int deleteMessage(int id);
	//��������IDɾ������
		public int deleteMessageDetail(int id);
	
			
}
