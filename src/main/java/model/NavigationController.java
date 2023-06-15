/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package model;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 * 
 */

@Named(value = "navigationController")
@ViewScoped
public class NavigationController implements Serializable {
    private String pageId;
    
    public NavigationController(){
    }
    
    public String showPage() {
        
        if( pageId != null) {
            
          return "index";
          
        }

        switch (pageId) {
            case "1":
                return "/pages/dashaboard";
                
            case "2":
                return "/pages/index";
                
            case "3":
                return "dashboard.xhtml";
            
            case "4":
                return "/pages/admin/template.xhtml";
            case "5":
                return "/pages/admin/ajouter.xhtml";
            case "6":
                return "/pages/admin/edit.xhtml";
                
            case "7":
                return "/pages/role/template.xhtml";
            case "8":
                return "/pages/role/ajouter.xhtml";
            case "9":
                return "/pages/role/edit.xhtml";
                
            case "10":
                return "/pages/permission/template.xhtml";
            case "11":
                return "/pages/permission/ajouter.xhtml";
            case "12":
                return "/pages/permission/edit.xhtml";
                
            default:
                return "index";
        }
    }
    
}
