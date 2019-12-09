package com.ecpbm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ecpbm.pojo.MessageInfo;
import com.ecpbm.pojo.Pager;

public class MessageInfoDynaSqlProvider {
	//根据条件分页查询
	public String selectWithParams(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("message_info");
				if(params.get("messageInfo")!= null && !"".equals(params.get("messageInfo"))) {
					MessageInfo messageInfo = (MessageInfo)params.get("messageInfo");
					//如果使用订单编号就是精确查询,只需要whereID即可
					if(messageInfo.getId() != null && messageInfo.getId() > 0) {
						System.out.println("精确查询");
						WHERE("id = #{id}");
					}
					//否则模糊查询
					else {
						//根据题目模糊查询,暂时只实现一个
						if(messageInfo.getTitle() != null && !messageInfo.getTitle().equals("")) {
							
							System.out.println("模糊查询");
							WHERE("title LIKE CONCAT ('%',#{messageInfo.title},'%')");
						}
					}
				}
			}
		}.toString();
		if(params.get("pager") != null && !params.get("pager").equals("") ) {
			Pager pager = (Pager) params.get("pager");
			if(pager.getDesc() == 0) {
				sql += " order by id desc limit #{pager.firstLimitPage}, #{pager.perPageRows}";
			}
			else {
				sql += " order by id asc limit #{pager.firstLimitPage}, #{pager.perPageRows}";
			}
		}
		System.out.println("rows" + sql);
		return sql;
	}
	//获得满足条件总数
	public String count(Map<String, Object> params) {
		String  sql =  new SQL() {
			{
				SELECT("count(*)");
				FROM("message_info");
				if(params.get("messageInfo")!= null && !"".equals(params.get("messageInfo"))) {
					MessageInfo messageInfo = (MessageInfo)params.get("messageInfo");
					//如果使用订单编号就是精确查询,只需要whereID即可
					System.out.println("精确查询还是模糊查询");
					if(messageInfo.getId() != null && messageInfo.getId() > 0) {
						System.out.println("精确查询");
						WHERE("id = #{id}");
					}
					//否则模糊查询
					else {
						//根据题目模糊查询,暂时只实现一个
						if(messageInfo.getTitle() != null && !messageInfo.getTitle().equals("")) {
							System.out.println("模糊查询");
							WHERE("title LIKE CONCAT ('%',#{messageInfo.title},'%')");
						}
					}
				}
			}
			
		}.toString();
		System.out.println("count : " + sql);
		return sql;
	}
}
