package com.cattsoft.sm.component.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.util.SysDate;
import com.cattsoft.sm.component.dao.ILoginLogMDAO;
import com.cattsoft.sm.component.dao.ILoginLogSDAO;
import com.cattsoft.sm.vo.LoginLogMVO;
import com.cattsoft.sm.vo.LoginLogSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-7 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class LoginLogDOM {
	
    private static Logger log = Logger.getLogger(LoginLogDOM.class);

    private ILoginLogSDAO loginLogSDao = (ILoginLogSDAO) DAOFactory.getInstance().getDAO(
            ILoginLogSDAO.class);
    private ILoginLogMDAO loginLogMDao = (ILoginLogMDAO) DAOFactory.getInstance().getDAO(ILoginLogMDAO.class);

    /**
     * 登出系统，修改登录日志信息
     * 
     * @param ovo
     *            LoginLogSVO
     * @throws SysException
     * @throws AppException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void updateLoginLog(LoginLogSVO vo) throws AppException, SysException {
        LoginLogSVO loginlog = (LoginLogSVO) loginLogSDao.findByPK(vo);
        if (loginlog == null) {
            return;
        }
        loginlog.setLogoutTime(SysDateUtil.getCurrentTimestamp());
        loginLogSDao.update(loginlog);

    }
    public PagView findLoginLogMVOByPage(LoginLogMVO vo, HashSet set, PagInfo pagInfo) throws Exception{
        return loginLogMDao.findLoginLogsByPage(vo, set, pagInfo);
    }
    
    public List findLoginLogMVO(LoginLogMVO vo,String localNetId) throws Exception{
        vo.getLl().setLocalNetId(localNetId);
        return loginLogSDao.findByMVO(vo);
    }
    
    public List find(LoginLogMVO vo) throws AppException, SysException {
        return loginLogMDao.find(vo);
    }
}
