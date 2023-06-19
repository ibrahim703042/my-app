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
import model.Payement;
import org.primefaces.PrimeFaces;
import util.PayementDbUtil;

/**
 *
 * @author Ibrahim
 */
@ManagedBean
@SessionScoped

public class PayementController extends PayementDbUtil implements Serializable {
    
    private Payement modelPayement;
    private List<Payement> payementList;
    
    @Inject
    private PayementDbUtil payementDbUtil;
    
    @PostConstruct
    public void init(){
        this.setPayementList(payementDbUtil.findAll());
    }

    public void createOrUpdate(){
        
        if(this.modelPayement.getId() == null){
                
                payementDbUtil.save(this.modelPayement);
                showInfo("Inserted","Data inserted");
                this.init();
                PrimeFaces.current().executeScript("PF('manageContribuableDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-contribuables");
   
            }else if(this.modelPayement.getId() != null){
                payementDbUtil.update(this.modelPayement);
                showInfo("Updated","Data Updated");
                this.init();
                PrimeFaces.current().executeScript("PF('manageContribuableDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-contribuables");
   
            }
    }
    
    public Payement getModelPayement() {
        return modelPayement;
    }

    public void setModelPayement(Payement modelPayement) {
        this.modelPayement = modelPayement;
    }

    public List<Payement> getPayementList() {
        return payementList;
    }

    public void setPayementList(List<Payement> payementList) {
        this.payementList = payementList;
    }
    
    
}
