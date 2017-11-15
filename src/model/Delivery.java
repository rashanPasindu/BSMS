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
    
    private String deliveryID;
    private String Item_Code;
    private String Item_Name;
    private int QTY;
    private String payment_Status;
    private String address;
    private String delivery_Status;
    private int invoice_ID;
    private String customer_ID;

    public Delivery() {
    }

    public Delivery(String deliveryID, String Item_Code, String Item_Name, int QTY, String payment_Status, String address, String delivery_Status, int invoice_ID, String customer_ID) {
        this.deliveryID = deliveryID;
        this.Item_Code = Item_Code;
        this.Item_Name = Item_Name;
        this.QTY = QTY;
        this.payment_Status = payment_Status;
        this.address = address;
        this.delivery_Status = delivery_Status;
        this.invoice_ID = invoice_ID;
        this.customer_ID = customer_ID;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public String getItem_Code() {
        return Item_Code;
    }

    public void setItem_Code(String Item_Code) {
        this.Item_Code = Item_Code;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
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

    public String getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        this.customer_ID = customer_ID;
    }


}
