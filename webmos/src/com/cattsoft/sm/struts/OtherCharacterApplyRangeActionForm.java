package com.cattsoft.sm.struts;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-19 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class OtherCharacterApplyRangeActionForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String characterApplyRangeId;

    private String characteristicId;

    private String applyType;

    private String[] rangIds;

    private String[] types;

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getRangIds() {
        return rangIds;
    }

    public void setRangIds(String[] rangIds) {
        this.rangIds = rangIds;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getCharacterApplyRangeId() {
        return characterApplyRangeId;
    }

    public void setCharacterApplyRangeId(String characterApplyRangeId) {
        this.characterApplyRangeId = characterApplyRangeId;
    }

    public String getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(String characteristicId) {
        this.characteristicId = characteristicId;
    }

}
