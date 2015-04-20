package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

public interface IStaffMemberMDAO extends IDAO {
	
	public List findStaff(GenericVO vo) throws SysException, AppException;
}
