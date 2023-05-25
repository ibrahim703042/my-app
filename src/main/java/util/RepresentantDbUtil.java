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
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import model.Representant;
import java.sql.*;
import java.util.*;

@ManagedBean
@ApplicationScoped

public class RepresentantDbUtil implements Serializable {
    
    private static RepresentantDbUtil instance;
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    public static RepresentantDbUtil getInstance() throws Exception {
        if (instance == null) {
            instance = new RepresentantDbUtil();
        }
        return instance;
    }

    //*************************** display data *****************/
    
    public ArrayList findAll() {
        
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
    public void save(Representant representant){
        
        try {

            String query = ""
                    + "INSERT INTO representant "
                    + "("
                    + "nomRepresentant, "
                    + "prenomRepresentant, "
                    + "emailRepresentant, "
                    + "telephoneRepresentant, "
                    + "bpRepresentant"
                    + ") "
                    + "values (?, ?, ?, ?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setString(1, representant.getNomRepresentant());
            pstmt.setString(2, representant.getPrenomRepresentant());
            pstmt.setString(3, representant.getEmailRepresentant());
            pstmt.setInt(4, representant.getTelephoneRepresentant());
            pstmt.setString(5, representant.getBpRepresentant());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        } 
       
    }

    //************** find data by ID ***************************/
    public void findById(int representantId) {
        
        Representant representant = null;
        System.out.println(" findById() : Representant Id: " + representantId);
        
        /* Setting The Particular representant Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT * "
                    + "FROM representant "
                    + "WHERE id = " + representantId ;
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                representant = new Representant();
                representant.setId(resultSet.getInt("id"));  
                representant.setNomRepresentant(resultSet.getString("nomRepresentant"));  
                representant.setPrenomRepresentant(resultSet.getString("prenomRepresentant"));  
                representant.setEmailRepresentant(resultSet.getString("emailRepresentant"));    
                representant.setTelephoneRepresentant(resultSet.getInt("telephoneRepresentant"));  
                representant.setBpRepresentant(resultSet.getString("bpRepresentant"));  
                //representant.setDate(resultSet.getDate("date")); 

                
            }
            
            sessionMap.put("representMapped", representant);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }	
    //************** update data ******************************/
    public void update(Representant representant){

        try {

            String query = " "
                    + "UPDATE representant "
                    + "SET "
                    + "nomRepresentant = ?, "
                    + "prenomRepresentant = ?, "
                    + "emailRepresentant = ?, "
                    + "telephonRepresentante = ?, "
                    + "bpRepresentant = ? "
                    + "WHERE id = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, representant.getNomRepresentant());
            pstmt.setString(2, representant.getPrenomRepresentant());
            pstmt.setString(3,representant.getEmailRepresentant());
            pstmt.setInt(4, representant.getTelephoneRepresentant());
            pstmt.setString(5, representant.getBpRepresentant());
            pstmt.setInt(6, representant.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int representantId) {
        
        connection = MySQLJDBCUtil.getConnection();
        System.out.println("delete() : Representant Id: " + representantId);

        try {

            String query = "DELETE FROM representant WHERE id = " + representantId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
    }
    
   
    //************** error  message from sql ***********************/
    private void addErrorMessage(SQLException ex) {
        
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}