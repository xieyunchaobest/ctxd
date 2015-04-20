package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class AreaCharacMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private String areaCharacId;

    private String characteristicId;

    private String regionType;

    private String regionId;

    private String value;

    private String sts;

    private Timestamp stsDate;

    private String enumValueId;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEnumValueId() {
        return enumValueId;
    }

    public void setEnumValueId(String enumValueId) {
        this.enumValueId = enumValueId;
    }

}
