package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IFaultReasonSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.FaultReasonSVO;

public class FaultReasonSDAOImpl implements IFaultReasonSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
    	
        FaultReasonSVO faultReason = (FaultReasonSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql
                .append(" FAULT_REASON(REASON_ID,NAME,REASON_CAT,DUTY_FLAG,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, faultReason.getReasonId());
            ps.setString(2, faultReason.getName());
            ps.setString(3, faultReason.getReasonCat());
            ps.setString(4, faultReason.getDutyFlag());
            ps.setString(5, faultReason.getSts());
            ps.setDate(6, faultReason.getStsDate());
            ps.setDate(7, faultReason.getCreateDate());
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
        FaultReasonSVO faultReason = (FaultReasonSVO) vo;
        StringBuffer sql = new StringBuffer("update FAULT_REASON set");
        if (faultReason.getName() != null) {
            sql.append(" NAME=?,");
        }
        if (faultReason.getReasonCat() != null) {
            sql.append(" REASON_CAT=?,");
        }
        if (faultReason.getDutyFlag() != null) {
            sql.append(" DUTY_FLAG=?,");
        }
        if (faultReason.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (faultReason.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (faultReason.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and REASON_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (faultReason.getName() != null) {
                ps.setString(index++, faultReason.getName());
            }
            if (faultReason.getReasonCat() != null) {
                ps.setString(index++, faultReason.getReasonCat());
            }
            if (faultReason.getDutyFlag() != null) {
                ps.setString(index++, faultReason.getDutyFlag());
            }
            if (faultReason.getSts() != null) {
                ps.setString(index++, faultReason.getSts());
            }
            if (faultReason.getStsDate() != null) {
                ps.setDate(index++, faultReason.getStsDate());
            }
            if (faultReason.getCreateDate() != null) {
                ps.setDate(index++, faultReason.getCreateDate());
            }
            ps.setString(index++, faultReason.getReasonId());
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
        FaultReasonSVO faultReason = (FaultReasonSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from FAULT_REASON where 1=1");
        sql.append(" and REASON_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, faultReason.getReasonId());
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
        FaultReasonSVO result = null;
        FaultReasonSVO faultReason = (FaultReasonSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.REASON_ID,a.NAME,a.REASON_CAT,a.DUTY_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from FAULT_REASON a where 1=1");
        sql.append(" and REASON_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, faultReason.getReasonId());
            rs = ps.executeQuery();
            result = (FaultReasonSVO) ResultSetUtil.convertToVo(rs, FaultReasonSVO.class);
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
        FaultReasonSVO faultReason = (FaultReasonSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.REASON_ID,a.NAME,a.REASON_CAT,a.DUTY_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from FAULT_REASON a where 1=1");
        if (faultReason.getReasonId() != null) {
            sql.append(" and REASON_ID=?");
        }
        if (faultReason.getName() != null) {
            sql.append(" and NAME=?");
        }
        if (faultReason.getReasonCat() != null) {
            sql.append(" and REASON_CAT=?");
        }
        if (faultReason.getDutyFlag() != null) {
            sql.append(" and DUTY_FLAG=?");
        }
        if (faultReason.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (faultReason.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (faultReason.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (faultReason.getReasonId() != null) {
                ps.setString(index++, faultReason.getReasonId());
            }
            if (faultReason.getName() != null) {
                ps.setString(index++, faultReason.getName());
            }
            if (faultReason.getReasonCat() != null) {
                ps.setString(index++, faultReason.getReasonCat());
            }
            if (faultReason.getDutyFlag() != null) {
                ps.setString(index++, faultReason.getDutyFlag());
            }
            if (faultReason.getSts() != null) {
                ps.setString(index++, faultReason.getSts());
            }
            if (faultReason.getStsDate() != null) {
                ps.setDate(index++, faultReason.getStsDate());
            }
            if (faultReason.getCreateDate() != null) {
                ps.setDate(index++, faultReason.getCreateDate());
            }
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, FaultReasonSVO.class);
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

    public PagView findFaultReasonsByPage(FaultReasonSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception {
        List results = null;
        FaultReasonSVO faultReason = (FaultReasonSVO) vo;
        StringBuffer sql = new StringBuffer();
        sql.append("select");
        sql.append(" a.REASON_ID,a.NAME,a.REASON_CAT,a.DUTY_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
        sql.append(" from FAULT_REASON a where 1=1");
        if (vo == null && set != null) {
            sql.append("and ( ");
            sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "reason_id", "reasonId"));
            sql.append(" ) ");
        }
        if (vo != null && set == null) {
            if (faultReason.getReasonId() != null) {
                sql.append(" and REASON_ID=" + faultReason.getReasonId());
            }
            if (faultReason.getName() != null) {
                sql.append(" and NAME like '%" + faultReason.getName()+"%'");
            }
            if (faultReason.getReasonCat() != null) {
                sql.append(" and REASON_CAT='" + faultReason.getReasonCat()+"'");
            }
            if (faultReason.getDutyFlag() != null) {
                sql.append(" and DUTY_FLAG='" + faultReason.getDutyFlag()+"'");
            }
            if (faultReason.getSts() != null) {
                sql.append(" and STS='" + faultReason.getSts() + "'");
            }
            if (faultReason.getStsDate() != null) {
                sql.append(" and STS_DATE=" + faultReason.getStsDate());
            }
            if (faultReason.getCreateDate() != null) {
                sql.append(" and CREATE_DATE=" + faultReason.getCreateDate());
            }
        }
        sql.append(" order by reason_id");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PagView pagView = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            Sql sql2 = new Sql();
            sql2.setSql(sql.toString());
            sql2.log(FaultReasonSDAOImpl.class);
            pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
            pagView.getPagCount();
            rs = PagUtil.queryOracle(connection, sql2, pagInfo);
            results = (List) ResultSetUtil.convertToList(rs, FaultReasonSVO.class);
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
