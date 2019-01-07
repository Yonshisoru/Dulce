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
import static javax.swing.JOptionPane.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Product_Panel extends javax.swing.JFrame {
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
//--------------------------------------------------
    ArrayList<Product_variable> Product_Array= new ArrayList<>();    
    ArrayList<Product_variable> Product_Unit_Array= new ArrayList<>();
    ArrayList<Vendor_variable> Vendor_Array= new ArrayList<>();
    ArrayList<Product_variable> Product_List_Array= new ArrayList<>();
//-----------------------------------------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
//------------------------------------------
    String output = null;
    String ven = null;
    String password= null;
    String createid = null;
    public String id = null;
//---------------------------------------------
    int max = 0;
//--------------------------------------------
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    boolean pass = false;
    /**
     * Creates new form Employee_create
     */
    public Product_Panel() {
        initComponents();
        ProductList();
        show_product();
        getProduct_type();
        setProduct_type_combo();
        getProduct_unit_type();
        setProduct_unit_type();
        getVendor();
        setVendor();
        id();
    }
    public void clear(){
         product_type_combo.removeAllItems();
         product_type_combo.addItem(">>Choose Product Type<<");
         Product_List_Array.clear();
         getProduct_type();
         setProduct_type_combo();
         unit_combo.removeAllItems();
         unit_combo.addItem(">>Choose Units Type<<");
         Product_Unit_Array.clear();
         getProduct_unit_type();
         setProduct_unit_type();
         pro_name_txt.setText("");
         v_txt.setSelectedIndex(0);
         pro_price_txt.setText("");
         pro_min_txt.setText("");
    }
    public void lock(){
         showid_txt.setText("NAN");
         product_type_combo.setEnabled(false);
         unit_combo.setEnabled(false);
         pro_name_txt.setEnabled(false);
         v_txt.setEnabled(false);
         pro_price_txt.setEnabled(false);
         pro_min_txt.setEnabled(false);
    }
    public void unlock(){
         product_type_combo.setEnabled(true);
         unit_combo.setEnabled(true);
         pro_name_txt.setEnabled(true);
         v_txt.setEnabled(true);
         pro_price_txt.setEnabled(true);
         pro_min_txt.setEnabled(true);
    }
public Connection getcon(){
    try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
    }catch(Exception e){
        System.err.println(e);
    }
    return con;
}
public String find(){
            String find = "SELECT V_ID,V_NAME FROM VENDOR";
        try{
        pat = getcon().prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
            if(v_txt.getSelectedItem().toString().equals(rs.getString("V_NAME"))){
                ven = rs.getString("V_ID");
                System.out.print(ven);
            }
        }
        rs.close();
        pat.close();
        getcon().close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return ven;
  }
    public String findProduct_Unit_ID(){
    int count=0;
    max = 0;
          String sql  ="select PRO_UNITS_TYPE from PRODUCT_UNIT_LIST";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PRO_UNITS_TYPE"))>max){
            max = Integer.parseInt(rs.getString("PRO_UNITS_TYPE"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "00"+max;
    }else if(max<100){
        output = "0"+max;
    }
    System.out.println(output);
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   } 
  public String id(){
       int count=0;
       max = 0;
          String sql  ="select PRO_ID from PRODUCT";
    try{
    pat = getcon().prepareStatement(sql);
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
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
    void getVendor(){
      try{
          String sql = "SELECT V_ID,V_NAME FROM VENDOR WHERE V_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Vendor_variable v = new Vendor_variable();
            v.setid(rs.getString("V_ID"));
            v.setname(rs.getString("V_NAME"));
            Vendor_Array.add(v);
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){
          
      }
  }
    void getProduct_Unit_Type_Array(String name){
        int count = 0;
      for(Product_variable p :Product_Unit_Array){
          count ++;
          if(p.getunit_type().equals(name)){
              unit_combo.setSelectedIndex(count);
              break;
          }
          /*if(i!=0){
              System.out.println(unit_combo.getSelectedItem().toString());
          if(unit_combo.getItemAt(i).equals(name)){
              unit_combo.setSelectedIndex(i);
              break;
          }
          }*/
      }
  }
    void setVendor(){
      for(Vendor_variable v:Vendor_Array){
          v_txt.addItem(v.getname());
      }
  }

    void getProduct_type(){
      try{
          String sql = "SELECT PRO_LIST_ID,PRO_LIST_NAME FROM PRODUCT_LIST WHERE PRO_LIST_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Product_variable p = new Product_variable();
            p.setProduct_type_ID(rs.getString("PRO_LIST_ID"));
            p.setProduct_type(rs.getString("PRO_LIST_NAME"));
            Product_List_Array.add(p);
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){
          
      }
    }
    void getProduct_unit_type(){
      try{
        String sql = "SELECT PRO_UNITS_TYPE,PRO_UNITS_TYPE_NAME FROM PRODUCT_UNIT_LIST WHERE PRO_UNITS_TYPE_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Product_variable p = new Product_variable();
            p.setunits_type(rs.getString("PRO_UNITS_TYPE"));
            p.setunits_type_name(rs.getString("PRO_UNITS_TYPE_NAME"));
            Product_Unit_Array.add(p);
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){
          
      }
    }
    void setProduct_unit_type(){
        for(Product_variable p:Product_Unit_Array){
            unit_combo.addItem(p.getunit_type_name());
        }
    }
    void setProduct_type_combo(){
        for(Product_variable p:Product_List_Array){
            System.out.println(p.getProduct_type());
            product_type_combo.addItem(p.getProduct_type());
        }
    }
   public void ProductList(){
        try{
        String sql  ="select PRO_ID,V_ID,V_NAME,PRO_NAME,PRO_PRICE,PRO_UNITS_TYPE,PRO_UNITS,PRO_UNITS_TYPE_NAME,PRO_LIST_ID,PRO_MIN FROM PRODUCT NATURAL JOIN VENDOR NATURAL JOIN PRODUCT_UNIT_LIST WHERE PRO_DEL = 'N' ORDER BY PRO_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Product_variable p = new Product_variable();
            p.setid(rs.getString("PRO_ID"));
            p.setvid(rs.getString("V_ID"));
            p.setvname(rs.getString("V_NAME"));
            p.setname(rs.getString("PRO_NAME"));
            p.setprice(rs.getInt("PRO_PRICE"));
            p.setunit(rs.getInt("PRO_UNITS"));
            p.setunits_type(rs.getString("PRO_UNITS_TYPE"));
            p.setunits_type_name(rs.getString("PRO_UNITS_TYPE_NAME"));
            p.setmin(rs.getInt("PRO_MIN"));
            p.setProduct_type_ID(rs.getString("PRO_LIST_ID"));
            Product_Array.add(p);
        }
        rs.close();
        st.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
}
    public void show_product(){
        ArrayList<Product_variable>ProductList = Product_Array;
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        Object[] row = new Object[7];
        for(int i=0;i<ProductList.size();i++){
            row[0]=ProductList.get(i).getid();
            row[1]=ProductList.get(i).getname();
            row[2]=ProductList.get(i).getprice();
            row[3]=ProductList.get(i).getunit();
            row[4]=ProductList.get(i).getunit_type_name();
            row[5]=ProductList.get(i).getmin();
            row[6]=ProductList.get(i).getvname();
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
        jLabel16 = new javax.swing.JLabel();
        unit_combo = new javax.swing.JComboBox<>();
        product_type_combo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Unit_type_btn = new javax.swing.JLabel();
        Product_type_btn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสินค้า", "ชื่อสินค้า", "ราคา", "จำนวน", "หน่วยของสินค้า", "จำนวนขั้นต่ำ", "ผู้จัดจำหน่าย"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 110, 30));
        getContentPane().add(pro_name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 220, 30));

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
        getContentPane().add(pro_price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 90, 30));
        getContentPane().add(pro_min_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 90, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("รหัสวัตถุดิบ:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("ชื่อวัตถุดิบ:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ราคาต่อหน่วย:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("จำนวนขั้นต่ำที่ต้องมีในคลัง:");
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

        v_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose Vendor<<" }));
        getContentPane().add(v_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 140, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("ผู้จัดจำหน่าย:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

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

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("หน่วยปริมาตร:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        unit_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose Units Type<<" }));
        unit_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unit_comboActionPerformed(evt);
            }
        });
        getContentPane().add(unit_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 170, 30));

        product_type_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose Product Type<<" }));
        getContentPane().add(product_type_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 160, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ประเภทของวัตถุดิบ:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        Unit_type_btn.setForeground(new java.awt.Color(0, 0, 255));
        Unit_type_btn.setText("สร้างหน่วยปริมาตรคลิ๊กที่นี้");
        Unit_type_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Unit_type_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Unit_type_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Unit_type_btnMouseExited(evt);
            }
        });
        getContentPane().add(Unit_type_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, -1, -1));

        Product_type_btn.setForeground(new java.awt.Color(0, 0, 255));
        Product_type_btn.setText("สร้างประเภทของวัตถุดิบคลิ๊กที่นี้");
        Product_type_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Product_type_btnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Product_type_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Product_type_btnMouseExited(evt);
            }
        });
        getContentPane().add(Product_type_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        String producttypeid = null;
        String vendor = null;
        if(editnaja==true||deletenaja==true){
        showid_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString());
        vendor = product_table.getModel().getValueAt(product_table.getSelectedRow(),6).toString();
        for(Product_variable p:Product_Array){
            if(product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString().equals(p.getid())){
                producttypeid = p.getProduct_type_ID();
                getProduct_Unit_Type_Array(p.getunit_type());
                System.out.println(p.getid());
                System.out.println(producttypeid);
                break;
            }
        }
        int count = 0;
        for(Product_variable p:Product_List_Array){
            count ++;
            if(p.getProduct_type_ID().equals(producttypeid)){
                product_type_combo.setSelectedIndex(count);
                break;
            }
        }
        count = 0;
        for(Vendor_variable v:Vendor_Array){
            count++;
            if(v.getname().equals(vendor)){
                v_txt.setSelectedIndex(count);
                break;
            }
        }
        /*for(Product_variable p:Product_List_Array){
            if()
        }*/
        pro_name_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),1).toString());
        pro_price_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),2).toString());
        pro_min_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),5).toString());
        }
        if(deletenaja==true){
            JOptionPane.showMessageDialog(null,"คุณได้เลือกสินค้ารหัส "+product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString());
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String id = showid_txt.getText();
        String proname = pro_name_txt.getText();
        String vendor = find();
        String price = pro_price_txt.getText();
        String min = pro_min_txt.getText();
        String unit_type_id = null;
        String product_type = null;
        for(Product_variable p:Product_Unit_Array){
            System.out.print(p.getunit_type_name());
            System.err.print(unit_combo.getSelectedItem().toString()+"\n");
            if(p.getunit_type_name().equals(unit_combo.getSelectedItem().toString())){
                unit_type_id = p.getunit_type();
                break;
            }
        }
        for(Product_variable p :Product_List_Array){
            System.out.print(p.getProduct_type());
            System.err.print(product_type_combo.getSelectedItem().toString()+"\n");
            if(p.getProduct_type().equals(product_type_combo.getSelectedItem().toString())){
                product_type = p.getProduct_type_ID();
            }
        }
        if(createnaja==true){
        String createproduct = "INSERT INTO PRODUCT VALUE('"+id+"','"+vendor+"','"+proname+"','"+price+"','0','"+unit_type_id+"','"+min+"','"+product_type+"','N')";  
        System.out.print(createproduct);
        try{
        pat = getcon().prepareStatement(createproduct);
        pat.executeUpdate(createproduct);
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
        Product_Array.clear();
        ProductList();
        show_product();
        id();
        clear();
        }else if(editnaja==true){
            String edit = "UPDATE PRODUCT SET V_ID = '"+vendor+"',PRO_NAME = '"+proname+"',PRO_PRICE = '"+price+"',PRO_UNITS_TYPE = '"+unit_type_id+"',PRO_MIN = '"+min+"',PRO_LIST_ID = '"+product_type+"' WHERE PRO_ID = '"+id+"'";  
            System.out.print(edit);
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(edit);
                pat.executeUpdate(edit);
                pat.close();
                clear();
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
       DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        Product_Array.clear();
        ProductList();
        show_product();
        clear();
        /*}else if(deletenaja==true){
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
        show_product();*/
        }else if(editnaja==true){
            
        }else if(deletenaja==true){
            if(JOptionPane.showConfirmDialog(null,"คุณมั่นใจที่จะลบสินค้ารัหัส "+id+" จริงหรือไม่",null,YES_NO_OPTION)==YES_OPTION){
            
            String deleteproduct = "UPDATE PRODUCT SET PRO_DEL = 'Y' WHERE PRO_ID = '"+id+"'";
            String deletestock = "UPDATE STOCK SET STOCK_DEL = 'Y' WHERE PRO_ID = '"+id+"'";
            try{
                System.out.println(deleteproduct);
                pat = getcon().prepareStatement(deleteproduct);
                pat.executeUpdate(deleteproduct);
                pat.close();
                    System.out.println(deletestock);
                    pat = getcon().prepareStatement(deletestock);
                    pat.executeUpdate(deletestock);
                    pat.close();
                    getcon().close();
            DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
            while(dm.getRowCount() > 0)
            {       
            dm.removeRow(0);
            }
            Product_Array.clear();
            ProductList();
            show_product();
            clear();
            showid_txt.setText("NAN");
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            JOptionPane.showMessageDialog(null,"ยกเลิกรายการเรียบร้อยแล้ว");   
            }
        }
        JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
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
        showid_txt.setText(createid);
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
        showid_txt.setText("NAN");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void unit_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unit_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unit_comboActionPerformed

    private void Unit_type_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Unit_type_btnMouseClicked
       Product_Units_Type_Panel p = new Product_Units_Type_Panel();
       p.setVisible(true);
    }//GEN-LAST:event_Unit_type_btnMouseClicked

    private void Unit_type_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Unit_type_btnMouseEntered
        Unit_type_btn.setForeground(Color.white);
        Unit_type_btn.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_Unit_type_btnMouseEntered

    private void Unit_type_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Unit_type_btnMouseExited
        Unit_type_btn.setForeground(Color.blue);
        Unit_type_btn.setCursor(new Cursor(DEFAULT_CURSOR));
    }//GEN-LAST:event_Unit_type_btnMouseExited

    private void Product_type_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_type_btnMouseClicked
        Product_Type_Panel p = new Product_Type_Panel();
        p.setVisible(true);
    }//GEN-LAST:event_Product_type_btnMouseClicked

    private void Product_type_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_type_btnMouseEntered
        Product_type_btn.setForeground(Color.white);
        Product_type_btn.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_Product_type_btnMouseEntered

    private void Product_type_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Product_type_btnMouseExited
       Product_type_btn.setForeground(Color.blue);
       Product_type_btn.setCursor(new Cursor(DEFAULT_CURSOR));
    }//GEN-LAST:event_Product_type_btnMouseExited

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
            java.util.logging.Logger.getLogger(Product_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Product_Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Product_type_btn;
    private javax.swing.JLabel Unit_type_btn;
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JRadioButton edit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JTable product_table;
    private javax.swing.JComboBox<String> product_type_combo;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JComboBox<String> unit_combo;
    private javax.swing.JComboBox<String> v_txt;
    // End of variables declaration//GEN-END:variables
}
