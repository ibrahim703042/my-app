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
import model.Role;
import org.primefaces.PrimeFaces;
import util.RoleDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class RoleController extends MessageContoller implements Serializable{

    private List<Role> roles;
    private Role selectedRole;
    private List<Role> selectedRoles;
    
    @Inject
    private RoleDbUtil roleDbUtil;

    //************************ display data **************************/
    @PostConstruct
    public void init() {
        this.roles = this.roleDbUtil.findAll();
    }
       
    public List<Role> getRoles() {
        return roles;
    }
    
    public Role getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Role selectedRole) {
        this.selectedRole = selectedRole;
    }

    public List<Role> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<Role> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }
    
    public void openNew() {
        this.selectedRole = new Role();
    }

    public void saveRole() {
        
        if(IsValid()){
            
            if (this.selectedRole.getId() == null) {

                this.roleDbUtil.save(this.selectedRole);
                this.init();
                showInfo("Inserted","Role Added");
                PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
                
            }else
                if (this.selectedRole.getId() != null) {

                    warningMessage("Exist","Data already exist!!!");
                   // PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
                
            
            } 
        }else{
            warningMessage("Required","Required");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs");
        }

    }
    public void updateRole() {
        
        if(IsValid()){
            
            if (this.selectedRole.getId() != null) {

                this.roleDbUtil.update(this.selectedRole);
                this.init();
                showInfo("Updated","Role Updated");
                PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
            }else{
                showError("Exist","Data exist");
                PrimeFaces.current().ajax().update("form:messages", "form:dialogs");
            } 
            
        }else{
            warningMessage("Invalid","Invalide data!!!");
            PrimeFaces.current().ajax().update("form:messages", "form:dialogs");
        }

    }
    
    // ******   Input Validation ******/
    private boolean IsValid(){
        if (this.selectedRole.getNomRole().isEmpty())
        {
            showError("Failed","Role name is required");
            return false;
        }
        if (this.selectedRole.getDescription().isEmpty())
        {
            showError("Failed","Description is required");
            return false;
        }
        return true;
    }
    
    // ******   Delete data ******/
    public void deleteRole() {
        this.roleDbUtil.delete(this.selectedRole);
        this.selectedRole = null;
        showInfo("Deleted","Role deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
    }

    public String getDeleteButtonMessage() {
        
        if (hasSelectedRoles()) {
            int size = this.selectedRoles.size();
            return size > 1 ? size + " roles selected" : "1 role selected";
        }

        return "Delete";
    }

    public boolean hasSelectedRoles() {
        return this.selectedRoles != null && !this.selectedRoles.isEmpty();
    }
    
    public void deleteSelectedRoles() {
        this.roleDbUtil.delete(this.selectedRole);
        this.init();
        this.selectedRoles = null;
        showInfo("Deleted","Role deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
        PrimeFaces.current().executeScript("PF('dtRoles').clearFilters()");
    }
    
}
