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
import model.Avenue;
import util.AvenueDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class AvenueController implements Serializable{

    public ArrayList avenues;
    
    @Inject
    private AvenueDbUtil avenueDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        avenues = avenueDbUtil.findAll();
    }

    public ArrayList avenueList() {
        
        avenues.clear();
        try {
            avenues = avenueDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return avenues;
    }
        
    //************************ save data **************************/
    public String save(Avenue a) {
        
        try {
            avenueDbUtil.save(a);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/avenue/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            avenueDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/avenue/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Avenue a) {
        
        try {
            avenueDbUtil.update(a);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/avenue/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            avenueDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/avenue/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
