package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.*;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.LoginLogMVO;


public interface ILoginLogSDAO extends ISDAO {
    
    public PagView findLoginLogsByPage(LoginLogMVO vo, HashSet set, PagInfo pagInfo) throws Exception;
    
    public List findByMVO(GenericVO vo) throws AppException, SysException ;
}