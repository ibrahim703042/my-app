/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private List<Contribuable> contribuables;
    private Contribuable contribuable;
    
    private List<Role> roles;
    private Role role;
    
    public AuthController()  {
       this.administrateur = new Administrateur();
       this.administrateurs = new ArrayList<Administrateur>();
       this.contribuable = new Contribuable();
       this.contribuables = new ArrayList<Contribuable>();
       this.role = new Role();
       this.roles = new ArrayList<Role>();
       
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
        
        String msg_1 ="Incorrect Email and Passowrd";
        String msg_2 ="Please Enter Correct Email and Password";
        
        String mail = this.getAdministrateur().getEmail();
        String password = DigestUtils.shaHex(this.getAdministrateur().getMotPasse());
//        String password = this.getAdministrateur().getMotPasse();
        
        
        boolean valid = AuthDbUtil.validate(mail,password);
        
        if(valid){
            
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("email", this.administrateur.getEmail());
            
            return "dashboard?faces-redirect=true";
            
            
        }else{
            warningMessage(msg_1,msg_2);
        }
        return "";
    }
    
    ///****************logout event, invalidate session*********
    public String logout() {
        
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
        
    }

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
 
}

