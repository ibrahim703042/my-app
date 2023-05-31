/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Impot implements Serializable{

    private Integer id;
    private int idSLocation;
    private Date impDPaye;
    
    private double tranche_1;
    private double tranche_2;
    private double tranche_3;
    
    private double impotTotalDu;
    private double accompteImpotDejaPaye;
    private double totalRestantDu;

    public Impot() {
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

    public Date getImpDPaye() {
        return impDPaye;
    }

    public void setImpDPaye(Date impDPaye) {
        this.impDPaye = impDPaye;
    }

    public double getTranche_1() {
        return tranche_1;
    }

    public void setTranche_1(double tranche_1) {
        this.tranche_1 = tranche_1;
    }

    public double getTranche_2() {
        return tranche_2;
    }

    public void setTranche_2(double tranche_2) {
        this.tranche_2 = tranche_2;
    }

    public double getTranche_3() {
        return tranche_3;
    }

    public void setTranche_3(double tranche_3) {
        this.tranche_3 = tranche_3;
    }

    public double getImpotTotalDu() {
        return impotTotalDu;
    }

    public void setImpotTotalDu(double impotTotalDu) {
        this.impotTotalDu = impotTotalDu;
    }

    public double getAccompteImpotDejaPaye() {
        return accompteImpotDejaPaye;
    }

    public void setAccompteImpotDejaPaye(double accompteImpotDejaPaye) {
        this.accompteImpotDejaPaye = accompteImpotDejaPaye;
    }

    public double getTotalRestantDu() {
        return totalRestantDu;
    }

    public void setTotalRestantDu(double totalRestantDu) {
        this.totalRestantDu = totalRestantDu;
    }

}
