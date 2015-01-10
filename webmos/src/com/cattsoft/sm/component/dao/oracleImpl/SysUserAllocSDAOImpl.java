package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysUserAllocSDAO;
import com.cattsoft.sm.vo.SysUserAllocSVO;

public class SysUserAllocSDAOImpl implements ISysUserAllocSDAO {

    public void add(GenericVO vo) throws AppException, SysException {
        SysUserAllocSVO sysUserAlloc = (SysUserAllocSVO) vo;
        StringBuffer sql = new StringBuffer("insert into");
        sql.append(" SYS_USER_ALLOC(ALLOC_ID,SYS_USER_ID,FUNC_NODE_ID,SYS_ROLE_ID,GRANT_SYS_USER_ID,ENTRUST_FLAG,ALLOC_AUTH,LOCAL_NET_ID,RANGE_ID,CREATE_DATE,STS_DATE,STS) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysUserAlloc.getAllocId());
            ps.setString(2, sysUserAlloc.getSysUserId());
            ps.setString(3, sysUserAlloc.getFuncNodeId());
            ps.setString(4, sysUserAlloc.getSysRoleId());
            ps.setString(5, sysUserAlloc.getGrantSysUserId());
            ps.setString(6, sysUserAlloc.getEntrustFlag());
            ps.setString(7, sysUserAlloc.getAllocAuth());
            ps.setString(8, sysUserAlloc.getLocalNetId());
            ps.setString(9, sysUserAlloc.getRangeId());
            ps.setDate(10, sysUserAlloc.getCreateDate());
            ps.setDate(11, sysUserAlloc.getStsDate());
            ps.setString(12, sysUserAlloc.getSts());
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
        SysUserAllocSVO sysUserAlloc = (SysUserAllocSVO) vo;
        StringBuffer sql = new StringBuffer("update SYS_USER_ALLOC set");
        if (sysUserAlloc.getSysUserId() != null) {
            sql.append(" SYS_USER_ID=?,");
        }
        if (sysUserAlloc.getFuncNodeId() != null) {
            sql.append(" FUNC_NODE_ID=?,");
        }
        if (sysUserAlloc.getSysRoleId() != null) {
            sql.append(" SYS_ROLE_ID=?,");
        }
        if (sysUserAlloc.getGrantSysUserId() != null) {
            sql.append(" GRANT_SYS_USER_ID=?,");
        }
        if (sysUserAlloc.getEntrustFlag() != null) {
            sql.append(" ENTRUST_FLAG=?,");
        }
        if (sysUserAlloc.getAllocAuth() != null) {
            sql.append(" ALLOC_AUTH=?,");
        }
        if (sysUserAlloc.getLocalNetId() != null) {
            sql.append(" LOCAL_NET_ID=?,");
        }
        if (sysUserAlloc.getRangeId() != null) {
            sql.append(" RANGE_ID=?,");
        }
        if (sysUserAlloc.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (sysUserAlloc.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (sysUserAlloc.getSts() != null) {
            sql.append(" STS=?,");
        }
        sql = sql.delete(sql.length() - 1, sql.length());
        sql.append(" where 1=1");
        sql.append(" and ALLOC_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            int index = 1;
            if (sysUserAlloc.getSysUserId() != null) {
                ps.setString(index++, sysUserAlloc.getSysUserId());
            }
            if (sysUserAlloc.getFuncNodeId() != null) {
                ps.setString(index++, sysUserAlloc.getFuncNodeId());
            }
            if (sysUserAlloc.getSysRoleId() != null) {
                ps.setString(index++, sysUserAlloc.getSysRoleId());
            }
            if (sysUserAlloc.getGrantSysUserId() != null) {
                ps.setString(index++, sysUserAlloc.getGrantSysUserId());
            }
            if (sysUserAlloc.getEntrustFlag() != null) {
                ps.setString(index++, sysUserAlloc.getEntrustFlag());
            }
            if (sysUserAlloc.getAllocAuth() != null) {
                ps.setString(index++, sysUserAlloc.getAllocAuth());
            }
            if (sysUserAlloc.getLocalNetId() != null) {
                ps.setString(index++, sysUserAlloc.getLocalNetId());
            }
            if (sysUserAlloc.getRangeId() != null) {
                ps.setString(index++, sysUserAlloc.getRangeId());
            }
            if (sysUserAlloc.getCreateDate() != null) {
                ps.setDate(index++, sysUserAlloc.getCreateDate());
            }
            if (sysUserAlloc.getStsDate() != null) {
                ps.setDate(index++, sysUserAlloc.getStsDate());
            }
            if (sysUserAlloc.getSts() != null) {
                ps.setString(index++, sysUserAlloc.getSts());
            }
            ps.setString(index++, sysUserAlloc.getAllocId());
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
        SysUserAllocSVO sysUserAlloc = (SysUserAllocSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        StringBuffer sql = new StringBuffer("delete from SYS_USER_ALLOC where 1=1");
        sql.append(" and ALLOC_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysUserAlloc.getAllocId());
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
        SysUserAllocSVO result = null;
        SysUserAllocSVO sysUserAlloc = (SysUserAllocSVO) vo;
        StringBuffer sql = new StringBuffer("select");
        sql.append(" a.ALLOC_ID,a.SYS_USER_ID,a.FUNC_NODE_ID,a.SYS_ROLE_ID,a.GRANT_SYS_USER_ID,a.ENTRUST_FLAG,a.ALLOC_AUTH,a.LOCAL_NET_ID,a.RANGE_ID,a.CREATE_DATE,a.STS_DATE,a.STS");
        sql.append(" from SYS_USER_ALLOC a where 1=1");
        sql.append(" and ALLOC_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.toString());
            ps.setString(1, sysUserAlloc.getAllocId());
            rs = ps.executeQuery();
            result = (SysUserAllocSVO) ResultSetUtil.convertToVo(rs, SysUserAllocSVO.class);
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
        SysUserAllocSVO sysUserAlloc = (SysUserAllocSVO) vo;
        Sql sql = new Sql();
        sql.append("select");
        sql.append(" a.ALLOC_ID,a.SYS_USER_ID,a.FUNC_NODE_ID,a.SYS_ROLE_ID,a.GRANT_SYS_USER_ID,a.ENTRUST_FLAG,a.ALLOC_AUTH,a.LOCAL_NET_ID,a.RANGE_ID,a.CREATE_DATE,a.STS_DATE,a.STS");
        sql.append(" from SYS_USER_ALLOC a where 1=1");
        if (sysUserAlloc.getAllocId() != null) {
            sql.append(" and ALLOC_ID=:allocId ");
            sql.setString("allocId", sysUserAlloc.getAllocId());
        }
        if (sysUserAlloc.getSysUserId() != null) {
            sql.append(" and SYS_USER_ID=:sysUserId ");
            sql.setString("sysUserId", sysUserAlloc.getSysUserId());
        }
        if (sysUserAlloc.getFuncNodeId() != null) {
            sql.append(" and FUNC_NODE_ID=:funcNodeId ");
            sql.setString("funcNodeId", sysUserAlloc.getFuncNodeId());
        }
        if (sysUserAlloc.getSysRoleId() != null) {
            sql.append(" and SYS_ROLE_ID=:sysRoleId ");
            sql.setString("sysRoleId", sysUserAlloc.getSysRoleId());
        }
        if (sysUserAlloc.getGrantSysUserId() != null) {
            sql.append(" and GRANT_SYS_USER_ID=:grantSysUserId ");
            sql.setString("grantSysUserId", sysUserAlloc.getGrantSysUserId());
        }
        if (sysUserAlloc.getEntrustFlag() != null) {
            sql.append(" and ENTRUST_FLAG=:entrustFlag ");
            sql.setString("entrustFlag", sysUserAlloc.getEntrustFlag());
        }
        if (sysUserAlloc.getAllocAuth() != null) {
            sql.append(" and ALLOC_AUTH=:allocAuth ");
            sql.setString("allocAuth", sysUserAlloc.getAllocAuth());
        }
        if (sysUserAlloc.getLocalNetId() != null) {
            sql.append(" and LOCAL_NET_ID=:localNetId ");
            sql.setString("localNetId", sysUserAlloc.getLocalNetId());
        }
        if (sysUserAlloc.getRangeId() != null) {
            sql.append(" and RANGE_ID=:rangeId ");
            sql.setString("rangeId", sysUserAlloc.getRangeId());
        }
        if (sysUserAlloc.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=:createDate ");
            sql.setTimestamp("createDate", sysUserAlloc.getCreateDate());
        }
        if (sysUserAlloc.getStsDate() != null) {
            sql.append(" and STS_DATE=:stsDate ");
            sql.setTimestamp("stsDate", sysUserAlloc.getStsDate());
        }
        if (sysUserAlloc.getSts() != null) {
            sql.append(" and STS=:sts ");
            sql.setString("sts", sysUserAlloc.getSts());
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            connection = ConnectionFactory.getConnection();
//            ps = connection.prepareStatement(sql.toString());
//            int index = 1;
//            if (sysUserAlloc.getAllocId() != null) {
//                ps.setString(index++, sysUserAlloc.getAllocId());
//            }
//            if (sysUserAlloc.getSysUserId() != null) {
//                ps.setString(index++, sysUserAlloc.getSysUserId());
//            }
//            if (sysUserAlloc.getFuncNodeId() != null) {
//                ps.setString(index++, sysUserAlloc.getFuncNodeId());
//            }
//            if (sysUserAlloc.getSysRoleId() != null) {
//                ps.setString(index++, sysUserAlloc.getSysRoleId());
//            }
//            if (sysUserAlloc.getGrantSysUserId() != null) {
//                ps.setString(index++, sysUserAlloc.getGrantSysUserId());
//            }
//            if (sysUserAlloc.getEntrustFlag() != null) {
//                ps.setString(index++, sysUserAlloc.getEntrustFlag());
//            }
//            if (sysUserAlloc.getAllocAuth() != null) {
//                ps.setString(index++, sysUserAlloc.getAllocAuth());
//            }
//            if (sysUserAlloc.getLocalNetId() != null) {
//                ps.setString(index++, sysUserAlloc.getLocalNetId());
//            }
//            if (sysUserAlloc.getRangeId() != null) {
//                ps.setString(index++, sysUserAlloc.getRangeId());
//            }
//            if (sysUserAlloc.getCreateDate() != null) {
//                ps.setDate(index++, sysUserAlloc.getCreateDate());
//            }
//            if (sysUserAlloc.getStsDate() != null) {
//                ps.setDate(index++, sysUserAlloc.getStsDate());
//            }
//            if (sysUserAlloc.getSts() != null) {
//                ps.setString(index++, sysUserAlloc.getSts());
//            }
//            conn = ConnectionFactory.getConnection();
//			PreparedStatement ps = null;
            
        	connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
            //rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, SysUserAllocSVO.class);
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

}
