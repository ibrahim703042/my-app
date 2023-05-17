/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



/**
 *
 * @author Ibrahim
 */


@Named(value = "credentials")
@RequestScoped

public class Credentials {

  private String username;
  private String password;
  
  @NotNull 
  @Size(min=3, max=25)
  public String getUsername() { 
    return username; 
  }
  
  public void setUsername(String username) { 
    this.username = username; 
  }
  
  @NotNull
  @Size(min=6, max=20)
  public String getPassword() { 
    return password; 
  }
  
  public void setPassword(String password) { 
    this.password = password; 
  }

}
