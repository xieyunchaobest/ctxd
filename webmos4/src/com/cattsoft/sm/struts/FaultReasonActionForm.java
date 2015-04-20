package com.cattsoft.sm.struts;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-12 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class FaultReasonActionForm extends ActionForm {

    private String reasonId;

    private String name;

    private String reasonCat;

    private String dutyFlag;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private String[] choses;

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDutyFlag() {
        return dutyFlag;
    }

    public void setDutyFlag(String dutyFlag) {
        this.dutyFlag = dutyFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReasonCat() {
        return reasonCat;
    }

    public void setReasonCat(String reasonCat) {
        this.reasonCat = reasonCat;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
    }

}
