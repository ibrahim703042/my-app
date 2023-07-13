/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentification;


import dbconnection.MySQLJDBCUtil;
import static dbconnection.MySQLJDBCUtil.connection;
import static dbconnection.MySQLJDBCUtil.dataSource;
import static dbconnection.MySQLJDBCUtil.pstmt;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;

import java.sql.*;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@ApplicationScoped
public class AuthentificatorDbUtil extends MySQLJDBCUtil {

    public AuthentificatorDbUtil() {
    }
    
    private static String query;

    //************** authentification  ***********************/
    
    public static boolean validate_1(String email, String motPasse) throws SQLException {
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
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException ex) {
                printSQLException(ex);
                System.out.println("Login error -->" + ex.getMessage());
                return false;
        } finally {
            connection.close();
        }
        return false;
    }
    
    
    public static boolean validate_2(String email, String motPasse) throws SQLException {
        
        query = "SELECT * FROM contribuable WHERE email = ? AND motPasse = ? ";
        
        try {
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, email);
            pstmt.setString(2, motPasse);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException ex) {
            printSQLException(ex);
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } 
        finally {
            connection.close();
        }
        return false;
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