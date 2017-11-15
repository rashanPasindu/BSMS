/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.LeasedVehicle;

/**
 *
 * @author Sandun
 */
public class LeasedVehicleController {
    
    static Connection con = DBconnect.connect();
    
     public static int addLeasedVehicle(LeasedVehicle leasedVehicle) throws SQLException{
        int result=0;
        String q = "INSERT INTO leased_vehicle(vehicle_ID,Initial_Pay,installment_Fee,leased_Company,installment_Scheme,noOFYears,endDate,Leased_Year,startDate) values ('" + leasedVehicle.getVehicle_ID()+ "'," + leasedVehicle.getInitial_Pay()+ "," + leasedVehicle.getInstallment_Fee()+ ",'" + leasedVehicle.getLeased_Company()+ "', '" + leasedVehicle.getInstallment_Scheme()+"', " + leasedVehicle.getNoOFYears()+", '" + leasedVehicle.getEndDate()+"', '" + leasedVehicle.getLeased_Year()+"','" + leasedVehicle.getStartString()+"')";

         PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     
     public static int deleteLeasedVehicle(LeasedVehicle leasedVehicle) throws SQLException {
        int result=0;
        String q = "DELETE from leased_vehicle WHERE vehicle_ID='"+ leasedVehicle.getVehicle_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
    
     public static int updateLeasedVehicle(LeasedVehicle leasedVehicle) throws SQLException{
        int result=0;
        String q = "UPDATE leased_vehicle  SET Initial_Pay=" + leasedVehicle.getInitial_Pay()+ ",installment_Fee=" + leasedVehicle.getInstallment_Fee()+ ",leased_Company='" + leasedVehicle.getLeased_Company()+ "',installment_Scheme='" + leasedVehicle.getInstallment_Scheme()+ "',noOFYears=" + leasedVehicle.getNoOFYears()+ ",endDate='" + leasedVehicle.getEndDate()+ "',Leased_Year='" + leasedVehicle.getLeased_Year()+ "',startDate='" + leasedVehicle.getStartString()+ "' where vehicle_ID='"+leasedVehicle.getVehicle_ID()+"' ";
//                                                                                                                                                                                                                                                                                                                            
        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
    
}
