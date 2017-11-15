/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import DBConnect.DBconnect;
import bsmanagementsystem.Approval;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Rashan
 */
public class approval {
    
    Approval app1 = new Approval();
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    
    String category;
    private String comb,combi1;
    private final String btwn = " - ";
    private final String space = " ";
    String cat1;
    String start1;
    String end1;    
    
    
    public approval(){
    
     //con=DBconnect.connect();
   }
    
public String concat(String Sdate,String Edate,String m,String m4){
    
        //Approval app11 = new Approval();
        
        String m1,m2,m3;
        String last;
        String cfinal;
        String Smonth;
        
        comb = Sdate; 
        combi1 = Edate;
        
        m1 = comb.concat(space);
        Smonth = m1.concat(m);
        last = Smonth.concat(btwn);
        m2 = last.concat(Edate);
        m3 = m2.concat(space);
        cfinal = m3.concat(m4);

        
        System.out.println(Sdate);
        System.out.println(Edate);
        System.out.println(comb);
        
        return cfinal;
}
  
   /*
  public void getapprovals(String cat,String start,String end){
       
       Approval n = new Approval(); 
      
       Connection con1 = null;
       PreparedStatement pst1=null;
       con1 = DBconnect.connect();
       ResultSet rs = null;
       //int num = n.jTable1.getColumnCount();
       //int i;
       
       
      try{
              
       if ("Administration Expenses".equals(cat)){
        try
        {
            String s = "SELECT `ExpenseID`,`Category`,`Approval`,`Date` FROM `adminexpenses` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
            pst1 = con1.prepareStatement(s);
            //pst1.execute(s);
            rs = pst1.executeQuery(s);
            n.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            JOptionPane.showMessageDialog(null,"Successfull");
            
            
        }
        
    catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull");
                }
        
       }
       else if(cat == "Maintenance Expenses"){
        
        
             try
        {
            
            String s = "SELECT `ExpenseID`,`Category`,`Approval`,`Date` FROM `maintainexp` WHERE `Date` >= any (SELECT `Date` FROM `maintainexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `maintainexp` WHERE `Date` <= '"+end+"')";
            pst1 = con1.prepareStatement(s);
            //pst1.execute();
            rs = pst1.executeQuery(s);
            n.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            JOptionPane.showMessageDialog(null,"Successfull");
            
        }
        
    catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Un-Successfull");
                }
       }
       
       else if (cat == "Petty Cash Expenses"){
               try
        {
            String s = "SELECT `ExpenseID`,`Category`,`Approval`,`Date` FROM `pettycashexp` WHERE `Date` >= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` <= '"+end+"')";
            pst1 = con1.prepareStatement(s);
            //pst1.execute();
            rs = pst1.executeQuery(s);
            n.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            JOptionPane.showMessageDialog(null,"Entry Successfull");
            
        }
        
    catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Successfull");
                }
       }
       
       else if (cat == "Other Expenses"){
               try
        {
            String s = "SELECT `ExpenseID`,`Category`,`Approval`,`Date` FROM `otherexp` WHERE `Date` >= any (SELECT `Date` FROM `otherexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `otherexp` WHERE `Date` <= '"+end+"')";
            pst1 = con1.prepareStatement(s);
            //pst1.execute();
            rs = pst1.executeQuery(s);
            n.jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            JOptionPane.showMessageDialog(null,"Successfull");
            
        }
        
    catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Un-Successfull");
                }
       }
       else{
           JOptionPane.showMessageDialog(null,"UN-Successfull - Invalid Category");
       }
      }
      catch (Exception e){
          System.out.println(e);
      }
   }
  

   /*
 public int chkDate(String cat,String Date){
     
     if (cat == "Administration Expenses"){
         
         
         String sql;
            sql = "SELECT Date FROM adminexpenses WHERE Category = '"+cat+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
         return 1;
     }
     else if (cat == "Maintenance Expenses"){
         
         return 1;
     }
     else if (cat == "PettyCash Expenses"){
         return 1;
     }
     else if (cat == "Other Expenses"){
         return 1;
     }
     else{
         return 0;
     }
 }*/
 
}
