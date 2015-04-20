package com.cattsoft.tm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

public interface ISysMDAO extends IDAO{
	
	public List getDataDicList(String tableName,String columnName) throws AppException,SysException;

}
