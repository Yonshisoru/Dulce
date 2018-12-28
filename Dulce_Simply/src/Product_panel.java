/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class Product_panel extends javax.swing.JFrame {
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    boolean pass = false;
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    public String id = null;
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    String output = null;
    String ven = null;
    String password= null;
    String createid = null;
    int max = 0;
    /**
     * Creates new form Employee_create
     */
    public Product_panel() {
        initComponents();
        show_product();
        id();
        fillcombo();
    }
    public void clear(){
         pro_name_txt.setText("");
         v_txt.setSelectedIndex(0);
         pro_price_txt.setText("");
         pro_unit_txt.setText("");
         pro_min_txt.setText("");
    }
    public void lock(){
         pro_name_txt.setEnabled(false);
         v_txt.setEnabled(false);
         pro_price_txt.setEnabled(false);
         pro_unit_txt.setEnabled(false);
         pro_min_txt.setEnabled(false);
    }
    public void unlock(){
         pro_name_txt.setEnabled(true);
         v_txt.setEnabled(true);
         pro_price_txt.setEnabled(true);
         pro_unit_txt.setEnabled(true);
         pro_min_txt.setEnabled(true);
    }
public String find(){
            String find = "SELECT V_ID,V_NAME FROM VENDOR";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
            if(v_txt.getSelectedItem().toString().equals(rs.getString("V_NAME"))){
                ven = rs.getString("V_ID");
                System.out.print(ven);
            }
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return ven;
  }
  public String id(){
       int count=0;
          String sql  ="select PRO_ID from PRODUCT";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PRO_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("PRO_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "P00"+max;
    }else if(max<100){
        output = "P0"+max;
    }else{
        output = "P"+max;
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
    void fillcombo(){
      try{
          String sql = "SELECT V_ID,V_NAME FROM VENDOR WHERE V_DEL = 'N'";
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            v_txt.addItem(rs.getString("V_NAME"));
        }
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){
          
      }
  }
   public ArrayList<Product_variable>ProductList(){
               ArrayList<Product_variable> Product_list = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select PRO_ID,V_ID,V_NAME,PRO_NAME,PRO_PRICE,PRO_UNITS,PRO_MIN FROM PRODUCT NATURAL JOIN VENDOR WHERE PRO_DEL = 'N' ORDER BY PRO_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Product_variable p = new Product_variable();
            p.setid(rs.getString("PRO_ID"));
            p.setvid(rs.getString("V_ID"));
            p.setvname(rs.getString("V_NAME"));
            p.setname(rs.getString("PRO_NAME"));
            p.setprice(rs.getInt("PRO_PRICE"));
            p.setunit(rs.getInt("PRO_UNITS"));
            p.setmin(rs.getInt("PRO_MIN"));
            Product_list.add(p);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Product_list;
}
    public void show_product(){
        ArrayList<Product_variable>ProductList = ProductList();
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<ProductList.size();i++){
            row[0]=ProductList.get(i).getid();
            row[1]=ProductList.get(i).getname();
            row[2]=ProductList.get(i).getprice();
            row[3]=ProductList.get(i).getunit();
            row[4]=ProductList.get(i).getmin();
            row[5]=ProductList.get(i).getvname();
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
        product_table = new javax.swing.JTable();
        showid_txt = new javax.swing.JTextField();
        pro_name_txt = new javax.swing.JTextField();
        pro_price_txt = new javax.swing.JTextField();
        pro_min_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        v_txt = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        delete = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        edit = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pro_unit_txt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสอุปกรณ์", "ชื่ออุปกรณ์", "ราคา", "จำนวน", "จำนวนต่ำสุดที่ต้องมีคงคลัง", "ชื่อบริษัทจัดจำหน่าย"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        product_table.getTableHeader().setReorderingAllowed(false);
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(product_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 820, 680));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 110, 30));
        getContentPane().add(pro_name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 340, 30));

        pro_price_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pro_price_txtFocusGained(evt);
            }
        });
        pro_price_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pro_price_txtActionPerformed(evt);
            }
        });
        getContentPane().add(pro_price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, 30));
        getContentPane().add(pro_min_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 90, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("รหัสอุปกรณ์/วัตถุดิบ:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("ชื่ออุปกรณ์/วัตถุดิบ:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ราคา:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("จำนวนต่ำที่สุดต้องมีคงคลัง:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
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

        v_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        getContentPane().add(v_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 130, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("ผู้จัดจำหน่าย:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("ฟังก์ชั่น");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

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
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, -1, -1));

        jLabel13.setText("ลบ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, -1, -1));

        jLabel14.setText("สร้าง");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, -1, -1));

        jLabel15.setText("แก้ไข");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, -1, -1));

        pro_unit_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pro_unit_txtFocusGained(evt);
            }
        });
        pro_unit_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pro_unit_txtActionPerformed(evt);
            }
        });
        getContentPane().add(pro_unit_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 80, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("จำนวน:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        if(editnaja==true||deletenaja==true){
        showid_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString());
        pro_name_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),1).toString());
        pro_price_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),2).toString());
        pro_unit_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),3).toString());
        pro_min_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),4).toString());
        v_txt.setSelectedItem(product_table.getModel().getValueAt(product_table.getSelectedRow(),5).toString());
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

           System.out.print("create!!");
        String id = showid_txt.getText();
        String proname = pro_name_txt.getText();
        String vendor = find();
        String price = pro_price_txt.getText();
        String unit = pro_unit_txt.getText();
        String min = pro_min_txt.getText();
        if(createnaja==true){
        String eiei = "INSERT INTO PRODUCT VALUE('"+id+"','"+vendor+"','"+proname+"','"+price+"','"+unit+"','"+min+"','N')";  
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
        DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_product();
        id();
        clear();
        }else if(editnaja==true){
            String edit = "UPDATE PRODUCT SET V_ID = '"+vendor+"',PRO_NAME = '"+proname+"',PRO_PRICE = '"+price+"',PRO_UNITS = '"+unit+"',PRO_MIN = '"+min+"' WHERE PRO_ID = '"+id+"'";  
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
                  DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_product();  
        }else if(deletenaja==true){
            String delete = "UPDATE PRODUCT SET PRO_DEL = 'Y' WHERE PRO_ID ='"+id+"'";
            System.out.print(delete);
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
        DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_product();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pro_price_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pro_price_txtActionPerformed

    }//GEN-LAST:event_pro_price_txtActionPerformed

    private void pro_price_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pro_price_txtFocusGained

    }//GEN-LAST:event_pro_price_txtFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
        showid_txt.setText(createid);
        product_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        unlock(); 
        clear();
        product_table.getSelectionModel().clearSelection();
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
        product_table.getSelectionModel().clearSelection();
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
        product_table.getSelectionModel().clearSelection();
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

    private void pro_unit_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pro_unit_txtFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pro_unit_txtFocusGained

    private void pro_unit_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pro_unit_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pro_unit_txtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     this.setVisible(false);
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
            java.util.logging.Logger.getLogger(Product_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JRadioButton edit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pro_min_txt;
    private javax.swing.JTextField pro_name_txt;
    private javax.swing.JTextField pro_price_txt;
    private javax.swing.JTextField pro_unit_txt;
    private javax.swing.JTable product_table;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JComboBox<String> v_txt;
    // End of variables declaration//GEN-END:variables
}
