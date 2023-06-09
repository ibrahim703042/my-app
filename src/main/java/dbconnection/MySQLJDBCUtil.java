/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dbconnection;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author Ibrahim
 * 
 */

public class MySQLJDBCUtil {
   
   private static final String DB_URL = "jdbc:mysql://localhost:3306/db_impotfiscal";
   private static final String USER = "root";
   private static final String PASS = "";
   private static final String DRIVER= "com.mysql.cj.jdbc.Driver";
   public static final BasicDataSource dataSource = new BasicDataSource();

    static {
        
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        dataSource.setDriverClassName(DRIVER);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}