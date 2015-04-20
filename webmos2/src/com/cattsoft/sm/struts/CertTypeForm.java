/**
 * 
 */
package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.sm.vo.CertTypeMVO;


/**
 * Title:系统管理-员工管理-证件类型管理<br>
 * Description:证件类型管理Form<br>
 * Copyright:Copyright (C) 2009<br>
 * Company:cattsoft<br>
 * 
 * @author 杨凯 2009-4-28<br>
 * @version 1.0 <br>
 * 
 */

public class CertTypeForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	// 证件类型MVO
	private CertTypeMVO certTypeMVO = null;

	private String certTypeId = null;

	// 证件类型名字
	private String chbCertTypeName = null;

	private String certTypeName = null;

	private String sts = null;
	
	// 证件类型状态
	private String chbCertTypeSts = null;

	// 证件类型状态列表
	private List stsList = null;

	// 证件类型标识集
	private String[] chbCertTypeId = null;

	public CertTypeMVO getCertTypeMVO() {
		return certTypeMVO;
	}

	public void setCertTypeMVO(CertTypeMVO certTypeMVO) {
		this.certTypeMVO = certTypeMVO;
	}

	public String getChbCertTypeName() {
		return chbCertTypeName;
	}

	public void setChbCertTypeName(String chbCertTypeName) {
		this.chbCertTypeName = chbCertTypeName;
	}

	public String getChbCertTypeSts() {
		return chbCertTypeSts;
	}

	public void setChbCertTypeSts(String chbCertTypeSts) {
		this.chbCertTypeSts = chbCertTypeSts;
	}

	public List getStsList() {
		return stsList;
	}

	public void setStsList(List stsList) {
		this.stsList = stsList;
	}

	public String[] getChbCertTypeId() {
		return chbCertTypeId;
	}

	public void setChbCertTypeId(String[] chbCertTypeId) {
		this.chbCertTypeId = chbCertTypeId;
	}

	public CertTypeForm() {
		super();
		certTypeMVO = new CertTypeMVO();
	}

	public String getCertTypeId() {
		return certTypeId;
	}

	public void setCertTypeId(String certTypeId) {
		this.certTypeId = certTypeId;
	}

	public String getCertTypeName() {
		return certTypeName;
	}

	public void setCertTypeName(String certTypeName) {
		this.certTypeName = certTypeName;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getExpDateStr() {
		return DateUtil.dateTime2Str(this.getCertTypeMVO().getExpDate());
	}

	public void setExpDateStr(String expDate) throws AppException, SysException {
		
		this.getCertTypeMVO().setExpDate(DateUtil.str2DateTime(expDate));
	}

}
