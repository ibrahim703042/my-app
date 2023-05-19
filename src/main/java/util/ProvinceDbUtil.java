/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;


import dbconnection.MySQLJDBCUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.*;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

import model.Province;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@ApplicationScoped

public class ProvinceDbUtil implements Serializable {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    /// ************************ display data *************************/
    public static ArrayList findAll() {
        
        ArrayList provinceList = new ArrayList();  
        
        try {
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM province WHERE id IS NOT NULL ORDER BY id DESC";
            resultSet = statement.executeQuery(query);  
            
            while(resultSet.next()) { 
                
                Province province = new Province(); 
                
                province.setId(resultSet.getInt("id"));  
                province.setNomProvince(resultSet.getString("nomProvince"));  
                
                provinceList.add(province);  
            }   
            
            System.out.println("Total Records Fetched: " + provinceList.size());
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        } 
        
        return provinceList;
    }
    
    /// ************************ save data *************************/
    public static String save(Province province) {
        int saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {   
            
            String query = "insert into province (nomProvince) values (?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         
            pstmt.setString(1, province.getNomProvince());
            
            saveResult = pstmt.executeUpdate();
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        if(saveResult !=0) {
            navigationResult = "/pages/pays/province/template.xhtml?faces-redirect=true";
            showMessage(message);
            
        } else {
            navigationResult = "";
        }
        return navigationResult;
    }
    
    /// ************************ find data by ID *************************/
    public static String findById(int provinceId) {
        
        Province province = null;
        System.out.println(" findById() : Province Id: " + provinceId);

        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from province where id = " + provinceId);    
            
            if(resultSet != null) {
                resultSet.next();
                province = new Province(); 
                province.setId(resultSet.getInt("id"));
                province.setNomProvince(resultSet.getString("nomProvince"));

            }
            sessionMapObj.put("provinceMapped", province);
            connection.close();
        } catch(SQLException sqlException) {
            //addErrorMessage(sqlException);
            addErrorMessage(sqlException);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }

    /// ************************ update data *************************/
    public static String update(Province province) {
        
        try {
            String query = "update province set nomProvince=? where id = ?";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,province.getNomProvince());
            
            pstmt.executeUpdate();
            
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }

    /// ************************ delete data *************************/
    public static String delete(int provinceId){
        
        //System.out.println("delete() : Province Id: " + provinceId);
        
        try {
            
            String query = "delete from province where id = " + provinceId ;
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
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