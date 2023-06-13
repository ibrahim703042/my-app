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
import model.Permission;
import model.Role;

import org.primefaces.PrimeFaces;
import util.AdministrateurDbUtil;
import util.PermissionDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class AdminController extends MessageController implements Serializable{

    private List<Role> listRole;
    private Role modelRole;
    
    private List<Permission> ListPermission;
    private Permission modelPermission;
    
    private List<Administrateur> administrateurs;
    private Administrateur administrateur;
    private List<Administrateur> selectedAdministrateurs;
    
    @Inject
    private AdministrateurDbUtil administrateurDbUtil;
    private PermissionDbUtil permissionDbUtil;

    //************************ display data **************************/
    @PostConstruct
    public void init() {
        
        this.administrateurs = this.administrateurDbUtil.getAdministrateurs();
        this.listRole = this.administrateurDbUtil.loadDropDown();
        this.permissionDbUtil = new PermissionDbUtil();

    }
     
    public void clearForm() {
        this.setAdministrateur(new Administrateur());
    }
     
    public boolean hasSelectedAdministrateurs() {
        return this.selectedAdministrateurs() != null && !this.selectedAdministrateurs.isEmpty();
    }
    
    public List<Administrateur> selectedAdministrateurs() {
        this.setAdministrateurs(this.administrateurDbUtil.getAdministrateurs());
        return getAdministrateurs();
    }
        
    // ************************ create Admin **************************/
    
    public void createOrUpdate() {
        
        if (IsValid()) {
            if (this.getAdministrateur().getId() == null){

                this.administrateurDbUtil.save(this.administrateur);
                this.init();
                showInfo("Inserted","Data Added");
                PrimeFaces.current().executeScript("PF('manageAdminDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
            }

        }
    }
    
    // ************************ create Admin **************************/
    
    public void updatePermission() {
        
        if (this.getAdministrateur().getId() != null){

            this.permissionDbUtil.update(this.administrateur.getId());
            this.init();
            showInfo("Permission","Privilegies granted");
            PrimeFaces.current().executeScript("PF('managePermissionDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
        }

    }
    
    //************************ update data **************************/
    public void update() {
        
        if (IsValid()) {
            if (this.getAdministrateur().getId() != null) {

                this.administrateurDbUtil.update(this.getAdministrateur());
                this.init();
                showInfo("Updated","Data updated");
                PrimeFaces.current().executeScript("PF('manageEditAdminDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
            
            }else{
                showError("Failed","Operation Failed");
                PrimeFaces.current().ajax().update("form:messages", "form:dialogs-edit-admin:manage-edit-admin-content");
            } 
            
        }else{
            showError("Failed","All field is required");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs-admin:manage-admin-content");
        
        }
    }
    
    // ************************ delete data **************************/
    
    public void delete(Integer id) {
        this.administrateurDbUtil.delete(id);
        this.init();
        showInfo("Deleted","Data deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
    }
    
    
   private boolean IsValid(){
       
       if (this.getAdministrateur().getNom().isEmpty())
       {
           showError("Failed","First name is required");
           return false;
       }
       if (this.getAdministrateur().getPrenom().isEmpty() )
       {
           showError("Failed","Last name is required");
           return false;
       }
       if (this.getAdministrateur().getEmail().isEmpty())
       {
           showError("Failed","E-mail is required");
           return false;
       }
       if (this.getAdministrateur().getMotPasse().isEmpty())
       {
           showError("Failed","Password is required");
           return false;
       }
       if (this.getAdministrateur().getBp().isEmpty())
       {
           showError("Failed","B.p is required");
           return false;
       }
       if (this.getAdministrateur().getTelephone() == null)
       {
           showError("Failed","Phone number is required");
           return false;
       }
//       if (this.getAdministrateur().getRoleId() == null)
//       {
//           showError("Failed","Role is required");
//           return false;
//       }
       if (this.getAdministrateur().getIsActive() == null)
       {
           showError("Failed","Status is required");
           return false;
       }
       
       return true;
   }

    
    // ************************************Getters And Setters ***************************************
    
    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole = listRole;
    }

    public Role getModelRole() {
        return modelRole;
    }

    public void setModelRole(Role modelRole) {
        this.modelRole = modelRole;
    }

    public List<Permission> getListPermission() {
        return ListPermission;
    }

    public void setListPermission(List<Permission> ListPermission) {
        this.ListPermission = ListPermission;
    }

    public Permission getModelPermission() {
        return modelPermission;
    }

    public void setModelPermission(Permission modelPermission) {
        this.modelPermission = modelPermission;
    }

    public List<Administrateur> getAdministrateurs() {
        return administrateurs;
    }

    public void setAdministrateurs(List<Administrateur> administrateurs) {
        this.administrateurs = administrateurs;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public List<Administrateur> getSelectedAdministrateurs() {
        return selectedAdministrateurs;
    }

    public void setSelectedAdministrateurs(List<Administrateur> selectedAdministrateurs) {
        this.selectedAdministrateurs = selectedAdministrateurs;
    }

    public PermissionDbUtil getPermissionDbUtil() {
        return permissionDbUtil;
    }
    
    
}

