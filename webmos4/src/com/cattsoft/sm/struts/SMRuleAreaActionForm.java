package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-5 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SMRuleAreaActionForm extends ActionForm {
	

    private String ruleAreaId;

    private String name;

    private String provId;

    private String[] choses;

    private List provSel;

    private String sts;

    private String localNetId;

    private String areaId;

    private List localNetSel;

    private List areaSel;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public List getProvSel() {
        return provSel;
    }

    public void setProvSel(List provSel) {
        this.provSel = provSel;
    }

    public String getRuleAreaId() {
        return ruleAreaId;
    }

    public void setRuleAreaId(String ruleAreaId) {
        this.ruleAreaId = ruleAreaId;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

}
