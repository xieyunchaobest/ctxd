package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;


/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-12 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class ExchActionForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String chbLocalNetId;          //add by xcj
	
	private String chbAreaId;           //add by xcj
	
	private String chbServDeptId;         //add by xcj
	
	private String chbExchType;           //add by xcj
	
	private String chbSts;               //add by xcj

    private String exchId;

    private String name;

    private String localNetId;              
 
    private String areaId;                   

    private String servDeptId;              

    private String branchId;

    private String ruleAreaId;

    private String abbrevName;

    private String code;

    private String exchType;               

    private String address;

    private Date commDate;

    private String standardCode;

    private String sts;                    

    private Date stsDate;

    private Date createDate;

    private List servDeptSel;

    private List branchSel;

    private String[] exchIds;

    private List ruleAreaSel;

    private List localNetSel;
    
    private List areaSel;
    
    private List stsList;   //add by xcj
    
    private List exchTypeList;   //add by xcj
    
   
    public List getExchTypeList() {
		return exchTypeList;
	}

	public void setExchTypeList(List exchTypeList) {
		this.exchTypeList = exchTypeList;
	}

	public List getStsList() {
		return stsList;
	}

	public void setStsList(List stsList) {
		this.stsList = stsList;
	}

	public List getAreaSel() {
        return areaSel;
    }

    public void setAreaSel(List areaSel) {
        this.areaSel = areaSel;
    }

    public List getLocalNetSel() {
        return localNetSel;
    }

    public void setLocalNetSel(List localNetSel) {
        this.localNetSel = localNetSel;
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public List getBranchSel() {
        return branchSel;
    }

    public void setBranchSel(List branchSel) {
        this.branchSel = branchSel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCommDate() {
        return commDate;
    }

    public void setCommDate(Date commDate) {
        this.commDate = commDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExchId() {
        return exchId;
    }

    public void setExchId(String exchId) {
        this.exchId = exchId;
    }

    public String[] getExchIds() {
        return exchIds;
    }

    public void setExchIds(String[] exchIds) {
        this.exchIds = exchIds;
    }

    public String getExchType() {
        return exchType;
    }

    public void setExchType(String exchType) {
        this.exchType = exchType;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleAreaId() {
        return ruleAreaId;
    }

    public void setRuleAreaId(String ruleAreaId) {
        this.ruleAreaId = ruleAreaId;
    }

    public List getRuleAreaSel() {
        return ruleAreaSel;
    }

    public void setRuleAreaSel(List ruleAreaSel) {
        this.ruleAreaSel = ruleAreaSel;
    }

    public String getServDeptId() {

        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public List getServDeptSel() {
        return servDeptSel;
    }

    public void setServDeptSel(List servDeptSel) {
        this.servDeptSel = servDeptSel;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
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

	public String getChbLocalNetId() {
		return chbLocalNetId;
	}

	public void setChbLocalNetId(String chbLocalNetId) {
		this.chbLocalNetId = chbLocalNetId;
	}

	public String getChbAreaId() {
		return chbAreaId;
	}

	public void setChbAreaId(String chbAreaId) {
		this.chbAreaId = chbAreaId;
	}

	public String getChbExchType() {
		return chbExchType;
	}

	public void setChbExchType(String chbExchType) {
		this.chbExchType = chbExchType;
	}

	public String getChbServDeptId() {
		return chbServDeptId;
	}

	public void setChbServDeptId(String chbServDeptId) {
		this.chbServDeptId = chbServDeptId;
	}

	public String getChbSts() {
		return chbSts;
	}

	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}

}
