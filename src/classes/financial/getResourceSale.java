/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.financial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Rashan
 */
public class getResourceSale {
    
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    public float getValueSaleofLand(String start,String end){
       float val = 0.00f;
               
        try{
            String sql="SELECT `Amount` FROM `otherentries` WHERE `Description`= 'Sale of Land' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val = Float.parseFloat(rs.getString(sql.toString()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return val;
    }
    public float getValueSaleofMV(String start,String end){
       float val = 0.00f;
               
        try{
            String sql="SELECT `Amount` FROM `otherentries` WHERE `Description`= 'Sale of Motor Vehicle' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val = Float.parseFloat(rs.getString(sql.toString()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return val;  
    }
    public float getValueSaleofEquipment(String start,String end){
      float val = 0.00f;
               
        try{
            String sql="SELECT `Amount` FROM `otherentries` WHERE `Description`= 'Sale of Equipment' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val = Float.parseFloat(rs.getString(sql.toString()));
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        return val;
    }
    
    
}
