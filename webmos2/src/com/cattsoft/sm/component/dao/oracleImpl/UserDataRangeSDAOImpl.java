package com.cattsoft.sm.component.dao.oracleImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.sm.component.dao.IUserDataRangeSDAO;
import com.cattsoft.sm.vo.UserDataRangeSVO;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.pub.util.StringUtil;
 /**
   * 方法UserDataRangeSDAOImpl
   * <p>Company: 大唐软件</p>
   * @author ：白小亮。
   * @version 1.1  2007-9-23
  */
public class UserDataRangeSDAOImpl implements IUserDataRangeSDAO{
    private static Logger log = Logger.getLogger(UserDataRangeSDAOImpl.class); 
   private static final String UNABLE_STS = "P";
 
 /**
   * 增加。
   * @return String
  */
 public void add(GenericVO vo)throws AppException, SysException {
         if (vo== null) {
         throw new AppException("100001", "缺少DAO操作对象！"); 
       }
     UserDataRangeSVO userDataRange=(UserDataRangeSVO) vo;
    if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
       throw new AppException("100002", "缺少对象的主键！");
      }

      Connection conn = null;
      PreparedStatement ps = null;
Sql sql = new Sql("INSERT INTO USER_DATA_RANGE(ALLOW_FLAG,CREATE_DATE,DATA_RANGE_ID,RANGE_TYPE_ID,STS,STS_DATE,SYS_USER_ID,USER_DATA_AREA_ID)");
sql.append(" VALUES (:allowFlag,:createDate,:dataRangeId,:rangeTypeId,:sts,:stsDate,:sysUserId,:userDataAreaId)");
      try {
           conn = ConnectionFactory.getConnection();
           ps = conn.prepareStatement(sql.getSql());
      if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
      sql.setNullString("allowFlag");
     } else {
    sql.setString("allowFlag", userDataRange.getAllowFlag());
    }
 
   if (userDataRange.getCreateDate() == null) {
      sql.setNullDate("createDate");
     } else {
    sql.setTimestamp("createDate", userDataRange.getCreateDate());
    }
 
      if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
      sql.setNullLong("dataRangeId");
     } else {
    sql.setLong("dataRangeId", userDataRange.getDataRangeId());
    }
 
      if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
      sql.setNullString("rangeTypeId");
     } else {
    sql.setString("rangeTypeId", userDataRange.getRangeTypeId());
    }
 
      if (StringUtil.isBlank(userDataRange.getSts())) {
      sql.setNullString("sts");
     } else {
    sql.setString("sts", userDataRange.getSts());
    }
 
   if (userDataRange.getStsDate() == null) {
      sql.setNullDate("stsDate");
     } else {
    sql.setTimestamp("stsDate", userDataRange.getStsDate());
    }
 
      if (StringUtil.isBlank(userDataRange.getSysUserId())) {
      sql.setNullString("sysUserId");
     } else {
//    sql.setLong("sysUserId", userDataRange.getSysUserId());
    	 sql.setString("sysUserId", userDataRange.getSysUserId());
    }
 
      if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
      sql.setNullLong("userDataAreaId");
     } else {
    sql.setLong("userDataAreaId", userDataRange.getUserDataAreaId());
    }
 
           sql.fillParams(ps);
           sql.log(this.getClass());
           ps.executeUpdate();
          } catch (SQLException se) {
           throw new SysException("100003", "JDBC操作异常！", se);
           } finally {
                    JdbcUtil.close(ps);
           }
  }
 /**
   * 主键查询的SQL。
   * @return String ： 主键查询的SQL。
  */
 public GenericVO findByPK(GenericVO vo)throws AppException, SysException {
         if (vo== null) {
         throw new AppException("100001", "缺少DAO操作对象！"); 
       }
     UserDataRangeSVO userDataRange=(UserDataRangeSVO) vo;
    if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
       throw new AppException("100002", "缺少对象的主键！");
      }

       Sql sql = new Sql("SELECT ALLOW_FLAG,CREATE_DATE,DATA_RANGE_ID,RANGE_TYPE_ID,STS,STS_DATE,SYS_USER_ID,USER_DATA_AREA_ID FROM USER_DATA_RANGE WHERE 1=1  ");
sql.append(" and USER_DATA_AREA_ID=:userDataAreaId");
sql.setLong("userDataAreaId", userDataRange.getUserDataAreaId());
 

      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      userDataRange =null;
      try {
           conn = ConnectionFactory.getConnection();
           ps = conn.prepareStatement(sql.getSql());
           sql.fillParams(ps);
           sql.log(this.getClass());
           rs = ps.executeQuery();
 
           while (rs.next()) {
           userDataRange = new UserDataRangeSVO();
           userDataRange.setAllowFlag(rs.getString("ALLOW_FLAG"));
           userDataRange.setCreateDate(rs.getTimestamp("CREATE_DATE"));
           userDataRange.setDataRangeId(rs.getString("DATA_RANGE_ID"));
           userDataRange.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
           userDataRange.setSts(rs.getString("STS"));
           userDataRange.setStsDate(rs.getTimestamp("STS_DATE"));
           userDataRange.setSysUserId(rs.getString("SYS_USER_ID"));
           userDataRange.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
                 }
           } catch (SQLException se) {
             throw new SysException("100003", "JDBC操作异常！", se);
           
           } finally {
                    JdbcUtil.close(rs,ps);
           }
           return userDataRange;
         }
 /**
   * 批量查询的SQL。
   * @return String ： 批量查询的SQL。
  */
 public List findByVO(GenericVO vo) throws AppException, SysException{
         if (vo== null) {
         throw new AppException("100001", "缺少DAO操作对象！"); 
       }
       UserDataRangeSVO userDataRange=(UserDataRangeSVO) vo;
          List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
          Connection conn = null;
          PreparedStatement ps = null;
          ResultSet rs = null;
          Sql sql = new Sql("SELECT ALLOW_FLAG,CREATE_DATE,DATA_RANGE_ID,RANGE_TYPE_ID,STS,STS_DATE,SYS_USER_ID,USER_DATA_AREA_ID FROM USER_DATA_RANGE WHERE 1=1 ");
     try {
if (userDataRange.getFlagAllowFlag() == 1) {
      if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
             sql.append(" and ALLOW_FLAG is null ");
          }
      else{
             sql.append(" and ALLOW_FLAG=:allowFlag");
             sql.setString("allowFlag", userDataRange.getAllowFlag());
          }
   }
 
if (userDataRange.getFlagCreateDate() == 1) {
      if (userDataRange.getCreateDate() == null) {
             sql.append(" and CREATE_DATE is null ");
          }
      else{
             sql.append(" and CREATE_DATE=:createDate");
             sql.setTimestamp("createDate", userDataRange.getCreateDate());
          }
   }
 
if (userDataRange.getFlagDataRangeId() == 1) {
      if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
             sql.append(" and DATA_RANGE_ID is null ");
          }
      else{
             sql.append(" and DATA_RANGE_ID=:dataRangeId");
             sql.setLong("dataRangeId", userDataRange.getDataRangeId());
          }
   }
 
if (userDataRange.getFlagRangeTypeId() == 1) {
      if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
             sql.append(" and RANGE_TYPE_ID is null ");
          }
      else{
             sql.append(" and RANGE_TYPE_ID=:rangeTypeId");
             sql.setString("rangeTypeId", userDataRange.getRangeTypeId());
          }
   }
 
if (userDataRange.getFlagSts() == 1) {
      if (StringUtil.isBlank(userDataRange.getSts())) {
             sql.append(" and STS is null ");
          }
      else{
             sql.append(" and STS=:sts");
             sql.setString("sts", userDataRange.getSts());
          }
   }
 
if (userDataRange.getFlagStsDate() == 1) {
      if (userDataRange.getStsDate() == null) {
             sql.append(" and STS_DATE is null ");
          }
      else{
             sql.append(" and STS_DATE=:stsDate");
             sql.setTimestamp("stsDate", userDataRange.getStsDate());
          }
   }
 
if (userDataRange.getFlagSysUserId() == 1) {
      if (StringUtil.isBlank(userDataRange.getSysUserId())) {
             sql.append(" and SYS_USER_ID is null ");
          }
      else{
             sql.append(" and SYS_USER_ID=:sysUserId");
//             sql.setLong("sysUserId", userDataRange.getSysUserId());
             sql.setString("sysUserId", userDataRange.getSysUserId());
          }
   }
 
if (userDataRange.getFlagUserDataAreaId() == 1) {
      if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
             sql.append(" and USER_DATA_AREA_ID is null ");
          }
      else{
             sql.append(" and USER_DATA_AREA_ID=:userDataAreaId");
             sql.setLong("userDataAreaId", userDataRange.getUserDataAreaId());
          }
   }
 
           conn = ConnectionFactory.getConnection();
           ps = conn.prepareStatement(sql.getSql());
           ps = sql.fillParams(ps);
           sql.log(this.getClass());
           rs = ps.executeQuery();
          
          while (rs.next()) {
           userDataRange = new UserDataRangeSVO();
           userDataRange.setAllowFlag(rs.getString("ALLOW_FLAG"));
           userDataRange.setCreateDate(rs.getTimestamp("CREATE_DATE"));
           userDataRange.setDataRangeId(rs.getString("DATA_RANGE_ID"));
           userDataRange.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
           userDataRange.setSts(rs.getString("STS"));
           userDataRange.setStsDate(rs.getTimestamp("STS_DATE"));
           userDataRange.setSysUserId(rs.getString("SYS_USER_ID"));
           userDataRange.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
               res.add(userDataRange);
              
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
   * 更新的SQL。
   * @return String ： 更新的SQL。
  */
 public void update(GenericVO vo)throws AppException, SysException {
         if (vo== null) {
         throw new AppException("100001", "缺少DAO操作对象！"); 
       }
       UserDataRangeSVO userDataRange=(UserDataRangeSVO) vo;
    if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
       throw new AppException("100002", "缺少对象的主键！");
      }
          Connection conn = null;
          PreparedStatement ps = null;
          Sql sql = new Sql("UPDATE USER_DATA_RANGE SET ");
     try {
if (userDataRange.getFlagAllowFlag() == 1) {
sql.append("ALLOW_FLAG=:allowFlag,");
 sql.setString("allowFlag", userDataRange.getAllowFlag());
 }
 
if (userDataRange.getFlagCreateDate() == 1) {
sql.append("CREATE_DATE=:createDate,");
 sql.setTimestamp("createDate", userDataRange.getCreateDate());
 }
 
if (userDataRange.getFlagDataRangeId() == 1) {
sql.append("DATA_RANGE_ID=:dataRangeId,");
sql.setLong("dataRangeId", userDataRange.getDataRangeId());
 }
 
if (userDataRange.getFlagRangeTypeId() == 1) {
sql.append("RANGE_TYPE_ID=:rangeTypeId,");
 sql.setString("rangeTypeId", userDataRange.getRangeTypeId());
 }
 
if (userDataRange.getFlagSts() == 1) {
sql.append("STS=:sts,");
 sql.setString("sts", userDataRange.getSts());
 }
 
if (userDataRange.getFlagStsDate() == 1) {
sql.append("STS_DATE=:stsDate,");
 sql.setTimestamp("stsDate", userDataRange.getStsDate());
 }
 
if (userDataRange.getFlagSysUserId() == 1) {
sql.append("SYS_USER_ID=:sysUserId,");
//sql.setLong("sysUserId", userDataRange.getSysUserId());
sql.setString("sysUserId", userDataRange.getSysUserId());
 }
 
           	sql.removeSuffix(1);
 sql.append(" WHERE 1=1 ");
sql.append(" and USER_DATA_AREA_ID=:userDataAreaId");
sql.setLong("userDataAreaId", userDataRange.getUserDataAreaId());
 
           conn = ConnectionFactory.getConnection();
           ps = conn.prepareStatement(sql.getSql());
           ps = sql.fillParams(ps);
           sql.log(this.getClass());
           ps.executeUpdate();
          
          } catch (SQLException se) {
           throw new SysException("100003", "JDBC操作异常！", se);
            } finally {
                JdbcUtil.close(ps);
               }
               
   }
 /**
   * 批量增加记录。
   * @return String ： 批量增加的SQL。
  */
 public void addBat(List list)throws AppException, SysException {
     if (list == null) {
     throw new AppException("100001", "缺少DAO操作对象！");
           }
          Connection conn = null;
          PreparedStatement ps = null;
Sql sql = new Sql("INSERT INTO USER_DATA_RANGE(ALLOW_FLAG,CREATE_DATE,DATA_RANGE_ID,RANGE_TYPE_ID,STS,STS_DATE,SYS_USER_ID,USER_DATA_AREA_ID)");
sql.append(" VALUES (:allowFlag,:createDate,:dataRangeId,:rangeTypeId,:sts,:stsDate,:sysUserId,:userDataAreaId)");
      try {
           conn = ConnectionFactory.getConnection();
           ps = conn.prepareStatement(sql.getSql());
    for(int i=0;i<list.size();i++){
       UserDataRangeSVO userDataRange=(UserDataRangeSVO) list.get(i);
         if (userDataRange== null) {
         throw new AppException("100001", "缺少DAO操作对象！");
       }
    if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
       throw new AppException("100002", "缺少对象的主键！");
      }
      if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
      sql.setNullString("allowFlag");
     } else {
    sql.setString("allowFlag", userDataRange.getAllowFlag());
    }
 
   if (userDataRange.getCreateDate() == null) {
      sql.setNullDate("createDate");
     } else {
    sql.setTimestamp("createDate", userDataRange.getCreateDate());
    }
 
      if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
      sql.setNullLong("dataRangeId");
     } else {
    sql.setLong("dataRangeId", userDataRange.getDataRangeId());
    }
 
      if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
      sql.setNullString("rangeTypeId");
     } else {
    sql.setString("rangeTypeId", userDataRange.getRangeTypeId());
    }
 
      if (StringUtil.isBlank(userDataRange.getSts())) {
      sql.setNullString("sts");
     } else {
    sql.setString("sts", userDataRange.getSts());
    }
 
   if (userDataRange.getStsDate() == null) {
      sql.setNullDate("stsDate");
     } else {
    sql.setTimestamp("stsDate", userDataRange.getStsDate());
    }
 
      if (StringUtil.isBlank(userDataRange.getSysUserId())) {
      sql.setNullString("sysUserId");
     } else {
//    sql.setLong("sysUserId", userDataRange.getSysUserId());
    	sql.setString("sysUserId", userDataRange.getSysUserId());
    }
 
      if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
      sql.setNullLong("userDataAreaId");
     } else {
    sql.setLong("userDataAreaId", userDataRange.getUserDataAreaId());
    }
 
           sql.fillParams(ps);
           sql.log(this.getClass());
           sql.clearParameters();
           ps.addBatch();
           }
           
       ps.executeBatch();
          } catch (SQLException se) {
           throw new SysException("100003", "JDBC操作异常！", se);
           } finally {
                    JdbcUtil.close(ps);
           }
  }
 /**
   * 根据传入参数删除一条或者一批记录。
   * @return String ： 删除的SQL。
  */
 public void delete(GenericVO vo)throws AppException, SysException {
         if (vo== null) {
         throw new AppException("100001", "缺少DAO操作对象！"); 
       }
     UserDataRangeSVO userDataRange=(UserDataRangeSVO) vo;
    if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
       throw new AppException("100002", "缺少对象的主键！");
      }
          Connection conn = null;
          PreparedStatement ps = null;
       Sql sql = new Sql("DELETE FROM USER_DATA_RANGE WHERE 1=1  ");
sql.append(" and USER_DATA_AREA_ID=:userDataAreaId");
sql.setLong("userDataAreaId", userDataRange.getUserDataAreaId());
 

      try {
           conn = ConnectionFactory.getConnection();
           ps = conn.prepareStatement(sql.getSql());
           sql.fillParams(ps);
           sql.log(this.getClass());
           ps.executeUpdate();
 
           } catch (SQLException se) {
           throw new SysException("100003", "JDBC操作异常！", se);
           
           } finally {
                    JdbcUtil.close(ps);
           }
         }
 /**
   * 注销一条或者一批
   * @return String ： 注销一条或者一批的SQL。
  */
 public void unable(GenericVO vo)throws AppException, SysException {
     UserDataRangeSVO userDataRange=(UserDataRangeSVO) vo;
       }
}
