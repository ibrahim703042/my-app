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

import static dbconnection.MySQLJDBCUtil.dataSource;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import model.Administrateur;

import java.sql.*;
import java.util.*;
import model.Permission;
import model.Role;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.PrimeFaces;

@ManagedBean
@ApplicationScoped

public class AdministrateurDbUtil{
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    protected List<Administrateur> administrateurs;
    protected Administrateur administrateur;
    
    private String query;
    

    //*************************** display data *****************/
    
    
    public List<Administrateur> getAdministrateurs() {
        
        administrateurs = new ArrayList<>();
        query = ""
                + "SELECT administrateur.*, "
                + "role.id AS RoleID, "
                + "role.nomRole, "
                + "permission.ajouter, "
                + "permission.afficher, "
                + "permission.supprimer, "
                + "permission.modifier "
                + "FROM administrateur, role, permission "
                + "WHERE administrateur.id_role = role.id "
                + "AND administrateur.id = permission.id_administrateur "
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
                
                //administrateur.setPermissionId(resultSet.getInt(" PermissionID"));
                administrateur.setAfficher(resultSet.getBoolean("afficher"));
                administrateur.setAjouter(resultSet.getBoolean("ajouter"));
                administrateur.setSupprimer(resultSet.getBoolean("supprimer"));
                administrateur.setModifier(resultSet.getBoolean("modifier"));
                
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
        
        List<Role>  roleList = new ArrayList<>();
        query = "SELECT id, nomRole from role ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
		    pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                //this.role = new Role();
                Role role = new Role();
                role.setNomRole(resultSet.getString("nomRole")); 
                role.setId(resultSet.getInt("id_role")); 

                roleList.add(role);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return new ArrayList<>(roleList);
    }
    
    //************** Save data **********************************/ 
    public Administrateur save(Administrateur administrateur){
        Administrateur model = null;
        try {

            query = ""
                    + "INSERT INTO administrateur (id_Role, nom, prenom, email, motPasse, telephone, BP, isActive, creerPar) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            String motPasse = DigestUtils.shaHex(administrateur.getMotPasse());
            String admin = "administrateur";
            
            pstmt.setInt(1, administrateur.getRoleId());
            pstmt.setString(2, administrateur.getNom());
            pstmt.setString(3, administrateur.getPrenom());
            pstmt.setString(4, administrateur.getEmail());
            pstmt.setString(5, motPasse);
            pstmt.setInt(6, administrateur.getTelephone());
            pstmt.setString(7, administrateur.getBp());
            pstmt.setString(8, administrateur.getIsActive());
//            pstmt.setString(9, administrateur.getCeerPar());
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

        Administrateur administrateur = null;
        System.out.println(" findById() : Administrateur Id: " + administrateurId);
        
        /* Setting The Particular administrateur Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("administrateur", administrateur);

        try {
           query = ""
                + "SELECT administrateur.*, role.nomRole, role.id as Role_id "
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
                
                administrateur.setRoleId(resultSet.getInt("Role_id"));  
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
    public Administrateur delete(Administrateur administrateur) {
        
        System.out.println("delete() : Administrateur Id: " + administrateur.getId());

        try {
            connection = dataSource.getConnection();
            query = "DELETE FROM administrateur WHERE id = ? " ;
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, administrateur.getId());
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        return administrateur;
    }

    
    
    //************** authentification  ***********************/
    
    public static boolean validate(String email,String motPasse) throws SQLException {
        
        Integer isActive= 1;
        connection = dataSource.getConnection();
        String sql = "SELECT * FROM administrateur WHERE email = ? AND motPasse = ? AND isActive = " + isActive ;
       String sqlc = ""
                + "SELECT administrateur.*, "
                + "role.nomRole, "
                + "role.description "
                + "FROM administrateur, role "
                + "WHERE email = ? AND motPasse = ? AND isActive = " + isActive ;
        
        boolean status = false;
            
        
        try {
            
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, motPasse);

            resultSet = pstmt.executeQuery();
            status = resultSet.next();
            
            while(status) { 
                
               Administrateur administrateur = new Administrateur(); 

                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP"));  
                administrateur.setDate(resultSet.getDate("date")); 
                
//                administrateur.setNomRole(resultSet.getString("nomRole")); 
//                administrateur.setDescriptionRole(resultSet.getString("description")); 
                //administrateur.setRole(resultSet.getObject("nomRole", role.getClass())); 

                //connection.commit();
            }

        }catch (SQLException ex) {
            printSQLException(ex);
        } finally {
            connection.close();
        }
        return status;
    }
    
    public List<Administrateur> findByEmailPassword(String email,String motPasse) {
        
        administrateurs = new ArrayList<>();
        query = ""
                + "SELECT administrateur.*, role.nomRole "
                + "FROM administrateur, role "
                + "WHERE email = " + motPasse + " ,"
                + "AND motPasse = "  + email + ","
                + "AND isActive = " + 1 ;
                
        
        try {
            
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            while(resultSet.next()) { 
                
                administrateur = new Administrateur(); 

                administrateur.setId(resultSet.getInt("id"));  
                administrateur.setNom(resultSet.getString("nom"));  
                administrateur.setPrenom(resultSet.getString("prenom"));  
                administrateur.setEmail(resultSet.getString("email"));  
                administrateur.setMotPasse(resultSet.getString("motPasse"));  
                administrateur.setTelephone(resultSet.getInt("telephone"));  
                administrateur.setBp(resultSet.getString("BP"));  
                administrateur.setDate(resultSet.getDate("date")); 
                
//                administrateur.setNomRole(resultSet.getString("nomRole")); 

                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return administrateurs;
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