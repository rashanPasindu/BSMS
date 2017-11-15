package bill;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class billInsert {
    private String ItemCode;
    private String invoiceId;
    private float mrp;
    private float quantity;
    private String ItemName;
    private float discount;
    private float discount_allowed;
    private float netprice;
    private String date;
    
    public billInsert( String ItemCode,String invoiceId,float mrp,float quantity,String ItemName,float discount,float discount_allowed,float netprice,String date)
    {
        this.ItemCode=ItemCode;
        this.invoiceId=invoiceId;
        this.mrp=mrp;
        this.quantity=quantity;
        this.ItemName=ItemName;
        this.discount=discount;
        this.discount_allowed=discount_allowed;
        this.netprice=netprice;
        this.date=date;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getDiscount_allowed() {
        return discount_allowed;
    }

    public void setDiscount_allowed(float discount_allowed) {
        this.discount_allowed = discount_allowed;
    }

    public float getNetprice() {
        return netprice;
    }

    public void setNetprice(float netprice) {
        this.netprice = netprice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
