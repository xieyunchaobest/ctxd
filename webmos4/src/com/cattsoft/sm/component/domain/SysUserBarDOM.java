package com.cattsoft.sm.component.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IActionLogSDAO;
import com.cattsoft.sm.component.dao.ISysUserBarSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.SysUserBarMVO;
import com.cattsoft.sm.vo.SysUserBarSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-7 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SysUserBarDOM {
	
    private static Logger log = Logger.getLogger(SysUserBarDOM.class);

    private ISysUserBarSDAO sysUserBarSDao = (ISysUserBarSDAO) DAOFactory.getInstance().getDAO(
            ISysUserBarSDAO.class);

    public String addSysUserBar(SysUserBarSVO vo) throws SysException, AppException {
        List list = (List) sysUserBarSDao.findByVO(vo);
        SysUserBarSVO bvo=new SysUserBarSVO();
        if (list != null) {
            bvo = (SysUserBarSVO) list.get(0);
            if (bvo.getSts().equals(Constant.STS_IN_USE))
                return "1"; // 此快捷方式已经存在
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_HISTORY))
            vo.setSts(Constant.STS_IN_USE);
        if (vo.getSysUserBarId() == null) 
            vo.setSysUserBarId(MaxId.getSequenceNextVal(Constant.SEQ_SYS_USER_BAR_ID));
        sysUserBarSDao.add(vo);
        return "0"; // 成功
    }

    public List findSysUserBar(SysUserBarSVO vo) throws SysException, AppException {
        return sysUserBarSDao.findSysUserBarMvo(vo);

    }

    public PagView findSysUserBarMVOByPage(SysUserBarMVO vo, HashSet set, PagInfo pagInfo)
            throws Exception {
        return sysUserBarSDao.findSysUserBarMVOsByPage(vo, set, pagInfo);
    }
    
    public List findSysUserBarMVOByVO(SysUserBarMVO vo)
    throws Exception {
        return sysUserBarSDao.findSysUserBarMvo(vo);
}

    public void addSysUserBars(List sysUserBarList,List logList) throws SysException, AppException {
        SysUserBarSVO vo = new SysUserBarSVO();
        Iterator it = sysUserBarList.iterator();
        while (it.hasNext()) {

            vo = (SysUserBarSVO) it.next();
            this.addSysUserBar(vo);
        }
        //记录操作日志
        List actionLogList=null;
        if(logList!=null&&logList.size()>0){
        	actionLogList=new ArrayList();
        	for(int i=0;i<logList.size();i++){
        		SysUserBarSVO bar=(SysUserBarSVO)sysUserBarList.get(i);
                //记录操作日志
                ActionLogSVO actionLog=(ActionLogSVO) logList.get(i);
                actionLog.setActionText(bar.getSysUserBarId()+actionLog.getActionText());
                actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                actionLogList.add(actionLog);
        	}
        	IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
            actionLogDao.addBat(actionLogList);
        }
    }

    public void deleteSysUserBar(SysUserBarSVO vo) throws SysException, AppException {
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_IN_USE)) {
            vo.setSts(Constant.STS_HISTORY);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        sysUserBarSDao.update(vo);
    }
    public void restartSysUserBar(SysUserBarSVO vo) throws SysException, AppException {
        if (vo.getSts() == null || vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts(Constant.STS_IN_USE);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        sysUserBarSDao.update(vo);
    }
    
    public void deleteSysUserBars(List sysUserBarList,List logList) throws SysException, AppException {
        SysUserBarSVO vo = new SysUserBarSVO();
        Iterator it = sysUserBarList.iterator();
        while (it.hasNext()) {

            vo = (SysUserBarSVO) it.next();
            this.deleteSysUserBar(vo);
        }
        //记录操作日志
        List actionLogList=null;
        if(logList!=null&&logList.size()>0){
        	actionLogList=new ArrayList();
        	for(int i=0;i<logList.size();i++){
                //记录操作日志
                ActionLogSVO actionLog=(ActionLogSVO) logList.get(i);
                actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                actionLogList.add(actionLog);
        	}
        	IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
            actionLogDao.addBat(actionLogList);
        }
    }
    
    public void restartSysUserBars(List sysUserBarList,List logList) throws SysException, AppException {
        SysUserBarSVO vo = new SysUserBarSVO();
        Iterator it = sysUserBarList.iterator();
        while (it.hasNext()) {

            vo = (SysUserBarSVO) it.next();
            this.restartSysUserBar(vo);
        }
        //记录操作日志
        List actionLogList=null;
        if(logList!=null&&logList.size()>0){
        	actionLogList=new ArrayList();
        	for(int i=0;i<logList.size();i++){
                //记录操作日志
                ActionLogSVO actionLog=(ActionLogSVO) logList.get(i);
                actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
                actionLogList.add(actionLog);
        	}
        	IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
            actionLogDao.addBat(actionLogList);
        }
    }
}
