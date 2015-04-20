package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-28 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class SMSetAuthActionForm extends ActionForm {

    private String name;

    private String sysRoleId;

    private String sysUserId;
    
    private String funcNodeId;
    
    private Long[] funcNodeIds;

    private Integer[] choses;

    private Long[] idChoses;

    private String userRoleId;

    private String funcSetAllocId;

    private String consPrivId;

    private Integer partyRoleTypeId;

    private List partyRoleTypeSel;

    private Long[] allocIds;

    private String[] allowAuth;

    private List allowAuths;

    public Long[] getAllocIds() {
        return allocIds;
    }

    public void setAllocIds(Long[] allocIds) {
        this.allocIds = allocIds;
    }

    public String[] getAllowAuth() {
        return allowAuth;
    }

    public void setAllowAuth(String[] allowAuth) {
        this.allowAuth = allowAuth;
    }

    public List getAllowAuths() {
        return allowAuths;
    }

    public void setAllowAuths(List allowAuths) {
        this.allowAuths = allowAuths;
    }

    public Integer[] getChoses() {
        return choses;
    }

    public void setChoses(Integer[] choses) {
        this.choses = choses;
    }

    public String getConsPrivId() {
        return consPrivId;
    }

    public void setConsPrivId(String consPrivId) {
        this.consPrivId = consPrivId;
    }

    public String getFuncSetAllocId() {
        return funcSetAllocId;
    }

    public void setFuncSetAllocId(String funcSetAllocId) {
        this.funcSetAllocId = funcSetAllocId;
    }

    public Long[] getIdChoses() {
        return idChoses;
    }

    public void setIdChoses(Long[] idChoses) {
        this.idChoses = idChoses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartyRoleTypeId() {
        return partyRoleTypeId;
    }

    public void setPartyRoleTypeId(Integer partyRoleTypeId) {
        this.partyRoleTypeId = partyRoleTypeId;
    }

    public List getPartyRoleTypeSel() {
        return partyRoleTypeSel;
    }

    public void setPartyRoleTypeSel(List partyRoleTypeSel) {
        this.partyRoleTypeSel = partyRoleTypeSel;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getFuncNodeId() {
        return funcNodeId;
    }

    public void setFuncNodeId(String funcNodeId) {
        this.funcNodeId = funcNodeId;
    }

    public Long[] getFuncNodeIds() {
        return funcNodeIds;
    }

    public void setFuncNodeIds(Long[] funcNodeIds) {
        this.funcNodeIds = funcNodeIds;
    }

}
