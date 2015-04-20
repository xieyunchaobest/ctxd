package com.cattsoft.sm.vo;

import java.util.*; 

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.cattsoft.pub.vo.GenericVO;

public class WorkTypeSVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    private String workTypeId;

    private String name;

    private String abbWord;

    private String type;

    private Date createDate;

    private String coMeth;    

	private String actionId = null;

	private String remarks = null;

	private String sts = null;

	private Date stsDate = null;
	
	private int flagAbbWord = 0;

	private int flagActionId = 0;

	private int flagCreateDate = 0;

	private int flagName = 0;

	private int flagRemarks = 0;

	private int flagSts = 0;

	private int flagStsDate = 0;

	private int flagType = 0;

	private int flagWorkTypeId = 0;
	
	private int flagCoMeth = 0;

	private ActionLogSVO actionLog = null;

    public String getCoMeth() {
        return coMeth;
    }

    public void setCoMeth(String coMeth) {
        this.coMeth = coMeth;
        flagCoMeth=1;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
        flagWorkTypeId=1;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }  

    public void setAbbWord(String abbWord) {
        this.abbWord = abbWord;
        flagAbbWord=1;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		flagName=1;
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

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
		flagSts=1;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date stsDate) {
		this.stsDate = stsDate;
		flagStsDate=1;
	}

	public int getFlagAbbWord() {
		return flagAbbWord;
	}

	public void setFlagAbbWord(int flagAbbWord) {
		this.flagAbbWord = flagAbbWord;
	}

	public int getFlagActionId() {
		return flagActionId;
	}

	public void setFlagActionId(int flagActionId) {
		this.flagActionId = flagActionId;
	}

	public int getFlagCreateDate() {
		return flagCreateDate;
	}

	public void setFlagCreateDate(int flagCreateDate) {
		this.flagCreateDate = flagCreateDate;
	}

	public int getFlagName() {
		return flagName;
	}

	public void setFlagName(int flagName) {
		this.flagName = flagName;
	}

	public int getFlagRemarks() {
		return flagRemarks;
	}

	public void setFlagRemarks(int flagRemarks) {
		this.flagRemarks = flagRemarks;
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

	public int getFlagType() {
		return flagType;
	}

	public void setFlagType(int flagType) {
		this.flagType = flagType;
	}

	public int getFlagWorkTypeId() {
		return flagWorkTypeId;
	}

	public void setFlagWorkTypeId(int flagWorkTypeId) {
		this.flagWorkTypeId = flagWorkTypeId;
	}

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
		
	}

	public String getAbbWord() {
        return abbWord;
    }

    public void setType(String type) {
        this.type = type;
        flagType=1;
    }

    public String getType() {
        return type;
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
        if (obj != null && obj instanceof WorkTypeSVO) {
            WorkTypeSVO another = (WorkTypeSVO) obj;
            equals = new EqualsBuilder().append(workTypeId, another.getWorkTypeId()).isEquals();
        }
        return equals;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(workTypeId).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("workTypeId", getWorkTypeId()).toString();
    }

	public int getFlagCoMeth() {
		return flagCoMeth;
	}

	public void setFlagCoMeth(int flagCoMeth) {
		this.flagCoMeth = flagCoMeth;
		
	}
}