/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
/**
 *
 * @author Ibrahim
 */
public class Amande implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private int idImpot;
    private int amandeFixe;
    private double penalite;
    private int total;
    private Collection<Payement> payementCollection;

    public Amande() {
    }

    public Amande(Integer id) {
        this.id = id;
    }

    public Amande(Integer id, int idImpot, int amandeFixe, double penalite, int total) {
        this.id = id;
        this.idImpot = idImpot;
        this.amandeFixe = amandeFixe;
        this.penalite = penalite;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdImpot() {
        return idImpot;
    }

    public void setIdImpot(int idImpot) {
        this.idImpot = idImpot;
    }

    public int getAmandeFixe() {
        return amandeFixe;
    }

    public void setAmandeFixe(int amandeFixe) {
        this.amandeFixe = amandeFixe;
    }

    public double getPenalite() {
        return penalite;
    }

    public void setPenalite(double penalite) {
        this.penalite = penalite;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<Payement> getPayementCollection() {
        return payementCollection;
    }

    public void setPayementCollection(Collection<Payement> payementCollection) {
        this.payementCollection = payementCollection;
    }

}
