/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package authentification;
 
import static authentification.AuthSession.getRequest;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import model.Administrateur;
import model.Contribuable;
import org.apache.commons.codec.digest.DigestUtils;
import util.AuthDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */
 
@ManagedBean
@SessionScoped
public class AuthentificatorController extends AuthDbUtil implements Serializable {
    
    private Administrateur administrateur;
    private Contribuable contribuable;
    private boolean valid;

    public AuthentificatorController()  {
       this.administrateur = new Administrateur();
       this.contribuable = new Contribuable();
    }
    
    private boolean IsTaxPayerValid(){
        
        if (this.contribuable.getEmail().isEmpty())
        {
            showError("Required","Email is required");
            return false;
        }
        if (this.contribuable.getMotPasse().isEmpty())
        {
            showError("Required","Password is required");
            return false;
        }
        
        return true;
    }
    
    private boolean IsAdminValid(){
        
        if (this.administrateur.getEmail().isEmpty())
        {
            showError("Required","Email is required");
            return false;
        }
        if (this.administrateur.getMotPasse().isEmpty())
        {
            showError("Required","Password is required");
            return false;
        }
        
        return true;
    }
    
    /* ========================================= Administrator ====================================================*/
    
    public String authAdministrator() throws SQLException {
        
        String mail = this.administrateur.getEmail();
        String password = DigestUtils.shaHex(this.administrateur.getMotPasse());
        valid = AuthentificatorDbUtil.validate_1(mail,password);
        
//        if(IsAdminValid()){
        
            if (valid) {

                HttpSession session = AuthSession.getSession();
                session.setAttribute("authSessionName", mail);
                return "/admin/dashboard?faces-redirect=true";

            } else {

                //showInfo("Bocked"," Your account is disable ");
                warningMessage("Incorrect Email and Password","Please Enter Correct Email and Password");
                return "";
            }
            
//        }
        
    }
    
    public String logoutAdministrator() {
        
        HttpSession session = getRequest().getSession(false);
        
        if (session != null) {
            session.invalidate();
        }
        return "/admin/index?faces-redirect=true";
    }
    
    
    /* ========================================= TaxPayer ====================================================*/
    
    public String authTaxPayer() throws SQLException {
        
        String nom = this.contribuable.getNom();
        String mail = this.contribuable.getEmail();
        String password = DigestUtils.shaHex(this.contribuable.getMotPasse());
        
        valid = AuthentificatorDbUtil.validate_2(mail,password);
                
        if (valid) {

            HttpSession session = AuthSession.getSession();
            session.setAttribute("authSessionName", nom);
            return "welcome?faces-redirect=true";

        } else {

            //showInfo("Bocked"," Your account is disable ");
            warningMessage("Incorrect Email and Password","Please Enter Correct Email and Password");
            return "";
        }
        
    }
    
    public String logoutTaxPayer() {
        
        HttpSession session = getRequest().getSession(false);
        
        if (session != null) {
            session.invalidate();
        }
        return "index.xhtml?faces-redirect=true";
        
    }
    
    /// **************** getters and setters *********

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }
    
    /// **************** contribuable *********

    public Contribuable getContribuable() {
        return contribuable;
    }

    public void setContribuable(Contribuable contribuable) {
        this.contribuable = contribuable;
    }
    
}

