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
//        
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession(false);
//        HttpServletResponse resp = (HttpServletResponse) response;
//        
//        //Administrateur loggedUser = (Administrateur) session.getAttribute("loggedUser");
//
//        String reqURI = req.getRequestURI();
//        
//        
//        if (
//            reqURI.contains("/login.xhtml")
//            || (session != null && session.getAttribute("username") != null)
//            || reqURI.contains("javax.faces.resource")
//        ){
//            
//            chain.doFilter(request, response);
//           
//            
//        }else{
//            resp.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
//            
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