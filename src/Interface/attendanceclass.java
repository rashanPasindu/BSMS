
package Interface;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class attendanceclass 
{
    Connection con =null;
    PreparedStatement pst =null;
    ResultSet rs=null;
    
    private String Id;
    private String date;
    
    public attendanceclass()
    {
         //connect to DB
        con =DBconnect.connect();
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
     public void insert()
    {
      try
        {
        String q="insert into attendance (Emp_ID,Ab_Dates) values('"+Id+"','"+date+"')";
        pst=con.prepareStatement(q);
        pst.execute();
       
        }
        catch(Exception ex)
        {
        }  
    }
}
