/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Administrateur;
import model.Colline;
import model.Commune;
import model.Contribuable;
import model.Declaration;
import model.Payement;
import model.Province;
import model.Quittance;
import model.Representant;
import model.Role;
import util.DashboardDbUtil;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@SessionScoped

public class DashboardController extends DashboardDbUtil implements Serializable {
    
    private static List<Role> roles;
    private static List<Province> provinces;
    private static List<Commune> communes;
    private static List<Colline> collines;
    
    private static List<Administrateur> administrateurs;
    private static List<Contribuable> contribuables;
    private static List<Representant> representants;
    
    private static List<Declaration> declarations;
    private static List<Payement> paidPayements;
    private static List<Payement> unpaidPayements;
    private static List<Quittance> quittances;
    
    public DashboardController() {
        
        roles = DashboardDbUtil.countRole();
        provinces = DashboardDbUtil.countProvince();
        communes = DashboardDbUtil.countCommune();
        collines = DashboardDbUtil.countColline();
        
//        administrateurs = DashboardDbUtil.countAdministrateur();
        contribuables = DashboardDbUtil.countContribuable();
        representants = DashboardDbUtil.countRepresentant();
        
        declarations = DashboardDbUtil.countDeclaration();
        paidPayements = DashboardDbUtil.countPaidPayement();
        unpaidPayements = DashboardDbUtil.countUnpaidPayement();
        quittances = DashboardDbUtil.countQuittance();
        
    }
    
    public void administrateur() {
        DashboardDbUtil.countAdministrateur();
    }
    // ************************ Getters && Setters **********************

    public static List<Role> getRoles() {
        return roles;
    }

    public static List<Province> getProvinces() {
        return provinces;
    }

    public static List<Commune> getCommunes() {
        return communes;
    }

    public static List<Colline> getCollines() {
        return collines;
    }

    public static List<Administrateur> getAdministrateurs() {
        return administrateurs;
    }

    public static List<Contribuable> getContribuables() {
        return contribuables;
    }

    public static List<Representant> getRepresentants() {
        return representants;
    }

    public static List<Declaration> getDeclarations() {
        return declarations;
    }

    public static List<Payement> getPaidPayements() {
        return paidPayements;
    }

    public static List<Payement> getUnpaidPayements() {
        return unpaidPayements;
    }

    public static List<Quittance> getQuittances() {
        return quittances;
    }
    
    
}
