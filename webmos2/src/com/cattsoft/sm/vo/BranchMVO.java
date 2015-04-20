package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-6 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class BranchMVO extends GenericVO {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1340899441112253584L;

	private String branchId;

    private String localNetId;

    private String areaId;

    private String servDeptId;

    private String abbrevName;

    private String name;

    private String sts;

    private Date stsDate;

    private Date createDate;

    /**
     * 当前登录员工参与人所属本地网名称
     */
    private String localNetName;

    /**
     * 当前登陆员工参与人所属本地网是否为中心本地网
     */

    private String localNetIscenter;

    /**
     * 当前登录员工参与人所属服务区名称
     */
    private String areaName;

    /**
     * 当前登录员工参与人所属服务区是否为中心服务区
     */
    private String areaIscenter;

    /**
     * 当前登录员工参与人所属营维中心名称
     */
    private String servDeptName;

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
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

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetIscenter() {
        return localNetIscenter;
    }

    public void setLocalNetIscenter(String localNetIscenter) {
        this.localNetIscenter = localNetIscenter;
    }

    public String getLocalNetName() {
        return localNetName;
    }

    public void setLocalNetName(String localNetName) {
        this.localNetName = localNetName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
