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
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;

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
		Sql sql = new Sql(
				"SELECT T1.TABLE_ID, T2.COLUMN_NAME, T2.COLUMN_DESC FROM D_TABLE_DESC T1, D_COLUMN_DESC T2 WHERE T1.TABLE_NAME=T2.TABLE_NAME AND T1.TABLE_NAME=:tableName");
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

	/**
	 * 获取某一实例中列的信息
	 * 
	 * @param instacneId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryConfigColumnList(String instanceId)
			throws AppException, SysException {
		if (instanceId == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"select T5.column_desc,t4.column_name, (case when t2.column_name is null then 'N' ELSE 'Y' END) AS IS_SHOW," +
				" (case when t3.column_name is null then 'N' ELSE 'Y' END) AS IS_CONDITION, " +
				"T3.CONDITION_TYPE, T2.SEQ, T1.TABLE_NAME, " +
				"T5.COLUMN_DESC,  t2.is_group, t2.is_sum," +
				"t2.column_width,t2.bg_color ," +
				" (case when t6.query_sort_id is null then 'N' ELSE 'Y' END ) AS IS_SORT,t2.IS_DATA_PRIV " +
				"from query_instance           t1, query_instance_column    t2," +
				" QUERY_CONDITION t3, user_tab_cols            t4, " +
				"D_COLUMN_DESC            T5  ," +
				" query_sort t6 " +
				"where t4.column_name = t2.column_name(+) " +
				"and t4.column_name=t3.column_name(+) " +
				"and t4.column_name=t5.column_name(+) " +
				"AND T1.TABLE_NAME=T4.TABLE_NAME AND " +
				"t5.table_name=t1.table_name and " +
				"t2.instance_id(+)=:instanceId and " +
				"t3.instance_id(+)=:instanceId and " +
				"t1.query_instance_id=:instanceId " +
				"and t6.query_instance_id(+)   =:instanceId "+
				"and t4.column_name=t6.query_column_name(+) "+
				"order by t2.seq,t2.column_name");
		try {
			sql.setString("instanceId", instanceId);

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				QueryInstanceColumnSVO column = new QueryInstanceColumnSVO();
				column.setColumnDesc(rs.getString("column_desc"));
				column.setColumnName(rs.getString("column_name"));
				column.setIsShow(rs.getString("IS_SHOW"));
				column.setIsCondition(rs.getString("IS_CONDITION"));
				column.setConditionType(rs.getString("CONDITION_TYPE"));
				column.setSeq(rs.getString("seq"));
				column.setIsGroup(rs.getString("is_group"));
				column.setIsSum(rs.getString("is_sum"));
				column.setBgColor(rs.getString("bg_color"));
				column.setWidth(rs.getString("column_width"));
				column.setIsSort(rs.getString("IS_SORT"));
				column.setIsDataPriv(rs.getString("IS_DATA_PRIV"));
				res.add(column);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return res;
	}

	public QueryInstanceSVO getTableByInstanceId(String instanceId)
			throws AppException, SysException {
		if (instanceId == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("select t1.table_name, t1.table_desc from d_table_desc t1, query_instance t2  where t1.table_name=t2.table_name and t2.query_instance_id=:instanceId");
		QueryInstanceSVO instance=null;
		try {				
			sql.setString("instanceId", instanceId);

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				instance = new QueryInstanceSVO();
				instance.setTableName(rs.getString("table_name"));
				instance.setTableDesc(rs.getString("table_desc"));
				res.add(instance);
			}
			if(instance==null)throw new AppException("", "缺少必要数据！");
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
		return instance;
	}
}
