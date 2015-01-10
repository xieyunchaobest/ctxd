package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class SysUserBarMVO extends SysUserBarSVO {
	

    private static final long serialVersionUID = 1L;

    private String funcNodeName;

    private String subSystemName;

    private String html;
    
    private String stsDesc;


    public String getStsDesc() {
        return stsDesc;
    }

    public void setStsDesc(String stsDesc) {
        this.stsDesc = stsDesc;
    }

    public String getFuncNodeName() {
        return funcNodeName;
    }

    public void setFuncNodeName(String funcNodeName) {
        this.funcNodeName = funcNodeName;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getSubSystemName() {
        return subSystemName;
    }

    public void setSubSystemName(String subSystemName) {
        this.subSystemName = subSystemName;
    }

    public SysUserBarMVO() {
    }

}
