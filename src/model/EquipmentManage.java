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
     private String equipmnt_ID;
     private String Description;
     private String Date;
     private float Cost;

    public EquipmentManage() {
    }

    public EquipmentManage(String equipmnt_ID, String Description, String Date, float Cost) {
        this.equipmnt_ID = equipmnt_ID;
        this.Description = Description;
        this.Date = Date;
        this.Cost = Cost;
    }

    public String getEquipmnt_ID() {
        return equipmnt_ID;
    }

    public void setEquipmnt_ID(String equipmnt_ID) {
        this.equipmnt_ID = equipmnt_ID;
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
