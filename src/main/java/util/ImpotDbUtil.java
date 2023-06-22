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
import java.sql.SQLException;
import java.sql.Statement;
import model.Impot;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@ApplicationScoped

public class ImpotDbUtil extends MySQLJDBCUtil {
    
   private List<Impot> impotList;
   private Impot impot;
   private String query;
    
    //*************************** display data *****************/
    public List<Impot> findAll() {
        
        impotList = new ArrayList();
        
        try {
            query = ""
                + "SELECT * "
                + "FROM revenusouslocation, impot "
                + "WHERE impot.id_sous_location = revenusouslocation.id_revenuSousLocatif "
                + "ORDER BY id_impot DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                impot = new Impot(); 

                impot.setId(resultSet.getInt("id_impot"));  
                impot.setIdRevenuSousLocation(resultSet.getInt("id_sous_location"));  
                impot.setAccompteImpotDejaPaye(resultSet.getDouble("impot_deja_paye")); 
                impot.setDate(resultSet.getDate("date_impot")); 
                //impot.setRevenuSousNetImposable(resultSet.getDouble("revenuNetImposable")); 

                impotList.add(impot);  
            }   

            System.out.println("Total Records Fetched: " + impotList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return impotList;
    }

    public void save(){
            
        try {

            connection = dataSource.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            String insertSql = "INSERT INTO table1 (column1, column2) VALUES (?, ?)";
            pstmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "value1");
            pstmt.setString(2, "value2");
            pstmt.executeUpdate();

            resultSet = pstmt.getGeneratedKeys();
            int generatedKey = 0;
            
            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }

            String updateSql = "INSERT INTO table2 (column1, column2) VALUES (?, ?)";
            pstmt = connection.prepareStatement(updateSql);
            pstmt.setInt(1, generatedKey);
            pstmt.setString(2, "value3");
            pstmt.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        
}