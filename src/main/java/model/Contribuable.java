/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@RequestScoped
public class Contribuable implements Serializable {

    private Integer id;
    private String nom;
    @Email
    private String email;
    private String motPasse;
    private Integer telephone;
    private String bp;
    
    //** represantant **/
    private Integer idRepresentant;
    private String bpRepresentant;
    private String nomRepresentant;
    private String prenomRepresentant;
    private String telephoneRepresentant;
    private String EmailRepresentant;
    
    //** Abbattement **/
    private String beneficiaire;
    private String motif;
    private short motif_A;
    private short motif_B;
    private short motif_C;
    private short motif_D;
    private short motif_E;
    
    private Abbattement model;
    
    
    public Contribuable (){
        beneficiaire = "No";
        motif_A = 1;
    }
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    //    *****************REPRESENTANT**********************************
    
    public Integer getIdRepresentant() {
        return idRepresentant;
    }

    public void setIdRepresentant(Integer idRepresentant) {
        this.idRepresentant = idRepresentant;
    }

    public String getBpRepresentant() {
        return bpRepresentant;
    }

    public void setBpRepresentant(String bpRepresentant) {
        this.bpRepresentant = bpRepresentant;
    }

    public String getNomRepresentant() {
        return nomRepresentant;
    }

    public void setNomRepresentant(String nomRepresentant) {
        this.nomRepresentant = nomRepresentant;
    }

    public String getPrenomRepresentant() {
        return prenomRepresentant;
    }

    public void setPrenomRepresentant(String prenomRepresentant) {
        this.prenomRepresentant = prenomRepresentant;
    }

    public String getTelephoneRepresentant() {
        return telephoneRepresentant;
    }

    public void setTelephoneRepresentant(String telephoneRepresentant) {
        this.telephoneRepresentant = telephoneRepresentant;
    }

    public String getEmailRepresentant() {
        return EmailRepresentant;
    }

    public void setEmailRepresentant(String EmailRepresentant) {
        this.EmailRepresentant = EmailRepresentant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //    *****************ABBATTEMENT**********************************
    
    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
    
    
    public void setModel(Abbattement model) {
        this.model = model;
    }

    public Abbattement getModel() {
        return model;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getMotif() {
        return motif;
    }

    public short getMotif_A() {
        return motif_A;
    }

    public void setMotif_A(short motif_A) {
        this.motif_A = motif_A;
    }

    public short getMotif_B() {
        return motif_B;
    }

    public void setMotif_B(short motif_B) {
        this.motif_B = motif_B;
    }

    public short getMotif_C() {
        return motif_C;
    }

    public void setMotif_C(short motif_C) {
        this.motif_C = motif_C;
    }

    public short getMotif_D() {
        return motif_D;
    }

    public void setMotif_D(short motif_D) {
        this.motif_D = motif_D;
    }

    public short getMotif_E() {
        return motif_E;
    }

    public void setMotif_E(short motif_E) {
        this.motif_E = motif_E;
    }
    
}
