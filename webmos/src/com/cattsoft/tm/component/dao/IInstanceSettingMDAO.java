package com.cattsoft.tm.component.dao;

import java.util.List;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

public interface IInstanceSettingMDAO extends IDColumnDescSDAO{
	
	public List getTableColumns(String tableName) throws AppException,SysException;

}
