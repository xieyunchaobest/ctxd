package com.cattsoft.sm.struts;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OtherCharacteristicForm extends ActionForm {
	
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

    public String getBackfillFlag() {
        return backfillFlag;
    }

    public void setBackfillFlag(String backfillFlag) {
        this.backfillFlag = backfillFlag;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCharacteristicId() {
        return parentCharacteristicId;
    }

    public void setParentCharacteristicId(String parentCharacteristicId) {
        this.parentCharacteristicId = parentCharacteristicId;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getStsDate() {
        return stsDate;
    }

    public void setStsDate(Timestamp stsDate) {
        this.stsDate = stsDate;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public String getValueLenLimit() {
        return valueLenLimit;
    }

    public void setValueLenLimit(String valueLenLimit) {
        this.valueLenLimit = valueLenLimit;
    }

    public String getValueNumLimit() {
        return valueNumLimit;
    }

    public void setValueNumLimit(String valueNumLimit) {
        this.valueNumLimit = valueNumLimit;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * Method reset
     * 
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
    }

    /**
     * Method validate
     * 
     * @param mapping
     * @param request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        return null;
    }
}
