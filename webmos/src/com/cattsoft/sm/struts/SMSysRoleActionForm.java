package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * <p>
 * Title: CRM系统角色信息维护Actionform
 * </p>
 * <p>
 * Description: CRM
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: CATTSoft
 * </p>
 * 
 * @author cason_lau
 * @version 1.0
 */

public class SMSysRoleActionForm extends ActionForm {
	private String chbsysRoleName;
	
	private String chbAreaId;
	
	private String chbLocalNetId;
	
	private String chbSTS;
	
    private Long[] sysRoleIds;

    private String sysRoleId;

    private Long[] funcNodeIds;

    private String sts;

    private Date stsDate;

    private String sysRoleName;

    private String roleMemo;

    private String localNetId;

    private String areaId;

    private List areaSel;

    private List localNetSel;

    private String[] sysUserRoleIds;

    public String getChbLocalNetId() {
		return chbLocalNetId;
	}

	public void setChbLocalNetId(String chbLocalNetId) {
		this.chbLocalNetId = chbLocalNetId;
	}

	public String[] getSysUserRoleIds() {
        return sysUserRoleIds;
    }

    public void setSysUserRoleIds(String[] sysUserRoleIds) {
        this.sysUserRoleIds = sysUserRoleIds;
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

    }

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

    public Long[] getFuncNodeIds() {
        return funcNodeIds;
    }

    public void setFuncNodeIds(Long[] funcNodeIds) {
        this.funcNodeIds = funcNodeIds;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public String getRoleMemo() {
        return roleMemo;
    }

    public void setRoleMemo(String roleMemo) {
        this.roleMemo = roleMemo;
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

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Long[] getSysRoleIds() {
        return sysRoleIds;
    }

    public void setSysRoleIds(Long[] sysRoleIds) {
        this.sysRoleIds = sysRoleIds;
    }

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName;
    }

	public String getChbAreaId() {
		return chbAreaId;
	}

	public void setChbAreaId(String chbAreaId) {
		this.chbAreaId = chbAreaId;
	}

	public String getChbSTS() {
		return chbSTS;
	}

	public void setChbSTS(String chbSTS) {
		this.chbSTS = chbSTS;
	}

	public String getChbsysRoleName() {
		return chbsysRoleName;
	}

	public void setChbsysRoleName(String chbsysRoleName) {
		this.chbsysRoleName = chbsysRoleName;
	}

}
