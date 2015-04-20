package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.MsAreaSVO;
import com.cattsoft.sm.vo.ServDeptSVO;

public interface IServDeptMDAO extends IDAO {

	public List findServDeptsByMVO(ServDeptSVO vo) throws AppException, SysException;
	
	public List findServDeptList(GenericVO vo) throws AppException, SysException;
	
	public List findServDeptList(MsAreaSVO msAreaSVO) throws AppException, SysException;
	
}
