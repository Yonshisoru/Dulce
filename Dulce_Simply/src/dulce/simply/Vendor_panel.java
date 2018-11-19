/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Vendor_panel extends javax.swing.JFrame {
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    int maxphonelength = 10;
    Database d = new Database();
    public String id = null;
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    String output = null;
    String position = null;
    String password= null;
    String createid = null;
    int max = 0;
    /**
     * Creates new form Employee_create
     */
    public Vendor_panel() {
        initComponents();
        show_vendor();
        id();
    }
    public void clear(){
         vname_txt.setText("");
         vphone_txt.setText("");
         vemail_txt.setText("");
         address_txt.setText("");
         account_txt.setText("");
         contact_txt.setText("");
    }
    public void lock(){
         vname_txt.setEnabled(false);
         vphone_txt.setEnabled(false);
         vemail_txt.setEnabled(false);
         address_txt.setEnabled(false);
         account_txt.setEnabled(false);
         contact_txt.setEnabled(false);
    }
    public void unlock(){
         vname_txt.setEnabled(true);
         vphone_txt.setEnabled(true);
         vemail_txt.setEnabled(true);
         address_txt.setEnabled(true);
        account_txt.setEnabled(true);
         contact_txt.setEnabled(true);
    }
  public String id(){
       int count=0;
          String sql  ="select V_ID from VENDOR";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("V_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("V_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "V00"+max;
    }else if(max<100){
        output = "V0"+max;
    }else{
        output = "V"+max;
    }
    showid_txt.setText(output);
    createid = output;
    con.close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
   public ArrayList<Vendor_variable>Vendor_list(){
               ArrayList<Vendor_variable> Vendor = new ArrayList<>();
               String sql = "SELECT V_ID,V_NAME,V_ADDRESS,V_PHONE,V_EMAIL,V_ACC_NUM,V_CONTACT FROM VENDOR WHERE V_DEL = 'N'";
        try{
        Class.forName("com.mysql.jdbc.Driver");     
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Vendor_variable v = new Vendor_variable();
            v.setid(rs.getString("V_ID"));
            v.setname(rs.getString("V_NAME"));
            v.setaddress(rs.getString("V_ADDRESS"));
            v.setphone(rs.getString("V_PHONE"));
            v.setemail(rs.getString("V_EMAIL"));
            v.setacc(rs.getString("V_ACC_NUM"));
            v.setcontact(rs.getString("V_CONTACT"));
            Vendor.add(v);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Vendor;
}
    public void show_vendor(){
        DefaultTableModel model = (DefaultTableModel)emp_table.getModel();
        ArrayList<Vendor_variable>Vendor = Vendor_list();
        Object[] row = new Object[11];
        for(int i=0;i<Vendor.size();i++){
            row[0]=Vendor.get(i).getid();
            row[1]=Vendor.get(i).getname();
            row[2]=Vendor.get(i).getaddress();
            row[3]=Vendor.get(i).getphone();
            row[4]=Vendor.get(i).getemail();
            row[5]=Vendor.get(i).getacc();
            row[6]=Vendor.get(i).getcontact();
            model.addRow(row);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        emp_table = new javax.swing.JTable();
        showid_txt = new javax.swing.JTextField();
        vemail_txt = new javax.swing.JTextField();
        vname_txt = new javax.swing.JTextField();
        vphone_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        address_txt = new javax.swing.JTextArea();
        delete = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        edit = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        account_txt = new javax.swing.JTextField();
        contact_txt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emp_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Phone", "Email", "Account_Num", "Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_table.getTableHeader().setReorderingAllowed(false);
        emp_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emp_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(emp_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 810, 680));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 110, 30));
        getContentPane().add(vemail_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 170, 30));
        getContentPane().add(vname_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 350, 30));

        vphone_txt.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                vphone_txtComponentAdded(evt);
            }
        });
        vphone_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vphone_txtActionPerformed(evt);
            }
        });
        vphone_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vphone_txtKeyTyped(evt);
            }
        });
        getContentPane().add(vphone_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 170, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Vendor Name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Email:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jButton1.setText("Close");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 670, 120, 50));

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 120, 50));

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 670, 120, 50));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Address:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        address_txt.setColumns(20);
        address_txt.setRows(5);
        jScrollPane2.setViewportView(address_txt);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 270, 110));

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 570, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Function:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, -1, -1));

        edit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                editFocusLost(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, -1, -1));

        jLabel13.setText("Delete");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 573, -1, -1));

        jLabel14.setText("Create");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 573, -1, -1));

        jLabel15.setText("Edit");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 573, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Account Number:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));
        getContentPane().add(account_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 200, 30));
        getContentPane().add(contact_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 170, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Contact Name:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emp_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emp_tableMouseClicked
        if(editnaja==true||deletenaja==true){
        showid_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString());
        vname_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),1).toString());
       address_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),2).toString());
        vphone_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),3).toString());
        vemail_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),4).toString());
       account_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),5).toString());
        contact_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),6).toString());
        }
    }//GEN-LAST:event_emp_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            System.out.print("create!!");
        String id = showid_txt.getText();
        String v_name = vname_txt.getText();
        String v_address = address_txt.getText();
        String v_phone = vphone_txt.getText();
        String v_email = vemail_txt.getText();
        String v_account = account_txt.getText();
         String v_contact = contact_txt.getText();
        if(createnaja==true){
        String eiei = "INSERT INTO VENDOR VALUE('"+id+"','"+v_name+"','"+v_address+"','"+v_phone+"','"+v_email+"','"+v_account+"','"+v_contact+"','N')";  
        System.out.print(eiei);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(eiei);
        pat.executeUpdate(eiei);
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        DefaultTableModel dm = (DefaultTableModel)emp_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_vendor();
        id();
        clear();
        }else if(editnaja==true){
            String edit = "UPDATE VENDOR SET V_NAME = '"+v_name+"',V_ADDRESS = '"+v_address+"',V_PHONE = '"+v_phone+"',V_EMAIL = '"+v_email+"',V_ACC_NUM = '"+v_account+"',V_CONTACT = '"+v_contact+"' WHERE V_ID ='"+id+"'";  
            System.out.print(edit);
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(edit);
                pat.executeUpdate(edit);
                pat.close();
                clear();
                JOptionPane.showMessageDialog(null,"Update Success");
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
                  DefaultTableModel dm = (DefaultTableModel)emp_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_vendor();  
        }else if(deletenaja==true){
            String delete = "UPDATE VENDOR SET V_DEL = 'Y' WHERE V_ID ='"+id+"'";
            System.out.print(edit);
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(delete);
                pat.executeUpdate(delete);
                pat.close();
                clear();
                JOptionPane.showMessageDialog(null,"Delete Success");
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
                  DefaultTableModel dm = (DefaultTableModel)emp_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_vendor();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
        showid_txt.setText(createid);
        emp_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        unlock(); 
        clear();
        emp_table.getSelectionModel().clearSelection();
        System.out.print("create!!");         
        create.setEnabled(false);       
        edit.setEnabled(true);        
        delete.setEnabled(true);
        showid_txt.setText(createid);
        delete.setSelected(false);
        edit.setSelected(false);
        createnaja = true;
        editnaja = false;
        deletenaja = false;
    }//GEN-LAST:event_createActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        unlock();
        clear();
        emp_table.getSelectionModel().clearSelection();
        System.out.print("edit!!");
        edit.setEnabled(false);
        create.setEnabled(true);                    
        delete.setEnabled(true);
        delete.setSelected(false);
        create.setSelected(false);
        editnaja=true;
        createnaja = false;
        deletenaja = false;
    }//GEN-LAST:event_editActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        lock(); 
        clear();
        emp_table.getSelectionModel().clearSelection();
        System.out.print("delete!!");
                    delete.setEnabled(false);
                    create.setEnabled(true);
                    edit.setEnabled(true);
        create.setSelected(false);
        edit.setSelected(false);
        deletenaja = true;
               editnaja = false;
        createnaja = false;
    }//GEN-LAST:event_deleteActionPerformed

    private void editFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_editFocusLost

    private void vphone_txtComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_vphone_txtComponentAdded

    }//GEN-LAST:event_vphone_txtComponentAdded

    private void vphone_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vphone_txtActionPerformed

    }//GEN-LAST:event_vphone_txtActionPerformed

    private void vphone_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vphone_txtKeyTyped

    }//GEN-LAST:event_vphone_txtKeyTyped

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
            java.util.logging.Logger.getLogger(Vendor_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendor_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendor_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendor_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField account_txt;
    private javax.swing.JTextArea address_txt;
    private javax.swing.JTextField contact_txt;
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JRadioButton edit;
    private javax.swing.JTable emp_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JTextField vemail_txt;
    private javax.swing.JTextField vname_txt;
    private javax.swing.JTextField vphone_txt;
    // End of variables declaration//GEN-END:variables
}
