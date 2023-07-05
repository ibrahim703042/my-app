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
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import model.Colline;
import java.sql.*;
import java.util.*;

@ManagedBean
@ApplicationScoped

public class CollineDbUtil extends MySQLJDBCUtil {
    private List<Colline> collineList;
    private Colline colline;
    //*************************** display data *****************/
    public List<Colline> findAll() {
        
        collineList = new ArrayList();
        
        try {
            String query = ""
                    + "SELECT C.*, P.nomCommune "
                    + "FROM colline C, commune P "
                    + "WHERE C.id_commune = P.id "
                    + "ORDER BY P.nomCommune";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                colline = new Colline(); 

                colline.setId(resultSet.getInt("id"));
                colline.setIdCommune(resultSet.getInt("id_commune"));  
                colline.setNomCommune(resultSet.getString("nomCommune"));  
                colline.setNomColline(resultSet.getString("nomColline")); 

                collineList.add(colline);  
            }   

            System.out.println("Total Records Fetched: " + collineList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return collineList;
    }

    //************** Save data **********************************/ 
    public void save(Colline colline){
        
        try {

            String query = "INSERT INTO colline (id_commune, nomColline) values (?, ?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, colline.getIdCommune());
            pstmt.setString(2, colline.getNomColline());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        } 
    }
    	
    //************** update data ******************************/
    public void update(Colline colline){

        try {

            String query =" UPDATE colline SET id_commune=?, nomColline=? WHERE id = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, colline.getIdCommune());
            pstmt.setString(2, colline.getNomColline());
            pstmt.setInt(3, colline.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
            
    }

    //************** delete data ********************************/
    public void delete(int collineId) {
        
        System.out.println("delete() : colline Id: " + collineId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM colline WHERE id = " + collineId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    
    
}