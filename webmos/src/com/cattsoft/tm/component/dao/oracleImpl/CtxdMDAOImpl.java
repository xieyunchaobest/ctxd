package com.cattsoft.tm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.tm.component.dao.ICtxdMDAO;
import com.cattsoft.tm.component.dao.IDColumnDescSDAO;
import com.cattsoft.tm.component.dao.IDTableDescSDAO;
import com.cattsoft.tm.struts.Tools;
import com.cattsoft.tm.vo.DColumnDescSVO;
import com.cattsoft.tm.vo.DTableDescSVO;
import com.cattsoft.tm.vo.FuncNodeSVO;
import com.cattsoft.tm.vo.QueryConditionSVO;
import com.cattsoft.tm.vo.QueryInstanceColumnSVO;
import com.cattsoft.tm.vo.QueryInstanceSVO;

public class CtxdMDAOImpl extends QueryInstanceSDAOImpl  implements ICtxdMDAO {

	
	/**
	 * 获取每一行的结果集
	 * 
	 * @return
	 * @throws AppException
	 * @throws SysException
	 * @throws SQLException
	 */
	private Map fetchRow(List columns, ResultSet rs) throws AppException,
			SysException, SQLException {
		Map m = new LinkedHashMap();
		for (int i = 0; i < columns.size(); i++) {
			QueryInstanceColumnSVO column = (QueryInstanceColumnSVO) columns.get(i);
			String columnName = column.getColumnName();
			m.put(columnName, rs.getString(columnName));
		}
		return m;
	}

	public PagView queryResult(String instanceId, List conditionListFromPage,
			PagInfo pagInfo) throws AppException, SysException {
		if (StringUtil.isBlank(instanceId)) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		PagView pagView = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(this.getWholeSql(instanceId, conditionListFromPage));
		List conditions = this
				.getQueryCondition(instanceId, conditionListFromPage);
		List columns = this.getColumnList(instanceId);
		try {
			fillParameters(conditions, sql);

			conn = ConnectionFactory.getConnection();
			// ps = conn.prepareStatement(sql.getSql());
			// ps = sql.fillParams(ps);
			pagView = PagUtil.InitPagViewJDBC(conn, sql, pagInfo);
			rs = PagUtil.queryOracle(conn, sql, pagInfo);
			sql.log(this.getClass());
			// rs = ps.executeQuery();
			while (rs.next()) {
				Map m = fetchRow(columns, rs);
				res.add(m);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		pagView.setViewList(res);

		return pagView;
	}

	/**
	 * 填充查询条件
	 * 
	 * @return
	 */
	private void fillParameters(List queryColumns, Sql sql) {
		for (int i = 0; i < queryColumns.size(); i++) {
			QueryConditionSVO m = (QueryConditionSVO) queryColumns.get(i);
			String columnName = (String) m.getColumnName();
			String dataType = (String) m.getDataType();
			String value = (String) m.getValue();
			if (!StringUtil.isBlank(value)) {
				if (Tools.isDateType(dataType) || Tools.isVarchar(dataType)) {
					sql.setString(columnName, value);
				} else {
					sql.setInteger(columnName, value);
				}

			}
		}
		System.out.println("ssssssss=" + sql.getSql());
	}

	// 获取表名
	public DTableDescSVO getTableByName(String tableName) throws AppException,
			SysException {
		IDTableDescSDAO tableSDAO = (IDTableDescSDAO) DAOFactory
				.getDAO(IDTableDescSDAO.class);
		DTableDescSVO t = new DTableDescSVO();
		t.setTableName(tableName);
		return  (DTableDescSVO)tableSDAO.findByVO(t).get(0);
	}

//	/**
//	 * 获取需要查询的列
//	 * 
//	 * @param tableId
//	 * @return
//	 * @throws AppException
//	 * @throws SysException
//	 */
//	public List getQueryColumnList(String tableName) throws AppException,
//			SysException {
//		IDColumnDescSDAO columnSDAO = (IDColumnDescSDAO) DAOFactory
//				.getDAO(IDColumnDescSDAO.class);
//		DColumnDescSVO column = new DColumnDescSVO();
//		column.setTableName(tableName);
//		column.setIsShow("Y");
//		List columns = columnSDAO.findByVO(column);
//		return columns;
//	}

	/**
	 * 获取完整sql
	 * 
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	private String getWholeSql(String instanceId, List conditionListFromPage)
			throws AppException, SysException {
		QueryInstanceSVO instance=new QueryInstanceSVO();
		instance.setQueryInstanceId(instanceId);
		String sql = "";
		String select = "select ";
		String from = " from ";
		String where = " where 1=1 ";
		String atableName = ((QueryInstanceSVO)findByPK(instance)).getTableName();

		List columns =getColumnList(instanceId);
		String sqlColumn = getSqlColumns(columns);

		List conditions = getQueryCondition(instanceId, conditionListFromPage);
		String sqlCondition = getConditionSql(conditions);
		sql = select + sqlColumn + from + atableName + where + sqlCondition;
		return sql;
	}

	/**
	 * 获取查询条件sql
	 * 
	 * @return
	 */
	private String getConditionSql(List conditions) throws AppException,
			SysException {
		if (conditions == null || conditions.size() == 0) {
			throw new AppException("11111", "获取不到查询条件！");
		}
		String sqlCondtion = "";
		for (int i = 0; i < conditions.size(); i++) {
			QueryConditionSVO m = (QueryConditionSVO) conditions.get(i);
			String columnName = (String) m.getColumnName();
			String dataType = (String) m.getDataType();
			String value = (String) m.getValue();
			if (!StringUtil.isBlank(value)) {
				if ("DATE".equals(dataType)) {
					sqlCondtion = sqlCondtion + " and to_char(" + columnName
							+ ",'yyyy-mm-dd')=:" + columnName;
				} else {
					sqlCondtion = sqlCondtion + " and " + columnName + "=:"
							+ columnName;
				}
			}
		}
		return sqlCondtion;
	}

	/**
	 * 获取sql语句中查询的字段
	 * 
	 * @param columns
	 * @return
	 */
	private String getSqlColumns(List columns) throws AppException,
			SysException {
		if (columns == null || columns.size() == 0) {
			throw new AppException("11111", "获取不到字段列表！");
		}
		String queryColum = "";
		for (int i = 0; i < columns.size(); i++) {
			QueryInstanceColumnSVO column = (QueryInstanceColumnSVO) columns.get(i);
			String columnName = column.getColumnName();
			String dataType=column.getDataType();
			if(com.cattsoft.tm.struts.Tools.isDateType(dataType)) {
				columnName=" to_char("+columnName+",'yyyy-mm-dd') as "+columnName;
			}
			queryColum = queryColum + columnName + ",";
		}
		queryColum = queryColum.substring(0, queryColum.length() - 1);
		return queryColum;
	}

	/**
	 * 获取查询条件列表,包含查询条件值
	 * 
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryCondition(String instanceId, List conditionListFromPage)
			throws AppException, SysException {
		List conditionList = getQueryConditionList(instanceId);
		if (conditionList != null && conditionList.size() > 0) {
			for (int i = 0; i < conditionList.size(); i++) {
				QueryConditionSVO qm = (QueryConditionSVO) conditionList.get(i);
				String conditionName = (String) qm.getColumnName();
				if (conditionListFromPage != null
						&& conditionListFromPage.size() > 0) {
					for (int j = 0; j < conditionListFromPage.size(); j++) {
						Map m = (Map) conditionListFromPage.get(j);
						String paraName = (String) m.get("paraName");
						String value = (String) m.get("value");
						if (paraName.equals(conditionName)) {
							qm.setValue(value);
							break;
						}
					}
				}
			}
		}
		return conditionList;
	}

//	/**
//	 * 获取查询条件列表
//	 * 
//	 * @param tableId
//	 * @return
//	 * @throws AppException
//	 * @throws SysException
//	 */
//	public List getQueryCondition(String tableName) throws AppException,
//			SysException {
//		if (StringUtil.isBlank(tableName)) {
//			throw new AppException("100001", "缺少DAO操作对象！");
//		}
//		List res = new ArrayList();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		Sql sql = new Sql(
//				"select t.table_id,  t.table_name, c.column_desc_id, c.column_name, c.column_desc,c.data_type, q.condition_type" +
//				" from d_column_desc C, D_TABLE_DESC T, D_QUERY_CONDITION Q "
//						+ " WHERE c.column_name = q.column_name and c.table_name = t.table_name and t.table_name = :tableName" +
//						" and Q.table_name=:tableName");
//		try {
//			sql.setString("tableName", tableName);
//
//			conn = ConnectionFactory.getConnection();
//			ps = conn.prepareStatement(sql.getSql());
//			ps = sql.fillParams(ps);
//			sql.log(this.getClass());
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				Map m = new HashMap();
////				m.put("tableName", rs.getString("TABLE_NAME"));
//				String atableName = rs.getString("table_name");
//				m.put("tableName", atableName);
//				m.put("columnDescId", rs.getString("column_desc_id"));
//				String columnName = rs.getString("column_name");
//				m.put("columnName", columnName);
//				m.put("columnDesc", rs.getString("column_desc"));
//				String conditionType = rs.getString("condition_type");
//				m.put("conditionType", conditionType);
//				m.put("dataType", rs.getString("data_type"));
//				m.put("value", "");
//				if ("M".equals(conditionType)) {
//					m.put("data", getQueryConditionData(tableName, columnName));
//				}
//				res.add(m);
//			}
//		} catch (SQLException se) {
//			throw new SysException("100003", "JDBC操作异常！", se);
//		} finally {
//			JdbcUtil.close(rs, ps);
//		}
//
//		if (0 == res.size())
//			res = null;
//		return res;
//	}

	/**
	 * 如果查询条件为M类型，则取该字段的distinct数据作为查询条件
	 * 
	 * @param columnName
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	private List getQueryConditionData(String instanceId, String columnName)
			throws AppException, SysException {
		if (StringUtil.isBlank(columnName)) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		QueryInstanceSVO instance=new QueryInstanceSVO();
		instance.setQueryInstanceId(instanceId);
		Sql sql = new Sql("select distinct " + columnName + " from "
				+ ((QueryInstanceSVO)findByPK(instance)).getTableName());
		try {

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				String r = rs.getString(columnName);
				res.add(r);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		return res;
	}



	public List getFuncNodeListByUser(SysUserSVO vo) throws AppException,
			SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT a.DESCRIPTION,a.FILE_NAME,a.FUNC_NODE_CODE,a.FUNC_NODE_ID,a.FUNC_NODE_NAME,a.FUNC_NODE_TYPE,a.HTML,NODE_TREE_ID,a.SECURITY_LEVEL,a.SHORT_CUT_IMAGE,a.STS,a.STS_DATE,a.SUB_SYSTEM_NAME,a.VERSION "
						+ "FROM func_node a ,   sys_user_alloc b WHERE a.func_node_id=b.func_node_id AND b.sys_user_id   =:sysUserId  and  a.sts='A' AND B.STS='A'   order by a.security_level ,a.func_node_id ");
		try {
			sql.setString("sysUserId", vo.getSysUserId());

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				FuncNodeSVO funcNode = new FuncNodeSVO();
				funcNode.setDescription(rs.getString("DESCRIPTION"));
				funcNode.setFileName(rs.getString("FILE_NAME"));
				funcNode.setFuncNodeCode(rs.getString("FUNC_NODE_CODE"));
				funcNode.setFuncNodeId(rs.getString("FUNC_NODE_ID"));
				funcNode.setFuncNodeName(rs.getString("FUNC_NODE_NAME"));
				funcNode.setFuncNodeType(rs.getString("FUNC_NODE_TYPE"));
				funcNode.setHtml(rs.getString("HTML"));
				funcNode.setNodeTreeId(rs.getString("NODE_TREE_ID"));
				funcNode.setSecurityLevel(rs.getString("SECURITY_LEVEL"));
				funcNode.setShortCutImage(rs.getString("SHORT_CUT_IMAGE"));
				funcNode.setSts(rs.getString("STS"));
				funcNode.setStsDate(rs.getTimestamp("STS_DATE"));
				funcNode.setSubSystemName(rs.getString("SUB_SYSTEM_NAME"));
				funcNode.setVersion(rs.getString("VERSION"));
				res.add(funcNode);

			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;

	}

	/**
	 * 获取当前数据库用户的表
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getDBTables()throws AppException, SysException {
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("select t1.table_name,t2.table_desc,t2.table_id,t2.statistics_comments "+
  " from user_tables t1,d_table_desc  t2 "+
  " where t1.table_name=t2.table_name(+)  ");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				DTableDescSVO table=new DTableDescSVO();
				table.setTableName(rs.getString("table_name"));
				table.setTableDesc(rs.getString("table_desc"));
				table.setTableId(rs.getString("table_id"));
				table.setStatisticsComments(rs.getString("statistics_comments"));
				res.add(table);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;
	}
	
	/**
	 * 获得配置表格的相关信息
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public DTableDescSVO getConfigTableInfo(String tableId)throws AppException, SysException {
		if(StringUtil.isBlank(tableId)) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("select t2.table_id, "+
				 " t2.table_name,"+
				 " (case when t2.table_desc is null then "+
				 " t1.comments "+
				 " else  "+
				 " t2.table_desc "+
				 " end  "+
				 " ) as tabledesc," +
				 " t2.statistics_comments "+
				 " from user_tab_comments  t1,d_table_desc t2 "+
				"+where t1.table_name=t2.table_name and t2.table_id=:tableId");
		DTableDescSVO table=null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.setString("tableId", tableId);
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
		
			while (rs.next()) {
				table=new DTableDescSVO();
				table.setTableName(rs.getString("table_name"));
				table.setTableId(rs.getString("table_id"));
				table.setTableDesc(rs.getString("tabledesc"));
				table.setTableDesc(rs.getString("statistics_comments"));
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return table;
	}
	
	/**
	 * 获取列的说明信息，如果没有，则取数据字典的说明
	 * @param svo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getColumnDescList(String tableName)throws AppException, SysException {
		if(tableName==null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("SELECT t1.column_desc_id, "+
				 " t2.column_name, "+
				 " t1.table_name,"+
				 " ( "+
				 " CASE "+
				 " WHEN t1.column_desc IS NULL "+
				 " THEN t2.comments "+
				 " ELSE t1.column_desc "+
				 " END ) AS column_desc, "+
				 " t1.is_show ,"+
				 " t4.data_type as data_type ,"+
				 " t4.data_type as data_type,"+
				 " (  "+
				 "   case when t5.column_name is null "+
				 "   then 'N' else 'Y' "+
				 "   end "+
				 " ) AS is_query_condition ,"+
				 " t5.CONDITION_TYPE as conditionType "+
				 " FROM d_column_desc t1, "+
				 " user_col_comments t2 , "+
				 " d_table_desc t3 ,user_tab_cols t4,d_query_condition t5 "+
				 " WHERE t2.column_name=t1.column_name(+) "+
				 " AND t2.table_name   =t3.table_name(+) "+
				 " and t2.column_name = t4.column_name "+
				 " AND t2.table_name   =:tableName "+
				 " and t4.table_name=:tableName " +
				 " and t1.column_name=t5.column_name(+)  "+
				 " AND t1.table_name=t5.table_name(+) " +
				 " and t5.table_name(+)=:tableName " +
				 "and t1.table_name(+)=:tableName"
				 );
		
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.setString("tableName", tableName);
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
		
			while (rs.next()) {
				DColumnDescSVO column=new DColumnDescSVO();
				column.setColumnDescId(rs.getString("column_desc_id"));
				column.setColumnName(rs.getString("column_name"));
				column.setTableName(rs.getString("table_name"));
				column.setColumnDesc(rs.getString("column_desc"));
				column.setIsShow(rs.getString("is_show"));
				column.setDataType(rs.getString("data_type"));
				column.setIsQueryCondition(rs.getString("is_query_condition"));
				column.setConditionType(rs.getString("conditionType"));
				res.add(column);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return res;
	}

	public void addBat(List vos) throws AppException, SysException {
		
	}
	
	
	public List getColumnCommentsByTable(String tableName)throws AppException, SysException {
		if(tableName==null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("SELECT t2.column_desc_id, t1.table_name, " + " t1.column_name," + " ("
				+ " CASE" + "   WHEN t2.column_desc IS NULL"
				+ "   THEN t1.comments" + "   ELSE t2.column_desc"
				+ " END ) AS column_desc," + " t3.data_type,"
				+ " t3.column_id AS seq" + " FROM user_col_comments t1,"
				+ " d_column_desc t2," + " user_tab_cols t3"
				+ " WHERE t3.column_name=t1.column_name"
				+ " AND t1.column_name  =t2.column_name(+)"
				+ " AND t1.table_name   =:tableName"
				+ " AND t2.table_name(+)=:tableName"
				+ " AND t3.table_name   =:tableName");
		
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.setString("tableName", tableName);
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
		
			while (rs.next()) {
				DColumnDescSVO column=new DColumnDescSVO();
				column.setColumnDescId(rs.getString("column_desc_id"));
				column.setColumnName(rs.getString("column_name"));
				column.setTableName(rs.getString("table_name"));
				column.setColumnDesc(rs.getString("column_desc"));
				column.setDataType(rs.getString("data_type"));
				res.add(column);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return res;
	
	}
	
	public PagView getQueryInstanceList(QueryInstanceSVO i,PagInfo pagInfo)throws AppException, SysException {
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(" select "+
				"q.query_instance_id,"+
				"q.instance_type,"+
				"decode(instance_type,'C','通用查询','S','分组汇总') instance_type_name,  "+
				"q.instance_name,"+
				"q.table_name,"+
				"t.table_desc "+
				"from query_instance q,"+
				"     d_table_desc t "+
				"     where q.table_name =t.table_name");
		PagView pagView = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			
			if(!StringUtil.isBlank(i.getInstanceName())) {
				sql.append(" and q.instance_name like :instanceName");
				sql.setString("instanceName", "%"+i.getInstanceName()+"%");
			}
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			
			pagView = PagUtil.InitPagViewJDBC(conn, sql, pagInfo);
			rs = PagUtil.queryOracle(conn, sql, pagInfo);
			
			sql.log(this.getClass());
			rs = ps.executeQuery();
		
			while (rs.next()) {
				QueryInstanceSVO instance=new QueryInstanceSVO();
				instance.setQueryInstanceId(rs.getString("query_instance_id"));
				instance.setInstanceType(rs.getString("instance_type"));
				instance.setInstanceName(rs.getString("instance_name"));
				instance.setTableName(rs.getString("table_name"));
				instance.setTableDesc(rs.getString("table_desc"));
				instance.setInstanceTypeName(rs.getString("instance_type_name"));
				res.add(instance);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		pagView.setViewList(res);
		return pagView;
	}
	
	
	public List getColumnList(String instacneId) throws AppException, SysException {
		if(instacneId==null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("SELECT  "+
       " T2.QUERY_INSTANCE_COLUMN_ID,"+
       " T2.COLUMN_NAME,"+
       " T3.COLUMN_DESC ,T3.DATA_TYPE"+
       " FROM QUERY_INSTANCE T1,"+
       " QUERY_INSTANCE_COLUMN T2,"+
       " D_COLUMN_DESC T3 "+
       " WHERE T1.QUERY_INSTANCE_ID = T2.INSTANCE_ID "+
       " AND T2.COLUMN_NAME=T3.COLUMN_NAME "+
       " AND T1.TABLE_NAME=T3.TABLE_NAME "+
       " AND T1.QUERY_INSTANCE_ID = :instacneId "+
       " ORDER BY T2.SEQ, T2.COLUMN_NAME");
		
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.setString("instacneId", instacneId);
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
		
			while (rs.next()) {
				QueryInstanceColumnSVO column=new QueryInstanceColumnSVO();
				column.setQueryInstanceColumnId(rs.getString("QUERY_INSTANCE_COLUMN_ID"));
				column.setColumnName(rs.getString("COLUMN_NAME"));
				column.setDataType(rs.getString("DATA_TYPE"));
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
	 * 获取查询条件列表
	 * 
	 * @param tableId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List getQueryConditionList(String instanceId)
			throws AppException, SysException {

		if(instanceId==null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql("SELECT  "+
       "T2.QUERY_CONDITION_ID,"+
       "T2.COLUMN_NAME,"+
       "T2.CONDITION_TYPE,"+
       "T2.INSTANCE_ID," +
       "T3.COLUMN_DESC, " +
       "T3.DATA_TYPE "+
  "FROM QUERY_INSTANCE T1,"+
  " QUERY_CONDITION T2,"+
   "D_COLUMN_DESC T3 "+
 "WHERE T1.QUERY_INSTANCE_ID = T2.INSTANCE_ID AND "+
  "     T3.TABLE_NAME=T1.TABLE_NAME AND "+
  "     T2.COLUMN_NAME=T3.COLUMN_NAME"+
  " AND T1.QUERY_INSTANCE_ID = :instanceId");
		
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.setString("instanceId", instanceId);
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
		
			while (rs.next()) {
				QueryConditionSVO condition=new QueryConditionSVO();
				String columnName=rs.getString("COLUMN_NAME");
				condition.setColumnName(columnName);
				String conditionType=rs.getString("CONDITION_TYPE");
				condition.setConditionType(conditionType);
				condition.setColumnDesc(rs.getString("COLUMN_DESC"));
				condition.setDataType(rs.getString("DATA_TYPE"));
				if ("B".equals(conditionType)) {
					condition.setData(getQueryConditionData(instanceId, columnName));
					//m.put("data", getQueryConditionData(tableName, columnName));
				}
				res.add(condition);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return res;
	
	}
	
	
}
