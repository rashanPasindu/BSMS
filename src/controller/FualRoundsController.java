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
import model.FualRounds;

/**
 *
 * @author Sandun
 */
public class FualRoundsController {
    
    static Connection con = DBconnect.connect();
    
     public static int addFualRound(FualRounds fualrounds) throws SQLException{
        int result=0;
        String q = "INSERT INTO fuel_rounds(vehicle_ID,cost,date,price_per_litre,total_litres) values ('" + fualrounds.getVehicle_ID()+ "','" + fualrounds.getCost()+ "','" + fualrounds.getDate()+ "','" + fualrounds.getPrice_per_litre()+ "'," + fualrounds.getTotal_litres()+ ") ";

         PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     
     public static int deleteFualRound(FualRounds fualrounds) throws SQLException {
        int result=0;
        String q = "DELETE from fuel_rounds WHERE vehicle_ID='"+fualrounds.getVehicle_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
    
     public static int updateFualRound(FualRounds fualrounds) throws SQLException{
        int result=0;
        String q = "UPDATE fuel_rounds  SET cost='" + fualrounds.getCost()+ "',date='" + fualrounds.getDate()+ "',price_per_litre='" + fualrounds.getPrice_per_litre()+ "',total_litres=" + fualrounds.getTotal_litres()+ " where vehicle_ID='"+fualrounds.getVehicle_ID()+"' ";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }

    public static ResultSet getAllFualRoundsAsResultSet() throws SQLException {
        String sql="select * from fuel_rounds";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
}
