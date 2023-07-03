/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import util.DashboardDbUtil;

/**
 *
 * @author Ibrahim
 */
@ManagedBean
@SessionScoped

public class DashboardController extends DashboardDbUtil implements Serializable{
    private Integer countAdministrateur;
    
    public DashboardController(){
        
    }

    public Integer getCountAdministrateur() {
        return countAdministrateur;
    }

    public void setCountAdministrateur(Integer countAdministrateur) {
        this.countAdministrateur = countAdministrateur;
    }
    
    
}


