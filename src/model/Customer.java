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
public class Customer {
    
   private String customer_ID;
   private String customer_Name;
   private String Address;
   private int tel_number;

    public Customer() {
    }

    public Customer(String customer_ID, String customer_Name, String Address, int tel_number) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.Address = Address;
        this.tel_number = tel_number;
    }

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getTel_number() {
        return tel_number;
    }

    public void setTel_number(int tel_number) {
        this.tel_number = tel_number;
    }
        
}
