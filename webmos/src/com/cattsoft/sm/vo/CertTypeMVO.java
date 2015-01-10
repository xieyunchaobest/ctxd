/**
 * 
 */
package com.cattsoft.sm.vo;

import com.cattsoft.sm.vo.CertTypeSVO;
import com.cattsoft.sm.vo.ActionLogSVO;

/**
 * @author yangkai
 *
 */
public class CertTypeMVO extends CertTypeSVO {
	private static final long serialVersionUID = 1L;
	private String stsName = null;
	private ActionLogSVO actionLog = null;
	

	public ActionLogSVO getActionLog() {
		return actionLog;
	}

	public void setActionLog(ActionLogSVO actionLog) {
		this.actionLog = actionLog;
	}

	public String getStsName() {
		return stsName;
	}

	public void setStsName(String stsName) {
		this.stsName = stsName;
	}	

}
