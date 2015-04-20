package com.cattsoft.pub.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.cattsoft.pub.LogConfig;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.StringUtil;

/**
 * 
 * Title: 服务开通系统<br>
 * Description: <br>
 * Date: 2007-6-12 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class Sql extends AbstractSql {

	// private static final Logger log = Logger.getLogger(Sql.class);

	// sql语句
	private StringBuffer sqlStr = new StringBuffer();

	public Sql() {

	}

	public Sql(String sql) {
		sqlStr = new StringBuffer(sql);
	}

	/**
	 * 获得标准的带参数sql(?作为参数引用的sql)
	 * 
	 * @param sql
	 */
	public void setSql(String sql) {
		this.sqlStr = new StringBuffer(sql);
		parameters = new HashMap();
		paramIndexs = new ArrayList();
	}

	public void append(String sql) {
		// this.sqlStr.append(" ");
		this.sqlStr.append(sql);
	}

	public void insert(int index, String str) {
		this.sqlStr.insert(index, str);
	}

	/**
	 * 返回 参数转换后的sql（占位符转成?）
	 * 
	 * @return
	 */
	public String getSql() {
		// paramIndexs = orderParameters();
		// sqlStr = new StringBuffer(converSql(sqlStr.toString()));
		String str = this.convertAskParameter(sqlStr.toString());
		return str.toString();

	}

	/**
	 * 返回加分区处理的sql
	 * 
	 * @return
	 */
	public String getPartitionSql(String partitionId) {
		return PartitionUtil.getPartitionSQL(this.getSql(), partitionId);
	}

	/*
	 * public void setList(String name, List value) { name = name.trim();
	 * parameters.put(name, new Parameter(name, Parameter.LIST, value)); }
	 */

	/**
	 * 获取不带参数的sql
	 */
	public String getPureSql() throws AppException {

		// 输出日志的完整sql（不含参数）
		String logSqlStr = sqlStr.toString();
		logSqlStr = this.convertAskParameter(logSqlStr);
		Iterator itor = parameters.keySet().iterator();
		while (itor.hasNext()) {
			String paramName = (String) itor.next();
			Parameter param = (Parameter) parameters.get(paramName);
			logSqlStr = logSqlStr.replaceAll(PARAM_SIGN + paramName + "\\b",
					StringUtil.getRegexStr(param.getSqlStr()));

		}

		return logSqlStr;
	}

	/**
	 * 输出完整不带参数的sql语句
	 * 
	 * @param classObj
	 * @throws AppException
	 */
	public void log(Class classObj) throws AppException {
		if (null == classObj) {
			throw new AppException("100006", "缺少日志输出的指定类！");
		}
		String logSqlStr = getPureSql();
		String className = classObj.getName();
		Logger log = Logger.getLogger(classObj);

		log.debug(StringUtil.getLastStr(className, ".") + ".sql=[ " + logSqlStr
				+ " ]");
	}

	/**
	 * 输出经分区处理过的sql
	 * 
	 * @param partitionId
	 * @param classObj
	 * @throws AppException
	 */
	public void logPartition(String partitionId, Class classObj)
			throws AppException {
		if (null == classObj) {
			throw new AppException("100006", "缺少日志输出的指定类！");
		}
		String className = classObj.getName();
		String logSqlStr = getPureSql();
		logSqlStr = PartitionUtil.getPartitionSQL(logSqlStr, partitionId);

		Logger log = Logger.getLogger(classObj);
		log.debug(StringUtil.getLastStr(className, ".") + "-" + logSqlStr);
	}

	/**
	 * 根据paramIndexs的参数顺序，从parameters里取出对应的值，给ps设置参数值
	 * 
	 * @param ps
	 * @return
	 * @throws SysException
	 */
	public PreparedStatement fillParams(PreparedStatement ps)
			throws AppException, SysException {
		if (null == ps)
			throw new AppException("100007", "缺少设置参数的PreparedStatement对象！");

		// 将sql中的参数排序
		paramIndexs = this.orderParameters();
		// 没有参数，直接返回
		if (null == paramIndexs)
			return ps;

		for (int i = 0; i < paramIndexs.size(); i++) {
			Parameter param = (Parameter) parameters.get(paramIndexs.get(i));
			if (null == param)
				throw new AppException("100008", "sql语句缺少参数"
						+ paramIndexs.get(i) + "设置!");
			ps = param.fillParam(ps, i + 1);
		}
		return ps;
	}

	public void clearParameters() {
		this.parameters = new HashMap();
	}

	/**
	 * 转换sql（占位符转成?）
	 * 
	 * @return
	 */
	public String convertSql(String converdSql) {
		if (null == converdSql || "".equals(converdSql)) {
			return null;
		}

		converdSql = convertAskParameter(converdSql);
		for (int i = 0; i < paramIndexs.size(); i++) {
			String paramName = (String) paramIndexs.get(i);
			converdSql = converdSql.replaceAll(PARAM_SIGN + paramName + "\\b",
					REF_SIGN);// \b匹配单词结尾
		}

		return converdSql;
	}

	/**
	 * 将sql中的占位符按顺序取出
	 * 
	 * @return
	 */
	public List orderParameters() {
		List paramsOrder = new ArrayList();
		sqlStr = new StringBuffer(this.convertAskParameter(sqlStr.toString()));
		if (sqlStr != null) {
			int index = sqlStr.toString().indexOf(PARAM_SIGN);
			// 无占位符
			if (-1 == index) {
				return null;
			}
			String[] ary = sqlStr.substring(index + 1).split(PARAM_SIGN);
			for (int i = 0; i < ary.length; i++) {
				String[] temp = ary[i].split("\\W");
				paramsOrder.add(temp[0]);
			}
		}

		return paramsOrder;

	}

	/**
	 * 将sql中的?参数转换成系统定义占位符的参数
	 * 
	 * @return
	 */
	public String convertAskParameter(String sql) {
		int step = 1;// ？参数的序号
		int index = 0;// 从第0位开始
		while (index >= 0) {
			index = sql.indexOf(REF_SIGN, index);
			sql = sql.replaceFirst("\\" + REF_SIGN, ":" + SYS_PARAMETER_PREFIX
					+ step);
			step++;
		}

		return sql;
	}

	/**
	 * 按指定长度，截取最后length长的字符串
	 * 
	 * @param length
	 */
	public void removeSuffix(int length) {
		sqlStr = new StringBuffer(sqlStr.substring(0, sqlStr.length() - length));
	}

	public Object clone() {
		Sql cloneSql = new Sql();
		cloneSql.setSqlStr(sqlStr.toString());
		cloneSql.setParameters(parameters);
		cloneSql.setParamIndexs(paramIndexs);
		return cloneSql;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public List getParamIndexs() {
		paramIndexs = this.orderParameters();
		return paramIndexs;
	}

	public void setParamIndexs(List paramIndexs) {
		this.paramIndexs = paramIndexs;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = new StringBuffer(sqlStr);
	}

	public static void main(String[] args) throws AppException {
		LogConfig.init();
		Sql sql = new Sql();
		// sql.append("select dd,bb , zz,aa from WHERE a=? and b=? STS=:STS1 and
		// c=? AND STSDATE =:STSDATE ");
		sql
				.append("select a.CO_NBR,a.CHANNEL_ID,a.CO_METH,a.WORK_AREA_ID,a.STAFF_ID,a.AREA_ID,c.name workName,a.LOCAL_NET_ID,a.APPL_DATE,b.so_nbr,a.contact_info,a.contact_name, d.cso_nbr from CO a, so b, work_area c,cso_item d,cso_item_co e where 1=1 and a.work_area_id=c.work_area_id and a.co_nbr=e.co_nbr and d.cso_item_id = e.cso_item_id and a.co_nbr=b.co_nbr and a.co_nbr=?");
		// sql.append("select * from WHERE ");
		sql.setString(1, "aValue");
		sql.setString(2, "bValue");
		sql.setString("STS1", "sts1Value");
		sql.setString(3, "*%&amp;$^@^**@#^&amp;^$%^^%$#");
		sql.setString("STSDATE", "stsDateValue");
		// System.out.println(sql.getSql().replaceAll(":STS1\\b", "1"));

		// System.out.println(sql.getSql().replaceAll(":STS1\\b", "1"));
		// System.out.println(sql.convertAskParameter(sql.getSql()));
		// System.out.println(sql.getPartitionSql("1"));
		// sql.log(Sql.class);
		// String str = "for the wise";
		// System.out.println(str.replaceAll("\\<the", "11"));

		String s = "select a.CO_NBR,a.CHANNEL_ID,a.CO_METH,a.WORK_AREA_ID,a.STAFF_ID,a.AREA_ID,c.name workName,a.LOCAL_NET_ID,a.APPL_DATE,b.so_nbr,a.contact_info,a.contact_name, d.cso_nbr from CO a, so b, work_area c,cso_item d,cso_item_co e where 1=1 and a.work_area_id=c.work_area_id and a.co_nbr=e.co_nbr and d.cso_item_id = e.cso_item_id and a.co_nbr=b.co_nbr and a.co_nbr=:SYS_PARAMETER_1";
		String str = PartitionUtil.getPartitionSQL(s, "1");
		//System.out.println(str);

	}


}
