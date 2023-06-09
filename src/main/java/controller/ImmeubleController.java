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
import model.Immeuble;
import util.ImmeubleDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@SessionScoped

public class ImmeubleController implements Serializable{

    public ArrayList immeubles;
    
    @Inject
    private ImmeubleDbUtil immeubleDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        immeubles = immeubleDbUtil.findAll();
        //immeubles = immeubleDbUtil.getAllByImmeubleDeclaration();
    }

    public ArrayList immeubleList() {
        
        immeubles.clear();
        try {
            immeubles = immeubleDbUtil.findAll();
            //immeubles = immeubleDbUtil.getAllByImmeubleDeclaration();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return immeubles;
    }
        
    //************************ save data **************************/
    public String save(Immeuble immeuble) {
        
        try {
            immeubleDbUtil.save(immeuble);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/immeuble/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            immeubleDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/immeuble/edit.xhtml?faces-redirect=true";
    }
    
    //************************  details data by Id  **************************/
    public String detail(int id) {
        
        try {
            immeubleDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/immeuble/details.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Immeuble immeuble) {
        
        try {
            immeubleDbUtil.update(immeuble);
            
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/immeuble/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        try {
            immeubleDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/immeuble/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
