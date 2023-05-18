
package util;

import dbconnection.MySQLJDBCUtil;
import java.sql.*;

public class UserDbUtil {
	
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
    
    //********************** admin authentificator ********************/	
    public static boolean validate(String email, String motPasse) {

        try {

            String query = "SELECT * FROM administrateur WHERE email = ? and motPasse = ?";
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, motPasse);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
            
        } catch(SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            MySQLJDBCUtil.close(connection);
        }
        return false;
    }
    
}

