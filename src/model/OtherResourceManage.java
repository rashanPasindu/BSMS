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
public class OtherResourceManage {
        
    private String resource_ID;
    private String Description;
    private String Date;
    private float Cost;

    public OtherResourceManage() {
    }

    public OtherResourceManage(String resource_ID, String Description, String Date, float Cost) {
        this.resource_ID = resource_ID;
        this.Description = Description;
        this.Date = Date;
        this.Cost = Cost;
    }

    public String getResource_ID() {
        return resource_ID;
    }

    public void setResource_ID(String resource_ID) {
        this.resource_ID = resource_ID;
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
