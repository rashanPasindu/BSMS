
package Interfaces;

import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class paint {
    
private String productid ;
private String productname;
private String producttype; 
private int quantity ;
private String date ;
private String manufacture ;
private float profit ;
private float spsi ;       
private String description ;
private float costperunit;

private float liters;
private String color;
                                
                                       
Connection con =null;
private PreparedStatement pst =null;
ResultSet rs=null;

    public paint()
    {
        //connect to DB
        con =DBconnect.connect();
    } 

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getSpsi() {
        return spsi;
    }

    public void setSpsi(float spsi) {
        this.spsi = spsi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCostperunit() {
        return costperunit;
    }

    public void setCostperunit(float costperunit) {
        this.costperunit = costperunit;
    }

    public float getLiters() {
        return liters;
    }

    public void setLiters(float liters) {
        this.liters = liters;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void insert()
    {
    try {
            String q= "INSERT INTO products(ProductID,ProductName,ProductType,Date,MRP,Manufacture,Description,Quantity) values"
                    + " ('"+ productid +"','"+ productname +"','"+ producttype +"','"+ date +"','"+ spsi +"','"+ manufacture +"','"+ description +"','"+ quantity +"')";

            String q1= "INSERT INTO paintsandtools (ProductID,PaintColor,Liters) values"
                   + " ('"+ productid +"','"+ color +"','"+ liters +"')";
           
            pst = con.prepareStatement(q);
            pst.execute();
            pst = con.prepareStatement(q1);
            pst.execute();
           // clearfields();

            //to load the table
          //  tableload();

        } catch (Exception e){

            System.out.println(e);
        }
    }
    public void delete()
    {
    try{

                String sql="DELETE from products WHERE ProductID ='"+ productid +"'";
                String sql1="DELETE from paintsandtools WHERE ProductID ='"+ productid +"'";

                pst = con.prepareStatement(sql);
                pst.execute();
                pst = con.prepareStatement(sql1);
                pst.execute();
               // clearfields();

                //load table
               // tableload();

            }catch(Exception e){

            }
    }
    public void update()
    {
     try{

                String sql="UPDATE products SET ProductName ='"+ productname +"',ProductType ='"+ producttype +"',"
                + "Date ='"+ date +"',MRP ='"+ spsi +"',Manufacture ='"+ manufacture +"',Description ='"+ description +"',"
                + "Quantity ='"+ quantity +"' WHERE ProductID = '"+ productid +"'";
        
                String sql1="UPDATE paintsandtools SET PaintColor ='"+ color +"',Liters ='"+ liters +"'WHERE ProductID = '"+ productid +"'";
        
        
                pst = con.prepareStatement(sql);
                pst.execute();

                pst = con.prepareStatement(sql1);
                pst.execute();
              //  clearfields();

                //load table
               // tableload();

            }catch(Exception e){

            }
    }
    public float calCPU(float mpr,float profit1)
    {
      float calprofit=mpr*(profit1/100);
      Float costperunit=new Float(mpr-calprofit);
             
     return costperunit;
    }
    
}
