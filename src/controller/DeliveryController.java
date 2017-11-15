/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Delivery;

/**
 *
 * @author Sandun
 */
public class DeliveryController {
    
    static Connection con = DBconnect.connect();
    
     public static int addDelivery(Delivery delivery) throws SQLException{
        int result=0;
        String q = "INSERT INTO delivery(deliveryID,Item_Code,Item_Name,QTY,payment_Status,address,delivery_Status,invoice_ID,customer_ID) values ('" + delivery.getDeliveryID()+ "','" + delivery.getItem_Code()+ "','" + delivery.getItem_Name()+ "'," + delivery.getQTY()+ ", '" + delivery.getPayment_Status()+"',' " + delivery.getAddress()+" ', '" + delivery.getDelivery_Status()+"', " + delivery.getInvoice_ID()+",'" + delivery.getCustomer_ID()+"')";

         PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     
     public static int deleteDelivery(Delivery delivery) throws SQLException {
        int result=0;
        String q = "DELETE from delivery WHERE deliveryID='"+ delivery.getDeliveryID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
    
     public static int updateDelivery(Delivery delivery) throws SQLException{
        int result=0;
        String q = "UPDATE delivery  SET Item_Code='" + delivery.getItem_Code()+ "',Item_Name='" + delivery.getItem_Name()+ "',QTY=" + delivery.getQTY()+ ",payment_Status='" + delivery.getPayment_Status()+ "',address='" + delivery.getAddress()+ "',delivery_Status='" + delivery.getDelivery_Status()+ "',invoice_ID='" + delivery.getInvoice_ID()+ "',customer_ID='" + delivery.getCustomer_ID()+ "' where deliveryID='"+delivery.getDeliveryID()+ "' ";
//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
     public static ResultSet getAllDeliveryResultSet() throws SQLException{
        String sql="select deliveryID,Item_Code,Item_Name,QTY,payment_Status,address,delivery_Status,invoice_ID,customer_ID from delivery";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
    
     public static ResultSet searchDeliveryByInvoiceID(String Invoice_ID) throws SQLException{
        String sql="select deliveryID,Item_Code,Item_Name,QTY,payment_Status,address,delivery_Status,invoice_ID,customer_ID from delivery WHERE  CAST(invoice_ID AS CHAR)  LIKE '%"+Invoice_ID+"%'";
         
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
}
