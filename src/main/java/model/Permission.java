/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ibrahim
 */

public class Permission{

    private Integer id;
    private short ajouter;
    private short supprimer;
    private short modifier;
    private short afficher;
    private Role idRole;

    public Permission() {
    }

    public Permission(Integer id) {
        this.id = id;
    }

    public Permission(Integer id, short ajouter, short supprimer, short modifier, short afficher) {
        this.id = id;
        this.ajouter = ajouter;
        this.supprimer = supprimer;
        this.modifier = modifier;
        this.afficher = afficher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getAjouter() {
        return ajouter;
    }

    public void setAjouter(short ajouter) {
        this.ajouter = ajouter;
    }

    public short getSupprimer() {
        return supprimer;
    }

    public void setSupprimer(short supprimer) {
        this.supprimer = supprimer;
    }

    public short getModifier() {
        return modifier;
    }

    public void setModifier(short modifier) {
        this.modifier = modifier;
    }

    public short getAfficher() {
        return afficher;
    }

    public void setAfficher(short afficher) {
        this.afficher = afficher;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Permission[ id=" + id + " ]";
    }
    
}
