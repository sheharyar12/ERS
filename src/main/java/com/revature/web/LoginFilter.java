package com.revature.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		
		HttpServletRequest request = (HttpServletRequest)req;
		System.out.println(request.getRequestURI());
		//ErsUser user = (ErsUser) request.getSession().getAttribute("userSession");
		if(request.getSession().getAttribute("userSession")==null){
			chain.doFilter(req, res);
			HttpServletResponse response = (HttpServletResponse) res;
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}else{
			if(request.getSession().getAttribute("userSession")!=null && 
					(request.getRequestURI().equals("/ERS/index.jsp") || request.getRequestURI().equals("/ERS/user.jsp") 
							|| request.getRequestURI().equals("/ERS/manager.jsp"))){
				HttpServletResponse response = (HttpServletResponse) res;
				request.getRequestDispatcher("loginServlet").forward(request, response);
			}
			else{
				HttpServletResponse response = (HttpServletResponse) res;
				request.getRequestDispatcher(request.getRequestURI().substring(5)).forward(request, response);
			}
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
