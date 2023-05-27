/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import java.io.Serializable;


/**
 *
 * @author Ibrahim
 */
@ManagedBean(name = "revenuLocatif")
@RequestScoped
public class RevenuLocatif implements Serializable {

    private Integer id;
    private double loyerExonere;
    private double loyerImposable;
    private double chargeIncombat;
    
    //chargeIncombat + loyerImposable
    private double revenuBrut = (chargeIncombat + loyerImposable);
    
    //revenuBrut * 40%
    private double deductionDepenses = (revenuBrut*40)/100 ;
    
    private double interetEmprunt;
    
    //revenuBrut-deduction-interetEmprunt
    private double revenuNetImposable = (revenuBrut-deductionDepenses-interetEmprunt) ;
    
    public RevenuLocatif() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLoyerExonere() {
        return loyerExonere;
    }

    public void setLoyerExonere(double loyerExonere) {
        this.loyerExonere = loyerExonere;
    }

    public double getLoyerImposable() {
        return loyerImposable;
    }

    public void setLoyerImposable(double loyerImposable) {
        this.loyerImposable = loyerImposable;
    }

    public double getChargeIncombat() {
        return chargeIncombat;
    }

    public void setChargeIncombat(double chargeIncombat) {
        this.chargeIncombat = chargeIncombat;
    }

    public double getRevenuBrut() {
        return revenuBrut;
    }

    public void setRevenuBrut(double revenuBrut) {
        this.revenuBrut = revenuBrut;
    }

    public double getDeductionDepenses() {
        return deductionDepenses;
    }

    public void setDeductionDepenses(double deductionDepenses) {
        this.deductionDepenses = deductionDepenses;
    }

    public double getInteretEmprunt() {
        return interetEmprunt;
    }

    public void setInteretEmprunt(double interetEmprunt) {
        this.interetEmprunt = interetEmprunt;
    }

    public double getRevenuNetImposable() {
        return revenuNetImposable;
    }

    public void setRevenuNetImposable(double revenuNetImposable) {
        this.revenuNetImposable = revenuNetImposable;
    }
    
    
}
