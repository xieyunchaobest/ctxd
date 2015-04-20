package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-26 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StaffExtendMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    /**
     * 当前登录员工信息
     */
    private StaffSVO staffSVO = new StaffSVO();

    /**
     * 当前登录员工参与人ID
     */
    private String partyId;

    /**
     * 当前登录员工参与人名称
     */
    private String partyName;

    /**
     * 当前登录员工参与人所属本地网
     */
    private String localNetId;

    /**
     * 当前登录员工参与人所属本地网名称
     */
    private String localNetName;

    /**
     * 当前登录员工参与人所属本地网是否为中心本地网
     */
    private String localNetIscenter;

    /**
     * 当前登录员工参与人所属服务区
     */
    private String areaId;

    /**
     * 当前登录员工参与人所属服务区名称
     */
    private String areaName;

    /**
     * 当前登录员工参与人所属服务区是否为中心服务区
     */
    private String areaIscenter;

    /**
     * 当前登录员工参与人所属营维中心
     */
    private String servDeptId;

    /**
     * 当前登录员工参与人所属营维中心名称
     */
    private String servDeptName;

    /**
     * 当前登录员工参与人所属支局
     */
    private String branchId;

    /**
     * 当前登录员工参与人所属支局名称
     */
    private String branchName;

    /**
     * 当前登录员工参与人状态
     */
    private String partySts;

    /**
     * 当前登录员工参与人类型
     */
    private String partyType;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetName() {
        return localNetName;
    }

    public void setLocalNetName(String localNetName) {
        this.localNetName = localNetName;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartySts() {
        return partySts;
    }

    public void setPartySts(String partySts) {
        this.partySts = partySts;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public String getServDeptName() {
        return servDeptName;
    }

    public void setServDeptName(String servDeptName) {
        this.servDeptName = servDeptName;
    }

    public StaffSVO getStaffSVO() {
        return staffSVO;
    }

    public void setStaffSVO(StaffSVO staffSVO) {
        this.staffSVO = staffSVO;
    }

    public String getAreaIscenter() {
        return areaIscenter;
    }

    public void setAreaIscenter(String areaIscenter) {
        this.areaIscenter = areaIscenter;
    }

    public String getLocalNetIscenter() {
        return localNetIscenter;
    }

    public void setLocalNetIscenter(String localNetIscenter) {
        this.localNetIscenter = localNetIscenter;
    }

}
