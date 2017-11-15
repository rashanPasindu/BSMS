/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Rashan
 */
import DBConnect.DBconnect;
import bsmanagementsystem.ExpenseInput;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class AdministrationExp extends allExpenses {
   
    ExpenseInput l5 = new ExpenseInput();
    
    
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    int cnt = 0;
    int count = -1;

    
    public AdministrationExp(){
        
       // tableLoad();
    }
    
    public AdministrationExp(String eID,String cat,String method, float amount, String desc, String apprvl){
        
      super(eID,cat,method,amount,desc,apprvl);
        
    }
    
  
    //@Override
    public void sendToDB(String cat, String method, double amount, String desc, String apprvl,String date){
       
        con = DBconnect.connect();
        try
        {
            String s = "INSERT INTO adminexpenses (Category,Method,Amount,Description,Approval,Date)VALUES ('"+cat+"','"+method+"','"+amount+"','"+desc+"','"+apprvl+"','"+date+"');";
            pst = con.prepareStatement(s);
            pst.execute();
            tableLoad();
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
            sql = "SELECT  ExpenseID,Category,Method,Amount,Description,Approval FROM adminexpenses";
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
        
            
        String q="DELETE from adminexpenses where ExpenseID = '"+n+"';";
            
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
            sql = "UPDATE adminexpenses SET Category='"+cat+"',Method = '"+method+"',Amount = '"+amount+"',Description = '"+desc+"',Approval = '"+apprvl+"',Date = '"+date+"' WHERE ExpenseID='"+id+"'";
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
   
   
  /*  
  public static void main(String []a)
  {
      AdministrationExp ad1 = new AdministrationExp();
      
      ad1.initiateTransfer();
  }
 */
  
}
