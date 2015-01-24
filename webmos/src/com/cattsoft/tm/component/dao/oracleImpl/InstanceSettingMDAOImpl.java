package com.cattsoft.tm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.tm.component.dao.IInstanceSettingMDAO;
import com.cattsoft.tm.vo.DColumnDescSVO;

public class InstanceSettingMDAOImpl extends DColumnDescSDAOImpl implements
		IInstanceSettingMDAO {

	public List getTableColumns(String tableName) throws AppException,
			SysException {
		if (tableName == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("SELECT T1.TABLE_ID, T2.COLUMN_NAME, T2.COLUMN_DESC FROM D_TABLE_DESC T1, D_COLUMN_DESC T2 WHERE T1.TABLE_NAME=T2.TABLE_NAME AND T1.TABLE_NAME=:tableName");
		try {
			sql.setString("tableName", tableName);

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				DColumnDescSVO column = new DColumnDescSVO();
				column.setColumnName(rs.getString("COLUMN_NAME"));
				column.setColumnDesc(rs.getString("COLUMN_DESC"));
				res.add(column);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return res;
	}
	

}
