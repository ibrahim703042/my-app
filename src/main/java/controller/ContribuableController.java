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

public class ContribuableController implements Serializable {

    public ArrayList contribuableDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        contribuableDbUtil = ContribuableDbUtil.findAll();
    }

    public ArrayList contribuableList() {
        return contribuableDbUtil;
    }
        
    
    //************************ save data **************************/
    public void save(Contribuable contribuable) {
        ContribuableDbUtil.save(contribuable);
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        return ContribuableDbUtil.findById(id);
    }
    
    //************************ update data **************************/
    public String update(Contribuable contribuable) {
        return ContribuableDbUtil.update(contribuable);
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        return ContribuableDbUtil.delete(id);
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
