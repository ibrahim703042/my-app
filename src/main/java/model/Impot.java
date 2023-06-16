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
    private Date date;
    
    private double tranche_1;
    private double tranche_2;
    private double tranche_3;
    
    private double montant_Col_3;
    
    private double tranche_1_Col_5;
    private double tranche_2_Col_5;
    private double tranche_3_Col_5;
    
    private double impotTotalDu;
    private double accompteImpotDejaPaye;
    private double totalRestantDu;
    
    private RevenuSousLocation revenuSousLocation;
    private int idRevenuSousLocation;
    private double revenuSousNetImposable;
    
    public Impot() {
    
        this.revenuSousLocation = new RevenuSousLocation();
              
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdRevenuSousLocation() {
        return idRevenuSousLocation;
    }

    public void setIdRevenuSousLocation(int idRevenuSousLocation) {
        this.idRevenuSousLocation = idRevenuSousLocation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTranche_1() {
       return tranche_1;
    }

    public void setTranche_1(double tranche_1) {
        this.tranche_1 = tranche_1;
    }

    public double getTranche_2() {
//        if(
//            this.tranche_1 >= 1800000 && 
//            this.tranche_1 < 3600000 
//        ){
//            return tranche_2 = this.tranche_1;
//        }else{
//            return tranche_2 - tranche_1;
//        }
        return tranche_2;
    }

    public void setTranche_2(double tranche_2) {
        this.tranche_2 = tranche_2;
    }

    public double getTranche_3() {
//        if(this.tranche_2 >= 3600000 ){
//            return tranche_3 = this.tranche_2;
//        }
//        return 0;
        return tranche_3;
    }

    public void setTranche_3(double tranche_3) {
        this.tranche_3 = tranche_3;
    }

    public double getMontant_Col_3() {
        return montant_Col_3;
    }

    public void setMontant_Col_3(double montant_Col_3) {
        this.montant_Col_3 = montant_Col_3;
    }

    public double getTranche_1_Col_5() {
        return tranche_1_Col_5;
    }

    public void setTranche_1_Col_5(double tranche_1_Col_5) {
        this.tranche_1_Col_5 = tranche_1_Col_5;
    }

    public double getTranche_2_Col_5() {
        return tranche_2_Col_5;
    }

    public void setTranche_2_Col_5(double tranche_2_Col_5) {
        this.tranche_2_Col_5 = tranche_2_Col_5;
    }

    public double getTranche_3_Col_5() {
        return tranche_3_Col_5;
    }

    public void setTranche_3_Col_5(double tranche_3_Col_5) {
        this.tranche_3_Col_5 = tranche_3_Col_5;
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

    public RevenuSousLocation getRevenuSousLocation() {
        return revenuSousLocation;
    }

    public void setRevenuSousLocation(RevenuSousLocation revenuSousLocation) {
        this.revenuSousLocation = revenuSousLocation;
    }

    public double getRevenuSousNetImposable() {
        return revenuSousNetImposable;
    }

    public void setRevenuSousNetImposable(double revenuSousNetImposable) {
        this.revenuSousNetImposable = revenuSousNetImposable;
    }

    
}
