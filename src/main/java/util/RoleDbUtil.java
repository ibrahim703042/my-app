/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;


import dbconnection.MySQLJDBCUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
//import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

import model.Role;

/**
 *
 * @author Ibrahim
 */


@Named
@ApplicationScoped

public class RoleDbUtil implements Serializable {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    /// ************************ display data *************************/
    public static ArrayList findAll() {
        
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
    public static String save(Role role) {
        int saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
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
            navigationResult = "/pages/pays/role/template.xhtml?faces-redirect=true";
            showMessage(message);
            
        } else {
            navigationResult = "";
        }
        return navigationResult;
    }
    
    /// ************************ find data by ID *************************/
    public static String findById(int roleId) {
        
        Role role = null;
        System.out.println(" findById() : role Id: " + roleId);

        /* Setting The Particular role Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from role where id = " + roleId);    
            
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
        return "/pages/pays/role/template.xhtml?faces-redirect=true";
    }

    /// ************************ update data *************************/
    public static String update(Role role) {
        
        try {
            String query = "update role set nomrole=? where id = ?";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,role.getNomRole());
            
            pstmt.executeUpdate();
            
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/pays/role/template.xhtml?faces-redirect=true";
    }

    /// ************************ delete data *************************/
    public static String delete(int roleId){
        
        //System.out.println("delete() : role Id: " + roleId);
        
        try {
            
            String query = "delete from role where id = " + roleId ;
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        return "/pages/pays/role/template.xhtml?faces-redirect=true";
    }

    /// ************************ show message after executing  *************************/
    private static void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
    /// ************************ error *************************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}