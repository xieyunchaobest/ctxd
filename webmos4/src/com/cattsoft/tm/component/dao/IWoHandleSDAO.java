package com.cattsoft.tm.component.dao;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
 /**
   * 方法IWoHandleSDAO
   * <p>Company: 大唐软件</p>
   * @author ：白小亮。
   * @version 1.0  2007-5-14
  */
public interface IWoHandleSDAO extends ISDAO{
 
	public void addBat(List vos)throws AppException,SysException;

	public String query(String workAreaId) throws AppException, SysException;
       }

