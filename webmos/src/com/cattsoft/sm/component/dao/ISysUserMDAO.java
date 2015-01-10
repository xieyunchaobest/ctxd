package com.cattsoft.sm.component.dao;

import java.util.List;
import java.util.Map;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.ConstraintAndPriviledgeSVO;
import com.cattsoft.sm.vo.SysUserAllocSVO;
import com.cattsoft.sm.vo.SysUserExtMVO;
import com.cattsoft.sm.vo.SysUserMVO;

public interface ISysUserMDAO extends IDAO {
	
    public SysUserMVO findSysUserByNamePwd(SysUserMVO vo);

    public List findSysUsers(SysUserMVO vo) throws SysException, AppException;

    public int findSysUsersSts(String sysUserName) throws SysException, AppException;

    public PagView findSysUsersBySysUserForPage(SysUserExtMVO voExt, PagInfo pagInfo) throws SysException,
            AppException;
    
    public  List findSysUserAllocListBySysUserId(SysUserAllocSVO vo) throws SysException,
    AppException;
    
    public List  findSysRoleAllocListBySysUserId(SysUserAllocSVO vo) throws SysException,
    AppException;
    
    public List findConstraintAndPriviledgeListBySysUserId(ConstraintAndPriviledgeSVO vo) throws SysException,
    AppException;
    /**
	 * 获得领单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public String getFetchOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException ;
	/**
	 * 获得回单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getReturnOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException ;
	/**
	 * 获得追单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getZhuiDanOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException;
	/**
	 * 获得退单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getBackOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException ;
	/**
	 * 获得催单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getPressOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException;
	/**
	 * 获得异常单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getExceptionOrderCount(String localNetId, String areaId,String workAreaId) throws AppException,
			SysException;
	
	  /**
	 * 获得所有有权限工区的领单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public String getFetchOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException ;
	/**
	 * 获得所有有权限工区的回单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getReturnOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException ;
	/**
	 * 获得所有有权限工区的追单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getZhuiDanOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException;
	/**
	 * 获得所有有权限工区的退单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getBackOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException ;
	/**
	 * 获得所有有权限工区的催单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getPressOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException;
	/**
	 * 获得所有有权限工区的异常单的数量
	 * 
	 * @param localNetId
	 * @param areaId
	 * @return
	 */
	public int getExceptionOrderCountAll(String localNetId, String areaId,String staffId) throws AppException,
			SysException;
	
	/**
	 * 获得所有类型工单的数据统计结果
	 * 	,map中返回的值是,returnorder,pressorder,exceptionorder
	 * @author wugang
	 * @param localNetId
	 * @param areaId
	 * @param staffId
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public Map getAllOrderCount(String localNetId, String areaId,String workAreaId,String staffId)throws AppException,
			SysException;
	
	/**
	 * 根据用户名和密码查询用户，用户名不区分大小写
	 * @param sysUserVO
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findByVOIgnorecase(GenericVO vo)throws AppException,
	SysException;
}
