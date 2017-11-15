/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class policyClass {
    

Connection con = null;
PreparedStatement pst=null;
ResultSet rs = null;

public void sendToDB( String cat, String iDate, float rate,float percentage, String desc,String UDate){
    con = DBconnect.connect();
        try
        {
            String sql;
            sql = "INSERT INTO policy_update (policyType,policyInitialDate,rate,percentage,description,policyUpdateDate)VALUES('"+cat+"','"+iDate+"','"+rate+"','"+percentage+"','"+desc+"','"+UDate+"')";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
            //fillcombo();
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UN-Successfull");
                }
}

public void updateDB(String id,float rate,float percentage, String desc,String UDate){
    con = DBconnect.connect();
        try
        {
            String sql;
            sql = "UPDATE policy_update SET rate='"+rate+"',percentage = '"+percentage+"',description = '"+desc+"',policyUpdateDate = '"+UDate+"' WHERE policyID='"+id+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
            //fillcombo();
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UN-Successfull");
                }
}

public void addPolicytoDB(String type){
    con = DBconnect.connect();
        try
        {
            String sql;
            sql = "INSERT INTO policytypes (policytype)VALUES('"+type+"')";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
            //fillcombo();
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UN-Successfull");
                }

}

public void removepolicyType(String type){
      con = DBconnect.connect();
        
        String n = type;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from policytypes where policytype = '"+n+"';";
            
        try{
            pst=con.prepareStatement(q);
            pst.execute();
            //emptycombo();
            //fillcombo();
            //clear();
            
            }
            catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,e+"iError");
            
            }
}
}

 public void delete(String id){
       
        con = DBconnect.connect();
        String n = id;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from policy_update where policyID = '"+n+"';";
            
        try{
            pst=con.prepareStatement(q);
            pst.execute();
            //tableLoad();
            //clear();
            
            }
            catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,e+"iError");
            
            }
        }
     }

}