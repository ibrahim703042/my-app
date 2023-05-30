/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
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
    private Integer nif;
    private Integer ccf;
    private String contribuable;
   
    private Date date_1;
    @Future
    private Date date_2;
    
    @PostConstruct
    public void init() {
      
        date_1 = new Date();
        
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

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public Integer getCcf() {
        return ccf;
    }

    public void setCcf(Integer ccf) {
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
    
}
