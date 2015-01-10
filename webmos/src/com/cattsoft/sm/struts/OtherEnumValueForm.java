package com.cattsoft.sm.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OtherEnumValueForm extends ActionForm {
	
    private static final long serialVersionUID = 1L;

    private String enumValueId;

    private String enumValueName;

    private String unitId;

    private String characteristicId;

    private String enumValueCode;

    private String sortBy;

    private String validFlag;

    private String[] enumValueIds;

    public String[] getEnumValueIds() {
        return enumValueIds;
    }

    public void setEnumValueIds(String[] enumValueIds) {
        this.enumValueIds = enumValueIds;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getEnumValueCode() {
        return enumValueCode;
    }

    public void setEnumValueCode(String enumValueCode) {
        this.enumValueCode = enumValueCode;
    }

    public String getEnumValueId() {
        return enumValueId;
    }

    public void setEnumValueId(String enumValueId) {
        this.enumValueId = enumValueId;
    }

    public String getEnumValueName() {
        return enumValueName;
    }

    public void setEnumValueName(String enumValueName) {
        this.enumValueName = enumValueName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
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
