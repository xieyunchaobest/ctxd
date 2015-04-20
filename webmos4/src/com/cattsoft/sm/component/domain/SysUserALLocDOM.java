package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.ISysUserAllocMDAO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */
public class SysUserALLocDOM {
	
    private static Logger log = Logger.getLogger(SysUserALLocDOM.class);

    ISysUserAllocMDAO sysUserMDao = (ISysUserAllocMDAO) DAOFactory.getInstance().getDAO(
            ISysUserAllocMDAO.class);

    /**
     * 查询用户权限表
     * 
     * @param vo
     *            DataRangeSVO
     * @return List
     * @throws Exception
     */

    public List findFuncAllocBySysUserNotInSet(String sysUserId, String localNetId)
            throws SysException, AppException {

        List list = sysUserMDao.findFuncAllocBySysUserNotInSet(sysUserId, localNetId);
        return list;
    }
}
