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

public class StaffPartyMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private PartySVO partyVO;

    private StaffSVO staffMemberVO;

    public StaffPartyMVO() {
    }

    public PartySVO getPartyVO() {
        return partyVO;
    }

    public StaffSVO getStaffMemberVO() {
        return staffMemberVO;
    }

    public void setPartyVO(PartySVO partyVO) {
        this.partyVO = partyVO;
    }

    public void setStaffMemberVO(StaffSVO staffMemberVO) {
        this.staffMemberVO = staffMemberVO;
    }

}
