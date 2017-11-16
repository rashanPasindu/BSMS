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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rashan
 */
public class acrruedpay {
    Connection con = null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    
   public float trackAccruedExpenses(String start, String end){
        
       return (adminAccruedTOT(start,end)+maintAccruedTOT(start,end)+pettyAccruedTOT(start,end)+otherAccruedTOT(start,end));
 
    }
    
    private float adminAccruedTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float adTOT = 0.00f;
        boolean status = false;
        String desad[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `adminexpenses` WHERE `Date` >= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `adminexpenses` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = accruedMatcher(rs.getString(sql).toString());
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
        
         adTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return adTOT;
    }
    
        private float maintAccruedTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float mainTOT = 0.00f;
        boolean status = false;
        String desmain[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `maintainexp` WHERE `Date` >= any (SELECT `Date` FROM `maintainexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `maintainexp` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = accruedMatcher(rs.getString(sql).toString());
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
        
         mainTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return mainTOT;
    }
        
     private float pettyAccruedTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float pettyTOT = 0.00f;
        boolean status = false;
        String despetty[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `pettycashexp` WHERE `Date` >= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `pettycashexp` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = accruedMatcher(rs.getString(sql).toString());
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
        
         pettyTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return pettyTOT;
    }
     
    private float otherAccruedTOT(String start,String end){
        con = DBconnect.connect();
        
        int count = 0;
        float otherTOT = 0.00f;
        boolean status = false;
        String desother[] = new String[50];
        
         
        try{
        String sql="SELECT `Description` FROM `otherexp` WHERE `Date` >= any (SELECT `Date` FROM `otherexp` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `otherexp` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while (rs.next())
            status = accruedMatcher(rs.getString(sql).toString());
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
        
         otherTOT += Float.parseFloat(rs.getString(sql).toString());
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        return otherTOT;
    }
    private boolean accruedMatcher(String Description){
        
      String match = ""; 
      
      String patt = "accrued payment|Accrued Payment";
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
    
}
