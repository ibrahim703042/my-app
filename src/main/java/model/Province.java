/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@RequestScoped

public class Province implements Serializable{

    private Integer id;
    private String nomProvince;
    
    public Province() {
    }

    public Province(Integer id) {
        this.id = id;
    }

    public Province(Integer id, String nomProvince) {
        this.id = id;
        this.nomProvince = nomProvince;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProvince() {
        return nomProvince;
    }

    public void setNomProvince(String nomProvince) {
        this.nomProvince = nomProvince;
    }
 
    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("Welcome " + nomProvince ));
    }
}
