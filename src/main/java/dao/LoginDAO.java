package dao;

import dbconnection.MySQLJDBCUtil;
import jakarta.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Named
public class LoginDAO {
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
    private static String query = "SELECT * FROM administrateur WHERE email = ? AND motPasse = ? " ;
    
    public static boolean validate(String email, String motPasse) {

    try {
        
        connection = MySQLJDBCUtil.getConnection();
        pstmt = connection.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.setString(2, motPasse);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            //result found, means valid inputs
            return true;
        }

    }catch (SQLException ex) {
        System.out.println("Login error -->" + ex.getMessage());
        return false;
    } finally {
        //   MySQLJDBCUtil.close(connection);
    }
    return false;
    }
}