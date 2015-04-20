package com.cattsoft.sm.component.domain;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.IOrgDeptMDAO;
import com.cattsoft.sm.component.dao.IOrgDeptSDAO;
import com.cattsoft.sm.component.dao.IPartySDAO;
import com.cattsoft.sm.component.dao.IStaffSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.OrgDeptSVO;
import com.cattsoft.sm.vo.StaffSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class OrgDeptDOM {

	private static Logger log = Logger.getLogger(OrgDeptDOM.class);

	private IOrgDeptSDAO orgDeptSDao = (IOrgDeptSDAO) DAOFactory.getInstance()
			.getDAO(IOrgDeptSDAO.class);

	private IOrgDeptMDAO orgDeptMDao = (IOrgDeptMDAO) DAOFactory.getInstance()
			.getDAO(IOrgDeptMDAO.class);

	private IStaffSDAO staffDao = (IStaffSDAO) DAOFactory.getInstance().getDAO(
			IStaffSDAO.class);

	private IPartySDAO partyDao = (IPartySDAO) DAOFactory.getInstance().getDAO(
			IPartySDAO.class);

	/**
	 * 查询组织机构信息
	 * 
	 * @param ovo
	 *            OrgDeptVO
	 * @param
	 * @throws SysException
	 * @throws AppException
	 * @return List
	 */
	public List findOrgDeptByTree(OrgDeptSVO vo) throws SysException,
			AppException {
		log.debug(vo.getLocalNetId());
		log.debug(vo.getAreaId());
		List list = orgDeptSDao.findOrgDeptByTree(vo);
		log.debug("list=" + list);
		return list;

	}

	/**
	 * 根据主键查询组织机构信息
	 * 
	 * @param deptId
	 *            Long
	 * @throws SysException
	 * @return OrgDeptPartyVO
	 */
	public OrgDeptSVO findOrgDeptById(String deptId) throws AppException,
			SysException {

		OrgDeptSVO svo = new OrgDeptSVO();
		svo.setDeptId(deptId);
		return (OrgDeptSVO) orgDeptSDao.findByPK(svo);

	}

	/**
	 * 修改组织机构信息
	 * 
	 * @param ovo
	 *            OrgDeptVO
	 * @param pvo
	 *            PartyVO
	 * @throws SysException
	 * @throws AppException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public void modOrgDept(OrgDeptSVO vo) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("需要修改组织结构的id为:" + vo.getDeptId());
		}
		orgDeptSDao.update(vo);

		// 记录操作日志
		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
				.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);

	}

	/**
	 * 删除组织机构
	 * 
	 * @param deptId
	 *            Long
	 * @throws SysException
	 * @throws AppException
	 */
	public void delOrgDept(String deptId, List logList) throws SysException,
			AppException {

		OrgDeptSVO deptVo = new OrgDeptSVO();
		StaffSVO staffVo = new StaffSVO();
		staffVo.setSts("A");
		staffVo.setDeptId(deptId);
		List list = staffDao.findByVO(staffVo);
		if (list != null) {
			throw new AppException("3250007", "该组织结构下存在员工，请先删除该组织结构下员工！");
		}

		deptVo.setDeptId(deptId);
		OrgDeptSVO deptVoRet = new OrgDeptSVO();
		deptVoRet.setDeptId(deptId);
		deptVoRet = (OrgDeptSVO) orgDeptSDao.findByPK(deptVoRet);
		deptVo.setSts("P");
		deptVo.setStsDate(SysDateUtil.getCurrentDate());
		orgDeptSDao.update(deptVo);
		// 记录操作日志
		List actionLogList = null;
		if (logList != null && logList.size() > 0) {
			actionLogList = new ArrayList();
			for (int i = 0; i < logList.size(); i++) {
				ActionLogSVO actionLog = (ActionLogSVO) logList.get(i);
				actionLog.setActionId(MaxId
						.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
				actionLogList.add(actionLog);
			}
			IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
					.getDAO(IActionLogSDAO.class);
			actionLogDao.addBat(actionLogList);
		}
	}

	/**
	 * 增加组织机构
	 * 
	 * @param ovo
	 * @return
	 * @throws Exception
	 */
	public String addOrgDept(OrgDeptSVO ovo) throws Exception {

		OrgDeptSVO deptVo = new OrgDeptSVO();
		deptVo.setDeptId(ovo.getParentDeptId());
		deptVo = (OrgDeptSVO) orgDeptSDao.findByPK(deptVo);
		if (deptVo.getAreaId().equals("0")) {
			if (deptVo.getLocalNetId().equals("0")) {
				ovo.setAreaId("0");
			}
		} else {
			ovo.setLocalNetId(deptVo.getLocalNetId());
			ovo.setAreaId(deptVo.getAreaId());
		}
		try {
			// ovo.setServDeptId(deptVo.getServDeptId());
			// ovo.setBranchId(deptVo.getBranchId());
			ovo.setDeptId(SMMaxId.getOrgDeptMaxId(ovo.getLocalNetId()));
			Date curDate = SysDateUtil.getCurrentDate();
			// 赋初值
			ovo.setCreateDate(curDate);
			ovo.setSts("A");
			ovo.setStsDate(curDate);

			orgDeptSDao.add(ovo);

			// 记录操作日志
			ActionLogSVO actionLog = ovo.getActionLog();
			actionLog.setActionId(MaxId
					.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
			actionLog.setActionText(ovo.getDeptId() + "" + ovo.getDeptName()
					+ ":增加");
			IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
					.getDAO(IActionLogSDAO.class);
			actionLogDao.add(actionLog);

		} catch (SysException e) {
			throw new SysException("", "addOrgDept", e);
		}

		return ovo.getDeptId();
	}

	public List findOrgDept(OrgDeptSVO vo) throws SysException, AppException {
		return orgDeptSDao.findByVO(vo);
	}

	public void uniteOrgDept(String deptId, String newDeptId)
			throws SysException, AppException {

		Date curDate = SysDateUtil.getCurrentDate();
		try {

			OrgDeptSVO ovo = new OrgDeptSVO();
			OrgDeptSVO oldVo = new OrgDeptSVO();
			StaffSVO staffVo = new StaffSVO();
			List list = null;

			ovo.setDeptId(newDeptId);
			ovo = (OrgDeptSVO) orgDeptSDao.findByPK(ovo);
			oldVo.setDeptId(deptId);
			oldVo.setParentDeptId(ovo.getParentDeptId());
			oldVo.setStsDate(curDate);
			orgDeptSDao.update(oldVo);

			staffVo.setDeptId(deptId);
			staffVo.setSts("A");
			list = staffDao.findByVO(staffVo);
			if (list != null) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
					staffVo = (StaffSVO) it.next();
					staffVo.setDeptId(newDeptId);
					staffVo.setStsDate(SysDateUtil.getCurrentTimestamp());
					staffDao.update(staffVo);

				}
			}
			oldVo.setSts("P");
			orgDeptSDao.update(oldVo);
		} catch (SysException se) {
			throw new SysException("sss", "OrgDeptDOM.uniteOrgDept", se);

		}

	}

	public List findSubDeptById(String deptId) throws AppException,
			SysException {
		List oList = new ArrayList();
		OrgDeptSVO vo = new OrgDeptSVO();
		OrgDeptSVO svo = new OrgDeptSVO();
		vo.setDeptId(deptId);
		vo.setSts(Constant.STS_IN_USE);
		vo = (OrgDeptSVO) orgDeptSDao.findByPK(vo);

		oList.add(vo);
		svo.setParentDeptId(deptId);
		svo.setSts(Constant.STS_IN_USE);
		List list = orgDeptSDao.findIdByVO(svo);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				svo = (OrgDeptSVO) list.get(i);
				if (!svo.getDeptId().equals(vo.getDeptId())) {
					oList.add(svo);
					this.findById(svo.getDeptId(), oList);
				}
			}
		}
		return oList;
	}

	public void findById(String deptId, List oList) throws AppException,
			SysException {
		OrgDeptSVO svo = new OrgDeptSVO();
		svo.setParentDeptId(deptId);
		svo.setSts(Constant.STS_IN_USE);
		List list = orgDeptSDao.findIdByVO(svo);
		if (list != null && list.size() > 0) {
			oList.addAll(list);
			for (int i = 0; i < list.size(); i++) {
				svo = (OrgDeptSVO) list.get(i);
				this.findById(svo.getDeptId(), oList);
			}
		}
	}

	public List findSubDeptByMap(String deptId) throws AppException,
			SysException {
		List oList = new ArrayList();
		OrgDeptSVO vo = new OrgDeptSVO();
		OrgDeptSVO svo = new OrgDeptSVO();
		vo.setDeptId(deptId);
		vo.setSts(Constant.STS_IN_USE);
		vo = (OrgDeptSVO) orgDeptSDao.findByPK(vo);

		oList.add(vo.getDeptId());
		if (!vo.getLocalNetId().equals("0")) {
			svo.setLocalNetId(vo.getLocalNetId());
			if (!vo.getAreaId().equals("0")) {
				svo.setAreaId(vo.getAreaId());
			}
		}
		svo.setSts(Constant.STS_IN_USE);
		List list = orgDeptSDao.findByMap(svo);
		HashMap keyMap = (HashMap) list.get(1);
		this.findByIdMap(vo.getDeptId(), oList, keyMap);
		return oList;
	}

	public void findByIdMap(String deptId, List oList, HashMap map)
			throws AppException, SysException {
		List lst = (List) map.get(deptId);
		if (lst != null && lst.size() > 0) {
			oList.addAll(lst);
			for (int i = 0; i < lst.size(); i++) {
				String id = (String) lst.get(i);
				if (!deptId.equals(id)) {
					this.findByIdMap(id, oList, map);
				}
			}
		}

	}

	public String findIdByPro(String deptId) throws AppException, SysException {
		List list = orgDeptMDao.findDeptTree(deptId);
		String deptStr = null;
		if (list != null && 0 < list.size()) {
			deptStr = "";
			OrgDeptSVO deptSVO = new OrgDeptSVO();
			for (int i = 0; i < list.size(); i++) {
				deptSVO = (OrgDeptSVO) list.get(i);
				if (i == 0)
					deptStr = "'" + deptSVO.getDeptId() + "'";
				deptStr = deptStr + "," + "'" + deptSVO.getDeptId() + "'";

			}
		}
		return deptStr;
	}

	public List findOrgDeptByDeptTypeTree(OrgDeptSVO vo) throws AppException, SysException {
		List list = orgDeptSDao.findOrgDeptByDeptTypeTree(vo);
		log.debug("list=" + list);
		return list;
	}
	
	 
}
