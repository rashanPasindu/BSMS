/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import DBConnect.DBconnect;
import bill.Payment;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;

/**
 *
 * @author jayabahu
 */
public class retun_damage_items extends javax.swing.JFrame {
 Connection con=null;
 Connection con1=null;
 PreparedStatement stm=null;
 
 PreparedStatement cmb=null;    
    ResultSet rs=null;
    
 //brand combobox
    ResultSet rs1=null;
    PreparedStatement stmt=null;
    
    //brand category
    ResultSet rs2=null;
    PreparedStatement stmt1=null;
    
    //brand itemname
    ResultSet rs3=null;
    PreparedStatement stmt2=null;
    String cate=null;
    
    //search bill
    //brand category
    ResultSet rs4=null;
    PreparedStatement stmt4=null;
    
    
     //find unit price
    ResultSet rs5=null;
    PreparedStatement stmt5=null;
    
    //add data to return
    ResultSet rs6=null;
    PreparedStatement stmt6=null;
    ResultSet rs7=null;
    PreparedStatement stmt7=null;
    String rn_or_dmg;
    String mny_trn_chk;
    String inv_or_grn;
     String prod_type;
    
    //retunID generate
    String rtn_id;
    int rtn;
    PreparedStatement cmb1=null;
    ResultSet rs0=null;
    
    
    //return table 
        String rtn_cat;
        
         //variables for data
                int return_id;
                String item_id;
                String pro_name;
                String pro_type;
                int qty;
                float tot;
                String reason;
                String status;
                float uni_prc;
                String grn_id=null;
                String mny_rtn;
                String amnt_rtn;
                String bill_id;
                String sup_id;
                String date = getCurrentDate();
        
        ResultSet rs11=null;
        PreparedStatement stmt11=null;
        ResultSet rsa=null;
    PreparedStatement stmta=null;
    
    ResultSet rsb=null;
    PreparedStatement stmtb=null;
    
        String date1;
    
    //brand category
    ResultSet rs12=null;
    PreparedStatement stmt12=null;
    
    
    //variables for exchange
        String ret_id;
        int bi_id;
        String pr_id;
        String pr_name;
        String pr_type;
        String manu;
        float cost;
        int qty_ex;
        float tot_ex;
        float ex_pay;
        int stat;
    
    /**
     * Creates new form return_damage_items
     */
    public retun_damage_items() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        con=DBconnect.connect();
        
        
        
        rtn_dmg_bill_id_txt.setEditable(false);
        rtn_dmg_item_id_txt.setEditable(false);
        rtn_id_gnrate_txt.setEditable(false);
        

        //auto generate ID for return
        try {
             String sql="SELECT max(return_ID) FROM returns";
             cmb1=con.prepareStatement(sql);
             rs0=cmb1.executeQuery();
                   rs0.next();
                   
             int a=0;
             a=rs0.getInt(1);
             if(a==0){
                 a++;
                    rtn_id_gnrate_txt.setText(""+a);
             }else{
                 a++;
                 rtn_id_gnrate_txt.setText(""+a);
             }
            
        } catch (Exception e) {
        }
        
        
        //system date
            
        
        
        
        
        
        
        
        //setting extra pay textbox disable
        rtn_dmg_exg_extra_txt.setEnabled(false);
        
        
        
        //load all Bill details
        tableload();
        
        //load manufac combo (brand)
        load_brand();
        
        
        
    }
    
    
    //method to load all data into the table
     public void tableload()
    {
        try {
             String sql="SELECT Bill_ID,item_code,Item_Name,QTY,Net_Amount FROM bill";
             cmb=con.prepareStatement(sql);
             rs=cmb.executeQuery();
             
             //loading data to table from database
             rtn_dmg_tbl.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
             
        } catch (Exception e) {
        }
    }
     
     
     //loading manufactures to combo
     public void load_brand(){
         
         //loading data from database to combo box
         try {
             stmt=con.prepareCall("SELECT Manufacture FROM products");
             rs1 = stmt.executeQuery();
             
             while(rs1.next()){                     
             rtn_dmg_exg_brnd_combo.addItem(rs1.getString("Manufacture")); 
        }
             
         } catch (SQLException ex) {
            
         }
     
     }
    
     //Loading category to combo
     public void load_category(){
         
         //loading data from database to combo box
         try {
             
             cate =rtn_dmg_exg_brnd_combo.getSelectedItem().toString();
             
             stmt1=con.prepareCall("SELECT ProductType  FROM products WHERE Manufacture='"+cate+"'");
             rs2 = stmt1.executeQuery();
             
             
             
             while(rs2.next()){                     
             rtn_dmg_exg_categ_combo.addItem(rs2.getString("ProductType")); 
        }
             
         } catch (SQLException ex) {
            
         }
     
     }
    
     //get system date


//get calculate total amount
   public void get_tot_amount(){
int x,y,z;
String tot;
    x=Integer.parseInt(rtn_dmg_unit_price_txt.getText());
    y=Integer.parseInt(rtn_dmg_qty_txt.getText());
    z=x*y;
    rtn_dmg_total_txt.setText(String.valueOf(z));



}
     
     
    
     //load item name when clicked category
     public void load_item_name(){
         
         //loading data from database to combo box
         try {
             stmt2=con.prepareCall("SELECT ProductName  FROM products");
             rs3 = stmt2.executeQuery();
             
             while(rs3.next()){                     
             rtn_dmg_exg_item_name_combo.addItem(rs3.getString("ProductName")); 
        }
             
         } catch (SQLException ex) {
            
         }
     
     }
     
     
     //loading data from table to text
     public void select_tbl_raw_data(){
       //getting selected raw data
        int r=rtn_dmg_tbl.getSelectedRow();
        
        //assign data to variables
        String bill_id=rtn_dmg_tbl.getValueAt(r,0).toString();
        String item_code=rtn_dmg_tbl.getValueAt(r,1).toString();
        
       
       
        
        //assigning data to textfields and combo box
        rtn_dmg_bill_id_txt.setText(bill_id);
        rtn_dmg_item_id_txt.setText(item_code);
       
     }
     
     
    //getting unit price
     public void select_tbl_raw_unit_prc(){
         
          try {
             String sql="SELECT CostPerUnit,ProductName,ProductType FROM products WHERE ProductID ='"+rtn_dmg_item_id_txt.getText()+"'";
             stmt5=con.prepareStatement(sql);
             rs5=stmt5.executeQuery();
             String val;
             String i_name;
            
             
             rs5.next();
                val=rs5.getString("CostPerUnit");
                i_name=rs5.getString("ProductName");
                prod_type=rs5.getString("ProductType");
                rtn_dmg_unit_price_txt.setText(val);
                rtn_dmg_item_name_txt.setText(i_name);
              System.out.println(prod_type);
             
        } catch (Exception e) {
        }
         
     }
     
     
     //method to load all data into the table
     public void select_bill()
    {
        
        try {
             String sql="SELECT Bill_ID,item_code,Item_Name,QTY,Net_Amount FROM bill WHERE bill_ID='"+rtn_dmg_srch_id_txt.getText()+"'";
             stmt4=con.prepareStatement(sql);
             rs4=stmt4.executeQuery();
             
             //loading data to table from database
             rtn_dmg_tbl.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs4));
             
        } catch (Exception e) {
        }
    }
    
    
    //add data into return table
     //getting unit price
     public void add_data_rtn(){
         
         
         //getting return or damage radio button values
         if(rtn_dmg_rtn_r_btn.isSelected()){
             rn_or_dmg="return";
         }else{
             rn_or_dmg="damage";
         }
         
         
        
                //getting money return check box values 
                 if(rtn_dmg_chk_bx.isSelected()){
                     mny_trn_chk="Yes";
                 }else{
                     mny_trn_chk="No";
                 }
         
                 
                 item_id=rtn_dmg_item_id_txt.getText();
                 return_id= Integer.parseInt(rtn_id_gnrate_txt.getText());
                 bill_id=rtn_dmg_bill_id_txt.getText();
                 pro_name=rtn_dmg_item_name_txt.getText();
                 uni_prc=Float.parseFloat(rtn_dmg_unit_price_txt.getText());
                 qty=Integer.parseInt(rtn_dmg_qty_txt.getText());
                 tot=Float.parseFloat(rtn_dmg_total_txt.getText());
                 status=rn_or_dmg;
                 reason=rtn_dmg_trn_reason_txt.getText();
                 mny_rtn=mny_trn_chk;
                 amnt_rtn=money_rtn_value_txt.getText();
                 pro_type=prod_type;
                 grn_id=grn_no_id_txt.getText();
                 sup_id=sup_id_combo.getSelectedItem().toString();
                 
         
         
         //adding data to correct tables in DB seperate to customer and supplier
                 
           
         int x=JOptionPane.showConfirmDialog(null,"Do you want to save");
         
            if(x==0){
                System.out.println("inside suctomer");
                    if(rtn_dmg_invo_r_btn.isSelected()){
                     inv_or_grn="Customer";
                     System.out.println("inside suctomer101");
                     
                     //add data about customer return 
                           try {
                          String sql=("INSERT INTO returns (return_ID,Item_ID,Product_Name,Product_Type,Qty,Total,Reason,returned_Date,status,unit_Price,GRN_ID,money_Returns,Amount_Returned)VALUES('"+return_id+"','"+item_id+"','"+pro_name+"','"+pro_type+"','"+qty+"','"+tot+"','"+reason+"','"+date+"','"+status+"','"+uni_prc+"','"+grn_id+"','"+mny_rtn+"','"+amnt_rtn+"'");
                           stmt7=con.prepareStatement(sql);
                            stmt7.executeUpdate();
                            
                          //  String val;
                          //insert into customer_return
                           String sql1=("INSERT INTO customer_returns (return_ID,bill_ID )VALUES('"+return_id+"','"+bill_id+"'");
                           stmta=con1.prepareStatement(sql1);
                           rsa=stmta.executeQuery();
                           
                           
                           
             
                           } catch (Exception e) {
                        }
         
                     
                 }else if(rtn_dmg_grn_r_btn.isSelected()){
                     
                      System.out.println("inside supplier");
                     inv_or_grn="Supplier";
                  try {
                           String sql=("INSERT INTO returns (return_ID,Item_ID,Product_Name,Product_Type,Qty,Total,Reason,returned_Date,status,unit_Price,GRN_ID,money_Returns,Amount_Returned)VALUES('"+return_id+"','"+item_id+"','"+pro_name+"','"+pro_type+"','"+qty+"','"+tot+"','"+reason+"','"+date+"','"+status+"','"+uni_prc+"','"+grn_id+"','"+mny_rtn+"','"+amnt_rtn+"'");
                           stmt7=con.prepareStatement(sql);
                            stmt7.executeUpdate();
                          //  String val;
             
                          
                          
                           //insert into customer_return
                           String sql1=("INSERT INTO return_to_suppliers (return_ID,Supplier_ID,damage_ID )VALUES('"+return_id+"','"+sup_id+"','"+grn_id+"'");
                           stmta=con1.prepareStatement(sql1);
                           rsa=stmta.executeQuery();
                          
                        } catch (Exception e) {
                      }
         
                 }
                     
                 }
         
         
       
         
       
     }
     
     public void add_exchange(){
         

        try {

            String qry = ("INSERT INTO exchangeallowed (bill_ID,ex_ProductID,ProductName,ProductType,Manufacture,CostPerUnit,QTY,Total,extra_payments,status,return_ID)VALUES('"+bi_id+"','"+pr_id+"','"+pr_name+"','"+pr_type+"','"+manu+"','"+cost+"','"+qty_ex+"','"+tot_ex+"','"+ex_pay+"','"+stat+"','"+ret_id+"')");
             stm = con.prepareStatement(qry);
                    //Statement pst=conn.createStatement();
            // tableload();
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Added");

           
            
             
                    //Payment_2 p=new Payment_2();
           
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
            e.printStackTrace();
        }
     
     
     }
     
     
     
     
     
     
    
     
     //get catergory to add into return table       
     public void load_category_rtn(){
                  
         try {
             
             String cate1 =rtn_dmg_item_id_txt.getText();
             
             stmt11=con.prepareCall("SELECT ProductType  FROM products WHERE ProductID ='"+cate1+"'");
             rs11 = stmt11.executeQuery();
             rs11.next();
             rtn_cat=rs11.toString();
             
             System.out.println(rtn_cat);
             
        }
             
          catch (SQLException ex) {
            
         }
     
     }
     
     
     //method to load all data into the table
     public void load_sup()
    {
        try {
             String sql="SELECT supplier_Id FROM supplier";
             stmt12=con.prepareStatement(sql);
             rs12=stmt12.executeQuery();
             
             
             while(rs12.next()){                     
             sup_id_combo.addItem(rs12.getString("supplier_Id")); 
        }
             
         } catch (SQLException ex) {
            
         }
             
         
    }
     
     
     public void load_item_id(){
                  
         try {
             
             String cate1 =rtn_dmg_exg_item_name_combo.getSelectedItem().toString();
             
             stmt11=con.prepareCall("SELECT ProductID   FROM products WHERE ProductName ='"+cate1+"'");
             rs11 = stmt11.executeQuery();
             rs11.next();
             rtn_dmg_exg_item_id_txt.setText(rs11.getString("ProductID"));
             
        }
             
          catch (SQLException ex) {
            
         }
     
     }
     
     
     //getting unit price
     public void select_exchange_unit_prc(){
         
          try {
             String sql="SELECT CostPerUnit FROM products WHERE ProductID ='"+rtn_dmg_exg_item_id_txt.getText()+"'";
             stmt5=con.prepareStatement(sql);
             rs5=stmt5.executeQuery();
             String val;
             
            
             
             rs5.next();
                val=rs5.getString("CostPerUnit");
                
        rtn_dmg_exg_unit_price_txt.setText(val);
        
             
        } catch (Exception e) {
        }
         
         
        
        
       
     }
     
     //get calculate total amount exchange
   public void get_tot_amount_exc(){
int x,y,z;
String tot;
    x=Integer.parseInt(rtn_dmg_exg_unit_price_txt.getText());
    y=Integer.parseInt(rtn_dmg_exg_qty_txt.getText());
    z=x*y;
    rtn_dmg_exg_total_txt.setText(String.valueOf(z));



}
     
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        invoice_grn_group = new javax.swing.ButtonGroup();
        return_or_damage_group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rtn_dmg_tbl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        rtn_dmg_rtn_r_btn = new javax.swing.JRadioButton();
        rtn_dmg_dmg_r_btn = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rtn_dmg_item_id_txt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rtn_dmg_item_name_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rtn_dmg_unit_price_txt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rtn_dmg_qty_txt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        rtn_dmg_total_txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rtn_dmg_trn_reason_txt = new javax.swing.JTextArea();
        rtn_dmg_chk_bx = new javax.swing.JCheckBox();
        rtn_dmg_rtn_btn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        rtn_dmg_bill_id_txt = new javax.swing.JTextField();
        money_rtn_value_txt = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        grn_no_id_txt = new javax.swing.JTextField();
        sup_id_combo = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rtn_dmg_exg_item_id_txt = new javax.swing.JTextField();
        rtn_dmg_exg_categ_combo = new javax.swing.JComboBox<>();
        rtn_dmg_exg_unit_price_txt = new javax.swing.JTextField();
        rtn_dmg_exg_qty_txt = new javax.swing.JTextField();
        rtn_dmg_exg_total_txt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        rtn_dmg_exg_extra_txt = new javax.swing.JTextField();
        rtn_dmg_exg_ext_pay_chk = new javax.swing.JCheckBox();
        rtn_dmg_exg_item_name_combo = new javax.swing.JComboBox<>();
        rtn_dmg_exg_brnd_combo = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        rtn_dmg_exg_btn = new javax.swing.JButton();
        rtn_dmg_invo_r_btn = new javax.swing.JRadioButton();
        rtn_dmg_grn_r_btn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        rtn_dmg_srch_id_txt = new javax.swing.JTextField();
        rtn_dmg_srch_btn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        rtn_id_gnrate_txt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Return and Damage Item");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 13, 340, -1));

        rtn_dmg_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bill ID", "Item Code", "Item Name", "Qty", "Total"
            }
        ));
        rtn_dmg_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_tblMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rtn_dmg_tblMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(rtn_dmg_tbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1270, 130));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        return_or_damage_group.add(rtn_dmg_rtn_r_btn);
        rtn_dmg_rtn_r_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_rtn_r_btn.setText("Return Item");

        return_or_damage_group.add(rtn_dmg_dmg_r_btn);
        rtn_dmg_dmg_r_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_dmg_r_btn.setText("Damaege Item");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Item ID :");

        rtn_dmg_item_id_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rtn_dmg_item_id_txtKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Item Name:");

        rtn_dmg_item_name_txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_item_name_txtMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Unit Price:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Quntity: ");

        rtn_dmg_qty_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rtn_dmg_qty_txtKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Total Amount: ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Reason For Return or Damage:");

        rtn_dmg_trn_reason_txt.setColumns(20);
        rtn_dmg_trn_reason_txt.setRows(5);
        jScrollPane2.setViewportView(rtn_dmg_trn_reason_txt);

        rtn_dmg_chk_bx.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_chk_bx.setText("Money Return");
        rtn_dmg_chk_bx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_chk_bxMouseClicked(evt);
            }
        });

        rtn_dmg_rtn_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_rtn_btn.setText("Return");
        rtn_dmg_rtn_btn.setPreferredSize(new java.awt.Dimension(130, 36));
        rtn_dmg_rtn_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_rtn_btnActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Bill ID:");

        money_rtn_value_txt.setEnabled(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("GRN number");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 13, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Supplier ID");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        grn_no_id_txt.setEnabled(false);
        jPanel6.add(grn_no_id_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 36, 92, -1));

        sup_id_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        sup_id_combo.setEnabled(false);
        jPanel6.add(sup_id_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 36, 146, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(rtn_dmg_rtn_r_btn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rtn_dmg_total_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel20))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rtn_dmg_bill_id_txt)
                                    .addComponent(rtn_dmg_item_id_txt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rtn_dmg_item_name_txt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rtn_dmg_unit_price_txt)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rtn_dmg_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rtn_dmg_dmg_r_btn)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel9))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(rtn_dmg_chk_bx)
                            .addGap(18, 18, 18)
                            .addComponent(money_rtn_value_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rtn_dmg_rtn_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32))))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rtn_dmg_rtn_r_btn)
                    .addComponent(rtn_dmg_dmg_r_btn))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rtn_dmg_chk_bx)
                            .addComponent(money_rtn_value_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(rtn_dmg_rtn_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(rtn_dmg_bill_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rtn_dmg_item_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rtn_dmg_item_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rtn_dmg_unit_price_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rtn_dmg_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rtn_dmg_total_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 590, 390));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Excange Item Details");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Item ID:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Brand :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Category:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Item Name:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Unit Price:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Quantity:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Total Amount:");

        rtn_dmg_exg_categ_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_exg_categ_comboMouseClicked(evt);
            }
        });

        rtn_dmg_exg_qty_txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_exg_qty_txtMouseClicked(evt);
            }
        });
        rtn_dmg_exg_qty_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_exg_qty_txtActionPerformed(evt);
            }
        });
        rtn_dmg_exg_qty_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rtn_dmg_exg_qty_txtKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Paid Extra:");

        rtn_dmg_exg_extra_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_exg_extra_txtActionPerformed(evt);
            }
        });

        rtn_dmg_exg_ext_pay_chk.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_exg_ext_pay_chk.setText("Paid Extra");
        rtn_dmg_exg_ext_pay_chk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_exg_ext_pay_chkMouseClicked(evt);
            }
        });

        rtn_dmg_exg_item_name_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rtn_dmg_exg_item_name_comboMouseReleased(evt);
            }
        });
        rtn_dmg_exg_item_name_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_exg_item_name_comboActionPerformed(evt);
            }
        });

        rtn_dmg_exg_brnd_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rtn_dmg_exg_brnd_comboMouseReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("(Select Brand Name and Catergory to Load Item Data)");

        rtn_dmg_exg_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_exg_btn.setText("Exchange");
        rtn_dmg_exg_btn.setPreferredSize(new java.awt.Dimension(130, 36));
        rtn_dmg_exg_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_exg_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rtn_dmg_exg_brnd_combo, javax.swing.GroupLayout.Alignment.LEADING, 0, 98, Short.MAX_VALUE)
                                    .addComponent(rtn_dmg_exg_categ_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rtn_dmg_exg_item_id_txt)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(23, 23, 23)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rtn_dmg_exg_item_name_combo, 0, 98, Short.MAX_VALUE)
                                    .addComponent(rtn_dmg_exg_unit_price_txt))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rtn_dmg_exg_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rtn_dmg_exg_total_txt)
                                    .addComponent(rtn_dmg_exg_extra_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                                .addGap(46, 46, 46)
                                .addComponent(rtn_dmg_exg_ext_pay_chk))
                            .addComponent(rtn_dmg_exg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(rtn_dmg_exg_item_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rtn_dmg_exg_qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(rtn_dmg_exg_ext_pay_chk))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel17)
                        .addComponent(rtn_dmg_exg_total_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rtn_dmg_exg_brnd_combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(rtn_dmg_exg_categ_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(rtn_dmg_exg_item_name_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(rtn_dmg_exg_unit_price_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(rtn_dmg_exg_extra_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rtn_dmg_exg_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 659, 390));

        invoice_grn_group.add(rtn_dmg_invo_r_btn);
        rtn_dmg_invo_r_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_invo_r_btn.setText("Customer Retun");
        rtn_dmg_invo_r_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_invo_r_btnMouseClicked(evt);
            }
        });
        jPanel1.add(rtn_dmg_invo_r_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        invoice_grn_group.add(rtn_dmg_grn_r_btn);
        rtn_dmg_grn_r_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_grn_r_btn.setText("Return to Supplier");
        rtn_dmg_grn_r_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtn_dmg_grn_r_btnMouseClicked(evt);
            }
        });
        rtn_dmg_grn_r_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_grn_r_btnActionPerformed(evt);
            }
        });
        jPanel1.add(rtn_dmg_grn_r_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Return ID :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
        jPanel1.add(rtn_dmg_srch_id_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 137, -1));

        rtn_dmg_srch_btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rtn_dmg_srch_btn.setText("Search");
        rtn_dmg_srch_btn.setPreferredSize(new java.awt.Dimension(130, 36));
        rtn_dmg_srch_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtn_dmg_srch_btnActionPerformed(evt);
            }
        });
        jPanel1.add(rtn_dmg_srch_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1123, 60, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setText("Return All");
        jButton9.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Damage All");
        jButton6.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("Exchange History");
        jButton8.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(747, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 1280, 70));
        jPanel1.add(rtn_id_gnrate_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 140, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 15, 130, 30));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgrnd1.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 740));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1290, 740));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgrnd1.png"))); // NOI18N
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rtn_dmg_exg_extra_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_extra_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtn_dmg_exg_extra_txtActionPerformed

    private void rtn_dmg_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_tblMouseClicked
             //call table rw select data
             select_tbl_raw_data();
      
    }//GEN-LAST:event_rtn_dmg_tblMouseClicked

    private void rtn_dmg_rtn_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_rtn_btnActionPerformed
        add_data_rtn();
               
        
    }//GEN-LAST:event_rtn_dmg_rtn_btnActionPerformed

    private void rtn_dmg_exg_brnd_comboMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_brnd_comboMouseReleased
       //load category
            load_category();
      
    }//GEN-LAST:event_rtn_dmg_exg_brnd_comboMouseReleased

    private void rtn_dmg_exg_categ_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_categ_comboMouseClicked
        //load item name to combo
        load_item_name();
    }//GEN-LAST:event_rtn_dmg_exg_categ_comboMouseClicked

    private void rtn_dmg_srch_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_srch_btnActionPerformed
        // selecting individual bill
        select_bill();
    }//GEN-LAST:event_rtn_dmg_srch_btnActionPerformed

    private void rtn_dmg_tblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_tblMouseReleased
   //  try {
         //getting unit price
       //select_tbl_raw_unit_prc();
     //} catch (SQLException ex) {
     //    Logger.getLogger(retun_damage_items.class.getName()).log(Level.SEVERE, null, ex);
    // }
     //  load_category_rtn();
    }//GEN-LAST:event_rtn_dmg_tblMouseReleased

    private void rtn_dmg_qty_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rtn_dmg_qty_txtKeyReleased
        //get total amount
        get_tot_amount();
    }//GEN-LAST:event_rtn_dmg_qty_txtKeyReleased

    private void rtn_dmg_chk_bxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_chk_bxMouseClicked
        money_rtn_value_txt.setEnabled(true);
    }//GEN-LAST:event_rtn_dmg_chk_bxMouseClicked

    private void rtn_dmg_exg_ext_pay_chkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_ext_pay_chkMouseClicked
        rtn_dmg_exg_extra_txt.setEnabled(true);
    }//GEN-LAST:event_rtn_dmg_exg_ext_pay_chkMouseClicked

    private void rtn_dmg_grn_r_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_grn_r_btnMouseClicked
        sup_id_combo.setEnabled(true);
        grn_no_id_txt.setEnabled(true);
        load_sup();        
        
    }//GEN-LAST:event_rtn_dmg_grn_r_btnMouseClicked

    private void rtn_dmg_grn_r_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_grn_r_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtn_dmg_grn_r_btnActionPerformed

    private void rtn_dmg_invo_r_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_invo_r_btnMouseClicked
        sup_id_combo.setEnabled(false);
        grn_no_id_txt.setEnabled(false);
    }//GEN-LAST:event_rtn_dmg_invo_r_btnMouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        exchange_history a= new exchange_history();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       damage_history a= new damage_history();
        a.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void rtn_dmg_item_id_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rtn_dmg_item_id_txtKeyPressed
       
    }//GEN-LAST:event_rtn_dmg_item_id_txtKeyPressed

    private void rtn_dmg_item_name_txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_item_name_txtMouseClicked
        select_tbl_raw_unit_prc();
    }//GEN-LAST:event_rtn_dmg_item_name_txtMouseClicked

    private void rtn_dmg_exg_item_name_comboMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_item_name_comboMouseReleased
        
    }//GEN-LAST:event_rtn_dmg_exg_item_name_comboMouseReleased

    private void rtn_dmg_exg_item_name_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_item_name_comboActionPerformed
       load_item_id();
    }//GEN-LAST:event_rtn_dmg_exg_item_name_comboActionPerformed

    private void rtn_dmg_exg_qty_txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_qty_txtMouseClicked
        select_exchange_unit_prc();
    }//GEN-LAST:event_rtn_dmg_exg_qty_txtMouseClicked

    private void rtn_dmg_exg_qty_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_qty_txtActionPerformed
        
    }//GEN-LAST:event_rtn_dmg_exg_qty_txtActionPerformed

    private void rtn_dmg_exg_qty_txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_qty_txtKeyReleased
        get_tot_amount_exc();
    }//GEN-LAST:event_rtn_dmg_exg_qty_txtKeyReleased

    private void rtn_dmg_exg_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtn_dmg_exg_btnActionPerformed
        ret_id=rtn_id_gnrate_txt.getText();
        bi_id=Integer.parseInt(rtn_dmg_bill_id_txt.getText());
        pr_id=rtn_dmg_exg_item_id_txt.getText();
        pr_name=rtn_dmg_exg_item_name_combo.getSelectedItem().toString();
        pr_type=rtn_dmg_exg_categ_combo.getSelectedItem().toString();
        manu=rtn_dmg_exg_brnd_combo.getSelectedItem().toString();
        cost=Float.parseFloat(rtn_dmg_exg_unit_price_txt.getText());
        qty_ex=Integer.parseInt(rtn_dmg_exg_qty_txt.getText());
        tot_ex=Float.parseFloat(rtn_dmg_exg_total_txt.getText());
        if(rtn_dmg_exg_ext_pay_chk.isSelected()){
            ex_pay=Float.parseFloat(rtn_dmg_exg_extra_txt.getText());
            stat=1;
        }else{
            ex_pay=0;
            stat=0;
        }
               
        add_exchange();
    }//GEN-LAST:event_rtn_dmg_exg_btnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new return_damage_history().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new  Payment().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(retun_damage_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(retun_damage_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(retun_damage_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(retun_damage_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new retun_damage_items().setVisible(true);
            }
        });
    }
    
     private String getCurrentDate(){
       String date1;
        
        java.util.Date date = new java.util.Date();
        
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date1 = formatter.format(date);
        
        System.out.println(date1);
        
        return date1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField grn_no_id_txt;
    private javax.swing.ButtonGroup invoice_grn_group;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField money_rtn_value_txt;
    private javax.swing.ButtonGroup return_or_damage_group;
    private javax.swing.JTextField rtn_dmg_bill_id_txt;
    private javax.swing.JCheckBox rtn_dmg_chk_bx;
    private javax.swing.JRadioButton rtn_dmg_dmg_r_btn;
    private javax.swing.JComboBox<String> rtn_dmg_exg_brnd_combo;
    private javax.swing.JButton rtn_dmg_exg_btn;
    private javax.swing.JComboBox<String> rtn_dmg_exg_categ_combo;
    private javax.swing.JCheckBox rtn_dmg_exg_ext_pay_chk;
    private javax.swing.JTextField rtn_dmg_exg_extra_txt;
    private javax.swing.JTextField rtn_dmg_exg_item_id_txt;
    private javax.swing.JComboBox<String> rtn_dmg_exg_item_name_combo;
    private javax.swing.JTextField rtn_dmg_exg_qty_txt;
    private javax.swing.JTextField rtn_dmg_exg_total_txt;
    private javax.swing.JTextField rtn_dmg_exg_unit_price_txt;
    private javax.swing.JRadioButton rtn_dmg_grn_r_btn;
    private javax.swing.JRadioButton rtn_dmg_invo_r_btn;
    private javax.swing.JTextField rtn_dmg_item_id_txt;
    private javax.swing.JTextField rtn_dmg_item_name_txt;
    private javax.swing.JTextField rtn_dmg_qty_txt;
    private javax.swing.JButton rtn_dmg_rtn_btn;
    private javax.swing.JRadioButton rtn_dmg_rtn_r_btn;
    private javax.swing.JButton rtn_dmg_srch_btn;
    private javax.swing.JTextField rtn_dmg_srch_id_txt;
    private javax.swing.JTable rtn_dmg_tbl;
    private javax.swing.JTextField rtn_dmg_total_txt;
    private javax.swing.JTextArea rtn_dmg_trn_reason_txt;
    private javax.swing.JTextField rtn_dmg_unit_price_txt;
    private javax.swing.JTextField rtn_id_gnrate_txt;
    private javax.swing.JComboBox<String> sup_id_combo;
    // End of variables declaration//GEN-END:variables
}
