package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IFuncRangeTypeSDAO;
import com.cattsoft.sm.vo.*;

public class FuncRangeTypeSDAOImpl implements IFuncRangeTypeSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		FuncRangeTypeSVO funcRangeType = (FuncRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" FUNC_RANGE_TYPE(FUNC_RANGE_TYPE_ID,FUNC_NODE_ID,DATA_RANGE_TYPE_ID,COLUMN_NAME,VERSION) values(?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcRangeType.getFuncRangeTypeId());
			ps.setString(2, funcRangeType.getFuncNodeId());
			ps.setString(3, funcRangeType.getDataRangeTypeId());
			ps.setString(4, funcRangeType.getColumnName());
			ps.setString(5, funcRangeType.getVersion());
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
		FuncRangeTypeSVO funcRangeType = (FuncRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer("update FUNC_RANGE_TYPE set");
		if (funcRangeType.getFuncNodeId() != null) {
			sql.append(" FUNC_NODE_ID=?,");
		}
		if (funcRangeType.getDataRangeTypeId() != null) {
			sql.append(" DATA_RANGE_TYPE_ID=?,");
		}
		if (funcRangeType.getColumnName() != null) {
			sql.append(" COLUMN_NAME=?,");
		}
		if (funcRangeType.getVersion() != null) {
			sql.append(" VERSION=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and FUNC_RANGE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (funcRangeType.getFuncNodeId() != null) {
				ps.setString(index++, funcRangeType.getFuncNodeId());
			}
			if (funcRangeType.getDataRangeTypeId() != null) {
				ps.setString(index++, funcRangeType.getDataRangeTypeId());
			}
			if (funcRangeType.getColumnName() != null) {
				ps.setString(index++, funcRangeType.getColumnName());
			}
			if (funcRangeType.getVersion() != null) {
				ps.setString(index++, funcRangeType.getVersion());
			}
			ps.setString(index++, funcRangeType.getFuncRangeTypeId());
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
		FuncRangeTypeSVO funcRangeType = (FuncRangeTypeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from FUNC_RANGE_TYPE where 1=1");
		sql.append(" and FUNC_RANGE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcRangeType.getFuncRangeTypeId());
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
		FuncRangeTypeSVO result = null;
		FuncRangeTypeSVO funcRangeType = (FuncRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.FUNC_RANGE_TYPE_ID,a.FUNC_NODE_ID,a.DATA_RANGE_TYPE_ID,a.COLUMN_NAME,a.VERSION");
		sql.append(" from FUNC_RANGE_TYPE a where 1=1");
		sql.append(" and FUNC_RANGE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, funcRangeType.getFuncRangeTypeId());
			rs = ps.executeQuery();
			result = (FuncRangeTypeSVO) ResultSetUtil.convertToVo(rs, FuncRangeTypeSVO.class);
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
		FuncRangeTypeSVO funcRangeType = (FuncRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.FUNC_RANGE_TYPE_ID,a.FUNC_NODE_ID,a.DATA_RANGE_TYPE_ID,a.COLUMN_NAME,a.VERSION");
		sql.append(" from FUNC_RANGE_TYPE a where 1=1");
		if (funcRangeType.getFuncRangeTypeId() != null) {
			sql.append(" and FUNC_RANGE_TYPE_ID=?");
		}
		if (funcRangeType.getFuncNodeId() != null) {
			sql.append(" and FUNC_NODE_ID=?");
		}
		if (funcRangeType.getDataRangeTypeId() != null) {
			sql.append(" and DATA_RANGE_TYPE_ID=?");
		}
		if (funcRangeType.getColumnName() != null) {
			sql.append(" and COLUMN_NAME=?");
		}
		if (funcRangeType.getVersion() != null) {
			sql.append(" and VERSION=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (funcRangeType.getFuncRangeTypeId() != null) {
				ps.setString(index++, funcRangeType.getFuncRangeTypeId());
			}
			if (funcRangeType.getFuncNodeId() != null) {
				ps.setString(index++, funcRangeType.getFuncNodeId());
			}
			if (funcRangeType.getDataRangeTypeId() != null) {
				ps.setString(index++, funcRangeType.getDataRangeTypeId());
			}
			if (funcRangeType.getColumnName() != null) {
				ps.setString(index++, funcRangeType.getColumnName());
			}
			if (funcRangeType.getVersion() != null) {
				ps.setString(index++, funcRangeType.getVersion());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, FuncRangeTypeSVO.class);
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
