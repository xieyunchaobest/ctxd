package com.cattsoft.pub.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.exception.AppException;
import com.cattsoft.pub.exception.SysException;

public class CollectionFactory {
	
	public static String COLLECTION_LIST = "List"; // 集合类型List
	
	public static String COLLECTION_SET = "Set"; // 集合类型Set
	
	public static String COLLECTION_LINKEDLIST = "LinkedList"; // 集合类型LinkedList
	
	public static String COLLECTION_MAP = "Map"; // 集合类型Map
	
	
	/**
	 * 根据参数创建一个Collection对象
	 * @param param
	 * @return	
	 * @throws AppException
	 * @throws SysException 
	 */
	public static Collection createCollection(String param) throws AppException, SysException {
		if (null == param)
			return null;
     
		Collection colObject = null;
		//从配置表获取查询条数限制的开关
		String limittedFlag;
		
			limittedFlag = SysConfigData.getSysConfigCurValue(SysConstants.LIMITTD_QUERY_RECORD, null,
					null, null, null, null);
		
		
		if(!StringUtil.isBlank(limittedFlag)&& SysConstants.TRUE.equals(limittedFlag)){
			
			if(COLLECTION_LIST.equalsIgnoreCase(param)){
				colObject=new LimitedArrayList();				
			}else if(COLLECTION_SET.equalsIgnoreCase(param)){
				colObject=new LimitedHashSet();				
			}else if(COLLECTION_LINKEDLIST.equalsIgnoreCase(param)){
				colObject=new LimitedLinkedList(); 
			}else{
				throw new AppException("610017","根据参数:"+param+"创建集合对象失败！没有找到匹配项! ");
			}
		}else{
			if(COLLECTION_LIST.equalsIgnoreCase(param)){
				colObject=new ArrayList();				
			}else if(COLLECTION_SET.equalsIgnoreCase(param)){
				colObject=new HashSet();				
			}else if(COLLECTION_LINKEDLIST.equalsIgnoreCase(param)){
				colObject=new LinkedList(); 
			}else{
				throw new AppException("610017","根据参数:"+param+"创建集合对象失败！没有找到匹配项! ");
			}
		}
		return colObject;
	}
	
	/**
	 * 根据参数创建一个Map对象
	 * @param param
	 * @return	 
	 * @throws AppException
	 * @throws SysException 
	 */
	public static Map createMap(String param) throws AppException, SysException {
		if (null == param)
			return null;
     
		Map colObject = null;
		//从配置表获取查询条数限制的开关
		String limittedFlag;
		
			limittedFlag = SysConfigData.getSysConfigCurValue(SysConstants.LIMITTD_QUERY_RECORD, null,
					null, null, null, null);
		
		
		if(!StringUtil.isBlank(limittedFlag)&& SysConstants.TRUE.equals(limittedFlag)){
			
			if(COLLECTION_MAP.equalsIgnoreCase(param)){
				colObject=new LimitedHashMap();				
			}else{
				throw new AppException("610018","根据参数:"+param+"创建集合对象失败！没有找到匹配项! ");
				
			}
		}else{
			if(COLLECTION_MAP.equalsIgnoreCase(param)){
				colObject=new HashMap();				
			}else{
				throw new AppException("610018","根据参数:"+param+"创建集合对象失败！没有找到匹配项! ");
				
			}
		}
		return colObject;
	}

}
