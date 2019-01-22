
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Customer_Claim_Report extends javax.swing.JFrame {
Table_variable t = new Table_variable();
Database d = new Database();
ArrayList<Menu_variable>Ordering_Array = new ArrayList<>();
ArrayList<Menu_variable>Ordering_List_Array = new ArrayList<>();
ArrayList<Stock_Variable>Stock_Array = new ArrayList<>();
ArrayList<Stock_Variable>Stock_Units = new ArrayList<>();
ArrayList<Product_variable>Menu_Ingredient_Array = new ArrayList<>();
ArrayList<Menu_variable>Menu_Array = new ArrayList<>();
ArrayList<Menu_variable>Menu_List_Array = new ArrayList<>();
//----------------------------
Connection con = null;
PreparedStatement pat = null;
ResultSet rs = null;
//----------------------------
int max = 0;
//--------------------------------
double stock_units = 0.0;
//------------------------------
String output = "";

    public Customer_Claim_Report() {
        initComponents();
        getMenu();
        getStock();
        getIngredient();
        getorderid();
        getorder_menu();
        order_list_id_txt.setText(t.getorder_list());
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
public String getreportid(){
    int count=0;
    max = 0;
    String sql  ="select REP_NUMBER from REP_ORDER";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("REP_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("REP_NUMBER"));
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        order_list_id_label = new javax.swing.JLabel();
        order_list_id_txt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text_txt = new javax.swing.JTextArea();
        order_list_id_label1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(530, 380));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        order_list_id_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        order_list_id_label.setText("สาเหตุการเคลม:");
        getContentPane().add(order_list_id_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        order_list_id_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(order_list_id_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 90, 30));

        text_txt.setColumns(20);
        text_txt.setRows(5);
        jScrollPane1.setViewportView(text_txt);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 230, 140));

        order_list_id_label1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        order_list_id_label1.setText("รหัสเมนู:");
        getContentPane().add(order_list_id_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jButton1.setText("ปิด");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 100, 50));

        jButton2.setText("ยืนยัน");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 100, 50));

        jButton3.setText("เคลียร์");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 100, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.setVisible(false);
       Customer_Claim c = new Customer_Claim();
       c.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       this.setVisible(false);
       Customer_Claim c = new Customer_Claim();
       c.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        text_txt.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String detail = text_txt.getText();
        String table = t.getid();
        String date = LocalDate.now().toString()+" "+LocalTime.now().toString().substring(0,8);
        try{
            String sql = "INSERT INTO REP_ORDER VALUES('"+getreportid()+"','"+t.getorder_list()+"','"+date+"','"+detail+"','N')";
            System.out.println(sql);
            pat = getcon().prepareCall(sql);
            pat.executeUpdate(sql);
            pat.close();
for(Menu_variable mm:Ordering_List_Array){
                    if(mm.c.getorder_menu_id().equals(t.getorder_list())){
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
            setStock();
            JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
            this.setVisible(false);
            Customer_Claim c = new Customer_Claim();
            c.setVisible(true);
        }catch(Exception e){
            System.out.println(e);
        }
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Claim_Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_Claim_Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel order_list_id_label;
    private javax.swing.JLabel order_list_id_label1;
    private javax.swing.JLabel order_list_id_txt;
    private javax.swing.JTextArea text_txt;
    // End of variables declaration//GEN-END:variables
}
