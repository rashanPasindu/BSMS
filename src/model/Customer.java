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

    private int customer_ID;
    private String customer_Name;
    private String Address;
    private String tel_number;

    public Customer() {
    }

    public Customer(int customer_ID, String customer_Name, String Address, String tel_number) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.Address = Address;
        this.tel_number = tel_number;
    }

    public Customer(String name, String address, String tel_no) {
        this.customer_Name = name;
        this.Address = address;
        this.tel_number = tel_no;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
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

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

}
