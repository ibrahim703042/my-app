/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.Colline;
import org.primefaces.PrimeFaces;
import util.CollineDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class CollineController extends MessageController implements Serializable {

    private List<Colline> collines;
    private Colline colline;
    
    
    @Inject
    private CollineDbUtil collineDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        collines = collineDbUtil.findAll();
        clearForm();
    }
    
    public void clearForm(){
       this.colline = new Colline();
    }
    
    public void createOrUpdate() {
        
        if(IsValid()){
            
            if (this.colline.getId() == null) {

                this.collineDbUtil.save(this.colline);
                showInfo("Inserted","Data Added");
                this.init();
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-collines");
                
            }else if (this.colline.getId() != null) {

                this.collineDbUtil.update(this.colline);
                this.init();
                showInfo("Updated","Data Updated");
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-collinees");
               
            } 
        }

    }
    
    // ******   Input Validation ******/
    private boolean IsValid(){
        if (this.colline.getNomColline().isEmpty())
        {
            showError("Required","Name is required");
            return false;
        }
        if (this.colline.getIdCommune() == null)
        {
            showError("Required","Name is required");
            return false;
        }
        
        return true;
    }
    
    ///************************ delete data **************************/
    public void delete(int id) {
        
        collineDbUtil.delete(id);
        this.showInfo("Deleted", "Data deleted");
        this.init();

    }

    public List<Colline> getCollines() {
        return collines;
    }

    public Colline getColline() {
        return colline;
    }
   
    public void setColline(Colline colline) {
        this.colline = colline;
    }


    
}
