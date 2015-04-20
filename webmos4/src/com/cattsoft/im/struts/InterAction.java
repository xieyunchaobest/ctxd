package com.cattsoft.im.struts;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.util.SysConfigData;

/**
 * Title: CRM <br>
 * Description: <br>
 * Date: 2007-7-24 <br>
 * Copyright (c) 2007 CATTSoft <br>
 * 
 * @author fushuang
 */

public class InterAction extends DispatchAction {

	private static Logger log = Logger.getLogger(InterAction.class);


	public InterAction() {
	}

	/**
	 * 测试web_mos与IOM是否联通
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param rep
	 * @return
	 * @throws Exception
	 */
	public ActionForward talkToIOM(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse rep) throws Exception {
		log.debug("WEB_MOS正在向IOM发起连接：InterAction.talkToIOM");
//		String msg = new String(req.getParameter("msgToIOM").getBytes("ISO-8859-1"),"GBK");
		String msg = req.getParameter("msgToIOM");
		String returnMsg = "";
		String urlStr="";
		try{
    		urlStr = SysConfigData.getSysConfigCurValue(SysConstants.SYS_CONFIG_MOS_LOCAL_SERVER_ADDR, null, null, null, null, null);
    		urlStr = urlStr + "IM4MOSServices";
			log.debug("IOM侧webservice地址为： " + urlStr);
			Service service = new Service();
			Call call = (Call) service.createCall();// 通过service创建call对象     
			// 设置service所在URL     
			call.setTargetEndpointAddress(new java.net.URL(urlStr));
			call.setOperationName("svcCallIOMByMosNative");
			returnMsg = (String) call.invoke(new Object[] { SysConstants.FUNCODE_MOS_TALK_TO_IOM,"",msg });
		} catch (RemoteException e) {
			returnMsg = "连接IOM时报错" + urlStr + e.getMessage();
		}catch (MalformedURLException e) {
			returnMsg = "连接IOM时报错" + urlStr + e.getMessage();
		} catch (ServiceException e) {
			returnMsg = "连接IOM时报错" + urlStr + e.getMessage();
		}
		req.setAttribute("IOMToMOS", returnMsg);
		req.setAttribute("msgToIOM", msg);
		return actionMapping.findForward("self");
	}
}
