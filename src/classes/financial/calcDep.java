/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.financial;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rashan
 */
public class calcDep {
    
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public float calcDepforLandNBuild(){
        
        String type = "Depreciation for Land & Building";
        float value = 0.00f;
        float cost = getCost(type);
        
        con = DBconnect.connect();
        
        try{
          
        String sql="SELECT `percentage` FROM `policy_update` WHERE `policyType` = '"+type+"'";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        value = Float.parseFloat(rs.getString(sql));
            
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return cost*(value/100);
        
    }
    
    public float calcDepforMV(){
        
        String type = "Depreciation for MotorVehicles";
        float value = 0.00f;
        float cost = getCost(type);
        
        con = DBconnect.connect();
        
        try{
          
        String sql="SELECT `percentage` FROM `policy_update` WHERE `policyType` = '"+type+"'";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        value = Float.parseFloat(rs.getString(sql));
            
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return cost*(value/100);
        
    }
    
    public float calcDepforEquip(){
        
        String type = "Depreciation for Equip";
        float value = 0.00f;
        float cost = getCost(type);
        
        con = DBconnect.connect();
        
        try{
          
        String sql="SELECT `percentage` FROM `policy_update` WHERE `policyType` = '"+type+"'";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        value = Float.parseFloat(rs.getString(sql));
            
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return cost*(value/100);
    }
    
    private float getCost(String type){
        
        float cost = 0.00f;
        
        con = DBconnect.connect();
        
        if (type == "Depreciation for Land & Building" ){
            
        try{
          
        String sql="SELECT SUM(`cost`) FROM `other_resources`";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        cost = Float.parseFloat(rs.getString(sql));
        return cost; 
        }
        catch (Exception e){
            System.out.println(e);  
            return cost;
        }
       }
        else if (type == "Depreciation for Motor Vehicles" ){
            
        try{
          
        String sql="SELECT SUM(`cost`) FROM `vehicle`";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        cost = Float.parseFloat(rs.getString(sql));
        return cost; 
        }
        catch (Exception e){
            System.out.println(e);  
            return cost;
        }
       }
        else if (type == "Depreciation for Equipment" ){
            
        try{
          
        String sql="SELECT SUM(`Value`) FROM `equipment`";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        cost = Float.parseFloat(rs.getString(sql));
        return cost; 
        }
        catch (Exception e){
            System.out.println(e);  
            return cost;
        }
       }
        else{
            return cost;
        }
        
    }
     //done
}


