package com.cattsoft.sm.struts;

import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;


/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-19 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class AreaCharacActionForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String areaCharacId;

    private String characteristicId;

    private String regionType;

    private String regionId;

    private String value;

    private String sts;

    private Timestamp stsDate;

    private String[] areaCharacIds;

    private String[] values;

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getAreaCharacId() {
        return areaCharacId;
    }

    public void setAreaCharacId(String areaCharacId) {
        this.areaCharacId = areaCharacId;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Timestamp getStsDate() {
        return stsDate;
    }

    public void setStsDate(Timestamp stsDate) {
        this.stsDate = stsDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getAreaCharacIds() {
        return areaCharacIds;
    }

    public void setAreaCharacIds(String[] areaCharacIds) {
        this.areaCharacIds = areaCharacIds;
    }

}
