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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import model.Declaration;
import java.util.*;


@ManagedBean
@ApplicationScoped

public class DeclarationDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList declarationList = new ArrayList();
        
        try {
            String quer = ""
                    + "SELECT * "
                    + "FROM declaration "
                    + "WHERE id IS NOT NULL "
                    + "ORDER BY id DESC";
            
            String query = ""
                    + "SELECT "
                    + "contribuable.nom AS Contribuable, "
                    + "immeuble.id AS Immeuble_id, "
                    + "declaration.* "
                    + "FROM contribuable, declaration, immeuble "
                    + "WHERE contribuable.id = immeuble.id_contribuable "
                    + "AND immeuble.id = declaration.id_immeuble";
                   
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Declaration declaration = new Declaration(); 

                declaration.setId(resultSet.getInt("id"));  
                declaration.setIdImmeuble(resultSet.getInt("Immeuble_id"));  
                declaration.setContribuable(resultSet.getString("Contribuable"));  
                declaration.setNif(resultSet.getInt("NIF"));  
                declaration.setCcf(resultSet.getInt("CCF")); 
                declaration.setDate_1(resultSet.getDate("datePlutot")); 
                declaration.setDate_2(resultSet.getDate("datePlutard")); 
                 

                declarationList.add(declaration);  
            }   

            System.out.println("Total Records Fetched: " + declarationList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return declarationList;
    }

    //************** Save data **********************************/ 
    public void save(Declaration declaration){
        
        try {

            String query = ""
                    + "INSERT INTO declaration (id_immeuble, NIF, CCF, datePlutot, datePlutard) "
                    + "values (?, ?, ?, ?, ?)";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            Date date_1 = declaration.getDate_1();
            Date date_2 = declaration.getDate_2();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String mysqlDate1String = sdf.format(date_1);
            String mysqlDate2String = sdf.format(date_2);
            java.sql.Date datePlutot = java.sql.Date.valueOf(mysqlDate1String);
            java.sql.Date datePlutard = java.sql.Date.valueOf(mysqlDate2String);

            
            pstmt.setInt(1, declaration.getIdImmeuble());
            pstmt.setInt(2, declaration.getNif());
            pstmt.setInt(3, declaration.getCcf());
            pstmt.setDate(4, datePlutot);
            pstmt.setDate(5, datePlutard);

            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();

        }catch (SQLException e) {
            // handle the exception
            System.err.println("Error inserting data: " + e.getMessage());
        }
    }

    //************** find data by ID ***************************/
    public void findById(int declarationId) {
        
        Declaration declaration = null;
        System.out.println(" findById() : Province Id: " + declarationId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT "
                    + "immeuble.id AS Immeuble_id, "
                    + "declaration.* "
                    + "FROM immeuble, declaration "
                    + "WHERE immeuble.id = declaration.id_immeuble "
                    + "AND declaration.id =" + declarationId ;
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                declaration = new Declaration();
                declaration.setId(resultSet.getInt("id"));  
                declaration.setIdImmeuble(resultSet.getInt("Immeuble_id"));  
                declaration.setNif(resultSet.getInt("NIF"));  
                declaration.setCcf(resultSet.getInt("CCF")); 
                declaration.setDate_1(resultSet.getDate("datePlutot")); 
                declaration.setDate_2(resultSet.getDate("datePlutard")); 

            }
            
            sessionMap.put("declarationMapped", declaration);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Declaration declaration){

        try {

            String query =""
                    + "UPDATE "
                    + "declaration SET "
                    + "id_immeuble = ?,"
                    + "NIF = ?, "
                    + "CCF = ?, "
                    + "datePlutot = ?, "
                    + "datePlutard = ? "
                    + "WHERE id = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            
            Date date_1 = declaration.getDate_1();
            Date date_2 = declaration.getDate_2();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String mysqlDate1String = sdf.format(date_1);
            String mysqlDate2String = sdf.format(date_2);
            java.sql.Date datePlutot = java.sql.Date.valueOf(mysqlDate1String);
            java.sql.Date datePlutard = java.sql.Date.valueOf(mysqlDate2String);

            
            pstmt.setInt(1, declaration.getIdImmeuble());
            pstmt.setInt(2, declaration.getNif());
            pstmt.setInt(3, declaration.getCcf());
            pstmt.setDate(4, datePlutot);
            pstmt.setDate(5, datePlutard);
            pstmt.setInt(6, declaration.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int declarationId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : declaration Id: " + declarationId);

        try {

            String query = "DELETE FROM declaration WHERE id = " + declarationId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        
    }
     
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}