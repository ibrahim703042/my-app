/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Declaration;
import org.primefaces.PrimeFaces;
import util.DeclarationDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class DeclarationController extends DeclarationDbUtil implements Serializable {

    public List<Declaration> declarations;
    private Declaration declaration;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        declarations = DeclarationDbUtil.findAll();
    }

    public void clearForm(){
        this.declaration = new Declaration();
    }
    
    public static void autoNIF() {
        DeclarationDbUtil.autoNIF();
    }
    
    private boolean IsValid() {
        
        if (this.declaration.getIdImmeuble() == null)
        {
            showError("Required","Immeuble is required");
            return false;
        }
        if (this.declaration.getNif().isEmpty())
        {
            showError("Required","NIF is required");
            return false;
        }
        if (this.declaration.getCcf().isEmpty())
        {
            showError("Required","CCF is required");
            return false;
        }
//        if (this.declaration.getDate_1().equals(this))
//        {
//            showError("Required","Date is required");
//            return false;
//        }
//        if (this.declaration.getDate_2().before(1-06-2023))
//        {
//            showError("Required","Must be a future date");
//            return false;
//        }
        
        return true;
    }
   
    //************************ save data **************************/
    
    public void createOrUpdate() {
        
        if(IsValid()){
            
            if(this.declaration.getId() == null){
                
                DeclarationDbUtil.save(this.declaration);
                showInfo("Inserted","Data inserted");
                this.init();
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-declarations");
   
            }else if(this.declaration.getId() != null){
                DeclarationDbUtil.update(this.declaration);
                showInfo("Updated","Data Updated");
                this.init();
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-declarations");
   
            }
        }
    }
    
    //************************  edit data by Id  **************************/
    public String calculRevenu(int id) {
        DeclarationDbUtil.ViewById(id);
        return "/admin/declaration/declaration.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public void update() {
        DeclarationDbUtil.update(this.declaration);
        
    }
    
    ///************************ delete data **************************/
    public void delete() {
        
        DeclarationDbUtil.delete(this.declaration.getId());
        
    }
    
    /// ************************ Getters && Setters **************************/
    
    public Declaration getDeclaration() {
        return declaration;
    }

    public void setDeclaration(Declaration declaration) {
        this.declaration = declaration;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    
    
}
