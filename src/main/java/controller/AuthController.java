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
import java.util.List;
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
public class AuthController extends AuthDbUtil implements Serializable {
    
    private List<Administrateur> administrateurs;
    private Administrateur administrateur;
    private Administrateur authAdministrateur;
    
    private List<Contribuable> contribuables;
    private Contribuable contribuable;
    private Contribuable authContribuable;

    public AuthController()  {
       this.administrateur = new Administrateur();
       this.contribuable = new Contribuable();
    }
    
    private boolean IsValid(){
        
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
    
    public String loginAdmin(){
        
        String mail = this.administrateur.getEmail();
        String password = DigestUtils.shaHex(this.administrateur.getMotPasse());
        
        if(IsAdminValid()){
            
        
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
        }
        
        return "";
    }
    
    /// ****************logout event, invalidate session*********
    public String logout() {
        
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/pages/index?faces-redirect=true";
        
    }
    
    
    public String taxPayer_login(){
        
        String mail = this.getContribuable().getEmail();
        String password = DigestUtils.shaHex(this.getContribuable().getMotPasse());
        
        if(IsValid()){
         
            List<Contribuable> list = AuthDbUtil.authentificato(mail,password);

            if( !list.isEmpty()){
                this.authContribuable = list.get(0);
                this.contribuable.setMotPasse("");

                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();
                externalContext.getSessionMap().put("authContribuable", this.contribuable);

                return "welcome?faces-redirect=true";

            }else{
                //showInfo("Bocked"," Your account is disable ");
                warningMessage("Incorrect Email and Passowrd","Please Enter Correct Email and Password");

            }
        }
        
        return "";
    }
    
    /// ****************logout event, invalidate session*********
    
    public String taxPayer_logout() {
        
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

    /// **************** contribuable *********

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

    public Contribuable getAuthContribuable() {
          FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        contribuable = (Contribuable) externalContext.getSessionMap().get("authContribuable");
        return authContribuable;
    }

    public void setAuthContribuable(Contribuable authContribuable) {
        this.authContribuable = authContribuable;
    }
    
    
    
}

