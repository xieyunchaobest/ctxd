package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IStaffWorkAreaSDAO;
import com.cattsoft.sm.vo.*;


public class StaffWorkAreaSDAOImpl implements IStaffWorkAreaSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		StaffWorkAreaSVO staffWorkArea = (StaffWorkAreaSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" STAFF_WORK_AREA(STAFF_WORK_AREA_ID,STAFF_ID,WORK_AREA_ID,GRANTOR,ADMIN_FLAG,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, staffWorkArea.getStaffWorkAreaId());
			ps.setString(2, staffWorkArea.getStaffId());
			ps.setString(3, staffWorkArea.getWorkAreaId());
			ps.setString(4, staffWorkArea.getGrantor());
			ps.setString(5, staffWorkArea.getAdminFlag());
			ps.setString(6, staffWorkArea.getSts());
			ps.setDate(7, staffWorkArea.getStsDate());
			ps.setDate(8, staffWorkArea.getCreateDate());
			ps.execute();
			connection.commit();
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
		StaffWorkAreaSVO staffWorkArea = (StaffWorkAreaSVO) vo;
		StringBuffer sql = new StringBuffer("update STAFF_WORK_AREA set");
		if (staffWorkArea.getStaffId() != null) {
			sql.append(" STAFF_ID=?,");
		}
		if (staffWorkArea.getWorkAreaId() != null) {
			sql.append(" WORK_AREA_ID=?,");
		}
		if (staffWorkArea.getGrantor() != null) {
			sql.append(" GRANTOR=?,");
		}
		if (staffWorkArea.getAdminFlag() != null) {
			sql.append(" ADMIN_FLAG=?,");
		}
		if (staffWorkArea.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (staffWorkArea.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (staffWorkArea.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and STAFF_WORK_AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (staffWorkArea.getStaffId() != null) {
				ps.setString(index++, staffWorkArea.getStaffId());
			}
			if (staffWorkArea.getWorkAreaId() != null) {
				ps.setString(index++, staffWorkArea.getWorkAreaId());
			}
			if (staffWorkArea.getGrantor() != null) {
				ps.setString(index++, staffWorkArea.getGrantor());
			}
			if (staffWorkArea.getAdminFlag() != null) {
				ps.setString(index++, staffWorkArea.getAdminFlag());
			}
			if (staffWorkArea.getSts() != null) {
				ps.setString(index++, staffWorkArea.getSts());
			}
			if (staffWorkArea.getStsDate() != null) {
				ps.setDate(index++, staffWorkArea.getStsDate());
			}
			if (staffWorkArea.getCreateDate() != null) {
				ps.setDate(index++, staffWorkArea.getCreateDate());
			}
			ps.setString(index++, staffWorkArea.getStaffWorkAreaId());
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
		StaffWorkAreaSVO staffWorkArea = (StaffWorkAreaSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from STAFF_WORK_AREA where 1=1");
		sql.append(" and STAFF_WORK_AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, staffWorkArea.getStaffWorkAreaId());
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
		StaffWorkAreaSVO result = null;
		StaffWorkAreaSVO staffWorkArea = (StaffWorkAreaSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.STAFF_WORK_AREA_ID,a.STAFF_ID,a.WORK_AREA_ID,a.GRANTOR,a.ADMIN_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from STAFF_WORK_AREA a where 1=1");
		sql.append(" and STAFF_WORK_AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, staffWorkArea.getStaffWorkAreaId());
			rs = ps.executeQuery();
			result = (StaffWorkAreaSVO) ResultSetUtil.convertToVo(rs, StaffWorkAreaSVO.class);
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
		StaffWorkAreaSVO staffWorkArea = (StaffWorkAreaSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.STAFF_WORK_AREA_ID,a.STAFF_ID,a.WORK_AREA_ID,a.GRANTOR,a.ADMIN_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from STAFF_WORK_AREA a where 1=1");
		if (staffWorkArea.getStaffWorkAreaId() != null) {
			sql.append(" and STAFF_WORK_AREA_ID=?");
		}
		if (staffWorkArea.getStaffId() != null) {
			sql.append(" and STAFF_ID=?");
		}
		if (staffWorkArea.getWorkAreaId() != null) {
			sql.append(" and WORK_AREA_ID=?");
		}
		if (staffWorkArea.getGrantor() != null) {
			sql.append(" and GRANTOR=?");
		}
		if (staffWorkArea.getAdminFlag() != null) {
			sql.append(" and ADMIN_FLAG=?");
		}
		if (staffWorkArea.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (staffWorkArea.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (staffWorkArea.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (staffWorkArea.getStaffWorkAreaId() != null) {
				ps.setString(index++, staffWorkArea.getStaffWorkAreaId());
			}
			if (staffWorkArea.getStaffId() != null) {
				ps.setString(index++, staffWorkArea.getStaffId());
			}
			if (staffWorkArea.getWorkAreaId() != null) {
				ps.setString(index++, staffWorkArea.getWorkAreaId());
			}
			if (staffWorkArea.getGrantor() != null) {
				ps.setString(index++, staffWorkArea.getGrantor());
			}
			if (staffWorkArea.getAdminFlag() != null) {
				ps.setString(index++, staffWorkArea.getAdminFlag());
			}
			if (staffWorkArea.getSts() != null) {
				ps.setString(index++, staffWorkArea.getSts());
			}
			if (staffWorkArea.getStsDate() != null) {
				ps.setDate(index++, staffWorkArea.getStsDate());
			}
			if (staffWorkArea.getCreateDate() != null) {
				ps.setDate(index++, staffWorkArea.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, StaffWorkAreaSVO.class);
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
