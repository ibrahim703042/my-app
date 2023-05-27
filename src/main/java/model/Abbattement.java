/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Abbattement implements Serializable{

    private Integer id;
    private String beneficiaire;
    private String[] selectedMotifs;
    private List<String> motif;
    private List<Contribuable> contribuable;
    
    String A = "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier";
    String B = "B. Enfant mineur ou enfant majeur, copropriétaires, cohéritiers et co-usufruitier";
    String C = "C. Veuf (veuve) propriétaire, possesseur, titulaire, héritier ou usufruitier";
    String D = "D. Retraité ou handicapé, personne ayant atteint l’âge légal de la retraite propriétaire, possesseur, titulaire, héritier ou usufruitier";
    String E = "E. Démobilisé non en fonction publique, privée ou élective ";

    @PostConstruct
    public void init() {
        motif = new ArrayList<>();
        
        motif.add(A);
        motif.add(B);
        motif.add(C);
        motif.add(D);
        motif.add(E);
        
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

    public String[] getSelectedMotifs() {
        return selectedMotifs;
    }

    public void setSelectedMotifs(String[] selectedMotifs) {
        this.selectedMotifs = selectedMotifs;
    }

    public List<String> getMotif() {
        return motif;
    }

    public void setMotif(List<String> motif) {
        this.motif = motif;
    }

    public List<Contribuable> getContribuable() {
        return contribuable;
    }

    public void setContribuable(List<Contribuable> contribuable) {
        this.contribuable = contribuable;
    }
    
    
}
