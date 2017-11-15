/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import DBConnect.DBconnect;
import bsmanagementsystem.ExpenseInput;
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
public class MaintenExp extends allExpenses {
    
    //ExpenseInput l7 = new ExpenseInput();
   
    
    
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    
    public MaintenExp(){
    
     //tableLoad();
     
    }
    
    public MaintenExp(String eID,String cat, String method, float amount, String desc, String apprvl){
        
        eID = super.expense_ID;
        cat = super.expense_category;
        method = super.payment_method;  
        amount = super.amount;
        desc = super.description;
        apprvl = super.approved_by;        
                
    }
       
      private void sendToDB(String cat, String method, float amount, String desc, String apprvl,String date){
        con = DBconnect.connect();
        try
        {
            String sql;
            sql = "INSERT INTO maintainexp (Category,Method,Amount,Description,Approval,Date)VALUES('"+cat+"','"+method+"','"+amount+"','"+desc+"','"+apprvl+"','"+date+"')";
            pst = con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Entry Successfull");
        }
        
      catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"Entry UN-Successfull");
                }
    
    }
      
      public void  initiateTransfer(String cat, String method, float amount, String desc, String apprvl,String date){
        
        this.sendToDB(cat,method,amount,desc,apprvl,date);
    }
      
      
     @Override
    public void tableLoad(){
        
         ExpenseInput ln5 = new ExpenseInput();
         
         con = DBconnect.connect();
         try
        {
            
            String sql;
            sql = "SELECT ExpenseID,Category,Method,Amount,Description,Approval FROM maintainexp";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            new ExpenseInput().jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            //JOptionPane.showMessageDialog(null,"Successfull");
        
        }
        
    catch(SQLException e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull");
                }
        
    }
    
     public void deleteExp(String id){
       
        con = DBconnect.connect();
        String n = id;
        
        int y= JOptionPane.showConfirmDialog(null,"Do you want to delete this ?");
        
        if (y==0){
        
            
        String q="DELETE from maintainexp where ExpenseID = '"+n+"';";
            
        try{
            pst=con.prepareStatement(q);
            pst.execute();
            tableLoad();
            //clear();
            
            }
            catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,e+"iError");
            
            }
        }
     }
      
     public void updateDB(String id,String cat,String method, float amount, String desc, String apprvl,String date){
    con = DBconnect.connect();
        try
        {
            String sql;
            sql = "UPDATE maintainexp SET Category='"+cat+"',Method = '"+method+"',Amount = '"+amount+"',Description = '"+desc+"',Approval = '"+apprvl+"',Date = '"+date+"' WHERE ExpenseID='"+id+"'";
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
        
    
    
}
