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
public class Equipment {
    	
  private String equipmnt_ID;	
  private String equip_Name;	
  private String Brand;	
  private String Serial;	
  private Float Value;

    public Equipment() {
    }

    public Equipment(String equipmnt_ID, String equip_Name, String Brand, String Serial, Float Value) {
        this.equipmnt_ID = equipmnt_ID;
        this.equip_Name = equip_Name;
        this.Brand = Brand;
        this.Serial = Serial;
        this.Value = Value;
    }

    public String getEquipmnt_ID() {
        return equipmnt_ID;
    }

    public void setEquipmnt_ID(String equipmnt_ID) {
        this.equipmnt_ID = equipmnt_ID;
    }

    public String getEquip_Name() {
        return equip_Name;
    }

    public void setEquip_Name(String equip_Name) {
        this.equip_Name = equip_Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    public Float getValue() {
        return Value;
    }

    public void setValue(Float Value) {
        this.Value = Value;
    }
      
}