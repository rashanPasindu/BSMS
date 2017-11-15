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

/**
 *
 * @author Rashan
 */
public class cashFlow {
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    float operatingIncome = 0; 
    float depreciationExpense = 0;
    float loss = 0; // coded
    float gain = 0;
    float increase_In_Receivables = 0;
    float decrease_In_prepaid_Expenses = 0;
    float decrease_In_account_Payable = 0;
    float decrease_In_accrued_Expenses = 0;
    float Net_Cash_Flow_from_Operating_Expenses = 0;
    
    float sale_Of_Equipment = 0;
    float sale_Of_Land = 0;
    float purchase_Of_Equipment = 0;
    float net_Cash_Flows_of_Investing = 0;
    
    float net_Cash_Flows_from_Financing_Activites = 0;
    
    float net_Change_in_Cash = 0;
    float begining_Cash_Balance = 0;
    float s_cash_Balance = 0;
    
    void getOperatingIncome(float value){
        
        operatingIncome = value;
    }
    
    void setDepreciationExpense(){
       
        
    }
    
    void getLoss(float value){
        
        if (value <= 0){
            loss = value;
        }
        else{
            loss = 0.00f;
        }
    }
    
    void getGain(float value){
        
        if (value > 0){
            gain = value;
        }
        else{
            gain = 0.00f;   
        }
    }
    
   void trackTheReceivables(String start, String end){
       
       con = DBconnect.connect();
       
       float currentReceivables;
       float previousReceivables;
       String pstart,pend;
       
       
       try{
          String sql="SELECT SUM(`Net_Amount`) FROM creditsales WHERE `Date` >= any (SELECT `Date` FROM `creditsales` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `creditsales` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        currentReceivables = Float.parseFloat(rs.getString(sql));
       }
       catch(Exception e){
           System.out.println(e);
       }
       
       
      
       try{
        String sql="SELECT SUM(`Net_Amount`) FROM creditsales WHERE `Date` >= any (SELECT `Date` FROM `creditsales` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `creditsales` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        previousReceivables = Float.parseFloat(rs.getString(sql));
       }
       catch(Exception e){
           System.out.println(e);
       }
       
   }
    

}
