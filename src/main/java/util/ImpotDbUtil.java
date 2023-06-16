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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.sql.SQLException;
import model.Impot;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped

public class ImpotDbUtil extends MySQLJDBCUtil {
    
   private List<Impot> impotList;
   private Impot impot;
    
    //*************************** display data *****************/
    public List<Impot> findAll() {
        
        impotList = new ArrayList();
        
        try {
            String query = ""
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
                impot.setRevenuSousNetImposable(resultSet.getDouble("revenuNetImposable")); 

                impotList.add(impot);  
            }   

            System.out.println("Total Records Fetched: " + impotList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return impotList;
    }

    
}