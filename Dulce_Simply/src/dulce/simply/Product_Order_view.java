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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Product_Order_view extends javax.swing.JFrame {
    Main_variable m = new Main_variable();
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    Database d = new Database();
    String id = null;
    int count = 0;

    /**
     * Creates new form Product_Order_view
     */
    public Product_Order_view() {
        initComponents();
        show_order_view();
    }
   public ArrayList<Product_Order_Variable>Product_order_List(){
               ArrayList<Product_Order_Variable> Product_order_list = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select PO_ID,EMP_ID,EMP_FNAME,EMP_LNAME,V_ID,V_NAME,PO_DATE,PO_PRICE,PO_REC_DATE,PO_PAY_DATE,PO_UNITS,PR_STATUS,OP_STATUS FROM (((PRO_ORDER NATURAL JOIN EMPLOYEE)NATURAL JOIN VENDOR)NATURAL JOIN PRO_RECEIVE)NATURAL JOIN ORDER_PAYMENT WHERE PO_DEL = 'N' ORDER BY PO_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Product_Order_Variable p = new Product_Order_Variable();
            p.setid(rs.getString("PO_ID"));
            p.e.setid(rs.getString("EMP_ID"));
            p.e.setfname(rs.getString("EMP_FNAME"));
            p.e.setlname(rs.getString("EMP_LNAME"));
            p.v.setid(rs.getString("V_ID"));
            p.v.setname(rs.getString("V_NAME"));
            p.setdate(rs.getString("PO_DATE"));
            p.setrec_date(rs.getString("PO_REC_DATE"));
            p.setpay_date(rs.getString("PO_PAY_DATE"));
            p.setunit(rs.getInt("PO_UNITS"));
            p.setprice(rs.getInt("PO_PRICE"));
            p.setpaystatus(rs.getString("OP_STATUS"));
            p.setreceivestatus(rs.getString("PR_STATUS"));
            Product_order_list.add(p);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Product_order_list;
}
    public void show_order_view(){
        ArrayList<Product_Order_Variable>Product_order_list = Product_order_List();
        DefaultTableModel model = (DefaultTableModel)order_view_table.getModel();
        Object[] row = new Object[10];
        for(int i=0;i<Product_order_list.size();i++){
            row[0]=Product_order_list.get(i).getdate();
            row[1]=Product_order_list.get(i).getid();
            row[2]=Product_order_list.get(i).getunit();
            row[3]=Product_order_list.get(i).getprice();
            row[4]=Product_order_list.get(i).getrec_date();
            row[5]=Product_order_list.get(i).getpay_date();
            row[6]=Product_order_list.get(i).e.getfname()+" "+Product_order_list.get(i).e.getlname();
            row[7]=Product_order_list.get(i).v.getname();
            row[8]=Product_order_list.get(i).getpaystatus();
            row[9]=Product_order_list.get(i).getreceivestatus();
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
        order_view_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(875, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        order_view_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "ID", "Total", "Price", "Receive date", "Pay date", "Employee", "Vendor", "Pay Status", "Receive Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        order_view_table.getTableHeader().setReorderingAllowed(false);
        order_view_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_view_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(order_view_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 820, 450));

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 0, 48)); // NOI18N
        jLabel1.setText("Ordering List");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 130, 50));

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 150, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(m.getorderpay()==1){
            
        }else{
        if(count == 0){
        JOptionPane.showMessageDialog(null,"Double Click Row of Table for view Order Details.");
        count = 1;
        }
        }
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.print("count");
        count = 0;
        m.setorderpayment(0);
        m.setreceive(0);
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        System.out.print("count");
        count = 0;
        m.setorderpayment(0);
        m.setreceive(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void order_view_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_view_tableMouseClicked
        boolean confirm = false;
        if(order_view_table.getModel().getValueAt(order_view_table.getSelectedRow(),1).toString().equals(id)){
            if(m.getorderpay()==1){
                  if(JOptionPane.showConfirmDialog(null,order_view_table.getModel().getValueAt(order_view_table.getSelectedRow(),1).toString()+" was paid already?","Confirm",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
            if(confirm==true){
                String sql = "UPDATE ORDER_PAYMENT SET OP_STATUS = 'Y' WHERE PO_ID='"+order_view_table.getModel().getValueAt(order_view_table.getSelectedRow(),1).toString()+"'";
                System.out.print(sql);
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(sql);
        pat.executeUpdate(sql);
        //JOptionPane.showMessageDialog(null,"Crerate orderpayment success!");
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
             DefaultTableModel dm = (DefaultTableModel)order_view_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_order_view();
        }
            }else if(m.getreceive()==1){
            Product_Order_Variable p = new Product_Order_Variable();
            p.setview(order_view_table.getModel().getValueAt(order_view_table.getSelectedRow(),1).toString());
            Product_Order_Receive_view pr = new Product_Order_Receive_view();
            pr.setVisible(true);
            id = null;   
            }else{
            Product_Order_Variable p = new Product_Order_Variable();
            p.setview(order_view_table.getModel().getValueAt(order_view_table.getSelectedRow(),1).toString());
            Product_Order_List_view pv = new Product_Order_List_view();
            pv.setVisible(true);
            id = null;
            }
        }else{
            id = order_view_table.getModel().getValueAt(order_view_table.getSelectedRow(),1).toString();
        }
    }//GEN-LAST:event_order_view_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel dm = (DefaultTableModel)order_view_table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
        show_order_view();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Product_Order_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product_Order_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product_Order_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product_Order_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product_Order_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable order_view_table;
    // End of variables declaration//GEN-END:variables
}
