/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.*;
import model.Province;

import java.util.*;
import util.ProvinceDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */


@ManagedBean
@SessionScoped

public class ProvinceController{

    public ArrayList provinceDbUtil;
    
    @PostConstruct
    public void init(){
       provinceDbUtil = ProvinceDbUtil.findAll();
    }
 
    public ArrayList provinceList() {
        return provinceDbUtil;
    }
     
    public String save(Province newProvince) {
        return ProvinceDbUtil.save(newProvince);
    }
     
    public String edit(int proId) {
        return ProvinceDbUtil.findById(proId);
    }
     
    public String update(Province updateProvince) {
        return ProvinceDbUtil.update(updateProvince);
    }
     
    public String delete(int proId) {
        return ProvinceDbUtil.delete(proId);
    }

	    
}	
