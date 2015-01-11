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

public class CtxdMDAOImpl implements ICtxdMDAO{

	
	/**
	 * 获取每一行的结果集
	 * @return
	 * @throws AppException
	 * @throws SysException
	 * @throws SQLException 
	 */
	private Map fetchRow(List columns,ResultSet rs) throws AppException, SysException, SQLException {
		Map m=new LinkedHashMap();
		for(int i=0;i<columns.size();i++) {
			DColumnDescSVO column=(DColumnDescSVO)columns.get(i);
			String columnName=column.getColumnName();
			m.put(columnName, rs.getString(columnName));
		}
		return m;
	}
	
	public List queryResult(String tableId,List conditionListFromPage) throws AppException, SysException {
        if (StringUtil.isBlank(tableId)) {
        throw new AppException("100001", "缺少DAO操作对象！"); 
      }
         List res = new ArrayList();
         Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Sql sql = new Sql(this.getWholeSql(tableId,conditionListFromPage));
         List conditions=this.getQueryCondition(tableId,conditionListFromPage);
         List columns=this.getQueryColumnList(tableId);
    try {
    	 fillParameters(conditions,sql);

          conn = ConnectionFactory.getConnection();
          ps = conn.prepareStatement(sql.getSql());
          ps = sql.fillParams(ps);
          sql.log(this.getClass());
          rs = ps.executeQuery();
         while (rs.next()) {
        	 Map m=fetchRow(columns,rs);
        	 res.add(m);
             }
         } catch (SQLException se) {
          throw new SysException("100003", "JDBC操作异常！", se);
           } finally {
               JdbcUtil.close(rs,ps);
              }
              
         return res;
	}
	
	
	/**
	 * 填充查询条件
	 * @return
	 */
	private void fillParameters(List queryColumns,Sql sql) {
		for(int i=0;i<queryColumns.size();i++) {
			Map m=(HashMap)queryColumns.get(i);
			String columnName=(String)m.get("columnName");
			String dataType=(String)m.get("dataType");
			String value=(String)m.get("value");
			if(!StringUtil.isBlank(value)) {
				if(Tools.isDateType(dataType) || Tools.isVarchar(dataType)) {
					sql.setString(columnName, value);
				}else {
					sql.setInteger(columnName, value);
				}
					
			}
		}
		System.out.println("ssssssss="+sql.getSql());
	}
	
	//获取表名
		public DTableDescSVO getTableById(String tableId) throws AppException, SysException{
			 IDTableDescSDAO tableSDAO = (IDTableDescSDAO) DAOFactory.getDAO(
					 IDTableDescSDAO.class);
			 DTableDescSVO t=new DTableDescSVO();
			 t.setTableId(tableId);
			 return (DTableDescSVO)tableSDAO.findByPK(t);
		}
		
		
		/**
		 * 获取需要查询的列
		 * @param tableId
		 * @return
		 * @throws AppException
		 * @throws SysException
		 */
		public  List getQueryColumnList(String tableId) throws AppException, SysException{
			IDColumnDescSDAO columnSDAO = (IDColumnDescSDAO) DAOFactory.getDAO(IDColumnDescSDAO.class);
			DColumnDescSVO column=new DColumnDescSVO();
			column.setTableId(tableId);
			List columns=columnSDAO.findByVO(column);
			return columns;
		}
		
		
		/**
		 * 获取完整sql
		 * @param tableId
		 * @return
		 * @throws AppException
		 * @throws SysException
		 */
		private String getWholeSql(String tableId,List conditionListFromPage) throws AppException, SysException{
			String sql="";
			String select ="select ";
			String from=" from ";
			String where=" where 1=1 ";
			String tableName=getTableById(tableId).getTableName();
			
			List columns=getQueryColumnList(tableId);
			String sqlColumn=getSqlColumns(columns);
			
			List conditions=getQueryCondition(tableId,conditionListFromPage);
			String sqlCondition=getConditionSql(conditions);
			sql=select + sqlColumn+ from +tableName+where +sqlCondition;
			return sql;
		}
		
		
		
		
		/**
		 * 获取查询条件sql
		 * @return
		 */
		private String getConditionSql(List conditions) throws AppException, SysException{
			if(conditions==null || conditions.size()==0) {
				throw new AppException("11111","获取不到查询条件！");
			}
			String sqlCondtion="";
			for(int i=0;i<conditions.size();i++){
				Map m=(HashMap)conditions.get(i);
				String columnName=(String)m.get("columnName");
				String dataType=(String)m.get("dataType");
				String value=(String)m.get("value");
				if(!StringUtil.isBlank(value)) {
					if("DATE".equals(dataType)) {
						sqlCondtion=sqlCondtion +" and to_char("+columnName+",'yyyy-mm-dd')=:"+columnName;
					}else {
						sqlCondtion=sqlCondtion +" and "+columnName+"=:"+columnName;
					}
				}
			}
			return sqlCondtion;
		}
		
		/**
		 * 获取sql语句中查询的字段
		 * @param columns
		 * @return
		 */
		private  String getSqlColumns(List columns)throws AppException, SysException{
			if(columns==null || columns.size()==0) {
				throw new AppException("11111","获取不到字段列表！");
			}
			String queryColum="";
			for(int i=0;i<columns.size();i++){
				DColumnDescSVO column=(DColumnDescSVO)columns.get(i);
				String columnName=column.getColumnName();
				queryColum=queryColum+columnName+",";
			}
			queryColum=queryColum.substring(0,queryColum.length()-1);
			return queryColum;
		}
		
		
		/**
		 * 获取查询条件列表,包含查询条件值
		 * @param tableId
		 * @return
		 * @throws AppException
		 * @throws SysException
		 */
		public List getQueryCondition(String tableId,List conditionListFromPage) throws AppException, SysException{
			List conditionList=getQueryCondition(tableId);
			if(conditionList!=null && conditionList.size()>0) {
				for(int i=0;i<conditionList.size();i++) {
					Map qm=(Map)conditionList.get(i);
					String conditionName=(String)qm.get("columnName");
					if(conditionListFromPage!=null &&conditionListFromPage.size()>0) {
						for(int j=0;j<conditionListFromPage.size();j++) {
							Map m=(Map)conditionListFromPage.get(j);
							String paraName=(String)m.get("paraName");
							String value=(String)m.get("value");
							if(paraName.equals(conditionName)) {
								qm.put("value", value);
								break;
							}
						}
					}
				}
			}
			return conditionList;
		}
		
		/**
		 * 获取查询条件列表
		 * @param tableId
		 * @return
		 * @throws AppException
		 * @throws SysException
		 */
		public List getQueryCondition(String tableId) throws AppException, SysException{
	         if (StringUtil.isBlank(tableId)) {
	         throw new AppException("100001", "缺少DAO操作对象！"); 
	       }
	          List res = new ArrayList();
	          Connection conn = null;
	          PreparedStatement ps = null;
	          ResultSet rs = null;
	          Sql sql = new Sql("select t.table_id,  t.table_name, c.column_desc_id, c.column_name, c.column_desc,c.data_type, q.condition_type from d_column_desc C, D_TABLE_DESC T, D_QUERY_CONDITION Q "+
	        		  " WHERE c.column_desc_id = q.column_id and c.table_id = t.table_id and t.table_id = :tableId");
	     try {
	             sql.setString("tableId", tableId);
	 
	           conn = ConnectionFactory.getConnection();
	           ps = conn.prepareStatement(sql.getSql());
	           ps = sql.fillParams(ps);
	           sql.log(this.getClass());
	           rs = ps.executeQuery();
	          
	          while (rs.next()) {
	           Map m   = new HashMap();
	           m.put("tableId", rs.getString("TABLE_ID"));
	           String tableName=rs.getString("table_name");
	           m.put("tableName", tableName);
	           m.put("columnDescId", rs.getString("column_desc_id"));
	           String columnName=rs.getString("column_name");
	           m.put("columnName", columnName);
	           m.put("columnDesc", rs.getString("column_desc"));
	           String conditionType=rs.getString("condition_type");
	           m.put("conditionType", conditionType);
	           m.put("dataType", rs.getString("data_type"));
	           m.put("value", "");
	           if("M".equals(conditionType)) {
	        	   m.put("data", getQueryConditionData(tableName, columnName));
	           }
	           res.add(m);
	              }
	          } catch (SQLException se) {
	           throw new SysException("100003", "JDBC操作异常！", se);
	            } finally {
	                JdbcUtil.close(rs,ps);
	               }
	               
	          if(0 == res.size()) res = null;
	          return res;
		}
		
	/**
	 * 如果查询条件为M类型，则取该字段的distinct数据作为查询条件
	 * 
	 * @param columnName
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	private List getQueryConditionData(String tableName,String columnName) throws AppException,
			SysException {
        if (StringUtil.isBlank(columnName)) {
        throw new AppException("100001", "缺少DAO操作对象！"); 
      }
         List res = new ArrayList();
         Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Sql sql = new Sql("select distinct "+ columnName+ " from "+tableName);
    try {

          conn = ConnectionFactory.getConnection();
          ps = conn.prepareStatement(sql.getSql());
          ps = sql.fillParams(ps);
          sql.log(this.getClass());
          rs = ps.executeQuery();
         
         while (rs.next()) {
        	String r= rs.getString(columnName);
        	res.add(r);
         }
         } catch (SQLException se) {
          throw new SysException("100003", "JDBC操作异常！", se);
           } finally {
               JdbcUtil.close(rs,ps);
              }
              
         return res;
	}

		
		
		
		
		public GenericVO findByPK(GenericVO vo) throws AppException,
				SysException {
			// TODO Auto-generated method stub
			return null;
		}

		public void add(GenericVO vo) throws AppException, SysException {
			// TODO Auto-generated method stub
			
		}

		public void update(GenericVO vo) throws AppException, SysException {
			// TODO Auto-generated method stub
			
		}

		public void delete(GenericVO vo) throws AppException, SysException {
			// TODO Auto-generated method stub
			
		}

		public List findByVO(GenericVO vo) throws AppException, SysException {
			// TODO Auto-generated method stub
			return null;
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
		
		
		
		

}
