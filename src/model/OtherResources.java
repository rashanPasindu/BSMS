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
public class OtherResources {
    
   private String resource_ID;
   private String type;
   private String description;
   private float value;
   private String Aquired_Date;
   private String lastdateupdated;

    public OtherResources() {
    }

    public OtherResources(String resource_ID, String type, String description, float value, String Aquired_Date, String lastdateupdated) {
        this.resource_ID = resource_ID;
        this.type = type;
        this.description = description;
        this.value = value;
        this.Aquired_Date = Aquired_Date;
        this.lastdateupdated = lastdateupdated;
    }

    public String getResource_ID() {
        return resource_ID;
    }

    public void setResource_ID(String resource_ID) {
        this.resource_ID = resource_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getAquired_Date() {
        return Aquired_Date;
    }

    public void setAquired_Date(String Aquired_Date) {
        this.Aquired_Date = Aquired_Date;
    }

    public String getLastdateupdated() {
        return lastdateupdated;
    }

    public void setLastdateupdated(String lastdateupdated) {
        this.lastdateupdated = lastdateupdated;
    }
   


    
}
