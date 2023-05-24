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
import java.sql.SQLException;
import java.util.ArrayList;
import model.Commune;
import util.CommuneDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class CommuneController {

    public ArrayList communes;
    
    @Inject
    private CommuneDbUtil communeDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        communes = communeDbUtil.findAll();
    }

    public ArrayList communeList() {
        
        communes.clear();
        try {
            communes = communeDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return communes;
    }
        
    //************************ save data **************************/
    public String save(Commune c) {
        
        try {
            communeDbUtil.save(c);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/commune/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            communeDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/commune/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Commune c) {
        
        try {
            communeDbUtil.update(c);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/commune/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            communeDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/commune/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
