package com.cattsoft.tm.component.domain;

import java.util.List;

import com.cattsoft.pub.dao.DAOFactory;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.tm.component.dao.IDTableDescSDAO;
import com.cattsoft.tm.component.dao.IInstanceSettingMDAO;
import com.cattsoft.tm.vo.DTableDescSVO;

public class InstanceSettingDOM {
	
	public List getTableColumns(String tableName) throws AppException,SysException{
		IInstanceSettingMDAO columnDAO= (IInstanceSettingMDAO) DAOFactory.getDAO(IInstanceSettingMDAO.class);
		return columnDAO.getTableColumns(tableName);
	}
	
	public List getTables() throws AppException,SysException{
		IDTableDescSDAO tableDAO= (IDTableDescSDAO) DAOFactory.getDAO(IDTableDescSDAO.class);
		return tableDAO.findByVO(new DTableDescSVO());
	}

}
