/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.Impot;
import util.ImpotDbUtil;

/**
 *
 * @author Ibrahim
 */
@ManagedBean
@SessionScoped
public class ImpotController implements Serializable {
    private List<Impot> impotList;
    private Impot impot;
    
    @Inject
   private ImpotDbUtil impotDbUtil; 
   
    @PostConstruct
    public void init(){
        this.impotList = impotDbUtil.findAll();
    }

    
// *************************** GETTERS & SETTERS ********************
    public List<Impot> getImpotList() {
        return impotList;
    }

    public Impot getImpot() {
        return impot;
    }

    public void setImpot(Impot impot) {
        this.impot = impot;
    }
    
    
}
