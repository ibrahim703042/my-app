/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 */

@Named
public class Declaration implements Serializable {

    private Integer id;
    private int nif;
    private int ccf;

    public Declaration() {
    }

    public Declaration(Integer id) {
        this.id = id;
    }

    public Declaration(Integer id, int nif, int ccf) {
        this.id = id;
        this.nif = nif;
        this.ccf = ccf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getCcf() {
        return ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }
}
