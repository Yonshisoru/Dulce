import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.StyleConstants.Bold;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Customer_Payment extends javax.swing.JFrame {
Database d = new Database();
Table_variable t = new Table_variable();
Employee e = new Employee();
Customer_variable c= new Customer_variable();
//-------------------------------------------------
ArrayList<Menu_variable>Menu_Array = new ArrayList<>();
ArrayList<Menu_variable>Menu_List_Array = new ArrayList<>();
ArrayList<Menu_variable>Ordering_Array = new ArrayList<>();
ArrayList<Menu_variable>Order_List_Array = new ArrayList<>();
//-----------------------------------------------------------
Connection con = null;
PreparedStatement pat = null;
ResultSet rs = null;
//-------------------------------------------------------------
String output = "";
String orderid = null;
//---------------------------------------------------------------
int count = 1;
int amount = 1;
int max = 0;
int countafterdot = 2;
//------------------------------------------------------------------
boolean pro_using = false;
boolean firstdigit = false;
boolean dot = false;
//----------------------------------------------
double incame = 0.0;
double price = 0.0;
double total = 0.0;
/********************************Font***************************************/
Font TopFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
Font Topic = new Font(Font.FontFamily.COURIER,24,Font.BOLD);
Font Bold = new Font(Font.FontFamily.COURIER,18,Font.BOLD);
Font normal = new Font(Font.FontFamily.COURIER,15,Font.NORMAL);
/***************************************************************************/
/**
     * Creates new form Customer_Payment
     */
    public Customer_Payment() {
        getMenu();
        getorder();
        getorder_menu();
        initComponents();
        locknumberic();
        setorder_table(t.getid());
        price_txt.setText(""+price);
        table_txt.setText(t.getid());
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
public void locknumberic(){
                one_btn.setEnabled(false);
                two_btn.setEnabled(false);
                three_btn.setEnabled(false);
                four_btn.setEnabled(false);
                five_btn.setEnabled(false);
                six_btn.setEnabled(false);
                seven_btn.setEnabled(false);
                eight_btn.setEnabled(false);
                nine_btn.setEnabled(false);
                zero_btn.setEnabled(false);
                point_btn.setEnabled(false);
                blank.setEnabled(false);
                pay_btn.setEnabled(false);
}
public void unlocknumberic(){
                    one_btn.setEnabled(true);
                two_btn.setEnabled(true);
                three_btn.setEnabled(true);
                four_btn.setEnabled(true);
                five_btn.setEnabled(true);
                six_btn.setEnabled(true);
                seven_btn.setEnabled(true);
                eight_btn.setEnabled(true);
                nine_btn.setEnabled(true);
                zero_btn.setEnabled(true);
                point_btn.setEnabled(true);
                blank.setEnabled(true);
                pay_btn.setEnabled(true);
                change_txt.setVisible(true);
                change_label.setVisible(true);
                income_label.setVisible(true);
                income_txt.setVisible(true);
}
public void firstdigits(String id){
    if(firstdigit == false&&(id.equals("0")==false)){
            income_txt.setText("");
            firstdigit=true;
        }
}
public void clear(){
    incame = 0;
    income_txt.setText("0");
    total = 0;
    change_txt.setText("0.00");
}
public void setorder_table(String table){
    String orderid = null;
    Object[] row = new Object[6];
    DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        for(Menu_variable m:Ordering_Array){
            if(m.c.gettable().equals(table)&&m.c.getpaytype().isEmpty()==true){
                orderid = m.c.getorderid();
                for(Menu_variable mm:Order_List_Array){
                    if(mm.c.getorderid().equals(orderid)){
                        row[0] = amount;
                        row[1] = mm.getname();
                        row[2] = mm.c.gettotal();
                        row[3] = mm.c.getunits();
                        double eiei = (double)mm.c.gettotal()*(double)mm.c.getunits();
                        row[4] = eiei;
                        if(mm.p.getname().equals("null")){
                           row[5] = ""; 
                        }else{
                        row[5] = mm.p.getname();
                        }
                        model.addRow(row);
                        price += eiei;
                    }
                }
                }
        }
}
public void getorder(){
        String sql = "Select ORD_ID,EMP_ID,T_ID,ORD_DATE,ORD_TOTAL,ORD_PAYTYPE FROM ORDERING WHERE ORD_DEL = 'N'";
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
        String sql = "Select OM_ID,ORD_ID,MENU_ID,OM_UNITS,OM_PRICE,OM_PROMOTION,OM_STATUS FROM ORDER_MENU_LIST WHERE OM_DEL = 'N'";
        //System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Menu_variable m = new Menu_variable();
                m.c.setorder_menu_id(rs.getString("OM_ID"));
                m.c.setorderid(rs.getString("ORD_ID"));
                m.setid(rs.getString("MENU_ID"));
                for(Menu_variable mm:Menu_Array){
                    if(mm.getid().equals(m.getid())){
                        m.setname(mm.getname());
                        break;
                    }
                }
                m.c.setunits(rs.getInt("OM_UNITS"));
                m.c.settotal(rs.getDouble("OM_PRICE"));
                m.p.setname(rs.getString("OM_PROMOTION")); 
                m.c.setorder_menu_status(rs.getString("OM_STATUS"));
                Order_List_Array.add(m);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            
        }
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
    public void printInvoice(){
        String time = LocalTime.now().toString().substring(0,2)+"-"+LocalTime.now().toString().substring(3,5)+"-"+LocalTime.now().toString().substring(6,8);
        String filename = "$"+LocalDate.now()+"$"+time+"$"+t.getorderid()+".pdf";
        try{
        Document doc = new Document();
        PdfWriter.getInstance(doc,new FileOutputStream("Invoice/"+filename));
        doc.open();
         doc.add(new Phrase(String.format("%38s","Date: "),Bold));
        doc.close();
}catch (DocumentException ex){
    Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE,null,ex);
}      catch (FileNotFoundException ex) {
           Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE, null, ex);
       }
try{
        Desktop.getDesktop().open(new File("./Invoice/"+filename));
                }catch(Exception e){
                    System.out.println(e);
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

        jLabel1 = new javax.swing.JLabel();
        table_txt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menu_table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        income_label = new javax.swing.JLabel();
        paytype_combo = new javax.swing.JComboBox<>();
        change_label = new javax.swing.JLabel();
        income_txt = new javax.swing.JTextField();
        change_txt = new javax.swing.JLabel();
        price_txt = new javax.swing.JLabel();
        point_btn = new javax.swing.JButton();
        one_btn = new javax.swing.JButton();
        two_btn = new javax.swing.JButton();
        close_btn = new javax.swing.JButton();
        four_btn = new javax.swing.JButton();
        five_btn = new javax.swing.JButton();
        six_btn = new javax.swing.JButton();
        seven_btn = new javax.swing.JButton();
        eight_btn = new javax.swing.JButton();
        nine_btn = new javax.swing.JButton();
        zero_btn = new javax.swing.JButton();
        blank = new javax.swing.JButton();
        three_btn = new javax.swing.JButton();
        pay_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(852, 577));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ประเภทการจ่ายเงิน");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        table_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(table_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 23, 89, 30));

        menu_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รายการที่", "ชื่อเมนู", "ราคา", "จำนวน", "ราคาสุทธิ", "หมายเหตุ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(menu_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 650, 230));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("โต๊ะ :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 23, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("ราคารวมทั้งหมด:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        income_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        income_label.setText("รับเงิน:");
        getContentPane().add(income_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, -1, -1));

        paytype_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "เลือกประเภทการจ่ายเงิน", "เงินสด", "บัตรเครดิต" }));
        paytype_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paytype_comboActionPerformed(evt);
            }
        });
        getContentPane().add(paytype_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 150, 30));

        change_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        change_label.setText("เงินทอน:");
        getContentPane().add(change_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        income_txt.setEditable(false);
        income_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        income_txt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        income_txt.setText("0");
        income_txt.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                income_txtComponentAdded(evt);
            }
        });
        income_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                income_txtActionPerformed(evt);
            }
        });
        income_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                income_txtKeyTyped(evt);
            }
        });
        getContentPane().add(income_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 150, 30));

        change_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        change_txt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        change_txt.setText("0.00");
        getContentPane().add(change_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 140, 30));

        price_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        price_txt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        price_txt.setText("0.00");
        getContentPane().add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 140, 30));

        point_btn.setText(".");
        point_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                point_btnActionPerformed(evt);
            }
        });
        getContentPane().add(point_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, -1, 40));

        one_btn.setText("1");
        one_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                one_btnActionPerformed(evt);
            }
        });
        getContentPane().add(one_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, -1, 40));

        two_btn.setText("2");
        two_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_btnActionPerformed(evt);
            }
        });
        getContentPane().add(two_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, -1, 40));

        close_btn.setText("ปิด");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });
        getContentPane().add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, 70, 40));

        four_btn.setText("4");
        four_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                four_btnActionPerformed(evt);
            }
        });
        getContentPane().add(four_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, 40));

        five_btn.setText("5");
        five_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                five_btnActionPerformed(evt);
            }
        });
        getContentPane().add(five_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, 40));

        six_btn.setText("6");
        six_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                six_btnActionPerformed(evt);
            }
        });
        getContentPane().add(six_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, -1, 40));

        seven_btn.setText("7");
        seven_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seven_btnActionPerformed(evt);
            }
        });
        getContentPane().add(seven_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, -1, 40));

        eight_btn.setText("8");
        eight_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eight_btnActionPerformed(evt);
            }
        });
        getContentPane().add(eight_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, -1, 40));

        nine_btn.setText("9");
        nine_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nine_btnActionPerformed(evt);
            }
        });
        getContentPane().add(nine_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, -1, 40));

        zero_btn.setText("0");
        zero_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zero_btnActionPerformed(evt);
            }
        });
        getContentPane().add(zero_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, 40));
        getContentPane().add(blank, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 480, 40, 40));

        three_btn.setText("3");
        three_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                three_btnActionPerformed(evt);
            }
        });
        getContentPane().add(three_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, 40));

        pay_btn.setText("จ่ายเงิน");
        pay_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pay_btnActionPerformed(evt);
            }
        });
        getContentPane().add(pay_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 70, 40));

        clear_btn.setText("เคลียร์");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        getContentPane().add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, 70, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void income_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_income_txtKeyTyped

    }//GEN-LAST:event_income_txtKeyTyped

    private void income_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_income_txtActionPerformed

    }//GEN-LAST:event_income_txtActionPerformed

    private void income_txtComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_income_txtComponentAdded

    }//GEN-LAST:event_income_txtComponentAdded

    private void paytype_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paytype_comboActionPerformed
        if(paytype_combo.getSelectedIndex()!=0){
            if(paytype_combo.getSelectedIndex()==2){
                locknumberic();
                pay_btn.setEnabled(true);
                change_txt.setVisible(false);
                change_label.setVisible(false);
                income_label.setVisible(false);
                income_txt.setVisible(false);
            }else if(paytype_combo.getSelectedIndex()==1){
                unlocknumberic();
            }
        }
    }//GEN-LAST:event_paytype_comboActionPerformed

    private void one_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_one_btnActionPerformed
        firstdigits("1");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            System.out.println(income_txt.getText());
            income_txt.setText(income_txt.getText()+"1");
            countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"1");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_one_btnActionPerformed

    private void two_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_btnActionPerformed
        firstdigits("2");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"2");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"2");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_two_btnActionPerformed

    private void three_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_three_btnActionPerformed
        firstdigits("3");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"3");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"3");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_three_btnActionPerformed

    private void four_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_four_btnActionPerformed
        firstdigits("4");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"4");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"4");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_four_btnActionPerformed

    private void five_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_five_btnActionPerformed
        firstdigits("5");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"5");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"5");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_five_btnActionPerformed

    private void six_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_six_btnActionPerformed
        firstdigits("6");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"6");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"6");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_six_btnActionPerformed

    private void seven_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seven_btnActionPerformed
        firstdigits("7");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"7");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"7");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_seven_btnActionPerformed

    private void eight_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eight_btnActionPerformed
        firstdigits("8");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"8");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"8");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_eight_btnActionPerformed

    private void nine_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nine_btnActionPerformed
        firstdigits("9");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"9");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"9");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_nine_btnActionPerformed

    private void zero_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zero_btnActionPerformed
        firstdigits("0");
        if(income_txt.getText().length()<7){
        if(dot==true){
        if(countafterdot>0){
            income_txt.setText(income_txt.getText()+"0");
                    countafterdot--;
        }else{
            System.err.println("alert");
        }
        }else{
        income_txt.setText(income_txt.getText()+"0");
        }
        }
        incame = Double.parseDouble(income_txt.getText());
        total = incame - price;
        change_txt.setText(String.format("%.2f",total));
    }//GEN-LAST:event_zero_btnActionPerformed

    private void point_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_point_btnActionPerformed
        firstdigit = true;
        if(dot==false){
        income_txt.setText(income_txt.getText()+".");
        dot = true;
        }
    }//GEN-LAST:event_point_btnActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        firstdigit = false;
        dot = false;
        countafterdot = 2;
        clear();
    }//GEN-LAST:event_clear_btnActionPerformed

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
        this.setVisible(false);
        Table_panel t = new Table_panel();
        t.setVisible(true);
    }//GEN-LAST:event_close_btnActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Table_panel t = new Table_panel();
        t.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void pay_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pay_btnActionPerformed
        if(JOptionPane.showConfirmDialog(null,"ยืนยันการจ่ายเงิน",null,YES_NO_OPTION,WARNING_MESSAGE)==YES_OPTION){
            System.out.println(t.getorderid());
            String updateorder  ="UPDATE ORDERING SET ORD_PAYTYPE = '"+paytype_combo.getSelectedItem().toString()+"' WHERE ORD_ID = '"+t.getorderid()+"'";
            String updatetable = "UPDATE TABLEZ SET T_STATUS = 'N' WHERE T_ID = '"+t.getid()+"'";
            System.out.println(updateorder);
            System.out.println(updatetable);
            try{
                pat = getcon().prepareStatement(updateorder);
                pat.executeUpdate(updateorder);
                pat.close();
                    pat = getcon().prepareStatement(updatetable);
                    pat.executeUpdate(updatetable);
                    pat.close();
                getcon().close();
            }catch(Exception e){
                System.out.println(e);
            }
            this.setVisible(false);
            Table_panel p = new Table_panel();
            p.setVisible(true);
            JOptionPane.showMessageDialog(null,"ชำระเงินเสร็จสิ้น");
            printInvoice();
        }
    }//GEN-LAST:event_pay_btnActionPerformed

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
            java.util.logging.Logger.getLogger(Customer_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_Payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blank;
    private javax.swing.JLabel change_label;
    private javax.swing.JLabel change_txt;
    private javax.swing.JButton clear_btn;
    private javax.swing.JButton close_btn;
    private javax.swing.JButton eight_btn;
    private javax.swing.JButton five_btn;
    private javax.swing.JButton four_btn;
    private javax.swing.JLabel income_label;
    private javax.swing.JTextField income_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menu_table;
    private javax.swing.JButton nine_btn;
    private javax.swing.JButton one_btn;
    private javax.swing.JButton pay_btn;
    private javax.swing.JComboBox<String> paytype_combo;
    private javax.swing.JButton point_btn;
    private javax.swing.JLabel price_txt;
    private javax.swing.JButton seven_btn;
    private javax.swing.JButton six_btn;
    private javax.swing.JLabel table_txt;
    private javax.swing.JButton three_btn;
    private javax.swing.JButton two_btn;
    private javax.swing.JButton zero_btn;
    // End of variables declaration//GEN-END:variables
}
