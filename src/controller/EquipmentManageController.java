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
import model.EquipmentManage;

/**
 *
 * @author Sandun
 */
public class EquipmentManageController {
    
    static Connection con = DBconnect.connect();

    public static int addEquipmentManage(EquipmentManage equipmentmanage) throws SQLException {
        int result = 0;
        String q = "INSERT INTO equipmentmang(equipmnt_ID,Description,Date,Cost) values ('" + equipmentmanage.getEquipmnt_ID()+ "','" + equipmentmanage.getDescription()+ "','" + equipmentmanage.getDate()+ "'," + equipmentmanage.getCost()+ ")";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static int deleteEquipmentManage(EquipmentManage equipmentmanage) throws SQLException {
        int result = 0;
        String q = "DELETE from equipmentmang WHERE equipmnt_ID='" + equipmentmanage.getEquipmnt_ID()+ "'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static int updateEquipmentManage(EquipmentManage equipmentmanage) throws SQLException {
        int result = 0;
        String q = "UPDATE equipmentmang  SET Description='" + equipmentmanage.getDescription()+ "',Date='" + equipmentmanage.getDate()+ "',Cost=" + equipmentmanage.getCost()+ " where equipmnt_ID='" + equipmentmanage.getEquipmnt_ID()+ "' ";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }
    
    public static ResultSet getAllEquipmentAsResultSet() throws SQLException{
        String sql="select equipmnt_ID,Description,Date,Cost from equipmentmang";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
}
