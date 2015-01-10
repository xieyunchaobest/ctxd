package com.cattsoft.pub;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";

	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	
	private static final String SMTP = "smtp";
	
	private static final String  ENCODING="GBK";

	private MimeMessage mimeMsg; //MIME邮件物件   

	private Session session; //邮件会话物件   

	private Properties props; //系统属性   

	private String username = ""; //smtp认证用户名和密码   

	private String password = "";

	private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象   

	private String mailHostName = "";

	private String needAuth = "false"; //smtp是否需要认证

	private String mailTitle = "   测试邮件"; //邮件标题   

	private String mailContent = "这是邮件正文"; //邮件正文

	private String sendFile = "";//发送附件
	
	private String sendMailName ="";//发送者邮箱
	
	private String[] receiveMailName={""};//接收者邮箱
	
	
	private static MailUtil mailUtil = null;//synchronized static

	/**
	 *缺省构造类
	 */
	private MailUtil() {
	}
	/**
	 * 
	 * @return
	 */
    public synchronized static MailUtil getInstance(){
    	if(mailUtil == null)
    		mailUtil = new MailUtil();
      return mailUtil;
    }
	/**   
	 *   @return   boolean   
	 */
	private boolean createMimeMessage(){
		try {
			if (props == null) {
				props = System.getProperties(); //获得系统属性对象
			}
			if(mailHostName == null || mailHostName.trim().length() == 0){
				System.err.println("请设置邮箱服务器名！");
				return false;
			}
			props.put(MAIL_SMTP_HOST, mailHostName); //设置SMTP主机   
			props.put(MAIL_SMTP_AUTH, needAuth); //设置身份认证为false    		
			//System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); //获得邮件会话对象
		} catch (Exception e) {
			e.printStackTrace();
			//System.err.println("获取邮件事务时发生错误！" + e);
			return false;
		}
		//System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); //创建MIME邮件对象   
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			//System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	private boolean setMail() {
		try {
			mimeMsg.setSubject(mailTitle, ENCODING); //主题 
			if(sendMailName == null || sendMailName.trim().length() == 0){
				System.err.println("请设置邮箱发送人邮箱！");
				return false;
			}
			if(receiveMailName == null || receiveMailName.length == 0){
				System.err.println("请设置邮箱接收人邮箱！");
				return false;
			}
			if(mailContent == null || mailContent.trim().length() == 0){
				System.err.println("请输入邮件内容！");
				return false;
			}
			mimeMsg.setFrom(new InternetAddress(sendMailName)); //发信人   
			BodyPart bp = new MimeBodyPart();
			bp.setText("正文");
			mp.addBodyPart(bp);
			bp.setContent(
					"<meta http-equiv=Content-Type content=text/html;   charset=gb2312>"
							+ mailContent, "text/html;charset=GB2312");
			if(sendFile.trim().length() > 0){
				mp.addBodyPart(bp);
				bp = new MimeBodyPart();
				FileDataSource fileds = new FileDataSource(sendFile);
				bp.setDataHandler(new DataHandler(fileds));
				bp.setFileName(fileds.getName()); //附件 
			}
			mp.addBodyPart(bp);
		    InternetAddress[] sendTo = new InternetAddress[receiveMailName.length];
		    for (int i = 0; i < receiveMailName.length; i++)
		       {
		         //System.out.println("发送到:" + receiveMailName[i]);
		         sendTo[i] = new InternetAddress(receiveMailName[i]);
		       }
			
			mimeMsg.setRecipients(Message.RecipientType.TO,sendTo);
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			//System.err.println("邮件设置失败!" + e);
			return false;
		}
	}

	/**
	 * 发送邮件
	 * @return
	 */
	private boolean sendout() {
		try {
			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport(SMTP);
			if(needAuth == null || needAuth.trim().length() == 0){
				needAuth = "false";				
			}
			if(needAuth.trim().toUpperCase().equals("TRUE")){
				if(username == null || username.trim().length() == 0){
					System.err.println("请输入认证用户名和密码邮件！");
					return false;
				}
			}
			transport.connect((String) props.get(MAIL_SMTP_HOST), username,password);
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("邮件发送失败！" + e);
			return false;
		}
	}
	/**
	 *发送邮件
	 */
    public void sendMail(){
    	boolean bRet = false;
    	try{
    		bRet = this.createMimeMessage();
    		if(!bRet)
    			return;
    		bRet = this.setMail();
    		if(!bRet)
    			return;
    		bRet = this.sendout();
    		if(!bRet)
    			return;    		
    	}catch(Exception e){
    		System.err.println("邮件发送失败！" + e);
    		e.printStackTrace();
    	}
    }
    /**
     * 认证邮箱用户名
     * @return
     */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    /**
     * 认证邮箱密码
     * @return
     */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    /**
     * 邮箱服务器名
     * @return
     */
	public String getMailHostName() {
		return mailHostName;
	}

	public void setMailHostName(String mailHostName) {
		this.mailHostName = mailHostName;
	}
    /**
     * 是否需要邮箱认证
     * 如果需要needAuth＝"true"否则needAuth＝"false"
     * @return
     */
	public String getNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(String needAuth) {
		this.needAuth = needAuth;
	}
    /**
     * 邮件标题
     * @return
     */
	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
    /**
     * 邮件正文
     * @return
     */
	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
    /**
     * 邮件附件
     * @return
     */
	public String getSendFile() {
		return sendFile;
	}

	public void setSendFile(String sendFile) {
		this.sendFile = sendFile;
	}
    /**
     * 发送邮件邮箱
     * @return
     */
	public String getSendMailName() {
		return sendMailName;
	}

	public void setSendMailName(String sendMailName) {
		this.sendMailName = sendMailName;
	}
    /**
     * 接收邮件邮箱
     * @return
     */
	public String[] getReceiveMailName() {
		return receiveMailName;
	}

	public void setReceiveMailName(String[] receiveMailName) {
		this.receiveMailName = receiveMailName;
	}
}
