package com.cattsoft.sm.vo;

import com.cattsoft.pub.vo.GenericVO;

public class QryCustInfoConditionMVO extends GenericVO {
	
    private static final long serialVersionUID = 1L;

    // 用来保存查询时的客户编号
    private String custId;

    // 用来保存本地网编号
    private String localNetId;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getLocalNetId() {
        return localNetId;
    }

    public void setLocalNetId(String localNetId) {
        this.localNetId = localNetId;
    }
}