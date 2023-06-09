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
import model.Administrateur;
import model.Role;

import org.primefaces.PrimeFaces;
import util.AdministrateurDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class AdminController extends MessageContoller implements Serializable{

    private List<Role> roles;
    private Role role;
    
    private List<Administrateur> administrateurs;
    private Administrateur administrateur;
    private List<Administrateur> selectedAdministrateurs;
    
    @Inject
    private AdministrateurDbUtil administrateurDbUtil;

    //************************ display data **************************/
    @PostConstruct
    public void init() {
        this.administrateurs = this.administrateurDbUtil.getAdministrateurs();
        //this.roles = this.administrateurDbUtil.loadDropDown();

    }

    public List<Administrateur> getAdministrateurs() {
        return administrateurs;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public void setSelectedAdministrateurs(List<Administrateur> selectedAdministrateurs) {
        this.selectedAdministrateurs = selectedAdministrateurs;
    }

    public List<Administrateur> getSelectedAdministrateurs() {
        return selectedAdministrateurs;
    }

    public void clearForm() {
       this.administrateur = new Administrateur();
    }
     
    
    public boolean hasSelectedAdministrateurs() {
        return this.selectedAdministrateurs() != null && !this.selectedAdministrateurs.isEmpty();
    }
    
    
    public List<Administrateur> selectedAdministrateurs() {
        this.administrateurs = this.administrateurDbUtil.getAdministrateurs();
        return administrateurs;
    }
        
    //************************ save data **************************/
    
    public void create(Administrateur administrateur) {
        
        if (hasSelectedAdministrateurs()) {
            if (this.administrateur.getId() == null){

                administrateurDbUtil.save(administrateur);
                this.init();
                showInfo("Inserted","Data Added");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
            }

        }else{
            showError("Failed","All field is required");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs-admin:manage-admin-content");
        }
    }
    	
    //************************  edit data by Id  **************************/
    public void edit(Administrateur administrateur) {
        if (this.administrateur.getId() != null) {

            int id = this.administrateur.getId();
            this.administrateurDbUtil.findById(id);

        }else{
            this.init();
            showInfo("Find ID","Unable to find ID");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
        }
    }
    
    //************************ update data **************************/
    public void update() {
        
        if (hasSelectedAdministrateurs()) {
            if (this.administrateur.getId() != null) {

                this.administrateurDbUtil.update(this.administrateur);
                showInfo("Updated","Data updated");
                this.init();
                PrimeFaces.current().executeScript("PF('manageAdminDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
            
            }else{
                showError("Exist","Data exist");
                PrimeFaces.current().ajax().update("form:messages", "form:dialogs-admin:manage-admin-content");
            } 
            
        }else{
            showError("Failed","All field is required");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs-admin:manage-admin-content");
        
        }
    }
    
    ///************************ delete data **************************/
    
    public void delete() {
        if (this.administrateur.getId() != null) {

            //int id = this.administrateur.getId();
            this.administrateurDbUtil.delete(this.administrateur);
            PrimeFaces.current().executeScript("PF('manageAdminDialog').show()");

        }else{
            this.init();
            showInfo("Find ID","Unable to find ID");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
        }
    }
    
    
//    private boolean IsValid(){
//        
//        if (this.getAdministrateur().getNom().isEmpty() || this.getAdministrateur().getPrenom().isEmpty() )
//        {
//            showError("Failed","Fullname is required");
//            return false;
//        }
//        if (this.getAdministrateur().getBp().isEmpty())
//        {
//            showError("Failed","Description is required");
//            return false;
//        }
//        return true;
//    }
    
}

