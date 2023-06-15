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

public class RoleController extends MessageController implements Serializable{
    
    private List<Role> roles;
    private Role role;
    private List<Role> listOfRoles;
    
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
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setListOfRoles(List<Role> listOfRoles) {
        this.listOfRoles = listOfRoles;
    }

    public List<Role> getListOfRoles() {
        return listOfRoles;
    }
    
    public void clearForm() {
        this.role = new Role();
    }

    public void createOrUpdate() {
        
        if(IsValid()){
            
            if (this.role.getId() == null) {

                this.roleDbUtil.save(this.role);
                this.init();
                showInfo("Inserted","Role Added");
                PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
                
            }else if (this.role.getId() != null) {

                this.roleDbUtil.update(this.role);
                this.init();
                showInfo("Updated","Role Updated");
                PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
               // warningMessage("Exist","Data already exist!!!");
               // PrimeFaces.current().executeScript("PF('manageRoleDialog').hide()");
                //PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");

            } 
        }

    }
    
    public boolean hasValue() {
        return this.listOfRoles != null && !this.listOfRoles.isEmpty();
    }
    
    // ******   Input Validation ******/
    private boolean IsValid(){
        if (this.role.getNomRole().isEmpty())
        {
            showError("Failed","Role name is required");
            return false;
        }
        if (this.role.getDescription().isEmpty())
        {
            showError("Failed","Description is required");
            return false;
        }
        return true;
    }
    
    // ******   Delete data ******/
    public void delete(int id) {
        this.roleDbUtil.delete(id);
        this.init();
        showInfo("Deleted","Role deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
    }

    
}
