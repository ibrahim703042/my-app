/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnection;

/**
 *
 * @author Ibrahim
 */

import jakarta.servlet.http.HttpServlet;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    private Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db_impotfiscal", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error initializing servlet", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM administrateur");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Process the results
            }
        } catch (SQLException e) {
            throw new ServletException("Error executing SQL query", e);
        }
    }

    @Override
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            // Ignore
        }
    }
}