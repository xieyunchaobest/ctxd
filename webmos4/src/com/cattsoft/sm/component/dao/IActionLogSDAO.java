package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;


public interface IActionLogSDAO extends ISDAO {
	public void addBat(List vos)throws AppException,SysException;
	
}