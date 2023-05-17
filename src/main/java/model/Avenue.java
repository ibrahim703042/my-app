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
public class Avenue {

 
    private Integer id;
    private String nomAvenue;
    private int numeroAvenue;
    private Colline idColline;

    public Avenue() {
    }

    public Avenue(Integer id) {
        this.id = id;
    }

    public Avenue(Integer id, String nomAvenue, int numeroAvenue) {
        this.id = id;
        this.nomAvenue = nomAvenue;
        this.numeroAvenue = numeroAvenue;
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

    public int getNumeroAvenue() {
        return numeroAvenue;
    }

    public void setNumeroAvenue(int numeroAvenue) {
        this.numeroAvenue = numeroAvenue;
    }

    public Colline getIdColline() {
        return idColline;
    }

    public void setIdColline(Colline idColline) {
        this.idColline = idColline;
    }

}
