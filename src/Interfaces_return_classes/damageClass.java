/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces_return_classes;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class damageClass {
    
Connection con = null;
PreparedStatement pst=null;
ResultSet rs = null;
    

public void updateDB( String id,String item_id,String item_name,String unit_prc,String qty,String total,String reason,String mny_return){
    con = DBconnect.connect();
        try
        {
            String sql;
            sql = "UPDATE returns SET Item_ID='"+item_id+"',Product_Name='"+item_name+"',unit_Price='"+unit_prc+"',Qty='"+qty+"',Total='"+total+"',Reason='"+reason+"',money_Returns='"+mny_return+"' WHERE return_ID='"+id+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Edit Sucsessfull");
            //fillcombo();
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Edit UN-Successfull");
                }
}


public void removeDamage(String id){
      con = DBconnect.connect();
        
        String n = id;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE FROM returns WHERE return_ID= '"+n+"';";
            
        try{
            pst=con.prepareStatement(q);
            pst.execute();
            
            }
            catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,e+"iError");
            
            }
}
}


    
    
}
