/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package authentification;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Ibrahim
 */

public class MyBean {
    
    public boolean isAuthenticated() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            
            String authSessionId = (String) session.getAttribute("authSessionId");
            
            if (authSessionId != null) {
                
              return true;
              
            }
            
        }
        
        return false;
    }

    public String getSessionId() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            
            return session.getId();
            
        }
        
        return null;
    }

    public String logout() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            
            session.invalidate();
            
        }
        
        return "index.xhtml?faces-redirect=true";
    }
}
