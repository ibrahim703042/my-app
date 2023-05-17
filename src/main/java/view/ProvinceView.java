/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package view;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.*;
import model.Province;
import service.ProvinceService;


/**
 *
 * @author Ibrahim
 */

@Named
@ViewScoped

public class ProvinceView implements Serializable {

    private List<Province> provinces;

    //private Province selectedProvince;
    //private List<Province> selectedProvinces;

    @Inject
    private ProvinceService provinceService;

    @PostConstruct
    public void init() {
        this.provinces = this.provinceService.getProvinces();
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }
   
    
    /*
    public void save() {
        if (this.selectedProvince.getNomProvince() == null) {
            //this.selectedProvince.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            //this.Provinces.add(this.selectedProvince);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Province Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Province Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProvinceDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Provinces");
    }

    public void deleteProvince() {
        this.provinces.remove(this.selectedProvince);
        this.selectedProvinces.remove(this.selectedProvince);
        this.selectedProvince = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Province Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Provinces");
    }

    public String getDeleteButtonMessage() {
        if (hasselectedProvinces()) {
            int size = this.selectedProvinces.size();
            return size > 1 ? size + " Provinces selected" : "1 provincesprovinceProvince selected";
        }

        return "Delete";
    }

    public boolean hasselectedProvinces() {
        return this.selectedProvinces != null && !this.selectedProvinces.isEmpty();
    }

    public void deleteselectedProvinces() {
        this.provinces.removeAll(this.selectedProvinces);
        this.selectedProvinces = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Provinces Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-Provinces");
        PrimeFaces.current().executeScript("PF('provinceView').clearFilters()");
    }
    */
   
}
