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
import model.Contribuable;
import util.ContribuableDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@SessionScoped

public class ContribuableController implements Serializable{

    public ArrayList contribuables;
    
    @Inject
    private ContribuableDbUtil contribuableDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        contribuables = contribuableDbUtil.findAll();
    }

    public ArrayList contribuableList() {
        
        contribuables.clear();
        try {
            contribuables = contribuableDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return contribuables;
    }
        
    //************************ save data **************************/
    public String save(Contribuable contribuable) {
        
        try {
            contribuableDbUtil.save(contribuable);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
          
        return "/pages/contribuable/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            contribuableDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/contribuable/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Contribuable contribuable) {
        
        try {
            contribuableDbUtil.update(contribuable);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/contribuable/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            contribuableDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/contribuable/template.xhtml?faces-redirect=true";
    }
    
    
    //************** conecxt msg data ***********************/
    private static void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
     //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
