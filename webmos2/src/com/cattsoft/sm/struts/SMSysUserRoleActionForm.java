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

public class SMSysUserRoleActionForm extends ActionForm {
    private String userRoleId;

    private Long[] userRoleIds;

    private String sysUserId;

    private String sysRoleId;
    
    private String sysRoleName;

    private Long[] sysRoleIds;

    private String sts;

    private Integer[] choses;

    private Long[] idChoses;

    private String srcUrl;// 标明来源。有多个地方访问同一个action

    private String[] allowAuth;

    private List allowAuths;

    private String allowAuthStrs;

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

    public String getAllowAuthStrs() {
        return allowAuthStrs;
    }

    public void setAllowAuthStrs(String allowAuthStrs) {
        this.allowAuthStrs = allowAuthStrs;
    }

    public Integer[] getChoses() {
        return choses;
    }

    public void setChoses(Integer[] choses) {
        this.choses = choses;
    }

    public Long[] getIdChoses() {
        return idChoses;
    }

    public void setIdChoses(Long[] idChoses) {
        this.idChoses = idChoses;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
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

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(Long[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName;
    }

}
