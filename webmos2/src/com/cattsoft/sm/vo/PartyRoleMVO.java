package com.cattsoft.sm.vo;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class PartyRoleMVO extends PartyRoleSVO {

    private static final long serialVersionUID = 1L;

    private PartySVO partySVO;
    
    private StaffMVO staffMVO;

    public PartySVO getPartySVO() {
        return partySVO;
    }

    public void setPartySVO(PartySVO partySVO) {
        this.partySVO = partySVO;
    }

    public StaffMVO getStaffMVO() {
        return staffMVO;
    }

    public void setStaffMVO(StaffMVO staffMVO) {
        this.staffMVO = staffMVO;
    }

   

}
