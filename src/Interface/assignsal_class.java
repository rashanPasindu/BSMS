
package Interface;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class assignsal_class
{
   private String id;
   private float salary;
   private String dt;
    
  Connection con=null;
  PreparedStatement pst=null;
  ResultSet rs=null;

   public assignsal_class()
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

     public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

      public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    
    public void insert()
    {
    try{
                String sql="update salary_level set Salary='"+salary+"',Assigned_Date='"+dt+"' where Emp_ID='"+id+"' ";
                pst=con.prepareStatement(sql);
                pst.execute();
                
                String sql2="update loans set Salary='"+salary+"' where Emp_ID='"+id+"' ";
                pst=con.prepareStatement(sql2);
                pst.execute();
                
               

            }
            catch(Exception e)
            {}
    
    }

 

 
    
    
   
   
}
