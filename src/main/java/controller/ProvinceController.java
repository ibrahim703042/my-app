/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.*;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import model.Province;

import java.util.*;
import util.ProvinceDbUtil;

/**
 *
 * @author Ibrahim
 * 
 * 
 */


@ManagedBean
@SessionScoped

public class ProvinceController implements Serializable{

    public ArrayList provinces;
    
    @Inject
    private ProvinceDbUtil provinceDbUtil;
    
    @PostConstruct
    public void init(){
       provinces = provinceDbUtil.findAll();
    }
 
    public ArrayList provinceList() {
        
        provinces.clear();
        try {
            provinces = provinceDbUtil.findAll();
        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return provinces;
    }
     
    public String save(Province province) {
        
        try {
            
            provinceDbUtil.save(province);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }
     
    public String edit(int id) {
        try {
            
            provinceDbUtil.findById(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/province/edit.xhtml?faces-redirect=true";
    }
     
    public String update(Province province) {
        
        try {
            
            provinceDbUtil.update(province);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }
     
    public String delete(int id) {
        
        try {
         provinceDbUtil.delete(id);

        }catch (Exception ex) {
            addErrorMessage ((SQLException) ex);
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }
    
    //************** error  message from sql ***********************/
    private static void addErrorMessage(SQLException ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}	
