package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.IDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.QryBasicCustInfoMVO;
import com.cattsoft.sm.vo.QryCustInfoConditionMVO;


public interface IPartyIdentityMDAO extends IDAO {

    /**
     * 根据证件号查询party_role_id
     * 
     * @param vo
     * @return party_role_id
     * @throws AppException
     * @throws SysException
     */
    public List findPartyRoleIdByCertCode(GenericVO vo) throws AppException, SysException;

    /**
     * 营业专用，勿改
     * 
     * @param vo
     * @return QryBasicCustInfoMVO
     * @throws AppException
     * @throws SysException
     * @author zhouqian
     */
    public QryBasicCustInfoMVO findCertInfo(QryCustInfoConditionMVO vo) throws AppException,
            SysException;
}