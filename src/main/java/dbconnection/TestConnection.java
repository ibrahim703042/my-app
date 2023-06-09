/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dbconnection;
import static dbconnection.MySQLJDBCUtil.dataSource;
import java.sql.*;

/**
 *
 * @author Ibrahim
 * 
 */

public class TestConnection {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        String msg = "Connected to database %s successfully.";
        
        try {
                Connection connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                
                // print out a message
                System.out.println(String.format(msg, connection.getCatalog()));
                connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
