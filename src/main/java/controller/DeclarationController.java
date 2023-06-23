/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import model.Declaration;
import util.DeclarationDbUtil;

/**
 *
 * @author Ibrahim
 * 
 */

@ManagedBean
@SessionScoped

public class DeclarationController implements Serializable {

    public ArrayList declarations;
    private Declaration declaration;
    
    @Inject
    private DeclarationDbUtil declarationDbUtil;
    
    //************************ display data **************************/
    @PostConstruct
    public void init() {
        declarations = declarationDbUtil.findAll();
        //declaration = new Declaration();
    }

    public void autoNIF() {
        declarationDbUtil.autoNIF();
    }
    
    public ArrayList declarationList() {
        
        declarations.clear();
        declarations = declarationDbUtil.findAll();
        return declarations;
    }
        
    //************************ save data **************************/
    public String save(Declaration declaration) {
        
        declarationDbUtil.save(declaration);
        return "/pages/declaration/template.xhtml?faces-redirect=true";
        
    }
    	
    //************************  edit data by Id  **************************/
    public String edit(int id) {
            
        declarationDbUtil.findById(id);
        return "/pages/declaration/edit.xhtml?faces-redirect=true";
    }
    
    //************************  edit data by Id  **************************/
    public String calculRevenu(int id) {
        declarationDbUtil.ViewById(id);
        return "/pages/declaration/declaration.xhtml?faces-redirect=true";
    }
    
    //************************ update data **************************/
    public String update(Declaration c) {
        
        declarationDbUtil.update(c);
        return "/pages/declaration/template.xhtml?faces-redirect=true";
        
    }
    
    ///************************ delete data **************************/
    public String delete(int id) {
        
        declarationDbUtil.delete(id);
        return "/pages/declaration/template.xhtml?faces-redirect=true";
    }
    
    
    public Declaration getDeclaration() {
        return declaration;
    }

    public void setDeclaration(Declaration declaration) {
        this.declaration = declaration;
    }
    
    
}
