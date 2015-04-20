package com.cattsoft.sm.component.domain;

import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.sm.component.dao.IPartyHistSDAO;
import com.cattsoft.sm.vo.PartyHistSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class PartyHistDOM {
	

    private IPartyHistSDAO partyHistDao = (IPartyHistSDAO) DAOFactory.getInstance().getDAO(
            IPartyHistSDAO.class);

    private static Logger log = Logger.getLogger(PartyHistDOM.class);

    /**
     * 根据主键查询单表PartyHistSVO
     * 
     * @param vo
     * @return PartyHistSVO
     * @throws SysException
     * @throws AppException
     */
    public PartyHistSVO findByPk(PartyHistSVO vo) throws SysException, AppException {
        return (PartyHistSVO) partyHistDao.findByPK(vo);
    }

    /**
     * 根据相应的条件查询单表PartyHistSVO
     * 
     * @param vo
     * @return List<PartyHistSVO>
     * @throws SysException
     * @throws AppException
     */
    public List findByVo(PartyHistSVO vo) throws SysException, AppException {
        return partyHistDao.findByVO(vo);
    }

    /**
     * 修改单表PartyHistSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void update(PartyHistSVO vo) throws SysException, AppException {
        partyHistDao.update(vo);
    }

    /**
     * 删除单表PartyHistSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void delete(PartyHistSVO vo) throws SysException, AppException {
        if (!vo.getSts().equals("P")) {
            vo.setSts("P");
        }
        partyHistDao.update(vo);

    }

    /**
     * 增加单表PartyHistSVO
     * 
     * @param vo
     * @throws SysException
     * @throws AppException
     */
    public void add(PartyHistSVO vo) throws SysException, AppException {
        if (vo.getPartyHistId() == null) {
            vo.setPartyHistId(MaxId.getSequenceNextVal(Constant.SEQ_PARTY_HIST_ID));
        }
        if(vo.getSts()==null||vo.getSts().equals("P")){
            vo.setSts("A");
        }
        partyHistDao.add(vo);
    }

}
