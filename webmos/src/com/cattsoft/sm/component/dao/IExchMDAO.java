package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.ExchSVO;

public interface IExchMDAO extends IDAO{

	public List findExchMVOByVO(ExchSVO vo) throws AppException, SysException;

	public List findByMVO(GenericVO vo) throws AppException, SysException;
}
