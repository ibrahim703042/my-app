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
import java.util.*;
import model.RevenuLocatif;
import util.RevenusDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class RevenusController implements Serializable {

    public List<RevenuLocatif> revenus;
    public RevenuLocatif model;
    public List<RevenuLocatif> listOfRevenus;
    
    @Inject
    private RevenusDbUtil revenuDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        revenus = revenuDbUtil.findAll();
        model = new RevenuLocatif();
        listOfRevenus = new ArrayList<>();
    }

    public List<RevenuLocatif> getRevenus() {
        return revenus;
    }

    public RevenuLocatif getModel() {
        return model;
    }

    public List<RevenuLocatif> getListOfRevenus() {
        return listOfRevenus;
    }
    
    public List<RevenuLocatif> revenuList() {
        
        revenus.clear();
        try {
            revenus = revenuDbUtil.findAll();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return revenus;
    }
    
    // ***********************Checked input Validation **************************
    public boolean hasSelectedRoles() {
        return this.listOfRevenus != null && !this.listOfRevenus.isEmpty();
    }
        
}
