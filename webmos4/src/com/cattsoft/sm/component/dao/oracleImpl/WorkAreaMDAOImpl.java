package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.SysConstants;
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
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IWorkAreaMDAO;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.WorkAreaMVO;
import com.cattsoft.sm.vo.WorkAreaSVO;

public class WorkAreaMDAOImpl implements IWorkAreaMDAO {

	private static Logger log = Logger.getLogger(WorkAreaMDAOImpl.class);
	
	/**
	 * 得到某个员工可访问的工区列表，
	 * 工区列表比findWorkAreasBySystemStaff的方法返回的工区列表多了工区名称拼音字段并按照拼音字段进行排序
	 * 
	 * @param staffId
	 *            Long
	 * @param localNetId
	 *            int
	 * @throws SysException
	 * @throws AppException
	 */
	public List findWorkAreasBySystemStaff4MOS(WorkAreaSVO vo, String systemName,
			String staffId) throws SysException {
		if (log.isDebugEnabled())
			log.debug("根据系统名称和员工查询工区：SYSTEM_NAME" + systemName + "STAFF_ID"
					+ staffId);

		Sql sql = new Sql(
				"select wa.WORK_AREA_ID,F_PINYIN(wa.name) as workareaSpell,wa.NAME,wa.WORK_TYPE_ID,wa.WORK_MODE,wa.LOCAL_NET_ID,wa.AREA_ID,wa.SERV_DEPT_ID,wa.BRANCH_ID,wa.DISPATCH_LEVEL,wa.PARENT_WORK_AREA_ID,wa.STANDARD_CODE,wa.STS,wa.STS_DATE,a.name area_name,a.iscenter area_iscenter,lc.name local_net_name,lc.iscenter local_net_iscenter,wt.name work_type_name,wt.type work_type,br.name branch_name,sd.name serv_dept_name,swa.admin_flag from Work_Area wa,Work_Type wt,Area a,Local_Net lc,branch br,serv_dept sd ,Staff_Work_Area swa ");
		sql
				.append("where 1=1 and wa.work_Type_Id=wt.work_Type_Id and wa.area_Id=a.area_Id and wa.local_Net_Id=lc.local_Net_Id and wa.branch_id=br.branch_id and wa.serv_dept_id=sd.serv_dept_id ");
		if (systemName != null) {
			sql.append("and wt.work_Type_Id in(");
			sql
					.append("select sswt.work_Type_Id from Sub_System_Work_Type sswt where sswt.sub_System_Name=:subSystemName )");
			sql.setString("subSystemName", systemName);
		}
		// sql.append(" and wa.work_Area_Id in(");
		// sql.append("select swa.work_Area_Id from Staff_Work_Area swa where
		// swa.staff_Id=?");
		sql.append(" and wa.work_area_id = swa.work_area_id");
		sql.append(" and swa.staff_Id=:staffId");
		sql.setString("staffId", staffId);
		sql.append(" and swa.sts='A'");
		sql.append(" and wa.sts='A'");
		// modify by baixd 2009-12-01
		if (vo.getWorkAreaId() != null) {
			sql.append(" and wa.work_Area_Id=:workAreaId");
			sql.setString("workAreaId", vo.getWorkAreaId());
		}
		// add by baixd 2009-10-30 工区类型区分
		if (vo.getFlagWorkTypeId() == 1) {
			sql.append(" AND wa.WORK_TYPE_ID =:workTypeId");
			sql.setString("workTypeId", vo.getWorkTypeId());
		}
		// add end;
		if (vo.getLocalNetId() != null) {
			sql.append(" and wa.local_Net_Id=:localNetId");
			sql.setString("localNetId", vo.getLocalNetId());
		}
		sql.append(" order by workareaSpell, a.area_id,wa.NAME ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List rtnList = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());

			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			
			rtnList = new ArrayList();
			WorkAreaMVO workAreaMVO = null;
			while (rs.next()) {
				workAreaMVO = new WorkAreaMVO();
				rtnList.add(workAreaMVO);
				
				workAreaMVO.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				workAreaMVO.setName(rs.getString("NAME"));
				workAreaMVO.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
				workAreaMVO.setWorkTypeName(rs.getString("WORK_TYPE_NAME"));
				workAreaMVO.setWorkMode(rs.getString("WORK_MODE"));
				workAreaMVO.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				workAreaMVO.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				workAreaMVO.setLocalNetIscenter(rs.getString("LOCAL_NET_ISCENTER"));
				workAreaMVO.setAreaId(rs.getString("AREA_ID"));
				workAreaMVO.setAreaName(rs.getString("AREA_NAME"));
				workAreaMVO.setAreaIscenter(rs.getString("AREA_ISCENTER"));
				workAreaMVO.setServDeptId(rs.getString("SERV_DEPT_ID"));
				workAreaMVO.setServDeptName(rs.getString("SERV_DEPT_NAME"));
				workAreaMVO.setBranchId(rs.getString("BRANCH_ID"));
				workAreaMVO.setBranchName(rs.getString("BRANCH_NAME"));
				workAreaMVO.setDispatchLevel(rs.getString("DISPATCH_LEVEL"));
				workAreaMVO.setParentWorkAreaId(rs.getString("PARENT_WORK_AREA_ID"));
				workAreaMVO.setStandardCode(rs.getString("STANDARD_CODE"));
				workAreaMVO.setSts(rs.getString("STS"));
				workAreaMVO.setStsDate(rs.getDate("STS_DATE"));
				workAreaMVO.setWorkType(rs.getString("WORK_TYPE"));
				workAreaMVO.setAdminFlag(rs.getString("ADMIN_FLAG"));
				workAreaMVO.setWorkAreaSpell(rs.getString("workareaSpell"));
			}
		} catch (Exception e) {
			throw new SysException("", "findWorkAreasBySystemStaff error..", e);
		}
		return rtnList;
	}

	/**
	 * 得到某个员工可访问的工区列表
	 * 
	 * @param staffId
	 *            Long
	 * @param localNetId
	 *            int
	 * @throws SysException
	 * @throws AppException
	 */
	public List findWorkAreasBySystemStaff(WorkAreaSVO vo, String systemName,
			String staffId) throws SysException {
		if (log.isDebugEnabled())
			log.debug("根据系统名称和员工查询工区：SYSTEM_NAME" + systemName + "STAFF_ID"
					+ staffId);

		Sql sql = new Sql(
				"select wa.WORK_AREA_ID,wa.NAME,wa.WORK_TYPE_ID,wa.WORK_MODE,wa.LOCAL_NET_ID,wa.AREA_ID,wa.SERV_DEPT_ID,wa.BRANCH_ID,wa.DISPATCH_LEVEL,wa.PARENT_WORK_AREA_ID,wa.STANDARD_CODE,wa.STS,wa.STS_DATE,a.name area_name,a.iscenter area_iscenter,lc.name local_net_name,lc.iscenter local_net_iscenter,wt.name work_type_name,wt.type work_type,br.name branch_name,sd.name serv_dept_name,swa.admin_flag from Work_Area wa,Work_Type wt,Area a,Local_Net lc,branch br,serv_dept sd ,Staff_Work_Area swa ");
		sql
				.append("where 1=1 and wa.work_Type_Id=wt.work_Type_Id and wa.area_Id=a.area_Id and wa.local_Net_Id=lc.local_Net_Id and wa.branch_id=br.branch_id and wa.serv_dept_id=sd.serv_dept_id ");
		if (systemName != null) {
			sql.append("and wt.work_Type_Id in(");
			sql
					.append("select sswt.work_Type_Id from Sub_System_Work_Type sswt where sswt.sub_System_Name=:subSystemName )");
			sql.setString("subSystemName", systemName);
		}
		// sql.append(" and wa.work_Area_Id in(");
		// sql.append("select swa.work_Area_Id from Staff_Work_Area swa where
		// swa.staff_Id=?");
		sql.append(" and wa.work_area_id = swa.work_area_id");
		sql.append(" and swa.staff_Id=:staffId");
		sql.setString("staffId", staffId);
		sql.append(" and swa.sts='A'");
		sql.append(" and wa.sts='A'");
		// modify by baixd 2009-12-01
		if (vo.getWorkAreaId() != null) {
			sql.append(" and wa.work_Area_Id=:workAreaId");
			sql.setString("workAreaId", vo.getWorkAreaId());
		}
		// add by baixd 2009-10-30 工区类型区分
		if (vo.getFlagWorkTypeId() == 1) {
			sql.append(" AND wa.WORK_TYPE_ID =:workTypeId");
			sql.setString("workTypeId", vo.getWorkTypeId());
		}
		// add end;
		if (vo.getLocalNetId() != null) {
			sql.append(" and wa.local_Net_Id=:localNetId");
			sql.setString("localNetId", vo.getLocalNetId());
		}
		sql.append(" order by a.area_id,wa.NAME ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List rtnList = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());

			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			
			rtnList = new ArrayList();
			WorkAreaMVO workAreaMVO = null;
			while (rs.next()) {
				workAreaMVO = new WorkAreaMVO();
				rtnList.add(workAreaMVO);
				
				workAreaMVO.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				workAreaMVO.setName(rs.getString("NAME"));
				workAreaMVO.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
				workAreaMVO.setWorkTypeName(rs.getString("WORK_TYPE_NAME"));
				workAreaMVO.setWorkMode(rs.getString("WORK_MODE"));
				workAreaMVO.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				workAreaMVO.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				workAreaMVO.setLocalNetIscenter(rs.getString("LOCAL_NET_ISCENTER"));
				workAreaMVO.setAreaId(rs.getString("AREA_ID"));
				workAreaMVO.setAreaName(rs.getString("AREA_NAME"));
				workAreaMVO.setAreaIscenter(rs.getString("AREA_ISCENTER"));
				workAreaMVO.setServDeptId(rs.getString("SERV_DEPT_ID"));
				workAreaMVO.setServDeptName(rs.getString("SERV_DEPT_NAME"));
				workAreaMVO.setBranchId(rs.getString("BRANCH_ID"));
				workAreaMVO.setBranchName(rs.getString("BRANCH_NAME"));
				workAreaMVO.setDispatchLevel(rs.getString("DISPATCH_LEVEL"));
				workAreaMVO.setParentWorkAreaId(rs.getString("PARENT_WORK_AREA_ID"));
				workAreaMVO.setStandardCode(rs.getString("STANDARD_CODE"));
				workAreaMVO.setSts(rs.getString("STS"));
				workAreaMVO.setStsDate(rs.getDate("STS_DATE"));
				workAreaMVO.setWorkType(rs.getString("WORK_TYPE"));
				workAreaMVO.setAdminFlag(rs.getString("ADMIN_FLAG"));
			}
		} catch (Exception e) {
			throw new SysException("", "findWorkAreasBySystemStaff error..", e);
		}
		return rtnList;
	}
	
	public List findWorkAreasBySystemStaffByMobile(WorkAreaMVO vo, String systemName,
			String staffId) throws SysException {
		if (log.isDebugEnabled())
			log.debug("根据系统名称和员工查询工区：SYSTEM_NAME" + systemName + "STAFF_ID"
					+ staffId);

		Sql sql = new Sql(
				"select wa.WORK_AREA_ID,wa.NAME,wa.WORK_TYPE_ID,wa.WORK_MODE,wa.LOCAL_NET_ID,wa.AREA_ID,wa.SERV_DEPT_ID,wa.BRANCH_ID,wa.DISPATCH_LEVEL,wa.PARENT_WORK_AREA_ID,wa.STANDARD_CODE,wa.STS,wa.STS_DATE,a.name area_name,a.iscenter area_iscenter,lc.name local_net_name,lc.iscenter local_net_iscenter,wt.name work_type_name,wt.type work_type,br.name branch_name,sd.name serv_dept_name,swa.admin_flag from Work_Area wa,Work_Type wt,Area a,Local_Net lc,branch br,serv_dept sd ,Staff_Work_Area swa ");
		sql
				.append("where 1=1 and wa.work_Type_Id=wt.work_Type_Id and wa.area_Id=a.area_Id and wa.local_Net_Id=lc.local_Net_Id and wa.branch_id=br.branch_id and wa.serv_dept_id=sd.serv_dept_id ");
		if (systemName != null) {
			sql.append("and wt.work_Type_Id in(");
			sql
					.append("select sswt.work_Type_Id from Sub_System_Work_Type sswt where sswt.sub_System_Name=:subSystemName )");
			sql.setString("subSystemName", systemName);
		}
		// sql.append(" and wa.work_Area_Id in(");
		// sql.append("select swa.work_Area_Id from Staff_Work_Area swa where
		// swa.staff_Id=?");
		sql.append(" and wa.work_area_id = swa.work_area_id");
		sql.append(" and swa.staff_Id=:staffId");
		sql.setString("staffId", staffId);
		sql.append(" and swa.sts='A'");
		sql.append(" and wa.sts='A'");
		// modify by baixd 2009-12-01
		if (vo.getWorkAreaId() != null) {
			sql.append(" and wa.work_Area_Id=:workAreaId");
			sql.setString("workAreaId", vo.getWorkAreaId());
		}
		// add by baixd 2009-10-30 工区类型区分
		if (vo.getFlagWorkTypeId() == 1) {
			sql.append(" AND wa.WORK_TYPE_ID =:workTypeId");
			sql.setString("workTypeId", vo.getWorkTypeId());
		}
		// add end;
		if (vo.getLocalNetId() != null) {
			sql.append(" and wa.local_Net_Id=:localNetId");
			sql.setString("localNetId", vo.getLocalNetId());
		}
		
		//设置默认工区对配单的时候有影响，无法选中，所以不需要此条件
//		if(!StringUtil.isBlank(vo.getAdminFlag())) {
//			sql.append(" and swa.admin_flag =  :adminFlag ");
//			sql.setString("adminFlag", vo.getAdminFlag());
//		}
//		
		sql.append(" order by a.area_id,wa.NAME ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List rtnList = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());

			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			
			rtnList = new ArrayList();
			WorkAreaMVO workAreaMVO = null;
			while (rs.next()) {
				workAreaMVO = new WorkAreaMVO();
				rtnList.add(workAreaMVO);
				
				workAreaMVO.setWorkAreaId(rs.getString("WORK_AREA_ID"));
				workAreaMVO.setName(rs.getString("NAME"));
				workAreaMVO.setWorkTypeId(rs.getString("WORK_TYPE_ID"));
				workAreaMVO.setWorkTypeName(rs.getString("WORK_TYPE_NAME"));
				workAreaMVO.setWorkMode(rs.getString("WORK_MODE"));
				workAreaMVO.setLocalNetId(rs.getString("LOCAL_NET_ID"));
				workAreaMVO.setLocalNetName(rs.getString("LOCAL_NET_NAME"));
				workAreaMVO.setLocalNetIscenter(rs.getString("LOCAL_NET_ISCENTER"));
				workAreaMVO.setAreaId(rs.getString("AREA_ID"));
				workAreaMVO.setAreaName(rs.getString("AREA_NAME"));
				workAreaMVO.setAreaIscenter(rs.getString("AREA_ISCENTER"));
				workAreaMVO.setServDeptId(rs.getString("SERV_DEPT_ID"));
				workAreaMVO.setServDeptName(rs.getString("SERV_DEPT_NAME"));
				workAreaMVO.setBranchId(rs.getString("BRANCH_ID"));
				workAreaMVO.setBranchName(rs.getString("BRANCH_NAME"));
				workAreaMVO.setDispatchLevel(rs.getString("DISPATCH_LEVEL"));
				workAreaMVO.setParentWorkAreaId(rs.getString("PARENT_WORK_AREA_ID"));
				workAreaMVO.setStandardCode(rs.getString("STANDARD_CODE"));
				workAreaMVO.setSts(rs.getString("STS"));
				workAreaMVO.setStsDate(rs.getDate("STS_DATE"));
				workAreaMVO.setWorkType(rs.getString("WORK_TYPE"));
				workAreaMVO.setAdminFlag(rs.getString("ADMIN_FLAG"));
			}
		} catch (Exception e) {
			throw new SysException("", "findWorkAreasBySystemStaffByMobile error..", e);
		}
		return rtnList;
	}	
	
	
	
//	按工区类型查有权限的工区
	public List findWorkAreasByType(WorkAreaSVO vo,String staffId) throws SysException {
		List vos = null;
	
		Sql sql = new Sql("SELECT WA.WORK_AREA_ID,WA.NAME,WA.WORK_TYPE_ID,WA.WORK_MODE,WA.LOCAL_NET_ID,WA.AREA_ID,WA.SERV_DEPT_ID,"+
				"WA.BRANCH_ID,WA.DISPATCH_LEVEL,WA.PARENT_WORK_AREA_ID,WA.STANDARD_CODE,WA.STS,WA.STS_DATE"+
			    " FROM WORK_AREA WA,STAFF_WORK_AREA SWA"+
			    " WHERE WA.WORK_AREA_ID = SWA.WORK_AREA_ID "+ 
			    " AND SWA.STAFF_ID = :staffId"+
			    " AND SWA.STS = 'A' AND WA.STS = 'A'"); 
		sql.setString("staffId", staffId); 
		// modify by baixd 2009-12-01
		if (vo.getWorkAreaId() != null) {
			sql.append(" and wa.work_Area_Id=:workAreaId");
			sql.setString("workAreaId", vo.getWorkAreaId());
		}
		// add by baixd 2009-10-30 工区类型区分
		if (vo.getFlagWorkTypeId() == 1) {
			sql.append(" AND wa.WORK_TYPE_ID =:workTypeId");
			sql.setString("workTypeId", vo.getWorkTypeId());
		}
		// add end;
		if (vo.getLocalNetId() != null) {
			sql.append(" and wa.local_Net_Id=:localNetId");
			sql.setString("localNetId", vo.getLocalNetId());
		}
		sql.append(" order by wa.NAME ");

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());

			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			vos = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);

		} catch (Exception e) {
			throw new SysException("", "findWorkAreasBySystemStaff error..", e);
		}
		return vos;
	}

	/**
	 * 查询所有WorkArea
	 * 
	 * @param vo
	 *            WorkAreaVO
	 * @throws SysException
	 */

	public List findSingleWorkAreasBySystemStaff(WorkAreaSVO vo,
			String systemName, String staffId) throws SysException {
		List vos = null;
		// get query total no;
		if (log.isDebugEnabled())
			log.debug("根据系统名称和员工查询工区：SYSTEM_NAME" + systemName + "STAFF_ID"
					+ staffId);
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						"select wa.WORK_AREA_ID,wa.NAME,wa.WORK_TYPE_ID,wa.LOCAL_NET_ID,wa.AREA_ID,wa.SERV_DEPT_ID,wa.BRANCH_ID,wa.DISPATCH_LEVEL,wa.PARENT_WORK_AREA_ID,wa.STANDARD_CODE,wa.STS,wa.STS_DATE,wa.CREATE_DATE,wa.work_mode,wa.channel_id,a.name area_name,a.iscenter area_iscenter, ")
				.append(
						" lc.name local_net_name,lc.iscenter local_net_iscenter,wt.name work_type_name,wt.type work_type,wt.co_meth ")
				.append(" from Work_Area wa,Work_Type wt,Area a,Local_Net lc ");
		sql
				.append("where wa.work_Type_Id=wt.work_Type_Id and wa.area_Id=a.area_Id and wa.local_Net_Id=lc.local_Net_Id ");
		if (systemName != null) {
			sql.append("and wt.work_Type_Id in(");
			sql
					.append("select sswt.work_Type_Id from Sub_System_Work_Type sswt where sswt.sub_System_Name=? )");
		}
		sql.append(" and wa.work_Area_Id in (");
		sql
				.append("select swa.work_Area_Id from Staff_Work_Area swa where swa.staff_Id= ? ");
		sql.append(" and swa.sts='A'");
		sql.append(") and wa.sts='A'");
		if (vo.getWorkAreaId() != null)
			sql.append(" and wa.work_Area_Id= ? ");
		if (vo.getLocalNetId() != null)
			sql.append(" and wa.local_Net_Id= ? ");
		sql.append(" order by a.area_id,wa.name");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			log.debug(sql.toString());
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (systemName != null) {
				ps.setString(index++, systemName);
			}

			ps.setString(index++, staffId);
			if (vo.getWorkAreaId() != null) {
				ps.setString(index++, vo.getWorkAreaId());
			}
			if (vo.getLocalNetId() != null) {
				ps.setString(index++, vo.getLocalNetId());
			}
			rs = ps.executeQuery();

			vos = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);

		} catch (Exception e) {
			throw new SysException("",
					"findSingleWorkAreasBySystemStaff error..", e);
		}
		return vos;
	}

	public PagView findByPage(GenericVO vo, PagInfo pagInfo)
			throws AppException, SysException {
		List results = null;
		WorkAreaSVO workArea = (WorkAreaSVO) vo;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		// sql
		// .append(
		// "
		// a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL,a.PARENT_WORK_AREA_ID,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.work_mode,
		// ")
		// .append(
		// "b.name local_net_name,c.name area_name,d.name serv_dept_name,e.name
		// branch_name,f.channel_id,f.channel_name ");
		// sql
		// .append(" from WORK_AREA a,branch e,serv_dept d,area c,local_net
		// b,channel f where 1=1 and a.channel_id=f.channel_id(+) and
		// a.branch_id=e.branch_id(+) and a.serv_dept_id=d.serv_dept_id and
		// a.area_id=c.area_id and a.local_net_id=b.local_net_id ");
		sql
				.append(
						" a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL,a.PARENT_WORK_AREA_ID,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.work_mode, ")
				.append(
						"b.name local_net_name,c.name area_name,d.name serv_dept_name,e.name branch_name,H.NAME WORK_TYPE_NAME ");
		sql
				.append(" from WORK_AREA a,branch e,serv_dept d,area c,WORK_TYPE H,local_net b where 1=1 and a.branch_id=e.branch_id(+) AND A.WORK_TYPE_ID=H.WORK_TYPE_ID(+) and a.serv_dept_id=d.serv_dept_id and a.area_id=c.area_id and a.local_net_id=b.local_net_id ");

		if (workArea.getName() != null) {
			sql.append(" and a.WORK_AREA_ID=" + workArea.getWorkAreaId());
		}

		if (workArea.getName() != null) {
			sql.append(" and a.NAME=" + workArea.getName());
		}
		if (workArea.getWorkTypeId() != null
				&& !workArea.getWorkTypeId().equals("")) {
			sql.append(" and a.WORK_TYPE_ID=" + workArea.getWorkTypeId());
		}
		if (workArea.getLocalNetId() != null) {
			sql.append(" and a.LOCAL_NET_ID=" + workArea.getLocalNetId());
		}
		if (workArea.getAreaId() != null) {
			sql.append(" and a.AREA_ID=" + workArea.getAreaId());
		}
		if (workArea.getServDeptId() != null) {
			sql.append(" and a.SERV_DEPT_ID=" + workArea.getServDeptId());
		}
		if (!StringUtil.isBlank(workArea.getBranchId())) {
			sql.append(" and a.BRANCH_ID=" + workArea.getBranchId());
		}
		if (workArea.getDispatchLevel() != null) {
			sql.append(" and a.DISPATCH_LEVEL=" + workArea.getDispatchLevel());
		}
		if (workArea.getParentWorkAreaId() != null) {
			sql.append(" and a.PARENT_WORK_AREA_ID="
					+ workArea.getParentWorkAreaId());
		}
		if (workArea.getStandardCode() != null) {
			sql.append(" and a.STANDARD_CODE=" + workArea.getStandardCode());
		}
		if (workArea.getSts() != null) {
			sql.append(" and a.STS='" + workArea.getSts() + "'");
		}
		if (workArea.getStsDate() != null) {
			sql.append(" and a.STS_DATE=" + workArea.getStsDate());
		}
		if (workArea.getCreateDate() != null) {
			sql.append(" and a.CREATE_DATE=" + workArea.getCreateDate());
		}
		if (workArea.getChannelId() != null) {
			sql.append(" and a.channel_id=" + workArea.getChannelId());
		}
		if (workArea.getWorkMode() != null) {
			sql.append(" and a.work_mode='" + workArea.getWorkMode() + "'");
		}
		log.debug(sql.toString());
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			Sql sql2 = new Sql();
			sql2.setSql(sql.toString());
			sql2.log(WorkAreaMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			results = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);
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

	public PagView findBySet(HashSet set, PagInfo pagInfo) throws Exception {
		List results = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql
				.append(
						" a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL,a.PARENT_WORK_AREA_ID,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.work_mode, ")
				.append(
						"b.name local_net_name,c.name area_name,d.name serv_dept_name,e.name branch_name ");
		sql
				.append(" from WORK_AREA a,branch e,serv_dept d,area c,local_net b where 1=1 and a.branch_id=e.branch_id(+) and a.serv_dept_id=d.serv_dept_id and a.area_id=c.area_id and a.local_net_id=b.local_net_id and ( ");

		sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "work_area_id",
				"workAreaId"));
		sql.append(") and a.sts='A' ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PagView pagView = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			Sql sql2 = new Sql();
			sql2.setSql(sql.toString());
			sql2.log(WorkAreaMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			results = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);
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

	public GenericVO findByPK(GenericVO vo) throws AppException, SysException {
		WorkAreaMVO result = null;
		WorkAreaSVO workArea = (WorkAreaSVO) vo;
		StringBuffer sql = new StringBuffer("select");
		sql
				.append(" a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL,a.PARENT_WORK_AREA_ID,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.channel_id,a.work_mode ");
		sql.append(" from WORK_AREA  a  where 1=1  ");
		sql.append(" and WORK_AREA_ID=? ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, workArea.getWorkAreaId());
			rs = ps.executeQuery();
			result = (WorkAreaMVO) ResultSetUtil.convertToVo(rs,
					WorkAreaMVO.class);
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

	// 设置默认工区
	public void setDefaultWorkArea(String staffId, String workAreaId,
			String subSystemName) throws SysException, AppException {

		StringBuffer preSql = new StringBuffer();
		preSql
				.append("UPDATE STAFF_WORK_AREA SWA SET SWA.ADMIN_FLAG = 'N' WHERE SWA.STAFF_ID = ");
		preSql.append("'");
		preSql.append(staffId.trim());
		preSql.append("'");
		preSql
				.append(" AND SWA.STS = 'A' AND SWA.ADMIN_FLAG='Y' AND SWA.WORK_AREA_ID IN ");
		preSql
				.append(" (SELECT WA.WORK_AREA_ID FROM WORK_AREA WA, WORK_TYPE WT WHERE WA.WORK_TYPE_ID = WT.WORK_TYPE_ID AND WA.STS='A'");
		preSql
				.append(" AND WT.WORK_TYPE_ID IN (select sswt.work_Type_Id from Sub_System_Work_Type sswt where sswt.sub_System_Name = '");
		preSql.append(subSystemName.trim());
		preSql.append("'))");
		log.debug(preSql.toString());

		StringBuffer sql = new StringBuffer();
		sql
				.append(" UPDATE STAFF_WORK_AREA SWA SET SWA.ADMIN_FLAG='Y' WHERE SWA.STAFF_ID= ");
		sql.append("'");
		sql.append(staffId);
		sql.append("'");
		sql.append(" AND SWA.WORK_AREA_ID= ");
		sql.append(workAreaId);
		log.debug(sql.toString());

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();

			// connection.setAutoCommit(false);
			ps = connection.prepareStatement(preSql.toString());
			ps.executeUpdate();
			// connection.commit();
			// connection.setAutoCommit(true);
			if (ps != null) {
				ps.close();
			}

			ps = connection.prepareStatement(sql.toString());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SysException("", "setDefaultWorkArea error..", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addWorkArea(GenericVO vo) throws AppException, SysException {
		WorkAreaMVO workArea = (WorkAreaMVO) vo;
		StringBuffer sql = new StringBuffer("insert into");
		sql
				.append(" WORK_AREA(WORK_AREA_ID,NAME,WORK_TYPE_ID,LOCAL_NET_ID,AREA_ID,SERV_DEPT_ID,BRANCH_ID,DISPATCH_LEVEL,PARENT_WORK_AREA_ID,STANDARD_CODE,STS,STS_DATE,CREATE_DATE,CHANNEL_ID,WORK_MODE,TERM_TYPE,TERM_NBR,REMARKS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, workArea.getWorkAreaId());
			ps.setString(2, workArea.getName());
			ps.setString(3, workArea.getWorkTypeId());
			ps.setString(4, workArea.getLocalNetId());
			ps.setString(5, workArea.getAreaId());
			ps.setString(6, workArea.getServDeptId());
			ps.setString(7, workArea.getBranchId());
			ps.setString(8, workArea.getDispatchLevel());
			ps.setString(9, workArea.getParentWorkAreaId());
			ps.setString(10, workArea.getStandardCode());
			ps.setString(11, workArea.getSts());
			ps.setDate(12, (java.sql.Date) workArea.getStsDate());
			ps.setDate(13, (java.sql.Date) workArea.getCreateDate());
			ps.setString(14, workArea.getChannelId());
			ps.setString(15, workArea.getWorkMode());
			ps.setString(16, workArea.getTermType());
			ps.setString(17, workArea.getTermNbr());
			ps.setString(18, workArea.getRemarks());
			ps.execute();
			String openFlag = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG, null,
					null, null, null, null);//从配置表获取是否调用同步资源的存储过程的开关
			if(!StringUtil.isBlank(openFlag)&&SysConstants.TRUE.equals(openFlag)){
			//if (workArea.getWorkTypeId().equals("105")) {
				WorkAreaSync("A", workArea.getWorkAreaId(), workArea
						.getLocalNetId(), workArea.getAreaId(), workArea
						.getName(), workArea.getDispatchLevel(), workArea
						.getParentWorkAreaId(), workArea.getStandardCode(),
						workArea.getSts(), workArea.getStsDate(), workArea
								.getCreateDate(), workArea.getWorkMode());
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new SysException("", "add error..", e);
		} finally {
			try {
				connection.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	public void updateWorkArea(GenericVO vo) throws AppException, SysException {
		WorkAreaMVO workArea = (WorkAreaMVO) vo;
		StringBuffer sql = new StringBuffer("update WORK_AREA set");
		if (workArea.getName() != null) {
			sql.append(" NAME=?,");
		}
		if (workArea.getWorkTypeId() != null) {
			sql.append(" WORK_TYPE_ID=?,");
		}
		if (workArea.getLocalNetId() != null) {
			sql.append(" LOCAL_NET_ID=?,");
		}
		if (workArea.getAreaId() != null) {
			sql.append(" AREA_ID=?,");
		}
		if (workArea.getServDeptId() != null) {
			sql.append(" SERV_DEPT_ID=?,");
		}
		if (workArea.getBranchId() != null) {
			sql.append(" BRANCH_ID=?,");
		}
		if (workArea.getDispatchLevel() != null) {
			sql.append(" DISPATCH_LEVEL=?,");
		}
		if (workArea.getParentWorkAreaId() != null) {
			sql.append(" PARENT_WORK_AREA_ID=?,");
		}
		if (workArea.getStandardCode() != null) {
			sql.append(" STANDARD_CODE=?,");
		}
		if (workArea.getSts() != null) {
			sql.append(" STS=?,");
		}
		if (workArea.getStsDate() != null) {
			sql.append(" STS_DATE=?,");
		}
		if (workArea.getCreateDate() != null) {
			sql.append(" CREATE_DATE=?,");
		}
		if (workArea.getChannelId() != null) {
			sql.append(" channel_id=?,");
		}
		if (workArea.getWorkMode() != null) {
			sql.append(" work_mode=?,");
		}
		if (workArea.getTermNbr() != null) {
			sql.append(" TERM_NBR=?,");
		}
		if (workArea.getTermType() != null) {
			sql.append(" TERM_TYPE=?,");
		}
		if (workArea.getRemarks() != null) {
			sql.append(" remarks=?,");
		}
		sql = sql.delete(sql.length() - 1, sql.length());
		sql.append(" where 1=1");
		sql.append(" and WORK_AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (workArea.getName() != null) {
				ps.setString(index++, workArea.getName());
			}
			if (workArea.getWorkTypeId() != null) {
				ps.setString(index++, workArea.getWorkTypeId());
			}
			if (workArea.getLocalNetId() != null) {
				ps.setString(index++, workArea.getLocalNetId());
			}
			if (workArea.getAreaId() != null) {
				ps.setString(index++, workArea.getAreaId());
			}
			if (workArea.getServDeptId() != null) {
				ps.setString(index++, workArea.getServDeptId());
			}
			if (workArea.getBranchId() != null) {
				ps.setString(index++, workArea.getBranchId());
			}
			if (workArea.getDispatchLevel() != null) {
				ps.setString(index++, workArea.getDispatchLevel());
			}
			if (workArea.getParentWorkAreaId() != null) {
				ps.setString(index++, workArea.getParentWorkAreaId());
			}
			if (workArea.getStandardCode() != null) {
				ps.setString(index++, workArea.getStandardCode());
			}
			if (workArea.getSts() != null) {
				ps.setString(index++, workArea.getSts());
			}
			if (workArea.getStsDate() != null) {
				ps.setDate(index++, (java.sql.Date) workArea.getStsDate());
			}
			if (workArea.getCreateDate() != null) {
				ps.setDate(index++, (java.sql.Date) workArea.getCreateDate());
			}
			if (workArea.getChannelId() != null) {
				ps.setString(index++, workArea.getChannelId());
			}
			if (workArea.getWorkMode() != null) {
				ps.setString(index++, workArea.getWorkMode());
			}
			if (workArea.getTermNbr() != null) {
				ps.setString(index++, workArea.getTermNbr());
			}
			if (workArea.getTermType() != null) {
				ps.setString(index++, workArea.getTermType());
			}
			if (workArea.getRemarks() != null) {
				ps.setString(index++, workArea.getRemarks());
			}

			ps.setString(index++, workArea.getWorkAreaId());
			ps.execute();
			String openFlag = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG, null,
					null, null, null, null);//从配置表获取是否调用同步资源的存储过程的开关
			if(!StringUtil.isBlank(openFlag)&&SysConstants.TRUE.equals(openFlag)){
			//if (workArea.getWorkTypeId().equals("105")) {
				WorkAreaSync("M", workArea.getWorkAreaId(), workArea
						.getLocalNetId(), workArea.getAreaId(), workArea
						.getName(), workArea.getDispatchLevel(), workArea
						.getParentWorkAreaId(), workArea.getStandardCode(),
						workArea.getSts(), workArea.getStsDate(), workArea
								.getCreateDate(), workArea.getWorkMode());
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SysException("", "update error..", e);
		} finally {
			try {
				connection.commit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	public void deleteWorkArea(GenericVO vo) throws AppException, SysException {
		WorkAreaMVO workArea = (WorkAreaMVO) vo;
		if (vo == null) {
			throw new RuntimeException();
		}
		StringBuffer sql = new StringBuffer("delete from WORK_AREA where 1=1");
		sql.append(" and WORK_AREA_ID=?");
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, workArea.getWorkAreaId());
			log.debug(workArea.getWorkAreaId() + "=="
					+ workArea.getWorkTypeId());
			ps.execute();
			String openFlag = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_SYNC_RES_PROCEDURE_FLAG, null,
					null, null, null, null);//从配置表获取是否调用同步资源的存储过程的开关
			if(!StringUtil.isBlank(openFlag)&&SysConstants.TRUE.equals(openFlag)){
			//if (workArea.getWorkTypeId() != null
			//		&& workArea.getWorkTypeId().equals("105")) {
				WorkAreaSync("R", workArea.getWorkAreaId(), workArea
						.getLocalNetId(), workArea.getAreaId(), workArea
						.getName(), workArea.getDispatchLevel(), workArea
						.getParentWorkAreaId(), workArea.getStandardCode(),
						workArea.getSts(), workArea.getStsDate(), workArea
								.getCreateDate(), workArea.getWorkMode());
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SysException("", "delete error..", e);
		} finally {
			try {
				connection.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	public void WorkAreaSync(String actType, String workAreaId,
			String localNetId, String areaId, String name,
			String dispatchLevel, String parentWorkAreaId, String standardCode,
			String sts, Date stsDate, Date createDate, String workMode)
			throws SysException, AppException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("{call P_SYNC_WORK_AREA(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		Connection conn = null;
		CallableStatement pscall = null;
		String retMsg = null;
		String stsdate = null;
		String createdate = null;
		String rserror = null;
		SimpleDateFormat SDM = new SimpleDateFormat("yyyy-MM-dd");
		if (stsDate != null) {
			stsdate = SDM.format(stsDate);
		}
		if (createDate != null) {
			createdate = SDM.format(createDate);
		}
		try {
			conn = ConnectionFactory.getConnection();
			pscall = conn.prepareCall(sql.toString());
			pscall.setString(1, actType);
			pscall.setString(2, workAreaId);
			pscall.setString(3, localNetId);
			pscall.setString(4, areaId);
			pscall.setString(5, name);
			pscall.setString(6, dispatchLevel);
			pscall.setString(7, parentWorkAreaId);
			pscall.setString(8, standardCode);
			pscall.setString(9, sts);
			pscall.setString(10, stsdate);
			pscall.setString(11, createdate);
			pscall.setString(12, workMode);
			pscall.registerOutParameter(13, java.sql.Types.VARCHAR);
			pscall.registerOutParameter(14, java.sql.Types.VARCHAR);
			pscall.executeUpdate();
		} catch (Exception e) {
			throw new SysException("", "workSync errors", e);
		} finally {
			try {
				if (pscall != null) {
					pscall.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public GenericVO findByWorkExch(String work_area_exch_id)
			throws SysException, AppException {
		WorkAreaSVO result = null;
		StringBuffer sql = new StringBuffer(
				" SELECT * FROM WORK_AREA WHERE WORK_AREA_ID IN(SELECT DISTINCT WORK_AREA_ID FROM WORK_AREA_EXCH WHERE WORK_AREA_EXCH_ID=?)");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, work_area_exch_id);
			rs = ps.executeQuery();
			result = (WorkAreaSVO) ResultSetUtil.convertToVo(rs,
					WorkAreaSVO.class);
		} catch (SQLException e) {
			throw new SysException("", "findByWorkExch error..", e);
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
		List rtnList = null;
		WorkAreaMVO workArea = (WorkAreaMVO) vo;

		Sql sql = new Sql("SELECT WA.*,");
		sql
				.append(" (SELECT TP.STS_WORDS FROM STATUS TP WHERE TP.TABLE_NAME = 'WORK_AREA' AND TP.COLUMN_NAME = 'TERM_TYPE' AND TP.STS_ID = WA.TERM_TYPE) TERM_TYPE_NAME,");
		sql
				.append(" (SELECT WM.STS_WORDS FROM STATUS WM WHERE WM.TABLE_NAME = 'WORK_AREA' AND WM.COLUMN_NAME = 'WORK_MODE' AND WM.STS_ID = WA.WORK_MODE) WORK_MODE_NAME,");
		sql
				.append(" (SELECT WM.STS_WORDS FROM STATUS WM WHERE WM.TABLE_NAME = 'WORK_AREA' AND WM.COLUMN_NAME = 'WORK_MODE' AND WM.STS_ID = WA.WORK_MODE) WORK_MODE_NAME,");
		sql
				.append(" (SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME = 'WORK_AREA' AND S.COLUMN_NAME = 'STS' AND S.STS_ID = WA.STS) STS_NAME,");
		sql
				.append(" (SELECT PWA.NAME FROM WORK_AREA PWA WHERE PWA.WORK_AREA_ID = WA.PARENT_WORK_AREA_ID) PARENT_WORK_AREA_NAME,");
		sql
				.append(" (SELECT SD.NAME FROM SERV_DEPT SD WHERE WA.SERV_DEPT_ID = SD.SERV_DEPT_ID AND SD.STS = 'A') SERV_DEPT_NAME,");
		sql
				.append(" (SELECT B.NAME FROM BRANCH B WHERE WA.BRANCH_ID = B.BRANCH_ID AND B.STS = 'A') BRANCH_NAME,");
		sql
				.append(" (SELECT WT.NAME FROM WORK_TYPE WT WHERE WA.WORK_TYPE_ID = WT.WORK_TYPE_ID AND WT.STS = 'A') WORK_TYPE_NAME,");
		sql.append(" LN.NAME LOCAL_NET_NAME, AREA.NAME AREA_NAME");
		sql.append(" FROM WORK_AREA WA, LOCAL_NET LN, AREA");
		sql.append(" WHERE WA.LOCAL_NET_ID = LN.LOCAL_NET_ID AND LN.STS = 'A'");
		sql.append(" AND WA.AREA_ID = AREA.AREA_ID AND AREA.STS = 'A'");

		if (workArea.getFlagWorkAreaId() == 1) {
			sql.append(" AND WA.WORK_AREA_ID=:workAreaId");
			sql.setString("workAreaId", removeBlank(workArea.getWorkAreaId()
					.trim()));
		}
		if (workArea.getFlagWorkAreaName() == 1) {
			sql.append(" AND WA.NAME like :workAreaName");
			sql.setString("workAreaName", "%" + workArea.getName() + "%");
		}
		if (workArea.getFlagWorkTypeId() == 1) {
			sql.append(" AND WA.WORK_TYPE_ID=:workTypeId");
			sql.setLong("workTypeId", workArea.getWorkTypeId());
		}
		if (workArea.getFlagLocalNetId() == 1) {
			sql.append(" AND WA.LOCAL_NET_ID=:localNetId");
			sql.setLong("localNetId", workArea.getLocalNetId());
		}
		if (workArea.getFlagAreaId() == 1) {
			sql.append(" AND WA.AREA_ID=:areaId");
			sql.setLong("areaId", workArea.getAreaId());
		}
		if (workArea.getFlagServDeptId() == 1) {
			sql.append(" AND WA.SERV_DEPT_ID=:servDeptId");
			sql.setString("servDeptId", workArea.getServDeptId());
		}
		if (workArea.getFlagBranchId() == 1) {
			sql.append(" AND WA.BRANCH_ID=:branchId");
			sql.setLong("branchId", workArea.getBranchId());
		}
		if (workArea.getFlagSts() == 1) {
			sql.append(" AND WA.STS=:sts");
			sql.setString("sts", workArea.getSts());
		}

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			rtnList = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);
		} catch (SQLException e) {
			throw new SysException("", "findByVO error..", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rtnList;
	}

	public static String removeBlank(String str) {
		// modi by liyaquan
		// StringBuilder sb = new StringBuilder();
		StringBuffer sb = new StringBuffer();
		char c = ' ';
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch != c)
				sb.append(ch);
		}
		return sb.toString();
	}

	public List findByStepId(String stepId, String localNetId)
			throws AppException, SysException {
		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		Sql sql = new Sql(
				"select a.WORK_AREA_ID,a.NAME,a.WORK_TYPE_ID,a.LOCAL_NET_ID,a.AREA_ID,a.SERV_DEPT_ID,a.BRANCH_ID,a.DISPATCH_LEVEL, ");
		sql
				.append(" a.PARENT_WORK_AREA_ID,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.channel_id,a.work_mode ");
		sql
				.append(" from WORK_AREA a, STEP S where a.STS='A' AND S.STS='A' AND a.WORK_TYPE_ID = S.WORK_TYPE_ID AND S.STEP_ID =:stepId ");
		sql.setString("stepId", stepId);
		if (!StringUtil.isBlank(localNetId)) {
			sql.append("and a.local_net_id =:localNetId ");
			sql.setString("localNetId", localNetId);
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				LabelValueBean lvBean = new LabelValueBean();
				lvBean.setLabel(rs.getString("NAME"));
				lvBean.setValue(rs.getString("WORK_AREA_ID"));
				results.add(lvBean);
			}
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

	/**
	 * 生成工区编码 add by zhaodan 2009-7-30
	 */
	public String getWorkAreaId(WorkAreaMVO waVO) throws AppException,
			SysException {
		
//		Sql sql = new Sql(
//				" select substr(max(work_area_id),(length(max(work_area_id))-1),2) last_two_nbr from work_area ");
//		sql.append(" where 1=1 ");
		// modity zhanghsuaia 2013/09/30 1214~1217行 现在通过count来取当前工区编码的最大值。然后区最后3位。
		Sql sql = new Sql(
				" select substr(max(work_area_id),(length(max(work_area_id))-1),2) last_two_nbr from work_area ");
		sql = new Sql(" select substr('000'||(count(1)+1),-3) last_two_nbr from work_area ");
		sql.append(" where 1=1 ");
		
		if (waVO != null) {
			String areaId = waVO.getAreaId();
			String workTypeId = waVO.getWorkTypeId();
			if (!StringUtil.isBlank(areaId)) {
				sql.append(" and area_id = :areaId ");
				sql.setString("areaId", areaId);
			}
			if (!StringUtil.isBlank(workTypeId)) {
				sql.append(" and work_type_id = :workTypeId ");
				sql.setString("workTypeId", workTypeId);
			}
		}

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String lastTwoNbr = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();

			while (rs.next()) {
				lastTwoNbr = rs.getString("LAST_TWO_NBR");
			}
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
		if (lastTwoNbr != null) {
			log.debug("最后两位数字：" + lastTwoNbr);
			return lastTwoNbr;
		} else {
			return "00";
		}
	}

	/*------------------------------------- 工区与社会关系维护---------------------------------------*/

	/**
	 * @method_name findMaintsSelected
	 * @author lijixu
	 * @date 2009-9-3 上午11:33:10
	 * @description 查询已选择的社区列表
	 * @param waevo
	 * @return
	 * @throws SysException
	 * @throws AppException
	 * @reviewed_by
	 */
	public List findMaintsSelected(WorkAreaMVO vo) throws AppException,
			SysException {
		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("SELECT MWA.MAINT_WORK_AREA_ID ,MWA.WORK_AREA_ID MWA_WORK_AREA_ID, MWA.MAINT_AREA_ID MWA_MAINT_AREA_ID, MWA.STS MAINT_WORK_AREA_STS, (SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME='MAINT_WORK_AREA' AND S.COLUMN_NAME='STS' AND S.STS_ID=MWA.STS) MAINT_WORK_AREA_STS_NAME, MWA.STS_DATE, MWA.ACTION_ID, MWA.REMARKS, WA.NAME WORK_AREA_NAME, WA.WORK_TYPE_ID WORK_AREA_WORK_TYPE_ID, WA.LOCAL_NET_ID WORK_AREA_LOCAL_NET_ID, WA.AREA_ID WORK_AREA_AREA_ID, WA.STS WORK_AREA_STS, (SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME='WORK_AREA' AND S.COLUMN_NAME='STS' AND S.STS_ID=WA.STS) WORK_AREA_STS_NAME, MA.NAME MAINT_AREA_NAME, MA.STS MAINT_AREA_STS, (SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME='MAINT_AREA' AND S.COLUMN_NAME='STS' AND S.STS_ID=MA.STS) MAINT_AREA_STS_NAME  FROM MAINT_WORK_AREA MWA, WORK_AREA WA, MAINT_AREA MA WHERE 1 = 1   AND MWA.WORK_AREA_ID = WA.WORK_AREA_ID AND MWA.MAINT_AREA_ID = MA.MAINT_AREA_ID  ");
		if (vo.getMaintWorkAreaId() != null) {
			sql.append("and MWA.MAINT_WORK_AREA_ID=? ");
		}
		if (vo.getMaintAreaId() != null) {
			sql.append("and MWA.WORK_AREA_ID=? ");
		}
		if (vo.getWorkAreaId() != null) {
			sql.append("and MWA.work_area_id=? ");
		}
		if (vo.getWorkTypeId() != null) {
			sql.append("and WA.WORK_TYPE_ID=? ");
		}

		sql.append(" AND MWA.STS = 'A' AND WA.STS = 'A' AND MA.STS = 'A' ");
		log.debug(sql);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (vo.getMaintWorkAreaId() != null) {
				ps.setString(index++, vo.getMaintWorkAreaId().trim());
			}
			if (vo.getMaintAreaId() != null) {
				ps.setString(index++, vo.getMaintAreaId().trim());
			}
			if (vo.getWorkAreaId() != null) {
				ps.setString(index++, vo.getWorkAreaId());
			}
			if (vo.getWorkTypeId() != null) {
				ps.setString(index++, vo.getWorkTypeId());
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				WorkAreaMVO mvo = new WorkAreaMVO();
				mvo.setMaintWorkAreaId(rs.getString("MAINT_WORK_AREA_ID"));
				mvo.setWorkAreaId(rs.getString("MWA_WORK_AREA_ID"));
				mvo.setWorkAreaName(rs.getString("WORK_AREA_NAME"));
				mvo.setMaintAreaId(rs.getString("MWA_MAINT_AREA_ID"));
				mvo.setMaintAreaName(rs.getString("MAINT_AREA_NAME"));

				mvo.setMaintAreaSts(rs.getString("MAINT_AREA_STS"));
				mvo.setSts(rs.getString("WORK_AREA_STS"));
				mvo.setMaintWorkAreaSts(rs.getString("MAINT_WORK_AREA_STS"));

				mvo.setLocalNetId(rs.getString("WORK_AREA_LOCAL_NET_ID"));
				mvo.setAreaId(rs.getString("WORK_AREA_AREA_ID"));
				results.add(mvo);
			}

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

	/**
	 * @method_name findExchsUnSelSo
	 * @author lijixu
	 * @date 2009-9-3 上午11:33:10
	 * @description 查询可选的社区列表
	 * @param waevo
	 * @return
	 * @throws SysException
	 * @throws AppException
	 * @reviewed_by
	 */
	public List findMaintsUnSel(WorkAreaMVO waevo) throws SysException,
			AppException {

		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append(" SELECT MA.MAINT_AREA_ID, MA.NAME MAINT_AREA_NAME, WA.WORK_AREA_ID, WA.NAME WORK_AREA_NAME, WA.WORK_TYPE_ID, WA.LOCAL_NET_ID, WA.AREA_ID FROM MAINT_AREA MA, WORK_AREA WA WHERE 1=1 AND MA.AREA_ID = WA.AREA_ID AND NOT EXISTS (SELECT 1 FROM MAINT_WORK_AREA MWA WHERE MWA.MAINT_AREA_ID = MA.MAINT_AREA_ID AND MWA.WORK_AREA_ID = WA.WORK_AREA_ID AND MWA.STS = 'A' ) AND NOT EXISTS (SELECT 1 FROM MAINT_WORK_AREA MWA, WORK_AREA WA2 WHERE MWA.MAINT_AREA_ID = MA.MAINT_AREA_ID AND MWA.WORK_AREA_ID = WA2.WORK_AREA_ID AND WA2.WORK_TYPE_ID = WA.WORK_TYPE_ID AND MWA.STS = 'A' AND WA2.STS = 'A' ) AND WA.WORK_AREA_ID = ? AND MA.STS = 'A'  AND WA.STS = 'A' ");
		// sql.append(" SELECT MA.MAINT_AREA_ID, MA.NAME, WA.WORK_AREA_ID,
		// WA.NAME, WA.WORK_TYPE_ID, WA.LOCAL_NET_ID, WA.AREA_ID FROM MAINT_AREA
		// MA, WORK_AREA WA WHERE 1=1 AND NOT EXISTS (SELECT 1 FROM
		// MAINT_WORK_AREA MWA WHERE MWA.MAINT_AREA_ID = MA.MAINT_AREA_ID AND
		// MWA.WORK_AREA_ID = WA.WORK_AREA_ID AND MWA.STS = 'A' ) AND NOT EXISTS
		// (SELECT 1 FROM MAINT_WORK_AREA MWA, WORK_AREA WA2 WHERE
		// MWA.MAINT_AREA_ID = MA.MAINT_AREA_ID AND MWA.WORK_AREA_ID =
		// WA2.WORK_AREA_ID AND WA2.WORK_TYPE_ID = WA.WORK_TYPE_ID AND MWA.STS =
		// 'A' AND WA2.STS = 'A' ) AND MA.STS = 'A' AND WA.STS = 'A' ");
		if (waevo.getWorkAreaId() == null)
			return results;
		log.debug(sql);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, waevo.getWorkAreaId());
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);
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

	/**
	 * @method_name findWorkAreaId
	 * @author lijixu
	 * @date 2009-9-4 下午05:16:51
	 * @description 根据社区id 查询 工区id
	 * @param maintAreaId
	 * @param workTypeId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 * @reviewed_by
	 */
	public List findWorkAreaId(String maintAreaId, String workTypeId)
			throws AppException, SysException {
		List list = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Sql sql = new Sql(
				" SELECT MWA.WORK_AREA_ID WORK_AREA_ID, WA.WORK_TYPE_ID WORK_TYPE_ID  FROM MAINT_WORK_AREA MWA ,WORK_AREA WA  WHERE 1=1  AND MWA.WORK_AREA_ID =WA.WORK_AREA_ID  ");
		sql
				.append(" AND WA.MAINT_AREA_ID =:maintAreaId AND WA.WORK_TYPE_ID =:workTypeId ");
		sql.setString("maintAreaId", maintAreaId);
		sql.setString("workTypeId", workTypeId);
		sql.append("  AND WA.STS = 'A' AND MWA.STS = 'A' ");
		String ret = null;

		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
			while (rs.next()) {

				ret = rs.getString("WORK_AREA_ID");
				list.add(ret);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		return list;
	}

	
	/*------------------------------------- 工区与网格关系维护---------------------------------------*/

	/**
	 * @method_name findMaintsSelected
	 * @author lijixu
	 * @date 2009-9-3 上午11:33:10
	 * @description 查询已选择的网格列表
	 * @param waevo
	 * @return
	 * @throws SysException
	 * @throws AppException
	 * @reviewed_by
	 */
	public List findServDeptSelected(WorkAreaMVO vo) throws AppException,
			SysException {
		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append("SELECT WAO.WORK_AREA_OBJ_ID,WAO.WORK_AREA_ID WAO_WORK_AREA_ID, WAO.OBJECT_ID WAO_SERV_DEPT_ID, WAO.STS WORK_AREA_OBJ_STS, (SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME = 'WORK_AREA_OBJ'AND S.COLUMN_NAME = 'STS' AND S.STS_ID = WAO.STS) MAINT_WORK_AREA_STS_NAME,WAO.STS_DATE,WAO.ACTION_ID, WAO.REMARKS,WA.NAME WORK_AREA_NAME, WA.WORK_TYPE_ID WORK_AREA_WORK_TYPE_ID, WA.LOCAL_NET_ID WORK_AREA_LOCAL_NET_ID, WA.AREA_ID WORK_AREA_AREA_ID, WA.STS WORK_AREA_STS,(SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME = 'WORK_AREA' AND S.COLUMN_NAME = 'STS' AND S.STS_ID = WA.STS) WORK_AREA_STS_NAME, SD.NAME SERV_DEPT_NAME, SD.STS SERV_DEPT_STS, (SELECT S.STS_WORDS FROM STATUS S WHERE S.TABLE_NAME = 'SERV_DEPT'AND S.COLUMN_NAME = 'STS' AND S.STS_ID = SD.STS) SERV_DEPT_STS_NAME FROM WORK_AREA_OBJ WAO, WORK_AREA WA, SERV_DEPT SD WHERE 1 = 1 AND WAO.WORK_AREA_ID = WA.WORK_AREA_ID AND WAO.OBJECT_ID = SD.SERV_DEPT_ID  ");
		if (vo.getWorkAreaObjId() != null) {
			sql.append("and WAO.WORK_AREA_OBJ_ID=? ");
		}
		if (vo.getServDeptId() != null) {
			sql.append("and WAO.OBJECT_ID=? ");
		}
		if (vo.getWorkAreaId() != null) {
			sql.append("and WAO.work_area_id=? ");
		}
		if (vo.getWorkTypeId() != null) {
			sql.append("and WA.WORK_TYPE_ID=? ");
		}

		sql.append(" AND WAO.STS = 'A' AND WA.STS = 'A' AND SD.STS = 'A' ");
		log.debug(sql);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (vo.getWorkAreaObjId() != null) {
				ps.setString(index++, vo.getWorkAreaObjId().trim());
			}
			if (vo.getServDeptId() != null) {
				ps.setString(index++, vo.getServDeptId().trim());
			}
			if (vo.getWorkAreaId() != null) {
				ps.setString(index++, vo.getWorkAreaId());
			}
			if (vo.getWorkTypeId() != null) {
				ps.setString(index++, vo.getWorkTypeId());
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				WorkAreaMVO mvo = new WorkAreaMVO();
				mvo.setWorkAreaObjId(rs.getString("WORK_AREA_OBJ_ID"));
				mvo.setWorkAreaId(rs.getString("WAO_WORK_AREA_ID"));
				mvo.setWorkAreaName(rs.getString("WORK_AREA_NAME"));
				mvo.setServDeptId(rs.getString("WAO_SERV_DEPT_ID"));
				mvo.setServDeptName(rs.getString("SERV_DEPT_NAME"));
				mvo.setServDeptSts(rs.getString("SERV_DEPT_STS"));
				mvo.setSts(rs.getString("WORK_AREA_STS"));
				mvo.setWorkAreaObjSts(rs.getString("WORK_AREA_OBJ_STS"));

				mvo.setLocalNetId(rs.getString("WORK_AREA_LOCAL_NET_ID"));
				mvo.setAreaId(rs.getString("WORK_AREA_AREA_ID"));
				results.add(mvo);
			}

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

	/**
	 * @method_name findExchsUnSelSo
	 * @author lijixu
	 * @date 2009-9-3 上午11:33:10
	 * @description 查询可选的社区列表
	 * @param waevo
	 * @return
	 * @throws SysException
	 * @throws AppException
	 * @reviewed_by
	 */
	public List findServDeptUnSel(WorkAreaMVO waevo) throws SysException,
			AppException {

		List results = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT SD.SERV_DEPT_ID,SD.NAME SERV_DEPT_NAME, WA.WORK_AREA_ID, WA.NAME WORK_AREA_NAME,WA.WORK_TYPE_ID,WA.LOCAL_NET_ID,WA.AREA_ID FROM SERV_DEPT SD, WORK_AREA WA WHERE 1 = 1 AND SD.AREA_ID = WA.AREA_ID AND NOT EXISTS (SELECT 1 FROM WORK_AREA_OBJ WAO WHERE WAO.OBJECT_ID = SD.SERV_DEPT_ID AND WAO.WORK_AREA_ID = WA.WORK_AREA_ID AND WAO.STS = 'A') AND NOT EXISTS (SELECT 1  FROM WORK_AREA_OBJ WAO, WORK_AREA WA2 WHERE WAO.OBJECT_ID =SD.SERV_DEPT_ID AND WAO.WORK_AREA_ID = WA2.WORK_AREA_ID AND WA2.WORK_TYPE_ID = WA.WORK_TYPE_ID  AND WAO.STS = 'A'  AND WA2.STS = 'A')  AND WA.WORK_AREA_ID =?   AND SD.STS = 'A'  AND WA.STS = 'A' ");
	
		if (waevo.getWorkAreaId() == null)
			return results;
		log.debug(sql);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, waevo.getWorkAreaId());
			rs = ps.executeQuery();
			results = (List) ResultSetUtil.convertToList(rs, WorkAreaMVO.class);
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

	/**
	 * @method_name findWorkAreaId
	 * @author lijixu
	 * @date 2009-9-4 下午05:16:51
	 * @description 根据社区id 查询 工区id
	 * @param maintAreaId
	 * @param workTypeId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 * @reviewed_by
	 */
	public List findWorkAreaIdbyServDept(String maintAreaId, String workTypeId)
			throws AppException, SysException {
		List list = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Sql sql = new Sql(
				" SELECT MWA.WORK_AREA_ID WORK_AREA_ID, WA.WORK_TYPE_ID WORK_TYPE_ID  FROM MAINT_WORK_AREA MWA ,WORK_AREA WA  WHERE 1=1  AND MWA.WORK_AREA_ID =WA.WORK_AREA_ID  ");
		sql
				.append(" AND WA.MAINT_AREA_ID =:maintAreaId AND WA.WORK_TYPE_ID =:workTypeId ");
		sql.setString("maintAreaId", maintAreaId);
		sql.setString("workTypeId", workTypeId);
		sql.append("  AND WA.STS = 'A' AND MWA.STS = 'A' ");
		String ret = null;

		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
			while (rs.next()) {

				ret = rs.getString("WORK_AREA_ID");
				list.add(ret);
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		return list;
	}
	/*------------------------------------- 工区与网格关系维护---------------------------------------*/
	
	/**
	 * 查询分局编码
	 * 用于吉林联通，驻地网新建扩容接口
	 */
	public String findStationCodeByWoNbr(String woNbr) throws AppException, SysException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sql sql = new Sql(
				"SELECT T1.LOCAL_NET_ID, T1.AREA_ID, T1.WORK_AREA_ID, T1.WORK_AREA_NAME, T1.FJBM, T1.FJMC");
		sql.append(" FROM WORK_AREA_YXZX_FJ_DY T1, WORK_AREA_EXCH T2, WO T3");
		sql.append(" WHERE T2.STS = 'A' AND T2.WORK_AREA_ID = T1.WORK_AREA_ID AND T3.EXCH_ID = T2.EXCH_ID");
		sql.append(" AND T3.WO_NBR =:woNbr");
		sql.setString("woNbr", woNbr);
		String stationCode = null;

		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			ps = sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				stationCode = rs.getString("FJBM");
				break;
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return stationCode;
	}
	
	/**
	 * 查询分局编码 ，调用存储过程
	 * 用于吉林联通，驻地网新建扩容接口
	 */
	public Map findStationCodeByWoNbr(String woNbr,String type) throws AppException, SysException {
		ResultSet rs = null;
		CallableStatement proc = null;

		String oflag = null;
		String remark = null;
		String stationCode = null;
		Map retMap = new HashMap();
		log.debug("调用存储过程 P_GET_FJBM 工单号：" + woNbr +"建设类型：" + type);
		try {
			Connection conn = ConnectionFactory.getConnection();
			String sql = "{ call P_GET_FJBM(?,?,?,?,?) }";
			proc = conn.prepareCall(sql);
			log.debug(sql);
			proc.setString(1, woNbr);//工单编号
			proc.setString(2, type);//建设类型 5 新建 6扩容
			proc.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);//查询结果 0查询失败，1查询成功
			proc.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);//查询结果描述
			proc.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);//分局编码
			proc.execute();
			// 查看执行后的状态代码和错误描述
			oflag = (String) proc.getObject(3);
			remark = (String) proc.getObject(4);
			stationCode = (String) proc.getObject(5);
			retMap.put("oflag", oflag);
			retMap.put("remark", remark);
			retMap.put("stationCode", stationCode);
			log.debug("分局编码查询存储过程返回状态代码：  " + retMap.toString());
		} catch (SQLException se) {
			throw new SysException("100002", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, proc);
		}

		return retMap;
	}
	
	public List getWorkAreasList(GenericVO vo) throws AppException, SysException {
		return null;
	}

}
