/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ibrahim
 */

public class Immeuble {

    private Integer idImmeuble;
    private int idContibuable;
    private int idAvenue;

    public Immeuble() {
    }

    public Immeuble(Integer idImmeuble) {
        this.idImmeuble = idImmeuble;
    }

    public Immeuble(Integer idImmeuble, int idContibuable, int idAvenue) {
        this.idImmeuble = idImmeuble;
        this.idContibuable = idContibuable;
        this.idAvenue = idAvenue;
    }

    public Integer getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdImmeuble(Integer idImmeuble) {
        this.idImmeuble = idImmeuble;
    }

    public int getIdContibuable() {
        return idContibuable;
    }

    public void setIdContibuable(int idContibuable) {
        this.idContibuable = idContibuable;
    }

    public int getIdAvenue() {
        return idAvenue;
    }

    public void setIdAvenue(int idAvenue) {
        this.idAvenue = idAvenue;
    }

    
}
