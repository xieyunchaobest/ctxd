package com.cattsoft.pub.dao;

import java.util.List;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

public interface ISDAO extends IDAO{
	
	
	/**
	 * 根据主键查vo
	 * 
	 * @param vo
	 * @return
	 */
	public GenericVO findByPK(GenericVO vo)throws AppException,SysException;
	
	
	/**
	 * 添加
	 * 
	 * @param vo
	 */
	public void add(GenericVO vo)throws AppException,SysException;
	
	/**
	 * 批量添加
	 * 
	 * @param vos
	 */
	public void addBat(List vos)throws AppException,SysException;
	
	
	/**
	 * 修改
	 * 
	 * @param vo
	 */
	public void update(GenericVO vo)throws AppException,SysException;
	
	
	/**
	 * 根据主键删除
	 * 
	 * @param vo
	 */
	public void delete(GenericVO vo)throws AppException,SysException;
	
	
	

	
	/**
	 * 修改状态为停用
	 * @param vo
	 */
	//public void unable(GenericVO vo)throws AppException,SysException;
	
	
	/**
	 * 根据vo查询
	 * 
	 * @param vo
	 * @return 
	 */
	public List findByVO(GenericVO vo)throws AppException,SysException;
	
}
