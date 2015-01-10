package com.cattsoft.sm.vo;

import java.sql.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class CharacteristicMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String characteristicId;

    private String name;// 查询条件

    private String valueType;// 查询条件

    private String valueTypeName;// 显示用

    private String standardCode;

    private String sts;// 查询条件

    private Date stsDate;

    private Date createDate;

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts() {
        return sts;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof CharacteristicMVO) {
            CharacteristicMVO another = (CharacteristicMVO) obj;
            equals = new EqualsBuilder().append(characteristicId, another.getCharacteristicId())
                    .isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(characteristicId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("characteristicId", getCharacteristicId())
                .toString();
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueTypeName() {
        return valueTypeName;
    }

    public void setValueTypeName(String valueTypeName) {
        this.valueTypeName = valueTypeName;
    }
}