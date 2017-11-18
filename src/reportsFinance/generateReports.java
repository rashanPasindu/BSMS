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
        String sql="SELECT s.quater,s.income_Total,s.Expense_Total,s.PBT,s.Tax,s.PAT,s.Year,v.Sales_Total,v.Other_Incomes,v.Discounts_Received,v.Admin_Exp_Total,v.Maint_Exp_Total,v.Petty_Exp_Total,v.Other_Exp_Total,v.Discounts_Allowed FROM incomestatevalues v, incomestatefinal s WHERE quater = '"+q+"' AND Year = '"+year+"'";
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
        String sql="SELECT  FROM WHERE quster = '"+q+"' AND Year = '"+year+"'";
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
        String sql="SELECT  FROM WHERE quster = '"+q+"' AND Year = '"+year+"'";
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
