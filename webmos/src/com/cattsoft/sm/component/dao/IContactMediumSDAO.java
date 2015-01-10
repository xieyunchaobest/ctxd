package com.cattsoft.sm.component.dao;

import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.ContactMediumInfoMVO;

public interface IContactMediumSDAO extends ISDAO {
    public List findInfoByMVO(ContactMediumInfoMVO info) throws AppException, SysException;

    public void updateByPartyId(GenericVO vo) throws AppException, SysException;
}