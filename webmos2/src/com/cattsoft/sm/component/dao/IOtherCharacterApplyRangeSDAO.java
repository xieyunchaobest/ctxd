package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;


public interface IOtherCharacterApplyRangeSDAO extends ISDAO {
    
    public List findMVO(GenericVO vo) throws AppException, SysException;
    
}