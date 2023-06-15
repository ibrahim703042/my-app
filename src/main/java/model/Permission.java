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
 * 
 */

@ManagedBean
public class Permission implements Serializable{

    private Integer id;
    private boolean ajouter;
    private boolean supprimer;
    private boolean modifier;
    private boolean afficher;
    
    private Administrateur administrateur;
    private Integer idAdministrateur;
    private String nom;
    private String prenom;
    
    public Permission() {
//       afficher = true;
//       ajouter = true;
//       supprimer = true;
//       modifier = true;
//       this.idAdministrateur = 1;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getAjouter() {
        return ajouter;
    }

    public void setAjouter(boolean ajouter) {
        this.ajouter = ajouter;
    }

    public boolean getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public boolean getModifier() {
        return modifier;
    }

    public void setModifier(boolean modifier) {
        this.modifier = modifier;
    }

    public boolean getAfficher() {
        return afficher;
    }

    public void setAfficher(boolean afficher) {
        this.afficher = afficher;
    }

        public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public Integer getIdAdministrateur() {
        return idAdministrateur;
    }

    public void setIdAdministrateur(Integer idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
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
    
    
}
