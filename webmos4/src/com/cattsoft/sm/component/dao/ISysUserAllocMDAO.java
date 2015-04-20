package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.SysUserAllocSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */
public interface ISysUserAllocMDAO extends IDAO {
	
	public List findBySysRoleId(SysUserAllocSVO suavo) throws SysException, AppException;

    public List findFuncAllocBySysUserNotInSet(String sysUserId, String localNetId)
            throws SysException, AppException;

    public List findSysUserByFuncNodeAndSysUser(SysUserAllocSVO vo) throws SysException,
            AppException;

    public void addBySysRole(SysUserAllocSVO suavo) throws SysException, AppException;

    public void delBySysRole(SysUserAllocSVO suavo) throws SysException, AppException;

    public void addByFuncNode(SysUserAllocSVO suavo) throws SysException, AppException;

    public void delBySysRoleAndSysUser(SysUserAllocSVO suavo) throws SysException, AppException;

    public void addBySysRoleAndSysUser(SysUserAllocSVO suavo) throws SysException, AppException;    

}
