/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bean;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import model.Administrateur;
import util.AdministrateurDbUtil;


/**
 *
 * @author Ibrahim
 */



@ManagedBean
public class LoginBean extends GenericBean{

    private Administrateur loggedUser;

    public LoginBean() {
        this.administrateurDbUtil = new AdministrateurDbUtil();
        this.model = new Administrateur();
    }

    public String logOut(){

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().clear();

        return "login.xhtml";
    }

    public String login(){
//        List<Administrateur> list = administrateurDbUtil.findByEmailPassword(this.model.getEmail(),DigestUtils.shaHex(this.model.getMotPasse()));
        List<Administrateur> list = administrateurDbUtil.findByEmailPassword(this.model.getEmail(),this.model.getMotPasse());
        //boolean valid = administrateurDbUtil.validate(this.model.getEmail(),DigestUtils.shaHex(this.model.getMotPasse()));
        
        if(!list.isEmpty()){
            this.loggedUser = list.get(0);
            this.model.setMotPasse("");

            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("loggedUser", this.model);

            return "dashboard.xhtml";
        }else{
            this.showMessage("Username or password incorrect!");	
        }
        return "";
    }

    public Administrateur getLoggedUser() {

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        loggedUser = (Administrateur) externalContext.getSessionMap().get("loggedUser");

        return loggedUser;
    }

    public void setLoggedUser(Administrateur loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void clearForm() {
        this.model = new Administrateur();
    }

    @Override
    public void setModel(Administrateur model) {
        this.model = model;
    }

    @Override
    public Administrateur getModel() {
        return this.model;
    }
    
}
