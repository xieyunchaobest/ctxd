package com.cattsoft.sm.vo;

import java.util.List;

import com.cattsoft.pub.vo.GenericVO;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-9-18 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */

public class StatusMVO extends GenericVO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5674407050569247113L;

	private String tableName;

    private String columnName;

    private String stsId;

    private String stsWords;

    private String orderId;

    private AppTableSVO appTableSVO = new AppTableSVO();

    private AppColumnSVO appColumnSVO = new AppColumnSVO();

    private List domainValueSVOList = null;

    private DomainSVO domainSVO = new DomainSVO();

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

    public AppColumnSVO getAppColumnSVO() {
        return appColumnSVO;
    }

    public void setAppColumnSVO(AppColumnSVO appColumnSVO) {
        this.appColumnSVO = appColumnSVO;
    }

    public DomainSVO getDomainSVO() {
        return domainSVO;
    }

    public void setDomainSVO(DomainSVO domainSVO) {
        this.domainSVO = domainSVO;
    }

    public List getDomainValueSVOList() {
        return domainValueSVOList;
    }

    public void setDomainValueSVOList(List domainValueSVOList) {
        this.domainValueSVOList = domainValueSVOList;
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

}
