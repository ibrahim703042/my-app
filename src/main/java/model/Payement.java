/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ibrahim
 */

public class Payement implements Serializable {

    private Integer id;
    private String modePayement;
    private double montantPaye;
    private Date datePayement;
    
    private Amande idAmande;

    public Payement() {
    }

    public Payement(Integer id) {
        this.id = id;
    }

    public Payement(Integer id, String modePayement, double montantPaye, Date datePayement) {
        this.id = id;
        this.modePayement = modePayement;
        this.montantPaye = montantPaye;
        this.datePayement = datePayement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModePayement() {
        return modePayement;
    }

    public void setModePayement(String modePayement) {
        this.modePayement = modePayement;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Date getDatePayement() {
        return datePayement;
    }

    public void setDatePayement(Date datePayement) {
        this.datePayement = datePayement;
    }

    public Amande getIdAmande() {
        return idAmande;
    }

    public void setIdAmande(Amande idAmande) {
        this.idAmande = idAmande;
    }

   
}
