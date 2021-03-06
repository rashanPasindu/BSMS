/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import DBConnect.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class Emp extends javax.swing.JFrame {

    Connection con =null;
    PreparedStatement pst =null;
    ResultSet rs=null;
    
    public Emp() {
        initComponents();
        
        //connect to DB
        con =DBconnect.connect();
        
        //load table
        tableload();
    }
    
    public void tableload()
    {
        try 
        {
        String sql="select Emp_ID,Emp_Name,Age,Address,Tel_number from employee";
        pst=con.prepareStatement(sql);
        rs= pst.executeQuery();
        
        employee.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e)
        {
        }
    }
    
      private void clearfields()
     {
        Name.setText("");
        Age.setText("");
        Id.setText("");
        Mobile.setText("");
        Address.setText("");
        search.setText("");
     }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        employee = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        Id = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Age = new javax.swing.JTextField();
        Mobile = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        Address = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Age", "Mobile", "Address"
            }
        ));
        employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employee);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(346, 203, 954, 287);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Name");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 250, 40, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Age");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 290, 27, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mobile");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 330, 44, 17);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Emp ID");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 210, 52, 17);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add Record");
        jButton2.setMaximumSize(new java.awt.Dimension(130, 36));
        jButton2.setMinimumSize(new java.awt.Dimension(130, 36));
        jButton2.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(706, 528, 130, 36);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Update Record");
        jButton3.setMaximumSize(new java.awt.Dimension(130, 36));
        jButton3.setMinimumSize(new java.awt.Dimension(130, 36));
        jButton3.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(854, 528, 140, 36);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Delete Record ");
        jButton4.setMaximumSize(new java.awt.Dimension(130, 36));
        jButton4.setMinimumSize(new java.awt.Dimension(130, 36));
        jButton4.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(1027, 528, 145, 36);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("Back");
        jButton6.setMaximumSize(new java.awt.Dimension(130, 36));
        jButton6.setMinimumSize(new java.awt.Dimension(130, 36));
        jButton6.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(1210, 598, 130, 36);
        getContentPane().add(Id);
        Id.setBounds(130, 210, 177, 22);

        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NameKeyReleased(evt);
            }
        });
        getContentPane().add(Name);
        Name.setBounds(130, 250, 177, 22);

        Age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AgeKeyReleased(evt);
            }
        });
        getContentPane().add(Age);
        Age.setBounds(130, 290, 177, 22);

        Mobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MobileKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MobileKeyTyped(evt);
            }
        });
        getContentPane().add(Mobile);
        Mobile.setBounds(130, 330, 177, 22);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Search By Name");
        jButton7.setMaximumSize(new java.awt.Dimension(130, 36));
        jButton7.setMinimumSize(new java.awt.Dimension(130, 36));
        jButton7.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(346, 121, 158, 36);

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        getContentPane().add(search);
        search.setBounds(522, 122, 195, 36);

        Address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddressKeyReleased(evt);
            }
        });
        getContentPane().add(Address);
        Address.setBounds(130, 370, 177, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Address");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 370, 56, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Employeee Details");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(262, 30, 165, 22);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Clear Fields");
        jButton5.setMaximumSize(new java.awt.Dimension(130, 36));
        jButton5.setMinimumSize(new java.awt.Dimension(130, 36));
        jButton5.setPreferredSize(new java.awt.Dimension(130, 36));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(543, 528, 145, 36);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgrnd1.png"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 1370, 770);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Delete records
      
        String a=Id.getText(); 
        Empclass x=new Empclass();
        x.setEmpid(a);
        
        x.delete();
        
        tableload();
        clearfields();
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        //add records
        
        String a=Id.getText();       
        String b=Name.getText();
        String c=Age.getText();
        String d=Address.getText();
        String e=Mobile.getText();
        
        if(!(Id.getText().isEmpty()) && !( Name.getText().isEmpty()) && !(Age.getText().isEmpty()) && 
                !(Address.getText().isEmpty()) && !( Mobile.getText().isEmpty()))
        {
     
            
        Empclass x=new Empclass();
        
        x.setEmpid(a);
        x.setName(b);
        x.setAge(Integer.parseInt(Age.getText()));
        x.setAddress(d);
        x.setMobile(Integer.parseInt(Mobile.getText()));
        
   
        if(e.length()==10)
        {
          x.insert();
        //load table
        tableload();
        clearfields();
        }
        
        else
        {
           JOptionPane.showMessageDialog(null, "Enter a valid 10 digit number for mobile");
            Mobile.setText("");   
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Please enter all the required fields","Error",JOptionPane.ERROR_MESSAGE); 
            clearfields();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Update records
        String a=Id.getText();       
        String b=Name.getText();
        String c=Age.getText();
        String d=Address.getText();
        String e=Mobile.getText();
        
        Empclass x=new Empclass();
        
        x.setEmpid(a);
        x.setName(b);
        x.setAge(Integer.parseInt(Age.getText()));
        x.setAddress(d);
        x.setMobile(Integer.parseInt(Mobile.getText()));
       
         if(e.length()==10)
        {
        x.update();
        tableload();
        clearfields();
        }
    else
        {
           JOptionPane.showMessageDialog(null, "Enter a valid 10 digit number for mobile");
            Age.setText("");   
        }
       
    }//GEN-LAST:event_jButton3ActionPerformed
    
    
    
   
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // back button
        HRM l=new HRM();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void employeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeMouseClicked
       
        int r=employee.getSelectedRow();
        String id=employee.getValueAt(r, 0).toString();
        String name=employee.getValueAt(r, 1).toString();
        String age=employee.getValueAt(r, 2).toString();
        String address=employee.getValueAt(r, 3).toString();
        String mobile=employee.getValueAt(r, 4).toString();
        
        Id.setText(id);
        Name.setText(name);
        Age.setText(age);
        Mobile.setText(mobile);
        Address.setText(address);
        
    }//GEN-LAST:event_employeeMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // search button
        String name=search.getText();  
       
        String sql="select Emp_ID,Emp_Name,Age,Address,Tel_number from employee where Emp_Name like '%"+name+"%'";
        
        try
        {
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        
        employee.setModel(DbUtils.resultSetToTableModel(rs));
        
        clearfields();
        }
        catch(Exception e)
        {}
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //clear fields
         clearfields();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void AgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AgeKeyReleased
        //Age
        String a=Age.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isDigit(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid Integer");
            Age.setText("");
        }
        
    }//GEN-LAST:event_AgeKeyReleased

    private void NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameKeyReleased
        //Name
         String a=Name.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isLetter(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid String");
            Name.setText("");
        }
    }//GEN-LAST:event_NameKeyReleased

    private void MobileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobileKeyReleased
        //Mobile
           String a=Mobile.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isDigit(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid Integer");
            Mobile.setText("");
        }
        
    }//GEN-LAST:event_MobileKeyReleased

    private void AddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddressKeyReleased
        // Address
           String a=Address.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isLetter(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid String");
            Address.setText("");
        }
    }//GEN-LAST:event_AddressKeyReleased

    private void MobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobileKeyTyped
      
    }//GEN-LAST:event_MobileKeyTyped

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // search validation
              String a=search.getText();
        int b=0;
        
       
        for(int i=0;i<a.length();i++)
        {
            if(!Character.isLetter(a.charAt(i)))
                b=1;    
        }
        
        if(b==1)
        {
            JOptionPane.showMessageDialog(null, "Enter a valid String");
            search.setText("");
        }
    }//GEN-LAST:event_searchKeyReleased

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
            java.util.logging.Logger.getLogger(Emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Emp().setVisible(true);
            }
        });
    }

     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Address;
    private javax.swing.JTextField Age;
    private javax.swing.JTextField Id;
    private javax.swing.JTextField Mobile;
    private javax.swing.JTextField Name;
    private javax.swing.JTable employee;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
