/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package authentification;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import model.Administrateur;
import model.Contribuable;
import org.apache.commons.codec.digest.DigestUtils;
import util.AuthDbUtil;
import util.SessionUtils;

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
    
    //validate login
    
    public String authAdministrator() throws SQLException {
        
        String mail = this.administrateur.getEmail();
        String password = DigestUtils.shaHex(this.administrateur.getMotPasse());
        valid = AuthentificatorDbUtil.validate_1(mail,password);
        
//        if(IsAdminValid()){
        
            if (valid) {

                HttpSession session = SessionUtils.getSession();
                session.setAttribute("email", mail);
                return "/admin/dashboard?faces-redirect=true";

            } else {

                //showInfo("Bocked"," Your account is disable ");
                warningMessage("Incorrect Email and Passowrd","Please Enter Correct Email and Password");
                return "";
            }
            
//        }
        
    }
    
    //validate login
    public String authTaxPayer() throws SQLException {
        
        String mail = this.administrateur.getEmail();
        String password = DigestUtils.shaHex(this.administrateur.getMotPasse());
        valid = AuthentificatorDbUtil.validate_2(mail,password);
                
        if (valid) {

            HttpSession session = SessionUtils.getSession();
            session.setAttribute("email", mail);
            return "welcome?faces-redirect=true";

        } else {

            //showInfo("Bocked"," Your account is disable ");
            warningMessage("Incorrect Email and Passowrd","Please Enter Correct Email and Password");
            return "";
        }
        
    }
    
    /// ****************logout event, invalidate session*********
    public String logout() {
        
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/admin/index?faces-redirect=true";
        
    }
    
    /// ****************logout event, invalidate session*********
    
    public String taxPayer_logout() {
        
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index?faces-redirect=true";
        
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

