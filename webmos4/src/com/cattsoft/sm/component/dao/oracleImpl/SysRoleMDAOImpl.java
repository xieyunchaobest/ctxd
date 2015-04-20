package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.ISysRoleMDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.SysRoleMVO;
import com.cattsoft.sm.vo.SysRoleSVO;
import com.cattsoft.sm.vo.SysUserRoleSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-21 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author Administrator
 */
public class SysRoleMDAOImpl implements ISysRoleMDAO {

	private static Log log = LogFactory.getLog(SysRoleMDAOImpl.class);

	public List findLatestSysRole(HashSet set) throws SysException, AppException, Exception {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("SELECT a.sys_role_id,a.sys_role_name,a.role_memo,a.local_net_id,a.area_id,a.create_date,a.sts,a.sts_date  ");
		sql.append(" from  sys_role a  ");
		sql.append("where  ( ");
		sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "sys_role_id", "sysRoleId"));
		sql.append(" ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

	public List findSysRoleAllowAuth2(String sysUserId, String localNetId, String areaId,
			SysRoleSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select distinct sr.sys_role_id,sr.sys_role_name,sr.role_memo,sr.local_net_id,sr.area_id,sr.create_date,sr.sts,sr.sts_date   ");
		sql.append("from sys_role sr,area a,local_net l   ");
		sql.append("where sr.sts='A' ");
		sql.append("and ((sr.area_id=a.area_id and a.iscenter='Y' and sr.local_net_id= ?  ");
		sql.append(") or ( sr.local_net_id=l.local_net_id and l.iscenter='Y')) ");
		sql.append("order by sr.sys_role_id   ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (localNetId != null) {
				ps.setString(index++, localNetId);
			}
			log.debug("findSysRoleAllowAuth2 " + sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

	public List findSysRoleAllowAuth3(String sysUserId, String localNetId, String areaId,
			SysRoleSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select distinct sr.sys_role_id,sr.sys_role_name,sr.role_memo,sr.local_net_id,sr.area_id,sr.create_date,sr.sts,sr.sts_date    ");
		sql.append("from sys_role sr,area a,local_net l  ");
		sql.append("where sr.sts='A'  ");
		sql
				.append("and ((sr.area_id=a.area_id and a.iscenter='Y') or( sr.local_net_id=l.local_net_id and l.iscenter='Y' ) ");
		sql.append("or ( sr.local_net_id= ?   ");
		sql.append("and sr.area_id = ?   ");
		sql.append(")) ");
		sql.append("order by sr.sys_role_id   ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (localNetId != null) {
				ps.setString(index++, localNetId);
			}
			if (areaId != null) {
				ps.setString(index++, areaId);
			}
			log.debug("findSysRoleAllowAuth3 " + sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

	public List findSysUserRole(SysUserRoleSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Sql sql = new Sql();
		sql.append("select sr.sys_role_id,sr.sys_role_name,sr.role_memo,sr.local_net_id,sr.area_id,sr.create_date,sr.sts,sr.sts_date    ");
		sql.append("from sys_user_role sur, sys_role sr    ");
		sql.append("where sur.sys_role_id=sr.sys_role_id  ");
		sql.append("and sr.sts='A'   ");
		sql.append("and sur.sts='A'  ");		
        if(!StringUtil.isBlank(vo.getSysUserId())){
        	sql.append("and sur.sys_user_id=:sysUserId   ");
        	sql.setString("sysUserId", vo.getSysUserId());
        }
        sql.append("order by sr.sys_role_id   ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

	public List findSysRoleMVO(SysRoleSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select sr.sys_role_id,sr.sys_role_name,sur.sys_user_role_id,su.sys_user_id,su.sys_user_name,st.staff_id,pt.name party_name,od.dept_id,od.dept_name ");
		sql
				.append("from sys_role sr,sys_user_role sur,sys_user su,staff st,party pt,org_dept od    ");
		sql
				.append("where sur.sys_role_id=sr.sys_role_id and sur.sys_user_id=su.sys_user_id and su.party_role_id=st.staff_id and st.dept_id=od.dept_id and st.party_id=pt.party_id  ");
		sql
				.append("and sr.sts='A' and sur.sts='A' and su.sts='A' and st.sts='A' and pt.sts='A' and od.sts='A' ");
		if (vo.getLocalNetId() != null && !vo.getLocalNetId().equals("")) {
			sql.append(" and sr.local_net_id=? ");
			sql.append(" and pt.local_net_id=? ");
		}
		if (vo.getAreaId() != null && !vo.getAreaId().equals("")) {
			sql.append(" and sr.area_id=? ");
			sql.append(" and pt.area_id=? ");
		}
		if (vo.getSysRoleId() != null && !vo.getSysRoleId().equals("")) {
			sql.append(" and sr.sys_role_id=? ");
		}
		sql.append("order by su.sys_user_id   ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (vo.getLocalNetId() != null && !vo.getLocalNetId().equals("")) {
				ps.setString(index++, vo.getLocalNetId());
				ps.setString(index++, vo.getLocalNetId());

			}
			if (vo.getAreaId() != null && !vo.getAreaId().equals("")) {
				ps.setString(index++, vo.getAreaId());
				ps.setString(index++, vo.getAreaId());
			}
			if (vo.getSysRoleId() != null && !vo.getSysRoleId().equals("")) {
				ps.setString(index++, vo.getSysRoleId());
			}

			log.debug("findSysRoleAllowAuth2 " + sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleMVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

	public List findSysRoleAllowAuth(SysRoleSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("select distinct sr.sys_role_id,sr.sys_role_name,sr.role_memo,sr.local_net_id,sr.area_id,sr.create_date,sr.sts,sr.sts_date   ");
		sql.append("from sys_role sr ");
		sql.append("where sr.sts='A' ");
		if (vo.getLocalNetId() != null)
			sql.append(" and sr.local_net_id= ?  ");
		if (vo.getAreaId() != null)
			sql.append(" and sr.area_id=? ");
		sql.append("order by sr.sys_role_id   ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (vo.getLocalNetId() != null) {
				ps.setString(index++, vo.getLocalNetId());
			}
			if (vo.getAreaId() != null) {
				ps.setString(index++, vo.getAreaId());
			}
			log.debug("findSysRoleAllowAuth " + sql.toString());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
		} catch (Exception e) {
			e.printStackTrace();
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
		return vos;

	}

	public List findSysRoleBySysUserId(SysRoleSVO vo, String sysUserId)
			throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Sql sql = new Sql();
		sql
				.append("select distinct sr.sys_role_id,sr.sys_role_name,sr.role_memo,sr.local_net_id,sr.area_id,sr.create_date,sr.sts,sr.sts_date   ");
		sql.append("from sys_role sr,sys_user_role b ");
		sql.append("where sr.sts='A' ");
		sql.append(" and sr.sys_role_id=b.sys_role_id ");
		sql.append(" and b.sts='A' ");
		if (vo.getLocalNetId() != null)
			sql
					.append(" and (sr.local_net_id= ? or sr.local_net_id in (select local_net_id from local_net where iscenter='Y' and sts='A')) ");
		if (sysUserId != null)
			sql.append(" and b.sys_user_id=? ");
		sql.append("order by sr.sys_role_id   ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getPartitionSql(null));
			int index = 1;
			if (vo.getLocalNetId() != null) {
				sql.setString(index++, vo.getLocalNetId());
			}

			if (sysUserId != null)
				sql.setString(index++, sysUserId);
			sql.fillParams(ps);
			sql.logPartition(null, this.getClass());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysRoleSVO.class);
		} catch (Exception e) {
			throw new SysException("", "", e);
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
		return vos;
	}

}
