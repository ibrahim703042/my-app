/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

 */
package bean;

import java.util.ArrayList;
import model.Administrateur;
import org.apache.commons.codec.digest.DigestUtils;
import util.AdministrateurDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

public class UserBean extends GenericBean {

    public UserBean() {
        this.administrateurDbUtil = new AdministrateurDbUtil();
        this.model = new Administrateur();
        this.listOfElements = new ArrayList<Administrateur>();
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
    
    public String create(){
		
        if(this.model.getId() == null && this.model.getMotPasse().isEmpty()){
            this.showMessage("Password can't be empty!");
        }else{
            if(this.model.getMotPasse().isEmpty() && this.model.getId() != null){
                //Administrateur user = this.administrateurDbUtil.findById(this.model.getId());
                //this.model.setMotPasse(user.getMotPasse());
            }else{
                this.model.setMotPasse(DigestUtils.shaHex(this.model.getMotPasse()));
            }

            this.administrateurDbUtil.save(this.model);
            this.loadList();
            this.model.setMotPasse("");
            this.showMessage("Record saved!");
        }

        return "";
    }
	
    public void clearPassword(){
        this.model.getMotPasse();
    }
    
}
