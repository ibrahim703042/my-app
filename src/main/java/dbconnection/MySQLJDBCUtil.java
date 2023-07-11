/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dbconnection;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author Ibrahim
 * 
 */

public class MySQLJDBCUtil {
   
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
    
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_impotfiscal";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DRIVER= "com.mysql.cj.jdbc.Driver";
    public static final BasicDataSource dataSource = new BasicDataSource();

    static {
        
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        dataSource.setDriverClassName(DRIVER);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    
    // ****************message*********
    public void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
    public void warningMessage(String msg1, String msg2){
        FacesContext.getCurrentInstance().addMessage( null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,msg1,msg2));
    }
    
    // ******   Message Session ******/
    public void showInfo(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, content, msg);
    }
    
    public void showWarn(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, content, msg);
    }

    public void showError(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, content, msg);
    }

    //************** error  message from sql ***********************/
    public void addErrorMessage(SQLException ex) {
        
        //FacesMessage message = new FacesMessage(ex.getMessage());
        //FacesContext.getCurrentInstance().addMessage(null, message);
        addMessage(FacesMessage.SEVERITY_ERROR,"Error SQL",ex.getMessage());
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public static void printSQLException(SQLException ex) {
      for (Throwable e : ex) {
          if (e instanceof SQLException) {
              e.printStackTrace(System.err);
              System.err.println("SQLState: " + ((SQLException) e).getSQLState());
              System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
              System.err.println("Message: " + e.getMessage());
              Throwable t = ex.getCause();
              while (t != null) {
                      System.out.println("Cause: " + t);
                      t = t.getCause();
              }
          }
      }
    }

}