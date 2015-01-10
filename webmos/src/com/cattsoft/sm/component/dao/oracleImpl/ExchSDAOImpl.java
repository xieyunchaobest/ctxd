package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IExchSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.ExchMVO;
import com.cattsoft.sm.vo.ExchSVO;

public class ExchSDAOImpl implements IExchSDAO {

    private static Logger log=Logger.getLogger(ExchSDAOImpl.class);
    
    public void add(GenericVO vo) throws AppException, SysException {
        ExchSVO exch = (ExchSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" EXCH(EXCH_ID,NAME,LOCAL_NET_ID,AREA_ID,SERV_DEPT_ID,BRANCH_ID,RULE_AREA_ID,ABBREV_NAME,CODE,EXCH_TYPE,SUB_TYPE,ADDRESS,COMM_DATE,STANDARD_CODE,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, exch.getExchId());
            ps.setString(2, exch.getName());
            ps.setString(3, exch.getLocalNetId());
            ps.setString(4, exch.getAreaId());
            ps.setString(5, exch.getServDeptId());
            ps.setString(6, exch.getBranchId());
            ps.setString(7, exch.getRuleAreaId());
            ps.setString(8, exch.getAbbrevName());
            ps.setString(9, exch.getCode());
            ps.setString(10, exch.getExchType());
            ps.setString(11, exch.getSubType());//added by yangkai 2009-9-3 增加了此字段
            ps.setString(12, exch.getAddress());
            ps.setDate(13, exch.getCommDate());
            ps.setString(14, exch.getStandardCode());
            ps.setString(15, exch.getSts());
            ps.setDate(16, exch.getStsDate());
            ps.setDate(17, exch.getCreateDate());
            ps.execute();
        } catch (SQLException e) {
            throw new SysException("", "add error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void update(GenericVO vo) throws AppException, SysException {
        ExchSVO exch = (ExchSVO) vo;
        StringBuffer sql = new StringBuffer("update EXCH set");
        if (exch.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (exch.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (exch.getAreaId() != null) {
            sql.append(" AREA_ID=?,");
        }
        if (exch.getServDeptId() != null) {
            sql.append(" SERV_DEPT_ID=?,");
        }
        if (exch.getBranchId() != null) {
            sql.append(" BRANCH_ID=?,");
        }
        if (exch.getRuleAreaId() != null) {
            sql.append(" RULE_AREA_ID=?,");
        }
        if (exch.getAbbrevName() != null) {
            sql.append(" ABBREV_NAME=?,");
        }
        if (exch.getCode() != null) {
            sql.append(" CODE=?,");
        }
        if (exch.getExchType() != null) {
            sql.append(" EXCH_TYPE=?,");
        }
        if (exch.getSubType() != null) {//added by yangkai 2009-9-3 增加了此字段
            sql.append(" SUB_TYPE=?,");
        }
        if (exch.getAddress() != null) {
            sql.append(" ADDRESS=?,");
        }
        if (exch.getCommDate() != null) {
            sql.append(" COMM_DATE=?,");
        }
        if (exch.getStandardCode() != null) {
            sql.append(" STANDARD_CODE=?,");
        }
        if (exch.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (exch.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (exch.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and EXCH_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (exch.getName() != null) {
                ps.setString(index++, exch.getName());
            }
            if (exch.getLocalNetId() != null) {
                ps.setString(index++, exch.getLocalNetId());
            }
            if (exch.getAreaId() != null) {
                ps.setString(index++, exch.getAreaId());
            }
            if (exch.getServDeptId() != null) {
                ps.setString(index++, exch.getServDeptId());
            }
            if (exch.getBranchId() != null) {
                ps.setString(index++, exch.getBranchId());
            }
            if (exch.getRuleAreaId() != null) {
                ps.setString(index++, exch.getRuleAreaId());
            }
            if (exch.getAbbrevName() != null) {
                ps.setString(index++, exch.getAbbrevName());
            }
            if (exch.getCode() != null) {
                ps.setString(index++, exch.getCode());
            }
            if (exch.getExchType() != null) {
                ps.setString(index++, exch.getExchType());
            }
            if (exch.getSubType() != null) {//added by yangkai 2009-9-3 增加了此字段
                ps.setString(index++, exch.getSubType());
            }
            if (exch.getAddress() != null) {
                ps.setString(index++, exch.getAddress());
            }
            if (exch.getCommDate() != null) {
                ps.setDate(index++, exch.getCommDate());
            }
            if (exch.getStandardCode() != null) {
                ps.setString(index++, exch.getStandardCode());
            }
            if (exch.getSts() != null) {
                ps.setString(index++, exch.getSts());
            }
            if (exch.getStsDate() != null) {
                ps.setDate(index++, exch.getStsDate());
            }
            if (exch.getCreateDate() != null) {
                ps.setDate(index++, exch.getCreateDate());
            }
            ps.setString(index++, exch.getExchId());
            ps.execute();
        } catch (SQLException e) {
            throw new SysException("", "update error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void delete(GenericVO vo) throws AppException, SysException {
        ExchSVO exch = (ExchSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from EXCH where 1=1");
        sql.append(" and EXCH_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, exch.getExchId());
            ps.execute();
        } catch (SQLException e) {
            throw new SysException("", "delete error..", e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
        ExchSVO result = null;
        ExchSVO exch = (ExchSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.EXCH_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.RULE_AREA_ID,a.ABBREV_NAME,a.CODE,a.EXCH_TYPE,a.SUB_TYPE,a.ADDRESS,a.COMM_DATE,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from EXCH a where 1=1");
        sql.append(" and EXCH_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, exch.getExchId());
            rs = ps.executeQuery();
            result = (ExchSVO) ResultSetUtil.convertToVo(rs, ExchSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findByPK error..", e);
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
        return result;
    }

    public List findByVO(GenericVO vo) throws AppException, SysException {
        List results = null;
        ExchSVO exch = (ExchSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.EXCH_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.RULE_AREA_ID,a.ABBREV_NAME,a.CODE,a.EXCH_TYPE,a.SUB_TYPE,a.ADDRESS,a.COMM_DATE,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from EXCH a where 1=1");
        if (exch.getExchId() != null) {
            sql.append(" and EXCH_ID=?");
        }
        if (exch.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (exch.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (exch.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (exch.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (exch.getBranchId() != null) {
            sql.append(" and BRANCH_ID=?");
        }
        if (exch.getRuleAreaId() != null) {
            sql.append(" and RULE_AREA_ID=?");
        }
        if (exch.getAbbrevName() != null) {
            sql.append(" and ABBREV_NAME=?");
        }
        if (exch.getCode() != null) {
            sql.append(" and CODE=?");
        }
        if (exch.getExchType() != null) {
            sql.append(" and EXCH_TYPE=?");
        }
        if (exch.getSubType() != null) {//added by yangkai 2009-9-3 增加了此字段
            sql.append(" and SUB_TYPE=?");
        }
        if (exch.getAddress() != null) {
            sql.append(" and ADDRESS=?");
        }
        if (exch.getCommDate() != null) {
            sql.append(" and COMM_DATE=?");
        }
        if (exch.getStandardCode() != null) {
            sql.append(" and STANDARD_CODE=?");
        }
        if (exch.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (exch.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (exch.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (exch.getExchId() != null) {
                ps.setString(index++, exch.getExchId());
            }
            if (exch.getName() != null) {
                ps.setString(index++, exch.getName());
            }
            if (exch.getLocalNetId() != null) {
                ps.setString(index++, exch.getLocalNetId());
            }
            if (exch.getAreaId() != null) {
                ps.setString(index++, exch.getAreaId());
            }
            if (exch.getServDeptId() != null) {
                ps.setString(index++, exch.getServDeptId());
            }
            if (exch.getBranchId() != null) {
                ps.setString(index++, exch.getBranchId());
            }
            if (exch.getRuleAreaId() != null) {
                ps.setString(index++, exch.getRuleAreaId());
            }
            if (exch.getAbbrevName() != null) {
                ps.setString(index++, exch.getAbbrevName());
            }
            if (exch.getCode() != null) {
                ps.setString(index++, exch.getCode());
            }
            if (exch.getExchType() != null) {
                ps.setString(index++, exch.getExchType());
            }
            if (exch.getSubType() != null) {
                ps.setString(index++, exch.getSubType());//added by yangkai 2009-9-3 增加了此字段
            }
            if (exch.getAddress() != null) {
                ps.setString(index++, exch.getAddress());
            }
            if (exch.getCommDate() != null) {
                ps.setDate(index++, exch.getCommDate());
            }
            if (exch.getStandardCode() != null) {
                ps.setString(index++, exch.getStandardCode());
            }
            if (exch.getSts() != null) {
                ps.setString(index++, exch.getSts());
            }
            if (exch.getStsDate() != null) {
                ps.setDate(index++, exch.getStsDate());
            }
            if (exch.getCreateDate() != null) {
                ps.setDate(index++, exch.getCreateDate());
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

    public PagView findByPage(GenericVO vo, PagInfo pagInfo) throws AppException, SysException {
        List results = null;
        ExchSVO exch = (ExchSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.EXCH_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.RULE_AREA_ID,a.ABBREV_NAME,a.CODE,a.EXCH_TYPE,a.SUB_TYPE,a.ADDRESS,a.COMM_DATE,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from EXCH a where 1=1");
        if (exch.getExchId() != null) {
            sql.append(" and EXCH_ID=" + exch.getExchId());
        }
        if (exch.getName() != null) {
            sql.append(" and NAME=" + exch.getName());
        }
        if (exch.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=" + exch.getLocalNetId());
        }
        if (exch.getAreaId() != null) {
            sql.append(" and AREA_ID=" + exch.getAreaId());
        }
        if (exch.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=" + exch.getServDeptId());
        }
        if (exch.getBranchId() != null) {
            sql.append(" and BRANCH_ID=" + exch.getBranchId());
        }
        if (exch.getRuleAreaId() != null) {
            sql.append(" and RULE_AREA_ID=" + exch.getRuleAreaId());
        }
        if (exch.getAbbrevName() != null) {
            sql.append(" and ABBREV_NAME=" + exch.getAbbrevName());
        }
        if (exch.getCode() != null) {
            sql.append(" and CODE=" + exch.getCode());
        }
        if (exch.getExchType() != null) {
            sql.append(" and EXCH_TYPE=" + exch.getExchType());
        }
        if (exch.getSubType() != null) {//added by yangkai 2009-9-3 增加了此字段
            sql.append(" and SUB_TYPE=" + exch.getSubType());
        }
        if (exch.getAddress() != null) {
            sql.append(" and ADDRESS=" + exch.getAddress());
        }
        if (exch.getCommDate() != null) {
            sql.append(" and COMM_DATE=" + exch.getCommDate());
        }
        if (exch.getStandardCode() != null) {
            sql.append(" and STANDARD_CODE=" + exch.getStandardCode());
        }
        if (exch.getSts() != null) {
            sql.append(" and STS=" + "'" + exch.getSts() + "'");
        }
        if (exch.getStsDate() != null) {
            sql.append(" and STS_DATE=" + exch.getStsDate());
        }
        if (exch.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=" + exch.getCreateDate());
        }
        Connection connection = null;
        PreparedStatement ps = null;
        PagView pagView = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(ExchSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, ExchSVO.class);
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

    public PagView findExchMVOByPage(ExchSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        ExchSVO exch = (ExchSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(
                        " a.EXCH_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.RULE_AREA_ID,a.ABBREV_NAME,a.CODE,a.EXCH_TYPE,a.SUB_TYPE,a.ADDRESS,a.COMM_DATE,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE, ")
                .append(
                        "b.name local_net_name,c.name area_name,d.name serv_dept_name,e.name branch_name,f.name rule_area_name ");
        sql.append("from exch a,local_net b,area c,serv_dept d,branch e,rule_area f  ");
        sql.append(" where  a.local_net_id=b.local_net_id  ");
        sql.append(" and a.area_id=c.area_id and a.serv_dept_id=d.serv_dept_id ");
        sql.append(" and a.branch_id=e.branch_id(+) and a.rule_area_id=f.rule_area_id(+)  ");
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "exch_id", "exchId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
            if (exch.getExchId() != null) {
                sql.append(" and a.EXCH_ID=" + exch.getExchId());
            }
            if (exch.getName() != null) {
                sql.append(" and a.NAME like '%" + exch.getName()+"%'");
            }
            if (exch.getLocalNetId() != null) {
                sql.append(" and a.LOCAL_NET_ID=" + exch.getLocalNetId());
            }
            if (exch.getAreaId() != null) {
                sql.append(" and a.AREA_ID=" + exch.getAreaId());
            }
            if (exch.getServDeptId() != null) {
                sql.append(" and a.SERV_DEPT_ID=" + exch.getServDeptId());
            }
            if (exch.getBranchId() != null) {
                sql.append(" and a.BRANCH_ID=" + exch.getBranchId());
            }
            if (exch.getRuleAreaId() != null) {
                sql.append(" and a.RULE_AREA_ID=" + exch.getRuleAreaId());
            }
            if (exch.getAbbrevName() != null) {
                sql.append(" and a.ABBREV_NAME=" + exch.getAbbrevName());
            }
            if (exch.getCode() != null) {
                sql.append(" and a.CODE=" + exch.getCode());
            }
            if (exch.getExchType() != null) {
                sql.append(" and a.EXCH_TYPE='" + exch.getExchType()+"'");
            }
            if (exch.getSubType() != null) {//added by yangkai 2009-9-3 增加了此字段
                sql.append(" and a.SUB_TYPE='" + exch.getSubType()+"'");
            }
            if (exch.getAddress() != null) {
                sql.append(" and a.ADDRESS=" + exch.getAddress());
            }
            if (exch.getCommDate() != null) {
                sql.append(" and a.COMM_DATE=" + exch.getCommDate());
            }
            if (exch.getStandardCode() != null) {
                sql.append(" and a.STANDARD_CODE=" + exch.getStandardCode());
            }
            if (exch.getSts() != null) {
                sql.append(" and a.STS=" + "'" + exch.getSts() + "'");
            }
            if (exch.getStsDate() != null) {
                sql.append(" and a.STS_DATE=" + exch.getStsDate());
            }
            if (exch.getCreateDate() != null) {
                sql.append(" and a.CREATE_DATE=" + exch.getCreateDate());
            }
        }
      
        Connection connection = null;
        PreparedStatement ps = null;
        PagView pagView = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            //System.out.println(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(ExchSDAOImpl.class);
            log.debug(sql.toString());
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);        
            //results = (List) ResultSetUtil.convertToList(rs, ExchMVO.class);
            results = convertToExchMVOList(rs);
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
    public List findExchListByWorkArea(String workAreaId) throws AppException, SysException {
//		if (StringUtil.isBlank(workAreaId)) {
//			throw new AppException("100001", "缺少DAO操作对象！");
//		}
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT EX.ADDRESS,EX.AREA_ID,EX.CODE,EX.COMM_DATE,EX.CREATE_DATE,EX.EXCH_ID,EX.EXCH_TYPE,EX.SUB_TYPE,EX.LOCAL_NET_ID,EX.NAME,EX.RULE_AREA_ID,EX.SERV_DEPT_ID,EX.STANDARD_CODE,EX.STS,EX.STS_DATE  FROM WORK_AREA_EXCH WE, WORK_AREA WA, EXCH EX ");
		sql
				.append(" WHERE EX.EXCH_ID = WE.EXCH_ID AND WE.WORK_AREA_ID = WA.WORK_AREA_ID AND WA.WORK_AREA_ID = :workAreaId AND EX.STS='A' AND WA.STS='A' AND WE.STS='A'");
		sql.setString("workAreaId", workAreaId);
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			ExchSVO exch = null;
			while (rs.next()) {
				exch = new ExchSVO();
				exch.setAddress(rs.getString("ADDRESS"));
				exch.setAreaId(rs.getString("AREA_ID"));
				exch.setCode(rs.getString("CODE"));
				//修改2009-12-7 caiqian
				//判断COMM_DATE为空的情况
				Timestamp commDate = rs.getTimestamp("COMM_DATE");
				if(commDate!=null){
					exch.setCommDate(new Date(commDate.getTime()));
				}
				exch.setCreateDate(new Date(rs.getTimestamp("CREATE_DATE").getTime()));
				exch.setExchId(rs.getString("EXCH_ID"));
				exch.setExchType(rs.getString("EXCH_TYPE"));
				exch.setSubType(rs.getString("SUB_TYPE"));//added by yangkai 2009-9-3 增加了此字段
				exch.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				exch.setName(rs.getString("NAME"));
				exch.setRuleAreaId(rs.getString("RULE_AREA_ID"));
				exch.setServDeptId(rs.getString("SERV_DEPT_ID"));
				exch.setStandardCode(rs.getString("STANDARD_CODE"));
				exch.setSts(rs.getString("STS"));
				exch.setStsDate(new Date(rs.getTimestamp("STS_DATE").getTime()));
				res.add(exch);
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

    private List convertToExchMVOList(ResultSet rs)throws SQLException, AppException, SysException{
    	List mvos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
    	while(rs.next()){
    		ExchMVO mvo = new ExchMVO();
    		mvo.setAbbrevName(rs.getString("ABBREV_NAME"));
    		mvo.setAddress(rs.getString("ADDRESS"));
    		mvo.setAreaId(rs.getString("AREA_ID"));
    		mvo.setAreaName(rs.getString("AREA_NAME"));
    		mvo.setBranchId(rs.getString("BRANCH_ID"));
    		mvo.setBranchName(rs.getString("BRANCH_NAME"));
    		mvo.setCode(rs.getString("CODE"));
    		mvo.setCommDate(rs.getDate("COMM_DATE"));
    		mvo.setCreateDate(rs.getDate("CREATE_DATE"));
    		mvo.setExchId(rs.getString("EXCH_ID"));
    		mvo.setExchType(rs.getString("EXCH_TYPE"));
    		mvo.setSubType(rs.getString("SUB_TYPE"));
    		//mvo.setExchTypeDesc(rs.getString("EXCH_TYPE_DESC"));
    		mvo.setLocalNetId(rs.getString("LOCAL_NET_ID"));
    		mvo.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
    		mvo.setName(rs.getString("NAME"));
    		mvo.setRuleAreaId(rs.getString("RULE_AREA_ID"));
    		mvo.setRuleAreaName(rs.getString("RULE_AREA_NAME"));
    		mvo.setServDeptId(rs.getString("SERV_DEPT_ID"));
    		mvo.setServDeptName(rs.getString("SERV_DEPT_NAME"));
    		mvo.setStandardCode(rs.getString("STANDARD_CODE"));
    		mvo.setSts(rs.getString("STS"));
    		mvo.setStsDate(rs.getDate("STS_DATE"));
    		//mvo.setStsDesc(rs.getString("STS_DESC"));
    		mvos.add(mvo);
    	}
    	return mvos;
    }
}
