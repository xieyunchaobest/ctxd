package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysUserRoleSDAO;
import com.cattsoft.sm.vo.*;

public class SysUserRoleSDAOImpl implements ISysUserRoleSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		SysUserRoleSVO sysUserRole = (SysUserRoleSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" SYS_USER_ROLE(SYS_USER_ROLE_ID,SYS_USER_ID,SYS_ROLE_ID,ALLOW_AUTH,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysUserRole.getSysUserRoleId());
			ps.setString(2, sysUserRole.getSysUserId());
			ps.setString(3, sysUserRole.getSysRoleId());
			ps.setString(4, sysUserRole.getAllowAuth());
			ps.setString(5, sysUserRole.getSts());
			ps.setDate(6, sysUserRole.getStsDate());
			ps.setDate(7, sysUserRole.getCreateDate());
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
		SysUserRoleSVO sysUserRole = (SysUserRoleSVO) vo;
		StringBuffer sql = new StringBuffer("update SYS_USER_ROLE set");
		if (sysUserRole.getSysUserId() != null) {
			sql.append(" SYS_USER_ID=?,");
		}
		if (sysUserRole.getSysRoleId() != null) {
			sql.append(" SYS_ROLE_ID=?,");
		}
		if (sysUserRole.getAllowAuth() != null) {
			sql.append(" ALLOW_AUTH=?,");
		}
		if (sysUserRole.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (sysUserRole.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (sysUserRole.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and SYS_USER_ROLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysUserRole.getSysUserId() != null) {
				ps.setString(index++, sysUserRole.getSysUserId());
			}
			if (sysUserRole.getSysRoleId() != null) {
				ps.setString(index++, sysUserRole.getSysRoleId());
			}
			if (sysUserRole.getAllowAuth() != null) {
				ps.setString(index++, sysUserRole.getAllowAuth());
			}
			if (sysUserRole.getSts() != null) {
				ps.setString(index++, sysUserRole.getSts());
			}
			if (sysUserRole.getStsDate() != null) {
				ps.setDate(index++, sysUserRole.getStsDate());
			}
			if (sysUserRole.getCreateDate() != null) {
				ps.setDate(index++, sysUserRole.getCreateDate());
			}
			ps.setString(index++, sysUserRole.getSysUserRoleId());
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
		SysUserRoleSVO sysUserRole = (SysUserRoleSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from SYS_USER_ROLE where 1=1");
		sql.append(" and SYS_USER_ROLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysUserRole.getSysUserRoleId());
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
		SysUserRoleSVO result = null;
		SysUserRoleSVO sysUserRole = (SysUserRoleSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.SYS_USER_ROLE_ID,a.SYS_USER_ID,a.SYS_ROLE_ID,a.ALLOW_AUTH,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from SYS_USER_ROLE a where 1=1");
		sql.append(" and SYS_USER_ROLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysUserRole.getSysUserRoleId());
			rs = ps.executeQuery();
			result = (SysUserRoleSVO) ResultSetUtil.convertToVo(rs, SysUserRoleSVO.class);
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
		SysUserRoleSVO sysUserRole = (SysUserRoleSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.SYS_USER_ROLE_ID,a.SYS_USER_ID,a.SYS_ROLE_ID,a.ALLOW_AUTH,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from SYS_USER_ROLE a where 1=1");
		if (sysUserRole.getSysUserRoleId() != null) {
			sql.append(" and SYS_USER_ROLE_ID=?");
		}
		if (sysUserRole.getSysUserId() != null) {
			sql.append(" and SYS_USER_ID=?");
		}
		if (sysUserRole.getSysRoleId() != null) {
			sql.append(" and SYS_ROLE_ID=?");
		}
		if (sysUserRole.getAllowAuth() != null) {
			sql.append(" and ALLOW_AUTH=?");
		}
		if (sysUserRole.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (sysUserRole.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (sysUserRole.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysUserRole.getSysUserRoleId() != null) {
				ps.setString(index++, sysUserRole.getSysUserRoleId());
			}
			if (sysUserRole.getSysUserId() != null) {
				ps.setString(index++, sysUserRole.getSysUserId());
			}
			if (sysUserRole.getSysRoleId() != null) {
				ps.setString(index++, sysUserRole.getSysRoleId());
			}
			if (sysUserRole.getAllowAuth() != null) {
				ps.setString(index++, sysUserRole.getAllowAuth());
			}
			if (sysUserRole.getSts() != null) {
				ps.setString(index++, sysUserRole.getSts());
			}
			if (sysUserRole.getStsDate() != null) {
				ps.setDate(index++, sysUserRole.getStsDate());
			}
			if (sysUserRole.getCreateDate() != null) {
				ps.setDate(index++, sysUserRole.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, SysUserRoleSVO.class);
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
