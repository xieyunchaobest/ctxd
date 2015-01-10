package com.cattsoft.sm.util;

import java.io.IOException; 

import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import org.apache.struts.action.ActionForm; 

import com.cattsoft.pub.SysConstants;
import com.cattsoft.pub.util.PasswordUtil; 
import com.cattsoft.pub.util.SysConfigData;
import com.cattsoft.sm.struts.SMLoginActionForm; 
import com.cattsoft.sm.vo.SysUserMVO; 
import com.linkage.component.bean.adm.Encryptor;

public class CountServ extends HttpServlet {
	
	/**
	 * Constructor of the object.
	 */
	public CountServ() {
		super();
	}

	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//        System.out.println("succeed");
//        HttpSession session = request.getSession();
//       
//        System.out.println(session.getId());
//        //request.getCookies();
//		response.setContentType("text/html");
//        String sessionId="";
//            
//            sessionId=session.getId();
//            SysUserExtendedMVO suve=null;
//            try {
//              suve=this.login(request, response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            session.setAttribute(Constant.SYS_USER_NAME, suve);
//            
//        
//        System.out.println(request.getParameter("username"));
//        PrintWriter out = response.getWriter();
//        String url="http://10.48.66.83:7001/web/sm/sMSysRoleAction.do?method=gotoMain&subSystemName=SE";
//      
//        out.print(sessionId+","+url);
//        //response.sendRedirect(url);
//
//		//String stype = (String) request.getParameter("in_stype");
//
//		//executeProc(stype);
//	}
    
    
     /**
     * 用户登陆
     * 
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//    public SysUserExtendedMVO login(
//            HttpServletRequest req, HttpServletResponse rep) throws Exception {
//        // begin 其他系统通过超链接登陆CRM系统密码为MD5的密文所以不需要加密.
//        String userName=req.getParameter("username");
//        String password=req.getParameter("password");
//        SysUserMVO vo = new SysUserMVO();
//        vo.setSysUserName(userName);
//        vo.setPassword(PasswordUtil.getMD5Str(password));
//        // 把登录地址传给ejb
//        LoginLogSVO ll = new LoginLogSVO();
//        ll.setSysDeviceMac(req.getRemoteHost());
//        ll.setIpAddr(req.getRemoteAddr());
//        Set s = new HashSet(); // 通过set将登录日志vo传递给ejb。
//        s.add(ll);
//        vo.setLoginLogs(s);
//        SysUserExtendedMVO suve = null;
//        suve = SMSysUserDelegate.getDelegate().login(vo, SMReqUtil.getLocalNetId(req));
//        WorkAreaMVO mvo=new WorkAreaMVO();
//        mvo.setLocalNetId("318");
//        mvo.setAreaId("31801");
//        mvo.setLocalNetIscenter("N");
//        mvo.setAreaIscenter("Y");
//        suve.setCurrentWorkAreaVO(mvo);
//        return suve;
//
//    }
    /**
     * 从Form中取出值组成vo对象。
     * 
     * @param af
     *            ActionForm
     * @return SysUserVO
     */
    private SysUserMVO getVOFromForm(ActionForm af) throws Exception {
        SMLoginActionForm aftmp = (SMLoginActionForm) af;
        SysUserMVO vo = new SysUserMVO();
        vo.setSysUserName(aftmp.getUserName());
        //added by yangkai 2009-8-26 加密算法选择，如果是N或没有配置则使用MD5算法，否则为联创提供的算法
        String flag=SysConfigData.getSysConfigCurValue("110010", null,null, null, null, null);
        if(flag==null ||flag.equals(SysConstants.PWD_FLAG))
         vo.setPassword(PasswordUtil.getMD5Str(aftmp.getPassword()));
        else vo.setPassword(Encryptor.fnEncrypt(aftmp.getPassword(), "00linkage"));
        return vo;
    }


	private void executeProc(String stype) {
		// TODO Auto-generated method stub
//		String procedure = "{call ADD_WEB_ACCESS(?) }";
//		Connection conn = null;
//		try {
//			DBOperation db = new DBOperation();
//			conn = db.getConInstance();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//		
//		CallableStatement cstmt;
//		try {
//			cstmt = conn.prepareCall(procedure);
//			cstmt.setString(1, stype);
//			cstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally{
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

