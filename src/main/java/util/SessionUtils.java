
package util;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Administrateur;
import model.Contribuable;


public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getEmail() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("email").toString();
    }

    public static String getId() {
        HttpSession session = getSession();

        if (session != null){
            return (String) session.getAttribute("id");
        }else{
            return null;
        }
    }
    
    public Administrateur getAdministrateurAuth() {
        Administrateur administrateur ;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        administrateur =   (Administrateur) externalContext.getSessionMap().get("auth");
        return administrateur ;
    }

    public Contribuable getContribuableAuth() {
        Contribuable contribuable;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        contribuable =  (Contribuable) externalContext.getSessionMap().get("contribuableAuth");
        return contribuable;
    }

}
