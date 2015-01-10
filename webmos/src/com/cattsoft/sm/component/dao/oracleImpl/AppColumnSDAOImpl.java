package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IAppColumnSDAO;
import com.cattsoft.sm.vo.*;

public class AppColumnSDAOImpl implements IAppColumnSDAO {

	
	public void add(GenericVO vo) throws AppException, SysException {
		AppColumnSVO appColumn = (AppColumnSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" APP_COLUMN(APP_COLUMN_ID,ENG_NAME,CH_NAME,DOMAIN_ID,APP_TABLE_ID,COLUMN_COMMENT,DATA_TYPE,COLUMN_LENGTH,MANDATORY_FLAG,PRIMARY_KEY_FLAG,FOREIGN_KEY_FLAG,LAST_MODI_DATE,STS,STS_DATE,CREATE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appColumn.getAppColumnId());
			ps.setString(2, appColumn.getEngName());
			ps.setString(3, appColumn.getChName());
			ps.setString(4, appColumn.getDomainId());
			ps.setString(5, appColumn.getAppTableId());
			ps.setString(6, appColumn.getColumnComment());
			ps.setString(7, appColumn.getDataType());
			ps.setString(8, appColumn.getColumnLength());
			ps.setString(9, appColumn.getMandatoryFlag());
			ps.setString(10, appColumn.getPrimaryKeyFlag());
			ps.setString(11, appColumn.getForeignKeyFlag());
			ps.setDate(12, appColumn.getLastModiDate());
			ps.setString(13, appColumn.getSts());
			ps.setDate(14, appColumn.getStsDate());
			ps.setDate(15, appColumn.getCreateDate());
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
		AppColumnSVO appColumn = (AppColumnSVO) vo;
		StringBuffer sql = new StringBuffer("update APP_COLUMN set");
		if (appColumn.getEngName() != null) {
			sql.append(" ENG_NAME=?,");
		}
		if (appColumn.getChName() != null) {
			sql.append(" CH_NAME=?,");
		}
		if (appColumn.getDomainId() != null) {
			sql.append(" DOMAIN_ID=?,");
		}
		if (appColumn.getAppTableId() != null) {
			sql.append(" APP_TABLE_ID=?,");
		}
		if (appColumn.getColumnComment() != null) {
			sql.append(" COLUMN_COMMENT=?,");
		}
		if (appColumn.getDataType() != null) {
			sql.append(" DATA_TYPE=?,");
		}
		if (appColumn.getColumnLength() != null) {
			sql.append(" COLUMN_LENGTH=?,");
		}
		if (appColumn.getMandatoryFlag() != null) {
			sql.append(" MANDATORY_FLAG=?,");
		}
		if (appColumn.getPrimaryKeyFlag() != null) {
			sql.append(" PRIMARY_KEY_FLAG=?,");
		}
		if (appColumn.getForeignKeyFlag() != null) {
			sql.append(" FOREIGN_KEY_FLAG=?,");
		}
		if (appColumn.getLastModiDate() != null) {
			sql.append(" LAST_MODI_DATE=?,");
		}
		if (appColumn.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (appColumn.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (appColumn.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and APP_COLUMN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (appColumn.getEngName() != null) {
				ps.setString(index++, appColumn.getEngName());
			}
			if (appColumn.getChName() != null) {
				ps.setString(index++, appColumn.getChName());
			}
			if (appColumn.getDomainId() != null) {
				ps.setString(index++, appColumn.getDomainId());
			}
			if (appColumn.getAppTableId() != null) {
				ps.setString(index++, appColumn.getAppTableId());
			}
			if (appColumn.getColumnComment() != null) {
				ps.setString(index++, appColumn.getColumnComment());
			}
			if (appColumn.getDataType() != null) {
				ps.setString(index++, appColumn.getDataType());
			}
			if (appColumn.getColumnLength() != null) {
				ps.setString(index++, appColumn.getColumnLength());
			}
			if (appColumn.getMandatoryFlag() != null) {
				ps.setString(index++, appColumn.getMandatoryFlag());
			}
			if (appColumn.getPrimaryKeyFlag() != null) {
				ps.setString(index++, appColumn.getPrimaryKeyFlag());
			}
			if (appColumn.getForeignKeyFlag() != null) {
				ps.setString(index++, appColumn.getForeignKeyFlag());
			}
			if (appColumn.getLastModiDate() != null) {
				ps.setDate(index++, appColumn.getLastModiDate());
			}
			if (appColumn.getSts() != null) {
				ps.setString(index++, appColumn.getSts());
			}
			if (appColumn.getStsDate() != null) {
				ps.setDate(index++, appColumn.getStsDate());
			}
			if (appColumn.getCreateDate() != null) {
				ps.setDate(index++, appColumn.getCreateDate());
			}
			ps.setString(index++, appColumn.getAppColumnId());
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
		AppColumnSVO appColumn = (AppColumnSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from APP_COLUMN where 1=1");
		sql.append(" and APP_COLUMN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appColumn.getAppColumnId());
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
		AppColumnSVO result = null;
		AppColumnSVO appColumn = (AppColumnSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.APP_COLUMN_ID,a.ENG_NAME,a.CH_NAME,a.DOMAIN_ID,a.APP_TABLE_ID,a.COLUMN_COMMENT,a.DATA_TYPE,a.COLUMN_LENGTH,a.MANDATORY_FLAG,a.PRIMARY_KEY_FLAG,a.FOREIGN_KEY_FLAG,a.LAST_MODI_DATE,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from APP_COLUMN a where 1=1");
		sql.append(" and APP_COLUMN_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appColumn.getAppColumnId());
			rs = ps.executeQuery();
			result = (AppColumnSVO) ResultSetUtil.convertToVo(rs, AppColumnSVO.class);
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
		AppColumnSVO appColumn = (AppColumnSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.APP_COLUMN_ID,a.ENG_NAME,a.CH_NAME,a.DOMAIN_ID,a.APP_TABLE_ID,a.COLUMN_COMMENT,a.DATA_TYPE,a.COLUMN_LENGTH,a.MANDATORY_FLAG,a.PRIMARY_KEY_FLAG,a.FOREIGN_KEY_FLAG,a.LAST_MODI_DATE,a.STS,a.STS_DATE,a.CREATE_DATE");
		sql.append(" from APP_COLUMN a where 1=1");
		if (appColumn.getAppColumnId() != null) {
			sql.append(" and APP_COLUMN_ID=?");
		}
		if (appColumn.getEngName() != null) {
			sql.append(" and ENG_NAME=?");
		}
		if (appColumn.getChName() != null) {
			sql.append(" and CH_NAME=?");
		}
		if (appColumn.getDomainId() != null) {
			sql.append(" and DOMAIN_ID=?");
		}
		if (appColumn.getAppTableId() != null) {
			sql.append(" and APP_TABLE_ID=?");
		}
		if (appColumn.getColumnComment() != null) {
			sql.append(" and COLUMN_COMMENT=?");
		}
		if (appColumn.getDataType() != null) {
			sql.append(" and DATA_TYPE=?");
		}
		if (appColumn.getColumnLength() != null) {
			sql.append(" and COLUMN_LENGTH=?");
		}
		if (appColumn.getMandatoryFlag() != null) {
			sql.append(" and MANDATORY_FLAG=?");
		}
		if (appColumn.getPrimaryKeyFlag() != null) {
			sql.append(" and PRIMARY_KEY_FLAG=?");
		}
		if (appColumn.getForeignKeyFlag() != null) {
			sql.append(" and FOREIGN_KEY_FLAG=?");
		}
		if (appColumn.getLastModiDate() != null) {
			sql.append(" and LAST_MODI_DATE=?");
		}
		if (appColumn.getSts() != null) {
			sql.append(" and STS=?");
		}
		if (appColumn.getStsDate() != null) {
			sql.append(" and STS_DATE=?");
		}
		if (appColumn.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (appColumn.getAppColumnId() != null) {
				ps.setString(index++, appColumn.getAppColumnId());
			}
			if (appColumn.getEngName() != null) {
				ps.setString(index++, appColumn.getEngName());
			}
			if (appColumn.getChName() != null) {
				ps.setString(index++, appColumn.getChName());
			}
			if (appColumn.getDomainId() != null) {
				ps.setString(index++, appColumn.getDomainId());
			}
			if (appColumn.getAppTableId() != null) {
				ps.setString(index++, appColumn.getAppTableId());
			}
			if (appColumn.getColumnComment() != null) {
				ps.setString(index++, appColumn.getColumnComment());
			}
			if (appColumn.getDataType() != null) {
				ps.setString(index++, appColumn.getDataType());
			}
			if (appColumn.getColumnLength() != null) {
				ps.setString(index++, appColumn.getColumnLength());
			}
			if (appColumn.getMandatoryFlag() != null) {
				ps.setString(index++, appColumn.getMandatoryFlag());
			}
			if (appColumn.getPrimaryKeyFlag() != null) {
				ps.setString(index++, appColumn.getPrimaryKeyFlag());
			}
			if (appColumn.getForeignKeyFlag() != null) {
				ps.setString(index++, appColumn.getForeignKeyFlag());
			}
			if (appColumn.getLastModiDate() != null) {
				ps.setDate(index++, appColumn.getLastModiDate());
			}
			if (appColumn.getSts() != null) {
				ps.setString(index++, appColumn.getSts());
			}
			if (appColumn.getStsDate() != null) {
				ps.setDate(index++, appColumn.getStsDate());
			}
			if (appColumn.getCreateDate() != null) {
				ps.setDate(index++, appColumn.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, AppColumnSVO.class);
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
