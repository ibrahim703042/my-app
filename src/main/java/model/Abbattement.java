/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

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
    
//    String A = "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier";
//    String B = "B. Enfant mineur ou enfant majeur, copropriétaires, cohéritiers et co-usufruitier";
//    String C = "C. Veuf (veuve) propriétaire, possesseur, titulaire, héritier ou usufruitier";
//    String D = "D. Retraité ou handicapé, personne ayant atteint l’âge légal de la retraite propriétaire, possesseur, titulaire, héritier ou usufruitier";
//    String E = "E. Démobilisé non en fonction publique, privée ou élective ";

    //private Short motif_A;
    
    
    
    public Abbattement(){
        beneficiaire = "No";
        
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
    
    
}
