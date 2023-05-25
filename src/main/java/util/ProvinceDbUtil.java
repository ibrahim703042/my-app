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
    public ArrayList findAll() {
        
        ArrayList provinceList = new ArrayList();  
        
        try {
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM province P WHERE P.id IS NOT NULL ORDER BY P.nomProvince";
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
    public void save(Province province) {
        
        try {   
            
            String query = "INSERT INTO province (nomProvince) VALUES (?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         
            pstmt.setString(1, province.getNomProvince());
            
            pstmt.executeUpdate();
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
    
    /// ************************ find data by ID *************************/
    public void findById(int provinceId) {
        
        Province province = null;
        System.out.println(" findById() : Province Id: " + provinceId);

        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            
            String query = "SELECT * FROM province WHERE id = " + provinceId ;
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet != null) {
                resultSet.next();
                province = new Province(); 
                province.setId(resultSet.getInt("id"));
                province.setNomProvince(resultSet.getString("nomProvince"));

            }
            sessionMapObj.put("provinceMapped", province);
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    /// ************************ update data *************************/
    public void update(Province province) {
        
        try {
            String query = "UPDATE province SET nomProvince = ? WHERE id = ? ";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,province.getNomProvince());
            pstmt.setInt(2,province.getId());
            
            pstmt.executeUpdate();
            
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    /// ************************ delete data *************************/
    public void delete(int provinceId){
        
        System.out.println("delete() : Province Id: " + provinceId);
        
        try {
            
            String query = "DELETE FROM province WHERE id = " + provinceId ;
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
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