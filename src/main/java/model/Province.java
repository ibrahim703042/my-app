/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.*;

/**
 *
 * @author Ibrahim
 */

@Named
@RequestScoped

public class Province{

    private Integer id;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String nomProvince;
    
    private Collection<Commune> communeCollection;

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

    public Collection<Commune> getCommuneCollection() {
        return communeCollection;
    }

    public void setCommuneCollection(Collection<Commune> communeCollection) {
        this.communeCollection = communeCollection;
    }
    
    @Override
    public Province clone() {
        return new Province(getId(), getNomProvince());
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Province)) {
            return false;
        }
        Province other = (Province) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Province[ id=" + id + " ]";
    }
    
    
    
    public void save() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + nomProvince ));
    }
}
