/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 */
@ManagedBean
public class Commune implements Serializable{

    private Integer id;
    private String nomCommune;
    private Integer idProvince;
    private String NomProvince;

    public Commune() {
    }

    public Commune(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getNomProvince() {
        return NomProvince;
    }

    public void setNomProvince(String NomProvince) {
        this.NomProvince = NomProvince;
    }
    
}
