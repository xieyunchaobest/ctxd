package com.cattsoft.sm.component.dao;


import java.util.HashSet;

import com.cattsoft.pub.dao.ISDAO;
import com.cattsoft.pub.util.PagInfo;
import com.cattsoft.pub.util.PagView;
import com.cattsoft.sm.vo.FaultReasonSVO;

public interface IFaultReasonSDAO extends ISDAO {

    public PagView findFaultReasonsByPage(FaultReasonSVO vo, HashSet set, PagInfo pagInfo)
            throws Exception;
}