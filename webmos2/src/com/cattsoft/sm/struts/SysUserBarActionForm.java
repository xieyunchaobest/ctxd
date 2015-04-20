package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.cattsoft.sm.vo.SysUserBarMVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-14 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class SysUserBarActionForm extends ActionForm {

    private static final long serialVersionUID = 1L;
    
    private SysUserBarMVO subMVO = new SysUserBarMVO();

    private String sysUserBarId;

    private String sysUserId;

    private String funcNodeId;

    private String sts;
    
    private String chbSts;
    
    private List stsList;

    private Date stsDate;

    private String funcNodeName;
    
    private String chbFuncNodeName;

    private String subSystemName;
    
    private String chbSubSystemName;
    
    private List subSystems;

    private String html;

    private String[] sysUserBarIds;
    
    /*用于页面向Action传值用*/
    private String querySysBarId;

    public String getQuerySysBarId() {
		return querySysBarId;
	}

	public void setQuerySysBarId(String querySysBarId) {
		this.querySysBarId = querySysBarId;
	}

	public String[] getSysUserBarIds() {
        return sysUserBarIds;
    }

    public void setSysUserBarIds(String[] sysUserBarIds) {
        this.sysUserBarIds = sysUserBarIds;
    }

    public String getFuncNodeId() {
        return funcNodeId;
    }

    public void setFuncNodeId(String funcNodeId) {
        this.funcNodeId = funcNodeId;
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

    public String getSubSystemName() {
        return subSystemName;
    }

    public void setSubSystemName(String subSystemName) {
        this.subSystemName = subSystemName;
    }

    public String getSysUserBarId() {
        return sysUserBarId;
    }

    public void setSysUserBarId(String sysUserBarId) {
        this.sysUserBarId = sysUserBarId;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public SysUserBarMVO getSubMVO() {
        return subMVO;
    }

    public void setSubMVO(SysUserBarMVO subMVO) {
        this.subMVO = subMVO;
    }

    public String getChbSubSystemName() {
        return chbSubSystemName;
    }

    public void setChbSubSystemName(String chbSubSystemName) {
        this.chbSubSystemName = chbSubSystemName;
    }

    public List getSubSystems() {
        return subSystems;
    }

    public void setSubSystems(List subSystems) {
        this.subSystems = subSystems;
    }

    public String getChbFuncNodeName() {
        return chbFuncNodeName;
    }

    public void setChbFuncNodeName(String chbFuncNodeName) {
        this.chbFuncNodeName = chbFuncNodeName;
    }

    public String getChbSts() {
        return chbSts;
    }

    public void setChbSts(String chbSts) {
        this.chbSts = chbSts;
    }

    public List getStsList() {
        return stsList;
    }

    public void setStsList(List stsList) {
        this.stsList = stsList;
    }

}
