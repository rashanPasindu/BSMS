package bill;


import java.util.HashMap;
import javax.swing.JFrame;
import java.sql.*;
import java.io.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.view.*;
import java.util.*;
import java.awt.*;
import java.text.SimpleDateFormat;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class IreportProfit extends JFrame {
     Connection con = null;
    public IreportProfit(String fileName,HashMap Parameters)
    {
       try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hardware2","root","");
        
        
         JasperPrint Jp=JasperFillManager.fillReport(fileName,Parameters,con);
        JRViewer viwer=new JRViewer(Jp);
        Container c=getContentPane();
        c.add(viwer);
       }
        catch (Exception e) {
            System.out.println(e);
         setBounds(10,10,600,500);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
       
    }
    public static void main(String[] args)
    {
        HashMap param=new HashMap();
        param.put("$P{fromDate}", "s1");
        param.put("$P{toDate}","s2");
        IreportProfit showme=new IreportProfit("C:\\Users\\DELL\\Documents\\NetBeansProjects\\check\\src\\profitable.jasper",param);
        showme.setVisible(true);
    }
}
