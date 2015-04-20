package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;


public interface ILocalNetAreaMDAO extends IDAO {
    public List findLocalNet(GenericVO vo1, GenericVO vo2) throws AppException, SysException;

    public List findArea(GenericVO vo1, GenericVO vo2) throws AppException, SysException;

    public List findAreaTree(GenericVO vo1, GenericVO vo2) throws AppException, SysException;

    public List findLocalNetTree(GenericVO vo1, GenericVO vo2) throws AppException, SysException;
}
