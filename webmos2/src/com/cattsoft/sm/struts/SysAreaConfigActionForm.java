package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-22 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SysAreaConfigActionForm extends ActionForm {

    private static final long serialVersionUID = 1L;
    
    private String asysAreaConfigId; //add by liyaquan
    private String aname;            //add by liyaquan
    private String aconfigId;
    private String sysAreaConfigId;

    private String configId;

    private String spAreaId;

    private String curValue;

    private String valueDesc;

    private String name;

    private String systemName;

    private String configType;
    
    private String configTypeName;

    private List nameSel;

    private List spAreaSel;
    
    private String[] sysAreaConfigIds;

    public String[] getSysAreaConfigIds() {
        return sysAreaConfigIds;
    }

    public void setSysAreaConfigIds(String[] sysAreaConfigIds) {
        this.sysAreaConfigIds = sysAreaConfigIds;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getCurValue() {
        return curValue;
    }

    public void setCurValue(String curValue) {
        this.curValue = curValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getNameSel() {
        return nameSel;
    }

    public void setNameSel(List nameSel) {
        this.nameSel = nameSel;
    }

    public String getSpAreaId() {
        return spAreaId;
    }

    public void setSpAreaId(String spAreaId) {
        this.spAreaId = spAreaId;
    }

    public List getSpAreaSel() {
        return spAreaSel;
    }

    public void setSpAreaSel(List spAreaSel) {
        this.spAreaSel = spAreaSel;
    }

    public String getSysAreaConfigId() {
        return sysAreaConfigId;
    }

    public void setSysAreaConfigId(String sysAreaConfigId) {
        this.sysAreaConfigId = sysAreaConfigId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public String getConfigTypeName() {
        return configTypeName;
    }

    public void setConfigTypeName(String configTypeName) {
        this.configTypeName = configTypeName;
    }

	public String getAsysAreaConfigId() {
		return asysAreaConfigId;
	}

	public void setAsysAreaConfigId(String asysAreaConfigId) {
		this.asysAreaConfigId = asysAreaConfigId;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAconfigId() {
		return aconfigId;
	}

	public void setAconfigId(String aconfigId) {
		this.aconfigId = aconfigId;
	}

}
