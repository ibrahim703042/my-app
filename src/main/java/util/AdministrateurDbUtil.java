/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 */

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import model.Administrateur;
import java.sql.*;
import java.util.*;
import java.util.Map;

@Named
@ApplicationScoped

public class AdministrateurDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
    
    //************** data connection ***********************/  
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");     
            String db_url ="jdbc:mysql://localhost:3306/db_impotFiscal",db_userName = "root",db_password = "";
            connection = DriverManager.getConnection(db_url,db_userName,db_password);  

        }catch(ClassNotFoundException | SQLException sqlException) {  
            sqlException.printStackTrace();
        } 

        return connection;
    }

    //*************************** display data *****************/
    public static ArrayList findAll() {
        ArrayList administrateurList = new ArrayList();  
        try {
            statement = getConnection().createStatement();
            String query = "SELECT * FROM administrateur WHERE id IS NOT NULL ORDER BY id DESC";
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
    public static String save(Administrateur administrateur){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO administrateur (nom, prenom, email, motPasse, telephone, BP) "
                    + "values (?, ?, ?, ?, ?, ?)";

            pstmt = getConnection().prepareStatement(query);         

            pstmt.setString(1, administrateur.getNom());
            pstmt.setString(2, administrateur.getPrenom());
            pstmt.setString(3, administrateur.getEmail());
            pstmt.setString(4, administrateur.getMotPasse());
            pstmt.setInt(5, administrateur.getTelephone());
            pstmt.setString(6, administrateur.getBp());
            //statement.setDate(7, (java.sql.Date) administrateur.getDate());

            saveResult = pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }if(saveResult !=0) {
            navigationResult = "/pages/admin/template.xhtml?faces-redirect=true";
            showMessage(message);
            
        } else {
            navigationResult = "";
        }
        return navigationResult;
    }

    //************** find data by ID ***************************/
    public static String findById(int administrateurId) {
        
        Administrateur administrateur = null;
        System.out.println(" findById() : Province Id: " + administrateurId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
            
            statement = getConnection().createStatement();
            String query = "SELECT * FROM administrateur WHERE id =" + administrateurId ;
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
                //administrateur.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("adminMapped", administrateur);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Administrateur administrateur){

        //Integer saveResult = 0;
        //String navigationResult = "";
        String message = "Updated Successfully";

        try {

            String query ="Update administrateur SET "
                    + "nom=?, "
                    + "prenom=?, "
                    + "telephone=?, "
                    + "BP=? "
                    + "where id= ? ";

            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, administrateur.getNom());
            pstmt.setString(2, administrateur.getPrenom());
            pstmt.setInt(3, administrateur.getTelephone());
            pstmt.setString(4, administrateur.getBp());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int administrateurId) {
        //System.out.println("delete() : Administrateur Id: " + administrateurId);

        try {

            String query = "DELETE FROM administrateur WHERE id = " + administrateurId ;
            pstmt = getConnection().prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
        return "/pages/admin/template.xhtml?faces-redirect=true";
    }
    
    
    //************** conecxt msg data ***********************/
    private static void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}