/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Colline{

    private Integer id;
    private Integer idCommune;
    private String nomCommune;
    private String nomColline;
    
    public Colline() {
    }

    public Colline(Integer id) {
        this.id = id;
    }

    public Colline(Integer id, Integer idCommune, String nomColline) {
        this.id = id;
        this.idCommune = idCommune;
        this.nomColline = nomColline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(Integer idCommune) {
        this.idCommune = idCommune;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getNomColline() {
        return nomColline;
    }

    public void setNomColline(String nomColline) {
        this.nomColline = nomColline;
    }

    
}
