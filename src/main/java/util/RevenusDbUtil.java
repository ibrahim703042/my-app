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
import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.*;
import jakarta.faces.context.FacesContext;
import java.sql.*;
import java.util.*;
import model.RevenuLocatif;

@ManagedBean
@ApplicationScoped

public class RevenusDbUtil extends MySQLJDBCUtil {
    
    private List<RevenuLocatif> revenusLocatif;
    
    //*************************** display data *****************/
    @PostConstruct
    public void init () {
        revenusLocatif = new ArrayList();
        
        try {
            String query = "SELECT * FROM revenulocatif WHERE id_revenuLocatif IS NOT NULL ORDER BY id_revenuLocatif DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                RevenuLocatif revenuLocatif = new RevenuLocatif(); 

                revenuLocatif.setId(resultSet.getInt("id_revenuLocatif"));  
                revenuLocatif.setLoyerExonere(resultSet.getDouble("loyerExonere"));  
                revenuLocatif.setLoyerImposable(resultSet.getDouble("loyerImposable"));  
                revenuLocatif.setChargeIncombat(resultSet.getDouble("chargeIncombat"));  
                revenuLocatif.setInteretEmprunt(resultSet.getDouble("interetEmprunt"));  
                
                revenusLocatif.add(revenuLocatif);
                //connection.commit();
                
            }   

            System.out.println("Total Records Fetched: " + revenusLocatif.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
    }

    public List<RevenuLocatif> getRevenusLocatif() {
        return new ArrayList<>(revenusLocatif);
    }
    
    
    
    public List<RevenuLocatif> findAll() {
        revenusLocatif = new ArrayList();
        
        
        try {
            String query = "SELECT * FROM revenulocatif WHERE id_revenuLocatif IS NOT NULL ORDER BY id_revenuLocatif DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                RevenuLocatif revenuLocatif = new RevenuLocatif(); 

                revenuLocatif.setId(resultSet.getInt("id_revenuLocatif"));  
                revenuLocatif.setLoyerExonere(resultSet.getDouble("loyerExonere"));  
                revenuLocatif.setLoyerImposable(resultSet.getDouble("loyerImposable"));  
                revenuLocatif.setChargeIncombat(resultSet.getDouble("chargeIncombat"));  
                revenuLocatif.setInteretEmprunt(resultSet.getDouble("interetEmprunt"));  
                

                revenusLocatif.add(revenuLocatif);
                //connection.commit();
                
            }   

            System.out.println("Total Records Fetched: " + revenusLocatif.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return revenusLocatif;
    }
    
    //************** Save data **********************************/ 
    public void save(RevenuLocatif revenuLocatif){
        
        try {

            String query ="INSERT INTO revenulocatif (id_immeuble, loyerExonere, loyerImposable, interetEmprunt) "
                    + "values (?, ?, ?, ?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenuLocatif.getId());
            pstmt.setDouble(2, revenuLocatif.getLoyerExonere());
            pstmt.setDouble(3, revenuLocatif.getLoyerImposable());
            pstmt.setDouble(4, revenuLocatif.getChargeIncombat());
            pstmt.setDouble(5, revenuLocatif.getInteretEmprunt());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
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
                    + "WHERE id_revenuLocatif = " + revenuId ;
            
            connection = dataSource.getConnection();
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
            printSQLException(sqlException);
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
                    + "where id_revenuLocatif = ? ";

            connection = dataSource.getConnection();
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
            printSQLException(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int revenuId) {
        
        System.out.println("delete() : Administrateur Id: " + revenuId);

        try {
            
            String query = "DELETE FROM revenuLocatif WHERE id_revenuLocatif = " + revenuId ;
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        
    }
    
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                }
            }
        }
    }
}