/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Quittance implements Serializable {

    private Integer id;
    private double montantPaye;
    private Date dateQuittance;
    private double montantRestanteDu;
    private Integer idPayement;

    public Quittance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdPayement() {
        return idPayement;
    }

    public void setIdPayement(Integer idPayement) {
        this.idPayement = idPayement;
    }

    public double getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(double montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Date getDateQuittance() {
        return dateQuittance;
    }

    public void setDateQuittance(Date dateQuittance) {
        this.dateQuittance = dateQuittance;
    }

    public double getMontantRestanteDu() {
        return montantRestanteDu;
    }

    public void setMontantRestanteDu(double montantRestanteDu) {
        this.montantRestanteDu = montantRestanteDu;
    }
        
}
