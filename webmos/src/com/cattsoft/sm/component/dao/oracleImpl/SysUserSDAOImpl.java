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
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.vo.SysUserSVO;

public class SysUserSDAOImpl implements ISysUserSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
        SysUserSVO sysUser = (SysUserSVO) vo;
        Sql sql = new Sql("insert into");
        sql
                .append(" SYS_USER(SYS_USER_ID,PARTY_ROLE_TYPE_ID,PARTY_ROLE_ID,SYS_USER_NAME,PASSWORD,SET_PWD_TIME,UPDATE_PWD_TIME,LAST_PWD,CREATE_DATE,STS,STS_DATE,local_net_id) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, sysUser.getSysUserId());
            sql.setString(2, sysUser.getPartyRoleTypeId());
            sql.setString(3, sysUser.getPartyRoleId());
            sql.setString(4, sysUser.getSysUserName());
            sql.setString(5, sysUser.getPassword());
            sql.setTimestamp(6, sysUser.getSetPwdTime());
            sql.setTimestamp(7, sysUser.getUpdatePwdTime());
            sql.setString(8, sysUser.getLastPwd());
            sql.setTimestamp(9, sysUser.getCreateDate());
            sql.setString(10, sysUser.getSts());
            sql.setTimestamp(11, sysUser.getStsDate());
            sql.setString(12, sysUser.getLocalNetId());
            sql.fillParams(ps);
            sql.logPartition(null, this.getClass());
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
        SysUserSVO sysUser = (SysUserSVO) vo;
        Sql sql = new Sql("update SYS_USER set");
        if (sysUser.getPartyRoleTypeId() != null) {
            sql.append(" PARTY_ROLE_TYPE_ID=?,");
        }
        if (sysUser.getPartyRoleId() != null) {
            sql.append(" PARTY_ROLE_ID=?,");
        }
        if (sysUser.getSysUserName() != null) {
            sql.append(" SYS_USER_NAME=?,");
        }
        if (sysUser.getPassword() != null) {
            sql.append(" PASSWORD=?,");
        }
        if (sysUser.getSetPwdTime() != null) {
            sql.append(" SET_PWD_TIME=?,");
        }
        if (sysUser.getUpdatePwdTime() != null) {
            sql.append(" UPDATE_PWD_TIME=?,");
        }
        if (sysUser.getLastPwd() != null) {
            sql.append(" LAST_PWD=?,");
        }
        if (sysUser.getCreateDate() != null) {
            sql.append(" CREATE_DATE=?,");
        }
        if (sysUser.getSts() != null) {
            sql.append(" STS=?,");
        }
        if (sysUser.getStsDate() != null) {
            sql.append(" STS_DATE=?,");
        }
        if (sysUser.getLocalNetId() != null) {
            sql.append(" local_net_id=?,");
        }
        sql.removeSuffix(1);
        sql.append(" where 1=1");
        sql.append(" and SYS_USER_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getSql());
            int index = 1;
            if (sysUser.getPartyRoleTypeId() != null) {
                sql.setString(index++, sysUser.getPartyRoleTypeId());
            }
            if (sysUser.getPartyRoleId() != null) {
                sql.setString(index++, sysUser.getPartyRoleId());
            }
            if (sysUser.getSysUserName() != null) {
                sql.setString(index++, sysUser.getSysUserName());
            }
            if (sysUser.getPassword() != null) {
                sql.setString(index++, sysUser.getPassword());
            }
            if (sysUser.getSetPwdTime() != null) {
                sql.setTimestamp(index++, sysUser.getSetPwdTime());
            }
            if (sysUser.getUpdatePwdTime() != null) {
                sql.setTimestamp(index++, sysUser.getUpdatePwdTime());
            }
            if (sysUser.getLastPwd() != null) {
                sql.setString(index++, sysUser.getLastPwd());
            }
            if (sysUser.getCreateDate() != null) {
                sql.setTimestamp(index++, sysUser.getCreateDate());
            }
            if (sysUser.getSts() != null) {
                sql.setString(index++, sysUser.getSts());
            }
            if (sysUser.getStsDate() != null) {
                sql.setTimestamp(index++, sysUser.getStsDate());
            }
            if (sysUser.getLocalNetId() != null) {
                sql.setString(index++, sysUser.getLocalNetId());
            }
            sql.setString(index++, sysUser.getSysUserId());
            sql.fillParams(ps);
            //sql.logPartition(null, this.getClass());
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
        SysUserSVO sysUser = (SysUserSVO) vo;
        if (vo == null) {
            throw new RuntimeException();
        }
        Sql sql = new Sql("delete from SYS_USER where 1=1");
        sql.append(" and SYS_USER_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, sysUser.getSysUserId());
            sql.fillParams(ps);
            sql.logPartition(null, this.getClass());
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
        SysUserSVO result = null;
        SysUserSVO sysUser = (SysUserSVO) vo;
        Sql sql = new Sql("select");
        sql
                .append(" a.SYS_USER_ID,a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.SYS_USER_NAME,a.PASSWORD,a.SET_PWD_TIME,a.UPDATE_PWD_TIME,a.LAST_PWD,a.CREATE_DATE,a.STS,a.STS_DATE,a.local_net_id ");
        sql.append(" from SYS_USER a where 1=1");
        sql.append(" and SYS_USER_ID=?");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getPartitionSql(null));
            sql.setString(1, sysUser.getSysUserId());
            sql.fillParams(ps);
            sql.logPartition(null, this.getClass());
            rs = ps.executeQuery();
            result = (SysUserSVO) ResultSetUtil.convertToVo(rs, SysUserSVO.class);
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
        SysUserSVO sysUser = (SysUserSVO) vo;
        Sql sql = new Sql();
        sql.append("select");
        sql
                .append(" a.SYS_USER_ID,a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.SYS_USER_NAME,a.PASSWORD,a.SET_PWD_TIME,a.UPDATE_PWD_TIME,a.LAST_PWD,a.CREATE_DATE,a.STS,a.STS_DATE,a.local_net_id ");
        sql.append(" from SYS_USER a where 1=1");
        if (sysUser.getSysUserId() != null) {
            sql.append(" and SYS_USER_ID=?");
        }
        if (sysUser.getPartyRoleTypeId() != null) {
            sql.append(" and PARTY_ROLE_TYPE_ID=?");
        }
        if (sysUser.getPartyRoleId() != null) {
            sql.append(" and PARTY_ROLE_ID=?");
        }
        if (sysUser.getSysUserName() != null) {
            sql.append(" and SYS_USER_NAME=?");
        }
        if (sysUser.getPassword() != null) {
            sql.append(" and PASSWORD=?");
        }
        if (sysUser.getSetPwdTime() != null) {
            sql.append(" and SET_PWD_TIME=?");
        }
        if (sysUser.getUpdatePwdTime() != null) {
            sql.append(" and UPDATE_PWD_TIME=?");
        }
        if (sysUser.getLastPwd() != null) {
            sql.append(" and LAST_PWD=?");
        }
        if (sysUser.getCreateDate() != null) {
            sql.append(" and CREATE_DATE=?");
        }
        if (sysUser.getSts() != null) {
            sql.append(" and STS=?");
        }
        if (sysUser.getStsDate() != null) {
            sql.append(" and STS_DATE=?");
        }
        if (sysUser.getStsDate() != null) {
            sql.append(" and local_net_id=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getSql());
            int index = 1;
            if (sysUser.getSysUserId() != null) {
                sql.setString(index++, sysUser.getSysUserId());
            }
            if (sysUser.getPartyRoleTypeId() != null) {
                sql.setString(index++, sysUser.getPartyRoleTypeId());
            }
            if (sysUser.getPartyRoleId() != null) {
                sql.setString(index++, sysUser.getPartyRoleId());
            }
            if (sysUser.getSysUserName() != null) {
                sql.setString(index++, sysUser.getSysUserName());
            }
            if (sysUser.getPassword() != null) {
                sql.setString(index++, sysUser.getPassword());
            }
            if (sysUser.getSetPwdTime() != null) {
                sql.setTimestamp(index++, sysUser.getSetPwdTime());
            }
            if (sysUser.getUpdatePwdTime() != null) {
                sql.setTimestamp(index++, sysUser.getUpdatePwdTime());
            }
            if (sysUser.getLastPwd() != null) {
                sql.setString(index++, sysUser.getLastPwd());
            }
            if (sysUser.getCreateDate() != null) {
                sql.setTimestamp(index++, sysUser.getCreateDate());
            }
            if (sysUser.getSts() != null) {
                sql.setString(index++, sysUser.getSts());
            }
            if (sysUser.getStsDate() != null) {
                sql.setTimestamp(index++, sysUser.getStsDate());
            }
            if (sysUser.getLocalNetId() != null)
                sql.setString(index++, sysUser.getLocalNetId());
            sql.fillParams(ps);
            sql.log(this.getClass());
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, SysUserSVO.class);
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
