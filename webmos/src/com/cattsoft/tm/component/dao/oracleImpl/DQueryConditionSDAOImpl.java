package com.cattsoft.tm.component.dao.oracleImpl;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import java.util.List;import java.util.ArrayList;import org.apache.log4j.Logger;import com.cattsoft.pub.connection.ConnectionFactory;import com.cattsoft.tm.component.dao.IDQueryConditionSDAO;import com.cattsoft.tm.vo.DQueryConditionSVO;import com.cattsoft.pub.util.JdbcUtil;import com.cattsoft.pub.dao.Sql;import com.cattsoft.pub.exception.AppException;import com.cattsoft.pub.exception.SysException;import com.cattsoft.pub.vo.GenericVO;import com.cattsoft.pub.util.StringUtil; /**   * 方法DQueryConditionSDAOImpl   * <p>Company: 大唐软件</p>   * @author ：白小亮。   * @version 1.1  2007-9-23  */public class DQueryConditionSDAOImpl implements IDQueryConditionSDAO{    private static Logger log = Logger.getLogger(DQueryConditionSDAOImpl.class);    private static final String UNABLE_STS = "P";  /**   * 增加。   * @return String  */ public void add(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }     DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) vo;    if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {       throw new AppException("100002", "缺少对象的主键！");      }      Connection conn = null;      PreparedStatement ps = null;Sql sql = new Sql("INSERT INTO D_QUERY_CONDITION(COLUMN_ID,CONDITION_TYPE,CREATE_TIME,QUERY_CONDITION_ID)");sql.append(" VALUES (:columnId,:conditionType,:createTime,:queryConditionId)");      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());      if (StringUtil.isBlank(dQueryCondition.getColumnId())) {      sql.setNullLong("columnId");     } else {    sql.setLong("columnId", dQueryCondition.getColumnId());    }       if (StringUtil.isBlank(dQueryCondition.getConditionType())) {      sql.setNullString("conditionType");     } else {    sql.setString("conditionType", dQueryCondition.getConditionType());    }    if (dQueryCondition.getCreateTime() == null) {      sql.setNullDate("createTime");     } else {    sql.setTimestamp("createTime", dQueryCondition.getCreateTime());    }       if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {      sql.setNullString("queryConditionId");     } else {    sql.setString("queryConditionId", dQueryCondition.getQueryConditionId());    }            sql.fillParams(ps);           sql.log(this.getClass());           ps.executeUpdate();          } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);           } finally {                    JdbcUtil.close(ps);           }  } /**   * 主键查询的SQL。   * @return String ： 主键查询的SQL。  */ public GenericVO findByPK(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }     DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) vo;    if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {       throw new AppException("100002", "缺少对象的主键！");      }       Sql sql = new Sql("SELECT COLUMN_ID,CONDITION_TYPE,CREATE_TIME,QUERY_CONDITION_ID FROM D_QUERY_CONDITION WHERE 1=1  ");sql.append(" and QUERY_CONDITION_ID=:queryConditionId");sql.setString("queryConditionId", dQueryCondition.getQueryConditionId());       Connection conn = null;      PreparedStatement ps = null;      ResultSet rs = null;      dQueryCondition =null;      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           sql.fillParams(ps);           sql.log(this.getClass());           rs = ps.executeQuery();            while (rs.next()) {           dQueryCondition = new DQueryConditionSVO();           dQueryCondition.setColumnId(rs.getString("COLUMN_ID"));           dQueryCondition.setConditionType(rs.getString("CONDITION_TYPE"));           dQueryCondition.setCreateTime(rs.getTimestamp("CREATE_TIME"));           dQueryCondition.setQueryConditionId(rs.getString("QUERY_CONDITION_ID"));                 }           } catch (SQLException se) {             throw new SysException("100003", "JDBC操作异常！", se);                      } finally {                    JdbcUtil.close(rs,ps);           }           return dQueryCondition;         } /**   * 批量查询的SQL。   * @return String ： 批量查询的SQL。  */ public List findByVO(GenericVO vo) throws AppException, SysException{         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }       DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) vo;          List res = new ArrayList();          Connection conn = null;          PreparedStatement ps = null;          ResultSet rs = null;          Sql sql = new Sql("SELECT COLUMN_ID,CONDITION_TYPE,CREATE_TIME,QUERY_CONDITION_ID FROM D_QUERY_CONDITION WHERE 1=1 ");     try {if (dQueryCondition.getFlagColumnId() == 1) {      if (StringUtil.isBlank(dQueryCondition.getColumnId())) {             sql.append(" and COLUMN_ID is null ");          }      else{             sql.append(" and COLUMN_ID=:columnId");             sql.setLong("columnId", dQueryCondition.getColumnId());          }   } if (dQueryCondition.getFlagConditionType() == 1) {      if (StringUtil.isBlank(dQueryCondition.getConditionType())) {             sql.append(" and CONDITION_TYPE is null ");          }      else{             sql.append(" and CONDITION_TYPE=:conditionType");             sql.setString("conditionType", dQueryCondition.getConditionType());          }   } if (dQueryCondition.getFlagCreateTime() == 1) {      if (dQueryCondition.getCreateTime() == null) {             sql.append(" and CREATE_TIME is null ");          }      else{             sql.append(" and CREATE_TIME=:createTime");             sql.setTimestamp("createTime", dQueryCondition.getCreateTime());          }   } if (dQueryCondition.getFlagQueryConditionId() == 1) {      if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {             sql.append(" and QUERY_CONDITION_ID is null ");          }      else{             sql.append(" and QUERY_CONDITION_ID=:queryConditionId");             sql.setString("queryConditionId", dQueryCondition.getQueryConditionId());          }   }            conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           ps = sql.fillParams(ps);           sql.log(this.getClass());           rs = ps.executeQuery();                    while (rs.next()) {           dQueryCondition = new DQueryConditionSVO();           dQueryCondition.setColumnId(rs.getString("COLUMN_ID"));           dQueryCondition.setConditionType(rs.getString("CONDITION_TYPE"));           dQueryCondition.setCreateTime(rs.getTimestamp("CREATE_TIME"));           dQueryCondition.setQueryConditionId(rs.getString("QUERY_CONDITION_ID"));               res.add(dQueryCondition);                            }          } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);            } finally {                JdbcUtil.close(rs,ps);               }                         if(0 == res.size()) res = null;          return res;   } /**   * 更新的SQL。   * @return String ： 更新的SQL。  */ public void update(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }       DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) vo;    if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {       throw new AppException("100002", "缺少对象的主键！");      }          Connection conn = null;          PreparedStatement ps = null;          Sql sql = new Sql("UPDATE D_QUERY_CONDITION SET ");     try {if (dQueryCondition.getFlagColumnId() == 1) {sql.append("COLUMN_ID=:columnId,");sql.setLong("columnId", dQueryCondition.getColumnId()); } if (dQueryCondition.getFlagConditionType() == 1) {sql.append("CONDITION_TYPE=:conditionType,"); sql.setString("conditionType", dQueryCondition.getConditionType()); } if (dQueryCondition.getFlagCreateTime() == 1) {sql.append("CREATE_TIME=:createTime,"); sql.setTimestamp("createTime", dQueryCondition.getCreateTime()); }            	sql.removeSuffix(1); sql.append(" WHERE 1=1 ");sql.append(" and QUERY_CONDITION_ID=:queryConditionId");sql.setString("queryConditionId", dQueryCondition.getQueryConditionId());            conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           ps = sql.fillParams(ps);           sql.log(this.getClass());           ps.executeUpdate();                    } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);            } finally {                JdbcUtil.close(ps);               }                  } /**   * 批量增加记录。   * @return String ： 批量增加的SQL。  */ public void addBat(List list)throws AppException, SysException {     if (list == null) {     throw new AppException("100001", "缺少DAO操作对象！");           }          Connection conn = null;          PreparedStatement ps = null;Sql sql = new Sql("INSERT INTO D_QUERY_CONDITION(COLUMN_ID,CONDITION_TYPE,CREATE_TIME,QUERY_CONDITION_ID)");sql.append(" VALUES (:columnId,:conditionType,:createTime,:queryConditionId)");      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());    for(int i=0;i<list.size();i++){       DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) list.get(i);         if (dQueryCondition== null) {         throw new AppException("100001", "缺少DAO操作对象！");       }    if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {       throw new AppException("100002", "缺少对象的主键！");      }      if (StringUtil.isBlank(dQueryCondition.getColumnId())) {      sql.setNullLong("columnId");     } else {    sql.setLong("columnId", dQueryCondition.getColumnId());    }       if (StringUtil.isBlank(dQueryCondition.getConditionType())) {      sql.setNullString("conditionType");     } else {    sql.setString("conditionType", dQueryCondition.getConditionType());    }    if (dQueryCondition.getCreateTime() == null) {      sql.setNullDate("createTime");     } else {    sql.setTimestamp("createTime", dQueryCondition.getCreateTime());    }       if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {      sql.setNullString("queryConditionId");     } else {    sql.setString("queryConditionId", dQueryCondition.getQueryConditionId());    }            sql.fillParams(ps);           sql.log(this.getClass());           sql.clearParameters();           ps.addBatch();           }                  ps.executeBatch();          } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);           } finally {                    JdbcUtil.close(ps);           }  } /**   * 根据传入参数删除一条或者一批记录。   * @return String ： 删除的SQL。  */ public void delete(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }     DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) vo;    if (StringUtil.isBlank(dQueryCondition.getQueryConditionId())) {       throw new AppException("100002", "缺少对象的主键！");      }          Connection conn = null;          PreparedStatement ps = null;       Sql sql = new Sql("DELETE FROM D_QUERY_CONDITION WHERE 1=1  ");sql.append(" and QUERY_CONDITION_ID=:queryConditionId");sql.setString("queryConditionId", dQueryCondition.getQueryConditionId());       try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           sql.fillParams(ps);           sql.log(this.getClass());           ps.executeUpdate();            } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);                      } finally {                    JdbcUtil.close(ps);           }         } /**   * 注销一条或者一批   * @return String ： 注销一条或者一批的SQL。  */ public void unable(GenericVO vo)throws AppException, SysException {     DQueryConditionSVO dQueryCondition=(DQueryConditionSVO) vo;       }}
