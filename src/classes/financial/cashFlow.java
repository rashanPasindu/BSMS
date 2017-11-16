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
    float decrease_In_prepaid_Expenses = 0;  //coded
    float decrease_In_account_Payable = 0; //-----
    float decrease_In_accrued_Expenses = 0; //coded
    float net_Cash_Flow_from_Operating_Expenses = 0; // coded
    
    float sale_Of_Equipment = 0; //coded
    float sale_Of_Land = 0; //coded
    float sale_Of_MV = 0; //coded
    float purchase_Of_Resources = 0; // codded
    float net_Cash_Flows_of_Investing = 0; //coded
    
    float stock_purchased = 0.00f; //coded
    float long_term_liab_paid = 0.00f; //-----------
    float net_Cash_Flows_from_Financing_Activites = 0; //coded
    
    float net_Change_in_Cash = 0; //coded
    float begining_Cash_Balance = 0; //conflict
    float cash_Balance_at_End = 0; //coded
    
    public boolean initiateTransfer(String start,String end,int q){
        boolean s1=false,s2=false,s3=false,s4=false;
        
        runALLMethods(start,end);
        
        s1= this.sendtoDBF(q,net_Cash_Flow_from_Operating_Expenses, net_Cash_Flows_of_Investing, net_Cash_Flows_from_Financing_Activites, net_Change_in_Cash, begining_Cash_Balance, cash_Balance_at_End,Integer.parseInt(this.getCurrentYear()),this.getDate());
        s2= this.sendtoDBOP(q, operatingIncome, depreciationExpense, loss, gain, increase_In_Receivables, decrease_In_prepaid_Expenses,decrease_In_account_Payable , decrease_In_accrued_Expenses,Integer.parseInt(this.getCurrentYear()),this.getDate());
        s3= this.sendtoDBOI(q, sale_Of_Equipment, sale_Of_Land, sale_Of_MV, purchase_Of_Resources,Integer.parseInt(this.getCurrentYear()),this.getDate());
        s4= this.sendtoDBFIN(q, long_term_liab_paid, stock_purchased,Integer.parseInt(this.getCurrentYear()),this.getDate());
        
        if (s1==true && s2 == true && s3==true && s4==true){
            return true;
        }
        else
        return false;
    }
    private void runALLMethods(String start, String end){
        
        this.cashFinal();
        this.netValues();
        this.setDepreciationExpense();
        this.trackTheReceivables(start,end);
        this.accruedPay(start, end);
        this.adminPrepaidTOT(start, end);
        this.getSaleValue(start, end);
        this.maintPrepaidTOT(start, end);
        this.pettyPrepaidTOT(start, end);
        this.otherPrepaidTOT(start, end);
        this.totalStockPurchased(start, end);
        this.trackPrepaidExpenses(start, end);
        this.trackPurchaseOfResources(start, end);
        this.trackTheReceivables(start, end);
        
        
    }
    private void cashFinal(){
        
    net_Change_in_Cash = (net_Cash_Flow_from_Operating_Expenses + net_Cash_Flows_of_Investing + net_Cash_Flows_from_Financing_Activites);
    begining_Cash_Balance = 0;
    cash_Balance_at_End = (begining_Cash_Balance - net_Change_in_Cash);;
    
    }
    private void netValues(){
        net_Cash_Flow_from_Operating_Expenses = (operatingIncome+depreciationExpense+loss+decrease_In_prepaid_Expenses+decrease_In_account_Payable+decrease_In_accrued_Expenses)-(gain+increase_In_Receivables);
        net_Cash_Flows_of_Investing = (sale_Of_Equipment+sale_Of_Land+sale_Of_MV)-(purchase_Of_Resources);
        net_Cash_Flows_from_Financing_Activites = -(stock_purchased+long_term_liab_paid);
    }
    void getOperatingIncome(float value){ // called in sofpclassto save the value
        
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
        
       decrease_In_prepaid_Expenses=adminPrepaidTOT(start,end)+maintPrepaidTOT(start,end)+pettyPrepaidTOT(start,end)+otherPrepaidTOT(start,end);
 
    }
    
    private float adminPrepaidTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float adpreTOT = 0.00f;
        boolean status = false;
        String desad[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `adminexpenses` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = prepaidMatcher(rs.getString(sql).toString());
            if (status == true){ 
              desad[count] =  rs.getString(sql).toString(); 
              
            }
            count++;
        } 
        catch(Exception e){
            System.out.println(e);
        }
        for (int i=0;i<=count;i++){
        try {
        String sql="SELECT `Amount` FROM `adminexpenses` WHERE Description` = '"+desad[i]+"' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
         adpreTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return adpreTOT;
    }
    
        private float maintPrepaidTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float mainpreTOT = 0.00f;
        boolean status = false;
        String desmain[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `maintainexp` WHERE `Date` >= any (SELECT `Date` FROM `maintainexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `maintainexp` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = prepaidMatcher(rs.getString(sql).toString());
            if (status == true){ 
              desmain[count] =  rs.getString(sql).toString();      
            }
            count++;
        } 
        catch(Exception e){
            System.out.println(e);
        }
        for (int i=0;i<=count;i++){
        try {
        String sql="SELECT `Amount` FROM `adminexpenses` WHERE Description` = '"+desmain[i]+"' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
         mainpreTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return mainpreTOT;
    }
        
     private float pettyPrepaidTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float pettypreTOT = 0.00f;
        boolean status = false;
        String despetty[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `pettycashexp` WHERE `Date` >= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = prepaidMatcher(rs.getString(sql).toString());
            if (status == true){ 
              despetty[count] =  rs.getString(sql).toString();      
            }
            count++;
        } 
        catch(Exception e){
            System.out.println(e);
        }
        for (int i=0;i<=count;i++){
        try {
        String sql="SELECT `Amount` FROM `adminexpenses` WHERE Description` = '"+despetty[i]+"' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
         pettypreTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return pettypreTOT;
    }
     
    private float otherPrepaidTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float otherpreTOT = 0.00f;
        boolean status = false;
        String desother[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `otherexp` WHERE `Date` >= any (SELECT `Date` FROM `otherexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `otherexp` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = prepaidMatcher(rs.getString(sql).toString());
            if (status == true){ 
              desother[count] =  rs.getString(sql).toString();      
            }
            count++;
        } 
        catch(Exception e){
            System.out.println(e);
        }
        for (int i=0;i<=count;i++){
        try {
        String sql="SELECT `Amount` FROM `adminexpenses` WHERE Description` = '"+desother[i]+"' AND `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
         otherpreTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return otherpreTOT;
    }
    private boolean prepaidMatcher(String Description){
        
      String match = ""; 
      
      String patt = "prepaid|Prepaid";
      Pattern pat = Pattern.compile(patt);
      Matcher mat = pat.matcher(Description);
    
       while (mat.find()){
                
           match = mat.group();
           System.out.println(match);
           if(mat.find() == true){
               break;
           }
          
            }
       System.out.println(match);
       
        if ( match != ""){
              return true;   
           }
           else{
               return false;  
           }
    }
    
  private void accruedPay(String start, String end){
      
      acrruedpay acc = new acrruedpay();
      
      decrease_In_accrued_Expenses =  acc.trackAccruedExpenses(start, end);
  }
  
  private void getSaleValue(String start, String end){
      
    getResourceSale gRS = new getResourceSale();  
      
    sale_Of_Equipment = gRS.getValueSaleofEquipment(start, end);
    sale_Of_Land = gRS.getValueSaleofLand(start, end);
    sale_Of_MV = gRS.getValueSaleofMV(start, end);  
  }
  
  private void trackPurchaseOfResources(String start, String end){
      
      float val1 =0.00f,val2 =0.00f,val3 =0.00f;
      
      try {
          String sql="SELECT SUM(`Value`) FROM `equipment` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val1 = Float.parseFloat(rs.getString(sql.toString()));
      }
      catch (Exception e){
          System.out.println(e);
      }
      
      try {
          String sql="SELECT SUM(`value`) FROM `other_resources` WHERE `Aquired_Date` >= any (SELECT `Aquired_Date` FROM `adminexpenses` WHERE `Aquired_Date` >= '"+start+"') AND `Aquired_Date` <= any (SELECT `Aquired_Date` FROM `adminexpenses` WHERE `Aquired_Date` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val2 = Float.parseFloat(rs.getString(sql.toString()));
      }
      catch (Exception e){
          System.out.println(e);
      }
      
      try {
          String sql="SELECT SUM(`vehicle_cost`) FROM `vehicle` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val3 = Float.parseFloat(rs.getString(sql.toString()));
      }
      catch (Exception e){
          System.out.println(e);
      }
      
       purchase_Of_Resources = val1+val2+val3;
  }
  
  private void totalStockPurchased(String start, String end){
      float val = 0.00f;
      
       try {
          String sql="SELECT SUM(`payment`) FROM `rorders` WHERE `rdate` >= any (SELECT `rdate` FROM `adminexpenses` WHERE `rdate` >= '"+start+"') AND `rdate` <= any (SELECT `rdate` FROM `adminexpenses` WHERE `rdate` <= '"+end+"')";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            val = Float.parseFloat(rs.getString(sql.toString()));
      }
      catch (Exception e){
          System.out.println(e);
      }
      
      stock_purchased = val;
  }
  
  private boolean sendtoDBF(int q,float opcash,float investcash,float financecash,float net_change_cash,float beginning_cash_bal,float end_cash_bal,int Year,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `cashflofinal` (`quarter`,`Net_Cash_Flows_From_Operating_Activities`,`Net_Cash_Flows_From_Investing_Activities`,`Net_Cash_Flows_From_Financing_Activities`,`Net_Change_in_Cash`,`Cash_in_Begining_of_Quater`,`Cash_in_End_of_Quater`,`Year`,`Date`)VALUES ('"+q+"','"+opcash+"','"+investcash+"','"+financecash+"','"+net_change_cash+"','"+beginning_cash_bal+"','"+end_cash_bal+"','"+Year+"','"+date+"');";
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
   private boolean sendtoDBOP(int q,float opInc,float depE,float loss,float gain,float inc_in_Rec,float pre_Exp,float acc_Pay,float accrE,int Year,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `cashflooperating` (`quater`,`Operating_Income`,`Depreciation_Expense`,`Loss`,`Gain`,`Increase_in_Receivables`,`Prepaid_Expenses`,`Accounts_Payable`,`Accrued_Expenses`,`Year`,`Date`)VALUES ('"+q+"','"+opInc+"','"+depE+"','"+loss+"','"+gain+"','"+inc_in_Rec+"','"+pre_Exp+"','"+acc_Pay+"','"+accrE+"','"+Year+"','"+date+"');";
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
   private boolean sendtoDBOI(int q,float salEq,float salLand,float salMV,float purcR,int Year,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `cashfloinvesting` (`quater`,`Sale_of_Equipment`,`Sale_of_Land`,`Sale_of_Motor_Vehicles`,`Purchase_Resources`,`Year`,`Date`)VALUES ('"+q+"','"+salEq+"','"+salLand+"','"+salMV+"','"+purcR+"','"+Year+"','"+date+"');";
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
   private boolean sendtoDBFIN(int q,float lt_liabPaid,float stockPurchase,int Year,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `cashflofinal` (`quater`,`Long_Term_Liabilities_Paid`,`Stock_Purchase`,`Year`,`Date`)VALUES ('"+q+"','"+lt_liabPaid+"','"+stockPurchase+"','"+Year+"','"+date+"');";
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
   
}
