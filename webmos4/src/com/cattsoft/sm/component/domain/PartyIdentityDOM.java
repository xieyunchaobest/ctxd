package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IPartyIdentityMDAO;
import com.cattsoft.sm.component.dao.IPartyIdentitySDAO;
import com.cattsoft.sm.vo.PartyIdentitySVO;
import com.cattsoft.sm.vo.QryBasicCustInfoMVO;
import com.cattsoft.sm.vo.QryCustInfoConditionMVO;
import com.cattsoft.sm.vo.QryPartyInfoConditionMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class PartyIdentityDOM {
	

    private IPartyIdentitySDAO partyIdentityDao = (IPartyIdentitySDAO) DAOFactory.getInstance()
            .getDAO(IPartyIdentitySDAO.class);

    private static Logger log = Logger.getLogger(PartyIdentityDOM.class);

    private IPartyIdentityMDAO partyIdentityMDAO = (IPartyIdentityMDAO) DAOFactory.getInstance()
            .getDAO(IPartyIdentityMDAO.class);

    /**
     * 根据主键查询单表PartyIdentitySVO
     * 
     * @param vo
     * @return PartyIdentitySVO
     * @throws SysException
     * @throws AppException
     */
    public PartyIdentitySVO findByPk(PartyIdentitySVO vo) throws SysException, AppException {
        return (PartyIdentitySVO) partyIdentityDao.findByPK(vo);
    }

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
            SysException {
        return partyIdentityMDAO.findCertInfo(vo);
    }

    /**
     * 根据相应的条件查询单表PartyIdentitySVO
     * 
     * @param vo
     * @return List<PartyIdentitySVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(PartyIdentitySVO vo) throws SysException, AppException {
        return partyIdentityDao.findByVO(vo);
    }

    /**
     * 修改单表PartyIdentitySVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(PartyIdentitySVO vo) throws SysException, AppException {
        partyIdentityDao.update(vo);
    }

    /**
     * 删除单表PartyIdentitySVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(PartyIdentitySVO vo) throws SysException, AppException {
        if (!vo.getSts().equals("P")) {
            vo.setSts("P");
        }
        partyIdentityDao.update(vo);

    }

    /**
     * 增加单表PartyIdentitySVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void add(PartyIdentitySVO vo) throws SysException, AppException {
        if (vo.getPartyIdentityId() == null) {
            vo.setPartyIdentityId(MaxId.getSequenceNextVal(Constant.SEQ_PARTY_IDENTITY_ID));
        }
        partyIdentityDao.add(vo);
    }

    /**
     * 通过身份证号查找客户编号
     * 
     * @param vo
     * @return List<String>
     * @throws AppException
     * @throws SysException
     */
    public String findCustIdByIDCard(QryPartyInfoConditionMVO vo) throws AppException, SysException {
        vo.setCertType(Constant.ID_CARD);
        vo.setPartyRoleType(Constant.PARTY_ROLE_TYPE_CUST);
        List temp = partyIdentityMDAO.findPartyRoleIdByCertCode(vo);
        if (temp == null) {
            return null;
        }
        return (String) temp.get(0);
    }

}
