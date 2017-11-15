package bill;


import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class Payment_2  

{ 
    
private JTable jTable1;
private JTextField jTextField2;
private JTextField jTextField7;
private JTextField jTextField6;
private JTextField jTextField8;

Connection con = null;
PreparedStatement pst = null;
ResultSet rs = null;
Statement st=null;

    public Payment_2() {
        con = DBconnect.connect();
    }
 





    public String validation()
         
        {
                int f=0; 
                String s1=jTextField2.getText();
                String s2=null;
                for(int i=0;i<s1.length();i++)
                {
                    char a=s1.charAt(i);  
       
                        if(Character.isLetter(a))
                            {
                                 f=1;
                                 s1=s1.substring(0, i);   
                            }
                }
                if(f==1)
                {
                    JOptionPane.showMessageDialog(null,"character not allowed");
                    jTextField2.setText(null);
                }
         return s2;

 
        }
    
    
    public  void  tableload()
            
        {
           
                try
                    {
                       
                         String sql= "SELECT ItemCode,ItemName,Quantity,Discount,NetPrice FROM billing";
                         pst = con.prepareStatement(sql);
                         rs = pst.executeQuery();
         
                         jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                         System.out.println("yes table load");
                    }
        
                catch(Exception e)
                    {
        
                         JOptionPane.showMessageDialog(null, e);
                         System.out.println(e);
                         e.printStackTrace();
                    }
        }
    
  
    
    public void Calculation()
        {
                Float saleAmount1;
                Float quty1;
                Float discount1;
                Float Sum=0f;
                saleAmount1=Float.parseFloat(jTextField2.getText());
                quty1=Float.parseFloat(jTextField6.getText());
                discount1=Float.parseFloat(jTextField8.getText());
        
        
                Sum=((saleAmount1*quty1)-((saleAmount1*quty1)*discount1/100));
                String Total=String.format("%.2f", Sum);
                jTextField7.setText(Total); 
        }
}




