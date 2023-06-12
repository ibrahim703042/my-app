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
import model.Declaration;
import util.DeclarationDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class DeclarationController implements Serializable {

    public ArrayList declarations;
    
    @Inject
    private DeclarationDbUtil declarationDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        declarations = declarationDbUtil.findAll();
    }

    public ArrayList declarationList() {
        
        declarations.clear();
        try {
            declarations = declarationDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return declarations;
    }
        
    //************************ save data **************************/
    public String save(Declaration declaration) {
        
        try {
            declarationDbUtil.save(declaration);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/declaration/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            declarationDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/declaration/edit.xhtml?faces-redirect=true";
    }
    
    //************************  edit data by Id  **************************/
    public String calculRevenu(int id) {
        
        try {
            declarationDbUtil.ViewById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/declaration/declaration.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Declaration c) {
        
        try {
            declarationDbUtil.update(c);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/declaration/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            declarationDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/declaration/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
