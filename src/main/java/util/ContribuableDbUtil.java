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
import jakarta.faces.bean.*;
import jakarta.faces.context.FacesContext;
import java.sql.*;
import java.util.*;
import model.Abbattement;
import model.Contribuable;
import model.Representant;
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean
@ApplicationScoped

public class ContribuableDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;
    
    protected List<Representant> representants;
    protected Representant representant;
    
    protected List< Abbattement>  abbattements;
    protected Contribuable abbattement;
    
    private List<Contribuable> contribuableList;

    //*************************** display data *****************/
    public List<Contribuable> findAll() {
        
        contribuableList = new ArrayList();
        
        try {

            String query = "SELECT * FROM contribuable WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

               Contribuable contribuable = new Contribuable(); 

               contribuable.setId(resultSet.getInt("id"));  
               contribuable.setNom(resultSet.getString("nom"));  
               //contribuable.setPrenom(resultSet.getString("prenom"));  
               contribuable.setEmail(resultSet.getString("email"));  
               contribuable.setMotPasse(resultSet.getString("motPasse"));  
               contribuable.setTelephone(resultSet.getInt("telephone"));  
               contribuable.setBp(resultSet.getString("BP"));  
               contribuable.setDate(resultSet.getDate("date")); 

               contribuableList.add(contribuable);  
            }   

            System.out.println("Total Records Fetched: " + contribuableList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return new ArrayList<>(this.contribuableList);
    }
    
    //*************************** load dropdown *****************/
    public List<Representant> loadDropDown() {
        
        this.representants = new ArrayList<>();
        String query = "SELECT * from representant ";
        
        try {
            connection = dataSource.getConnection();
            //connection.setAutoCommit(false);
		    pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();  
            
            while(resultSet.next()) { 
                this.representant = new Representant();
                this.representant.setNomRepresentant(resultSet.getString("nomRepresentant")); 
                this.representant.setPrenomRepresentant(resultSet.getString("prenomRepresentant")); 
                this.representant.setId(resultSet.getInt("id")); 

                this.representants.add(this.representant);  
                //connection.commit();
            }
           
            
        } catch (SQLException ex) {
            //connection.rollback();
            printSQLException(ex);
        }finally {
            //connection.setAutoCommit(true);
        }
        return new ArrayList<>(this.representants);
    }
    
    //************** Save data **********************************/ 
    public Contribuable save(Contribuable contribuable){
        Contribuable model = null;
        try {

            String query = 
                    "INSERT INTO contribuable (id_representant, nom, email, motPasse, telephone, BP) "
                    + "values (?, ?, ?, ?, ?, ?)";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            String motPasse = DigestUtils.shaHex(contribuable.getMotPasse());
            //String motPasse = administrateur.getMotPasse();
            //String admin = "Super administrateur";
            
            pstmt.setInt(1, contribuable.getIdRepresentant());
            pstmt.setString(2, contribuable.getNom());
            pstmt.setString(3, contribuable.getEmail());
            pstmt.setString(4, motPasse);
            pstmt.setInt(5, contribuable.getTelephone());
            pstmt.setString(6, contribuable.getBp());
            
            pstmt.executeUpdate();
            connection.close();

        }catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
        return model;
    }

    //************** find data by ID ***************************/
    public void findById(int contribuableId) {
        
        Contribuable contribuable = null;
        System.out.println(" findById() : Contribuable Id: " + contribuableId);
        
        /* Setting The Particular contribuable Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           String query = ""
                    + "SELECT contribuable.*, "
                    + "representant.nomRepresentant,"
                    + "representant.prenomRepresentant,"
                    + "representant.id as Representant_id "
                    + "FROM contribuable, representant "
                    + "WHERE contribuable.id_representant = representant.id "
                    + "AND contribuable.id = " + contribuableId ;
            
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                contribuable = new Contribuable();
                contribuable.setId(resultSet.getInt("id"));  
                contribuable.setNom(resultSet.getString("nom"));  
                //contribuable.setPrenom(resultSet.getString("prenom"));  
                contribuable.setEmail(resultSet.getString("email"));  
                contribuable.setMotPasse(resultSet.getString("motPasse"));  
                contribuable.setTelephone(resultSet.getInt("telephone"));  
                contribuable.setBp(resultSet.getString("BP"));  
                
                contribuable.setIdRepresentant(resultSet.getInt("Representant_id"));  
                contribuable.setNomRepresentant(resultSet.getString("nomRepresentant"));  
                contribuable.setPrenomRepresentant(resultSet.getString("prenomRepresentant")); 
                
            }
            
            sessionMap.put("contribuableMapped", contribuable);
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
	
    //************** update data ******************************/
    public void update(Contribuable contribuable){

        try {

            var query =" "
                    + "UPDATE contribuable "
                    + "SET "
                    + "id_representant = ?, "
                    + "nom = ?, "
                    + "email = ?, "
                    + "telephone = ?, "
                    + "BP = ? "
                    + "where id = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            pstmt.setInt(1, contribuable.getIdRepresentant());
            pstmt.setString(2, contribuable.getNom());
           // pstmt.setString(3, contribuable.getPrenom());
            pstmt.setString(3, contribuable.getEmail());
            pstmt.setInt(4, contribuable.getTelephone());
            pstmt.setString(5, contribuable.getBp());
            pstmt.setInt(6, contribuable.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int contribuableId) {
        
        System.out.println("delete() : Contribuable Id: " + contribuableId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM contribuable WHERE id = " + contribuableId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        
    }

    //************** error  message from sql ***********************/
    public void printSQLException(SQLException ex) {
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