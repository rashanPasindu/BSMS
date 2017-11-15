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
import model.Equipment;

/**
 *
 * @author Sandun
 */
public class EquipmentController {
    
    static Connection con = DBconnect.connect();
    
     public static int addEquipment(Equipment equipment) throws SQLException{
        int result=0;
        String q = "INSERT INTO equipment(equipmnt_ID,equip_Name,Brand,Serial,Value) values ('" + equipment.getEquipmnt_ID()+ "','" + equipment.getEquip_Name()+ "','" + equipment.getBrand()+ "','" + equipment.getSerial()+ "'," + equipment.getValue()+ ")";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     
     public static int deleteEquipment(Equipment equipment) throws SQLException {
        int result=0;
        String q = "DELETE from equipment WHERE equipmnt_ID='"+equipment.getEquipmnt_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
    
     public static int updateEquipment(Equipment equipment) throws SQLException{
        int result=0;
        String q = "UPDATE equipment  SET equip_Name='" + equipment.getEquip_Name()+ "',Brand='" + equipment.getBrand()+ "',Serial='" + equipment.getSerial()+ "',Value=" + equipment.getValue()+ " where equipmnt_ID='"+equipment.getEquipmnt_ID()+"' ";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
     
     public static ResultSet getAllEqipmentAsResultSet() throws SQLException{
        String sql="select equipmnt_ID,equip_Name,Brand,Serial,Value from equipment";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
}
