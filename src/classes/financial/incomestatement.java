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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Rashan
 */
public class incomestatement {
    
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    float incomeTotal = 0; //done_coding_method
    float salesTotal =0; //done_coding_method
    float disReceivedTotal = 0; 
    float otherIncomesTotal = 0; //done_coding_method
    
    float expTotal = 0; //done_coding_method
    float adminExpTotal = 0; //done_coding_method
    float maintExpTotal = 0; //done_coding_method
    float pettyExpTotal = 0; //done_coding_method
    float otherExpTotal = 0; //done_coding_method
    float disAllowedTotal = 0; //done_coding_method
    
    float profitBeforeTax = 0; //done_coding_method
    float profitAfterTax = 0; //done_coding_method
    float taxTotal = 0; //done_coding_method
    
    String date;
    
    public boolean initiate(String start, String end){
        boolean chk1,chk2;
        assignValues(start,end);
        chk1=sendtoDB(salesTotal,otherIncomesTotal,disReceivedTotal,adminExpTotal,maintExpTotal,pettyExpTotal,otherExpTotal,disAllowedTotal,date);
        chk2=sendtoDB1(incomeTotal,expTotal,profitBeforeTax,taxTotal,profitAfterTax,date);
    
       if (chk1==true && chk2==true){
       return true;
        }
       else{
       System.out.println("Error");
       return false;
   }
    
    }
    
   private void assignValues(String start, String end){
        
        salesTotal = Float.parseFloat(getSalesTotal(start,end));
        adminExpTotal = Float.parseFloat(getAdminExpTotal(start,end));
        maintExpTotal = Float.parseFloat(getmaintExpTotal(start,end));
        pettyExpTotal = Float.parseFloat(getpettyExpTotal(start,end));
        otherExpTotal = Float.parseFloat(getotherExpTotal(start,end));
        taxTotal = Float.parseFloat(gettaxTotal(start,end));
        disAllowedTotal = Float.parseFloat(disAllowedTOT(start,end));
        otherIncomesTotal=getOtherIncomes(start,end);
        disReceivedTotal=disReceivedToT(start,end);
        
        date=getCurrentDate();
    }
   
   private boolean sendtoDB(float salesTOT,float otherIncomes,float discountsR, float adminExp,float MaintExp, float PettyExp, float OtherExp, float discountsA, String date){
       
       con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `incomestatevalues` (`Sales_Total`,`Other_Incomes`,`Discounts_Received`,`Admin_Exp_Total`,`Maint_Exp_Total`,`Petty_Exp_Total`,`Other_Exp_Total`,`Discounts_Allowed`,`Date`)VALUES ('"+salesTOT+"','"+otherIncomes+"','"+discountsR+"','"+adminExp+"','"+MaintExp+"','"+PettyExp+"','"+OtherExp+"','"+discountsA+"','"+date+"');";
           pst = con.prepareStatement(sql);
           pst.execute();
           System.out.println("Successful");
           return true;
       }
       catch(Exception e){
           System.out.println(e);
           return false;
       }   
   }
   
     private boolean sendtoDB1(float incomeTOT,float ExpenseTOT,float PBT, float Tax,float PAT, String date){
       
       con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `incomestatefinal` (`income_Total`,`Expense_Total`,`PBT`,`Tax`,`PAT`,`Date`)VALUES ('"+incomeTOT+"','"+ExpenseTOT+"','"+PBT+"','"+Tax+"','"+PAT+"','"+date+"');";
           pst = con.prepareStatement(sql);
           pst.execute();
           System.out.println("Successful");
           return true;
       }
       catch(Exception e){
           System.out.println(e);
           return false;
       }   
   }
    
    private void calculatePBT(){
        
        profitBeforeTax = incomeTotal - expTotal;
        
        cashFlow cash = new cashFlow();
        
        cash.getLoss(profitBeforeTax);
        cash.getOperatingIncome(profitBeforeTax);
    }
    
    private void calculatePAT(){
        
        profitAfterTax = profitBeforeTax - taxTotal;
        
    }
    
    private void calculateExpTotal(){
       
        expTotal = adminExpTotal + maintExpTotal + pettyExpTotal + otherExpTotal;
    }
    
    private void calculateIncomeTotal(){
        incomeTotal = salesTotal + otherIncomesTotal + disReceivedTotal;
    }
    
    private String getSalesTotal(String start, String end){
        String salestot = null;
        con = DBconnect.connect();
        
        try{
            String s = "SELECT SUM(Total) FROM receipt WHERE `datetime` >= any (SELECT `datetime` FROM `receipt` WHERE `datetime` >= '"+start+"') AND `datetime` <= any (SELECT `datetime` FROM `receipt` WHERE `datetime` <= '"+end+"'"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            salestot =  rs.getString(s);
            
            JOptionPane.showMessageDialog(null,"Successfull");
            return salestot;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return salestot;
        }  
    }
    

    private String getAdminExpTotal(String start, String end){
        String AdminExpTotal = null;
        
         con = DBconnect.connect();
        
        try{
            String s = "SELECT SUM(`Amount`) FROM `adminexpenses` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            AdminExpTotal =  rs.getString(s);
            
            JOptionPane.showMessageDialog(null,"Successfull");
            return AdminExpTotal;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return AdminExpTotal;
        }  
        
        //return AdminExpTotal;
    }
    private String getmaintExpTotal(String start, String end){
        String maintExpTotal = null;
        
           con = DBconnect.connect();
        
        try{
            String s = "SELECT SUM(`Amount`) FROM `maintainexp` WHERE `Date` >= any (SELECT `Date` FROM `maintainexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `maintainexp` WHERE `Date` <= '"+end+"')"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            maintExpTotal =  rs.getString(s);
            
            JOptionPane.showMessageDialog(null,"Successfull");
            return maintExpTotal;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return maintExpTotal;
        } 
        
        //return maintExpTotal;
    }
    private String getpettyExpTotal(String start, String end){
        String pettyExpTotal = null;
            con = DBconnect.connect();
        
        try{
            String s = "SELECT SUM(`Amount`) FROM `pettycashexp` WHERE `Date` >= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` <= '"+end+"')"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            pettyExpTotal =  rs.getString(s);
            
            JOptionPane.showMessageDialog(null,"Successfull");
            return pettyExpTotal;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return pettyExpTotal;
        } 
        
        //return pettyExpTotal;
    }
    private String getotherExpTotal(String start, String end){
        String otherExpTotal = null;
            con = DBconnect.connect();
        
        try{
            String s = "SELECT SUM(`Amount`) FROM `otherexp` WHERE `Date` >= any (SELECT `Date` FROM `otherexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `otherexp` WHERE `Date` <= '"+end+"')"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            otherExpTotal =  rs.getString(s);
            
            JOptionPane.showMessageDialog(null,"Successfull");
            return otherExpTotal;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return otherExpTotal;
        } 
        //return otherExpTotal;
    }
    
    private String gettaxTotal(String start, String end){
          String taxTotal = null;   
        con = DBconnect.connect();
        
        try{
            String s = "SELECT SUM(`Amount`) FROM `adminexpenses` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"') AND `Category` = any (SELECT `Category` WHERE `Category` = 'Tax')"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            taxTotal =  rs.getString(s);
            System.out.println(taxTotal);
            JOptionPane.showMessageDialog(null,"Successfull");
            return taxTotal;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return taxTotal;
        } 
       
    }
    
    private String disAllowedTOT(String start, String end){
        String disAllowed = null;
        
        try{
            String s = "SELECT SUM(`Discounts_Allowed`) FROM `bill` WHERE `bill_Date` >= any (SELECT `bill_Date` FROM `bill` WHERE `bill_Date` >= '"+start+"') AND `bill_Date` <= any (SELECT `bill_Date` FROM `bill` WHERE `bill_Date` <= '"+end+"')"; //YYYY-MM-DD HH:MM:SS
            pst = con.prepareStatement(s);
            pst.execute();
            rs= pst.executeQuery(s);
            disAllowed =  rs.getString(s);
            System.out.println(disAllowed);
            JOptionPane.showMessageDialog(null,"Successfull");
            return disAllowed;
        }
        catch(SQLException e){
            
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"UN-Successfull Find");
                     return disAllowed;
        } 
    }  
    
    private float disReceivedToT(String start, String end){
        float disRe =0;
        
        return disRe;
    }
    
  private float getOtherIncomes(String start,String end){
    float OtherIncomeTOT=0.0f;
    
     con = DBconnect.connect();
    
    try{
        String sql="SELECT SUM(`Amount`) FROM otherentries WHERE `Category`='Other Incomes' AND `Date` >= any (SELECT `Date` FROM `otherentries` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `otherentries` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        OtherIncomeTOT = Float.parseFloat(rs.getString(sql));
        return OtherIncomeTOT;
    }
    catch (Exception e){
        System.out.println(e);
        return OtherIncomeTOT;
    }
    
    
}
   private String getCurrentDate(){
        
        String date1;
        
        Date date = new Date();
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date1 = formatter.format(date);
        
        System.out.println(date1);
        
        return date1;
    }
 
    private String getStartDateFormat(String start){
     
     //String SDate = this.jDateChooser1.getDate().toString();
     
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     
     String date1 = sdf.format(start);
     
     System.out.println(date1);
     
     return date1;
 }
 
 private String getEndDateFormat(String end){
     //String EDate = this.jDateChooser2.getDate();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     
     String date1 = sdf.format(end);
     
     System.out.println(date1);
      
     return date1;
 }
    
}
