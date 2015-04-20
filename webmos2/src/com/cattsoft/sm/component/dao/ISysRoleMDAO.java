package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.SysRoleSVO;
import com.cattsoft.sm.vo.SysUserRoleSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-21 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */
public interface ISysRoleMDAO extends IDAO {
	

    public List findLatestSysRole(HashSet set) throws SysException, AppException, Exception;

    public List findSysRoleAllowAuth2(String sysUserId, String localNetId, String areaId,
            SysRoleSVO vo) throws SysException, AppException;

    public List findSysRoleAllowAuth3(String sysUserId, String localNetId, String areaId,
            SysRoleSVO vo) throws SysException, AppException;

    public List findSysUserRole(SysUserRoleSVO vo) throws SysException, AppException;

    public List findSysRoleMVO(SysRoleSVO vo) throws SysException, AppException;

    public List findSysRoleAllowAuth(SysRoleSVO vo)
            throws SysException, AppException;
    
    public List findSysRoleBySysUserId(SysRoleSVO vo, String sysUserId)
			throws SysException, AppException ;

}
