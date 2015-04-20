package com.cattsoft.sm.struts;

import org.apache.struts.action.ActionForm;

import com.cattsoft.sm.vo.AppColumnSVO;
import com.cattsoft.sm.vo.AppTableSVO;
import com.cattsoft.sm.vo.DomainSVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-19 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StatusActionForm extends ActionForm {

	private String tableName;

	private String columnName;

	private String stsId;

	private String stsWords;

	private String orderId;

	private AppTableSVO appTableSVO = new AppTableSVO();

	private AppColumnSVO appColumnSVO = new AppColumnSVO();

	private String[] domainValues;

	private String[] domainMeanings;

	private String[] orderIds;

	private String[] applScopes;

	private DomainSVO domainSVO = new DomainSVO();

	private String idStr;

	private String[] idStrs;

	// lilin[20080819]
	private String chbTableName = null;

	private String chbColumnName = null;
	
	private String chbStsId = null;

	// end lilin[20080819]

	public String getChbStsId() {
		return chbStsId;
	}

	public void setChbStsId(String chbStsId) {
		this.chbStsId = chbStsId;
	}

	public String[] getIdStrs() {
		return idStrs;
	}

	public void setIdStrs(String[] idStrs) {
		this.idStrs = idStrs;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public AppColumnSVO getAppColumnSVO() {
		return appColumnSVO;
	}

	public void setAppColumnSVO(AppColumnSVO appColumnSVO) {
		this.appColumnSVO = appColumnSVO;
	}

	public AppTableSVO getAppTableSVO() {
		return appTableSVO;
	}

	public void setAppTableSVO(AppTableSVO appTableSVO) {
		this.appTableSVO = appTableSVO;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public DomainSVO getDomainSVO() {
		return domainSVO;
	}

	public void setDomainSVO(DomainSVO domainSVO) {
		this.domainSVO = domainSVO;
	}

	public String[] getApplScopes() {
		return applScopes;
	}

	public void setApplScopes(String[] applScopes) {
		this.applScopes = applScopes;
	}

	public String[] getDomainMeanings() {
		return domainMeanings;
	}

	public void setDomainMeanings(String[] domainMeanings) {
		this.domainMeanings = domainMeanings;
	}

	public String[] getDomainValues() {
		return domainValues;
	}

	public void setDomainValues(String[] domainValues) {
		this.domainValues = domainValues;
	}

	public String[] getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String[] orderIds) {
		this.orderIds = orderIds;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStsId() {
		return stsId;
	}

	public void setStsId(String stsId) {
		this.stsId = stsId;
	}

	public String getStsWords() {
		return stsWords;
	}

	public void setStsWords(String stsWords) {
		this.stsWords = stsWords;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getChbColumnName() {
		return chbColumnName;
	}

	public void setChbColumnName(String chbColumnName) {
		this.chbColumnName = chbColumnName;
	}

	public String getChbTableName() {
		return chbTableName;
	}

	public void setChbTableName(String chbTableName) {
		this.chbTableName = chbTableName;
	}

}
