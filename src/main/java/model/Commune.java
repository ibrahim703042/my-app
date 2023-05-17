/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.inject.Named;

/**
 *
 * @author Ibrahim
 */
@Named
public class Commune {

    private Integer id;
    private String nomCommune;
    private Colline colline;
    private Province idProvince;

    public Commune() {
    }

    public Commune(Integer id) {
        this.id = id;
    }

    public Commune(Integer id, String nomCommune) {
        this.id = id;
        this.nomCommune = nomCommune;
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

    public Colline getColline() {
        return colline;
    }

    public void setColline(Colline colline) {
        this.colline = colline;
    }

    public Province getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Province idProvince) {
        this.idProvince = idProvince;
    }

}
