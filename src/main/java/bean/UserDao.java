///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package bean;
//
//import dbconnection.MySQLJDBCUtil;
//import java.sql.SQLException;
//import java.util.*;
//import model.Administrateur;
//import util.AdministrateurDbUtil;
//
///**
// *
// * @author Ibrahim
// */
//
//public class UserDao{
//    
//    public List<Administrateur> findByEmailPassword(String email,String motPasse) {
//        
//        List<Administrateur> administrateurs = new ArrayList<>();
//        
//        connection = MySQLJDBCUtil.getConnection();
//        query = ""
//                + "SELECT administrateur.*, role.nomRole "
//                + "FROM administrateur, role "
//                + "WHERE administrateur.id_role = role.id "
//                + "AND email = " + motPasse + " ,"
//                + "AND motPasse = "  + email + ","
//                + "AND isActive = " + 1 ;
//                
//        
//        try {
//            
//            //connection.setAutoCommit(false);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(query);
//            
//            connection = MySQLJDBCUtil.getConnection();
//            pstmt = connection.prepareStatement(query);
//            
//            while(resultSet.next()) { 
//                
//                Administrateur administrateur = new Administrateur(); 
//
//                administrateur.setId(resultSet.getInt("id"));  
//                administrateur.setNom(resultSet.getString("nom"));  
//                administrateur.setPrenom(resultSet.getString("prenom"));  
//                administrateur.setEmail(resultSet.getString("email"));  
//                administrateur.setMotPasse(resultSet.getString("motPasse"));  
//                administrateur.setTelephone(resultSet.getInt("telephone"));  
//                administrateur.setBp(resultSet.getString("BP"));  
//                administrateur.setDate(resultSet.getDate("date")); 
//                
//                administrateur.setNomRole(resultSet.getString("nomRole")); 
//
//                //connection.commit();
//            }
//           
//            
//        } catch (SQLException ex) {
//            //connection.rollback();
//            printSQLException(ex);
//        }finally {
//            //connection.setAutoCommit(true);
//        }
//        return administrateurs;
//    }
//    
//}
