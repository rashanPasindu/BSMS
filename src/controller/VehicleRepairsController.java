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
import model.VehicleRepair;

/**
 *
 * @author Sandun
 */
public class VehicleRepairsController {
    
    static Connection con = DBconnect.connect();

    public static int addVehicleRepairs(VehicleRepair vehiclerepair) throws SQLException {
        int result = 0;
        String q = "INSERT INTO repairs(vehicle_ID,cost,Date,Description) values ('" + vehiclerepair.getVehicle_ID()+ "','" + vehiclerepair.getCost()+ "','" + vehiclerepair.getDate()+ "','" + vehiclerepair.getDescription()+ "')";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static int deleteVehicleRepairs(VehicleRepair vehiclerepair) throws SQLException {
        int result = 0;
        String q = "DELETE from repairs WHERE vehicle_ID='" + vehiclerepair.getVehicle_ID()+ "'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static int updateVehicleRepairs(VehicleRepair vehiclerepair) throws SQLException {
        int result = 0;
        String q = "UPDATE repairs  SET cost='" + vehiclerepair.getCost()+ "',Date='" + vehiclerepair.getDate()+ "',Description=" + vehiclerepair.getDescription()+ ", where vehicle_ID='" + vehiclerepair.getVehicle_ID()+ "' ";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }
    
    
    public static ResultSet getAllRepairsAsResultSet() throws SQLException{
        String sql="select vehicle_ID,Description,Date,cost from repairs";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
}
