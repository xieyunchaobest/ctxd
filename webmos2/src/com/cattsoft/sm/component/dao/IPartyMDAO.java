package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.QryBasicCustInfoMVO;
import com.cattsoft.sm.vo.QryCustInfoConditionMVO;
import com.cattsoft.sm.vo.SysUserSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public interface IPartyMDAO extends IDAO {

    public List findPartyBySysUserId(SysUserSVO suv,String localNetId) throws AppException, SysException;

    public List findPartyRoleIdByName(GenericVO vo) throws SysException, AppException;

    public List findByDevelopMVO(GenericVO vo) throws AppException, SysException;

    public List findByLabelValueBean(GenericVO vo) throws AppException, SysException;

    /**
     * 营业专用，勿改
     * 
     * @param vo
     * @return QryBasicCustInfoMVO
     * @throws AppException
     * @throws SysException
     * @author zhouqian
     */
    public QryBasicCustInfoMVO findName(QryCustInfoConditionMVO vo) throws AppException,
            SysException;
    
    public List findByWorkAreaId(String workAreaId) throws AppException, SysException;
}
