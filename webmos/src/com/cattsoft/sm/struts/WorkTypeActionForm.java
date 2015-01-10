package com.cattsoft.sm.struts;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-17 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class WorkTypeActionForm extends ActionForm {

    private static final long serialVersionUID = 1L;

    private String workTypeId;

    private String name;

    private String abbWord;

    private String type;

    private Date createDate;

    private String[] workTypeIds;

    private String coMeth;

    public String getCoMeth() {
        return coMeth;
    }

    public void setCoMeth(String coMeth) {
        this.coMeth = coMeth;
    }

    public String getAbbWord() {
        return abbWord;
    }

    public void setAbbWord(String abbWord) {
        this.abbWord = abbWord;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String[] getWorkTypeIds() {
        return workTypeIds;
    }

    public void setWorkTypeIds(String[] workTypeIds) {
        this.workTypeIds = workTypeIds;
    }

}
