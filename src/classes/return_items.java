/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import DBConnect.DBconnect;
import net.proteanit.sql.DbUtils;
import Interface.retun_damage_items;
import com.mysql.jdbc.ResultSetImpl;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import Interface.return_damage_history;
import javax.swing.JOptionPane;

/**
 *
 * @author jayabahu
 */
public class return_items extends JFrame{
    
     //update data
    PreparedStatement pst;
    
    
    
    private String itemId;
    private String itemname;
    private float uniprice;
    private int qty;
    private float totamnt;
    private String rsn;
    private String mnyrtn;
    
    
    return_items(String i,String t, float a,int p,float c,String sno,String nC){
       
        this.itemId=i;
        this.itemname=t;
        this.uniprice =a;
        this.qty = p;
        this.totamnt = c;
        this.rsn =sno;
        this.mnyrtn = nC;
        
        Connection con=null;
        
        
            con = DBconnect.connect();//getting the DB connection
                              
                    String sql="UPDATE returns SET Item_ID=?,Product_Name=?,unit_Price=?,Qty=?,Total=?,Reason=?,money_Returns=? WHERE return_ID=?";
                    try {
                        pst=con.prepareStatement(sql);
                        
                pst.setString(1, t);
		pst.setFloat(2, a);
		pst.setInt(3,p);
                pst.setFloat(4, c);
                pst.setString(5, i);
                pst.setString(6,sno);
                pst.setString(7,nC);
                
                        
                        pst.execute();                
                        
                        
                    } catch (Exception e) {
                    }
                    
                    //message after data edit
            JOptionPane.showMessageDialog(rootPane,"Data been updated");
            
      
      
             
        
        
        
        
        
        
    }
    

}
