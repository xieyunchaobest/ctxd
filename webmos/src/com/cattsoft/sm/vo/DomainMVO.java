package com.cattsoft.sm.vo;

import java.util.List;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-18 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class DomainMVO extends GenericVO {
    
    private List domainValueSVOList = null ;
    
    private DomainSVO domainSVO=new DomainSVO();

    public DomainSVO getDomainSVO() {
        return domainSVO;
    }

    public void setDomainSVO(DomainSVO domainSVO) {
        this.domainSVO = domainSVO;
    }

    public List getDomainValueSVOList() {
        return domainValueSVOList;
    }

    public void setDomainValueSVOList(List domainValueSVOList) {
        this.domainValueSVOList = domainValueSVOList;
    }

}
