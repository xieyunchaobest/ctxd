package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IBranchSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.BranchMVO;
import com.cattsoft.sm.vo.BranchSVO;


public class BranchSDAOImpl implements IBranchSDAO {

    private Logger log = Logger.getLogger(BranchSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
        BranchSVO branch = (BranchSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" BRANCH(BRANCH_ID,LOCAL_NET_ID,AREA_ID,SERV_DEPT_ID,ABBREV_NAME,NAME,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, branch.getBranchId());
            ps.setString(2, branch.getLocalNetId());
            ps.setString(3, branch.getAreaId());
            ps.setString(4, branch.getServDeptId());
            ps.setString(5, branch.getAbbrevName());
            ps.setString(6, branch.getName());
            ps.setString(7, branch.getSts());
            ps.setDate(8, branch.getStsDate());
            ps.setDate(9, branch.getCreateDate());
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
        BranchSVO branch = (BranchSVO) vo;
        StringBuffer sql = new StringBuffer("update BRANCH set");
        if (branch.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (branch.getAreaId() != null) {
            sql.append(" AREA_ID=?,");
        }
        if (branch.getServDeptId() != null) {
            sql.append(" SERV_DEPT_ID=?,");
        }
        if (branch.getAbbrevName() != null) {
            sql.append(" ABBREV_NAME=?,");
        }
        if (branch.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (branch.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (branch.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (branch.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and BRANCH_ID=?");
        log.debug(sql.toString());
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (branch.getLocalNetId() != null) {
                ps.setString(index++, branch.getLocalNetId());
            }
            if (branch.getAreaId() != null) {
                ps.setString(index++, branch.getAreaId());
            }
            if (branch.getServDeptId() != null) {
                ps.setString(index++, branch.getServDeptId());
            }
            if (branch.getAbbrevName() != null) {
                ps.setString(index++, branch.getAbbrevName());
            }
            if (branch.getName() != null) {
                ps.setString(index++, branch.getName());
            }
            if (branch.getSts() != null) {
                ps.setString(index++, branch.getSts());
            }
            if (branch.getStsDate() != null) {
                ps.setDate(index++, branch.getStsDate());
            }
            if (branch.getCreateDate() != null) {
                ps.setDate(index++, branch.getCreateDate());
            }
            ps.setString(index++, branch.getBranchId());
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
        BranchSVO branch = (BranchSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from BRANCH where 1=1");
        sql.append(" and BRANCH_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, branch.getBranchId());
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
        BranchSVO result = null;
        BranchSVO branch = (BranchSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.BRANCH_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from BRANCH a where 1=1");
        sql.append(" and BRANCH_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, branch.getBranchId());
            rs = ps.executeQuery();
            result = (BranchSVO) ResultSetUtil.convertToVo(rs, BranchSVO.class);
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
        BranchSVO branch = (BranchSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.BRANCH_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from BRANCH a where 1=1");
        if (branch.getBranchId() != null) {
            sql.append(" and BRANCH_ID=?");
        }
        if (branch.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (branch.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (branch.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (branch.getAbbrevName() != null) {
            sql.append(" and ABBREV_NAME=?");
        }
        if (branch.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (branch.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (branch.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (branch.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (branch.getBranchId() != null) {
                ps.setString(index++, branch.getBranchId());
            }
            if (branch.getLocalNetId() != null) {
                ps.setString(index++, branch.getLocalNetId());
            }
            if (branch.getAreaId() != null) {
                ps.setString(index++, branch.getAreaId());
            }
            if (branch.getServDeptId() != null) {
                ps.setString(index++, branch.getServDeptId());
            }
            if (branch.getAbbrevName() != null) {
                ps.setString(index++, branch.getAbbrevName());
            }
            if (branch.getName() != null) {
                ps.setString(index++, branch.getName());
            }
            if (branch.getSts() != null) {
                ps.setString(index++, branch.getSts());
            }
            if (branch.getStsDate() != null) {
                ps.setDate(index++, branch.getStsDate());
            }
            if (branch.getCreateDate() != null) {
                ps.setDate(index++, branch.getCreateDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, BranchSVO.class);
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

    public BranchSVO findByName(GenericVO vo) throws AppException, SysException {
        BranchSVO result = null;
        BranchSVO branch = (BranchSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.BRANCH_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from BRANCH a where 1=1");
        sql.append(" and a.name=?");
        sql.append(" and a.serv_dept_id=? ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, branch.getName());
            ps.setString(2, branch.getServDeptId());
            rs = ps.executeQuery();
            result = (BranchSVO) ResultSetUtil.convertToVo(rs, BranchSVO.class);
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

    public PagView findBranchsByPage(BranchSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
        List results = null;
        BranchSVO branch = (BranchSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(
                        " a.BRANCH_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE, ")
                .append(" b.name local_net_name,c.name area_name,d.name serv_dept_name ");
        sql
                .append(" from local_net b,area c,serv_dept d,BRANCH a where 1=1 and a.local_net_id=b.local_net_id and a.area_id=c.area_id and a.serv_dept_id=d.serv_dept_id ");
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "branch_id", "branchId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
            if (branch.getBranchId() != null) {
                sql.append(" and a.BRANCH_ID=" + branch.getBranchId());
            }
            if (branch.getLocalNetId() != null) {
                sql.append(" and a.LOCAL_NET_ID=" + branch.getLocalNetId());
            }
            if (branch.getAreaId() != null) {
                sql.append(" and a.AREA_ID=" + branch.getAreaId());
            }
            if (branch.getServDeptId() != null) {
                sql.append(" and a.SERV_DEPT_ID=" + branch.getServDeptId());
            }
            if (branch.getAbbrevName() != null) {
                sql.append(" and a.ABBREV_NAME='%" + branch.getAbbrevName() + "%'");
            }
            if (branch.getName() != null) {
                sql.append(" and a.NAME='%" + branch.getName() + "%'");
            }
            if (branch.getSts() != null) {
                sql.append(" and a.STS='" + branch.getSts() + "'");
            }
        }
        sql.append(" order by branch_id ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(BranchSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, BranchMVO.class);
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

}
