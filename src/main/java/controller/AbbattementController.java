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
import model.Abbattement;
import util.AbbattementDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class AbbattementController implements Serializable {

    public ArrayList abbattements;
    
    @Inject
    private AbbattementDbUtil abbattementDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        abbattements = abbattementDbUtil.findAll();
    }

    public ArrayList abbattementList() {
        
        abbattements.clear();
        try {
            abbattements = abbattementDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return abbattements;
    }
        
    //************************ save data **************************/
    public String save(Abbattement abbattement) {
        
        try {
            abbattementDbUtil.save(abbattement);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/abbattement/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            abbattementDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/abbattement/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Abbattement abbattement) {
        
        try {
            abbattementDbUtil.update(abbattement);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/abbattement/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            abbattementDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/abbattement/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
