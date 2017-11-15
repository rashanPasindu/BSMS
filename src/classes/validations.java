/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import bsmanagementsystem.Approval;
import bsmanagementsystem.ExpenseInput;
import bsmanagementsystem.TaxSummary;
import bsmanagementsystem.policy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Rashan
 */
public class validations {
    
    Approval app = new Approval();
    ExpenseInput exp = new ExpenseInput();
    TaxSummary tax = new TaxSummary();
    policy po = new policy();
    
    public boolean validateExpAdmin(String AdminAmount,String AdminDesc,String AdminAprvl){
         
     try{   
      if(AdminAmount == ""){
      msgbox("Enter Amount");
      return false;    
      }
      else if(AdminDesc == ""){
      msgbox("Enter Description");
      return false;    
      }
      else if(AdminAprvl == ""){
      msgbox("Enter Description");
      return false;
      }
      else{
          return validateAmount(AdminAmount);
           
      }
      
    }
     catch (Exception e)
     {
         return false;
     }
    }
    public boolean validateExpMainten(String MaintenAmount,String MaintenDesc,String MaintenAprvl){
     try{
      if(MaintenAmount== ""){
      msgbox("Enter Description");
      return false;
      }
      else if(MaintenDesc== ""){
      msgbox("Enter Description");
      return false;
      }
      else if(MaintenAprvl== ""){
      msgbox("Enter Description");
      return false;
      }
      else{
         return validateAmount(MaintenAmount);
      }}
     catch (Exception e){
         return false;
     }
    }
      public boolean validateExpPetty(String PettyAmount,String PettyDesc,String PettyAprvl){
      try{
      if(PettyAmount== ""){
      msgbox("Enter Description");
      return false;
      }
      else if(PettyDesc== ""){
      msgbox("Enter Description");
      return false;
      }
      else if(PettyAprvl== ""){
      msgbox("Enter Description");
      return false;
      }
      else{
          return validateAmount(PettyAmount);
      }     
    }
      catch(Exception e){
          return false;
      }
      }
    public boolean validateExpOther(String OtherAmount,String OtherDesc,String OtherAprvl){
      try{
      if(OtherAmount== ""){
      msgbox("Enter Description");
      return false;
      }
      else if(OtherDesc== ""){
      msgbox("Enter Description");
      return false;
      }
      else if(OtherAprvl== ""){
      msgbox("Enter Description");
      return false;
      }
      else{
          return validateAmount(OtherAmount);
      }     
    }
      catch (Exception e){
          return false;
      }}
    public boolean validateAmount(String Amount){
        boolean status = false;
        int l; char temp; boolean chk1 = false,chk3 = false; boolean chk2 = false;
        
        try{
        if(!"0.00".equals(Amount)){
        status=false;
        
        }
        else{
            for (l=0;l<=Amount.length();l++){
                temp = Amount.charAt(l);
                int n = Amount.length();
                System.out.println();
                if (l == n-3){
                    //chk1 =  true;
                  if (temp =='.'){
                      chk1 = true;
                  }
                  else{
                      chk1 = false;
                  }}
                  else if( l == n-2){
                         
                     if (temp == '0'){
                          chk2 = true;
                     }
                     else{
                          chk2 = false;
                     }
                  }
                  else if( l == n-1){
                      if (temp == '0'){
                          chk3 = true;
                      }
                      else{
                          chk3 = false;
                      }
                  } 
                  else {
                    chk1 = false;
                    chk2 = false;
                    chk3 = false;
                } 
            }
            if (chk1 == true && chk2 == true && chk3 == true){
                status = true;
            }
            else{
            status = false;
        }
        }}
        catch (Exception e){
             status=false;
        }
        return status;
    }
    public void validateApproval(){
    
         
                if (app.jDateChooser1.getDate() == null && app.jDateChooser2.getDate() == null )
                {
                    JOptionPane.showMessageDialog(null, "Please Select Start & End Dates", "Date Selector Check!", JOptionPane.INFORMATION_MESSAGE );
                }
                
                else if (app.jDateChooser1.getDate() == null && app.jDateChooser2.getDate() != null)
                {
                    JOptionPane.showMessageDialog(null, "Please Select Start & End Dates", "Date Selector Check!", JOptionPane.INFORMATION_MESSAGE );
                }
                
                else if (app.jDateChooser1.getDate() != null && app.jDateChooser2.getDate() == null )
                {
                    JOptionPane.showMessageDialog(null, "Please Select CStart & End Dates", "Date Selector Check!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    
                }
                        
}
    public void validateTax(){
        
        
                if (tax.jDateChooser1.getDate() == null && tax.jDateChooser2.getDate() == null )
                {
                    JOptionPane.showMessageDialog(null, "Please Select Start & End Dates", "Date Selector Check!", JOptionPane.INFORMATION_MESSAGE );
                }
                
                else if (tax.jDateChooser1.getDate() == null && tax.jDateChooser2.getDate() != null)
                {
                    JOptionPane.showMessageDialog(null, "Please Select Start & End Dates", "Date Selector Check!", JOptionPane.INFORMATION_MESSAGE );
                }
                
                else if (tax.jDateChooser1.getDate() != null && tax.jDateChooser2.getDate() == null )
                {
                    JOptionPane.showMessageDialog(null, "Please Select CStart & End Dates", "Date Selector Check!", JOptionPane.INFORMATION_MESSAGE );
                }
                else{
                    
                }
        
    }
    public boolean validatePolicy(String Pselection,float porra, String des){
        
      
      if(Pselection == ""){
      msgbox("Please Select either Percentage OR Rate from available selections");
      return false;    
      }
      else if(porra == 0){
      msgbox("Enter Value");
      return false;    
      }
      else if(des == ""){
      msgbox("Enter Description");
      return false;
      }
      else{
          return true;         
      }
      
    }
    private void msgbox(String s)
{
      JOptionPane.showMessageDialog(null,s);
}
}
