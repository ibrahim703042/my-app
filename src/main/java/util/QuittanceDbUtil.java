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
import model.Quittance;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Named
@ApplicationScoped
public class QuittanceDbUtil extends MySQLJDBCUtil {
   
    //*************************** display data *****************/
    public static ArrayList findAll(Object newParam) {
        
        ArrayList quittanceList = new ArrayList();
        
        try {
            String query = "SELECT * FROM quittance WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Quittance quittance = new Quittance(); 

                quittance.setId(resultSet.getInt("id"));  
                //quittance.setIdPayement(resultSet.getInt("idPayement"));  
                quittance.setDateQuittance(resultSet.getDate("dateQuittance")); 
                //quittance.setMontantRestante(resultSet.getInt("montantRestante")); 

                quittanceList.add(quittance);  
            }   

            System.out.println("Total Records Fetched: " + quittanceList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return quittanceList;
    }

    //************** Save data **********************************/ 
    public static String save(Quittance quittance){
        
        Integer saveResult = 0;
        String navigationResult = "";
        String message = "Record Inserted";
        
        try {

            String query = 
                    "INSERT INTO quittance (idPayement, dateQuittance, montantRestante) "
                    + "values (?, ?,?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            //pstmt.setInt(1, quittance.getIdPayement());
            pstmt.setDate(2, (Date) quittance.getDateQuittance());
            //pstmt.setInt(3, quittance.getMontantRestante());
            //statement.setDate(7, (java.sql.Date) quittance.getDate());

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
    public static String findById(int quittanceId) {
        
        Quittance quittance = null;
        System.out.println(" findById() : Province Id: " + quittanceId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM quittance WHERE id =" + quittanceId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                quittance = new Quittance();
                quittance.setId(resultSet.getInt("id"));  
                //quittance.setIdPayement(resultSet.getInt("idPayement"));  
                quittance.setDateQuittance(resultSet.getDate("dateQuittance")); 
                //quittance.setMontantRestante(resultSet.getInt("montantRestante")); 
                //quittance.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("quittanceMapped", quittance);
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public static String update(Quittance quittance){

        String message = "Updated Successfully";

        try {

            String query ="Update quittance SET "
                    + "idPayement=?, "
                    + "dateQuittance=?, "
                    + "montantRestante=?, ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            //pstmt.setInt(1, quittance.getIdPayement());
            pstmt.setDate(2, (Date) quittance.getDateQuittance());
            //pstmt.setInt(3, quittance.getMontantRestante());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
            showMessage(message);
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public static String delete(int quittanceId) {
        
        System.out.println("delete() : quittance Id: " + quittanceId);

        try {

            String query = "DELETE FROM quittance WHERE id = " + quittanceId ;
            connection = dataSource.getConnection();
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