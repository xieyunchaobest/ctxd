package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IUnitSDAO;
import com.cattsoft.sm.vo.*;

public class UnitSDAOImpl implements IUnitSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		UnitSVO unit = (UnitSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" UNIT(UNIT_ID,UNIT_TYPE_ID,UNIT_NAME,STANDARD_CONVERT_RATE) values(?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, unit.getUnitId());
			ps.setString(2, unit.getUnitTypeId());
			ps.setString(3, unit.getUnitName());
			ps.setString(4, unit.getStandardConvertRate());
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
		UnitSVO unit = (UnitSVO) vo;
		StringBuffer sql = new StringBuffer("update UNIT set");
		if (unit.getUnitTypeId() != null) {
			sql.append(" UNIT_TYPE_ID=?,");
		}
		if (unit.getUnitName() != null) {
			sql.append(" UNIT_NAME=?,");
		}
		if (unit.getStandardConvertRate() != null) {
			sql.append(" STANDARD_CONVERT_RATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and UNIT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (unit.getUnitTypeId() != null) {
				ps.setString(index++, unit.getUnitTypeId());
			}
			if (unit.getUnitName() != null) {
				ps.setString(index++, unit.getUnitName());
			}
			if (unit.getStandardConvertRate() != null) {
				ps.setString(index++, unit.getStandardConvertRate());
			}
			ps.setString(index++, unit.getUnitId());
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
		UnitSVO unit = (UnitSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from UNIT where 1=1");
		sql.append(" and UNIT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, unit.getUnitId());
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
		UnitSVO result = null;
		UnitSVO unit = (UnitSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.UNIT_ID,a.UNIT_TYPE_ID,a.UNIT_NAME,a.STANDARD_CONVERT_RATE");
		sql.append(" from UNIT a where 1=1");
		sql.append(" and UNIT_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, unit.getUnitId());
			rs = ps.executeQuery();
			result = (UnitSVO) ResultSetUtil.convertToVo(rs, UnitSVO.class);
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
		UnitSVO unit = (UnitSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.UNIT_ID,a.UNIT_TYPE_ID,a.UNIT_NAME,a.STANDARD_CONVERT_RATE");
		sql.append(" from UNIT a where 1=1");
		if (unit.getUnitId() != null) {
			sql.append(" and UNIT_ID=?");
		}
		if (unit.getUnitTypeId() != null) {
			sql.append(" and UNIT_TYPE_ID=?");
		}
		if (unit.getUnitName() != null) {
			sql.append(" and UNIT_NAME=?");
		}
		if (unit.getStandardConvertRate() != null) {
			sql.append(" and STANDARD_CONVERT_RATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (unit.getUnitId() != null) {
				ps.setString(index++, unit.getUnitId());
			}
			if (unit.getUnitTypeId() != null) {
				ps.setString(index++, unit.getUnitTypeId());
			}
			if (unit.getUnitName() != null) {
				ps.setString(index++, unit.getUnitName());
			}
			if (unit.getStandardConvertRate() != null) {
				ps.setString(index++, unit.getStandardConvertRate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, UnitSVO.class);
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
