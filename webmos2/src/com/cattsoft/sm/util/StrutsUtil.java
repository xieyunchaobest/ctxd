package com.cattsoft.sm.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.util.LabelValueBean;

/**
 * Title: CRM <br>
 * Description: 系统struts工具<br>
 * Date: 2007-8-11 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author anzhiqiang
 */
public class StrutsUtil {

	public StrutsUtil() {

	}

	/**
	 * <pre>
	 * 根据vo集合拼凑struts的LabelValueBean集合，供页面的select标签使用。
	 * 得到的LabelValueBean集合包括new LabelValueBean(&quot;全部&quot;, &quot;-1&quot;)，查询时可能更多使用。
	 * </pre>
	 * 
	 * @param vos
	 *            List
	 * @param labelFld
	 *            String
	 * @param valFld
	 *            String
	 * @throws ReflectUtilException
	 * @return List
	 */
	public static List getSelIncAll(List vos, String labelFld, String valFld)
			throws Exception {
		List retLst = getSel(vos, labelFld, valFld);
		retLst.add(0, new LabelValueBean("全部", "-1"));
		return retLst;
	}

	/**
	 * <pre>
	 * 根据vo集合拼凑struts的LabelValueBean集合，供页面的select标签使用。
	 * 得到的LabelValueBean集合不包括new LabelValueBean(&quot;全部&quot;, &quot;-1&quot;)，增加、修改时可能更多使用。
	 * 例如：
	 *     List provinceVOs=SMRegionDelegate.getDelegate().findProvs(new ProvinceVO());
	 *      form.setProvSel(StrutsUtil.getSelIncAll(provinceVOs,&quot;name&quot;,&quot;provId&quot;));
	 *      其中，form为SMProvinceActionForm，provinceVOs为ProvinceVO集合
	 *      name为ProvinceVO的属性，该属性将作为select标签的label属性
	 *      provId为ProvinceVO的属性，该属性将作为select标签的value属性
	 *      在页面中：
	 *      &lt; html:select property=&quot;provId&quot; size=&quot;1&quot; &gt;
	 *      &lt; html:optionsCollection property=&quot;provSel&quot;  label=&quot;label&quot; value=&quot;value&quot; /&gt;
	 *      &lt; /html:select &gt;
	 *      具体可参考sm下的程序
	 * 
	 * </pre>
	 * 
	 * @param vos
	 *            List
	 * @param labelFld
	 *            String
	 * @param valFld
	 *            String
	 * @throws ReflectUtilException
	 * @return List
	 */
	public static List getSel(List vos, String labelFld, String valFld)
			throws Exception {
		List retLst = new ArrayList();
		LabelValueBean option = null;
		Object obj = null;
		Object lbl = null;
		Object val = null;

		// add by zhangshuaia 2013-09-17
		if (!"0".equals(vos) && vos != null) {
			option = new LabelValueBean();
			option.setLabel("请选择...");
			option.setValue("0");
			retLst.add(option);
		}

		if (vos != null) {
			for (int i = 0; i < vos.size(); i++) {
				obj = vos.get(i);
				ReflectUtil l;
				lbl = ReflectUtil.invokeMethod(obj, ReflectUtil
						.getMethodNameByFldName(labelFld));
				val = ReflectUtil.invokeMethod(obj, ReflectUtil
						.getMethodNameByFldName(valFld));
				option = new LabelValueBean(lbl.toString(), val.toString());
				retLst.add(option);
			}
		}
		return retLst;

	}

	public static List getHourSel() {
		List retLst = new ArrayList();
		LabelValueBean option = null;
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				option = new LabelValueBean("0" + String.valueOf(i), "0"
						+ String.valueOf(i));
			} else
				option = new LabelValueBean(String.valueOf(i), String
						.valueOf(i));
			retLst.add(option);
		}
		return retLst;

	}

	public static List getMinSel() {
		List retLst = new ArrayList();
		LabelValueBean option = null;
		for (int i = 0; i < 61; i++) {
			if (i < 10) {
				option = new LabelValueBean("0" + String.valueOf(i), "0"
						+ String.valueOf(i));
			} else
				option = new LabelValueBean(String.valueOf(i), String
						.valueOf(i));
			retLst.add(option);
		}
		return retLst;

	}

	public static List getSecSel() {
		List retLst = new ArrayList();
		LabelValueBean option = null;
		for (int i = 0; i < 61; i++) {
			if (i < 10) {
				option = new LabelValueBean("0" + String.valueOf(i), "0"
						+ String.valueOf(i));
			} else
				option = new LabelValueBean(String.valueOf(i), String
						.valueOf(i));
			retLst.add(option);
		}
		return retLst;
	}

}
