package calcul;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
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

@ManagedBean
public class CalculRevenus implements Serializable {
    
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


    public CalculRevenus (){

        // this.modelRevenuLocatif = new RevenuLocatif();
        // this.modelRevenuLocatif = new RevenuLocatif();
        // this.modelRevenuSousLocation = new RevenuSousLocation();
        // this.modelImpot = new Impot();
        // this.modelAmande = new Amande();
        // this.modelPayement = new Payement();
        // this.modelQuittance = new Quittance();
        
    }
    
    // *********** REVENULOCATIF ****************/
    public Double getRevenuBrut() {
       double total = this.modelRevenuLocatif.getLoyerImposable() + this.modelRevenuLocatif.getChargeIncombat();
       return total;
    }
    
    public Double getDeductionDepensesEntretien() {
       double total = (getRevenuBrut() * 40)/100 ;
       return total;
    }
    
    public Double getRevenuLocatifRevenusNetsImposable() {
       double total = (getRevenuBrut() - getDeductionDepensesEntretien() - this.modelRevenuLocatif.getInteretEmprunt());
       return total;
    }
    
    // *********** REVENULOCATIF SOUS LOCATION ****************/
    
    public Double getRevenuSousLocationRevenusNetsImposables() {
       double total = this.modelRevenuSousLocation.getRecetteSousLocation() - this.modelRevenuSousLocation.getLoyerPayes();
       return total;
    }
    
    public Double getRevenuSousLocatifTotalRevenusNetsImposable() {
       double total = (getRevenuLocatifRevenusNetsImposable() + getRevenuSousLocationRevenusNetsImposables());
       return total;
    }
    
    public Double getRevenuSousLocatifRevenuNetImposable() {
       double total = (getRevenuSousLocatifTotalRevenusNetsImposable() - this.modelRevenuSousLocation.getAbbattements() );
       return total;
    }
    
    // *********** DETERMINATION DE L’IMPOT DU  ****************/
    public double montant = getRevenuSousLocatifRevenuNetImposable();
    public double operation_1 = this.modelImpot.getTranche_1();
    public double operation_2 = this.modelImpot.getTranche_2();
    public double operation_3 = this.modelImpot.getTranche_3();
    
    public double finalTranche_1 = 1800000;
    public double finalTranche_2 = 3600000;
    public double restante = 0;
    
    
    public double impotDu(){
        
        if( montant >= 1800000 && montant > 0 ){
            
            return operation_1 = finalTranche_1 - montant ;
            
        }else if( operation_1 > finalTranche_1 && montant < finalTranche_2 ){
            
            return  operation_2 = finalTranche_2 - finalTranche_1 ;  
            
        }else if( operation_2 > finalTranche_2){
            
            return operation_2;  
            
        }else {
            
            return operation_1 = 0 ;  
            
        }
    }
    
    // *********** . RESERVE A L’ADMINISTRATION FISCALE   ****************/
    public double operation_1_col_6 = this.modelImpot.getTranche_1_Col_5();
    public double operation_2_col_5 = this.modelImpot.getTranche_2_Col_5();
    public double operation_3_col_5 = this.modelImpot.getTranche_3_Col_5();
    
    public double operation_1_col_5(){
        return (operation_1 * 0) /100;
    }
    
    public double operation_2_col_5(){
        return (((operation_1 * 0) /100) +(operation_2 * 20) / 100) ;
    }
    
    public double operation_3_col_5(){
        return ((((operation_1 * 0) /100) + (operation_2 * 20) / 100) + ((operation_3 * 30) / 100)) ;
    }
    
    
    // *********** TOTALITE RESERVE A L’ADMINISTRATION FISCALE   ****************/
    public double totalImpotDU(){
        return operation_1_col_5() + operation_2_col_5() + operation_3_col_5();
    }
    
    public double totalRestantDU(){
        return  totalImpotDU() - this.modelImpot.getAccompteImpotDejaPaye();
    }

    // *********** AMANDE ****************/
    
    public double totalAmandePenalite(){
        return  totalRestantDU() + this.modelAmande.getAmandeFixe() + (this.modelAmande.getPenalite() * 10)/100 ;
    }
    
    // *********** Qittance  ****************/
    public double quittanceMontantRestantDu(){
        return  totalRestantDU() + this.modelQuittance.getMontantPaye() ;
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

    // *******************************Notif MESSAGE******************************************
    
    private static void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
    private void warningMessage(String msg1, String msg2){
        FacesContext.getCurrentInstance().addMessage( null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,msg1,msg2));
    }
    
    // ******   Message Session ******/
    private void showInfo(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, content, msg);
    }
    
    private void showWarn(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, content, msg);
    }

    private void showError(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, content, msg);
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
