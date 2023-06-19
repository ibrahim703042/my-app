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
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import model.Payement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ManagedBean
@ApplicationScoped

public class PayementDbUtil extends MySQLJDBCUtil {
   
    private List<Payement> payementList;
    private Payement payement;
    
    
    //*************************** display data *****************/
    public List<Payement> findAll() {
        
        payementList = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM payement WHERE id_payement IS NOT NULL ORDER BY id_payement DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                payement  = new Payement(); 

                payement.setId(resultSet.getInt("id_payement"));  
                payement.setModePayement(resultSet.getString("modePayement"));  
                payement.setMontantPaye(resultSet.getDouble("montantPaye")); 
                payement.setDatePayement(resultSet.getDate("datePayement")); 

                payementList.add(payement);  
            }   

            System.out.println("Total Records Fetched: " + payementList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return payementList;
    }

    //************** Save data **********************************/ 
    public Payement save(Payement payement){
        Payement model = null ;
        
        try {

            String query = "INSERT INTO payement (modePayement, montantPaye, datePayement) values (?, ?,?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setString(1, payement.getModePayement());
            pstmt.setDouble(2, payement.getMontantPaye());
            pstmt.setDate(3, (Date) payement.getDatePayement());

            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }

    //************** find data by ID ***************************/
    public static String findById(int payementId) {
        
        Payement payement = null;
        System.out.println(" findById() : Province Id: " + payementId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = "SELECT * FROM payement WHERE id =" + payementId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                payement = new Payement();
                payement.setId(resultSet.getInt("id"));  
                payement.setModePayement(resultSet.getString("modePayement"));  
                payement.setMontantPaye(resultSet.getDouble("montantPaye")); 
                payement.setDatePayement(resultSet.getDate("datePayement")); 
                //payement.setDate(resultSet.getDate("date"));  
               //LocalDate date = LocalDate.now();

            }
            
            sessionMap.put("payementMapped", payement);
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return "/pages/admin/edit.xhtml";
    }
	
    //************** update data ******************************/
    public String update(Payement payement){

        try {

            String query ="Update payement SET "
                    + "modePayement=?, "
                    + "montantPaye=?, "
                    + "datePayement=?, ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, payement.getModePayement());
            pstmt.setDouble(2, payement.getMontantPaye());
            pstmt.setDate(3, (Date) payement.getDatePayement());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
            return "/pages/admin/template.xhtml?faces-redirect=true";
    }

    //************** delete data ********************************/
    public String delete(int payementId) {
        
        System.out.println("delete() : payement Id: " + payementId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM payement WHERE id = " + payementId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        return "/pages/admin/template.xhtml?faces-redirect=true";
    }
    
   
}