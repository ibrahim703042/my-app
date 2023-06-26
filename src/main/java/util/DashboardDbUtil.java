/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dbconnection.MySQLJDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Administrateur;
import model.Colline;
import model.Commune;
import model.Contribuable;
import model.Declaration;
import model.Payement;
import model.Province;
import model.Quittance;
import model.Representant;
import model.Role;

/**
 *
 * @author Ibrahim
 * 
 */

public class DashboardDbUtil extends MySQLJDBCUtil {
    
    private static List<Role> roles;
    private static List<Province> provinces;
    private static List<Commune> communes;
    private static List<Colline> collines;
    private static List<Administrateur> administrateurs;
    private static List<Contribuable> contribuables;
    private static List<Representant> representants;
    private static List<Declaration> declarations;
    private static List<Payement> payements;
    private static List<Quittance> quittances;
    private static String query;
    
    public DashboardDbUtil(){
    }

    public static List<Role> countRole() {
        
        roles = new ArrayList<>();
        query = " SELECT COUNT(*) FROM role ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Role role = new Role();
                role.setId(resultSet.getInt("id"));  
                
                roles.add(role);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return roles;
    }
   
    public static List<Province> countProvince() {
        
        contribuables = new ArrayList<>();
        query = " SELECT COUNT(*) FROM province ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Province province = new Province();
                province.setId(resultSet.getInt("id"));  
                
                provinces.add(province);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return provinces;
    }

    public static List<Commune> countCommune() {
        
        communes = new ArrayList<>();
        query = " SELECT COUNT(*) FROM commune ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Commune commune = new Commune();
                commune.setId(resultSet.getInt("id"));  
                
                communes.add(commune);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return communes;
    }
    
    public static List<Colline> countColline() {
        
        declarations = new ArrayList<>();
        query = " SELECT COUNT(*) FROM colline ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Colline colline = new Colline();
                colline.setId(resultSet.getInt("id"));  
                
                collines.add(colline);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return collines;
    }


    public static void countAdministrateur() {
        
        //administrateurs = new ArrayList<>();
        query = " SELECT COUNT(*) FROM administrateur ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            resultSet.next() ;
            int count = resultSet.getInt(1);
            System.out.println("Number of rows: " + count);
            //connection.commit();
            
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        //return administrateurs;
    }
    
    public static List<Contribuable> countContribuable() {
        
        contribuables = new ArrayList<>();
        query = " SELECT COUNT(*) FROM contribuable ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Contribuable contribuable = new Contribuable();
                contribuable.setId(resultSet.getInt("id"));  
                
                contribuables.add(contribuable);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return contribuables;
    }

    public static List<Representant> countRepresentant() {
        
        representants = new ArrayList<>();
        query = " SELECT COUNT(*) FROM representant ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Representant representant = new Representant();
                representant.setId(resultSet.getInt("id"));  
                
                representants.add(representant);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return representants;
    }
    
    public static List<Declaration> countDeclaration() {
        
        declarations = new ArrayList<>();
        query = " SELECT COUNT(*) FROM declaration ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Declaration declaration = new Declaration();
                declaration.setId(resultSet.getInt("id"));  
                
                declarations.add(declaration);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return declarations;
    }


    public static List<Payement> countPaidPayement() {
        
        payements = new ArrayList<>();
        query = " SELECT COUNT(*) FROM payement ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Payement payement = new Payement();
                payement.setId(resultSet.getInt("id"));  
                
                payements.add(payement);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return payements;
    }
    
    public static List<Payement> countUnpaidPayement() {
        
        payements = new ArrayList<>();
        query = " SELECT COUNT(*) FROM payement ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Payement payement = new Payement();
                payement.setId(resultSet.getInt("id"));  
                
                payements.add(payement);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return payements;
    }
    
    public static List<Quittance> countQuittance() {
        
        quittances = new ArrayList<>();
        query = " SELECT COUNT(*) FROM quittance ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                Quittance quittance = new Quittance();
                quittance.setId(resultSet.getInt("id"));  
                
                quittances.add(quittance);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return quittances;
    }


}
