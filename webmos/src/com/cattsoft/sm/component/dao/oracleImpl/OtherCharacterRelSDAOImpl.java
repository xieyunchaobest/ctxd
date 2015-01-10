package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IOtherCharacterRelSDAO;
import com.cattsoft.sm.vo.OtherCharacterRelSVO;

public class OtherCharacterRelSDAOImpl implements IOtherCharacterRelSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		OtherCharacterRelSVO otherCharacterRel = (OtherCharacterRelSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" OTHER_CHARACTER_REL(CHARACTER_REL_ID,CHARACTERISTIC_ID,REL_CHARACTERISTIC_ID,REL_TYPE,OTH_CHARACTERISTIC_ID,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, otherCharacterRel.getCharacterRelId());
			ps.setString(2, otherCharacterRel.getCharacteristicId());
			ps.setString(3, otherCharacterRel.getRelCharacteristicId());
			ps.setString(4, otherCharacterRel.getRelType());
			ps.setString(5, otherCharacterRel.getOthCharacteristicId());
			ps.setString(6, otherCharacterRel.getSts());
			ps.setTimestamp(7, otherCharacterRel.getStsDate());
			ps.setTimestamp(8, otherCharacterRel.getCreateDate());
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
		OtherCharacterRelSVO otherCharacterRel = (OtherCharacterRelSVO) vo;
		StringBuffer sql = new StringBuffer("update OTHER_CHARACTER_REL set");
		if (otherCharacterRel.getCharacteristicId() != null) {
			sql.append(" CHARACTERISTIC_ID=?,");
		}
		if (otherCharacterRel.getRelCharacteristicId() != null) {
			sql.append(" REL_CHARACTERISTIC_ID=?,");
		}
		if (otherCharacterRel.getRelType() != null) {
			sql.append(" REL_TYPE=?,");
		}
		if (otherCharacterRel.getOthCharacteristicId() != null) {
			sql.append(" OTH_CHARACTERISTIC_ID=?,");
		}
		if (otherCharacterRel.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (otherCharacterRel.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (otherCharacterRel.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and CHARACTER_REL_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (otherCharacterRel.getCharacteristicId() != null) {
				ps.setString(index++, otherCharacterRel.getCharacteristicId());
			}
			if (otherCharacterRel.getRelCharacteristicId() != null) {
				ps.setString(index++, otherCharacterRel.getRelCharacteristicId());
			}
			if (otherCharacterRel.getRelType() != null) {
				ps.setString(index++, otherCharacterRel.getRelType());
			}
			if (otherCharacterRel.getOthCharacteristicId() != null) {
				ps.setString(index++, otherCharacterRel.getOthCharacteristicId());
			}
			if (otherCharacterRel.getSts() != null) {
				ps.setString(index++, otherCharacterRel.getSts());
			}
			if (otherCharacterRel.getStsDate() != null) {
				ps.setTimestamp(index++, otherCharacterRel.getStsDate());
			}
			if (otherCharacterRel.getCreateDate() != null) {
				ps.setTimestamp(index++, otherCharacterRel.getCreateDate());
			}
			ps.setString(index++, otherCharacterRel.getCharacterRelId());
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
		OtherCharacterRelSVO otherCharacterRel = (OtherCharacterRelSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from OTHER_CHARACTER_REL where 1=1");
		sql.append(" and CHARACTER_REL_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, otherCharacterRel.getCharacterRelId());
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
		OtherCharacterRelSVO result = null;
		OtherCharacterRelSVO otherCharacterRel = (OtherCharacterRelSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.CHARACTER_REL_ID,a.CHARACTERISTIC_ID,a.REL_CHARACTERISTIC_ID,a.REL_TYPE,a.OTH_CHARACTERISTIC_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from OTHER_CHARACTER_REL a where 1=1");
		sql.append(" and CHARACTER_REL_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, otherCharacterRel.getCharacterRelId());
			rs = ps.executeQuery();
			result = (OtherCharacterRelSVO) ResultSetUtil.convertToVo(rs, OtherCharacterRelSVO.class);
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
		OtherCharacterRelSVO otherCharacterRel = (OtherCharacterRelSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.CHARACTER_REL_ID,a.CHARACTERISTIC_ID,a.REL_CHARACTERISTIC_ID,a.REL_TYPE,a.OTH_CHARACTERISTIC_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from OTHER_CHARACTER_REL a where 1=1");
		if (otherCharacterRel.getCharacterRelId() != null) {
			sql.append(" and CHARACTER_REL_ID=?");
		}
		if (otherCharacterRel.getCharacteristicId() != null) {
			sql.append(" and CHARACTERISTIC_ID=?");
		}
		if (otherCharacterRel.getRelCharacteristicId() != null) {
			sql.append(" and REL_CHARACTERISTIC_ID=?");
		}
		if (otherCharacterRel.getRelType() != null) {
			sql.append(" and REL_TYPE=?");
		}
		if (otherCharacterRel.getOthCharacteristicId() != null) {
			sql.append(" and OTH_CHARACTERISTIC_ID=?");
		}
		if (otherCharacterRel.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (otherCharacterRel.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (otherCharacterRel.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (otherCharacterRel.getCharacterRelId() != null) {
				ps.setString(index++, otherCharacterRel.getCharacterRelId());
			}
			if (otherCharacterRel.getCharacteristicId() != null) {
				ps.setString(index++, otherCharacterRel.getCharacteristicId());
			}
			if (otherCharacterRel.getRelCharacteristicId() != null) {
				ps.setString(index++, otherCharacterRel.getRelCharacteristicId());
			}
			if (otherCharacterRel.getRelType() != null) {
				ps.setString(index++, otherCharacterRel.getRelType());
			}
			if (otherCharacterRel.getOthCharacteristicId() != null) {
				ps.setString(index++, otherCharacterRel.getOthCharacteristicId());
			}
			if (otherCharacterRel.getSts() != null) {
				ps.setString(index++, otherCharacterRel.getSts());
			}
			if (otherCharacterRel.getStsDate() != null) {
				ps.setTimestamp(index++, otherCharacterRel.getStsDate());
			}
			if (otherCharacterRel.getCreateDate() != null) {
				ps.setTimestamp(index++, otherCharacterRel.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, OtherCharacterRelSVO.class);
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
