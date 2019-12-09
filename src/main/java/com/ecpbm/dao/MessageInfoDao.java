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

	//留言信息数据访问层
	
//	private Integer uid;
//	private String title;
//	private String mtime;
//  private UserInfo ui;
//	private Integer id;
	//分页获取留言信息
	@SelectProvider(type = MessageInfoDynaSqlProvider.class, method = "selectWithParams")
	@Results({
		@Result(column = "uid", property = "ui",one = @One(select = "com.ecpbm.dao.UserInfoDao.getUserInfoById", fetchType = FetchType.EAGER)),
		@Result(column = "uid", property = "uid")
	})
	List<MessageInfo> selectByPage( Map <String, Object> params);
	//根据条件获取留言总数
	@SelectProvider(type = MessageInfoDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object>params);
	//保存留言主表信息
	@Insert("insert into message_info(uid, title , mtime)values(#{uid},#{title},#{mtime})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveMessageInfo(MessageInfo mi);
//	private Integer mid;
//	private String mcontent;
	//保存留言明细信息
	@Insert("insert into message_detail(mid, mcontent)values(#{mid},#{mcontent})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int saveMessageDetail(MessageDetail md);
	
	//根据留言id获得留言对象
	@Select("select * from message_info where id = #{id}")
	@Results({
		@Result(column = "uid", property = "ui", one = @One(select = "com.ecpbm.dao.UserInfoDao.getUserInfoById", fetchType = FetchType.EAGER))
		,@Result(column = "uid", property = "uid")
	})
	public MessageInfo getMessageInfoById(int id);
	//根据留言ID获得留言明细
	@Select("select * from message_detail where mid = #{mid}")
	public List<MessageDetail> getMessageDetailById(int mid);
	
	//根据留言ID删除留言主表记录
	@Delete("delete from message_info where id = #{id}")
	public void deleteMessageInfoById(int id);
	
	//根据留言ID删除留言明细记录
	@Delete("delete from message_detail where mid = #{id}")
	public void deleteMessageDetailById(int id);
	
	
}















