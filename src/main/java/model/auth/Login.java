//package model.auth;
//
//import dao.LoginDAO;
//import jakarta.enterprise.context.SessionScoped;
//import java.io.Serializable;
//
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.inject.Named;
//import jakarta.servlet.http.HttpSession;
//
//@Named
//@SessionScoped
//public class Login implements Serializable {
//	
//    private static final long serialVersionUID = 1094801825228386363L;
//
//    private String email;
//    private String nom;
//    private String motPasse;
//    private String message;
//	
//    public String getEmail() {
//        return email;
//    }
//    
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    
//    public String getnom() {
//        return nom;
//    }
//
//    public void setnom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getmotPasse() {
//        return motPasse;
//    }
//
//    public void setmotPasse(String motPasse) {
//        this.motPasse = motPasse;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//	
//    ///******** validate login ****************************//
//    public String validateUsernamePassword() {
//        
//        String msg_1 ="Incorrect nom and Passowrd";
//        String msg_2 ="Please enter correct email and Password";
//        
//        boolean valid = LoginDAO.validate(email, motPasse);
//        
//        if(valid){
//            
//            HttpSession session = SessionUtils.getSession();
//            session.setAttribute("email", email);
//            
////            return "dashboard?faces-redirect=true";
//            return "dashboard";
//            
//        }else{
//            
//            FacesContext.getCurrentInstance().addMessage( null,
//            new FacesMessage(FacesMessage.SEVERITY_WARN,msg_1,msg_2));
//            return "welcome";
//        }
//    }
//
//    ///****************logout event, invalidate session*********
//    public String logout() {
//        
//        HttpSession session = SessionUtils.getSession();
//        session.invalidate();
//        return "login";
//        
//    }
//}
