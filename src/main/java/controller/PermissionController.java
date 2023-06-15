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
public class PermissionController extends MessageController implements Serializable {
    
    private List<Permission> permissions;
    private Permission permission;
    
    private List<Administrateur> administrateurs;
    private Administrateur administrateur;
    
    @Inject
    private PermissionDbUtil permissionDbUtil;

    //************************ display data **************************/
    @PostConstruct
    public void init() {
        this.permissions = this.permissionDbUtil.findAll();
        this.administrateurs = this.permissionDbUtil.loadDropDown();
    }
     
    public void clearForm(){
        this.permission = new Permission();
    }
    public void createOrUpdatePermission() {
        
        if (this.permission.getId() == null){

            this.permissionDbUtil.save(this.permission);
            this.init();
            showInfo("Permission","Privilegies granted");
            PrimeFaces.current().executeScript("PF('managePermissionDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-permissions");
        
        }else if (this.permission.getId() != null){

            this.permissionDbUtil.update(this.permission);
            this.init();
            showInfo("Permission","Privilegies updated");
            PrimeFaces.current().executeScript("PF('managePermissionDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-permissions");

        }
    }
    
    // ************************ delete data **************************/
    
    public void delete(Integer id) {
        this.permissionDbUtil.delete(id);
        this.init();
        showInfo("Deleted","Data deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-permissions");
    }

    // ************************ Getters And setters **************************/

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
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
   
    
}
