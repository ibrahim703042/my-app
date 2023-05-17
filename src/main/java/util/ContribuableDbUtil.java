/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
//import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import model.Contribuable;

import model.Province;


/**
 *
 * @author Ibrahim
 */

@Named
@ApplicationScoped

public class ContribuableDbUtil implements Serializable {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
     
    //************** data connection ***********************/  
    public static Connection getConnection() {
        
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");     
            String db_url ="jdbc:mysql://localhost:3306/db_impotFiscal",db_userName = "root",db_password = "";
            connection = DriverManager.getConnection(db_url,db_userName,db_password);  
        
        }catch(ClassNotFoundException | SQLException sqlException) {  
            sqlException.printStackTrace();
        } 
        
        return connection;
    }
    
    //*************************** display data *****************/
    public static ArrayList findAll() {
        ArrayList contribuableList = new ArrayList();  
        try {
            statement = getConnection().createStatement();
            String query = "SELECT * FROM contribuable WHERE id IS NOT NULL ORDER BY id DESC";
            resultSet = statement.executeQuery(query);  
            
            while(resultSet.next()) { 
                
                Contribuable contribuable = new Contribuable(); 
                
                contribuable.setId(resultSet.getInt("id"));  
                contribuable.setNom(resultSet.getString("nom"));  
                contribuable.setPrenom(resultSet.getString("prenom"));  
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
            addErrorMessage(sqlException);
        } 
        
        return contribuableList;
    }
    
    // insert into 
    public static String save(Province province) {
        int saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {   
            
            String query = "insert into province (nomProvince) values (?)";
            pstmt = getConnection().prepareStatement(query);         
            pstmt.setString(1, province.getNomProvince());
            
            saveResult = pstmt.executeUpdate();
            connection.close();
            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        if(saveResult !=0) {
            //navigationResult = "/pages/pays/province/template.xhtml?faces-redirect=true";
            showMessage(message);
            
        } else {
            navigationResult = "";
        }
        return navigationResult;
    }
    
    public static String findById(int provinceId) {
        
        Province province = null;
        System.out.println(" findById() : Province Id: " + provinceId);

        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("select * from province where id = " + provinceId);    
            
            if(resultSet != null) {
                resultSet.next();
                province = new Province(); 
                province.setId(resultSet.getInt("id"));
                province.setNomProvince(resultSet.getString("nomProvince"));

            }
            sessionMapObj.put("province", province);
            connection.close();
        } catch(SQLException sqlException) {
            //addErrorMessage(sqlException);
            addErrorMessage(sqlException);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }

    public static String update(Province province) {
        try {
            pstmt = getConnection().prepareStatement("update province set nomProvince=? where id = ? ");    
            pstmt.setString(1,province.getNomProvince());  
            pstmt.executeUpdate();
            connection.close();            
        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }

    public static String delete(int provinceId){
        System.out.println("delete() : Province Id: " + provinceId);
        
        try {
            
            pstmt = getConnection().prepareStatement("delete from province where id = " + provinceId );  
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }

    private static void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}