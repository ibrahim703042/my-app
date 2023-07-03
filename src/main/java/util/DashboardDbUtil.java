/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dbconnection.MySQLJDBCUtil;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.sql.SQLException;

/**
 *
 * @author Ibrahim
 * 
 */
@ManagedBean
@ViewScoped
public class DashboardDbUtil extends MySQLJDBCUtil {
    
    private static Integer administrateur;
    private static Integer contribuable;
    private static Integer representant;
    private static Integer immeuble;
    private static Integer province;
    private static Integer commune;
    private static Integer colline;
    private static Integer role;
    private static String query;
    
    public DashboardDbUtil(){
    }

    public Integer adminCount(){
        
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
    
    public Integer taxPayerCount(){
        
        query = "SELECT COUNT(*) FROM contribuable ";
         
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            
            resultSet = pstmt.executeQuery();  
            resultSet.next();
            
            contribuable = resultSet.getInt(1);
            
         } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        
        return contribuable;
    }
    
    public Integer reperesentative(){
        
        query = "SELECT COUNT(*) FROM representant ";
         
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            
            resultSet = pstmt.executeQuery();  
            resultSet.next();
            
            representant = resultSet.getInt(1);
            
         } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        
        return representant;
    }
    
    public Integer building(){
        
        query = "SELECT COUNT(*) FROM immeuble ";
         
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            
            resultSet = pstmt.executeQuery();  
            resultSet.next();
            
            immeuble = resultSet.getInt(1);
            
         } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        
        return immeuble;
    }
    
    public static Integer countRole() {
        query = " SELECT COUNT(*) FROM role ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                role = resultSet.getInt(1);
                //connection.commit();
            }
           
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return role;
    }
   
    public static Integer countProvince() {
        
        query = " SELECT COUNT(*) FROM province ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                province = resultSet.getInt(1);
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return province;
    }

    public static Integer countCommune() {
        
        query = " SELECT COUNT(*) FROM commune ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                commune = resultSet.getInt(1);
                
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return commune;
    }
    
    public static Integer countColline() {
        
        query = " SELECT COUNT(*) FROM colline ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                colline = resultSet.getInt(1);
                //connection.commit();
            }
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return colline;
    }


}
