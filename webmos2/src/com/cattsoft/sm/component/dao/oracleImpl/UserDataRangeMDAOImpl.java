package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IUserDataRangeMDAO;
import com.cattsoft.sm.vo.UserDataRangeMVO;
import com.cattsoft.sm.vo.UserDataRangeSVO;

public class UserDataRangeMDAOImpl implements IUserDataRangeMDAO {

	// private static Logger log =
	// Logger.getLogger(UserDataRangeMDAOImpl.class);

	/**
	 * 返回允许被当前员工授权的纪录
	 * 
	 * @param vo
	 *            <UserDataRangeSVO>
	 * @return List<UserDataRangeSVO>
	 */

	public List findUserDataRangeList(GenericVO vo) throws AppException,
			SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeSVO userDataRange = (UserDataRangeSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT ud.ALLOW_FLAG,ud.CREATE_DATE,ud.DATA_RANGE_ID,ud.RANGE_TYPE_ID,ud.STS,ud.STS_DATE,ud.SYS_USER_ID,ud.USER_DATA_AREA_ID ,");
		sql.append(" su.SYS_USER_NAME userName ");
		sql.append(" FROM USER_DATA_RANGE ud ");
		sql
				.append(" left join SYS_USER su on ud.DATA_RANGE_ID=su.SYS_USER_ID ");
		sql.append(" WHERE 1=1 ");
		try {
			if (userDataRange.getFlagAllowFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
					sql.append(" and ud.ALLOW_FLAG is null ");
				} else {
					sql.append(" and ud.ALLOW_FLAG=:allowFlag");
					sql.setString("allowFlag", userDataRange.getAllowFlag());
				}
			}

			if (userDataRange.getFlagCreateDate() == 1) {
				if (userDataRange.getCreateDate() == null) {
					sql.append(" and ud.CREATE_DATE is null ");
				} else {
					sql.append(" and ud.CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", userDataRange
							.getCreateDate());
				}
			}

			if (userDataRange.getFlagDataRangeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
					sql.append(" and ud.DATA_RANGE_ID is null ");
				} else {
					sql.append(" and ud.DATA_RANGE_ID=:dataRangeId");
					sql.setLong("dataRangeId", userDataRange.getDataRangeId());
				}
			}

			if (userDataRange.getFlagRangeTypeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
					sql.append(" and ud.RANGE_TYPE_ID is null ");
				} else {
					sql.append(" and ud.RANGE_TYPE_ID=:rangeTypeId");
					sql
							.setString("rangeTypeId", userDataRange
									.getRangeTypeId());
				}
			}

			if (userDataRange.getFlagSts() == 1) {
				if (StringUtil.isBlank(userDataRange.getSts())) {
					sql.append(" and ud.STS is null ");
				} else {
					sql.append(" and ud.STS=:sts");
					sql.setString("sts", userDataRange.getSts());
				}
			}

			if (userDataRange.getFlagStsDate() == 1) {
				if (userDataRange.getStsDate() == null) {
					sql.append(" and ud.STS_DATE is null ");
				} else {
					sql.append(" and ud.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", userDataRange.getStsDate());
				}
			}

			// if (userDataRange.getFlagSysUserId() == 1) {
			// if (StringUtil.isBlank(userDataRange.getSysUserId())) {
			// sql.append(" and SYS_USER_ID is null ");
			// } else {
			if (userDataRange.getSysUserId() != null) {
				sql.append(" and ud.SYS_USER_ID=:sysUserId");
//				sql.setLong("sysUserId", userDataRange.getSysUserId());
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}
			// }
			// }

			if (userDataRange.getFlagUserDataAreaId() == 1) {
				if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
					sql.append(" and ud.USER_DATA_AREA_ID is null ");
				} else {
					sql.append(" and ud.USER_DATA_AREA_ID=:userDataAreaId");
					sql.setLong("userDataAreaId", userDataRange
							.getUserDataAreaId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setAllowFlag(rs.getString("ALLOW_FLAG"));
				user.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setSts(rs.getString("STS"));
				user.setStsDate(rs.getTimestamp("STS_DATE"));
				user.setSysUserId(rs.getString("SYS_USER_ID"));
				user.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
				user.setUserName(rs.getString("userName"));
				res.add(user);

			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;
	}

	/**
	 * lilin[20080815] 用于查找员工有的本地网的权限（distinct）
	 */
	public List findDistinctUserLocalNets(GenericVO vo) throws AppException,
			SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeSVO userDataRange = (UserDataRangeSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				" SELECT distinct ud.DATA_RANGE_ID,ud.RANGE_TYPE_ID, a.name  localNetName");
		sql.append(" FROM USER_DATA_RANGE ud  ");
		sql
				.append("  join local_net a  on ud.data_range_id=a.local_net_id and ud.range_type_id='L' ");
		sql.append(" WHERE 1=1 ");
		try {
			if (userDataRange.getFlagAllowFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
					sql.append(" and ud.ALLOW_FLAG is null ");
				} else {
					sql.append(" and ud.ALLOW_FLAG=:allowFlag");
					sql.setString("allowFlag", userDataRange.getAllowFlag());
				}
			}

			if (userDataRange.getFlagCreateDate() == 1) {
				if (userDataRange.getCreateDate() == null) {
					sql.append(" and ud.CREATE_DATE is null ");
				} else {
					sql.append(" and ud.CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", userDataRange
							.getCreateDate());
				}
			}

			if (userDataRange.getFlagDataRangeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
					sql.append(" and ud.DATA_RANGE_ID is null ");
				} else {
					sql.append(" and ud.DATA_RANGE_ID=:dataRangeId");
					sql.setLong("dataRangeId", userDataRange.getDataRangeId());
				}
			}

			if (userDataRange.getFlagRangeTypeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
					sql.append(" and ud.RANGE_TYPE_ID is null ");
				} else {
					sql.append(" and ud.RANGE_TYPE_ID=:rangeTypeId");
					sql
							.setString("rangeTypeId", userDataRange
									.getRangeTypeId());
				}
			}

			if (userDataRange.getFlagSts() == 1) {
				if (StringUtil.isBlank(userDataRange.getSts())) {
					sql.append(" and ud.STS is null ");
				} else {
					sql.append(" and ud.STS=:sts");
					sql.setString("sts", userDataRange.getSts());
				}
			}

			if (userDataRange.getFlagStsDate() == 1) {
				if (userDataRange.getStsDate() == null) {
					sql.append(" and ud.STS_DATE is null ");
				} else {
					sql.append(" and ud.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", userDataRange.getStsDate());
				}
			}

			if (userDataRange.getSysUserId() != null) {
				sql.append(" and ud.SYS_USER_ID=:sysUserId");
//				sql.setLong("sysUserId", userDataRange.getSysUserId());
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}

			if (userDataRange.getFlagUserDataAreaId() == 1) {
				if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
					sql.append(" and ud.USER_DATA_AREA_ID is null ");
				} else {
					sql.append(" and ud.USER_DATA_AREA_ID=:userDataAreaId");
					sql.setLong("userDataAreaId", userDataRange
							.getUserDataAreaId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setLocalNetName(rs.getString("localNetName"));
				res.add(user);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;
	}

	/**
	 * 查询当前员工拥有的服务区
	 * 
	 * @param vo
	 *            <UserDataRangeSVO>
	 */
	public List findUserAreas(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeMVO userDataRange = (UserDataRangeMVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// lilin[20080815] 多查出allow_flag对应的状态
		Sql sql = new Sql(
				" SELECT ud.ALLOW_FLAG,ud.CREATE_DATE,ud.DATA_RANGE_ID,ud.RANGE_TYPE_ID,ud.STS,ud.STS_DATE,ud.SYS_USER_ID,ud.USER_DATA_AREA_ID ,");
		sql
				.append(" a.local_net_id,a.name  areaName, s.STS_WORDS ALLOW_FLAG_NAME ");
		sql.append(" FROM USER_DATA_RANGE ud ");
		sql
				.append("  join area a  on ud.data_range_id=a.area_id and ud.range_type_id='A' ");
		/*
		 * if(!StringUtil.isBlank(userDataRange.getLocalNetId())){ sql.append("
		 * AND A.LOCAL_NET_ID =:localNetId "); sql.setInteger("localNetId",
		 * userDataRange.getLocalNetId()); }
		 */
		sql
				.append("  join status s  on s.table_name='USER_DATA_RANGE' and s.column_name='ALLOW_FLAG' and ud.allow_flag=s.sts_id ");
		sql.append(" WHERE 1=1 ");
		try {
			if (userDataRange.getFlagAllowFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
					sql.append(" and ud.ALLOW_FLAG is null ");
				} else {
					sql.append(" and ud.ALLOW_FLAG=:allowFlag");
					sql.setString("allowFlag", userDataRange.getAllowFlag());
				}
			}

			if (userDataRange.getFlagCreateDate() == 1) {
				if (userDataRange.getCreateDate() == null) {
					sql.append(" and ud.CREATE_DATE is null ");
				} else {
					sql.append(" and ud.CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", userDataRange
							.getCreateDate());
				}
			}

			if (userDataRange.getFlagDataRangeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
					sql.append(" and ud.DATA_RANGE_ID is null ");
				} else {
					sql.append(" and ud.DATA_RANGE_ID=:dataRangeId");
					sql.setLong("dataRangeId", userDataRange.getDataRangeId());
				}
			}

			if (userDataRange.getFlagRangeTypeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
					sql.append(" and ud.RANGE_TYPE_ID is null ");
				} else {
					sql.append(" and ud.RANGE_TYPE_ID=:rangeTypeId");
					sql
							.setString("rangeTypeId", userDataRange
									.getRangeTypeId());
				}
			}

			if (userDataRange.getFlagSts() == 1) {
				if (StringUtil.isBlank(userDataRange.getSts())) {
					sql.append(" and ud.STS is null ");
				} else {
					sql.append(" and ud.STS=:sts");
					sql.setString("sts", userDataRange.getSts());
				}
			}

			if (userDataRange.getFlagStsDate() == 1) {
				if (userDataRange.getStsDate() == null) {
					sql.append(" and ud.STS_DATE is null ");
				} else {
					sql.append(" and ud.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", userDataRange.getStsDate());
				}
			}

			// if (userDataRange.getFlagSysUserId() == 1) {
			// if (StringUtil.isBlank(userDataRange.getSysUserId())) {
			// sql.append(" and SYS_USER_ID is null ");
			// } else {
			if (userDataRange.getSysUserId() != null) {
				sql.append(" and ud.SYS_USER_ID=:sysUserId");
//				sql.setLong("sysUserId", userDataRange.getSysUserId());
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}
			// }
			// }

			if (userDataRange.getFlagUserDataAreaId() == 1) {
				if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
					sql.append(" and ud.USER_DATA_AREA_ID is null ");
				} else {
					sql.append(" and ud.USER_DATA_AREA_ID=:userDataAreaId");
					sql.setLong("userDataAreaId", userDataRange
							.getUserDataAreaId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setAllowFlag(rs.getString("ALLOW_FLAG"));
				user.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setSts(rs.getString("STS"));
				user.setStsDate(rs.getTimestamp("STS_DATE"));
				user.setSysUserId(rs.getString("SYS_USER_ID"));
				user.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
				// user.setUserName(rs.getString("userName"));
				user.setAreaName(rs.getString("areaName"));
				user.setLocalNetId(rs.getString("local_net_id"));
				user.setAllowFlagName(rs.getString("ALLOW_FLAG_NAME"));
				// end lilin[20080815]
				res.add(user);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;
	}

	/**
	 * lilin[20080815] 用于查找员工有的服务区的权限（distinct）
	 */
	public List findDistinctUserAreas(GenericVO vo) throws AppException,
			SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeMVO userDataRange = (UserDataRangeMVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				" SELECT distinct ud.DATA_RANGE_ID,ud.RANGE_TYPE_ID, a.LOCAL_NET_ID,a.NAME  areaName ");
		sql.append(" FROM USER_DATA_RANGE ud ");
		sql
				.append("  join area a  on ud.data_range_id=a.area_id and ud.range_type_id='A' ");
		/*
		 * if(!StringUtil.isBlank(userDataRange.getLocalNetId())){ sql.append("
		 * AND A.LOCAL_NET_ID =:localNetId "); sql.setInteger("localNetId",
		 * userDataRange.getLocalNetId()); }
		 */
		sql.append(" WHERE 1=1 ");
		try {
			if (userDataRange.getFlagAllowFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
					sql.append(" and ud.ALLOW_FLAG is null ");
				} else {
					sql.append(" and ud.ALLOW_FLAG=:allowFlag");
					sql.setString("allowFlag", userDataRange.getAllowFlag());
				}
			}

			if (userDataRange.getFlagCreateDate() == 1) {
				if (userDataRange.getCreateDate() == null) {
					sql.append(" and ud.CREATE_DATE is null ");
				} else {
					sql.append(" and ud.CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", userDataRange
							.getCreateDate());
				}
			}

			if (userDataRange.getFlagDataRangeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
					sql.append(" and ud.DATA_RANGE_ID is null ");
				} else {
					sql.append(" and ud.DATA_RANGE_ID=:dataRangeId");
					sql.setLong("dataRangeId", userDataRange.getDataRangeId());
				}
			}

			if (userDataRange.getFlagRangeTypeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
					sql.append(" and ud.RANGE_TYPE_ID is null ");
				} else {
					sql.append(" and ud.RANGE_TYPE_ID=:rangeTypeId");
					sql
							.setString("rangeTypeId", userDataRange
									.getRangeTypeId());
				}
			}

			if (userDataRange.getFlagSts() == 1) {
				if (StringUtil.isBlank(userDataRange.getSts())) {
					sql.append(" and ud.STS is null ");
				} else {
					sql.append(" and ud.STS=:sts");
					sql.setString("sts", userDataRange.getSts());
				}
			}

			if (userDataRange.getFlagStsDate() == 1) {
				if (userDataRange.getStsDate() == null) {
					sql.append(" and ud.STS_DATE is null ");
				} else {
					sql.append(" and ud.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", userDataRange.getStsDate());
				}
			}

			if (userDataRange.getSysUserId() != null) {
				sql.append(" and ud.SYS_USER_ID=:sysUserId");
//				sql.setLong("sysUserId", userDataRange.getSysUserId());
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}

			if (userDataRange.getFlagUserDataAreaId() == 1) {
				if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
					sql.append(" and ud.USER_DATA_AREA_ID is null ");
				} else {
					sql.append(" and ud.USER_DATA_AREA_ID=:userDataAreaId");
					sql.setLong("userDataAreaId", userDataRange
							.getUserDataAreaId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setAreaName(rs.getString("areaName"));
				user.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				res.add(user);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;
	}

	/**
	 * 查询当前员工拥有的本地网
	 * 
	 * @param vo
	 *            <UserDataRangeSVO>
	 */
	public List findUserLocalNets(GenericVO vo) throws AppException,
			SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeSVO userDataRange = (UserDataRangeSVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// lilin[20080815] 多查出allow_flag对应的值
		Sql sql = new Sql(
				" SELECT ud.ALLOW_FLAG,ud.CREATE_DATE,ud.DATA_RANGE_ID,ud.RANGE_TYPE_ID,ud.STS,ud.STS_DATE,ud.SYS_USER_ID,ud.USER_DATA_AREA_ID ,");
		sql.append(" a.name  localNetName ,s.STS_WORDS ALLOW_FLAG_NAME ");
		sql.append(" FROM USER_DATA_RANGE ud  ");
		sql
				.append("  join local_net a  on ud.data_range_id=a.local_net_id and ud.range_type_id='L' ");
		sql
				.append("  join status s  on s.table_name='USER_DATA_RANGE' and s.column_name='ALLOW_FLAG' and ud.allow_flag=s.sts_id ");
		sql.append(" WHERE 1=1 ");
		try {
			if (userDataRange.getFlagAllowFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
					sql.append(" and ud.ALLOW_FLAG is null ");
				} else {
					sql.append(" and ud.ALLOW_FLAG=:allowFlag");
					sql.setString("allowFlag", userDataRange.getAllowFlag());
				}
			}

			if (userDataRange.getFlagCreateDate() == 1) {
				if (userDataRange.getCreateDate() == null) {
					sql.append(" and ud.CREATE_DATE is null ");
				} else {
					sql.append(" and ud.CREATE_DATE=:createDate");
					sql.setTimestamp("createDate", userDataRange
							.getCreateDate());
				}
			}

			if (userDataRange.getFlagDataRangeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
					sql.append(" and ud.DATA_RANGE_ID is null ");
				} else {
					sql.append(" and ud.DATA_RANGE_ID=:dataRangeId");
					sql.setLong("dataRangeId", userDataRange.getDataRangeId());
				}
			}

			if (userDataRange.getFlagRangeTypeId() == 1) {
				if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
					sql.append(" and ud.RANGE_TYPE_ID is null ");
				} else {
					sql.append(" and ud.RANGE_TYPE_ID=:rangeTypeId");
					sql
							.setString("rangeTypeId", userDataRange
									.getRangeTypeId());
				}
			}

			if (userDataRange.getFlagSts() == 1) {
				if (StringUtil.isBlank(userDataRange.getSts())) {
					sql.append(" and ud.STS is null ");
				} else {
					sql.append(" and ud.STS=:sts");
					sql.setString("sts", userDataRange.getSts());
				}
			}

			if (userDataRange.getFlagStsDate() == 1) {
				if (userDataRange.getStsDate() == null) {
					sql.append(" and ud.STS_DATE is null ");
				} else {
					sql.append(" and ud.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", userDataRange.getStsDate());
				}
			}

			// if (userDataRange.getFlagSysUserId() == 1) {
			// if (StringUtil.isBlank(userDataRange.getSysUserId())) {
			// sql.append(" and SYS_USER_ID is null ");
			// } else {
			if (userDataRange.getSysUserId() != null) {
				sql.append(" and ud.SYS_USER_ID=:sysUserId");
//				sql.setLong("sysUserId", userDataRange.getSysUserId());
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}
			// }
			// }

			if (userDataRange.getFlagUserDataAreaId() == 1) {
				if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
					sql.append(" and ud.USER_DATA_AREA_ID is null ");
				} else {
					sql.append(" and ud.USER_DATA_AREA_ID=:userDataAreaId");
					sql.setLong("userDataAreaId", userDataRange
							.getUserDataAreaId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setAllowFlag(rs.getString("ALLOW_FLAG"));
				user.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setSts(rs.getString("STS"));
				user.setStsDate(rs.getTimestamp("STS_DATE"));
				user.setSysUserId(rs.getString("SYS_USER_ID"));
				user.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
				user.setLocalNetName(rs.getString("localNetName"));
				user.setAllowFlagName(rs.getString("ALLOW_FLAG_NAME"));
				// end lilin[20080815]
				res.add(user);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == res.size())
			res = null;
		return res;
	}

	/**
	 * 获取员工
	 * 
	 * @param vo
	 *            <UserDataRangeMVO>
	 */
	public List getStaffs(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeMVO userDataRange = (UserDataRangeMVO) vo;
		List res = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql();
		sql
				.append(" select distinct sys.sys_user_id,sys.SYS_USER_NAME userName,p.name partyName ");
		
		// lijixu 2010-3-29 11:4:01 数据权限页面，根据工区联动查询该工区下的员工，没有关联staff_work_area表进行过滤
		// sql.append(" from SYS_USER sys,PARTY_ROLE  pr,STAFF st ,party p,work_area wa ");
		sql.append(" from SYS_USER sys,PARTY_ROLE  pr,STAFF st ,party p,work_area wa , STAFF_WORK_AREA SWA ");
		sql.append(" where 1=1 ");
		sql.append(" AND SWA.WORK_AREA_ID =WA.WORK_AREA_ID ");
		sql.append(" AND SWA.STAFF_ID = ST.STAFF_ID ");
		sql.append(" and sys.PARTY_ROLE_ID=pr.PARTY_ROLE_ID  ");
		sql.append(" and sys.PARTY_ROLE_TYPE_ID=pr.PARTY_ROLE_TYPE_ID ");
		sql.append(" and pr.PARTY_ID=st.PARTY_ID  ");
		sql.append(" and pr.party_id=p.party_id  ");
		sql
				.append(" and p.local_net_id=wa.local_net_id and p.area_id=wa.area_id ");
		sql.append(" and sys.party_role_type_id=7 ");
		try {
			if (userDataRange.getLocalNetFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getLocalNetId())) {
					sql.append(" and p.local_net_id is null ");
				} else {
					sql.append(" and p.local_net_id=:localNetId");
					sql.setLong("localNetId", Long.valueOf(userDataRange.getLocalNetId()));
				}
			}
			
			  if (userDataRange.getAreaFlag() == 1) { 
				  if(StringUtil.isBlank(userDataRange.getAreaId())) { 
					 // sql.append(" and p.area_id is null ");
					  } else { 
						  sql.append(" and  p.area_id=:areaId"); 
						  sql.setString("areaId",userDataRange.getAreaId()); 
						  } 
				  }
			 
			if (userDataRange.getWorkAreaFlag() == 1) {
				if (StringUtil.isBlank(userDataRange.getWorkAreaId())) {
				//	sql.append(" and wa.work_area_id is null ");
				} else {
					sql.append(" and wa.work_area_id=:workAreaId");
					sql.setString("workAreaId", userDataRange.getWorkAreaId());
				}
			}
			if (!StringUtil.isBlank(userDataRange.getSysUserId())) {
				sql.append(" and sys.sys_user_id != :sysUserId");
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}
			sql.append(" order by sys.sys_user_id ");
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setSysUserId(rs.getString("sys_user_id"));
				user.setUserName(rs.getString("userName").trim() + "("
						+ rs.getString("partyName").trim() + ")");
				res.add(user);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		if (0 == res.size())
			res = null;
		return res;
	}

	/**
	 * 根据传入参数删除一条或者一批记录。
	 * 
	 * @return String ： 删除的SQL。
	 */
	public void delete(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		UserDataRangeSVO userDataRange = (UserDataRangeSVO) vo;
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql("DELETE FROM USER_DATA_RANGE WHERE 1=1  ");
		if (userDataRange.getFlagUserDataAreaId() == 1) {
			if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
				sql.append(" and USER_DATA_AREA_ID is null ");
			} else {
				sql.append(" and USER_DATA_AREA_ID=:userDataAreaId");
				sql
						.setLong("userDataAreaId", userDataRange
								.getUserDataAreaId());
			}
		}
		// if(userDataRange.getFlagSysUserId()==1){
		// if(StringUtil.isBlank(userDataRange.getSysUserId())){
		// sql.append(" and sys_user_id is null ");
		// }else{
		// sql.append(" and sys_user_id=:sysUserId");
		// sql.setLong("sysUserId", userDataRange.getSysUserId());
		// }
		// }
		if (userDataRange.getSysUserId() != null) {
			sql.append(" and SYS_USER_ID=:sysUserId");
//			sql.setLong("sysUserId", userDataRange.getSysUserId());
			sql.setString("sysUserId", userDataRange.getSysUserId());
		}

		// lilin[20080815] 将range_type也做为条件，
		if (userDataRange.getFlagRangeTypeId() == 1) {
			sql.append(" and RANGE_TYPE_ID=:rangeTypeId ");
			sql.setString("rangeTypeId", userDataRange.getRangeTypeId());
		}
		// end lilin[20080815]
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 增加。
	 * 
	 * @return String
	 */
	public void addBat(List list) throws AppException, SysException {
		if (list == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		Sql sql = new Sql(
				"INSERT INTO USER_DATA_RANGE(ALLOW_FLAG,CREATE_DATE,DATA_RANGE_ID,RANGE_TYPE_ID,STS,STS_DATE,SYS_USER_ID,USER_DATA_AREA_ID)");
		sql
				.append(" VALUES (:allowFlag,:createDate,:dataRangeId,:rangeTypeId,:sts,:stsDate,:sysUserId,:userDataAreaId)");
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			for (int i = 0; i < list.size(); i++) {
				UserDataRangeSVO userDataRange = (UserDataRangeSVO) list.get(i);
				if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
					sql.setNullString("allowFlag");
				} else {
					sql.setString("allowFlag", userDataRange.getAllowFlag());
				}

				if (userDataRange.getCreateDate() == null) {
					sql.setNullDate("createDate");
				} else {
					sql.setTimestamp("createDate", userDataRange
							.getCreateDate());
				}

				if (StringUtil.isBlank(userDataRange.getDataRangeId())) {
					sql.setNullLong("dataRangeId");
				} else {
					sql.setLong("dataRangeId", userDataRange.getDataRangeId());
				}

				if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
					sql.setNullString("rangeTypeId");
				} else {
					sql
							.setString("rangeTypeId", userDataRange
									.getRangeTypeId());
				}

				if (StringUtil.isBlank(userDataRange.getSts())) {
					sql.setNullString("sts");
				} else {
					sql.setString("sts", userDataRange.getSts());
				}

				if (userDataRange.getStsDate() == null) {
					sql.setNullDate("stsDate");
				} else {
					sql.setTimestamp("stsDate", userDataRange.getStsDate());
				}

				if (StringUtil.isBlank(userDataRange.getSysUserId())) {
					sql.setNullString("sysUserId");
				} else {
//					sql.setLong("sysUserId", userDataRange.getSysUserId());
					sql.setString("sysUserId", userDataRange.getSysUserId());
				}

				if (StringUtil.isBlank(userDataRange.getUserDataAreaId())) {
					sql.setNullLong("userDataAreaId");
				} else {
					sql.setLong("userDataAreaId", userDataRange
							.getUserDataAreaId());
				}

				sql.fillParams(ps);
				sql.log(this.getClass());
				sql.clearParameters();
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	/**
	 * 按照用户权限查询本地网或者服务区
	 * 
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findDataRange(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		List res = null;
		UserDataRangeMVO userDataRange = (UserDataRangeMVO) vo;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuffer selectSql = new StringBuffer("SELECT");
		selectSql.append(" UDR.USER_DATA_AREA_ID,");
		selectSql.append("UDR.SYS_USER_ID,");
		selectSql.append("UDR.RANGE_TYPE_ID,");
		selectSql.append("UDR.DATA_RANGE_ID,");
		selectSql.append("UDR.ALLOW_FLAG,");
		selectSql.append("UDR.CREATE_DATE,");
		selectSql.append("UDR.STS,UDR.STS_DATE");

		Sql sql = new Sql(" WHERE UDR.STS='A' ");
		if (userDataRange.getFlagAllowFlag() == 1) {
			if (StringUtil.isBlank(userDataRange.getAllowFlag())) {
				sql.append(" AND UDR.ALLOW_FLAG IS NULL ");
			} else {
				sql.append(" AND UDR.ALLOW_FLAG=:allowFlag");
				sql.setString("allowFlag", userDataRange.getAllowFlag());
			}
		}

		if (userDataRange.getFlagRangeTypeId() == 1) {
			if (StringUtil.isBlank(userDataRange.getRangeTypeId())) {
				sql.append(" AND UDR.RANGE_TYPE_ID IS NULL ");
			} else {
				sql.append(" AND UDR.RANGE_TYPE_ID=:rangeTypeId");
				sql.setString("rangeTypeId", userDataRange.getRangeTypeId());
			}
		}

		if (userDataRange.getFlagSysUserId() == 1) {
			if (StringUtil.isBlank(userDataRange.getSysUserId())) {
				sql.append(" AND UDR.SYS_USER_ID IS NULL ");
			} else if (userDataRange.getSysUserId() != null) {
				sql.append(" AND UDR.SYS_USER_ID=:sysUserId");
//				sql.setLong("sysUserId", userDataRange.getSysUserId());
				sql.setString("sysUserId", userDataRange.getSysUserId());
			}
		}

		if (SysConstants.AREA_TYPE_LOCAL_NET.equals(userDataRange
				.getRangeTypeId())) {
			selectSql.append(" , LN.NAME RANGE_NAME");
			selectSql.append(" FROM USER_DATA_RANGE UDR");
			selectSql.append(" , LOCAL_NET LN");

			sql.append(" AND UDR.RANGE_TYPE_ID=:rangeTypeId ");
			sql.append(" AND UDR.DATA_RANGE_ID=LN.LOCAL_NET_ID ");
			sql.append(" AND LN.STS='A' ");
			sql.setString("rangeTypeId", userDataRange.getRangeTypeId());

			sql.append(" ORDER BY LN.LOCAL_NET_ID ");

		} else if (SysConstants.AREA_TYPE_AREA.equals(userDataRange
				.getRangeTypeId())) {
			selectSql.append(" , AREA.NAME RANGE_NAME");
			selectSql.append(" FROM USER_DATA_RANGE UDR");
			selectSql.append(" , AREA");

			sql.append(" AND UDR.RANGE_TYPE_ID=:rangeTypeId ");
			sql.append(" AND UDR.DATA_RANGE_ID=AREA.AREA_ID ");
			sql.append(" AND AREA.STS='A' ");
			sql.setString("rangeTypeId", userDataRange.getRangeTypeId());

			if (userDataRange.getLocalNetId() != null) {
				sql.append(" AND (AREA.LOCAL_NET_ID=:localNetId");
				sql.setLong("localNetId", userDataRange.getLocalNetId());
				/**
				 * 配置数据时，读取所有本地网对应的服务区
				 */
				if (SysConstants.ALLOW_FLAG_READ.equals(userDataRange
						.getAllowFlag())) {
					sql.append(" OR AREA.LOCAL_NET_ID=0 ");
				}
				sql.append(" )");
			}
			sql.setString("rangeTypeId", userDataRange.getRangeTypeId());

			sql.append(" ORDER BY AREA.AREA_ID ");
		} else {
			selectSql.append(" , '' RANGE_NAME");
		}

		sql.insert(0, selectSql.toString());
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());

			rs = ps.executeQuery();
			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
				user.setSysUserId(rs.getString("SYS_USER_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setAllowFlag(rs.getString("ALLOW_FLAG"));
				user.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				user.setSts(rs.getString("STS"));
				user.setStsDate(rs.getTimestamp("STS_DATE"));
				user.setAreaName(rs.getString("RANGE_NAME"));
				if (res == null) {
					res = new ArrayList();
				}
				res.add(user);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
		return res;
	}
	
	/**
	 * 功能：按照用户权限查询本地网
	 * 
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findLocalNetByRange(GenericVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		
		UserDataRangeMVO userDataRange = (UserDataRangeMVO) vo;
		
		if(StringUtil.isBlank(userDataRange.getWorkTypeId())){
			throw new AppException("100001","缺少WorkTypeId查询条件");
		}
		
		if(StringUtil.isBlank(userDataRange.getSysUserId())){
			throw new AppException("100001","缺少SysUserId查询条件");
		}
		
		List res = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Sql sql = new Sql("SELECT UDR.USER_DATA_AREA_ID,UDR.SYS_USER_ID,UDR.RANGE_TYPE_ID,UDR.DATA_RANGE_ID,UDR.ALLOW_FLAG,UDR.CREATE_DATE, ");
		sql.append("UDR.STS,UDR.STS_DATE,LN.NAME LOCAL_NET_NAME,LN.LOCAL_NET_ID ");
		sql.append("FROM LOCAL_NET LN,WORK_AREA WA,STAFF_WORK_AREA SWA,USER_DATA_RANGE UDR,STAFF S,SYS_USER SU,PARTY P,PARTY_ROLE PR ");
		sql.append("WHERE LN.LOCAL_NET_ID = WA.LOCAL_NET_ID AND SWA.WORK_AREA_ID = WA.WORK_AREA_ID ");
		sql.append("AND UDR.DATA_RANGE_ID = LN.LOCAL_NET_ID AND S.STAFF_ID = SWA.STAFF_ID ");
		sql.append("AND P.PARTY_ID = PR.PARTY_ID AND PR.PARTY_ROLE_ID = SU.PARTY_ROLE_ID ");
		sql.append("AND SU.SYS_USER_ID = UDR.SYS_USER_ID AND S.PARTY_ID = P.PARTY_ID ");
		sql.append("AND LN.STS = 'A' AND WA.STS = 'A' AND SWA.STS = 'A' AND UDR.STS = 'A' ");
		sql.append("AND S.STS = 'A' AND SU.STS = 'A' AND P.STS = 'A' ");
		sql.append("AND UDR.RANGE_TYPE_ID = 'L' AND UDR.ALLOW_FLAG = 'Q' ");
		sql.append("AND WA.WORK_TYPE_ID =:workTypeId ");
		sql.setString("workTypeId",userDataRange.getWorkTypeId());
		sql.append("AND SU.SYS_USER_ID =:sysUserId ");
		sql.setString("sysUserId",userDataRange.getSysUserId());
		sql.append(" ORDER BY LN.LOCAL_NET_ID");
		
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());

			rs = ps.executeQuery();
			while (rs.next()) {
				UserDataRangeMVO user = new UserDataRangeMVO();
				user.setUserDataAreaId(rs.getString("USER_DATA_AREA_ID"));
				user.setSysUserId(rs.getString("SYS_USER_ID"));
				user.setRangeTypeId(rs.getString("RANGE_TYPE_ID"));
				user.setDataRangeId(rs.getString("DATA_RANGE_ID"));
				user.setAllowFlag(rs.getString("ALLOW_FLAG"));
				user.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				user.setSts(rs.getString("STS"));
				user.setStsDate(rs.getTimestamp("STS_DATE"));
				user.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				user.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				
				if (res == null) {
					res = new ArrayList();
				}
				res.add(user);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(ps);
		}
		return res;
	}
}
