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
import com.cattsoft.sm.component.dao.IServDeptSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.ServDeptMVO;
import com.cattsoft.sm.vo.ServDeptSVO;

public class ServDeptSDAOImpl implements IServDeptSDAO {

    private Logger log = Logger.getLogger(ServDeptSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
    	
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" SERV_DEPT(SERV_DEPT_ID,LOCAL_NET_ID,AREA_ID,ABBREV_NAME,NAME,STS,STS_DATE,CREATE_DATE,AREA_TYPE) values(?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, servDept.getServDeptId());
            ps.setString(2, servDept.getLocalNetId());
            ps.setString(3, servDept.getAreaId());
            ps.setString(4, servDept.getAbbrevName());
            ps.setString(5, servDept.getName());
            ps.setString(6, servDept.getSts());
            ps.setDate(7, servDept.getStsDate());
            ps.setDate(8, servDept.getCreateDate());
            ps.setString(9, servDept.getAreaType());
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
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer("update SERV_DEPT set");
        if (servDept.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (servDept.getAreaId() != null) {
            sql.append(" AREA_ID=?,");
        }
        if (servDept.getAbbrevName() != null) {
            sql.append(" ABBREV_NAME=?,");
        }
        if (servDept.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (servDept.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (servDept.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (servDept.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (servDept.getAreaType() != null) {
            sql.append(" AREA_TYPE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and SERV_DEPT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (servDept.getLocalNetId() != null) {
                ps.setString(index++, servDept.getLocalNetId());
            }
            if (servDept.getAreaId() != null) {
                ps.setString(index++, servDept.getAreaId());
            }
            if (servDept.getAbbrevName() != null) {
                ps.setString(index++, servDept.getAbbrevName());
            }
            if (servDept.getName() != null) {
                ps.setString(index++, servDept.getName());
            }
            if (servDept.getSts() != null) {
                ps.setString(index++, servDept.getSts());
            }
            if (servDept.getStsDate() != null) {
                ps.setDate(index++, servDept.getStsDate());
            }
            if (servDept.getCreateDate() != null) {
                ps.setDate(index++, servDept.getCreateDate());
            }
            if (servDept.getAreaType() != null) {
                ps.setString(index++, servDept.getAreaType());
            }
            ps.setString(index++, servDept.getServDeptId());
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
        ServDeptSVO servDept = (ServDeptSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from SERV_DEPT where 1=1");
        sql.append(" and SERV_DEPT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, servDept.getServDeptId());
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
        ServDeptSVO result = null;
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.SERV_DEPT_ID,a.LOCAL_NET_ID,a.AREA_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE,a.AREA_TYPE");
        sql.append(" from SERV_DEPT a where 1=1");
        sql.append(" and SERV_DEPT_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, servDept.getServDeptId());
            rs = ps.executeQuery();
            result = (ServDeptSVO) ResultSetUtil.convertToVo(rs, ServDeptSVO.class);
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
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.SERV_DEPT_ID,a.LOCAL_NET_ID,a.AREA_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE,a.AREA_TYPE");
        sql.append(" from SERV_DEPT a where 1=1");
        if (servDept.getServDeptId() != null) {
            sql.append(" and SERV_DEPT_ID=?");
        }
        if (servDept.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (servDept.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (servDept.getAbbrevName() != null) {
            sql.append(" and ABBREV_NAME=?");
        }
        if (servDept.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (servDept.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (servDept.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (servDept.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (servDept.getAreaType() != null) {
            sql.append(" and AREA_TYPE=?");
        }
        sql.append(" AND STS='A'");
        sql.append(" ORDER BY A.SERV_DEPT_ID");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (servDept.getServDeptId() != null) {
                ps.setString(index++, servDept.getServDeptId());
            }
            if (servDept.getLocalNetId() != null) {
                ps.setString(index++, servDept.getLocalNetId());
            }
            if (servDept.getAreaId() != null) {
                ps.setString(index++, servDept.getAreaId());
            }
            if (servDept.getAbbrevName() != null) {
                ps.setString(index++, servDept.getAbbrevName());
            }
            if (servDept.getName() != null) {
                ps.setString(index++, servDept.getName());
            }
            if (servDept.getSts() != null) {
                ps.setString(index++, servDept.getSts());
            }
            if (servDept.getStsDate() != null) {
                ps.setDate(index++, servDept.getStsDate());
            }
            if (servDept.getCreateDate() != null) {
                ps.setDate(index++, servDept.getCreateDate());
            }
            if (servDept.getAreaType() != null) {
                ps.setString(index++, servDept.getAreaType());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, ServDeptSVO.class);
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

    public ServDeptSVO findByName(GenericVO vo) throws AppException, SysException {
        ServDeptSVO result = null;
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.SERV_DEPT_ID,a.LOCAL_NET_ID,a.AREA_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE,a.AREA_TYPE");
        sql.append(" from SERV_DEPT a where 1=1");
        sql.append(" and a.NAME=? ");
        sql.append(" and a.area_id=? ");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, servDept.getName());
            ps.setString(2, servDept.getAreaId());
            rs = ps.executeQuery();
            result = (ServDeptSVO) ResultSetUtil.convertToVo(rs, ServDeptSVO.class);
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

    public PagView findServDeptsByPage(ServDeptSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception {
        List results = null;
        ServDeptSVO servDept = (ServDeptSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(
                        " a.SERV_DEPT_ID,a.LOCAL_NET_ID,a.AREA_ID,a.ABBREV_NAME,a.NAME,a.STS,a.STS_DATE,a.CREATE_DATE, ")
                .append(
                        "b.name local_net_name,b.iscenter local_Net_Iscenter,c.name area_name ,c.iscenter area_iscenter ");
        sql
                .append(" from local_net b,area c,SERV_DEPT a where 1=1 and a.local_net_id=b.local_net_id and a.area_id=c.area_id ");

        if (vo == null && set != null) {
            sql.append(" and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "serv_dept_id", "servDeptId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
            if (servDept.getServDeptId() != null) {
                sql.append(" and a.SERV_DEPT_ID=" + servDept.getServDeptId());
            }
            if (servDept.getLocalNetId() != null) {
                sql.append(" and a.LOCAL_NET_ID=" + servDept.getLocalNetId());
            }
            if (servDept.getAreaId() != null) {
                sql.append(" and a.AREA_ID=" + servDept.getAreaId());
            }
            if (servDept.getAbbrevName() != null) {
                sql.append(" and a.ABBREV_NAME='%" + servDept.getAbbrevName() + "%'");
            }
            if (servDept.getName() != null) {
                sql.append(" and a.NAME='%" + servDept.getName() + "%'");
            }
            if (servDept.getSts() != null) {
                sql.append(" and a.STS='" + servDept.getSts() + "'");
            }
        }
        sql.append(" order by a.serv_dept_id");
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
            sql2.log(ServDeptSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, ServDeptMVO.class);
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
