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
import static dbconnection.MySQLJDBCUtil.pstmt;
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

public class ContribuableDbUtil extends MySQLJDBCUtil {
    
    protected List<Representant> representants;
    protected Representant representant;
    
    protected List<Abbattement>  abbattements;
    protected Abbattement abbattement;
    
    private List<Contribuable> contribuableList;

    //*************************** display data *****************/
    public List<Contribuable> findAll() {
        
        contribuableList = new ArrayList();
        
        try {

            String query = ""
                    + "SELECT contribuable.*, "+ "abbattement.id_abbattement as Abbattement_ID, "
                    + "abbattement.beneficiaire, "
                    + "abbattement.motif_A, "
                    + "abbattement.motif_B, "
                    + "abbattement.motif_C, "
                    + "abbattement.motif_D, "
                    + "abbattement.motif_E "
                    + "FROM contribuable,  abbattement "
                    + "WHERE contribuable.id = abbattement.id_contribuable "
                    + "AND contribuable.id IS NOT NULL ORDER BY contribuable.id DESC";
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

               Contribuable contribuable = new Contribuable(); 

               contribuable.setId(resultSet.getInt("id"));  
               contribuable.setNom(resultSet.getString("nom"));  
               contribuable.setEmail(resultSet.getString("email"));  
               contribuable.setMotPasse(resultSet.getString("motPasse"));  
               contribuable.setTelephone(resultSet.getInt("telephone"));  
               contribuable.setBp(resultSet.getString("BP"));  
               contribuable.setDate(resultSet.getDate("date")); 
               
               contribuable.setIdRepresentant(resultSet.getInt("id_representant"));
               contribuable.setBeneficiaire(resultSet.getString("beneficiaire"));
               contribuable.setMotif_A(resultSet.getShort("motif_A"));
               contribuable.setMotif_B(resultSet.getShort("motif_B")); 
               contribuable.setMotif_C(resultSet.getShort("motif_C")); 
               contribuable.setMotif_D(resultSet.getShort("motif_D")); 
               contribuable.setMotif_E(resultSet.getShort("motif_E")); 
               
               
               

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
           
            
        }catch (SQLException ex) {
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
        
        String query_1 = ""
                + "INSERT INTO contribuable "
                + "(id_representant, nom, email, motPasse, telephone, BP) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        String query_2 = ""
                + "INSERT INTO abbattement "
                + "(id_contribuable,beneficiaire,motifAbbattement) "
                + "VALUES (?,?,?) ";
                
        try {

            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            
            pstmt = connection.prepareStatement(query_1, Statement.RETURN_GENERATED_KEYS);         

            String motPasse = DigestUtils.shaHex(contribuable.getMotPasse());
            //String admin = "Super administrateur";
            
            // Insert data into the first table
            pstmt.setInt(1, contribuable.getIdRepresentant());
            pstmt.setString(2, contribuable.getNom());
            pstmt.setString(3, contribuable.getEmail());
            pstmt.setString(4, motPasse);
            pstmt.setInt(5, contribuable.getTelephone());
            pstmt.setString(6, contribuable.getBp());
            pstmt.executeUpdate();
            
            // Retrieve last inserted id on contribuable table
            //resultSet.next();
            //int id_contribuable = resultSet.getInt(1);
            
            // Insert data into Abbattement table
//            pstmt = connection.prepareStatement(query_2);
//            pstmt.setInt(1, id_contribuable);
//            pstmt.setString(2, abbattement.getBeneficiaire());
//            pstmt.setString(3, Arrays.toString(abbattement.getMotifAbbattement()));
//            pstmt.executeUpdate();
//            
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            if (connection!= null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                   printSQLException(ex);
                }
            }
            printSQLException(e);
            
        }finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
}
