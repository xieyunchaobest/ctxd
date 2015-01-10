package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IDistrictLevelSDAO;
import com.cattsoft.sm.vo.*;


public class DistrictLevelSDAOImpl implements IDistrictLevelSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		DistrictLevelSVO districtLevel = (DistrictLevelSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" DISTRICT_LEVEL(DISTRICT_LEVEL_ID,NAME,PARENT_DISTRICT_LEVEL_ID,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, districtLevel.getDistrictLevelId());
			ps.setString(2, districtLevel.getName());
			ps.setString(3, districtLevel.getParentDistrictLevelId());
			ps.setString(4, districtLevel.getSts());
			ps.setDate(5, districtLevel.getStsDate());
			ps.setDate(6, districtLevel.getCreateDate());
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
		DistrictLevelSVO districtLevel = (DistrictLevelSVO) vo;
		StringBuffer sql = new StringBuffer("update DISTRICT_LEVEL set");
		if (districtLevel.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (districtLevel.getParentDistrictLevelId() != null) {
			sql.append(" PARENT_DISTRICT_LEVEL_ID=?,");
		}
		if (districtLevel.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (districtLevel.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (districtLevel.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and DISTRICT_LEVEL_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (districtLevel.getName() != null) {
				ps.setString(index++, districtLevel.getName());
			}
			if (districtLevel.getParentDistrictLevelId() != null) {
				ps.setString(index++, districtLevel.getParentDistrictLevelId());
			}
			if (districtLevel.getSts() != null) {
				ps.setString(index++, districtLevel.getSts());
			}
			if (districtLevel.getStsDate() != null) {
				ps.setDate(index++, districtLevel.getStsDate());
			}
			if (districtLevel.getCreateDate() != null) {
				ps.setDate(index++, districtLevel.getCreateDate());
			}
			ps.setString(index++, districtLevel.getDistrictLevelId());
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
		DistrictLevelSVO districtLevel = (DistrictLevelSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from DISTRICT_LEVEL where 1=1");
		sql.append(" and DISTRICT_LEVEL_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, districtLevel.getDistrictLevelId());
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
		DistrictLevelSVO result = null;
		DistrictLevelSVO districtLevel = (DistrictLevelSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.DISTRICT_LEVEL_ID,a.NAME,a.PARENT_DISTRICT_LEVEL_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from DISTRICT_LEVEL a where 1=1");
		sql.append(" and DISTRICT_LEVEL_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, districtLevel.getDistrictLevelId());
			rs = ps.executeQuery();
			result = (DistrictLevelSVO) ResultSetUtil.convertToVo(rs, DistrictLevelSVO.class);
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
		DistrictLevelSVO districtLevel = (DistrictLevelSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.DISTRICT_LEVEL_ID,a.NAME,a.PARENT_DISTRICT_LEVEL_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from DISTRICT_LEVEL a where 1=1");
		if (districtLevel.getDistrictLevelId() != null) {
			sql.append(" and DISTRICT_LEVEL_ID=?");
		}
		if (districtLevel.getName() != null) {
			sql.append(" and NAME=?");
		}
		if (districtLevel.getParentDistrictLevelId() != null) {
			sql.append(" and PARENT_DISTRICT_LEVEL_ID=?");
		}
		if (districtLevel.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (districtLevel.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (districtLevel.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (districtLevel.getDistrictLevelId() != null) {
				ps.setString(index++, districtLevel.getDistrictLevelId());
			}
			if (districtLevel.getName() != null) {
				ps.setString(index++, districtLevel.getName());
			}
			if (districtLevel.getParentDistrictLevelId() != null) {
				ps.setString(index++, districtLevel.getParentDistrictLevelId());
			}
			if (districtLevel.getSts() != null) {
				ps.setString(index++, districtLevel.getSts());
			}
			if (districtLevel.getStsDate() != null) {
				ps.setDate(index++, districtLevel.getStsDate());
			}
			if (districtLevel.getCreateDate() != null) {
				ps.setDate(index++, districtLevel.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, DistrictLevelSVO.class);
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
