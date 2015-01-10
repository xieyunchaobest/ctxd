package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IProvinceSDAO;
import com.cattsoft.sm.vo.*;

public class ProvinceSDAOImpl implements IProvinceSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		
		ProvinceSVO province = (ProvinceSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" PROVINCE(PROV_ID,NAME) values(?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, province.getProvId());
			ps.setString(2, province.getName());
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
		ProvinceSVO province = (ProvinceSVO) vo;
		StringBuffer sql = new StringBuffer("update PROVINCE set");
		if (province.getName() != null) {
			sql.append(" NAME=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and PROV_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (province.getName() != null) {
				ps.setString(index++, province.getName());
			}
			ps.setString(index++, province.getProvId());
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
		ProvinceSVO province = (ProvinceSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from PROVINCE where 1=1");
		sql.append(" and PROV_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, province.getProvId());
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
		ProvinceSVO result = null;
		ProvinceSVO province = (ProvinceSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.PROV_ID,a.NAME");
		sql.append(" from PROVINCE a where 1=1");
		sql.append(" and PROV_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, province.getProvId());
			rs = ps.executeQuery();
			result = (ProvinceSVO) ResultSetUtil.convertToVo(rs, ProvinceSVO.class);
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
		ProvinceSVO province = (ProvinceSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.PROV_ID,a.NAME");
		sql.append(" from PROVINCE a where 1=1");
		if (province.getProvId() != null) {
			sql.append(" and PROV_ID=?");
		}
		if (province.getName() != null) {
			sql.append(" and NAME=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (province.getProvId() != null) {
				ps.setString(index++, province.getProvId());
			}
			if (province.getName() != null) {
				ps.setString(index++, province.getName());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, ProvinceSVO.class);
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
