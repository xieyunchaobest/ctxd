package com.cattsoft.pub.util;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;

/**
 * Title: 服务开通系统<br>
 * Description: 根据SQL状态，拼接SQL<br>
 * Date: 2007-8-24<br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author gongzhen
 */
public class ArcSqlUtil {

	public static Sql generalSql(String head, String sqlSingle, String status) {
		StringBuffer sql = new StringBuffer(sqlSingle);
		int start = sql.indexOf("FROM");
		int end = sql.length();
		if(sql.indexOf("WHERE") !=-1)
			end = sql.indexOf("WHERE");
		String rs = sql.substring(start + 4, end).trim();
		String beforeFrom = sql.substring(0, start + 4);
		String afterWhere = sql.substring(end, sql.length());
		String[] midle = rs.split(",");
		StringBuffer result = new StringBuffer();
		StringBuffer resultTemp = new StringBuffer();
		result.append(beforeFrom).append(" ");
		for (int i = 0; i < midle.length; i++) {
			int start1 = midle[i].trim().indexOf(" ");
			result.append(head).append(midle[i].trim().substring(0, start1));
			result.append(status);
			result.append(midle[i].trim().substring(start1, midle[i].trim().length()));
			if (i != midle.length - 1) {
				result.append(",");
			}
		}
		result.append(" ").append(afterWhere);
		resultTemp.append(" UNION " + result);

		Sql resultSql = new Sql(resultTemp.toString());
		return resultSql;
	}

	/**
	 * 功能:确定查询表状态
	 * 
	 * Method determineSql
	 * 
	 * @param head
	 *            USER
	 * @param sql
	 * @param status
	 *            ARC;HISTORY
	 * @return
	 * @throws AppException
	 */
	public static Sql determineSql(String head, String sql, String status) throws AppException {
		if (sql == null) {
			throw new AppException("100001", "数据传入发生错误！");
		}
		StringBuffer sqlRs = new StringBuffer(sql);
		StringBuffer sqlTemp = new StringBuffer("");
		StringBuffer headTemp = new StringBuffer(head);
		head = headTemp.append(".").toString();

		// 默认(归档表)
		String statusTemp = SysConstants.STATUS_ARC;
		sqlTemp.append(generalSql(head, sqlRs.toString(), statusTemp));
		// 特殊(历史表)
		if (status.equals("1")) {
			statusTemp = SysConstants.STATUS_HIS;
			sqlTemp.append(generalSql(head, sqlRs.toString(), statusTemp));
		}
		// 拼成最后的sql
		sqlRs.append(sqlTemp);
		Sql resultSql = new Sql(sqlRs.toString());
		return resultSql;
	}

}
