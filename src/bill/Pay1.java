package bill;


import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class Pay1 {
    
     Float saleAmount1;
     Float quty1;
     Float discount1;
     Float total;
     Float mony;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getMony() {
        return mony;
    }

    public void setMony(Float mony) {
        this.mony = mony;
    }
     
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Statement st=null;
    
    public Pay1()
    {
         con = DBconnect.connect();
    }

    public Float getSaleAmount1() {
        return saleAmount1;
    }

    public void setSaleAmount1(Float saleAmount1) {
        this.saleAmount1 = saleAmount1;
    }

    public Float getQuty1() {
        return quty1;
    }

    public void setQuty1(Float quty1) {
        this.quty1 = quty1;
    }

    public Float getDiscount1() {
        return discount1;
    }

    public void setDiscount1(Float discount1) {
        this.discount1 = discount1;
    }
    
    public double getBalance()
    {
        return mony-total;
    }

  
     
     
     
   public double cal()
   {
    
       return ((saleAmount1*quty1)-((saleAmount1*quty1)*discount1/100)); 
   }
    
   
   public double cal2()
   {
       return ((saleAmount1*quty1)*discount1/100);
   }
}
