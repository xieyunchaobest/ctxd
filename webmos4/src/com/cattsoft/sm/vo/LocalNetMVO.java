package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

public class LocalNetMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String localNetId;

    private String abbrevName;

    private String name;

    private String distNbr;

    private String iscenter;

    private String sts;

    private Date stsDate;

    private Date createDate;

    private String workArea;

    private String cust;

    private String areaid;

    private String servdeptid;

    private String branchid;

    private String msarea;

    private String maint;

    private String jfLocalNetId;

    public String getJfLocalNetId() {
        return jfLocalNetId;
    }

    public void setJfLocalNetId(String jfLocalNetId) {
        this.jfLocalNetId = jfLocalNetId;
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getDistNbr() {
        return distNbr;
    }

    public void setDistNbr(String distNbr) {
        this.distNbr = distNbr;
    }

    public String getIscenter() {
        return iscenter;
    }

    public void setIscenter(String iscenter) {
        this.iscenter = iscenter;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getMaint() {
        return maint;
    }

    public void setMaint(String maint) {
        this.maint = maint;
    }

    public String getMsarea() {
        return msarea;
    }

    public void setMsarea(String msarea) {
        this.msarea = msarea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServdeptid() {
        return servdeptid;
    }

    public void setServdeptid(String servdeptid) {
        this.servdeptid = servdeptid;
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

    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }

}