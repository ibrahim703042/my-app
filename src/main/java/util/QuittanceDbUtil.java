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
import model.Quittance;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class QuittanceDbUtil extends MySQLJDBCUtil {
    private Quittance quittance;
    private List<Quittance> quittanceList;
    private String query;
   
    //*************************** display data *****************/
    public List<Quittance> findAll() {
        
        quittanceList = new ArrayList();
        
        try {
            query = ""
                + "SELECT * "
                + "FROM quittance "
                + "WHERE id_quittance IS NOT NULL ORDER BY id_quittance DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                quittance = new Quittance(); 

                quittance.setId(resultSet.getInt("id_quittance"));  
                quittance.setIdPayement(resultSet.getInt("id_payement"));  
                quittance.setDateQuittance(resultSet.getDate("dateQuittance")); 
                quittance.setMontantRestanteDu(resultSet.getInt("montantRestante")); 

                quittanceList.add(quittance);  
            }   

            System.out.println("Total Records Fetched: " + quittanceList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return quittanceList;
    }
    
    public void autoID() {
        
         query = "SELECT Max(id_quittance) FROM quittance ";
                
           
        try {
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) {
                quittance = new Quittance(); 

                quittance.setId(resultSet.getInt("id_quittance"));
                //quittance.setNumero(resultSet.getString("numero"));
                resultSet.getString("MAX(numero)");
                
                if(resultSet.getString("MAX(numero)") == null){
                    quittance.setNumero(("F001"));
                }
                else{
                    long id = Long.parseLong(resultSet.getString("MAX(numero)").substring(2,resultSet.getString("MAX(numero)").length()));
                    id++;
                    
                    quittance.setNumero(("F" + String.format("%03d", id)));
                }
                
                quittanceList.add(quittance);
            }
            
            System.out.println("Total Records Fetched: " + quittanceList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
      }

}