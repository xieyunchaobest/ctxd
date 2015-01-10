package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.IPartyRelMDAO;
import com.cattsoft.sm.component.dao.IPartyRelSDAO;
import com.cattsoft.sm.vo.PartyRelSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author sky_star
 */

public class PartyRelDOM {
    private IPartyRelMDAO partyRelMDAO = (IPartyRelMDAO) DAOFactory.getInstance().getDAO(
            IPartyRelMDAO.class);

    private IPartyRelSDAO partyRelDao = (IPartyRelSDAO) DAOFactory.getInstance().getDAO(
            IPartyRelSDAO.class);

    /** 根据客户的partyId找上级客户 */
    public LabelValueBean findParentCust(String partyId2) throws SysException, AppException {
        return partyRelMDAO.findParentCust(partyId2);
    }

    /**
     * 根据主键查询单表PartyRelSVO
     * 
     * @param vo
     * @return PartyRelSVO
     * @throws SysException
     * @throws AppException
     */
    public PartyRelSVO findByPk(PartyRelSVO vo) throws SysException, AppException {
        return (PartyRelSVO) partyRelDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表PartyRelSVO
     * 
     * @param vo
     * @return List<PartyRelSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(PartyRelSVO vo) throws SysException, AppException {
        return partyRelDao.findByVO(vo);
    }

    /**
     * 修改单表PartyRelSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(PartyRelSVO vo) throws SysException, AppException {

        partyRelDao.update(vo);
    }

    /**
     * 删除单表PartyRelSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(PartyRelSVO vo) throws SysException, AppException {

        partyRelDao.update(vo);

    }

    /**
     * 增加单表PartyRelSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void add(PartyRelSVO vo) throws SysException, AppException {

        partyRelDao.add(vo);
    }

}
