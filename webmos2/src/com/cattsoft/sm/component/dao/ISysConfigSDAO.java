package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.SysConfigSVO;

public interface ISysConfigSDAO extends ISDAO {

	public SysConfigSVO findSysConfigByPK(SysConfigSVO vo) throws AppException,
			SysException;

	public PagView findSysConfigsByPage(SysConfigSVO vo, HashSet set,
			PagInfo pagInfo) throws Exception;

	public List findByLocalNet(GenericVO vo, String localNetId)
			throws AppException, SysException;
}