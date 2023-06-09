/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@RequestScoped
public class Representant implements Serializable {

    private Integer id;
    private String nomRepresentant;
    private String prenomRepresentant;
    @Email
    private String emailRepresentant;
    private Integer telephoneRepresentant;
    private String bpRepresentant;
    private Date date;
    
    private Integer idContribuable;
    private String nomContribuable;
    private String prenomContribuable;
    
    private Contribuable contribuable;
    private List<Contribuable> contribuableList;
    
    public Representant() {
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

    public Integer getTelephoneRepresentant() {
        return telephoneRepresentant;
    }

    public void setTelephoneRepresentant(Integer telephoneRepresentant) {
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

    public Contribuable getContribuable() {
        return contribuable;
    }

    public void setContribuable(Contribuable contribuable) {
        this.contribuable = contribuable;
    }

    public List<Contribuable> getContribuableList() {
        return contribuableList;
    }

    public void setContribuableList(List<Contribuable> contribuableList) {
        this.contribuableList = contribuableList;
    }

}
