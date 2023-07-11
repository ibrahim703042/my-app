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
    
    private List<Abbattement> abbattementList;
    private Abbattement abbattement;
    
    //*************************** display data *****************/
    public List<Abbattement> findAll() {
        
        abbattementList = new ArrayList<>();
        
        try {
            String query = ""
                    + "SELECT contribuable.nom,contribuable.email, abbattement.* "
                    + "FROM contribuable, abbattement "
                    + "WHERE contribuable.id = abbattement.id_contribuable "
                    + "AND contribuable.id IS NOT NULL ORDER BY contribuable.id DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            //Contribuable cont = new Contribuable();
            while(resultSet.next()) { 

                abbattement = new Abbattement(); 

                abbattement.setNom(resultSet.getString("nom"));  
                abbattement.setEmail(resultSet.getString("email")); 
                abbattement.setIdContribuable(resultSet.getInt("id_contribuable")); 
                
                abbattement.setId(resultSet.getInt("id_abbattement"));  
                abbattement.setBeneficiaire(resultSet.getString("beneficiaire"));  
                abbattement.setMotifAbbattement(resultSet.getString("motif"));  

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
        
        int count =0;
        try {

            String query = "INSERT INTO abbattement (id_contribuable, beneficiaire, motif) VALUES (?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, abbattement.getIdContribuable());
            pstmt.setString(2, abbattement.getBeneficiaire());
            pstmt.setString(3, abbattement.getMotifAbbattement());
            
            count = pstmt.executeUpdate();
            connection.close();
            
            if (count > 0){
                showInfo("Success", "Data Added Successfully.");
            }else{
                showError("Error", "Operation failed.");
            }

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Abbattement abbattement){
            
        int count =0;
        try {

            String query =""
                    + "UPDATE abbattement SET "
                    + "id_contribuable = ?,"
                    + "beneficiaire=?, "
                    + "motif=? "
                    + "WHERE id_abbattement = ?";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, abbattement.getIdContribuable());
            pstmt.setString(2, abbattement.getBeneficiaire());
            pstmt.setString(3, abbattement.getMotifAbbattement());
            pstmt.setInt(4, abbattement.getId());

            count = pstmt.executeUpdate();
            connection.close();
            
            if (count > 0){
                showInfo("Success", "Data Updated Successfully.");
            }else{
                showError("Error", "Operation failed.");
            }

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            
    }

    //************** delete data ********************************/
    public void delete(int abbattementId) {
        
        System.out.println("delete() : Abbattement Id: " + abbattementId);

        int count = 0;
        
        try {
            
            connection = dataSource.getConnection();
            String query = "DELETE FROM abbattement WHERE id_abbattement = " + abbattementId ;
            pstmt = connection.prepareStatement(query);
            
            count = pstmt.executeUpdate();
            connection.close();
            
            if (count > 0){
                showInfo("Delete", "Data Deleted Successfully.");
            }else{
                showError("Error", "Operation failed.");
            }
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
    }
    
    
}