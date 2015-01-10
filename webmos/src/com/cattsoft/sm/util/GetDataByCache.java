package com.cattsoft.sm.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.cattsoft.pub.exception.SysException;
import com.cattsoft.pub.util.DataCache;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-27 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class GetDataByCache {
	
    private static Logger log = Logger.getLogger(GetDataByCache.class);
    private static int lt=0;
    public static Map getServDeptMap(String areaId) throws Exception {
        try {
            if(areaId!=null){
            Map treeMap =  DataCache.getCache(DataCache.AREA_SERV_DEPT);
            //HashMap chiledMap = (HashMap) treeMap.get(localNetId);   
            Map nextMap = null;
            if(treeMap!=null)
            	nextMap = (Map) treeMap.get(areaId);
           /* HashMap servDeptMap = new HashMap();
            Iterator it = nextMap.keySet().iterator();
            while (it.hasNext()) {
                String servDeptId = (String) it.next();
                String servDeptName = DataCache.getString(DataCache.SERV_DEPT, servDeptId);
                servDeptMap.put(servDeptId, servDeptName);
            }*/
            return nextMap;}
            else return DataCache.getCache(DataCache.SERV_DEPT);
        } catch (Exception e) {
            throw new SysException("getCache error..", e);
        }
    }

    public static Map getAreaMap(String localNetId) throws Exception {
        try {
            if(localNetId!=null){
            Map treeMap = DataCache.getCache(DataCache.LOCAL_NET_AREA);
            
            Map childMap = null;
            if(treeMap!=null)
            	childMap=(Map)treeMap.get(localNetId);
            /*HashMap areaMap = new HashMap();
            Iterator it = chiledMap.keySet().iterator();
            while (it.hasNext()) {
                String areaId = (String) it.next();
                String areaName = DataCache.getString(DataCache.AREA, areaId);
                areaMap.put(areaId, areaName);
            }*/
            
  
            return childMap;}
            else return DataCache.getCache(DataCache.AREA);
        } catch (Exception e) {
            throw new SysException("getCache error..", e);
        }
    }

    public static Map getLocalNetMap() throws Exception {
        try {
            return DataCache.getCache(DataCache.LOCAL_NET);
        } catch (Exception e) {
            throw new SysException("getCache error..", e);
        }
    }

    public static List mapToSelectList(Map map,int length) throws Exception {
        LabelValueBean option = null;
        lt=length;
        List list = new ArrayList();
        if (map != null) {
            list = new ArrayList();
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                option = new LabelValueBean();
                java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                if(!entry.getKey().equals("0")){
                option.setLabel(entry.getValue().toString());
                option.setValue(entry.getKey().toString());
                list.add(option);}
            }
        }   
        if(list!=null&&list.size()>1){
        	if(length==0){
        		Collections.sort(list, new Comparator() { public int
        			compare(Object o1, Object o2) { LabelValueBean fnv1 = (LabelValueBean) o1; LabelValueBean fnv2 =
        				(LabelValueBean) o2; return Integer.parseInt(fnv1.getValue()) -
        				Integer.parseInt(fnv2.getValue()); } });}
        	else{
        		Collections.sort(list, new Comparator() { public int
        			compare(Object o1, Object o2) { LabelValueBean fnv1 = (LabelValueBean) o1; LabelValueBean fnv2 =
        				(LabelValueBean) o2; return Integer.parseInt(fnv1.getValue().substring(lt)) -
        				Integer.parseInt(fnv2.getValue().substring(lt)); } });
        	}
        }
        if(list!=null&&list.size()>0){
        	LabelValueBean allServDept=new LabelValueBean();
        	allServDept.setLabel("所有营维中心");
        	allServDept.setValue("0");
        	list.add(allServDept);
        }
        if(list==null){
        	list=getListByNull();
        }
        return list;
    }
    
    public static List servDeptMapToSelectList(Map map,int length) throws Exception {
        LabelValueBean option = null;
        lt=length;
        List list = new ArrayList();
        if (map != null) {
            list = new ArrayList();
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                option = new LabelValueBean();
                java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                if(!entry.getKey().equals("0")){
                option.setLabel(entry.getValue().toString());
                option.setValue(entry.getKey().toString());
                list.add(option);}
            }
        }   
     /* if(list!=null&&list.size()>1){
        	if(length==0){
        		Collections.sort(list, new Comparator() { public int
        			compare(Object o1, Object o2) { LabelValueBean fnv1 = (LabelValueBean) o1; LabelValueBean fnv2 =
        				(LabelValueBean) o2; return Integer.parseInt(fnv1.getValue()) -
        				Integer.parseInt(fnv2.getValue()); } });}
        	else{
        		Collections.sort(list, new Comparator() { public int
        			compare(Object o1, Object o2) { LabelValueBean fnv1 = (LabelValueBean) o1; LabelValueBean fnv2 =
        				(LabelValueBean) o2; return Integer.parseInt(fnv1.getValue().substring(lt)) -
        				Integer.parseInt(fnv2.getValue().substring(lt)); } });
        	}
        }*/
        if(list!=null&&list.size()>0){
        	LabelValueBean allServDept=new LabelValueBean();
        	allServDept.setLabel("所有营维中心");
        	allServDept.setValue("0");
        	list.add(allServDept);
        }
        if(list==null){
        	list=getListByNull();
        }
        return list;
    }
    public static Map getBranchMap(String servDeptId) throws Exception {
        try {
            if(servDeptId!=null){
            Map treeMap = DataCache.getCache(DataCache.SERV_DEPT_BRANCH);
            Map childMap = null;
            if(treeMap!=null)
            	childMap = (Map)treeMap.get(servDeptId);
            //HashMap nextMap = (HashMap) childMap.get(servDeptId);
           
            return childMap;}
            else return DataCache.getCache(DataCache.BRANCH);
        } catch (Exception e) {
            throw new SysException("getCache error..", e);
        }
    }
    public static Map getWorkAreaMap() throws Exception {
        try {
            Map treeMap =  DataCache.getCache(DataCache.WORK_AREA);
            
            return treeMap;
        } catch (Exception e) {
            throw new SysException("getCache error..", e);
        }
    }
    public static Map getExchMap(String areaId) throws Exception {
        try {
            Map treeMap =  DataCache.getCache(DataCache.AREA_EXCH);
            Map childMap=(Map)treeMap.get(areaId);
            return childMap;
        } catch (Exception e) {
            throw new SysException("getCache error..", e);
        }
    }
   public static Map getSysConfig() throws Exception{
       try {
           Map treeMap =  DataCache.getCache(DataCache.SYS_CONFIG);
           return treeMap;
       } catch (Exception e) {
           throw new SysException("getCache error..", e);
       }
   }
    public static List getListByNull() {
        List list = new ArrayList();
        LabelValueBean bean = new LabelValueBean();
        bean.setLabel("");
        bean.setValue("");
        list.add(bean);
        return list;
    }
}
