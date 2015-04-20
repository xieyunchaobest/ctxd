package com.cattsoft.sm.vo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class EnumValueMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String enumValueId;

    private String enumValueName;

    private String unitName;

    private String characteristicId;// ²éÑ¯Ìõ¼þ

    private String enumValueCode;

    private String sortBy;

    private String validFlag;

    public void setEnumValueId(String enumValueId) {
        this.enumValueId = enumValueId;
    }

    public String getEnumValueId() {
        return enumValueId;
    }

    public void setEnumValueName(String enumValueName) {
        this.enumValueName = enumValueName;
    }

    public String getEnumValueName() {
        return enumValueName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setEnumValueCode(String enumValueCode) {
        this.enumValueCode = enumValueCode;
    }

    public String getEnumValueCode() {
        return enumValueCode;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof EnumValueMVO) {
            EnumValueMVO another = (EnumValueMVO) obj;
            equals = new EqualsBuilder().append(enumValueId, another.getEnumValueId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(enumValueId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("enumValueId", getEnumValueId()).toString();
    }
}