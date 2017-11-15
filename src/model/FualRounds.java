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
public class FualRounds {
        
    private String vehicle_ID;
    private float cost;
    private String date;
    private float price_per_litre;
    private float total_litres;

    public FualRounds() {
    }

    public FualRounds(String vehicle_ID, float cost, String date, float price_per_litre, float total_litres) {
        this.vehicle_ID = vehicle_ID;
        this.cost = cost;
        this.date = date;
        this.price_per_litre = price_per_litre;
        this.total_litres = total_litres;
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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice_per_litre() {
        return price_per_litre;
    }

    public void setPrice_per_litre(float price_per_litre) {
        this.price_per_litre = price_per_litre;
    }

    public float getTotal_litres() {
        return total_litres;
    }

    public void setTotal_litres(float total_litres) {
        this.total_litres = total_litres;
    }

}
