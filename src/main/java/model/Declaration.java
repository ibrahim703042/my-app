/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
public class Declaration implements Serializable {

    private Integer id;
    private Integer idImmeuble;
    private String nif;
    private String ccf;
    private String contribuable;
   
    private Date date_1;
    private Date date_2;
    
    private String nomAvenue;

    private int idColline;
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
    
    private String nomProvince;
    private String nomCommune;
    
    @PostConstruct
    public void init() {
      
        setDate_1(new Date());
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdImmeuble() {
        return idImmeuble;
    }

    public void setIdImmeuble(Integer idImmeuble) {
        this.idImmeuble = idImmeuble;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCcf() {
        return ccf;
    }

    public void setCcf(String ccf) {
        this.ccf = ccf;
    }

    public String getContribuable() {
        return contribuable;
    }

    public void setContribuable(String contribuable) {
        this.contribuable = contribuable;
    }

    public Date getDate_1() {
        return date_1;
    }

    public void setDate_1(Date date_1) {
        this.date_1 = date_1;
    }

    public Date getDate_2() {
        return date_2;
    }

    public void setDate_2(Date date_2) {
        this.date_2 = date_2;
    }

    public String getNomAvenue() {
        return nomAvenue;
    }

    public void setNomAvenue(String nomAvenue) {
        this.nomAvenue = nomAvenue;
    }

    public int getIdColline() {
        return idColline;
    }

    public void setIdColline(int idColline) {
        this.idColline = idColline;
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

}
