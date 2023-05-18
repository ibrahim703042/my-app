/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 */

import dbconnection.MySQLJDBCUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import model.Permission;
import java.sql.*;
import java.util.*;

@Named
@ApplicationScoped

public class PermissionDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static ArrayList findAll() {
        
        ArrayList permissionList = new ArrayList();
        
        try {
            String query = "SELECT * FROM permission WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Permission permission = new Permission(); 

                permission.setId(resultSet.getInt("id"));  
                permission.setAjouter(resultSet.getShort("ajouter"));  
                permission.setSupprimer(resultSet.getShort("supprimer"));  
                permission.setModifier(resultSet.getShort("modifier"));  
                permission.setAfficher(resultSet.getShort("afficher")); 

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
    public static String save(Permission permission){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO permission (ajouter, supprimer, modifier, afficher) "
                    + "values (?, ?, ?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setShort(1, permission.getAjouter());
            pstmt.setShort(2, permission.getSupprimer());
            pstmt.setShort(3, permission.getModifier());
            pstmt.setShort(4, permission.getAfficher());
            //statement.setDate(7, (java.sql.Date) permission.getDate());

            saveResult = pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }if(saveResult !=0) {
            navigationResult = "/pages/admin/template.xhtml?faces-redirect=true";
            showMessage(message);
            
        } else {
            navigationResult = "";
        }
        return navigationResult;
    }

    //************** find data by ID ***************************/
    public static String findById(int permissionId) {
        
        Permission permission = null;
        System.out.println(" findById() : Province Id: " + permissionId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM permission WHERE id =" + permissionId ;
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                permission = new Permission();
                permission.setId(resultSet.getInt("id"));  
                permission.setAjouter(resultSet.getShort("ajouter"));  
                permission.setSupprimer(resultSet.getShort("supprimer"));  
                permission.setModifier(resultSet.getShort("modifier"));  
                permission.setAfficher(resultSet.getShort("afficher")); 
                //permission.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("permissionMapped", permission);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Permission permission){

        String message = "Updated Successfully";

        try {

            String query ="Update permission SET "
                    + "ajouter=?, "
                    + "supprimer=?, "
                    + "modifier=?, "
                    + "afficher=? "
                    + "where id= ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setShort(1, permission.getAjouter());
            pstmt.setShort(2, permission.getSupprimer());
            pstmt.setShort(3, permission.getModifier());
            pstmt.setShort(4, permission.getAfficher());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int permissionId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : permission Id: " + permissionId);

        try {

            String query = "DELETE FROM permission WHERE id = " + permissionId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        return "/pages/admin/template.xhtml?faces-redirect=true";
    }
    
    
    //************** conecxt msg data ***********************/
    private static void showMessage(String msg){
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}