/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import model.Commune;
import util.CommuneDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class CommuneController extends MessageController implements Serializable {

    public ArrayList communes;
    
    @Inject
    private CommuneDbUtil communeDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        communes = communeDbUtil.findAll();
    }

    public ArrayList communeList() {
        
        communes.clear();
        try {
            communes = communeDbUtil.findAll();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return communes;
    }
        
    //************************ save data **************************/
    public String save(Commune c) {
        
        try {
            communeDbUtil.save(c);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/pays/commune/template.xhtml?faces-redirect=true";
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
        
        try {
            communeDbUtil.findById(id);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/pays/commune/edit.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Commune c) {
        
        try {
            communeDbUtil.update(c);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/pays/commune/template.xhtml?faces-redirect=true";
    }
    
    ///************************ delete data **************************/
    public void delete(int id) {
        
        try {
            communeDbUtil.delete(id);
            this.showInfo("Deleted", "Data deleted");
            this.init();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
//        return "/pages/pays/commune/template.xhtml?faces-redirect=true";
    }
    
}
