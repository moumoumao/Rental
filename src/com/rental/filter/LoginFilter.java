package com.rental.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest  httpReq  = (HttpServletRequest) request;  
	        HttpServletResponse httpResp = (HttpServletResponse) response; 
	        HttpSession session=httpReq.getSession();
	        String path = httpReq.getServletPath();
	        String name = httpReq.getServerName();
	        System.out.println("path:"+path+"\nname:"+name);
	        Cookie[] cookies=httpReq.getCookies(); 
	        if(path.equals("/html/sign-in.html")){
	        	chain.doFilter(request, response);
	        }else if(cookies!=null){
	        	boolean flag =true;
	        	for(int i=0;i<cookies.length;i++){
	      		  if(cookies[i].getName().equals("jfh_user")){ 
	      			  flag = false;
	      			chain.doFilter(request, response);
	      		  }
	      		 }
	        	if(flag){
	        	httpResp.sendRedirect("/JFHServer/html/sign-in.html");
	        	}
	        }else{
	        	httpResp.sendRedirect("/JFHServer/html/sign-in.html");
	        }
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
