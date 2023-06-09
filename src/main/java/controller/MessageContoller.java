/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author Ibrahim
 */
public class MessageContoller {
    
    // ****************message*********
    public void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
    
    public void warningMessage(String msg1, String msg2){
        FacesContext.getCurrentInstance().addMessage( null,
        new FacesMessage(FacesMessage.SEVERITY_WARN,msg1,msg2));
    }
    
    // ******   Message Session ******/
    public void showInfo(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, content, msg);
    }
    
    public void showWarn(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, content, msg);
    }

    public void showError(String content, String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, content, msg);
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
