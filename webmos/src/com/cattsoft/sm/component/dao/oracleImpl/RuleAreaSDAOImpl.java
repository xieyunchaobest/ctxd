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
import com.cattsoft.sm.component.dao.IRuleAreaSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.RuleAreaMVO;
import com.cattsoft.sm.vo.RuleAreaSVO;

public class RuleAreaSDAOImpl implements IRuleAreaSDAO {
	
	private static final Logger log = Logger.getLogger(RuleAreaSDAOImpl.class);

    public void add(GenericVO vo) throws AppException, SysException {
    	
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" RULE_AREA(RULE_AREA_ID,NAME,LOCAL_NET_ID,AREA_ID,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, ruleArea.getRuleAreaId());
            ps.setString(2, ruleArea.getName());
            ps.setString(3, ruleArea.getLocalNetId());
            ps.setString(4, ruleArea.getAreaId());
            ps.setString(5, ruleArea.getSts());
            ps.setDate(6, ruleArea.getStsDate());
            ps.setDate(7, ruleArea.getCreateDate());
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
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        StringBuffer sql = new StringBuffer("update RULE_AREA set");
        if (ruleArea.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (ruleArea.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (ruleArea.getAreaId() != null) {
            sql.append(" AREA_ID=?,");
        }
        if (ruleArea.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (ruleArea.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (ruleArea.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and RULE_AREA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (ruleArea.getName() != null) {
                ps.setString(index++, ruleArea.getName());
            }
            if (ruleArea.getLocalNetId() != null) {
                ps.setString(index++, ruleArea.getLocalNetId());
            }
            if (ruleArea.getAreaId() != null) {
                ps.setString(index++, ruleArea.getAreaId());
            }
            if (ruleArea.getSts() != null) {
                ps.setString(index++, ruleArea.getSts());
            }
            if (ruleArea.getStsDate() != null) {
                ps.setDate(index++, ruleArea.getStsDate());
            }
            if (ruleArea.getCreateDate() != null) {
                ps.setDate(index++, ruleArea.getCreateDate());
            }
            ps.setString(index++, ruleArea.getRuleAreaId());
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
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from RULE_AREA where 1=1");
        sql.append(" and RULE_AREA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, ruleArea.getRuleAreaId());
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
        RuleAreaSVO result = null;
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql
                .append(" a.RULE_AREA_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from RULE_AREA a where 1=1");
        sql.append(" and RULE_AREA_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, ruleArea.getRuleAreaId());
            rs = ps.executeQuery();
            result = (RuleAreaSVO) ResultSetUtil.convertToVo(rs, RuleAreaSVO.class);
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
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.RULE_AREA_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from RULE_AREA a where 1=1");
        if (ruleArea.getRuleAreaId() != null) {
            sql.append(" and RULE_AREA_ID=?");
        }
        if (ruleArea.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (ruleArea.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=?");
        }
        if (ruleArea.getAreaId() != null) {
            sql.append(" and AREA_ID=?");
        }
        if (ruleArea.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (ruleArea.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (ruleArea.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (ruleArea.getRuleAreaId() != null) {
                ps.setString(index++, ruleArea.getRuleAreaId());
            }
            if (ruleArea.getName() != null) {
                ps.setString(index++, ruleArea.getName());
            }
            if (ruleArea.getLocalNetId() != null) {
                ps.setString(index++, ruleArea.getLocalNetId());
            }
            if (ruleArea.getAreaId() != null) {
                ps.setString(index++, ruleArea.getAreaId());
            }
            if (ruleArea.getSts() != null) {
                ps.setString(index++, ruleArea.getSts());
            }
            if (ruleArea.getStsDate() != null) {
                ps.setDate(index++, ruleArea.getStsDate());
            }
            if (ruleArea.getCreateDate() != null) {
                ps.setDate(index++, ruleArea.getCreateDate());
            }
            log.info(sql.toString());
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, RuleAreaSVO.class);
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

    public RuleAreaSVO findByName(GenericVO vo) throws AppException, SysException {
        RuleAreaSVO result = null;
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql
                .append(" a.RULE_AREA_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from RULE_AREA a where 1=1");
        sql.append(" and NAME=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, ruleArea.getName());
            rs = ps.executeQuery();
            result = (RuleAreaSVO) ResultSetUtil.convertToVo(rs, RuleAreaSVO.class);
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
        return result;
    }

    public PagView findByPage(GenericVO vo, PagInfo pagInfo) throws AppException, SysException {
        List results = null;
        RuleAreaSVO ruleArea = (RuleAreaSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(
                " a.RULE_AREA_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.STS,a.STS_DATE,a.CREATE_DATE, ")
                .append(" b.name local_net_name,c.name area_name ");
        sql
                .append(" from local_net b,area c,RULE_AREA a where 1=1 and a.local_net_id=b.local_net_id and a.area_id=c.area_id ");
        if (ruleArea.getRuleAreaId() != null) {
            sql.append(" and a.RULE_AREA_ID=" + ruleArea.getRuleAreaId());
        }
        if (ruleArea.getName() != null) {
            sql.append(" and a.NAME='%" + ruleArea.getName() + "%'");
        }
        if (ruleArea.getLocalNetId() != null) {
            sql.append(" and a.LOCAL_NET_ID=" + ruleArea.getLocalNetId());
        }
        if (ruleArea.getAreaId() != null) {
            sql.append(" and a.AREA_ID=" + ruleArea.getAreaId());
        }
        if (ruleArea.getSts() != null) {
            sql.append(" and a.STS='" + ruleArea.getSts() + "'");
        }
        if (ruleArea.getStsDate() != null) {
            sql.append(" and a.STS_DATE=" + ruleArea.getStsDate());
        }
        if (ruleArea.getCreateDate() != null) {
            sql.append(" and a.CREATE_DATE=" + ruleArea.getCreateDate());
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(RuleAreaSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, RuleAreaMVO.class);
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
        sql.append(
                " a.RULE_AREA_ID,a.NAME,a.LOCAL_NET_ID,a.AREA_ID,a.STS,a.STS_DATE,a.CREATE_DATE, ")
                .append(" b.name local_net_name,c.name area_name ");
        sql
                .append(" from local_net b,area c,RULE_AREA a where 1=1 and a.local_net_id=b.local_net_id and a.area_id=c.area_id and ( ");
        sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "rule_area_id", "ruleAreaId"));
        sql.append(")");

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(RuleAreaSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, RuleAreaMVO.class);
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
