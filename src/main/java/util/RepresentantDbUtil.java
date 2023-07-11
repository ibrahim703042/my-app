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
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import model.Representant;
import java.sql.*;
import java.util.*;

@ManagedBean
@ApplicationScoped

public class RepresentantDbUtil extends MySQLJDBCUtil{
    private List< Representant>  representantList;
    private Representant representant;
    //*************************** display data *****************/
    
    public List< Representant> findAll() {
        
        representantList = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM representant WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                representant = new Representant(); 

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
            connection = dataSource.getConnection();
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
        
        representant = null;
        System.out.println(" findById() : Representant Id: " + representantId);
        
        /* Setting The Particular representant Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT * "
                    + "FROM representant "
                    + "WHERE id = " + representantId ;
            
            connection = dataSource.getConnection();
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
                representant.setDate(resultSet.getDate("date")); 

                
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

            String query = ""
                    + "UPDATE representant "
                    + "SET "
                    + "nomRepresentant = ?, "
                    + "prenomRepresentant = ?, "
                    + "emailRepresentant = ?, "
                    + "telephoneRepresentant = ?, "
                    + "bpRepresentant = ? "
                    + "WHERE id = ? ";

            connection = dataSource.getConnection();
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
        
        System.out.println("delete() : Representant Id: " + representantId);

        try {

            String query = "DELETE FROM representant WHERE id = ? " ;
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,representantId);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
    }
    
}