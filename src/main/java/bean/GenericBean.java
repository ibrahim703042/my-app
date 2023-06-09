/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.util.List;
import model.Administrateur;
import util.AdministrateurDbUtil;

/**
 *
 * @author Ibrahim
 */

abstract class GenericBean {
	
    protected Administrateur model;
    protected AdministrateurDbUtil administrateurDbUtil;
    protected List<Administrateur> listOfElements;
    
    @PostConstruct
    public void loadList(){
        this.listOfElements = this.administrateurDbUtil.getAdministrateurs();
    }
	
    public abstract void clearForm();

    public abstract void setModel(Administrateur model);

    public abstract Administrateur getModel();

    public String removeRecord(){

        //this.administrateurDbUtil.delete(this.model);
        this.loadList();
        this.clearForm();
        this.showMessage("Record deleted!");
        return "";
    }

    public List<Administrateur> getListOfElements() {
        return this.listOfElements;
    }
    public void setListOfElements(List<Administrateur> list) {
        this.listOfElements = list;
    }

    public String save(){
        this.administrateurDbUtil.save(this.model);
        this.loadList();
        this.showMessage("Record saved!");
        return "";
    }

    public String newRecord(){
        this.clearForm();
        return "";
    }
	
    public void showMessage(String msg){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage("Notice",msg);
        context.addMessage(null, message);
    }
	
}
