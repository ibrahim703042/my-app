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
import model.Abbattement;
import java.sql.*;
import java.util.*;

@ManagedBean
@ApplicationScoped

public class AbbattementDbUtil  extends MySQLJDBCUtil {
   
    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList abbattementList = new ArrayList();
        
        try {
            String query = "SELECT * FROM abbattement WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Abbattement abbattement = new Abbattement(); 

                abbattement.setId(resultSet.getInt("id"));  
                abbattement.setBeneficiaire(resultSet.getString("beneficiaire"));  
                //abbattement.setMotif(resultSet.getString("motif")); 

                abbattementList.add(abbattement);  
            }   

            System.out.println("Total Records Fetched: " + abbattementList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return abbattementList;
    }

    //************** Save data **********************************/ 
    public void save(Abbattement abbattement){
        
        try {

            String query = "INSERT INTO abbattement (id_contribuable, benificiare, motif) VALUES (?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, abbattement.getId());
            pstmt.setString(2, abbattement.getBeneficiaire());
           // pstmt.setSelectedMotifs(3, abbattement.getSelectedMotifs());
            //statement.setDate(7, (java.sql.Date) abbattement.getDate());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** find data by ID ***************************/
    public void findById(int abbattementId) {
        
        Abbattement abbattement = null;
        System.out.println(" findById() : Abbattement Id: " + abbattementId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM abbattement WHERE id =" + abbattementId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                abbattement = new Abbattement();
                abbattement.setId(resultSet.getInt("id"));  
                abbattement.setBeneficiaire(resultSet.getString("beneficiaire"));  
                //abbattement.setMotif(resultSet.getString("motif")); 
                //abbattement.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("abbattementMapped", abbattement);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Abbattement abbattement){

        try {

            String query =""
                    + "UPDATE abbattement SET "
                    + "id_contribuable = ?,"
                    + "beneficiaire=?, "
                    + "motif=? "
                    + "WHERE id = ?";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, abbattement.getBeneficiaire());
            //pstmt.setString(2, abbattement.getMotif());
            pstmt.setInt(3, abbattement.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            
    }

    //************** delete data ********************************/
    public void delete(int abbattementId) {
        
        System.out.println("delete() : Abbattement Id: " + abbattementId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM abbattement WHERE id = " + abbattementId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}