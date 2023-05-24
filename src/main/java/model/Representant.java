/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import java.util.Date;

/**
 *
 * @author Ibrahim
 */

@ManagedBean

public class Representant  {

    private Integer id;
    private String nomRepresentant;
    private String prenomRepresentant;
    private String emailRepresentant;
    private int telephoneRepresentant;
    private String bpRepresentant;
    private Date date;
    
    private Integer idContribuable;
    private String nomContribuable;
    private String prenomContribuable;
    
    public Representant() {
    }

    public Representant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRepresentant() {
        return nomRepresentant;
    }

    public void setNomRepresentant(String nomRepresentant) {
        this.nomRepresentant = nomRepresentant;
    }

    public String getPrenomRepresentant() {
        return prenomRepresentant;
    }

    public void setPrenomRepresentant(String prenomRepresentant) {
        this.prenomRepresentant = prenomRepresentant;
    }

    public String getEmailRepresentant() {
        return emailRepresentant;
    }

    public void setEmailRepresentant(String emailRepresentant) {
        this.emailRepresentant = emailRepresentant;
    }

    public int getTelephoneRepresentant() {
        return telephoneRepresentant;
    }

    public void setTelephoneRepresentant(int telephoneRepresentant) {
        this.telephoneRepresentant = telephoneRepresentant;
    }

    public String getBpRepresentant() {
        return bpRepresentant;
    }

    public void setBpRepresentant(String bpRepresentant) {
        this.bpRepresentant = bpRepresentant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdContribuable() {
        return idContribuable;
    }

    public void setIdContribuable(Integer idContribuable) {
        this.idContribuable = idContribuable;
    }

    public String getNomContribuable() {
        return nomContribuable;
    }

    public void setNomContribuable(String nomContribuable) {
        this.nomContribuable = nomContribuable;
    }

    public String getPrenomContribuable() {
        return prenomContribuable;
    }

    public void setPrenomContribuable(String prenomContribuable) {
        this.prenomContribuable = prenomContribuable;
    }
    
}
