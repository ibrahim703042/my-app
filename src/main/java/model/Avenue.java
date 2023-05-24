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
public class Avenue {

    private Integer id;
    private String nomAvenue;
    private String nomColline;
    private Integer idColline;

    public Avenue() {
    }

    public Avenue(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomAvenue() {
        return nomAvenue;
    }

    public void setNomAvenue(String nomAvenue) {
        this.nomAvenue = nomAvenue;
    }

    public String getNomColline() {
        return nomColline;
    }

    public void setNomColline(String nomColline) {
        this.nomColline = nomColline;
    }

    public Integer getIdColline() {
        return idColline;
    }

    public void setIdColline(Integer idColline) {
        this.idColline = idColline;
    }

    
   
}
