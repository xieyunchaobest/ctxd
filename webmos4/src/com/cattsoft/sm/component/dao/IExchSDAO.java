package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.ExchSVO;

public interface IExchSDAO extends ISDAO {
	
    public PagView findByPage(GenericVO vo, PagInfo pagInfo) throws AppException, SysException;
    
    public PagView findExchMVOByPage(ExchSVO vo, HashSet set, PagInfo pagInfo) throws Exception;
    
    public List findExchListByWorkArea(String workAreaId) throws AppException, SysException;
}