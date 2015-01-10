package com.cattsoft.sm.component.dao;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

public interface IAppPermissionSDAO extends ISDAO {

	public void add(GenericVO vo) throws SysException, AppException;

	public void delete(GenericVO vo) throws AppException, SysException;

}
