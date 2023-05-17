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
public class Impot{

    private Integer id;
    private int idSLocation;
    private int impDPaye;
    private int impotTotal;

    public Impot() {
    }

    public Impot(Integer id) {
        this.id = id;
    }

    public Impot(Integer id, int idSLocation, int impDPaye, int impotTotal) {
        this.id = id;
        this.idSLocation = idSLocation;
        this.impDPaye = impDPaye;
        this.impotTotal = impotTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdSLocation() {
        return idSLocation;
    }

    public void setIdSLocation(int idSLocation) {
        this.idSLocation = idSLocation;
    }

    public int getImpDPaye() {
        return impDPaye;
    }

    public void setImpDPaye(int impDPaye) {
        this.impDPaye = impDPaye;
    }

    public int getImpotTotal() {
        return impotTotal;
    }

    public void setImpotTotal(int impotTotal) {
        this.impotTotal = impotTotal;
    }

}
