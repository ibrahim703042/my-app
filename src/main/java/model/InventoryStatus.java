/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package model;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

/**
 *
 * @author Ibrahim
 */
@Named(value = "inventoryStatus")
@Dependent
public enum InventoryStatus {
    INSTOCK,
    LOWSTOCK,
    OUTOFSTOCK;
        
}
