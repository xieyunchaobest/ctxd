package com.cattsoft.sm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-8-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class ReflectUtil {
	
    private static Log log = LogFactory.getLog(ReflectUtil.class);

    public ReflectUtil() {
    }

    /**
     * 调用某个对象的方法。
     * 
     * <pre>
     * ProvinceVO vo = new ProvinceVO();
     * 
     * String name = (String) ReflectUtil.invokeMethod(vo, &quot;getName&quot;);
     * </pre>
     * 
     * @param obj
     *            Object
     * @param funcName
     *            String
     * @throws ReflectUtilException
     * @return Object
     */
    public static Object invokeMethod(Object obj, String funcName) throws Exception {
        if (obj == null)
            return null;
        Class cla = obj.getClass();
        try {
            Method m = cla.getDeclaredMethod(funcName, null);
            return m.invoke(obj, null);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * 调用某个对象的方法。
     * 
     * <pre>
     * ProvinceVO vo = new ProvinceVO();
     * 
     * String name = (String) ReflectUtil.invokeMethod(vo, &quot;getName&quot;);
     * </pre>
     * 
     * @param obj
     *            Object
     * @param funcName
     *            String
     * @throws ReflectUtilException
     * @return Object
     */
    public static Object invokeMethod(Object obj, String fldName, Object[] ref) throws Exception {
        if (obj == null)
            return null;
        Class cla = obj.getClass();
        try {
            Field fld = cla.getDeclaredField(fldName);
            String funcName = getSetMethodNameByFldName(fldName);
            if (log.isDebugEnabled()) {
                log.debug("fld.getType()----------------------" + fld.getType());
            }

            Method m = cla.getDeclaredMethod(funcName, new Class[] { fld.getType() });
            return m.invoke(obj, ref);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * 根据属性名得到属性方法，get 方法
     * 
     * <pre>
     * ProvinceVO vo = new ProvinceVO();
     * 
     * String name = (String) ReflectUtil.invokeMethod(vo, ReflectUtil.getMethodNameByFldName(&quot;name&quot;));
     * </pre>
     * 
     * @param fldName
     *            String
     * @return String
     */
    public static String getMethodNameByFldName(String fldName) {
        if (fldName == null)
            return null;
        return "get" + fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
    }

    /**
     * 根据属性名得到属性方法,Set方法
     * 
     * <pre>
     * ProvinceVO vo = new ProvinceVO();
     * 
     * String name = (String) ReflectUtil.invokeMethod(vo, ReflectUtil.getMethodNameByFldName(&quot;name&quot;));
     * </pre>
     * 
     * @param fldName
     *            String
     * @return String
     */
    public static String getSetMethodNameByFldName(String fldName) {
        if (fldName == null)
            return null;
        return "set" + fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
    }

    /**
     * 得到List里对象集合的某个属性值的组合，以"--"隔开，适于测试时使用。
     * 
     * <pre>
     * List lst=new ArrayList();
     * ....给lst付值，例如ProvinceVO的集合
     * log.debug(ReflectUtil.getListCont(lst,&quot;name&quot;));
     * </pre>
     * 
     * @param lst
     *            List
     * @param fldName
     *            String
     * @throws ReflectUtilException
     * @return String
     */
    public static String getListCont(List lst, String fldName) throws Exception {
        if (lst == null)
            return null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lst.size(); i++) {
            Object obj = lst.get(i);
            sb.append(invokeMethod(obj, getMethodNameByFldName(fldName)) + "--");
        }
        return sb.toString();
    }

    public static String getArrayCont(Object[] objs) {
        if (objs == null)
            return null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < objs.length; i++) {
            sb.append(objs[i] + "--");
        }
        return sb.toString();
    }

    /**
     * 根据HashSet来取得HashSet中vo集合拼凑的hql的where条件，如：
     * 
     * <pre>
     * ProvinceVO vo=null;
     *      HashSet latestSet=new HashSet();
     *      vo=new ProvinceVO();
     *      vo.setProvId(new Integer(1));
     *      latestSet.add(vo);
     *      vo=new ProvinceVO();
     *      vo.setProvId(new Integer(2));
     *      latestSet.add(vo);
     *      try {
     *      //System.out.println(ReflectUtil.getLatestHqlWhereInt(latestSet, &quot;province&quot;,
     *      &quot;provId&quot;));
     *      }
     *      catch (ReflectUtilException ex) {
     *      }
     * 
     * </pre>
     * 
     * @param latestSet
     *            HashSet
     * @param poName
     *            String hql中po的别名，as 后面的名字
     * @param fldName
     *            String 要拼凑的属性名
     * @throws ReflectUtilException
     * @return String
     */
    public static String getLatestHqlWhereInt(HashSet latestSet, String poName, String fldTableName,String fldName)
            throws Exception {
        if (latestSet == null || latestSet.size() == 0)
            return " 1=0 ";
        StringBuffer sb = new StringBuffer();
        Iterator ita = latestSet.iterator();
        Object obj = null;
        while (ita.hasNext()) {
            obj = ita.next();
            sb.append(poName + "." + fldTableName + "=");
            sb.append("'"+invokeMethod(obj, getMethodNameByFldName(fldName))+"'");
            sb.append(" or ");
        }
        String retStr = sb.toString();
        return retStr.substring(0, retStr.length() - 4);
    }

    /**
     * field类型为String的拼凑hql的方法
     * 
     * @param latestSet
     *            HashSet
     * @param poName
     *            String
     * @param fldName
     *            String
     * @throws ReflectUtilException
     * @return String
     */
    public static String getLatestHqlWhereStr(HashSet latestSet, String poName, String fldName)
            throws Exception {
        if (latestSet == null || latestSet.size() == 0)
            return " 1=0 ";
        StringBuffer sb = new StringBuffer();
        Iterator ita = latestSet.iterator();
        Object obj = null;
        while (ita.hasNext()) {
            obj = ita.next();
            sb.append(poName + "." + fldName + "='");
            sb.append(invokeMethod(obj, getMethodNameByFldName(fldName)) + "'");
            sb.append(" or ");
        }
        String retStr = sb.toString();
        return retStr.substring(0, retStr.length() - 4);
    }

    public static List getListFromArray(Object[] objs) {
        List retLst = new ArrayList();
        for (int i = 0; i < objs.length; i++)
            retLst.add(objs[i]);
        return retLst;
    }

    /**
     * 将vo list根据vo的主键生成Set。供根据主键判是否包含某vo使用。 mjh 2005-01-18
     * 
     * @param lst
     *            List
     * @param fldName
     *            String
     * @return Set
     */
    public static Set getSetFromListKey(List lst, String fldName) throws Exception {
        Set set = new HashSet();
        if (lst == null || lst.size() == 0)
            return set;
        Object obj = null;
        for (int i = 0; i < lst.size(); i++) {
            obj = lst.get(i);
            set.add(invokeMethod(obj, getMethodNameByFldName(fldName)));
        }
        return set;
    }

    /**
     * 合并两个list，将重复的项去掉。
     * 
     * @param lst1
     *            List
     * @param lst2
     *            List
     * @param fldName
     *            String
     * @throws ReflectUtilException
     * @return List
     */
    public static List getMergeList(List lst1, List lst2, String fldName) throws Exception {
        if (lst1 == null || lst1.size() == 0)
            return lst2;
        if (lst2 == null || lst2.size() == 0)
            return lst1;
        Object obj = null;
        Set set = getSetFromListKey(lst1, fldName);
        Set settmp = new HashSet(); // 没比较不同加一次后，要把该值记录下来，再被加list出现同样的值时候可以区别并不再加。
        for (int i = 0; i < lst2.size(); i++) {
            obj = invokeMethod(lst2.get(i), getMethodNameByFldName(fldName));
            if (!set.contains(obj) && !settmp.contains(obj)) {
                lst1.add(lst2.get(i));
                settmp.add(obj);
            }
        }
        return lst1;
    }

    /**
     * 从一个list去除另外一个list包含的项。
     * 
     * @param lstSrc
     *            List
     * @param lstClear
     *            List
     * @param fldName
     *            String
     * @throws ReflectUtilException
     * @return List
     */
    public static List getClearList(List lstSrc, List lstClear, String fldName)
            throws Exception {
        if (lstClear == null || lstClear.size() == 0)
            return lstSrc;
        if (lstSrc == null || lstSrc.size() == 0)
            return lstSrc;
        Object obj = null;
        Set set = getSetFromListKey(lstClear, fldName);
        for (int i = 0; i < lstSrc.size(); i++) {
            obj = invokeMethod(lstSrc.get(i), getMethodNameByFldName(fldName));
            if (set.contains(obj)) {
                lstSrc.remove(lstSrc.get(i));
            }
        }
        return lstSrc;
    }
}
