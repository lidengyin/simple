package com.ecpbm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.ecpbm.dao.provider.MessageInfoDynaSqlProvider;
import com.ecpbm.pojo.MessageDetail;
import com.ecpbm.pojo.MessageInfo;

public interface MessageInfoDao {

	//������Ϣ���ݷ��ʲ�
	
//	private Integer uid;
//	private String title;
//	private String mtime;
//  private UserInfo ui;
//	private Integer id;
	//��ҳ��ȡ������Ϣ
	@SelectProvider(type = MessageInfoDynaSqlProvider.class, method = "selectWithParams")
	@Results({
		@Result(column = "uid", property = "ui",one = @One(select = "com.ecpbm.dao.UserInfoDao.getUserInfoById", fetchType = FetchType.EAGER)),
		@Result(column = "uid", property = "uid")
	})
	List<MessageInfo> selectByPage( Map <String, Object> params);
	//����������ȡ��������
	@SelectProvider(type = MessageInfoDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object>params);
	//��������������Ϣ
	@Insert("insert into message_info(uid, title , mtime)values(#{uid},#{title},#{mtime})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveMessageInfo(MessageInfo mi);
//	private Integer mid;
//	private String mcontent;
	//����������ϸ��Ϣ
	@Insert("insert into message_detail(mid, mcontent)values(#{mid},#{mcontent})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveMessageDetail(MessageDetail md);
	
	//��������id������Զ���
	@Select("select * from message_info where id = #{id}")
	@Results({
		@Result(column = "uid", property = "ui", one = @One(select = "com.ecpbm.dao.UserInfoDao.getUserInfoById", fetchType = FetchType.EAGER))
		,@Result(column = "uid", property = "uid")
	})
	public MessageInfo getMessageInfoById(int id);
	//��������ID���������ϸ
	@Select("select * from message_detail where mid = #{mid}")
	public List<MessageDetail> getMessageDetailById(int mid);
	
	//��������IDɾ�����������¼
	@Delete("delete from message_info where id = #{id}")
	public void deleteMessageInfoById(int id);
	
	//��������IDɾ��������ϸ��¼
	@Delete("delete from message_detail where mid = #{id}")
	public void deleteMessageDetailById(int id);
	
	
}















