/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 */

import static dbconnection.MySQLJDBCUtil.dataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import model.Payement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


@Named
@ApplicationScoped

public class PayementDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static ArrayList findAll() {
        
        ArrayList impotList = new ArrayList();
        
        try {
            String query = "SELECT * FROM impot WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Payement impot = new Payement(); 

                impot.setId(resultSet.getInt("id"));  
                impot.setModePayement(resultSet.getString("modePayement"));  
                impot.setMontantPaye(resultSet.getDouble("montantPaye")); 
                impot.setDatePayement(resultSet.getDate("datePayement")); 

                impotList.add(impot);  
            }   

            System.out.println("Total Records Fetched: " + impotList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return impotList;
    }

    //************** Save data **********************************/ 
    public static String save(Payement impot){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO impot (modePayement, montantPaye, datePayement) "
                    + "values (?, ?,?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setString(1, impot.getModePayement());
            pstmt.setDouble(2, impot.getMontantPaye());
            pstmt.setDate(3, (Date) impot.getDatePayement());
            //statement.setDate(7, (java.sql.Date) impot.getDate());

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
    public static String findById(int impotId) {
        
        Payement impot = null;
        System.out.println(" findById() : Province Id: " + impotId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM impot WHERE id =" + impotId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                impot = new Payement();
                impot.setId(resultSet.getInt("id"));  
                impot.setModePayement(resultSet.getString("modePayement"));  
                impot.setMontantPaye(resultSet.getDouble("montantPaye")); 
                impot.setDatePayement(resultSet.getDate("datePayement")); 
                //impot.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("payementMapped", impot);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Payement impot){

        String message = "Updated Successfully";

        try {

            String query ="Update impot SET "
                    + "modePayement=?, "
                    + "montantPaye=?, "
                    + "datePayement=?, ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, impot.getModePayement());
            pstmt.setDouble(2, impot.getMontantPaye());
            pstmt.setDate(3, (Date) impot.getDatePayement());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int impotId) {
        
        System.out.println("delete() : impot Id: " + impotId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM impot WHERE id = " + impotId ;
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