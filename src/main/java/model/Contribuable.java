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
 * 
 */

@ManagedBean

public class Contribuable implements Serializable {

    private Integer id;
    private String nom;
    private String prenom;
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
    
    private Date date;
   
    public Contribuable() {
    }

    public Contribuable(Integer id) {
        this.id = id;
    }

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    
}
