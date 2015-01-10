package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;


/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-27 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public interface IFuncNodeTreeMDAO extends IDAO {
    public List findFuncNodeTreeVOs(List fnvs) throws SysException, AppException;

}
