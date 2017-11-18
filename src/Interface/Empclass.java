
package Interface;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Empclass 
{
    private String empid;
    private String name;
    private int age;
    private int mobile;
    private String address;  
    
    Connection con =null;
    PreparedStatement pst =null;
    ResultSet rs=null;

    public Empclass()
    {
        //connect to DB
        con =DBconnect.connect();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    
    
    public void insert()
    {
          try
      {  
        String q="insert into employee(Emp_ID,Emp_Name,Age,Address,Tel_number) values('"+empid+"','"+name+"','"+age+"','"+address+"','"+mobile+"')";
         pst=con.prepareStatement(q);
         pst.execute();
       
        
        String k="insert into salary_level(Emp_ID) values('"+empid+"')";
         pst=con.prepareStatement(k);
         pst.execute();
         
        String t="insert into salary(Emp_ID) values('"+empid+"')";
         pst=con.prepareStatement(t);
         pst.execute();
         
          
        String g="insert into loans(Emp_ID) values('"+empid+"')";
         pst=con.prepareStatement(g);
         pst.execute();
         
        String h="insert into salary_reduction(Emp_ID) values('"+empid+"')";
         pst=con.prepareStatement(h);
         pst.execute();
         
        String i="insert into attendance(Emp_ID) values('"+empid+"')";
         pst=con.prepareStatement(i);
         pst.execute();
         
         
         
        int result=pst.executeUpdate();
        //tableload();
        if(result == 1){
            JOptionPane.showMessageDialog(null,"Succusfully added");
           // clearfields();
        }
        }
      catch(Exception ex)
      {
      }
    }
    
    public void delete()
    {
        int x=JOptionPane.showConfirmDialog(null,"Do you want to delete?");
       if(x==0)
       {
         
           try{
           String sql="delete from employee where Emp_ID='"+empid+"' ";
           pst=con.prepareStatement(sql);
           pst.execute();
              
         String k="delete from salary_level where Emp_ID='"+empid+"' ";
         pst=con.prepareStatement(k);
         pst.execute();
         
        String t="delete from salary where Emp_ID='"+empid+"'";
         pst=con.prepareStatement(t);
         pst.execute();
         
          
        String g="delete from loans where Emp_ID='"+empid+"'";
         pst=con.prepareStatement(g);
         pst.execute();
         
        String h="delete from salary_reduction where Emp_ID='"+empid+"'";
         pst=con.prepareStatement(h);
         pst.execute();
         
        String i="delete from attendance where Emp_ID='"+empid+"'";
         pst=con.prepareStatement(i);
         pst.execute();
         
           }
        catch(Exception e)
        {
        }
       }
    }
    
    public void update()
    {
           int x=JOptionPane.showConfirmDialog(null,"Do you want to Update?");
       if(x==0)
       {
         
          String sql="update employee set Emp_ID='"+empid+"', Emp_Name='"+name+"', Age='"+age+"', Tel_number='"+mobile+"' ,Address='"+address+"' where Emp_ID='"+empid+"' ";
       try{
        pst=con.prepareStatement(sql);
       
        int result=pst.executeUpdate();
       
        if(result == 1){
            JOptionPane.showMessageDialog(null,"Succusfully updated");
            
        }
        }
        catch(Exception e)
        {}
        
    }
    }
  
    
    
    
    
}
