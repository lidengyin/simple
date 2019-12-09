package com.ecpbm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ecpbm.pojo.MessageInfo;
import com.ecpbm.pojo.Pager;

public class MessageInfoDynaSqlProvider {
	//����������ҳ��ѯ
	public String selectWithParams(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("message_info");
				if(params.get("messageInfo")!= null && !"".equals(params.get("messageInfo"))) {
					MessageInfo messageInfo = (MessageInfo)params.get("messageInfo");
					//���ʹ�ö�����ž��Ǿ�ȷ��ѯ,ֻ��ҪwhereID����
					if(messageInfo.getId() != null && messageInfo.getId() > 0) {
						System.out.println("��ȷ��ѯ");
						WHERE("id = #{id}");
					}
					//����ģ����ѯ
					else {
						//������Ŀģ����ѯ,��ʱֻʵ��һ��
						if(messageInfo.getTitle() != null && !messageInfo.getTitle().equals("")) {
							
							System.out.println("ģ����ѯ");
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
	//���������������
	public String count(Map<String, Object> params) {
		String  sql =  new SQL() {
			{
				SELECT("count(*)");
				FROM("message_info");
				if(params.get("messageInfo")!= null && !"".equals(params.get("messageInfo"))) {
					MessageInfo messageInfo = (MessageInfo)params.get("messageInfo");
					//���ʹ�ö�����ž��Ǿ�ȷ��ѯ,ֻ��ҪwhereID����
					System.out.println("��ȷ��ѯ����ģ����ѯ");
					if(messageInfo.getId() != null && messageInfo.getId() > 0) {
						System.out.println("��ȷ��ѯ");
						WHERE("id = #{id}");
					}
					//����ģ����ѯ
					else {
						//������Ŀģ����ѯ,��ʱֻʵ��һ��
						if(messageInfo.getTitle() != null && !messageInfo.getTitle().equals("")) {
							System.out.println("ģ����ѯ");
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
