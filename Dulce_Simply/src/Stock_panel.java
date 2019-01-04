/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Cursor;
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
public class Stock_panel extends javax.swing.JFrame {
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    Stock_Variable s = new Stock_Variable();
//--------------------------------------------------------
     ArrayList<Stock_Variable> Stock_Array = new ArrayList<>();
     ArrayList<Product_variable> Product_Array = new ArrayList<>();
//-----------------------------------------------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
//-------------------------------------------------
    String output = null;
    String menu = null;
    String password= null;
    String createid = null;
    public String id = null;
//----------------------------------------------------
    int max = 0;
//------------------------------------------------------
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    boolean pass = false;
    /**
     * Creates new form Employee_create
     */
    public Stock_panel() {
        initComponents();
        StockList();
        show_stock();
        fillcombo();
        showid_txt.setText(id());
        //fillcombo();
    }
    public void clear(){
         m_price_txt.setText("");
    }
    public void lock(){

         m_price_txt.setEnabled(false);
    }
    public void unlock(){

         m_price_txt.setEnabled(true);
    }
/*public String find(){
            String find = "SELECT M_T_ID,M_T_NAME FROM MENU_TYPE";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
            if(m_cata_txt.getSelectedItem().toString().equals(rs.getString("M_T_NAME"))){
                menu = rs.getString("M_T_ID");
                System.out.print(menu);
            }
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        return menu;
  }*/
public Connection getcon(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
    }catch(Exception e){
        System.err.println("Cannot connect to server");
        throw new RuntimeException(e);
    }
    return con;
}

  public String id(){
       int count=0;
       max = 0;
          String sql  ="select STOCK_NUMBER from STOCK";
    try{
    pat = getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
     count++;
     System.out.println(rs.getString("STOCK_NUMBER"));
    if(Integer.parseInt(rs.getString("STOCK_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("STOCK_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    output = ""+max;
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
  
    void fillcombo(){
      try{
          String sql = "SELECT PRO_ID,PRO_NAME,PRO_UNITS,PRO_UNITS_TYPE FROM PRODUCT WHERE PRO_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Product_variable p = new Product_variable();
            p.setid(rs.getString("PRO_ID"));
            p.setname(rs.getString("PRO_NAME"));
            p.setunit(rs.getDouble("PRO_UNITS"));
            p.setunits_type(rs.getString("PRO_UNITS_TYPE"));
            Product_Array.add(p);
            product_combo.addItem(rs.getString("PRO_NAME"));
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){

      }
  }
   public void StockList(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select STOCK_NUMBER,PRO_ID,PRO_NAME,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID FROM STOCK NATURAL JOIN PRODUCT ORDER BY STOCK_NUMBER";
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Stock_Variable s = new Stock_Variable();
            s.setstocknumber(rs.getInt("STOCK_NUMBER"));
            s.setproductid(rs.getString("PRO_ID"));
            s.setproductname(rs.getString("PRO_NAME"));
            s.setstockexpdate(rs.getString("STOCK_EXP"));
            s.setstockstartdate(rs.getString("STOCK_STARTDATE"));
            s.setstockunits(rs.getDouble("STOCK_UNITS"));
            s.setorderid(rs.getString("PO_ID"));
            Stock_Array.add(s);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
}
    public void show_stock(){
        ArrayList<Stock_Variable>MenuList = Stock_Array;
        DefaultTableModel model = (DefaultTableModel)stock_table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<MenuList.size();i++){
            row[0]=MenuList.get(i).getstocknumber();
            row[1]=MenuList.get(i).getproductname();
            row[2]=MenuList.get(i).getstockunits();
            row[3]=MenuList.get(i).getstockexpdate();
            row[4]=MenuList.get(i).getstockstartdate();
            row[5]=MenuList.get(i).getorderid();
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
        stock_table = new javax.swing.JTable();
        showid_txt = new javax.swing.JTextField();
        m_price_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        delete = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        edit = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        product_combo = new javax.swing.JComboBox<>();
        exp_date = new datechooser.beans.DateChooserCombo();
        mft_date = new datechooser.beans.DateChooserCombo();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Product", "Units", "Mfg Date", "Exp Date", "OrderID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stock_table.getTableHeader().setReorderingAllowed(false);
        stock_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stock_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stock_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 820, 680));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 110, 30));

        m_price_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                m_price_txtFocusGained(evt);
            }
        });
        m_price_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_price_txtActionPerformed(evt);
            }
        });
        getContentPane().add(m_price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 90, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Units:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

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

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Manufactured Date:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        delete.setOpaque(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Function:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.setOpaque(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, -1, -1));

        edit.setOpaque(false);
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
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        jLabel13.setText("Delete");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, -1, -1));

        jLabel14.setText("Create");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, -1, -1));

        jLabel15.setText("Edit");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Product:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));

        product_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose Product<<" }));
        product_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_comboMouseClicked(evt);
            }
        });
        product_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_comboActionPerformed(evt);
            }
        });
        getContentPane().add(product_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 160, 30));
        getContentPane().add(exp_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, -1, 30));
        getContentPane().add(mft_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Expired Date:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stock_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stock_tableMouseClicked
       /*if(editnaja==true||deletenaja==true){
        showid_txt.setText(menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString());
        m_name_txt.setText(menu_table.getModel().getValueAt(menu_table.getSelectedRow(),1).toString());
        m_price_txt.setText(menu_table.getModel().getValueAt(menu_table.getSelectedRow(),2).toString());
        m_cata_txt.setSelectedItem(menu_table.getModel().getValueAt(menu_table.getSelectedRow(),3));
        }*/
    }//GEN-LAST:event_stock_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String id = showid_txt.getText();
    
                /*
        if(createnaja==true){
        String eiei = "INSERT INTO MENU VALUE('"+id+"','"+menu_name+"','"+menu_price+"','"+menu+"','N')";
        System.out.print(eiei);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(eiei);
        pat.executeUpdate(eiei);
        JOptionPane.showMessageDialog(null,"Adding Menu success");
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        DefaultTableModel dm = (DefaultTableModel)menu_table.getModel();
        while(dm.getRowCount() > 0)
        {
        dm.removeRow(0);
        }
        show_product();
        id();
        clear();
        }else if(editnaja==true){
            String edit = "UPDATE MENU SET MENU_NAME = '"+menu_name+"',MENU_PRICE = '"+menu_price+"',M_T_ID = '"+menu+"' WHERE MENU_ID = '"+id+"'";
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
                  DefaultTableModel dm = (DefaultTableModel)menu_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {
        dm.removeRow(0);
        }
        show_product();
        }else if(deletenaja==true){
            String delete = "UPDATE MENU SET MENU_DEL = 'Y' WHERE MENU_ID ='"+id+"'";
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
        DefaultTableModel dm = (DefaultTableModel)menu_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {
        dm.removeRow(0);
        }
        show_product();
        }*/
    }//GEN-LAST:event_jButton2ActionPerformed

    private void m_price_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_price_txtActionPerformed

    }//GEN-LAST:event_m_price_txtActionPerformed

    private void m_price_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_m_price_txtFocusGained

    }//GEN-LAST:event_m_price_txtFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
        showid_txt.setText(createid);
        stock_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        unlock();
        clear();
        stock_table.getSelectionModel().clearSelection();
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
        stock_table.getSelectionModel().clearSelection();
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
        stock_table.getSelectionModel().clearSelection();
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void product_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_comboMouseClicked

    }//GEN-LAST:event_product_comboMouseClicked

    private void product_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_comboActionPerformed
        if(product_combo.getSelectedIndex()==0){
            
        }else{
            for(Product_variable p:Product_Array){
                if(product_combo.getSelectedItem().toString().equals(p.getname())){
                   JOptionPane.showMessageDialog(null,p.getid()+" "+p.getname()+" "+p.getunit()+" "+p.getunit_type());
                   break;
                }
            }
        }
    }//GEN-LAST:event_product_comboActionPerformed

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
            java.util.logging.Logger.getLogger(Stock_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JRadioButton edit;
    private datechooser.beans.DateChooserCombo exp_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField m_price_txt;
    private datechooser.beans.DateChooserCombo mft_date;
    private javax.swing.JComboBox<String> product_combo;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JTable stock_table;
    // End of variables declaration//GEN-END:variables
}
