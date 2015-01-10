package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IContactMediumSDAO;
import com.cattsoft.sm.vo.ContactMediumSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class ContactMediumDOM {
    private IContactMediumSDAO contactMediumDao = (IContactMediumSDAO) DAOFactory.getInstance().getDAO(IContactMediumSDAO.class);

    private static Logger log = Logger.getLogger(ContactMediumDOM.class);

    /**
     * 根据主键查询单表ContactMediumSVO
     * 
     * @param vo
     * @return ContactMediumSVO
     * @throws SysException
     * @throws AppException
     */
    public ContactMediumSVO findByPk(ContactMediumSVO vo) throws SysException, AppException {
        return (ContactMediumSVO) contactMediumDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表ContactMediumSVO
     * 
     * @param vo
     * @return List<ContactMediumSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(ContactMediumSVO vo) throws SysException, AppException {
        return contactMediumDao.findByVO(vo);
    }

    /**
     * 修改单表ContactMediumSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(ContactMediumSVO vo) throws SysException, AppException {
        contactMediumDao.update(vo);
    }

    /**
     * 删除单表ContactMediumSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(ContactMediumSVO vo) throws SysException, AppException {
        if (!vo.getSts().equals("P")) {
            vo.setSts("P");
        }
        contactMediumDao.update(vo);

    }

    /**
     * 增加单表ContactMediumSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void add(ContactMediumSVO vo) throws SysException, AppException {
        if (vo.getContactId()==null) {
            vo.setContactId(MaxId.getSequenceNextVal(Constant.SEQ_CONTACT_ID));
        }
        contactMediumDao.add(vo);
    }


}
