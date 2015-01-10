package com.cattsoft.sm.component.domain;

import java.sql.Timestamp;
import java.util.List;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IAreaCharacSDAO;
import com.cattsoft.sm.component.dao.IOtherEnumValueSDAO;
import com.cattsoft.sm.vo.AreaCharacSVO;
import com.cattsoft.sm.vo.OtherEnumValueSVO;
import com.cattsoft.webpub.util.SysDateUtil;



/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class AreaCharacDOM {

    private IAreaCharacSDAO areaChDao = (IAreaCharacSDAO) DAOFactory.getInstance().getDAO(
            IAreaCharacSDAO.class);
    private IOtherEnumValueSDAO enumValueDao = (IOtherEnumValueSDAO) DAOFactory.getInstance().getDAO(
            IOtherEnumValueSDAO.class);
    

    public List findAreaCharac(AreaCharacSVO vo) throws AppException, SysException {
        return areaChDao.findByVO(vo);
    }

    public AreaCharacSVO getAreaCharac(AreaCharacSVO vo) throws AppException, SysException {
        return (AreaCharacSVO) areaChDao.findByPK(vo);
    }

    public String addAreaCharac(AreaCharacSVO vo) throws AppException, SysException {
        if (vo.getAreaCharacId() == null)
            vo.setAreaCharacId(MaxId.getSequenceNextVal(Constant.SEQ_AREA_CHARAC_ID));
        areaChDao.add(vo);
        return vo.getAreaCharacId();
    }

    public void deleteAreaCharac(AreaCharacSVO vo) throws AppException, SysException {
        areaChDao.delete(vo);
    }

    public void updateAreaCharac(AreaCharacSVO vo) throws AppException, SysException {
        areaChDao.update(vo);
    }

    public void deleteBatchAreaCharac(String[] areaCharacIds) throws AppException, SysException {
        AreaCharacSVO vo = new AreaCharacSVO();
        for (int i = 0, size = areaCharacIds.length; i < size; i++) {
            vo.setAreaCharacId(areaCharacIds[i]);
            areaChDao.delete(vo);
        }
    }

    public void addBatchAreaCharac(List list) throws AppException, SysException {
        AreaCharacSVO vo = null;
        OtherEnumValueSVO evo=new OtherEnumValueSVO();
        Timestamp date=SysDateUtil.getCurrentTimestamp();
        for (int i = 0; i < list.size(); i++) {
            vo = (AreaCharacSVO) list.get(i);
            evo.setEnumValueId(vo.getValue());
            evo=(OtherEnumValueSVO)enumValueDao.findByPK(evo);
            vo.setValue(evo.getEnumValueCode());
            vo.setStsDate(date);
          
            this.addAreaCharac(vo);
        }
    }
    public List findAreaCharacMVO(AreaCharacSVO vo) throws AppException, SysException {
        return areaChDao.findByMVO(vo);
    }


}
