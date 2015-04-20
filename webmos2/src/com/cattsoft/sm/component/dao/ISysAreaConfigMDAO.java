package com.cattsoft.sm.component.dao;

import java.util.HashSet;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.SysAreaConfigMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-22 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public interface ISysAreaConfigMDAO extends IDAO {
	
    
    public PagView findSysAreaConfigsByPage(SysAreaConfigMVO vo, HashSet set, PagInfo pagInfo)
            throws Exception;

    public SysAreaConfigMVO findByPK(SysAreaConfigMVO mvo) throws AppException, SysException;
    
    public PagView findByLocalNet(GenericVO svo, String localNetId,
			PagInfo pagInfo) throws AppException, SysException;
}
