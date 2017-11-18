/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportsFinance;

import java.sql.Connection;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Rashan
 */
public class generateReports {
    
        Connection con = null;
    
 public void showReport(int q,int year) throws JRException{
            
     try {
        this.reportIncomeStatement(q,year);
        //this.reportStatementOfFinancialPosition(q, year);
        //.reportCashFlowStatement(q, year);
         } 
     catch (SQLException ex) {
               System.out.println(ex);
         }
 }
 
public void reportIncomeStatement(int q, int year) throws SQLException
 {   
    try
        {
       
        JasperDesign jd=JRXmlLoader.load("C:\\Users\\Rashan\\Documents\\NetBeansProjects\\BuisnessManagementSystem\\src\\reportsFinance\\reports\\incomestatement.jrxml");
        String sql="SELECT DISTINCT s.quater,s.income_Total,s.Expense_Total,s.PBT,s.Tax,s.PAT,s.Year,v.Sales_Total,v.Other_Incomes,v.Discounts_Received,v.Admin_Exp_Total,v.Maint_Exp_Total,v.Petty_Exp_Total,v.Other_Exp_Total,v.Discounts_Allowed FROM incomestatevalues v, incomestatefinal s WHERE v.quater = any(SELECT DISTINCT quater FROM incomestatevalues WHERE quater = '"+q+"' AND Year = '"+year+"') AND s.quater = any (SELECT DISTINCT quater FROM incomestatefinal WHERE quater = '"+q+"' AND Year = '"+year+"')";
        JRDesignQuery q1= new JRDesignQuery();
        q1.setText(sql);
        jd.setQuery(q1);
        
        JasperReport jr=JasperCompileManager.compileReport(jd);
        JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp);
        }
      catch(Exception e)
        {}

    } 

public void reportStatementOfFinancialPosition(int q, int year) throws SQLException, JRException
 {   
    try
        {
       
        JasperDesign jd=JRXmlLoader.load("C:\\Users\\Rashan\\Documents\\NetBeansProjects\\BuisnessManagementSystem\\src\\reportsFinance\\reports\\incomestatement.jrxml");
        String sql="SELECT DISTINCT s.quater,s.Goodwill,s.Trademark,s.Other,s.Cash,s.Inventory,s.Debtors,s.Land_n_Building,s.Motor_Vehicles,s.Equipment,s.Year,v.Creditors,v.Accrued_Payments,v.Short_Term_Loans,v.Long_Term_Loans,w.Intangible_Assets_Total,w.Current_Assets_Total,w.Fixed_Assets_Total,x.Owners_Equity,x.Current_Liabilities_Total,x.Long_Term_Liabilities_Total FROM sofpvaluestotliab x,sofpvaluestotassets w, sofpvaluesliablities v , sofpvaluesassets s WHERE v.quater = any(SELECT DISTINCT quater FROM sofpvaluesliablities WHERE quater = '"+q+"' AND Year = '"+year+"') AND s.quater = any (SELECT DISTINCT quater FROM sofpvaluesassets WHERE quater = '"+q+"' AND Year = '"+year+"')AND w.quater = any (SELECT DISTINCT quater FROM sofpvaluesassets WHERE quater = '"+q+"' AND Year = '"+year+"')AND x.quater = any (SELECT DISTINCT quater FROM sofpvaluesassets WHERE quater = '"+q+"' AND Year = '"+year+"')";
        JRDesignQuery q1= new JRDesignQuery();
        q1.setText(sql);
        jd.setQuery(q1);
        
        JasperReport jr=JasperCompileManager.compileReport(jd);
        JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp);
        }
      catch(Exception e)
        {}

    } 
public void reportCashFlowStatement(int q, int year) throws SQLException, JRException
 {   
    try
        {
       
        JasperDesign jd=JRXmlLoader.load("C:\\Users\\Rashan\\Documents\\NetBeansProjects\\BuisnessManagementSystem\\src\\reportsFinance\\reports\\incomestatement.jrxml");
        //String sql="SELECT  FROM WHERE quater = '"+q+"' AND Year = '"+year+"'";
        String sql = "SELECT DISTINCT x.quarter,x.Net_Cash_Flows_From_Operating_Activities,x.Net_Cash_Flows_From_Investing_Activities,x.Net_Cash_Flows_From_Financing_Activities,x.Net_Change_in_Cash,x.Cash_in_Begining_of_Quater,x.Cash_in_End_of_Quater,w.Sale_of_Equipment,w.Sale_of_Land,w.Sale_of_Motor_Vehicles,w.Purchase_Resources,v.Operating_Income,v.Depreciation_Expense,v.Loss,v.Gain,v.Increase_in_Receivables,v.Prepaid_Expenses,v.Accounts_Payable,v.Accrued_Expenses,f.Long_Term_Liabilities_Paid, f.Stock_Purchase FROM cashflofinal x,cashfloinvesting w, cashflooperating v , cashflofinancing f WHERE x.quarter = any(SELECT DISTINCT quarter FROM cashflofinal WHERE quarter = '"+q+"' AND Year = '"+q+"') AND w.quater = any (SELECT DISTINCT quater FROM cashfloinvesting WHERE quater = '"+q+"' AND Year = '"+year+"')AND v.quater = any (SELECT DISTINCT quater FROM cashflooperating WHERE quater = '"+q+"' AND Year = '"+year+"')AND f.quater = any (SELECT DISTINCT quater FROM cashflofinancing WHERE quater = '"+q+"' AND Year = '"+year+"')";
        JRDesignQuery q1= new JRDesignQuery();
        q1.setText(sql);
        jd.setQuery(q1);
        
        JasperReport jr=JasperCompileManager.compileReport(jd);
        JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp);
        }
      catch(Exception e)
        {}

    } 
}
