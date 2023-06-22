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
import model.RevenuSousLocation;
import util.RevenusSousLocationDbUtil;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@SessionScoped
public class RevenusSousLocationController implements Serializable {
    
    private RevenuSousLocation modelRevenuSousLocation;
    private List<RevenuSousLocation> listRevenuSousLocation;
    
    @Inject
    private RevenusSousLocationDbUtil revenusSousLocationDbUtil;
    
    @PostConstruct
    public void init(){
        this.listRevenuSousLocation = revenusSousLocationDbUtil.findAll();
    }
    
    // ************************** CALCUL ************************************************/
    
    public Double totalRevenuSousLocationRevenusNetsImposables() {
       double total = this.modelRevenuSousLocation.getRecetteSousLocation() - this.modelRevenuSousLocation.getLoyerPayes();
       return total;
    }

    public Double totalRevenuSousLocatifTotalRevenusNetsImposable() {
       //double total = (totalRevenuLocatifRevenunetImposable() + totalRevenuSousLocationRevenusNetsImposables());
       double total = (totalRevenuSousLocationRevenusNetsImposables() + totalRevenuSousLocationRevenusNetsImposables());
       return total;
    }
     
    public Double totalRevenuSousLocatifRevenuNetImposable() {
       double total = (totalRevenuSousLocatifTotalRevenusNetsImposable() - this.modelRevenuSousLocation.getAbbattements() );
       return total;
    }
    
    // ************************** Getters et Setters ************************************************/

    public RevenuSousLocation getModelRevenuSousLocation() {
        return modelRevenuSousLocation;
    }

    public void setModelRevenuSousLocation(RevenuSousLocation modelRevenuSousLocation) {
        this.modelRevenuSousLocation = modelRevenuSousLocation;
    }

    public List<RevenuSousLocation> getListRevenuSousLocation() {
        return listRevenuSousLocation;
    }
    
}
