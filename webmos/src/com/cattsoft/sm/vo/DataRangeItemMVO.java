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
public class DataRangeItemMVO extends GenericVO {
	
    
    private static final long serialVersionUID = 1L;

    private DataRangeItemSVO driv;

    private FuncRangeTypeSVO frtv;

    private Long sysUserId;

    private Integer sysRoleId;

    private Long funcNodeId;

    public DataRangeItemSVO getDriv() {
        return driv;
    }

    public void setFrtv(FuncRangeTypeSVO frtv) {
        this.frtv = frtv;
    }

    public void setDriv(DataRangeItemSVO driv) {
        this.driv = driv;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public void setFuncNodeId(Long funcNodeId) {
        this.funcNodeId = funcNodeId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public FuncRangeTypeSVO getFrtv() {
        return frtv;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public Long getFuncNodeId() {
        return funcNodeId;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public DataRangeItemMVO() {
    }

}
