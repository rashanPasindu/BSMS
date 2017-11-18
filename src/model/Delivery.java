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
public class Delivery {
    
    private int deliveryID;
    private String deliveryList;
    private String payment_Status;
    private String address;
    private String delivery_Status;
    private int invoice_ID;
    private int customer_ID;

    public Delivery() {
    }

    public Delivery(int deliveryID, String deliveryList, String payment_Status, String address, String delivery_Status, int invoice_ID, int customer_ID) {
        this.deliveryID = deliveryID;
        this.deliveryList = deliveryList;
        this.payment_Status = payment_Status;
        this.address = address;
        this.delivery_Status = delivery_Status;
        this.invoice_ID = invoice_ID;
        this.customer_ID = customer_ID;
    }

    public Delivery(String dlist, String payment, String address, String status, Integer invoiceId, Integer customer) {
         this.deliveryList = dlist;
        this.payment_Status = payment;
        this.address = address;
        this.delivery_Status = status;
        this.invoice_ID = invoiceId;
        this.customer_ID = customer;
    }

  

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(String deliveryList) {
        this.deliveryList = deliveryList;
    }

    public String getPayment_Status() {
        return payment_Status;
    }

    public void setPayment_Status(String payment_Status) {
        this.payment_Status = payment_Status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery_Status() {
        return delivery_Status;
    }

    public void setDelivery_Status(String delivery_Status) {
        this.delivery_Status = delivery_Status;
    }

    public int getInvoice_ID() {
        return invoice_ID;
    }

    public void setInvoice_ID(int invoice_ID) {
        this.invoice_ID = invoice_ID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    
}
