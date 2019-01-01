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
public class Menu_panel extends javax.swing.JFrame {
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    Product_variable p = new Product_variable();
    //----------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    //------------------------
    ArrayList<Product_variable> productarray = new ArrayList<Product_variable>();
    ArrayList<Product_variable> productusing = new ArrayList<Product_variable>();
    //------------------------
    String output = null;
    String menu = null;
    String password= null;
    String createid = null;
    //------------------------
    int max = 0;
    //------------------------
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    boolean pass = false;
    //--------------------------
    public String id = null;
    /**
     * Creates new form Employee_create
     */
    public Menu_panel() {
        initComponents();
        show_product();
        id();
        fillcombo();
        fillproductcombo();
    }
    public void clear(){
         m_name_txt.setText("");
         m_cata_txt.setSelectedIndex(0);
         m_price_txt.setText("");
    }
    public void lock(){
         m_name_txt.setEnabled(false);
         m_cata_txt.setEnabled(false);
         m_price_txt.setEnabled(false);
    }
    public void unlock(){
         m_name_txt.setEnabled(true);
         m_cata_txt.setEnabled(true);
         m_price_txt.setEnabled(true);
    }
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
public String find(){
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
  }
  public String id(){
       int count=0;
          String sql  ="select MENU_ID from MENU";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("MENU_ID"))>max){
            max = Integer.parseInt(rs.getString("MENU_ID"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "M00"+max;
    }else if(max<100){
        output = "M0"+max;
    }else{
        output = "M"+max;
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
    void fillcombo(){
      try{
          String sql = "SELECT M_T_ID,M_T_NAME FROM MENU_TYPE WHERE M_T_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            m_cata_txt.addItem(rs.getString("M_T_NAME"));
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){
          
      }
  }
    void fillproductcombo(){
        try{
            String sql = "SELECT PRO_ID,PRO_NAME,PRO_UNITS,PRO_UNITS_TYPE,PRO_LIST_ID FROM PRODUCT WHERE PRO_DEL = 'N'";
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Product_variable p = new Product_variable();
                p.setid(rs.getString("PRO_ID"));
                p.setname(rs.getString("PRO_NAME"));
                p.setunit(rs.getDouble("PRO_UNITS"));
                p.setunits_type(rs.getString("PRO_UNITS_TYPE"));
                p.setProduct_type(rs.getString("PRO_LIST_ID"));
                productarray.add(p);
                if(rs.getString("PRO_LIST_ID").equals("04")){
                product_combo.addItem(rs.getString("PRO_NAME"));
                }
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }
   public ArrayList<Menu_variable>MenuList(){
               ArrayList<Menu_variable> Menu_list = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select MENU_ID,MENU_NAME,MENU_PRICE,M_T_ID,M_T_NAME FROM MENU NATURAL JOIN MENU_TYPE WHERE MENU_DEL = 'N' ORDER BY MENU_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
       st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Menu_variable m = new Menu_variable();
            m.setid(rs.getString("MENU_ID"));
            m.setname(rs.getString("MENU_NAME"));
            m.setprice(rs.getInt("MENU_PRICE"));
            m.setcataid(rs.getString("M_T_ID"));
            m.setcataname(rs.getString("M_T_NAME"));
            Menu_list.add(m);
        }
        rs.close();
        st.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Menu_list;
}
    public void show_product(){
        ArrayList<Menu_variable>MenuList = MenuList();
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<MenuList.size();i++){
            row[0]=MenuList.get(i).getid();
            row[1]=MenuList.get(i).getname();
            row[2]=MenuList.get(i).getprice();
            row[3]=MenuList.get(i).getcataname();
            model.addRow(row);
        }
    }

    public void productusing(){
        DefaultTableModel productmodel = (DefaultTableModel)product_table.getModel();
        ArrayList<Product_variable> product = productusing;
        Object[] o = new Object[3];
        for(int i =0;i<productarray.size();i++){
        o[0] = product.get(i).getid();
        o[1] = product.get(i).getname();
        o[2] = product.get(i).getunit();
        productmodel.addRow(o);
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
        m_name_txt = new javax.swing.JTextField();
        m_price_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        m_cata_txt = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        delete = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        edit = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        product_labal = new javax.swing.JLabel();
        product_combo = new javax.swing.JComboBox<>();
        addproduct_btn = new javax.swing.JButton();
        menu_ = new javax.swing.JScrollPane();
        menu_table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1400, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสวัตถุดิบ", "ชื่อวัตถุดิบ", "จำนวนที่ใช้", "หน่วยของวัตถุดิบ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 660, 250));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 110, 30));
        getContentPane().add(m_name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 370, 30));

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
        getContentPane().add(m_price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 90, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("รหัสเมนู:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("ชื่อเมนู:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ราคา:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

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

        m_cata_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        getContentPane().add(m_cata_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 130, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("หมวดหมู่:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 620, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Function:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 620, -1, -1));

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
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, -1, -1));

        jLabel13.setText("Delete");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 620, -1, -1));

        jLabel14.setText("Create");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, -1, -1));

        jLabel15.setText("Edit");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 620, -1, -1));

        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("สร้างหมวดหมู่ คลิ๊กที่นี้");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 255, -1, -1));

        product_labal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        product_labal.setText("วัตถุดิบ:");
        getContentPane().add(product_labal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        product_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Choose Ingredient>" }));
        product_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_comboActionPerformed(evt);
            }
        });
        getContentPane().add(product_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 190, 30));

        addproduct_btn.setText("เพิ่ม");
        addproduct_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addproduct_btnActionPerformed(evt);
            }
        });
        getContentPane().add(addproduct_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 60, 30));

        menu_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเมนู", "ชื่อเมนู", "ราคา", "หมวดหมู่"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menu_table.getTableHeader().setReorderingAllowed(false);
        menu_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_tableMouseClicked(evt);
            }
        });
        menu_.setViewportView(menu_table);

        getContentPane().add(menu_, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 670, 670));

        jLabel3.setText("ตารางเมนู");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
       if(editnaja==true||deletenaja==true){
        showid_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString());
        m_name_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),1).toString());
        m_price_txt.setText(product_table.getModel().getValueAt(product_table.getSelectedRow(),2).toString());
        m_cata_txt.setSelectedItem(product_table.getModel().getValueAt(product_table.getSelectedRow(),3));
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(productarray.isEmpty()){
            JOptionPane.showMessageDialog(null,"คุณยังไม่ได้ทำการเพิ่มวัตถุดิบ\nกรุณาเพิ่มวัตถุดิบด้วยครับ");
        }else{
        String id = showid_txt.getText();
        String menu_name = m_name_txt.getText();
        String menu = find();
        String menu_price = m_price_txt.getText();
        if(createnaja==true){
        String eiei = "INSERT INTO MENU VALUE('"+id+"','"+menu_name+"','"+menu_price+"','"+menu+"','N')";  
        System.out.print(eiei);
        try{
        pat = getcon().prepareStatement(eiei);
        pat.executeUpdate(eiei);
        JOptionPane.showMessageDialog(null,"Adding Menu success");
        pat.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
        DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
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
                pat = getcon().prepareStatement(edit);
                pat.executeUpdate(edit);
                pat.close();
                clear();
                JOptionPane.showMessageDialog(null,"Update Success");
                getcon().close(); 
            }catch(Exception e){
                System.out.print(e);
            }
                  DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
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
                pat = getcon().prepareStatement(delete);
                pat.executeUpdate(delete);
                pat.close();
                clear();
                JOptionPane.showMessageDialog(null,"Delete Success");
                getcon().close(); 
            }catch(Exception e){
                System.out.print(e);
            }
        DefaultTableModel dm = (DefaultTableModel)product_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_product();
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void m_price_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_price_txtActionPerformed

    }//GEN-LAST:event_m_price_txtActionPerformed

    private void m_price_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_m_price_txtFocusGained

    }//GEN-LAST:event_m_price_txtFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
        showid_txt.setText(createid);
        product_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        unlock(); 
        clear();
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

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setForeground(Color.white);
        jLabel1.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setForeground(Color.blue);
        jLabel1.setCursor(new Cursor(DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Menu_type_panel p = new Menu_type_panel();
        p.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void menu_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_tableMouseClicked

    private void addproduct_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addproduct_btnActionPerformed
        Double units = 0.0;
        String units_type = null;
        String productid = null;
        String productname = null;
        if(product_combo.getSelectedIndex()==0){
            
        }else{
        for(Product_variable pv:productarray){
            if(pv.getname().equals(product_combo.getSelectedItem().toString())){
                System.out.print(productid);
                productid = pv.getid();
                productname = pv.getname();
                units_type = pv.getunit_type();
            }
        }
        for(int i =0;i<productarray.size();i++){
            if(product_combo.getSelectedItem().toString().equals(productarray.get(i).getname())){
               try{
                   units = Double.parseDouble(JOptionPane.showInputDialog(null,"\""+productname+"\" มีหน่วยเป็น \""+units_type+"\"\n\nกรุณากรอกปริมาณของวัตถุดิบที่จะใช้ด้วยครับ\n(ต่อหน่วยของวัตถุดิบ)\nตัวอย่างเช่น ใช้ 100มิลลิลิตรให้กรอก 0.1\nถ้าหากใช้ 10กรัมให้กรอก 0.01\nถ้าใช้ 1/20 ของขวด ให้กรอก 0.05"));
                   //-----
                   Product_variable p = new Product_variable();
                   p.setid(productarray.get(i).getid());
                   p.setname(productarray.get(i).getname());
                   p.setunit(units);
                   productusing.add(p);
                   for(Product_variable pk:productusing){
                       System.out.print(pk.getid());
                   }
                   DefaultTableModel model = (DefaultTableModel)product_table.getModel();
                   while(model.getRowCount()>0){
                       model.removeRow(0);
                   }
                   productusing();
                   product_combo.removeItemAt(product_combo.getSelectedIndex());
                   product_combo.setSelectedIndex(0);
               }catch (Exception e){
                   System.err.println(e);
                   product_combo.setSelectedIndex(0);
               }
            }
        }
        }
    }//GEN-LAST:event_addproduct_btnActionPerformed

    private void product_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_comboActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(Menu_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Menu_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addproduct_btn;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> m_cata_txt;
    private javax.swing.JTextField m_name_txt;
    private javax.swing.JTextField m_price_txt;
    private javax.swing.JScrollPane menu_;
    private javax.swing.JTable menu_table;
    private javax.swing.JComboBox<String> product_combo;
    private javax.swing.JLabel product_labal;
    private javax.swing.JTable product_table;
    private javax.swing.JTextField showid_txt;
    // End of variables declaration//GEN-END:variables
}
