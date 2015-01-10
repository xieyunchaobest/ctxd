package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.*;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysConfigSDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.*;

public class SysConfigSDAOImpl implements ISysConfigSDAO {

	public void add(GenericVO vo) throws AppException, SysException {
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql
				.append(" SYS_CONFIG(CONFIG_ID,NAME,SYSTEM_NAME,CONFIG_TYPE,CUR_VALUE,VALUE_DESC,CREATE_DATE) values(?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysConfig.getConfigId());
			ps.setString(2, sysConfig.getName());
			ps.setString(3, sysConfig.getSystemName());
			ps.setString(4, sysConfig.getConfigType());
			ps.setString(5, sysConfig.getCurValue());
			ps.setString(6, sysConfig.getValueDesc());
			ps.setDate(7, sysConfig.getCreateDate());
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
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		StringBuffer sql = new StringBuffer("update SYS_CONFIG set");
		if (sysConfig.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (sysConfig.getSystemName() != null) {
			sql.append(" SYSTEM_NAME=?,");
		}
		if (sysConfig.getConfigType() != null) {
			sql.append(" CONFIG_TYPE=?,");
		}
		if (sysConfig.getCurValue() != null) {
			sql.append(" CUR_VALUE=?,");
		}
		if (sysConfig.getValueDesc() != null) {
			sql.append(" VALUE_DESC=?,");
		}
		if (sysConfig.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and CONFIG_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysConfig.getName() != null) {
				ps.setString(index++, sysConfig.getName());
			}
			if (sysConfig.getSystemName() != null) {
				ps.setString(index++, sysConfig.getSystemName());
			}
			if (sysConfig.getConfigType() != null) {
				ps.setString(index++, sysConfig.getConfigType());
			}
			if (sysConfig.getCurValue() != null) {
				ps.setString(index++, sysConfig.getCurValue());
			}
			if (sysConfig.getValueDesc() != null) {
				ps.setString(index++, sysConfig.getValueDesc());
			}
			if (sysConfig.getCreateDate() != null) {
				ps.setDate(index++, sysConfig.getCreateDate());
			}
			ps.setString(index++, sysConfig.getConfigId());
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
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from SYS_CONFIG where 1=1");
		sql.append(" and CONFIG_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysConfig.getConfigId());
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
		SysConfigSVO result = null;
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.CONFIG_ID,a.NAME,a.SYSTEM_NAME,a.CONFIG_TYPE,a.CUR_VALUE,a.VALUE_DESC,a.CREATE_DATE");
		sql.append(" from SYS_CONFIG a where 1=1");
		sql.append(" and CONFIG_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysConfig.getConfigId());
			rs = ps.executeQuery();
			result = (SysConfigSVO) ResultSetUtil.convertToVo(rs,
					SysConfigSVO.class);
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
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(" a.CONFIG_ID,a.NAME,a.SYSTEM_NAME,a.CONFIG_TYPE,a.CUR_VALUE,a.VALUE_DESC,a.CREATE_DATE");
		sql.append(" from SYS_CONFIG a where 1=1");
		if (sysConfig.getConfigId() != null) {
			sql.append(" and CONFIG_ID=?");
		}
		if (sysConfig.getName() != null) {
			sql.append(" and NAME=?");
		}
		if (sysConfig.getSystemName() != null) {
			sql.append(" and SYSTEM_NAME=?");
		}
		if (sysConfig.getConfigType() != null) {
			sql.append(" and CONFIG_TYPE=?");
		}
		if (sysConfig.getCurValue() != null) {
			sql.append(" and CUR_VALUE=?");
		}
		if (sysConfig.getValueDesc() != null) {
			sql.append(" and VALUE_DESC=?");
		}
		if (sysConfig.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (sysConfig.getConfigId() != null) {
				ps.setString(index++, sysConfig.getConfigId());
			}
			if (sysConfig.getName() != null) {
				ps.setString(index++, sysConfig.getName());
			}
			if (sysConfig.getSystemName() != null) {
				ps.setString(index++, sysConfig.getSystemName());
			}
			if (sysConfig.getConfigType() != null) {
				ps.setString(index++, sysConfig.getConfigType());
			}
			if (sysConfig.getCurValue() != null) {
				ps.setString(index++, sysConfig.getCurValue());
			}
			if (sysConfig.getValueDesc() != null) {
				ps.setString(index++, sysConfig.getValueDesc());
			}
			if (sysConfig.getCreateDate() != null) {
				ps.setDate(index++, sysConfig.getCreateDate());
			}
			rs = ps.executeQuery();
			results = (List) ResultSetUtil
					.convertToList(rs, SysConfigSVO.class);
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

	public SysConfigSVO findSysConfigByPK(SysConfigSVO vo) throws AppException,
			SysException {
		SysConfigSVO result = null;
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		StringBuffer sql = new StringBuffer(
				"select a.CONFIG_ID,a.NAME,a.SYSTEM_NAME,a.CONFIG_TYPE,a.CUR_VALUE,a.VALUE_DESC,a.CREATE_DATE from SYS_CONFIG  a where 1=1");
		sql.append(" and CONFIG_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysConfig.getConfigId());
			rs = ps.executeQuery();
			result = (SysConfigSVO) ResultSetUtil.convertToVo(rs,
					SysConfigSVO.class);
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

	public PagView findSysConfigsByPage(SysConfigSVO vo, HashSet set,
			PagInfo pagInfo) throws Exception {
		List results = null;
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		Sql sql = new Sql();
		sql.append("select");
		sql
				.append(" a.CONFIG_ID,a.NAME,a.SYSTEM_NAME,a.CONFIG_TYPE,a.CUR_VALUE,a.VALUE_DESC,a.CREATE_DATE");
		sql.append(" from SYS_CONFIG a where 1=1");

		if (vo == null && set != null) {
			sql.append("and ( ");
			sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "config_id",
					"configId"));
			sql.append(" ) ");
		}
		if (vo != null && set == null) {
			if (sysConfig.getConfigId() != null) {
				sql.append(" and CONFIG_ID=:configId");
				sql.setInteger("configId", sysConfig.getConfigId());
			}
			if (sysConfig.getName() != null) {
				sql.append(" and NAME like :name");
				sql.setString("name", "%" + sysConfig.getName() + "%");
			}
			if (sysConfig.getSystemName() != null) {
				sql.append(" and SYSTEM_NAME=:systemName");
				sql.setString("systemName", sysConfig.getSystemName());
			}
			if (sysConfig.getConfigType() != null) {
				sql.append(" and CONFIG_TYPE=:configType");
				sql.setString("configType", sysConfig.getConfigType());
			}
			if (sysConfig.getCurValue() != null) {
				sql.append(" and CUR_VALUE=:curValue");
				sql.setString("curValue", sysConfig.getCurValue());
			}
			if (sysConfig.getValueDesc() != null) {
				sql.append(" and VALUE_DESC=:valueDesc");
				sql.setString("valueDesc", sysConfig.getValueDesc());

			}
		}
		sql.append(" order by config_id");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			sql.log(this.getClass());
			pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql, pagInfo);
			results = (List) ResultSetUtil
					.convertToList(rs, SysConfigSVO.class);
			pagView.setViewList(results);
		} catch (Exception e) {
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

	public List findByLocalNet(GenericVO vo, String localNetId)
			throws AppException, SysException {
		List results = null;
		SysConfigSVO sysConfig = (SysConfigSVO) vo;
		Sql sql = new Sql();
		sql.append("select");
		sql
				.append(" a.CONFIG_ID,a.NAME,a.SYSTEM_NAME,a.CONFIG_TYPE,a.CUR_VALUE,a.VALUE_DESC,a.CREATE_DATE");
		sql.append(" from SYS_CONFIG a where 1=1");
		if (sysConfig.getConfigId() != null) {
			sql.append(" and CONFIG_ID=?");
		}
		if (sysConfig.getName() != null) {
			sql.append(" and NAME=?");
		}
		if (sysConfig.getSystemName() != null) {
			sql.append(" and SYSTEM_NAME=?");
		}
		if (sysConfig.getConfigType() != null) {
			sql.append(" and CONFIG_TYPE=?");
		}
		if (sysConfig.getCurValue() != null) {
			sql.append(" and CUR_VALUE=?");
		}
		if (sysConfig.getValueDesc() != null) {
			sql.append(" and VALUE_DESC=?");
		}
		if (sysConfig.getCreateDate() != null) {
			sql.append(" and CREATE_DATE=?");
		}
		if (null != localNetId) {
			sql.append(" and config_type not in('G')");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getPartitionSql(null));
			int index = 1;
			if (sysConfig.getConfigId() != null) {
				sql.setString(index++, sysConfig.getConfigId());
			}
			if (sysConfig.getName() != null) {
				sql.setString(index++, sysConfig.getName());
			}
			if (sysConfig.getSystemName() != null) {
				sql.setString(index++, sysConfig.getSystemName());
			}
			if (sysConfig.getConfigType() != null) {
				sql.setString(index++, sysConfig.getConfigType());
			}
			if (sysConfig.getCurValue() != null) {
				sql.setString(index++, sysConfig.getCurValue());
			}
			if (sysConfig.getValueDesc() != null) {
				sql.setString(index++, sysConfig.getValueDesc());
			}
			if (sysConfig.getCreateDate() != null) {
				sql.setTimestamp(index++, sysConfig.getCreateDate());
			}
			sql.fillParams(ps);
			sql.logPartition(null, this.getClass());
			rs = ps.executeQuery();
			results = (List) ResultSetUtil
					.convertToList(rs, SysConfigSVO.class);
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
