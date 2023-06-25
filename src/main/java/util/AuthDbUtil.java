/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import dbconnection.MySQLJDBCUtil;
import static dbconnection.MySQLJDBCUtil.connection;
import static dbconnection.MySQLJDBCUtil.dataSource;
import static dbconnection.MySQLJDBCUtil.pstmt;
import static dbconnection.MySQLJDBCUtil.resultSet;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Administrateur;
import model.Contribuable;

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
    
    public static List<Administrateur> authentificator(String email,String motPasse) {

        List<Administrateur> administrateurList = new ArrayList();
        
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
             
            
            if(resultSet.next()) {
                
                Administrateur administrateur = new Administrateur();
                
                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP")); 
                administrateur.setIsActive(resultSet.getString("isActive"));  
                
                /********** Role *********/
                
                administrateur.setRoleId(resultSet.getInt("id_role"));  
                administrateur.setNomRole(resultSet.getString("nomRole")); 
               
                administrateurList.add(administrateur);

            }
            
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return administrateurList;
    }
    
    public static List<Contribuable> authentificato(String email,String motPasse) {

        List<Contribuable> contribuableList = new ArrayList();
        
        query = "SELECT * FROM contribuable WHERE email = ? AND motPasse = ? ";
        
        try {
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, email);
            pstmt.setString(2, motPasse);
            
            resultSet = pstmt.executeQuery();
             
            
            if(resultSet.next()) {
                
               Contribuable contribuable = new Contribuable(); 

               contribuable.setId(resultSet.getInt("id"));  
               contribuable.setNom(resultSet.getString("nom"));  
               contribuable.setEmail(resultSet.getString("email"));  
               contribuable.setMotPasse(resultSet.getString("motPasse"));  
               contribuable.setTelephone(resultSet.getInt("telephone"));  
               contribuable.setBp(resultSet.getString("BP"));  
               contribuable.setDate(resultSet.getDate("date")); 
               
               contribuableList.add(contribuable);  
            }   

            System.out.println("Total Records Fetched: " + contribuableList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return contribuableList;
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