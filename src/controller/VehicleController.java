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
import model.Vehicle;

/**
 *
 * @author Sandun
 */
public class VehicleController {
    
    static Connection con = DBconnect.connect();
    
    public static int addNormalVehicle(Vehicle vehicle) throws SQLException{
        int result=0;
        String q = "INSERT INTO vehicle(vehicle_ID,vehicle_type,vehicle_number,vehicle_cost) values ('" + vehicle.getVehicle_ID()+ "','" + vehicle.getVehicle_type()+ "','" + vehicle.getVehicle_number()+ "'," + vehicle.getVehicle_cost()+ ")";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
    public static int deleteNormalVehicle(Vehicle vehicle) throws SQLException {
        int result=0;
        String q = "DELETE from vehicle WHERE vehicle_ID='"+vehicle.getVehicle_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
    public static int updateNormalVehicle(Vehicle vehicle) throws SQLException{
        int result=0;
        String q = "UPDATE vehicle  SET vehicle_type='" + vehicle.getVehicle_type()+ "',vehicle_number='" + vehicle.getVehicle_number()+ "',vehicle_cost=" + vehicle.getVehicle_cost()+ " where vehicle_ID='"+vehicle.getVehicle_ID()+"' ";
                                                                                                               
        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     public static ResultSet getAllNormalVAsResultSet() throws SQLException{
        String sql="select * from vehicle WHERE vehicle_ID NOT IN (SELECT vehicle_ID  FROM leased_vehicle ) ";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
    
    public static int addLeasedVehicle(Vehicle vehicle) throws SQLException{
        int result= addNormalVehicle(vehicle);
        if(result == 1){
            result= LeasedVehicleController.addLeasedVehicle(vehicle.getLeasedVehicle());
            
            if(result == -1){
                deleteNormalVehicle(vehicle);
            }
        }
        
        return result;
    }
    
     public static int updateLeasedVehicle(Vehicle vehicle) throws SQLException{
        int result= updateNormalVehicle(vehicle);
        if(result == 1){
            result= LeasedVehicleController.updateLeasedVehicle(vehicle.getLeasedVehicle());
           
        }
        
        return result;
    }
     
    public static int deleteLeasedVehicle(Vehicle vehicle) throws SQLException{
        if(vehicle.getLeasedVehicle()== null){
            LeasedVehicle leasedVehicle=new LeasedVehicle();
            leasedVehicle.setVehicle_ID(vehicle.getVehicle_ID());
            vehicle.setLeasedVehicle(leasedVehicle);
        }
        int result= LeasedVehicleController.deleteLeasedVehicle(vehicle.getLeasedVehicle());
        if(result == 1){
            result= deleteNormalVehicle(vehicle);
           
        }
//        int result= deleteNormalVehicle(vehicle);
        return result;
    }
    
    
     public static ResultSet getAllLeasedVAsResultSet() throws SQLException{
        String sql="select vehicle.vehicle_ID,vehicle.vehicle_type,vehicle.vehicle_number,vehicle.vehicle_cost,"
                + "leased_vehicle.Initial_Pay,leased_vehicle.installment_Fee,leased_vehicle.leased_Company,leased_vehicle.installment_Scheme,leased_vehicle.noOFYears,leased_vehicle.startDate,leased_vehicle.endDate,leased_vehicle.Leased_Year"
                + " from vehicle INNER JOIN leased_vehicle ON vehicle.vehicle_ID =leased_vehicle.vehicle_ID  ";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
     
     
     
      public static Vehicle getVehicleByID(String id) throws SQLException{
        String sql="select vehicle_ID,vehicle_type,vehicle_number,vehicle_cost from vehicle WHERE vehicle_ID='"+id+"'";
        Vehicle vehicle=null;
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) {
            
            vehicle =new Vehicle(rs.getString("vehicle_ID"), rs.getString("vehicle_type"),rs.getString("vehicle_number"), rs.getInt("vehicle_cost"));
        }
        return vehicle;
    }
    
}
