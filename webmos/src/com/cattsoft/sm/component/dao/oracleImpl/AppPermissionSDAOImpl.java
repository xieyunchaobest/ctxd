package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IAppPermissionSDAO;
import com.cattsoft.sm.vo.AppPermissionSVO;

public class AppPermissionSDAOImpl implements IAppPermissionSDAO {

	private static Logger log = Logger.getLogger(AppPermissionSDAOImpl.class);

	public void add(GenericVO vo) throws AppException, SysException {

		// TODO Auto-generated method stub

		AppPermissionSVO appPermissionSVO = (AppPermissionSVO) vo;

		StringBuffer sql = new StringBuffer("insert into");
		sql
				.append(" PERMISSION_APP(PERMISSION_APP_ID, COMMON_ID, APP_ID, PERMISSION, STS, STS_DATE, LOCAL_NET_ID, DEFAULT_APP) values(?,?,?,?,?,?,?,?)");

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appPermissionSVO.getPermissionAppId());
			ps.setString(2, appPermissionSVO.getCommonId());
			ps.setString(3, appPermissionSVO.getAppId());
			ps.setString(4, appPermissionSVO.getPermission());
			ps.setString(5, appPermissionSVO.getSts());
			ps.setDate(6, appPermissionSVO.getStsDate());
			ps.setString(7, appPermissionSVO.getLocalNetId());
			ps.setString(8, appPermissionSVO.getDefaultApp());
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

	public void delete(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub

		AppPermissionSVO appPermissionSVO = (AppPermissionSVO) vo;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM PERMISSION_APP A ");
		sql.append("WHERE 1=1 ");
		sql.append("and A.COMMON_ID = ? ");
		sql.append("and A.APP_ID = ? ");

		Connection connection = null;
		PreparedStatement ps = null;

		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, appPermissionSVO.getCommonId());
			ps.setString(2, appPermissionSVO.getAppId());
			ps.execute();

		} catch (SQLException e) {
			throw new SysException("", "del error..", e);
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
		// TODO Auto-generated method stub
		return null;
	}

	public List findByVO(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(GenericVO vo) throws AppException, SysException {
		// TODO Auto-generated method stub
		
	}
}