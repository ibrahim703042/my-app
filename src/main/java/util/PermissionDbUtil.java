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
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
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
    private List<Administrateur> administrateurList;
    private Administrateur administrateur;
    private String query;

    //*************************** display data *****************/
    public List<Permission> findAll() {
        
        permissionList = new ArrayList();
        
        try {
            
            query = ""
                + "SELECT administrateur.*, "
                + "permission.* "
                + "FROM administrateur, permission "
                + "WHERE administrateur.id = permission.id_administrateur "
                + "ORDER BY permission.id_permission DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Permission permission = new Permission(); 

                permission.setId(resultSet.getInt("id_permission"));  
                permission.setAjouter(resultSet.getBoolean("ajouter"));  
                permission.setSupprimer(resultSet.getBoolean("supprimer"));  
                permission.setModifier(resultSet.getBoolean("modifier"));  
                permission.setAfficher(resultSet.getBoolean("afficher"));
                
                permission.setIdAdministrateur(resultSet.getInt("id"));  
                permission.setNom(resultSet.getString("nom"));  
                permission.setPrenom(resultSet.getString("prenom"));  
                
                
                permissionList.add(permission);  
            }   

            System.out.println("Total Records Fetched: " + permissionList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return permissionList;
    }
    
    public List<Administrateur> loadDropDown() {
        
        administrateurList = new ArrayList();
        
        query = ""
                + "SELECT * FROM administrateur "
                + "WHERE administrateur.id "
                + "IS NOT NULL "
                + "ORDER By administrateur.id DESC";
               
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                administrateur = new Administrateur(); 

                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP"));
                administrateur.setIsActive(resultSet.getString("isActive"));
                administrateur.setDate(resultSet.getDate("date"));
                
                administrateurList.add(administrateur);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return administrateurList;
    }

    //************** Save data **********************************/ 
    public Permission save(Permission permission){
        Permission  model= null;
        
        try {

            query = 
                    "INSERT INTO permission (id_administrateur, ajouter, supprimer, modifier, afficher) "
                    + "values (?, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, permission.getIdAdministrateur());
            pstmt.setBoolean(2, permission.getAjouter());
            pstmt.setBoolean(3, permission.getSupprimer());
            pstmt.setBoolean(4, permission.getModifier());
            pstmt.setBoolean(5, permission.getAfficher());
            //statement.setDate(7, (java.sql.Date) permission.getDate());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }
    //************** update data ******************************/
    public Permission update(Permission permission){
        Permission model = null;
        try {

            query = ""
                    + "UPDATE permission "
                    + "SET "
                    + "id_administrateur = ?, "
                    + "ajouter = ?, "
                    + "supprimer = ?, "
                    + "modifier = ?, "
                    + "afficher = ? "
                    + "where id_permission = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, permission.getIdAdministrateur());
            pstmt.setBoolean(2, permission.getAjouter());
            pstmt.setBoolean(3, permission.getSupprimer());
            pstmt.setBoolean(4, permission.getModifier());
            pstmt.setBoolean(5, permission.getAfficher());
            pstmt.setInt(6, permission.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }

    //************** delete data ********************************/
    public void delete(int permissionId) {
        
        System.out.println("delete() : permission Id: " + permissionId);

        try {
            
            query = "DELETE FROM permission WHERE id = " + permissionId ;
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