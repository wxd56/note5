package com.wxd.note5.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wxd.note5.model.WebConstants;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

	private List<String> URLWhiteList = new ArrayList<String>();
	
    /**
     * Default constructor. 
     */
    public SecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 
	    HttpServletRequest httpReq = (HttpServletRequest) request;
	    HttpServletResponse httpResp = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession();
	 	
		//设置basePath	
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" +   request.getServerPort() +  httpReq.getContextPath() + "/";
		request.setAttribute("baseURL", basePath);
		
		
		Object accessToken = session.getAttribute(WebConstants.ACCESS_TOKEN);
		String accessURL = httpReq.getRequestURI();
		
		//如果没有验证通过
		if(accessToken == null){
			
			//是否包含着白名单内
			if(URLWhiteList.contains(accessURL)){
				chain.doFilter(request, response);
			}else{
				//返回404错误
				httpResp.setStatus(404);			
			}
			
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String ctxPath = fConfig.getServletContext().getContextPath();

		if(ctxPath.equals("/")){
			ctxPath = "";
		}

		URLWhiteList.add(ctxPath + "/login/showLoginUI.do");
		URLWhiteList.add(ctxPath + "/login/doLogin.do");
	}

}
