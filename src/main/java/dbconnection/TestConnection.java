package dbconnection;


import java.io.*;
import java.sql.*;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/TestConnection")
public class TestConnection extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    @Resource(name="jdbc/TDImpotFiscal")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM administrateur");
            while(resultSet.next()) {
                String name = resultSet.getString("nom");
                System.out.println("Name: "+name);
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}