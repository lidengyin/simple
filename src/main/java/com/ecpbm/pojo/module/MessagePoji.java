package com.ecpbm.pojo.module;



import java.util.List;

import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;

public class MessagePoji {

	//������Ϣ
	private MessageInfo messageinfo;
	//������ϸ
	private MessageDetail messagedetail;
	public MessageDetail getMessagedetail() {
		return messagedetail;
	}
	public void setMessagedetail(MessageDetail messagedetail) {
		this.messagedetail = messagedetail;
	}
	//
	public MessageInfo getMessageinfo() {
		return messageinfo;
	}
	public void setMessageinfo(MessageInfo messageinfo) {
		this.messageinfo = messageinfo;
	}

}
