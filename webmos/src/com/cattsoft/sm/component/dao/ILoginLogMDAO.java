package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.vo.LoginLogMVO;


public interface ILoginLogMDAO extends IDAO{

    public PagView findLoginLogsByPage(LoginLogMVO vo, HashSet set, PagInfo pagInfo)
	throws Exception ;
    
    public List find(LoginLogMVO vo)  throws SysException, AppException;
}
