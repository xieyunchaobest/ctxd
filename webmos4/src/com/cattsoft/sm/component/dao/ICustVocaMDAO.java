/**
 * 
 */
package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

/**
 * Title:系统管理-员工管理-客户行业管理<br>
 * Description:客户行业管理MDAO接口<br>
 * Copyright:Copyright (C) 2009<br>
 * Company:cattsoft<br>
 * 
 * @author 杨凯 2009-4-28<br>
 * @version 1.0 <br>
 * 
 */
public interface ICustVocaMDAO extends IDAO {
	/**
	 * 根椐vo中的信息获取所有相关信息<br>
	 * 
	 * @param vo包含部分信息
	 * @return List 返回所有相关信息
	 * @throws AppException
	 * @throws SysException
	 */
	public List findByVO(GenericVO vo) throws AppException, SysException;
		

	/**
	 * 主键查询的SQL。
	 * 
	 * @return GenericVO ： 返回VO。
	 */
	public GenericVO findByPK(GenericVO vo) throws AppException, SysException;
	
	/**
	 * 批量注销客户行业
	 * 
	 * @param voList
	 * @throws AppException
	 * @throws SysException
	 */
	public void unableBat(List voList) throws AppException, SysException;
}
