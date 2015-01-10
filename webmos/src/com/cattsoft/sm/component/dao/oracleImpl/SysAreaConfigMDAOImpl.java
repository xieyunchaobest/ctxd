package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysAreaConfigMDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.SysAreaConfigMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-22 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysAreaConfigMDAOImpl implements ISysAreaConfigMDAO {

	public SysAreaConfigMVO findByPK(SysAreaConfigMVO mvo) throws AppException, SysException {
		SysAreaConfigMVO result = null;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC,b.name, b.system_name,b.config_type ");
		sql.append(" from SYS_AREA_CONFIG a,sys_config b where 1=1 and a.config_id=b.config_id ");
		sql.append(" and a.SYS_AREA_CONFIG_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, mvo.getSysAreaConfigId());
			rs = ps.executeQuery();
			result = (SysAreaConfigMVO) ResultSetUtil.convertToVo(rs, SysAreaConfigMVO.class);
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

	public PagView findSysAreaConfigsByPage(SysAreaConfigMVO vo, HashSet set, PagInfo pagInfo)
			throws Exception {
		List results = null;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC,b.name,b.system_name,b.config_type ");
		sql.append(" from SYS_AREA_CONFIG a,sys_config b where 1=1 and a.config_id=b.config_id ");
		if (vo == null && set != null) {
			sql.append("and ( ");
			sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "sys_area_config_id",
					"sysAreaConfigId"));
			sql.append(" ) ");
		}
		if (vo != null && set == null) {
			if (vo.getSysAreaConfigId() != null) {
				sql.append(" and a.SYS_AREA_CONFIG_ID=" + vo.getSysAreaConfigId());
			}
			if (vo.getConfigId() != null) {
				sql.append(" and a.CONFIG_ID=" + vo.getConfigId());
			}
			if (vo.getSpAreaId() != null) {
				sql.append(" and a.SP_AREA_ID=" + vo.getSpAreaId());
			}
			if (vo.getCurValue() != null) {
				sql.append(" and a.CUR_VALUE='" + vo.getCurValue() + "'");
			}
			if (vo.getValueDesc() != null) {
				sql.append(" and a.VALUE_DESC='%" + vo.getValueDesc() + "%'");
			}
			if (vo.getName() != null) {
				sql.append(" and b.name like '%" + vo.getName() + "%'");
			}
			if (vo.getConfigType() != null) {
				sql.append(" and b.config_type='" + vo.getConfigType() + "'");
			}
		}
		sql.append(" order by sys_area_config_id");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			Sql sql2 = new Sql();
			sql2.setSql(sql.toString());
			sql2.log(SysAreaConfigMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			results = (List) ResultSetUtil.convertToList(rs, SysAreaConfigMVO.class);
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

	public PagView findByLocalNet(GenericVO svo, String localNetId, PagInfo pagInfo)
			throws AppException, SysException {
		SysAreaConfigMVO vo = (SysAreaConfigMVO) svo;
		List results = null;
		Sql sql = new Sql("select");
		sql
				.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC,b.name,b.system_name,b.config_type ");
		sql
				.append(" from SYS_AREA_CONFIG a,sys_config b where 1=1 and a.config_id=b.config_id  and b.config_type='L'");

		//		if (vo.getSysAreaConfigId() != null) {
		//			sql.append(" and a.SYS_AREA_CONFIG_ID=:sysAreaConfigId");
		//			sql.setInteger("sysAreaConfigId", StringUtil.toString(vo.getSysAreaConfigId()).trim());
		//		}

		if (vo.getSysAreaConfigId() != null) {
			sql.append(" and a.SYS_AREA_CONFIG_ID like :sysAreaConfigId");
			sql.setString("sysAreaConfigId", "%"
					+ StringUtil.toString(vo.getSysAreaConfigId()).trim() + "%");
		}

		if (vo.getConfigId() != null) {
			sql.append(" and b.CONFIG_ID like :configId");
			sql.setString("configId", "%" + vo.getConfigId() + "%");
		}
		if (vo.getSpAreaId() != null) {
			sql.append(" and a.SP_AREA_ID=:spAreaId");
			sql.setString("spAreaId", vo.getSpAreaId());
		}
		if (vo.getCurValue() != null) {
			sql.append(" and a.CUR_VALUE=:curValue");
			sql.setString("curValue", vo.getCurValue());
		}
		if (vo.getValueDesc() != null) {
			sql.append(" and a.VALUE_DESC like :valueDesc");
			sql.setString("valueDesc", "%" + vo.getValueDesc() + "%");
		}
		if (vo.getName() != null) {
			sql.append(" and b.name like :name");
			sql.setString("name", "%" + vo.getName() + "%");
		}
		if (vo.getConfigType() != null) {
			sql.append(" and b.config_type=:configType");
			sql.setString("configType", vo.getConfigType());
		}
		if (localNetId != null) {
			sql.append(" and a.sp_area_id=:spId");
			sql.setString("spId", localNetId);
		}
		sql.append(" union select ");
		sql
				.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC,b.name,b.system_name,b.config_type ");
		sql
				.append(" from SYS_AREA_CONFIG a,sys_config b,AREA C where 1=1 and a.config_id=b.config_id  and b.config_type='A' ");
		sql.append(" and c.area_id=a.sp_area_id and c.sts='A' ");

		//		if (vo.getSysAreaConfigId() != null) {
		//			sql.append(" and a.SYS_AREA_CONFIG_ID=:sysAreaConfigId");
		//			sql.setInteger("sysAreaConfigId", StringUtil.toString(vo.getSysAreaConfigId()).trim());
		//		}

		if (vo.getSysAreaConfigId() != null) {
			sql.append(" and a.SYS_AREA_CONFIG_ID like :sysAreaConfigId");
			sql.setString("sysAreaConfigId", "%"
					+ StringUtil.toString(vo.getSysAreaConfigId()).trim() + "%");
		}

		//		if (vo.getConfigId() != null) {
		//			sql.append(" and a.CONFIG_ID=:configId");
		//			sql.setInteger("configId", vo.getConfigId());
		//		}

		if (vo.getConfigId() != null) {
			sql.append(" and b.CONFIG_ID like :configId");
			sql.setString("configId", "%" + vo.getConfigId() + "%");
		}
		if (vo.getSpAreaId() != null) {
			sql.append(" and a.SP_AREA_ID=:spAreaId");
			sql.setString("spAreaId", vo.getSpAreaId());
		}
		if (vo.getCurValue() != null) {
			sql.append(" and a.CUR_VALUE=:curValue");
			sql.setString("curValue", vo.getCurValue());
		}
		if (vo.getValueDesc() != null) {
			sql.append(" and a.VALUE_DESC like :valueDesc");
			sql.setString("valueDesc", "%" + vo.getValueDesc() + "%");
		}
		if (vo.getName() != null) {
			sql.append(" and b.name like :name");
			sql.setString("name", "%" + vo.getName() + "%");
		}
		if (vo.getConfigType() != null) {
			sql.append(" and b.config_type=:configType");
			sql.setString("configType", vo.getConfigType());
		}
		if (localNetId != null) {
			sql.append(" and c.local_NET_ID=:localNetId");
			sql.setString("localNetId", localNetId);
		}
		sql.append(" union select ");
		sql
				.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC,b.name,b.system_name,b.config_type ");
		sql
				.append(" from SYS_AREA_CONFIG a,sys_config b,exch c where 1=1 and a.config_id=b.config_id  and b.config_type='A' ");
		sql.append(" and c.exch_id=a.sp_area_id and c.sts='A' ");

		//		if (vo.getSysAreaConfigId() != null) {
		//			sql.append(" and a.SYS_AREA_CONFIG_ID=:sysAreaConfigId");
		//			sql.setInteger("sysAreaConfigId", StringUtil.toString(vo.getSysAreaConfigId()).trim());
		//		}
		if (vo.getSysAreaConfigId() != null) {
			sql.append(" and a.SYS_AREA_CONFIG_ID like :sysAreaConfigId");
			sql.setString("sysAreaConfigId", "%"
					+ StringUtil.toString(vo.getSysAreaConfigId()).trim() + "%");
		}
		//		if (vo.getConfigId() != null) {
		//			sql.append(" and a.CONFIG_ID=:configId");
		//			sql.setInteger("configId", vo.getConfigId());
		//		}
		if (vo.getConfigId() != null) {
			sql.append(" and b.CONFIG_ID like :configId");
			sql.setString("configId", "%" + vo.getConfigId() + "%");
		}
		if (vo.getSpAreaId() != null) {
			sql.append(" and a.SP_AREA_ID=:spAreaId");
			sql.setString("spAreaId", vo.getSpAreaId());
		}
		if (vo.getCurValue() != null) {
			sql.append(" and a.CUR_VALUE=:curValue");
			sql.setString("curValue", vo.getCurValue());
		}
		if (vo.getValueDesc() != null) {
			sql.append(" and a.VALUE_DESC like :valueDesc");
			sql.setString("valueDesc", "%" + vo.getValueDesc() + "%");
		}
		if (vo.getName() != null) {
			sql.append(" and b.name like :name");
			sql.setString("name", "%" + vo.getName() + "%");
		}
		if (vo.getConfigType() != null) {
			sql.append(" and b.config_type=:configType");
			sql.setString("configType", vo.getConfigType());
		}
		if (localNetId != null) {
			sql.append(" and c.local_NET_ID=:localNetId");
			sql.setString("localNetId", localNetId);
		}
		sql.append(" union select ");
		sql
				.append(" a.SYS_AREA_CONFIG_ID,a.CONFIG_ID,a.SP_AREA_ID,a.CUR_VALUE,a.VALUE_DESC,b.name,b.system_name,b.config_type ");
		sql
				.append(" from SYS_AREA_CONFIG a,sys_config b,work_area c where 1=1 and a.config_id=b.config_id  and b.config_type='A' ");
		sql.append(" and c.work_area_id=a.sp_area_id and c.sts='A' ");

		//		if (vo.getSysAreaConfigId() != null) {
		//			sql.append(" and a.SYS_AREA_CONFIG_ID=:sysAreaConfigId");
		//			sql.setInteger("sysAreaConfigId", StringUtil.toString(vo.getSysAreaConfigId()).trim());
		//		}

		if (vo.getSysAreaConfigId() != null) {
			sql.append(" and a.SYS_AREA_CONFIG_ID like :sysAreaConfigId");
			sql.setString("sysAreaConfigId", "%"
					+ StringUtil.toString(vo.getSysAreaConfigId()).trim() + "%");
		}

		//		if (vo.getConfigId() != null) {
		//			sql.append(" and a.CONFIG_ID=:configId");
		//			sql.setInteger("configId", vo.getConfigId());
		//		}
		if (vo.getConfigId() != null) {
			sql.append(" and b.CONFIG_ID like :configId");
			sql.setString("configId", "%" + vo.getConfigId() + "%");
		}
		if (vo.getSpAreaId() != null) {
			sql.append(" and a.SP_AREA_ID=:spAreaId");
			sql.setString("spAreaId", vo.getSpAreaId());
		}
		if (vo.getCurValue() != null) {
			sql.append(" and a.CUR_VALUE=:curValue");
			sql.setString("curValue", vo.getCurValue());
		}
		if (vo.getValueDesc() != null) {
			sql.append(" and a.VALUE_DESC like :valueDesc");
			sql.setString("valueDesc", "%" + vo.getValueDesc() + "%");
		}
		if (vo.getName() != null) {
			sql.append(" and b.name like :name");
			sql.setString("name", "%" + vo.getName() + "%");
		}
		if (vo.getConfigType() != null) {
			sql.append(" and b.config_type=:configType");
			sql.setString("configType", vo.getConfigType());
		}
		if (localNetId != null) {
			sql.append(" and c.local_NET_ID=:localNetId");
			sql.setString("localNetId", localNetId);
		}

		Connection connection = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			sql.logPartition(null, this.getClass());
			pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql, pagInfo);
			results = (List) ResultSetUtil.convertToList(rs, SysAreaConfigMVO.class);
			pagView.setViewList(results);
		} catch (Exception e) {
			throw new SysException("", "findByVO error..", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
			}
		}
		return pagView;
	}
}
