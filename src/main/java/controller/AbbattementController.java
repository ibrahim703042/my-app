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
import org.primefaces.PrimeFaces;
import util.AbbattementDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@SessionScoped

public class AbbattementController extends MessageController implements Serializable{
    
    private List< Abbattement>  abbattementList;
    private Abbattement modelAbbattement;
    
    @Inject
    private AbbattementDbUtil abbattementDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        abbattementList = abbattementDbUtil.findAll();
    }

    public void clearForm(){
        this.modelAbbattement = new Abbattement();
    }
        
    
    //************************ save data **************************/
    public void createOrUpdate() {
        
        if(IsValid()){
            
            if(this.modelAbbattement.getId() == null){
                
                abbattementDbUtil.save(this.modelAbbattement);
                this.init();
                PrimeFaces.current().executeScript("PF('manageContribuableDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-abbattement");
   
            }else if(this.modelAbbattement.getId() != null){
                abbattementDbUtil.update(this.modelAbbattement);
                this.init();
                PrimeFaces.current().executeScript("PF('manageContribuableDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-abbattement");
   
            }
        }
    }
    
    ///************************ delete data **************************/
    public void delete(int id) {
        
        abbattementDbUtil.delete(id);
        this.init();
        PrimeFaces.current().ajax().update("form:messages", "form:dt-abbattement");
    }
    
    
    //************** Getters && Setters ***********************/

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
    
    
    // ******   Input Validation ******/
    
    private boolean IsValid(){
        if (this.modelAbbattement.getIdContribuable() == null)
        {
            showError("Required","Full name is required");
            return false;
        }
        if (this.modelAbbattement.getBeneficiaire().isBlank())
        {
            showError("Required","Beneficiaire is required");
            return false;
        }
        if (this.modelAbbattement.getMotifAbbattement().isEmpty())
        {
            showError("Required","Motif is required");
            return false;
        }
        
        return true;
    }
    
}
