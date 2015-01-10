package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class OtherCharacteristicSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String characteristicId;

    private String name;

    private String parentCharacteristicId;

    private String dataType;

    private String valueType;

    private String fromValue;

    private String toValue;

    private String defaultValue;

    private String encrypted;

    private String valueLenLimit;

    private String valueNumLimit;

    private String standardCode;

    private String backfillFlag;

    private String sts;

    private Timestamp stsDate;

    private Timestamp createDate;

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

    public void setParentCharacteristicId(String parentCharacteristicId) {
        this.parentCharacteristicId = parentCharacteristicId;
    }

    public String getParentCharacteristicId() {
        return parentCharacteristicId;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public String getToValue() {
        return toValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setValueLenLimit(String valueLenLimit) {
        this.valueLenLimit = valueLenLimit;
    }

    public String getValueLenLimit() {
        return valueLenLimit;
    }

    public void setValueNumLimit(String valueNumLimit) {
        this.valueNumLimit = valueNumLimit;
    }

    public String getValueNumLimit() {
        return valueNumLimit;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setBackfillFlag(String backfillFlag) {
        this.backfillFlag = backfillFlag;
    }

    public String getBackfillFlag() {
        return backfillFlag;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts() {
        return sts;
    }

    public void setStsDate(Timestamp stsDate) {
        this.stsDate = stsDate;
    }

    public Timestamp getStsDate() {
        return stsDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof OtherCharacteristicSVO) {
            OtherCharacteristicSVO another = (OtherCharacteristicSVO) obj;
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
}