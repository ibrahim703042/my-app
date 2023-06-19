/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dbconnection;

import static dbconnection.MySQLJDBCUtil.connection;
import static dbconnection.MySQLJDBCUtil.dataSource;
import static dbconnection.MySQLJDBCUtil.pstmt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ibrahim
 */
public class app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String query_1 = "INSERT INTO province (nomProvince) VALUES (?)";
        String query_2 = "INSERT INTO commune (id_province,nomCommune) VALUES (?,?)";
        String query_select = "SELECT id FROM commune ORDER BY id DESC LIMIT 1";
        String query_3 = "INSERT INTO colline (id_commune,nomColline) VALUES (?,?)";
        String query_4 = "INSERT INTO immeuble (id_colline,id_contrinuable,nomAvenue) VALUES (?,?,?)";
        
        ResultSet rs;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            pstmt = connection.prepareStatement(query_1, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "Muyinga");
            pstmt.executeUpdate();
            
            // Retrieve the value from the first table
            
            //pstmt = connection.prepareStatement(query_select);
            //pstmt.executeUpdate();
            
            //pstmt = connection.prepareStatement("SELECT id FROM commune WHERE id_commune = ?");
            //pstmt.setInt(1, id);
            //rs = pstmt.executeQuery();
            
            rs = pstmt.executeQuery(query_select);
            rs = pstmt.getGeneratedKeys();
            
            rs.next();
            int id = rs.getInt(1);
            int id_Commune = rs.getInt("id");
            
            pstmt = connection.prepareStatement(query_2, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, id);
            pstmt.setString(2, "Kobero");
            pstmt.executeUpdate();
            
            
            pstmt = connection.prepareStatement(query_3);
            pstmt.setInt(1, id_Commune);
            pstmt.setString(2, "Magara");
            pstmt.executeUpdate();
            
//            rs.next();
//            int id_Colline = rs.getInt(1);
//            
//            pstmt = connection.prepareStatement(query_4);
//            pstmt.setInt(1, id_Colline);
//            pstmt.setString(2, "Kayo");
//            pstmt.executeUpdate();
            
//            if (rs.next()) {
//                int id = rs.getInt(1);
//                int id_Commune = rs.getInt(1);
//                System.out.println("Last inserted ID on table Province: " + id);
//                System.out.println("Last inserted ID on table Commune: " + id_Commune);
//            }
            
            connection.commit();
            
        }catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}