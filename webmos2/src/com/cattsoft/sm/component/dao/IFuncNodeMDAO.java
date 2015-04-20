package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;


public interface IFuncNodeMDAO extends IDAO {
	public List findFuncNodeForLogin4MOS(String sysUserId, String localNetId)
	throws SysException, AppException ;
	
	public List findFuncNodeForLogin(String sysUserId, String localNetId) throws SysException,
	AppException;
	
	public List findFuncNodeForOssLogin(String sysUserId, String localNetId) throws SysException,
	AppException;

	public List findFuncNodeForMenu(String sysUserId, String subSystemName) throws SysException,
	AppException;

	public List findFuncAllocBySysRoleNotInSet(String id) throws SysException, AppException;

	public List findFuncNodeVOsBySysUserAuthNew(String sysUserId, String funcNodeId,
			String funcNodeType, String nodeTreeId, String type) throws SysException, AppException;

	public PagView findFuncNodeForLogin(String sysUserId, PagInfo pagInfo) throws SysException,
	AppException;
}
