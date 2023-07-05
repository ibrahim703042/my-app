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
import model.Commune;
import org.primefaces.PrimeFaces;
import util.CommuneDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class CommuneController extends MessageController implements Serializable {

    private List<Commune> communes;
    private Commune commune;
    
    @Inject
    private CommuneDbUtil communeDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        communes = communeDbUtil.findAll();
    }

   public void clearForm(){
       this.setCommune(new Commune());
    }
    
    public void createOrUpdate() {
        
        if(IsValid()){
            
            if (this.getCommune().getId() == null) {

                this.communeDbUtil.save(this.getCommune());
                this.init();
                showInfo("Inserted","Data Added");
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-communes");
                
            }else if (this.getCommune().getId() != null) {

                this.communeDbUtil.update(this.getCommune());
                this.init();
                showInfo("Updated","Data Updated");
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-communes");
               
            } 
        }

    }
    
    // ******   Input Validation ******/
    private boolean IsValid(){
        if (this.getCommune().getNomCommune().isEmpty())
        {
            showError("Required","Name is required");
            return false;
        }
        if (this.getCommune().getIdProvince() == null)
        {
            showError("Required","Name is required");
            return false;
        }
        
        return true;
    }
    
    
    ///************************ delete data **************************/
    public void delete(int id) {
        
        try {
            communeDbUtil.delete(id);
            this.showInfo("Deleted", "Data deleted");
            this.init();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }
    
    
}
