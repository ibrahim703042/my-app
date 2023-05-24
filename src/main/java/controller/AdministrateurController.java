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
import model.Administrateur;
import util.AdministrateurDbUtil;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@SessionScoped

public class AdministrateurController implements Serializable{

    public ArrayList administrateurs;
    
    @Inject
    private AdministrateurDbUtil administrateurDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        administrateurs = administrateurDbUtil.findAll();
    }

    public ArrayList administrateurList() {
        administrateurs.clear();
        try {
            administrateurs = administrateurDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return administrateurs;
    }
        
    //************************ save data **************************/
    public String save(Administrateur administrateur) {
        try {
            administrateurDbUtil.save(administrateur);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/admin/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            administrateurDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/admin/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Administrateur administrateur) {
        try {
            administrateurDbUtil.update(administrateur);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/admin/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        try {
            administrateurDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/admin/template.xhtml?faces-redirect=true";
    }
    
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
