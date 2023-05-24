/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;


import dbconnection.MySQLJDBCUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

import model.Role;

/**
 *
 * @author Ibrahim
 */


@ManagedBean
@ApplicationScoped

public class RoleDbUtil implements Serializable {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    /// ************************ display data *************************/
    public ArrayList findAll() {
        
        ArrayList roleList = new ArrayList();  
        
        try {
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM role WHERE id IS NOT NULL ORDER BY id DESC";
            resultSet = statement.executeQuery(query);  
            
            while(resultSet.next()) { 
                
                Role role = new Role(); 
                
                role.setId(resultSet.getInt("id"));  
                role.setNomRole(resultSet.getString("nomRole"));  
                
                roleList.add(role);  
            }   
            
            System.out.println("Total Records Fetched: " + roleList.size());
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        } 
        
        return roleList;
    }
    
    /// ************************ save data *************************/
    public String save(Role role) {
        int saveResult = 0;
        String navigationResult = "";
        
        try {   
            
            String query = "insert into role (nomrole) values (?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         
            pstmt.setString(1, role.getNomRole());
            
            saveResult = pstmt.executeUpdate();
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        if(saveResult !=0) {
            navigationResult = "/pages/role/template.xhtml?faces-redirect=true";
            
        } else {
            navigationResult = "";
        }
        return navigationResult;
    }
    
    /// ************************ find data by ID *************************/
    public String findById(int roleId) {
        
        Role role = null;
        System.out.println(" findById() : Role Id: " + roleId);

        /* Setting The Particular role Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            
            String query = "SELECT * FROM role WHERE id = " + roleId ;
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query );    
            
            if(resultSet != null) {
                resultSet.next();
                role = new Role(); 
                role.setId(resultSet.getInt("id"));
                role.setNomRole(resultSet.getString("nomRole"));

            }
            sessionMapObj.put("roleMapped", role);
            connection.close();
        } catch(SQLException sqlException) {
            //addErrorMessage(sqlException);
            addErrorMessage(sqlException);
        }
        return "/pages/role/edit.xhtml?faces-redirect=true";
    }

    /// ************************ update data *************************/
    public String update(Role role) {
        
        try {
            String query = " UPDATE role SET nomrole = ? WHERE id = ? ";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,role.getNomRole());
            
            pstmt.executeUpdate();
            
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/role/template.xhtml?faces-redirect=true";
    }

    /// ************************ delete data *************************/
    public String delete(int roleId){
        
        //System.out.println("delete() : role Id: " + roleId);
        
        try {
            
            String query = "DELETE FROM role WHERE id = " + roleId ;
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        return "/pages/role/template.xhtml?faces-redirect=true";
    }
    
    /// ************************ error *************************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}