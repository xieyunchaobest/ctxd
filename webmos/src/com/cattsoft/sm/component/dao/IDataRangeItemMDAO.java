package com.cattsoft.sm.component.dao;


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
public interface IDataRangeItemMDAO  extends IDAO  {
    public List findDataRangeItem(String rangeId ) throws SysException, AppException;

}
