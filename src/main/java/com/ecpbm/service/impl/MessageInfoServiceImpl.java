package com.ecpbm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecpbm.dao.MessageInfoDao;
import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;
import com.ecpbm.pojo.Pager;
import com.ecpbm.service.MessageInfoService;
@Service("messageInfoService")
public class MessageInfoServiceImpl implements MessageInfoService{
	
	@Autowired
	private MessageInfoDao messageInfoDao;
	@Override
	public List<MessageInfo> findMessageInfo(MessageInfo messageInfo, Pager pager) {
		// TODO Auto-generated method stub
		//封装查询条件
		Map<String, Object>params = new HashMap<String, Object>();
		params.put("messageInfo", messageInfo);
		//获得满足条件的总数
		int totalCount = messageInfoDao.count(params);
		if(totalCount > 0) {
			pager.setRowCount(totalCount);
			params.put("pager", pager);
		}
		return messageInfoDao.selectByPage(params);
	}

	@Override
	public Integer count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return messageInfoDao.count(params);
	}

	@Override
	public int addMessageInfo(MessageInfo mi) {
		// TODO Auto-generated method stub
		return messageInfoDao.saveMessageInfo(mi);
	}

	@Override
	public int addMessageDetaail(MessageDetail md) {
		// TODO Auto-generated method stub
		return messageInfoDao.saveMessageDetail(md);
	}

	@Override
	public MessageInfo getMessageInfoById(int id) {
		// TODO Auto-generated method stub
		return messageInfoDao.getMessageInfoById(id);
	}

	@Override
	public List<MessageDetail> getMessageDetailById(int mid) {
		// TODO Auto-generated method stub
		return messageInfoDao.getMessageDetailById(mid);
	}

	@Override
	public int deleteMessage(int id) {
		// TODO Auto-generated method stub
		int result = 1;
		try {
			
			messageInfoDao.deleteMessageDetailById(id);
			//messageInfoDao.deleteMessageInfoById(id);
			
		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public int deleteMessageDetail(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int result = 1;
				try {
					
					//messageInfoDao.deleteMessageDetailById(id);
					messageInfoDao.deleteMessageInfoById(id);
					
				}catch(Exception e) {
					e.printStackTrace();
					result = 0;
				}
				return result;
	}

}
