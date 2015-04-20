package com.cattsoft.sm.component.dao;

import java.util.Set;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;

public interface IOtherEnumValueSDAO extends ISDAO {
    
	
    public PagView findEnumValueMVO(GenericVO vo, PagInfo pagInfo,Set set) throws AppException,
    SysException ;
}