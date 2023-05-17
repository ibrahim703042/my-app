/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dbconnection;

import java.sql.*;


/**
 *
 * @author Ibrahim
 * 
 */

public class MySQLJDBCUtil {

   public static final String DB_URL = "jdbc:mysql://localhost:3306/db_impotfiscal";
   public static final String USER = "root";
   public static final String PASS = "";
   public static Statement statement;
   public static Connection connection;
   
    public static Connection getConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER,PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database.getConnection() Error -->"+ ex.getMessage());
        }
        return connection;
    }
    
    
//    public static void close(Connection connection) {
//        try {
//
//            connection.close();
//
//        }catch (SQLException ex) {
//
//            ex.printStackTrace();
//        }
//    }
    
}