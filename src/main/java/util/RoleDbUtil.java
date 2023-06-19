/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;

import dbconnection.MySQLJDBCUtil;
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import java.sql.*;
import java.util.*;

import model.Role;

/**
 *
 * @author Ibrahim
 */


@ManagedBean
@ApplicationScoped

public class RoleDbUtil extends MySQLJDBCUtil {
    
    private String query;
    
    private List<Role> roles;

    // ************************ display data *************************/
    @PostConstruct
    public void init(){
        
        roles = new ArrayList<>();
        
        query = "SELECT * FROM role WHERE id IS NOT NULL ORDER BY id DESC";
        
        try {        
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  
            
            while(resultSet.next()) { 
                
                Role role = new Role(); 
                role.setId(resultSet.getInt("id"));  
                role.setNomRole(resultSet.getString("nomRole"));  
                role.setDescription(resultSet.getString("description"));  
                
                roles.add(role);
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
    }
    
    public List<Role> findAll(){
        
        roles = new ArrayList<>();
        query = "SELECT * FROM role WHERE id IS NOT NULL ORDER BY id DESC";
        
        try {        connection = dataSource.getConnection();

            
            //connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  
            
            while(resultSet.next()) { 
                
                Role role = new Role(); 
                role.setId(resultSet.getInt("id"));  
                role.setNomRole(resultSet.getString("nomRole"));  
                role.setDescription(resultSet.getString("description"));  
                
                roles.add(role);
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        
       return roles;
    }
    
    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }
    
    // ************************ save data *************************/
    public Role save(Role role) {
        Role model = null;
        
        try {   
            
            connection = dataSource.getConnection();
            
            connection.setAutoCommit(false);
            
            query = "INSERT INTO role (nomrole, description, creerPar) values (?,?,?)";
            pstmt = connection.prepareStatement(query);  

            String admin = "Super Administrateur";
            
            pstmt.setString(1, role.getNomRole());
            pstmt.setString(2, role.getNomRole());
            //pstmt.setString(3, role.getNomRole());
            pstmt.setString(3, admin);
            
            pstmt.executeUpdate();
           connection.commit();
                               
            //connection.close();

            
        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }
    
    // ************************ update data *************************/
    public Role update(Role role) {
        //Role role = null;
        
        try {        
            connection = dataSource.getConnection();
            query ="UPDATE role SET nomRole = ?, description = ? WHERE id = ? ";
            
            
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,role.getNomRole());
            pstmt.setString(2,role.getDescription());
            pstmt.setInt(3,role.getId());
            
            pstmt.executeUpdate();
            connection.close();
            
        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return role;
    }

    // ************************ delete data *************************/
    public Role delete(Integer roleId){
        Role role = null;
        try {        
            connection = dataSource.getConnection();
            query = "DELETE FROM role WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,roleId);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        return role;
    }
   
}