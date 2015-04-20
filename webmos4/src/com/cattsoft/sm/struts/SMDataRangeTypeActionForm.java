package com.cattsoft.sm.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class SMDataRangeTypeActionForm extends ActionForm {

    private String dataRangePattern;

    private String dataRangeTypeId;

    private String dataRangeTypeName;

    private String setRangeComp;

    private String version;

    private Long[] choses;

    private Long[] dataRangeTypeIds;

    public Long[] getChoses() {
        return choses;
    }

    public void setChoses(Long[] choses) {
        this.choses = choses;
    }

    public String getDataRangePattern() {
        return dataRangePattern;
    }

    public void setDataRangePattern(String dataRangePattern) {
        this.dataRangePattern = dataRangePattern;
    }

    public String getDataRangeTypeId() {
        return dataRangeTypeId;
    }

    public void setDataRangeTypeId(String dataRangeTypeId) {
        this.dataRangeTypeId = dataRangeTypeId;
    }

    public Long[] getDataRangeTypeIds() {
        return dataRangeTypeIds;
    }

    public void setDataRangeTypeIds(Long[] dataRangeTypeIds) {
        this.dataRangeTypeIds = dataRangeTypeIds;
    }

    public String getDataRangeTypeName() {
        return dataRangeTypeName;
    }

    public void setDataRangeTypeName(String dataRangeTypeName) {
        this.dataRangeTypeName = dataRangeTypeName;
    }

    public String getSetRangeComp() {
        return setRangeComp;
    }

    public void setSetRangeComp(String setRangeComp) {
        this.setRangeComp = setRangeComp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    }
}
