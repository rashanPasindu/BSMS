/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier_management_system;

import DBConnect.DBconnect;
import bsmanagementsystem.MainPage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DUL
 */
public class order_items extends javax.swing.JFrame {

   Connection con=null;
    ResultSet rs=null;
    PreparedStatement pts=null;
    
    
    
    public order_items() {
        initComponents();
        
        con = DBconnect.connect();
        loadSupppliers();
        combox_supplier.setSelectedIndex(0);
        tableload3();
        tableload4();
        setEmail();
        
        
    }
    
     public void clearfields1()
    {
    search_oID.setText("");
    orderitems.setText("");
    discription.setText("");
    my_mail_password.setText("");
    
    }
     
     
       public void clearfields2()
    {
    orderID_lable.setText("");
    discription_label.setText("");
    supplier_lable.setText("");
    items_label.setText("");
    remarks.setText("");
    billnumber.setText("");
    payment.setText("");
    
    }
    
    
    
    
    public String sendMail(String Email,String Password,String ToEmail,String Subject,String Text){

	String Msg;
    
        final String username = Email;
        final String password = Password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println(password+" "+username);
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Email));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ToEmail));//u will send to //changed
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);
            Msg="true";
    	    return Msg;

        } catch (MessagingException e) {
            return e.toString();
        }
    
    
    
    }
    
    
    
    
    
    
    
    
    public void tableload3()
    {
        try {
            
       String sqlp_item="SELECT POrderID, Suplier, Item, Description FROM pending_orde where status like 'pending' ";
       PreparedStatement pst = con.prepareStatement(sqlp_item);
       rs=pst.executeQuery();
       table_pending_order.setModel(DbUtils.resultSetToTableModel(rs));
       
       
        } 
        catch (Exception e) {
        }
    
    
        
    }
    
    public void tableload4()
    {
        try {
            
         String sqlr_item="SELECT POrderID, suplier, rdate, Discription, rmarks, billnum, payment, payType, Item FROM rorders";
         pts = con.prepareStatement(sqlr_item);
         rs=pts.executeQuery();    
         table_received_order.setModel(DbUtils.resultSetToTableModel(rs));   
        } 
        catch (Exception e) {
        }
    
    }
    
    
    
    
    
    
    private void loadSupppliers(){
        combox_supplier.removeAllItems();
       try {
           pts=con.prepareStatement("SELECT * FROM `supplier`");
           rs=pts.executeQuery();
           
           while (rs.next()) {               
               combox_supplier.addItem(rs.getString(2));
           }
          setEmail();
       } catch (SQLException ex) {
           Logger.getLogger(order_items.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void setEmail(){
        
        
        try {
            String name=combox_supplier.getSelectedItem()+"";
           
           pts=con.prepareStatement("SELECT * FROM `supplier`where supplier_Name='"+name+"'");
           rs=pts.executeQuery();
           if(rs.next()){
             
               label_email1.setText(rs.getString("supplier_email"));
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(order_items.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public String getDate(){
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        Date date =new Date();
        return sd.format(date);
    }
    
    public  void searchOrder(){
    String search=search_oID.getText();

        try
        {

            String sql_serch="SELECT POrderID, Suplier, Item, Description FROM pending_orde where POrderID like '%"+search+"%'";
            System.out.println(sql_serch);
            pts=con.prepareStatement(sql_serch);

            rs=pts.executeQuery();
            table_pending_order.setModel(DbUtils.resultSetToTableModel(rs));
            System.out.println("ddddd");

        }

        catch (Exception e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        discription_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        discription = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_pending_order = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_received_order = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        search_oID = new javax.swing.JTextField();
        search_oid_button = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        orderitems = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        items_label = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        label_email1 = new javax.swing.JLabel();
        payment = new javax.swing.JTextField();
        billnumber = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        remarks = new javax.swing.JTextPane();
        conform_order_button = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        add_suppliers_move = new javax.swing.JButton();
        re_orderlevel_button = new javax.swing.JButton();
        order_button = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        combox_paymenttype = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        supplier_lable = new javax.swing.JLabel();
        combox_supplier = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        orderID_lable = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        my_mail_password = new javax.swing.JPasswordField();
        myemaillable = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        discount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ORDERS");
        setPreferredSize(new java.awt.Dimension(1366, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PENDING ORDERS");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("EMAIL:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 255, 255));
        jLabel6.setText("SUPPLIER:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, -1, -1));

        discription_label.setText("aa");
        getContentPane().add(discription_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 440, 20));

        jScrollPane1.setViewportView(discription);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 230, 60));

        table_pending_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_pending_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_pending_orderMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_pending_order);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 860, 240));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("-----------------------------------");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, 140, -1));

        jButton1.setText("REPORTS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 230, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("SUPPLIER:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        table_received_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(table_received_order);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 950, 220));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 255, 255));
        jLabel7.setText("ORDER ID:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("BILL NUMBER:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, -1, -1));

        search_oID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_oIDActionPerformed(evt);
            }
        });
        search_oID.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                search_oIDPropertyChange(evt);
            }
        });
        search_oID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_oIDKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                search_oIDKeyTyped(evt);
            }
        });
        getContentPane().add(search_oID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 50, 30));

        search_oid_button.setText("SEARCH");
        search_oid_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_oid_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(search_oid_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 255, 255));
        jLabel13.setText("ITEMS:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 400, -1, -1));
        getContentPane().add(orderitems, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 230, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 255, 255));
        jLabel14.setText("DISCRIPTION:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, -1, -1));

        jLabel15.setFont(new java.awt.Font("Sylfaen", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("CONFORM PENDING ORDERS:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("ITEMS:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        items_label.setText("aa");
        getContentPane().add(items_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 400, 310, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setText("PAYMENT:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel19.setText("DISCOUNT:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, -1));

        label_email1.setText("df");
        getContentPane().add(label_email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 230, 20));

        payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentActionPerformed(evt);
            }
        });
        payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentKeyReleased(evt);
            }
        });
        getContentPane().add(payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 160, 30));

        billnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billnumberActionPerformed(evt);
            }
        });
        billnumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                billnumberKeyReleased(evt);
            }
        });
        getContentPane().add(billnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 160, 30));

        jScrollPane4.setViewportView(remarks);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 160, 30));

        conform_order_button.setText("CONFORM PENDING ORDER");
        conform_order_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conform_order_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(conform_order_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 290, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ORDER ITEMS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, -1, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("-----------------------------------");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 140, -1));

        add_suppliers_move.setText("ADD SUPPLIERS");
        add_suppliers_move.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_suppliers_moveActionPerformed(evt);
            }
        });
        getContentPane().add(add_suppliers_move, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 230, -1));

        re_orderlevel_button.setText("RE-ORDER LEVEL");
        re_orderlevel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                re_orderlevel_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(re_orderlevel_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 230, -1));

        order_button.setText("ORDER THIS ITEM");
        order_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(order_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 230, -1));

        jLabel23.setFont(new java.awt.Font("Sitka Text", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("RECEIVED ORDERS");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, -1, -1));

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("-----------------------------------");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 140, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("-----------------------------------");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 440, 140, -1));

        jButton2.setText("BACK TO MENU");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, 230, -1));

        jButton3.setText("BACK TO HOME");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 230, -1));

        combox_paymenttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cash", "Check" }));
        getContentPane().add(combox_paymenttype, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, 160, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("RE-MARKS:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        supplier_lable.setText("aa");
        getContentPane().add(supplier_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 180, 20));

        combox_supplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combox_supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combox_supplierMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                combox_supplierMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                combox_supplierMouseReleased(evt);
            }
        });
        combox_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combox_supplierActionPerformed(evt);
            }
        });
        getContentPane().add(combox_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 230, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel26.setText("ORDER ID:");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        orderID_lable.setText("jLabel2");
        getContentPane().add(orderID_lable, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, 60, 20));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setText("PASSWORD:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setText("DISCRIPTION:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));
        getContentPane().add(my_mail_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 230, -1));

        myemaillable.setForeground(new java.awt.Color(255, 255, 255));
        myemaillable.setText("dulmi2ndacc@gmail.com");
        getContentPane().add(myemaillable, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 140, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel27.setText("PAYMENT TYPE:");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, -1, -1));

        discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountActionPerformed(evt);
            }
        });
        getContentPane().add(discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, 160, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgrnd1_1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentActionPerformed

    private void billnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billnumberActionPerformed

    private void conform_order_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conform_order_buttonActionPerformed
        
        
        
   
        
        
        String uorderid=orderID_lable.getText();
        String usullpier= supplier_lable.getText();
        String urdate=getDate();
        String udiscription=discription_label.getText();
        String uremarks=remarks.getText();
        String ubillnum= billnumber.getText();
        String upay=payment.getText();
        String upaytype=combox_paymenttype.getSelectedItem().toString();
        String uitems=items_label.getText();
        String discountlbl=discount.getText();
        
        //SELECT `POrderID`, `suplier`, `rdate`, `Discription`, `rmarks`, `billnum`, `payment`, `disReceived`, `payType`, `Item` FROM `rorders`
        try {
       String q3="INSERT INTO rorders (POrderID, suplier, rdate, Discription, rmarks, billnum, payment, disReceived, payType, Item) values ('"+uorderid+"', '"+usullpier+"', '"+urdate+"', '"+udiscription+"', '"+uremarks+"', '"+ubillnum+"', '"+upay+"', '"+discountlbl+"', '"+upaytype+"', '"+uitems+"')";
            System.out.println(q3);
       pts = con.prepareStatement(q3);
       pts.execute();
       
       
       String sql_delete="UPDATE `pending_orde` set `status`='recieved' WHERE  POrderID='"+uorderid+"'";
       pts=con.prepareStatement(sql_delete);
       pts.execute();        
       
       tableload3();
       tableload4();
        } catch (Exception e) {
            System.out.println("Sql erroe "+e);
        }
      
       
   /*     try {
       String q3="INSERT INTO rorders (POrderID, suplier, rdate, Discription, rmarks, billnum, payment, payType, Item) values ('"+uorderid+"', '"+usullpier+"', '"+urdate+"', '"+udiscription+"', '"+uremarks+"', '"+ubillnum+"', '"+upay+"', '"+upaytype+"', '"+uitems+"')";
            System.out.println(q3);
       pts = con.prepareStatement(q3);
       pts.execute();
       tableload3();
       tableload4();
        } catch (Exception e) {
        }*/
        
        /*int delete11=JOptionPane.showConfirmDialog(null, "CONFORM THIS ORDER !");
        
        if(delete11==0)
            {
                String update_supID=orderID_lable.getText();
                String sql_delete="DELETE FROM pending_orde WHERE POrderID='"+update_supID+"'";
                
                try {
                    pts=con.prepareStatement(sql_delete);
                    pts.execute();
                    tableload3();
                    tableload4();
        
                    
                } catch (Exception e) {
                }
                
            }*/
        
        
        
        
    }//GEN-LAST:event_conform_order_buttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void re_orderlevel_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_re_orderlevel_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_re_orderlevel_buttonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        order_reports c2 = new order_reports();
        c2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       MainPage mp = new MainPage();
       mp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void add_suppliers_moveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_suppliers_moveActionPerformed
        suppliersx c1 = new suppliersx();
        c1.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_add_suppliers_moveActionPerformed

    private void order_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_buttonActionPerformed
        String supplier = combox_supplier.getSelectedItem().toString();
        String oitems=orderitems.getText();
        String orderdiscription=discription.getText();
        
        try {
       String q2= "INSERT INTO pending_orde (Suplier, Item, Description) values('"+supplier+"', '"+oitems+"', '"+orderdiscription+"')";
            //System.out.println("first" +" "+q2);
        pts = con.prepareStatement(q2);
        //System.out.println("second");
       pts.execute();
       tableload3();
        //System.out.println("third");
       
       //tableload3();
            
        } catch (Exception e) {
        }
        
        
        String xemail= myemaillable.getText();
        String xpassword=String.valueOf(my_mail_password.getPassword());
        String xtoemail= label_email1.getText();
        String xsubject= orderitems.getText();
        String xdiscripiton= discription.getText();
        
        
        //public String sendMail(String Email,String Password,String ToEmail,String Subject,String Text)
        String DATA=new order_items().sendMail(xemail, xpassword, xtoemail, xsubject, xdiscripiton);
        System.out.println(DATA);
        
        if(DATA.equals("true"))
        {
            JOptionPane.showMessageDialog(this, "Email send sucsessfull!");
        }
        
        else{
        
            JOptionPane.showMessageDialog(this, "email send faild!");
        }
        clearfields1();
        
    }//GEN-LAST:event_order_buttonActionPerformed

    private void combox_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combox_supplierActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_combox_supplierActionPerformed

    private void combox_supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combox_supplierMouseClicked
        // TODO add your handling code here:
        setEmail();
    }//GEN-LAST:event_combox_supplierMouseClicked

    private void search_oid_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_oid_buttonActionPerformed

       searchOrder();

    }//GEN-LAST:event_search_oid_buttonActionPerformed

    private void search_oIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_oIDActionPerformed
        // TODO add your handling code here:
        searchOrder();
    }//GEN-LAST:event_search_oIDActionPerformed

    private void table_pending_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_pending_orderMouseClicked
        int row1=table_pending_order.getSelectedRow();
        
        String orderid1=table_pending_order.getValueAt(row1, 0).toString();
        String supplier1=table_pending_order.getValueAt(row1, 1).toString();
        String items1=table_pending_order.getValueAt(row1, 2).toString();
        String discription=table_pending_order.getValueAt(row1, 3).toString();
        
        orderID_lable.setText(orderid1);
        supplier_lable.setText(supplier1);
        items_label.setText(items1);
        discription_label.setText(discription);
        
        tableload3();
        
        
           
    }//GEN-LAST:event_table_pending_orderMouseClicked

    private void search_oIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_oIDKeyTyped
        // TODO add your handling code here:
        searchOrder();
        
    }//GEN-LAST:event_search_oIDKeyTyped

    private void search_oIDPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_search_oIDPropertyChange
        // TODO add your handling code here:
       
    }//GEN-LAST:event_search_oIDPropertyChange

    private void search_oIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_oIDKeyPressed
        // TODO add your handling code here:
        searchOrder();
    }//GEN-LAST:event_search_oIDKeyPressed

    private void billnumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billnumberKeyReleased
        String a=billnumber.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isDigit(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid Integer");
            billnumber.setText("");
        }
    }//GEN-LAST:event_billnumberKeyReleased

    private void paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentKeyReleased
           String a=payment.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isDigit(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid Integer");
            payment.setText("");
        }
    }//GEN-LAST:event_paymentKeyReleased

    private void combox_supplierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combox_supplierMousePressed
        setEmail();
    }//GEN-LAST:event_combox_supplierMousePressed

    private void combox_supplierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combox_supplierMouseReleased
        setEmail();
    }//GEN-LAST:event_combox_supplierMouseReleased

    private void discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountActionPerformed

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
            java.util.logging.Logger.getLogger(order_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(order_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(order_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(order_items.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new order_items().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_suppliers_move;
    private javax.swing.JTextField billnumber;
    private javax.swing.JComboBox combox_paymenttype;
    private javax.swing.JComboBox combox_supplier;
    private javax.swing.JButton conform_order_button;
    private javax.swing.JTextField discount;
    private javax.swing.JTextPane discription;
    private javax.swing.JLabel discription_label;
    private javax.swing.JLabel items_label;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel label_email1;
    private javax.swing.JPasswordField my_mail_password;
    private javax.swing.JLabel myemaillable;
    private javax.swing.JLabel orderID_lable;
    private javax.swing.JButton order_button;
    private javax.swing.JTextField orderitems;
    private javax.swing.JTextField payment;
    private javax.swing.JButton re_orderlevel_button;
    private javax.swing.JTextPane remarks;
    private javax.swing.JTextField search_oID;
    private javax.swing.JButton search_oid_button;
    private javax.swing.JLabel supplier_lable;
    private javax.swing.JTable table_pending_order;
    private javax.swing.JTable table_received_order;
    // End of variables declaration//GEN-END:variables
}
