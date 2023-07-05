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
import jakarta.faces.bean.*;
import java.sql.*;
import java.util.*;
import model.RevenuSousLocation;
import model.RevenusLocatif;

@ManagedBean
@ApplicationScoped

public class RevenusDbUtil extends MySQLJDBCUtil {
    
    private List<RevenusLocatif> revenusLocatif;
    private RevenusLocatif model;
    
    private List<RevenuSousLocation> revenuSousLocationList;
    private RevenuSousLocation revenuSousLocation;
    
    private String query;
    
    //*************************** display data *****************/
  
    public List<RevenusLocatif> getRevenusLocatif() {
        revenusLocatif = new ArrayList<>();
        
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

                model = new RevenusLocatif();
                
                model.setId(resultSet.getInt("id_revenuLocatif"));
                model.setId(resultSet.getInt("id_immeuble"));
                model.setLoyerExonere(resultSet.getDouble("loyerExonere"));
                model.setLoyerImposable(resultSet.getDouble("loyerImposable"));
                model.setChargeIncombat(resultSet.getDouble("chargeIncombat"));
                model.setRevenuBrut(resultSet.getDouble("revenuBrut"));
                model.setDeductionDepenses(resultSet.getDouble("deductionPourDepenses"));
                model.setInteretEmprunt(resultSet.getDouble("interetEmprunt"));
                model.setRevenuNetImposable(resultSet.getDouble("RevenusNetsImposables"));

                revenusLocatif.add(model);
                
            }   

            System.out.println("Total Records Fetched: " + revenusLocatif.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return revenusLocatif;

    }
    
    public List<RevenuSousLocation> getRevenuSousLocation() {
        
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
    public RevenusLocatif saveRevenusLocatif(RevenusLocatif revenusLocatif){
        model = null;
        
        try {

            query =""
                + "INSERT INTO revenulocatif "
                + "(id_immeuble, loyerExonere, loyerImposable, chargeIncombat, interetEmprunt) "
                + "VALUES (?, ?, ?, ?, ?)";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenusLocatif.getIdImmeuble());
            pstmt.setDouble(2, revenusLocatif.getLoyerExonere());
            pstmt.setDouble(3, revenusLocatif.getLoyerImposable());
            pstmt.setDouble(4, revenusLocatif.getChargeIncombat());
            pstmt.setDouble(5, revenusLocatif.getInteretEmprunt());

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
    
    public RevenuSousLocation saveRevenuSousLocation(RevenuSousLocation revenuSousLocation){
        RevenuSousLocation modelRevenuSousLocation = null;
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
        return modelRevenuSousLocation;
    }
    
    //************** update data **********************************/ 
    public RevenusLocatif updateRevenusLocatif(RevenusLocatif revenusLocatif){
        model = null;
        try {

            query =""
                    + " UPDATE revenulocatif SET "
                    + "id_immeuble = ?,"
                    + "loyerExonere = ?, "
                    + "loyerImposable = ?, "
                    + "chargeIncombat = ?, "
                    + "interetEmprunt = ? "
                    + "WHERE = ? ";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenusLocatif.getIdImmeuble());
            pstmt.setDouble(2, revenusLocatif.getLoyerExonere());
            pstmt.setDouble(3, revenusLocatif.getLoyerImposable());
            pstmt.setDouble(4, revenusLocatif.getChargeIncombat());
            pstmt.setDouble(5, revenusLocatif.getInteretEmprunt());
            pstmt.setInt(1, revenusLocatif.getId());

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
    
    public RevenuSousLocation updateRevenuSousLocation(RevenuSousLocation revenuSousLocation){
        RevenuSousLocation modelRevenuSousLocation = null;
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
        return modelRevenuSousLocation;
    }
    
    //************** delete data **********************************/ 
    public void deleteRevenusLocatif(Integer revenulocatifId){
        
        System.out.println("delete() : Revenus Locatif Id: " + revenulocatifId);
        
        try {

            query = " DELETE revenulocatif WHERE = ? ";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, revenulocatifId);

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

    public void deleteRevenuSousLocation(Integer revenuSousLocationId){
        
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
    
}