package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.sm.vo.AppPermissionMVO;

public interface IAppPermissionMDAO extends IDAO {

	public List findByVO(AppPermissionMVO mvo) throws AppException,
			SysException;

	public List findAppInfo(AppPermissionMVO mvo) throws AppException,
			SysException;

}
