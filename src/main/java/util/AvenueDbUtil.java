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
import model.Avenue;
import java.sql.*;
import java.util.*;

@ManagedBean
@ApplicationScoped

public class AvenueDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList avenueList = new ArrayList();
        
        try {
            String query = "SELECT * FROM avenue,colline WHERE avenue.id_colline = colline.id ";
                    
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Avenue avenue = new Avenue(); 

                avenue.setId(resultSet.getInt("id"));  
                avenue.setIdColline(resultSet.getInt("id_colline"));  
                avenue.setNomAvenue(resultSet.getString("nomAvenue"));  
                avenue.setNomColline(resultSet.getString("nomColline")); 

                avenueList.add(avenue);  
            }   

            System.out.println("Total Records Fetched: " + avenueList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return avenueList;
    }

    //************** Save data **********************************/ 
    public void save(Avenue avenue){
        
        try {

            String query = "INSERT INTO avenue (id_colline, nomAvenue) values (?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, avenue.getIdColline());
            pstmt.setString(2,avenue.getNomAvenue());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            //addErrorMessage(sqlException);
            sqlException.printStackTrace();
        }
    }

    //************** find data by ID ***************************/
    public void findById(int avenueId) {
        
        Avenue avenue = null;
        System.out.println(" findById() : Avenue Id: " + avenueId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
//            String query = ""
//                    + "SELECT A.*, C.* "
//                    + "FROM avenue A, colline C "
//                    + "WHERE A.id_colline = C.id "
//                    + "ORDER BY C.nomColline";
            String query = "SELECT * FROM avenue,colline WHERE avenue.id_colline = colline.id ";
                    
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                avenue = new Avenue(); 

                avenue.setId(resultSet.getInt("id"));  
                avenue.setIdColline(resultSet.getInt("id"));  
                avenue.setNomAvenue(resultSet.getString("nomAvenue"));  
                avenue.setNomColline(resultSet.getString("nomColline")); 

            }
            
            sessionMap.put("avenueMapped", avenue);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
  
    }
	
    //************** update data ******************************/
    public void update(Avenue avenue){

        try {

            String query = " "
                    + "UPDATE avenue "
                    + "SET "
                    + "nomAvenue = ?, "
                    + "id_colline = ? "
                    + "WHERE id = ?";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, avenue.getIdColline());
            pstmt.setString(2, avenue.getNomAvenue());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        
    }

    //************** delete data ********************************/
    public void delete(int avenueId) {
        
        connection = MySQLJDBCUtil.getConnection();
        System.out.println("delete() : Avenue Id: " + avenueId);

        try {

            String query = "DELETE FROM avenue WHERE id = " + avenueId ;
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