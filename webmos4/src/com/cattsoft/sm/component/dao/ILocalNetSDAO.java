package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;


public interface ILocalNetSDAO extends ISDAO {
    
    public List findBySet(HashSet set) throws Exception;
    public List findByVO(GenericVO vo, String staffId)throws AppException, SysException;
}