package com.cattsoft.sm.vo;

import java.sql.Date;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-10 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StaffWorkAreaMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String staffWorkAreaId;

    private String staffId;

    /**
     * 员工姓名
     */
    private String staffName;

    private String workAreaId;
    
    private String staff112Id;
    
    private String staff112Name;
    
    private String notify;

    /**
     * 对应工区的名称
     */
    private String workAreaName;

    private String grantor;

    private String adminFlag;

    private String sts;

    private Date stsDate;

    private Date createDate;
    
    private String localNetId;
    
    private String areaId;
    
    private String adminFlagName;
    
    /***
     * 对应班组
     */
    private String maintAreaName;
    
    private String maintAreaId;

    public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getGrantor() {
        return grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffWorkAreaId() {
        return staffWorkAreaId;
    }

    public void setStaffWorkAreaId(String staffWorkAreaId) {
        this.staffWorkAreaId = staffWorkAreaId;
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

    public String getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public String getStaff112Id() {
		return staff112Id;
	}

	public void setStaff112Id(String staff112Id) {
		this.staff112Id = staff112Id;
	}

	public String getStaff112Name() {
		return staff112Name;
	}

	public void setStaff112Name(String staff112Name) {
		this.staff112Name = staff112Name;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public String getAdminFlagName() {
		return adminFlagName;
	}

	public void setAdminFlagName(String adminFlagName) {
		this.adminFlagName = adminFlagName;
	}

	public String getMaintAreaName() {
		return maintAreaName;
	}

	public void setMaintAreaName(String maintAreaName) {
		this.maintAreaName = maintAreaName;
	}

	public String getMaintAreaId() {
		return maintAreaId;
	}

	public void setMaintAreaId(String maintAreaId) {
		this.maintAreaId = maintAreaId;
	}

	public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }

}
