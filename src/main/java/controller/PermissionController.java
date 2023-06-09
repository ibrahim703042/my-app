/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import org.primefaces.PrimeFaces;
import util.PermissionDbUtil;

/**
 *
 * @author Ibrahim
 * 
 * 
 */

@ManagedBean
@SessionScoped
public class PermissionController extends MessageContoller implements Serializable {
    
    private List<Permission> permissions;
    private Permission selectedPermission;
    private List<Permission> selectedPermissions;
    
    private Administrateur administrateur;
    private Integer idAdministrateur;
    
    @Inject
    private PermissionDbUtil permissionDbUtil;

    //************************ display data **************************/
    @PostConstruct
    public void init() {
        this.permissions = this.permissionDbUtil.findAll();
        this.administrateur = new Administrateur();
        selectedPermission = new Permission();
    }
       
    public List<Permission> getPermissions() {
        return permissions;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public Integer getIdAdministrateur() {
        idAdministrateur = this.administrateur.getId();
        return idAdministrateur ;
    }

    public void setIdAdministrateur(Integer idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }
    
    public Permission getSelectedPermission() {
        return selectedPermission;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setSelectedPermission(Permission selectedPermission) {
        this.selectedPermission = selectedPermission;
    }

    public List<Permission> getSelectedPermissions() {
        return selectedPermissions;
    }

    public void setSelectedPermissions(List<Permission> selectedPermissions) {
        this.selectedPermissions = selectedPermissions;
    }
    
    public boolean hasSelectedPermissions() {
        return this.selectedPermissions != null && !this.selectedPermissions.isEmpty();
    }
    
    public void create() {
        
        if(hasSelectedPermissions()){
            
            if (this.selectedPermission.getId() != null) {

                this.permissionDbUtil.save(this.selectedPermission);
                this.init();
                showInfo("Inserted","Permission Added");
                PrimeFaces.current().executeScript("PF('managePermissionDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
                
            }else
            
                if (this.selectedPermission.getId() != null) {

                    //this.permissionDbUtil.update(this.selectedPermission.getId());
                    this.init();
                    showInfo("Updated","Permission Updated");
                    PrimeFaces.current().executeScript("PF('managePermissionDialog').hide()");
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
                
            
            } 
        }else{
            warningMessage("Required","Required");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs-permission:manage-permission-content");
        }

    }

    public void updatePermission() {
        
        if(hasSelectedPermissions()){
            
            if (this.selectedPermission.getId() != null) {

                //this.permissionDbUtil.update(this.selectedPermission.getId());
                this.init();
                showInfo("Updated","Permission Updated");
                PrimeFaces.current().executeScript("PF('managePermissionDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-administrateurs");
            } 
            
        }else{
            warningMessage("Required","Required");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs-permission");
        }

    }
    
    public void deleteSelectedPermissions() {
        //this.permissionDbUtil.delete(this.selectedPermission);
//        this.roles.removeAll(this.selectedRoles);
        this.init();
        this.selectedPermissions = null;
        showInfo("Deleted","Permission deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-administateurs");
        PrimeFaces.current().executeScript("PF('dtadministateurs').clearFilters()");
    }
    
}
