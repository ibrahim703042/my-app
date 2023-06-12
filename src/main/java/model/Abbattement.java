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
    private List<Contribuable> contribuables;
    private Contribuable contribuable;
    
//    String A = "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier";
//    String B = "B. Enfant mineur ou enfant majeur, copropriétaires, cohéritiers et co-usufruitier";
//    String C = "C. Veuf (veuve) propriétaire, possesseur, titulaire, héritier ou usufruitier";
//    String D = "D. Retraité ou handicapé, personne ayant atteint l’âge légal de la retraite propriétaire, possesseur, titulaire, héritier ou usufruitier";
//    String E = "E. Démobilisé non en fonction publique, privée ou élective ";

    private Short motif_A;
//    private boolean motifB;
//    private boolean motifC;
//    private boolean motifD;
//    private boolean motifE;
    
    
    
    public Abbattement(){
        beneficiaire = "No";
        //motif_A = true;
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

    public List<Contribuable> getContribuables() {
        return contribuables;
    }

    public void setContribuables(List<Contribuable> contribuables) {
        this.contribuables = contribuables;
    }

    public Contribuable getContribuable() {
        return contribuable;
    }

    public void setContribuable(Contribuable contribuable) {
        this.contribuable = contribuable;
    }

    public void setMotif_A(Short motif_A) {
        this.motif_A = motif_A;
    }

    public Short getMotif_A() {
        return motif_A;
    }

    
    
    
}
