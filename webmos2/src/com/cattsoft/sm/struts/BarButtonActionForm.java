package com.cattsoft.sm.struts;

import org.apache.struts.action.ActionForm;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class BarButtonActionForm extends ActionForm {

    private String barButtonId;

    private String funcNodeId;

    private String sysUserBarId;

    private String[] choses;

    private String subSystemName;

    private String addSysUserBars;

    public String getAddSysUserBars() {
        return addSysUserBars;
    }

    public void setAddSysUserBars(String addSysUserBars) {
        this.addSysUserBars = addSysUserBars;
    }

    public String getSubSystemName() {
        return subSystemName;
    }

    public void setSubSystemName(String subSystemName) {
        this.subSystemName = subSystemName;
    }

    public String getBarButtonId() {
        return barButtonId;
    }

    public void setBarButtonId(String barButtonId) {
        this.barButtonId = barButtonId;
    }

    public String getFuncNodeId() {
        return funcNodeId;
    }

    public void setFuncNodeId(String funcNodeId) {
        this.funcNodeId = funcNodeId;
    }

    public String getSysUserBarId() {
        return sysUserBarId;
    }

    public void setSysUserBarId(String sysUserBarId) {
        this.sysUserBarId = sysUserBarId;
    }

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

}
