package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

/**
 * 保存基本的客户信息，即通过以下查询信息能区别于别的客户 <br>
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-29 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author zhouqian
 */

public class QryBasicCustInfoMVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    // 客户编号
    private String custId;

    // 客户名称
    private String custName;

    // 客户证件号
    private String certCode;

    // 客户证件类型
    private String certType;

    // 客户类型
    private String custType;

    // 客户行业
    private String custVoca;

    // 客户级别
    private String custLevel;

    // 服务区编号
    private String areaId;

    // 服务区名称－－－－－－－－－－－－服务区名称在DAO中并未取出，而是在DataCache中取出
    private String areaName;

    /** add by zhangchao as follows* */

    // 客户序号
    private String custSeq;

    // 证件类型ID
    private String certTypeId;

    // 客户行业ID
    private String custVocaId;

    // 客户性质ID
    private String custTypeId;

    // 客户分类ID
    private String partyType;

    // 客户级别Id
    private String custLevelId;

    // 客户所在本地网
    private String localNetId;
    
    /** 以下属性是干天星添加(客户的上级客户) */
    //参与人编号
    private String partyId;
    
    //上级客户编号
    private String parentCustId;
    
    //上级客户名称
    private String parentCustName;

    public String getParentCustId() {
        return parentCustId;
    }

    public void setParentCustId(String parentCustId) {
        this.parentCustId = parentCustId;
    }

    public String getParentCustName() {
        return parentCustName;
    }

    public void setParentCustName(String parentCustName) {
        this.parentCustName = parentCustName;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustVoca() {
        return custVoca;
    }

    public void setCustVoca(String custVoca) {
        this.custVoca = custVoca;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCertTypeId() {
        return certTypeId;
    }

    public void setCertTypeId(String certTypeId) {
        this.certTypeId = certTypeId;
    }

    public String getCustSeq() {
        return custSeq;
    }

    public void setCustSeq(String custSeq) {
        this.custSeq = custSeq;
    }

    public String getCustTypeId() {
        return custTypeId;
    }

    public void setCustTypeId(String custTypeId) {
        this.custTypeId = custTypeId;
    }

    public String getCustVocaId() {
        return custVocaId;
    }

    public void setCustVocaId(String custVocaId) {
        this.custVocaId = custVocaId;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCustLevelId() {
        return custLevelId;
    }

    public void setCustLevelId(String custLevelId) {
        this.custLevelId = custLevelId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

}