/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Administrateur;
import util.AdministrateurDbUtil;

/**
 *
 * @author Ibrahim
 */

@Named(value = "administrateurController")
//@ViewScoped
@SessionScoped


public class AdministrateurController {

    public ArrayList administrateurDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        administrateurDbUtil = AdministrateurDbUtil.findAll();
    }

    public ArrayList administrateurList() {
        return administrateurDbUtil;
    }
        
    //************************ save data **************************/
    public String save(Administrateur administrateur) {
        return AdministrateurDbUtil.save(administrateur);
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        return AdministrateurDbUtil.findById(id);
    }
    
    //************************ update data **************************/
    public String update(Administrateur administrateur) {
        return AdministrateurDbUtil.update(administrateur);
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        return AdministrateurDbUtil.delete(id);
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
