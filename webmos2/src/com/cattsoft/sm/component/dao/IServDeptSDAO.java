package com.cattsoft.sm.component.dao;

import java.util.HashSet;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.ServDeptSVO;

public interface IServDeptSDAO extends ISDAO {
    public ServDeptSVO findByName(GenericVO vo) throws AppException, SysException;

    public PagView findServDeptsByPage(ServDeptSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception;
}