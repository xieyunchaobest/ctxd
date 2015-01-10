package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.IAppPermissionMDAO;
import com.cattsoft.sm.vo.AppPermissionMVO;

public class AppPermissionMDAOImpl implements IAppPermissionMDAO {

	private static Logger log = Logger.getLogger(AppPermissionMDAOImpl.class);

	/**
	 * 批量查询的SQL。
	 * 
	 * @return String ： 批量查询的SQL。
	 */
	public List findByVO(AppPermissionMVO mvo) throws AppException,
			SysException {

		if (mvo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}

		AppPermissionMVO appPermission = (AppPermissionMVO) mvo;
		List list = (List) CollectionFactory
				.createCollection(CollectionFactory.COLLECTION_LIST);

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Sql sql = new Sql(
				"SELECT P.PERMISSION_APP_ID, P.COMMON_ID, P.APP_ID, P.PERMISSION, P.STS, P.STS_DATE, P.LOCAL_NET_ID, P.DEFAULT_APP, A.APP_NAME, A.APP_INFO_ID FROM APP_INFO A, PERMISSION_APP P WHERE 1=1 AND P.APP_ID = A.APP_ID ");
		try {
			if (appPermission.getFlagAppId() == 1) {
				if (StringUtil.isBlank(appPermission.getAppId())) {
					sql.append(" and P.APP_ID is null ");
				} else {
					sql.append(" and P.APP_ID=:appId");
					sql.setString("appId", appPermission.getAppId());
				}
			}

			if (appPermission.getFlagAppInfoId() == 1) {
				if (StringUtil.isBlank(appPermission.getAppInfoId())) {
					sql.append(" and P.APP_INFO_ID is null ");
				} else {
					sql.append(" and P.APP_INFO_ID=:appInfoId");
					sql.setString("appInfoId", appPermission.getAppInfoId());
				}
			}

			if (appPermission.getFlagAppName() == 1) {
				if (StringUtil.isBlank(appPermission.getAppName())) {
					sql.append(" and A.APP_NAME is null ");
				} else {
					sql.append(" and A.APP_NAME=:appName");
					sql.setString("appName", appPermission.getAppName());
				}
			}

			if (appPermission.getFlagSts() == 1) {
				if (StringUtil.isBlank(appPermission.getSts())) {
					sql.append(" and P.STS is null ");
				} else {
					sql.append(" and P.STS=:sts");
					sql.setString("sts", appPermission.getSts());
				}
			}

			if (appPermission.getFlagStsDate() == 1) {
				if (appPermission.getStsDate() == null) {
					sql.append(" and P.STS_DATE is null ");
				} else {
					sql.append(" and P.STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", appPermission.getStsDate());
				}
			}

			if (appPermission.getFlagCommonId() == 1) {
				if (appPermission.getCommonId() == null) {
					sql.append(" and P.COMMON_ID is null ");
				} else {
					sql.append(" and P.COMMON_ID=:commonId");
					sql.setString("commonId", appPermission.getCommonId());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				appPermission = new AppPermissionMVO();
				appPermission.setAppId(rs.getString("APP_ID"));
				appPermission.setAppName(rs.getString("APP_NAME"));
				appPermission.setSts(rs.getString("STS"));
				appPermission.setStsDate(rs.getTimestamp("STS_DATE"));
				appPermission.setAppInfoId(rs.getString("APP_INFO_ID"));
				appPermission.setPermissionAppId(rs
						.getString("PERMISSION_APP_ID"));
				list.add(appPermission);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		if (0 == list.size())
			list = null;
		return list;
	}

	public List findAppInfo(AppPermissionMVO mvo) throws AppException,
			SysException {
		if (mvo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		AppPermissionMVO appPermissionMVO = (AppPermissionMVO) mvo;
		List res = (List) CollectionFactory
				.createCollection(CollectionFactory.COLLECTION_LIST);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT APP_ID,APP_INFO_ID,APP_NAME,STS,STS_DATE,URL FROM APP_INFO WHERE 1=1 ");
		try {
			if (appPermissionMVO.getFlagAppId() == 1) {
				if (StringUtil.isBlank(appPermissionMVO.getAppId())) {
					sql.append(" and APP_ID is null ");
				} else {
					sql.append(" and APP_ID=:appId");
					sql.setString("appId", appPermissionMVO.getAppId());
				}
			}

			if (appPermissionMVO.getFlagAppInfoId() == 1) {
				if (StringUtil.isBlank(appPermissionMVO.getAppInfoId())) {
					sql.append(" and APP_INFO_ID is null ");
				} else {
					sql.append(" and APP_INFO_ID=:appInfoId");
					sql.setLong("appInfoId", appPermissionMVO.getAppInfoId());
				}
			}

			if (appPermissionMVO.getFlagAppName() == 1) {
				if (StringUtil.isBlank(appPermissionMVO.getAppName())) {
					sql.append(" and APP_NAME is null ");
				} else {
					sql.append(" and APP_NAME=:appName");
					sql.setString("appName", appPermissionMVO.getAppName());
				}
			}

			if (appPermissionMVO.getFlagSts() == 1) {
				if (StringUtil.isBlank(appPermissionMVO.getSts())) {
					sql.append(" and STS is null ");
				} else {
					sql.append(" and STS=:sts");
					sql.setString("sts", appPermissionMVO.getSts());
				}
			}

			if (appPermissionMVO.getFlagStsDate() == 1) {
				if (appPermissionMVO.getStsDate() == null) {
					sql.append(" and STS_DATE is null ");
				} else {
					sql.append(" and STS_DATE=:stsDate");
					sql.setTimestamp("stsDate", appPermissionMVO.getStsDate());
				}
			}

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				appPermissionMVO = new AppPermissionMVO();
				appPermissionMVO.setAppId(rs.getString("APP_ID"));
				appPermissionMVO.setAppInfoId(rs.getString("APP_INFO_ID"));
				appPermissionMVO.setAppName(rs.getString("APP_NAME"));
				appPermissionMVO.setSts(rs.getString("STS"));
				appPermissionMVO.setStsDate(rs.getTimestamp("STS_DATE"));
				res.add(appPermissionMVO);

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
}
