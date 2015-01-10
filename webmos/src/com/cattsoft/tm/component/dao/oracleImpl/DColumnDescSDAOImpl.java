package com.cattsoft.tm.component.dao.oracleImpl;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import java.util.List;import java.util.ArrayList;import org.apache.log4j.Logger;import com.cattsoft.pub.connection.ConnectionFactory;import com.cattsoft.tm.component.dao.IDColumnDescSDAO;import com.cattsoft.tm.vo.DColumnDescSVO;import com.cattsoft.pub.util.JdbcUtil;import com.cattsoft.pub.dao.Sql;import com.cattsoft.pub.exception.AppException;import com.cattsoft.pub.exception.SysException;import com.cattsoft.pub.vo.GenericVO;import com.cattsoft.pub.util.StringUtil; /**   * 方法DColumnDescSDAOImpl   * <p>Company: 大唐软件</p>   * @author ：白小亮。   * @version 1.1  2007-9-23  */public class DColumnDescSDAOImpl implements IDColumnDescSDAO{    private static Logger log = Logger.getLogger(DColumnDescSDAOImpl.class);    private static final String UNABLE_STS = "P";  /**   * 增加。   * @return String  */ public void add(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }     DColumnDescSVO dColumnDesc=(DColumnDescSVO) vo;      Connection conn = null;      PreparedStatement ps = null;Sql sql = new Sql("INSERT INTO D_COLUMN_DESC(COLUMN_DESC,COLUMN_DESC_ID,COLUMN_NAME,CREATE_TIME,DATA_TYPE,SEQ,TABLE_ID)");sql.append(" VALUES (:columnDesc,:columnDescId,:columnName,:createTime,:dataType,:seq,:tableId)");      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());      if (StringUtil.isBlank(dColumnDesc.getColumnDesc())) {      sql.setNullString("columnDesc");     } else {    sql.setString("columnDesc", dColumnDesc.getColumnDesc());    }       if (StringUtil.isBlank(dColumnDesc.getColumnDescId())) {      sql.setNullLong("columnDescId");     } else {    sql.setLong("columnDescId", dColumnDesc.getColumnDescId());    }       if (StringUtil.isBlank(dColumnDesc.getColumnName())) {      sql.setNullString("columnName");     } else {    sql.setString("columnName", dColumnDesc.getColumnName());    }    if (dColumnDesc.getCreateTime() == null) {      sql.setNullDate("createTime");     } else {    sql.setTimestamp("createTime", dColumnDesc.getCreateTime());    }       if (StringUtil.isBlank(dColumnDesc.getDataType())) {      sql.setNullString("dataType");     } else {    sql.setString("dataType", dColumnDesc.getDataType());    }       if (StringUtil.isBlank(dColumnDesc.getSeq())) {      sql.setNullLong("seq");     } else {    sql.setLong("seq", dColumnDesc.getSeq());    }       if (StringUtil.isBlank(dColumnDesc.getTableId())) {      sql.setNullLong("tableId");     } else {    sql.setLong("tableId", dColumnDesc.getTableId());    }            sql.fillParams(ps);           sql.log(this.getClass());           ps.executeUpdate();          } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);           } finally {                    JdbcUtil.close(ps);           }  } /**   * 主键查询的SQL。   * @return String ： 主键查询的SQL。  */ public GenericVO findByPK(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }     DColumnDescSVO dColumnDesc=(DColumnDescSVO) vo;       Sql sql = new Sql("SELECT COLUMN_DESC,COLUMN_DESC_ID,COLUMN_NAME,CREATE_TIME,DATA_TYPE,SEQ,TABLE_ID FROM D_COLUMN_DESC WHERE 1=1  ");      Connection conn = null;      PreparedStatement ps = null;      ResultSet rs = null;      dColumnDesc =null;      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           sql.fillParams(ps);           sql.log(this.getClass());           rs = ps.executeQuery();            while (rs.next()) {           dColumnDesc = new DColumnDescSVO();           dColumnDesc.setColumnDesc(rs.getString("COLUMN_DESC"));           dColumnDesc.setColumnDescId(rs.getString("COLUMN_DESC_ID"));           dColumnDesc.setColumnName(rs.getString("COLUMN_NAME"));           dColumnDesc.setCreateTime(rs.getTimestamp("CREATE_TIME"));           dColumnDesc.setDataType(rs.getString("DATA_TYPE"));           dColumnDesc.setSeq(rs.getString("SEQ"));           dColumnDesc.setTableId(rs.getString("TABLE_ID"));                 }           } catch (SQLException se) {             throw new SysException("100003", "JDBC操作异常！", se);                      } finally {                    JdbcUtil.close(rs,ps);           }           return dColumnDesc;         } /**   * 批量查询的SQL。   * @return String ： 批量查询的SQL。  */ public List findByVO(GenericVO vo) throws AppException, SysException{         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }       DColumnDescSVO dColumnDesc=(DColumnDescSVO) vo;          List res = new ArrayList();          Connection conn = null;          PreparedStatement ps = null;          ResultSet rs = null;          Sql sql = new Sql("SELECT COLUMN_DESC,COLUMN_DESC_ID,COLUMN_NAME,CREATE_TIME,DATA_TYPE,SEQ,TABLE_ID FROM D_COLUMN_DESC WHERE 1=1 ");     try {if (dColumnDesc.getFlagColumnDesc() == 1) {      if (StringUtil.isBlank(dColumnDesc.getColumnDesc())) {             sql.append(" and COLUMN_DESC is null ");          }      else{             sql.append(" and COLUMN_DESC=:columnDesc");             sql.setString("columnDesc", dColumnDesc.getColumnDesc());          }   } if (dColumnDesc.getFlagColumnDescId() == 1) {      if (StringUtil.isBlank(dColumnDesc.getColumnDescId())) {             sql.append(" and COLUMN_DESC_ID is null ");          }      else{             sql.append(" and COLUMN_DESC_ID=:columnDescId");             sql.setLong("columnDescId", dColumnDesc.getColumnDescId());          }   } if (dColumnDesc.getFlagColumnName() == 1) {      if (StringUtil.isBlank(dColumnDesc.getColumnName())) {             sql.append(" and COLUMN_NAME is null ");          }      else{             sql.append(" and COLUMN_NAME=:columnName");             sql.setString("columnName", dColumnDesc.getColumnName());          }   } if (dColumnDesc.getFlagCreateTime() == 1) {      if (dColumnDesc.getCreateTime() == null) {             sql.append(" and CREATE_TIME is null ");          }      else{             sql.append(" and CREATE_TIME=:createTime");             sql.setTimestamp("createTime", dColumnDesc.getCreateTime());          }   } if (dColumnDesc.getFlagDataType() == 1) {      if (StringUtil.isBlank(dColumnDesc.getDataType())) {             sql.append(" and DATA_TYPE is null ");          }      else{             sql.append(" and DATA_TYPE=:dataType");             sql.setString("dataType", dColumnDesc.getDataType());          }   } if (dColumnDesc.getFlagSeq() == 1) {      if (StringUtil.isBlank(dColumnDesc.getSeq())) {             sql.append(" and SEQ is null ");          }      else{             sql.append(" and SEQ=:seq");             sql.setLong("seq", dColumnDesc.getSeq());          }   } if (dColumnDesc.getFlagTableId() == 1) {      if (StringUtil.isBlank(dColumnDesc.getTableId())) {             sql.append(" and TABLE_ID is null ");          }      else{             sql.append(" and TABLE_ID=:tableId");             sql.setLong("tableId", dColumnDesc.getTableId());          }   }            conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           ps = sql.fillParams(ps);           sql.log(this.getClass());           rs = ps.executeQuery();                    while (rs.next()) {           dColumnDesc = new DColumnDescSVO();           dColumnDesc.setColumnDesc(rs.getString("COLUMN_DESC"));           dColumnDesc.setColumnDescId(rs.getString("COLUMN_DESC_ID"));           dColumnDesc.setColumnName(rs.getString("COLUMN_NAME"));           dColumnDesc.setCreateTime(rs.getTimestamp("CREATE_TIME"));           dColumnDesc.setDataType(rs.getString("DATA_TYPE"));           dColumnDesc.setSeq(rs.getString("SEQ"));           dColumnDesc.setTableId(rs.getString("TABLE_ID"));               res.add(dColumnDesc);                            }          } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);            } finally {                JdbcUtil.close(rs,ps);               }                         if(0 == res.size()) res = null;          return res;   } /**   * 更新的SQL。   * @return String ： 更新的SQL。  */ public void update(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }       DColumnDescSVO dColumnDesc=(DColumnDescSVO) vo;          Connection conn = null;          PreparedStatement ps = null;          Sql sql = new Sql("UPDATE D_COLUMN_DESC SET ");     try {if (dColumnDesc.getFlagColumnDesc() == 1) {sql.append("COLUMN_DESC=:columnDesc,"); sql.setString("columnDesc", dColumnDesc.getColumnDesc()); } if (dColumnDesc.getFlagColumnDescId() == 1) {sql.append("COLUMN_DESC_ID=:columnDescId,");sql.setLong("columnDescId", dColumnDesc.getColumnDescId()); } if (dColumnDesc.getFlagColumnName() == 1) {sql.append("COLUMN_NAME=:columnName,"); sql.setString("columnName", dColumnDesc.getColumnName()); } if (dColumnDesc.getFlagCreateTime() == 1) {sql.append("CREATE_TIME=:createTime,"); sql.setTimestamp("createTime", dColumnDesc.getCreateTime()); } if (dColumnDesc.getFlagDataType() == 1) {sql.append("DATA_TYPE=:dataType,"); sql.setString("dataType", dColumnDesc.getDataType()); } if (dColumnDesc.getFlagSeq() == 1) {sql.append("SEQ=:seq,");sql.setLong("seq", dColumnDesc.getSeq()); } if (dColumnDesc.getFlagTableId() == 1) {sql.append("TABLE_ID=:tableId,");sql.setLong("tableId", dColumnDesc.getTableId()); }            	sql.removeSuffix(1); sql.append(" WHERE 1=1 ");           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           ps = sql.fillParams(ps);           sql.log(this.getClass());           ps.executeUpdate();                    } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);            } finally {                JdbcUtil.close(ps);               }                  } /**   * 批量增加记录。   * @return String ： 批量增加的SQL。  */ public void addBat(List list)throws AppException, SysException {     if (list == null) {     throw new AppException("100001", "缺少DAO操作对象！");           }          Connection conn = null;          PreparedStatement ps = null;Sql sql = new Sql("INSERT INTO D_COLUMN_DESC(COLUMN_DESC,COLUMN_DESC_ID,COLUMN_NAME,CREATE_TIME,DATA_TYPE,SEQ,TABLE_ID)");sql.append(" VALUES (:columnDesc,:columnDescId,:columnName,:createTime,:dataType,:seq,:tableId)");      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());    for(int i=0;i<list.size();i++){       DColumnDescSVO dColumnDesc=(DColumnDescSVO) list.get(i);         if (dColumnDesc== null) {         throw new AppException("100001", "缺少DAO操作对象！");       }      if (StringUtil.isBlank(dColumnDesc.getColumnDesc())) {      sql.setNullString("columnDesc");     } else {    sql.setString("columnDesc", dColumnDesc.getColumnDesc());    }       if (StringUtil.isBlank(dColumnDesc.getColumnDescId())) {      sql.setNullLong("columnDescId");     } else {    sql.setLong("columnDescId", dColumnDesc.getColumnDescId());    }       if (StringUtil.isBlank(dColumnDesc.getColumnName())) {      sql.setNullString("columnName");     } else {    sql.setString("columnName", dColumnDesc.getColumnName());    }    if (dColumnDesc.getCreateTime() == null) {      sql.setNullDate("createTime");     } else {    sql.setTimestamp("createTime", dColumnDesc.getCreateTime());    }       if (StringUtil.isBlank(dColumnDesc.getDataType())) {      sql.setNullString("dataType");     } else {    sql.setString("dataType", dColumnDesc.getDataType());    }       if (StringUtil.isBlank(dColumnDesc.getSeq())) {      sql.setNullLong("seq");     } else {    sql.setLong("seq", dColumnDesc.getSeq());    }       if (StringUtil.isBlank(dColumnDesc.getTableId())) {      sql.setNullLong("tableId");     } else {    sql.setLong("tableId", dColumnDesc.getTableId());    }            sql.fillParams(ps);           sql.log(this.getClass());           sql.clearParameters();           ps.addBatch();           }                  ps.executeBatch();          } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);           } finally {                    JdbcUtil.close(ps);           }  } /**   * 根据传入参数删除一条或者一批记录。   * @return String ： 删除的SQL。  */ public void delete(GenericVO vo)throws AppException, SysException {         if (vo== null) {         throw new AppException("100001", "缺少DAO操作对象！");        }     DColumnDescSVO dColumnDesc=(DColumnDescSVO) vo;          Connection conn = null;          PreparedStatement ps = null;       Sql sql = new Sql("DELETE FROM D_COLUMN_DESC WHERE 1=1  ");      try {           conn = ConnectionFactory.getConnection();           ps = conn.prepareStatement(sql.getSql());           sql.fillParams(ps);           sql.log(this.getClass());           ps.executeUpdate();            } catch (SQLException se) {           throw new SysException("100003", "JDBC操作异常！", se);                      } finally {                    JdbcUtil.close(ps);           }         } /**   * 注销一条或者一批   * @return String ： 注销一条或者一批的SQL。  */ public void unable(GenericVO vo)throws AppException, SysException {     DColumnDescSVO dColumnDesc=(DColumnDescSVO) vo;       }}
