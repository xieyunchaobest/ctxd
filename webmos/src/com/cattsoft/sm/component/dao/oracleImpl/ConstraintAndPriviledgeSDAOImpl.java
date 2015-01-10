package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IConstraintAndPriviledgeSDAO;
import com.cattsoft.sm.vo.*;

public class ConstraintAndPriviledgeSDAOImpl implements IConstraintAndPriviledgeSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		ConstraintAndPriviledgeSVO constraintAndPriviledge = (ConstraintAndPriviledgeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" CONSTRAINT_AND_PRIVILEDGE(CONS_PRIV_ID,SYS_USER_ROLE_ID,SYS_USER_ID,FUNC_NODE_ID,FLAG,RANGE_ID,CREATE_DATE,STS,STS_DATE) values(?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, constraintAndPriviledge.getConsPrivId());
			ps.setString(2, constraintAndPriviledge.getSysUserRoleId());
			ps.setString(3, constraintAndPriviledge.getSysUserId());
			ps.setString(4, constraintAndPriviledge.getFuncNodeId());
			ps.setString(5, constraintAndPriviledge.getFlag());
			ps.setString(6, constraintAndPriviledge.getRangeId());
			ps.setDate(7, constraintAndPriviledge.getCreateDate());
			ps.setString(8, constraintAndPriviledge.getSts());
			ps.setDate(9, constraintAndPriviledge.getStsDate());
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
		ConstraintAndPriviledgeSVO constraintAndPriviledge = (ConstraintAndPriviledgeSVO) vo;
		StringBuffer sql = new StringBuffer("update CONSTRAINT_AND_PRIVILEDGE set");
		if (constraintAndPriviledge.getSysUserRoleId() != null) {
			sql.append(" SYS_USER_ROLE_ID=?,");
		}
		if (constraintAndPriviledge.getSysUserId() != null) {
			sql.append(" SYS_USER_ID=?,");
		}
		if (constraintAndPriviledge.getFuncNodeId() != null) {
			sql.append(" FUNC_NODE_ID=?,");
		}
		if (constraintAndPriviledge.getFlag() != null) {
			sql.append(" FLAG=?,");
		}
		if (constraintAndPriviledge.getRangeId() != null) {
			sql.append(" RANGE_ID=?,");
		}
		if (constraintAndPriviledge.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (constraintAndPriviledge.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (constraintAndPriviledge.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and CONS_PRIV_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (constraintAndPriviledge.getSysUserRoleId() != null) {
				ps.setString(index++, constraintAndPriviledge.getSysUserRoleId());
			}
			if (constraintAndPriviledge.getSysUserId() != null) {
				ps.setString(index++, constraintAndPriviledge.getSysUserId());
			}
			if (constraintAndPriviledge.getFuncNodeId() != null) {
				ps.setString(index++, constraintAndPriviledge.getFuncNodeId());
			}
			if (constraintAndPriviledge.getFlag() != null) {
				ps.setString(index++, constraintAndPriviledge.getFlag());
			}
			if (constraintAndPriviledge.getRangeId() != null) {
				ps.setString(index++, constraintAndPriviledge.getRangeId());
			}
			if (constraintAndPriviledge.getCreateDate() != null) {
				ps.setDate(index++, constraintAndPriviledge.getCreateDate());
			}
			if (constraintAndPriviledge.getSts() != null) {
				ps.setString(index++, constraintAndPriviledge.getSts());
			}
			if (constraintAndPriviledge.getStsDate() != null) {
				ps.setDate(index++, constraintAndPriviledge.getStsDate());
			}
			ps.setString(index++, constraintAndPriviledge.getConsPrivId());
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
		ConstraintAndPriviledgeSVO constraintAndPriviledge = (ConstraintAndPriviledgeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from CONSTRAINT_AND_PRIVILEDGE where 1=1");
		sql.append(" and CONS_PRIV_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, constraintAndPriviledge.getConsPrivId());
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
		ConstraintAndPriviledgeSVO result = null;
		ConstraintAndPriviledgeSVO constraintAndPriviledge = (ConstraintAndPriviledgeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.CONS_PRIV_ID,a.SYS_USER_ROLE_ID,a.SYS_USER_ID,a.FUNC_NODE_ID,a.FLAG,a.RANGE_ID,a.CREATE_DATE,a.STS,a.STS_DATE");
		sql.append(" from CONSTRAINT_AND_PRIVILEDGE a where 1=1");
		sql.append(" and CONS_PRIV_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, constraintAndPriviledge.getConsPrivId());
			rs = ps.executeQuery();
			result = (ConstraintAndPriviledgeSVO) ResultSetUtil.convertToVo(rs, ConstraintAndPriviledgeSVO.class);
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
		ConstraintAndPriviledgeSVO constraintAndPriviledge = (ConstraintAndPriviledgeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.CONS_PRIV_ID,a.SYS_USER_ROLE_ID,a.SYS_USER_ID,a.FUNC_NODE_ID,a.FLAG,a.RANGE_ID,a.CREATE_DATE,a.STS,a.STS_DATE");
		sql.append(" from CONSTRAINT_AND_PRIVILEDGE a where 1=1");
		if (constraintAndPriviledge.getConsPrivId() != null) {
			sql.append(" and CONS_PRIV_ID=?");
		}
		if (constraintAndPriviledge.getSysUserRoleId() != null) {
			sql.append(" and SYS_USER_ROLE_ID=?");
		}
		if (constraintAndPriviledge.getSysUserId() != null) {
			sql.append(" and SYS_USER_ID=?");
		}
		if (constraintAndPriviledge.getFuncNodeId() != null) {
			sql.append(" and FUNC_NODE_ID=?");
		}
		if (constraintAndPriviledge.getFlag() != null) {
			sql.append(" and FLAG=?");
		}
		if (constraintAndPriviledge.getRangeId() != null) {
			sql.append(" and RANGE_ID=?");
		}
		if (constraintAndPriviledge.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (constraintAndPriviledge.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (constraintAndPriviledge.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (constraintAndPriviledge.getConsPrivId() != null) {
				ps.setString(index++, constraintAndPriviledge.getConsPrivId());
			}
			if (constraintAndPriviledge.getSysUserRoleId() != null) {
				ps.setString(index++, constraintAndPriviledge.getSysUserRoleId());
			}
			if (constraintAndPriviledge.getSysUserId() != null) {
				ps.setString(index++, constraintAndPriviledge.getSysUserId());
			}
			if (constraintAndPriviledge.getFuncNodeId() != null) {
				ps.setString(index++, constraintAndPriviledge.getFuncNodeId());
			}
			if (constraintAndPriviledge.getFlag() != null) {
				ps.setString(index++, constraintAndPriviledge.getFlag());
			}
			if (constraintAndPriviledge.getRangeId() != null) {
				ps.setString(index++, constraintAndPriviledge.getRangeId());
			}
			if (constraintAndPriviledge.getCreateDate() != null) {
				ps.setDate(index++, constraintAndPriviledge.getCreateDate());
			}
			if (constraintAndPriviledge.getSts() != null) {
				ps.setString(index++, constraintAndPriviledge.getSts());
			}
			if (constraintAndPriviledge.getStsDate() != null) {
				ps.setDate(index++, constraintAndPriviledge.getStsDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, ConstraintAndPriviledgeSVO.class);
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
