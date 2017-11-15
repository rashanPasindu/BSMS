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
import model.OtherResourceManage;
import model.OtherResources;

/**
 *
 * @author Sandun
 */
public class OtherResourcesManageController {

    static Connection con = DBconnect.connect();

    public static int addOtherResourcesManage(OtherResourceManage otherresourcesmanage) throws SQLException {
        int result = 0;
        String q = "INSERT INTO other_resource_manag(resource_ID,Description,Date,Cost) values ('" + otherresourcesmanage.getResource_ID() + "','" + otherresourcesmanage.getDescription() + "','" + otherresourcesmanage.getDate() + "'," + otherresourcesmanage.getCost() + ")";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static int deleteOtherResourcesManage(OtherResourceManage otherresourcesmanage) throws SQLException {
        int result = 0;
        String q = "DELETE from other_resource_manag WHERE resource_ID='" + otherresourcesmanage.getResource_ID() + "'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static int updateOtherResourcesManage(OtherResourceManage otherresourcesmanage) throws SQLException {
        int result = 0;
        String q = "UPDATE other_resource_manag  SET Description='" + otherresourcesmanage.getDescription() + "',Date='" + otherresourcesmanage.getDate() + "',Cost=" + otherresourcesmanage.getCost() + " where resource_ID='" + otherresourcesmanage.getResource_ID() + "' ";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();

        return result;
    }

    public static ResultSet getAllOtherResourceAsResultSet() throws SQLException {
         String sql="select resource_ID,Description,Date,Cost from other_resource_manag";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }

}
