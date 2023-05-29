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

public class CollineDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList collineList = new ArrayList();
        
        try {
            String query = ""
                    + "SELECT C.*, P.nomCommune "
                    + "FROM colline C, commune P "
                    + "WHERE C.id_commune = P.id "
                    + "ORDER BY P.nomCommune";
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Colline colline = new Colline(); 

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
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, colline.getIdCommune());
            pstmt.setString(2, colline.getNomColline());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        } 
    }
    
    //************** find data by ID ***************************/
    public void findById(int collineId) {
        
        Colline colline = null;
        System.out.println(" findById() : Colline Id: " + collineId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT colline.*, commune.nomCommune, commune.id as Commune_id "
                    + "FROM commune, colline "
                    + "WHERE commune.id = colline.id_commune "
                    + "AND colline.id = " + collineId ;
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                colline = new Colline();
                
                colline.setId(resultSet.getInt("id"));  
                colline.setIdCommune(resultSet.getInt("Commune_id"));
                colline.setNomCommune(resultSet.getString("nomCommune"));  
                colline.setNomColline(resultSet.getString("nomColline")); 
                 
            }
            
            sessionMap.put("collineMapped", colline);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Colline colline){

        try {

            String query =" UPDATE colline SET id_commune=?, nomColline=? WHERE id = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, colline.getIdCommune());
            pstmt.setString(2, colline.getNomColline());
            pstmt.setInt(3, colline.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            
    }

    //************** delete data ********************************/
    public void delete(int collineId) {
        
        connection = MySQLJDBCUtil.getConnection();
        System.out.println("delete() : colline Id: " + collineId);

        try {

            String query = "DELETE FROM colline WHERE id = " + collineId ;
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