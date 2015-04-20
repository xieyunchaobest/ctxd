package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class OrgDeptPartyMVO extends GenericVO {

    private static final long serialVersionUID = 1L;
    private PartySVO partyVO;
    private OrgDeptSVO orgDeptVO;
    public OrgDeptPartyMVO() {
    }
    public OrgDeptSVO getOrgDeptVO() {
      return orgDeptVO;
    }
    public PartySVO getPartyVO() {
      return partyVO;
    }
    public void setOrgDeptVO(OrgDeptSVO orgDeptVO) {
      this.orgDeptVO = orgDeptVO;
    }
    public void setPartyVO(PartySVO partyVO) {
      this.partyVO = partyVO;
    }

}
