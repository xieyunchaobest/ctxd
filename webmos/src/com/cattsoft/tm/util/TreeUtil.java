package com.cattsoft.tm.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.StringUtil;
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.tm.vo.TreeNodeInfo;
/**
 * Title: 服务开通系统 <br>
 * Description: 树 的公共的静态属性，树的构建<br>
 * Date: Sep 18, 2012 <br>
 * Copyright (c) 2012 CATTSoft <br>
 * 
 * @author 
 */
public class TreeUtil {
	
	public static final String ROOT = "tree";
	
	public static final String ITEM = "item";
	
	public static final String USER_DATA = "userdata";
	
	public static final String ID = "id";
	
	public static final String NAME = "text";
	
	public static final String ATTR_NAME="name";
	
	public static final String CHILD = "child";
	
	public static final String ROOT_ID = "0";
	
	private static final String HAS_CHILD = "1";
	
	private static final String IS_OPEN="open";
	private static final String OPEN_VALUE="0";
	private static final String SELECTED="select";
	private static final String SELECTED_VALUE="1";
	private static final String URL="url";
	
	

	/**
	 * 根据传入的treeList生成XML格式的文件
	 * @param treeList
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	public static Document getTree(List<TreeNodeInfo> treeList,String search) throws SysException,AppException{
		if(treeList != null && treeList.size() > 0){
			Document treeDomXml = DocumentHelper.createDocument();
			Element treeElement = treeDomXml.addElement(ROOT);
			treeElement.addAttribute(ID,ROOT_ID);
			Element item = treeElement.addElement(ITEM);
			TreeNodeInfo rootNode = new TreeNodeInfo();
			for(TreeNodeInfo treeNode : treeList){
				if(treeNode.getParentId() == null|| "0".equals(treeNode.getParentId())){
					rootNode = treeNode;
					break;
				}
			}
			String depth=rootNode.getDepth().toString();
			String urlFlag=rootNode.getUserdataFalg();
			if("area".equals(urlFlag)) {
				Element userdata=item.addElement(USER_DATA);
				userdata.addAttribute(ATTR_NAME, URL);
				userdata.addText(depth);
			}
			
			item.addAttribute(ID, rootNode.getId().toString());
			item.addAttribute(NAME, rootNode.getName());
			item.addAttribute(IS_OPEN, OPEN_VALUE);
			getTreeXml(rootNode,treeList,null,item,search);
			return treeDomXml;			
		}else{
			return null;
		}
	}
 
	/**
	 * 根据sortposition字段对treeList进行排序
	 * @param treeList
	 * @return 排好序的treeList
	 */
	private static List<TreeNodeInfo> sortTreeList(List<TreeNodeInfo> treeList){
		for(int i=0; i<treeList.size();i++){
			for(int j=0; j<treeList.size()-1-i;j++){
				TreeNodeInfo temp = new TreeNodeInfo();
				if(Integer.valueOf(treeList.get(j).getSortPosition().toString())>Integer.valueOf(treeList.get(j+1).getSortPosition().toString())){
					temp = treeList.get(j);
					treeList.set(j, treeList.get(j+1));
					treeList.set(j+1, temp);
				}
			}
		}
		return treeList;
	}

	/**
	 * 可进行分级加载的树
	 * 
	 * @param nodeList
	 *            所有树节点list
	 * @param subList
	 *            如果不为空，则进行分级加载，通常用在非子关联树
	 * @param childTreeId
	 *            分级加载tree id
	 * @param dynaLoad
	 *            是否分级加载
	 * @param search 按search查询条件搜索树，如果符合条件则被选中         
	 * @return
	 */
	public static Document buildTree(List<TreeNodeInfo> nodeList,
			List<TreeNodeInfo> subList, String childTreeId,String dynaLoad,String search) {
		boolean isRoot = false;
		Document treeDomXml=null;
		if (nodeList != null && nodeList.size() > 0) {
			treeDomXml = DocumentHelper.createDocument();
			Element treeElement = treeDomXml.addElement(ROOT);
			treeElement.addAttribute(ID, ROOT_ID);
			if (!StringUtil.isBlank(childTreeId)) {
				treeElement.addAttribute(ID, childTreeId);
			}
			Element item = treeElement.addElement(ITEM);
			TreeNodeInfo rootNode = new TreeNodeInfo();
			
			for (TreeNodeInfo treeNode : nodeList) {
				if (treeNode.getParentId() == null  ) {
					rootNode = treeNode;
					rootNode.setDepth(0L);
					isRoot = true;
					break;
				}

				if (!StringUtil.isBlank(treeNode.getDepth()) && "0".equals(treeNode.getDepth().toString())) {
					rootNode = treeNode;
					isRoot = true;
					break;
				}
			}
			String depth=rootNode.getDepth().toString();
			if (isRoot) {
				if("Y".equals(dynaLoad)){
					treeElement.remove(item);
					getTreeXml(rootNode, nodeList, subList, treeElement,search);
				}else{
					item.addAttribute(ID, rootNode.getId().toString());
					item.addAttribute(NAME, rootNode.getName());
					if(search!=null) {
						if(rootNode.getName().indexOf(search)!=-1) {
							item.addAttribute(SELECTED, SELECTED_VALUE);
						}
					}
					
					String urlFlag=rootNode.getUserdataFalg();
					if("area".equals(urlFlag)) {
						Element userdata=item.addElement(USER_DATA);
						userdata.addAttribute(ATTR_NAME, URL);
						userdata.addText(depth);
					}
					
					item.addAttribute(IS_OPEN, OPEN_VALUE); //一级菜单默认是打开的状态
					getTreeXml(rootNode, nodeList, subList, item,search);
				}
				
			}else{
				treeElement.remove(item);
				rootNode.setId(childTreeId);
				getTreeXml(rootNode, nodeList, subList, treeElement,search);
			}
		}else {
			treeDomXml= DocumentHelper.createDocument();
			Element treeElement = treeDomXml.addElement(ROOT);
			treeElement.addAttribute(ID, ROOT_ID);
			Element item = treeElement.addElement(ITEM);
			item.addAttribute(NAME, "查询结果为空");
			item.addAttribute(ID, "-1");
		}
			
		return treeDomXml;
	}
	
	/**
	 * 获取树节点
	 * 
	 * @param rootNode
	 * @param treeList
	 * @param pItem
	 * @return
	 * @throws SysException
	 * @throws AppException
	 */
	private static Element getTreeXml(TreeNodeInfo rootNode,
			List<TreeNodeInfo> nodeList, List<TreeNodeInfo> subList,
			Element pItem,String search) {

		List<TreeNodeInfo> treeNodeList = new ArrayList<TreeNodeInfo>();

		for (int i = 0; i < nodeList.size(); i++) {
			TreeNodeInfo childNode = (TreeNodeInfo) nodeList.get(i);
//			if ("0".equals(childNode.getParentId())||childNode.getParentId() == null || (!StringUtil.isBlank(childNode.getDepth()) && childNode.getDepth() == 0)) { //如果没有父节点，则退出本次循环
//				continue;
//			}
			//add by xyc，用于区分叶子节点是地区还是机构
			String rootNodeId=rootNode.getId(); 
			
			//add by xyc 110630 ，判断标志位是否为机构、地区、员工、用户
			if(rootNodeId.length()>6) {
				//add by xyc 110630 ，判断标志位是否为机构、地区、员工、用户
				if(rootNodeId.endsWith("-1orga")) {
					rootNodeId=rootNodeId.substring(0, rootNodeId.length()-6);
				}else if(rootNodeId.endsWith("-1user")) {
					rootNodeId=rootNodeId.substring(0, rootNodeId.length()-6);
				}else if(rootNodeId.endsWith("-1area" )) {
					rootNodeId=rootNodeId.substring(0, rootNodeId.length()-6);
				}else if(rootNodeId.endsWith("-1staf")) {
					rootNodeId=rootNodeId.substring(0, rootNodeId.length()-6);
				}
			}
			
			String jiLinAreaFlag =null;
			 if("Y".equalsIgnoreCase(jiLinAreaFlag)){
				 if (childNode.getParentId().equals(rootNodeId)&& !(rootNodeId.endsWith("430")&&!StringUtil.isBlank(rootNode.getParentId())&&rootNode.getParentId().endsWith("430047005"))) { //如果父节点是根，则放入list
						if(!StringUtil.isBlank(rootNode.getDepth()))childNode.setDepth(rootNode.getDepth()+1);
						treeNodeList.add(childNode);
					}
			 }else{
	            	if (childNode.getParentId().equals(rootNodeId)) { //如果父节点是根，则放入list
	    				if(!StringUtil.isBlank(rootNode.getDepth()))childNode.setDepth(rootNode.getDepth()+1);
	    				treeNodeList.add(childNode);
	    				}
	          }
			
		}
		if (treeNodeList.size() == 0) {
			return null;
		} else {
			sortTreeList(treeNodeList);
			for (int i = 0; i < treeNodeList.size(); i++) {
				Element item = pItem.addElement(ITEM); //增加item 元素
				item.addAttribute(ID, treeNodeList.get(i).getId().toString());//设置item元素的id属性和值
				String nodeName= treeNodeList.get(i).getName();
				item.addAttribute(NAME, nodeName);//设置text属性和值
				String selectedFlag=treeNodeList.get(i).getSelectedFlag();
				String urlDataFlag=treeNodeList.get(i).getUserdataFalg();
				String html=treeNodeList.get(i).getHtml();
				String funcNodeType=treeNodeList.get(i).getFuncNodeType();
				String depth=treeNodeList.get(i).getDepth().toString();
				String typeDepth=funcNodeType+"___"+depth;
				if(!StringUtil.isBlank(html)) {
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(html);
				}
				
				//判断节点的类型
				if(typeDepth.startsWith("M___") ) {
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(typeDepth);
				}else if(typeDepth.startsWith("B___") ) {
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(typeDepth);
				}else if(typeDepth.startsWith("O___") ) { //机构
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(typeDepth);
				}else if(typeDepth.startsWith("U___") ) { //用户
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(typeDepth);
				}else if(typeDepth.startsWith("S___") ) { //员工
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(typeDepth);
				}
				
				
				 
				//如果和search匹配，则选中该节点
				if(search!=null) {
					if(nodeName.indexOf(search)!=-1) {
						item.addAttribute(SELECTED, SELECTED_VALUE);
					}
				}
				
				
				if("user".equals(urlDataFlag)) {
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText("../system/sysUserAction!showUserAndStaffInfo.do");
				}else if("area".equals(urlDataFlag)){
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText(depth);
				}else if("orga".equals(urlDataFlag)) {
					
				}else if("exch".equals(urlDataFlag)) {
					Element userdata=item.addElement(USER_DATA);
					userdata.addAttribute(ATTR_NAME, URL);
					userdata.addText("../system/exchAction!showExchInfo.do");
				}
				getTreeXml(treeNodeList.get(i), nodeList, subList,item,search);
				if (subList != null && subList.size() > 0) {
					for(TreeNodeInfo t:subList){
						if(t.getParentId()!=null && t.getParentId().equals(treeNodeList.get(i).getId())){
							item.addAttribute(CHILD, HAS_CHILD);
						}
					}
				}
			}
		}
		return pItem;
	}
	
	/**
	 * 根据节点列表创建树
	 * @param nodeList
	 * @return
	 * @throws AppException
	 * @throws SysException
	 */
	public static Document buildTree(TreeNodeInfo rootNode,List<TreeNodeInfo> nodeList,String search) throws AppException,SysException{
		Document treeDomXml = DocumentHelper.createDocument();
		Element treeElement = treeDomXml.addElement(ROOT);
		treeElement.addAttribute(ID, ROOT_ID);
		getTreeXml(rootNode, nodeList, null, treeElement,search);
		return treeDomXml;
	}
	 

	
}
