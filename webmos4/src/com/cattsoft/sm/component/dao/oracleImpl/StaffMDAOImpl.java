package com.cattsoft.sm.component.dao.oracleImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.JdbcUtil;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagUtil;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.PartitionUtil;
import com.cattsoft.pub.util.ResultSetUtil;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.component.dao.IStaffMDAO;
import com.cattsoft.sm.component.domain.SysUserDOM;
import com.cattsoft.sm.util.ReflectUtil;
import com.cattsoft.sm.vo.DevelopMVO;
import com.cattsoft.sm.vo.LocalNetSVO;
import com.cattsoft.sm.vo.OrgDeptSVO;
import com.cattsoft.sm.vo.PartyRoleSVO;
import com.cattsoft.sm.vo.PartySVO;
import com.cattsoft.sm.vo.StaffExtendMVO;
import com.cattsoft.sm.vo.StaffMVO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;
import com.cattsoft.sm.vo.WorkAreaSVO;

public class StaffMDAOImpl implements IStaffMDAO {

	private static Logger log = Logger.getLogger(SysUserDOM.class);
	
	
	public List findByLabelValueBean(GenericVO vo) throws SysException, AppException {
		WorkAreaSVO svo = (WorkAreaSVO) vo;
		if(StringUtil.isBlank(svo.getAreaId())&& StringUtil.isBlank(svo.getLocalNetId())){
			throw new AppException("10000000","缺少必须的参数");
		}
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						" select a.STAFF_ID,b.name,b.local_net_id,b.area_id,b.serv_dept_id,b.branch_id ")
				.append(" from staff a, party b,work_area w,staff_work_area sw where 1 = 1 and a.party_id = b.party_id ")
				.append(" and w.area_id=b.area_id and w.local_net_id=b.local_net_id and sw.work_area_id=w.work_area_id and a.staff_id=sw.staff_id ");
				
		if (svo.getAreaId() != null) {
			sql.append(" and w.area_id=? ");
		}
		if (svo.getLocalNetId() != null) {
			sql.append(" and w.local_net_id=? ");
		}
		
		sql.append("and a.sts='A' and b.sts='A' and w.sts='A' and sw.sts='A' group by a.staff_id,b.name,b.local_net_id,b.area_id,b.serv_dept_id,b.branch_id");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		LabelValueBean lvBean = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(), svo
					.getLocalNetId()));
			int index = 1;		
		
			if (svo.getAreaId() != null) {
				ps.setString(index++, svo.getAreaId());
			}
			if (svo.getLocalNetId() != null) {
				ps.setString(index++, svo.getLocalNetId());
			}
			
			log.debug(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				lvBean = new LabelValueBean();
				lvBean.setLabel(rs.getString("NAME"));
				lvBean.setValue(rs.getString("staff_id"));
				vos.add(lvBean);
			}

		} catch (SQLException e) {			
			throw new SysException("10000002", "query error..", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
		return vos;
	}
	
	public List getWorkStaffList(WorkAreaSVO svo) throws SysException, AppException {
		StaffMVO staff=null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql.append("select s.STAFF_ID,p.NAME from STAFF s,PARTY p   ");
		sql.append(" where 1=1 ");
		sql.append(" and s.PARTY_ID=p.PARTY_ID ");
		sql.append(" and  s.STAFF_ID in (select sw.STAFF_ID from STAFF_WORK_AREA sw where sw.WORK_AREA_ID = ? )");
				
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index, svo.getWorkAreaId());
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				staff=new StaffMVO();
				staff.getStaffSVO().setStaffId(rs.getString("STAFF_ID"));
				staff.getPartySVO().setName(rs.getString("NAME"));
				vos.add(staff);
			}
		} catch (Exception e) {
			throw new SysException("", "findCustAcctInUseByVO error..", e);
		}
		if(vos==null || vos.size()==0){
			vos= new ArrayList();
		}
		return vos;
	}
	/**
	 * 得到某个员工可访问的本地网列表
	 * 
	 * @param vo
	 *            SysUserVO
	 * @throws SysException
	 * @return SysUserVO
	 */
	public List getStaffLocalNetVOs(Long staffId, String systemName) throws SysException {
		List vos = null;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE ");
		sql.append("from Local_Net  a ");
		sql.append("where a.sts='A' and a.local_Net_Id in ( ");
		sql.append("select distinct wa.local_Net_Id ");
		sql.append("from Work_Area  wa ");
		sql.append("where wa.sts='A' and wa.work_Area_Id in (");
		sql.append("select swa.work_Area_Id ");
		sql.append("from Staff_Work_Area  swa ");
		sql.append("where swa.sts='A' and swa.staff_Id=?) ");
		if (systemName != null) {
			sql.append("and wa.work_Type_Id in(");
			sql.append("select sswt.work_Type_Id ");
			sql.append("from Sub_System_Work_Type sswt ");
			sql.append("where sswt.sub_System_Name=? )");
		}
		sql.append(")");
		System.out.println("sql语句："+sql);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, staffId.toString());
			if (systemName != null) {
				ps.setString(index++, systemName);
			}
			rs = ps.executeQuery();

			vos = (List) ResultSetUtil.convertToList(rs, LocalNetSVO.class);
		} catch (Exception e) {
			throw new SysException("", "findCustAcctInUseByVO error..", e);
		}
		return vos;
	}

	/**
	 * 得到某个员工可访问的本地网列表
	 * 
	 * lijixu 2009-11-16 22:10:38
	 * 
	 * @param vo
	 *            SysUserVO
	 * @throws SysException
	 * @return SysUserVO
	 */
	public List getStaffLocalNetVOs(String staffId, String systemName) throws SysException {
		List vos = null;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.LOCAL_NET_ID,a.ABBREV_NAME,a.NAME,a.DIST_NBR,a.ISCENTER,a.STS,a.STS_DATE,a.CREATE_DATE ");
		sql.append("from Local_Net  a ");
		sql.append("where a.sts='A' and a.local_Net_Id in ( ");
		sql.append("select distinct wa.local_Net_Id ");
		sql.append("from Work_Area  wa ");
		sql.append("where wa.sts='A' and wa.work_Area_Id in (");
		sql.append("select swa.work_Area_Id ");
		sql.append("from Staff_Work_Area  swa ");
		sql.append("where swa.sts='A' and swa.staff_Id=?) ");
		if (systemName != null) {
			sql.append("and wa.work_Type_Id in(");
			sql.append("select sswt.work_Type_Id ");
			sql.append("from Sub_System_Work_Type sswt ");
			sql.append("where sswt.sub_System_Name=? )");
		}
		sql.append(")");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, staffId);
			if (systemName != null) {
				ps.setString(index++, systemName);
			}
			rs = ps.executeQuery();

			vos = (List) ResultSetUtil.convertToList(rs, LocalNetSVO.class);
		} catch (Exception e) {
			throw new SysException("", "findCustAcctInUseByVO error..", e);
		}
		return vos;
	}
	
	/**
	 * 根据员工id查询员工信息。
	 * 
	 * @param vo
	 *            SysUserVO
	 * @throws SysException
	 * @return SysUserVO
	 */
	public StaffMVO findStaffMemberByStaffId(String staffId) throws AppException, SysException {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS, ")
				.append(" a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.dept_type staff_type, ")
				.append(" a.company_code,a.tel_nbr,a.terminal_flag,b.JUR_PERSON,b.NET_TYPE,b.TAX_NBR, ")
				.append(" b.COMPANY_SIZE,b.START_TIME,b.COMPANY_CHARACTER,b.COMPANY_STRUCTURE, ")
				.append(" b.REMARKS,b.GANDER,b.BIRTHDAY,b.AGE,b.NATIVE_PLACE,b.NATIONALITY, ")
				.append(" b.POLITICAL_STATUS,b.EDUC_LEVEL,b.GRADUATE_SCHOOL,b.MARITAL_STATUS, ")
				.append(" b.DOMESTIC_RELATION,b.MARITAL_DATE,b.COMPANY_NAME,b.DEPARTMENT, ")
				.append(" b.POSITION,b.WORK_ADDR,b.FUNC_DESC,b.SALARY,b.CHAR_TYPE,b.FAVORITE, ")
				.append(" b.TELECOM_ATTITUDE,b.WORK_EXPER,b.BRANCH_ID,b.AREA_ID,b.LOCAL_NET_ID, ")
				.append(" b.SERV_DEPT_ID,b.NAME,b.NAME_BRIEF,b.NAME_OTHER,b.PARTY_TYPE, ")
				.append(
						" b.STANDARD_CODE,b.STS P_STS,b.STS_DATE P_STS_DATE,b.CREATE_DATE P_CREATE_DATE, ")
				.append(
						" b.COMMUNICATION_SPECIALTY,b.MAIN_PRODUCTION,b.TOTAL_ASSET,b.WEB_ADDRESS, ")
				.append(
						" b.LAST_MOD_DATE,c.dept_name,c.dept_type,c.sts dept_sts,c.sts_date dept_sts_date ")
				.append(" ,pi.cert_Code,pi.cert_Exp_Date ")
				.append("  from Staff a,Party b,Org_Dept c ")
				.append(" ,PARTY_IDENTITY pi  ")
				.append(" where 1=1  ")
				.append(
						" and a.party_Id = b.party_Id and a.dept_Id=c.dept_Id(+)  and b.sts='A' and c.sts(+)='A' ")
				.append(" and b.party_id=pi.party_id(+) ");

		sql.append(" and a.staff_id=? ");

		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StaffMVO smo = new StaffMVO();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, staffId);
			rs = ps.executeQuery();
			PartySVO pvo = new PartySVO();
			StaffSVO svo = new StaffSVO();
			if (rs.next()) {
				// smo.setContactId(rs.getString("contact_id"));
				// smo.setContactAddr(rs.getString("contact_addr"));
				// smo.setPostalCode(rs.getString("postal_code"));
				// smo.setPartyIdentityId(rs.getString("party_identity_id"));
				// smo.setCertTypeId(rs.getString("cert_type_id"));
				smo.setCertCode(rs.getString("cert_code"));
				smo.setCertExpDate(rs.getDate("cert_exp_date"));
				smo.setDeptName(rs.getString("dept_name"));
				smo.setDeptSts(rs.getString("dept_sts"));
				smo.setDeptType(rs.getString("dept_type"));
				smo.setDeptStsDate(rs.getDate("dept_sts_date"));
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));
				svo.setDeptType(rs.getString("staff_type"));
				svo.setCompanyCode(rs.getString("company_code"));
				svo.setTelNbr(rs.getString("tel_nbr"));
				svo.setTerminalFlag(rs.getString("terminal_flag"));
				if (rs.getString("jur_person") != null) {
					pvo.setJurPerson(rs.getString("jur_person"));
				}
				if (rs.getString("net_type") != null) {
					pvo.setNetType(rs.getString("net_type"));
				}
				if (rs.getString("tax_nbr") != null) {
					pvo.setTaxNbr(rs.getString("tax_nbr"));
				}
				if (rs.getString("company_size") != null) {
					pvo.setCompanySize(rs.getString("company_size"));
				}
				if (rs.getString("company_structure") != null) {
					pvo.setCompanyStructure(rs.getString("company_structure"));
				}
				if (rs.getString("remarks") != null) {
					pvo.setRemarks(rs.getString("remarks"));
				}
				if (rs.getString("gander") != null) {
					pvo.setGander(rs.getString("gander"));
				}
				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("age") != null) {
					pvo.setAge(rs.getString("age"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("nationality") != null) {
					pvo.setNationality(rs.getString("nationality"));
				}
				if (!StringUtil.isBlank(rs.getString("political_status")))
					pvo.setPoliticalStatus(rs.getString("political_status").trim());
				if (rs.getString("educ_level") != null) {
					pvo.setEducLevel(rs.getString("educ_level"));
				}
				if (rs.getString("graduate_school") != null) {
					pvo.setGraduateSchool(rs.getString("graduate_school"));
				}
				if (rs.getString("marital_status") != null) {
					pvo.setMaritalStatus(rs.getString("marital_status"));
				}
				if (rs.getString("domestic_relation") != null) {
					pvo.setDomesticRelation(rs.getString("domestic_relation"));
				}
				if (rs.getString("marital_date") != null) {
					pvo.setMaritalDate(rs.getDate("marital_date"));
				}
				if (rs.getString("company_name") != null) {
					pvo.setCompanyName(rs.getString("company_name"));
				}
				if (rs.getString("department") != null) {
					pvo.setDepartment(rs.getString("department"));
				}
				if (rs.getString("position") != null) {
					pvo.setPosition(rs.getString("position"));
				}
				if (rs.getString("work_addr") != null) {
					pvo.setWorkAddr(rs.getString("work_addr"));
				}
				if (rs.getString("func_desc") != null) {
					pvo.setFuncDesc(rs.getString("func_desc"));
				}
				if (rs.getString("salary") != null) {
					pvo.setSalary(rs.getString("salary"));
				}
				if (rs.getString("char_type") != null) {
					pvo.setCharType(rs.getString("char_type"));
				}
				if (rs.getString("favorite") != null) {
					pvo.setFavorite(rs.getString("favorite"));
				}
				if (rs.getString("telecom_attitude") != null) {
					pvo.setTelecomAttitude(rs.getString("telecom_attitude"));
				}
				if (rs.getString("work_exper") != null) {
					pvo.setWorkExper(rs.getString("work_exper"));
				}
				if (rs.getString("party_id") != null) {
					pvo.setPartyId(rs.getString("party_id"));
				}
				if (rs.getString("branch_id") != null) {
					pvo.setBranchId(rs.getString("branch_id"));
				}
				if (rs.getString("area_id") != null) {
					pvo.setAreaId(rs.getString("area_id"));
				}
				if (rs.getString("local_net_id") != null) {
					pvo.setLocalNetId(rs.getString("local_net_id"));
				}
				if (rs.getString("serv_dept_id") != null) {
					pvo.setServDeptId(rs.getString("serv_dept_id"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				if (rs.getString("name_brief") != null) {
					pvo.setNameBrief(rs.getString("name_brief"));
				}
				if (rs.getString("name_other") != null) {
					pvo.setNameOther(rs.getString("name_other"));
				}
				if (rs.getString("party_type") != null) {
					pvo.setPartyType(rs.getString("party_type"));
				}
				if (rs.getString("standard_code") != null) {
					pvo.setStandardCode(rs.getString("standard_code"));
				}
				if (rs.getString("P_STS") != null) {
					pvo.setSts(rs.getString("P_STS"));
				}
				if (rs.getString("P_STS_DATE") != null) {
					pvo.setStsDate(rs.getTimestamp("P_STS_DATE"));
				}
				if (rs.getString("P_CREATE_DATE") != null) {
					pvo.setCreateDate(rs.getTimestamp("P_CREATE_DATE"));
				}
				if (rs.getString("communication_specialty") != null) {
					pvo.setCommunicationSpecialty(rs.getString("communication_specialty"));
				}
				if (rs.getString("main_production") != null) {
					pvo.setMainProduction(rs.getString("main_production"));
				}
				if (rs.getString("total_asset") != null) {
					pvo.setTotalAsset(rs.getString("total_asset"));
				}
				if (rs.getString("web_address") != null) {
					pvo.setWebAddress(rs.getString("web_address"));
				}
				if (rs.getString("last_mod_date") != null) {
					pvo.setLastModDate(rs.getTimestamp("last_mod_date"));
				}
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
			}

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

		return smo;

	}

	public PagView findStaffMemberFast(StaffMVO vo, PagInfo pagInfo, List oList, String deptIds)
			throws SysException, AppException {
		//StringBuffer sql = new StringBuffer();
		//sql
		//		.append(
		//				" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name from staff a,party b,org_dept d ")
		//		.append("where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) ");
		
		Sql sql = new Sql("");
		sql.append(
						" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name,s.sys_user_name from staff a,party b,org_dept d, sys_user s ");
		sql.append("where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) and a.staff_id = s.sys_user_id(+)");
		
        
		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		// if (oList != null && oList.size() > 0) {
		// sql.append(" and a.dept_id in (");
		// for (int i = 0; i < oList.size(); i++) {
		// if (i == 0) {
		// sql.append((String) oList.get(0));
		// } else
		// sql.append("," + (String) oList.get(i));
		// }
		// sql.append(")");
		// }
		if (deptIds != null) {
			sql.append(" and a.dept_id in (" );
			sql.append("select od.dept_id from org_dept od connect by nocycle prior od.dept_id = od.parent_dept_id start with od.dept_id='"+
							deptIds+"'");
			sql.append(")");
		}
		if (vo.getStaffSVO().getStaffId() != null) {
		//  sql.append(" and a.staff_id=" + vo.getStaffSVO().getStaffId());
        	sql.append(" and a.staff_id=:staffId");
			sql.setString("staffId", vo.getStaffSVO().getStaffId());
		}
		if (vo.getPartySVO().getName() != null) {
			sql.append(" and b.name like '%" + vo.getPartySVO().getName() + "%'");
		}
		if (vo.getPartySVO().getLocalNetId() != null) {
		//	sql.append(" and b.local_net_id=" + vo.getPartySVO().getLocalNetId());
			sql.append(" and b.local_net_id=:localNetId");
			sql.setString("localNetId", vo.getPartySVO().getLocalNetId());
		}
		if (vo.getPartySVO().getAreaId() != null) {
		//	sql.append(" and b.area_id=" + vo.getPartySVO().getAreaId());
			sql.append(" and b.area_id=:areaId");
			sql.setString("areaId", vo.getPartySVO().getAreaId());
		}
		if (vo.getStaffSVO().getSts() != null) {
		//	sql.append(" and a.sts='" + vo.getStaffSVO().getSts() + "'");
			sql.append(" and a.sts=:sts");
			sql.setString("sts", vo.getStaffSVO().getSts());
		}
		if (vo.getStaffSVO().getCompanyCode() != null) {
		//	sql.append(" and a.company_code ='" + vo.getStaffSVO().getCompanyCode() + "'");
			sql.append(" and a.company_code =:companyCode");
			sql.setString("companyCode", vo.getStaffSVO().getCompanyCode());
		}
		if (vo.getStaffSVO().getDeptType() != null) {
		//	sql.append(" and a.dept_type ='" + vo.getStaffSVO().getDeptType() + "'");
			sql.append(" and a.dept_type =:deptType");
			sql.setString("deptType", vo.getStaffSVO().getDeptType());
		}
		if (vo.getStaffSVO().getTelNbr() != null) {
		//	sql.append(" and a.tel_nbr='" + vo.getStaffSVO().getTelNbr() + "' ");
			sql.append(" and a.tel_nbr=:telNbr");
			sql.setString("telNbr", vo.getStaffSVO().getTelNbr());
		}
		if (vo.getSysUserName() != null) {
			sql.append(" and s.sys_user_name like '%" + vo.getSysUserName() + "%'");
		}
		sql.append(" order by staff_id");
		Connection connection = null;
		PreparedStatement ps = null;
		PagView pagView = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.log(StaffMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
			//ps = connection.prepareStatement(sql.toString());
			//Sql sql2 = new Sql();
			//sql2.setSql(sql.toString());
			//sql2.log(StaffMDAOImpl.class);
			//pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			//pagView.getPagCount();
			//rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql, pagInfo);

			while (rs.next()) {
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));

				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				smo.setDeptName(rs.getString("dept_name"));
				if (rs.getString("sys_user_name") != null) {
					smo.setSysUserName(rs.getString("sys_user_name"));
				}
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				vos.add(smo);
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

	public StaffExtendMVO findStaffExtendMVO(String staffId) throws SysException, AppException {
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						"select a.staff_id,a.party_id,a.dept_id,a.position,a.standard_code,a.sts,a.sts_date,a.create_date,a.dept_type,a.company_code,a.tel_nbr, ")
				.append(
						"b.party_id,b.name party_name,b.sts party_sts,b.party_type,c.local_net_id,c.name local_net_name,c.iscenter local_net_iscenter, ")
				.append(
						"d.area_id,d.name area_name,d.iscenter area_iscenter,e.serv_dept_id,e.name serv_dept_name,")
				.append(
						"f.branch_id,f.name branch_name from staff a,party b,local_net c,area d,serv_dept e,branch f where 1=1 ")
				.append(
						"and a.party_id=b.party_id and b.local_net_id=c.local_net_id and b.area_id=d.area_id and b.serv_dept_id=e.serv_dept_id and b.branch_id=f.branch_id(+) and a.staff_id=?");
		log.debug(sql.toString());
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StaffExtendMVO mvo = new StaffExtendMVO();
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, staffId);
			rs = ps.executeQuery();
			StaffSVO svo = new StaffSVO();
			if (rs.next()) {
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));
				svo.setCompanyCode(rs.getString("company_code"));
				svo.setDeptType(rs.getString("dept_type"));
				svo.setTelNbr(rs.getString("tel_nbr"));
				mvo.setStaffSVO(svo);
				mvo.setPartyId(rs.getString("party_id"));
				mvo.setPartyName(rs.getString("party_name"));
				mvo.setPartySts(rs.getString("party_sts"));
				mvo.setPartyType(rs.getString("party_type"));
				mvo.setLocalNetId(rs.getString("local_net_id"));
				mvo.setLocalNetName(rs.getString("local_net_name"));
				mvo.setLocalNetIscenter(rs.getString("local_net_iscenter"));
				mvo.setAreaId(rs.getString("area_id"));
				mvo.setAreaName(rs.getString("area_name"));
				mvo.setAreaIscenter(rs.getString("area_iscenter"));
				mvo.setServDeptId(rs.getString("serv_dept_id"));
				mvo.setServDeptName(rs.getString("serv_dept_name"));
				mvo.setBranchId(rs.getString("branch_id"));
				mvo.setBranchName(rs.getString("branch_name"));
			}
		} catch (SQLException e) {
			throw new SysException("", "select error..", e);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
		return mvo;

	}

	public List findStaffMember(StaffMVO vo) throws SysException, AppException {
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.dept_type,a.company_code,a.tel_nbr,b.name,b.birthday,b.native_place from staff a,party b ")
				.append("where 1=1 and a.party_id=b.party_id");

		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		if (vo.getStaffSVO().getDeptId() != null) {
			sql.append(" and a.dept_id=?");
		}

		if (vo.getStaffSVO().getStaffId() != null) {
			sql.append(" and a.staff_id=?");
		}
		if (vo.getPartySVO().getName() != null) {
			sql.append(" and b.name like '%"+vo.getPartySVO().getName()+"%'");
		}
		if (vo.getPartySVO().getLocalNetId() != null) {
			sql.append(" and b.local_net_id=?");
		}
		if (vo.getPartySVO().getAreaId() != null) {
			sql.append(" and b.area_id=?");
		}
		if (vo.getPartySVO().getServDeptId() != null) {
			sql.append(" and b.serv_dept_id=?");
		}
		if (vo.getStaffSVO().getSts() != null) {
			sql.append(" and a.sts=?");
		}
		if (vo.getStaffSVO().getCompanyCode() != null) {
			sql.append(" and a.company_code=? ");
		}
		if (vo.getStaffSVO().getDeptType() != null) {
			sql.append(" and a.dept_type=? ");
		}
		if (vo.getStaffSVO().getTelNbr() != null) {
			sql.append(" and a.tel_nbr=? ");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(), vo
					.getPartySVO().getLocalNetId()));
			int index = 1;

			if (vo.getStaffSVO().getDeptId() != null) {
				ps.setString(index++, vo.getStaffSVO().getDeptId());
			}
			if (vo.getStaffSVO().getStaffId() != null) {
				ps.setString(index++, vo.getStaffSVO().getStaffId());
			}
			if (vo.getPartySVO().getName() != null) {
				//ps.setString(index++, vo.getPartySVO().getName());
			}
			if (vo.getPartySVO().getLocalNetId() != null) {
				ps.setString(index++, vo.getPartySVO().getLocalNetId());
			}
			if (vo.getPartySVO().getAreaId() != null) {
				ps.setString(index++, vo.getPartySVO().getAreaId());
			}
			if (vo.getPartySVO().getServDeptId() != null) {
				ps.setString(index++, vo.getPartySVO().getServDeptId());
			}
			if (vo.getStaffSVO().getSts() != null) {
				ps.setString(index++, vo.getStaffSVO().getSts());
			}
			if (vo.getStaffSVO().getCompanyCode() != null) {
				ps.setString(index++, vo.getStaffSVO().getCompanyCode());
			}
			if (vo.getStaffSVO().getDeptType() != null) {
				ps.setString(index++, vo.getStaffSVO().getDeptType());
			}
			if (vo.getStaffSVO().getTelNbr() != null) {
				ps.setString(index++, vo.getStaffSVO().getTelNbr());
			}
			log.debug(ps.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));
				svo.setCompanyCode(rs.getString("company_code"));
				svo.setDeptType(rs.getString("dept_type"));
				svo.setTelNbr(rs.getString("tel_nbr"));
				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}

				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				vos.add(smo);
			}

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
		return vos;
	}

	public PagView findStaffMemberSet(HashSet set, PagInfo pagInfo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name,s.sys_user_name from staff a,party b,org_dept d,sys_user s ")
				.append("where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) and a.staff_id = s.sys_user_id and ( ");

		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		sql.append(ReflectUtil.getLatestHqlWhereInt(set, "a", "staff_id", "staffId"));
		sql.append(") and a.sts='A' and b.sts='A' ");
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
			sql2.log(StaffMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql2, pagInfo);

			while (rs.next()) {
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));
				svo.setTelNbr(rs.getString("tel_nbr"));
				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				if (rs.getString("sys_user_name") != null) {
					smo.setSysUserName(rs.getString("sys_user_name"));
				}
				smo.setDeptName(rs.getString("dept_name"));
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				vos.add(smo);
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

	public List findDevelopMVO(DevelopMVO dmvo) throws SysException, AppException {
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						" select a.STAFF_ID,b.name,b.local_net_id,b.area_id,b.serv_dept_id,b.branch_id from staff a,party b ")
				.append("where 1=1 and a.party_id=b.party_id");

		if (dmvo.getDevelopId() != null) {
			sql.append(" and a.dept_id=? ");
		}
		if (dmvo.getAreaId() != null) {
			sql.append("and b.area_id=? ");
		}
		if (dmvo.getLocalNetId() != null) {
			sql.append("and b.local_net_id=? ");
		}
		if (dmvo.getServDeptId() != null) {
			sql.append("and b.serv_dept_id=? ");
		}
		if (dmvo.getBranchId() != null) {
			sql.append("and b.branch_id=? ");
		}
		sql.append("and a.sts='A' and b.sts='A' ");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(), dmvo
					.getLocalNetId()));
			int index = 1;
			if (dmvo.getDevelopId() != null) {
				ps.setString(index++, dmvo.getDevelopId());
			}
			if (dmvo.getAreaId() != null) {
				ps.setString(index++, dmvo.getAreaId());
			}
			if (dmvo.getLocalNetId() != null) {
				ps.setString(index++, dmvo.getLocalNetId());
			}
			if (dmvo.getServDeptId() != null) {
				ps.setString(index++, dmvo.getServDeptId());
			}
			if (dmvo.getBranchId() != null) {
				ps.setString(index++, dmvo.getBranchId());
			}
			log.debug(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				DevelopMVO mvo = new DevelopMVO();
				mvo.setDevelopId(rs.getString("staff_id"));
				mvo.setName(rs.getString("name"));
				mvo.setLocalNetId(rs.getString("local_net_id"));
				mvo.setAreaId(rs.getString("area_id"));
				mvo.setServDeptId(rs.getString("serv_dept_id"));
				mvo.setBranchId(rs.getString("branch_id"));
				mvo.setPartyRoleType("7");
				vos.add(mvo);
			}

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
		return vos;
	}

	public List findStaffMVOByExchId(WorkAreaExchSVO vo) throws SysException, AppException {
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,b.name,b.birthday,b.native_place from work_area_exch c,staff_work_area d,work_area e,staff a,party b ")
				.append("where 1=1 and c.work_area_id=d.work_area_id ");
		if (vo.getExchId() != null) {
			sql.append("and c.exch_id=? ");
		}
		sql
				.append("and c.sts='A' and c.work_area_id=e.work_area_id and e.work_type_id=1 and d.staff_id=a.staff_id and d.sts='A' and a.party_id=b.party_id and a.sts='A' and b.sts='A' ");
		Connection connection = null;
		PreparedStatement ps = null;
		log.debug(sql.toString());
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			int index = 1;
			if (vo.getExchId() != null) {
				ps.setString(index++, vo.getExchId());
			}
			log.debug(ps.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));

				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}

				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				vos.add(smo);
			}

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
		return vos;
	}
	
	/**
	 * 根据员工id查询员工信息。
	 * 
	 * @param vo
	 *            SysUserVO
	 * @throws SysException
	 * @return SysUserVO
	 */
	public List findStaffMemberByStaffIds(String staffIds) throws AppException, SysException {
		List list = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		StringBuffer sql = new StringBuffer();
		sql
				.append(
						" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.JUR_PERSON,b.NET_TYPE,b.TAX_NBR,b.COMPANY_SIZE,b.START_TIME,b.COMPANY_CHARACTER,b.COMPANY_STRUCTURE,b.REMARKS,b.GANDER,b.BIRTHDAY,b.AGE,b.NATIVE_PLACE,b.NATIONALITY,b.POLITICAL_STATUS,b.EDUC_LEVEL,b.GRADUATE_SCHOOL,b.MARITAL_STATUS,b.DOMESTIC_RELATION,b.MARITAL_DATE,b.COMPANY_NAME,b.DEPARTMENT,b.POSITION,b.WORK_ADDR,b.FUNC_DESC,b.SALARY,b.CHAR_TYPE,b.FAVORITE,b.TELECOM_ATTITUDE,b.WORK_EXPER,b.BRANCH_ID,b.AREA_ID,b.LOCAL_NET_ID,b.SERV_DEPT_ID,b.NAME,b.NAME_BRIEF,b.NAME_OTHER,b.PARTY_TYPE,b.STANDARD_CODE,b.STS P_STS,b.STS_DATE P_STS_DATE,b.CREATE_DATE P_CREATE_DATE,b.COMMUNICATION_SPECIALTY,b.MAIN_PRODUCTION,b.TOTAL_ASSET,b.WEB_ADDRESS,b.LAST_MOD_DATE,c.dept_name,c.dept_type,c.sts dept_sts,c.sts_date dept_sts_date,d.contact_id,d.contact_addr,d.postal_code,")
				.append(
						" e.party_identity_id,e.cert_code,e.cert_type_id,e.cert_exp_date from Staff a,Party b,Org_Dept c,contact_medium d,party_identity e where 1=1 ")
				.append(
						" and a.party_Id = b.party_Id and a.dept_Id=c.dept_Id and b.party_id=d.party_id(+) and b.party_id=e.party_id(+) and a.sts='A' and b.sts='A' and c.sts='A' and d.sts(+)='A' and e.sts(+)='A' ");

		sql.append("and a.staff_id in(" + staffIds + ")");

		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		log.debug(sql.toString());
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StaffMVO smo = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			PartySVO pvo = null;
			StaffSVO svo = null;
			while (rs.next()) {
				pvo = new PartySVO();
				svo = new StaffSVO();
				smo = new StaffMVO();
				smo.setContactId(rs.getString("contact_id"));
				smo.setContactAddr(rs.getString("contact_addr"));
				smo.setPostalCode(rs.getString("postal_code"));
				smo.setPartyIdentityId(rs.getString("party_identity_id"));
				smo.setCertCode(rs.getString("cert_code"));
				smo.setCertTypeId(rs.getString("cert_type_id"));
				smo.setCertExpDate(rs.getDate("cert_exp_date"));
				smo.setDeptName(rs.getString("dept_name"));
				smo.setDeptSts(rs.getString("dept_sts"));
				smo.setDeptType(rs.getString("dept_type"));
				smo.setDeptStsDate(rs.getDate("dept_sts_date"));
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));
				svo.setTelNbr(rs.getString("tel_nbr"));
				if (rs.getString("jur_person") != null) {
					pvo.setJurPerson(rs.getString("jur_person"));
				}
				if (rs.getString("net_type") != null) {
					pvo.setNetType(rs.getString("net_type"));
				}
				if (rs.getString("tax_nbr") != null) {
					pvo.setTaxNbr(rs.getString("tax_nbr"));
				}
				if (rs.getString("company_size") != null) {
					pvo.setCompanySize(rs.getString("company_size"));
				}
				if (rs.getString("company_structure") != null) {
					pvo.setCompanyStructure(rs.getString("company_structure"));
				}
				if (rs.getString("remarks") != null) {
					pvo.setRemarks(rs.getString("remarks"));
				}
				if (rs.getString("gander") != null) {
					pvo.setGander(rs.getString("gander"));
				}
				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("age") != null) {
					pvo.setAge(rs.getString("age"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("nationality") != null) {
					pvo.setNationality(rs.getString("nationality"));
				}
				if (rs.getString("political_status") != null) {
					pvo.setPoliticalStatus(rs.getString("political_status"));
				}
				if (rs.getString("educ_level") != null) {
					pvo.setEducLevel(rs.getString("educ_level"));
				}
				if (rs.getString("graduate_school") != null) {
					pvo.setGraduateSchool(rs.getString("graduate_school"));
				}
				if (rs.getString("marital_status") != null) {
					pvo.setMaritalStatus(rs.getString("marital_status"));
				}
				if (rs.getString("domestic_relation") != null) {
					pvo.setDomesticRelation(rs.getString("domestic_relation"));
				}
				if (rs.getString("marital_date") != null) {
					pvo.setMaritalDate(rs.getDate("marital_date"));
				}
				if (rs.getString("company_name") != null) {
					pvo.setCompanyName(rs.getString("company_name"));
				}
				if (rs.getString("department") != null) {
					pvo.setDepartment(rs.getString("department"));
				}
				if (rs.getString("position") != null) {
					pvo.setPosition(rs.getString("position"));
				}
				if (rs.getString("work_addr") != null) {
					pvo.setWorkAddr(rs.getString("work_addr"));
				}
				if (rs.getString("func_desc") != null) {
					pvo.setFuncDesc(rs.getString("func_desc"));
				}
				if (rs.getString("salary") != null) {
					pvo.setSalary(rs.getString("salary"));
				}
				if (rs.getString("char_type") != null) {
					pvo.setCharType(rs.getString("char_type"));
				}
				if (rs.getString("favorite") != null) {
					pvo.setFavorite(rs.getString("favorite"));
				}
				if (rs.getString("telecom_attitude") != null) {
					pvo.setTelecomAttitude(rs.getString("telecom_attitude"));
				}
				if (rs.getString("work_exper") != null) {
					pvo.setWorkExper(rs.getString("work_exper"));
				}
				if (rs.getString("party_id") != null) {
					pvo.setPartyId(rs.getString("party_id"));
				}
				if (rs.getString("branch_id") != null) {
					pvo.setBranchId(rs.getString("branch_id"));
				}
				if (rs.getString("area_id") != null) {
					pvo.setAreaId(rs.getString("area_id"));
				}
				if (rs.getString("local_net_id") != null) {
					pvo.setLocalNetId(rs.getString("local_net_id"));
				}
				if (rs.getString("serv_dept_id") != null) {
					pvo.setServDeptId(rs.getString("serv_dept_id"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				if (rs.getString("name_brief") != null) {
					pvo.setNameBrief(rs.getString("name_brief"));
				}
				if (rs.getString("name_other") != null) {
					pvo.setNameOther(rs.getString("name_other"));
				}
				if (rs.getString("party_type") != null) {
					pvo.setPartyType(rs.getString("party_type"));
				}
				if (rs.getString("standard_code") != null) {
					pvo.setStandardCode(rs.getString("standard_code"));
				}
				if (rs.getString("P_STS") != null) {
					pvo.setSts(rs.getString("P_STS"));
				}
				if (rs.getString("P_STS_DATE") != null) {
					pvo.setStsDate(rs.getTimestamp("P_STS_DATE"));
				}
				if (rs.getString("P_CREATE_DATE") != null) {
					pvo.setCreateDate(rs.getTimestamp("P_CREATE_DATE"));
				}
				if (rs.getString("communication_specialty") != null) {
					pvo.setCommunicationSpecialty(rs.getString("communication_specialty"));
				}
				if (rs.getString("main_production") != null) {
					pvo.setMainProduction(rs.getString("main_production"));
				}
				if (rs.getString("total_asset") != null) {
					pvo.setTotalAsset(rs.getString("total_asset"));
				}
				if (rs.getString("web_address") != null) {
					pvo.setWebAddress(rs.getString("web_address"));
				}
				if (rs.getString("last_mod_date") != null) {
					pvo.setLastModDate(rs.getTimestamp("last_mod_date"));
				}
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				list.add(smo);
			}

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
		return list.size() == 0 ? null : list;

	}

	// 姓名为精确查询(为了给袁宵提供)
	public List findStaffMVO(StaffMVO vo) throws SysException, AppException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.STAFF_ID,b.name from staff a,party b ").append(
				"where 1=1 and a.party_id=b.party_id");

		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		if (vo.getStaffSVO().getDeptId() != null) {
			sql.append(" and a.dept_id=?");
		}
		if (vo.getStaffSVO().getStaffId() != null) {
			sql.append(" and a.staff_id=?");
		}
		if (vo.getPartySVO().getName() != null) {
			sql.append(" and b.name=? ");
		}
		if (vo.getPartySVO().getLocalNetId() != null) {
			sql.append(" and b.local_net_id=?");
		}
		if (vo.getStaffSVO().getSts() != null) {
			sql.append(" and a.sts=?");
		}
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(PartitionUtil.getPartitionSQL(sql.toString(), vo
					.getPartySVO().getLocalNetId()));
			int index = 1;

			if (vo.getStaffSVO().getDeptId() != null) {
				ps.setString(index++, vo.getStaffSVO().getDeptId());
			}
			if (vo.getStaffSVO().getStaffId() != null) {
				ps.setString(index++, vo.getStaffSVO().getStaffId());
			}
			if (vo.getPartySVO().getName() != null) {
				ps.setString(index++, vo.getPartySVO().getName());
			}
			if (vo.getPartySVO().getLocalNetId() != null) {
				ps.setString(index++, vo.getPartySVO().getLocalNetId());
			}
			if (vo.getStaffSVO().getSts() != null) {
				ps.setString(index++, vo.getStaffSVO().getSts());
			}
			log.debug(ps.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setStaffId(rs.getString("staff_id"));
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				vos.add(smo);
			}

		} catch (SQLException e) {
			throw new SysException("StaffMDAOImpl", "findStaffMVO error..", e);
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

	// 用于同步资源
	public String[] updateStaffMVOByRs(GenericVO vo, String type) throws AppException, SysException {
		StaffMVO mvo = (StaffMVO) vo;
		String[] str = new String[2];
		Connection connection = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {

			connection = ConnectionFactory.getConnection();
			cs = connection.prepareCall("{call CALL_STAFF_SYNC(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, mvo.getStaffSVO().getStaffId());
			cs.setString(2, mvo.getPartySVO().getName());
			cs.setString(3, mvo.getSysUserId());
			cs.setString(4, mvo.getPassWord());
			cs.setString(5, mvo.getStaffSVO().getDeptId());
			if (mvo.getPartySVO().getGander() != null) {
				if (mvo.getPartySVO().getGander().equals("M"))
					cs.setInt(6, 1);
				else
					cs.setInt(6, 2);
			} else
				cs.setInt(6, 0);
			cs.setTimestamp(7, (Timestamp) mvo.getStaffSVO().getCreateDate());

			if (mvo.getStaffSVO().getSts().equals(Constant.STS_IN_USE)) {
				cs.setInt(8, 1);
			} else if (mvo.getStaffSVO().getSts().equals(Constant.STS_HISTORY))
				cs.setInt(8, 2);

			cs.setTimestamp(9, (Timestamp) mvo.getStaffSVO().getStsDate());
			cs.setInt(10, Integer.parseInt(mvo.getPartySVO().getLocalNetId()));
			cs.setInt(11, Integer.parseInt(mvo.getPartySVO().getAreaId()));

			cs.registerOutParameter(12, java.sql.Types.INTEGER);
			cs.registerOutParameter(13, java.sql.Types.VARCHAR);
			cs.setString(14, type);

			cs.execute();
			str[0] = cs.getString(12);
			str[1] = cs.getString(13);

		} catch (SQLException e) {
			throw new SysException("", "updateServAddr error..", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cs != null) {
					cs.close();
				}
			} catch (SQLException e) {
				if (log.isDebugEnabled()) {
					log.debug("close cs err");
				}
				throw new AppException("", "数据库关闭错误");
			}

		}
		return str;
	}

	/**
	 * 根据员工id查询员工姓名。
	 * 
	 * @param vo
	 *            SysUserVO
	 * @throws SysException
	 * @return SysUserVO
	 */
	public String findNameByStaffId(String staffId) throws AppException, SysException {
		StringBuffer sql = new StringBuffer();
		String name = null;
		sql
				.append(" select b.NAME from staff a,party b where a.party_id=b.party_id and a.sts='A' and b.sts='A' ");
		sql.append("and a.staff_id=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, staffId);
			rs = ps.executeQuery();

			if (rs.next()) {
				name = rs.getString("name");
			}

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

		return name;

	}

	/**
	 * 根据staffId与deptId查询
	 */
	public GenericVO findStaffMVO(StaffSVO vo) throws AppException, SysException {
		if (vo == null) {
			throw new AppException("100001", "缺少DAO操作对象！");
		}
		if (StringUtil.isBlank(vo.getStaffId()) && StringUtil.isBlank(vo.getDeptId())) {
			throw new AppException("100002", "缺少staffId与deptId参数！");
		}
		Sql sql = new Sql(" SELECT P.NAME PARTY_NAME, ");
		sql.append(" P.PARTY_ID, ");
		sql.append(" O.DEPT_NAME, ");
		sql.append(" O.DEPT_ID, ");
		sql.append(" S.STAFF_ID ");
		sql.append(" FROM STAFF S, ");
		sql.append(" PARTY P, ");
		sql.append(" ORG_DEPT O ");
		sql.append(" WHERE S.PARTY_ID = P.PARTY_ID ");
		sql.append(" AND S.DEPT_ID = O.DEPT_ID ");
		if (!StringUtil.isBlank(vo.getStaffId())) {
			sql.append(" AND S.STAFF_ID = :staffId ");
			sql.setString("staffId", vo.getStaffId());
		}
		if (!StringUtil.isBlank(vo.getDeptId())) {
			sql.append(" AND S.DEPT_ID = :deptId ");
			sql.setString("deptId", vo.getDeptId());
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StaffMVO staffMVO = null;
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(sql.getSql());
			sql.fillParams(ps);
			sql.log(this.getClass());
			rs = ps.executeQuery();
			while (rs.next()) {
				staffMVO = new StaffMVO();
				staffMVO.getStaffSVO().setStaffId(rs.getString("STAFF_ID"));
				staffMVO.getPartySVO().setPartyId(rs.getString("PARTY_ID"));
				staffMVO.getPartySVO().setName(rs.getString("PARTY_NAME"));
				staffMVO.setDeptName(rs.getString("DEPT_NAME"));
				staffMVO.getStaffSVO().setDeptId(rs.getString("DEPT_ID"));
			}
		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);

		} finally {
			JdbcUtil.close(rs, ps);
		}
		return staffMVO;
	}
	

	public String findStaffIdByPartyId(String partyId) throws AppException, SysException {
		StringBuffer sql = new StringBuffer();
		String staffId = null;
		sql
				.append(" select a.STAFF_ID from staff a,party b where a.party_id=b.party_id and a.sts='A' and b.sts='A' ");
		sql.append("and B.party_id=?");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, partyId);
			rs = ps.executeQuery();

			if (rs.next()) {
				staffId = rs.getString("STAFF_ID");
			}

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
		return staffId;
	}
	
	/**
	 * @method_name addUserService
	 * @author lijixu
	 * @date 2011-2-11 下午07:43:42
	 * @description 增加员工及相关信息
	 * @param paraMap
	 * @throws AppException
	 * @throws SysException 
	 * @reviewed_by
	 */
	public void addUserService(Map paraMap) throws AppException,SysException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PartySVO party = (PartySVO)paraMap.get("partySVO");
		OrgDeptSVO orgDept = (OrgDeptSVO)paraMap.get("orgDeptSVO");
		PartyRoleSVO partyRole = (PartyRoleSVO)paraMap.get("partyRoleSVO");
		StaffSVO staff = (StaffSVO)paraMap.get("staffSVO");
		SysUserSVO sysUser = (SysUserSVO)paraMap.get("sysUserSVO");
		
		Sql partySql = new Sql(" INSERT INTO PARTY(PARTY_ID,BRANCH_ID,AREA_ID,LOCAL_NET_ID,SERV_DEPT_ID,PARTY_TYPE,NAME,NAME_BRIEF,NAME_OTHER,JUR_PERSON,NET_TYPE,TAX_NBR,COMPANY_SIZE,START_TIME,COMPANY_CHARACTER,COMPANY_STRUCTURE,REMARKS,MAIN_PRODUCTION,TOTAL_ASSET,GANDER,BIRTHDAY,AGE,NATIVE_PLACE,NATIONALITY,POLITICAL_STATUS,EDUC_LEVEL,GRADUATE_SCHOOL,MARITAL_STATUS,DOMESTIC_RELATION,MARITAL_DATE,COMPANY_NAME,DEPARTMENT,POSITION,WORK_ADDR,FUNC_DESC,SALARY,CHAR_TYPE,FAVORITE,TELECOM_ATTITUDE,WORK_EXPER,STANDARD_CODE,CREATE_DATE,COMMUNICATION_SPECIALTY,WEB_ADDRESS,LAST_MOD_DATE,STS,STS_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Sql orgDeptSql = new Sql(" INSERT INTO ORG_DEPT(DEPT_ID,DEPT_NAME,AREA_ID,PARENT_DEPT_ID,LOCAL_NET_ID,SERV_DEPT_ID,ADMIN_STAFF_ID,DEPT_TYPE,ROOT_FLAG,STS,STS_DATE,CREATE_DATE,DEPT_DESC,ADDRESS,POST_NBR,BRANCH_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Sql partyRoleSql = new Sql(" INSERT INTO PARTY_ROLE(PARTY_ROLE_TYPE_ID,PARTY_ROLE_ID,PARTY_ID,LOCAL_NET_ID) values(?,?,?,?)");
		Sql staffSql = new Sql(" INSERT INTO STAFF(STAFF_ID,PARTY_ID,DEPT_ID,SIM_SYS_USER_NAME,SIM_PASSWORD,POSITION,STANDARD_CODE,STS,STS_DATE,CREATE_DATE,DEPT_TYPE,COMPANY_CODE,TEL_NBR,local_net_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Sql sysUserSql = new Sql(" INSERT INTO SYS_USER(SYS_USER_ID,PARTY_ROLE_TYPE_ID,PARTY_ROLE_ID,SYS_USER_NAME,PASSWORD,SET_PWD_TIME,UPDATE_PWD_TIME,LAST_PWD,CREATE_DATE,STS,STS_DATE,local_net_id) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        
		try {
			// 参与人 party
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(partySql.getSql());
			if (party.getPartyId() == null) {
	                ps.setString(1, MaxId.getSequenceNextVal(Constant.SEQ_PARTY_ID));
            } else {
                ps.setString(1, party.getPartyId());
	        }
            ps.setString(2, party.getBranchId());
            ps.setString(3, party.getAreaId());
            ps.setString(4, party.getLocalNetId());
            ps.setString(5, party.getServDeptId());
            ps.setString(6, party.getPartyType());
            ps.setString(7, party.getName());
            ps.setString(8, party.getNameBrief());
            ps.setString(9, party.getNameOther());
            ps.setString(10, party.getJurPerson());
            ps.setString(11, party.getNetType());
            ps.setString(12, party.getTaxNbr());
            ps.setString(13, party.getCompanySize());
            ps.setDate(14, party.getStartTime());
            ps.setString(15, party.getCompanyCharacter());
            ps.setString(16, party.getCompanyStructure());
            ps.setString(17, party.getRemarks());
            ps.setString(18, party.getMainProduction());
            ps.setString(19, party.getTotalAsset());
            ps.setString(20, party.getGander());
            ps.setDate(21, party.getBirthday());
            ps.setString(22, party.getAge());
            ps.setString(23, party.getNativePlace());
            ps.setString(24, party.getNationality());
            ps.setString(25, party.getPoliticalStatus());
            ps.setString(26, party.getEducLevel());
            ps.setString(27, party.getGraduateSchool());
            ps.setString(28, party.getMaritalStatus());
            ps.setString(29, party.getDomesticRelation());
            ps.setDate(30, party.getMaritalDate());
            ps.setString(31, party.getCompanyName());
            ps.setString(32, party.getDepartment());
            ps.setString(33, party.getPosition());
            ps.setString(34, party.getWorkAddr());
            ps.setString(35, party.getFuncDesc());
            ps.setString(36, party.getSalary());
            ps.setString(37, party.getCharType());
            ps.setString(38, party.getFavorite());
            ps.setString(39, party.getTelecomAttitude());
            ps.setString(40, party.getWorkExper());
            ps.setString(41, party.getStandardCode());
            ps.setTimestamp(42, party.getCreateDate());
            ps.setString(43, party.getCommunicationSpecialty());
            ps.setString(44, party.getWebAddress());
            ps.setTimestamp(45, party.getLastModDate());
            ps.setString(46, party.getSts());
            ps.setTimestamp(47, party.getStsDate());
			// ps = partySql.fillParams(ps);
			partySql.log(this.getClass());
			ps.execute();
			
			// 机构org_dept
			conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(orgDeptSql.getSql());
            ps.setString(1, orgDept.getDeptId());
            ps.setString(2, orgDept.getDeptName());
            ps.setString(3, orgDept.getAreaId());
            ps.setString(4, orgDept.getParentDeptId());
            ps.setString(5, orgDept.getLocalNetId());
            ps.setString(6, orgDept.getServDeptId());
            ps.setString(7, orgDept.getAdminStaffId());
            ps.setString(8, orgDept.getDeptType());
            ps.setString(9, orgDept.getRootFlag());
            ps.setString(10, orgDept.getSts());
            ps.setDate(11, orgDept.getStsDate());
            ps.setDate(12, orgDept.getCreateDate());
            ps.setString(13, orgDept.getDeptDesc());
            ps.setString(14, orgDept.getAddress());
            ps.setString(15, orgDept.getPostNbr());
            ps.setString(16, orgDept.getBranchId());
            orgDeptSql.log(this.getClass());
            ps.execute();
            
            // 参与人角色party_role
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(partyRoleSql.getSql());
            ps.setString(1, partyRole.getPartyRoleTypeId());
            ps.setString(2, partyRole.getPartyRoleId());
            ps.setString(3, partyRole.getPartyId());
            ps.setString(4, partyRole.getLocalNetId());
            partyRoleSql.log(this.getClass());
            ps.execute();
            
            // 员工staff
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(staffSql.getPartitionSql(null));
            staffSql.setString(1, staff.getStaffId());
            staffSql.setString(2, staff.getPartyId());
            staffSql.setString(3, staff.getDeptId());
            staffSql.setString(4, staff.getSimSysUserName());
            staffSql.setString(5, staff.getSimPassword());
            staffSql.setString(6, staff.getPosition());
            staffSql.setString(7, staff.getStandardCode());
            staffSql.setString(8, staff.getSts());
            staffSql.setTimestamp(9, staff.getStsDate());
            staffSql.setTimestamp(10, staff.getCreateDate());
            staffSql.setString(11, staff.getDeptType());
            staffSql.setString(12, staff.getCompanyCode());
            staffSql.setString(13, staff.getTelNbr());
            staffSql.setString(14, staff.getLocalNetId());
            staffSql.fillParams(ps);
            staffSql.logPartition(null, this.getClass());
            // staffSql.log(this.getClass());
            ps.execute();
            
            // 系统用户sys_user
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sysUserSql.getPartitionSql(null));
            sysUserSql.setString(1, sysUser.getSysUserId());
            sysUserSql.setString(2, sysUser.getPartyRoleTypeId());
            sysUserSql.setString(3, sysUser.getPartyRoleId());
            sysUserSql.setString(4, sysUser.getSysUserName());
            sysUserSql.setString(5, sysUser.getPassword());
            sysUserSql.setTimestamp(6, sysUser.getSetPwdTime());
            sysUserSql.setTimestamp(7, sysUser.getUpdatePwdTime());
            sysUserSql.setString(8, sysUser.getLastPwd());
            sysUserSql.setTimestamp(9, sysUser.getCreateDate());
            sysUserSql.setString(10, sysUser.getSts());
            sysUserSql.setTimestamp(11, sysUser.getStsDate());
            sysUserSql.setString(12, sysUser.getLocalNetId());
            sysUserSql.fillParams(ps);
            sysUserSql.logPartition(null, this.getClass());
            // sysUserSql.log(this.getClass());
            ps.execute();

		} catch (SQLException se) {
			// 增加用户失败，事务回滚
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new SysException("100003", "JDBC操作异常！", se);
		} catch (Exception se) {
			// 增加用户失败，事务回滚
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new SysException("100003", "系统操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		// return null;
	}
	
	/**
	 * @method_name deleteUserService
	 * @author lijixu
	 * @date 2011-2-11 下午07:43:42
	 * @description 删除员工及相关信息
	 * @param paraMap
	 * @throws AppException
	 * @throws SysException 
	 * @reviewed_by
	 */
	public void deleteUserService(String code) throws AppException,SysException {
		if(StringUtil.isBlank(code)){
			throw new AppException("", "用户标识code 为空！" );
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List res = null;
		
		Sql querySql = new Sql(" SELECT SU.SYS_USER_ID,PR.PARTY_ROLE_ID ,PR.PARTY_ID,S.DEPT_ID FROM SYS_USER SU ,PARTY_ROLE PR ,STAFF S WHERE 1=1 AND SU.PARTY_ROLE_ID = PR.PARTY_ROLE_ID AND S.STAFF_ID = SU.PARTY_ROLE_ID AND SU.SYS_USER_NAME =:sysUserName ");
		
		Sql partySql = new Sql(" UPDATE PARTY P SET P.STS ='P' WHERE P.STS = 'A' AND P.PARTY_ID =:parytId ");
		Sql orgDeptSql = new Sql(" UPDATE ORG_DEPT OD SET OD.STS ='P' WHERE OD.STS = 'A' AND OD.DEPT_ID =:deptId ");
		Sql partyRoleSql = new Sql(" UPDATE PARTY_ROLE PR SET PR.STS ='P' WHERE  PR.PARTY_ROLE_ID =:partyRoleId ");
		Sql staffSql = new Sql(" UPDATE STAFF S SET S.STS ='P' WHERE S.STS = 'A' AND S.STAFF_ID =:staffId");
		Sql sysUserSql = new Sql(" UPDATE SYS_USER SU SET SU.STS ='P' WHERE SU.STS = 'A' AND SU.SYS_USER_ID =:sysUserId ");
        
		try {
		 
		    // 查询
//			conn = ConnectionFactory.getConnection();
//          ps = conn.prepareStatement(PartitionUtil.getPartitionSQL(querySql.toString(), ""));
//          int index = 1;
//          ps.setString(index++, code);
//          rs = ps.executeQuery();
			querySql.setString("sysUserName", code);
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(querySql.getSql());
			ps = querySql.fillParams(ps);
			querySql.log(this.getClass());
			rs = ps.executeQuery();
			
			StaffMVO staffMVO = null;
			while (rs.next()) {
				staffMVO = new StaffMVO();
				staffMVO.setSysUserId(rs.getString("SYS_USER_ID"));
				staffMVO.getPartySVO().setPartyId(rs.getString("PARTY_ID"));
				staffMVO.getStaffSVO().setStaffId(rs.getString("PARTY_ROLE_ID"));
				staffMVO.getStaffSVO().setDeptId(rs.getString("DEPT_ID"));
				break;
			}
			if(staffMVO==null){
				throw new AppException("", "删除用户:staffMVO为空，没有获得有效的数据(sysUserId,partyId,partyRoleId,staffId,deptId...)信息" );
			}
			
			// 参与人 party
			partySql.setString("parytId", staffMVO.getPartySVO().getPartyId());
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(partySql.getSql());
			ps = partySql.fillParams(ps);
			partySql.log(this.getClass());
			ps.executeUpdate();
			
			// 机构org_dept
			orgDeptSql.setString("deptId", staffMVO.getStaffSVO().getDeptId());
			conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(orgDeptSql.getSql());
            ps = orgDeptSql.fillParams(ps);
            orgDeptSql.log(this.getClass());
			ps.executeUpdate();
            
            // 参与人角色party_role
			// 先对party_role不作处理，如有需要，后续再改
//			partyRoleSql.setString("partyRoleId", staffMVO.getStaffSVO().getStaffId());
//          conn = ConnectionFactory.getConnection();
//          ps = conn.prepareStatement(partyRoleSql.getSql());
//          ps = partyRoleSql.fillParams(ps);
//          partyRoleSql.log(this.getClass());
//			ps.executeUpdate();
            
            // 员工staff
			staffSql.setString("staffId", staffMVO.getStaffSVO().getStaffId());
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(staffSql.getPartitionSql(null));
            ps = staffSql.fillParams(ps);
            staffSql.log(this.getClass());
			ps.executeUpdate();
            
            // 系统用户sys_user
			sysUserSql.setString("sysUserId", staffMVO.getSysUserId());
            conn = ConnectionFactory.getConnection();
            ps = conn.prepareStatement(sysUserSql.getPartitionSql(null));
            ps = sysUserSql.fillParams(ps);
            sysUserSql.log(this.getClass());
			ps.executeUpdate();

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		// return null;
	}
	
	/**
	 * @method_name updateUserService
	 * @author lijixu
	 * @date 2011-2-11 下午09:14:31
	 * @description 修改员工及相关信息
	 * @param paraMap
	 * @throws AppException
	 * @throws SysException 
	 * @reviewed_by
	 */
	public void updateUserService(Map paraMap) throws AppException,SysException {
		String code = (String)paraMap.get("code");
		String name = (String)paraMap.get("name");
		String password = (String)paraMap.get("password");
		String email = (String)paraMap.get("email");
		String mobile = (String)paraMap.get("mobile");
		String organcode = (String)paraMap.get("organcode");
		
		if(StringUtil.isBlank(code)){
			throw new AppException("", "修改用户信息：用户标识code 为空！" );
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Sql querySql = new Sql(" SELECT SU.SYS_USER_ID,PR.PARTY_ROLE_ID ,PR.PARTY_ID,S.DEPT_ID FROM SYS_USER SU ,PARTY_ROLE PR ,STAFF S WHERE 1=1 AND SU.PARTY_ROLE_ID = PR.PARTY_ROLE_ID AND S.STAFF_ID = SU.PARTY_ROLE_ID AND SU.SYS_USER_NAME =:sysUserName ");
		// 根据主键查询
//		Sql partyQuerySql = new Sql(" SELECT * FROM PARTY P WHERE P.PARTY_ID =:parytId AND P.STS ='A' ");
//		Sql deptQuerySql = new Sql(" SELECT * FROM ORG_DEPT OD WHERE OD.DEPT_ID =:deptId AND OD.STS ='A' ");
//		Sql prQuerySql = new Sql(" SELECT * FROM  PARTY_ROLE PR WHERE PR.PARTY_ROLE_ID =:partyRoleId AND PR.STS ='A' ");
//		Sql staffQuerySql = new Sql(" SELECT * FROM  STAFF S WHERE S.STAFF_ID =:staffId AND S.STS ='A' ");
//		Sql suQuerySql = new Sql(" SELECT * FROM  SYS_USER SU WHERE SU.SYS_USER_ID =:sysUserId AND SU.STS ='A' ");
		// 更新
		Sql partyUpdSql = new Sql(" UPDATE PARTY P SET "); // 网址 -email
		Sql deptUpdSql = new Sql(" UPDATE ORG_DEPT OD SET ");
		Sql prUpdSql = new Sql(" UPDATE PARTY_ROLE PR SET ");
		Sql staffUpdSql = new Sql(" UPDATE STAFF S SET ");
		Sql suUpdSql = new Sql(" UPDATE SYS_USER SU SET ");
        
		try {
		 
		    // 查询
			querySql.setString("sysUserName", code);
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(querySql.getSql());
			ps = querySql.fillParams(ps);
			querySql.log(this.getClass());
			rs = ps.executeQuery();
			
			StaffMVO staffMVO = null;
			while (rs.next()) {
				staffMVO = new StaffMVO();
				staffMVO.setSysUserId(rs.getString("SYS_USER_ID"));
				staffMVO.getPartySVO().setPartyId(rs.getString("PARTY_ID"));
				staffMVO.getStaffSVO().setStaffId(rs.getString("PARTY_ROLE_ID"));
				staffMVO.getStaffSVO().setDeptId(rs.getString("DEPT_ID"));
				break;
			}
			if(staffMVO==null){
				throw new AppException("", "修改用户信息:staffMVO为空，没有获得有效的数据(sysUserId,partyId,partyRoleId,staffId,deptId...)信息" );
			}
			
			boolean partyFlag = false ;
			boolean orgDeptFlag = false ;
			boolean staffFlag = false ;
			boolean sysUserFlag = false ;
			
			// 1、参与人 party
			if(!StringUtil.isBlank(name)){
				partyFlag =true ;
				partyUpdSql.append(" P.NAME=:name ");
				partyUpdSql.setString("name", name);
			}
			if(!StringUtil.isBlank(email)){
				if(partyFlag){
					partyUpdSql.append(" , ");
				}else{
					partyFlag =true ;
				}
				partyUpdSql.append(" P.WEB_ADDRESS =:webAddr ");
				partyUpdSql.setString("webAddr", email);
			}
			if(partyFlag){
				partyUpdSql.append(" WHERE P.PARTY_ID =:partyId AND P.STS = 'A' ");
				partyUpdSql.setString("partyId", staffMVO.getPartySVO().getPartyId());
				conn = ConnectionFactory.getConnection();
				ps = conn.prepareStatement(partyUpdSql.getSql());
				log.debug("-------partyUpdSql.getSql()---------"+partyUpdSql.getSql());
				ps = partyUpdSql.fillParams(ps);
				partyUpdSql.log(this.getClass());
				ps.executeUpdate();
			}
			
			// 2、机构org_dept
			if(!StringUtil.isBlank(organcode)){
				orgDeptFlag =true ;
				deptUpdSql.append(" OD.DEPT_NAME=:deptName ,OD.DEPT_ID=:newDeptId WHERE OD.DEPT_ID=:oldDeptId AND OD.STS = 'A' ");
				deptUpdSql.setString("deptName", staffMVO.getPartySVO().getName());
				staffUpdSql.append(" S.DEPT_ID=:staffDeptId ");
			}
			if(orgDeptFlag){
				deptUpdSql.setString("newDeptId", organcode);
				deptUpdSql.setString("oldDeptId", staffMVO.getStaffSVO().getDeptId());
				// staffUpdSql.setString("staffDeptId", organcode);
				conn = ConnectionFactory.getConnection();
				ps = conn.prepareStatement(deptUpdSql.getSql());
				ps = deptUpdSql.fillParams(ps);
				deptUpdSql.log(this.getClass());
				ps.executeUpdate();
			}
            
            // 3、参与人角色party_role
//			partyRoleSql.setString("partyRoleId", staffMVO.getStaffSVO().getStaffId());
//            conn = ConnectionFactory.getConnection();
//            ps = conn.prepareStatement(partyRoleSql.getSql());
//            ps = partyRoleSql.fillParams(ps);
//            partyRoleSql.log(this.getClass());
//			ps.executeUpdate();
            
            // 4、员工staff
			if(!StringUtil.isBlank(mobile)){
				if(orgDeptFlag){
					staffUpdSql.setString("staffDeptId", organcode);
					staffUpdSql.append(" , ");
				}
				staffFlag =true ;
				staffUpdSql.append(" S.TEL_NBR=:telNbr ");
				staffUpdSql.setString("telNbr", mobile);
			}
			
			if(staffFlag||orgDeptFlag){
				staffUpdSql.append(" WHERE S.STAFF_ID=:staffId AND S.STS = 'A' ");
				staffUpdSql.setString("staffId", staffMVO.getStaffSVO().getStaffId());
				conn = ConnectionFactory.getConnection();
				ps = conn.prepareStatement(staffUpdSql.getPartitionSql(null));
				ps = staffUpdSql.fillParams(ps);
				staffUpdSql.log(this.getClass());
				ps.executeUpdate();
			}
			
            
            // 5、系统用户sys_user
			if(!StringUtil.isBlank(password)){
				sysUserFlag =true ;
				suUpdSql.append(" SU.SYS_USER_NAME=:sysUserName ,SU.PASSWORD=:pwd ");
				suUpdSql.setString("sysUserName", code);
				suUpdSql.setString("pwd", password);
			}
			if(sysUserFlag){
				suUpdSql.append(" WHERE SU.SYS_USER_ID=:sysUserId and SU.STS = 'A' ");
				suUpdSql.setString("sysUserId", staffMVO.getSysUserId());
				conn = ConnectionFactory.getConnection();
				ps = conn.prepareStatement(suUpdSql.getPartitionSql(null));
				ps = suUpdSql.fillParams(ps);
				suUpdSql.log(this.getClass());
				ps.executeUpdate();
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		// return null;
	}
	
	/**
	 * @method_name findAllUserService
	 * @author lijixu
	 * @date 2011-2-12 上午10:43:17
	 * @description 查询所有用户
	 * @param code
	 * @throws AppException
	 * @throws SysException 
	 * @reviewed_by
	 */
	public List findAllUserService() throws AppException,SysException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List res = new ArrayList();
		
		Sql querySql = new Sql(" SELECT SU.SYS_USER_NAME CODE,P.NAME, SU.PASSWORD PWD , P.WEB_ADDRESS EMAIL,S.TEL_NBR MOBILE FROM SYS_USER SU, PARTY_ROLE PR, STAFF S,PARTY P WHERE 1 = 1 AND SU.PARTY_ROLE_ID = PR.PARTY_ROLE_ID AND S.STAFF_ID = SU.PARTY_ROLE_ID AND S.PARTY_ID = P.PARTY_ID  AND PR.PARTY_ID = P.PARTY_ID   AND SU.STS='A' AND S.STS='A' AND P.STS='A' ");
		try {
		 
		    // 查询
//			conn = ConnectionFactory.getConnection();
//          ps = conn.prepareStatement(PartitionUtil.getPartitionSQL(querySql.toString(), ""));
//          int index = 1;
//          ps.setString(index++, code);
//          rs = ps.executeQuery();
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(querySql.getSql());
			ps = querySql.fillParams(ps);
			querySql.log(this.getClass());
			rs = ps.executeQuery();
			
			StaffMVO staffMVO = null;
			while (rs.next()) {
				staffMVO = new StaffMVO();
				staffMVO.setSysUserName(rs.getString("CODE"));
				staffMVO.getPartySVO().setName(rs.getString("NAME"));
				staffMVO.setPassWord(rs.getString("PWD"));
				staffMVO.getPartySVO().setWebAddress(rs.getString("EMAIL"));
				staffMVO.getStaffSVO().setTelNbr(rs.getString("MOBILE"));
				res.add(staffMVO);
			}

		} catch (SQLException se) {
			throw new SysException("100003", "JDBC操作异常！", se);
		} finally {
			JdbcUtil.close(rs, ps);
		}

		 return res;
	}

	/**
	 * 更新用户手机终端配置
	 */
	public void updateTerminalConfig(String updateValue) throws AppException,
			SysException {
		if(StringUtil.isBlank(updateValue)){
			throw new AppException("", "StaffMDAOImpl:updateTerminalConfig方法时传入的值为空！" );
		}
		//对所得到的字符串进行解析处理，并将所需要的结果进行判断处理
		int lastChar = updateValue.lastIndexOf(',');
		String staffId = updateValue.substring(0, lastChar);
		String updateFlag = updateValue.substring(lastChar+1);
		
		String terminalUpdateSql = "";
		Connection conn = null;
		PreparedStatement ps = null;
		
		if(updateFlag.equals("Y")) {
			terminalUpdateSql = "UPDATE STAFF SET TERMINAL_FLAG='Y' WHERE STAFF_ID IN("+staffId+")";
		} else if(updateFlag.equals("N")) {
			terminalUpdateSql = "UPDATE STAFF SET TERMINAL_FLAG='N' WHERE STAFF_ID IN("+staffId+")";
		}
		Sql updateSql = new Sql(terminalUpdateSql);
		try {
			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(updateSql.getSql());
			ps = updateSql.fillParams(ps);
			updateSql.log(this.getClass());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public PagView findStaffMemberPer(StaffMVO vo, PagInfo pagInfo, List oList,
			String deptIds) throws SysException, AppException {
		//StringBuffer sql = new StringBuffer();
		//sql
		//		.append(
		//				" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name from staff a,party b,org_dept d ")
		//		.append("where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) ");
		
		Sql sql = new Sql("");
		sql.append(
						" select pko.performance_check_obj_id,pko.check_object_id,a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name,s.sys_user_name from staff a,party b,org_dept d, sys_user s");
		sql.append(",(select performance_check_obj_id,check_object_id from performance_check_obj where performance_id = :performanceId and sts='A') pko ");
		sql.setString("performanceId", vo.getPerformanceId());
		sql.append("where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) and a.staff_id = s.sys_user_id(+)");
		
        
		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		// if (oList != null && oList.size() > 0) {
		// sql.append(" and a.dept_id in (");
		// for (int i = 0; i < oList.size(); i++) {
		// if (i == 0) {
		// sql.append((String) oList.get(0));
		// } else
		// sql.append("," + (String) oList.get(i));
		// }
		// sql.append(")");
		// }
		if (deptIds != null) {
			sql.append(" and a.dept_id in (" );
			sql.append("select od.dept_id from org_dept od connect by nocycle prior od.dept_id = od.parent_dept_id start with od.dept_id='"+
							deptIds+"'");
			sql.append(")");
		}
		if (vo.getStaffSVO().getStaffId() != null) {
		//  sql.append(" and a.staff_id=" + vo.getStaffSVO().getStaffId());
        	sql.append(" and a.staff_id=:staffId");
			sql.setString("staffId", vo.getStaffSVO().getStaffId());
		}
		if (vo.getPartySVO().getName() != null) {
			sql.append(" and b.name like '%" + vo.getPartySVO().getName() + "%'");
		}
		if (vo.getPartySVO().getLocalNetId() != null) {
		//	sql.append(" and b.local_net_id=" + vo.getPartySVO().getLocalNetId());
			sql.append(" and b.local_net_id=:localNetId");
			sql.setString("localNetId", vo.getPartySVO().getLocalNetId());
		}
		if (vo.getPartySVO().getAreaId() != null) {
		//	sql.append(" and b.area_id=" + vo.getPartySVO().getAreaId());
			sql.append(" and b.area_id=:areaId");
			sql.setString("areaId", vo.getPartySVO().getAreaId());
		}
		if (vo.getStaffSVO().getSts() != null) {
		//	sql.append(" and a.sts='" + vo.getStaffSVO().getSts() + "'");
			sql.append(" and a.sts=:sts");
			sql.setString("sts", vo.getStaffSVO().getSts());
		}
		if (vo.getStaffSVO().getCompanyCode() != null) {
		//	sql.append(" and a.company_code ='" + vo.getStaffSVO().getCompanyCode() + "'");
			sql.append(" and a.company_code =:companyCode");
			sql.setString("companyCode", vo.getStaffSVO().getCompanyCode());
		}
		if (vo.getStaffSVO().getDeptType() != null) {
		//	sql.append(" and a.dept_type ='" + vo.getStaffSVO().getDeptType() + "'");
			sql.append(" and a.dept_type =:deptType");
			sql.setString("deptType", vo.getStaffSVO().getDeptType());
		}
		if (vo.getStaffSVO().getTelNbr() != null) {
		//	sql.append(" and a.tel_nbr='" + vo.getStaffSVO().getTelNbr() + "' ");
			sql.append(" and a.tel_nbr=:telNbr");
			sql.setString("telNbr", vo.getStaffSVO().getTelNbr());
		}
		if (vo.getSysUserName() != null) {
			sql.append(" and s.sys_user_name like '%" + vo.getSysUserName() + "%'");
		}
		sql.append(" and pko.check_object_id(+) = a.staff_id");
		sql.append(" order by staff_id");
		Connection connection = null;
		PreparedStatement ps = null;
		PagView pagView = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.log(StaffMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
			//ps = connection.prepareStatement(sql.toString());
			//Sql sql2 = new Sql();
			//sql2.setSql(sql.toString());
			//sql2.log(StaffMDAOImpl.class);
			//pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			//pagView.getPagCount();
			//rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql, pagInfo);

			while (rs.next()) {
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));

				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				smo.setDeptName(rs.getString("dept_name"));
				if (rs.getString("sys_user_name") != null) {
					smo.setSysUserName(rs.getString("sys_user_name"));
				}
				if(!StringUtil.isBlank(rs.getString("check_object_id"))){
					smo.setIsCheckObj(SysConstants.TRUE);
				}else{
					smo.setIsCheckObj(SysConstants.FALSE);
				}
				smo.setCheckObjectId(rs.getString("check_object_id"));
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				smo.setPerformanceCheckObjId(rs.getString("performance_check_obj_id"));
				vos.add(smo);
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
	
	/**
	 * 选取考核评分人时，列出员工列表
	 * @param vo
	 * @param pagInfo
	 * @param oList
	 * @param deptIds
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public PagView findStaffMemberForCheker(StaffMVO vo, PagInfo pagInfo, List oList,
			String deptIds) throws SysException, AppException {
		//StringBuffer sql = new StringBuffer();
		//sql
		//		.append(
		//				" select a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name from staff a,party b,org_dept d ")
		//		.append("where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) ");
		
		Sql sql = new Sql("");
		sql.append(
						" select ck.checker_id,ck.check_staff_id,ck.power, a.STAFF_ID,a.DEPT_ID,a.POSITION,a.STANDARD_CODE,a.STS,a.STS_DATE,a.CREATE_DATE,a.PARTY_ID,a.tel_nbr,b.name,b.birthday,b.native_place,d.dept_name,s.sys_user_name from staff a,party b,org_dept d, sys_user s");
		sql.append(", (select check_staff_id,power ,checker_id from checker where PERFORMANCE_INDEX_ID = :performanceIndexId AND PERFORMANCE_CHECK_OBJ_ID = :performanceCheckObjId AND STS = 'A') ck");
		sql.setString("performanceIndexId", vo.getPerformanceIndexId());
		sql.setString("performanceCheckObjId", vo.getPerformanceCheckObjId());
		sql.append(" where 1=1 and a.party_id=b.party_id and a.dept_id=d.dept_id(+) and a.staff_id = s.sys_user_id(+)");
		
        
		// 增加服务区限制。
		/*
		 * if (vo.getParty() != null && vo.getParty().getAreaId() != null) { SMRegionDOM dom = new
		 * SMRegionDOM(); if (!dom.isCenterArea(vo.getParty().getAreaId())) hql = hql + "(b.areaId=" +
		 * vo.getParty().getAreaId() + " or b.areaId=0) and "; }
		 */
		// if (oList != null && oList.size() > 0) {
		// sql.append(" and a.dept_id in (");
		// for (int i = 0; i < oList.size(); i++) {
		// if (i == 0) {
		// sql.append((String) oList.get(0));
		// } else
		// sql.append("," + (String) oList.get(i));
		// }
		// sql.append(")");
		// }
		if (deptIds != null) {
			sql.append(" and a.dept_id in (" );
			sql.append("select od.dept_id from org_dept od connect by nocycle prior od.dept_id = od.parent_dept_id start with od.dept_id='"+
							deptIds+"'");
			sql.append(")");
		}
		if (vo.getStaffSVO().getStaffId() != null) {
		//  sql.append(" and a.staff_id=" + vo.getStaffSVO().getStaffId());
        	sql.append(" and a.staff_id=:staffId");
			sql.setString("staffId", vo.getStaffSVO().getStaffId());
		}
		if (vo.getPartySVO().getName() != null) {
			sql.append(" and b.name like '%" + vo.getPartySVO().getName() + "%'");
		}
		if (vo.getPartySVO().getLocalNetId() != null) {
		//	sql.append(" and b.local_net_id=" + vo.getPartySVO().getLocalNetId());
			sql.append(" and b.local_net_id=:localNetId");
			sql.setString("localNetId", vo.getPartySVO().getLocalNetId());
		}
		if (vo.getPartySVO().getAreaId() != null) {
		//	sql.append(" and b.area_id=" + vo.getPartySVO().getAreaId());
			sql.append(" and b.area_id=:areaId");
			sql.setString("areaId", vo.getPartySVO().getAreaId());
		}
		if (vo.getStaffSVO().getSts() != null) {
		//	sql.append(" and a.sts='" + vo.getStaffSVO().getSts() + "'");
			sql.append(" and a.sts=:sts");
			sql.setString("sts", vo.getStaffSVO().getSts());
		}
		if (vo.getStaffSVO().getCompanyCode() != null) {
		//	sql.append(" and a.company_code ='" + vo.getStaffSVO().getCompanyCode() + "'");
			sql.append(" and a.company_code =:companyCode");
			sql.setString("companyCode", vo.getStaffSVO().getCompanyCode());
		}
		if (vo.getStaffSVO().getDeptType() != null) {
		//	sql.append(" and a.dept_type ='" + vo.getStaffSVO().getDeptType() + "'");
			sql.append(" and a.dept_type =:deptType");
			sql.setString("deptType", vo.getStaffSVO().getDeptType());
		}
		if (vo.getStaffSVO().getTelNbr() != null) {
		//	sql.append(" and a.tel_nbr='" + vo.getStaffSVO().getTelNbr() + "' ");
			sql.append(" and a.tel_nbr=:telNbr");
			sql.setString("telNbr", vo.getStaffSVO().getTelNbr());
		}
		if (vo.getSysUserName() != null) {
			sql.append(" and s.sys_user_name like '%" + vo.getSysUserName() + "%'");
		}
		//判断前台获取的列表，是考核人，还是非考核人 fs 2013-08-05
		if(SysConstants.TRUE.equals(vo.getIsCheckStaff())){
			sql.append(" and ck.check_staff_id=a.staff_id");
		}else{
			sql.append(" and ck.check_staff_id(+)=a.staff_id");
		}
		sql.append(" order by staff_id");
		Connection connection = null;
		PreparedStatement ps = null;
		PagView pagView = null;
		ResultSet rs = null;
		List vos = (List)CollectionFactory.createCollection(CollectionFactory.COLLECTION_LIST);
		try {
			connection = ConnectionFactory.getConnection();
			ps = connection.prepareStatement(sql.getSql());
			sql.log(StaffMDAOImpl.class);
			pagView = PagUtil.InitPagViewJDBC(connection, sql, pagInfo);
			//ps = connection.prepareStatement(sql.toString());
			//Sql sql2 = new Sql();
			//sql2.setSql(sql.toString());
			//sql2.log(StaffMDAOImpl.class);
			//pagView = PagUtil.InitPagViewJDBC(connection, sql2, pagInfo);
			//pagView.getPagCount();
			//rs = PagUtil.queryOracle(connection, sql2, pagInfo);
			pagView.getPagCount();
			rs = PagUtil.queryOracle(connection, sql, pagInfo);

			while (rs.next()) {
				
				if(!SysConstants.TRUE.equals(vo.getIsCheckStaff())){
					if(!StringUtil.isBlank(rs.getString("check_staff_id"))){
						continue;
					}
				}
				
				StaffMVO smo = new StaffMVO();
				PartySVO pvo = new PartySVO();
				StaffSVO svo = new StaffSVO();
				svo.setCreateDate(rs.getTimestamp("create_date"));
				svo.setDeptId(rs.getString("dept_id"));
				svo.setPartyId(rs.getString("party_id"));
				svo.setPosition(rs.getString("position"));
				svo.setStaffId(rs.getString("staff_id"));
				svo.setStandardCode(rs.getString("standard_code"));
				svo.setSts(rs.getString("sts"));
				svo.setStsDate(rs.getTimestamp("sts_date"));

				if (rs.getString("birthday") != null) {
					pvo.setBirthday(rs.getDate("birthday"));
				}
				if (rs.getString("native_place") != null) {
					pvo.setNativePlace(rs.getString("native_place"));
				}
				if (rs.getString("name") != null) {
					pvo.setName(rs.getString("name"));
				}
				smo.setDeptName(rs.getString("dept_name"));
				if (rs.getString("sys_user_name") != null) {
					smo.setSysUserName(rs.getString("sys_user_name"));
				}
				smo.setPartySVO(pvo);
				smo.setStaffSVO(svo);
				smo.setCheckStaffId(rs.getString("check_staff_id"));
				smo.setPower(rs.getString("POWER"));
				smo.setPerformanceIndexId(vo.getPerformanceIndexId());
				smo.setCheckerId(rs.getString("CHECKER_ID"));
				vos.add(smo);
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

	public List findStaffList(GenericVO vo) throws SysException, AppException {
		// TODO Auto-generated method stub
		return null;
	}

	public List findLatestStaffList(GenericVO vo) throws SysException,
			AppException {
		// TODO Auto-generated method stub
		return null;
	}

	public List findLastStaffList(GenericVO vo) throws SysException,
			AppException {
		// TODO Auto-generated method stub
		return null;
	}

	public PagView findStaffListByStaffName(GenericVO vo, PagInfo pagInfo)
			throws SysException, AppException {
		// TODO Auto-generated method stub
		return null;
	}

	public PagView findLatestStaffListByStaffName(GenericVO vo, PagInfo pagInfo)
			throws SysException, AppException {
		// TODO Auto-generated method stub
		return null;
	}
}
