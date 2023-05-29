/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Ibrahim
 */

import dbconnection.MySQLJDBCUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import java.sql.*;
import java.util.*;
import model.Immeuble;

@ManagedBean
@ApplicationScoped

public class ImmeubleDbUtil {
    
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement pstmt;

    //*************************** display data *****************/
    public static  ArrayList findAll() {
        
        ArrayList administrateurList = new ArrayList();
        
        try {
            String query = 
                    "SELECT "
                    + "contribuable.id As Contribuable_ID, "
                    + "contribuable.nom As nom_contribuable, "
                    + "contribuable.prenom AS prenom_contribuable, "
                    + "contribuable.email AS email_contribuable, "
                    + "contribuable.telephone AS tel_contribuable, "
                    + "contribuable.BP AS BP_contribuable, "
                    + "representant.id As representant_ID, "
                    + "representant.nomRepresentant As nom_representant, "
                    + "representant.prenomRepresentant AS prenom_representant, "
                    + "representant.emailRepresentant AS email_representant, "
                    + "representant.telephoneRepresentant AS tel_representant, "
                    + "representant.bpRepresentant AS BP_representant, "
                    + "immeuble.id as Immeuble_ID, "
                    + "immeuble.nomAvenue as Rue , "
                    + "colline.nomColline as Colline,  "
                    + "commune.nomCommune as Commune "
                    + "FROM representant "
                    + "JOIN contribuable "
                    + "ON contribuable.id_representant = representant.id "
                    + "JOIN immeuble "
                    + "ON immeuble.id_contribuable = contribuable.id "
                    + "JOIN colline "
                    + "ON immeuble.id_colline = colline.id "
                    + "JOIN commune ON colline.id_commune = commune.id ";

            // String query = "SELECT * FROM immeuble WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                Immeuble immeuble = new Immeuble();
                
                /* *******contribuable **** */
                
                immeuble.setId(resultSet.getInt("Immeuble_ID")); 
                immeuble.setIdContribuable(resultSet.getInt("Contribuable_ID"));  
                immeuble.setNomContribuable(resultSet.getString("nom_contribuable"));  
                immeuble.setPrenomContribuable(resultSet.getString("prenom_contribuable"));  
                immeuble.setNomColline(resultSet.getString("Colline"));  
                immeuble.setNomCommune(resultSet.getString("Commune"));  
                //immeuble.setNomProvince(resultSet.getString("Province"));  
                immeuble.setNomAvenue(resultSet.getString("Rue"));  
                immeuble.setTelephoneContribuable(resultSet.getString("tel_contribuable"));  
                immeuble.setBpContribuable(resultSet.getString("BP_contribuable"));  
                immeuble.setEmailContribuable(resultSet.getString("email_contribuable"));  
                
                /* *******representant **** */
                immeuble.setIdRepresentant(resultSet.getInt("Representant_ID"));  
                immeuble.setNomRepresentant(resultSet.getString("nom_representant"));  
                immeuble.setPrenomRepresentant(resultSet.getString("prenom_representant"));  
                immeuble.setNomColline(resultSet.getString("Colline"));  
                immeuble.setNomCommune(resultSet.getString("Commune"));  
                //immeuble.setNomProvince(resultSet.getString("Province"));  
                immeuble.setNomAvenue(resultSet.getString("Rue"));  
                immeuble.setTelephoneRepresentant(resultSet.getString("tel_representant"));
                immeuble.setBpRepresentant(resultSet.getString("BP_representant"));  
                immeuble.setEmailRepresentant(resultSet.getString("email_representant"));
                

                administrateurList.add(immeuble);  
            }   

            System.out.println("Total Records Fetched: " + administrateurList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
        return administrateurList;

    }
    
    //************** Save data **********************************/ 
    public void save(Immeuble immeuble){
        
        try {

            String query ="INSERT INTO immeuble (id_colline, id_contribuable, nomAvenue) values (?, ?, ?)";
            
            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);         

            pstmt.setInt(1, immeuble.getIdColline());
            pstmt.setInt(2, immeuble.getIdContribuable());
            pstmt.setString(3, immeuble.getNomAvenue());

            pstmt.executeUpdate();
            connection.close();

        }catch (NullPointerException e) {
            // handle the null value appropriately
            System.err.println("Caught NullPointerException: " + e.getMessage());
        } catch (SQLException e) {
            // handle the SQLException appropriately
            System.err.println("Caught SQLException: " + e.getMessage());
        } 
    }

    //*************************** find data by id *****************/
    public void findById(int immeubleId ) {
        
        Immeuble immeuble = null;
        System.out.println(" findById() : immeuble Id: " + immeubleId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        try {
            String query = 
                    "SELECT "
                    + "contribuable.id As Contribuable_ID, "
                    + "contribuable.nom As nom_contribuable, "
                    + "contribuable.prenom AS prenom_contribuable, "
                    + "contribuable.email AS email_contribuable, "
                    + "contribuable.telephone AS tel_contribuable, "
                    + "contribuable.BP AS BP_contribuable, "
                    + "immeuble.id as Immeuble_ID, "
                    + "immeuble.nomAvenue as Rue , "
                    + "colline.nomColline as Colline,  "
                    + "commune.nomCommune as Commune,  "
                    + "province.nomProvince as Province "
                    + "FROM contribuable "
                    + "JOIN immeuble "
                    + "ON immeuble.id_contribuable = "
                    + "contribuable.id JOIN colline "
                    + "ON immeuble.id_colline = colline.id "
                    + "JOIN commune ON colline.id_commune = commune.id "
                    + "JOIN province ON commune.id_province = province.id "
                    + "WHERE contribuable.id = " +immeubleId;

            // String query = "SELECT * FROM immeuble WHERE id IS NOT NULL ORDER BY id DESC";
            connection = MySQLJDBCUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                immeuble = new Immeuble();
                
                /* *******contribuable **** */
                
                immeuble.setId(resultSet.getInt("Immeuble_ID")); 
                immeuble.setIdContribuable(resultSet.getInt("Contribuable_ID"));  
                immeuble.setNomContribuable(resultSet.getString("nom_contribuable"));  
                immeuble.setPrenomContribuable(resultSet.getString("prenom_contribuable"));  
                immeuble.setNomColline(resultSet.getString("Colline"));  
                immeuble.setNomCommune(resultSet.getString("Commune"));  
                immeuble.setNomProvince(resultSet.getString("Province"));  
                immeuble.setNomAvenue(resultSet.getString("Rue"));  
                immeuble.setTelephoneContribuable(resultSet.getString("tel_contribuable"));  
                immeuble.setBpContribuable(resultSet.getString("BP_contribuable"));  
                immeuble.setEmailContribuable(resultSet.getString("email_contribuable"));  
                
                /* *******representant **** */
//                immeuble.setIdRepresentant(resultSet.getInt("Representant_ID"));  
//                immeuble.setNomRepresentant(resultSet.getString("nom_representant"));  
//                immeuble.setPrenomRepresentant(resultSet.getString("prenom_representant"));  
//                immeuble.setNomColline(resultSet.getString("Colline"));  
//                immeuble.setNomCommune(resultSet.getString("Commune"));  
//                immeuble.setNomProvince(resultSet.getString("Province"));  
//                immeuble.setNomAvenue(resultSet.getString("Rue"));  
//                immeuble.setTelephoneRepresentant(resultSet.getString("tel_representant"));
//                immeuble.setBpRepresentant(resultSet.getString("BP_representant"));  
//                immeuble.setEmailRepresentant(resultSet.getString("email_representant"));
                
            }   

            sessionMap.put("immeubleMapped", immeuble);
            connection.close();
            
        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }

    }
	
    //************** update data ******************************/
    public void update(Immeuble immeuble){

        try {

            String query =""
                    + "UPDATE immeuble "
                    + "SET "
                    + "id_colline = ?, "
                    + "id_contribuable = ?, "
                    + "nomAvenue = ? ";

            connection = MySQLJDBCUtil.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, immeuble.getIdColline());
            pstmt.setInt(2, immeuble.getIdContribuable());
            pstmt.setString(3, immeuble.getNomAvenue());
            
            pstmt.setInt(4,immeuble.getId());
            
            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            addErrorMessage(sqlException);
        }
        
    }

    //************** delete data ********************************/
    public void delete(int immeubleId) {
        
        connection = MySQLJDBCUtil.getConnection();
        System.out.println("delete() : Immeuble Id: " + immeubleId);

        try {

            String query = "DELETE FROM immeuble WHERE id = " + immeubleId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            addErrorMessage(sqlException);
        }
    }
    
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}