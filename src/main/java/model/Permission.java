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
    private Administrateur idadministrateur;
    

    public Permission() {
        afficher = true;
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

    public void setIdadministrateur(Administrateur idadministrateur) {
        this.idadministrateur = idadministrateur;
    }

    public Administrateur getIdadministrateur() {
        return idadministrateur;
    }

    
}
