package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IWorkAreaExchMDAO;
import com.cattsoft.sm.vo.ExchSVO;
import com.cattsoft.sm.vo.WorkAreaExchMVO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;
import com.cattsoft.sm.vo.WorkAreaMVO;
import com.cattsoft.sm.vo.WorkAreaSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class WorkAreaExchMDAOImpl implements IWorkAreaExchMDAO {
	

    private static Logger log = Logger.getLogger(WorkAreaExchMDAOImpl.class);

    /**
	 * 查询所有Exch
	 * 
	 * @param vo
	 *            ExchVO
	 * @throws SysException
	 */

    public List findExchsUnSelSo(WorkAreaExchMVO waevo) throws SysException,
			AppException {

		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		// add by shilei 2008-11-20
		
		// sql.append("SELECT EX.* FROM EXCH EX, WORK_AREA WA WHERE EX.STS = 'A' AND WA.STS = 'A' AND EX.AREA_ID = WA.AREA_ID AND NOT EXISTS (SELECT *");
		//lijixu 2010-3-19 15:17:51 山西需要把不同服务区的工区与局向对到一起，在分配页面，如果选择服务区，刚要根据所
		// 选的服务区进行过滤(工区管理-选择局向按钮-弹出页面-根据本地网、服务区过滤尚未选择的局向)
		sql.append("SELECT EX.* FROM EXCH EX, WORK_AREA WA WHERE EX.STS = 'A' AND WA.STS = 'A' AND EX.LOCAL_NET_ID = WA.LOCAL_NET_ID AND NOT EXISTS (SELECT *");
		sql
				.append(" FROM WORK_AREA_EXCH WAE WHERE WAE.STS = 'A' AND WAE.EXCH_ID = EX.EXCH_ID AND WAE.WORK_AREA_ID = WA.WORK_AREA_ID) AND NOT EXISTS (SELECT *");
		sql
				.append(" FROM WORK_AREA_EXCH WAE, WORK_AREA WA2 WHERE WAE.STS = 'A' AND WA2.STS = 'A' AND WAE.EXCH_ID = EX.EXCH_ID AND WAE.WORK_AREA_ID = WA2.WORK_AREA_ID");
		sql
				.append(" AND WA2.WORK_TYPE_ID = WA.WORK_TYPE_ID) AND WA.WORK_AREA_ID = ? AND EX.AREA_ID=? ");
		if (waevo.getWorkAreaId() == null)
			return results;
		ExchSVO vo = waevo.getExchSVO();

		// if (vo != null && vo.getName() != null) {
		// sql.append(" and ex.name like '%?%");
		// }
		// if (vo != null && vo.getAreaId() != null) {
		// sql.append(" and ex.area_id=?");
		// }
		log.debug(sql);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, waevo.getWorkAreaId());
			
			// lijixu 2010-3-19 15:50:45 新增服务区查询条件
			ps.setString(index++, waevo.getExchSVO().getAreaId());
			// if (vo != null && vo.getName() != null) {
			// ps.setString(index++, vo.getName());
			// }
			// if (vo != null && vo.getAreaId() != null) {
			// ps.setString(index++, vo.getAreaId());
			// }
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, ExchSVO.class);
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
		return results;
	}

    /**
	 * 查询所有Exch
	 * 
	 * @param vo
	 *            ExchVO
	 * @throws SysException
	 * @throws AppException
	 */

    public List findExchsUnSelNotSo(WorkAreaExchMVO waevo) throws SysException, AppException {

        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        StringBuffer sql = new StringBuffer();
        sql
                .append("select ex.EXCH_ID,ex.NAME,ex.LOCAL_NET_ID,ex.AREA_ID,ex.SERV_DEPT_ID,ex.BRANCH_ID,ex.RULE_AREA_ID,ex.ABBREV_NAME,ex.CODE,ex.EXCH_TYPE,ex.ADDRESS,ex.COMM_DATE,ex.STANDARD_CODE,ex.STS,ex.STS_DATE,ex.CREATE_DATE from Exch ex where ex.exch_Id not in(select distinct wae.exch_Id from Work_Area_Exch wae ");

        ExchSVO vo = waevo.getExchSVO();
        sql.append(" where wae.sts='A' ");
        if (waevo.getWorkAreaId() != null) {
			sql
					.append(
							"and wae.work_Area_Id in( select wa2.work_Area_Id from Work_Area wa1,Work_Area wa2 where wa1.work_Type_Id=wa2.work_Type_Id and ")
					.append(
							"wa1.sts='A' and wa2.sts='A' and wa1.work_Area_Id=?) ");
		}
        sql.append(") and ex.sts='A' ");
        if (vo != null && vo.getAreaId() != null) {
            sql.append(" and ex.area_id =?");
        }
        log.debug(sql);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (waevo.getWorkAreaId() != null) {
                ps.setString(index++, waevo.getWorkAreaId());
            }
            if (vo != null && vo.getAreaId() != null) {
                ps.setString(index++, vo.getAreaId());
            }

            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, ExchSVO.class);
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
        return results;
    }

    /**
	 * 查询所有WorkAreaExch
	 * 
	 * @param vo
	 *            WorkAreaExchVO
	 * @throws SysException
	 */

    public List findWorkAreaExchs(WorkAreaExchMVO vo) throws AppException, SysException {
        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        StringBuffer sql = new StringBuffer();
        sql
                .append(
                        "select wt.NAME WORK_TYPE_NAME,a.WORK_AREA_EXCH_ID,a.WORK_AREA_ID,a.EXCH_ID,a.STS,a.STS_DATE,a.CREATE_DATE,b.WORK_AREA_ID,b.NAME wa_name,b.WORK_TYPE_ID,b.LOCAL_NET_ID wa_local_net_id,b.AREA_ID wa_area_id,b.SERV_DEPT_ID wa_serv_dept_id,b.BRANCH_ID wa_branch_id,b.PARENT_WORK_AREA_ID, ")
                .append(
                        "c.EXCH_ID,c.NAME ex_name,c.LOCAL_NET_ID ex_local_net_id,c.AREA_ID ex_area_id,c.SERV_DEPT_ID ex_serv_dept_id,c.BRANCH_ID ex_branch_id,c.RULE_AREA_ID,c.ABBREV_NAME,c.CODE,c.EXCH_TYPE,c.ADDRESS ")
                .append(
                        "from Work_Area_Exch a,work_area b,exch c,WORK_TYPE wt where 1=1 and a.work_area_id=b.work_area_id and b.WORK_TYPE_ID=wt.WORK_TYPE_ID and a.exch_id=c.exch_id ");
        if (vo.getWorkAreaExchId() != null) {
            sql.append("and a.work_area_exch_id=? ");
        }
        if (vo.getExchId() != null) {
            sql.append("and a.exch_id=? ");
        }
        if (vo.getWorkAreaId() != null) {
            sql.append("and a.work_area_id=? ");
        }
        if (vo.getWorkAreaSVO() != null && vo.getWorkAreaSVO().getWorkTypeId() != null) {
            sql.append("and b.work_type_id=? ");
        }

        sql.append("and a.sts='A' and b.sts='A' and c.sts='A' ");
        log.debug(sql);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (vo.getWorkAreaExchId() != null) {
                ps.setString(index++, vo.getWorkAreaExchId());
            }
            if (vo.getExchId() != null) {
                ps.setString(index++, vo.getExchId());
            }
            if (vo.getWorkAreaId() != null) {
                ps.setString(index++, vo.getWorkAreaId());
            }
            if (vo.getWorkAreaSVO() != null && vo.getWorkAreaSVO().getWorkTypeId() != null) {
                ps.setString(index++, vo.getWorkAreaSVO().getWorkTypeId());
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                WorkAreaExchMVO mvo = new WorkAreaExchMVO();
                WorkAreaSVO wasvo = new WorkAreaSVO();
                ExchSVO esvo = new ExchSVO();
                wasvo.setWorkAreaId(rs.getString("work_area_id"));
                wasvo.setWorkTypeId(rs.getString("work_type_id"));
                wasvo.setName(rs.getString("wa_name"));
                wasvo.setLocalNetId(rs.getString("wa_local_net_id"));
                wasvo.setAreaId(rs.getString("wa_area_id"));
                wasvo.setServDeptId(rs.getString("wa_serv_dept_id"));
                wasvo.setBranchId(rs.getString("wa_branch_id"));
                wasvo.setParentWorkAreaId(rs.getString("parent_work_area_id"));
                esvo.setExchId(rs.getString("exch_id"));
                esvo.setName(rs.getString("ex_name"));
                esvo.setLocalNetId(rs.getString("ex_local_net_id"));
                esvo.setAreaId(rs.getString("ex_area_id"));
                esvo.setServDeptId(rs.getString("ex_serv_dept_id"));
                esvo.setBranchId(rs.getString("ex_branch_id"));
                esvo.setRuleAreaId(rs.getString("rule_area_id"));
                esvo.setAbbrevName(rs.getString("abbrev_name"));
                esvo.setCode(rs.getString("code"));
                esvo.setExchType(rs.getString("exch_type"));
                esvo.setAddress(rs.getString("address"));
                mvo.setWorkAreaExchId(rs.getString("work_area_exch_id"));
                mvo.setWorkAreaId(rs.getString("work_area_id"));
                mvo.setExchId(rs.getString("exch_id"));
                mvo.setSts(rs.getString("sts"));
                mvo.setStsDate(rs.getDate("sts_date"));
                mvo.setCreateDate(rs.getDate("create_date"));
                mvo.setWorkAreaSVO(wasvo);
                mvo.setExchSVO(esvo);
                mvo.setWorkTypeId(rs.getString("work_type_id"));
                mvo.setWorkTypeName(rs.getString("WORK_TYPE_NAME"));
                results.add(mvo);

            }

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
        return results;
    }

    /**
	 * 根据workareaid查询所有的exch
	 * 
	 * @param vo
	 *            ExchVO
	 * @throws SysException
	 */

    public List findExchByWorkAreaSVO(WorkAreaExchSVO waevo) throws SysException, AppException {

        List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
        StringBuffer sql = new StringBuffer();
        sql
                .append("select ex.EXCH_ID,ex.NAME,ex.LOCAL_NET_ID,ex.AREA_ID,ex.SERV_DEPT_ID,ex.BRANCH_ID,ex.RULE_AREA_ID,ex.ABBREV_NAME,ex.CODE,ex.EXCH_TYPE,ex.ADDRESS,ex.COMM_DATE,ex.STANDARD_CODE,ex.STS,ex.STS_DATE,ex.CREATE_DATE from work_area_exch wae,exch ex where 1=1 ");
        if (waevo.getWorkAreaId() != null) {
            sql.append("and wae.work_area_id = ? ");
        }
        sql.append("and wae.exch_id=ex.exch_id and wae.sts='A' and ex.sts='A' ");

        log.debug(sql);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (waevo.getWorkAreaId() != null) {
                ps.setString(index++, waevo.getWorkAreaId());
            }

            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, ExchSVO.class);
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
        return results;
    }

    /**
	 * 根据workareaid查询所有的exch
	 * 
	 * @param vo
	 *            ExchVO
	 * @throws SysException
	 */

    public String findWorkAreaByExchAndStep(String exchId, String stepId) throws SysException,
            AppException {
        String workAreaId = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select wa.work_area_id  ");
        sql.append("from work_area_exch wae ,work_area wa,exch e,step s  ");
        sql.append("where wae.exch_id= ?  ");
        sql.append("and wae.sts='A'  ");
        sql.append("and wae.work_area_id=wa.work_area_id  ");
        sql.append("and wa.sts='A'  ");
        sql.append("and wa.work_type_id=s.work_type_id  ");
        sql.append("and s.step_id= ?    ");
        log.debug(sql);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (exchId != null) {
                ps.setString(index++, exchId);
            }
            if (stepId != null) {
                ps.setString(index++, stepId);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                workAreaId = rs.getString(1);
            }

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
        return workAreaId;
    }
    
    public void WorkAreaExchSync(String actType, String workAreaExchId, String workAreaId, String exchId, String sts, Date stsDate, Date createDate) throws SysException, AppException {
        StringBuffer sql=new StringBuffer();
	    sql.append("{call P_SYNC_WORK_AREA_EXCH(?,?,?,?,?,?,?,?,?)}");
	   // log.debug(sql);
	    Connection conn=null;
		CallableStatement pscall=null;
		String retMsg=null;
		String stsdate=null;
		String createdate=null;
		SimpleDateFormat SDM=new SimpleDateFormat("yyyy-MM-dd");
		if(stsDate!=null){
			 stsdate=SDM.format(stsDate);
		}
		if(createDate!=null){
			createdate=SDM.format(createDate);
		}
		try{
			conn=ConnectionFactory.getConnection();
			pscall=conn.prepareCall(sql.toString());
			pscall.setString(1,actType);
			pscall.setString(2,workAreaExchId);
			pscall.setString(3,workAreaId);
			pscall.setString(4,exchId);
			pscall.setString(5,sts);
	        pscall.setString(6,stsdate);
			pscall.setString(7,createdate);
		    pscall.registerOutParameter(8,java.sql.Types.VARCHAR);
			pscall.registerOutParameter(9, java.sql.Types.VARCHAR);
		    pscall.executeUpdate();
		    log.debug(actType+"=="+workAreaExchId+"=="+workAreaId+"=="+exchId+"=="+sts+"=="+stsdate+"=="+createdate);
		}catch (Exception e) {
		   throw new SysException("", "WorkAreaSync error..", e);
   	} finally {
   		try {
   			if (pscall != null) {
   				pscall.close();
   			}
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
   	}
  
		
	}

	public void addWorkAreaExch(GenericVO vo) throws AppException, SysException {
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" WORK_AREA_EXCH(WORK_AREA_EXCH_ID,WORK_AREA_ID,EXCH_ID,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, workAreaExch.getWorkAreaExchId());
			ps.setString(2, workAreaExch.getWorkAreaId());
			ps.setString(3, workAreaExch.getExchId());
			ps.setString(4, workAreaExch.getSts());
			ps.setDate(5, workAreaExch.getStsDate());
			ps.setDate(6, workAreaExch.getCreateDate());
			ps.execute();
			String openFlag = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG, null,
					null, null, null, null);//从配置表获取是否调用同步资源的存储过程的开关
			 if(!StringUtil.isBlank(openFlag)&&SysConstants.TRUE.equals(openFlag)){
				WorkAreaExchSync("A", workAreaExch.getWorkAreaExchId(),workAreaExch.getWorkAreaId(),workAreaExch.getExchId(), workAreaExch.getSts(),workAreaExch.getStsDate(),workAreaExch.getCreateDate());
			 }
			} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new SysException("", "add error..", e);
		} finally {
			  try {
				connection.commit();
			} catch (SQLException e1) {
	           e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}

	public void deleteWorkAreaExch(GenericVO vo) throws AppException, SysException {
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from WORK_AREA_EXCH where 1=1");
		sql.append(" and WORK_AREA_EXCH_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, workAreaExch.getWorkAreaExchId());
			ps.execute();
			String openFlag = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG, null,
					null, null, null, null);//从配置表获取是否调用同步资源的存储过程的开关
			 if(!StringUtil.isBlank(openFlag)&&SysConstants.TRUE.equals(openFlag)){
				 WorkAreaExchSync("R", workAreaExch.getWorkAreaExchId(),workAreaExch.getWorkAreaId(),workAreaExch.getExchId(), workAreaExch.getSts(),workAreaExch.getStsDate(),workAreaExch.getCreateDate());
			  }
			} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SysException("", "delete error..", e);
		} finally {
			try {
				connection.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}

	public void updateWorkAreaExch(GenericVO vo) throws AppException, SysException {
		WorkAreaExchSVO workAreaExch = (WorkAreaExchSVO) vo;
		StringBuffer sql = new StringBuffer("update WORK_AREA_EXCH set");
		if (workAreaExch.getWorkAreaId() != null) {
			sql.append(" WORK_AREA_ID=?,");
		}
		if (workAreaExch.getExchId() != null) {
			sql.append(" EXCH_ID=?,");
		}
		if (workAreaExch.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (workAreaExch.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (workAreaExch.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and WORK_AREA_EXCH_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (workAreaExch.getWorkAreaId() != null) {
				ps.setString(index++, workAreaExch.getWorkAreaId());
			}
			if (workAreaExch.getExchId() != null) {
				ps.setString(index++, workAreaExch.getExchId());
			}
			if (workAreaExch.getSts() != null) {
				ps.setString(index++, workAreaExch.getSts());
			}
			if (workAreaExch.getStsDate() != null) {
				ps.setDate(index++, workAreaExch.getStsDate());
			}
			if (workAreaExch.getCreateDate() != null) {
				ps.setDate(index++, workAreaExch.getCreateDate());
			}
			ps.setString(index++, workAreaExch.getWorkAreaExchId());
			ps.execute();			
			String openFlag = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG, null,
								null, null, null, null);//从配置表获取是否调用同步资源的存储过程的开关
			 if(!StringUtil.isBlank(openFlag)&&SysConstants.TRUE.equals(openFlag)){
				WorkAreaExchSync("M", workAreaExch.getWorkAreaExchId(),workAreaExch.getWorkAreaId(),workAreaExch.getExchId(), workAreaExch.getSts(),workAreaExch.getStsDate(),workAreaExch.getCreateDate());
			 }
			} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SysException("", "update error..", e);
		} finally {
			try {
				connection.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
		
	}
	
	public List findWorkId(GenericVO vo, String exchId) throws AppException,
	SysException {
		List list = null;
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		WorkAreaSVO wosVO = (WorkAreaSVO) vo;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Sql sql = new Sql(
		"SELECT WORK_AREA_ID FROM WORK_AREA_EXCH WHERE 1 = 1 and EXCH_ID = :exchId  "
				+ " and STS = 'A' AND WORK_AREA_ID IN(   SELECT WORK_AREA_ID FROM work_area WHERE  "
				+ "WORK_TYPE_ID =:workTypeId   AND LOCAL_NET_ID =:localNetId AND  STS='A')"
				+ " and sts='A' ");
		sql.setString("exchId", exchId);
		sql.setString("workTypeId", wosVO.getWorkTypeId());
		sql.setString("localNetId", wosVO.getLocalNetId());

		String ret = null;

		try {
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		ps = sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();
		list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		while (rs.next()) {
	
			ret = rs.getString("WORK_AREA_ID");
			list.add(ret);
		}
	} catch (SQLException se) {
		throw new SysException("100003", "JDBC操作异常！", se);
	} finally {
		JdbcUtil.close(rs, ps);
	}
	
	return list;
	}

	public List findWorkIdByServDept(GenericVO vo, String exchId)
		throws AppException, SysException {
	List list = null;
	if (vo == null) {
		throw new AppException("100001", "缺少DAO操作对象！");
	}
	WorkAreaSVO wosVO = (WorkAreaSVO) vo;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	Sql sql = new Sql(
			"SELECT WORK_AREA_ID FROM WORK_AREA_EXCH WHERE 1 = 1 and EXCH_ID = :exchId  "
					+ " and STS = 'A' AND WORK_AREA_ID IN(   SELECT WORK_AREA_ID FROM work_area WHERE  "
					+ "WORK_TYPE_ID =:workTypeId   AND LOCAL_NET_ID =:localNetId AND AREA_ID =:areaId AND STS='A'"
					+ " and serv_dept_id =:servDeptId) and sts='A' ");
	sql.setString("exchId", exchId);
	sql.setString("workTypeId", wosVO.getWorkTypeId());
	sql.setString("localNetId", wosVO.getLocalNetId());
	sql.setString("areaId", wosVO.getAreaId());
	sql.setString("servDeptId", wosVO.getServDeptId());
	String ret = null;
	
	try {
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		ps = sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();
		list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		while (rs.next()) {
	
			ret = rs.getString("WORK_AREA_ID");
			list.add(ret);
		}
	} catch (SQLException se) {
		throw new SysException("100003", "JDBC操作异常！", se);
	} finally {
		JdbcUtil.close(rs, ps);
	}
	
	return list;
	}
	
	/**
	 * 查询所有工区
	 * @author yangkai 2009-9-1
	 * @param vo
	 *            ExchVO
	 * @throws SysException
	 */
	
	public List findWorkAreaUnSel(WorkAreaExchMVO waevo) throws SysException,
			AppException {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Sql sql = new Sql();
		sql
				.append("SELECT WA.WORK_AREA_ID,WA.NAME WORK_AREA_NAME,WA.WORK_TYPE_ID,WA.Local_Net_Id,WA.AREA_ID,WT.NAME WORK_TYPE_NAME");
		sql
				.append(" FROM EXCH EX, WORK_AREA WA,WORK_TYPE WT WHERE EX.STS = 'A'   AND WA.STS = 'A'   AND EX.AREA_ID = WA.AREA_ID ");
		sql
				.append(" AND WA.WORK_TYPE_ID=WT.WORK_TYPE_ID   AND NOT EXISTS (SELECT *   FROM WORK_AREA_EXCH WAE");
		sql
				.append("     WHERE WAE.STS = 'A'  AND WAE.EXCH_ID = EX.EXCH_ID   AND WAE.WORK_AREA_ID = WA.WORK_AREA_ID)  ");
		try {	
		if (waevo.getExchId() == null)
			return res;	
	    sql.append(" and EX.EXCH_ID=:exchId");
	    sql.setLong("exchId", waevo.getExchId());    
	    if (waevo.getWorkTypeId()!= null) {
	    	sql.append(" and WA.WORK_TYPE_ID=:workTypeId");
	    	sql.setLong("workTypeId", waevo.getWorkTypeId());      	
	    	}	
	    sql.append(" ORDER BY WA.WORK_AREA_ID");
		log.debug(sql);
		conn = ConnectionFactory.getConnection();
		ps = conn.prepareStatement(sql.getSql());
		ps = sql.fillParams(ps);
		sql.log(this.getClass());
		rs = ps.executeQuery();
		while (rs.next()) {
			WorkAreaMVO mvo=new WorkAreaMVO();
			mvo.setAreaId(rs.getString("AREA_ID"));
			mvo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
			mvo.setName(rs.getString("WORK_AREA_NAME"));
			mvo.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
			mvo.setWorkTypeName(rs.getString("WORK_TYPE_NAME"));
			mvo.setWorkAreaId(rs.getString("WORK_AREA_ID"));
			res.add(mvo);
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



