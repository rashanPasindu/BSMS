/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Sandun
 */
public class LeasedVehicle {
    
    private String vehicle_ID;
    private float Initial_Pay;
    private float installment_Fee;
    private String leased_Company;
    private String installment_Scheme;
    private int noOFYears;
    private String startDate;
    private String endDate;
    private String Leased_Year;
    

    public LeasedVehicle() {
    }

    public LeasedVehicle(String vehicle_ID, float Initial_Pay, float installment_Fee, String leased_Company, String installment_Scheme, int noOFYears,String startDate, String endDate, String Leased_Year) {
        this.vehicle_ID = vehicle_ID;
        this.Initial_Pay = Initial_Pay;
        this.installment_Fee = installment_Fee;
        this.leased_Company = leased_Company;
        this.installment_Scheme = installment_Scheme;
        this.noOFYears = noOFYears;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Leased_Year = Leased_Year;
      
    }

    public String getVehicle_ID() {
        return vehicle_ID;
    }

    public void setVehicle_ID(String vehicle_ID) {
        this.vehicle_ID = vehicle_ID;
    }

    public float getInitial_Pay() {
        return Initial_Pay;
    }

    public void setInitial_Pay(float Initial_Pay) {
        this.Initial_Pay = Initial_Pay;
    }

    public float getInstallment_Fee() {
        return installment_Fee;
    }

    public void setInstallment_Fee(float installment_Fee) {
        this.installment_Fee = installment_Fee;
    }

    public String getLeased_Company() {
        return leased_Company;
    }

    public void setLeased_Company(String leased_Company) {
        this.leased_Company = leased_Company;
    }

    public String getInstallment_Scheme() {
        return installment_Scheme;
    }

    public void setInstallment_Scheme(String installment_Scheme) {
        this.installment_Scheme = installment_Scheme;
    }

    public int getNoOFYears() {
        return noOFYears;
    }

    public void setNoOFYears(int noOFYears) {
        this.noOFYears = noOFYears;
    }
    
    public String getStartString() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLeased_Year() {
        return Leased_Year;
    }

    public void setLeased_Year(String Leased_Year) {
        this.Leased_Year = Leased_Year;
    }

   
        
}
