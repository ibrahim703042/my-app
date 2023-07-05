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
import org.primefaces.PrimeFaces;
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

    public List<Province> provinces;
    private Province province;
    
    @Inject
    private ProvinceDbUtil provinceDbUtil;
    
    @PostConstruct
    public void init(){
       provinces = provinceDbUtil.findAll();
    }
    
    public void clearForm(){
        this.province = new Province();
    }
    
    public void createOrUpdate() {
        
        if(IsValid()){
            
            if (this.province.getId() == null) {

                this.provinceDbUtil.save(this.province);
                this.init();
                showInfo("Inserted","Data Added");
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-provinces");
                
            }else if (this.province.getId() != null) {

                this.provinceDbUtil.update(this.province);
                this.init();
                showInfo("Updated","Data Updated");
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-provinces");
               
            } 
        }

    }
    
    // ******   Input Validation ******/
    private boolean IsValid(){
        if (this.province.getNomProvince().isEmpty())
        {
            showError("Failed"," Name is required");
            return false;
        }
        
        return true;
    }
    
    // ******   Delete data ******/
    public void delete(int id) {
        this.provinceDbUtil.delete(id);
        this.init();
        showInfo("Deleted","Data deleted");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-provinces");
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    
    public Province getProvince() {
        return province;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    
    
}	
