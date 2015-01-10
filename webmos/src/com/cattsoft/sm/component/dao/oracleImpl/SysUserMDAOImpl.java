package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.Sql;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.CollectionFactory;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.ISysUserMDAO;
import com.cattsoft.sm.vo.ConstraintAndPriviledgeSVO;
import com.cattsoft.sm.vo.FuncNodeSVO;
import com.cattsoft.sm.vo.PartyRoleMVO;
import com.cattsoft.sm.vo.PartyRoleTypeSVO;
import com.cattsoft.sm.vo.PartySVO;
import com.cattsoft.sm.vo.StaffMVO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.sm.vo.SysUserAllocSVO;
import com.cattsoft.sm.vo.SysUserExtMVO;
import com.cattsoft.sm.vo.SysUserMVO;
import com.cattsoft.sm.vo.SysUserSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SysUserMDAOImpl implements ISysUserMDAO {

	private static Log log = LogFactory.getLog(SysUserMDAOImpl.class);

	public SysUserMVO findSysUserByNamePwd(SysUserMVO vo) {
		SysUserMVO retVO = null;
		try {

			// vo.setPassword(PasswordUtil.getMD5Str(vo.getPassword()));//改为前端加密
			vo.setSts("A");
			List lst = this.findSysUsers(vo);
			if (log.isDebugEnabled())
				log.debug("根据用户名和密码查询到的用户数:" + lst.size());
			if (lst.size() > 0)
				retVO = (SysUserMVO) lst.get(0);

			log.debug("dao end.............");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retVO;
	}

	/**
	 * 查询所有SysUser，除去所有项。
	 * 
	 * @param vo
	 *            SysUserVO
	 * @throws SysException
	 * @throws AppException 
	 */

	public List findSysUsers(SysUserMVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		// get query total no;
		Sql sql = new Sql();
		sql
				.append("select a.SYS_USER_ID,a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.SYS_USER_NAME,a.PASSWORD,a.SET_PWD_TIME,a.UPDATE_PWD_TIME,a.LAST_PWD, ");
		sql
				.append("a.CREATE_DATE,a.STS,a.STS_DATE,p.name,p.local_net_id,p.area_id,p.sts party_Sts,p.branch_Id,p.serv_Dept_Id,p.party_Id,p.party_Type,p.sts_Date party_Sts_Date from Sys_User a,Party_Role pr,Party p ");
		sql
				.append("where 1=1 and a.party_role_id=pr.party_role_id and a.party_role_type_id=pr.party_role_type_id and pr.party_id=p.party_id ");

		if (!StringUtil.isBlank(vo.getSysUserId())) {
			sql.append(" and a.sys_user_id=:sysUserId");
			sql.setString("sysUserId", vo.getSysUserId());
		}
		if (!StringUtil.isBlank(vo.getSysUserName())) {
			sql.append(" and a.sys_user_name =:sysUserName");
			sql.setString("sysUserName", vo.getSysUserName());
		}
		if (!StringUtil.isBlank(vo.getPassword())) {
			sql.append(" and a.password =:password ");
			sql.setString("password", vo.getPassword());
		}

		if (!StringUtil.isBlank(vo.getPartyRoleTypeId())) {
			sql.append(" and a.party_role_type_id=:partyRoleTypeId ");
			sql.setString("partyRoleTypeId", vo.getPartyRoleTypeId());
		}
		if (!StringUtil.isBlank(vo.getPartyRoleId())) {
			sql.append(" and a.party_role_id=:partyRoleId ");
			sql.setString("partyRoleId", vo.getPartyRoleId());
		}

		if (!StringUtil.isBlank(vo.getSts())) {
			sql.append(" and a.sts=:sts ");
			sql.setString("sts", vo.getSts());
		}
		// 以下条件为查某本地网的系统用户
		if (!StringUtil.isBlank(vo.getLocalNetId())) {
			sql.append(" and p.local_net_id=:localNetId ");
			sql.setString("localNetId", vo.getLocalNetId());
		}
		if (!StringUtil.isBlank(vo.getName())) {
			sql.append(" and p.name like '%" + vo.getName() + "%'");
		}

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, SysUserMVO.class);
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

	public PagView findSysUsersForPage(SysUserExtMVO voExt, PagInfo pagInfo) {
		return null;
	}

	public int findSysUsersSts(String sysUserName) throws SysException, AppException {
		int size = 0;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select  a.SYS_USER_ID,a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.SYS_USER_NAME,a.PASSWORD,a.SET_PWD_TIME,a.UPDATE_PWD_TIME,a.LAST_PWD,a.CREATE_DATE,a.STS,a.STS_DATE from Sys_User a,Login_Log b where a.sts= 'P' and a.sys_User_Id=b.sys_User_Id and a.sys_User_Name=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List results;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, sysUserName);
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, SysUserSVO.class);
			if (results != null) {
				size = results.size();
			}
		} catch (SQLException e) {
			throw new SysException("", "findSysUsersSts error..", e);
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
		return size;
	}

	public PagView findSysUsersBySysUserForPage(SysUserExtMVO voExt, PagInfo pagInfo)
			throws SysException, AppException {
		SysUserSVO vo = voExt.getSvo();
		StringBuffer sql = new StringBuffer();
		//增加终端配置列  add by liangyongxing 2013-7-3  -->s.terminal_flag 
		sql
				.append(" select su.sys_user_id,pr.party_role_id,p.name,od.dept_id,od.dept_name,s.staff_id,s.terminal_flag,su.sts,su.sys_user_name  ");
		sql.append(" from party p, sys_user su,party_role pr,org_dept od,staff s  ");
		sql.append(" where su.party_role_id=pr.party_role_id  ");
		sql.append(" and su.party_role_type_id=pr.party_role_type_id  ");
		sql.append(" and pr.party_id=p.party_id  ");
		if (vo.getSysUserId() != null) {
			sql.append(" and su.sys_user_id=" + vo.getSysUserId());
		}
		if (vo.getSysUserName() != null) {
			sql.append(" and su.sys_user_name like '%" + vo.getSysUserName() + "%'");
		}
		if (vo.getPassword() != null) {
			sql.append(" and su.password=" + vo.getPassword());
		}
		if (vo.getPartyRoleTypeId() != null) {
			sql.append(" and su.party_role_type_id=" + vo.getPartyRoleTypeId());
		}
		if (vo.getPartyRoleId() != null) {
			sql.append(" and su.party_role_id=" + vo.getPartyRoleId());
		}
		if (vo.getSts() != null) {
			sql.append(" and su.sts='" + vo.getSts() + "'");
		}
		// 以下条件为查某本地网的系统用户
		if (voExt.getLocalNetId() != null) {
			sql.append(" and p.local_net_id=" + voExt.getLocalNetId());
			if (voExt.getAreaId() != null) {
				sql.append(" and p.area_id=" + voExt.getAreaId());
			}
		}
			if (voExt.getPartyName() != null) {
				sql.append(" and p.name like '%" + voExt.getPartyName() + "%'");
			}
		
		sql.append(" and su.party_role_id=s.staff_id  ");
		sql.append(" and s.dept_id=od.dept_id  ");
		sql.append(" order by su.sys_user_id  ");

		Connection connection = null;
		PreparedStatement ps = null;
		PagView pagView = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			Sql sql2 = new Sql();
			sql2.setSql(sql.toString());
			sql2.log(SysUserMDAOImpl.class);
			log.debug("sql :" + sql.toString());
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);

			while (rs.next()) {
				SysUserExtMVO suemv = new SysUserExtMVO();
				SysUserSVO susc = new SysUserSVO();
				PartyRoleMVO prmv = new PartyRoleMVO();
				PartySVO psv = new PartySVO();
				StaffMVO msv = new StaffMVO();
				StaffSVO ssv = new StaffSVO();
				PartyRoleTypeSVO tvo = new PartyRoleTypeSVO();
				susc.setSysUserId(rs.getString("sys_user_id"));
				susc.setSts(rs.getString("sts"));
				susc.setSysUserName(rs.getString("sys_user_name"));
				psv.setName(rs.getString("name"));
				prmv.setPartyRoleId(rs.getString("party_role_id"));
				ssv.setDeptId(rs.getString("dept_id"));
				msv.setDeptName(rs.getString("dept_name"));
				ssv.setStaffId(rs.getString("staff_id"));
				ssv.setTerminalFlag(rs.getString("terminal_flag"));

				suemv.setSvo(susc);
				suemv.setPvo(prmv);
				suemv.setTvo(tvo);
				prmv.setStaffMVO(msv);
				msv.setStaffSVO(ssv);
				prmv.setPartySVO(psv);
				vos.add(suemv);
			}
			pagView.setViewList(vos);

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
		return pagView;
	}

	public List findSysUserAllocListBySysUserId(SysUserAllocSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		// get query total no;
		Sql sql = new Sql();
		sql
				.append("select fn.func_node_id,fn.node_tree_id,fn.func_node_code,fn.func_node_name,fn.sub_system_name,fn.security_level,fn.func_node_type,fn.html,fn.file_name,fn.version,fn.description,fn.short_cut_image,fn.sts,fn.sts_date  ");
		sql.append("from sys_user_alloc sua,func_node fn  ");
		sql.append("where sua.func_node_id=fn.func_node_id  ");
		sql.append("and sua.sts='A'  ");
		sql.append("and fn.sts='A' ");
		sql.append("and sua.sys_role_id is null ");

		if (vo.getSysUserId() != null) {
			sql.append(" and sua.sys_user_id=:sysUserId  ");
			sql.setString("sysUserId", vo.getSysUserId());
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
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

	public List findSysRoleAllocListBySysUserId(SysUserAllocSVO vo) throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		// get query total no;
		Sql sql = new Sql();
		sql
				.append(" select fn.func_node_id,fn.node_tree_id,fn.func_node_code,fn.func_node_name,fn.sub_system_name,fn.security_level,fn.func_node_type,fn.html,fn.file_name,fn.version,fn.description,fn.short_cut_image,fn.sts,fn.sts_date  ");
		sql.append(" from sys_user_alloc sua, func_node fn  ");
		sql.append(" where sua.func_node_id = fn.func_node_id  ");
		sql.append(" and sua.sts = 'A'  ");
		sql.append(" and fn.sts = 'A' ");
		sql.append(" and sua.sys_role_id is not null  ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (!StringUtil.isBlank(vo.getSysUserId())) {
				sql.append(" and sua.sys_user_id=:sysUserId  ");
				sql.setString("sysUserId", vo.getSysUserId());
			}

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
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

	public List findConstraintAndPriviledgeListBySysUserId(ConstraintAndPriviledgeSVO vo)
			throws SysException, AppException {
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		// get query total no;
		Sql sql = new Sql();
		sql
				.append("select fn.func_node_id,fn.node_tree_id,fn.func_node_code,fn.func_node_name,fn.sub_system_name,fn.security_level,fn.func_node_type,fn.html,fn.file_name,fn.version,fn.description,fn.short_cut_image,fn.sts,fn.sts_date  ");
		sql.append("from Constraint_And_Priviledge cap,func_node fn  ");
		sql.append("where cap.func_node_id=fn.func_node_id  ");
		sql.append("and cap.sts='A' ");
		sql.append("and cap.flag='C'  ");
		sql.append("and fn.sts='A' ");
		if (!StringUtil.isBlank(vo.getSysUserId())) {
			sql.append("and cap.sys_user_id=:sysUserId  ");
			sql.setString("sysUserId", vo.getSysUserId());
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			vos = (List) ResultSetUtil.convertToList(rs, FuncNodeSVO.class);
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

	/**
	 * 获得领单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public String getFetchOrderCount(String localNetId, String areaId, String workAreaId)
			throws AppException, SysException {
		// StringBuffer sql = new StringBuffer(" SELECT " + " count(*) orderCount "
		// + " FROM " + " WO wo,SO so,step sp " + " WHERE 1=1 "
		// + " and wo.LOCAL_NET_ID=? " + " and wo.AREA_ID=? and wo.RUN_STS='D' "
		// + " and wo.SO_NBR = so.SO_NBR AND wo.BUSI_STS ='N' "
		// + " AND SO.SO_LOCK_STS ='N' "
		// + " AND so.SO_STS IN ('P','g','h','F') "
		// + " and wo.step_id=sp.step_id "
		// + " AND sp.work_mode='M' "
		// + " ORDER BY wo.SO_NBR");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT WO.WO_TYPE,count(*) orderCount ");
		sql.append(" FROM WO WO, SO SO ");
		sql.append(" WHERE WO.LOCAL_NET_ID =?  AND WO.SO_NBR = SO.SO_NBR AND WO.AREA_ID =? ");
		if (!StringUtil.isBlank(workAreaId)) {
			sql.append(" AND WO.WORK_AREA_ID = ? ");
		}
		sql.append(" AND WO.RUN_STS = 'D' AND WO.BUSI_STS = 'N' AND WO.BOOK_FLAG = 'N' ");
		sql.append(" AND (WO.WO_TYPE = 'N' OR WO.WO_TYPE = 'F' OR WO.WO_TYPE = 'B') ");
		// sql.append(" AND (WO.MAIN_FLAG ='M' OR WO.MAIN_FLAG ='N') AND (WO.MERG_FLAG = 'Y' OR
		// WO.MERG_FLAG = 'W') ");
		sql.append(" AND SO.STS = 'A' ");
		sql.append(" AND (SO.SO_STS = 'P' OR SO.SO_STS = 'g' OR SO.SO_STS = 'h' OR SO.SO_STS = 'F' ) ");
		sql.append(" AND SO.SO_LOCK_STS = 'N' GROUP BY WO.WO_TYPE ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		int appendOrderCount = 0; // 追单
		int backOrderCount = 0; // 退单
		int commonOrderCount = 0; // 正常
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, Long.parseLong(localNetId));
			ps.setLong(2, Long.parseLong(areaId));
			if (!StringUtil.isBlank(workAreaId)) {
				ps.setLong(3, Long.parseLong(workAreaId));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				String workType = rs.getString("WO_TYPE");// 工单类型
				result = rs.getInt("orderCount");
				if ("F".equals(workType))
					appendOrderCount = result;
				else if ("B".equals(workType))
					backOrderCount = result;
				else if ("N".equals(workType))
					commonOrderCount = result;
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
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
		return appendOrderCount + "/" + backOrderCount + "/" + commonOrderCount;
	}

	/**
	 * 获得回单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getReturnOrderCount(String localNetId, String areaId, String workAreaId)
			throws AppException, SysException {
		
		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT count(*)  orderCount FROM WO WO ");
		sql.append(" WHERE WO.LOCAL_NET_ID =? AND WO.AREA_ID =?  ");
		if (!StringUtil.isBlank(workAreaId)) {
			sql.append(" AND WO.WORK_AREA_ID = ? ");
		}
		sql
				.append(" AND WO.RUN_STS = 'P' AND WO.BUSI_STS = 'N' AND (WO.WO_TYPE = 'N' OR WO.WO_TYPE = 'F' OR WO.WO_TYPE = 'B' ) ");
		// sql.append(" AND (WO.MAIN_FLAG = 'M' OR WO.MAIN_FLAG = 'N') AND (WO.MERG_FLAG = 'Y' OR
		// WO.MERG_FLAG = 'W') ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setLong(1, Long.parseLong(localNetId));
			ps.setLong(2, Long.parseLong(areaId));
			if (!StringUtil.isBlank(workAreaId)) {
				ps.setLong(3, Long.parseLong(workAreaId));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
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

	/**
	 * 获得追单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getZhuiDanOrderCount(String localNetId, String areaId, String workAreaId)
			throws AppException, SysException {
		Sql sql = new Sql(" SELECT COUNT(*) orderCount ");
		sql.append("  FROM WO WO, SO SO  ");
		sql.append(" WHERE 1=1 ");
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)) {
			sql.append(" AND WO.AREA_ID = :areaId ");
			sql.setLong("areaId", areaId);
		}
		if (!StringUtil.isBlank(workAreaId)) {
			sql.append(" AND WO.WORK_AREA_ID = :workAreaId ");
			sql.setLong("workAreaId", workAreaId);
		}

		sql.append(" AND WO.RUN_STS IN ('D', 'P') ");
		sql.append(" AND WO.BUSI_STS = 'N' ");
		sql.append(" AND WO.WO_TYPE = 'F'   ");
		sql.append(" AND WO.WORK_MODE = 'M' ");
		sql.append(" AND WO.SO_NBR = SO.SO_NBR ");
		sql.append(" AND SO.SO_LOCK_STS = 'N' ");
		sql.append(" ORDER BY WO.SO_NBR ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getZhuiDanOrderCount error..", e);
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

	/**
	 * 获得退单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getBackOrderCount(String localNetId, String areaId, String workAreaId)
			throws AppException, SysException {
		Sql sql = new Sql(" SELECT COUNT(*) orderCount ");
		sql.append("  FROM WO, SO  ");
		sql.append(" where  1=1 ");
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" and WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)) {
			sql.append(" AND WO.area_id = :areaId ");
			sql.setLong("areaId", areaId);
		}
		if (!StringUtil.isBlank(workAreaId)) {
			sql.append(" AND WO.WORK_AREA_ID = :workAreaId ");
			sql.setLong("workAreaId", workAreaId);
		}

		sql.append(" AND ( WO.RUN_STS  = 'D' OR WO.RUN_STS  = 'P' ) ");
		sql.append(" AND WO.BUSI_STS = 'N' ");
		sql.append(" AND WO.WO_TYPE = 'B'   ");
		sql.append(" AND WO.WORK_MODE = 'M' ");
		sql.append(" AND WO.SO_NBR = SO.SO_NBR ");
		sql.append(" AND SO.SO_LOCK_STS = 'N' ");
		sql.append(" ORDER BY WO.SO_NBR ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.log(this.getClass());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getBackOrderCount error..", e);
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

	/**
	 * 获得催单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getPressOrderCount(String localNetId, String areaId, String workAreaId)
			throws AppException, SysException {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT count(*) orderCount FROM WO WO, SO SO ");
		sql.append(" WHERE WO.LOCAL_NET_ID =? AND WO.AREA_ID =?  ");
		if (!StringUtil.isBlank(workAreaId)) {
			sql.append(" AND WO.WORK_AREA_ID =? ");
		}
		sql.append(" AND WO.RUN_STS = 'D' AND (WO.WO_TYPE = 'H' OR WO.WO_TYPE = 'C' ) AND WO.SO_NBR = SO.SO_NBR ");
		sql.append(" AND SO.STS = 'A' AND SO.SO_LOCK_STS = 'N' AND (SO.SO_STS = 'P' OR SO.SO_STS = 'g' OR SO.SO_STS = 'h' OR SO.SO_STS = 'F' ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, Integer.parseInt(localNetId));
			ps.setInt(2, Integer.parseInt(areaId));
			if (!StringUtil.isBlank(workAreaId)) {
				ps.setLong(3, Long.parseLong(workAreaId));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
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

	/**
	 * 获得异常单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getExceptionOrderCount(String localNetId, String areaId, String workAreaId)
			throws AppException, SysException {
		StringBuffer sql = new StringBuffer(1000);
		sql.append("SELECT count(*) orderCount FROM WO WO, SO SO ");
		sql.append(" WHERE WO.SO_NBR = SO.SO_NBR AND WO.LOCAL_NET_ID =? AND WO.AREA_ID =?  ");
		if (!StringUtil.isBlank(workAreaId)) {
			sql.append(" AND WO.WORK_AREA_ID =? ");
		}
		sql.append(" AND ( WO.BUSI_STS= 'S' OR WO.BUSI_STS = 'I' ) AND ( WO.WO_TYPE = 'N' OR WO.WO_TYPE = 'F' OR WO.WO_TYPE = 'B' ) ");
		sql.append(" AND SO.STS = 'A'  AND SO.SO_LOCK_STS = 'N' AND ( SO.SO_STS = 'P' OR SO.SO_STS = 'g' OR SO.SO_STS = 'h' OR SO.SO_STS = 'F' ) ");
		sql.append(" AND WO.RUN_STS NOT IN ('C','R')  ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, Integer.parseInt(localNetId));
			ps.setInt(2, Integer.parseInt(areaId));
			if (!StringUtil.isBlank(workAreaId)) {
				ps.setLong(3, Long.parseLong(workAreaId));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
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

	/**
	 * 获得领单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public String getFetchOrderCountAll(String localNetId, String areaId, String staffId)
			throws AppException, SysException {

		Sql sql = new Sql(" SELECT WO.WO_TYPE,count(*) orderCount ");
		sql.append(" FROM WO WO, SO SO, STAFF_WORK_AREA SWA ");
		sql.append(" WHERE 1=1  AND WO.SO_NBR = SO.SO_NBR ");
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)&&!"0".equals(areaId)) {
			sql.append(" AND WO.AREA_ID = :areaId ");
			sql.setLong("areaId", areaId);
		}
		sql.append(" AND WO.WORK_AREA_ID = SWA.WORK_AREA_ID ");
		if (!StringUtil.isBlank(staffId)) {
			sql.append(" AND SWA.STAFF_ID =:staffId ");
			sql.setString("staffId", staffId.trim());
		}
		sql.append(" AND WO.RUN_STS = 'D' AND WO.BUSI_STS = 'N' AND WO.BOOK_FLAG = 'N' ");
		sql.append(" AND ( WO.WO_TYPE = 'N' OR WO.WO_TYPE = 'F' OR WO.WO_TYPE = 'B' )  ");
		sql.append(" AND SO.STS = 'A' ");
		sql.append(" AND (SO.SO_STS = 'P' OR SO.SO_STS = 'g' OR SO.SO_STS = 'h' OR SO.SO_STS = 'F' ) ");
		sql.append(" AND SO.SO_LOCK_STS = 'N' GROUP BY WO.WO_TYPE ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		int appendOrderCount = 0; // 追单
		int backOrderCount = 0; // 退单
		int commonOrderCount = 0; // 正常
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());

			rs = ps.executeQuery();
			while (rs.next()) {
				String workType = rs.getString("WO_TYPE");// 工单类型
				result = rs.getInt("orderCount");
				if ("F".equals(workType))
					appendOrderCount = result;
				else if ("B".equals(workType))
					backOrderCount = result;
				else if ("N".equals(workType))
					commonOrderCount = result;
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
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
		return appendOrderCount + "/" + backOrderCount + "/" + commonOrderCount;
	}

	/**
	 * 获得回单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getReturnOrderCountAll(String localNetId, String areaId, String staffId)
			throws AppException, SysException {

		Sql sql = new Sql("SELECT count(*)  orderCount FROM WO WO, STAFF_WORK_AREA SWA ");
		sql.append(" WHERE 1=1 ");
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)&&!"0".equals(areaId)) {
			sql.append(" AND WO.AREA_ID = :areaId ");
			sql.setLong("areaId", areaId);
		}
		sql.append(" AND WO.WORK_AREA_ID = SWA.WORK_AREA_ID ");
		if (!StringUtil.isBlank(staffId)) {
			sql.append(" AND SWA.STAFF_ID =:staffId ");
			sql.setString("staffId", staffId.trim());
		}
		sql.append(" AND WO.RUN_STS = 'P' AND WO.BUSI_STS = 'N' ");
		sql.append(" AND (WO.WO_TYPE = 'N' OR WO.WO_TYPE = 'F' OR WO.WO_TYPE = 'B' ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());

			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return result;
	}

	/**
	 * 获得追单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getZhuiDanOrderCountAll(String localNetId, String areaId, String staffId)
			throws AppException, SysException {
		Sql sql = new Sql(" SELECT COUNT(*) orderCount ");
		sql.append("  FROM WO WO, SO SO, STAFF_WORK_AREA SWA  ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND WO.SO_NBR = SO.SO_NBR ");
		
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)&&!"0".equals(areaId)) {
			sql.append(" AND WO.AREA_ID = :areaId ");
			sql.setLong("areaId", areaId);
		}
		sql.append(" AND WO.WORK_AREA_ID = SWA.WORK_AREA_ID ");
		if (!StringUtil.isBlank(staffId)) {
			sql.append(" AND SWA.STAFF_ID =:staffId ");
			sql.setString("staffId", staffId.trim());
		}
		sql.append(" AND (WO.RUN_STS = 'D' OR WO.RUN_STS = 'P' ) ");
		sql.append(" AND WO.BUSI_STS = 'N' ");
		sql.append(" AND WO.WO_TYPE = 'F'   ");
		sql.append(" AND WO.WORK_MODE = 'M' ");
		sql.append(" AND SO.SO_LOCK_STS = 'N' ");
		sql.append(" ORDER BY WO.SO_NBR ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getZhuiDanOrderCount error..", e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return result;
	}

	/**
	 * 获得退单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getBackOrderCountAll(String localNetId, String areaId, String staffId)
			throws AppException, SysException {
		Sql sql = new Sql(" SELECT COUNT(*) orderCount ");
		sql.append("  FROM WO, SO , STAFF_WORK_AREA SWA ");
		sql.append(" WHERE  1=1 ");
		sql.append(" AND WO.SO_NBR = SO.SO_NBR ");
		
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)&&!"0".equals(areaId)) {
			sql.append(" AND WO.area_id = :areaId ");
			sql.setLong("areaId", areaId);
		}
		sql.append(" AND WO.WORK_AREA_ID = SWA.WORK_AREA_ID ");
		if (!StringUtil.isBlank(staffId)) {
			sql.append(" AND SWA.STAFF_ID =:staffId ");
			sql.setString("staffId", staffId.trim());
		}
		sql.append(" AND (WO.RUN_STS = 'D' OR WO.RUN_STS = 'P') ");
		sql.append(" AND WO.BUSI_STS = 'N' ");
		sql.append(" AND WO.WO_TYPE = 'B'   ");
		sql.append(" AND WO.WORK_MODE = 'M' ");
		sql.append(" AND SO.SO_LOCK_STS = 'N' ");
		sql.append(" ORDER BY WO.SO_NBR ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.log(this.getClass());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getBackOrderCount error..", e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return result;
	}

	/**
	 * 获得催单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getPressOrderCountAll(String localNetId, String areaId, String staffId)
			throws AppException, SysException {
		Sql sql = new Sql("SELECT count(*) orderCount FROM WO WO, SO SO, STAFF_WORK_AREA SWA ");
		sql.append(" WHERE 1=1 AND WO.SO_NBR = SO.SO_NBR ");
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)&& !"0".equals(areaId)) {
			sql.append(" AND WO.area_id = :areaId ");
			sql.setLong("areaId", areaId);
		}
		sql.append(" AND WO.WORK_AREA_ID = SWA.WORK_AREA_ID ");
		if (!StringUtil.isBlank(staffId)) {
			sql.append(" AND SWA.STAFF_ID =:staffId ");
			sql.setString("staffId", staffId.trim());
		}
		sql.append(" AND WO.RUN_STS = 'D' AND (WO.WO_TYPE = 'H' OR WO.RUN_STS = 'C' )  ");
		sql.append(" AND SO.STS = 'A' AND SO.SO_LOCK_STS = 'N' AND (SO.SO_STS = 'P' OR SO.SO_STS = 'g' OR SO.SO_STS = 'h' OR SO.SO_STS = 'F' ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return result;
	}

	/**
	 * 获得异常单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getExceptionOrderCountAll(String localNetId, String areaId, String staffId)
			throws AppException, SysException {
		Sql sql = new Sql("SELECT count(*) orderCount FROM WO WO, SO SO, STAFF_WORK_AREA SWA ");
		sql.append(" WHERE  1=1  AND WO.SO_NBR = SO.SO_NBR  ");
		if (!StringUtil.isBlank(localNetId)) {
			sql.append(" AND WO.LOCAL_NET_ID = :localNetId ");
			sql.setLong("localNetId", localNetId);
		}
		if (!StringUtil.isBlank(areaId)&&!"0".equals(areaId)) {
			sql.append(" AND WO.area_id = :areaId ");
			sql.setLong("areaId", areaId);
		}
		sql.append(" AND WO.WORK_AREA_ID = SWA.WORK_AREA_ID ");
		if (!StringUtil.isBlank(staffId)) {
			sql.append(" AND SWA.STAFF_ID =:staffId ");
			sql.setString("staffId", staffId.trim());
		}
		sql.append(" AND WO.RUN_STS NOT IN ('C','R')  ");
		sql.append(" AND (WO.BUSI_STS = 'S' OR WO.BUSI_STS = 'I' ) AND (WO.WO_TYPE = 'N' OR WO.WO_TYPE = 'F' OR WO.WO_TYPE = 'B' ) ");
		sql.append(" AND SO.STS = 'A'  AND SO.SO_LOCK_STS = 'N' AND (SO.SO_STS = 'P' OR SO.SO_STS = 'g' OR SO.SO_STS = 'h' OR SO.SO_STS = 'F' ) ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("orderCount");
			}
		} catch (SQLException e) {
			throw new SysException("", "getFetchOrder error..", e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return result;
	}

	/**
	 * 
	 * 获得所有类别的工单的统计集合
	 * @param localNetId
	 * @param areaId
	 * @param staffId
	 * 
	 * @return
	 */
	public Map getAllOrderCount(String localNetId, String areaId,
		String workAreaId,String staffId) throws AppException, SysException {
		ResultSet rs = null;
		CallableStatement proc = null;
		Map countValueMap=(Map)CollectionFactory.createMap(CollectionFactory.COLLECTION_MAP); 
		try {

			Connection conn = ConnectionFactory.getConnection();
			String sql = "{ call get_all_order_count(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			proc = conn.prepareCall(sql);
			log.debug(sql);
			int index = 1;
			proc.setString(index++, localNetId);
			proc.setString(index++, areaId);
			proc.setString(index++, workAreaId);
			proc.setString(index++, staffId);
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// returnCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// pressCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// exceptionCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// appendCunt
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// backCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// commonCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// userReturnCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// userPressCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// userExceptionCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// userAppendCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// userBackCount
			proc.registerOutParameter(index++, oracle.jdbc.OracleTypes.NUMBER);// userCommonCount

			proc.execute();
			// 查看执行后的状态代码和错误描述
			index = 5;// 定位为OUT参数的索引位置
			countValueMap.put("returnCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("pressCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("exceptionCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("appendCunt", String.valueOf(proc.getInt(index++)));
			countValueMap.put("backCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("commonCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("userReturnCount", String
					.valueOf(proc.getInt(index++)));
			countValueMap
					.put("userPressCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("userExceptionCount", String.valueOf(proc
					.getInt(index++)));
			countValueMap.put("userAppendCount", String
					.valueOf(proc.getInt(index++)));
			countValueMap.put("userBackCount", String.valueOf(proc.getInt(index++)));
			countValueMap.put("userCommonCount", String
					.valueOf(proc.getInt(index++)));

			log.debug("getAllOrderCount参数:" + "localNetId=" + localNetId
					+ ",areaId=" + areaId + ",workareaId=" + workAreaId
					+ ",staffId=" + staffId);
			log.error("CALL GET_ALL_ORDER_COUNT BY [localNetId=" + localNetId
					+ "] [areaId=" + areaId + "] [workareaId=" + workAreaId
					+ "] [staffId=" + staffId + "]");
			index = 5;
			log.debug("取得的工单统计数据: " + "returnCount="
					+ String.valueOf(proc.getInt(index++)) + ",pressCount="
					+ String.valueOf(proc.getInt(index++)) + ",exceptionCount="
					+ String.valueOf(proc.getInt(index++)) + ",appendCunt="
					+ String.valueOf(proc.getInt(index++)) + ",backCount="
					+ String.valueOf(proc.getInt(index++)) + ",commonCount="
					+ String.valueOf(proc.getInt(index++))+",userReturnCount="
					+ String.valueOf(proc.getInt(index++)) + ",userPressCount="
					+ String.valueOf(proc.getInt(index++)) + ",userExceptionCount="
					+ String.valueOf(proc.getInt(index++)) + ",userAppendCount="
					+ String.valueOf(proc.getInt(index++)) + ",userBackCount="
					+ String.valueOf(proc.getInt(index++)) + ",userCommonCount="
					+ String.valueOf(proc.getInt(index++)));

		} catch (SQLException se) {
			throw new SysException("", "JDBC操作异常！", se);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, proc);
		}
	
	    return countValueMap;
	}
	
	public List findByVOIgnorecase(GenericVO vo) throws AppException, SysException {
        List results = null;
        SysUserSVO sysUser = (SysUserSVO) vo;
        Sql sql = new Sql();
        sql.append("select");
        sql
                .append(" a.SYS_USER_ID,a.PARTY_ROLE_TYPE_ID,a.PARTY_ROLE_ID,a.SYS_USER_NAME,a.PASSWORD,a.SET_PWD_TIME,a.UPDATE_PWD_TIME,a.LAST_PWD,a.CREATE_DATE,a.STS,a.STS_DATE,a.local_net_id ");
        sql.append(" from SYS_USER a where 1=1");
        if (sysUser.getSysUserName() != null) {        	
            sql.append(" and UPPER(SYS_USER_NAME)=?");
        }
        if (sysUser.getPassword() != null) {
            sql.append(" and PASSWORD=?");
        }
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            ps = connection.prepareStatement(sql.getSql());
            int index = 1;
            if (sysUser.getSysUserName() != null) {             	
                sql.setString(index++, sysUser.getSysUserName().toUpperCase());
            }
            if (sysUser.getPassword() != null) {
                sql.setString(index++, sysUser.getPassword());
            }
            sql.fillParams(ps);
            sql.log(this.getClass());
            rs = ps.executeQuery();
            results = (List) ResultSetUtil.convertToList(rs, SysUserSVO.class);
        } catch (SQLException e) {
            throw new SysException("", "findByVOIgnorecase error..", e);
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
