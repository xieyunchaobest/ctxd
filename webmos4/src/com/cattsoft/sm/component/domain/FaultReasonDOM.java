package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IFaultReasonSDAO;
import com.cattsoft.sm.vo.FaultReasonSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-9 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class FaultReasonDOM {

    private IFaultReasonSDAO faultReasonDao = (IFaultReasonSDAO) DAOFactory.getInstance().getDAO(
            IFaultReasonSDAO.class);

    private static Logger log = Logger.getLogger(FaultReasonDOM.class);

    /**
     * 根据主键查询单表FaultReasonSVO
     * 
     * @param vo
     * @return FaultReasonSVO
     * @throws SysException
     * @throws AppException
     */
    public FaultReasonSVO findByPk(FaultReasonSVO vo) throws SysException, AppException {
        return (FaultReasonSVO) faultReasonDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表FaultReasonSVO
     * 
     * @param vo
     * @return List<FaultReasonSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(FaultReasonSVO vo) throws SysException, AppException {
        return faultReasonDao.findByVO(vo);
    }

    /**
     * 修改单表FaultReasonSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(FaultReasonSVO vo) throws SysException, AppException {
        vo.setStsDate(SysDateUtil.getCurrentDate());
        faultReasonDao.update(vo);
    }

    /**
     * 删除单表FaultReasonSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(FaultReasonSVO vo) throws SysException, AppException {
        if (vo.getSts()==null||!vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts("P");
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        faultReasonDao.update(vo);

    }
    public void deleteFaultReasons(String[] reasonIds) throws SysException,AppException{
        FaultReasonSVO vo=new FaultReasonSVO();
        for(int i=0;i<reasonIds.length;i++){
            vo.setReasonId(reasonIds[i]);
            this.delete(vo);
        }
    }

    /**
     * 增加单表FaultReasonSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public String add(FaultReasonSVO vo) throws SysException, AppException {
        if(vo.getReasonId()==null){
            vo.setReasonId(MaxId.getSequenceNextVal(Constant.SEQ_REASON_ID));
        }
        Date curDate=SysDateUtil.getCurrentDate();
        vo.setSts(Constant.STS_IN_USE);
        vo.setCreateDate(curDate);
        vo.setStsDate(curDate);
        faultReasonDao.add(vo);
        return vo.getReasonId();
    }
   public PagView findFaultReasonsByPage(FaultReasonSVO vo,HashSet set,PagInfo pagInfo) throws Exception{
       return faultReasonDao.findFaultReasonsByPage(vo, set, pagInfo);
   }
   public String getFaultReasonId() throws AppException,SysException{
       return MaxId.getSequenceNextVal(Constant.SEQ_REASON_ID);
   }
}
