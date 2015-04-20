package com.cattsoft.sm.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SMWorkAreaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	// add by gaofei at 2008-10-14
	private String chbLocalNetName;
	private String chbLocalNetId;
	private String chbAreaId;
	private String chbServDeptId;
	private String chbBranchId;
	private String chbWorkTypeId;
	private String chbSts;
	// the end
	private String areaId;

	private List areaSel;

	private String dispatchLevel;

	private String localNetId;

	private String localNetName;

	private String name;

	private String parentId;
	
	private String parentName;

	private String servDeptId;

	private String standardCode;

	private String sts;

	private String workAreaId;

	private String workTypeId;

	private List workTypeSel;

	private List servDeptSel;

	private String[] choses;

	private String changeInPage; // localnet变，area变，action将forwoard的页面

	// private List localNets; // 员工可访问的本地网。

	private String branchId;

	private List branchSel;

	private String name1;

	private String channelId;

	private String channelName;

	private String workMode = null;

	private List workModeList = null;
	
	private List localNetSel = null;
	private String workAreaName = null;
	
	//add by gaofei
	private String termType;
	private List termTypeList;
	private String termNbr;
	private String remarks; 

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTermNbr() {
		return termNbr;
	}

	public void setTermNbr(String termNbr) {
		this.termNbr = termNbr;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public List getWorkModeList() {
		return workModeList;
	}

	public void setWorkModeList(List workModeList) {
		this.workModeList = workModeList;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}
	//edd of add

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
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

	public String getChangeInPage() {
		return changeInPage;
	}

	public void setChangeInPage(String changeInPage) {
		this.changeInPage = changeInPage;
	}

	public String[] getChoses() {
		return choses;
	}

	public void setChoses(String[] choses) {
		this.choses = choses;
	}

	public String getDispatchLevel() {
		return dispatchLevel;
	}

	public void setDispatchLevel(String dispatchLevel) {
		this.dispatchLevel = dispatchLevel;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(String workAreaId) {
		this.workAreaId = workAreaId;
	}

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public List getWorkTypeSel() {
		return workTypeSel;
	}

	public void setWorkTypeSel(List workTypeSel) {
		this.workTypeSel = workTypeSel;
	}

	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getChbLocalNetName() {
		return chbLocalNetName;
	}

	public void setChbLocalNetName(String chbLocalNetName) {
		this.chbLocalNetName = chbLocalNetName;
	}

	public String getChbAreaId() {
		return chbAreaId;
	}

	public void setChbAreaId(String chbAreaId) {
		this.chbAreaId = chbAreaId;
	}

	public String getChbServDeptId() {
		return chbServDeptId;
	}

	public void setChbServDeptId(String chbServDeptId) {
		this.chbServDeptId = chbServDeptId;
	}

	public String getChbBranchId() {
		return chbBranchId;
	}

	public void setChbBranchId(String chbBranchId) {
		this.chbBranchId = chbBranchId;
	}

	public String getChbWorkTypeId() {
		return chbWorkTypeId;
	}

	public void setChbWorkTypeId(String chbWorkTypeId) {
		this.chbWorkTypeId = chbWorkTypeId;
	}

	public String getChbSts() {
		return chbSts;
	}

	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getChbLocalNetId() {
		return chbLocalNetId;
	}

	public void setChbLocalNetId(String chbLocalNetId) {
		this.chbLocalNetId = chbLocalNetId;
	}

	private List localNetList;

	public List getLocalNetList() {
		return localNetList;
	}

	public void setLocalNetList(List localNetList) {
		this.localNetList = localNetList;
	}

	private String chbWorkAreaId;
	private String chbName;

	public String getChbWorkAreaId() {
		return chbWorkAreaId;
	}

	public void setChbWorkAreaId(String chbWorkAreaId) {
		this.chbWorkAreaId = chbWorkAreaId;
	}

	public String getChbName() {
		return chbName;
	}

	public void setChbName(String chbName) {
		this.chbName = chbName;
	}

	public List getTermTypeList() {
		return termTypeList;
	}

	public void setTermTypeList(List termTypeList) {
		this.termTypeList = termTypeList;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List getLocalNetSel() {
		return localNetSel;
	}

	public void setLocalNetSel(List localNetSel) {
		this.localNetSel = localNetSel;
	}

	public String getWorkAreaName() {
		return workAreaName;
	}

	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}

}
