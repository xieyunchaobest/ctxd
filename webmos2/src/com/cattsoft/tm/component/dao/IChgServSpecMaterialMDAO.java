package com.cattsoft.tm.component.dao;

import java.util.List;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;


public interface IChgServSpecMaterialMDAO extends IChgServSpecMaterialSDAO{

	public List findByMaterialVO(GenericVO vo) throws AppException, SysException ;
	
}
