/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package util;


import dbconnection.MySQLJDBCUtil;
import jakarta.faces.bean.*;
import java.sql.*;
import java.util.*;

import model.Province;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@ApplicationScoped

public class ProvinceDbUtil extends MySQLJDBCUtil{
    
    private String query;
    private List<Province> provinceList;
    private Province province;

    /// ************************ display data *************************/
    public List<Province> findAll() {
        
        provinceList = new ArrayList();  
        
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            query = "SELECT * FROM province P WHERE P.id IS NOT NULL ORDER BY P.nomProvince";
            resultSet = statement.executeQuery(query);  
            
            while(resultSet.next()) { 
                
                province = new Province(); 
                
                province.setId(resultSet.getInt("id"));  
                province.setNomProvince(resultSet.getString("nomProvince"));  
                
                provinceList.add(province);  
            }   
            
            System.out.println("Total Records Fetched: " + provinceList.size());
            connection.close();
            
        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        } 
        
        return provinceList;
    }
    
    /// ************************ save data *************************/
    public Province save(Province province) {
        Province model = null;
        
        try {   
            
            query = "INSERT INTO province (nomProvince) VALUES (?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         
            pstmt.setString(1, province.getNomProvince());
            
            pstmt.executeUpdate();
            connection.close();
            
        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        
        return model;
    }
    
    /// ************************ update data *************************/
    public Province update(Province province) {
        Province model = null;
        
        try {
            query = "UPDATE province SET nomProvince = ? WHERE id = ? ";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,province.getNomProvince());
            pstmt.setInt(2,province.getId());
            
            pstmt.executeUpdate();
            
            connection.close();
            
        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        
        return model;
    }

    /// ************************ delete data *************************/
    public void delete(int provinceId){
        
        System.out.println("delete() : Province Id: " + provinceId);
        
        try {
            
            query = "DELETE FROM province WHERE id = " + provinceId ;
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate(); 
            
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
    }
    
}