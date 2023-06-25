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
import model.RevenusLocatif;

@ManagedBean
@ApplicationScoped

public class RevenusLocatifDbUtil extends MySQLJDBCUtil {
    private static RevenusLocatif revenuLocatif;
    private static List<RevenusLocatif> revenuLocatifList;
    private static String query;

    //*************************** display data *****************/
    public static List<RevenusLocatif> findAll() {
        
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

                revenuLocatif = new RevenusLocatif();
                
                revenuLocatif.setId(resultSet.getInt("id_revenuLocatif"));
                revenuLocatif.setId(resultSet.getInt("id_immeuble"));
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
    public static RevenusLocatif save(RevenusLocatif revenusLocatif){
        RevenusLocatif model = null;
        
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
    
    
    //************** update data **********************************/ 
    public static RevenusLocatif update(RevenusLocatif revenusLocatif){
        RevenusLocatif model = null;
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
    
    //************** delete data **********************************/ 
    public static void delete(Integer revenulocatifId){
        
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
}