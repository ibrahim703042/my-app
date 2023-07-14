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

public class AuthSession {
    
    //private Administrateur authAdministrateur;
    //private Contribuable authContribuable;
    
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
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
        
        HttpSession session = getRequest().getSession(false);
        
        if (session != null) {
            
            return session.getId();
            
        }
        
        return null;
    }
    
    public static String getSessionName() { 
        
        HttpSession session = getRequest().getSession(false);
        return session.getAttribute("authSessionName").toString();
    }
    
    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
          return null;
    }

    public String logout() {
        
        HttpSession session = getRequest().getSession(false);
        
        if (session != null) {
            
            session.invalidate();
            
        }
        
        return "index.xhtml?faces-redirect=true";
    }
}
