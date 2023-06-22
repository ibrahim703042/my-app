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
import model.Amende;
import java.sql.*;
import java.util.*;

@ManagedBean
@ApplicationScoped

public class AmendeDbUtil extends MySQLJDBCUtil {
    
    private List<Amende> amandeList;
    private Amende amande;
    private String query;
    
    //*************************** display data *****************/
    public List<Amende> findAll() {
        
        amandeList = new ArrayList<>();
        
        try {
            
            query = ""
                    + "SELECT "
                    + "amende.id_amende, "
                    + "amende.id_impot, "
                    + "amende.amande_fixe, "
                    + "amende.penalite, "
                    + "(amende.amande_fixe + amende.penalite) As total "
                    + "FROM amende WHERE id_amende IS NOT NULL ORDER BY id_amende DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                amande = new Amende(); 

                amande.setId(resultSet.getInt("id_amende"));  
                amande.setIdImpot(resultSet.getInt("id_impot"));  
                //amande.setBaseLine(resultSet.getDouble(""));  
                amande.setAmandeFixe(resultSet.getInt("amande_fixe"));  
                amande.setPenalite(resultSet.getDouble("penalite"));  
                amande.setTotal(resultSet.getInt("total"));

                amandeList.add(amande);  
            }   

            System.out.println("Total Records Fetched: " + amandeList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return amandeList;
    }

    //************** Save data **********************************/ 
    public Amende save(Amende amande){
        Amende model = null;
        
        try {

            query = ""
                    + "INSERT INTO amende "
                    + "(id_impot, amande_fixe, penalite) "
                    + "values (?, ?, ?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, amande.getIdImpot());
            pstmt.setDouble(2, amande.getAmandeFixe());
            pstmt.setDouble(3, amande.getPenalite());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }
	
    //************** update data ******************************/
    public Amende update(Amende amande){
        Amende model = null;
        
        try {

            query =""
                    + "UPDATE amende SET "
                    + "id_impot = ?, "
                    + "amande_fixe = ?, "
                    + "penalite = ? "
                    + "WHERE id_amende= ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, amande.getIdImpot());
            pstmt.setDouble(2, amande.getAmandeFixe());
            pstmt.setDouble(3, amande.getPenalite());
            pstmt.setInt(4, amande.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }

    //************** delete data ********************************/
    public void delete(int amandeId) {
        
        System.out.println("delete() : amande Id: " + amandeId);

        try {
            connection = dataSource.getConnection();
            query = "DELETE FROM amande WHERE id_amende = " + amandeId ;
            
            pstmt = connection.prepareStatement(query);
            
            pstmt.executeUpdate();  
            
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    
}