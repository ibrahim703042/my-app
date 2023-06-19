/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 * 
 */

import dbconnection.MySQLJDBCUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.sql.*;
import java.util.*;
import model.Immeuble;
import model.RevenuLocatif;

@Named
@ApplicationScoped

public class RevenusLocatifDbUtil extends MySQLJDBCUtil {
    private RevenuLocatif revenuLocatif;
    private List<RevenuLocatif> revenuLocatifList;
    private String query;

    //*************************** display data *****************/
    public List<RevenuLocatif> findAll() {
        
        revenuLocatifList = new ArrayList<>();
        
        try {
            query = ""
                    + "SELECT "
                    + "revenulocatif.id_revenuLocatif, "
                    + "revenulocatif.id_immeuble, "
                    + "revenulocatif.loyerExonere, "
                    + "revenulocatif.loyerImposable, "
                    + "revenulocatif.chargeIncombat, "
                    + "(revenulocatif.loyerExonere + revenulocatif.loyerImposable) AS revenuBrut , "
                    + "((revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) AS deductionPourDepenses , "
                    + "revenulocatif.interetEmprunt, "
                    + "(((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) AS RevenusNetsImposables  "
                    + "FROM revenulocatif ORDER BY revenulocatif.id_revenuLocatif DESC";

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                revenuLocatif = new RevenuLocatif();
                
                revenuLocatif.setId(resultSet.getInt("id_revenuLocatif"));
//                revenuLocatif.setId(resultSet.getInt("id_immeuble"));
                revenuLocatif.setLoyerExonere(resultSet.getDouble("loyerExonere"));
                revenuLocatif.setLoyerImposable(resultSet.getDouble("loyerImposable"));
                revenuLocatif.setChargeIncombat(resultSet.getDouble("chargeIncombat"));
                revenuLocatif.setRevenuBrut(resultSet.getDouble("revenuBrut"));
                revenuLocatif.setDeductionDepenses(resultSet.getDouble("deductionPourDepenses"));
                revenuLocatif.setInteretEmprunt(resultSet.getDouble("interetEmprunt"));
                revenuLocatif.setRevenuNetImposable(resultSet.getDouble("RevenusNetsImposables"));

                revenuLocatifList.add(revenuLocatif);  
            }   

            System.out.println("Total Records Fetched: " + revenuLocatifList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return revenuLocatifList;

    }
    
    //************** Save data **********************************/ 
    public void save(Immeuble immeuble){
        
        try {

            query ="INSERT INTO immeuble (id_colline, id_contribuable, nomAvenue) values (?, ?, ?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, immeuble.getIdColline());
            pstmt.setInt(2, immeuble.getIdContribuable());
            pstmt.setString(3, immeuble.getNomAvenue());

            pstmt.executeUpdate();
            connection.close();

        }
//        catch (NullPointerException e) {
//            // handle the null value appropriately
//            System.err.println("Caught NullPointerException: " + e.getMessage());
//        } 
        catch (SQLException e) {
            // handle the SQLException appropriately
            System.err.println("Caught SQLException: " + e.getMessage());
        } 
    }

    //*************************** find data by id *****************/
    public void findById(int immeubleId ) {
        
        Immeuble immeuble = null;
        System.out.println(" findById() : immeuble Id: " + immeubleId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        try {
            String query = ""
                    + "SELECT contribuable.*, "
                    + "colline.nomColline AS Colline, "
                    + "colline.id AS Colline_id, "
                    + "immeuble.id AS immeuble_id,"
                    + "immeuble.nomAvenue AS Rue "
                    + "FROM contribuable, immeuble, colline "
                    + "WHERE immeuble.id_contribuable = contribuable.id "
                    + "AND immeuble.id_colline = colline.id "
                    + "AND immeuble.id = " + immeubleId ;

            // String query = "SELECT * FROM immeuble WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                immeuble = new Immeuble();
                
                /* *******contribuable **** */
                
                immeuble.setId(resultSet.getInt("Immeuble_ID"));
                immeuble.setNomAvenue(resultSet.getString("Rue"));  

                immeuble.setIdContribuable(resultSet.getInt("id"));  
                immeuble.setNomContribuable(resultSet.getString("nom"));  
                
                immeuble.setIdColline(resultSet.getInt("Colline_id"));  
                immeuble.setNomColline(resultSet.getString("Colline"));  
                                
            }   

            sessionMap.put("immeubleMapped", immeuble);
            connection.close();
            
        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }

    }
    
    	
    //************** update data ******************************/
    public void update(Immeuble immeuble){

        try {

            query =""
                    + "UPDATE immeuble "
                    + "SET "
                    + "id_colline = ?, "
                    + "id_contribuable = ?, "
                    + "nomAvenue = ? "
                    + "WHERE id = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, immeuble.getIdColline());
            pstmt.setInt(2, immeuble.getIdContribuable());
            pstmt.setString(3, immeuble.getNomAvenue());
            pstmt.setInt(4,immeuble.getId());
            
            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        
    }

    //************** delete data ********************************/
    public void delete(int immeubleId) {
        
        System.out.println("delete() : Immeuble Id: " + immeubleId);

        try {
            connection = dataSource.getConnection();
            query = "DELETE FROM immeuble WHERE id = " + immeubleId ;
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
