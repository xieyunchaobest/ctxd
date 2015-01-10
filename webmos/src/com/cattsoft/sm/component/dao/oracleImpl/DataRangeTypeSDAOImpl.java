package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IDataRangeTypeSDAO;
import com.cattsoft.sm.vo.*;


public class DataRangeTypeSDAOImpl implements IDataRangeTypeSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		DataRangeTypeSVO dataRangeType = (DataRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DATA_RANGE_TYPE(DATA_RANGE_TYPE_ID,DATA_RANGE_TYPE_NAME,DATA_RANGE_PATTERN,SET_RANGE_COMP,VERSION) values(?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRangeType.getDataRangeTypeId());
			ps.setString(2, dataRangeType.getDataRangeTypeName());
			ps.setString(3, dataRangeType.getDataRangePattern());
			ps.setString(4, dataRangeType.getSetRangeComp());
			ps.setString(5, dataRangeType.getVersion());
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
		DataRangeTypeSVO dataRangeType = (DataRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer("update DATA_RANGE_TYPE set");
		if (dataRangeType.getDataRangeTypeName() != null) {
			sql.append(" DATA_RANGE_TYPE_NAME=?,");
		}
		if (dataRangeType.getDataRangePattern() != null) {
			sql.append(" DATA_RANGE_PATTERN=?,");
		}
		if (dataRangeType.getSetRangeComp() != null) {
			sql.append(" SET_RANGE_COMP=?,");
		}
		if (dataRangeType.getVersion() != null) {
			sql.append(" VERSION=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DATA_RANGE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (dataRangeType.getDataRangeTypeName() != null) {
				ps.setString(index++, dataRangeType.getDataRangeTypeName());
			}
			if (dataRangeType.getDataRangePattern() != null) {
				ps.setString(index++, dataRangeType.getDataRangePattern());
			}
			if (dataRangeType.getSetRangeComp() != null) {
				ps.setString(index++, dataRangeType.getSetRangeComp());
			}
			if (dataRangeType.getVersion() != null) {
				ps.setString(index++, dataRangeType.getVersion());
			}
			ps.setString(index++, dataRangeType.getDataRangeTypeId());
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
		DataRangeTypeSVO dataRangeType = (DataRangeTypeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DATA_RANGE_TYPE where 1=1");
		sql.append(" and DATA_RANGE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRangeType.getDataRangeTypeId());
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
		DataRangeTypeSVO result = null;
		DataRangeTypeSVO dataRangeType = (DataRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DATA_RANGE_TYPE_ID,a.DATA_RANGE_TYPE_NAME,a.DATA_RANGE_PATTERN,a.SET_RANGE_COMP,a.VERSION");
		sql.append(" from DATA_RANGE_TYPE a where 1=1");
		sql.append(" and DATA_RANGE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRangeType.getDataRangeTypeId());
			rs = ps.executeQuery();
			result = (DataRangeTypeSVO) ResultSetUtil.convertToVo(rs, DataRangeTypeSVO.class);
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
		DataRangeTypeSVO dataRangeType = (DataRangeTypeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DATA_RANGE_TYPE_ID,a.DATA_RANGE_TYPE_NAME,a.DATA_RANGE_PATTERN,a.SET_RANGE_COMP,a.VERSION");
		sql.append(" from DATA_RANGE_TYPE a where 1=1");
		if (dataRangeType.getDataRangeTypeId() != null) {
			sql.append(" and DATA_RANGE_TYPE_ID=?");
		}
		if (dataRangeType.getDataRangeTypeName() != null) {
			sql.append(" and DATA_RANGE_TYPE_NAME=?");
		}
		if (dataRangeType.getDataRangePattern() != null) {
			sql.append(" and DATA_RANGE_PATTERN=?");
		}
		if (dataRangeType.getSetRangeComp() != null) {
			sql.append(" and SET_RANGE_COMP=?");
		}
		if (dataRangeType.getVersion() != null) {
			sql.append(" and VERSION=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (dataRangeType.getDataRangeTypeId() != null) {
				ps.setString(index++, dataRangeType.getDataRangeTypeId());
			}
			if (dataRangeType.getDataRangeTypeName() != null) {
				ps.setString(index++, dataRangeType.getDataRangeTypeName());
			}
			if (dataRangeType.getDataRangePattern() != null) {
				ps.setString(index++, dataRangeType.getDataRangePattern());
			}
			if (dataRangeType.getSetRangeComp() != null) {
				ps.setString(index++, dataRangeType.getSetRangeComp());
			}
			if (dataRangeType.getVersion() != null) {
				ps.setString(index++, dataRangeType.getVersion());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DataRangeTypeSVO.class);
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
