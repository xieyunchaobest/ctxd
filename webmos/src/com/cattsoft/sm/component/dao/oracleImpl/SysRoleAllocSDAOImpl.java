package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysRoleAllocSDAO;
import com.cattsoft.sm.vo.*;

public class SysRoleAllocSDAOImpl implements ISysRoleAllocSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		SysRoleAllocSVO sysRoleAlloc = (SysRoleAllocSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" SYS_ROLE_ALLOC(SYS_ROLE_ALLOC_ID,SYS_ROLE_ID,FUNC_NODE_ID,ALLOW_AUTH,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysRoleAlloc.getSysRoleAllocId());
			ps.setString(2, sysRoleAlloc.getSysRoleId());
			ps.setString(3, sysRoleAlloc.getFuncNodeId());
			ps.setString(4, sysRoleAlloc.getAllowAuth());
			ps.setString(5, sysRoleAlloc.getSts());
			ps.setDate(6, sysRoleAlloc.getStsDate());
			ps.setDate(7, sysRoleAlloc.getCreateDate());
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
		SysRoleAllocSVO sysRoleAlloc = (SysRoleAllocSVO) vo;
		StringBuffer sql = new StringBuffer("update SYS_ROLE_ALLOC set");
		if (sysRoleAlloc.getSysRoleId() != null) {
			sql.append(" SYS_ROLE_ID=?,");
		}
		if (sysRoleAlloc.getFuncNodeId() != null) {
			sql.append(" FUNC_NODE_ID=?,");
		}
		if (sysRoleAlloc.getAllowAuth() != null) {
			sql.append(" ALLOW_AUTH=?,");
		}
		if (sysRoleAlloc.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (sysRoleAlloc.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (sysRoleAlloc.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and SYS_ROLE_ALLOC_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysRoleAlloc.getSysRoleId() != null) {
				ps.setString(index++, sysRoleAlloc.getSysRoleId());
			}
			if (sysRoleAlloc.getFuncNodeId() != null) {
				ps.setString(index++, sysRoleAlloc.getFuncNodeId());
			}
			if (sysRoleAlloc.getAllowAuth() != null) {
				ps.setString(index++, sysRoleAlloc.getAllowAuth());
			}
			if (sysRoleAlloc.getSts() != null) {
				ps.setString(index++, sysRoleAlloc.getSts());
			}
			if (sysRoleAlloc.getStsDate() != null) {
				ps.setDate(index++, sysRoleAlloc.getStsDate());
			}
			if (sysRoleAlloc.getCreateDate() != null) {
				ps.setDate(index++, sysRoleAlloc.getCreateDate());
			}
			ps.setString(index++, sysRoleAlloc.getSysRoleAllocId());
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
		SysRoleAllocSVO sysRoleAlloc = (SysRoleAllocSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from SYS_ROLE_ALLOC where 1=1");
		sql.append(" and SYS_ROLE_ALLOC_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysRoleAlloc.getSysRoleAllocId());
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
		SysRoleAllocSVO result = null;
		SysRoleAllocSVO sysRoleAlloc = (SysRoleAllocSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.SYS_ROLE_ALLOC_ID,a.SYS_ROLE_ID,a.FUNC_NODE_ID,a.ALLOW_AUTH,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from SYS_ROLE_ALLOC a where 1=1");
		sql.append(" and SYS_ROLE_ALLOC_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysRoleAlloc.getSysRoleAllocId());
			rs = ps.executeQuery();
			result = (SysRoleAllocSVO) ResultSetUtil.convertToVo(rs, SysRoleAllocSVO.class);
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
		SysRoleAllocSVO sysRoleAlloc = (SysRoleAllocSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.SYS_ROLE_ALLOC_ID,a.SYS_ROLE_ID,a.FUNC_NODE_ID,a.ALLOW_AUTH,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from SYS_ROLE_ALLOC a where 1=1");
		if (sysRoleAlloc.getSysRoleAllocId() != null) {
			sql.append(" and SYS_ROLE_ALLOC_ID=?");
		}
		if (sysRoleAlloc.getSysRoleId() != null) {
			sql.append(" and SYS_ROLE_ID=?");
		}
		if (sysRoleAlloc.getFuncNodeId() != null) {
			sql.append(" and FUNC_NODE_ID=?");
		}
		if (sysRoleAlloc.getAllowAuth() != null) {
			sql.append(" and ALLOW_AUTH=?");
		}
		if (sysRoleAlloc.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (sysRoleAlloc.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (sysRoleAlloc.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysRoleAlloc.getSysRoleAllocId() != null) {
				ps.setString(index++, sysRoleAlloc.getSysRoleAllocId());
			}
			if (sysRoleAlloc.getSysRoleId() != null) {
				ps.setString(index++, sysRoleAlloc.getSysRoleId());
			}
			if (sysRoleAlloc.getFuncNodeId() != null) {
				ps.setString(index++, sysRoleAlloc.getFuncNodeId());
			}
			if (sysRoleAlloc.getAllowAuth() != null) {
				ps.setString(index++, sysRoleAlloc.getAllowAuth());
			}
			if (sysRoleAlloc.getSts() != null) {
				ps.setString(index++, sysRoleAlloc.getSts());
			}
			if (sysRoleAlloc.getStsDate() != null) {
				ps.setDate(index++, sysRoleAlloc.getStsDate());
			}
			if (sysRoleAlloc.getCreateDate() != null) {
				ps.setDate(index++, sysRoleAlloc.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, SysRoleAllocSVO.class);
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
