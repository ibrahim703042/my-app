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
import model.Immeuble;
import java.sql.*;
import java.util.*;

@Named
@ApplicationScoped

public class ImmeubleDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static ArrayList findAll() {
        
        ArrayList immeubleList = new ArrayList();
        
        try {
            String query = "SELECT * FROM immeuble WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Immeuble immeuble = new Immeuble(); 

                immeuble.setIdImmeuble(resultSet.getInt("id_immeuble"));  
                immeuble.setIdContibuable(resultSet.getInt("id_contibuable"));  
                immeuble.setIdAvenue(resultSet.getInt("id_avenue")); 

                immeubleList.add(immeuble);  
            }   

            System.out.println("Total Records Fetched: " + immeubleList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return immeubleList;
    }

    //************** Save data **********************************/ 
    public static String save(Immeuble immeuble){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO immeuble (id_contibuable, id_avenue) "
                    + "values (?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, immeuble.getIdContibuable());
            pstmt.setInt(2, immeuble.getIdAvenue());
            //statement.setDate(7, (java.sql.Date) immeuble.getDate());

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
    public static String findById(int immeubleId) {
        
        Immeuble immeuble = null;
        System.out.println(" findById() : Province Id: " + immeubleId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM immeuble WHERE id =" + immeubleId ;
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                immeuble = new Immeuble();
                immeuble.setIdImmeuble(resultSet.getInt("id_immeuble"));  
                immeuble.setIdContibuable(resultSet.getInt("id_contibuable"));  
                immeuble.setIdAvenue(resultSet.getInt("id_avenue")); 
                //immeuble.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("immeubleMapped", immeuble);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Immeuble immeuble){

        String message = "Updated Successfully";

        try {

            String query ="Update immeuble SET "
                    + "id_contibuable=?, "
                    + "id_avenue=?, ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, immeuble.getIdContibuable());
            pstmt.setInt(2, immeuble.getIdAvenue());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int immeubleId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : immeuble Id: " + immeubleId);

        try {

            String query = "DELETE FROM immeuble WHERE id = " + immeubleId ;
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