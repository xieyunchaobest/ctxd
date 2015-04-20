package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class BulletinSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String bulletinId;

    private String title;

    private String localNetId;

    private String areaId;

    private String servDeptId;

    private String branchId;

    private String content;

    private String systemName;
    
   

    private String type;

    private String creater;

    private Timestamp createDate;

    private Timestamp releaseDate;

    private Timestamp endDate;

    private String sts;
    
    private ActionLogSVO actionLog=null;

    public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public void setBulletinId(String bulletinId) {
        this.bulletinId = bulletinId;
    }

    public String getBulletinId() {
        return bulletinId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreater() {
        return creater;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getSts() {
        return sts;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof BulletinSVO) {
            BulletinSVO another = (BulletinSVO) obj;
            equals = new EqualsBuilder().append(bulletinId, another.getBulletinId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(bulletinId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("bulletinId", getBulletinId()).toString();
    }

}