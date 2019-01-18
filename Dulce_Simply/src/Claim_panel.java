/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanachot
 */
public class Claim_panel extends javax.swing.JFrame{
    ArrayList<Claim_Variable> Claimproduct = new ArrayList<>();
    ArrayList<Stock_Variable> stock = new ArrayList<>();
    ArrayList<Stock_Variable> orderid = new ArrayList<>();
    ArrayList<Claim_Variable> order = new ArrayList<>();
    ArrayList<Stock_Variable> product = new ArrayList<>();
    ArrayList<Stock_Variable> orderidfromstock = new ArrayList<>();
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
//----------------------------Integer variable---------------------------------------//
    int max = 0;
//-----------------------------String variable-----------------------------------------------//
    String output = "";
    String claimid = "";
    String claimidtablecheck = "";
//-----------------------------Boolean variable---------------------------------------------------//
    boolean Stock_clicked = false;
    boolean create = true;
    boolean delete = false;
    boolean firstdialog = false;
//-------------------------------------------------------------------------------------------------//
    /**
     * Creates new form Claim_panel
     */
    public Claim_panel() {
        initComponents();
        show_productfromstock();
        claimid();
        Employeeid_txt.setText(e.getshowid());
        fillcombo();
    }
    void producttable(){
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        while(model.getRowCount() > 0)
        {       
        model.removeRow(0);
        }
        Object[] row = new Object[5];
        for(int i=0;i<product.size();i++){
            row[0]=product.get(i).getstocknumber();
            row[1]=product.get(i).getproductname();
            row[2]=product.get(i).getstockunits();
            row[3]=product.get(i).getorderid();
            row[4]=product.get(i).c.getcause();
            model.addRow(row);
        }
        delete();
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
    void lock(){
        product.clear();
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        while(model.getRowCount() > 0)
        {       
        model.removeRow(0);
        }
        Claimid_txt.setText("");
        Claimid_txt.setEnabled(false);
        orderid_combo.setEnabled(false);
        Stock_combo.setEnabled(false);
        create_btn.setEnabled(false);
        clear_btn.setEnabled(false);
        Employeeid_txt.setText("");
        Employeeid_txt.setEnabled(false);
        orderid_combo.setSelectedIndex(0);
        Stock_combo.setSelectedIndex(0);
        product_txt.setText("");
        product_txt.setEnabled(false);
        unit_txt.setText("");
        unit_txt.setEnabled(false);
        date_combo.setEnabled(false);
        receive_combo.setEnabled(false);
        /*Claimid_txt.setText("");
        orderid_combo.removeAllItems();*/
    }
    void unlock(){
        Claimid_txt.setText(claimid);
        orderid_combo.setEnabled(true);
        Stock_combo.setEnabled(true);
        create_btn.setEnabled(true);
        clear_btn.setEnabled(true);
        Employeeid_txt.setText(e.getshowid());
        orderid_combo.setSelectedIndex(0);
        Stock_combo.setSelectedIndex(0);
        product_txt.setText("");
        unit_txt.setText("");
        date_combo.setEnabled(true);
        receive_combo.setEnabled(true);
        Claimid_txt.setEnabled(true);
        Employeeid_txt.setEnabled(true);
        product_txt.setEnabled(true);
        unit_txt.setEnabled(true);
        /*Claimid_txt.setText("");
        orderid_combo.removeAllItems();*/
    }
    void delete(){
        Stock_combo.removeItemAt(Stock_combo.getSelectedIndex());
        product_txt.setText("");
        System.out.print(Stock_combo.getItemCount());
    }
    void fillstock(String id){
        Stock_combo.removeAllItems();
        Stock_combo.addItem("<Choose Stocknumber>");
        try{
        String sql = "select STOCK_NUMBER,PRO_ID,PRO_NAME,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID FROM STOCK NATURAL JOIN PRODUCT WHERE PO_ID = '"+id+"' AND STOCK_DEL = 'N' ORDER BY STOCK_NUMBER";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Stock_combo.addItem(rs.getString("STOCK_NUMBER"));
            Stock_Variable v = new Stock_Variable();
            v.setstocknumber(Integer.parseInt(rs.getString("STOCK_NUMBER")));
            System.out.print(rs.getString("STOCK_NUMBER"));
            v.setstockunits(rs.getDouble("STOCK_UNITS"));
            v.setorderid(rs.getString("PO_ID"));
            v.setproductid(rs.getString("PRO_ID"));
            v.setproductname(rs.getString("PRO_NAME"));
            orderid.add(v);
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){

  }
    }
void fillproduct(String id){
        try{
        String sql = "select STOCK_NUMBER,PRO_ID,PRO_NAME FROM STOCK NATURAL JOIN PRODUCT WHERE STOCK_NUMBER = '"+id+"' ORDER BY STOCK_NUMBER";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            product_txt.setText(rs.getString("PRO_NAME"));
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){

  }
    }
    void fillcombo(){
        order.clear();
        orderid_combo.removeAllItems();
        orderid_combo.addItem("<Choose OrderID>");
        try{
          String sql = "select STOCK_NUMBER,PRO_ID,PRO_NAME,STOCK_EXP,STOCK_STARTDATE,STOCK_UNITS,PO_ID,STOCK_DEL FROM STOCK NATURAL JOIN PRODUCT WHERE STOCK_DEL = 'N' AND PO_ID <> ''";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            System.out.println(order.size());
            if(order.isEmpty()){
            Claim_Variable ck = new Claim_Variable();
            ck.setorderid(rs.getString("PO_ID"));
            order.add(ck);
            System.out.print(rs.getString("PO_ID"));
            }else{
            System.out.println(rs.getString("PO_ID"));
            for(Claim_Variable c:order){
            if(!c.getorderid().equals(rs.getString("PO_ID"))){
            Claim_Variable ck = new Claim_Variable();
            ck.setorderid(rs.getString("PO_ID"));
            order.add(ck);
            }
            }
            }
            /*if(orderid_combo.getItemAt(orderid_combo.getItemCount()-1).equals(rs.getString("PO_ID"))){
                //System.out.print("Duplicated is "+orderid_combo.getItemAt(orderid_combo.getItemCount()-1));
            }else{
            System.out.println(rs.getString("PO_ID"));
            }
            Claim_Variable c = new Claim_Variable();
            c.setorderid(rs.getString("PO_ID"));
            order.add(c);
            /*
            if(!rs.getString("PO_ID").isEmpty()&&rs.getInt("COUNT(STOCK_NUMBER)")>0){
            Stock_Variable s = new Stock_Variable();
            s.setorderid(rs.getString("PO_ID"));
            stock.add(s);
            }
            }*/
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){

      }
        for(Claim_Variable c:order){
            orderid_combo.addItem(c.getorderid());
            System.out.print(c.getorderid());
        }
      //System.out.println("Size of stock ="+stock.size()); 
  }
 public String claimid(){
    int count=0;
    max = 0;
    String sql  ="select CL_ID from CLAIM";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    pat = getcon().prepareStatement(sql);
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
    getcon().close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    claimid = output;
    System.out.print(output);
    return output;
   }
 public String claimlistid(){
    int count=0;
    max = 0;
    String sql  ="select C_L_NUMBER from CLAIM_LIST";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    pat = getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("C_L_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("C_L_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    getcon().close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return ""+max;
   }
 public String receiveclaimid(){
    int count=0;
    max = 0;
    String output = null;
    String sql  ="select CR_ID from CLAIM_RECEIVE";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    pat = getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("CR_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("CR_ID").substring(1,4));
        }
    }
            //System.out.println("maxrec = "+max);
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "c00"+max;
    }else if(max<100){
        output = "c0"+max;
    }else{
        output = "c"+max;
    }
    getcon().close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
 public String receivelistid(){
    int count=0;
    String output = null;
    max = 0;
    String sql  ="select CRL_NUMBER from CLAIM_REC_LIST";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    pat =getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("CRL_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("CRL_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    output = ""+max;
    getcon().close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
public ArrayList<Claim_Variable> Claimproduct(){
        Claimproduct.clear();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select CL_ID,EMP_ID,EMP_FNAME,EMP_LNAME,CL_DATE,CL_REC_DATE,PO_ID,CL_STATUS,COUNT(C_L_NUMBER) FROM (CLAIM NATURAL JOIN CLAIM_LIST)NATURAL JOIN EMPLOYEE WHERE CL_DEL = 'N' GROUP BY CL_ID";
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Claim_Variable c = new Claim_Variable();
            c.setclaimid(rs.getString("CL_ID"));
            c.setdate(rs.getString("CL_DATE"));
            c.setreceivedate(rs.getString("CL_REC_DATE"));
            c.setproductcount(rs.getInt("COUNT(C_L_NUMBER)"));
            c.setempfname(rs.getString("EMP_FNAME"));
            c.setemplname(rs.getString("EMP_LNAME"));
            c.setorderid(rs.getString("PO_ID"));
            c.setstatus(rs.getString("CL_STATUS"));
            Claimproduct.add(c);
        }
        rs.close();
        st.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Claimproduct;
}
    public void show_productfromstock(){
        ArrayList<Claim_Variable>product = Claimproduct();
        DefaultTableModel model = (DefaultTableModel)Claim_Table.getModel();
        Object[] row = new Object[7];
        for(int i=0;i<product.size();i++){
            row[0]=product.get(i).getclaimid();
            row[1]=product.get(i).getdate();
            row[2]=product.get(i).getreceivedate();
            row[3]=product.get(i).getproductcount();
            row[4]=product.get(i).getempfname()+" "+product.get(i).getemplname();
            row[5]=product.get(i).getorderid();
            row[6]=product.get(i).getstatus();
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
        product_table = new javax.swing.JTable();
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
        unit_label = new javax.swing.JLabel();
        unit_txt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสต๊อกสินค้า", "ชื่ออุปกรณ์/วัตถุดิบ", "จำนวน", "รหัสการสั่ง", "สาเหตุ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(product_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 730, 280));

        Claim_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสการเคลม", "วันที่ส่งเคลม", "วันที่รับสินค้า", "จำนวนที่ส่งเคลมทั้งหมด", "ชื่อพนักงาน", "รหัสการสั่ง", "สถานะการเคลม"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Claim_Table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Claim_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Claim_TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Claim_Table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 610, 710));

        Claim_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Claim_label.setText("รหัสการเคลม:");
        getContentPane().add(Claim_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        emp_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emp_label.setText("รหัสพนักงาน:");
        getContentPane().add(emp_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        Orderid_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Orderid_label.setText("รหัสการสั่งอุปกรณ์:");
        getContentPane().add(Orderid_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        orderid_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Choose OrderID>" }));
        orderid_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderid_comboActionPerformed(evt);
            }
        });
        getContentPane().add(orderid_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 140, 30));

        Stock_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Choose Stocknumber>" }));
        Stock_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stock_comboActionPerformed(evt);
            }
        });
        getContentPane().add(Stock_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 150, 30));

        Employeeid_txt.setEditable(false);
        getContentPane().add(Employeeid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 100, 30));

        Claimid_txt.setEditable(false);
        getContentPane().add(Claimid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 100, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ุอุปกรณ์/วัตถุดิบที่จะส่งเคลม");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ตารางการเคลม");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, -1, -1));

        Header.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Header.setText("หน้าต่างการเคลม");
        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));
        getContentPane().add(receive_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, -1, 30));
        getContentPane().add(date_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, -1, 30));

        date_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        date_label.setText("วันที่ส่งเคลม:");
        getContentPane().add(date_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 130, -1));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        close_btn.setText("ปิด");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });
        getContentPane().add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 100, 40));

        Type.add(create_radio);
        create_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        create_radio.setSelected(true);
        create_radio.setText("สร้าง");
        create_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_radioActionPerformed(evt);
            }
        });
        getContentPane().add(create_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, -1, -1));

        Type.add(delete_radio);
        delete_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        delete_radio.setText("ลบ");
        delete_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_radioActionPerformed(evt);
            }
        });
        getContentPane().add(delete_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, -1));

        receive_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        receive_label.setText("วันที่รับสินค้าจากการเคลม:");
        getContentPane().add(receive_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        function_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        function_label.setText("ฟังก์ชั่น");
        getContentPane().add(function_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        create_btn.setText("ยืนยัน");
        create_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_btnActionPerformed(evt);
            }
        });
        getContentPane().add(create_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 100, 40));

        clear_btn.setText("เคลียร์");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        getContentPane().add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, 100, 40));

        Stock_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Stock_label.setText("สต๊อกสินค้า:");
        getContentPane().add(Stock_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        Product_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Product_label.setText("อุปกรณ์/วัตถุดิบ:");
        getContentPane().add(Product_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        product_add.setText("Add");
        product_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_addActionPerformed(evt);
            }
        });
        getContentPane().add(product_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, 60, 30));

        product_txt.setEditable(false);
        getContentPane().add(product_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 150, 30));

        unit_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        unit_label.setText("จำนวน:");
        getContentPane().add(unit_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 275, -1, -1));

        unit_txt.setEditable(false);
        getContentPane().add(unit_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 70, 30));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderid_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderid_comboActionPerformed
    product.clear();
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        while(model.getRowCount() > 0)
        {       
        model.removeRow(0);
        }
       if(orderid_combo.getSelectedIndex()==0){
           
       }else{
       for(int i =0;i<order.size();i++){
           System.out.println(order.get(i).getorderid());
           if(orderid_combo.getSelectedItem().toString().equals(order.get(i).getorderid())){
               fillstock(orderid_combo.getSelectedItem().toString());
               break;
        }
       }
       }
    }//GEN-LAST:event_orderid_comboActionPerformed

    private void product_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_addActionPerformed
        String cause = null;
        Stock_Variable s = new Stock_Variable();
        s.setstocknumber(Integer.parseInt(Stock_combo.getSelectedItem().toString()));
        s.setproductname(product_txt.getText());
        for(int i =0;i<orderid.size();i++){
            if(orderid.get(i).getstocknumber()==Integer.parseInt(Stock_combo.getSelectedItem().toString())){
                s.setstocknumber(orderid.get(i).getstocknumber());
                s.setproductid(orderid.get(i).getproductid());
                s.setstockunits(Double.parseDouble(unit_txt.getText()));
                s.setorderid(orderid.get(i).getorderid());
                break;
            }
        }
        try{
        cause = JOptionPane.showInputDialog(null, "กรุณากรอกสาเหตุการเคลม");
        if(cause.isEmpty()){
            JOptionPane.showMessageDialog(null,"กรุณากรอกสาเหตุการเคลมให้ถูกต้องด้วยครับ"); 
        }else{
        s.c.setcause(cause);
        product.add(s);
        producttable();
        }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"กรุณากรอกสาเหตุการเคลมให้ถูกต้องด้วยครับ");
        }
    }//GEN-LAST:event_product_addActionPerformed

    private void Stock_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stock_comboActionPerformed
        product_txt.setText("");
        unit_txt.setText("");
        if(Stock_combo.getSelectedIndex()==-1){ 
        }else{
            for(int i =0;i<orderid.size();i++){
                try{
                 if((Integer.parseInt(Stock_combo.getSelectedItem().toString()))==orderid.get(i).getstocknumber()){
                    product_txt.setText(orderid.get(i).getproductname());
                    unit_txt.setText(""+orderid.get(i).getstockunits());
                } 
                }catch(NumberFormatException e){
                }catch(NullPointerException e){
                }
           //System.out.print(Stock_combo.getSelectedItem()+" ");
           //System.out.print(orderid.get(i).getstocknumber()+"\n");
            }
       }
       /*for(int i =0;i<orderid.size();i++){
           System.out.println(orderid.get(i).getstocknumber());
           System.out.println(Stock_combo.getSelectedItem().toString());
           if(Stock_combo.getSelectedItem().toString().equals(""+orderid.get(i).getstocknumber())){
               fillproduct(Stock_combo.getSelectedItem().toString());
           }
       }*/
    }//GEN-LAST:event_Stock_comboActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for(Stock_Variable k:product){
            System.out.print(k.getstocknumber()+" ");
            System.out.print(k.getproductid()+" ");
            System.out.print(k.getproductname()+" ");
            System.out.print(k.getstockunits()+" ");
            System.out.print(k.c.getcause()+"\n");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void create_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_btnActionPerformed
        if(create==true){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String orderid = orderid_combo.getSelectedItem().toString();
        String empid = Employeeid_txt.getText();
        String receiveclaimid = receiveclaimid();
        String date = sdf.format(date_combo.getSelectedDate().getTime());
        String receiveclaim = sdf.format(receive_combo.getSelectedDate().getTime());
        try{
            String insertclaimsql = "INSERT INTO CLAIM VALUES('"+claimid+"','"+date+"','"+receiveclaim+"','N','"+empid+"','"+orderid+"','N')";
            System.out.println(insertclaimsql);
            pat = getcon().prepareStatement(insertclaimsql);
            pat.executeUpdate(insertclaimsql);
            pat.close();
                String insertclaimreceivesql = "INSERT INTO CLAIM_RECEIVE VALUES('"+receiveclaimid+"','"+empid+"','"+claimid+"','"+receiveclaim+"','N','N')"; 
                System.out.println(insertclaimreceivesql);
                pat = getcon().prepareStatement(insertclaimreceivesql);
                pat.executeUpdate(insertclaimreceivesql);
                pat.close();
                    for(int i=0;i<product.size();i++){
                    String insertclaimlistsql = "INSERT INTO CLAIM_LIST VALUES('"+claimlistid()+"','"+claimid+"','"+product.get(i).getorderid()+"','"+product.get(i).getproductid()+"','"+product.get(i).getstocknumber()+"','"+product.get(i).getstockunits()+"','"+product.get(i).c.getcause()+"','N','N')";
                    System.out.println(insertclaimlistsql);
                    pat = getcon().prepareStatement(insertclaimlistsql);
                    pat.executeUpdate(insertclaimlistsql);
                        String insertclaimreceivelistsql = "INSERT INTO CLAIM_REC_LIST VALUES('"+receivelistid()+"','"+receiveclaimid+"','"+product.get(i).getproductid()+"','"+product.get(i).getstockunits()+"','0','N','N')";
                        System.out.println(insertclaimreceivelistsql);
                        pat = getcon().prepareStatement(insertclaimreceivelistsql);
                        pat.executeUpdate(insertclaimreceivelistsql);
                        pat.close();      
                    }
                            String viewstock = "SELECT STOCK_NUMBER,PRO_ID,PO_ID,STOCK_UNITS FROM STOCK WHERE STOCK_DEL = 'N' AND PO_ID = '"+orderid+"'";
                            pat=  getcon().prepareStatement(viewstock);
                            rs = pat.executeQuery(viewstock);
                                while(rs.next()){
                                    Stock_Variable s = new Stock_Variable();
                                    s.setstocknumber(rs.getInt("STOCK_NUMBER"));
                                    s.setorderid(rs.getString("PO_ID"));
                                    s.setstockunits(rs.getDouble("STOCK_UNITS"));
                                    s.setproductid(rs.getString("PRO_ID"));
                                    orderidfromstock.add(s);  
                                }
                            rs.close();
                            pat.close();
                                for(int i =0;i<product.size();i++){
                                    int stocknumber = product.get(i).getstocknumber();
                                    String productid = "";
                                    double productunits = 0.0;
                                    String viewproduct = "SELECT PRO_ID,PRO_UNITS FROM PRODUCT WHERE PRO_DEL = 'N' AND PRO_ID = '"+product.get(i).getproductid()+"'";
                                    System.err.println(viewproduct);
                                    pat = getcon().prepareStatement(viewproduct);
                                    rs = pat.executeQuery(viewproduct);
                                    while(rs.next()){
                                        productid = rs.getString("PRO_ID");
                                        productunits = rs.getDouble("PRO_UNITS");
                                    }
                                    rs.close();
                                    pat.close();
                                        String removefromstock = "UPDATE STOCK SET STOCK_DEL = 'Y' WHERE STOCK_NUMBER = '"+stocknumber+"'";
                                        System.err.println(removefromstock);
                                        pat = getcon().prepareStatement(removefromstock);
                                        pat.executeUpdate(removefromstock);
                                        pat.close();
                                            String removefromproduct = "UPDATE PRODUCT SET PRO_UNITS = '"+((productunits)-product.get(i).getstockunits())+"' WHERE PRO_ID = '"+productid+"'";
                                            System.err.println(removefromproduct);
                                            pat = getcon().prepareStatement(removefromproduct);
                                            pat.executeUpdate(removefromproduct);
                                            pat.close();
                                }
                                        
            getcon().close();
            DefaultTableModel claimmodel = (DefaultTableModel) Claim_Table.getModel();
            while(claimmodel.getRowCount() > 0){       
            claimmodel.removeRow(0);
            }
            DefaultTableModel productmodel = (DefaultTableModel) product_table.getModel();
            while(productmodel.getRowCount() > 0){       
            productmodel.removeRow(0);
            }
            JOptionPane.showMessageDialog(null,"ทำรายการสำเร็จ");
            fillcombo();
            orderid_combo.setSelectedIndex(0);
        }catch(Exception e){
            System.out.print(e);
        }
        /*try{
            con = DriverManager.getConnection(d.url(),d.username(),d.password());
            for(int i=0;i<product.size();i++){
                String insertclaimlistsql = "INSERT INTO CLAIM_LIST VALUES('"+claimlistid()+"','"+claimid+"','"+product.get(i).getorderid()+"','"+product.get(i).getproductid()+"','"+product.get(i).getstockunits()+"','"+product.get(i).c.getcause()+"','N','N')";
                System.out.println(insertclaimlistsql);
                pat = con.prepareStatement(insertclaimlistsql);
                pat.executeQuery(insertclaimlistsql);
                  String insertclaimreceivelistsql = "INSERT INTO CLAIM_REC_LIST VALUES('"+receivelistid()+"','"+claimid+"','"+product.get(i).getproductid()+"','"+product.get(i).getstockunits()+"','0','N','N')";
                  System.out.println(insertclaimreceivelistsql);
                  pat = con.prepareStatement(insertclaimreceivelistsql);
                  pat.close();
            }
        }catch(Exception e){
            System.out.print(e);
        }*/
        claimid();
        show_productfromstock();
        unlock();
        create = true;
        }
    }//GEN-LAST:event_create_btnActionPerformed

    private void create_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_radioActionPerformed
        unlock();
        create = true;
        delete = false;
    }//GEN-LAST:event_create_radioActionPerformed

    private void delete_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_radioActionPerformed
        lock();
        delete = true;
        create = false;
        JOptionPane.showMessageDialog(null, "ดับเบิ้ลคลิ๊กที่รายการในตารางการเคลมเพื่อลบ");
    }//GEN-LAST:event_delete_radioActionPerformed

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_close_btnActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        lock();
        unlock();
    }//GEN-LAST:event_clear_btnActionPerformed

    private void Claim_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Claim_TableMouseClicked
    if(Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString().equals(claimidtablecheck)){ 
            if(create==true){
            c.setclaimidview(Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString());
            Claim_List_Panel cl = new Claim_List_Panel();
            cl.setVisible(true);
            }else if(delete == true){
                int claimlistcount = 0;
                int receiveclaimlist = 0;
                String receivedclaimid = null;
                boolean claimreceived = false;
                String claimid = Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString();
                ArrayList<Stock_Variable> claimlist = new ArrayList<>();
                if(JOptionPane.showConfirmDialog(null, "คุณแน่ใจที่จะลบการเคลมนี้ใช่หรือไม่","",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                String selectreceiveid = "SELECT CR_ID FROM CLAIM_RECEIVE WHERE CL_ID = '"+claimid+"' AND CR_DEL = 'N'";
                System.err.println(selectreceiveid);
                try{
                    pat = getcon().prepareStatement(selectreceiveid);
                    rs = pat.executeQuery();
                    while(rs.next()){
                        receivedclaimid = rs.getString("CR_ID");
                    }
                    rs.close();
                    pat.close();
                        String checkreceived = "SELECT CRL_NUMBER,CR_ID,CRL_CURRENT FROM CLAIM_REC_LIST WHERE CRL_DEL = 'N' AND CR_ID = '"+receivedclaimid+"'";
                        System.err.println(checkreceived);
                        pat = getcon().prepareStatement(checkreceived);
                        rs = pat.executeQuery(checkreceived);
                        while(rs.next()){
                            System.out.println(rs.getDouble("CRL_CURRENT"));
                            if(rs.getDouble("CRL_CURRENT")>0){
                                claimreceived = true;
                                break;
                            }
                        }
                        rs.close();
                        pat.close();
                        getcon().close();
                        }catch(Exception e){
                            System.out.println(e);
                }
                if(claimreceived==false){
                    try{
                    String viewclaimlistcount = "SELECT CL_ID,C_L_NUMBER,STOCK_NUMBER,PRO_ID,C_L_UNITS FROM CLAIM NATURAL JOIN CLAIM_LIST WHERE CL_DEL = 'N' AND CL_ID = '"+claimid+"' AND C_L_DEL = 'N'";
                    System.out.println(viewclaimlistcount);
                    pat=  getcon().prepareStatement(viewclaimlistcount);
                    rs = pat.executeQuery(viewclaimlistcount);
                    while(rs.next()){
                        Stock_Variable s = new Stock_Variable();
                        s.setstocknumber(rs.getInt("STOCK_NUMBER"));
                        s.setorderid(claimid);
                        s.setproductid(rs.getString("PRO_ID"));
                        s.c.setclaimid(rs.getString("CL_ID"));
                        s.c.setclaimlistnumber(rs.getInt("C_L_NUMBER"));
                        s.setstockunits(rs.getDouble("C_L_UNITS"));
                        claimlist.add(s);
                    }
                    rs.close();
                    pat.close();
                    System.err.print(claimlist.size());
                    for(int i = 0;i<claimlist.size();i++){
                        String updateproduct = "UPDATE PRODUCT SET PRO_UNITS = PRO_UNITS+'"+claimlist.get(i).getstockunits()+"' WHERE PRO_ID = '"+claimlist.get(i).getproductid()+"'";
                        pat = getcon().prepareStatement(updateproduct);
                        pat.executeUpdate(updateproduct);
                        pat.close();
                        String updatestock = "UPDATE STOCK SET STOCK_DEL = 'N' WHERE STOCK_NUMBEr = '"+claimlist.get(i).getstocknumber()+"'";
                        System.err.println(updatestock);
                        pat = getcon().prepareStatement(updatestock);
                        pat.executeUpdate(updatestock);
                        pat.close();
                        System.err.print(updateproduct);
                        }
                    String claimlistdelete = "UPDATE CLAIM_LIST SET C_L_DEL = 'Y' WHERE CL_ID = '"+claimid+"'";
                    System.out.println(claimlistdelete);
                    pat = getcon().prepareStatement(claimlistdelete);
                    pat.executeUpdate(claimlistdelete);
                    pat.close();
                        String claimreceivelistdelete = "UPDATE CLAIM_REC_LIST NATURAL JOIN CLAIM_RECEIVE SET CRL_DEL = 'Y' WHERE CL_ID = '"+claimid+"'";
                        System.out.println(claimreceivelistdelete);
                        pat = getcon().prepareStatement(claimreceivelistdelete);
                        pat.executeUpdate(claimreceivelistdelete);
                        pat.close();    
                            String claimdelete =  "UPDATE CLAIM SET CL_DEL = 'Y' WHERE CL_ID = '"+claimid+"'";
                            System.out.println(claimdelete);
                            pat = getcon().prepareStatement(claimdelete);
                            pat.executeUpdate(claimdelete);
                            pat.close();
                                String claimreceivedelete = "UPDATE CLAIM_RECEIVE SET CR_DEL = 'Y' WHERE CL_ID = '"+claimid+"'";
                                System.out.println(claimreceivedelete);
                                pat = getcon().prepareStatement(claimreceivedelete);
                                pat.executeUpdate(claimreceivedelete);
                                pat.close();
                    DefaultTableModel model = (DefaultTableModel)Claim_Table.getModel();
                    while(model.getRowCount()>0){
                    model.removeRow(0);
                    }
                    show_productfromstock();
                    unlock();
                    create_radio.setSelected(true);
                    JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
                    con.close();
                    }catch(Exception e){
                    System.err.println(e);
                    }
                    fillcombo();
                    orderid_combo.setSelectedIndex(0);
                }else{
                    JOptionPane.showMessageDialog(null,"รายการเคลมนี้มีการรับสินค้าแล้ว ไม่สามารถลบได้ครับ","",ERROR_MESSAGE);
                }
                }else{
                    
                }
            }
            claimidtablecheck="";
        }else{
            claimidtablecheck = Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString();
        }
    unlock();
    }//GEN-LAST:event_Claim_TableMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    if(firstdialog==false){       
    JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กที่ตารางการเคลมเพื่อดูรายละเอียดได้ครับ");
    firstdialog=true;
            }
    }//GEN-LAST:event_formWindowActivated

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> orderid_combo;
    private javax.swing.JButton product_add;
    private javax.swing.JTable product_table;
    private javax.swing.JTextField product_txt;
    private datechooser.beans.DateChooserCombo receive_combo;
    private javax.swing.JLabel receive_label;
    private javax.swing.JLabel unit_label;
    private javax.swing.JTextField unit_txt;
    // End of variables declaration//GEN-END:variables
}
