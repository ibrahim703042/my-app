/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.validation.constraints.Email;
import java.util.Date;

/**
 *
 * @author Ibrahim
 */

public class Representant  {

    private Integer id;
    
    private String nomRepresentant;
    
    private String prenomRepresentant;
    
    @Email
    private String emailRepresentant;
    
    private int telephoneRepresentant;
    
    private String bpRepresentant;
    
    private Date date;
    
    private Contribuable idContribuable;

    public Representant() {
    }

    public Representant(Integer id) {
        this.id = id;
    }

    public Representant(Integer id, String nomRepresentant, String prenomRepresentant, String emailRepresentant, int telephoneRepresentant, String bpRepresentant, Date date) {
        this.id = id;
        this.nomRepresentant = nomRepresentant;
        this.prenomRepresentant = prenomRepresentant;
        this.emailRepresentant = emailRepresentant;
        this.telephoneRepresentant = telephoneRepresentant;
        this.bpRepresentant = bpRepresentant;
        this.date = date;
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

    public Contribuable getIdContribuable() {
        return idContribuable;
    }

    public void setIdContribuable(Contribuable idContribuable) {
        this.idContribuable = idContribuable;
    }

}
