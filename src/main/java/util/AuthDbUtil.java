/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import dbconnection.MySQLJDBCUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;

import java.sql.*;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@ApplicationScoped
public class AuthDbUtil extends MySQLJDBCUtil {

    public AuthDbUtil() {
    }
    
    private static String query;

    //************** authentification  ***********************/
    
    public static boolean validate(String email,String motPasse) throws SQLException {
        
        String isActive = "Actif";
          query = " "
                + "SELECT administrateur.*, role.nomRole, role.description "
                + "FROM administrateur, role "
                + "WHERE administrateur.id_role = role.id "
                + "AND email = ? AND motPasse = ? AND isActive = ? " ;
        
        try {
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, motPasse);
            pstmt.setString(3, isActive);

            resultSet = pstmt.executeQuery();
            
            while(resultSet.next()) { 
                return true;
            }

        }catch (SQLException ex) {
            printSQLException(ex);
            return false;
        } finally {
            connection.close();
        }
        return false;
    }
    
    public static boolean validateContribuable(String email,String motPasse) throws SQLException {
        
        query = "SELECT * FROM contribuable WHERE email = ? AND motPasse = ? ";
        
        try {
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, motPasse);

            resultSet = pstmt.executeQuery();
            
            while(resultSet.next()) { 
                return true;
                //connection.commit();
            }

        }catch (SQLException ex) {
            printSQLException(ex);
            return false;
        } finally {
            connection.close();
        }
        return false;
    }
    
    
    public void warningMessage(String msg1, String msg2){
        FacesContext.getCurrentInstance().addMessage( null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,msg1,msg2));
    }
    
    // ******   Message Session ******/
    public void showInfo(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, content, msg);
    }
    
    public void showWarn(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, content, msg);
    }

    public void showError(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, content, msg);
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                }
            }
        }
    }
    
}