package com.cattsoft.sm.vo;

import java.io.*;
import com.cattsoft.pub.vo.GenericVO;
import java.util.*;
import java.sql.Clob;
import java.sql.Blob;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
public class WorkAreaSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;
    
    private String actionId = null;
    private String areaId = null;
    private String branchId = null;
    private String channelId = null;
    private Date createDate = null;
    private String dispatchLevel = null;
    private String localNetId = null;
    private String name = null;
    private String parentWorkAreaId = null;
    private String remarks = null;
    private String servDeptId = null;
    private String standardCode = null;
    private String sts = null;
    private Date stsDate = null;
    private String termNbr = null;
    private String termType = null;
    private String workAreaId = null;
    private String workMode = null;
    private String workTypeId = null;
    private int flagActionId = 0;
    private int flagAreaId = 0;
    private int flagBranchId = 0;
    private int flagChannelId = 0;
    private int flagCreateDate = 0;
    private int flagDispatchLevel = 0;
    private int flagLocalNetId = 0;
    private int flagName = 0;
    private int flagParentWorkAreaId = 0;
    private int flagRemarks = 0;
    private int flagServDeptId = 0;
    private int flagStandardCode = 0;
    private int flagSts = 0;
    private int flagStsDate = 0;
    private int flagTermNbr = 0;
    private int flagTermType = 0;
    private int flagWorkAreaId = 0;
    private int flagWorkMode = 0;
    private int flagWorkTypeId = 0;   
    
    private ActionLogSVO actionLog=null;
    
  

    public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public String getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workMode) {
        this.workMode = workMode;
        flagWorkMode=1;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
        flagChannelId=1;
    }

    public void setWorkAreaId(String workAreaId) {
        this.workAreaId = workAreaId;
        flagWorkAreaId=1;
    }

    public String getWorkAreaId() {
        return workAreaId;
    }

    public void setName(String name) {
        this.name = name;
        flagName=1;
    }

    public String getName() {
        return name;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
        flagWorkTypeId=1;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
        flagLocalNetId=1;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
        flagAreaId=1;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setServDeptId(String servDeptId) {
        this.servDeptId = servDeptId;
        flagServDeptId=1;
    }

    public String getServDeptId() {
        return servDeptId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
        flagBranchId=1;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setDispatchLevel(String dispatchLevel) {
        this.dispatchLevel = dispatchLevel;
        flagDispatchLevel=1;
    }

    public String getDispatchLevel() {
        return dispatchLevel;
    }

    public void setParentWorkAreaId(String parentWorkAreaId) {
        this.parentWorkAreaId = parentWorkAreaId;
        flagParentWorkAreaId=1;
    }

    public String getParentWorkAreaId() {
        return parentWorkAreaId;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode;
        flagStandardCode=1;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setSts(String sts) {
        this.sts = sts;
        flagSts=1;
    }

    public String getSts() {
        return sts;
    }

    public void setStsDate(Date stsDate) {
        this.stsDate = stsDate;
        flagStsDate=1;
    }

    public Date getStsDate() {
        return stsDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        flagCreateDate=1;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj instanceof WorkAreaSVO) {
            WorkAreaSVO another = (WorkAreaSVO) obj;
            equals = new EqualsBuilder().append(workAreaId, another.getWorkAreaId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(workAreaId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("workAreaId", getWorkAreaId()).toString();
    }

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
		flagActionId=1;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
		flagRemarks=1;
	}

	public String getTermNbr() {
		return termNbr;
	}

	public void setTermNbr(String termNbr) {
		this.termNbr = termNbr;
		flagTermNbr=1;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
		flagTermType=1;
	}

	public int getFlagActionId() {
		return flagActionId;
	}

	public void setFlagActionId(int flagActionId) {
		this.flagActionId = flagActionId;
	}

	public int getFlagAreaId() {
		return flagAreaId;
	}

	public void setFlagAreaId(int flagAreaId) {
		this.flagAreaId = flagAreaId;
	}

	public int getFlagBranchId() {
		return flagBranchId;
	}

	public void setFlagBranchId(int flagBranchId) {
		this.flagBranchId = flagBranchId;
	}

	public int getFlagCreateDate() {
		return flagCreateDate;
	}

	public void setFlagCreateDate(int flagCreateDate) {
		this.flagCreateDate = flagCreateDate;
	}

	public int getFlagDispatchLevel() {
		return flagDispatchLevel;
	}

	public void setFlagDispatchLevel(int flagDispatchLevel) {
		this.flagDispatchLevel = flagDispatchLevel;
	}

	public int getFlagLocalNetId() {
		return flagLocalNetId;
	}

	public void setFlagLocalNetId(int flagLocalNetId) {
		this.flagLocalNetId = flagLocalNetId;
	}

	public int getFlagName() {
		return flagName;
	}

	public void setFlagName(int flagName) {
		this.flagName = flagName;
	}

	public int getFlagParentWorkAreaId() {
		return flagParentWorkAreaId;
	}

	public void setFlagParentWorkAreaId(int flagParentWorkAreaId) {
		this.flagParentWorkAreaId = flagParentWorkAreaId;
	}

	public int getFlagRemarks() {
		return flagRemarks;
	}

	public void setFlagRemarks(int flagRemarks) {
		this.flagRemarks = flagRemarks;
	}

	public int getFlagServDeptId() {
		return flagServDeptId;
	}

	public void setFlagServDeptId(int flagServDeptId) {
		this.flagServDeptId = flagServDeptId;
	}

	public int getFlagStandardCode() {
		return flagStandardCode;
	}

	public void setFlagStandardCode(int flagStandardCode) {
		this.flagStandardCode = flagStandardCode;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public void setFlagSts(int flagSts) {
		this.flagSts = flagSts;
	}

	public int getFlagStsDate() {
		return flagStsDate;
	}

	public void setFlagStsDate(int flagStsDate) {
		this.flagStsDate = flagStsDate;
	}

	public int getFlagTermNbr() {
		return flagTermNbr;
	}

	public void setFlagTermNbr(int flagTermNbr) {
		this.flagTermNbr = flagTermNbr;
	}

	public int getFlagTermType() {
		return flagTermType;
	}

	public void setFlagTermType(int flagTermType) {
		this.flagTermType = flagTermType;
	}

	public int getFlagWorkAreaId() {
		return flagWorkAreaId;
	}

	public void setFlagWorkAreaId(int flagWorkAreaId) {
		this.flagWorkAreaId = flagWorkAreaId;
	}

	public int getFlagWorkMode() {
		return flagWorkMode;
	}

	public void setFlagWorkMode(int flagWorkMode) {
		this.flagWorkMode = flagWorkMode;
	}

	public int getFlagWorkTypeId() {
		return flagWorkTypeId;
	}

	public void setFlagWorkTypeId(int flagWorkTypeId) {
		this.flagWorkTypeId = flagWorkTypeId;
	}

	public int getFlagChannelId() {
		return flagChannelId;
	}

	public void setFlagChannelId(int flagChannelId) {
		this.flagChannelId = flagChannelId;
	}
}