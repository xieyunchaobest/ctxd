package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IDataRangeSDAO;
import com.cattsoft.sm.vo.*;

public class DataRangeSDAOImpl implements IDataRangeSDAO {
	

	public void add(GenericVO vo) throws AppException, SysException {
		DataRangeSVO dataRange = (DataRangeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DATA_RANGE(DATA_RANGE_ID,RANGE_NAME,CREATE_FLAG,LOCAL_NET_ID,AREA_ID,CREATE_DATE,STS,STS_DATE) values(?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRange.getDataRangeId());
			ps.setString(2, dataRange.getRangeName());
			ps.setString(3, dataRange.getCreateFlag());
			ps.setString(4, dataRange.getLocalNetId());
			ps.setString(5, dataRange.getAreaId());
			ps.setDate(6, dataRange.getCreateDate());
			ps.setString(7, dataRange.getSts());
			ps.setDate(8, dataRange.getStsDate());
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
		DataRangeSVO dataRange = (DataRangeSVO) vo;
		StringBuffer sql = new StringBuffer("update DATA_RANGE set");
		if (dataRange.getRangeName() != null) {
			sql.append(" RANGE_NAME=?,");
		}
		if (dataRange.getCreateFlag() != null) {
			sql.append(" CREATE_FLAG=?,");
		}
		if (dataRange.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (dataRange.getAreaId() != null) {
			sql.append(" AREA_ID=?,");
		}
		if (dataRange.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (dataRange.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (dataRange.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DATA_RANGE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (dataRange.getRangeName() != null) {
				ps.setString(index++, dataRange.getRangeName());
			}
			if (dataRange.getCreateFlag() != null) {
				ps.setString(index++, dataRange.getCreateFlag());
			}
			if (dataRange.getLocalNetId() != null) {
				ps.setString(index++, dataRange.getLocalNetId());
			}
			if (dataRange.getAreaId() != null) {
				ps.setString(index++, dataRange.getAreaId());
			}
			if (dataRange.getCreateDate() != null) {
				ps.setDate(index++, dataRange.getCreateDate());
			}
			if (dataRange.getSts() != null) {
				ps.setString(index++, dataRange.getSts());
			}
			if (dataRange.getStsDate() != null) {
				ps.setDate(index++, dataRange.getStsDate());
			}
			ps.setString(index++, dataRange.getDataRangeId());
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
		DataRangeSVO dataRange = (DataRangeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DATA_RANGE where 1=1");
		sql.append(" and DATA_RANGE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRange.getDataRangeId());
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
		DataRangeSVO result = null;
		DataRangeSVO dataRange = (DataRangeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DATA_RANGE_ID,a.RANGE_NAME,a.CREATE_FLAG,a.LOCAL_NET_ID,a.AREA_ID,a.CREATE_DATE,a.STS,a.STS_DATE");
		sql.append(" from DATA_RANGE a where 1=1");
		sql.append(" and DATA_RANGE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, dataRange.getDataRangeId());
			rs = ps.executeQuery();
			result = (DataRangeSVO) ResultSetUtil.convertToVo(rs, DataRangeSVO.class);
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
		DataRangeSVO dataRange = (DataRangeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DATA_RANGE_ID,a.RANGE_NAME,a.CREATE_FLAG,a.LOCAL_NET_ID,a.AREA_ID,a.CREATE_DATE,a.STS,a.STS_DATE");
		sql.append(" from DATA_RANGE a where 1=1");
		if (dataRange.getDataRangeId() != null) {
			sql.append(" and DATA_RANGE_ID=?");
		}
		if (dataRange.getRangeName() != null) {
			sql.append(" and RANGE_NAME=?");
		}
		if (dataRange.getCreateFlag() != null) {
			sql.append(" and CREATE_FLAG=?");
		}
		if (dataRange.getLocalNetId() != null) {
			sql.append(" and LOCAL_NET_ID=?");
		}
		if (dataRange.getAreaId() != null) {
			sql.append(" and AREA_ID=?");
		}
		if (dataRange.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (dataRange.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (dataRange.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (dataRange.getDataRangeId() != null) {
				ps.setString(index++, dataRange.getDataRangeId());
			}
			if (dataRange.getRangeName() != null) {
				ps.setString(index++, dataRange.getRangeName());
			}
			if (dataRange.getCreateFlag() != null) {
				ps.setString(index++, dataRange.getCreateFlag());
			}
			if (dataRange.getLocalNetId() != null) {
				ps.setString(index++, dataRange.getLocalNetId());
			}
			if (dataRange.getAreaId() != null) {
				ps.setString(index++, dataRange.getAreaId());
			}
			if (dataRange.getCreateDate() != null) {
				ps.setDate(index++, dataRange.getCreateDate());
			}
			if (dataRange.getSts() != null) {
				ps.setString(index++, dataRange.getSts());
			}
			if (dataRange.getStsDate() != null) {
				ps.setDate(index++, dataRange.getStsDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DataRangeSVO.class);
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
