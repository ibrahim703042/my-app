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
import model.Administrateur;
import java.sql.*;
import java.util.*;
import model.Immeuble;

@ManagedBean
@ApplicationScoped

public class AdministrateurDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList administrateurList = new ArrayList();
        
        try {
            String query = "SELECT * FROM administrateur WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Administrateur administrateur = new Administrateur(); 

                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP"));  
                administrateur.setDate(resultSet.getDate("date")); 

                administrateurList.add(administrateur);  
            }   

            System.out.println("Total Records Fetched: " + administrateurList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return administrateurList;
    }
    
    //************** Save data **********************************/ 
    public void save(Administrateur administrateur){
        
        try {

            String query = 
                    "INSERT INTO administrateur (id_Role, nom, prenom, email, motPasse, telephone, BP) "
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, administrateur.getIdRole());
            pstmt.setString(2, administrateur.getNom());
            pstmt.setString(3, administrateur.getPrenom());
            pstmt.setString(4, administrateur.getEmail());
            pstmt.setString(5, administrateur.getMotPasse());
            pstmt.setInt(6, administrateur.getTelephone());
            pstmt.setString(7, administrateur.getBp());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** find data by ID ***************************/
    public void findById(int administrateurId) {
        
        Administrateur administrateur = null;
        System.out.println(" findById() : Province Id: " + administrateurId);
        
        /* Setting The Particular administrateur Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT A.*, "
                    + "R.id As role_ID, "
                    + "R.nomRole AS role_name "
                    + "FROM administrateur A, role R "
                    + "WHERE  A.id = " + administrateurId ;
            
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                administrateur = new Administrateur();
                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP"));  
                
                /********** Role *********/
                
                administrateur.setIdRole(resultSet.getInt("role_ID"));  
                administrateur.setNomRole(resultSet.getString("role_name")); 

            }
            
            sessionMap.put("adminMapped", administrateur);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Administrateur administrateur){

        try {

            var query =" "
                    + "UPDATE administrateur "
                    + "SET "
                    + "nom = ?, "
                    + "prenom = ?, "
                    + "email = ?, "
                    + "telephone = ?, "
                    + "BP = ? "
                    + "where id = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, administrateur.getNom());
            pstmt.setString(2, administrateur.getPrenom());
            pstmt.setString(3, administrateur.getEmail());
            pstmt.setInt(4, administrateur.getTelephone());
            pstmt.setString(5, administrateur.getBp());
            pstmt.setInt(6, administrateur.getId());
            //pstmt.setInt(7, administrateur.getIdRole());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int administrateurId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : Administrateur Id: " + administrateurId);

        try {

            String query = "DELETE FROM administrateur WHERE id = " + administrateurId ;
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