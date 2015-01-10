package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IVirtualTeamSDAO;
import com.cattsoft.sm.vo.*;

public class VirtualTeamSDAOImpl implements IVirtualTeamSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		VirtualTeamSVO virtualTeam = (VirtualTeamSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" VIRTUAL_TEAM(VIRTUAL_TEAM_ID,PARTY_ID,DEPT_ID,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, virtualTeam.getVirtualTeamId());
			ps.setString(2, virtualTeam.getPartyId());
			ps.setString(3, virtualTeam.getDeptId());
			ps.setString(4, virtualTeam.getSts());
			ps.setDate(5, virtualTeam.getStsDate());
			ps.setDate(6, virtualTeam.getCreateDate());
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
		VirtualTeamSVO virtualTeam = (VirtualTeamSVO) vo;
		StringBuffer sql = new StringBuffer("update VIRTUAL_TEAM set");
		if (virtualTeam.getPartyId() != null) {
			sql.append(" PARTY_ID=?,");
		}
		if (virtualTeam.getDeptId() != null) {
			sql.append(" DEPT_ID=?,");
		}
		if (virtualTeam.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (virtualTeam.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (virtualTeam.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and VIRTUAL_TEAM_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (virtualTeam.getPartyId() != null) {
				ps.setString(index++, virtualTeam.getPartyId());
			}
			if (virtualTeam.getDeptId() != null) {
				ps.setString(index++, virtualTeam.getDeptId());
			}
			if (virtualTeam.getSts() != null) {
				ps.setString(index++, virtualTeam.getSts());
			}
			if (virtualTeam.getStsDate() != null) {
				ps.setDate(index++, virtualTeam.getStsDate());
			}
			if (virtualTeam.getCreateDate() != null) {
				ps.setDate(index++, virtualTeam.getCreateDate());
			}
			ps.setString(index++, virtualTeam.getVirtualTeamId());
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
		VirtualTeamSVO virtualTeam = (VirtualTeamSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from VIRTUAL_TEAM where 1=1");
		sql.append(" and VIRTUAL_TEAM_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, virtualTeam.getVirtualTeamId());
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
		VirtualTeamSVO result = null;
		VirtualTeamSVO virtualTeam = (VirtualTeamSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.VIRTUAL_TEAM_ID,a.PARTY_ID,a.DEPT_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from VIRTUAL_TEAM a where 1=1");
		sql.append(" and VIRTUAL_TEAM_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, virtualTeam.getVirtualTeamId());
			rs = ps.executeQuery();
			result = (VirtualTeamSVO) ResultSetUtil.convertToVo(rs, VirtualTeamSVO.class);
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
		VirtualTeamSVO virtualTeam = (VirtualTeamSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.VIRTUAL_TEAM_ID,a.PARTY_ID,a.DEPT_ID,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from VIRTUAL_TEAM a where 1=1");
		if (virtualTeam.getVirtualTeamId() != null) {
			sql.append(" and VIRTUAL_TEAM_ID=?");
		}
		if (virtualTeam.getPartyId() != null) {
			sql.append(" and PARTY_ID=?");
		}
		if (virtualTeam.getDeptId() != null) {
			sql.append(" and DEPT_ID=?");
		}
		if (virtualTeam.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (virtualTeam.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (virtualTeam.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (virtualTeam.getVirtualTeamId() != null) {
				ps.setString(index++, virtualTeam.getVirtualTeamId());
			}
			if (virtualTeam.getPartyId() != null) {
				ps.setString(index++, virtualTeam.getPartyId());
			}
			if (virtualTeam.getDeptId() != null) {
				ps.setString(index++, virtualTeam.getDeptId());
			}
			if (virtualTeam.getSts() != null) {
				ps.setString(index++, virtualTeam.getSts());
			}
			if (virtualTeam.getStsDate() != null) {
				ps.setDate(index++, virtualTeam.getStsDate());
			}
			if (virtualTeam.getCreateDate() != null) {
				ps.setDate(index++, virtualTeam.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, VirtualTeamSVO.class);
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
