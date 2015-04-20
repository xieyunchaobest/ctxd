package com.cattsoft.sm.component.domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.Constant;
import com.cattsoft.pub.util.DAOFactory;
import com.cattsoft.pub.util.MaxId;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.component.dao.IRuleAreaSDAO;
import com.cattsoft.sm.vo.RuleAreaSVO;
import com.cattsoft.webpub.util.SysDateUtil;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-3 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class RuleAreaDOM {
	
    private Logger log = Logger.getLogger(RuleAreaDOM.class);

    private IRuleAreaSDAO ruleAreaDao = (IRuleAreaSDAO) DAOFactory.getInstance().getDAO(
            IRuleAreaSDAO.class);

    public void add(RuleAreaSVO vo) throws AppException, SysException {
        RuleAreaSVO rasvo = new RuleAreaSVO();
        rasvo.setName(vo.getName());
        if (ruleAreaDao.findByName(rasvo) != null)
            throw new AppException("3210040", "名称重复");
        if (vo.getRuleAreaId() == null) {
            vo.setRuleAreaId(MaxId.getSequenceNextVal(Constant.SEQ_RULE_AREA_ID));
        }
        Date curDate = SysDateUtil.getCurrentDate();
        vo.setSts(Constant.STS_IN_USE);
        vo.setStsDate(curDate);
        vo.setCreateDate(curDate);
        ruleAreaDao.add(vo);
    }

    public List findByVo(RuleAreaSVO vo) throws AppException, SysException {
        return ruleAreaDao.findByVO(vo);
    }

    public RuleAreaSVO findByPk(RuleAreaSVO vo) throws AppException, SysException {
        return (RuleAreaSVO) ruleAreaDao.findByPK(vo);
    }

    public void mod(RuleAreaSVO vo) throws AppException, SysException {
        RuleAreaSVO rasvo = new RuleAreaSVO();
        rasvo.setName(vo.getName());
        rasvo = ruleAreaDao.findByName(rasvo);
        if (rasvo != null && !vo.getRuleAreaId().equals(rasvo.getRuleAreaId())) {
            throw new AppException("3210040", "名称重复");
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        ruleAreaDao.update(vo);
    }

    public void delete(RuleAreaSVO vo) throws AppException, SysException {

        if (vo.getSts()==null||!vo.getSts().equals(Constant.STS_HISTORY)) {
            vo.setSts(Constant.STS_HISTORY);
        }
        vo.setStsDate(SysDateUtil.getCurrentDate());
        ruleAreaDao.update(vo);
    }

    public void delRuleAreas(String[] ruleAreaIds) throws AppException, SysException {
        RuleAreaSVO vo = new RuleAreaSVO();
        for (int i = 0; i < ruleAreaIds.length; i++) {
            vo.setRuleAreaId(ruleAreaIds[i]);
            this.delete(vo);
        }
    }

    public String getRuleAreaId() throws SysException, AppException, SQLException {
        return MaxId.getSequenceNextVal(Constant.SEQ_RULE_AREA_ID);
    }

    public PagView findRuleAreasByPage(RuleAreaSVO vo, PagInfo pagInfo) throws SysException,
            AppException {
        return ruleAreaDao.findByPage(vo, pagInfo);
    }

    public PagView findRuleAreasBySet(HashSet set, PagInfo pagInfo) throws Exception {
        return ruleAreaDao.findBySet(set, pagInfo);
    }
}
