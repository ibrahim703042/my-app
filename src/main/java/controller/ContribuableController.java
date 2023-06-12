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
import model.Abbattement;
import model.Contribuable;
import model.Representant;
import org.primefaces.PrimeFaces;
import util.ContribuableDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@SessionScoped

public class ContribuableController extends MessageController implements Serializable{

    private List< Representant>  representantList;
    private Representant modelReperesentant;
    
    private List< Contribuable>  contribuableList;
    private Contribuable modelContribuable;
    
    private List< Abbattement>  abbattementList;
    private Abbattement modelAbbattement;
    
    @Inject
    private ContribuableDbUtil contribuableDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        this.contribuableList = contribuableDbUtil.findAll();
        this.representantList = contribuableDbUtil.loadDropDown();
        this.modelContribuable = new Contribuable();
        this.modelAbbattement = new Abbattement();
    }

    public List<Contribuable> contribuableList() {
        
        contribuableList.clear();
        try {
            contribuableList = contribuableDbUtil.findAll();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return contribuableList;
    }
        
    //************************ save data **************************/
    public void create(Contribuable contribuable) {
        
        if(IsValid()){
            
            if(this.modelContribuable.getId() == null){
                
                contribuableDbUtil.save(contribuable);
                showInfo("Inserted","Data inserted");
                this.init();
    //          PrimeFaces.current().executeScript("PF('manageRepresentantDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-contribuables");
   
            }else if(this.modelContribuable.getId() != null){
                contribuableDbUtil.update(contribuable);
                showInfo("Updated","Data Updated");
                this.init();
    //          PrimeFaces.current().executeScript("PF('manageRepresentantDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-contribuables");
   
            }
        }
          //return "/pages/contribuable/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            contribuableDbUtil.findById(id);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/contribuable/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Contribuable contribuable) {
        try {
            contribuableDbUtil.update(contribuable);
            showInfo("Updated","Data Updated");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-contribuables");
   

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/contribuable/template.xhtml?faces-redirect=true";
    }
    
     
    ///************************ delete data **************************/
    public void delete(int id) {
        
        contribuableDbUtil.delete(id);
        this.init();
        showInfo("Deleted","Data Deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-contribuables");
   
    }
    
    
    //************** Getters && Setters ***********************/

    public List< Representant> getRepresentantList() {
        return representantList;
    }

    public void setRepresentantList(List< Representant> representantList) {
        this.representantList = representantList;
    }

    public Representant getModelReperesentant() {
        return modelReperesentant;
    }

    public void setModelReperesentant(Representant modelReperesentant) {
        this.modelReperesentant = modelReperesentant;
    }

    public List< Contribuable> getContribuableList() {
        return contribuableList;
    }

    public void setContribuableList(List< Contribuable> contribuableList) {
        this.contribuableList = contribuableList;
    }

    public Contribuable getModelContribuable() {
        return modelContribuable;
    }

    public void setModelContribuable(Contribuable modelContribuable) {
        this.modelContribuable = modelContribuable;
    }

    public List< Abbattement> getAbbattementList() {
        return abbattementList;
    }

    public void setAbbattementList(List< Abbattement> abbattementList) {
        this.abbattementList = abbattementList;
    }

    public Abbattement getModelAbbattement() {
        return modelAbbattement;
    }

    public void setModelAbbattement(Abbattement modelAbbattement) {
        this.modelAbbattement = modelAbbattement;
    }
    
    public boolean hasValue() {
        return this.contribuableList != null && !this.contribuableList.isEmpty();
    }
    
    // ******   Input Validation ******/
    
    private boolean IsValid(){
        if (this.modelContribuable.getNom().isEmpty())
        {
            showError("Required","Full name is required");
            return false;
        }
        if (this.modelContribuable.getEmail().isEmpty())
        {
            showError("Required","Email is required");
            return false;
        }
        if (this.modelContribuable.getMotPasse().isEmpty())
        {
            showError("Required","Password is required");
            return false;
        }
        if (this.modelContribuable.getTelephone() == null)
        {
            showError("Required","Email is required");
            return false;
        }
        if (this.modelContribuable.getBp().isEmpty())
        {
            showError("Required","B.p is required");
            return false;
        }
        
        return true;
    }
    
}
