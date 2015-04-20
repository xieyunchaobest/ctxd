package com.cattsoft.webpub.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cattsoft.pub.util.StringUtil;


public class SessionFilter implements Filter {
 
    /** 要检查的 session 的名称 */
    private String sessionKey;
     
    /** 需要排除（不拦截）的URL的正则表达式 */
    private Pattern excepUrlPattern;
     
    /** 检查不通过时，转发的URL */
    private String forwardUrl;
 
    public void init(FilterConfig cfg) throws ServletException {
        sessionKey ="user";
        forwardUrl ="/page/login.jsp";
    }
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String servletPath = request.getServletPath();
 
        // 如果请求的路径与forwardUrl相同，或请求的路径是排除的URL时，则直接放行
        if (validate(servletPath,req)) {
            chain.doFilter(req, res);
            return;
        }
 
        Object sessionObj = request.getSession().getAttribute(sessionKey);
        // 如果Session为空，则跳转到指定页面
        if (sessionObj == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath +"/page/login.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }

	public void destroy() {
		
	}
	
	private boolean validate(String path,ServletRequest req) {
		String method=req.getParameter("method");
		if(path.equals("/page/login.jsp")) {
			return true;
		}
		if("login".equals(method)) {
			return true;
		}
		return false;
	}
 
}