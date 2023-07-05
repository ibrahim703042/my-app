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
import java.sql.*;
import java.util.*;
import model.Commune;

@ManagedBean
@ApplicationScoped

public class CommuneDbUtil extends MySQLJDBCUtil{
    private List<Commune> communeList;
    private Commune commune;
    //*************************** display data *****************/
    public  List<Commune> findAll() {
        communeList = new ArrayList<>();
        
        try {
            String query = ""
                    + "SELECT C.*, P.nomProvince "
                    + "FROM commune C, province P "
                    + "WHERE C.id_province = P.id "
                    + "ORDER BY P.nomProvince";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                commune = new Commune(); 

                commune.setId(resultSet.getInt("id"));  
                commune.setIdProvince(resultSet.getInt("id_province"));  
                commune.setNomProvince(resultSet.getString("nomProvince"));
                commune.setNomCommune(resultSet.getString("nomCommune"));

                communeList.add(commune);
                
            }   

            System.out.println("Total Records Fetched: " + communeList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return communeList;
    }

    //************** Save data **********************************/ 
    public void save(Commune commune){
        
        try {

            String query = "INSERT INTO commune (id_province, nomCommune) values (?, ?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, commune.getIdProvince());
            pstmt.setString(2, commune.getNomCommune());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        } 
    }
	
    //************** update data ******************************/
    public void update(Commune commune){

        try {

            String query =""
                    + "UPDATE commune "
                    + "SET id_province = ?, "
                    + "nomCommune = ? "
                    + "WHERE id =? " ;

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, commune.getIdProvince());
            pstmt.setString(2, commune.getNomCommune());
            pstmt.setInt(3,commune.getId());

            
            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        
    }

    //************** delete data ********************************/
    public void delete(int communeId) {
        
        System.out.println("delete() : Commune Id: " + communeId);

        try {

            String query = "DELETE FROM commune  WHERE id = " + communeId ;
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    
    
}