package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IPartyRoleTypeSDAO;
import com.cattsoft.sm.vo.*;

public class PartyRoleTypeSDAOImpl implements IPartyRoleTypeSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		PartyRoleTypeSVO partyRoleType = (PartyRoleTypeSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" PARTY_ROLE_TYPE(PARTY_ROLE_TYPE_ID,NAME,TABLE_NAME,AUTH_FLAG,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, partyRoleType.getPartyRoleTypeId());
			ps.setString(2, partyRoleType.getName());
			ps.setString(3, partyRoleType.getTableName());
			ps.setString(4, partyRoleType.getAuthFlag());
			ps.setString(5, partyRoleType.getSts());
			ps.setDate(6, partyRoleType.getStsDate());
			ps.setDate(7, partyRoleType.getCreateDate());
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
		PartyRoleTypeSVO partyRoleType = (PartyRoleTypeSVO) vo;
		StringBuffer sql = new StringBuffer("update PARTY_ROLE_TYPE set");
		if (partyRoleType.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (partyRoleType.getTableName() != null) {
			sql.append(" TABLE_NAME=?,");
		}
		if (partyRoleType.getAuthFlag() != null) {
			sql.append(" AUTH_FLAG=?,");
		}
		if (partyRoleType.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (partyRoleType.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (partyRoleType.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and PARTY_ROLE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (partyRoleType.getName() != null) {
				ps.setString(index++, partyRoleType.getName());
			}
			if (partyRoleType.getTableName() != null) {
				ps.setString(index++, partyRoleType.getTableName());
			}
			if (partyRoleType.getAuthFlag() != null) {
				ps.setString(index++, partyRoleType.getAuthFlag());
			}
			if (partyRoleType.getSts() != null) {
				ps.setString(index++, partyRoleType.getSts());
			}
			if (partyRoleType.getStsDate() != null) {
				ps.setDate(index++, partyRoleType.getStsDate());
			}
			if (partyRoleType.getCreateDate() != null) {
				ps.setDate(index++, partyRoleType.getCreateDate());
			}
			ps.setString(index++, partyRoleType.getPartyRoleTypeId());
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
		PartyRoleTypeSVO partyRoleType = (PartyRoleTypeSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from PARTY_ROLE_TYPE where 1=1");
		sql.append(" and PARTY_ROLE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, partyRoleType.getPartyRoleTypeId());
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
		PartyRoleTypeSVO result = null;
		PartyRoleTypeSVO partyRoleType = (PartyRoleTypeSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.PARTY_ROLE_TYPE_ID,a.NAME,a.TABLE_NAME,a.AUTH_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from PARTY_ROLE_TYPE a where 1=1");
		sql.append(" and PARTY_ROLE_TYPE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, partyRoleType.getPartyRoleTypeId());
			rs = ps.executeQuery();
			result = (PartyRoleTypeSVO) ResultSetUtil.convertToVo(rs, PartyRoleTypeSVO.class);
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
		PartyRoleTypeSVO partyRoleType = (PartyRoleTypeSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.PARTY_ROLE_TYPE_ID,a.NAME,a.TABLE_NAME,a.AUTH_FLAG,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from PARTY_ROLE_TYPE a where 1=1");
		if (partyRoleType.getPartyRoleTypeId() != null) {
			sql.append(" and PARTY_ROLE_TYPE_ID=?");
		}
		if (partyRoleType.getName() != null) {
			sql.append(" and NAME=?");
		}
		if (partyRoleType.getTableName() != null) {
			sql.append(" and TABLE_NAME=?");
		}
		if (partyRoleType.getAuthFlag() != null) {
			sql.append(" and AUTH_FLAG=?");
		}
		if (partyRoleType.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (partyRoleType.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (partyRoleType.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (partyRoleType.getPartyRoleTypeId() != null) {
				ps.setString(index++, partyRoleType.getPartyRoleTypeId());
			}
			if (partyRoleType.getName() != null) {
				ps.setString(index++, partyRoleType.getName());
			}
			if (partyRoleType.getTableName() != null) {
				ps.setString(index++, partyRoleType.getTableName());
			}
			if (partyRoleType.getAuthFlag() != null) {
				ps.setString(index++, partyRoleType.getAuthFlag());
			}
			if (partyRoleType.getSts() != null) {
				ps.setString(index++, partyRoleType.getSts());
			}
			if (partyRoleType.getStsDate() != null) {
				ps.setDate(index++, partyRoleType.getStsDate());
			}
			if (partyRoleType.getCreateDate() != null) {
				ps.setDate(index++, partyRoleType.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, PartyRoleTypeSVO.class);
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
