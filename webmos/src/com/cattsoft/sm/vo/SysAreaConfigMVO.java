package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-22 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysAreaConfigMVO extends GenericVO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4278065784049697082L;

	private String sysAreaConfigId;

    private String configId;

    private String spAreaId;

    private String curValue;

    private String valueDesc;

    private String name;

    private String systemName;

    private String configType;

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

    public String getSpAreaId() {
        return spAreaId;
    }

    public void setSpAreaId(String spAreaId) {
        this.spAreaId = spAreaId;
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

}
