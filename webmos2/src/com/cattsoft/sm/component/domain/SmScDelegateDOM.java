package com.cattsoft.sm.component.domain;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-8 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SmScDelegateDOM {
	
    private static Logger log = Logger.getLogger(SmScDelegateDOM.class);

    /**
     * 调用同步员工给计费 String workStaffSyn(String staffId, String officeId, String name, String passWord,
     * String regionId, String actType, Date createDate, Date expDate, Date effDate, String
     * operType)
     */
    public String findChannel(GenericVO vo) throws AppException, SysException {
//        ChannelDOM dom = new ChannelDOM();
//        return dom.initChannelTree(vo);
    	return null;
    }
}
