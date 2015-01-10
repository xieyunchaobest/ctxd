package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.*;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.LocalNetMVO;


public interface ILocalNetMDAO extends IDAO {
    public List findLocalNetsExceptAll(LocalNetMVO mvo) throws AppException, SysException;
    public List findLocalNets(LocalNetMVO mvo) throws AppException, SysException;
    /**
     * 作用：获取本地网的google map中心点
     * create time: Feb 11, 2010
     * author: yangkai
     * @param vo
     * @return
     * @throws AppException
     * @throws SysException
     */
    public List findByPK(GenericVO vo) throws AppException, SysException;
}