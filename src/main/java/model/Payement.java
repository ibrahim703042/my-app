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
    private String status;
    private double montantPaye;
    private Date datePayement;
    
    private Amande amande;
    private Integer idAmande;
    //private Quittance  quittance;
    //private int  idQuittance;

    public Payement() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Amande getAmande() {
        return amande;
    }

    public void setAmande(Amande amande) {
        this.amande = amande;
    }

    public Integer getIdAmande() {
        return idAmande;
    }

    public void setIdAmande(Integer idAmande) {
        this.idAmande = idAmande;
    }

    
}
