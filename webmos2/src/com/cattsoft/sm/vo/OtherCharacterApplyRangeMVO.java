package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-10-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class OtherCharacterApplyRangeMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private String characterApplyRangeId;

    private String characteristicId;

    private String applyType;

    private String typeName;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
