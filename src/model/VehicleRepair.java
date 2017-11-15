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
public class VehicleRepair {
    
        private String vehicle_ID;
        private float cost;
        private String Date;
        private String Description;

    public VehicleRepair() {
    }

    public VehicleRepair(String vehicle_ID, float cost, String Date, String Description) {
        this.vehicle_ID = vehicle_ID;
        this.cost = cost;
        this.Date = Date;
        this.Description = Description;
    }

    public String getVehicle_ID() {
        return vehicle_ID;
    }

    public void setVehicle_ID(String vehicle_ID) {
        this.vehicle_ID = vehicle_ID;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
        
        
}
