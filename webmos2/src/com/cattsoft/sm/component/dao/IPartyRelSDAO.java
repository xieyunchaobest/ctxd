package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;

/**
 * 联系人信息
 * 
 * @author wangdongxun
 * @param mapping
 * @param actionForm
 * @param request
 * @param response
 * @return
 * @throws Exception
 */

public interface IPartyRelSDAO extends ISDAO {
    public void updateByParPartyId(GenericVO vo) throws AppException, SysException;

    public List findByPartyId(String partyId) throws AppException, SysException;
}