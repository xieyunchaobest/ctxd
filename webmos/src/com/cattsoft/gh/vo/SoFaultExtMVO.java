package com.cattsoft.gh.vo;

import java.util.Date;
import java.util.List;

import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DateUtil;
import com.cattsoft.pub.util.StringUtil;

public class SoFaultExtMVO extends SoFaultExtSVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3940684547958572769L;
	
	private String chbHandleType;
	
	private String confirmResultName;
	private String  confirmStyleName;
	private String  handleTyleName;
	private String  customrSatisfactionName;
	private String  anlysisTypeName;
	private String  disableFlagName;
	private String  resFaultLevelName;
    private String faultDutyDeptName;
    private String faultDutyStaffName;
	private String  localNetName;
	
	private String repairDateStr;
	private String chRepairDate;
	private Date fromRepairDate;
	private Date toRepairDate;
	
	private String  confirmContentList;	   //障碍证实内容

	private List     customerSatisfactionList;  //客户满意度
	
	private List     resFaultLevelList ;		   //核心层/接入层
	        
	private List     analysisTypeList ;         //专业分析方式
		
	private List     handleTypeList ;           //障碍处理方式
	
	private String faultReasonName;		  //故障愿意名称

	
public List getAnalysisTypeList() {
		return analysisTypeList;
	}

	public void setAnalysisTypeList(List analysisTypeList) {
		this.analysisTypeList = analysisTypeList;
	}

	public String getConfirmContentList() {
		return confirmContentList;
	}

	public void setConfirmContentList(String confirmContentList) {
		this.confirmContentList = confirmContentList;
	}

	public List getCustomerSatisfactionList() {
		return customerSatisfactionList;
	}

	public void setCustomerSatisfactionList(List customerSatisfactionList) {
		this.customerSatisfactionList = customerSatisfactionList;
	}

	public String getFaultReasonName() {
		return faultReasonName;
	}

	public void setFaultReasonName(String faultReasonName) {
		this.faultReasonName = faultReasonName;
	}

	public List getHandleTypeList() {
		return handleTypeList;
	}

	public void setHandleTypeList(List handleTypeList) {
		this.handleTypeList = handleTypeList;
	}

	public List getResFaultLevelList() {
		return resFaultLevelList;
	}

	public void setResFaultLevelList(List resFaultLevelList) {
		this.resFaultLevelList = resFaultLevelList;
	}

	//	故障时间	
	public String getFromRepairDateTimeStr() {
		String str = "";
		if( null != this.fromRepairDate){
			str = DateUtil.to_char(fromRepairDate, "yyyy-MM-dd HH:mm:ss");
		}
		return str;
	}
	
	public void setFromRepairDateTimeStr(String repairDateStr) throws SysException{
		if( ! StringUtil.isBlank(repairDateStr)){
			this.fromRepairDate = DateUtil.to_date(repairDateStr, "yyyy-MM-dd HH:mm:ss");
			return;
		}
		this.repairDateStr = null;
	}
	
	public String getToRepairDateTimeStr() {
		String str = "";
		if( null != this.toRepairDate){
			str = DateUtil.to_char(toRepairDate, "yyyy-MM-dd HH:mm:ss");
		}
		return str;
	}
	
	public void setToRepairDateTimeStr(String repairDateStr) throws SysException{
		if( ! StringUtil.isBlank(repairDateStr)){
			this.toRepairDate = DateUtil.to_date(repairDateStr, "yyyy-MM-dd HH:mm:ss");
			return;
		}
		this.repairDateStr = null;
	}
	
	public String getChRepairDate() {
		return chRepairDate;
	}

	public void setChRepairDate(String chRepairDate) {
		this.chRepairDate = chRepairDate;
	}

	public Date getFromFaultDate() {
		return fromRepairDate;
	}

	public void setFromFaultDate(Date fromFaultDate) {
		this.fromRepairDate = fromFaultDate;
	}

	public Date getToFaultDate() {
		return toRepairDate;
	}

	public void setToFaultDate(Date toFaultDate) {
		this.toRepairDate = toFaultDate;
	}

	public String getChbHandleType() {
		return chbHandleType;
	}

	public void setChbHandleType(String chbHandleType) {
		this.chbHandleType = chbHandleType;
	}

	public String getFaultDateStr() {
		return repairDateStr;
	}

	public void setFaultDateStr(String faultDateStr) {
		this.repairDateStr = faultDateStr;
	}

	public String getConfirmResultName() {
		return confirmResultName;
	}

	public void setConfirmResultName(String confirmResultName) {
		this.confirmResultName = confirmResultName;
	}

	public String getConfirmStyleName() {
		return confirmStyleName;
	}

	public void setConfirmStyleName(String confirmStyleName) {
		this.confirmStyleName = confirmStyleName;
	}

	public String getHandleTyleName() {
		return handleTyleName;
	}

	public void setHandleTyleName(String handleTyleName) {
		this.handleTyleName = handleTyleName;
	}

	public String getCustomrSatisfactionName() {
		return customrSatisfactionName;
	}

	public void setCustomrSatisfactionName(String customrSatisfactionName) {
		this.customrSatisfactionName = customrSatisfactionName;
	}

	public String getAnlysisTypeName() {
		return anlysisTypeName;
	}

	public void setAnlysisTypeName(String anlysisTypeName) {
		this.anlysisTypeName = anlysisTypeName;
	}

	public String getDisableFlagName() {
		return disableFlagName;
	}

	public void setDisableFlagName(String disableFlagName) {
		this.disableFlagName = disableFlagName;
	}

	public String getResFaultLevelName() {
		return resFaultLevelName;
	}

	public void setResFaultLevelName(String resFaultLevelName) {
		this.resFaultLevelName = resFaultLevelName;
	}


	public String getLocalNetName() {
		return localNetName;
	}

	public void setLocalNetName(String localNetName) {
		this.localNetName = localNetName;
	}

	public String getRepairDateStr() {
		return repairDateStr;
	}

	public void setRepairDateStr(String repairDateStr) {
		this.repairDateStr = repairDateStr;
	}

	public Date getFromRepairDate() {
		return fromRepairDate;
	}

	public void setFromRepairDate(Date fromRepairDate) {
		this.fromRepairDate = fromRepairDate;
	}

	public Date getToRepairDate() {
		return toRepairDate;
	}

	public void setToRepairDate(Date toRepairDate) {
		this.toRepairDate = toRepairDate;
	}

	public String getFaultDutyDeptName() {
		return faultDutyDeptName;
	}

	public void setFaultDutyDeptName(String faultDutyDeptName) {
		this.faultDutyDeptName = faultDutyDeptName;
	}

	public String getFaultDutyStaffName() {
		return faultDutyStaffName;
	}

	public void setFaultDutyStaffName(String faultDutyStaffName) {
		this.faultDutyStaffName = faultDutyStaffName;
	}

	
	
	
}
