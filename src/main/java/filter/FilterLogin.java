///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.logging.Filter;
//import java.util.logging.LogRecord;
//import model.Administrateur;
//
///**
// *
// * @author Ibrahim
// */
//
//@WebFilter(urlPatterns={"/*"})
//public class FilterLogin implements Filter{
//
//    public void destroy() {
//        // TODO Auto-generated method stub
//
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession();
//
//        Administrateur authAdministrateur = (Administrateur) session.getAttribute("authAdministrateur");
//
//        String url = req.getServletPath();
//
//        if (
//                !url.equalsIgnoreCase("/index.xhtml") 
//                && url.indexOf("/javax.faces.resource",0) == -1 
//                && authAdministrateur == null
//            ){
//            
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.xhtml");
//                dispatcher.forward(request, response);
//                
//                return;
//        }else{
//
//            chain.doFilter(request, response);
//            
//        }
//    }
//
//    public void init(FilterConfig arg0) throws ServletException {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public boolean isLoggable(LogRecord record) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//}
