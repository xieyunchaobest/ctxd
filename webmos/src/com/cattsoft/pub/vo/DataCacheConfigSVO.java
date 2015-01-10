package com.cattsoft.pub.vo;
 

import com.cattsoft.pub.vo.GenericVO;

import java.util.*; 

/**
 * DataCacheConfigSVO
 * 
 * @author £º°×Ð¡ÁÁ¡£
 * @version 1.0 2007-5-14
 *          <p>
 *          Company: ´óÌÆÈí¼þ
 *          </p>
 */
public class DataCacheConfigSVO extends GenericVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2322402609254773906L;

	private String cacheId = null;

	private String cacheValue = null;

	private String condition = null;

	private String conditionValue = null;

	private String dataCacheConfigId = null;

	private String hashMap = null;

	private String orderId = null;

	private String parentId = null;

	private String remarks = null;

	private String sts = null;

	private Date stsDate = null;

	private String tableName = null;

	private String treeFlag = null;

	private int flagCacheId = 0;

	private int flagCacheValue = 0;

	private int flagCondition = 0;

	private int flagConditionValue = 0;

	private int flagDataCacheConfigId = 0;

	private int flagHashMap = 0;

	private int flagOrderId = 0;

	private int flagParentId = 0;

	private int flagRemarks = 0;

	private int flagSts = 0;

	private int flagStsDate = 0;

	private int flagTableName = 0;

	private int flagTreeFlag = 0;

	public String getCacheId() {
		return cacheId;
	}

	public void setCacheId(String newValue) {
		this.cacheId = newValue;
		flagCacheId = 1;
	}

	public int getFlagCacheId() {
		return flagCacheId;
	}

	public String getCacheValue() {
		return cacheValue;
	}

	public void setCacheValue(String newValue) {
		this.cacheValue = newValue;
		flagCacheValue = 1;
	}

	public int getFlagCacheValue() {
		return flagCacheValue;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String newValue) {
		this.condition = newValue;
		flagCondition = 1;
	}

	public int getFlagCondition() {
		return flagCondition;
	}

	public String getConditionValue() {
		return conditionValue;
	}

	public void setConditionValue(String newValue) {
		this.conditionValue = newValue;
		flagConditionValue = 1;
	}

	public int getFlagConditionValue() {
		return flagConditionValue;
	}

	public String getDataCacheConfigId() {
		return dataCacheConfigId;
	}

	public void setDataCacheConfigId(String newValue) {
		this.dataCacheConfigId = newValue;
		flagDataCacheConfigId = 1;
	}

	public int getFlagDataCacheConfigId() {
		return flagDataCacheConfigId;
	}

	public String getHashMap() {
		return hashMap;
	}

	public void setHashMap(String newValue) {
		this.hashMap = newValue;
		flagHashMap = 1;
	}

	public int getFlagHashMap() {
		return flagHashMap;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String newValue) {
		this.orderId = newValue;
		flagOrderId = 1;
	}

	public int getFlagOrderId() {
		return flagOrderId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String newValue) {
		this.parentId = newValue;
		flagParentId = 1;
	}

	public int getFlagParentId() {
		return flagParentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String newValue) {
		this.remarks = newValue;
		flagRemarks = 1;
	}

	public int getFlagRemarks() {
		return flagRemarks;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String newValue) {
		this.sts = newValue;
		flagSts = 1;
	}

	public int getFlagSts() {
		return flagSts;
	}

	public Date getStsDate() {
		return stsDate;
	}

	public void setStsDate(Date newValue) {
		this.stsDate = newValue;
		flagStsDate = 1;
	}

	public int getFlagStsDate() {
		return flagStsDate;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String newValue) {
		this.tableName = newValue;
		flagTableName = 1;
	}

	public int getFlagTableName() {
		return flagTableName;
	}

	public String getTreeFlag() {
		return treeFlag;
	}

	public void setTreeFlag(String newValue) {
		this.treeFlag = newValue;
		flagTreeFlag = 1;
	}

	public int getFlagTreeFlag() {
		return flagTreeFlag;
	}

	public void clearFlagCacheId() {
		flagCacheId = 0;
	}

	public void clearFlagCacheValue() {
		flagCacheValue = 0;
	}

	public void clearFlagCondition() {
		flagCondition = 0;
	}

	public void clearFlagConditionValue() {
		flagConditionValue = 0;
	}

	public void clearFlagDataCacheConfigId() {
		flagDataCacheConfigId = 0;
	}

	public void clearFlagHashMap() {
		flagHashMap = 0;
	}

	public void clearFlagOrderId() {
		flagOrderId = 0;
	}

	public void clearFlagParentId() {
		flagParentId = 0;
	}

	public void clearFlagRemarks() {
		flagRemarks = 0;
	}

	public void clearFlagSts() {
		flagSts = 0;
	}

	public void clearFlagStsDate() {
		flagStsDate = 0;
	}

	public void clearFlagTableName() {
		flagTableName = 0;
	}

	public void clearFlagTreeFlag() {
		flagTreeFlag = 0;
	}

	public void clearAll() {
		flagCacheId = 0;
		flagCacheValue = 0;
		flagCondition = 0;
		flagConditionValue = 0;
		flagDataCacheConfigId = 0;
		flagHashMap = 0;
		flagOrderId = 0;
		flagParentId = 0;
		flagRemarks = 0;
		flagSts = 0;
		flagStsDate = 0;
		flagTableName = 0;
		flagTreeFlag = 0;

	}
}
