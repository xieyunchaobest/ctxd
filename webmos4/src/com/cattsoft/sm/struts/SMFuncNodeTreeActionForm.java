package com.cattsoft.sm.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-23 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author cason_lau
 */

public class SMFuncNodeTreeActionForm extends ActionForm  {

    private String srcUrl;// 访问来源。有不同的页面将用到same tree

    private String sysRoleId;
    
    private String funcSetId;

    private String sysUserId;
    
    private String funcNodeId;

    private Long[] sysRoleIds;

    private Long[] sysUserIds;
    
    private Long[] funcNodeIds;

    private String name;

    private Long[] choses;

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    }

    public Long[] getChoses() {
        return choses;
    }

    public void setChoses(Long[] choses) {
        this.choses = choses;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long[] getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(Long[] sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long[] getSysUserIds() {
        return sysUserIds;
    }

    public void setSysUserIds(Long[] sysUserIds) {
        this.sysUserIds = sysUserIds;
    }

    public String getFuncSetId() {
        return funcSetId;
    }

    public void setFuncSetId(String funcSetId) {
        this.funcSetId = funcSetId;
    }

}
