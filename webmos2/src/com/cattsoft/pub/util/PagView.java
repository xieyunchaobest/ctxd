package com.cattsoft.pub.util;
import java.util.List;
import java.io.Serializable;
/**
 *
 * <p>Title: 分页显示返回的类</p>
 * <p>Description: 新九七系统</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: CATTSoft</p>
 * @author shijian
 * @version 1.0
 */
public class PagView implements Serializable{
 
	private static final long serialVersionUID = 7695670883536635177L;

public PagView() {
  }

  int count;//总记录数
  int pagSize;//每页显示记录数
  int pagNo;//页码数
  int pagCount;//页数
  int rowLimit;//查询总数限制，Added by YingHS,May19 2008
  private List viewList;//显示数组

  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public int getPagCount() {
    return pagCount;
  }
  public void setPagCount(int pagCount) {
    this.pagCount = pagCount;
  }
  public int getPagNo() {
    return pagNo;
  }
  public void setPagNo(int pagNo) {
    this.pagNo = pagNo;
  }
  public int getPagSize() {
    return pagSize;
  }
  public void setPagSize(int pagSize) {
    this.pagSize = pagSize;
  }
  public int getRowLimit()	{
	  return rowLimit;
  }
  public void setRowLimit(int rowLimit)	{
	  this.rowLimit = rowLimit;
  }
  public List getViewList() {
    return viewList;
  }
  public void setViewList(List viewList) {
    this.viewList = viewList;
  }

}
