package com.cattsoft.sm.struts;

import java.sql.Timestamp;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-20 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */


public class BulletinActionForm extends ActionForm {

	private String bulLocalNetId;
	
	private String bulAreaId;
	
    private String bulletinId;
    
    private String bulTitle;
    
    private String bulCreater;

    private String[] bulletinIdAry;
    private String title;

    private String localNetId;
    
    private String localNetName;

	private FormFile formfile;
    
	
	 private String areaName;

    private String areaId;

    private String servDeptId;

    private String branchId;

    private String content;

    private String systemName;

    private String type;

    private String creater;

    private Timestamp createDate;

    private String releaseDate1;

    private String endDate1;

    private String sts;

    private String releaseDate2;

    private String endDate2;

    private List localNetSel;

    private List servDeptSel;

    private List areaSel;

    private List branchSel;

    private String[] bulletinIds;
    
    private String attachmentId;
    
    private String fileName;
    
    private String delAttachFlag;

	public String getDelAttachFlag() {
		return delAttachFlag;
	}

	public void setDelAttachFlag(String delAttachFlag) {
		this.delAttachFlag = delAttachFlag;
	}

	public String[] getBulletinIds() {
        return bulletinIds;
    }

    public void setBulletinIds(String[] bulletinIds) {
        this.bulletinIds = bulletinIds;
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

    public List getBranchSel() {
        return branchSel;
    }

    public void setBranchSel(List branchSel) {
        this.branchSel = branchSel;
    }

    public String getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getEndDate1() {
        return endDate1;
    }

    public void setEndDate1(String endDate1) {
        this.endDate1 = endDate1;
    }

    public String getEndDate2() {
        return endDate2;
    }

    public void setEndDate2(String endDate2) {
        this.endDate2 = endDate2;
    }

    public String getReleaseDate1() {
        return releaseDate1;
    }

    public void setReleaseDate1(String releaseDate1) {
        this.releaseDate1 = releaseDate1;
    }

    public String getReleaseDate2() {
        return releaseDate2;
    }

    public void setReleaseDate2(String releaseDate2) {
        this.releaseDate2 = releaseDate2;
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

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public String getBulLocalNetId() {
		return bulLocalNetId;
	}

	public void setBulLocalNetId(String bulLocalNetId) {
		this.bulLocalNetId = bulLocalNetId;
	}

	public String getBulAreaId() {
		return bulAreaId;
	}

	public void setBulAreaId(String bulAreaId) {
		this.bulAreaId = bulAreaId;
	}

	public String getBulTitle() {
		return bulTitle;
	}

	public void setBulTitle(String bulTitle) {
		this.bulTitle = bulTitle;
	}

	public String getBulCreater() {
		return bulCreater;
	}

	public void setBulCreater(String bulCreater) {
		this.bulCreater = bulCreater;
	}

	public String[] getBulletinIdAry() {
		return bulletinIdAry;
	}

	public void setBulletinIdAry(String[] bulletinIdAry) {
		this.bulletinIdAry = bulletinIdAry;
	}

	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public FormFile getFormfile() {
		return formfile;
	}

	public void setFormfile(FormFile formfile) {
		this.formfile = formfile;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

}
