/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;
/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Amende implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Integer idImpot;
    private double baseLine;
    private double amandeFixe;
    private double penalite;
    private double total;
    
    private Impot impot;
    
    public Amende() {
        amandeFixe=200;
        penalite=200;
        total=200;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdImpot() {
        return idImpot;
    }

    public void setIdImpot(Integer idImpot) {
        this.idImpot = idImpot;
    }

    public double getBaseLine() {
        return baseLine ;
    }

    public void setBaseLine(double baseLine) {
        this.baseLine = baseLine;
    }

    public double getAmandeFixe() {
        return amandeFixe;
    }

    public void setAmandeFixe(double amandeFixe) {
        this.amandeFixe = amandeFixe;
    }

    public double getPenalite() {
        return penalite;
    }

    public void setPenalite(double penalite) {
        this.penalite = penalite;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setImpot(Impot impot) {
        this.impot = impot;
    }

    public Impot getImpot() {
        return impot;
    }

    
}
