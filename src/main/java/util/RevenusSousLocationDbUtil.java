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
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import java.sql.*;
import java.util.*;
import model.RevenuSousLocation;
import model.RevenusLocatif;

@ManagedBean
@ApplicationScoped

public class RevenusSousLocationDbUtil extends MySQLJDBCUtil {
    
    private List<RevenuSousLocation> revenuSousLocationList;
    private RevenuSousLocation revenuSousLocation;
    private String query;
    
    //*************************** display data *****************/
    public List<RevenuSousLocation> findAll() {
        
        revenuSousLocationList = new ArrayList<>();
        
        try {
            query =  " "
                    + "SELECT "
                    + "revenusouslocation.id_revenuSousLocatif, "
                    + "revenusouslocation.id_revenuLocatif, "
                    + "revenusouslocation.recetteSousLocation,"
                    + "revenusouslocation.loyerPayes, "
                    + "(revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes) AS Revenus_nets_imposables , "
                    + "((((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) + (revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes)) AS  Total_revenus_nets_imposables , "
                    + "revenusouslocation.abbattements, "
                    + "((((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) + (revenusouslocation.recetteSousLocation - revenusouslocation.loyerPayes)) - revenusouslocation.abbattements AS REVENU_NET_IMPOSABLE "
                    + "FROM revenusouslocation, revenulocatif "
                    + "WHERE revenulocatif.id_revenuLocatif = revenusouslocation.id_revenuLocatif "
                    + "AND revenusouslocation.id_revenuSousLocatif IS NOT NULL ORDER BY revenusouslocation.id_revenuSousLocatif DESC";

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                revenuSousLocation = new RevenuSousLocation();
                
                revenuSousLocation.setId(resultSet.getInt("id_revenuSousLocatif")); 
                revenuSousLocation.setIdRevenusLocatif(resultSet.getInt("id_revenuLocatif")); 
                revenuSousLocation.setRecetteSousLocation(resultSet.getDouble("recetteSousLocation")); 
                revenuSousLocation.setLoyerPayes(resultSet.getDouble("loyerPayes")); 
                revenuSousLocation.setRevenusNetsImposables(resultSet.getDouble("Revenus_nets_imposables")); 
                revenuSousLocation.setTotalRevenusNetsImposables(resultSet.getDouble("Total_revenus_nets_imposables")); 
                revenuSousLocation.setAbbattements(resultSet.getDouble("abbattements")); 
                revenuSousLocation.setRevenuSousNetImposable(resultSet.getDouble("REVENU_NET_IMPOSABLE")); 
                

                revenuSousLocationList.add(revenuSousLocation);  
            }   

            System.out.println("Total Records Fetched: " + revenuSousLocationList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return revenuSousLocationList;

    }
    
    //************** Save data **********************************/ 
    public RevenuSousLocation save(RevenuSousLocation revenuSousLocation){
        RevenuSousLocation model = null;
        try {

            query =""
                + "INSERT INTO revenusouslocation "
                + "(id_revenuLocatif, recetteSousLocation, loyerPayes, abbattements) "
                + "VALUES (?, ?, ?, ?)";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenuSousLocation.getId());
            pstmt.setDouble(2, revenuSousLocation.getRecetteSousLocation());
            pstmt.setDouble(3, revenuSousLocation.getLoyerPayes());
            pstmt.setDouble(4, revenuSousLocation.getAbbattements());

            pstmt.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
             }
        }
        return model;
    }
    
    //************** update data **********************************/ 
    public RevenuSousLocation update(RevenuSousLocation revenuSousLocation){
        RevenuSousLocation model = null;
        try {

            query =""
                + "UPDATE revenusouslocation SET "
                + "id_revenuLocatif = ?, "
                + "recetteSousLocation = ?, "
                + "loyerPayes = ?, "
                + "abbattements = ? "
                + "WHERE = ? ";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenuSousLocation.getIdRevenusLocatif());
            pstmt.setDouble(2, revenuSousLocation.getRecetteSousLocation());
            pstmt.setDouble(3, revenuSousLocation.getLoyerPayes());
            pstmt.setDouble(4, revenuSousLocation.getAbbattements());
            pstmt.setInt(5, revenuSousLocation.getId());

            pstmt.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return model;
    }
    
    //************** delete data **********************************/ 
    public void delete(Integer revenuSousLocationId){
        
        System.out.println("delete() : RevenuSousLocation Id: " + revenuSousLocationId);
        
        try {

            query = " DELETE revenusouslocation WHERE = ? ";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenuSousLocation.getId());

            pstmt.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    //*************************** fetch a single value *****************/
    public List<RevenusLocatif> findRevenuBrut( Integer id){
        List<RevenusLocatif> revenuLocatifList = new ArrayList<>();
        System.out.println("findRevenuBrut() : Revenu Locatif Id: " + id);
        
        try {
            query = ""
                    + "SELECT "
                    + "(((revenulocatif.loyerExonere + revenulocatif.loyerImposable)-(revenulocatif.loyerExonere + revenulocatif.loyerImposable) * 0.4) - revenulocatif.interetEmprunt) AS RevenusNetsImposables  "
                    + "FROM revenulocatif WHERE  ORDER BY revenulocatif.id_revenuLocatif DESC";

            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            while(resultSet.next()) { 

                RevenusLocatif revenuLocatif = new RevenusLocatif();
                revenuLocatif.setRevenuNetImposable(resultSet.getDouble("RevenusNetsImposables"));
                revenuLocatifList.add(revenuLocatif);
            }   
            System.out.println("Total Records Fetched: " + revenuLocatifList.size());
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return revenuLocatifList;
    }
    
}
