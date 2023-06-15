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
import model.Representant;
import org.primefaces.PrimeFaces;
import util.RepresentantDbUtil;

/**
 *
 * @author Ibrahim
 */


@ManagedBean
@SessionScoped

public class RepresentantController extends MessageController implements Serializable{

    private List< Representant>  representantList;
    private Representant model;
    
    @Inject
    private RepresentantDbUtil representantDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        this.representantList = representantDbUtil.findAll();
        
    }
    
    public void clearForm() {
        this.model = new Representant();
    }
    
    
    // ******   Input Validation ******/
    private boolean IsValid(){
        
        if (this.model.getNomRepresentant().isEmpty())
        {
            showError("Required","First name is required");
            return false;
        }
        if (this.model.getPrenomRepresentant().isEmpty())
        {
            showError("Required","Last name is required");
            return false;
        }
        if (this.model.getEmailRepresentant().isEmpty())
        {
            showError("Required","Email is required");
            return false;
        }
        if (this.model.getBpRepresentant().isEmpty())
        {
            showError("Required","B.p is required");
            return false;
        }
        return true;
    }
    
    public void createOrUpdate() {
        
        if(IsValid()){
            
            if (this.model.getId() == null) {

                representantDbUtil.save(this.model);
                this.init();
                showInfo("Inserted","Data Added");
                PrimeFaces.current().executeScript("PF('manageRepresentantDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-representants");
                
            }else if (this.model.getId() != null) {
                representantDbUtil.update(this.model);
                //warningMessage("Exist","Data already exist!!!");
                this.init();
                showInfo("Updated","Data updated");
                PrimeFaces.current().executeScript("PF('manageRepresentantDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-representants");
            } 
        }

    }
    
    
    ///************************ delete data **************************/
    public void delete(int id) {
        representantDbUtil.delete(id);
        this.init();
        showInfo("Deleted","Data Deleted");
        PrimeFaces.current().executeScript("PF('manageRepresentantDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-representants");
   
    }
    
    //*********************Getters && setters**********************************/
    
    public List< Representant> getRepresentantList() {
        return representantList;
    }

    public void setRepresentantList(List< Representant> representantList) {
        this.representantList = representantList;
    }

    public Representant getModel() {
        return model;
    }

    public void setModel(Representant model) {
        this.model = model;
    }
    
}
