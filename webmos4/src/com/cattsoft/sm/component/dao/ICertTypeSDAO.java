package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

/**
 * Title:系统管理-员工管理-证件类型管理<br>
 * Description:证件类型管理SDAO接口<br>
 * Copyright:Copyright (C) 2009<br>
 * Company:cattsoft<br>
 * 
 * @author 杨凯 2009-4-28<br>
 * @version 1.0 <br>
 * 
 */
public interface ICertTypeSDAO extends ISDAO {
	
	/**
	 * 增加记录。
	 * 
	 * @return
	 */
	public void add(GenericVO vo) throws AppException, SysException;

	/**
	 * 主键查询的SQL。
	 * 
	 * @return GenericVO ： 返回VO。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException;

	/**
	 * 批量查询的SQL。
	 * 
	 * @return List ： 返回VO列表。
	 */
	public List findByVO(GenericVO vo) throws AppException, SysException;

	/**
	 * 更新数据库表的SQL。
	 * 
	 * @return
	 */
	public void update(GenericVO vo) throws AppException, SysException;

	/**
	 * 批量增加记录。
	 * 
	 * @return
	 */
	public void addBat(List list) throws AppException, SysException;

	/**
	 * 根据传入参数删除一条或者一批记录。
	 * 
	 * @return
	 */
	public void delete(GenericVO vo) throws AppException, SysException;

	
}