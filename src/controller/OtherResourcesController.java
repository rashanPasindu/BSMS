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
import model.OtherResources;

/**
 *
 * @author Sandun
 */
public class OtherResourcesController {
    
    
    static Connection con = DBconnect.connect();
    
     public static int addOtherResources(OtherResources otherresources) throws SQLException{
        int result=0;
        String q = "INSERT INTO other_resources(resource_ID,type,description,value,Aquired_Date,lastdateupdated) values ('" + otherresources.getResource_ID()+ "','" + otherresources.getType()+ "','" + otherresources.getDescription()+ "'," + otherresources.getValue()+ ", '" + otherresources.getAquired_Date()+"', '" + otherresources.getLastdateupdated()+"')";

         PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
     
     public static int deleteOtherResources(OtherResources otherresources) throws SQLException {
        int result=0;
        String q = "DELETE from other_resources WHERE resource_ID='"+ otherresources.getResource_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
    
     public static int updateOtherResources(OtherResources otherresources) throws SQLException{
        int result=0;
        String q = "UPDATE other_resources  SET type='" + otherresources.getType()+ "',description='" + otherresources.getDescription()+ "',value=" + otherresources.getValue()+ ",Aquired_Date='" + otherresources.getAquired_Date()+ "',lastdateupdated='" + otherresources.getLastdateupdated()+ "' where resource_ID='"+ otherresources.getResource_ID()+"' ";
                                                                                                
        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
     
     public static ResultSet getAllOtherResoucesAsResultSet() throws SQLException{
        String sql="select * from other_resources";    
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
}
