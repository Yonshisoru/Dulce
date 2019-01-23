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
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Waste_panel extends javax.swing.JFrame {
ArrayList<Stock_Variable>Stock_Array = new ArrayList<>();
ArrayList<Stock_Variable>Stock_Using_Array = new ArrayList<>();
ArrayList<Stock_Variable>Waste_List_Array = new ArrayList<>();
//-----------------------------------------
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    Waste_variable w = new Waste_variable();
//------------------------------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
//------------------------------------------------------
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    public String id = null;
    String output = null;
    String createid = null;
    String doubleclick = "";
    double title = 0.0;
    double totalall = 0.0;
    int max = 0;
    int count = 0;
    /**
     * Creates new form Employee_create
     */
    public Waste_panel() {
        initComponents();
        getwasteid();
        getStock();
        showStock();
        System.out.println(e.getshowid());
    }
    public void clear(){
        showid_txt.setText(createid);
        totalall=0;
        total_txt.setText("0.00");
        Stock_Array.clear();
        Stock_Using_Array.clear();
        getStock();
                DefaultTableModel model1 = (DefaultTableModel)stock_table.getModel();
                while(model1.getRowCount()>0){
                    model1.removeRow(0);
                }
                showStock();
                DefaultTableModel model2 = (DefaultTableModel)waste_stock_table.getModel();
                while(model2.getRowCount()>0){
                    model2.removeRow(0);
                }
                showStock_Using();
        waste_stock_table.getSelectionModel().clearSelection();
        stock_table.getSelectionModel().clearSelection(); 
    }
    public Connection getcon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(d.url(),d.username(),d.password());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Didn';t connect");
            throw new RuntimeException(e);
        }
    }
  public String getwaste_list_id(){
       int count=0;
          String sql  ="select WL_NUMBER from WASTE_LIST";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("WL_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("WL_NUMBER"));
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
  public String getwasteid(){
       int count=0;
       max = 0;
          String sql  ="select W_ID from WASTE";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("W_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("W_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "W00"+max;
    }else if(max<100){
        output = "W0"+max;
    }else{
        output = "W"+max;
    }
    showid_txt.setText(output);
    createid = output;
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
   public void getStock(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select STOCK_NUMBER,PRO_ID,PRO_NAME,PRO_PRICE,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID FROM STOCK NATURAL JOIN PRODUCT WHERE STOCK_DEL = 'N' ORDER BY STOCK_NUMBER";
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Stock_Variable s = new Stock_Variable();
            s.setstocknumber(rs.getInt("STOCK_NUMBER"));
            s.setproductid(rs.getString("PRO_ID"));
            s.setproductname(rs.getString("PRO_NAME"));
            s.p.setprice(rs.getInt("PRO_PRICE"));
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
    public void showStock(){
        DefaultTableModel model = (DefaultTableModel)stock_table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<Stock_Array.size();i++){
            row[0]=Stock_Array.get(i).getstocknumber();
            row[1]=Stock_Array.get(i).getproductname();
            row[3]=Stock_Array.get(i).getstockunits();
            row[2]=Stock_Array.get(i).p.getprice();
            row[4]=Stock_Array.get(i).getstockstartdate();
            row[5]=Stock_Array.get(i).getstockexpdate();
            model.addRow(row);
        }
    }
    public void showStock_Using(){
        DefaultTableModel model = (DefaultTableModel)waste_stock_table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<Stock_Using_Array.size();i++){
            row[0]=Stock_Using_Array.get(i).getstocknumber();
            row[1]=Stock_Using_Array.get(i).getproductname();
            row[2]=Stock_Using_Array.get(i).p.getprice();
            row[3]=Stock_Using_Array.get(i).getstockunits();
            row[4]=Stock_Using_Array.get(i).p.gettotal_price();
            row[5]=Stock_Using_Array.get(i).getdetail();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        waste_stock_table = new javax.swing.JTable();
        showid_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        stock_table = new javax.swing.JTable();
        total_txt = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1420, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        waste_stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสต๊อก", "ชื่อสินค้า", "ราคาต่อหน่วย", "จำนวนที่ทิ้ง", "มูลค่าทั้งหมด", "สาเหตุการทิ้ง"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        waste_stock_table.getTableHeader().setReorderingAllowed(false);
        waste_stock_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waste_stock_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(waste_stock_table);
        if (waste_stock_table.getColumnModel().getColumnCount() > 0) {
            waste_stock_table.getColumnModel().getColumn(0).setPreferredWidth(80);
            waste_stock_table.getColumnModel().getColumn(1).setPreferredWidth(200);
            waste_stock_table.getColumnModel().getColumn(2).setPreferredWidth(80);
            waste_stock_table.getColumnModel().getColumn(3).setPreferredWidth(80);
            waste_stock_table.getColumnModel().getColumn(4).setPreferredWidth(80);
            waste_stock_table.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 700, 520));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 120, 50));

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 120, 50));

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 120, 50));

        stock_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสต๊อก", "ชื่อสินค้า", "ราคาต่อหน่วย", "จำนวน", "วันรับเข้า", "วันหมดอายุ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stock_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stock_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(stock_table);
        if (stock_table.getColumnModel().getColumnCount() > 0) {
            stock_table.getColumnModel().getColumn(0).setResizable(false);
            stock_table.getColumnModel().getColumn(0).setPreferredWidth(50);
            stock_table.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 110, 610, 210));

        total_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        total_txt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total_txt.setText("0.00");
        getContentPane().add(total_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 590, 200, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("มูลค่ารวม:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 590, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("บาท");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 590, -1, -1));

        jButton5.setText("?");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 40, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void waste_stock_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waste_stock_tableMouseClicked

    }//GEN-LAST:event_waste_stock_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(Stock_Using_Array.isEmpty()==true){
            JOptionPane.showMessageDialog(null,"คุณยังไม่ได้ใส่สินค้าที่ต้องการจะทิ้ง กรุณาทำรายการใหม่ด้วยครับ",null,ERROR_MESSAGE);
            clear();
        }else{
            String date = LocalDate.now().toString()+" "+LocalTime.now().toString().substring(0,8);
            String createwaste = "INSERT WASTE VALUES('"+createid+"','"+Stock_Using_Array.size()+"','"+date+"','"+totalall+"','"+e.getshowid()+"','N')";
            //System.out.println(createwaste);
            try{
                pat = getcon().prepareStatement(createwaste);
                pat.executeUpdate(createwaste);
                pat.close();
                getcon().close();
                for(Stock_Variable s:Stock_Using_Array){
                    String createwastelist = "INSERT WASTE_LIST VALUES('"+getwaste_list_id()+"','"+s.getstockunits()+"','"+s.getdetail()+"','"+s.p.gettotal_price()+"','"+s.getproductid()+"','"+createid+"','N')";
                    String updateproduct = "UPDATE PRODUCT SET PRO_UNITS = PRO_UNITS-'"+s.getstockunits()+"' WHERE PRO_ID = '"+s.getproductid()+"'";
                    String updatestock = "UPDATE STOCK SET STOCK_UNITS = STOCK_UNITS-'"+s.getstockunits()+"' WHERE STOCK_NUMBER = '"+s.getstocknumber()+"'";
                    //System.out.println(createwastelist);
                    //System.err.println(updateproduct);
                    //System.out.println("+"+updatestock);
                    try{
                        pat = getcon().prepareStatement(createwastelist);
                        pat.executeUpdate(createwastelist);
                        pat.close();
                            pat = getcon().prepareStatement(updateproduct);
                            pat.executeUpdate(updateproduct);
                            pat.close();
                                pat = getcon().prepareStatement(updatestock);
                                pat.executeUpdate(updatestock);
                                pat.close();
                        getcon().close();
                    }catch(Exception e){
                        System.out.println(e);
                    }
                boolean delete = false;
                String find = "SELECT STOCK_UNITS FROM STOCK WHERE STOCK_DEL = 'N' AND STOCK_NUMBER = '"+s.getstocknumber()+"'";
                try{
                    pat = getcon().prepareStatement(find);
                    rs = pat.executeQuery(find);
                    while(rs.next()){
                        if(rs.getDouble("STOCK_UNITS")<=0){
                            delete=true;
                        }
                    }
                    rs.close();
                    pat.close();
                    if(delete==true){
                        String deletenaja = "UPDATE STOCK SET STOCK_DEL = 'Y' WHERE STOCK_NUMBER = '"+s.getstocknumber()+"'";
                        //System.out.println(delete);
                        try{
                            pat = getcon().prepareStatement(deletenaja);
                            pat.executeUpdate(deletenaja);
                            pat.close();
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    }
                    getcon().close();
                }catch(Exception e){
                    System.out.println(e);
                }
                
                }
            }catch(Exception e){
                System.out.println(e);
            }
            clear();
            JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
            this.setVisible(false);
            Waste w = new Waste();
            w.setVisible(true);   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            clear();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void stock_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stock_tableMouseClicked
        if(doubleclick.equals(stock_table.getValueAt(stock_table.getSelectedRow(),0).toString())){
            if(JOptionPane.showConfirmDialog(null,"คุณต้องการที่จะทิ้งวัตถุดิบในสต๊อกหมายเลขที่ "+doubleclick+" จริงหรือไม่",null,YES_NO_OPTION)==YES_OPTION){
                
            double input = 0;
            try{
                input = Double.parseDouble(JOptionPane.showInputDialog(null,"กรุณากรอกจำนวนที่ต้องการจะทิ้งด้วยครับ"));
                if(input<=0){
                    throw new NumberFormatException();
                }
                System.out.println(input);
                System.err.println(Double.parseDouble(stock_table.getValueAt(stock_table.getSelectedRow(),3).toString()));
                if(input>Double.parseDouble(stock_table.getValueAt(stock_table.getSelectedRow(),3).toString())){
                    throw new NumberFormatException();
                }
                String detail = JOptionPane.showInputDialog(null,"กรุณากรอกสาเหตุการทิ้งด้วยครับ");
                title = input;
                for(Stock_Variable s:Stock_Array){
                if(s.getstocknumber()==(Integer.parseInt(doubleclick))){
                Stock_Variable ss = new Stock_Variable();
                ss = Stock_Array.get(stock_table.getSelectedRow());
                ss.setdetail(detail);
                ss.setstockunits(title);
                ss.p.settotal_price(title*s.p.getprice());
                Stock_Using_Array.add(ss);
                Stock_Array.remove(s);
                totalall += s.p.getprice()*title;
                break;
                }
                }
                DefaultTableModel model1 = (DefaultTableModel)stock_table.getModel();
                while(model1.getRowCount()>0){
                    model1.removeRow(0);
                }
                showStock();
                DefaultTableModel model2 = (DefaultTableModel)waste_stock_table.getModel();
                while(model2.getRowCount()>0){
                    model2.removeRow(0);
                }
                showStock_Using();
                title = 0;
                total_txt.setText(""+totalall);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"คุณกรอกจำนวนไม่ถูกต้อง\nกรุณาทำรายการใหม่ครับ");
            }
            
            }else{
                doubleclick = "";
            }
        }else{
            doubleclick = stock_table.getValueAt(stock_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_stock_tableMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(count==0){
            count = 1;
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กที่ตารางสต๊อกเพื่อเลือกวัตถุดิบที่จะทิ้งได้ครับ");
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กที่ตารางสต๊อกเพื่อเลือกวัตถุดิบที่จะทิ้งได้ครับ");
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(Waste_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Waste_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Waste_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Waste_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Waste_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JTable stock_table;
    private javax.swing.JLabel total_txt;
    private javax.swing.JTable waste_stock_table;
    // End of variables declaration//GEN-END:variables
}
