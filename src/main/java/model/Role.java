/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlTransient;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
public class Role implements Serializable{

    private Integer id;
    
    private String nomRole;
    private String description;
    private String creerPar;

    @OneToMany(mappedBy = "idRole")
    private List<Administrateur> administrateurList;

    public Role() {
        
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreerPar() {
        return creerPar;
    }

    public void setCreerPar(String creerPar) {
        this.creerPar = creerPar;
    }

    @XmlTransient
    public List<Administrateur> getAdministrateurList() {
        return administrateurList;
    }

    public void setAdministrateurList(List<Administrateur> administrateurList) {
        this.administrateurList = administrateurList;
    }

    
}
