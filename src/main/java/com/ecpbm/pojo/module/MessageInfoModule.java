package com.ecpbm.pojo.module;

import java.util.List;

import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;

public class MessageInfoModule {
	private MessageDetail detail;
	private String name;
	private Integer id;
	private List<MessageInfo> msgInfos;
	
	public MessageDetail getDetail() {
		return detail;
	}

	public void setDetail(MessageDetail detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<MessageInfo> getMsgInfos() {
		return msgInfos;
	}

	public void setMsgInfos(List<MessageInfo> msgInfos) {
		this.msgInfos = msgInfos;
	}
	
}
