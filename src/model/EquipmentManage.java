/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Sandun
 */
public class EquipmentManage {
     private String equipment_ID;
     private String Description;
     private String Date;
     private float Cost;

    public EquipmentManage() {
    }

    public EquipmentManage(String equipment_ID, String Description, String Date, float Cost) {
        this.equipment_ID = equipment_ID;
        this.Description = Description;
        this.Date = Date;
        this.Cost = Cost;
    }

    public String getEquipment_ID() {
        return equipment_ID;
    }

    public void setEquipment_ID(String equipment_ID) {
        this.equipment_ID = equipment_ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float Cost) {
        this.Cost = Cost;
    }

    
}
