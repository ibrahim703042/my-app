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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import model.Abattement;
import java.sql.*;
import java.util.*;

@Named
@ApplicationScoped

public class AbattementDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static ArrayList findAll() {
        
        ArrayList abattementList = new ArrayList();
        
        try {
            String query = "SELECT * FROM abattement WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Abattement abattement = new Abattement(); 

                abattement.setId(resultSet.getInt("id"));  
                abattement.setBeneficiaire(resultSet.getShort("beneficiaire"));  
                abattement.setMotif(resultSet.getString("motif")); 

                abattementList.add(abattement);  
            }   

            System.out.println("Total Records Fetched: " + abattementList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return abattementList;
    }

    //************** Save data **********************************/ 
    public static String save(Abattement abattement){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO abattement (benificiare, motif) "
                    + "values (?, ?)";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setShort(1, abattement.getBeneficiaire());
            pstmt.setString(2, abattement.getMotif());
            //statement.setDate(7, (java.sql.Date) abattement.getDate());

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
    public static String findById(int abattementId) {
        
        Abattement abattement = null;
        System.out.println(" findById() : Province Id: " + abattementId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM abattement WHERE id =" + abattementId ;
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                abattement = new Abattement();
                abattement.setId(resultSet.getInt("id"));  
                abattement.setBeneficiaire(resultSet.getShort("beneficiaire"));  
                abattement.setMotif(resultSet.getString("motif")); 
                //abattement.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("abattementMapped", abattement);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Abattement abattement){

        String message = "Updated Successfully";

        try {

            String query ="Update abattement SET "
                    + "beneficiaire=?, "
                    + "motif=?, ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setShort(1, abattement.getBeneficiaire());
            pstmt.setString(2, abattement.getMotif());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int abattementId) {
        
        connection = MySQLJDBCUtil.getConnection();
        //System.out.println("delete() : abattement Id: " + abattementId);

        try {

            String query = "DELETE FROM abattement WHERE id = " + abattementId ;
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