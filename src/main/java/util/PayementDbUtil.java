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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ManagedBean
@ApplicationScoped

public class PayementDbUtil extends MySQLJDBCUtil {
   
    private List<Payement> payementList;
    private Payement payement;
    private String query;
    
    
    //*************************** display data *****************/
    public List<Payement> findAll() {
        
        payementList = new ArrayList<>();
        
        try {
            query = ""
                    + "SELECT * "
                    + "FROM payement "
                    + "WHERE id_payement IS NOT NULL ORDER BY id_payement DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                payement  = new Payement(); 

                payement.setId(resultSet.getInt("id_payement"));  
                payement.setIdAmande(resultSet.getInt("id_amande"));  
                payement.setModePayement(resultSet.getString("modePayement"));  
                payement.setMontantPaye(resultSet.getDouble("montantPaye")); 
                payement.setStatus(resultSet.getString("payement_status")); 
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

            query = ""
                    + "INSERT INTO payement "
                    + "(modePayement, montantPaye,payement_status, payement_status, datePayement) "
                    + "values (?,?,?,?,?)";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            pstmt = connection.prepareStatement(query);
            
            Date date = (Date) payement.getDatePayement();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String mysqlDateString = sdf.format(date);
            java.sql.Date payementDate = java.sql.Date.valueOf(mysqlDateString);


            pstmt.setString(1, payement.getModePayement());
            pstmt.setDouble(2, payement.getMontantPaye());
            pstmt.setString(3, payement.getStatus());
             pstmt.setString(4, payement.getStatus());
            pstmt.setDate(5, payementDate);

            pstmt.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
             }
        }
        return model;
    }
    
    //************** update data **********************************/ 
    public Payement update(Payement payement){
        Payement model = null ;
        
        try {

            query = ""
                    + "UPDATE payement SET"
                    + "id_amande  = ?, "
                    + "modePayement = ?, "
                    + "montantPaye = ?, "
                    + "payement_status = ?, "
                    + "datePayement = ? "
                    + "wHERE id_payement = ? ";
            
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            pstmt = connection.prepareStatement(query);
            
            Date date = (Date) payement.getDatePayement();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String mysqlDateString = sdf.format(date);
            java.sql.Date payementDate = java.sql.Date.valueOf(mysqlDateString);


            pstmt.setInt(1, payement.getIdAmande());
            pstmt.setString(2, payement.getModePayement());
            pstmt.setDouble(3, payement.getMontantPaye());
            pstmt.setString(4, payement.getStatus());
            pstmt.setDate(5, payementDate);
            pstmt.setInt(6, payement.getId());

            pstmt.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
             }
        }
        return model;
    }

    //************** find data by ID ***************************/
    public String findById(int payementId) {
        
        payement = null;
        System.out.println(" findById() : Province Id: " + payementId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            query = "SELECT * FROM payement WHERE id =" + payementId ;
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                payement = new Payement();
                payement.setId(resultSet.getInt("id_payement"));  
                payement.setIdAmande(resultSet.getInt("id_amande"));  
                payement.setModePayement(resultSet.getString("modePayement"));  
                payement.setMontantPaye(resultSet.getDouble("montantPaye")); 
                payement.setStatus(resultSet.getString("payement_status")); 
                payement.setDatePayement(resultSet.getDate("datePayement")); 
            }
            
            sessionMap.put("payementMapped", payement);
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        //return "/pages/admin/edit.xhtml";
        return "";
    }
	
    //************** delete data ********************************/
    public void delete(int payementId) {
        
        System.out.println("delete() : Payement Id: " + payementId);

        try {
            
            query = "DELETE FROM payement WHERE id = ? " ;
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, payementId);
            
            pstmt.executeUpdate();  
            
           connection.commit();
           connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}