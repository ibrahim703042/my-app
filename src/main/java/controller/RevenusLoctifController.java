/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.RevenusLocatif;
import util.RevenusLocatifDbUtil;

/**
 *
 * @author Ibrahim
 */

public class RevenusLoctifController  implements Serializable{
    
    private RevenusLocatif modelRevenusLocatif;
    private List<RevenusLocatif> revenusLocatifList;
    
    @Inject
    private RevenusLocatifDbUtil revenusLocatifDbUtil;
    
    @PostConstruct
    public void init(){
        revenusLocatifList = revenusLocatifDbUtil.findAll();
    }

    
    
    // ************************** CALCUL ************************************************/
    
    public Double totalRevenulocatifRevenubrut() {
        return (this.modelRevenusLocatif.getLoyerImposable() + this.modelRevenusLocatif.getChargeIncombat());
    }
    
    public Double totalDeductionDepensesEntretien() {
        return (totalRevenulocatifRevenubrut() * 40)/100;
    }
    
    public Double totalRevenuLocatifRevenunetImposable() {
        return (totalRevenulocatifRevenubrut()-totalDeductionDepensesEntretien() - this.modelRevenusLocatif.getInteretEmprunt());
    }
    
    public RevenusLocatif getModelRevenusLocatif() {
        return modelRevenusLocatif;
    }

    public void setModelRevenusLocatif(RevenusLocatif modelRevenusLocatif) {
        this.modelRevenusLocatif = modelRevenusLocatif;
    }

    
    // ************************** Getters et Setters ************************************************/
    public List<RevenusLocatif> getRevenusLocatifList() {
        return revenusLocatifList;
    }

    
    
}
