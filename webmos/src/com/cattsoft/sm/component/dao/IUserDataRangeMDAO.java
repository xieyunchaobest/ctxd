package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

public interface IUserDataRangeMDAO extends IDAO {
	/**
	 * 批量查询用户权限数据
	 * 
	 * @param vo
	 *            <UserDataRangeSVO>
	 * @return List<UserDataRangeSVO>
	 */
	public List findUserDataRangeList(GenericVO vo) throws AppException, SysException;

	/**
	 * 查询当前员工拥有的服务区
	 * 
	 * @param vo
	 *            <UserDataRangeSVO>
	 */
	public List findUserAreas(GenericVO vo) throws AppException, SysException;

	/**
	 * 查询当前员工拥有的服务区(服务区不重复distinct）
	 * 
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findDistinctUserAreas(GenericVO vo) throws AppException, SysException;

	/**
	 * 查询当前员工拥有的本地网
	 * 
	 * @param vo
	 *            <UserDataRangeSVO>
	 */
	public List findUserLocalNets(GenericVO vo) throws AppException, SysException;

	/**
	 * 查询当前员工拥有的本地网在（本地网不重复distinct）
	 * 
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findDistinctUserLocalNets(GenericVO vo) throws AppException, SysException;

	/**
	 * 获取员工
	 * 
	 * @param vo
	 *            <UserDataRangeMVO>
	 */
	public List getStaffs(GenericVO vo) throws AppException, SysException;

	/**
	 * 根据传入参数删除一条或者一批记录。
	 * 
	 * @return String ： 删除的SQL。
	 */
	public void delete(GenericVO vo) throws AppException, SysException;

	/**
	 * 增加。
	 * 
	 * @return String
	 */
	public void addBat(List list) throws AppException, SysException;

	/**
	 * 按照用户权限查询本地网或者服务区
	 * 
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findDataRange(GenericVO vo) throws AppException, SysException;
	
	/**
	 * 功能：按照用户权限查询本地网
	 * 
	 * @param vo
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public List findLocalNetByRange(GenericVO vo) throws AppException,
			SysException;
}
