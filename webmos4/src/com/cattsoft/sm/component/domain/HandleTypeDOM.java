package com.cattsoft.sm.component.domain;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.cattsoft.sm.component.dao.IHandleTypeSDAO;
import com.cattsoft.sm.vo.ActionLogSVO;
import com.cattsoft.sm.vo.HandleTypeSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class HandleTypeDOM {
	

    private IHandleTypeSDAO handleTypeDao = (IHandleTypeSDAO) DAOFactory.getInstance().getDAO(
            IHandleTypeSDAO.class);

    private static Logger log = Logger.getLogger(HandleTypeDOM.class);

    /**
     * 根据主键查询单表HandleTypeSVO
     * 
     * @param vo
     * @return HandleTypeSVO
     * @throws SysException
     * @throws AppException
     */
    public HandleTypeSVO findByPk(HandleTypeSVO vo) throws SysException, AppException {
        return (HandleTypeSVO) handleTypeDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表HandleTypeSVO
     * 
     * @param vo
     * @return List<HandleTypeSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(HandleTypeSVO vo) throws SysException, AppException {
        return handleTypeDao.findByVO(vo);
    }

    /**
     * 修改单表HandleTypeSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(HandleTypeSVO vo) throws SysException, AppException {
        HandleTypeSVO svo = new HandleTypeSVO();
        svo.setName(vo.getName());
        if (handleTypeDao.findByName(svo) != null
                && !handleTypeDao.findByName(svo).getHandleTypeId().equals(vo.getHandleTypeId())) {
            throw new AppException("333", "名称重复!");
        }
        handleTypeDao.update(vo);
        //记录操作日志
        ActionLogSVO actionLog=vo.getActionLog();
        actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.add(actionLog);
    }

    /**
     * 删除单表HandleTypeSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(HandleTypeSVO vo) throws SysException, AppException {

        handleTypeDao.delete(vo);

    }

    public void deleteHandleTypes(String[] handleTypeIds,List logList) throws SysException, AppException {
        List list=new ArrayList();
    	HandleTypeSVO vo = new HandleTypeSVO();
        for (int i = 0; i < handleTypeIds.length; i++) {
            vo.setHandleTypeId(handleTypeIds[i]);
            //记录操作日志
            ActionLogSVO actionLog=(ActionLogSVO) logList.get(i);
            actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
            list.add(actionLog);
            
            this.delete(vo);
        }
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.addBat(list);
    }

    /**
     * 增加单表HandleTypeSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public String add(HandleTypeSVO vo) throws SysException, AppException {
        if (vo.getHandleTypeId() == null) {
            vo.setHandleTypeId(MaxId.getSequenceNextVal(Constant.SEQ_HANDLE_TYPE_ID));
        }
        HandleTypeSVO svo = new HandleTypeSVO();
        svo.setName(vo.getName());
        if (handleTypeDao.findByName(svo) != null) {
            throw new AppException("333", "名称重复!");
        }
        vo.setCreateDate(SysDateUtil.getCurrentDate());
        handleTypeDao.add(vo);
        //记录操作日志
        ActionLogSVO actionLog=vo.getActionLog();
        actionLog.setActionId(MaxId.getSequenceNextVal(SysConstants.ACTION_LOG_SEQ));
        actionLog.setActionText(vo.getHandleTypeId()+":"+vo.getName()+":增加");
        IActionLogSDAO actionLogDao=(IActionLogSDAO)com.cattsoft.pub.dao.DAOFactory.getDAO(IActionLogSDAO.class);
        actionLogDao.add(actionLog);
        
        return vo.getHandleTypeId();
    }

    public PagView findHandelTypesByPage(HandleTypeSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception {
        return handleTypeDao.findHandleTypesByPage(vo, set, pagInfo);
    }

    public String getHandleTypeId() throws AppException, SysException {

        return MaxId.getSequenceNextVal(Constant.SEQ_HANDLE_TYPE_ID);
    }

    public HandleTypeSVO findHandleTypeByName(HandleTypeSVO vo) throws AppException, SysException {

        return handleTypeDao.findByName(vo);
    }

}
