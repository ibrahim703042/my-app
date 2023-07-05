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

public class PayementController extends MessageController implements Serializable {
    
    private Payement modelPayement;
    private List<Payement> payementList;
    
    @Inject
    private PayementDbUtil payementDbUtil;
    
    @PostConstruct
    public void init(){
        payementList = payementDbUtil.findAll();
    }

    private boolean IsValid(){
       
       if ( this.modelPayement.getMontantPaye() < 0 || this.modelPayement.getMontantPaye() == 0 )
       {
           showError("Failed","The amount must be greater than 0 ");
           return false;
       }
       
       return true;
   }
    
    public void createOrUpdate(){
        
        if(IsValid()){
            if(this.modelPayement.getId() == null){

                payementDbUtil.save(this.modelPayement);
                showInfo("Inserted","Data inserted");
                this.init();
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-payement");

            }else if(this.modelPayement.getId() != null){

                payementDbUtil.update(this.modelPayement);
                this.init();
                showInfo("Updated","Data Updated");
                PrimeFaces.current().executeScript("PF('manageFormDialog').hide()");
                PrimeFaces.current().ajax().update("form:messages", "form:dt-payement");

            }
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
        
}
