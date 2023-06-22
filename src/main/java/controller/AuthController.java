/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import model.Administrateur;
import model.Contribuable;
import model.Role;
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
public class AuthController extends AuthDbUtil implements Serializable {
    
    private List<Administrateur> administrateurs;
    private Administrateur administrateur;
    private Administrateur authAdministrateur;

    public AuthController()  {
       this.administrateur = new Administrateur();
    }
    
    public String loginAdmin(){
        
        String mail = this.administrateur.getEmail();
        String password = DigestUtils.shaHex(this.administrateur.getMotPasse());
        
        List<Administrateur> list = AuthDbUtil.authentificator(mail,password);

        if( !list.isEmpty()){
            this.authAdministrateur = list.get(0);
            this.administrateur.setMotPasse("");

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("authAdministrateur", this.administrateur);

            return "dashboard?faces-redirect=true";
            
        }else{
            //showInfo("Bocked"," Your account is disable ");
            warningMessage("Incorrect Email and Passowrd","Please Enter Correct Email and Password");
        
        }
        
        return "";
    }
    
    ///****************logout event, invalidate session*********
    public String logout() {
        
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index?faces-redirect=true";
        
    }
    
    /// **************** getters and setters *********
    
    public List<Administrateur> getAdministrateurs() {
        
        return administrateurs;
    }

    public void setAdministrateurs(List<Administrateur> administrateurs) {
        this.administrateurs = administrateurs;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }
    
    public Administrateur getAuthAdministrateur() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        administrateur = (Administrateur) externalContext.getSessionMap().get("authAdministrateur");
        return authAdministrateur;
    }

    public void setAuthAdministrateur(Administrateur authAdministrateur) {
        this.authAdministrateur = authAdministrateur;
    }

    
}

