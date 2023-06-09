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
import static dbconnection.MySQLJDBCUtil.dataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import model.Amande;
import java.sql.*;
import java.util.*;
import static util.AbbattementDbUtil.connection;

@Named
@ApplicationScoped

public class AmandeDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static ArrayList findAll() {
        
        ArrayList amandeList = new ArrayList();
        
        try {
            String query = "SELECT * FROM amande WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Amande amande = new Amande(); 

                amande.setId(resultSet.getInt("id"));  
                amande.setIdImpot(resultSet.getInt("id_impot"));  
                amande.setAmandeFixe(resultSet.getInt("amande_fixe"));  
                amande.setPenalite(resultSet.getDouble("penalite"));  
                amande.setTotal(resultSet.getInt("total"));

                amandeList.add(amande);  
            }   

            System.out.println("Total Records Fetched: " + amandeList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return amandeList;
    }

    //************** Save data **********************************/ 
    public static String save(Amande amande){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO amande (id_impot, amande_fixe, penalite, total) "
                    + "values (?, ?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, amande.getIdImpot());
            pstmt.setInt(2, amande.getAmandeFixe());
            pstmt.setDouble(3, amande.getPenalite());
            pstmt.setInt(4, amande.getTotal());
            //statement.setDate(7, (java.sql.Date) amande.getDate());

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
    public static String findById(int amandeId) {
        
        Amande amande = null;
        System.out.println(" findById() : Province Id: " + amandeId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM amande WHERE id =" + amandeId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                amande = new Amande();
                amande.setId(resultSet.getInt("id"));  
                amande.setIdImpot(resultSet.getInt("id_impot"));  
                amande.setAmandeFixe(resultSet.getInt("amande_fixe"));  
                amande.setPenalite(resultSet.getDouble("penalite"));  
                amande.setTotal(resultSet.getInt("total")); 
                //amande.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("amandeMapped", amande);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Amande amande){

        String message = "Updated Successfully";

        try {

            String query ="Update amande SET "
                    + "id_impot=?, "
                    + "amande_fixe=?, "
                    + "penalite=?, "
                    + "total=? "
                    + "where id= ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, amande.getIdImpot());
            pstmt.setInt(2, amande.getAmandeFixe());
            pstmt.setDouble(3, amande.getPenalite());
            pstmt.setInt(4, amande.getTotal());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int amandeId) {
        
        //System.out.println("delete() : amande Id: " + amandeId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM amande WHERE id = " + amandeId ;
            pstmt = connection.prepareStatement(query);
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