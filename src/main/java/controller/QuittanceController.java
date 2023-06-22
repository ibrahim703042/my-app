/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.Quittance;
import util.QuittanceDbUtil;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
@SessionScoped
public class QuittanceController implements Serializable {
    private Quittance quittance;
    private List<Quittance> quittanceList;
    
    @Inject
    private QuittanceDbUtil quittanceDbUtil;
    
    @PostConstruct
    public void init(){
        this.quittanceList = this.quittanceDbUtil.findAll();
    }

    public Quittance getQuittance() {
        return quittance;
    }

    public void setQuittance(Quittance quittance) {
        this.quittance = quittance;
    }

    public List<Quittance> getQuittanceList() {
        return quittanceList;
    }
    
    
}
