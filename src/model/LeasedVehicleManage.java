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
public class LeasedVehicleManage {
    private String vehicle_ID;
    private String payment_date;
    private float last_payment_amount;
    private String next_due_date;
    private float remaining_amount;
    private boolean Fully_paid;

    public LeasedVehicleManage() {
    }

    public LeasedVehicleManage(String vehicle_ID, String payment_date, float last_payment_amount, String next_due_date, float remaining_amount, boolean Fully_paid) {
        this.vehicle_ID = vehicle_ID;
        this.payment_date = payment_date;
        this.last_payment_amount = last_payment_amount;
        this.next_due_date = next_due_date;
        this.remaining_amount = remaining_amount;
        this.Fully_paid = Fully_paid;
    }

    public String getVehicle_ID() {
        return vehicle_ID;
    }

    public void setVehicle_ID(String vehicle_ID) {
        this.vehicle_ID = vehicle_ID;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public float getLast_payment_amount() {
        return last_payment_amount;
    }

    public void setLast_payment_amount(float last_payment_amount) {
        this.last_payment_amount = last_payment_amount;
    }

    public String getNext_due_date() {
        return next_due_date;
    }

    public void setNext_due_date(String next_due_date) {
        this.next_due_date = next_due_date;
    }

    public float getRemaining_amount() {
        return remaining_amount;
    }

    public void setRemaining_amount(float remaining_amount) {
        this.remaining_amount = remaining_amount;
    }

    public boolean isFully_paid() {
        return Fully_paid;
    }

    public void setFully_paid(boolean Fully_paid) {
        this.Fully_paid = Fully_paid;
    }
    

}
