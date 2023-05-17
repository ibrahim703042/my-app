///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dbconnection;
//
///**
// *
// * @author Ibrahim
// */
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
//public class DataConnect {
//   
//    public static Connection getConnection() {
//        
//        try {
//
//            Class.forName("com.mysql.cj.jdbc.Driver");     
//            String db_url ="jdbc:mysql://localhost:3306/db_impotFiscal",db_userName = "root",db_password = "";
//            connection = DriverManager.getConnection(db_url,db_userName,db_password);  
//        
//        }catch(ClassNotFoundException | SQLException sqlException) {  
//            sqlException.printStackTrace();
//        } 
//        
//        return connection;
//    }
//
////    public static void close(Connection connection) {
////        try {
////            connection.close();
////        } catch (SQLException ex) {
////            
////            ex.printStackTrace();
////        }
////    }
//}
