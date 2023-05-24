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
import model.Colline;
import util.CollineDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class CollineController {

    public ArrayList collines;
    
    @Inject
    private CollineDbUtil collineDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        collines = collineDbUtil.findAll();
    }

    public ArrayList collineList() {
        
        collines.clear();
        try {
            collines = collineDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return collines;
    }
        
    //************************ save data **************************/
    public String save(Colline c) {
        
        try {
            collineDbUtil.save(c);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/colline/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            collineDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/colline/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Colline c) {
        
        try {
            collineDbUtil.update(c);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/colline/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            collineDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/colline/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
