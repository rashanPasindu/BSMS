
package Interface;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Loanclass 
{
    Connection con =null;
    PreparedStatement pst =null;
    ResultSet rs=null;
    
    private String id;
    private float amount;
    private int perc;
    private String date;
    private String ltype;
    private String duration;
    private float salary;
    
    public Loanclass()
    {
        //connect to DB
        con =DBconnect.connect();
    }
  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPerc() {
        return perc;
    }

    public void setPerc(int perc) {
        this.perc = perc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLtype() {
        return ltype;
    }

    public void setLtype(String ltype) {
        this.ltype = ltype;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
     public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    
      public void assign()
    {
       try
        {
        String q="Update loans set Loan_Type='"+ltype+"',Loan_amount='"+amount+"',Duration='"+duration+"',Obtained_on='"+date+"',Percentage_Allowed='"+perc+"' where Emp_ID='"+id+"'  ";
        pst=con.prepareStatement(q);
        pst.execute();
        
        }
        catch(Exception ex)
        {
        }
    }
       
    

      public float calculate()
    {
       float y=(perc*salary)/100;
       return y;
    }

    public void update()
    {
    
         
        try{
             
        String q="Update loans set Loan_Type='"+ltype+"',Loan_amount='"+amount+"',Duration='"+duration+"',Obtained_on='"+date+"',Percentage_Allowed='"+perc+"' where Emp_ID='"+id+"'  ";
        pst=con.prepareStatement(q);
       
        int result=pst.executeUpdate();
     
        if(result == 1){
            JOptionPane.showMessageDialog(null,"Succusfully updated");
      
           
        }
        }
        catch(Exception e)
        {}
    }

    
}
    

