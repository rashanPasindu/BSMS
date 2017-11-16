/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import classes.financial.cashFlow;
import classes.financial.financialposition;
import classes.financial.incomestatement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rashan
 */
public class workoutthestatements {
    
    public void initiate(){
        
        String date = this.getDate();
        String currmonth = this.getCurrentMonth();
        String curryear = this.getCurrentYear();
        String date1 = this.getCurrentYear().concat("-").concat(this.getCurrentMonth()).concat("-").concat("01");
        
        int qtr = getQuater(currmonth);
           /* String startmonth = "0".concat(String.valueOf((Integer.parseInt(currmonth))-3)) ;
            String endmonth = "0".concat(String.valueOf((Integer.parseInt(currmonth))-1));*/
            
           if (date == date1 && qtr != 0){
            String startdate = this.getStartDateFormat(this.dateStart(curryear,currmonth));
            String enddate =  this.getEndDateFormat(dateEnd(curryear,currmonth));
            
            this.inititateCashFLow(startdate, enddate, qtr);
            this.inititateIncomeStatement(startdate, enddate, qtr);
            this.inititateSOFP(startdate, enddate, qtr);
           }
    }
    
    private void inititateCashFLow(String start,String end,int q){
       
        cashFlow  cs = new cashFlow();
        
        boolean status = cs.initiateTransfer(start, end, q);
        
    }
    
    private void inititateIncomeStatement(String start,String end,int q){
       
        incomestatement inc = new incomestatement();
        
        boolean status = inc.initiate(start, end, q);
        
    }
    
     private void inititateSOFP(String start,String end,int q){
       
        financialposition sofp = new financialposition();
        
        boolean status = sofp.initiate(start, end, q);
        
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
      private String dateStart(String year,String month){
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
      
     private String dateEnd(String year,String month){
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
     
  private int getQuater(String crrmonth){
      
      if (crrmonth == "01" || crrmonth == "02" || crrmonth == "03" ){
          return 1;
      }
      else if (crrmonth == "04" || crrmonth == "05" || crrmonth == "06"){
          return 2;
      }
      else if (crrmonth == "07" || crrmonth == "08" || crrmonth == "09"){
          return 3;
      }
      else if (crrmonth == "10" || crrmonth == "11" || crrmonth == "12"){
          return 4;
      }
      else {
          return 0;
      }
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
