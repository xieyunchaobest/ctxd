package com.cattsoft.sm.component.domain;

import java.util.List;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IOtherCharacterApplyRangeSDAO;
import com.cattsoft.sm.vo.OtherCharacterApplyRangeSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-19 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class OtherCharacterApplyRangeDOM {
	
    private IOtherCharacterApplyRangeSDAO characterApplyRangeDao = (IOtherCharacterApplyRangeSDAO) DAOFactory
            .getInstance().getDAO(IOtherCharacterApplyRangeSDAO.class);

    public List findOtherCharacterApplyRange(OtherCharacterApplyRangeSVO vo) throws AppException,
            SysException {
        return characterApplyRangeDao.findMVO(vo);
    }

    public OtherCharacterApplyRangeSVO getOtherCharacterApplyRange(OtherCharacterApplyRangeSVO vo)
            throws AppException, SysException {
        return (OtherCharacterApplyRangeSVO) characterApplyRangeDao.findByPK(vo);
    }

    public String addOtherCharacterApplyRange(OtherCharacterApplyRangeSVO vo) throws AppException,
            SysException {
        if (vo.getCharacterApplyRangeId() == null)
            vo.setCharacterApplyRangeId(MaxId
                    .getSequenceNextVal(Constant.SEQ_CHARACTER_APPLY_RANGE_ID));
        characterApplyRangeDao.add(vo);
        return vo.getCharacterApplyRangeId();
    }

    public void deleteOtherCharacterApplyRange(OtherCharacterApplyRangeSVO vo) throws AppException,
            SysException {
        characterApplyRangeDao.delete(vo);
    }

    public void updateOtherCharacterApplyRange(OtherCharacterApplyRangeSVO vo) throws AppException,
            SysException {
        characterApplyRangeDao.update(vo);
    }

    public void deleteBatchOtherCharacterApplyRange(List list)
            throws AppException, SysException {
        OtherCharacterApplyRangeSVO vo=null;
        for(int i=0;i<list.size();i++)
        {
            vo= (OtherCharacterApplyRangeSVO)list.get(i);
            this.deleteOtherCharacterApplyRange(vo);
        }

    }

    public void addBatchOtherCharacterApplyRange(List list) throws AppException,
            SysException {
        OtherCharacterApplyRangeSVO vo=null;
       for(int i=0;i<list.size();i++)
       {
           vo= (OtherCharacterApplyRangeSVO)list.get(i);
           this.addOtherCharacterApplyRange(vo);
       }
    }
}
