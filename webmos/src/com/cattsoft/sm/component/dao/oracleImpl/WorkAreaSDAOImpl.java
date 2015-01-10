package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.sm.component.dao.IWorkAreaSDAO;
import com.cattsoft.sm.vo.WorkAreaSVO;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.sm.util.ReflectUtil;



public class WorkAreaSDAOImpl implements IWorkAreaSDAO {
	
    private Logger log = Logger.getLogger(WorkAreaSDAOImpl.class);

   

    public PagView findByPage(GenericVO vo, PagInfo pagInfo) throws AppException, SysException {
        List results = null;
        WorkAreaSVO workArea = (WorkAreaSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL,a.PARENT_WORK_AREA_ID, ")
                .append(" a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.channel_id,a.work_mode ");
        sql.append(" from WORK_AREA a where 1=1");
        if (workArea.getWorkAreaId() != null) {
            sql.append(" and WORK_AREA_ID=" + workArea.getWorkAreaId());
        }
        if (workArea.getName() != null) {
            sql.append(" and NAME=" + workArea.getName());
        }
        if (workArea.getWorkTypeId() != null) {
            sql.append(" and WORK_TYPE_ID=" + workArea.getWorkTypeId());
        }
        if (workArea.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=" + workArea.getLocalNetId());
        }
        if (workArea.getAreaId() != null) {
            sql.append(" and AREA_ID=" + workArea.getAreaId());
        }
        if (workArea.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=" + workArea.getServDeptId());
        }
        if (workArea.getBranchId() != null) {
            sql.append(" and BRANCH_ID=" + workArea.getBranchId());
        }
        if (workArea.getDispatchLevel() != null) {
            sql.append(" and DISPATCH_LEVEL=" + workArea.getDispatchLevel());
        }
        if (workArea.getParentWorkAreaId() != null) {
            sql.append(" and PARENT_WORK_AREA_ID=" + workArea.getParentWorkAreaId());
        }
        if (workArea.getStandardCode() != null) {
            sql.append(" and STANDARD_CODE=" + workArea.getStandardCode());
        }
        if (workArea.getSts() != null) {
            sql.append(" and STS='" + workArea.getSts() + "'");
        }
        if (workArea.getStsDate() != null) {
            sql.append(" and STS_DATE=" + workArea.getStsDate());
        }
        if (workArea.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=" + workArea.getCreateDate());
        }
        if (workArea.getChannelId()!= null) {
            sql.append(" and channel_id='" + workArea.getChannelId()+"'");
        }
        if (workArea.getWorkMode()!= null) {
            sql.append(" and work_mode='" + workArea.getWorkMode()+"'");
        }
        sql.append(" order by work_area_id");
        log.debug(sql.toString());
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(WorkAreaSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, WorkAreaSVO.class);
            pagView.setViewList(results);
        } catch (SQLException e) {
            throw new SysException("", "findByVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return pagView;
    }

    public PagView findBySet(HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL,a.PARENT_WORK_AREA_ID, ")
                       .append(" a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.channel_id,a.work_mode ");
        sql.append(" from WORK_AREA a where 1=1 and (");

        sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "work_area_id", "workAreaId"));
        sql.append(")");
        sql.append(" order by a.work_area_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(WorkAreaSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, WorkAreaSVO.class);
            pagView.setViewList(results);
        } catch (SQLException e) {
            throw new SysException("", "findByVO error..", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
        return pagView;
    }
    
  
	/**
	   * 增加。
	   * @return String
	  */
	 public void add(GenericVO vo)throws AppException, SysException {
	         if (vo== null) {
	         throw new AppException("100001", "缺少DAO操作对象！"); 
	       }
	     WorkAreaSVO workArea=(WorkAreaSVO) vo;
	    if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	       throw new AppException("100002", "缺少对象的主键！");
	      }

	      Connection conn = null;
	      PreparedStatement ps = null;
	Sql sql = new Sql("INSERT INTO WORK_AREA(ACTION_ID,AREA_ID,BRANCH_ID,CHANNEL_ID,CREATE_DATE,DISPATCH_LEVEL,LOCAL_NET_ID,NAME,PARENT_WORK_AREA_ID,REMARKS,SERV_DEPT_ID,STANDARD_CODE,STS,STS_DATE,TERM_NBR,TERM_TYPE,WORK_AREA_ID,WORK_MODE,WORK_TYPE_ID)");
	sql.append(" VALUES (:actionId,:areaId,:branchId,:channelId,:createDate,:dispatchLevel,:localNetId,:name,:parentWorkAreaId,:remarks,:servDeptId,:standardCode,:sts,:stsDate,:termNbr,:termType,:workAreaId,:workMode,:workTypeId)");
	      try {
	           conn = ConnectionFactory.getConnection();
	           ps = conn.prepareStatement(sql.getSql());
	      if (StringUtil.isBlank(workArea.getActionId())) {
	      sql.setNullLong("actionId");
	     } else {
	    sql.setLong("actionId", workArea.getActionId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getAreaId())) {
	      sql.setNullLong("areaId");
	     } else {
	    sql.setLong("areaId", workArea.getAreaId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getBranchId())) {
	      sql.setNullLong("branchId");
	     } else {
	    sql.setLong("branchId", workArea.getBranchId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getChannelId())) {
	      sql.setNullLong("channelId");
	     } else {
	    sql.setLong("channelId", workArea.getChannelId());
	    }
	 
	   if (workArea.getCreateDate() == null) {
	      sql.setNullDate("createDate");
	     } else {
	    sql.setTimestamp("createDate", workArea.getCreateDate());
	    }
	 
	      if (StringUtil.isBlank(workArea.getDispatchLevel())) {
	      sql.setNullLong("dispatchLevel");
	     } else {
	    sql.setLong("dispatchLevel", workArea.getDispatchLevel());
	    }
	 
	      if (StringUtil.isBlank(workArea.getLocalNetId())) {
	      sql.setNullLong("localNetId");
	     } else {
	    sql.setLong("localNetId", workArea.getLocalNetId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getName())) {
	      sql.setNullString("name");
	     } else {
	    sql.setString("name", workArea.getName());
	    }
	 
	      if (StringUtil.isBlank(workArea.getParentWorkAreaId())) {
	      sql.setNullLong("parentWorkAreaId");
	     } else {
	    sql.setLong("parentWorkAreaId", workArea.getParentWorkAreaId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getRemarks())) {
	      sql.setNullString("remarks");
	     } else {
	    sql.setString("remarks", workArea.getRemarks());
	    }
	 
	      if (StringUtil.isBlank(workArea.getServDeptId())) {
	      sql.setNullString("servDeptId");
	     } else {
	    sql.setString("servDeptId", workArea.getServDeptId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getStandardCode())) {
	      sql.setNullString("standardCode");
	     } else {
	    sql.setString("standardCode", workArea.getStandardCode());
	    }
	 
	      if (StringUtil.isBlank(workArea.getSts())) {
	      sql.setNullString("sts");
	     } else {
	    sql.setString("sts", workArea.getSts());
	    }
	 
	   if (workArea.getStsDate() == null) {
	      sql.setNullDate("stsDate");
	     } else {
	    sql.setTimestamp("stsDate", workArea.getStsDate());
	    }
	 
	      if (StringUtil.isBlank(workArea.getTermNbr())) {
	      sql.setNullString("termNbr");
	     } else {
	    sql.setString("termNbr", workArea.getTermNbr());
	    }
	 
	      if (StringUtil.isBlank(workArea.getTermType())) {
	      sql.setNullString("termType");
	     } else {
	    sql.setString("termType", workArea.getTermType());
	    }
	 
	      if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	      sql.setNullLong("workAreaId");
	     } else {
	    sql.setLong("workAreaId", workArea.getWorkAreaId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getWorkMode())) {
	      sql.setNullString("workMode");
	     } else {
	    sql.setString("workMode", workArea.getWorkMode());
	    }
	 
	      if (StringUtil.isBlank(workArea.getWorkTypeId())) {
	      sql.setNullLong("workTypeId");
	     } else {
	    sql.setLong("workTypeId", workArea.getWorkTypeId());
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
	     WorkAreaSVO workArea=(WorkAreaSVO) vo;
	    if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	       throw new AppException("100002", "缺少对象的主键！");
	      }

	       Sql sql = new Sql("SELECT ACTION_ID,AREA_ID,BRANCH_ID,CHANNEL_ID,CREATE_DATE,DISPATCH_LEVEL,LOCAL_NET_ID,NAME,PARENT_WORK_AREA_ID,REMARKS,SERV_DEPT_ID,STANDARD_CODE,STS,STS_DATE,TERM_NBR,TERM_TYPE,WORK_AREA_ID,WORK_MODE,WORK_TYPE_ID FROM WORK_AREA WHERE 1=1  ");
	sql.append(" and WORK_AREA_ID=:workAreaId");
	sql.setLong("workAreaId", workArea.getWorkAreaId());
	 

	      Connection conn = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      workArea =null;
	      try {
	           conn = ConnectionFactory.getConnection();
	           ps = conn.prepareStatement(sql.getSql());
	           sql.fillParams(ps);
	           sql.log(this.getClass());
	           rs = ps.executeQuery();
	 
	           while (rs.next()) {
	           workArea = new WorkAreaSVO();
	           workArea.setActionId(rs.getString("ACTION_ID"));
	           workArea.setAreaId(rs.getString("AREA_ID"));
	           workArea.setBranchId(rs.getString("BRANCH_ID"));
	           workArea.setChannelId(rs.getString("CHANNEL_ID"));
	           workArea.setCreateDate(rs.getTimestamp("CREATE_DATE"));
	           workArea.setDispatchLevel(rs.getString("DISPATCH_LEVEL"));
	           workArea.setLocalNetId(rs.getString("LOCAL_NET_ID"));
	           workArea.setName(rs.getString("NAME"));
	           workArea.setParentWorkAreaId(rs.getString("PARENT_WORK_AREA_ID"));
	           workArea.setRemarks(rs.getString("REMARKS"));
	           workArea.setServDeptId(rs.getString("SERV_DEPT_ID"));
	           workArea.setStandardCode(rs.getString("STANDARD_CODE"));
	           workArea.setSts(rs.getString("STS"));
	           workArea.setStsDate(rs.getTimestamp("STS_DATE"));
	           workArea.setTermNbr(rs.getString("TERM_NBR"));
	           workArea.setTermType(rs.getString("TERM_TYPE"));
	           workArea.setWorkAreaId(rs.getString("WORK_AREA_ID"));
	           workArea.setWorkMode(rs.getString("WORK_MODE"));
	           workArea.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
	                 }
	           } catch (SQLException se) {
	             throw new SysException("100003", "JDBC操作异常！", se);
	           
	           } finally {
	                    JdbcUtil.close(rs,ps);
	           }
	           return workArea;
	         }
	 /**
	   * 批量查询的SQL。
	   * @return String ： 批量查询的SQL。
	  */
	 public List findByVO(GenericVO vo) throws AppException, SysException{
	         if (vo== null) {
	         throw new AppException("100001", "缺少DAO操作对象！"); 
	       }
	       WorkAreaSVO workArea=(WorkAreaSVO) vo;
	          List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
	          Connection conn = null;
	          PreparedStatement ps = null;
	          ResultSet rs = null;
	          Sql sql = new Sql("SELECT ACTION_ID,AREA_ID,BRANCH_ID,CHANNEL_ID,CREATE_DATE,DISPATCH_LEVEL,LOCAL_NET_ID,NAME,PARENT_WORK_AREA_ID,REMARKS,SERV_DEPT_ID,STANDARD_CODE,STS,STS_DATE,TERM_NBR,TERM_TYPE,WORK_AREA_ID,WORK_MODE,WORK_TYPE_ID FROM WORK_AREA WHERE 1=1 ");
	     try {
	if (workArea.getFlagActionId() == 1) {
	      if (StringUtil.isBlank(workArea.getActionId())) {
	             sql.append(" and ACTION_ID is null ");
	          }
	      else{
	             sql.append(" and ACTION_ID=:actionId");
	             sql.setLong("actionId", workArea.getActionId());
	          }
	   }
	 
	if (workArea.getFlagAreaId() == 1) {
	      if (StringUtil.isBlank(workArea.getAreaId())) {
	             sql.append(" and AREA_ID is null ");
	          }
	      else{
	             sql.append(" and AREA_ID=:areaId");
	             sql.setLong("areaId", workArea.getAreaId());
	          }
	   }
	 
	if (workArea.getFlagBranchId() == 1) {
	      if (StringUtil.isBlank(workArea.getBranchId())) {
	             sql.append(" and BRANCH_ID is null ");
	          }
	      else{
	             sql.append(" and BRANCH_ID=:branchId");
	             sql.setLong("branchId", workArea.getBranchId());
	          }
	   }
	 
	if (workArea.getFlagChannelId() == 1) {
	      if (StringUtil.isBlank(workArea.getChannelId())) {
	             sql.append(" and CHANNEL_ID is null ");
	          }
	      else{
	             sql.append(" and CHANNEL_ID=:channelId");
	             sql.setLong("channelId", workArea.getChannelId());
	          }
	   }
	 
	if (workArea.getFlagCreateDate() == 1) {
	      if (workArea.getCreateDate() == null) {
	             sql.append(" and CREATE_DATE is null ");
	          }
	      else{
	             sql.append(" and CREATE_DATE=:createDate");
	             sql.setTimestamp("createDate", workArea.getCreateDate());
	          }
	   }
	 
	if (workArea.getFlagDispatchLevel() == 1) {
	      if (StringUtil.isBlank(workArea.getDispatchLevel())) {
	             sql.append(" and DISPATCH_LEVEL is null ");
	          }
	      else{
	             sql.append(" and DISPATCH_LEVEL=:dispatchLevel");
	             sql.setLong("dispatchLevel", workArea.getDispatchLevel());
	          }
	   }
	 
	if (workArea.getFlagLocalNetId() == 1) {
	      if (StringUtil.isBlank(workArea.getLocalNetId())) {
	             sql.append(" and LOCAL_NET_ID is null ");
	          }
	      else{
	             sql.append(" and LOCAL_NET_ID=:localNetId");
	             sql.setLong("localNetId", workArea.getLocalNetId());
	          }
	   }
	 
	if (workArea.getFlagName() == 1) {
	      if (StringUtil.isBlank(workArea.getName())) {
	             sql.append(" and NAME is null ");
	          }
	      else{
	             sql.append(" and NAME=:name");
	             sql.setString("name", workArea.getName());
	          }
	   }
	 
	if (workArea.getFlagParentWorkAreaId() == 1) {
	      if (StringUtil.isBlank(workArea.getParentWorkAreaId())) {
	             sql.append(" and PARENT_WORK_AREA_ID is null ");
	          }
	      else{
	             sql.append(" and PARENT_WORK_AREA_ID=:parentWorkAreaId");
	             sql.setLong("parentWorkAreaId", workArea.getParentWorkAreaId());
	          }
	   }
	 
	if (workArea.getFlagRemarks() == 1) {
	      if (StringUtil.isBlank(workArea.getRemarks())) {
	             sql.append(" and REMARKS is null ");
	          }
	      else{
	             sql.append(" and REMARKS=:remarks");
	             sql.setString("remarks", workArea.getRemarks());
	          }
	   }
	 
	if (workArea.getFlagServDeptId() == 1) {
	      if (StringUtil.isBlank(workArea.getServDeptId())) {
	             sql.append(" and SERV_DEPT_ID is null ");
	          }
	      else{
	             sql.append(" and SERV_DEPT_ID=:servDeptId");
	             sql.setString("servDeptId", workArea.getServDeptId());
	          }
	   }
	 
	if (workArea.getFlagStandardCode() == 1) {
	      if (StringUtil.isBlank(workArea.getStandardCode())) {
	             sql.append(" and STANDARD_CODE is null ");
	          }
	      else{
	             sql.append(" and STANDARD_CODE=:standardCode");
	             sql.setString("standardCode", workArea.getStandardCode());
	          }
	   }
	 
	if (workArea.getFlagSts() == 1) {
	      if (StringUtil.isBlank(workArea.getSts())) {
	             sql.append(" and STS is null ");
	          }
	      else{
	             sql.append(" and STS=:sts");
	             sql.setString("sts", workArea.getSts());
	          }
	   }
	 
	if (workArea.getFlagStsDate() == 1) {
	      if (workArea.getStsDate() == null) {
	             sql.append(" and STS_DATE is null ");
	          }
	      else{
	             sql.append(" and STS_DATE=:stsDate");
	             sql.setTimestamp("stsDate", workArea.getStsDate());
	          }
	   }
	 
	if (workArea.getFlagTermNbr() == 1) {
	      if (StringUtil.isBlank(workArea.getTermNbr())) {
	             sql.append(" and TERM_NBR is null ");
	          }
	      else{
	             sql.append(" and TERM_NBR=:termNbr");
	             sql.setString("termNbr", workArea.getTermNbr());
	          }
	   }
	 
	if (workArea.getFlagTermType() == 1) {
	      if (StringUtil.isBlank(workArea.getTermType())) {
	             sql.append(" and TERM_TYPE is null ");
	          }
	      else{
	             sql.append(" and TERM_TYPE=:termType");
	             sql.setString("termType", workArea.getTermType());
	          }
	   }
	 
	if (workArea.getFlagWorkAreaId() == 1) {
	      if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	             sql.append(" and WORK_AREA_ID is null ");
	          }
	      else{
	             sql.append(" and WORK_AREA_ID=:workAreaId");
	             sql.setLong("workAreaId", workArea.getWorkAreaId());
	          }
	   }
	 
	if (workArea.getFlagWorkMode() == 1) {
	      if (StringUtil.isBlank(workArea.getWorkMode())) {
	             sql.append(" and WORK_MODE is null ");
	          }
	      else{
	             sql.append(" and WORK_MODE=:workMode");
	             sql.setString("workMode", workArea.getWorkMode());
	          }
	   }
	 
	if (workArea.getFlagWorkTypeId() == 1) {
	      if (StringUtil.isBlank(workArea.getWorkTypeId())) {
	             sql.append(" and WORK_TYPE_ID is null ");
	          }
	      else{
	             sql.append(" and WORK_TYPE_ID=:workTypeId");
	             sql.setLong("workTypeId", workArea.getWorkTypeId());
	          }
	   }
	sql.append(" order by NAME,WORK_AREA_ID ");
	           conn = ConnectionFactory.getConnection();
	           ps = conn.prepareStatement(sql.getSql());
	           ps = sql.fillParams(ps);
	           sql.log(this.getClass());
	           rs = ps.executeQuery();
	          
	          while (rs.next()) {
	           workArea = new WorkAreaSVO();
	           workArea.setActionId(rs.getString("ACTION_ID"));
	           workArea.setAreaId(rs.getString("AREA_ID"));
	           workArea.setBranchId(rs.getString("BRANCH_ID"));
	           workArea.setChannelId(rs.getString("CHANNEL_ID"));
	           workArea.setCreateDate(rs.getTimestamp("CREATE_DATE"));
	           workArea.setDispatchLevel(rs.getString("DISPATCH_LEVEL"));
	           workArea.setLocalNetId(rs.getString("LOCAL_NET_ID"));
	           workArea.setName(rs.getString("NAME"));
	           workArea.setParentWorkAreaId(rs.getString("PARENT_WORK_AREA_ID"));
	           workArea.setRemarks(rs.getString("REMARKS"));
	           workArea.setServDeptId(rs.getString("SERV_DEPT_ID"));
	           workArea.setStandardCode(rs.getString("STANDARD_CODE"));
	           workArea.setSts(rs.getString("STS"));
	           workArea.setStsDate(rs.getTimestamp("STS_DATE"));
	           workArea.setTermNbr(rs.getString("TERM_NBR"));
	           workArea.setTermType(rs.getString("TERM_TYPE"));
	           workArea.setWorkAreaId(rs.getString("WORK_AREA_ID"));
	           workArea.setWorkMode(rs.getString("WORK_MODE"));
	           workArea.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
	               res.add(workArea);
	              
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
	       WorkAreaSVO workArea=(WorkAreaSVO) vo;
	    if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	       throw new AppException("100002", "缺少对象的主键！");
	      }
	          Connection conn = null;
	          PreparedStatement ps = null;
	          Sql sql = new Sql("UPDATE WORK_AREA SET ");
	     try {
	if (workArea.getFlagActionId() == 1) {
	sql.append("ACTION_ID=:actionId,");
	sql.setLong("actionId", workArea.getActionId());
	 }
	 
	if (workArea.getFlagAreaId() == 1) {
	sql.append("AREA_ID=:areaId,");
	sql.setLong("areaId", workArea.getAreaId());
	 }
	 
	if (workArea.getFlagBranchId() == 1) {
	sql.append("BRANCH_ID=:branchId,");
	sql.setLong("branchId", workArea.getBranchId());
	 }
	 
	if (workArea.getFlagChannelId() == 1) {
	sql.append("CHANNEL_ID=:channelId,");
	sql.setLong("channelId", workArea.getChannelId());
	 }
	 
	if (workArea.getFlagCreateDate() == 1) {
	sql.append("CREATE_DATE=:createDate,");
	 sql.setTimestamp("createDate", workArea.getCreateDate());
	 }
	 
	if (workArea.getFlagDispatchLevel() == 1) {
	sql.append("DISPATCH_LEVEL=:dispatchLevel,");
	sql.setLong("dispatchLevel", workArea.getDispatchLevel());
	 }
	 
	if (workArea.getFlagLocalNetId() == 1) {
	sql.append("LOCAL_NET_ID=:localNetId,");
	sql.setLong("localNetId", workArea.getLocalNetId());
	 }
	 
	if (workArea.getFlagName() == 1) {
	sql.append("NAME=:name,");
	 sql.setString("name", workArea.getName());
	 }
	 
	if (workArea.getFlagParentWorkAreaId() == 1) {
	sql.append("PARENT_WORK_AREA_ID=:parentWorkAreaId,");
	sql.setLong("parentWorkAreaId", workArea.getParentWorkAreaId());
	 }
	 
	if (workArea.getFlagRemarks() == 1) {
	sql.append("REMARKS=:remarks,");
	 sql.setString("remarks", workArea.getRemarks());
	 }
	 
	if (workArea.getFlagServDeptId() == 1) {
	sql.append("SERV_DEPT_ID=:servDeptId,");
	sql.setString("servDeptId", workArea.getServDeptId());
	 }
	 
	if (workArea.getFlagStandardCode() == 1) {
	sql.append("STANDARD_CODE=:standardCode,");
	 sql.setString("standardCode", workArea.getStandardCode());
	 }
	 
	if (workArea.getFlagSts() == 1) {
	sql.append("STS=:sts,");
	 sql.setString("sts", workArea.getSts());
	 }
	 
	if (workArea.getFlagStsDate() == 1) {
	sql.append("STS_DATE=:stsDate,");
	 sql.setTimestamp("stsDate", workArea.getStsDate());
	 }
	 
	if (workArea.getFlagTermNbr() == 1) {
	sql.append("TERM_NBR=:termNbr,");
	 sql.setString("termNbr", workArea.getTermNbr());
	 }
	 
	if (workArea.getFlagTermType() == 1) {
	sql.append("TERM_TYPE=:termType,");
	 sql.setString("termType", workArea.getTermType());
	 }
	 
	if (workArea.getFlagWorkMode() == 1) {
	sql.append("WORK_MODE=:workMode,");
	 sql.setString("workMode", workArea.getWorkMode());
	 }
	 
	if (workArea.getFlagWorkTypeId() == 1) {
	sql.append("WORK_TYPE_ID=:workTypeId,");
	sql.setLong("workTypeId", workArea.getWorkTypeId());
	 }
	 
	           	sql.removeSuffix(1);
	 sql.append(" WHERE 1=1 ");
	sql.append(" and WORK_AREA_ID=:workAreaId");
	sql.setLong("workAreaId", workArea.getWorkAreaId());
	 
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
	Sql sql = new Sql("INSERT INTO WORK_AREA(ACTION_ID,AREA_ID,BRANCH_ID,CHANNEL_ID,CREATE_DATE,DISPATCH_LEVEL,LOCAL_NET_ID,NAME,PARENT_WORK_AREA_ID,REMARKS,SERV_DEPT_ID,STANDARD_CODE,STS,STS_DATE,TERM_NBR,TERM_TYPE,WORK_AREA_ID,WORK_MODE,WORK_TYPE_ID)");
	sql.append(" VALUES (:actionId,:areaId,:branchId,:channelId,:createDate,:dispatchLevel,:localNetId,:name,:parentWorkAreaId,:remarks,:servDeptId,:standardCode,:sts,:stsDate,:termNbr,:termType,:workAreaId,:workMode,:workTypeId)");
	      try {
	           conn = ConnectionFactory.getConnection();
	           ps = conn.prepareStatement(sql.getSql());
	    for(int i=0;i<list.size();i++){
	       WorkAreaSVO workArea=(WorkAreaSVO) list.get(i);
	         if (workArea== null) {
	         throw new AppException("100001", "缺少DAO操作对象！");
	       }
	    if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	       throw new AppException("100002", "缺少对象的主键！");
	      }
	      if (StringUtil.isBlank(workArea.getActionId())) {
	      sql.setNullLong("actionId");
	     } else {
	    sql.setLong("actionId", workArea.getActionId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getAreaId())) {
	      sql.setNullLong("areaId");
	     } else {
	    sql.setLong("areaId", workArea.getAreaId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getBranchId())) {
	      sql.setNullLong("branchId");
	     } else {
	    sql.setLong("branchId", workArea.getBranchId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getChannelId())) {
	      sql.setNullLong("channelId");
	     } else {
	    sql.setLong("channelId", workArea.getChannelId());
	    }
	 
	   if (workArea.getCreateDate() == null) {
	      sql.setNullDate("createDate");
	     } else {
	    sql.setTimestamp("createDate", workArea.getCreateDate());
	    }
	 
	      if (StringUtil.isBlank(workArea.getDispatchLevel())) {
	      sql.setNullLong("dispatchLevel");
	     } else {
	    sql.setLong("dispatchLevel", workArea.getDispatchLevel());
	    }
	 
	      if (StringUtil.isBlank(workArea.getLocalNetId())) {
	      sql.setNullLong("localNetId");
	     } else {
	    sql.setLong("localNetId", workArea.getLocalNetId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getName())) {
	      sql.setNullString("name");
	     } else {
	    sql.setString("name", workArea.getName());
	    }
	 
	      if (StringUtil.isBlank(workArea.getParentWorkAreaId())) {
	      sql.setNullLong("parentWorkAreaId");
	     } else {
	    sql.setLong("parentWorkAreaId", workArea.getParentWorkAreaId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getRemarks())) {
	      sql.setNullString("remarks");
	     } else {
	    sql.setString("remarks", workArea.getRemarks());
	    }
	 
	      if (StringUtil.isBlank(workArea.getServDeptId())) {
	      sql.setNullString("servDeptId");
	     } else {
	    sql.setString("servDeptId", workArea.getServDeptId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getStandardCode())) {
	      sql.setNullString("standardCode");
	     } else {
	    sql.setString("standardCode", workArea.getStandardCode());
	    }
	 
	      if (StringUtil.isBlank(workArea.getSts())) {
	      sql.setNullString("sts");
	     } else {
	    sql.setString("sts", workArea.getSts());
	    }
	 
	   if (workArea.getStsDate() == null) {
	      sql.setNullDate("stsDate");
	     } else {
	    sql.setTimestamp("stsDate", workArea.getStsDate());
	    }
	 
	      if (StringUtil.isBlank(workArea.getTermNbr())) {
	      sql.setNullString("termNbr");
	     } else {
	    sql.setString("termNbr", workArea.getTermNbr());
	    }
	 
	      if (StringUtil.isBlank(workArea.getTermType())) {
	      sql.setNullString("termType");
	     } else {
	    sql.setString("termType", workArea.getTermType());
	    }
	 
	      if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	      sql.setNullLong("workAreaId");
	     } else {
	    sql.setLong("workAreaId", workArea.getWorkAreaId());
	    }
	 
	      if (StringUtil.isBlank(workArea.getWorkMode())) {
	      sql.setNullString("workMode");
	     } else {
	    sql.setString("workMode", workArea.getWorkMode());
	    }
	 
	      if (StringUtil.isBlank(workArea.getWorkTypeId())) {
	      sql.setNullLong("workTypeId");
	     } else {
	    sql.setLong("workTypeId", workArea.getWorkTypeId());
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
	     WorkAreaSVO workArea=(WorkAreaSVO) vo;
	    if (StringUtil.isBlank(workArea.getWorkAreaId())) {
	       throw new AppException("100002", "缺少对象的主键！");
	      }
	          Connection conn = null;
	          PreparedStatement ps = null;
	       Sql sql = new Sql("DELETE FROM WORK_AREA WHERE 1=1  ");
	sql.append(" and WORK_AREA_ID=:workAreaId");
	sql.setLong("workAreaId", workArea.getWorkAreaId());
	 

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
	     WorkAreaSVO workArea=(WorkAreaSVO) vo;
	       }
}
