package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IStatusSDAO;
import com.cattsoft.sm.vo.StatusSVO;

public class StatusSDAOImpl implements IStatusSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		StatusSVO status = (StatusSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql.append(" STATUS(TABLE_NAME,COLUMN_NAME,STS_ID,STS_WORDS,ORDER_ID) values(?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, status.getTableName());
			ps.setString(2, status.getColumnName());
			ps.setString(3, status.getStsId());
			ps.setString(4, status.getStsWords());
			ps.setString(5, status.getOrderId());
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
		StatusSVO status = (StatusSVO) vo;
		StringBuffer sql = new StringBuffer("update STATUS set");
		if (status.getTableName() != null) {
			sql.append(" TABLE_NAME=?,");
		}
		if (status.getColumnName() != null) {
			sql.append(" COLUMN_NAME=?,");
		}
		if (status.getStsId() != null) {
			sql.append(" STS_ID=?,");
		}
		if (status.getStsWords() != null) {
			sql.append(" STS_WORDS=?,");
		}
		if (status.getOrderId() != null) {
			sql.append(" ORDER_ID=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (status.getTableName() != null) {
				ps.setString(index++, status.getTableName());
			}
			if (status.getColumnName() != null) {
				ps.setString(index++, status.getColumnName());
			}
			if (status.getStsId() != null) {
				ps.setString(index++, status.getStsId());
			}
			if (status.getStsWords() != null) {
				ps.setString(index++, status.getStsWords());
			}
			if (status.getOrderId() != null) {
				ps.setString(index++, status.getOrderId());
			}
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
		// lilin[20080819]
		StatusSVO status = (StatusSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		if (StringUtil.isBlank(status.getTableName()) || StringUtil.isBlank(status.getColumnName())
				|| StringUtil.isBlank(status.getStsId())) {
			throw new AppException("100002", "缺少对象的主键！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("delete from STATUS where 1=1");
		sql.append(" and TABLE_NAME=:tableName");
		sql.setString("tableName", status.getTableName());
		sql.append(" and COLUMN_NAME=:columnName");
		sql.setString("columnName", status.getColumnName());
		sql.append(" and STS_ID=:stsId");
		sql.setString("stsId", status.getStsId());
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SysException("100003", "delete error..", e);
		} finally {
			JdbcUtil.close(ps);
		}
		// end lilin[20080819]
	}

	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		StatusSVO result = null;
		// StatusSVO status = (StatusSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql.append(" a.TABLE_NAME,a.COLUMN_NAME,a.STS_ID,a.STS_WORDS,a.ORDER_ID");
		sql.append(" from STATUS a where 1=1");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			result = (StatusSVO) ResultSetUtil.convertToVo(rs, StatusSVO.class);
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
		StatusSVO status = (StatusSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.TABLE_NAME,a.COLUMN_NAME,a.STS_ID,a.STS_WORDS,a.ORDER_ID");
		sql.append(" from STATUS a where 1=1");
		if (status.getTableName() != null) {
			sql.append(" and TABLE_NAME=?");
		}
		if (status.getColumnName() != null) {
			sql.append(" and COLUMN_NAME=?");
		}
		if (status.getStsId() != null) {
			sql.append(" and STS_ID=?");
		}
		if (status.getStsWords() != null) {
			sql.append(" and STS_WORDS=?");
		}
		if (status.getOrderId() != null) {
			sql.append(" and ORDER_ID=?");
		}
		sql.append(" order by  order_id ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (status.getTableName() != null) {
				ps.setString(index++, status.getTableName());
			}
			if (status.getColumnName() != null) {
				ps.setString(index++, status.getColumnName());
			}
			if (status.getStsId() != null) {
				ps.setString(index++, status.getStsId());
			}
			if (status.getStsWords() != null) {
				ps.setString(index++, status.getStsWords());
			}
			if (status.getOrderId() != null) {
				ps.setString(index++, status.getOrderId());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, StatusSVO.class);
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

	public PagView findStatusByPage(StatusSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
		List results = null;
		StatusSVO status = (StatusSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append(" a.TABLE_NAME,a.COLUMN_NAME,a.STS_ID,a.STS_WORDS,a.ORDER_ID");
		sql.append(" from STATUS a where 1=1");
		if (vo == null && set != null) {
			sql.append(" and ");
			sql.append(this.getLatestHqlWhereInt(set));
		}
		if (vo != null && set == null) {
			if (status.getTableName() != null) {
				// sql.append(" and TABLE_NAME='" + status.getTableName() + "'");
				sql.append(" and TABLE_NAME like '%" + status.getTableName() + "%'");
			}
			if (status.getColumnName() != null) {
				// sql.append(" and COLUMN_NAME='" + status.getColumnName() + "'");
				sql.append(" and COLUMN_NAME like '%" + status.getColumnName() + "%'");
			}
			if (status.getStsId() != null) {
				// sql.append(" and STS_ID='" + status.getStsId() + "'");
				sql.append(" and STS_ID like '%" + status.getStsId() + "%'");
			}
			if (status.getStsWords() != null) {
				sql.append(" and STS_WORDS='" + status.getStsWords() + "'");
			}
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			Sql sql2 = new Sql();
			sql2.setSql(sql.toString());
			sql2.log(StatusSDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			results = (List) ResultSetUtil.convertToList(rs, StatusSVO.class);
			pagView.setViewList(results);
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
		return pagView;
	}

	private String getLatestHqlWhereInt(HashSet latestSet) {
		if (latestSet == null || latestSet.size() == 0)
			return " 1=0 ";
		StringBuffer sb = new StringBuffer();
		Iterator ita = latestSet.iterator();
		StatusSVO vo = null;
		while (ita.hasNext()) {
			vo = (StatusSVO) ita.next();
			// a.stsId = '" + vo.getStsId()+"' and a.tableName = '"+vo.getTableName()+"' and
			// a.columnNameName = '"+vo.getColumnName() +"'"
			sb.append("( a.table_Name = '" + vo.getTableName() + "' and a.column_Name = '"
					+ vo.getColumnName() + "'");
			if (vo.getStsId() != null) {
				sb.append("and a.sts_Id = '" + vo.getStsId() + "'");
			}
			sb.append(" )");
			sb.append(" or ");
		}
		String retStr = sb.toString();
		return retStr.substring(0, retStr.length() - 4);
	}

	public void updateStatus(GenericVO vo) throws AppException, SysException {
		StatusSVO status = (StatusSVO) vo;
		StringBuffer sql = new StringBuffer("update status d set");

		if (status.getStsWords() != null) {
			sql.append(" d.sts_words=?,");
		}
		if (status.getOrderId() != null) {
			sql.append(" d.ORDER_ID=?,");
		}

		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		if (status.getColumnName() != null) {
			sql.append(" and d.column_name=? ");
		}
		if (status.getTableName() != null) {
			sql.append(" and d.table_name=? ");
		}
		if (status.getStsId() != null) {
			sql.append(" and d.sts_id=? ");
		}

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;

			if (status.getStsWords() != null) {
				ps.setString(index++, status.getStsWords());
			}
			if (status.getOrderId() != null) {
				ps.setString(index++, status.getOrderId());
			}

			ps.setString(index++, status.getColumnName());
			ps.setString(index++, status.getTableName());
			ps.setString(index++, status.getStsId());
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
}
