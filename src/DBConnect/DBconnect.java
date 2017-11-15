/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnect;

/**
 *
 * @author Rashan
 */

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;

public class DBconnect {

       public static Connection connect()
    {
      Connection con=null;
    
    try{
    
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/businessmanagementsystem1","root","");
        
    
    }
    catch(ClassNotFoundException | SQLException e){
    
    System.out.print(e);
    
    }
    
    return con;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
