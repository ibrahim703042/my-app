/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.inject.Named;

/**
 *
 * @author Ibrahim
 */

@Named
public class Abattement{

    private Integer id;
    private short beneficiaire;
    private String motif;
    private Contribuable idContribuable;

    public Abattement() {
    }

    public Abattement(Integer id) {
        this.id = id;
    }

    public Abattement(Integer id, short beneficiaire, String motif) {
        this.id = id;
        this.beneficiaire = beneficiaire;
        this.motif = motif;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(short beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Contribuable getIdContribuable() {
        return idContribuable;
    }

    public void setIdContribuable(Contribuable idContribuable) {
        this.idContribuable = idContribuable;
    }

}
