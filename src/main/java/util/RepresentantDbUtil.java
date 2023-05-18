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
import model.Representant;
import java.sql.*;
import java.util.*;

@Named
@ApplicationScoped

public class RepresentantDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static ArrayList findAll() {
        
        ArrayList representantList = new ArrayList();
        
        try {
            String query = "SELECT * FROM representant WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Representant representant = new Representant(); 

                representant.setId(resultSet.getInt("id"));  
                representant.setNomRepresentant(resultSet.getString("nomRepresentant"));  
                representant.setPrenomRepresentant(resultSet.getString("prenomRepresentant"));  
                representant.setEmailRepresentant(resultSet.getString("emailRepresentant"));    
                representant.setTelephoneRepresentant(resultSet.getInt("telephoneRepresentant"));  
                representant.setBpRepresentant(resultSet.getString("bpRepresentant"));  
                representant.setDate(resultSet.getDate("date")); 

                representantList.add(representant);  
            }   

            System.out.println("Total Records Fetched: " + representantList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return representantList;
    }

    //************** Save data **********************************/ 
    public static String save(Representant representant){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO representant (nomRepresentant, prenomRepresentant, emailRepresentant, telephoneRepresentant, bpRepresentant) "
                    + "values (?, ?, ?, ?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setString(1, representant.getNomRepresentant());
            pstmt.setString(2, representant.getPrenomRepresentant());
            pstmt.setString(3, representant.getEmailRepresentant());
            pstmt.setInt(4, representant.getTelephoneRepresentant());
            pstmt.setString(5, representant.getBpRepresentant());
            //statement.setDate(7, (java.sql.Date) representant.getDate());

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
    public static String findById(int representantId) {
        
        Representant representant = null;
        System.out.println(" findById() : Province Id: " + representantId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM representant WHERE id =" + representantId ;
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                representant = new Representant();
                representant.setId(resultSet.getInt("id"));  
                representant.setNomRepresentant(resultSet.getString("nomRepresentant"));  
                representant.setPrenomRepresentant(resultSet.getString("prenomRepresentant"));  
                representant.setEmailRepresentant(resultSet.getString("emaiRepresentantl"));
                representant.setTelephoneRepresentant(resultSet.getInt("telephoneRepresentant"));  
                representant.setBpRepresentant(resultSet.getString("bpRepresentant"));  
                //representant.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("adminMapped", representant);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Representant representant){

        String message = "Updated Successfully";

        try {

            String query ="Update representant SET "
                    + "nomRepresentant=?, "
                    + "prenomRepresentant=?, "
                    + "telephonRepresentante=?, "
                    + "bpRepresentant=? "
                    + "where id= ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, representant.getNomRepresentant());
            pstmt.setString(2, representant.getPrenomRepresentant());
            pstmt.setInt(3, representant.getTelephoneRepresentant());
            pstmt.setString(4, representant.getBpRepresentant());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int representantId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : representant Id: " + representantId);

        try {

            String query = "DELETE FROM representant WHERE id = " + representantId ;
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