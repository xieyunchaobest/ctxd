package com.cattsoft.sm.component.dao;

import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author sky_star
 */
public interface IPartyRelMDAO extends IDAO {
	
    public LabelValueBean findParentCust(String partyId2) throws SysException, AppException;
}
