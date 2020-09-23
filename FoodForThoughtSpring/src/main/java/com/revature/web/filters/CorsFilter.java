//package com.revature.web.filters;
//
//import java.io.IOException;  
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//public class CorsFilter implements Filter {
//	
//	@Override
//	   public void init(FilterConfig filterconfig) throws ServletException {}
//	
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		System.out.println("CORS Filter leveraged");
//		
//		if(!(response instanceof HttpServletResponse)) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		
//		HttpServletResponse res = (HttpServletResponse) response;
//		
//		res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//		//star instead of null
//		
//		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");
//		
//		res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type,"
//				+ "Access-Control-Request-Method, Access-Control-Request-Headers,"
//				+ "Access-Control-Allow-Origin, Access-Control-Allow-Headers");
//		
//		res.setHeader("Access-Control-Allow-Credentials", "true");
//		
//		
//		chain.doFilter(request, response);
//		
//	}
//	
//	@Override
//	   public void destroy() {}
//
//}