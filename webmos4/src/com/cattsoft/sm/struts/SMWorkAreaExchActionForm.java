package com.cattsoft.sm.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author yangkai
 *
 */
public class SMWorkAreaExchActionForm extends ActionForm {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 8190300348920226684L;

	private String exchId;

    private String sts;

    private String workAreaExchId;

    private String workAreaId;

    private String areaId;
    
    private String localNetId;

    private String[] choses;

    private String[] exchChoses;
    
    private List workTypeSel;//工区类型下拉列表 yangkai 2009-9-1
    
    private String workTypeId;//工区类型ID yangkai 2009-9-1
    
    private String exchName;//局向名称 yangkai 2009-9-1
    
    private String result=null;//增加局向后返回值 yangkai 2009-9-2
    
    private List areaSel;
    
    private List localNetSel;

    private String workAreaName;

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        return null;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
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

    public String[] getChoses() {
        return choses;
    }

    public void setChoses(String[] choses) {
        this.choses = choses;
    }

    public String[] getExchChoses() {
        return exchChoses;
    }

    public void setExchChoses(String[] exchChoses) {
        this.exchChoses = exchChoses;
    }

    public String getExchId() {
        return exchId;
    }

    public void setExchId(String exchId) {
        this.exchId = exchId;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getWorkAreaExchId() {
        return workAreaExchId;
    }

    public void setWorkAreaExchId(String workAreaExchId) {
        this.workAreaExchId = workAreaExchId;
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

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
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

	public List getWorkTypeSel() {
		return workTypeSel;
	}

	public void setWorkTypeSel(List workTypeSel) {
		this.workTypeSel = workTypeSel;
	}

	public String getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(String workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getExchName() {
		return exchName;
	}

	public void setExchName(String exchName) {
		this.exchName = exchName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
