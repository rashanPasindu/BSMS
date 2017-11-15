/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author Sandun
 */
public class CustomerController {
    
    static Connection con = DBconnect.connect();
    
    public static int addCustomer(Customer customer) throws SQLException{
        int result=0;
        String q = "INSERT INTO customer(customer_ID,customer_Name,Address,tel_number) values ('" + customer.getCustomer_ID()+ "','" + customer.getCustomer_Name()+ "','" + customer.getAddress()+ "'," + customer.getTel_number()+ ")";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
    public static int deleteCustomer(Customer customer) throws SQLException {
        int result=0;
        String q = "DELETE from customer WHERE customer_ID='"+customer.getCustomer_ID()+"'";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
    public static int updateCustomer(Customer customer) throws SQLException{
        int result=0;
        String q = "UPDATE customer  SET customer_Name='" + customer.getCustomer_Name()+ "',Address='" + customer.getAddress()+ "',tel_number=" + customer.getTel_number()+ " where customer_ID='"+ customer.getCustomer_ID()+"' ";

        PreparedStatement pst = con.prepareStatement(q);
        result = pst.executeUpdate();
        
        return result;
    }
    
    
    public static List<Customer> getAllCustomers() throws SQLException{
        List<Customer> customers =new ArrayList<Customer>();
       
        String sql="select customer_ID,customer_Name,Address,tel_number from Customer";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()) {
            customers.add(new Customer(rs.getString(0), rs.getString(1),rs.getString(2), rs.getInt(3)));
        }
        return customers;
    }
    
    public static ResultSet getAllCustomersAsResultSet() throws SQLException{
        String sql="select customer_ID,customer_Name,Address,tel_number from Customer";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
    
     public static ResultSet searchCustomerByID(String customerId) throws SQLException{
        String sql="select customer_ID,customer_Name,Address,tel_number from Customer WHERE customer_ID LIKE '%"+customerId+"%' ";
         
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
     
      public static ResultSet searchCustomerByName(String customerName) throws SQLException{
        String sql="select customer_ID,customer_Name,Address,tel_number from Customer WHERE customer_Name LIKE '"+customerName+"%' ";
        
        PreparedStatement pst= con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        
        return rs;
    }
    
}
