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
import jakarta.faces.bean.*;
import jakarta.faces.context.FacesContext;
import java.sql.*;
import java.util.*;
import model.Contribuable;

@ManagedBean
@ApplicationScoped

public class ContribuableDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList contribuableList = new ArrayList();
        
        try {

            String query = "SELECT * FROM contribuable WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

               Contribuable contribuable = new Contribuable(); 

               contribuable.setId(resultSet.getInt("id"));  
               contribuable.setNom(resultSet.getString("nom"));  
               contribuable.setPrenom(resultSet.getString("prenom"));  
               contribuable.setEmail(resultSet.getString("email"));  
               contribuable.setMotPasse(resultSet.getString("motPasse"));  
               contribuable.setTelephone(resultSet.getInt("telephone"));  
               contribuable.setBp(resultSet.getString("BP"));  
               contribuable.setDate(resultSet.getDate("date")); 

               contribuableList.add(contribuable);  
            }   

            System.out.println("Total Records Fetched: " + contribuableList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return contribuableList;
    }
    
    //************** Save data **********************************/ 
    public void save(Contribuable contribuable){
        
        try {

            String query = 
                    "INSERT INTO contribuable (id_representant, nom, prenom, email, motPasse, telephone, BP) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, contribuable.getIdRepresentant());
            pstmt.setString(2, contribuable.getNom());
            pstmt.setString(3, contribuable.getPrenom());
            pstmt.setString(4, contribuable.getEmail());
            pstmt.setString(5, contribuable.getMotPasse());
            pstmt.setInt(6, contribuable.getTelephone());
            pstmt.setString(7, contribuable.getBp());
            
            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** find data by ID ***************************/
    public void findById(int contribuableId) {
        
        Contribuable contribuable = null;
        System.out.println(" findById() : Contribuable Id: " + contribuableId);
        
        /* Setting The Particular contribuable Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT C.*, R.* "
                    + "FROM contribuable C, representant R  "
                    + "WHERE  C.id = " + contribuableId ;
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                contribuable = new Contribuable();
                contribuable.setId(resultSet.getInt("id"));  
                contribuable.setNom(resultSet.getString("nom"));  
                contribuable.setPrenom(resultSet.getString("prenom"));  
                contribuable.setEmail(resultSet.getString("email"));  
                contribuable.setMotPasse(resultSet.getString("motPasse"));  
                contribuable.setTelephone(resultSet.getInt("telephone"));  
                contribuable.setBp(resultSet.getString("BP"));  
                
                contribuable.setIdRepresentant(resultSet.getInt("id"));  
                contribuable.setNomRepresentant(resultSet.getString("nom"));  
                contribuable.setPrenomRepresentant(resultSet.getString("prenom")); 
                
            }
            
            sessionMap.put("contribuableMapped", contribuable);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Contribuable contribuable){

        try {

            var query =" "
                    + "UPDATE contribuable "
                    + "SET "
                    + "nom = ?, "
                    + "prenom = ?, "
                    + "email = ?, "
                    + "telephone = ?, "
                    + "BP = ? "
                    + "where id = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, contribuable.getNom());
            pstmt.setString(2, contribuable.getPrenom());
            pstmt.setString(3, contribuable.getEmail());
            pstmt.setInt(4, contribuable.getTelephone());
            pstmt.setString(5, contribuable.getBp());
            pstmt.setInt(6, contribuable.getId());
            //pstmt.setInt(6, contribuable.getIdRepresentant());


            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int contribuableId) {
        
        connection = MySQLJDBCUtil.getConnection();
        System.out.println("delete() : Contribuable Id: " + contribuableId);

        try {

            String query = "DELETE FROM contribuable WHERE id = " + contribuableId ;
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