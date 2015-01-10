package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.MaxId;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.connection.ConnectionFactory;
import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DataCache;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.sm.component.dao.ILocalNetSDAO;
import com.cattsoft.sm.component.dao.IUserDataRangeMDAO;
import com.cattsoft.sm.vo.LocalNetSVO;
import com.cattsoft.sm.vo.SysUserExtendedMVO;
import com.cattsoft.sm.vo.UserDataRangeMVO;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-5 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class LocalNetDOM {

    private Logger log = Logger.getLogger(LocalNetDOM.class);    

    public void add(LocalNetSVO vo) throws AppException, SysException, DataCacheException {
    	ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getDAO(ILocalNetSDAO.class);
    	LocalNetSVO rasvo = new LocalNetSVO();
        if (localNetDao.findByPK(vo) != null)
            throw new AppException("3210004", "编号存在"); // 编号存在
        rasvo.setName(vo.getName());
        List list = localNetDao.findByVO(rasvo);
        if (list != null) {
            rasvo = (LocalNetSVO) list.get(0);
            if (!vo.getLocalNetId().equals(rasvo.getLocalNetId())) {
                throw new AppException("3210040", "名称重复");
            }
        }
        Date curDate = SysDateUtil.getCurrentDate();
        vo.setSts(Constant.STS_IN_USE);
        vo.setStsDate(curDate);
        vo.setCreateDate(curDate);
        localNetDao.add(vo);
        ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(vo.getLocalNetId() + actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory
				.getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.LOCAL_NET);
          
            DataCache.initTreeHashMap(DataCache.LOCAL_NET_AREA);
           
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }

    }

    public List findByVo(LocalNetSVO vo) throws AppException, SysException {
    	ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getDAO(ILocalNetSDAO.class);
        return localNetDao.findByVO(vo);
    }

    public LocalNetSVO findByPk(LocalNetSVO vo) throws AppException, SysException {
    	ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getDAO(ILocalNetSDAO.class);
    	return (LocalNetSVO) localNetDao.findByPK(vo);
    }

    public void mod(LocalNetSVO vo) throws AppException, SysException {
    	ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getDAO(ILocalNetSDAO.class);
        LocalNetSVO rasvo = new LocalNetSVO();
        rasvo.setName(vo.getName());
        List list = localNetDao.findByVO(rasvo);
        if (list != null) {
            rasvo = (LocalNetSVO) list.get(0);
            if (!vo.getLocalNetId().equals(rasvo.getLocalNetId())) {
                throw new AppException("3210040", "名称重复");
            }
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        localNetDao.update(vo);
        ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(vo.getLocalNetId() + actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory
				.getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.LOCAL_NET);
          
            DataCache.initTreeHashMap(DataCache.LOCAL_NET_AREA);
           
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }


    }

    public void delete(LocalNetSVO vo) throws AppException, SysException {
    	ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getDAO(ILocalNetSDAO.class);
        if (vo.getSts()==null||!vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts(Constant.STS_HISTORY);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        localNetDao.delete(vo);
        ActionLogSVO actionLog = vo.getActionLog();
		actionLog.setActionId(MaxId
				.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
		actionLog.setActionText(vo.getLocalNetId() + actionLog.getActionText());
		IActionLogSDAO sDao = (IActionLogSDAO) DAOFactory
				.getDAO(IActionLogSDAO.class);
		sDao.add(actionLog);
    }

    public void delLocalNets(List list) throws AppException, SysException {
        
        if (list == null)
			throw new AppException("", "注销集合对象为null");	
		
		if (list != null && list.size() > 0) {			
			for (int i = 0; i < list.size(); i++) {
				LocalNetSVO lsvo = (LocalNetSVO) list.get(i);				
				this.delete(lsvo);
			}			
			
		}
        
        // 同步缓存
        try {
            DataCache.initHashMaps(DataCache.LOCAL_NET);
          
            DataCache.initTreeHashMap(DataCache.LOCAL_NET_AREA);
           
        } catch (DataCacheException e) {
            throw new SysException("222","同步缓存错误",e);
        }


    }

    public List findLocalNetsBySet(HashSet set) throws Exception {
    	ILocalNetSDAO localNetDao = (ILocalNetSDAO) DAOFactory.getDAO(ILocalNetSDAO.class);
        return localNetDao.findBySet(set);
    }

    /**
	 * 根据用户ID，查询用户权限，本地网列表
	 * @param jsonUserInfo封装了用户信息
	 * @param rangeType
	 * @param allowFlag
	 * @param localNetId
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public String getDataRangeByRight4MOS(String jsonUserInfo)
			throws SysException, AppException {
		List resList = null;
				
//			JSONObject userInfojsonObject = JSONObject.fromObject(jsonUserInfo);
		//将传送过来的json字符串转化成SysUserExtendedMVO对象
			SysUserExtendedMVO sysUserExtendedMVO = com.alibaba.fastjson.JSON.parseObject(jsonUserInfo,SysUserExtendedMVO.class);
			UserDataRangeMVO vo = new UserDataRangeMVO();
			if (sysUserExtendedMVO != null
					&& sysUserExtendedMVO.getSysUserSVO() != null
					&& !StringUtil.isBlank(sysUserExtendedMVO.getSysUserSVO()
							.getSysUserId())) {
				vo.setSysUserId(sysUserExtendedMVO.getSysUserSVO()
						.getSysUserId());
			}
			vo.setRangeTypeId(SysConstants.AREA_TYPE_LOCAL_NET);
			vo.setAllowFlag(SysConstants.ALL0W_FLAG_QUERY);
			
			IUserDataRangeMDAO dao = (IUserDataRangeMDAO) DAOFactory
					.getDAO(IUserDataRangeMDAO.class);
			resList = dao.findDataRange(vo);
			List beanList = new ArrayList();
			if (resList != null && resList.size() > 0) {
				Iterator it = resList.iterator();
				while (it.hasNext()) {
					/*
					 * if (beanList == null) { beanList = new ArrayList(); }
					 */
					UserDataRangeMVO uservo = (UserDataRangeMVO) it.next();
					if (!SysConstants.SYS_LOCAL_NET_ID.equals(uservo.getDataRangeId())) {
						// 不显示系统本地网 HBLTIOM-2002
						LabelValueBean lvBean = new LabelValueBean();
						lvBean.setValue(uservo.getDataRangeId());
						lvBean.setLabel(uservo.getAreaName());
						beanList.add(lvBean);
					}

				}
			}
			JSONArray jsonArray1 = JSONArray.fromObject(beanList);
		return jsonArray1.toString();
	}
}
