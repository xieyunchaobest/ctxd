package com.cattsoft.tm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.tm.component.dao.ISysMDAO;

public class SysMDAOImpl implements ISysMDAO{
	
	public List getDataDicList(String tableName,String columnName) throws AppException,SysException{
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				" select label,value from DATA_DIC where TABLE_NAME=:tableName and COLUMN_NAME=:columnName");
		try {
			sql.setString("tableName", tableName);
			sql.setString("columnName", columnName);
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				Map m=new HashMap();
				m.put("label", rs.getString("label"));
				m.put("value", rs.getString("value"));
				res.add(m);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC²Ù×÷Òì³££¡", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return res;
	
	}
	
}
