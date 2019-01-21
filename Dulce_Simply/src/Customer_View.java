
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
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
public class Customer_View extends javax.swing.JFrame {
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

String orderid = null;
String tableid = t.getid();
String doubleclick = "";

double stock_units = 0.0;

boolean fullserve = false;
    /**
     * Creates new form Customer_View
     */
    public Customer_View() {
        initComponents();
        tableid_txt.setText(t.getid());
        //System.out.println(tableid);
        getMenu();
        getStock();
        getIngredient();
        getorderid();
        getorder_menu();
        showorder_menu();
        jPanel1.setVisible(false);
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
                        //System.out.println(mv.getname());
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
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        Object[] row = new Object[5];
        fullserve = true;
        //System.out.println(tableid);
        for(Menu_variable mv:Ordering_Array){
            //System.out.println(mv.c.gettable());
            if(mv.c.gettable().equals(tableid)&&mv.c.getpaytype().isEmpty()){
                //System.out.println("eiei"+ mv.c.getorderid());
                orderid = mv.c.getorderid();
                break;
            }
        }
        for(Menu_variable m:Ordering_List_Array){
            if(m.c.getorderid().equals(orderid)){
                order_menu_id.add(m.c.getorder_menu_id());
                //System.out.println(m.c.getorder_menu_id());
                row[0] = m.getid();
                row[1] = m.getname();
                row[2] = m.c.getunits();
                row[3] = m.c.gettotal();
                row[4] = m.c.getorder_menu_status();
                if(m.c.getorder_menu_status().equals("N")){
                    fullserve = false;
                }
                model.addRow(row);
            }
        }
        if(fullserve==true){
        serve_txt.setText("เสิร์ฟครบทุกเมนูแล้ว");
        serve_txt.setForeground(java.awt.Color.CYAN);
        }else{
        serve_txt.setText("เสิร์ฟยังไม่ครบ");
        serve_txt.setForeground(java.awt.Color.RED);
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        menu_table = new javax.swing.JTable();
        tableid_txt = new javax.swing.JLabel();
        tableid_label = new javax.swing.JLabel();
        serve_txt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 560));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 430, -1, -1));

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

        serve_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(serve_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 37, 200, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("เสิร์ฟครบ:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        jButton3.setText("ปิด");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 150, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        Table_panel p = new Table_panel();
        p.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void menu_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseClicked
        if(doubleclick.equals(menu_table.getValueAt(menu_table.getSelectedRow(),0).toString())){
            String name = null;
            int count = menu_table.getSelectedRow();
            for(Menu_variable m:Menu_Array){
                if(doubleclick.equals(m.getid())){
                    //System.out.println(order_menu_id.get(count));
                    name = m.getname();
                    break;
                }
            }
            if(fullserve==false){
                if(menu_table.getValueAt(menu_table.getSelectedRow(),4).toString().equals("Y")){
                    JOptionPane.showMessageDialog(null,"รายการนี้เสิร์ฟเรียบร้อยแล้ว ยกเลิกรายการ");
                    doubleclick = "";
                }else{
            if(JOptionPane.showConfirmDialog(null,"เมนู " +name+" มีการเสิร์ฟแล้วหรือไม่",null,YES_NO_OPTION)==YES_OPTION){
                String sql = "UPDATE ORDER_MENU_LIST SET OM_STATUS = 'Y' WHERE OM_ID ='"+order_menu_id.get(count)+"'";
                try{
                    pat = getcon().prepareStatement(sql);
                    pat.executeUpdate(sql);
                    pat.close();
                    getcon().close();
                }catch(Exception e){
                    
                }
                //System.err.println(sql);
                for(Menu_variable mm:Ordering_List_Array){
                    if(mm.c.getorder_menu_id().equals(order_menu_id.get(count))){
                        for(Menu_variable mv:Menu_Array){
                            if(mm.getid().equals(mv.getid())){
                                //System.out.println(mv.getid());
                                //System.out.println(mv.getname());
                                    for(Product_variable p:Menu_Ingredient_Array){
                                        if(p.m.getid().equals(mv.getid())){
                                            //System.out.println("----"+p.getid());
                                            //System.out.println("----"+p.getunit());
                                        for(int i =0;i<mm.c.getunits();i++){
                                            if(p.m.getid().equals(mm.getid())){
                                                try{
                                                    String deletefromproduct = "UPDATE PRODUCT SET PRO_UNITS = PRO_UNITS - '"+p.getunit()+"' WHERE PRO_ID = '"+p.getid()+"'";
                                                    System.out.println(deletefromproduct);
                                                    pat = getcon().prepareStatement(deletefromproduct);
                                                    pat.executeUpdate(deletefromproduct);
                                                    pat.close();
                                                    getcon().close();
                                                }catch(Exception e){
                                                    System.err.println(e);
                                                }
                                            System.out.print(p.getIngredient_ID()+" ");
                                            System.out.print(p.m.getid()+" ");
                                            System.out.print(p.getid()+" ");
                                            System.out.print(p.getunit()+"\n");
                                                for(Stock_Variable s:Stock_Array){
                                                    if(s.getproductid().equals(p.getid())){
                                                        if(s.getstockunits()-p.getunit()<0){
                                                            stock_units = p.getunit()-s.getstockunits();
                                                            s.setstockunits(0);
                                                            System.out.println("-"+stock_units);
                                                                for(Stock_Variable ss:Stock_Array){
                                                                    if(ss.getproductid().equals(p.getid())&&ss.getstockunits()>0){
                                                                        ss.setstockunits(ss.getstockunits()-stock_units);
                                                                        stock_units = 0;
                                                                        break;
                                                                    }
                                                                }
                                                        }else{
                                                            s.setstockunits(s.getstockunits()-p.getunit());
                                                        }
                                                        System.out.print(s.getstocknumber()+" ");
                                                        System.out.print(s.getproductid()+" ");
                                                        System.out.print(s.getstockexpdate()+" ");
                                                        System.out.print(s.getstockstartdate()+" ");
                                                        System.out.print(s.getstockunits()+"\n");
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                        }
                                    }
                            }
                        }
                    }
                }
            Ordering_List_Array.clear();
            getorder_menu();
            order_menu_id.clear();
            setStock();
            Stock_Array.clear();
            getStock();
            DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
            while(model.getRowCount()>0){
                model.removeRow(0);
            }
            showorder_menu();
            doubleclick = "";
            }else{
              doubleclick = "";  
            }
            }
            }else{
                JOptionPane.showMessageDialog(null,"ออเดอร์นี้ได้รับสินค้าครบแล้ว");
                doubleclick = "";
            }
        }else{
            doubleclick = menu_table.getValueAt(menu_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_menu_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for(Stock_Variable p:Stock_Units){
            System.err.print(p.getstocknumber()+"-");
            System.err.print(p.getproductid()+" ");
            System.err.print(p.getstockexpdate()+" ");
            System.err.print(p.getstockstartdate()+" ");
            System.err.print(p.getstockunits()+"\n");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        for(Stock_Variable p:Stock_Array){
            System.err.print(p.getstocknumber()+"*");
            System.err.print(p.getproductid()+" ");
            System.err.print(p.getstockexpdate()+" ");
            System.err.print(p.getstockstartdate()+" ");
            System.err.print(p.getstockunits()+"\n");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        Table_panel p = new Table_panel();
        p.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Customer_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menu_table;
    private javax.swing.JLabel serve_txt;
    private javax.swing.JLabel tableid_label;
    private javax.swing.JLabel tableid_txt;
    // End of variables declaration//GEN-END:variables
}
