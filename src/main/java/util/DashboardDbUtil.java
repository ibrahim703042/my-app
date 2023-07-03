/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dbconnection.MySQLJDBCUtil;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import java.sql.SQLException;

/**
 *
 * @author Ibrahim
 * 
 */
@ManagedBean
@ApplicationScoped
public class DashboardDbUtil extends MySQLJDBCUtil {
    
    private static Integer countList = 0 ;
    private static Integer count;
    private static Integer administrateur;
    private static String query;
    
    public DashboardDbUtil(){
    }

    public Integer administrateurCount(){
        
        query = "SELECT COUNT(*) FROM administrateur ";
         
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            
            resultSet = pstmt.executeQuery();  
            resultSet.next();
            
            administrateur = resultSet.getInt(1);
            
         } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        
        return administrateur;
    }
    
    public static Integer countRole() {
        
        query = " SELECT COUNT(*) FROM role ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                count = resultSet.getInt(1);
                //connection.commit();
            }
           
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return countList;
    }
   
    public static Integer countProvince() {
        
        query = " SELECT COUNT(*) FROM province ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                count = resultSet.getInt(1);
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return countList;
    }

    public static Integer countCommune() {
        
        query = " SELECT COUNT(*) FROM commune ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                count = resultSet.getInt(1);
                
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return countList;
    }
    
    public static Integer countColline() {
        
        query = " SELECT COUNT(*) FROM colline ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                count = resultSet.getInt(1);
                //connection.commit();
            }
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return countList;
    }


}
