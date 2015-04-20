package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.cattsoft.sm.component.dao.IExchMDAO;
import com.cattsoft.sm.component.dao.IExchSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.ExchSVO;
import com.cattsoft.sm.vo.WorkAreaExchSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-24 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class ExchDOM {
	private IExchSDAO exchDao = (IExchSDAO) DAOFactory.getInstance().getDAO(
			IExchSDAO.class);

	private IExchMDAO exchMDao = (IExchMDAO) DAOFactory.getInstance().getDAO(
			IExchMDAO.class); // add by xcj

	private static Logger log = Logger.getLogger(ExchDOM.class);

	public List findExchMVOByVO(ExchSVO vo) throws SysException, AppException {
		return exchMDao.findExchMVOByVO(vo);
	}

	/**
	 * 根据主键查询单表ExchSVO
	 * 
	 * @param vo
	 * @return ExchSVO
	 * @throws SysException
	 * @throws AppException
	 */
	public ExchSVO findByPk(ExchSVO vo) throws SysException, AppException {
		return (ExchSVO) exchDao.findByPK(vo);
	}

	/**
	 * 根据相应的条件查询单表ExchSVO
	 * 
	 * @param vo
	 * @return List<ExchSVO>
	 * @throws SysException
	 * @throws AppException
	 */
	public List findByVo(ExchSVO vo) throws SysException, AppException {
		return exchDao.findByVO(vo);
	}

	/**
	 * 修改单表ExchSVO
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 * @throws DataCacheException
	 */
	public void update(ExchSVO vo) throws SysException, AppException {
		ExchSVO esvo = new ExchSVO();
		esvo.setName(vo.getName());
		List list = exchDao.findByVO(esvo);
		if (list != null) {
			esvo = (ExchSVO) list.get(0);
			if (!esvo.getExchId().equals(vo.getExchId())) {
				throw new AppException("2222", "局向名称重复!");
			}

		}
		vo.setStsDate(SysDateUtil.getCurrentDate());
		exchDao.update(vo);
		// 记录操作日志
		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
				.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);

		// 同步缓存
		try {
			DataCache.initTreeHashMap(DataCache.AREA_EXCH);
		} catch (DataCacheException e) {
			throw new SysException("222", "同步缓存area_exch错误", e);
		}

	}

	/**
	 * 删除单表ExchSVO
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 */
	public void delete(ExchSVO vo) throws SysException, AppException {

		vo.setSts("P");
		vo.setStsDate(SysDateUtil.getCurrentDate());
		exchDao.update(vo);

	}

	public void deleteExchs(String[] exchIds) throws SysException, AppException {
		ExchSVO vo = new ExchSVO();
		WorkAreaExchDOM wdom = new WorkAreaExchDOM();
		WorkAreaExchSVO wsvo = new WorkAreaExchSVO();
		for (int i = 0; i < exchIds.length; i++) {
			wsvo.setExchId(exchIds[i]);
			wsvo.setSts(Constant.STS_IN_USE);
			List lsttmp = wdom.findByVO(wsvo);
			if (lsttmp == null || lsttmp.size() == 0) {

				vo.setExchId(exchIds[i]);
				this.delete(vo);
			} else
				throw new AppException("3210046", "请先删除局向号为" + exchIds[i]
						+ "的工作区与局向的对应关系!");

		}
		// 同步缓存
		try {
			DataCache.initTreeHashMap(DataCache.AREA_EXCH);
		} catch (DataCacheException e) {
			throw new SysException("222", "同步缓存area_exch错误", e);
		}
	}

	/**
	 *  注销
	 * @param exchIds
	 * @param logList
	 * @throws SysException
	 * @throws AppException
	 */
	public void unableExchs(String[] exchIds, List logList)
			throws SysException, AppException {
		ExchSVO vo = new ExchSVO();
		WorkAreaExchDOM wdom = new WorkAreaExchDOM();
		WorkAreaExchSVO wsvo = new WorkAreaExchSVO();
		for (int i = 0; i < exchIds.length; i++) {
			wsvo.setExchId(exchIds[i]);
			wsvo.setSts(Constant.STS_IN_USE);
			List lsttmp = wdom.findByVO(wsvo);
			if (lsttmp == null || lsttmp.size() == 0) {

				vo.setExchId(exchIds[i]);
				this.delete(vo);

			} else
				throw new AppException("3210046", "请先删除局向号为" + exchIds[i]
						+ "的工作区与局向的对应关系!");

		}
		//日志
		List list = null;
		if (logList != null && logList.size() > 0) {
			list = new ArrayList();
			for (int i = 0; i < logList.size(); i++) {
				ActionLogSVO actionLog = (ActionLogSVO) logList.get(i);
				actionLog.setActionId(MaxId
						.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
				list.add(actionLog);
			}
			IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
					.getDAO(IActionLogSDAO.class);
			actionLogDao.addBat(list);
		}
		// 同步缓存
		try {
			DataCache.initTreeHashMap(DataCache.AREA_EXCH);
		} catch (DataCacheException e) {
			throw new SysException("222", "同步缓存area_exch错误", e);
		}
	}

	/**
	 * 增加单表ExchSVO
	 * 
	 * @param vo
	 * @throws SysException
	 * @throws AppException
	 * @throws SQLException
	 * @throws DataCacheException
	 */
	public String add(ExchSVO vo) throws SysException, AppException,
			SQLException {
		ExchSVO evo = new ExchSVO();
		evo.setName(vo.getName());
		if (exchDao.findByVO(evo) != null) {
			throw new AppException("2222", "已经存在该局向");
		}
		Date curDate = SysDateUtil.getCurrentDate();
		vo.setSts(Constant.STS_IN_USE);
		vo.setStsDate(curDate);
		vo.setCreateDate(curDate);
		if (vo.getExchId() == null) {
			vo.setExchId(SMMaxId.getExchMaxId(vo.getLocalNetId()));
		}
		while (exchDao.findByPK(vo) != null) {
			vo.setExchId(SMMaxId.getExchMaxId(vo.getLocalNetId()));
		}
		exchDao.add(vo);

		ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		IActionLogSDAO actionLogDao = (IActionLogSDAO) com.cattsoft.pub.dao.DAOFactory
				.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);
		// 同步缓存
		try {
			DataCache.initTreeHashMap(DataCache.AREA_EXCH);
		} catch (DataCacheException e) {
			throw new SysException("222", "同步缓存area_exch错误", e);
		}
		return vo.getExchId();
	}

	/**
	 * 根据相应的条件分页查询单表ExchSVO
	 * 
	 * @param vo
	 * @return PagView(List<ExchSVO>)
	 * @throws SysException
	 * @throws AppException
	 */
	public PagView findByPage(PagInfo pagInfo, ExchSVO vo) throws SysException,
			AppException {
		return exchDao.findByPage(vo, pagInfo);
	}

	public PagView findExchMVOByPage(ExchSVO vo, HashSet set, PagInfo pagInfo)
			throws Exception {
		return exchDao.findExchMVOByPage(vo, set, pagInfo);
	}

}
