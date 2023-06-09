/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.SQLException;
import model.Administrateur;
import util.AdministrateurDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped
public class AuthController extends MessageContoller implements Serializable {
    private Administrateur model;
    //private String pageId;
    
    public AuthController()  {
        super();
       model = new Administrateur();
    }

    public Administrateur getModel() {
        return model;
    }
    
    ///******** validate login ****************************//
    public String login() throws SQLException{
        
        String msg_1 ="Incorrect Email and Passowrd";
        String msg_2 ="Please Enter Correct Email and Password";
        
        boolean valid = AdministrateurDbUtil.validate(model.getEmail(),model.getMotPasse());
        
        if(valid){
            
//            FacesContext context = FacesContext.getCurrentInstance();
//            ExternalContext externalContext = context.getExternalContext();
//            externalContext.getSessionMap().put("adminSession", model);
            
            return "dashboard?faces-redirect=true";
            
            
        }else{
            warningMessage(msg_1,msg_2);
        }
        //return "index?faces-redirect=true";
        return "";
    }
    
    ///****************logout event, invalidate session*********
    public String logout() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().clear();
 
        return "login?faces-redirect=true";
        
    }
    
//    public void clearForm() {
//        model = new Administrateur();        
//    }
    
//    public String showPage() {
//        
//        if( pageId != null) {
//            
//          return "index";
//          
//        }
//
//        switch (pageId) {
//            case "1":
//                return "index";
//                
//            case "2":
//                return "login";
//                
//            case "3":
//                return "dashboard.xhtml";
//            
//            case "4":
//                return "/pages/admin/template.xhtml";
//            case "5":
//                return "/pages/admin/ajouter.xhtml";
//            case "6":
//                return "/pages/admin/edit.xhtml";
//                
//            case "7":
//                return "/pages/role/template.xhtml";
//            case "8":
//                return "/pages/role/ajouter.xhtml";
//            case "9":
//                return "/pages/role/edit.xhtml";
//                
//            case "10":
//                return "/pages/permission/template.xhtml";
//            case "11":
//                return "/pages/permission/ajouter.xhtml";
//            case "12":
//                return "/pages/permission/edit.xhtml";
//                
//            default:
//                return "index";
//        }
//    }
}

