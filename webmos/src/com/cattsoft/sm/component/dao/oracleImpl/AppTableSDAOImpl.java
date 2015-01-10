package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IAppTableSDAO;
import com.cattsoft.sm.vo.*;

public class AppTableSDAOImpl implements IAppTableSDAO {
	public void add(GenericVO vo) throws AppException, SysException {
		AppTableSVO appTable = (AppTableSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" APP_TABLE(APP_TABLE_ID,ENG_NAME,CH_NAME,TABLE_COMMENT,DB_USER,LAST_MODI_DATE,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appTable.getAppTableId());
			ps.setString(2, appTable.getEngName());
			ps.setString(3, appTable.getChName());
			ps.setString(4, appTable.getTableComment());
			ps.setString(5, appTable.getDbUser());
			ps.setDate(6, appTable.getLastModiDate());
			ps.setString(7, appTable.getSts());
			ps.setDate(8, appTable.getStsDate());
			ps.setDate(9, appTable.getCreateDate());
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
		AppTableSVO appTable = (AppTableSVO) vo;
		StringBuffer sql = new StringBuffer("update APP_TABLE set");
		if (appTable.getEngName() != null) {
			sql.append(" ENG_NAME=?,");
		}
		if (appTable.getChName() != null) {
			sql.append(" CH_NAME=?,");
		}
		if (appTable.getTableComment() != null) {
			sql.append(" TABLE_COMMENT=?,");
		}
		if (appTable.getDbUser() != null) {
			sql.append(" DB_USER=?,");
		}
		if (appTable.getLastModiDate() != null) {
			sql.append(" LAST_MODI_DATE=?,");
		}
		if (appTable.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (appTable.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (appTable.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and APP_TABLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (appTable.getEngName() != null) {
				ps.setString(index++, appTable.getEngName());
			}
			if (appTable.getChName() != null) {
				ps.setString(index++, appTable.getChName());
			}
			if (appTable.getTableComment() != null) {
				ps.setString(index++, appTable.getTableComment());
			}
			if (appTable.getDbUser() != null) {
				ps.setString(index++, appTable.getDbUser());
			}
			if (appTable.getLastModiDate() != null) {
				ps.setDate(index++, appTable.getLastModiDate());
			}
			if (appTable.getSts() != null) {
				ps.setString(index++, appTable.getSts());
			}
			if (appTable.getStsDate() != null) {
				ps.setDate(index++, appTable.getStsDate());
			}
			if (appTable.getCreateDate() != null) {
				ps.setDate(index++, appTable.getCreateDate());
			}
			ps.setString(index++, appTable.getAppTableId());
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
		AppTableSVO appTable = (AppTableSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from APP_TABLE where 1=1");
		sql.append(" and APP_TABLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appTable.getAppTableId());
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
		AppTableSVO result = null;
		AppTableSVO appTable = (AppTableSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.APP_TABLE_ID,a.ENG_NAME,a.CH_NAME,a.TABLE_COMMENT,a.DB_USER,a.LAST_MODI_DATE,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from APP_TABLE a where 1=1");
		sql.append(" and APP_TABLE_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appTable.getAppTableId());
			rs = ps.executeQuery();
			result = (AppTableSVO) ResultSetUtil.convertToVo(rs, AppTableSVO.class);
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
		AppTableSVO appTable = (AppTableSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.APP_TABLE_ID,a.ENG_NAME,a.CH_NAME,a.TABLE_COMMENT,a.DB_USER,a.LAST_MODI_DATE,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from APP_TABLE a where 1=1");
		if (appTable.getAppTableId() != null) {
			sql.append(" and APP_TABLE_ID=?");
		}
		if (appTable.getEngName() != null) {
			sql.append(" and ENG_NAME=?");
		}
		if (appTable.getChName() != null) {
			sql.append(" and CH_NAME=?");
		}
		if (appTable.getTableComment() != null) {
			sql.append(" and TABLE_COMMENT=?");
		}
		if (appTable.getDbUser() != null) {
			sql.append(" and DB_USER=?");
		}
		if (appTable.getLastModiDate() != null) {
			sql.append(" and LAST_MODI_DATE=?");
		}
		if (appTable.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (appTable.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (appTable.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (appTable.getAppTableId() != null) {
				ps.setString(index++, appTable.getAppTableId());
			}
			if (appTable.getEngName() != null) {
				ps.setString(index++, appTable.getEngName());
			}
			if (appTable.getChName() != null) {
				ps.setString(index++, appTable.getChName());
			}
			if (appTable.getTableComment() != null) {
				ps.setString(index++, appTable.getTableComment());
			}
			if (appTable.getDbUser() != null) {
				ps.setString(index++, appTable.getDbUser());
			}
			if (appTable.getLastModiDate() != null) {
				ps.setDate(index++, appTable.getLastModiDate());
			}
			if (appTable.getSts() != null) {
				ps.setString(index++, appTable.getSts());
			}
			if (appTable.getStsDate() != null) {
				ps.setDate(index++, appTable.getStsDate());
			}
			if (appTable.getCreateDate() != null) {
				ps.setDate(index++, appTable.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, AppTableSVO.class);
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
