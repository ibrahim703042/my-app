//package filter;
//
//import java.io.IOException;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
//@SuppressWarnings("IndexOfReplaceableByContains")
//
//public class AuthorizationFilter implements Filter {
//
//    public AuthorizationFilter() {
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
//
//    try {
//
//        HttpServletRequest reqt = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession ses = reqt.getSession(false);
//
//        String reqURI = reqt.getRequestURI();
//        if (
//            reqURI.indexOf("/login.xhtml") >= 0
//            || (ses != null && ses.getAttribute("username") != null)
//            || reqURI.indexOf("/public/") >= 0
//            || reqURI.contains("javax.faces.resource")){
//            
//            chain.doFilter(request, response);
//            
//        }else{
//            resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
//        }
//
//        } catch (ServletException | IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}