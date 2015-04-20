package com.cattsoft.pub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

//import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;

/**
 * 
 * Title: CRM系统<br>
 * Description: <br>
 * Date: 2007-6-6 <br>
 * Copyright (c) 2007 CATTSoft<br>
 * 
 * @author liaoyh
 */
public class MaxId {
//	private static Logger log = Logger.getLogger(MaxId.class);

	/**
	 * MaxId
	 */
	public MaxId() {
	}

	/**
	 * getSequenceNextVal:得到下一个Sequence
	 * 
	 * @param columnName
	 *            String
	 * @throws AppException
	 * @throws SysException
	 * @throws NamingException
	 * @return long
	 * @throws AppException
	 */
	public static String getSequenceNextVal(String seqName) throws SysException, AppException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		long seq = 0;
		Sql sql = new Sql();
		sql.append("select " + seqName + "_SEQ.nextval from dual");
		// sql.setString("seqName", seqName);
		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(MaxId.class);
			rs = ps.executeQuery();
			if (rs.next()) {
				seq = rs.getLong(1);
				// System.out.println(columnName + "_SEQ is :" + seq);
			}

		} catch (SQLException ex) {
			throw new SysException(SysConstants.FAIL_REASON_SPS_SYS_ERROR, "查找字段名" + seqName + "的最大值失败", ex);
		} finally {

			JdbcUtil.close(rs, ps);

		}
		String str = Long.toString(seq);
		return str;

	}

	/**
	 * getMaxIdFromTable:从某表中根据条件得到指定字段的最大值
	 * 
	 * @param tableName
	 *            String
	 * @param columnName
	 *            String
	 * @param condition
	 *            String
	 * @throws AppException
	 * @throws SysException
	 * @throws NamingException
	 * @return long
	 * @throws AppException
	 */
	public static Long getMaxIdFromTable(String tableName, String columnName, String condition)
			throws SysException, AppException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Long seq = null;
		Sql sql = new Sql();
		sql.append("select nvl(max(");
		sql.append(columnName);
		sql.append("),0) from ");
		sql.append(tableName);

		if (!StringUtil.isBlank(condition)) {
			sql.append(" where ");
			sql.append(condition);

		}
		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.log(MaxId.class);
			rs = ps.executeQuery();
			if (rs.next()) {
				seq = new Long(rs.getLong(1));
				// //System.out.println("max_id is :" + seq);
			}

		} catch (SQLException ex) {
			throw new SysException("", "查找" + tableName + "表字段" + columnName + "的最大值失败" + "sql is "
					+ sql, ex);
		}

		finally {

			JdbcUtil.close(rs, ps);

		}
		return seq;

	}

	/**
	 * getMaxIdFromTable:从MaxIds表中根据条件得到指定字段的最大值
	 * 
	 * @param tableName
	 *            String
	 * @param columnName
	 *            String
	 * @param condition
	 *            String
	 * @throws AppException
	 * @throws SysException
	 * @throws NamingException
	 * @return long
	 * @throws AppException
	 */
	public static synchronized Long getMaxIdFromMaxIdx(String tableName, String columnName,
			String prefix, String spAreaId) throws SysException, AppException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Long seq = null;
		Sql sql = new Sql();
		sql.append("select MAX_ID");
		sql.append(" from max_ids");

		if (!StringUtil.isBlank(tableName)) {
			sql.append(" where TABLE_NAME=:tableName");
			sql.setString("tableName", tableName);
		}
		if (!StringUtil.isBlank(columnName)) {
			sql.append(" and COLUMN_NAME=:columnName");
			sql.setString("columnName", columnName);
		}
		if (!StringUtil.isBlank(prefix)) {
			sql.append(" and PREFIX=:prefix");
			sql.setString("prefix", prefix);
		} else
			sql.append(" and PREFIX='UNABLE'");
		if (!StringUtil.isBlank(spAreaId)) {
			sql.append(" and SP_AREA_ID=:spAreaId");
			sql.setString("spAreaId", spAreaId);
		} else
			sql.append(" and SP_AREA_ID=0");
		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(MaxId.class);
			rs = ps.executeQuery();
			if (rs.next()) {
				seq = new Long(rs.getLong(1) + 1);
				updateMaxIds(tableName, columnName, prefix, spAreaId, seq);
			} else {
				seq = new Long(2000);
				saveMaxIdx(tableName, columnName, prefix, spAreaId, seq);
			}
		} catch (SQLException ex) {
			throw new SysException("", "在max_ids表中查找" + tableName + "表字段" + columnName + "的最大值失败"
					+ "sql is " + sql, ex);
		}

		finally {

			JdbcUtil.close(rs, ps);

		}
		return seq;

	}

	/**
	 * 从MaxIds表中根据条件保存指定字段的最大值
	 * 
	 * @param tableName
	 * @param columnName
	 * @param prefix
	 * @param spAreaId
	 * @throws AppException
	 * @throws SysException
	 */
	public static void saveMaxIdx(String tableName, String columnName, String prefix,
			String spAreaId, Long maxId) throws AppException, SysException {
		PreparedStatement ps = null;
		Connection conn = null;
		Sql sql = new Sql();
		sql.append("insert into MAX_IDS(TABLE_NAME,COLUMN_NAME,SP_AREA_ID,MAX_ID,PREFIX)");
		sql.append("VALUES (:tableName,:columnName,:spAreaId,:maxId,:prefix)");
		if (!StringUtil.isBlank(tableName)) {
			sql.setString("tableName", tableName);
		}
		if (!StringUtil.isBlank(columnName)) {
			sql.setString("columnName", columnName);
		}
		if (!StringUtil.isBlank(prefix)) {
			sql.setString("prefix", prefix);
		} else
			sql.setString("prefix", "UNABLE");
		if (!StringUtil.isBlank(spAreaId)) {
			sql.setString("spAreaId", spAreaId);
		} else
			sql.setLong("spAreaId", "0");
		if (maxId != null) {
			sql.setString("maxId", maxId.toString());
		} else
			throw new AppException("", "缺少maxId参数！");
		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(MaxId.class);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new SysException("", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public static void updateMaxIds(String tableName, String columnName, String prefix,
			String spAreaId, Long maxId) throws AppException, SysException {
		PreparedStatement ps = null;
		Connection conn = null;
		Sql sql = new Sql();
		sql.append("update MAX_IDS m set ");
		if (maxId != null) {
			sql.append("m.MAX_ID=:maxId where 1=1");
			sql.setString("maxId", maxId.toString());
		} else
			throw new AppException("", "缺少maxId参数!");
		if (!StringUtil.isBlank(tableName)) {
			sql.append(" and TABLE_NAME=:tableName");
			sql.setString("tableName", tableName);
		} else
			throw new AppException("", "缺少tableName参数!");
		if (!StringUtil.isBlank(columnName)) {
			sql.append(" and COLUMN_NAME=:columnName");
			sql.setString("columnName", columnName);
		} else
			throw new AppException("", "缺少columnName参数!");
		if (!StringUtil.isBlank(prefix)) {
			sql.append(" and PREFIX=:prefix");
			sql.setString("prefix", prefix);
		} else {
			sql.append(" and PREFIX=:prefix");
			sql.setString("prefix", "UNABLE");
		}
		if (!StringUtil.isBlank(spAreaId)) {
			sql.append(" and SP_AREA_ID=:spAreaId");
			sql.setString("spAreaId", spAreaId);
		} else {
			sql.append(" and SP_AREA_ID=:spAreaId");
			sql.setLong("spAreaId", "0");
		}
		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(MaxId.class);
			ps.executeUpdate();
		} catch (SQLException se) {
			throw new SysException("", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * <p>
	 * 使用方法示例1:getMaxId,取最大值(一般情况下都用这个方法)
	 * </p>
	 * <p>
	 * 说明:
	 * </p>
	 * <p>
	 * 如果是oracle请确认已经建好Sequence
	 * </p>
	 * <p>
	 * 如果是Sybase请确认在MAX_IDS表中已经有相关数据
	 * </p>
	 * <p>
	 * long commServSpecId ;
	 * </p>
	 * <p>
	 * MaxId maxId = new MaxId() ;
	 * </p>
	 * <p>
	 * commServSpecId = maxId.getMaxId("comm_serv_spec_id");
	 * </p>
	 * <p>
	 * .......
	 * </p>
	 * 
	 * 
	 * 
	 * <p>
	 * 使用方法示例2:getMaxAreaId,取最大值(工单打印时按照区域取最大值)
	 * </p>
	 * <p>
	 * 说明:
	 * </p>
	 * <p>
	 * 请确认在MAX_IDS表中已经有相关数据
	 * </p>
	 * 
	 * <p>
	 * long printNbr ;
	 * </p>
	 * <p>
	 * MaxId maxId = new MaxId() ;
	 * </p>
	 * <p>
	 * printNbr = maxId.getMaxAreaId("PRINT_NBR","10501");
	 * </p>
	 * 
	 * 
	 * 
	 * <p>
	 * 使用方法示例2:getMaxIdFromTable,从某表中根据条件得到指定字段的最大值
	 * </p>
	 * 
	 * <p>
	 * long prodId ;
	 * </p>
	 * <p>
	 * MaxId maxId = new MaxId() ;
	 * </p>
	 * <p>
	 * prodId = maxId.getMaxIdFromTable("product","prod_id","sts = 'A'");
	 * </p>
	 * <p>
	 * .......
	 * </p>
	 * 
	 * 
	 */
	public static void main(String args[]) {
		String s = "ACCT_NBR = " + "22222";
		s = s.substring(0, 11) + "'" + s.substring(11, s.length()) + "'";
		// System.out.print(s);
	}

}
