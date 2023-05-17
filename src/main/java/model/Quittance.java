/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Ibrahim
 */

public class Quittance  {

    private Integer id;
    private int idPayement;
    private Date dateQuittance;
    private int montantRestante;

    public Quittance() {
    }

    public Quittance(Integer id) {
        this.id = id;
    }

    public Quittance(Integer id, int idPayement, Date dateQuittance, int montantRestante) {
        this.id = id;
        this.idPayement = idPayement;
        this.dateQuittance = dateQuittance;
        this.montantRestante = montantRestante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdPayement() {
        return idPayement;
    }

    public void setIdPayement(int idPayement) {
        this.idPayement = idPayement;
    }

    public Date getDateQuittance() {
        return dateQuittance;
    }

    public void setDateQuittance(Date dateQuittance) {
        this.dateQuittance = dateQuittance;
    }

    public int getMontantRestante() {
        return montantRestante;
    }

    public void setMontantRestante(int montantRestante) {
        this.montantRestante = montantRestante;
    }

    
}
