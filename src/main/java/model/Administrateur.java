/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.faces.bean.ManagedBean;
import jakarta.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Ibrahim
 */

@ManagedBean
public class Administrateur implements Serializable{

    private Integer id;
    private String nom;
    private String prenom;
    private String ceerPar;
    
    @Email
    private String email;
    private String motPasse;
    private Integer telephone;
    
    private String isActive;
    private String bp;
    
    public List<Role> roleList;
    public Role role;
    
    private List<Permission> permissionList;
    private Permission permission;
    
    private Integer permissionId;
    private boolean ajouter;
    private boolean supprimer;
    private boolean modifier;
    private boolean afficher;
            
    private Integer roleId;
    private String nomRole;
    private String descriptionRole;
    
    private Date date;

    public Administrateur() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCeerPar() {
        return ceerPar;
    }

    public void setCeerPar(String ceerPar) {
        this.ceerPar = ceerPar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getDescriptionRole() {
        return descriptionRole;
    }

    public void setDescriptionRole(String descriptionRole) {
        this.descriptionRole = descriptionRole;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public boolean isAjouter() {
        return ajouter;
    }

    public void setAjouter(boolean ajouter) {
        this.ajouter = ajouter;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public boolean isModifier() {
        return modifier;
    }

    public void setModifier(boolean modifier) {
        this.modifier = modifier;
    }

    public boolean isAfficher() {
        return afficher;
    }

    public void setAfficher(boolean afficher) {
        this.afficher = afficher;
    }

        
}
