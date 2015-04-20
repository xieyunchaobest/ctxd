package com.cattsoft.sm.component.domain;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IOrgDeptSDAO;
import com.cattsoft.sm.component.dao.IPartyMDAO;
import com.cattsoft.sm.component.dao.IPartySDAO;
import com.cattsoft.sm.component.dao.IStaffMDAO;
import com.cattsoft.sm.util.SMMaxId;
import com.cattsoft.sm.vo.DevelopMVO;
import com.cattsoft.sm.vo.PartyHistSVO;
import com.cattsoft.sm.vo.PartySVO;
import com.cattsoft.sm.vo.QryBasicCustInfoMVO;
import com.cattsoft.sm.vo.QryCustInfoConditionMVO;
import com.cattsoft.sm.vo.QryPartyInfoConditionMVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class PartyDOM {
	
    private IPartySDAO partyDao = (IPartySDAO) DAOFactory.getInstance().getDAO(IPartySDAO.class);

    private IPartyMDAO partyMDAO = (IPartyMDAO) DAOFactory.getInstance().getDAO(IPartyMDAO.class);

    private IOrgDeptSDAO orgDeptDAO = (IOrgDeptSDAO) DAOFactory.getInstance().getDAO(
            IOrgDeptSDAO.class);

    private IStaffMDAO staffMDAO = (IStaffMDAO) DAOFactory.getInstance().getDAO(IStaffMDAO.class);

    private static Logger log = Logger.getLogger(PartyDOM.class);

    /**
     * 根据主键查询单表partysvo
     * 
     * @param vo
     * @return partysvo
     * @throws SysException
     * @throws AppException
     */
    public PartySVO findByPk(PartySVO vo) throws SysException, AppException {
        return (PartySVO) partyDao.findByPK(vo);
    }

    /**
     * 营业专用，勿改
     * 
     * @param vo
     * @return QryBasicCustInfoMVO
     * @throws SysException
     * @throws AppException
     * @author zhouqian
     */
    public QryBasicCustInfoMVO findName(QryCustInfoConditionMVO vo) throws SysException,
            AppException {
        return partyMDAO.findName(vo);
    }

    /**
     * 根据相应的条件查询单表partysvo
     * 
     * @param vo
     * @return List<PartySVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(PartySVO vo) throws SysException, AppException {
        return partyDao.findByVO(vo);
    }

    /**
     * 修改单表PartySVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     * @throws SQLException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void update(PartySVO vo) throws SysException, AppException, SQLException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        PartyHistSVO phsvo = new PartyHistSVO();
        PartyHistDOM phdom = new PartyHistDOM();
        partyDao.update(vo);
        vo=(PartySVO)partyDao.findByPK(vo);
        String seq = SMMaxId.getNextSeqByPartyHist(vo.getPartyId());
        PropertyUtils.copyProperties(phsvo, vo);
        phsvo.setCreateDate(SysDateUtil.getCurrentTimestamp());
        phsvo.setStsDate(SysDateUtil.getCurrentTimestamp());
        phsvo.setSeq(seq);
        phdom.add(phsvo);
    }

    /**
     * 增加单表PartySVO及其历史表信息
     * 
     * @param vo
     * @return String(partyId)
     * @throws SysException
     * @throws AppException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String add(PartySVO vo) throws SysException, AppException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        if (vo.getPartyId() == null) {
            vo.setPartyId(MaxId.getSequenceNextVal(Constant.SEQ_PARTY_ID));
        }
        partyDao.add(vo);
        PartyHistSVO hvo = new PartyHistSVO();
        PartyHistDOM hdom = new PartyHistDOM();
        PropertyUtils.copyProperties(hvo, vo);
        hvo.setSeq("1");
        hdom.add(hvo);
        return vo.getPartyId();
    }

    /**
     * 删除单表PartySVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(PartySVO vo) throws SysException, AppException {
        if (vo.getSts() == null || !vo.getSts().equals("P")) {
            vo.setSts("P");
        }
        partyDao.update(vo);

    }

    /**
     * 通过客户姓名查找客户编号
     * 
     * @param vo
     * @return List<String>
     * @throws AppException
     * @throws SysException
     */
    public List findCustIdByName(QryPartyInfoConditionMVO vo) throws AppException, SysException {
        vo.setPartyRoleType(Constant.PARTY_ROLE_TYPE_CUST);
        return partyMDAO.findPartyRoleIdByName(vo);
    }

    /**
     * 通过员工姓名查找员工编号
     * 
     * @param vo
     * @return List<String>
     * @throws AppException
     * @throws SysException
     */
    public List findStaffIdByName(QryPartyInfoConditionMVO vo) throws AppException, SysException {
        vo.setPartyRoleType(Constant.PARTY_ROLE_TYPE_STAFF);
        return partyMDAO.findPartyRoleIdByName(vo);
    }

    /**
     * 根据不同的发展人类型找不同的List(2:组织结构,9:代理商)
     * 
     * @param vo
     * @return List<LavelValueBean>(为2,developId为空,返回组织结构集合,不为空,返回员工集合;为9,返回代理商集合)
     * @throws AppException
     * @throws SysException
     */
    public List findDeveloper(DevelopMVO vo) throws AppException, SysException {

        if (vo.getPartyRoleType().equals("2")) {
            if (vo.getDevelopId() != null && !vo.getDevelopId().equals("")) {
                return staffMDAO.findByLabelValueBean(vo);
            } else
                return orgDeptDAO.findByLabelValueBean(vo);
        }
        if (vo.getPartyRoleType().equals("9")) {
            return partyMDAO.findByLabelValueBean(vo);
        }
        return null;
    }
}
