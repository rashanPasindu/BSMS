/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import bsmanagementsystem.ExpenseInput;

/**
 *
 * @author Rashan
 */



public abstract class allExpenses {
    
    
   ExpenseInput l6 = new ExpenseInput();
   
    
   
 
    public String expense_ID;
    public String expense_type;
    public String expense_category;
    public String payment_method;
    public float amount;
    public String description;
    public String approved_by;
    
    
    
    public allExpenses(){
        
    }
    
    public allExpenses(String id, String eCat, String eType, String pMethod, float amnt, String descp, String by){
        
        this.expense_ID = id;
        this.expense_type = eType;
        this.expense_category = eCat;
        this.payment_method = pMethod;
        this.amount = amnt;
        this.description = descp;
        this.approved_by = by;
        
    }
    
    public allExpenses(String id, String eCat, String pMethod, float amnt, String descp, String by){
        
        this.expense_ID = id;
        this.expense_category = eCat;
        this.payment_method = pMethod;
        this.amount = amnt;
        this.description = descp;
        this.approved_by = by;
        
    }
    
    
    
    public void fillcombo(){
        
    }
    
    public void fillcombo1(){
        
    }
    
    
    public int tabCount(){
           
       int count = l6.tbdp.getTabCount();
       
       return count;
        
   }
    public void sendToDB(String eID,String cat, String method, double amount, String desc, String apprvl,String date){
    
    }
    
    public void  initiateTransfer(){
    
    }
     public void tableLoad(){
     }
     
}
