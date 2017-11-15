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
public class Vehicle {
    
    private String vehicle_ID;	
    private String vehicle_type;	
    private String vehicle_number;
    private int vehicle_cost;
    private LeasedVehicle leasedVehicle;

    public Vehicle() {
    }

    public Vehicle(String vehicle_ID, String vehicle_type, String vehicle_number, int vehicle_cost) {
        this.vehicle_ID = vehicle_ID;
        this.vehicle_type = vehicle_type;
        this.vehicle_number = vehicle_number;
        this.vehicle_cost = vehicle_cost;
    }

    public Vehicle(String vehicle_ID, String vehicle_type, String vehicle_number, int vehicle_cost, LeasedVehicle leasedVehicle) {
        this.vehicle_ID = vehicle_ID;
        this.vehicle_type = vehicle_type;
        this.vehicle_number = vehicle_number;
        this.vehicle_cost = vehicle_cost;
        this.leasedVehicle = leasedVehicle;
    }
    
    

    public String getVehicle_ID() {
        return vehicle_ID;
    }

    public void setVehicle_ID(String vehicle_ID) {
        this.vehicle_ID = vehicle_ID;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public int getVehicle_cost() {
        return vehicle_cost;
    }

    public void setVehicle_cost(int vehicle_cost) {
        this.vehicle_cost = vehicle_cost;
    }

    public LeasedVehicle getLeasedVehicle() {
        return leasedVehicle;
    }

    public void setLeasedVehicle(LeasedVehicle leasedVehicle) {
        this.leasedVehicle = leasedVehicle;
    }
    
}
