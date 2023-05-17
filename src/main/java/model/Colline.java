/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;
/**
 *
 * @author Ibrahim
 */

public class Colline{

    private Integer id;
    private int idCommune;
    private String nomColline;
    private Commune commune;
    private Collection<Avenue> avenueCollection;

    public Colline() {
    }

    public Colline(Integer id) {
        this.id = id;
    }

    public Colline(Integer id, int idCommune, String nomColline) {
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

    public int getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(int idCommune) {
        this.idCommune = idCommune;
    }

    public String getNomColline() {
        return nomColline;
    }

    public void setNomColline(String nomColline) {
        this.nomColline = nomColline;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Collection<Avenue> getAvenueCollection() {
        return avenueCollection;
    }

    public void setAvenueCollection(Collection<Avenue> avenueCollection) {
        this.avenueCollection = avenueCollection;
    }

    
}
