/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Role;
import util.RoleDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class RoleController implements Serializable{

    public ArrayList roles;
    
    @Inject
    private RoleDbUtil roleDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        roles = roleDbUtil.findAll();
    }

    public ArrayList roleList() {
        roles.clear();
        try {
            roles = roleDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return roles;
    }
        
    //************************ save data **************************/
    public void save(Role role) {
        try {
            roleDbUtil.save(role);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        //return "/pages/role/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            roleDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/role/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Role role) {
        try {
            roleDbUtil.update(role);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/role/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        try {
            roleDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/role/template.xhtml?faces-redirect=true";
    }
    
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
