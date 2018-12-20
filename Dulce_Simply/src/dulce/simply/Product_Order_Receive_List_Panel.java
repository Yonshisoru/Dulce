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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Yonshisoru
 */
public class Product_Order_Receive_List_Panel extends javax.swing.JFrame {
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    int max = 0;
    String output  = null;
    String stockid = null;
    Database d = new Database();
    Product_Order_Variable p = new Product_Order_Variable();
    Product_Order_Receive_view pv = new Product_Order_Receive_view();
    /**
     * Creates new form Product_Order_Receive_List_Panel
     */
    public Product_Order_Receive_List_Panel() {
        initComponents();
        fill();
        id_txt.setText(p.getlist_showid());
        product_txt.setText(p.getlist_productname());
        price_txt.setText(""+p.getlist_price());
        received_txt.setText(""+p.getlist_current());
        unit_txt.setText(""+p.getlist_unit());
        System.out.print(p.getlist_current());
    }
    
public void fill(){
    for(int i =1 ;i<=(p.getlist_unit()-p.getlist_current());i++){
        receiving_txt.addItem(""+i);
    }
}
public double findcurrent(String product){
    double output=0.0;
    String sql  ="select PRO_UNITS from PRODUCT WHERE PRO_NAME = '"+product+"'";
    System.out.println(sql);
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
     while(rs.next()){
         output = rs.getDouble("PRO_UNITS");
     }
    rs.close();
    pat.close();
    con.close();
    }catch(Exception e){
        
    }
    return output;
}
  public String stockid(){
       int count=0;
       max = 0;
          String sql  ="select STOCK_NUMBER from STOCK";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("STOCK_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("STOCK_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    stockid = output = String.valueOf(max);
    con.close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        received_txt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        exp_date = new datechooser.beans.DateChooserCombo();
        jLabel7 = new javax.swing.JLabel();
        id_txt = new javax.swing.JLabel();
        product_txt = new javax.swing.JLabel();
        price_txt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        receiving_txt = new javax.swing.JComboBox<>();
        unit_txt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(650, 300));
        setMaximumSize(new java.awt.Dimension(400, 260));
        setMinimumSize(new java.awt.Dimension(400, 260));
        setPreferredSize(new java.awt.Dimension(400, 260));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        received_txt.setText(" ");
        received_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(received_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 150, -1));

        jLabel2.setText("Product:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jLabel3.setText("Expiry Date:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel4.setText("Price:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jLabel5.setText("Received:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jLabel6.setText("Receiving:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));
        getContentPane().add(exp_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 180, -1));

        jLabel7.setText("ID:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        id_txt.setText(" ");
        id_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(id_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 150, -1));

        product_txt.setText(" ");
        product_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(product_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 150, -1));

        price_txt.setText(" ");
        price_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 150, -1));

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        getContentPane().add(receiving_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 40, -1));

        unit_txt.setText(" ");
        unit_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(unit_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 150, -1));

        jLabel8.setText("Ordering Unit:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
               pv.show_order_view();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        stockid();
        boolean check = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String gg = sdf.format(exp_date.getSelectedDate().getTime());
        String stock ="INSERT INTO STOCK VALUES('"+stockid+"','"+p.getlist_productid()+"','"+gg+"','"+LocalDate.now()+"','"+Integer.parseInt(receiving_txt.getSelectedItem().toString())+"',"+"'"+p.getview()+"','N')";
        System.out.print(stock);
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
       pat = con.prepareStatement(stock);
       pat.executeUpdate(stock);
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
     String sql ="UPDATE PRO_REC_LIST SET PRL_CURRENT = '"+(p.getlist_current()+Integer.parseInt(receiving_txt.getSelectedItem().toString()))+"' WHERE PRL_NUMBER = '"+p.getlist_showid()+"'";
     System.out.print(sql);
     if((p.getlist_current()+Integer.parseInt(receiving_txt.getSelectedItem().toString()))==p.getlist_unit()){
         check=true;
         System.out.print(check);
     }
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
       pat = con.prepareStatement(sql);
       pat.executeUpdate(sql);
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
     String updateproduct ="UPDATE PRODUCT SET PRO_UNITS = '"+((findcurrent(product_txt.getText()))+Integer.parseInt(receiving_txt.getSelectedItem().toString()))+"' WHERE PRO_NAME = '"+product_txt.getText()+"';";
     System.out.println(updateproduct);
         try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
       pat = con.prepareStatement(updateproduct);
       pat.executeUpdate(updateproduct);
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
         if((p.getlist_current()+Integer.parseInt(receiving_txt.getSelectedItem().toString()))==p.getlist_unit()){
        String updateorder ="UPDATE PRO_REC_LIST SET PRL_STATUS = 'Y' WHERE PRL_NUMBER = '"+p.getlist_showid()+"'";
        System.out.println(updateorder);
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(updateorder);
        pat.executeUpdate(updateorder);
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        }
        String updateorder ="SELECT PRL_STATUS FROM PRO_REC_LIST WHERE PR_ID = '"+p.getreceiveid()+"'";
        System.out.println(updateorder);
        boolean receiveall = true;
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(updateorder);
        rs = pat.executeQuery(updateorder);
        while(rs.next()){
            if(rs.getString("PRL_STATUS").equals("N")){
                receiveall = false;
            }
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        System.out.print(receiveall);
        if(receiveall == true){
        String updatereceive ="UPDATE PRO_RECEIVE SET PR_STATUS = 'Y' WHERE PR_ID = '"+p.getreceiveid()+"'";
        System.out.println(updatereceive);
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(updatereceive);
        pat.executeUpdate(updatereceive);
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }    
        }
        JOptionPane.showMessageDialog(null, "Success");
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
               pv.show_order_view();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Product_Order_Receive_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product_Order_Receive_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product_Order_Receive_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product_Order_Receive_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product_Order_Receive_List_Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo exp_date;
    private javax.swing.JLabel id_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel price_txt;
    private javax.swing.JLabel product_txt;
    private javax.swing.JLabel received_txt;
    private javax.swing.JComboBox<String> receiving_txt;
    private javax.swing.JLabel unit_txt;
    // End of variables declaration//GEN-END:variables
}
