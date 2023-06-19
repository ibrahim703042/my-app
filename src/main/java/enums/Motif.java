/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author Ibrahim
 */
public enum Motif {
    
    Motif_A( "A. Enfant mineur ou enfant majeur en cours de scolarisation jusqu’à l’âge de 25 ans, orphelin de père et de mère, héritier ou usufruitier"),
    Motif_B( "B. Enfant mineur ou enfant majeur, copropriétaires, cohéritiers et co-usufruitier"),
    Motif_C( "C. Veuf (veuve) propriétaire, possesseur, titulaire, héritier ou usufruitier"),
    Motif_D( "D. Retraité ou handicapé, personne ayant atteint l’âge légal de la retraite propriétaire, possesseur, titulaire, héritier ou usufruitier"),
    Motif_E( "E. Démobilisé non en fonction publique, privée ou élective ");
	
    private String m_abbattement;
    
    Motif(  String  m_abbattement){
        this.m_abbattement = m_abbattement;
    }

    public String getM_abbattement() {
        return m_abbattement;
    }
    
}
