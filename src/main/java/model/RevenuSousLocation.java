/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import java.util.List;

/**
 *
 * @author Ibrahim
 * 
 * 
 */

@ManagedBean(name = "revenuSousLocation")
@RequestScoped
public class RevenuSousLocation {
    
    private Integer id;
    private double recetteSousLocation;
    private double loyerPayes;
    private double revenusNetsImposables;
    private double totalRevenusNetsImposables;
    private double abbattements ;
    private double revenuNetImposable;
    private RevenuLocatif revenuLocatif;
    
    private List <RevenuLocatif> ListRevenuLocatif;
    
    
    public RevenuSousLocation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getRecetteSousLocation() {
        return recetteSousLocation;
    }

    public void setRecetteSousLocation(double recetteSousLocation) {
        this.recetteSousLocation = recetteSousLocation;
    }

    public double getLoyerPayes() {
        return loyerPayes;
    }

    public void setLoyerPayes(double loyerPayes) {
        this.loyerPayes = loyerPayes;
    }

    public double getRevenusNetsImposables() {
        revenusNetsImposables = recetteSousLocation-loyerPayes;
        return revenusNetsImposables;
    }

    public void setRevenusNetsImposables(double revenusNetsImposables) {
        this.revenusNetsImposables = revenusNetsImposables;
    }

    public double getTotalRevenusNetsImposables() {
        revenuLocatif = new RevenuLocatif();
        totalRevenusNetsImposables = (revenuLocatif.getRevenuNetImposable()+revenusNetsImposables);
        return totalRevenusNetsImposables;
    }

    public void setTotalRevenusNetsImposables(double totalRevenusNetsImposables) {
        this.totalRevenusNetsImposables = totalRevenusNetsImposables;
    }

    public double getAbbattements() {
        return abbattements;
    }

    public void setAbbattements(double abbattements) {
        this.abbattements = abbattements;
    }

    public double getRevenuNetImposable() {
        revenuNetImposable = totalRevenusNetsImposables-abbattements;
        return revenuNetImposable;
    }

    public void setRevenuNetImposable(double revenuNetImposable) {
        this.revenuNetImposable = revenuNetImposable;
    }

        
}
