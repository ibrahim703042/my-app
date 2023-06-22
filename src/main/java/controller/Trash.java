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
public class Trash extends AuthDbUtil implements Serializable {
    
    private List<Administrateur> administrateurs;
    private Administrateur administrateur;
    private Administrateur authAdministrateur;

    private List<Contribuable> contribuables;
    private Contribuable contribuable;
    
    public Trash()  {
       this.administrateur = new Administrateur();
//       this.administrateurs = new ArrayList<Administrateur>();
//       this.contribuable = new Contribuable();
//       this.contribuables = new ArrayList<Contribuable>();
    }
    
    ///******** validate login ****************************//
    public String loginContribuable() throws SQLException{
        
        String msg_1 ="Incorrect Email and Passowrd";
        String msg_2 ="Please Enter Correct Email and Password";
        
        String mail = this.contribuable.getEmail();
        String password = DigestUtils.shaHex(this.contribuable.getMotPasse());
//      String password = this.contribuable.getMotPasse();
        
        
        boolean valid = AuthDbUtil.validateContribuable(mail,password);
        
        if(valid){
            
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("email", this.contribuable.getEmail());
            
            return "welcome?faces-redirect=true";
            
            
        }else{
            warningMessage(msg_1,msg_2);
        }
        return "";
    }
    
    ///******** validate login ****************************//
    public String login() throws SQLException{
        
        String mail = this.administrateur.getEmail();
        String password = DigestUtils.shaHex(this.administrateur.getMotPasse());
        
        boolean valid = AuthDbUtil.validate(mail,password);
        
        if(valid){
            
            //HttpSession session = SessionUtils.getSession();
            //session.setAttribute("user_email", this.administrateur.getEmail());
            
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
        return "index";
        
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
 
}

