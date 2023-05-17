/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;

/**
 *
 * @author Ibrahim
 */
@Named
@RequestScoped
public class Administrateur {

    
    private Integer id;
    
    @NotNull
    @Size(min = 1, max = 20)
    private String nom;
    
    @NotNull
    @Size(min = 1, max = 20)
    private String prenom;
    
    @NotNull
    @Size(min = 1, max = 255)
    private String email;
    
    @NotNull
    @Size(min = 1, max = 255)
    private String motPasse;
    
    @NotNull
    private int telephone;
    
    @NotNull
    @Size(min = 1, max = 255)
    private String bp;
   
    private Date date;

    public Administrateur() {
    }

    public Administrateur(Integer id) {
        this.id = id;
    }

    public Administrateur(Integer id, String nom, String prenom, String email, String motPasse, int telephone, String bp, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motPasse = motPasse;
        this.telephone = telephone;
        this.bp = bp;
        this.date = date;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
 
}
