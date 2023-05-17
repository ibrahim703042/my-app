/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package model;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Ibrahim
 */
@Named(value = "navigationController")
@ViewScoped
public class NavigationController implements Serializable {
    
    private String adminId;
    private String addAdminId;
    private String editAdminId;
    private String contribuableId;
    private String addContribuableId;
    private String editContribuableId;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAddAdminId() {
        return addAdminId;
    }

    public void setAddAdminId(String addAdminId) {
        this.addAdminId = addAdminId;
    }

    public String getEditAdminId() {
        return editAdminId;
    }

    public void setEditAdminId(String editAdminId) {
        this.editAdminId = editAdminId;
    }

    public String getContribuableId() {
        return contribuableId;
    }

    public void setContribuableId(String contribuableId) {
        this.contribuableId = contribuableId;
    }

    public String getAddContribuableId() {
        return addContribuableId;
    }

    public void setAddContribuableId(String addContribuableId) {
        this.addContribuableId = addContribuableId;
    }

    public String getEditContribuableId() {
        return editContribuableId;
    }

    public void setEditContribuableId(String editContribuableId) {
        this.editContribuableId = editContribuableId;
    }

    
    
    
    
}
