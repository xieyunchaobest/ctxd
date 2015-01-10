package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.DataCache;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.IAppColumnSDAO;
import com.cattsoft.sm.component.dao.IAppTableSDAO;
import com.cattsoft.sm.component.dao.IDomainSDAO;
import com.cattsoft.sm.component.dao.IDomainValueSDAO;
import com.cattsoft.sm.component.dao.IStatusSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.AppColumnSVO;
import com.cattsoft.sm.vo.AppTableSVO;
import com.cattsoft.sm.vo.DomainSVO;
import com.cattsoft.sm.vo.DomainValueSVO;
import com.cattsoft.sm.vo.StatusMVO;
import com.cattsoft.sm.vo.StatusSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-18 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StatusDOM {

	private Logger log = Logger.getLogger(StatusDOM.class);

	private IAppTableSDAO appTableDao = (IAppTableSDAO) DAOFactory.getInstance().getDAO(
			IAppTableSDAO.class);

	private IAppColumnSDAO appColumnDao = (IAppColumnSDAO) DAOFactory.getInstance().getDAO(
			IAppColumnSDAO.class);

	private IDomainSDAO domainDao = (IDomainSDAO) DAOFactory.getInstance()
			.getDAO(IDomainSDAO.class);

	private IDomainValueSDAO domainValueDao = (IDomainValueSDAO) DAOFactory.getInstance().getDAO(
			IDomainValueSDAO.class);

	private IStatusSDAO statusDao = (IStatusSDAO) DAOFactory.getInstance()
			.getDAO(IStatusSDAO.class);

	public void add(StatusMVO mvo) throws AppException, SysException, DataCacheException {

		AppTableSVO atvo = mvo.getAppTableSVO();
		Date curDate = SysDateUtil.getCurrentDate();
		List appTableList = appTableDao.findByVO(atvo);
		if (appTableList != null && appTableList.size() > 0) {
			atvo = (AppTableSVO) appTableList.get(0);
			if (atvo.getSts() == null || atvo.getSts().equals(Constant.STS_HISTORY)) {
				atvo.setSts(Constant.STS_IN_USE);
				appTableDao.update(atvo);
			}

		} else {
			atvo.setAppTableId(MaxId.getSequenceNextVal(Constant.SEQ_APP_TABLE_ID));
			// atvo.setDbUser(Constant.DB_USER);
			atvo.setSts(Constant.STS_IN_USE);
			atvo.setCreateDate(curDate);
			atvo.setStsDate(curDate);
			atvo.setLastModiDate(curDate);
			appTableDao.add(atvo);
		}

		DomainSVO dsvo = null;
		DomainValueSVO dvsvo = null;
		AppColumnSVO acvo = new AppColumnSVO();
		mvo.getDomainSVO().setDomainName(mvo.getAppColumnSVO().getEngName());
		mvo.getDomainSVO().setDomainMeaning(mvo.getAppColumnSVO().getChName());
		String domainId = this.judgeDomainValue(mvo);
		if (domainId == null) {
			domainId = MaxId.getSequenceNextVal(Constant.SEQ_DOMAIN_ID);
			dsvo = mvo.getDomainSVO();
			dsvo.setDomainId(domainId);
			dsvo.setSts(Constant.STS_IN_USE);
			dsvo.setCreateDate(curDate);
			dsvo.setStsDate(curDate);
			// 先暂时插入我知道的字段,以后加
			domainDao.add(dsvo);
			List domainValueList = mvo.getDomainValueSVOList();
			for (int i = 0; i < domainValueList.size(); i++) {
				dvsvo = (DomainValueSVO) domainValueList.get(i);
				dvsvo.setDomainId(domainId);
				dvsvo.setDomainValueId(MaxId.getSequenceNextVal(Constant.SEQ_DOMAIN_VALUE_ID));
				domainValueDao.add(dvsvo);
			}
		} else {
			DomainSVO ddvo = new DomainSVO();
			ddvo.setDomainId(domainId);
			ddvo = (DomainSVO) domainDao.findByPK(ddvo);
			if (ddvo.getSts() == null || ddvo.getSts().equals(Constant.STS_HISTORY)) {
				ddvo.setSts(Constant.STS_IN_USE);
				domainDao.update(ddvo);
			}
		}
		acvo.setAppTableId(atvo.getAppTableId());
		acvo.setDomainId(domainId);
		if (appColumnDao.findByVO(acvo) == null) {
			acvo.setEngName(mvo.getDomainSVO().getDomainName());
			acvo.setChName(mvo.getDomainSVO().getDomainMeaning());
			acvo.setColumnComment("维持" + mvo.getDomainSVO().getDomainMeaning());
			acvo.setColumnLength("1");
			acvo.setDataType("CHR");
			acvo.setMandatoryFlag("Y");
			acvo.setForeignKeyFlag("N");
			acvo.setPrimaryKeyFlag("N");
			acvo.setSts(Constant.STS_IN_USE);
			acvo.setCreateDate(curDate);
			acvo.setStsDate(curDate);
			acvo.setLastModiDate(curDate);
			acvo.setAppColumnId(MaxId.getSequenceNextVal(Constant.SEQ_APP_COLUMN_ID));
			appColumnDao.add(acvo);
		}
		DataCache.initStatusHashMap(atvo.getEngName());

	}

	private String judgeDomainValue(StatusMVO mvo) throws AppException, SysException {
		DomainSVO vo = mvo.getDomainSVO();
		DomainValueSVO dvvo = null;
		List domainList = domainDao.findByVO(vo);
		if (domainList != null && domainList.size() > 0) {
			Iterator it = domainList.iterator();
			dvvo = new DomainValueSVO();
			while (it.hasNext()) {
				vo = (DomainSVO) it.next();
				dvvo.setDomainId(vo.getDomainId());
				List value2List = domainValueDao.findByVO(dvvo);
				List value1List = mvo.getDomainValueSVOList();
				if (value1List.size() == value2List.size() && this.compate(value1List, value2List)) {
					return dvvo.getDomainId();
				}
			}
		}
		return null;
	}

	private boolean compate(List list1, List list2) {
		int j = 0;
		DomainValueSVO svo1 = null;
		DomainValueSVO svo2 = null;
		for (int i = 0; i < list1.size(); i++) {

			svo1 = (DomainValueSVO) list1.get(i);
			for (j = 0; j < list2.size(); j++) {
				svo2 = (DomainValueSVO) list2.get(j);
				if (!svo1.getDomainValue().equals(svo2.getDomainValue())) {
					continue;
				}
				if (!svo1.getValueMeaning().equals(svo2.getValueMeaning())) {
					continue;
				}
				if (!svo1.getOrderId().equals(svo2.getOrderId())) {
					continue;
				} else {
					break;
				}
			}
			if (j == list2.size()) {
				return false;
			}
		}
		return true;
	}

	public PagView findStatusByPage(StatusSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
		return statusDao.findStatusByPage(vo, set, pagInfo);
	}

	public List findByVo(StatusSVO vo) throws AppException, SysException {
		return statusDao.findByVO(vo);
	}

	/**
	 * lilin[20080819]增加status数据，原来的方法不好用
	 * 
	 * @param vo
	 * @throws AppException
	 * @throws SysException
	 */
	public void addStatus(StatusSVO vo) throws AppException, SysException {
		statusDao.add(vo);
		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
				.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);
	}

	public void modStatus(StatusSVO vo) throws AppException, SysException {
		statusDao.updateStatus(vo);
		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
				.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);
	}

	public void delStatus(List list) throws AppException, SysException {
		// lilin[20080819]
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				StatusSVO vo = (StatusSVO) list.get(i);
				statusDao.delete(vo);
				ActionLogSVO actionLog = vo.getActionLog();
				actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
				IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
						.getDAO(IActionLogSDAO.class);
				actionLogDao.add(actionLog);
			}
		}
		// end lilin[20080819]
	}

}
