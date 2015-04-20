package com.cattsoft.pub.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataCacheException extends GenericException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	/**
	 * SysException的跟踪调试信息，一般包括类名和方法名。
	 */
	private String trace = "";

	public DataCacheException() {
	}

	public DataCacheException(String errId, String errOwnMsg, Exception oriEx) {

		super(oriEx);
		this.errId = errId;
		this.errMsg += ":" + errOwnMsg;
		this.errMsgOri = oriEx.getMessage();
		this.errOri = oriEx;
		this.writeDataCacheException();
	}

	public DataCacheException(String errId, Exception oriEx) {
		super(oriEx);
		this.errId = errId;
		this.errMsgOri = oriEx.getMessage();
		this.errOri = oriEx;
		this.writeDataCacheException();
	}

	public DataCacheException(Exception oriEx) {
		super(oriEx);
		this.errMsgOri = oriEx.getMessage();
		this.errOri = oriEx;
		this.writeDataCacheException();
	}

	/**
	 * 添加跟踪信息，只有捕捉到SysException的时候才调用该函数。
	 * 
	 * @param msg
	 *            String 跟踪信息
	 */
	public void appendMsg(String msg) {
		trace += msg;
	}

	/**
	 * 返回跟踪信息
	 * 
	 * @return String
	 */
	public String getTrace() {
		return trace;
	}

	/**
	 * 打印原发错误信息
	 */
	public void printDebug() {
		// //System.out.println("errId:"+this.errId);
		// //System.out.println("errMsg:"+this.errMsg);
		// //System.out.println("trace:"+this.trace);
		// //System.out.println("errMsgOri:"+this.errMsgOri);
		this.errOri.printStackTrace();
	}

	private void writeDataCacheException() {
		Log log = LogFactory.getLog(DataCacheException.class);
		if (log.isDebugEnabled()) {
			log.debug("writeSysException begin");
		}
		if (log.isDebugEnabled()) {
			log.debug("id:" + this.errId);
			log.debug("msg:" + this.errMsg);
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(os);
		this.errOri.printStackTrace(p);
		if (log.isDebugEnabled())
			log.debug(os.toString());

	}
}
