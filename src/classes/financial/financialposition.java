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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rashan
 */
 

public class financialposition {
    
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    
    float intangibleTotal = 0;
    float goodwill =0;
    float trademark=0;
    
    float cashTotal = 0;
    float InventroyTOT = 0;
    float debtorsTot = 0;
    float other=0;
    
    float landBuild=0;
    float motorV=0l;
    float equip=0;
    
    float currAssestsTot = 0;
    float fixdAssestsTot = 0;
    float assetsTot = 0;
    
    float equity = 0;
    float creditorsTotal = 0;
    float accruedPaymentsTot = 0;
    float loansTotal =0;
    float shortloansTotal =0;
    float LongloansTotal =0;
    float loanInstallmentTOT=0;
    
    float currentLiabilitiesTotal=0;
    float longtermLiabilitiesTotal=0;
    float liablilitiesTotal = 0;
    
    String date;
    String matchedCat="";
    float extrapay=0.0f;
    float rechk=0.0f;

private void setValues(String start,String end){
    
    intangibleTotal=goodwill+trademark;
    cashTotal = cashTotal(start,end);
    InventroyTOT = inventoryTot(getStartDateFormat(start),getEndDateFormat(end));
    debtorsTot = getDebtors(start,end);
    
    landBuild=calcBuilding();
    motorV=calcMV();
    equip=calcEquip();
    
    currAssestsTot=cashTotal+InventroyTOT+debtorsTot+intangibleTotal+other;
    fixdAssestsTot=fixdAssestsTot();
    
    assetsTot=currAssestsTot+fixdAssestsTot;
    
    creditorsTotal=getTotCreditors(start,end);
    accruedPaymentsTot=getTOTAccruedPayments(start,end);
    if(chkLoaninstallmentPayStatus()==false){
         accruedPaymentsTot += rechk;
    }
    
    loanInstallmentTOT=getTOTloanInstallments();
    shortloansTotal=shortTermLoans();
    LongloansTotal=LongTermLoans();
    loansTotal=shortloansTotal+LongloansTotal;
    
    currentLiabilitiesTotal=creditorsTotal+accruedPaymentsTot+loanInstallmentTOT;
    longtermLiabilitiesTotal=loansTotal;
    
    liablilitiesTotal=currentLiabilitiesTotal+longtermLiabilitiesTotal;
    
    date = getCurrentDate() ;
}

public boolean initiate(String start, String end){
    boolean chk1,chk2,chk3,chk4;
    setValues(start,end);
    chk1=sendtoDB(goodwill,trademark,other,cashTotal,InventroyTOT,debtorsTot,landBuild,motorV,equip,date);
    chk2=sendtoDB1(intangibleTotal,currAssestsTot,fixdAssestsTot,date);
    chk3=sendtoDB2(creditorsTotal,accruedPaymentsTot,shortloansTotal,LongloansTotal,date);
    chk4=sendtoDB3(currentLiabilitiesTotal,longtermLiabilitiesTotal,date);
    
   if (chk1==true && chk2==true && chk3==true && chk4==true){
       return true;
   }
   else{
       System.out.println("Error");
       return false;
   }
    
}

private boolean sendtoDB(float goodwill,float trademark,float other,float cash,float inventory,float debtors,float landANDBuild,float motorV,float equip,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `sofpvaluesassests` (`Goodwill`,`Trademark`,`Other`,`Cash`,`Inventory`,`Debtors`,`Land_&_Bilding`,`Motor_Vehicles`,`Equipment`,`Date`)VALUES ('"+goodwill+"','"+trademark+"','"+other+"','"+cash+"','"+inventory+"','"+debtors+"','"+landANDBuild+"','"+motorV+"','"+equip+"','"+date+"');";
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

private boolean sendtoDB1(float intangibleTOT,float currentTOT,float fixedTOT,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `sofpvaluestotassests` (`Intangible_Assets_Total`,`Current_Assets_Total`,`Fixed_Assets_Total`,`Date`)VALUES ('"+intangibleTOT+"','"+currentTOT+"','"+fixedTOT+"','"+date+"','"+date+"');";
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

private boolean sendtoDB2(float creditors,float accruedPay,float shortloans,float longloans,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `sofpvaluesliablities` (`Creditors`,`Accrued_Payments`,`Short-Term-Loans`,`Long-Term-Loans`,`Date`)VALUES ('"+creditors+"','"+accruedPay+"','"+shortloans+"','"+longloans+"','"+date+"');";
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

private boolean sendtoDB3(float currentTOT,float longTOT,String date){
      con = DBconnect.connect();
       
       try{
           String sql = "INSERT INTO `sofpvaluestotliab` (`Current_Liabilities_Total`,`Long_Term_Liabilities_Total`,`Date`)VALUES ('"+currentTOT+"','"+longTOT+"','"+date+"');";
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

private boolean chkLoaninstallmentPayStatus(){
    boolean finals=false;
    boolean fStatus=false;
    boolean catStatus=false;
    boolean valuestatus=false;
    float valueOFInstallment=0.0f;
    float valuefromOtherExpenses=0.0f;
    String CategoryfromOtherEXp;
    
    try{
        String sql="SELECT `Category` FROM otherexp ";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while(rs.next()){
            CategoryfromOtherEXp= rs.getString(sql);
            catStatus=chkCat(CategoryfromOtherEXp);
             if(catStatus==true){
                break;
            }
        }
        
    }
    catch(Exception e){
        System.out.println(e);
        catStatus=false;
    }
    
    if (catStatus==true){
        try{
        String sql="SELECT `Amount` FROM otherexp WHERE `Category`='"+matchedCat+"'";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        valuefromOtherExpenses = Float.parseFloat(rs.getString(sql));
        
       }
       catch(Exception e){
        System.out.println(e);
       }
        
        try{
        String sql="SELECT `Installment` FROM loandetails";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        while(rs.next()){
        valueOFInstallment = Float.parseFloat(rs.getString(sql));
        valuestatus=chkvalue(valueOFInstallment,valuefromOtherExpenses);
        if (valuestatus==true){
            fStatus= true;
            break;
         }
        else{
            fStatus= false;
        }
        }
        finals= true;
       }
       catch(Exception e){
        System.out.println(e);
        finals= false;
       }
    }
    return finals;  
}

private boolean chkCat(String cat){
    int i=0;
    String[] arr = {"Installment","installment","Loan","loan","Loan Pay","loan pay","Monthly Loan Pay","monthly loan pay","Bank Loan","bank loan"};
    
    while(arr.length != i)
    {   
        if (cat==arr[i]){
            matchedCat=cat;
            return true;
        }
        else{
            i++;
        }
    }
    return false;
}

private boolean chkvalue(float valInst,float valFromOthers){
   String valueDis;
   float foundVal=0.0f;
   boolean dummyStatus=false;
   
    if (valInst==valFromOthers || dummyStatus==true){
     return true;
    }
    else{
        try{
            String sql="SELECT `Description` FROM otherexp WHERE `Amount`='"+valFromOthers+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            valueDis=rs.getString(sql);
            
            Pattern pat = Pattern.compile("[0-9]+.[0-9]+|[0-9]+");
            Matcher mat = pat.matcher(valueDis);
            
            foundVal=Float.parseFloat(mat.group());
            
            //extrapay=valFromOthers-valInst;
             extrapay = foundVal; 
            if(extrapay > 0){
                boolean stat; int i=0;
              
                rechk=valFromOthers-extrapay;
                stat=chkvalue(valInst,rechk);
                
                if(stat==false){
                    
               /* Pattern pat1 = Pattern.compile("[a-z]+|\\d+");
                Matcher mat1 = pat1.matcher(valueDis);*/
                
                //String[] arr = new String[mat.groupCount()];
                String[] arr = valueDis.split("\\b+");
                String[] extraPay = {"extraPay","Extra Pay","Extra pay","extra pay","extra Pay","extra","Extra","additional","Additional"};
                String[] doubleInstPay = {"two months pay","twomonthspay","Two months pay","Two Months Pay","twomonthspay","Double Installments","double installments"};
                String[] penalty = {"Penalty Percentage","Penalty Percent","penalty percentage","penaltypercentage","Penalty %","penalty %","penalty%","Penalty","penalty"};
                
                boolean extraStat=false,doubleStat=false,penaltyStat=false;
                //while(mat1.find()){
                while(i<arr.length){
                i=0;
                //arr[i]=mat.group();
                
                if (arr.length != 0){
                 int x;
                    Pattern pat2 = Pattern.compile(extraPay[i]);
                    Pattern pat3 = Pattern.compile(doubleInstPay[i]);
                    Pattern pat4 = Pattern.compile(penalty[i]);
                    
                    int size1=extraPay.length;
                    int size2=doubleInstPay.length;
                    int size3=penalty.length;
                    
                    
                    
                    for(x=0;x<size1;x++){
                      Matcher mat2 = pat2.matcher(arr[i]);
                      
                      if(mat2.find()==true){
                          extraStat=true;
                      }
                      else{
                          extraStat=false;
                          
                      }
                   }
                   for(x=0;x<size2;x++){
                      Matcher mat2 = pat3.matcher(arr[i]);
                      
                      if(mat2.find()==true){
                          doubleStat=true;
                      }
                      else{
                          doubleStat=false;
                          
                      }
                   }
                   for(x=0;x<size3;x++){
                      Matcher mat2 = pat4.matcher(arr[i]);
                      
                      if(mat2.find()==true){
                          penaltyStat=true;
                      }
                      else{
                          penaltyStat=false;      
                      }
                   }
                    
                }
                i++;
                }
                float value=getTheCalculated(extraStat,doubleStat,penaltyStat,valFromOthers,valueDis);
                boolean stat1=chkvalue(valInst,value);
               }
            }
           }
        catch(Exception e){
            System.out.println(e);
            
        }
        return false;
    }    
}

private float getTheCalculated(boolean extraStat,boolean doubleStat,boolean penaltyStat,float valFromOthers,String valDis){
    float value=0.0f;
    int  x=1;
    
    if(doubleStat==true ){
        value = (valFromOthers/2);
        return value;
    }
    else if(doubleStat==true && penaltyStat==true ){
        
        float givenValue=valFromOthers;
        float percent=getPenaltyPercentage(valDis);
        
        float val1=(givenValue/2);
        float val2=(2/percent);
        float val3=(val1*val2);
        float val4=(x+val2);
        
        value=(val3/val4);
        return value;
    }
    else if(extraStat==true && doubleStat==true && penaltyStat==true){
        float extra = getExtraValue(valDis);
        
        float givenValue=(valFromOthers-extra);
        float percent=getPenaltyPercentage(valDis);
        
        float val1=(givenValue/2);
        float val2=(2/percent);
        float val3=(val1*val2);
        float val4=(x+val2);
        return value;
    }
    else{
        return value;
    }
}

private float getPenaltyPercentage(String valDis){
    float pen=0.0f;
    
       int i,size=0;
      String valU="";
      
      String patt ="[0-9]+.[0-9]+%|[0-9]+%";
      Pattern pat = Pattern.compile(patt);
      Matcher mat = pat.matcher(valDis);
      String match="";
      while(mat.find()){             
                match=mat.group();
                
                if(match != null){
                    for(int x=0;x<match.length()-1;x++){
                        valU = valU.concat(Character.toString(match.charAt(x)));
                    }
                    size=match.length();
                }    
      }
        pen=Float.parseFloat(valU);
       
    return pen;
}

private float getExtraValue(String valDis){
    float extraV=0.0f;
    int i=0,x;
    String match="";
      //String patt ="[0-9]+.[0-9]+%|[0-9]+%";
      String[] arr = valDis.split("\\b+");
      int size = arr.length;
      
      for(i=0;i<size;i++){
      
          Pattern pat = Pattern.compile("^(([1-9]*)|(([1-9]*)\\.([0-9]*)))$");

          Matcher mat = pat.matcher(arr[i]);
      
            while(mat.find()){
                match = mat.group();
                x = i;
            }
       
     }
      
    return extraV;
}
private float fixdAssestsTot(){
    float tot=0.0f;
    
    tot=calcBuilding()+calcMV()+calcEquip();
    
    return tot;
}
private float cashTotal(String start,String end){
    
    float cashTOT=0.0f;
    
    return cashTOT;        
}    

private float inventoryTot(String start,String end){
    float invTot=0.0f;
    //String array[];
    
    con=DBconnect.connect();
    try{
    String sql = "SELECT `ProductID`,`Date`,`Quantity`,`CostPerUnit` FROM products WHERE `Date` >= any (SELECT `Date` FROM `products` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `products` WHERE `Date` <= '"+end+"')";
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
    
    int nCol = rs.getMetaData().getColumnCount();
    System.out.print(nCol);
    
    List<String[]> table = new ArrayList<>();                                                                                                                                      
    String[] row = new String[nCol];
    
    while(rs.next()){
  
        for(int iCol=1;iCol<=nCol;iCol++){
            Object obj = rs.getObject(iCol);
            row[iCol-1]=(obj == null)?null:obj.toString();
        }
        table.add(row);
    }
    
    if (table.isEmpty()){
        System.out.println("No data to get details");
    }
    else{
        int rowCount = table.size();
        
        String qaty[];
        int qty,i=1;
        float value,valueF;
        
        while(i<=rowCount){
            qaty=table.get(i);
            System.out.println(qaty.length);
            
            qty   = Integer.parseInt(qaty[2]);
            value = Float.parseFloat(qaty[3]);
            valueF = qty*value;
            System.out.println(valueF);
            
            invTot += valueF;
            i++;
        }
        
    }
    
    return invTot;
    }
    catch(Exception e){
        System.out.println(e);
        return invTot=0.0f;
    }
}

private float getDebtors(String start,String end){
    float debtTot=0.0f;
    
    con = DBconnect.connect();
    
    try{
        String sql = "SELECT SUM(`Net_Amount`) FROM creditsales WHERE `Date` >= any (SELECT `Date` FROM `creditsales` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `creditsales` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        String tot = rs.getString(sql);
        
        debtTot=Float.parseFloat(tot);
        return debtTot;
    }
    catch(Exception e){
        System.out.println(e);
        return debtTot;
    }
     
}

private float calcBuilding(){
    float builvalF=0.0f;
    float originalBuildingValue=0.0f;
    float perc=0.0f;
    
    con = DBconnect.connect();
    
    try{
     String sql = "SELECT SUM(`cost`) FROM `other_resources`";    
     pst = con.prepareStatement(sql);
     rs = pst.executeQuery();
     
      originalBuildingValue= Float.parseFloat(rs.getString(sql));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    try{
      
     String sql0 = "SELECT SUM(`value`) FROM `other_resources`";    
     pst = con.prepareStatement(sql0);
     rs = pst.executeQuery();
     
     builvalF= Float.parseFloat(rs.getString(sql0));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    try{
      
     String sql1 = "SELECT `percentage` FROM `policy_update` WHERE `policyType`='Depreciation for Land & Building'";    
     pst = con.prepareStatement(sql1);
     rs = pst.executeQuery();
     
     perc = Float.parseFloat(rs.getString(sql1));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    if(builvalF != 0.0f && perc != 0.0f){
      
        builvalF = builvalF-((builvalF*(perc/100.0f))/4);
      return builvalF;
    }
    else{
    System.out.println("No depreciation calculated");
    return builvalF;
    }
}

private float calcMV(){
    float MVvalF=0.0f;
    float originalMvValue=0.0f;
    float perc=0.0f;
    
    con = DBconnect.connect();
    
    try{
     String sql = "SELECT SUM(`vehicle_cost`) FROM `vehicle`";    
     pst = con.prepareStatement(sql);
     rs = pst.executeQuery();
     
      originalMvValue= Float.parseFloat(rs.getString(sql));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    try{
      
     String sql0 = "SELECT SUM(`vehicle_cost`) FROM `vehicle`";    
     pst = con.prepareStatement(sql0);
     rs = pst.executeQuery();
     
     MVvalF= Float.parseFloat(rs.getString(sql0));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    try{
      
     String sql1 = "SELECT `percentage` FROM `policy_update` WHERE `policyType`='Depreciation for MotorVehicles'";    
     pst = con.prepareStatement(sql1);
     rs = pst.executeQuery();
     
     perc = Float.parseFloat(rs.getString(sql1));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    if(MVvalF != 0.0f && perc != 0.0f){
      
        MVvalF = MVvalF-((MVvalF*(perc/100.0f))/4);
      return MVvalF;
    }
    else{
    System.out.println("No depreciation calculated");
    return MVvalF;
    }
}

private float calcEquip(){
    float EquipvalF=0.0f;
    float originalEquipValue=0.0f;
    float perc=0.0f;
    
    con = DBconnect.connect();
    
    try{
     String sql = "SELECT SUM(`Value`) FROM `equipment`";    
     pst = con.prepareStatement(sql);
     rs = pst.executeQuery();
     
      originalEquipValue= Float.parseFloat(rs.getString(sql));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    try{
      
     String sql0 = "SELECT SUM(`Value`) FROM `equipment`";    
     pst = con.prepareStatement(sql0);
     rs = pst.executeQuery();
     
     EquipvalF= Float.parseFloat(rs.getString(sql0));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    try{
      
     String sql1 = "SELECT `percentage` FROM `policy_update` WHERE `policyType`='Depreciation for Equip'";    
     pst = con.prepareStatement(sql1);
     rs = pst.executeQuery();
     
     perc = Float.parseFloat(rs.getString(sql1));
    }
    catch (Exception e){
        System.out.println(e);
    }
    
    if(EquipvalF != 0.0f && perc != 0.0f){
      
        EquipvalF = EquipvalF-((EquipvalF*(perc/100.0f))/4);
      return EquipvalF;
    }
    else{
    System.out.println("No depreciation calculated");
    return EquipvalF;
    }
}

private float getTotCreditors(String start,String end){
    float cTot=0.0f;
    
    con = DBconnect.connect();
    
    try{
        String sql="SELECT SUM(`payment`) FROM rorders WHERE `payType`='Credit' AND `rdate` >= any (SELECT `rdate` FROM `rorders` WHERE `rdate` >= '"+start+"') AND `rdate` <= any (SELECT `rdate` FROM `rorders` WHERE `rdate` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        cTot = Float.parseFloat(rs.getString(sql));
        return cTot;
    }
    catch (Exception e){
        System.out.println(e);
        return cTot;
    }
    
}

private float getTOTAccruedPayments(String start,String end){
    float accTOT=0.0f;
    
    con = DBconnect.connect();
    try{
        
        String sql = "SELECT SUM(`Amount`) FROM otherentries WHERE `Date` >= any (SELECT `Date` FROM `otherentries` WHERE `Date` >= '"+start+"') AND `Date` <= any (SELECT `Date` FROM `otherentries` WHERE `Date` <= '"+end+"')";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        accTOT = Float.parseFloat(rs.getString(sql));
        return accTOT;
        
    }
    catch (Exception e){
        System.out.println(e);
        return accTOT;
    }
    
    
}

private float getTOTloanInstallments(){
    float loanInstTOT=0.0f;
    
    con = DBconnect.connect();
    try{
        
        String sql = "SELECT SUM(`Installment`) FROM loandetails";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        loanInstTOT = Float.parseFloat(rs.getString(sql));
        return loanInstTOT;
        
    }
    catch (Exception e){
        System.out.println(e);
        return loanInstTOT;
    }
}

private float shortTermLoans(){
    float shortloan=0.0f;
    con = DBconnect.connect();
    
    try{
      String sql="SELECT SUM(`Value`) FROM loandetails WHERE Loan_Type='Short-Term Loans'";
      pst = con.prepareStatement(sql);
      rs = pst.executeQuery();
       shortloan = Float.parseFloat(rs.getString(sql));
       return shortloan;
    }
    catch(Exception e){
        System.out.println(e);
        return shortloan;
    }  
}

private float LongTermLoans(){
    float longloan=0.0f;
    con = DBconnect.connect();
    
    try{
      String sql="SELECT SUM(`Value`) FROM loandetails WHERE Loan_Type='Long-Term Loans'";
      pst = con.prepareStatement(sql);
      rs = pst.executeQuery();
       longloan = Float.parseFloat(rs.getString(sql));
       return longloan;
    }
    catch(Exception e){
        System.out.println(e);
        return longloan;
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

