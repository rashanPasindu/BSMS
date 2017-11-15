package bill;


import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class invoiceId {
    
    Connection connection =null;
    int getValue;

    public int getGetValue() {
        return getValue;
    }

    public void setGetValue(int getValue) {
        this.getValue = getValue;
    }
    
    public void generateInvoiceNo(String passQuery)
    {
      connection=DBconnect.connect();  
      try{
          Statement st=connection.createStatement();
          ResultSet set=st.executeQuery(passQuery);
          
          if(set.next())
          {
              getValue=Integer.parseInt(set.getString(1));
              
          } 
              
          
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    
    
    public void insertInvoiceNo()
    {
        generateInvoiceNo("select count(InvoiceId)+1 from bill");
        String insertdate="insert into bill values (?)";
        connection=DBconnect.connect();
        
        String inv="Invo"+getValue;
        System.out.println(inv);
        
        try{
            PreparedStatement ps=connection.prepareStatement(insertdate);
            ps.setString(1, inv);
            ps.execute();
            System.out.println("data succeful");
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        new invoiceId().insertInvoiceNo();
    }
}
