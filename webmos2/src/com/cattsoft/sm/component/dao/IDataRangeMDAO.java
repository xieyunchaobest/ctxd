package com.cattsoft.sm.component.dao;


import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */
public interface IDataRangeMDAO extends IDAO {

    public List findDataRangesBySet(HashSet set)throws SysException, AppException, Exception;

}
