package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.PasswordUtil;
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.sm.component.dao.IOrgDeptSDAO;
import com.cattsoft.sm.component.dao.IStaffMDAO;
import com.cattsoft.sm.component.dao.IStaffSDAO;
import com.cattsoft.sm.component.dao.ISysConfigSDAO;
import com.cattsoft.sm.component.dao.ISysUserSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.ContactMediumSVO;
import com.cattsoft.sm.vo.LocalNetSVO;
import com.cattsoft.sm.vo.OrgDeptSVO;
import com.cattsoft.sm.vo.PartyIdentitySVO;
import com.cattsoft.sm.vo.PartyRoleSVO;
import com.cattsoft.sm.vo.PartySVO;
import com.cattsoft.sm.vo.StaffMVO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.sm.vo.SysConfigSVO;
import com.cattsoft.sm.vo.SysUserSVO;
import com.cattsoft.webpub.util.SysDateUtil;
import com.linkage.component.bean.adm.Encryptor;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-7 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StaffDOM {

	private IStaffSDAO staffDao = (IStaffSDAO) DAOFactory.getInstance().getDAO(
			IStaffSDAO.class);

	private IStaffMDAO staffMDao = (IStaffMDAO) DAOFactory.getInstance()
			.getDAO(IStaffMDAO.class);

	private IOrgDeptSDAO orgDeptDao = (IOrgDeptSDAO) DAOFactory.getInstance()
			.getDAO(IOrgDeptSDAO.class);

	private ISysUserSDAO sysUserDao = (ISysUserSDAO) DAOFactory.getInstance()
			.getDAO(ISysUserSDAO.class);

	private ISysConfigSDAO sysConfigDao = (ISysConfigSDAO) DAOFactory
			.getInstance().getDAO(ISysConfigSDAO.class);

	private static Logger log = Logger.getLogger(StaffDOM.class);

	/**
	 * 根据员工得到员工对应工区所属本地网信息
	 * 
	 * @param info
	 * @return List
	 * @throws SysException
	 * @throws AppException
	 */
	public List getStaffLocalNetVOs(Long staffId, String systemName)
			throws AppException, SysException {
		List staff = staffMDao.getStaffLocalNetVOs(staffId, systemName);
		if (staff == null) {
			return null;
		}
		return staff;
	}

	/**
	 * 根据员工得到员工对应工区所属本地网信息
	 * 
	 * @param info
	 * @return List
	 * @throws SysException
	 * @throws AppException
	 */
	public List getStaffLocalNetVOs4Mobile(Map paraMap) throws AppException,
			SysException {
		String staffId = (String) paraMap.get(SysConstants.PARA_MAP_STAFF_ID);
		String systemName = (String) paraMap
				.get(SysConstants.PARA_MAP_SYSTEM_NAME);
		List staff = staffMDao.getStaffLocalNetVOs(staffId, systemName);
		if (staff == null) {
			return null;
		}
		return staff;
	}

	public String getStaffLocalNetVOs4MOS(String json) throws AppException,
			SysException {

		JSONObject jsonObject = JSONObject.fromObject(json);

		String systemName = (String) jsonObject.get("systemName");
		String staffId = (String) jsonObject.get("staffId");
		String LocalNetId = (String) jsonObject.get("localNetId");
		List staffLns = staffMDao.getStaffLocalNetVOs(staffId, systemName);
		List staffLnsToJson = new ArrayList();
		for (int i = 0; i < staffLns.size(); i++) {
			LocalNetSVO localNetSVO = (LocalNetSVO) staffLns.get(i);

			java.util.Date stsDate = new java.util.Date(localNetSVO
					.getStsDate().getTime());
			java.util.Date createDate = new java.util.Date(localNetSVO
					.getCreateDate().getTime());
			localNetSVO.setStsDate(stsDate);
			localNetSVO.setCreateDate(createDate);
			staffLnsToJson.add(localNetSVO);
			System.out.print("=======" + localNetSVO.getStsDate());

		}
		JSONArray jsonArray1 = JSONArray.fromObject(staffLnsToJson);
		if (jsonArray1 == null) {
			return null;
		}
		return jsonArray1.toString();
	}

	/**
	 * 根据员工得到员工对应工区所属本地网信息
	 * 
	 * @param info
	 * @return List
	 * @throws SysException
	 * @throws AppException
	 */
	public List getStaffLocalNetVOs(String staffId, String systemName)
			throws AppException, SysException {
		List staff = staffMDao.getStaffLocalNetVOs(staffId, systemName);
		if (staff == null) {
			return null;
		}
		return staff;
	}

	/**
	 * 分页查询员工(根据部门查询,查询部门及其它的下属部门符合条件的员工信息)
	 * 
	 * @param ovo
	 * 
	 * @param pagInfo
	 * @throws SysException
	 * @throws AppException
	 * @return pagview
	 */
	public PagView findStaff(StaffMVO ovo, PagInfo pagInfo)
			throws SysException, AppException {
		List oList = null;
		String dpstr = null;
		if (ovo.getPartyRoleType().equals(Constant.PARTY_ROLE_TYPE_ORG_DEPT)) {

			// OrgDeptDOM odom = new OrgDeptDOM();
			// dpstr=odom.findIdByPro(ovo.getStaffSVO().getDeptId());
			dpstr = ovo.getStaffSVO().getDeptId();
			// oList = odom.findSubDeptByMap(ovo.getStaffSVO().getDeptId());
		}
		return staffMDao.findStaffMemberFast(ovo, pagInfo, oList, dpstr);

	}

	/**
	 * 根据考核目标，获取考核目标对象
	 * 
	 * @param ovo
	 * @param pagInfo
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public PagView findStaffMemberPer(StaffMVO ovo, PagInfo pagInfo)
			throws SysException, AppException {
		List oList = null;
		String dpstr = null;
		if (ovo.getPartyRoleType().equals(Constant.PARTY_ROLE_TYPE_ORG_DEPT)) {
			dpstr = ovo.getStaffSVO().getDeptId();
		}
		return staffMDao.findStaffMemberPer(ovo, pagInfo, oList, dpstr);

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
	public PagView findStaffMemberForCheker(StaffMVO ovo, PagInfo pagInfo)
			throws SysException, AppException {
		List oList = null;
		String dpstr = null;
		if (Constant.PARTY_ROLE_TYPE_ORG_DEPT.equals(ovo.getPartyRoleType())) {
			dpstr = ovo.getStaffSVO().getDeptId();
		}
		return staffMDao.findStaffMemberForCheker(ovo, pagInfo, oList, dpstr);

	}

	/**
	 * 修改员工信息,只是操纵单表(主要用于修改员工所对应组织结构信息)
	 * 
	 * @param ovo
	 * @param localNetId
	 * @return void
	 * @throws SysException
	 * @throws AppException
	 */
	public void modStaffOnly(StaffSVO ovo, String localNetId)
			throws SysException, AppException {
		OrgDeptSVO odv = new OrgDeptSVO();
		PartySVO psvo = new PartySVO();
		PartyDOM pdom = new PartyDOM();
		StaffMVO smvo = new StaffMVO();
		try {
			staffDao.update(ovo);
			// 组织机构修改后，应该判断新的组织机构的本地网ID和服务区ID和原来的是否一致，不一致则修改。
			smvo = staffMDao.findStaffMemberByStaffId(ovo.getStaffId());
			odv.setDeptId(smvo.getStaffSVO().getDeptId());
			odv = (OrgDeptSVO) orgDeptDao.findByPK(odv);
			psvo = smvo.getPartySVO();
			log.debug("psvo:" + psvo);
			log.debug("odv:" + odv);
			if (!odv.getLocalNetId().equals(psvo.getLocalNetId())
					|| !odv.getAreaId().equals(psvo.getAreaId())
					|| !odv.getServDeptId().equals(psvo.getServDeptId())
					|| !odv.getBranchId().equals(psvo.getBranchId())) {// if
				// 本地网和服务区不同，则修改员工Party。
				psvo.setLocalNetId(odv.getLocalNetId());
				psvo.setAreaId(odv.getAreaId());
				psvo.setServDeptId(odv.getServDeptId());
				psvo.setBranchId(odv.getBranchId());
				pdom.update(psvo);
			}
		} catch (Exception ex) {
			throw new SysException("333", "modStaffOnly", ex);

		}
		// // 同步给计费//
		//        
		// Date curDate = SysDateUtil.getCurrentDate();
		// String staffId = smvo.getStaffSVO().getStaffId();
		// String officeId = odv.getDeptId();
		// String name = psvo.getName();
		// String passWord = "666666"; // 初始密码,默认
		// String regionId = "311"; // TODO 暂定死;
		// String actType = "M"; // M表示修改
		// Date createDate = curDate;
		// Date effDate = curDate;
		// java.util.Date expDate;
		// try {
		// expDate = DateUtil.to_date("2020-10-17", "yyyy-MM-dd");
		// } catch (Exception e) {
		// throw new AppException("001001", "同步员工时时间转换异常");
		// }
		// String operType = "0"; // 0表示同步员工,1表示同步工区;
		// SmIsDelegateDOM smIsDelegateDom = new SmIsDelegateDOM();
		// smIsDelegateDom.workStaffSyn(staffId, officeId, name, passWord,
		// regionId, actType,
		// createDate, expDate, effDate, operType);
		// // 同步给计费//

		// 同步给资源//
		// String[] backStr = staffMDao.updateStaffMVOByRs(smvo,
		// Constant.STAFF_ACT_TYPE_R);
		// if (backStr[0].equals("-1")) {
		// throw new AppException("3333", "同步资源出错,原因为:" + backStr[1]);
		// }
		// 同步给资源//

	}

	/**
	 * 查询员工信息(只用于单表)
	 * 
	 * @param staffId
	 * @return StaffSVO
	 * @throws SysException
	 * @throws AppException
	 */
	public StaffSVO findStaffByPk(String staffId) throws SysException,
			AppException {
		StaffSVO vo = new StaffSVO();
		vo.setStaffId(staffId);
		return (StaffSVO) staffDao.findByPK(vo);
	}

	/**
	 * 更新单表信息(主要用于更新简单字段)
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 */
	public void update(StaffSVO vo) throws SysException, AppException {
		staffDao.update(vo);
	}

	/**
	 * 根据主键查询staffmvo
	 * 
	 * @param staffId
	 * @return Staffmvo
	 * @throws SysException
	 * @throws AppException
	 */
	public StaffMVO findStaffMVOByPK(String staffId) throws SysException,
			AppException {
		return staffMDao.findStaffMemberByStaffId(staffId);
	}

	/**
	 * 增加多表staffmvo
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 */
	public String addStaffMVO(StaffMVO vo) throws Exception, AppException {
		String staffId = null;
		PartySVO psvo = new PartySVO();
		StaffSVO svo = new StaffSVO();
		Date curDate = null;
		Timestamp curTime = null;
		try {
			PartyRoleSVO prsvo = new PartyRoleSVO();
			PartyRoleDOM prdom = new PartyRoleDOM();
			PartyDOM partyDom = new PartyDOM();
			// 取系统日期
			curDate = SysDateUtil.getCurrentDate();
			curTime = SysDateUtil.getCurrentTimestamp();

			svo = vo.getStaffSVO();
			psvo = vo.getPartySVO();

			// 判断是否为代理商创建员工,如果是员工的本地网,服务区,营维中心,支局根据代理上来取
			if (vo.getPartyRoleType().equals(Constant.PARTY_ROLE_TYPE_MS_AGENT)) {

				prsvo.setPartyRoleTypeId(Constant.PARTY_ROLE_TYPE_MS_AGENT);
				prsvo.setPartyRoleId(svo.getCompanyCode());
				prsvo = prdom.findByPk(prsvo);
				if (prsvo == null)
					throw new AppException("222", "不存在此代理商!");
				PartySVO apsvo = new PartySVO();
				apsvo.setPartyId(prsvo.getPartyId());
				apsvo = partyDom.findByPk(apsvo);

				psvo.setLocalNetId(apsvo.getLocalNetId());
				psvo.setAreaId(apsvo.getAreaId());
				psvo.setServDeptId(apsvo.getServDeptId());
				psvo.setBranchId(apsvo.getBranchId());

				svo.setDeptType(Constant.STAFF_DEPT_TYPE_E);

			}

			if (vo.getPartyRoleType().equals(Constant.PARTY_ROLE_TYPE_ORG_DEPT)) {
				OrgDeptSVO odvo = new OrgDeptSVO();
				odvo.setDeptId(vo.getStaffSVO().getDeptId());
				odvo = (OrgDeptSVO) orgDeptDao.findByPK(odvo);
				vo.setDeptName(odvo.getDeptName());

				// 向party增加本地网,服务区,直局,营销中心 先取组织机构的
				psvo.setLocalNetId(odvo.getLocalNetId());
				psvo.setAreaId(odvo.getAreaId());
				psvo.setServDeptId(odvo.getServDeptId());
				psvo.setBranchId(odvo.getBranchId());

				svo.setDeptType(Constant.STAFF_DEPT_TYPE_I);
				svo.setCompanyCode("0");
			}

			psvo.setSts(Constant.STS_IN_USE);
			psvo.setPartyType(Constant.PARYT_TYPE_I);
			psvo.setCreateDate(curTime);
			psvo.setStsDate(curTime);

			String partyId = partyDom.add(psvo);
			// 向参与人对应证件信息表,联系信息表插入数据

			if (vo.getCertCode() != null || vo.getCertTypeId() != null
					|| vo.getCertExpDate() != null) {
				PartyIdentitySVO isvo = new PartyIdentitySVO();
				PartyIdentityDOM pidom = new PartyIdentityDOM();
				isvo.setPartyId(partyId);
				isvo.setLocalNetId(psvo.getLocalNetId());
				isvo.setCertCode(vo.getCertCode());
				isvo.setCertTypeId(vo.getCertTypeId());
				isvo.setCertExpDate(vo.getCertExpDate());
				isvo.setSts(Constant.STS_IN_USE);
				isvo.setCreateDate(curDate);
				isvo.setStsDate(curDate);
				pidom.add(isvo);

			}
			// /服务开通没有contactMedium表
			// if (vo.getPostalCode() != null || vo.getContactAddr() != null) {
			// ContactMediumSVO cmsvo = new ContactMediumSVO();
			// ContactMediumDOM cmdom = new ContactMediumDOM();
			// cmsvo.setPartyId(partyId);
			// cmsvo.setPostalCode(vo.getPostalCode());
			// cmsvo.setContactAddr(vo.getContactAddr());
			// cmsvo.setSts(Constant.STS_IN_USE);
			// cmsvo.setCreateDate(curDate);
			// cmsvo.setStsDate(curDate);
			// cmdom.add(cmsvo);
			// }
			// 向员工表插入记录
			svo.setPartyId(partyId);
			svo.setSts(Constant.STS_IN_USE);
			svo.setCreateDate(curTime);
			svo.setStsDate(curTime);
			svo.setLocalNetId(psvo.getLocalNetId());
			staffId = SMMaxId.getStaffMaxId(psvo.getLocalNetId(), psvo
					.getAreaId());
			svo.setStaffId(staffId);
			int i=0;
			while (staffDao.findByPK(svo) != null) {
				i++;
				staffId = String.valueOf(Long.parseLong(SMMaxId.getStaffMaxId(
						psvo.getLocalNetId(), psvo.getAreaId())) + i);
				svo.setStaffId(staffId);
			}
			staffDao.add(svo);
			// 判断是否为自动创建用户,如果是,自动创建用户.
			SysConfigSVO scsvo = new SysConfigSVO();
			scsvo.setConfigId("21001");
			scsvo = (SysConfigSVO) sysConfigDao.findByPK(scsvo);
			if (scsvo.getCurValue().equals("Y")) {
				SysUserSVO susvo = new SysUserSVO();
				susvo.setSysUserId(staffId);
				susvo.setPartyRoleId(staffId);
				susvo.setPartyRoleTypeId("7");
				susvo.setSysUserName(staffId);
				susvo.setLocalNetId(psvo.getLocalNetId());
				scsvo.setConfigId("21002");
				scsvo = (SysConfigSVO) sysConfigDao.findByPK(scsvo);
				// added by yangkai 2009-8-26
				// 加密算法选择，如果是N或没有配置则使用MD5算法，否则为联创提供的算法
				String flag = SysConfigData.getSysConfigCurValue("110010",
						null, null, null, null, null);
				if (flag == null || flag.equals(SysConstants.PWD_FLAG))
					susvo.setPassword(PasswordUtil.getMD5Str(scsvo
							.getCurValue()));
				else
					susvo.setPassword(Encryptor.fnEncrypt(scsvo.getCurValue(),
							"00linkage"));
				susvo.setCreateDate(curTime);
				susvo.setSts(Constant.STS_IN_USE);
				susvo.setSetPwdTime(curTime);
				susvo.setStsDate(curTime);
				sysUserDao.add(susvo);
				vo.setSysUserId(susvo.getSysUserId());
				vo.setPassWord(susvo.getPassword());
			}

			// 向对应角色表插入记录
			prsvo.setPartyId(partyId);
			prsvo.setPartyRoleId(staffId);
			prsvo.setPartyRoleTypeId(Constant.PARTY_ROLE_TYPE_STAFF);// 7表示为员工
			prsvo.setLocalNetId(psvo.getLocalNetId());
			prdom.add(prsvo);
		} catch (SysException se) {
			throw new SysException("222", "addStaffMVO出错", se);
		} catch (AppException se) {
			throw new AppException("111", "addStaffMVO出错");
		}
		// // 同步给计费//
		// /*
		// String officeId = svo.getDeptId();
		// String name = psvo.getName();
		// String passWord = "666666"; // 初始密码,默认
		// String regionId = "311"; // TODO 暂定死;
		// String actType = "A"; // A表示增加
		// Date createDate = curDate;
		// Date effDate = curDate;
		// java.util.Date expDate = DateUtil.to_date("2020-10-17",
		// "yyyy-MM-dd");
		// String operType = "0"; // 0表示给增加员工,1表示增加工区;
		// SmIsDelegateDOM smIsDelegateDom = new SmIsDelegateDOM();
		// String retStaff = smIsDelegateDom.workStaffSyn(staffId, officeId,
		// name, passWord,
		// regionId,
		// actType, createDate, expDate, effDate, operType);
		// staffId = staffId + "\t" + retStaff;
		// 同步给计费//

		// 同步给资源//
		// String[] backStr = staffMDao.updateStaffMVOByRs(vo,
		// Constant.STAFF_ACT_TYPE_A);
		// if (backStr[0].equals("-1")) {
		// throw new AppException("3333", "同步资源出错,原因为:" + backStr[1]);
		// }

		return staffId;

	}

	/**
	 * 删除多表staffmvo
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 */
	public void delStaffMVOByOnly(StaffSVO vo) throws SysException,
			AppException {
		SysUserSVO susvo = new SysUserSVO();
		susvo.setPartyRoleId(vo.getStaffId());
		susvo.setPartyRoleTypeId(Constant.PARTY_ROLE_TYPE_STAFF);
		susvo.setSts(Constant.STS_IN_USE);

		List list = sysUserDao.findByVO(susvo);
		// 判断是否为自动创建用户,如果是,自动删除所有该员工的用户.
		SysConfigSVO scsvo = new SysConfigSVO();
		scsvo.setConfigId("21001");
		scsvo = (SysConfigSVO) sysConfigDao.findByPK(scsvo);
		if (scsvo.getCurValue().equals("Y")) {
			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					susvo = (SysUserSVO) it.next();
					susvo.setSts("P");
					sysUserDao.update(susvo);
				}
			}
		} else {
			if (list != null) {
				throw new AppException("111", "删除员工出现错误，该员工存在用户");
			}
		}
		Date curDate = SysDateUtil.getCurrentDate();
		/*
		 * PartyDOM pdom = new PartyDOM(); PartySVO psvo = new PartySVO();
		 * psvo.setPartyId(vo.getPartyId()); psvo.setStsDate(curDate);
		 * pdom.delete(psvo);
		 * 
		 * PartyIdentityDOM pidom = new PartyIdentityDOM(); ContactMediumDOM
		 * cmdom = new ContactMediumDOM(); PartyIdentitySVO pisvo = new
		 * PartyIdentitySVO(); ContactMediumSVO cmsvo = new ContactMediumSVO();
		 * pisvo.setPartyId(vo.getPartyId()); pisvo.setSts("A");
		 * cmsvo.setPartyId(vo.getPartyId()); cmsvo.setSts("A");
		 * 
		 * List piList = pidom.findByVo(pisvo); if (piList != null) { pisvo =
		 * (PartyIdentitySVO) piList.get(0); pisvo.setStsDate(curDate);
		 * pidom.delete(pisvo); } List cmList = cmdom.findByVo(cmsvo); if
		 * (cmList != null) { cmsvo = (ContactMediumSVO) cmList.get(0);
		 * cmsvo.setStsDate(curDate); cmdom.delete(cmsvo); }
		 */
		vo.setStsDate(SysDateUtil.getCurrentTimestamp());
		vo.setSts(Constant.STS_HISTORY);
		staffDao.update(vo);

		PartyRoleSVO prsvo = new PartyRoleSVO();
		PartyRoleDOM pdom = new PartyRoleDOM();

		prsvo.setPartyRoleId(vo.getStaffId());
		prsvo.setPartyRoleTypeId(Constant.PARTY_ROLE_TYPE_STAFF);
		pdom.delete(prsvo);

		// 同步给计费
		/*
		 * String staffId = vo.getStaffId(); String officeId = vo.getDeptId();
		 * String name = null; String passWord = "666666"; // 初始密码,默认 String
		 * regionId = "311"; // TODO 暂定死; String actType = "D"; // D表示删除 Date
		 * createDate = curDate; Date effDate = curDate; java.util.Date expDate;
		 * try { expDate = DateUtil.to_date("2020-10-17", "yyyy-MM-dd"); } catch
		 * (Exception e) { // TODO Auto-generated catch block throw new
		 * AppException("0.0", "同步员工时间转换错误"); } String operType = "0"; //
		 * 0表示同步员工,1表示同步工区; SmIsDelegateDOM smIsDelegateDom = new
		 * SmIsDelegateDOM(); smIsDelegateDom.workStaffSyn(staffId, officeId,
		 * name, passWord, regionId, actType, createDate, expDate, effDate,
		 * operType); // 同步给计费 //
		 */
		// 同步给资源//
		// StaffMVO mvo = staffMDao.findStaffMemberByStaffId(vo.getStaffId());
		// String[] backStr = staffMDao.updateStaffMVOByRs(mvo,
		// Constant.STAFF_ACT_TYPE_D);
		// if (backStr[0].equals("-1")) {
		// throw new AppException("3333", "同步资源出错,原因为:" + backStr[1]);
		// } //
		// 同步给资源//
	}

	/**
	 * 修改多表staffmvo
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 */
	public void updateStaffMVO(StaffMVO vo) throws Exception {
		StaffSVO svo = vo.getStaffSVO();
		PartySVO psvo = vo.getPartySVO();

		PartyDOM pdom = new PartyDOM();
		Timestamp curTime = SysDateUtil.getCurrentTimestamp();
		Date curDate = SysDateUtil.getCurrentDate();

		// 修改员工对应的party表信息
		psvo.setStsDate(curTime);
		pdom.update(psvo);
		// 修改party对应的证件信息及其联系信息
		PartyIdentityDOM pidom = new PartyIdentityDOM();
		ContactMediumDOM cmdom = new ContactMediumDOM();
		PartyIdentitySVO pisvo = new PartyIdentitySVO();
		ContactMediumSVO cmsvo = new ContactMediumSVO();
		pisvo.setPartyId(vo.getPartySVO().getPartyId());
		pisvo.setSts(Constant.STS_IN_USE);
		cmsvo.setPartyId(vo.getPartySVO().getPartyId());
		cmsvo.setSts(Constant.STS_IN_USE);

		List piList = pidom.findByVo(pisvo);
		if (piList == null) {
			if (vo.getCertCode() != null || vo.getCertTypeId() != null
					|| vo.getCertExpDate() != null) {
				pisvo.setLocalNetId(vo.getPartySVO().getLocalNetId());
				pisvo.setCertCode(vo.getCertCode());
				pisvo.setCertTypeId(vo.getCertTypeId());
				pisvo.setCertExpDate(vo.getCertExpDate());
				pisvo.setSts(Constant.STS_IN_USE);
				pisvo.setCreateDate(curDate);
				pisvo.setStsDate(curDate);
				pidom.add(pisvo);

			}
		} else {
			pisvo = (PartyIdentitySVO) piList.get(0);
			pisvo.setCertCode(vo.getCertCode());
			pisvo.setCertTypeId(vo.getCertTypeId());
			pisvo.setCertExpDate(vo.getCertExpDate());
			pisvo.setStsDate(curDate);
			pidom.update(pisvo);
		}

		// 修改contactMedium表信息
		// List cmList = cmdom.findByVo(cmsvo);
		// if (cmList == null) {
		// if (vo.getPostalCode() != null || vo.getContactAddr() != null) {
		// cmsvo.setSts(Constant.STS_IN_USE);
		// cmsvo.setCreateDate(curDate);
		// cmsvo.setStsDate(curDate);
		// cmdom.add(cmsvo);
		// }
		//
		// } else {
		// cmsvo = (ContactMediumSVO) cmList.get(0);
		// cmsvo.setPostalCode(vo.getPostalCode());
		// cmsvo.setContactAddr(vo.getContactAddr());
		// cmsvo.setStsDate(curDate);
		// cmdom.update(cmsvo);
		// }

		// 修改员工信息
		svo.setStsDate(SysDateUtil.getCurrentTimestamp());
		staffDao.update(svo);
		// 同步给计费//

		// String staffId = svo.getStaffId();
		// String officeId = svo.getDeptId();
		// String name = psvo.getName();
		// String passWord = "666666"; // 初始密码,默认
		// String regionId = "311"; // TODO 暂定死;
		// String actType = "M"; // M表示修改
		// Date createDate = curDate;
		// Date effDate = curDate;
		// java.util.Date expDate = DateUtil.to_date("2020-10-17",
		// "yyyy-MM-dd");
		// String operType = "0"; // 0表示同步员工,1表示同步工区;
		// SmIsDelegateDOM smIsDelegateDom = new SmIsDelegateDOM();
		// smIsDelegateDom.workStaffSyn(staffId, officeId, name, passWord,
		// regionId, actType,
		// createDate, expDate, effDate, operType);

		// 同步给计费//

		// 同步给资源//
		// vo =
		// staffMDao.findStaffMemberByStaffId(vo.getStaffSVO().getStaffId());
		// String[] backStr = staffMDao.updateStaffMVOByRs(vo,
		// Constant.STAFF_ACT_TYPE_R);
		// if (backStr[0].equals("-1")) {
		// throw new AppException("3333", "同步资源出错,原因为:" + backStr[1]);
		// }
		// 同步给资源//

	}

	/**
	 * 查询多表staffmvo
	 * 
	 * @param vo
	 * @return List<StaffMVO>
	 * @throws SysException
	 * @throws AppException
	 */
	public List findStaffMember(StaffMVO vo) throws SysException, AppException {
		return staffMDao.findStaffMember(vo);
	}

	public void delStaffMVO(String[] staffIds) throws SysException,
			AppException {
		String staffId = null;
		StaffSVO vo = new StaffSVO();
		for (int i = 0; i < staffIds.length; i++) {
			staffId = staffIds[i];
			vo.setStaffId(staffId);
			vo = (StaffSVO) staffDao.findByPK(vo);
			this.delStaffMVOByOnly(vo);

		}
	}

	/**
	 * 查询最新修改的员工记录
	 * 
	 * @param set
	 *            HashSet
	 * @return List
	 * @throws Exception
	 */
	public PagView findStaffBySet(HashSet set, PagInfo pagInfo)
			throws Exception {
		return staffMDao.findStaffMemberSet(set, pagInfo);
	}

	public List findStaffMVOByStaffIds(String staffIds) throws Exception {
		List list = staffMDao.findStaffMemberByStaffIds(staffIds);
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				StaffMVO fnv1 = (StaffMVO) o1;
				StaffMVO fnv2 = (StaffMVO) o2;
				return Integer.parseInt(fnv1.getStaffSVO().getStaffId())
						- Integer.parseInt(fnv2.getStaffSVO().getStaffId());
			}
		});

		// 为了防止出现重复的staff
		StaffMVO mvo1 = null;
		StaffMVO mvo2 = null;
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				mvo1 = (StaffMVO) list.get(i);
				mvo2 = (StaffMVO) list.get(i - 1);
				if (mvo1.getStaffSVO().getStaffId().equals(
						mvo2.getStaffSVO().getStaffId())) {
					list.remove(i - 1);
				}
			}

		}
		return staffMDao.findStaffMemberByStaffIds(staffIds);
	}

	/**
	 * 查询多表staffmvo(姓名为精确查询)
	 * 
	 * @param vo
	 * @return List<StaffMVO>
	 * @throws SysException
	 * @throws AppException
	 */
	public List findStaffMVO(StaffMVO vo) throws SysException, AppException {
		return staffMDao.findStaffMVO(vo);
	}

	/**
	 * 分页查询员工(接口)
	 * 
	 * @param ovo
	 * 
	 * @param pagInfo
	 * @throws SysException
	 * @throws AppException
	 * @return pagview
	 */
	public PagView findStaffNotByDept(StaffMVO ovo, PagInfo pagInfo)
			throws SysException, AppException {
		return staffMDao.findStaffMemberFast(ovo, pagInfo, null, null);

	}

	/**
	 * 根据主键查询staffmvo
	 * 
	 * @param staffId
	 * @return Staffmvo
	 * @throws SysException
	 * @throws AppException
	 */
	public String findNameByPK(String staffId) throws SysException,
			AppException {
		return staffMDao.findNameByStaffId(staffId);
	}

}
