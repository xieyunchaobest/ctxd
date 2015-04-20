package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

public class WorkAreaMVO extends GenericVO {

	private static final long serialVersionUID = 1L;

	private String workTypeName;
	private String workAreaSpell;

	private String workType;

	private String workAreaId;

	private String coMeth;

	private String name;

	private String workTypeId;

	private String localNetId;
	
	private String staffId;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String stuffId) {
		this.staffId = stuffId;
	}

	private String workMode = null;

	/**
	 * 当前登录员工参与人所属本地网名称
	 */
	private String localNetName;

	/**
	 * 当前登陆员工参与人所属本地网是否为中心本地网
	 */

	private String localNetIscenter;

	private String areaId;

	/**
	 * 当前登录员工参与人所属服务区名称
	 */
	private String areaName;

	/**
	 * 当前登录员工参与人所属服务区是否为中心服务区
	 */
	private String areaIscenter;

	private String servDeptId;

	/**
	 * 当前登录员工参与人所属营维中心名称
	 */
	private String servDeptName;
	
	
	// 社区与网格的维护关系
	private String workAreaObjId = null;
	public String getWorkAreaObjId() {
		return workAreaObjId;
	}

	public void setWorkAreaObjId(String workAreaObjId) {
		this.workAreaObjId = workAreaObjId;
	}

	public String getServDeptSts() {
		return servDeptSts;
	}

	public void setServDeptSts(String servDeptSts) {
		this.servDeptSts = servDeptSts;
	}

	public String getWorkAreaObjSts() {
		return workAreaObjSts;
	}

	public void setWorkAreaObjSts(String workAreaObjSts) {
		this.workAreaObjSts = workAreaObjSts;
	}

	private String servDeptSts = null;
	private String workAreaObjSts = null;

	private String branchId;

	/**
	 * 当前登录员工参与人所属支局名称
	 */
	private String branchName;

	private String dispatchLevel;

	private String parentWorkAreaId;
	private String parentWorkAreaName;

	private String standardCode;

	private String sts;

	private Date stsDate;

	private Date createDate;

	private String channelId;

	private String channelName;

	private String adminFlag;
	
	// 社区与工区的维护关系
	private String maintWorkAreaId = null;
	private String workAreaName = null;
	private String maintAreaId = null;
	private String maintAreaName = null;
	private String maintAreaSts = null;
	private String maintWorkAreaSts = null;
    private ActionLogSVO actionLog=new ActionLogSVO();

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

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

	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		flagAreaId = 1;
		this.areaId = areaId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		flagBranchId = 1;
		this.branchId = branchId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
		flagLocalNetId = 1;
		this.localNetId = localNetId;
	}

	public String getLocalNetIscenter() {
		return localNetIscenter;
	}

	public void setLocalNetIscenter(String localNetIscenter) {
		this.localNetIscenter = localNetIscenter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		flagWorkAreaName = 1;
		this.name = name;
	}

	public String getParentWorkAreaId() {
		return parentWorkAreaId;
	}

	public void setParentWorkAreaId(String parentWorkAreaId) {
		this.parentWorkAreaId = parentWorkAreaId;
	}

	public String getServDeptId() {
		return servDeptId;
	}

	public void setServDeptId(String servDeptId) {
		flagServDeptId = 1;
		this.servDeptId = servDeptId;
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
		flagSts = 1;
		this.sts = sts;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
	}

	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(String workAreaId) {
		flagWorkAreaId = 1;
		this.workAreaId = workAreaId;
	}

	public String getAreaIscenter() {
		return areaIscenter;
	}

	public void setAreaIscenter(String areaIscenter) {
		this.areaIscenter = areaIscenter;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		flagWorkTypeId = 1;
		this.workTypeId = workTypeId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getServDeptName() {
		return servDeptName;
	}

	public void setServDeptName(String servDeptName) {
		this.servDeptName = servDeptName;
	}

	public String getCoMeth() {
		return coMeth;
	}

	public void setCoMeth(String coMeth) {
		this.coMeth = coMeth;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}

	private String termType;
	private String termNbr;
	private String remarks;

	private String termTypeName;
	private String workModeName;
	private String stsName;
	

	public String getParentWorkAreaName() {
		return parentWorkAreaName;
	}

	public void setParentWorkAreaName(String parentWorkAreaName) {
		this.parentWorkAreaName = parentWorkAreaName;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getTermNbr() {
		return termNbr;
	}

	public void setTermNbr(String termNbr) {
		this.termNbr = termNbr;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTermTypeName() {
		return termTypeName;
	}

	public void setTermTypeName(String termTypeName) {
		this.termTypeName = termTypeName;
	}

	public String getWorkModeName() {
		return workModeName;
	}

	public void setWorkModeName(String workModeName) {
		this.workModeName = workModeName;
	}

	public String getStsName() {
		return stsName;
	}

	public void setStsName(String stsName) {
		this.stsName = stsName;
	}

	private int flagLocalNetId = 0;
	private int flagAreaId = 0;
	private int flagServDeptId = 0;
	private int flagBranchId = 0;
	private int flagWorkAreaId = 0;
	private int flagWorkAreaName = 0;
	private int flagWorkTypeId = 0;
	private int flagSts = 0;

	public int getFlagLocalNetId() {
		return flagLocalNetId;
	}

	public int getFlagAreaId() {
		return flagAreaId;
	}

	public int getFlagServDeptId() {
		return flagServDeptId;
	}

	public int getFlagBranchId() {
		return flagBranchId;
	}

	public int getFlagWorkAreaId() {
		return flagWorkAreaId;
	}

	public int getFlagWorkAreaName() {
		return flagWorkAreaName;
	}

	public int getFlagWorkTypeId() {
		return flagWorkTypeId;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public String getMaintAreaId() {
		return maintAreaId;
	}

	public void setMaintAreaId(String maintAreaId) {
		this.maintAreaId = maintAreaId;
	}

	public String getMaintAreaName() {
		return maintAreaName;
	}

	public void setMaintAreaName(String maintAreaName) {
		this.maintAreaName = maintAreaName;
	}

	public String getMaintAreaSts() {
		return maintAreaSts;
	}

	public void setMaintAreaSts(String maintAreaSts) {
		this.maintAreaSts = maintAreaSts;
	}

	public String getMaintWorkAreaId() {
		return maintWorkAreaId;
	}

	public void setMaintWorkAreaId(String maintWorkAreaId) {
		this.maintWorkAreaId = maintWorkAreaId;
	}

	public String getMaintWorkAreaSts() {
		return maintWorkAreaSts;
	}

	public void setMaintWorkAreaSts(String maintWorkAreaSts) {
		this.maintWorkAreaSts = maintWorkAreaSts;
	}

	public String getWorkAreaName() {
		return workAreaName;
	}

	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}

	public String getWorkAreaSpell() {
		return workAreaSpell;
	}

	public void setWorkAreaSpell(String workAreaSpell) {
		this.workAreaSpell = workAreaSpell;
	}
	

}