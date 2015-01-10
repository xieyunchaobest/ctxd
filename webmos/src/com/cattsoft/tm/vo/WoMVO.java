package com.cattsoft.tm.vo;

import java.util.Date;
import java.util.Map;

/**
 * PrintTemplateSVO
 * 
 * @author ：徐海平。
 * @version 1.0 2007-5-14
 *          <p>
 *          Company: 大唐软件
 *          </p>
 */

public class WoMVO extends WoSVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String devPartyName;
	private String workType = null;
	private String reasonCatName;
	private String reasonCat;
	private String pageCode;
	private String soSeq = null;

	private String cancelFlag = null;// 撤改标志

	private String cancelSts = null;// 撤改状态

	private String soRunStsName = null;

	private String prodName = null;

	private String chgName = null;

	private String nwoNbr = null;

	private String woNbr = null;

	private String extWoNbr = null;

	// 定单号码

	private String relatedExtSoNbr = null;

	private String extSoNbr;

	private String arFlag = null;

	private String workMode = null;

	private String workModeName = null;

	private String dealFlag = null;

	private String dealFlagName = null;

	private String businessName = null;

	private String businessId = null;

	private String custId = null;

	private String custName = null;

	private String custAddr = null;

	private String custCatId = null;

	private String custCatName = null;

	private String custLevelId = null;

	private String custLevelName = null;

	private String soNbr = null;

	private String actType = null;

	private String actTypeName = null;

	private String exchId = null;

	private String exchName = null;

	private String soStaffId = null;

	private String soStaffName = null;

	private String woStaffId = null;// 施工员工

	private String woStaffName = null;

	private String rtStaffId = null;// 回单员工

	private String partyName = null;// 负责员工名称

	private String accNbr = null;

	private Date applDate = null;// 受理时间

	private String soWorkAreaName = null;

	private String soWorkAreaId = null;

	private String workAreaId = null;

	private String woType = null;

	private String woTypeName = null;

	private String priority = null;

	private String busiSts = null;

	private String busiStsName = null;

	private Date asgnDate = null; // 派发日期

	private String stepId = null; // 环节

	private String runSts = null;

	private String runStsName = null;

	// private int relaWoNbr;
	//
	// private int printCount;
	private String relaWoNbr;

	private String printCount;

	private String failReasonId = null;

	private String overtimeId = null;

	private Date preAlarmDate = null;

	private Date alarmDate = null;

	private Date busiStsDate = null;

	private String sts = null;

	private String failId = null;

	private String failName = null;

	private String remarks = null;

	// ADD by fys 070614 16:18
	private String tabpagecode = null;

	// woInfo
	private String halt;

	private String stepName;

	private String bookFlag;

	private String bookFlagName;

	private String overName;

	private String localNetId = null;

	private String areaId = null;

	private String prodId = null;

	private String chgServSpec = null;
	
	private String chgServSpecId = null;
	


	private String workAreaName = null;

	private String handleType = null;

	private Date handleDate = null;

	private String mainFlagName = null;

	private Date runStsDate = null;

	private String areaName = null;

	// 完成日期
	private Date compDate = null;

	// 工作项 WORK_ITEM_ID
	private String workItemId = null;

	private String inspInfo = null;// 外堪内容

	private String contactInfo = null;// 联系信息

	private String procInstId = null;

	private String qryFlag = null;

	private String auditFlag = null;

	private String addrLocalNetId = null;

	private String addrAreaId = null;

	private String cancelJudgeRule = null;

	private String mdfName = null; // 配线架名称

	private Date bookDate = null;

	private Date bookTime = null;
	
	private String specServId = null;//专业服务ID add by gongzhen
	
	private String staffId = null;
	
	private String eventType  = null;
	
	//对象类型	OBJECT_TYPE
	private String objectType = null;
	
	private String objectId = null;
	
	//可对应定单号码、工单号码、任务单号、告警日志ID
	private String toNbr = null;
	
	private String toObjectType = null;
	
	private String toObjectId  = null;
	
	private String fromObjectType = null;
	
	private String fromObjectId = null;
	
	//告警用 add by gongzhen
	private Map retMap = null;
	
	//联系人 add by zhaodan
	private String contactName = null;
	
	private String situated = null;//详细地址
	
	//服务保障 当前故障类别
	private String faultCat = null;
	
	//施工历时
	private String woTimeCost = null;
	//超时时间
	private String woTimeOut = null;
	
	private String pressType = null;

	//add by lijingjun for report (工单是否超时)
	private int isOverTIme;
	
	private String soCat;
	//是否告警，预警信息 N 正常 P预警 A告警
	private String alarmSts;
	
	private String staffPhoneNbr;//施工人联系电话
	
	private String prodCatId;// 业务类别
	
	private String faultReasonName;//障碍原因
	
	private Date repairDate; //修复时间
	
	private String dutyName; //责任部门

	public int getIsOverTIme() {
		return isOverTIme;
	}

	public void setIsOverTIme(int isOverTIme) {
		this.isOverTIme = isOverTIme;
	}	 

	public String getPressType() {
		return pressType;
	}

	public void setPressType(String pressType) {
		this.pressType = pressType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Map getRetMap() {
		return retMap;
	}

	public void setRetMap(Map retMap) {
		this.retMap = retMap;
	}
	public String getChgServSpecId() {
		return chgServSpecId;
	}

	public void setChgServSpecId(String chgServSpecId) {
		this.chgServSpecId = chgServSpecId;
	}
	public String getToObjectType() {
		return toObjectType;
	}

	public void setToObjectType(String toObjectType) {
		this.toObjectType = toObjectType;
	}

	public String getToObjectId() {
		return toObjectId;
	}

	public void setToObjectId(String toObjectId) {
		this.toObjectId = toObjectId;
	}

	public String getFromObjectType() {
		return fromObjectType;
	}

	public void setFromObjectType(String fromObjectType) {
		this.fromObjectType = fromObjectType;
	}

	public String getFromObjectId() {
		return fromObjectId;
	}

	public void setFromObjectId(String fromObjectId) {
		this.fromObjectId = fromObjectId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getSpecServId() {
		return specServId;
	}

	public void setSpecServId(String specServId) {
		this.specServId = specServId;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getCancelSts() {
		return cancelSts;
	}

	public void setCancelSts(String cancelSts) {
		this.cancelSts = cancelSts;
	}

	public String getQryFlag() {
		return qryFlag;
	}

	public void setQryFlag(String qryFlag) {
		this.qryFlag = qryFlag;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getWorkItemId() {
		return workItemId;
	}

	public void setWorkItemId(String workItemId) {
		this.workItemId = workItemId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getMainFlagName() {
		return mainFlagName;
	}

	public void setMainFlagName(String mainFlagName) {
		this.mainFlagName = mainFlagName;
	}

	public String getRunStsName() {
		return runStsName;
	}

	public void setRunStsName(String runStsName) {
		this.runStsName = runStsName;
	}

	public String getWorkAreaName() {
		return workAreaName;
	}

	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}

	public String getChgServSpec() {
		return chgServSpec;
	}

	public void setChgServSpec(String chgServSpec) {
		this.chgServSpec = chgServSpec;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getLocalNetId() {
		return localNetId;
	}

	public void setLocalNetId(String localNetId) {
		this.localNetId = localNetId;
	}

	public String getWorkAreaId() {
		return workAreaId;
	}

	public void setWorkAreaId(String workAreaId) {
		this.workAreaId = workAreaId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public Date getPreAlarmDate() {
		return preAlarmDate;
	}

	public void setPreAlarmDate(Date preAlarmDate) {
		this.preAlarmDate = preAlarmDate;
	}

	public String getAccNbr() {
		return accNbr;
	}

	public void setAccNbr(String accNbr) {
		this.accNbr = accNbr;
	}

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Date getBusiStsDate() {
		return busiStsDate;
	}

	public void setBusiStsDate(Date busiStsDate) {
		this.busiStsDate = busiStsDate;
	}

	public String getExchId() {
		return exchId;
	}

	public void setExchId(String exchId) {
		this.exchId = exchId;
	}

	public String getFailId() {
		return failId;
	}

	public void setFailId(String failId) {
		this.failId = failId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSoNbr() {
		return soNbr;
	}

	public void setSoNbr(String soNbr) {
		this.soNbr = soNbr;
	}

	public String getWoNbr() {
		return woNbr;
	}

	public void setWoNbr(String woNbr) {
		this.woNbr = woNbr;
	}

	public String getActTypeName() {
		return actTypeName;
	}

	public void setActTypeName(String actTypeName) {
		this.actTypeName = actTypeName;
	}

	public Date getApplDate() {
		return applDate;
	}

	public void setApplDate(Date applDate) {
		this.applDate = applDate;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getExchName() {
		return exchName;
	}

	public void setExchName(String exchName) {
		this.exchName = exchName;
	}

	public String getFailName() {
		return failName;
	}

	public void setFailName(String failName) {
		this.failName = failName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getWoTypeName() {
		return woTypeName;
	}

	public void setWoTypeName(String woTypeName) {
		this.woTypeName = woTypeName;
	}

	/**
	 * @return the rtStaffId
	 */
	public String getRtStaffId() {
		return rtStaffId;
	}

	/**
	 * @param rtStaffId
	 *            the rtStaffId to set
	 */
	public void setRtStaffId(String rtStaffId) {
		this.rtStaffId = rtStaffId;
	}

	/**
	 * @return the woStaffId
	 */
	public String getWoStaffId() {
		return woStaffId;
	}

	/**
	 * @param woStaffId
	 *            the woStaffId to set
	 */
	public void setWoStaffId(String woStaffId) {
		this.woStaffId = woStaffId;
	}

	public String getWoType() {
		return woType;
	}

	public void setWoType(String woType) {
		this.woType = woType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getBusiSts() {
		return busiSts;
	}

	public void setBusiSts(String busiSts) {
		this.busiSts = busiSts;
	}

	public Date getAsgnDate() {
		return asgnDate;
	}

	public void setAsgnDate(Date asgnDate) {
		this.asgnDate = asgnDate;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getRunSts() {
		return runSts;
	}

	public void setRunSts(String runSts) {
		this.runSts = runSts;
	}

	public String getPrintCount() {
		return printCount;
	}

	public void setPrintCount(String printCount) {
		this.printCount = printCount;
	}

	public String getRelaWoNbr() {
		return relaWoNbr;
	}

	public void setRelaWoNbr(String relaWoNbr) {
		this.relaWoNbr = relaWoNbr;
	}

	public String getFailReasonId() {
		return failReasonId;
	}

	public void setFailReasonId(String failReasonId) {
		this.failReasonId = failReasonId;
	}

	public String getOvertimeId() {
		return overtimeId;
	}

	public void setOvertimeId(String overtimeId) {
		this.overtimeId = overtimeId;
	}

	public String getSoStaffId() {
		return soStaffId;
	}

	public void setSoStaffId(String soStaffId) {
		this.soStaffId = soStaffId;
	}

	public String getSoStaffName() {
		return soStaffName;
	}

	public void setSoStaffName(String soStaffName) {
		this.soStaffName = soStaffName;
	}

	public String getCustCatId() {
		return custCatId;
	}

	public void setCustCatId(String custCatId) {
		this.custCatId = custCatId;
	}

	public String getCustCatName() {
		return custCatName;
	}

	public void setCustCatName(String custCatName) {
		this.custCatName = custCatName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustLevelId() {
		return custLevelId;
	}

	public void setCustLevelId(String custLevelId) {
		this.custLevelId = custLevelId;
	}

	public String getCustLevelName() {
		return custLevelName;
	}

	public void setCustLevelName(String custLevelName) {
		this.custLevelName = custLevelName;
	}

	public String getWoStaffName() {
		return woStaffName;
	}

	public void setWoStaffName(String woStaffName) {
		this.woStaffName = woStaffName;
	}

	public String getSoWorkAreaName() {
		return soWorkAreaName;
	}

	public void setSoWorkAreaName(String soWorkAreaName) {
		this.soWorkAreaName = soWorkAreaName;
	}

	public String getSoWorkAreaId() {
		return soWorkAreaId;
	}

	public void setSoWorkAreaId(String soWorkAreaId) {
		this.soWorkAreaId = soWorkAreaId;
	}

	/**
	 * @return the tabpagecode
	 */
	public String getTabpagecode() {
		return tabpagecode;
	}

	/**
	 * @param tabpagecode
	 *            the tabpagecode to set
	 */
	public void setTabpagecode(String tabpagecode) {
		this.tabpagecode = tabpagecode;
	}

	public String getHalt() {
		return halt;
	}

	public void setHalt(String halt) {
		this.halt = halt;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getBookFlag() {
		return bookFlag;
	}

	public void setBookFlag(String bookFlag) {
		this.bookFlag = bookFlag;
	}

	public String getOverName() {
		return overName;
	}

	public void setOverName(String overName) {
		this.overName = overName;
	}

	public String getArFlag() {
		return arFlag;
	}

	public void setArFlag(String arFlag) {
		this.arFlag = arFlag;
	}

	public String getWorkMode() {
		return workMode;
	}

	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}

	public String getWorkModeName() {
		return workModeName;
	}

	public void setWorkModeName(String workModeName) {
		this.workModeName = workModeName;
	}

	public String getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}

	public String getDealFlagName() {
		return dealFlagName;
	}

	public void setDealFlagName(String dealFlagName) {
		this.dealFlagName = dealFlagName;
	}

	public String getBookFlagName() {
		return bookFlagName;
	}

	public void setBookFlagName(String bookFlagName) {
		this.bookFlagName = bookFlagName;
	}

	public String getNwoNbr() {
		return nwoNbr;
	}

	public void setNwoNbr(String nwoNbr) {
		this.nwoNbr = nwoNbr;
	}

	public Date getCompDate() {
		return compDate;
	}

	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}

	public String getExtSoNbr() {
		return extSoNbr;
	}

	public void setExtSoNbr(String extSoNbr) {
		this.extSoNbr = extSoNbr;
	}

	public String getInspInfo() {
		return inspInfo;
	}

	public void setInspInfo(String inspInfo) {
		this.inspInfo = inspInfo;
	}

	public Date getRunStsDate() {
		return runStsDate;
	}

	public void setRunStsDate(Date runStsDate) {
		this.runStsDate = runStsDate;
	}

	public String getBusiStsName() {
		return busiStsName;
	}

	public void setBusiStsName(String busiStsName) {
		this.busiStsName = busiStsName;
	}

	public String getExtWoNbr() {
		return extWoNbr;
	}

	public void setExtWoNbr(String extWoNbr) {
		this.extWoNbr = extWoNbr;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getChgName() {
		return chgName;
	}

	public void setChgName(String chgName) {
		this.chgName = chgName;
	}

	public String getSoRunStsName() {
		return soRunStsName;
	}

	public void setSoRunStsName(String soRunStsName) {
		this.soRunStsName = soRunStsName;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getSoSeq() {
		return soSeq;
	}

	public void setSoSeq(String soSeq) {
		this.soSeq = soSeq;
	}

	public String getAddrAreaId() {
		return addrAreaId;
	}

	public void setAddrAreaId(String addrAreaId) {
		this.addrAreaId = addrAreaId;
	}

	public String getAddrLocalNetId() {
		return addrLocalNetId;
	}

	public void setAddrLocalNetId(String addrLocalNetId) {
		this.addrLocalNetId = addrLocalNetId;
	}

	public String getCancelJudgeRule() {
		return cancelJudgeRule;
	}

	public void setCancelJudgeRule(String cancelJudgeRule) {
		this.cancelJudgeRule = cancelJudgeRule;
	}

	public String getMdfName() {
		return mdfName;
	}

	public void setMdfName(String mdfName) {
		this.mdfName = mdfName;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getRelatedExtSoNbr() {
		return relatedExtSoNbr;
	}

	public void setRelatedExtSoNbr(String relatedExtSoNbr) {
		this.relatedExtSoNbr = relatedExtSoNbr;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getToNbr() {
		return toNbr;
	}

	public void setToNbr(String toNbr) {
		this.toNbr = toNbr;
	}

	public String getSituated() {
		return situated;
	}

	public void setSituated(String situated) {
		this.situated = situated;
	}

	public String getWoTimeCost() {
		return woTimeCost;
	}

	public void setWoTimeCost(String woTimeCost) {
		this.woTimeCost = woTimeCost;
	}

	public String getWoTimeOut() {
		return woTimeOut;
	}

	public void setWoTimeOut(String woTimeOut) {
		this.woTimeOut = woTimeOut;
	}

	public String getFaultCat() {
		return faultCat;
	}

	public void setFaultCat(String faultCat) {
		this.faultCat = faultCat;
	}

	public String getSoCat() {
		return soCat;
	}

	public void setSoCat(String soCat) {
		this.soCat = soCat;
	}

	public String getAlarmSts() {
		return alarmSts;
	}

	public void setAlarmSts(String alarmSts) {
		this.alarmSts = alarmSts;
	}

	public String getDevPartyName() {
		return devPartyName;
	}

	public void setDevPartyName(String devPartyName) {
		this.devPartyName = devPartyName;
	}

	public String getReasonCatName() {
		return reasonCatName;
	}

	public void setReasonCatName(String reasonCatName) {
		this.reasonCatName = reasonCatName;
	}

	public String getReasonCat() {
		return reasonCat;
	}

	public void setReasonCat(String reasonCat) {
		this.reasonCat = reasonCat;
	}

	public String getStaffPhoneNbr() {
		return staffPhoneNbr;
	}

	public void setStaffPhoneNbr(String staffPhoneNbr) {
		this.staffPhoneNbr = staffPhoneNbr;
	}

	public String getProdCatId() {
		return prodCatId;
	}

	public void setProdCatId(String prodCatId) {
		this.prodCatId = prodCatId;
	}

	public String getFaultReasonName() {
		return faultReasonName;
	}

	public void setFaultReasonName(String faultReasonName) {
		this.faultReasonName = faultReasonName;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
}
