package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.OrgDeptSVO;


public interface IOrgDeptSDAO extends ISDAO {
    public List findOrgDeptByTree(GenericVO vo) throws AppException, SysException;

    public List findByDevelopMVO(GenericVO vo) throws AppException, SysException;

    public List findByLabelValueBean(GenericVO vo) throws AppException, SysException;

    public List findIdByVO(OrgDeptSVO vo) throws AppException, SysException;

    public List findByMap(GenericVO vo) throws AppException, SysException;

	public List findOrgDeptByDeptTypeTree(OrgDeptSVO vo) throws AppException, SysException;
}