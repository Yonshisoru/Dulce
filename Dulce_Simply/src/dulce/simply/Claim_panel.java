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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanachot
 */
public class Claim_panel extends javax.swing.JFrame {
    ArrayList<Claim_Variable> Claimproduct = new ArrayList<>();
    ArrayList<Stock_Variable> stock = new ArrayList<>();
    ArrayList<Stock_Variable> orderid = new ArrayList<>();
//-------------------------Call date from another class-----------------------------------------------//
    Stock_Variable s = new Stock_Variable();
    Database d = new Database();
    Employee e = new Employee();
    Claim_Variable c=  new Claim_Variable();
//-----------------------------Initilize variable---------------------------------------//
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    int max = 0;
    String output = "";
    /**
     * Creates new form Claim_panel
     */
    public Claim_panel() {
        initComponents();
        show_productfromstock();
        id();
        Employeeid_txt.setText(e.getshowid());
        fillcombo();
    }
    void fillstock(String id){
        try{
          String sql = "select STOCK_NUMBER,PRO_ID,PRO_NAME,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID FROM STOCK NATURAL JOIN PRODUCT WHERE PO_ID = '"+id+"' ORDER BY STOCK_NUMBER";
         System.out.print(sql);
          con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Stock_combo.addItem(rs.getString("STOCK_NUMBER"));
            Stock_Variable v = new Stock_Variable();
            v.setstocknumber(Integer.parseInt(rs.getString("STOCK_NUMBER")));
            System.out.print(rs.getString("STOCK_NUMBER"));
            v.setorderid(rs.getString("PO_ID"));
            v.setproductid(rs.getString("PRO_ID"));
            v.setproductname(rs.getString("PRO_NAME"));
            orderid.add(v);
        }
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){

  }
    }
void fillproduct(String id){
        try{
          String sql = "select STOCK_NUMBER,PRO_ID,PRO_NAME FROM STOCK NATURAL JOIN PRODUCT WHERE STOCK_NUMBER = '"+id+"' ORDER BY STOCK_NUMBER";
         System.out.print(sql);
          con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            product_txt.setText(rs.getString("PRO_NAME"));
        }
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){

  }
    }
    void fillcombo(){
      try{
          String sql = "select STOCK_NUMBER,PRO_ID,PRO_NAME,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID FROM STOCK NATURAL JOIN PRODUCT ORDER BY STOCK_NUMBER";
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            orderid_combo.addItem(rs.getString("PO_ID"));
            if(!rs.getString("PO_ID").isEmpty()){
            Stock_Variable s = new Stock_Variable();
            s.setorderid(rs.getString("PO_ID"));
            stock.add(s);
            }
        }
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){

      }
      System.out.println("Size of stock ="+stock.size()); 
  }
 public String id(){
       int count=0;
          String sql  ="select CL_ID from CLAIM";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("CL_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("CL_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "C00"+max;
    }else if(max<100){
        output = "C0"+max;
    }else{
        output = "C"+max;
    }
    Claimid_txt.setText(output);
    con.close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    System.out.print(output);
    return output;
   }
public ArrayList<Claim_Variable> Claimproduct(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select CL_ID,EMP_ID,EMP_FNAME,EMP_LNAME,CL_DATE,CL_REC_DATE,CL_STATUS,COUNT(C_L_NUMBER) FROM (CLAIM NATURAL JOIN CLAIM_LIST)NATURAL JOIN EMPLOYEE WHERE CL_DEL = 'N'";
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Claim_Variable c = new Claim_Variable();
            c.setclaimid(rs.getString("CL_ID"));
            c.setdate(rs.getString("CL_DATE"));
            c.setreceivedate(rs.getString("CL_REC_DATE"));
            c.setproductcount(rs.getInt("COUNT(C_L_NUMBER)"));
            c.setempfname(rs.getString("EMP_FNAME"));
            c.setemplname(rs.getString("EMP_LNAME"));
            c.setstatus(rs.getString("CL_STATUS"));
            Claimproduct.add(c);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Claimproduct;
}
    public void show_productfromstock(){
        ArrayList<Claim_Variable>product = Claimproduct();
        DefaultTableModel model = (DefaultTableModel)Claim_Table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<product.size();i++){
            row[0]=product.get(i).getclaimid();
            row[1]=product.get(i).getdate();
            row[2]=product.get(i).getreceivedate();
            row[3]=product.get(i).getproductcount();
            row[4]=product.get(i).getempfname()+" "+product.get(i).getemplname();
            row[5]=product.get(i).getstatus();
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

        Type = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Claim_Table = new javax.swing.JTable();
        Claim_label = new javax.swing.JLabel();
        emp_label = new javax.swing.JLabel();
        Orderid_label = new javax.swing.JLabel();
        orderid_combo = new javax.swing.JComboBox<>();
        Stock_combo = new javax.swing.JComboBox<>();
        Employeeid_txt = new javax.swing.JTextField();
        Claimid_txt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Header = new javax.swing.JLabel();
        receive_combo = new datechooser.beans.DateChooserCombo();
        date_combo = new datechooser.beans.DateChooserCombo();
        date_label = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        close_btn = new javax.swing.JButton();
        create_radio = new javax.swing.JRadioButton();
        delete_radio = new javax.swing.JRadioButton();
        receive_label = new javax.swing.JLabel();
        function_label = new javax.swing.JLabel();
        create_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        Stock_label = new javax.swing.JLabel();
        Product_label = new javax.swing.JLabel();
        product_add = new javax.swing.JButton();
        product_txt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock Number", "Product", "Units", "Price", "OrderID", "Cause"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 730, 280));

        Claim_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ClaimID", "Date", "Receive Date", "ProductCount", "Employee", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Claim_Table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 610, 710));

        Claim_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Claim_label.setText("Claim ID:");
        getContentPane().add(Claim_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        emp_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emp_label.setText("Employee ID:");
        getContentPane().add(emp_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        Orderid_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Orderid_label.setText("OrderID:");
        getContentPane().add(Orderid_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        orderid_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Choose OrderID>" }));
        orderid_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderid_comboActionPerformed(evt);
            }
        });
        getContentPane().add(orderid_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 140, 30));

        Stock_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Choose Stocknumber>" }));
        Stock_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stock_comboActionPerformed(evt);
            }
        });
        getContentPane().add(Stock_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 215, 150, 30));

        Employeeid_txt.setEditable(false);
        getContentPane().add(Employeeid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 100, 30));

        Claimid_txt.setEditable(false);
        getContentPane().add(Claimid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 100, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Product to Claim");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Claim Table");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        Header.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Header.setText("Claim Panel");
        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));
        getContentPane().add(receive_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, -1, 30));
        getContentPane().add(date_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 275, -1, 30));

        date_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_label.setText("Date:");
        getContentPane().add(date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        close_btn.setText("Close");
        getContentPane().add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 100, 40));

        Type.add(create_radio);
        create_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        create_radio.setSelected(true);
        create_radio.setText("Create");
        getContentPane().add(create_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        Type.add(delete_radio);
        delete_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        delete_radio.setText("Delete");
        getContentPane().add(delete_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, -1, -1));

        receive_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        receive_label.setText("Received Claimed Product:");
        getContentPane().add(receive_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        function_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        function_label.setText("Function:");
        getContentPane().add(function_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 345, -1, -1));

        create_btn.setText("Create");
        getContentPane().add(create_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 100, 40));

        clear_btn.setText("Clear");
        getContentPane().add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 90, 40));

        Stock_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Stock_label.setText("Stock:");
        getContentPane().add(Stock_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        Product_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Product_label.setText("Product:");
        getContentPane().add(Product_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        product_add.setText("Add");
        product_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_addActionPerformed(evt);
            }
        });
        getContentPane().add(product_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 60, 30));

        product_txt.setEditable(false);
        getContentPane().add(product_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 150, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderid_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderid_comboActionPerformed
       Stock_combo.removeAllItems();
       Stock_combo.addItem("<Choose Stocknumber>");
       if(orderid_combo.getSelectedIndex()==0){
           
       }else{
       if(orderid_combo.getSelectedItem().toString().isEmpty()){
           System.out.println("EIEI");
       }else{
       for(int i =0;i<stock.size();i++){
           System.out.println(stock.get(i).getorderid());
           if(orderid_combo.getSelectedItem().toString().equals(stock.get(i).getorderid())){
               fillstock(orderid_combo.getSelectedItem().toString());
               break;
        }
       }
       }
       }
    }//GEN-LAST:event_orderid_comboActionPerformed

    private void product_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_product_addActionPerformed

    private void Stock_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stock_comboActionPerformed
       product_txt.setText("");
       if(Stock_combo.getSelectedIndex()==0){ 
       }else{
       for(int i =0;i<orderid.size();i++){
           System.out.println(orderid.get(i).getstocknumber());
           if(Integer.parseInt(Stock_combo.getSelectedItem().toString())==((orderid.get(i).getstocknumber()))){
           fillproduct(Stock_combo.getSelectedItem().toString());
       }
       }
       }
    }//GEN-LAST:event_Stock_comboActionPerformed

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
            java.util.logging.Logger.getLogger(Claim_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Claim_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Claim_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Claim_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Claim_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Claim_Table;
    private javax.swing.JLabel Claim_label;
    private javax.swing.JTextField Claimid_txt;
    private javax.swing.JTextField Employeeid_txt;
    private javax.swing.JLabel Header;
    private javax.swing.JLabel Orderid_label;
    private javax.swing.JLabel Product_label;
    private javax.swing.JComboBox<String> Stock_combo;
    private javax.swing.JLabel Stock_label;
    private javax.swing.ButtonGroup Type;
    private javax.swing.JButton clear_btn;
    private javax.swing.JButton close_btn;
    private javax.swing.JButton create_btn;
    private javax.swing.JRadioButton create_radio;
    private datechooser.beans.DateChooserCombo date_combo;
    private javax.swing.JLabel date_label;
    private javax.swing.JRadioButton delete_radio;
    private javax.swing.JLabel emp_label;
    private javax.swing.JLabel function_label;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> orderid_combo;
    private javax.swing.JButton product_add;
    private javax.swing.JTextField product_txt;
    private datechooser.beans.DateChooserCombo receive_combo;
    private javax.swing.JLabel receive_label;
    // End of variables declaration//GEN-END:variables
}
