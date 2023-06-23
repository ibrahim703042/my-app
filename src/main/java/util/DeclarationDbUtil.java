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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import model.Declaration;
import java.util.*;


@ManagedBean
@ApplicationScoped

public class DeclarationDbUtil extends MySQLJDBCUtil {
    private Declaration declaration;
    
    //*************************** display data *****************/
    public ArrayList findAll() {
        
        ArrayList declarationList = new ArrayList();
        
        try {
            
            String query = ""
                    + "SELECT "
                    + "contribuable.nom AS Contribuable, "
                    + "immeuble.id AS Immeuble_id, "
                    + "declaration.* "
                    + "FROM contribuable, declaration, immeuble "
                    + "WHERE contribuable.id = immeuble.id_contribuable "
                    + "AND immeuble.id = declaration.id_immeuble ORDER BY declaration.id DESC ";
                   
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                declaration = new Declaration(); 

                declaration.setId(resultSet.getInt("id"));  
                declaration.setIdImmeuble(resultSet.getInt("Immeuble_id"));  
                declaration.setContribuable(resultSet.getString("Contribuable"));  
                declaration.setNif(resultSet.getString("NIF"));  
                declaration.setCcf(resultSet.getString("CCF")); 
                declaration.setDate_1(resultSet.getDate("datePlutot")); 
                declaration.setDate_2(resultSet.getDate("datePlutard")); 
                 

                declarationList.add(declaration);  
            }   

            System.out.println("Total Records Fetched: " + declarationList.size());
            connection.close();

        } catch(SQLException sqlException) {  
            printSQLException(sqlException);
        }
        return declarationList;
    }

    //************** Save data **********************************/ 
    public void save(Declaration declaration){
        
        try {

            String query = ""
                    + "INSERT INTO declaration (id_immeuble, NIF, CCF, datePlutot, datePlutard) "
                    + "values (?, ?, ?, ?, ?)";
            
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);         

            Date date_1 = declaration.getDate_1();
            Date date_2 = declaration.getDate_2();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String mysqlDate1String = sdf.format(date_1);
            String mysqlDate2String = sdf.format(date_2);
            java.sql.Date datePlutot = java.sql.Date.valueOf(mysqlDate1String);
            java.sql.Date datePlutard = java.sql.Date.valueOf(mysqlDate2String);

            
            pstmt.setInt(1, declaration.getIdImmeuble());
            pstmt.setString(2, declaration.getNif());
            pstmt.setString(3, declaration.getCcf());
            pstmt.setDate(4, datePlutot);
            pstmt.setDate(5, datePlutard);

            pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();

        }catch (SQLException e) {
            // handle the exception
            printSQLException(e);
        }
    }

    //************** find data by ID ***************************/
    public void findById(int declarationId) {
        
        declaration = null;
        System.out.println(" findById() : Immeuble Id: " + declarationId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        try {
           
            String query = ""
                    + "SELECT "
                    + "immeuble.id AS Immeuble_id, "
                    + "declaration.* "
                    + "FROM immeuble, declaration "
                    + "WHERE immeuble.id = declaration.id_immeuble "
                    + "AND declaration.id =" + declarationId ;
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);    
            
            if(resultSet.next()) {
                
                declaration = new Declaration();
                declaration.setId(resultSet.getInt("id"));  
                declaration.setIdImmeuble(resultSet.getInt("Immeuble_id"));  
                declaration.setNif(resultSet.getString("NIF"));  
                declaration.setCcf(resultSet.getString("CCF")); 
                declaration.setDate_1(resultSet.getDate("datePlutot")); 
                declaration.setDate_2(resultSet.getDate("datePlutard")); 

            }
            
            sessionMap.put("declarationMapped", declaration);
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
	
    //*************************** View data by id *****************/
    public void ViewById(int declarationId ) {
        
        declaration = null;
        System.out.println(" findById() : Declaration Id: " + declarationId);
        
        /* Setting The Particular province Details In Session */
        Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        
        try {
            String query =
            "SELECT "
            + "contribuable.id As Contribuable_ID, "
            + "contribuable.nom As nom_contribuable, "
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
            + "commune.nomCommune as Commune, "
            + "province.nomProvince as province, "
            + "declaration.* "
            + "FROM representant, declaration , contribuable, immeuble, colline, commune, province "
            + "WHERE contribuable.id_representant = representant.id "
            + "AND immeuble.id_contribuable = contribuable.id "
            + "AND immeuble.id_colline = colline.id "
            + "AND colline.id_commune = commune.id "
            + "AND declaration.id = " + declarationId ;
            

            // String query = "SELECT * FROM declaration WHERE id IS NOT NULL ORDER BY id DESC";
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) { 

                declaration = new Declaration();
                
                /* *******contribuable **** */
                
                declaration.setId(resultSet.getInt("id"));  
                declaration.setIdImmeuble(resultSet.getInt("Immeuble_ID"));  
                declaration.setNif(resultSet.getString("NIF"));  
                declaration.setCcf(resultSet.getString("CCF")); 
                declaration.setDate_1(resultSet.getDate("datePlutot")); 
                declaration.setDate_2(resultSet.getDate("datePlutard")); 
 
                declaration.setIdContribuable(resultSet.getInt("Contribuable_ID"));  
                declaration.setNomContribuable(resultSet.getString("nom_contribuable"));  
                declaration.setNomColline(resultSet.getString("Colline"));  
                declaration.setNomCommune(resultSet.getString("Commune"));  
                declaration.setNomProvince(resultSet.getString("Province"));  
                declaration.setNomAvenue(resultSet.getString("Rue"));  
                declaration.setTelephoneContribuable(resultSet.getString("tel_contribuable"));  
                declaration.setBpContribuable(resultSet.getString("BP_contribuable"));  
                declaration.setEmailContribuable(resultSet.getString("email_contribuable"));  
                
                /* *******representant **** */
               declaration.setIdRepresentant(resultSet.getInt("Representant_ID"));  
               declaration.setNomRepresentant(resultSet.getString("nom_representant"));  
               declaration.setPrenomRepresentant(resultSet.getString("prenom_representant"));  
               declaration.setNomColline(resultSet.getString("Colline"));  
               declaration.setNomCommune(resultSet.getString("Commune"));  
               declaration.setNomProvince(resultSet.getString("Province"));  
               declaration.setNomAvenue(resultSet.getString("Rue"));  
               declaration.setTelephoneRepresentant(resultSet.getString("tel_representant"));
               declaration.setBpRepresentant(resultSet.getString("BP_representant"));  
               declaration.setEmailRepresentant(resultSet.getString("email_representant"));
                
            }   

            sessionMap.put("declarationMapped", declaration);
            connection.close();
            
        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }

    }

    //************** update data ******************************/
    public void update(Declaration declaration){

        try {

            String query =""
                    + "UPDATE "
                    + "declaration SET "
                    + "id_immeuble = ?,"
                    + "NIF = ?, "
                    + "CCF = ?, "
                    + "datePlutot = ?, "
                    + "datePlutard = ? "
                    + "WHERE id = ? ";

            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(query);
            
            Date date_1 = declaration.getDate_1();
            Date date_2 = declaration.getDate_2();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String mysqlDate1String = sdf.format(date_1);
            String mysqlDate2String = sdf.format(date_2);
            java.sql.Date datePlutot = java.sql.Date.valueOf(mysqlDate1String);
            java.sql.Date datePlutard = java.sql.Date.valueOf(mysqlDate2String);

            
            pstmt.setInt(1, declaration.getIdImmeuble());
            pstmt.setString(2, declaration.getNif());
            pstmt.setString(3, declaration.getCcf());
            pstmt.setDate(4, datePlutot);
            pstmt.setDate(5, datePlutard);
            pstmt.setInt(6, declaration.getId());

            pstmt.execute();
            connection.close();

        } catch(SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    //************** delete data ********************************/
    public void delete(int declarationId) {
        
        System.out.println("delete() : declaration Id: " + declarationId);

        try {
            connection = dataSource.getConnection();
            String query = "DELETE FROM declaration WHERE id = " + declarationId ;
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();  
            connection.close();
            
        } catch(SQLException sqlException){
            printSQLException(sqlException);
        }
        
    }
     
    
    public void autoNIF() {
        
         String query = "SELECT Max(NIF) FROM declaration ";
                
           
        try {
            
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);  

            while(resultSet.next()) {
               declaration = new Declaration(); 

                //declaration.setId(resultSet.getInt("id")); 
               // declaration.setNif(resultSet.getString("NIF"));  
                //declaration.setCcf(resultSet.getString("CCF")); 

                resultSet.getString("MAX(NIF)");
                //resultSet.getString("MAX(CCF)");
                
                if(resultSet.getString("MAX(NIF)") == null){
                    declaration.setNif(("4000000001"));
                }
                else{
                    long id = Long.parseLong(resultSet.getString("MAX(NIF)").substring(2,resultSet.getString("MAX(NIF)").length()));
                    id++;
                    
                    declaration.setNif(("400000000" + String.format("%03d", id)));
                }
                
                //declarationList.add(declaration);
            }
            
            connection.close();

        } catch(SQLException sqlException) {  
            sqlException.printStackTrace();
        }
      }
    
}
