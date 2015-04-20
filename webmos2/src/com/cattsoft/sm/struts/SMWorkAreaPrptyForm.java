package com.cattsoft.sm.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.cattsoft.sm.vo.WorkAreaPrptyMVO;

public class SMWorkAreaPrptyForm extends ActionForm {
	private static final long serialVersionUID = 3902226231923029278L;
    private WorkAreaPrptyMVO wapmvo = new WorkAreaPrptyMVO();
    //以下为主页面中用到的元素
	private List localNetList;
	private List areaList;
	private List workTypeList;
	private List prodList;
	private List stsList;
	private List exchList;
	private List workAreaList;
	private List ruleList;
	private String localNetIdSelect;
	private String areaIdSelect;
	private String workTypeIdSelect;
	private String stsSelect;
	private String exchIdSelect;
	private String chbLocalNetId = "1";
	private String chbAreaId;
	private String chbWorkTypeId;
	private String chbSts;
	private String chbExchId;
	
	public List getLocalNetList() {
		return localNetList;
	}
	public void setLocalNetList(List localNetList) {
		this.localNetList = localNetList;
	}
	public List getAreaList() {
		return areaList;
	}
	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}
	public List getWorkTypeList() {
		return workTypeList;
	}
	public void setWorkTypeList(List workTypeList) {
		this.workTypeList = workTypeList;
	}
	public List getProdList() {
		return prodList;
	}
	public void setProdList(List prodList) {
		this.prodList = prodList;
	}
	public List getStsList() {
		return stsList;
	}
	public void setStsList(List stsList) {
		this.stsList = stsList;
	}
	public WorkAreaPrptyMVO getWapmvo() {
		return wapmvo;
	}
	public void setWapmvo(WorkAreaPrptyMVO wapmvo) {
		this.wapmvo = wapmvo;
	}
	public String getLocalNetIdSelect() {
		return localNetIdSelect;
	}
	public void setLocalNetIdSelect(String localNetIdSelect) {
		this.localNetIdSelect = localNetIdSelect;
	}
	public String getAreaIdSelect() {
		return areaIdSelect;
	}
	public void setAreaIdSelect(String areaIdSelect) {
		this.areaIdSelect = areaIdSelect;
	}
	public String getWorkTypeIdSelect() {
		return workTypeIdSelect;
	}
	public void setWorkTypeIdSelect(String workTypeIdSelect) {
		this.workTypeIdSelect = workTypeIdSelect;
	}
	public String getStsSelect() {
		return stsSelect;
	}
	public void setStsSelect(String stsSelect) {
		this.stsSelect = stsSelect;
	}
	public String getChbLocalNetId() {
		return chbLocalNetId;
	}
	public void setChbLocalNetId(String chbLocalNetId) {
		this.chbLocalNetId = chbLocalNetId;
	}
	public String getChbAreaId() {
		return chbAreaId;
	}
	public void setChbAreaId(String chbAreaId) {
		this.chbAreaId = chbAreaId;
	}
	public String getChbWorkTypeId() {
		return chbWorkTypeId;
	}
	public void setChbWorkTypeId(String chbWorkTypeId) {
		this.chbWorkTypeId = chbWorkTypeId;
	}
	public String getChbSts() {
		return chbSts;
	}
	public void setChbSts(String chbSts) {
		this.chbSts = chbSts;
	}
	public List getExchList() {
		return exchList;
	}
	public void setExchList(List exchList) {
		this.exchList = exchList;
	}
	public List getWorkAreaList() {
		return workAreaList;
	}
	public void setWorkAreaList(List workAreaList) {
		this.workAreaList = workAreaList;
	}
	public String getExchIdSelect() {
		return exchIdSelect;
	}
	public void setExchIdSelect(String exchIdSelect) {
		this.exchIdSelect = exchIdSelect;
	}
	public String getChbExchId() {
		return chbExchId;
	}
	public void setChbExchId(String chbExchId) {
		this.chbExchId = chbExchId;
	}
	public List getRuleList() {
		return ruleList;
	}
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
}
