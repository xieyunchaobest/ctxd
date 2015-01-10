package com.cattsoft.sm.vo;

import java.sql.Timestamp;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-25 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class BulletinMVO extends BulletinSVO {
	
    private static final long serialVersionUID = 1L;
    private BulletinSVO bvo;
    private Timestamp releaseDate2;
    private Timestamp endDate2;
    private String branchName;
    private String servSpecName;
    private String localNetName;
    private String areaName;
    private String stsWords;
    private String typeName;

    // 增加公共栏附件 caiqian 20100224
    private String delAttachFlag;

	public String getDelAttachFlag() {
		return delAttachFlag;
	}

	public void setDelAttachFlag(String delAttachFlag) {
		this.delAttachFlag = delAttachFlag;
	}
    private AttachmentMVO attachMVO;
    
    public AttachmentMVO getAttachMVO() {
		return attachMVO;
	}

	public void setAttachMVO(AttachmentMVO attachMVO) {
		this.attachMVO = attachMVO;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getStsWords() {
		return stsWords;
	}

	public void setStsWords(String stsWords) {
		this.stsWords = stsWords;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getServSpecName() {
		return servSpecName;
	}

	public void setServSpecName(String servSpecName) {
		this.servSpecName = servSpecName;
	}

	public BulletinSVO getBvo() {
      return bvo;
    }

    public Timestamp getEndDate2() {
      return endDate2;
    }

    public Timestamp getReleaseDate2() {
      return releaseDate2;
    }

    public void setBvo(BulletinSVO bvo) {
      this.bvo = bvo;
    }

    public void setEndDate2(Timestamp endDate2) {
      this.endDate2 = endDate2;
    }

    public void setReleaseDate2(Timestamp releaseDate2) {
      this.releaseDate2 = releaseDate2;
    }

    public BulletinMVO() {
    }

}
