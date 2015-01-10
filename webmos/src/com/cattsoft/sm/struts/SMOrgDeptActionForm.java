package com.cattsoft.sm.struts;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SMOrgDeptActionForm extends ActionForm {
	
    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    }

    private String deptId;

    private String deptName;

    private String areaId;

    private String parentDeptId;

    private String localNetId;

    private String servDeptId;

    private String adminStaffId;

    private String deptType;

    private String rootFlag;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private String deptDesc;

    private String address;

    private String postNbr;

    private String branchId;

    private String srcUrl;

    private List localNetSel;

    private List areaSel;

    private List deptTypeList;
    
    //用于存放左边下拉框的服务区id
    private String parentAreaId;
    
    //  用于存放左边下拉框的本地网id

    private String parentLocalNetId;
    
    private List servdeptList;
    
    private List branchList;

    public String getParentLocalNetId() {
        return parentLocalNetId;
    }

    public void setParentLocalNetId(String parentLocalNetId) {
        this.parentLocalNetId = parentLocalNetId;
    }

    public String getParentAreaId() {
        return parentAreaId;
    }

    public void setParentAreaId(String parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    public List getDeptTypeList() {
        return deptTypeList;
    }

    public void setDeptTypeList(List deptTypeList) {
        this.deptTypeList = deptTypeList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminStaffId() {
        return adminStaffId;
    }

    public void setAdminStaffId(String adminStaffId) {
        this.adminStaffId = adminStaffId;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
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

    public String getParentDeptId() {
        return parentDeptId;
    }

    public void setParentDeptId(String parentDeptId) {
        this.parentDeptId = parentDeptId;
    }

    public String getPostNbr() {
        return postNbr;
    }

    public void setPostNbr(String postNbr) {
        this.postNbr = postNbr;
    }

    public String getRootFlag() {
        return rootFlag;
    }

    public void setRootFlag(String rootFlag) {
        this.rootFlag = rootFlag;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
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

	public List getServdeptList() {
		return servdeptList;
	}

	public void setServdeptList(List servdeptList) {
		this.servdeptList = servdeptList;
	}

	public List getBranchList() {
		return branchList;
	}

	public void setBranchList(List branchList) {
		this.branchList = branchList;
	}

}
