/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@ViewScoped
public class Abbattement implements Serializable{

    private Integer id;
    private String beneficiaire;
    //private String[] motifAbbattement;
    private String motifAbbattement;
    private Integer IdContribuable;
    
    private static Contribuable contribuable;
    private String nom;
    private String email;
    
    private String motif_A = "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier";
    private String motif_B = "B. Enfant mineur ou enfant majeur, copropriétaires, cohéritiers et co-usufruitier";
    private String motif_C = "C. Veuf (veuve) propriétaire, possesseur, titulaire, héritier ou usufruitier";
    private String motif_D = "D. Retraité ou handicapé, personne ayant atteint l’âge légal de la retraite propriétaire, possesseur, titulaire, héritier ou usufruitier";
    private String motif_E = "E. Démobilisé non en fonction publique, privée ou élective ";

    public Abbattement(){
        //beneficiaire = "Non";
        //motif_A = "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier";
        //motifAbbattement = new String[3];
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public String getMotifAbbattement() {
        return motifAbbattement;
    }

    public void setMotifAbbattement(String motifAbbattement) {
        this.motifAbbattement = motifAbbattement;
    }

    public Integer getIdContribuable() {
        return IdContribuable;
    }

    public void setIdContribuable(Integer IdContribuable) {
        this.IdContribuable = IdContribuable;
    }

    public String getMotif_A() {
        return motif_A;
    }

    public void setMotif_A(String motif_A) {
        this.motif_A = motif_A;
    }

    public String getMotif_B() {
        return motif_B;
    }

    public void setMotif_B(String motif_B) {
        this.motif_B = motif_B;
    }

    public String getMotif_C() {
        return motif_C;
    }

    public void setMotif_C(String motif_C) {
        this.motif_C = motif_C;
    }

    public String getMotif_D() {
        return motif_D;
    }

    public void setMotif_D(String motif_D) {
        this.motif_D = motif_D;
    }

    public String getMotif_E() {
        return motif_E;
    }

    public void setMotif_E(String motif_E) {
        this.motif_E = motif_E;
    }

    public static void setContribuable(Contribuable contribuable) {
        Abbattement.contribuable = contribuable;
    }

    public static Contribuable getContribuable() {
        return contribuable;
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
    
    
    
}
