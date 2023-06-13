/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 */

import static dbconnection.MySQLJDBCUtil.dataSource;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import model.Permission;
import java.sql.*;
import java.util.*;
import model.Administrateur;

@ManagedBean
@ApplicationScoped

public class PermissionDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
    
    private List<Permission> permissionList;

    //*************************** display data *****************/
    public List<Permission> findAll() {
        
        permissionList = new ArrayList();
        
        try {
            String query = "SELECT * FROM permission WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Permission permission = new Permission(); 

                permission.setId(resultSet.getInt("id"));  
                permission.setAjouter(resultSet.getBoolean("ajouter"));  
                permission.setSupprimer(resultSet.getBoolean("supprimer"));  
                permission.setModifier(resultSet.getBoolean("modifier"));  
                permission.setAfficher(resultSet.getBoolean("afficher")); 
                
                permissionList.add(permission);  
            }   

            System.out.println("Total Records Fetched: " + permissionList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return permissionList;
    }

    //************** Save data **********************************/ 
    public Permission save(Permission permission){
        Permission  model= null;
        
        try {

            String query = 
                    "INSERT INTO permission (ajouter, supprimer, modifier, afficher) "
                    + "values (?, ?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setBoolean(1, permission.getAjouter());
            pstmt.setBoolean(2, permission.getSupprimer());
            pstmt.setBoolean(3, permission.getModifier());
            pstmt.setBoolean(4, permission.getAfficher());
            //statement.setDate(7, (java.sql.Date) permission.getDate());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }

    //************** find data by ID ***************************/
    public String findById(int permissionId) {
        
        Permission permission = null;
        System.out.println(" findById() : Permission Id: " + permissionId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM permission WHERE id =" + permissionId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                permission = new Permission();
                permission.setId(resultSet.getInt("id"));  
                permission.setAjouter(resultSet.getBoolean("ajouter"));  
                permission.setSupprimer(resultSet.getBoolean("supprimer"));  
                permission.setModifier(resultSet.getBoolean("modifier"));  
                permission.setAfficher(resultSet.getBoolean("afficher")); 
                //permission.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("permissionMapped", permission);
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public Permission update(Integer administrateurId){
        Permission permission = null;
        try {

            String query =""
                    + "UPDATE permission "
                    + "SET "
                    + "ajouter = ?, "
                    + "supprimer = ?, "
                    + "modifier = ?, "
                    + "afficher = ? "
                    + "where id_administrateur = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setBoolean(1, permission.getAjouter());
            pstmt.setBoolean(2, permission.getSupprimer());
            pstmt.setBoolean(3, permission.getModifier());
            pstmt.setBoolean(4, permission.getAfficher());
            pstmt.setInt(5, administrateurId);

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return permission;
    }

    //************** delete data ********************************/
    public void delete(int permissionId) {
        
        System.out.println("delete() : permission Id: " + permissionId);

        try {
            
            String query = "DELETE FROM permission WHERE id = " + permissionId ;
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    
    
    //************** error  message from sql ***********************/
    public void printSQLException(SQLException ex) {
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