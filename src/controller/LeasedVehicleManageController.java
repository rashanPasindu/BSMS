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
import model.LeasedVehicle;
import model.LeasedVehicleManage;

/**
 *
 * @author Sandun
 */
public class LeasedVehicleManageController {
    
    static Connection con = DBconnect.connect();
    
     public static int addLeasedVehicleManage(LeasedVehicleManage leasedvehiclemanage) throws SQLException{
        int result=0;
        String q = "INSERT INTO leased_vehicle_management(vehicle_ID,payment_date,last_payment_amount,next_due_date,remaining_amount,Fully_paid) values ('" + leasedvehiclemanage.getVehicle_ID()+ "','" + leasedvehiclemanage.getPayment_date()+ "'," + leasedvehiclemanage.getLast_payment_amount()+ ",'" + leasedvehiclemanage.getNext_due_date()+ "'," + leasedvehiclemanage.getRemaining_amount()+ ",'" + (leasedvehiclemanage.isFully_paid()== true ? 1 :0)+ "') ";

         PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     
     public static int deleteLeasedVehicleManage(LeasedVehicleManage leasedvehiclemanage) throws SQLException {
        int result=0;
        String q = "DELETE from leased_vehicle_management WHERE vehicle_ID='"+leasedvehiclemanage.getVehicle_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
    
     public static int updateLeasedVehicleManage(LeasedVehicleManage leasedvehiclemanage) throws SQLException{
        int result=0;
        String q = "UPDATE leased_vehicle_management  SET payment_date='" + leasedvehiclemanage.getPayment_date()+ "',last_payment_amount=" + leasedvehiclemanage.getLast_payment_amount()+ ",next_due_date='" + leasedvehiclemanage.getNext_due_date()+ "',remaining_amount=" + leasedvehiclemanage.getRemaining_amount()+ ",Fully_paid='" + (leasedvehiclemanage.isFully_paid()== true ? 1 :0)+ "' where vehicle_ID='"+leasedvehiclemanage.getVehicle_ID()+"' ";
                                                                                                                                                                                                                                                                            
        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }

    public static ResultSet getAllLeasedVehicleManageAsResultSet() throws SQLException {
        String sql="select * from leased_vehicle_management";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    
   }
    
    public static ResultSet searchVehicleByID(String vehicle_ID) throws SQLException{
        String sql="select vehicle_ID,payment_date,last_payment_amount,next_due_date,remaining_amount,Fully_paid from leased_vehicle_management WHERE vehicle_ID = '"+vehicle_ID+"' ";
         
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
}