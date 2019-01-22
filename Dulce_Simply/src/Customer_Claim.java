
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Customer_Claim extends javax.swing.JFrame {
ArrayList<Stock_Variable>Stock_Array = new ArrayList<>();
ArrayList<Stock_Variable>Stock_Units = new ArrayList<>();
ArrayList<Menu_variable> Ordering_Array = new ArrayList<>();
ArrayList<Menu_variable> Menu_Array = new ArrayList<>();
ArrayList<Menu_variable> Ordering_List_Array = new ArrayList<>();
ArrayList<String> order_menu_id = new ArrayList<>();
ArrayList<Product_variable>Menu_Ingredient_Array = new ArrayList<>();
Database d = new Database();
Table_variable t = new Table_variable();

Connection con = null;
PreparedStatement pat = null;
ResultSet rs = null;

String doubleclick = "";
String orderid = "";
String tableid = "";

int eiei = 0;
    /**
     * Creates new form Customer_Claim
     */
    public Customer_Claim() {
        initComponents();
        t.setid("01");
        tableid_txt.setText(t.getid());
        //System.out.println(tableid);
        getMenu();
        getStock();
        getIngredient();
        getorderid();
        getorder_menu();
        showorder_menu();
    }
 public Connection getcon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(d.url(),d.username(),d.password());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Didn't connect");
            throw new RuntimeException(e);
        }
    }
    public void getIngredient(){
        String sql = "Select ING_NUMBER,MENU_ID,PRO_ID,ING_UNITS FROM INGREDIENT WHERE ING_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Product_variable p = new Product_variable();
                p.setIngredient_ID(rs.getString("ING_NUMBER"));
                p.m.setid(rs.getString("MENU_ID"));
                p.setid(rs.getString("PRO_ID"));
                p.setunit(rs.getDouble("ING_UNITS"));
                Menu_Ingredient_Array.add(p);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void getStock(){
        String sql = "Select STOCK_NUMBER,PRO_ID,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID FROM STOCK WHERE STOCK_DEL = 'N' AND STOCK_EXP >= '"+LocalDate.now()+"' ORDER BY STOCK_EXP";
        //System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Stock_Variable s = new Stock_Variable();
                s.setstocknumber(rs.getInt("STOCK_NUMBER"));
                s.setproductid(rs.getString("PRO_ID"));
                s.setstockexpdate(rs.getString("STOCK_EXP"));
                s.setstockstartdate(rs.getString("STOCK_STARTDATE"));
                s.setstockunits(rs.getDouble("STOCK_UNITS"));
                Stock_Array.add(s);
                Stock_Variable ss = new Stock_Variable();
                ss.setstocknumber(rs.getInt("STOCK_NUMBER"));
                ss.setproductid(rs.getString("PRO_ID"));
                ss.setstockexpdate(rs.getString("STOCK_EXP"));
                ss.setstockstartdate(rs.getString("STOCK_STARTDATE"));
                ss.setstockunits(rs.getDouble("STOCK_UNITS"));
                Stock_Units.add(ss);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
public void setStock(){
        for(Stock_Variable s:Stock_Array){
            for(Stock_Variable ss:Stock_Units){
                if(s.getstocknumber()==ss.getstocknumber()){
                if(s.getstockunits()!=ss.getstockunits()){
                    String sql = "UPDATE STOCK SET STOCK_UNITS = '"+s.getstockunits()+"' WHERE STOCK_NUMBER = '"+s.getstocknumber()+"'";
                    System.err.println(sql);
                    try{
                        pat = getcon().prepareStatement(sql);
                        pat.executeUpdate(sql);
                        pat.close();
                        getcon().close();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                break;
                    }
                }
            }
        }
        Stock_Units.clear();
    }
    public void getMenu(){
        String sql = "Select MENU_ID,MENU_NAME,MENU_PRICE,M_T_ID FROM MENU WHERE MENU_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Menu_variable m = new Menu_variable();
                m.setid(rs.getString("MENU_ID"));
                m.setname(rs.getString("MENU_NAME"));
                m.setprice(rs.getInt("MENU_PRICE"));
                m.setcataid(rs.getString("M_T_ID"));
                Menu_Array.add(m);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){

        }
    }
    public void getorderid(){
        String sql = "Select ORD_ID,EMP_ID,T_ID,ORD_DATE,ORD_TOTAL,ORD_PAYTYPE FROM ORDERING";
        //System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Menu_variable m = new Menu_variable();
                m.c.setorderid(rs.getString("ORD_ID"));
                m.e.setid(rs.getString("EMP_ID"));
                m.c.settable(rs.getString("T_ID"));
                m.c.setorderdate(rs.getString("ORD_DATE"));
                m.c.settotal(rs.getDouble("ORD_TOTAL"));
                m.c.setpaytype(rs.getString("ORD_PAYTYPE"));
                Ordering_Array.add(m);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){

        }
    }
    public void getorder_menu(){
        String sql = "Select OM_ID,ORD_ID,MENU_ID,OM_UNITS,OM_PRICE,OM_STATUS FROM ORDER_MENU_LIST";
        //System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Menu_variable m = new Menu_variable();
                m.c.setorder_menu_id(rs.getString("OM_ID"));
                m.c.setorderid(rs.getString("ORD_ID"));
                m.setid(rs.getString("MENU_ID"));
                for(Menu_variable mv :Menu_Array){
                    if(m.getid().equals(mv.getid())){
                        m.setname(mv.getname());
                        break;
                    }
                }
                m.c.setunits(rs.getInt("OM_UNITS"));
                m.c.settotal(rs.getDouble("OM_PRICE"));
                m.c.setorder_menu_status(rs.getString("OM_STATUS"));
                Ordering_List_Array.add(m);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){

        }
    }
 public void showorder_menu(){
     int count = 1;
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        Object[] row = new Object[5];
        for(Menu_variable mv:Ordering_Array){
            //System.out.println(mv.c.gettable());
            if(mv.c.gettable().equals(t.getid())){
                //System.out.println("eiei"+ mv.c.getorderid());
                t.setorderid(orderid);
                orderid = mv.c.getorderid();
                System.out.println(orderid);
                break;
            }
        }
        for(Menu_variable m:Ordering_List_Array){
            if(m.c.getorderid().equals(orderid)){
                order_menu_id.add(m.c.getorder_menu_id());
                row[0] = m.c.getorder_menu_id();
                row[1] = m.getname();
                row[2] = m.c.getunits();
                row[3] = m.c.gettotal();
                row[4] = m.c.getorder_menu_status();
                model.addRow(row);
            }
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
        menu_table = new javax.swing.JTable();
        tableid_txt = new javax.swing.JLabel();
        tableid_label = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(829, 557));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเมนู", "ชื่อเมนู", "จำนวน", "ราคา", "สถานะ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menu_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(menu_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 600, 350));

        tableid_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableid_txt.setText("  ");
        getContentPane().add(tableid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 50, -1));

        tableid_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableid_label.setText("โต๊ะที่ :");
        getContentPane().add(tableid_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jButton3.setText("ปิด");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 150, 50));

        jButton1.setText("?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseClicked
        if(doubleclick.equals(menu_table.getValueAt(menu_table.getSelectedRow(),0).toString())){
            String status = menu_table.getValueAt(menu_table.getSelectedRow(),4).toString();
            if(status.equals("Y")){
                if(JOptionPane.showConfirmDialog(null,"คุณต้องการจะเคลมเมนูรายการที่ "+(menu_table.getSelectedRow()+1)+" จริงหรือไม่",null,YES_NO_OPTION)==YES_OPTION){
                    Table_variable t = new Table_variable();
                    t.setorder_list(menu_table.getValueAt(menu_table.getSelectedRow(),0).toString());
                    this.setVisible(false);
                    Customer_Claim_Report ccr = new Customer_Claim_Report();
                    ccr.setVisible(true);
                }
            }else{
               JOptionPane.showMessageDialog(null,"คุณไม่สามารถเคลมเมนูนี้ได้ เนื่องจากยังไม่ได้เสิร์ฟเมนูไปก่อนหน้านี้");
            }
        }else{
            doubleclick = menu_table.getValueAt(menu_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_menu_tableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        Table_panel p = new Table_panel();
        p.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(eiei==0){
            JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กเพื่อเคลมสินค้ารายการนั้นๆ");
            eiei++;
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กเพื่อเคลมสินค้ารายการนั้นๆ");
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_Claim().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menu_table;
    private javax.swing.JLabel tableid_label;
    private javax.swing.JLabel tableid_txt;
    // End of variables declaration//GEN-END:variables
}
