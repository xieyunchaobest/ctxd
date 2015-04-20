package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.SysUserBarMVO;

public interface ISysUserBarSDAO extends ISDAO {
	
    public List findSysUserBarMvo(GenericVO vo) throws AppException, SysException;

    public PagView findSysUserBarMVOsByPage(SysUserBarMVO vo, HashSet set, PagInfo pagInfo)
            throws Exception;
}