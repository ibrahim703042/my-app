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
import model.Amende;
import util.AmendeDbUtil;

/**
 *
 * @author Ibrahim
 */
@ManagedBean
@SessionScoped

public class AmendeController extends MessageController implements Serializable{
    
    private List<Amende> listAmende;
    private Amende modelAmende;
    
    @Inject
    private AmendeDbUtil amendeDbUtil;
    
    @PostConstruct
    public void init(){
        listAmende = this.amendeDbUtil.findAll();
    }

    public List<Amende> getListAmende() {
        return listAmende;
    }

    public Amende getModelAmende() {
        return modelAmende;
    }

    public void setModelAmende(Amende modelAmende) {
        this.modelAmende = modelAmende;
    }
    
}
