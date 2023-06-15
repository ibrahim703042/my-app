/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.*;
import jakarta.inject.Inject;
import java.io.Serializable;
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

public class ProvinceController extends MessageController implements Serializable{

    public ArrayList provinces;
    private Province province;
    
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
            ex.printStackTrace();
        }
        return provinces;
    }
     
    public String save(Province province) {
        
        try {
            
            provinceDbUtil.save(province);
            this.showInfo("Inserted", "Data Inserted");
            this.init();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }
     
    public String edit(int id) {
        try {
            
            provinceDbUtil.findById(id);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/pays/province/edit.xhtml?faces-redirect=true";
    }
     
    public String update(Province province) {
        
        try {
            
            provinceDbUtil.update(province);
            this.showInfo("Updated", "Data updeted");
//            this.init();


        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }
     
    public void delete(int id) {
        
        try {
         provinceDbUtil.delete(id);
            this.showInfo("Deleted", "Data deleted");
            this.init();


        }catch (Exception ex) {
            ex.printStackTrace();
        }
//        return "/pages/pays/province/template.xhtml?faces-redirect=true";
    }
    

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}	
