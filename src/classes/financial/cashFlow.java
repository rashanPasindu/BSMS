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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rashan
 */
public class cashFlow {
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
    float operatingIncome = 0; //coded
    float depreciationExpense = 0; //coded
    float loss = 0; // coded
    float gain = 0; // coded
    float increase_In_Receivables = 0; // coded
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
       
        calcDep dep = new calcDep();
        
        float lNBvalue = dep.calcDepforLandNBuild();
        float MVvalue = dep.calcDepforMV();
        float EQPvalue = dep.calcDepforMV();
        
        depreciationExpense = lNBvalue+MVvalue+EQPvalue;
        
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
       
       float currentReceivables = 0.00f;
       float previousReceivables = 0.00f;
       float change = 0.00f;
       
       String pstart = previousDateStart(getCurrentYear(),getCurrentMonth());
       String pend = previousDateEnd(getCurrentYear(),getCurrentMonth());
       
       
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
        String sql="SELECT SUM(`Net_Amount`) FROM creditsales WHERE `Date` >= any (SELECT `Date` FROM `creditsales` WHERE `Date` >= '"+pstart+"') AND `Date` <= any (SELECT `Date` FROM `creditsales` WHERE `Date` <= '"+pend+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        previousReceivables = Float.parseFloat(rs.getString(sql));
       }
       catch(Exception e){
           System.out.println(e);
       }
       
       change = currentReceivables-previousReceivables;
       
       increase_In_Receivables = change;
   }
    
 private String getDate(){
        String date1;
        
        Date date = new Date();
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date1 = formatter.format(date);
        
        System.out.println(date1);
        
        return date1;
    }
     
     private String getCurrentMonth(){
         
        char m1 = getDate().charAt(5);
        char m2 = getDate().charAt(6);
       
        StringBuilder month = new StringBuilder("");
        
        month.insert(0, m1);
        month.insert(1, m2);
        
        System.out.println(month.toString());
        
        String currentMonth = month.toString();
        
        return currentMonth;
     }
     
     private String getCurrentYear(){
        char arr[] = new char[4];
        
        for (int i=0;i<=4;i++){
            arr[i] = getDate().charAt(i);
        }
        
        StringBuilder year = new StringBuilder("");
        
        for (int i=0;i<=4;i++){
        year.insert(i, arr[i]);
        }
        
        System.out.println(year.toString());
        
        String currentYear = year.toString();
        
        return currentYear;
     }
     
      private String previousDateStart(String year,String month){
        String month1;
        int m,nyr;
        String year1;
        
        m = Integer.parseInt(month);
        
        if(m == 1){
            m = 10;
            month1 = String.valueOf(m);
            nyr = Integer.parseInt(year);
            nyr -= 1;
            year1 = String.valueOf(nyr);
            String date = year1.concat("-").concat(month1).concat("-").concat("01");
        
        return date;
        }
        else if (m == 4){
            m -= 3;        
            month1 = "0".concat(String.valueOf(m));
            String date = year.concat("-").concat(month1).concat("-").concat("01");
            
            return date;
        }
        else if(m == 7){
           m -= 3;
            month1 = "0".concat(String.valueOf(m));
            String date = year.concat("-").concat(month1).concat("-").concat("01");
        
        return date;
        }
        else if (m == 10){
            m -= 3;        
            month1 = "0".concat(String.valueOf(m));
            String date = year.concat("-").concat(month1).concat("-").concat("01");
            
            return date;
        }
        else{
            System.out.println("error");
            String date = null;
            return date;
        }
    }
      
    private String previousDateEnd(String year,String month){
        String month1,year1;
        int m,nyr;
        
        m = Integer.parseInt(month);
        
        if (m == 1){
            m = 12;
            month1 = String.valueOf(m);
            nyr = Integer.parseInt(year);
            nyr -= 1;
            year1 = String.valueOf(nyr);
            String date = year1.concat("-").concat(month1).concat("-").concat("31");
        
        return date;
        }
        else if(m == 4){
           m -= 1;
            month1 = "0".concat(String.valueOf(m));
            String date = year.concat("-").concat(month1).concat("-").concat("31");
        
        return date;
        }
        
        else if(m == 7){
           m -= 1;
            month1 = "0".concat(String.valueOf(m));
            String date = year.concat("-").concat(month1).concat("-").concat("31");
        
        return date;
        }
        else if (m == 10){
            m -= 1;        
            month1 = "0".concat(String.valueOf(m));
            String date = year.concat("-").concat(month1).concat("-").concat("30");
            
            return date;
        }
        else {
            System.out.println("error");
            String date = null;
            
            return date;
        }
    }
    
    void trackPrepaidExpenses(String start, String end){
        
       
    }
    
    private boolean prepaidMatcher(String Description){
        
      String patt = "prepaid|Prepaid";
      Pattern pat = Pattern.compile(patt);
      Matcher mat = pat.matcher(Description);
      String match = "";
      
       while(mat.find()){
                match = mat.group();
                
            }
       
       if (match != ""){
        return true;
       }
       else{
           return false;
       }
    }
    
}
