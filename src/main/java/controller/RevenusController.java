/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.Amande;
import model.Colline;
import model.Commune;
import model.Contribuable;
import model.Declaration;
import model.Impot;
import model.Payement;
import model.Province;
import model.Quittance;
import model.Representant;
import model.RevenuLocatif;
import model.RevenuSousLocation;
import util.RevenusDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@ViewScoped
public final class RevenusController implements Serializable {
    
    
    private Contribuable modelContribuable;
    private List<Contribuable> listContribuable;

    private Representant modelRepresentant;
    private List<Representant> listRepresentant;

    private Colline modelColline;
    private List<Colline> listColline;

    private Commune modelCommune;
    private List<Commune> listCommune;
    
    private Province modelProvince;
    private List<Province> listProvince;

    private Declaration modelDeclaration;
    private List<Declaration> listDeclaration;

    private RevenuLocatif modelRevenuLocatif;
    private List<RevenuLocatif> listRevenuLocatif;
    
    private RevenuSousLocation modelRevenuSousLocation;
    private List<RevenuSousLocation> listRevenuSousLocation;
    
    private Impot modelImpot;
    private List<Impot> listImpot;
    
    private Amande modelAmande;
    private List< Amande> listAmande;
    
    private Payement modelPayement;
    private List<Payement> listPayement;
    
    private List<Quittance> listQuittance;
    private Quittance modelQuittance;
    
    public static double restante = 0;

    @Inject
    private RevenusDbUtil revenuDbUtil;
  
    @PostConstruct
    public void init(){
        
       listRevenuLocatif = revenuDbUtil.findAll();
       this.modelRevenuLocatif = new RevenuLocatif();
       this.modelRevenuSousLocation = new RevenuSousLocation();
       this.modelImpot = new Impot();
       this.modelAmande = new Amande();
       this.modelPayement = new Payement();
       this.modelQuittance = new Quittance();
       
    }

    // ************************** REVENULOCATIF ************************************************/
    
    public Double totalRevenulocatifRevenubrut() {
        return (this.getModelRevenuLocatif().getLoyerImposable() + this.getModelRevenuLocatif().getChargeIncombat());
    }
    
    public Double totalDeductionDepensesEntretien() {
        return (totalRevenulocatifRevenubrut() * 40)/100;
    }
    
    public Double totalRevenuLocatifRevenunetImposable() {
        return (totalRevenulocatifRevenubrut()-totalDeductionDepensesEntretien() - this.modelRevenuLocatif.getInteretEmprunt());
    }
    
    // *********** REVENULOCATIF SOUS LOCATION ****************/
     
    public Double totalRevenuSousLocationRevenusNetsImposables() {
       double total = this.modelRevenuSousLocation.getRecetteSousLocation() - this.modelRevenuSousLocation.getLoyerPayes();
       return total;
    }

    public Double totalRevenuSousLocatifTotalRevenusNetsImposable() {
       double total = (totalRevenuLocatifRevenunetImposable() + totalRevenuSousLocationRevenusNetsImposables());
       return total;
    }
     
    public Double totalRevenuSousLocatifRevenuNetImposable() {
       double total = (totalRevenuSousLocatifTotalRevenusNetsImposable() - this.modelRevenuSousLocation.getAbbattements() );
       return total;
    }
     
    // *********** MONTANT PAR TRANCHE L’IMPOT DU  ****************/
    
    public static double tranche = 0;
    public static double revenus_1 ;
    public static double revenus_2;
    public  static double revenus_3;
    
    
    public Double revenuTranche() {
        
        double montant = totalRevenuSousLocatifTotalRevenusNetsImposable();
        double montant_restante_par_tranche = montant;
        
       if( montant_restante_par_tranche > 0 && montant_restante_par_tranche <= 1800000 ){
           
           tranche = montant_restante_par_tranche;
//           revenus_1 = montant_restante_par_tranche;
           montant_restante_par_tranche = this.modelImpot.getTranche_1();

       }else if( montant_restante_par_tranche > 1800000 && montant_restante_par_tranche <=3600000 ){
            
            tranche = montant_restante_par_tranche  ;
//            revenus_2 = montant_restante_par_tranche  ;
              montant_restante_par_tranche = this.modelImpot.getTranche_2()  ;
            

       }else if( montant_restante_par_tranche > 3600000){

            tranche = montant_restante_par_tranche  ;
//            revenus_3 = montant_restante_par_tranche  ;
            montant_restante_par_tranche = this.modelImpot.getTranche_3();
       }
       
       return montant_restante_par_tranche;
    }
    
    // *********** DETERMINATION DE L’IMPOT DU  ****************/
    public static double impot = 0;
    public static double impot_1;
    public static double impot_2;
    public  static double impot_3;
    
    public double impotDu() {
        
        double revenus = totalRevenuSousLocatifTotalRevenusNetsImposable();
        restante = revenus;
        
       if( restante > 0 && restante <= 1800000 ){
           impot = restante*0;
           impot_1 = restante*0;

       }else if( restante > 1800000 && restante <=3600000 ){

            impot =  (restante-1800000) *0.2;  
            impot_2 =  (restante-1800000) *0.2;  

       }else if( restante > 3600000){

           impot =  0+30000+(restante-3600000) *0.3; 
           impot_3 =(restante-3600000) *0.3; 

       }
       
       return impot;
    }
     
    
    // *********** . RESERVE A L’ADMINISTRATION FISCALE   ****************/
     


    // *********** TOTALITE RESERVE A L’ADMINISTRATION FISCALE   ****************/
    public double totalImpotDU(){
       return impot_1 + impot_2 + impot_3;
    }

    public double totalRestantDU(){
       return  totalImpotDU() - this.modelImpot.getAccompteImpotDejaPaye();
    }

    // *********** AMANDE ****************/

    public double totalAmandePenalite(){
       return  totalRestantDU() + this.modelAmande.getAmandeFixe() + (this.modelAmande.getPenalite() * 10)/100 ;
    }

    // *********** Qittance  ****************/
    public double getQuittanceMontantRestantDu(){
       return  totalRestantDU() - this.modelPayement.getMontantPaye() ;
    }

    
    // ********************************Getters and Setters************************************************/

    public Contribuable getModelContribuable() {
        return modelContribuable;
    }

    public void setModelContribuable(Contribuable modelContribuable) {
        this.modelContribuable = modelContribuable;
    }

    public List<Contribuable> getListContribuable() {
        return listContribuable;
    }

    public void setListContribuable(List<Contribuable> listContribuable) {
        this.listContribuable = listContribuable;
    }

    public Representant getModelRepresentant() {
        return modelRepresentant;
    }

    public void setModelRepresentant(Representant modelRepresentant) {
        this.modelRepresentant = modelRepresentant;
    }

    public List<Representant> getListRepresentant() {
        return listRepresentant;
    }

    public void setListRepresentant(List<Representant> listRepresentant) {
        this.listRepresentant = listRepresentant;
    }

    public Colline getModelColline() {
        return modelColline;
    }

    public void setModelColline(Colline modelColline) {
        this.modelColline = modelColline;
    }

    public List<Colline> getListColline() {
        return listColline;
    }

    public void setListColline(List<Colline> listColline) {
        this.listColline = listColline;
    }

    public Commune getModelCommune() {
        return modelCommune;
    }

    public void setModelCommune(Commune modelCommune) {
        this.modelCommune = modelCommune;
    }

    public List<Commune> getListCommune() {
        return listCommune;
    }

    public void setListCommune(List<Commune> listCommune) {
        this.listCommune = listCommune;
    }

    public Province getModelProvince() {
        return modelProvince;
    }

    public void setModelProvince(Province modelProvince) {
        this.modelProvince = modelProvince;
    }

    public List<Province> getListProvince() {
        return listProvince;
    }

    public void setListProvince(List<Province> listProvince) {
        this.listProvince = listProvince;
    }

    public Declaration getModelDeclaration() {
        return modelDeclaration;
    }

    public void setModelDeclaration(Declaration modelDeclaration) {
        this.modelDeclaration = modelDeclaration;
    }

    public List<Declaration> getListDeclaration() {
        return listDeclaration;
    }

    public void setListDeclaration(List<Declaration> listDeclaration) {
        this.listDeclaration = listDeclaration;
    }

    public RevenuLocatif getModelRevenuLocatif() {
        return modelRevenuLocatif;
    }

    public void setModelRevenuLocatif(RevenuLocatif modelRevenuLocatif) {
        this.modelRevenuLocatif = modelRevenuLocatif;
    }

    public List<RevenuLocatif> getListRevenuLocatif() {
        return listRevenuLocatif;
    }

    public void setListRevenuLocatif(List<RevenuLocatif> listRevenuLocatif) {
        this.listRevenuLocatif = listRevenuLocatif;
    }

    public RevenuSousLocation getModelRevenuSousLocation() {
        return modelRevenuSousLocation;
    }

    public void setModelRevenuSousLocation(RevenuSousLocation modelRevenuSousLocation) {
        this.modelRevenuSousLocation = modelRevenuSousLocation;
    }

    public List<RevenuSousLocation> getListRevenuSousLocation() {
        return listRevenuSousLocation;
    }

    public void setListRevenuSousLocation(List<RevenuSousLocation> listRevenuSousLocation) {
        this.listRevenuSousLocation = listRevenuSousLocation;
    }

    public Impot getModelImpot() {
        return modelImpot;
    }

    public void setModelImpot(Impot modelImpot) {
        this.modelImpot = modelImpot;
    }

    public List<Impot> getListImpot() {
        return listImpot;
    }

    public void setListImpot(List<Impot> listImpot) {
        this.listImpot = listImpot;
    }

    public Amande getModelAmande() {
        return modelAmande;
    }

    public void setModelAmande(Amande modelAmande) {
        this.modelAmande = modelAmande;
    }

    public List< Amande> getListAmande() {
        return listAmande;
    }

    public void setListAmande(List< Amande> listAmande) {
        this.listAmande = listAmande;
    }

    public Payement getModelPayement() {
        return modelPayement;
    }

    public void setModelPayement(Payement modelPayement) {
        this.modelPayement = modelPayement;
    }

    public List<Payement> getListPayement() {
        return listPayement;
    }

    public void setListPayement(List<Payement> listPayement) {
        this.listPayement = listPayement;
    }

    public List<Quittance> getListQuittance() {
        return listQuittance;
    }

    public void setListQuittance(List<Quittance> listQuittance) {
        this.listQuittance = listQuittance;
    }

    public Quittance getModelQuittance() {
        return modelQuittance;
    }

    public void setModelQuittance(Quittance modelQuittance) {
        this.modelQuittance = modelQuittance;
    }
     
}
