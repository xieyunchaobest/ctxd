package com.cattsoft.sm.struts;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-18 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysConfigActionForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String asystemName; //add by liyaquan 2008-8-19
    private String aconfigId;   //add by liyaquan 2008-8-19
    private String aname;       //add by liyaquan 2008-8-19
    private String configId;

    private String name;

    private String systemName;

    private String configType;

    private String curValue;

    private String valueDesc;

    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

	public String getAsystemName() {
		return asystemName;
	}

	public void setAsystemName(String asystemName) {
		this.asystemName = asystemName;
	}

	public String getAconfigId() {
		return aconfigId;
	}

	public void setAconfigId(String aconfigId) {
		this.aconfigId = aconfigId;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

}
