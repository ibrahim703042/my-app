/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dbconnection;
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
        
        // create a new connection from MySQLJDBCUtil
        try (
                Connection connection = MySQLJDBCUtil.getConnection()) {
            
            // print out a message
            System.out.println(String.format(msg, connection.getCatalog()));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
