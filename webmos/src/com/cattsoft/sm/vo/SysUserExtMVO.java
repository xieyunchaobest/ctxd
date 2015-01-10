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

public class SysUserExtMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private SysUserSVO svo;
    
    private PartyRoleMVO pvo;
    
    private PartyRoleTypeSVO tvo;

    private String localNetId;

    private String partyName;

    private String areaId;

    private String deptId;
    
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public PartyRoleMVO getPvo() {
        return pvo;
    }

    public void setPvo(PartyRoleMVO pvo) {
        this.pvo = pvo;
    }

    public SysUserSVO getSvo() {
        return svo;
    }

    public void setSvo(SysUserSVO svo) {
        this.svo = svo;
    }

    public PartyRoleTypeSVO getTvo() {
        return tvo;
    }

    public void setTvo(PartyRoleTypeSVO tvo) {
        this.tvo = tvo;
    }

  


}
