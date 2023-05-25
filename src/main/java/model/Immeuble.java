/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Immeuble implements Serializable{

    private Integer id;
    private int idColline;
    private Integer id_immeuble;
    private String nomAvenue;

    private String nomProvince;
    private String nomCommune;
    private String nomColline;
    
    private Integer idContribuable;
    private String bpContribuable;
    private String nomContribuable;
    private String prenomContribuable;
    private String telephoneContribuable;
    private String EmailContribuable;
    
    private Integer idRepresentant;
    private String bpRepresentant;
    private String nomRepresentant;
    private String prenomRepresentant;
    private String telephoneRepresentant;
    private String EmailRepresentant;

    public Immeuble() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdColline() {
        return idColline;
    }

    public void setIdColline(int idColline) {
        this.idColline = idColline;
    }

    public Integer getId_immeuble() {
        return id_immeuble;
    }

    public void setId_immeuble(Integer id_immeuble) {
        this.id_immeuble = id_immeuble;
    }

    public String getNomAvenue() {
        return nomAvenue;
    }

    public void setNomAvenue(String nomAvenue) {
        this.nomAvenue = nomAvenue;
    }

    public String getNomProvince() {
        return nomProvince;
    }

    public void setNomProvince(String nomProvince) {
        this.nomProvince = nomProvince;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getNomColline() {
        return nomColline;
    }

    public void setNomColline(String nomColline) {
        this.nomColline = nomColline;
    }

    public Integer getIdContribuable() {
        return idContribuable;
    }

    public void setIdContribuable(Integer idContribuable) {
        this.idContribuable = idContribuable;
    }

    public String getBpContribuable() {
        return bpContribuable;
    }

    public void setBpContribuable(String bpContribuable) {
        this.bpContribuable = bpContribuable;
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

    public String getTelephoneContribuable() {
        return telephoneContribuable;
    }

    public void setTelephoneContribuable(String telephoneContribuable) {
        this.telephoneContribuable = telephoneContribuable;
    }

    public String getEmailContribuable() {
        return EmailContribuable;
    }

    public void setEmailContribuable(String EmailContribuable) {
        this.EmailContribuable = EmailContribuable;
    }

    public Integer getIdRepresentant() {
        return idRepresentant;
    }

    public void setIdRepresentant(Integer idRepresentant) {
        this.idRepresentant = idRepresentant;
    }

    public String getBpRepresentant() {
        return bpRepresentant;
    }

    public void setBpRepresentant(String bpRepresentant) {
        this.bpRepresentant = bpRepresentant;
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

    public String getTelephoneRepresentant() {
        return telephoneRepresentant;
    }

    public void setTelephoneRepresentant(String telephoneRepresentant) {
        this.telephoneRepresentant = telephoneRepresentant;
    }

    public String getEmailRepresentant() {
        return EmailRepresentant;
    }

    public void setEmailRepresentant(String EmailRepresentant) {
        this.EmailRepresentant = EmailRepresentant;
    }

    
}
