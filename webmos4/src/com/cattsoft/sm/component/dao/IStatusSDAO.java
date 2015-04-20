package com.cattsoft.sm.component.dao;

import java.util.HashSet;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.StatusSVO;

public interface IStatusSDAO extends ISDAO {
    public PagView findStatusByPage(StatusSVO vo, HashSet set, PagInfo pagInfo) throws Exception;
    
    public void updateStatus(GenericVO vo) throws AppException, SysException;
}