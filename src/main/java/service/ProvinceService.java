/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.sql.*;
import java.util.*;
import model.Province;

/**
 *
 * @author Ibrahim
 */

@Named
@ApplicationScoped

public class ProvinceService {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
        
    public static Connection getConnection() {
        
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");     
            String db_url ="jdbc:mysql://localhost:3306/db_impotFiscal",db_userName = "root",db_password = "";
            connection = DriverManager.getConnection(db_url,db_userName,db_password);  
        
        }catch(ClassNotFoundException | SQLException sqlException) {  
            sqlException.printStackTrace();
        } 
        
        return connection;
    }
    private List<Province> provinces;

    @PostConstruct
    public void init() {
        provinces = new ArrayList<>();

        try {
            statement = getConnection().createStatement();    
            resultSet = statement.executeQuery("SELECT * FROM province");  
            
            while(resultSet.next()) {  
                Province province = new Province(); 
                province.setId(resultSet.getInt("id"));  
                province.setNomProvince(resultSet.getString("nomProvince"));  
                provinces.add(province);  
            }   
            
            System.out.println("Total Records Fetched: " + provinces.size());
            connection.close();
            
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } 
        
    }

    public List<Province> getProvinces() {
        return new ArrayList<>(provinces);
    }

    public List<Province> getProvinces(int size) {

        if (size > provinces.size()) {
            Random rand = new Random();

            List<Province> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(provinces.size());
                randomList.add(provinces.get(randomIndex));
            }

            return randomList;
        }

        else {
           return new ArrayList<>(provinces.subList(0, size));
        }

    }

    public List<Province> getClonedProvinces(int size) {
        List<Province> results = new ArrayList<>();
        List<Province> originals = getProvinces(size);
        for (Province original : originals) {
            results.add(original.clone());
        }

        // make sure to have unique codes
//        for (Province Province : results) {
//            Province.setCode(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
//        }

        return results;
    }
    
}
