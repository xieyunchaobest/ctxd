package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.DataCacheException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IWorkTypeSDAO;
import com.cattsoft.sm.vo.WorkTypeSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class WorkTypeDOM {

    private Logger log = Logger.getLogger(WorkTypeDOM.class);

    private IWorkTypeSDAO workTypeDao = (IWorkTypeSDAO) DAOFactory.getInstance().getDAO(
            IWorkTypeSDAO.class);

    public void add(WorkTypeSVO vo) throws AppException, SysException {
        WorkTypeSVO rasvo = new WorkTypeSVO();
        if (workTypeDao.findByPK(vo) != null)
            throw new AppException("3210004", "±àºÅ´æÔÚ"); // ±àºÅ´æÔÚ
        rasvo.setName(vo.getName());
        List list = workTypeDao.findByVO(rasvo);
        if (list != null) {
            rasvo = (WorkTypeSVO) list.get(0);
            if (!vo.getWorkTypeId().equals(rasvo.getWorkTypeId())) {
                throw new AppException("3210040", "Ãû³ÆÖØ¸´");
            }
        }
        Date curDate = SysDateUtil.getCurrentDate();
        vo.setCreateDate(curDate);
        workTypeDao.add(vo);

    }

    public List findByVo(WorkTypeSVO vo) throws AppException, SysException {
        return workTypeDao.findByVO(vo);
    }

    public WorkTypeSVO findByPk(WorkTypeSVO vo) throws AppException, SysException {
        return (WorkTypeSVO) workTypeDao.findByPK(vo);
    }

    public void mod(WorkTypeSVO vo) throws AppException, SysException {
        WorkTypeSVO rasvo = new WorkTypeSVO();
        rasvo.setName(vo.getName());
        List list = workTypeDao.findByVO(rasvo);
        if (list != null) {
            rasvo = (WorkTypeSVO) list.get(0);
            if (!vo.getWorkTypeId().equals(rasvo.getWorkTypeId())) {
                throw new AppException("3210040", "Ãû³ÆÖØ¸´");
            }
        }
        workTypeDao.update(vo);
    }

    public void delete(WorkTypeSVO vo) throws AppException, SysException{
        workTypeDao.delete(vo);
    }

    public void delWorkType(String[] workTypeIds) throws AppException, SysException {
        WorkTypeSVO vo = new WorkTypeSVO();
        for (int i = 0; i < workTypeIds.length; i++) {
            vo.setWorkTypeId(workTypeIds[i]);
            this.delete(vo);
        }
    }

    public PagView findWorkTypesByPage(WorkTypeSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception {
        return workTypeDao.findWorkTypesByPage(vo, set, pagInfo);
    }

    public String getWorkTypeId(WorkTypeSVO vo) throws SysException, AppException, SQLException {
        return MaxId.getSequenceNextVal(Constant.SEQ_WORK_TYPE_ID);
    }

}
