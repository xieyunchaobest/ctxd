package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.MaxId;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.util.DataCache;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IAreaSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.AreaSVO;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * 
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-5 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class AreaDOM {
    private Logger log = Logger.getLogger(AreaDOM.class);   

    public void add(AreaSVO vo) throws AppException, SysException {
    	IAreaSDAO areaDao = (IAreaSDAO) DAOFactory.getDAO(IAreaSDAO.class);
        AreaSVO rasvo = new AreaSVO();
        if (areaDao.findByPK(vo) != null)
            throw new AppException("3210004", "编号存在"); // 编号存在
        rasvo.setName(vo.getName());
        List list = areaDao.findByVO(rasvo);
        if (list != null) {
            rasvo = (AreaSVO) list.get(0);
            if (!vo.getAreaId().equals(rasvo.getAreaId())) {
                throw new AppException("3210040", "名称重复");
            }
        }
        Date curDate = SysDateUtil.getCurrentDate();
        vo.setSts(Constant.STS_IN_USE);
        vo.setStsDate(curDate);
        vo.setCreateDate(curDate);
        areaDao.add(vo);
        ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory
				.getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.AREA);
            DataCache.initTreeHashMap(DataCache.AREA_EXCH);
            DataCache.initTreeHashMap(DataCache.LOCAL_NET_AREA);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }

    }

    public List findByVo(AreaSVO vo) throws AppException, SysException {
    	IAreaSDAO areaDao = (IAreaSDAO) DAOFactory.getDAO(IAreaSDAO.class);
        return areaDao.findByVO(vo);
    }

    public AreaSVO findByPk(AreaSVO vo) throws AppException, SysException {
    	IAreaSDAO areaDao = (IAreaSDAO) DAOFactory.getDAO(IAreaSDAO.class);
        return (AreaSVO) areaDao.findByPK(vo);
    }

    public void mod(AreaSVO vo) throws AppException, SysException{
    	IAreaSDAO areaDao = (IAreaSDAO) DAOFactory.getDAO(IAreaSDAO.class);
        AreaSVO rasvo = new AreaSVO();
        rasvo.setName(vo.getName());
        List list = areaDao.findByVO(rasvo);
        if (list != null) {
            rasvo = (AreaSVO) list.get(0);
            if (!vo.getAreaId().equals(rasvo.getAreaId())) {
                throw new AppException("3210040", "名称重复");
            }
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        areaDao.update(vo);
        ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory
				.getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.AREA);
            DataCache.initTreeHashMap(DataCache.AREA_EXCH);
            DataCache.initTreeHashMap(DataCache.LOCAL_NET_AREA);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }
       
    }

    public void delete(AreaSVO vo) throws AppException, SysException{
    	IAreaSDAO areaDao = (IAreaSDAO) DAOFactory.getDAO(IAreaSDAO.class);
        if (vo.getSts() == null || !vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts(Constant.STS_HISTORY);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        areaDao.delete(vo);
        ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory.getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
        
       
    }

    public void delAreas(List list) throws AppException, SysException{
        AreaSVO vo = new AreaSVO();
        if (list != null && list.size() > 0) {			
			for (int i = 0; i < list.size(); i++) {
				vo = (AreaSVO) list.get(i);				
				this.delete(vo);
			}		
			
		}
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.AREA);
            DataCache.initTreeHashMap(DataCache.AREA_EXCH);
            DataCache.initTreeHashMap(DataCache.LOCAL_NET_AREA);
            DataCache.initTreeHashMap(DataCache.AREA_SERV_DEPT);
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }
    }

    public PagView findAreasByPage(AreaSVO vo, HashSet set, PagInfo pagInfo) throws Exception {
    	IAreaSDAO areaDao = (IAreaSDAO) DAOFactory.getDAO(IAreaSDAO.class);
    	return areaDao.findAreasByPage(vo, set, pagInfo);
    }

    public String getAreaId(AreaSVO vo) throws SysException, AppException, SQLException {
        return SMMaxId.getAreaMaxId(vo.getLocalNetId());// 怎样加安全限制???
    }

}
