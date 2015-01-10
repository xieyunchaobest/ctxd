package com.cattsoft.sm.component.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.sm.component.dao.IPartyRoleSDAO;
import com.cattsoft.sm.vo.PartyRoleSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class PartyRoleDOM {

    private IPartyRoleSDAO partyRoleDao = (IPartyRoleSDAO) DAOFactory.getInstance().getDAO(
            IPartyRoleSDAO.class);

    private static Logger log = Logger.getLogger(PartyRoleDOM.class);

    /**
     * 根据主键查询单表PartyRoleSVO
     * 
     * @param vo
     * @return PartyRoleSVO
     * @throws SysException
     * @throws AppException
     */
    public PartyRoleSVO findByPk(PartyRoleSVO vo) throws SysException, AppException {
        return (PartyRoleSVO) partyRoleDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表PartyRoleSVO
     * 
     * @param vo
     * @return List<PartyRoleSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(PartyRoleSVO vo) throws SysException, AppException {
        return partyRoleDao.findByVO(vo);
    }

    /**
     * 修改单表PartyRoleSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(PartyRoleSVO vo) throws SysException, AppException {
        partyRoleDao.update(vo);
    }

    /**
     * 删除单表PartyRoleSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(PartyRoleSVO vo) throws SysException, AppException {
        partyRoleDao.delete(vo);

    }

    /**
     * 增加单表PartyRoleSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void add(PartyRoleSVO vo) throws SysException, AppException {
        partyRoleDao.add(vo);

    }

}
