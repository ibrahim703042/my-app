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
import jakarta.faces.bean.*;
import jakarta.faces.context.FacesContext;
import model.Administrateur;
import java.sql.*;
import java.util.*;
import model.RevenuLocatif;

@ManagedBean
@ApplicationScoped

public class RevenusDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public ArrayList findAll() {
        ArrayList revenuLocatifList = new ArrayList();
        String query = "SELECT * FROM revenulocatif WHERE id IS NOT NULL ORDER BY id DESC";
        connection = MySQLJDBCUtil.getConnection();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                RevenuLocatif revenuLocatif = new RevenuLocatif(); 

                revenuLocatif.setId(resultSet.getInt("id"));  
                revenuLocatif.setLoyerExonere(resultSet.getDouble("loyerExonere"));  
                revenuLocatif.setLoyerImposable(resultSet.getDouble("loyerImposable"));  
                revenuLocatif.setChargeIncombat(resultSet.getDouble("chargeIncombat"));  
                revenuLocatif.setInteretEmprunt(resultSet.getDouble("interetEmprunt"));  
                

                revenuLocatifList.add(revenuLocatif);
                //connection.commit();
                
            }   

            System.out.println("Total Records Fetched: " + revenuLocatifList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return revenuLocatifList;
    }
    
    //************** Save data **********************************/ 
    public void save(RevenuLocatif revenuLocatif){
        
        try {

            String query ="INSERT INTO revenulocatif (id_immeuble, loyerExonere, loyerImposable, interetEmprunt) "
                    + "values (?, ?, ?, ?)";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenuLocatif.getId());
            pstmt.setDouble(2, revenuLocatif.getLoyerExonere());
            pstmt.setDouble(3, revenuLocatif.getLoyerImposable());
            pstmt.setDouble(4, revenuLocatif.getChargeIncombat());
            pstmt.setDouble(5, revenuLocatif.getInteretEmprunt());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** find data by ID ***************************/
    public void findById(int revenuId) {
        
        RevenuLocatif revenuLocatif = null;
        System.out.println(" findById() : Revenu Id: " + revenuId);
        
        /* Setting The Particular administrateur Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT * "
                    + "FROM revenuLocatif "
                    + "WHERE id = " + revenuId ;
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                revenuLocatif = new RevenuLocatif(); 

                revenuLocatif.setId(resultSet.getInt("id"));  
                revenuLocatif.setLoyerExonere(resultSet.getDouble("loyerExonere"));  
                revenuLocatif.setLoyerImposable(resultSet.getDouble("loyerImposable"));  
                revenuLocatif.setChargeIncombat(resultSet.getDouble("chargeIncombat"));  
                revenuLocatif.setInteretEmprunt(resultSet.getDouble("interetEmprunt"));  
                
            }
            
            sessionMap.put("revenuMapped", revenuLocatif);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(RevenuLocatif revenuLocatif){

        try {

            var query =" "
                    + "UPDATE revenuLocatif "
                    + "SET "
                    + "id_immeuble  = ?, "
                    + "loyerExonere = ?, "
                    + "loyerImposable = ?, "
                    + "interetEmprunt = ? " 
                    + "where id = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, revenuLocatif.getId());
            pstmt.setDouble(2, revenuLocatif.getLoyerExonere());
            pstmt.setDouble(3, revenuLocatif.getLoyerImposable());
            pstmt.setDouble(4, revenuLocatif.getChargeIncombat());
            pstmt.setDouble(5, revenuLocatif.getInteretEmprunt());
            pstmt.setInt(6, revenuLocatif.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int revenuId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : Administrateur Id: " + administrateurId);

        try {

            String query = "DELETE FROM revenuLocatif WHERE id = " + revenuId ;
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