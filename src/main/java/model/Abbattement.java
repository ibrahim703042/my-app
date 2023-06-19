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
    private String[] motifAbbattement;
    private Representant representant;
    private Integer Idrepresentant;
    
    private String motif_A = "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier";
    private String motif_B = "B. Enfant mineur ou enfant majeur, copropriétaires, cohéritiers et co-usufruitier";
    private String motif_C = "C. Veuf (veuve) propriétaire, possesseur, titulaire, héritier ou usufruitier";
    private String motif_D = "D. Retraité ou handicapé, personne ayant atteint l’âge légal de la retraite propriétaire, possesseur, titulaire, héritier ou usufruitier";
    private String motif_E = "E. Démobilisé non en fonction publique, privée ou élective ";

    public Abbattement(){
        //beneficiaire = "No";
        
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

    public String[] getMotifAbbattement() {
        return motifAbbattement;
    }

    public void setMotifAbbattement(String[] motifAbbattement) {
        this.motifAbbattement = motifAbbattement;
    }

    public Representant getRepresentant() {
        return representant;
    }

    public void setRepresentant(Representant representant) {
        this.representant = representant;
    }

    public Integer getIdrepresentant() {
        return Idrepresentant;
    }

    public void setIdrepresentant(Integer Idrepresentant) {
        this.Idrepresentant = Idrepresentant;
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
    
    
}
