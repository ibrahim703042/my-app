/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 * 
 */


import dbconnection.MySQLJDBCUtil;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import model.Administrateur;

import java.sql.*;
import java.util.*;
import model.Role;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean
@ApplicationScoped

public class AdministrateurDbUtil  extends MySQLJDBCUtil{
    
    protected List<Administrateur> administrateurs;
    protected Administrateur administrateur;
    
    protected List<Role> roles;
    protected Role role;
    
    private String query;
    
    //*************************** display data *****************/
    
    public List<Administrateur> findAll() {
        
        administrateurs = new ArrayList<>();
        query = ""
                + "SELECT administrateur.*, "
                + "role.id AS RoleID, "
                + "role.nomRole "
                + "FROM administrateur, role "
                + "WHERE administrateur.id_role = role.id "
                + "ORDER BY administrateur.id DESC";
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                
                administrateur = new Administrateur(); 

                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP"));
                administrateur.setIsActive(resultSet.getString("isActive"));
                administrateur.setDate(resultSet.getDate("date"));
                
                administrateur.setNomRole(resultSet.getString("nomRole")); 
                administrateur.setRoleId(resultSet.getInt("RoleID")); 

                administrateurs.add(administrateur);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return new ArrayList<>(administrateurs);
    }
    
    //*************************** load data into dropdown *****************/
    
    public List<Role> loadDropDown() {
        
        this.roles= new ArrayList<>();
        query = "SELECT id, nomRole from role ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
		    pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                this.role = new Role();
                this.role.setNomRole(resultSet.getString("nomRole")); 
                this.role.setId(resultSet.getInt("id")); 

                this.roles.add(this.role);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return new ArrayList<>(this.roles);
    }
    
    //************** Save data **********************************/ 
    public Administrateur save(Administrateur administrateur){
        Administrateur model = null;
        try {

            query = ""
                    + "INSERT INTO administrateur (id_Role, nom, prenom, email, motPasse, telephone, BP, isActive, creerPar) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            String query_2 = "INSERT INTO permission (id_administrateur,affiche, ajouter, modifier, supprimer)"
                    + "values(?,?,?,?,?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            String motPasse = DigestUtils.shaHex(administrateur.getMotPasse());
//            String motPasse = administrateur.getMotPasse();
            String admin = "Super administrateur";
            
            pstmt.setInt(1, administrateur.getRoleId());
            pstmt.setString(2, administrateur.getNom());
            pstmt.setString(3, administrateur.getPrenom());
            pstmt.setString(4, administrateur.getEmail());
            pstmt.setString(5, motPasse);
            pstmt.setInt(6, administrateur.getTelephone());
            pstmt.setString(7, administrateur.getBp());
            pstmt.setString(8, administrateur.getIsActive());
            //pstmt.setString(9, administrateur.getCeerPar());
            pstmt.setString(9, admin);

            pstmt.executeUpdate();
            
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }

    //************** find data by ID ***************************/
    public Administrateur findById(Integer administrateurId) {

        administrateur = null;
        System.out.println(" findById() : Administrateur Id: " + administrateurId);
        
        /* Setting The Particular administrateur Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("administrateurMapped", administrateur);

        try {
           query = ""
                + "SELECT administrateur.*, role.nomRole, role.id as RoleId "
                + "FROM administrateur, role "
                + "WHERE administrateur.id_role = role.id "
                + "AND administrateur.id = ?" ;
                    
            
            connection = dataSource.getConnection();

            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, administrateurId);
            resultSet = pstmt.executeQuery(query);    
            
            if(resultSet.next()) {
                
                administrateur = new Administrateur();
                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP")); 
                administrateur.setIsActive(resultSet.getString("isActive"));  
                
                /********** Role *********/
                
                administrateur.setRoleId(resultSet.getInt("RoleId"));  
                administrateur.setNomRole(resultSet.getString("nomRole")); 

            }
            
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return administrateur;
    }
    
    //************** update data ******************************/
    public Administrateur update(Administrateur administrateur){
        
        try {
            query =" "
                    + "UPDATE administrateur "
                    + "SET "
                    + "id_role = ?, "
                    + "nom = ?, "
                    + "prenom = ?, "
                    + "email = ?, "
                    + "telephone = ?, "
                    + "BP = ?, "
                    + "isActive = ? "
                    + "where id = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, administrateur.getRoleId());
            pstmt.setString(2, administrateur.getNom());
            pstmt.setString(3, administrateur.getPrenom());
            pstmt.setString(4, administrateur.getEmail());
            pstmt.setInt(5, administrateur.getTelephone());
            pstmt.setString(6, administrateur.getBp());
            pstmt.setString(7, administrateur.getIsActive());
            pstmt.setInt(8, administrateur.getId());
            //pstmt.setInt(7, administrateur.getIdRole());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
           printSQLException(sqlException);
        }
        return administrateur;
    }

    //************** delete data ********************************/
    public Administrateur delete(Integer administrateurId) {
        administrateur = null;
        System.out.println("delete() : Administrateur Id: " + administrateurId);

        try {
            connection = dataSource.getConnection();
            query = "DELETE FROM administrateur WHERE id = ? " ;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, administrateurId);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        return administrateur;
    }

    
      public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                }
            }
        }
    }
}