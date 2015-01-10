package com.cattsoft.sm.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SMDataRangeActionForm extends ActionForm {
	
    private String createFlag;

    private String dataRangeId;

    private String rangeName;

    private String sts;

    private Long[] dataRangeIds;

    private String areaId;

    private List areaSel;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public String getCreateFlag() {
        return createFlag;
    }

    public void setCreateFlag(String createFlag) {
        this.createFlag = createFlag;
    }

    public String getDataRangeId() {
        return dataRangeId;
    }

    public void setDataRangeId(String dataRangeId) {
        this.dataRangeId = dataRangeId;
    }

    public Long[] getDataRangeIds() {
        return dataRangeIds;
    }

    public void setDataRangeIds(Long[] dataRangeIds) {
        this.dataRangeIds = dataRangeIds;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    }

}
