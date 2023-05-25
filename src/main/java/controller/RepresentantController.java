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
import model.Representant;
import util.RepresentantDbUtil;

/**
 *
 * @author Ibrahim
 */


@ManagedBean
@SessionScoped

public class RepresentantController implements Serializable{

    public ArrayList representants;
    
    @Inject
    private RepresentantDbUtil representantDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        representants = representantDbUtil.findAll();
    }

    public ArrayList representantList() {
        representants.clear();
        try {
            representants = representantDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        
        return representants;
    }
    
    //************************ save data **************************/
    public String save(Representant representant) {
        
        try {
            representantDbUtil.save(representant);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        
        return "/pages/representant/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {

        try {
            representantDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/representant/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Representant representant) {
        
        try {
            representantDbUtil.update(representant);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        
        return "/pages/representant/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {

        try {
            representantDbUtil.delete(id);
            
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/representant/template.xhtml?faces-redirect=true";
    }
        
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
