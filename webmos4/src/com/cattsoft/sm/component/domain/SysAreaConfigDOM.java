package com.cattsoft.sm.component.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.MaxId;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.ISysAreaConfigMDAO;
import com.cattsoft.sm.component.dao.ISysAreaConfigSDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.SysAreaConfigMVO;
import com.cattsoft.sm.vo.SysAreaConfigSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-22 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysAreaConfigDOM {

    private ISysAreaConfigSDAO sysAreaConfigDao = (ISysAreaConfigSDAO) DAOFactory.getInstance()
            .getDAO(ISysAreaConfigSDAO.class);

    private ISysAreaConfigMDAO sysAreaConfigMDao = (ISysAreaConfigMDAO) DAOFactory.getInstance()
            .getDAO(ISysAreaConfigMDAO.class);

    /**
     * 根据主键查询多表SysAreaConfigMVO
     * 
     * @param vo
     * @return SysAreaConfigSVO
     * @throws SysException
     * @throws AppException
     */
    public SysAreaConfigMVO findSysAreaConfigByPK(SysAreaConfigMVO mvo) throws AppException,
            SysException {
        return sysAreaConfigMDao.findByPK(mvo);

    }

    /**
     * 根据相应的条件查询单表SysAreaConfigSVO
     * 
     * @param vo
     * @return List<SysAreaConfigSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(SysAreaConfigSVO vo) throws SysException, AppException {
        return sysAreaConfigDao.findByVO(vo);
    }

    /**
     * 修改单表SysAreaConfigSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(SysAreaConfigSVO vo) throws SysException, AppException {
        SysAreaConfigSVO svo = new SysAreaConfigSVO();

        svo.setConfigId(vo.getConfigId());
        svo.setSpAreaId(vo.getSpAreaId());
        List alst = sysAreaConfigDao.findByVO(svo);
        if (alst != null && alst.size() > 0) {
            svo = (SysAreaConfigSVO) alst.get(0);
            if (!svo.getSysAreaConfigId().equals(vo.getSysAreaConfigId())) {
                throw new AppException("3333", "区域重复!");
            }
        }

        sysAreaConfigDao.update(vo);
        
        ActionLogSVO actionLog=vo.getActionLog();
        actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);
    }

    /**
     * 删除单表SysAreaConfigSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(SysAreaConfigSVO vo) throws SysException, AppException {
        sysAreaConfigDao.delete(vo);

    }

    /**
     * 多删
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void deleteSysAreaConfigs(String[] sysAreaConfigIds,List logList) throws SysException, AppException {
        SysAreaConfigSVO vo = new SysAreaConfigSVO();
        List list=new ArrayList();
        for (int i = 0; i < sysAreaConfigIds.length; i++) {
            vo.setSysAreaConfigId(sysAreaConfigIds[i]);
            
            ActionLogSVO svo=(ActionLogSVO)logList.get(i);
            svo.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
            list.add(svo);
            
            this.delete(vo);
        }
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.addBat(list);
    }

    /**
     * 增加单表SysAreaConfigSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     */
    public String add(SysAreaConfigSVO vo) throws SysException, AppException, SQLException {

        SysAreaConfigSVO svo = new SysAreaConfigSVO();

        svo.setConfigId(vo.getConfigId());
        svo.setSpAreaId(vo.getSpAreaId());
        List alst = sysAreaConfigDao.findByVO(svo);
        if (alst != null && alst.size() > 0) {
            throw new AppException("3333", "区域重复!");
        }

        while (vo.getSysAreaConfigId() == null || sysAreaConfigDao.findByPK(vo) != null) {
            vo.setSysAreaConfigId(SMMaxId.getSysAreaConfigMaxId(vo.getConfigId()));

        }
        sysAreaConfigDao.add(vo);
        //记录当前所做的操作
        ActionLogSVO actionLog=vo.getActionLog();
        actionLog.setActionText(vo.getSysAreaConfigId()+actionLog.getActionText());
        actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
		actionLogDao.add(actionLog);
        return vo.getSysAreaConfigId();

    }

    public PagView findSysAreaConfigsByPage(SysAreaConfigMVO vo, HashSet set, PagInfo pagInfo,
            String localNetId) throws Exception {
    	if(null!=localNetId&&set==null){
    		return sysAreaConfigMDao.findByLocalNet(vo, localNetId, pagInfo);
    	}
        return sysAreaConfigMDao.findSysAreaConfigsByPage(vo, set, pagInfo);
    }

}
