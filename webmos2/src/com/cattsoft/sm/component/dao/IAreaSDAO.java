package com.cattsoft.sm.component.dao;

import java.util.HashSet;
import java.util.List;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.pub.vo.GenericVO;
import com.cattsoft.sm.vo.AreaSVO;


public interface IAreaSDAO extends ISDAO {
  
    public PagView findAreasByPage(AreaSVO vo,HashSet set, PagInfo pagInfo) throws Exception;
    public List findByVO(GenericVO vo1, String staffId, String localnetid)throws AppException, SysException;
}