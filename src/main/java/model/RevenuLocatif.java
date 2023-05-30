/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.event.ValueChangeEvent;
import java.io.Serializable;


/**
 *
 * @author Ibrahim
 */
@ManagedBean
@RequestScoped
public class RevenuLocatif implements Serializable {

    private Integer id;
    private double loyerExonere;
    private double loyerImposable;
    private double chargeIncombat;
    //chargeIncombat + loyerImposable
    private double revenuBrut;
    //revenuBrut * 40%
    private double deductionDepenses;
    private double interetEmprunt;
    //revenuBrut-deduction-interetEmprunt
    private double revenuNetImposable;
    private Immeuble idImmeuble ;
    
    public void changeListener(ValueChangeEvent event) {
        String newValue = (String) event.getNewValue();
        // Perform some logic to update the value of input2
        
        revenuBrut = (chargeIncombat + loyerImposable);
        deductionDepenses = (revenuBrut*40)/100;
        revenuNetImposable = (revenuBrut-deductionDepenses-interetEmprunt) ;
        
        String input2 = newValue + " updated";
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

    public Immeuble getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdImmeuble(Immeuble idImmeuble) {
        this.idImmeuble = idImmeuble;
    }
        
}
