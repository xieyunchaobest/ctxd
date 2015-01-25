package com.cattsoft.tm.vo;import java.io.*;import com.cattsoft.pub.vo.GenericVO;import java.util.*;import java.sql.Clob;import java.sql.Blob; /**   * QueryInstanceSVO   * @author ����С����   * @version 1.1  2007-9-23   * <p>Company: ��������</p>  */public class QueryInstanceSVO extends GenericVO {private Date createTime = null;private String instanceName = null;private String instanceType = null;private String queryInstanceId = null;private String sts = null;private String tableDesc=null;private String tableName = null;private int flagCreateTime = 0;private int flagInstanceName = 0;private int flagInstanceType = 0;private int flagQueryInstanceId = 0;private int flagSts = 0;private int flagTableName = 0;private String instanceTypeName=null;private String treeId;public Date getCreateTime(){  return createTime;}public void setCreateTime(Date newValue){   this.createTime = newValue;  flagCreateTime = 1;}public int getFlagCreateTime(){  return flagCreateTime;}public String getInstanceName(){ return instanceName;}public void setInstanceName(String newValue){  this.instanceName = newValue;  flagInstanceName = 1;}public int getFlagInstanceName(){  return flagInstanceName;}public String getInstanceType(){ return instanceType;}public void setInstanceType(String newValue){  this.instanceType = newValue;  flagInstanceType = 1;}public int getFlagInstanceType(){  return flagInstanceType;}public String getQueryInstanceId(){ return queryInstanceId;}public void setQueryInstanceId(String newValue){  this.queryInstanceId = newValue;  flagQueryInstanceId = 1;}public int getFlagQueryInstanceId(){  return flagQueryInstanceId;}public String getSts(){ return sts;}public void setSts(String newValue){  this.sts = newValue;  flagSts = 1;}public int getFlagSts(){  return flagSts;}public String getTableName(){ return tableName;}public void setTableName(String newValue){  this.tableName = newValue;  flagTableName = 1;}public int getFlagTableName(){  return flagTableName;}public void clearFlagCreateTime(){ flagCreateTime = 0 ;}public void clearFlagInstanceName(){ flagInstanceName = 0 ;}public void clearFlagInstanceType(){ flagInstanceType = 0 ;}public void clearFlagQueryInstanceId(){ flagQueryInstanceId = 0 ;}public void clearFlagSts(){ flagSts = 0 ;}public void clearFlagTableName(){ flagTableName = 0 ;}public void clearAll(){ flagCreateTime = 0; flagInstanceName = 0; flagInstanceType = 0; flagQueryInstanceId = 0; flagSts = 0; flagTableName = 0;}public String getTableDesc() {	return tableDesc;}public void setTableDesc(String tableDesc) {	this.tableDesc = tableDesc;}public String getInstanceTypeName() {	return instanceTypeName;}public void setInstanceTypeName(String instanceTypeName) {	this.instanceTypeName = instanceTypeName;}public String getTreeId() {	return treeId;}public void setTreeId(String treeId) {	this.treeId = treeId;}}
