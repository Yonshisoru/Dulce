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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Product_Order_panel extends javax.swing.JFrame {
//----------------------------------------------------------
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
//-----------------------------------------------------------------------
    ArrayList<Product_variable> productlist = new ArrayList<>();
    ArrayList<Product_Order_Variable> Product_Order_Array = new ArrayList<>();
    ArrayList<Product_Order_Variable> Product_Order_Receive_Array = new ArrayList<>();
    ArrayList<Product_Order_Variable> Product_Order_Receive_List_Array = new ArrayList<>();
    ArrayList<Product_variable> productlisttoorder = new ArrayList<>();
    ArrayList<Product_Order_Variable> product = new ArrayList<Product_Order_Variable>();
    ArrayList<Product_variable> product_id = new ArrayList<Product_variable>();
    ArrayList<Employee> employee = new ArrayList<Employee>();
    ArrayList<Vendor_variable> vendor = new ArrayList<Vendor_variable>();
//-----------------------------------------------------------------------
    boolean deletenakub = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    boolean adding = false;
    boolean pass = false;
//--------------------------------------------------------------------------
    int max = 0;
    int totalorder = 0;
    int productprice = 0;
    int price  =0;
    int Window_Activated = 0;
//--------------------------------------------------------------------------------
    public String id = null;
    String checkview = null;
    String output = null;
    String menu = null;
    String password= null;
    String createid = null;
    String productname = null;
    String orderpaymentid = null;
    String receiveid = null;
    String productid = null;
    String doubleclick = null;
    String empid = e.getshowid();
    String year = ""+(Integer.parseInt(LocalDate.now().toString().substring(0,4))+543);
    String month = LocalDate.now().toString().substring(5,7);
    String day = LocalDate.now().toString().substring(8,10);
    String now = month+"/"+day+"/"+year;
//--------------------------------------------------------------------------------
    double sum = 0;
    /**
     * Creates new form Employee_create
     */
    public Product_Order_panel() {
        initComponents();
        show_order();
        id();
        getProduct_Order();
        fillcombovendor();
        getProduct();
        getProduct_Order_Receive();
        getProduct_Order_Receive_List();
        show_order();
        emp_txt.setText(empid);
    }
    public void clear(){
        v_txt.setSelectedIndex(0);
        emp_txt.setText(empid);
        setdate();
         showid_txt.setText(createid);
         price_txt.setText("0.0");
         p_txt1.setEnabled(true);
    }
public void setdate(){
 year = ""+(Integer.parseInt(LocalDate.now().toString().substring(0,4))+543);
month = LocalDate.now().toString().substring(5,7);
day = LocalDate.now().toString().substring(8,10);
 now = month+"/"+day+"/"+year;
System.out.print(now);
 try{
Calendar cal = Calendar.getInstance();
java.util.Date date = new SimpleDateFormat("MM/dd/yy").parse(now);
cal.setTime(date);
receive_date.setSelectedDate(cal);
pay_date.setSelectedDate(cal);
}catch(Exception e){
    System.out.print(e);
    }
}
    
    public void lock(){
        pay_date.setEnabled(false);
        receive_date.setEnabled(false);
         v_txt.setEnabled(false);
         price_txt.setEnabled(false);
         p_txt1.setEnabled(false);
         /*receive_date.setEnabled(false);
         pay_date.setEnabled(false);*/
    }
    public void unlock(){
        pay_date.setEnabled(true);
        receive_date.setEnabled(true);
         v_txt.setEnabled(true);
         price_txt.setEnabled(true);
         receive_date.setEnabled(true);
         pay_date.setEnabled(true);
         p_txt1.setEnabled(true);
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
public String findorderpayment(String id){
            String find = "SELECT OP_NUMBER FROM ORDER_PAYMENT WHERE PO_ID = '"+id+"'";
        try{
        pat = getcon().prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
                orderpaymentid = rs.getString("OP_NUMBER");
                System.out.print(orderpaymentid);
        }
        rs.close();
        pat.close();
        getcon().close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return orderpaymentid;
  }

public String findreceive(String id){
            String find = "SELECT PR_ID FROM PRO_RECEIVE WHERE PO_ID = '"+id+"'";
        try{
        pat = getcon().prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
                receiveid = rs.getString("PR_ID");
                System.out.print(receiveid);
        }
        rs.close();
        pat.close();
        getcon().close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return receiveid;
  }
public void getProduct(){
    String sql = "SELECT PRO_ID,PRO_NAME,PRO_PRICE,PRO_UNITS,PRO_UNITS_TYPE,PRO_MIN,V_ID,V_NAME FROM PRODUCT NATURAL JOIN VENDOR WHERE PRO_DEL = 'N'";
    try{
        pat = getcon().prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            Product_variable p = new Product_variable();
            p.setid(rs.getString("PRO_ID"));
            p.setname(rs.getString("PRO_NAME"));
            p.setprice(rs.getInt("PRO_PRICE"));
            p.setunit(rs.getDouble("PRO_UNITS"));
            p.setunits_type(rs.getString("PRO_UNITS_TYPE"));
            p.setmin(rs.getInt("PRO_MIN"));
            p.setvid(rs.getString("V_ID"));
            p.setvname(rs.getString("V_NAME"));
            productlist.add(p);
        }
        rs.close();
        pat.close();
        getcon().close();
    }catch(Exception e){
        System.err.println(e);
    }
}
public void getProduct_Order_Receive(){
    String sql = "SELECT PR_ID,PR_DATE,PR_STATUS,EMP_ID,EMP_FNAME,EMP_LNAME,PO_ID FROM PRO_RECEIVE NATURAL JOIN EMPLOYEE WHERE PR_DEL = 'N'";
    try{
        pat = getcon().prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            Product_Order_Variable p = new Product_Order_Variable();
            p.setProduct_Order_Receive_id(rs.getString("PR_ID"));
            p.setrec_date(rs.getString("PR_DATE"));
            p.setreceivestatus(rs.getString("PR_STATUS"));
            p.e.setid(rs.getString("EMP_ID"));
            p.e.setfname(rs.getString("EMP_FNAME"));
            p.e.setlname(rs.getString("EMP_LNAME"));
            p.setProduct_Order_ID(rs.getString("PO_ID"));
            Product_Order_Receive_Array.add(p);
        }
        rs.close();
        pat.close();
        getcon().close();
    }catch(Exception e){
        System.err.println(e);
    }
}
public void getProduct_Order_Receive_List(){
    String sql = "SELECT PRL_NUMBER,PR_ID,PRO_ID,PRO_NAME,PRL_UNITS,PRL_PRICE,PRL_CURRENT,PRL_STATUS FROM PRO_REC_LIST NATURAL JOIN PRODUCT WHERE PRL_DEL = 'N'";
    try{
        pat = getcon().prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            Product_Order_Variable p = new Product_Order_Variable();
            p.setProduct_Order_Receive_List_Number(rs.getString("PRL_NUMBER"));
            p.setProduct_Order_Receive_id(rs.getString("PR_ID"));
            p.setproductid(rs.getString("PRO_ID"));
            p.setproductname(rs.getString("PRO_NAME"));
            p.setunit(rs.getInt("PRL_UNITS"));
            p.setprice(rs.getDouble("PRL_PRICE"));
            p.setcurrent(rs.getInt("PRL_CURRENT"));
            p.setreceivestatus(rs.getString("PRL_STATUS"));
            Product_Order_Receive_List_Array.add(p);
        }
        rs.close();
        pat.close();
        getcon().close();
    }catch(Exception e){
        System.err.println(e);
    }
}
public void showProduct_list(){
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<productlisttoorder.size();i++){
            row[0]=productlisttoorder.get(i).getid();
            row[1]=productlisttoorder.get(i).getname();
            row[2]=productlisttoorder.get(i).getprice();
            row[3]=productlisttoorder.get(i).getunit();
            row[4]=productlisttoorder.get(i).getunit_type();
            row[5]=productlisttoorder.get(i).gettotal_price();
            model.addRow(row);
        }
}
public String findempid(String id){
            String find = "SELECT EMP_ID FROM PRO_ORDER WHERE PO_ID = '"+id+"'";
        try{
        pat = getcon().prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
                empid = rs.getString("EMP_ID");
                System.out.print(empid);
        }
        rs.close();
        pat.close();
        getcon().close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return empid;
  }
public void delete(){
    deletenakub = true;
    p_txt1.removeAllItems();
         p_txt1.addItem(">>Choose<<");
         deletenakub=false;
}
  public String id(){
       int count=0;
       max = 0;
          String sql  ="select PO_ID from PRO_ORDER";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PO_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("PO_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "O00"+max;
    }else if(max<100){
        output = "O0"+max;
    }else{
        output = "O"+max;
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
  public String orderpayment(){
       int count=0;
       max = 0;
          String sql  ="select OP_NUMBER from ORDER_PAYMENT";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("OP_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("OP_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "000"+max;
    }else if(max<100){
        output = "00"+max;
    }else if(max<1000){
        output = "0"+max;
    }else{
        output = ""+max;
    }
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
public String idlist(){
    int count=0;
    max = 0;
          String sql  ="select POL_NUMBER from PRO_ORDER_LIST";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("POL_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("POL_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "000"+max;
    }else if(max<100){
        output = "00"+max;
    }else if(max<1000){
        output = "0"+max;
    }else{
        output = ""+max;
    }
    System.out.print("POL_NUMBER = "+max);
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
public String receivelist(){
    max = 0;
       int count=0;
          String sql  ="select PRL_NUMBER from PRO_REC_LIST";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PRL_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("PRL_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "000"+max;
    }else if(max<100){
        output = "00"+max;
    }else if(max<1000){
        output = "0"+max;
    }else{
        output = ""+max;
    }
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
 public String receive(){
    max = 0;
       int count=0;
          String sql  ="select PR_ID from PRO_RECEIVE";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PR_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("PR_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "R00"+max;
    }else if(max<100){
        output = "R0"+max;
    }else if(max<1000){
        output = "R"+max;
    }
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
    void fillcombovendor(){
      try{
          String sql = "SELECT V_ID,V_NAME FROM VENDOR WHERE V_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            v_txt.addItem(rs.getString("V_NAME"));
            Vendor_variable v = new Vendor_variable();
            v.setid(rs.getString("V_ID"));
            v.setname(rs.getString("V_NAME"));
            vendor.add(v);
        }
        rs.close();
        st.close();
        getcon().close();
      }catch(Exception e){
          
      }
  }
    void fillcomboproduct(String id){
      p_txt1.removeAllItems();;
      p_txt1.addItem(">>Choose<<");
      try{
          for(Product_variable p:productlist){
              if(id.equals(p.getvid())){
                  p_txt1.addItem(p.getname());
              }
          }
        /*try{
          String sql = "SELECT PRO_ID,V_ID,PRO_NAME,PRO_PRICE FROM PRODUCT WHERE V_ID ='"+id+"' AND PRO_DEL = 'N'";
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            p_txt1.addItem(rs.getString("PRO_NAME"));
            Product_variable p = new Product_variable();
            p.setid(rs.getString("PRO_ID"));
            p.setname(rs.getString("PRO_NAME"));
            p.setprice(Integer.parseInt(rs.getString("PRO_PRICE")));
            product_id.add(p);
        }
        rs.close();
        st.close();
        con.close();*/
      }catch(Exception e){
          
      }
  }
   public void getProduct_Order(){
        try{
        String sql  ="select PO_ID,EMP_ID,EMP_FNAME,EMP_LNAME,V_ID,V_NAME,PO_DATE,PO_PRICE,PO_REC_DATE,PO_PAY_DATE,PO_UNITS,PR_STATUS,OP_STATUS FROM (((PRO_ORDER NATURAL JOIN EMPLOYEE)NATURAL JOIN VENDOR)NATURAL JOIN PRO_RECEIVE)NATURAL JOIN ORDER_PAYMENT WHERE PO_DEL = 'N' ORDER BY PO_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
       st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Product_Order_Variable p = new Product_Order_Variable();
            p.setProduct_Order_Receive_id(rs.getString("PO_ID"));
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
            System.out.print(p.e.getid());
            Product_Order_Array.add(p);
        }
        rs.close();
        st.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
}
    public void show_order(){
        ArrayList<Product_Order_Variable>Product_order_list = Product_Order_Array;
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        Object[] row = new Object[10];
        for(int i=0;i<Product_order_list.size();i++){
            row[0]=Product_order_list.get(i).getdate();
            row[1]=Product_order_list.get(i).getProduct_Order_Receive_id();
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
public void findproduct(String product){
    for(int i=0;i<product_id.size();i++){
        if(product_id.get(i).getname().equals(product)){
            productid = product_id.get(i).getid();
            productprice = product_id.get(i).getprice();
            break;
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
        order_table = new javax.swing.JTable();
        showid_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        delete = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        receive_date = new datechooser.beans.DateChooserCombo();
        pay_date = new datechooser.beans.DateChooserCombo();
        jLabel17 = new javax.swing.JLabel();
        v_txt = new javax.swing.JComboBox<>();
        price_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        p_txt1 = new javax.swing.JComboBox<>();
        emp_txt = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        help_product_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        help_product_btn1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1530, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "วันที่", "รหัสการสั่ง", "สินค้าที่สั่งทั้งหมด", "ราคาสุทธิ", "วันที่รับสินค้า", "วันจ่ายเงิน", "ชื่อพนักงาน", "ชื่อผู้จัดจำหน่าย", "สถานะการจ่ายเงิน", "สถานะการรับ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        order_table.getTableHeader().setReorderingAllowed(false);
        order_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(order_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 900, 630));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("รหัสการสั่งสินค้า:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ผู้จัดจำหน่าย:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 710, 120, 50));

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 710, 120, 50));

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 710, 120, 50));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("วันจ่ายเงิน:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 650, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("ฟังก์ชั่น");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 650, -1, -1));

        jLabel13.setText("ลบ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 650, -1, -1));

        jLabel14.setText("สร้าง");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 650, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("รหัสพนักงาน:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));
        getContentPane().add(receive_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 600, -1, 30));
        getContentPane().add(pay_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, -1, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("วันรับสินค้า:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, -1, -1));

        v_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        v_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v_txtActionPerformed(evt);
            }
        });
        getContentPane().add(v_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 130, 30));

        price_txt.setEditable(false);
        price_txt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        price_txt.setText("0.0");
        price_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                price_txtActionPerformed(evt);
            }
        });
        getContentPane().add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 110, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ราคาสุทธิ:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, -1, -1));

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("สินค้า:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        p_txt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt1ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 160, 30));

        emp_txt.setEditable(false);
        emp_txt.setEnabled(false);
        getContentPane().add(emp_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 130, 30));

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 60, 30));

        help_product_btn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        help_product_btn.setText("?");
        help_product_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help_product_btnActionPerformed(evt);
            }
        });
        getContentPane().add(help_product_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 40, 20));

        product_table.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสสินค้า", "ชื่อสินค้า", "ราคาต่อชิ้น", "จำนวน", "หน่วย", "ราคารวม"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        product_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                product_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(product_table);
        if (product_table.getColumnModel().getColumnCount() > 0) {
            product_table.getColumnModel().getColumn(0).setPreferredWidth(40);
            product_table.getColumnModel().getColumn(2).setPreferredWidth(40);
            product_table.getColumnModel().getColumn(3).setPreferredWidth(40);
            product_table.getColumnModel().getColumn(4).setPreferredWidth(30);
            product_table.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 540, 260));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        help_product_btn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        help_product_btn1.setText("?");
        help_product_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help_product_btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(help_product_btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 680, 40, 20));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("ตารางการสั่งสินค้า");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("ตารางสินค้า");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void order_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_tableMouseClicked
    if(createnaja==true){
 if(order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString().equals(doubleclick)){
        Product_Order_Variable pov = new Product_Order_Variable();
        pov.setview(order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString());
        Product_Order_List_view p = new Product_Order_List_view();
        p.setVisible(true);
        doubleclick = null;
 }else{
     doubleclick = order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString();
 }
    }else if(deletenaja==true){
    String Product_Order_ID = null;
    String Product_Order_Receive_ID = null;
    boolean receive = false;
    if(order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString().equals(doubleclick)){
        if(JOptionPane.showConfirmDialog(null,"คุณต้องการที่จะลบการสั่งสินค้ารายการ "+order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString(),null,YES_NO_OPTION)==YES_OPTION){
            
        Product_Order_ID = order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString();
        System.out.print(doubleclick);
        for(Product_Order_Variable p:Product_Order_Receive_Array){
            System.out.println(p.getProduct_Order_Receive_id());
            if(p.getProduct_Order_ID().equals(doubleclick)){
                System.err.println(doubleclick);
                Product_Order_Receive_ID = p.getProduct_Order_Receive_id();
                System.out.println(p.getProduct_Order_Receive_id());
                break;
            }
        }
        for(Product_Order_Variable p:Product_Order_Receive_List_Array){
            if(p.getProduct_Order_Receive_id().equals(Product_Order_Receive_ID)){
                System.out.println(p.getcurrent());
                if(p.getcurrent()>0){
                    receive = true;
                }
                System.out.println(p.getProduct_Order_Receive_List_Number());
            }
        }
        if(receive==false){
            int index = 0;
            for(Product_Order_Variable p:Product_Order_Array){
                index ++;
                System.out.print(p.getProduct_Order_Receive_id());
                    String removefromorderreceivelist = "UPDATE PRO_REC_LIST SET PRL_DEL = 'Y' WHERE PR_ID = '"+Product_Order_Receive_ID+"'";
                    String removefromorderreceive = "UPDATE PRO_RECEIVE SET PR_DEL = 'Y' WHERE PO_ID = '"+Product_Order_ID+"'";
                    String removefromorderlist = "UPDATE PRO_ORDER_LIST SET POL_DEL = 'Y' WHERE PO_ID = '"+Product_Order_ID+"'";
                    String removefromorder = "UPDATE PRO_ORDER SET PO_DEL = 'Y' WHERE PO_ID = '"+Product_Order_ID+"'";
                    try{
                    pat = getcon().prepareStatement(removefromorderreceivelist);
                    pat.executeUpdate(removefromorderreceivelist); 
                    pat.close();
                    System.err.println(removefromorderreceivelist);
                        pat = getcon().prepareStatement(removefromorderlist);
                        pat.executeUpdate(removefromorderlist); 
                        pat.close();
                        System.err.println(removefromorderlist);
                            pat = getcon().prepareStatement(removefromorderreceive);
                            pat.executeUpdate(removefromorderreceive); 
                            pat.close();
                            System.err.println(removefromorderreceive);
                                pat = getcon().prepareStatement(removefromorder);
                                pat.executeUpdate(removefromorder); 
                                pat.close();
                                System.err.println(removefromorder);
                                getcon().close();
                    }catch(Exception e){
                        System.err.println(e);
                    }
                    for(Product_Order_Variable pov:Product_Order_Receive_List_Array){
                        
                    }
                if(Product_Order_ID.equals(p.getProduct_Order_Receive_id())){
                    Product_Order_Array.remove(index-1);
                    DefaultTableModel model = (DefaultTableModel)order_table.getModel();
                    while(model.getRowCount()>0){
                        model.removeRow(0);
                    }
                    show_order();
                    System.out.println(p.getProduct_Order_Receive_id());
                    break;
                }
            }
            JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
            System.out.println("Didn't receive");
        }else if(receive==true){
            JOptionPane.showMessageDialog(null,"คุณไม่สามารถลบรายการนี้ได้\nเนื่องจากมีการรับสินค้าแล้ว",null,ERROR_MESSAGE);
            System.out.println("Received");
        }
        try{
        }catch(Exception e){
            System.err.println(e);
        }
        }else{
            JOptionPane.showMessageDialog(null,"ยกเลิกรายการ",null,ERROR_MESSAGE);
            doubleclick = null;
        }
    }else{
        doubleclick = order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString();
    }
        /*delete();
        setdate();
        showid_txt.setText(order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString());
        price_txt.setText(order_table.getModel().getValueAt(order_table.getSelectedRow(),3).toString());
        emp_txt.setText(findempid(order_table.getModel().getValueAt(order_table.getSelectedRow(),6).toString()));
        totalorder = Integer.parseInt(order_table.getModel().getValueAt(order_table.getSelectedRow(),2).toString());
        v_txt.setSelectedItem(order_table.getModel().getValueAt(order_table.getSelectedRow(),7).toString());
        receivedate.setText(order_table.getModel().getValueAt(order_table.getSelectedRow(),4).toString());
        paydate.setText(order_table.getModel().getValueAt(order_table.getSelectedRow(),5).toString());
        String receive = order_table.getModel().getValueAt(order_table.getSelectedRow(),4).toString();
        String year = ""+(Integer.parseInt(receive.substring(0,4))+543);
        String month = receive.substring(5,7);
        String day = receive.substring(8,10);
        String now = month+"/"+day+"/"+year;
        //showid_txt.getText();
         try{
        Calendar cal = Calendar.getInstance();
        java.util.Date date = new SimpleDateFormat("MM/dd/yy").parse(now);
        cal.setTime(date);
        receive_date.setSelectedDate(cal);
        System.out.println("recieve update");
         }catch (Exception e){
             System.out.print(e);
         }
        String pay = order_table.getModel().getValueAt(order_table.getSelectedRow(),5).toString();
        year = ""+(Integer.parseInt(pay.substring(0,4))+543);
        month = pay.substring(5,7);
        day = pay.substring(8,10);
        now = month+"/"+day+"/"+year;
         try{
        Calendar cal = Calendar.getInstance();
        java.util.Date date = new SimpleDateFormat("MM/dd/yy").parse(now);
        cal.setTime(date);
        pay_date.setSelectedDate(cal);
        System.out.print("pay update");
}catch(Exception e){
    System.out.print(e);
}
         int productnaja = 0;
         String sql = "SELECT PRO_NAME FROM PRODUCT WHERE PRO_ID IN (SELECT PRO_ID FROM PRO_ORDER_LIST WHERE PO_ID='"+showid_txt.getText()+"')";
try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            adding = true;
            if(productnaja==0){
                p_txt1.setSelectedItem(rs.getString("PRO_NAME"));
            }
        }
        rs.close();
        st.close();
        con.close();
        adding=false;
System.out.print(sql);
}catch(Exception e){
    System.out.print(e);
}
         System.out.print(pay+" "+receive);
        }else if(viewnaja==true){
            if(order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString().equals(checkview)){
                Product_Order_Variable p = new Product_Order_Variable();
                p.setview(order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString());
                Product_Order_List_view pv = new Product_Order_List_view();
                pv.setVisible(true);
                JOptionPane.showMessageDialog(null,checkview);
                checkview = null;
            }else{
                checkview = order_table.getModel().getValueAt(order_table.getSelectedRow(),1).toString();
            }*/
}
    }//GEN-LAST:event_order_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(createnaja==true){
        if(productlisttoorder.isEmpty()){
            JOptionPane.showMessageDialog(null,"Product was empty.\nPlease choose product for ordering.");
        }else{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = showid_txt.getText();
        String emp = emp_txt.getText();
        String vendornaja = vendor.get(v_txt.getSelectedIndex()-1).getid();
        System.out.print(vendornaja);
        String price = price_txt.getText();
        String recdate = sdf.format(receive_date.getSelectedDate().getTime());
        String paydate = sdf.format(pay_date.getSelectedDate().getTime());
        paydate = ""+(Integer.parseInt(paydate.substring(0,4))-543)+paydate.substring(4);
        recdate = ""+(Integer.parseInt(recdate.substring(0,4))-543)+recdate.substring(4);
        String Order_Payment_ID = orderpayment();
        String Product_Order_Receive_ID = receive();
            String create = "INSERT INTO PRO_ORDER VALUE('"+id+"','"+LocalDate.now()+"','"+price+"','"+recdate+"','"+paydate+"','"+productlisttoorder.size()+"','"+emp+"','"+vendornaja+"','N')";  
            String createorder = "INSERT ORDER_PAYMENT VALUE('"+Order_Payment_ID+"','"+id+"','"+vendornaja+"','"+paydate+"','N','N')";
            String createreceive = "INSERT PRO_RECEIVE VALUE('"+Product_Order_Receive_ID+"','"+recdate+"','N','"+emp+"','"+id+"','N')";
            System.out.println(create);
            System.out.println(createorder);
            System.out.println(createreceive);
      try{
        pat = getcon().prepareStatement(create);
        pat.executeUpdate(create);
        pat.close();
            pat = getcon().prepareStatement(createorder);
            pat.executeUpdate(createorder);
            pat.close();
                pat = getcon().prepareStatement(createreceive);
                pat.executeUpdate(createreceive);
                pat.close();  
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
        
            for(Product_variable p:productlisttoorder){
                String addingorderlist = "INSERT INTO PRO_ORDER_LIST VALUE('"+idlist()+"','"+id+"','"+p.getid()+"','"+p.getunit()+"','"+p.getprice()+"','N')";
                String addingreceivelist = "INSERT INTO PRO_REC_LIST VALUE('"+receivelist()+"','"+Product_Order_Receive_ID+"','"+p.getid()+"','"+p.getunit()+"','"+p.getprice()+"','0','N','N')";  
                try{
                    System.err.println(addingorderlist);
                    pat = getcon().prepareStatement(addingorderlist);
                    pat.executeUpdate(addingorderlist);
                    pat.close(); 
                        System.err.println(addingreceivelist);
                        pat = getcon().prepareStatement(addingreceivelist );
                        pat.executeUpdate(addingreceivelist);
                        pat.close(); 
                getcon().close();
                }catch(Exception e){
                    System.err.println(e);
                }
            }
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        Product_Order_Array.clear();
        getProduct_Order();
        show_order();
        DefaultTableModel modelz = (DefaultTableModel)product_table.getModel();
        while(modelz.getRowCount()>0){
            modelz.removeRow(0);
        }
        productlisttoorder.clear();
        clear();
        showProduct_list();
        JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
        /*findproduct(product.get(i).getproduct());
        String orderlist = "INSERT INTO PRO_ORDER_LIST VALUE('"+idlist()+"','"+orderid+"','"+productid+"','"+product.get(i).getproductunit()+"','"+productprice+"','N')";  
        System.out.println(orderlist);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(orderlist);
        pat.executeUpdate(orderlist);
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        String receivelist = "INSERT INTO PRO_REC_LIST VALUE('"+receivelist()+"','"+receive+"','"+productid+"','"+product.get(i).getproductunit()+"','"+productprice+"','0','N','N')";  
        System.out.println(receivelist);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(receivelist);
        pat.executeUpdate(receivelist);
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
            }
        product.clear();
        id();
        clear();
        delete();
       DefaultTableModel dm = (DefaultTableModel)order_table.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_order();
        }
        }else if(deletenaja==true){
            String orderdelete = "UPDATE PRO_ORDER SET PO_DEL = 'Y' WHERE PO_ID ='"+orderid+"'";
            String paymentdelete = "UPDATE ORDER_PAYMENT SET OP_DEL = 'Y' WHERE PO_ID ='"+orderid+"'";
            String receivedelete = "UPDATE PRO_RECEIVE SET PR_DEL = 'Y' WHERE PO_ID ='"+orderid+"'";
            String deletereceivelist = "UPDATE PRO_REC_LIST SET PRL_DEL = 'Y' WHERE PR_ID ='"+findreceive(orderid)+"'";
            String deleteorderlist = "UPDATE PRO_ORDER_LIST SET POL_DEL = 'Y' WHERE PO_ID ='"+orderid+"'";
            System.out.println(orderdelete);
            System.out.println(paymentdelete);
            System.out.println(receivedelete);
            System.out.println(deletereceivelist);
            System.out.println(deleteorderlist);
            System.out.println(totalorder);
            System.out.println("eiei"+findreceive(orderid));
            System.out.println("eiei2"+orderid);
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(orderdelete);
                pat.executeUpdate(orderdelete);
                pat.close();
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(paymentdelete);
                pat.executeUpdate(paymentdelete);
                pat.close();
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(receivedelete);
                pat.executeUpdate(receivedelete);
                pat.close();
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(deletereceivelist);
                pat.executeUpdate(deletereceivelist);
                pat.close();
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(orderdelete);
                pat.executeUpdate(orderdelete);
                pat.close();
                clear();
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(deleteorderlist);
                pat.executeUpdate(deleteorderlist);
                pat.close();
                clear();
                JOptionPane.showMessageDialog(null,"Delete Success");
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
                       
        DefaultTableModel dm = (DefaultTableModel)order_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_order();
        }*/
        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        product.clear();
        Product_Order_Array.clear();
        delete();
        clear();
        showid_txt.setText(createid);
        order_table.getSelectionModel().clearSelection();
        productlisttoorder.clear();
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        while(model.getRowCount()>0){
                    model.removeRow(0);
        }
        sum = 0;
        price_txt.setText(""+sum);
        getProduct_Order();
        DefaultTableModel modelz = (DefaultTableModel)order_table.getModel();
        while(modelz.getRowCount()>0){
                    modelz.removeRow(0);
        }
        show_order();
        showProduct_list();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        product.clear();
        unlock(); 
        delete();
        clear();
        order_table.getSelectionModel().clearSelection();
        System.out.print("create!!");         
        create.setEnabled(false);              
        delete.setEnabled(true);
        showid_txt.setText(createid);
        delete.setSelected(false);
        createnaja = true;
        deletenaja = false;
        JOptionPane.showMessageDialog(null,"คุณสามารถดูรายละเอียดการสั่งได้\nโดยการดับเบิ้ลคลิ๊กที่ตารางการสั่งสินค้า");
    }//GEN-LAST:event_createActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        clear();
        lock(); 
        order_table.getSelectionModel().clearSelection();
        productlisttoorder.clear();
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        while(model.getRowCount()>0){
                    model.removeRow(0);
        }
        sum = 0;
        price_txt.setText(""+sum);
        delete.setEnabled(false);
        create.setEnabled(true);
        create.setSelected(false);
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กที่ตารางการสั่ง\nเพื่อลบการสั่งสินค้าได้ครับ\n(หากการสั่งนั้นมีการรับสินค้าแล้วจะไม่สามารถลบได้)"); 
        deletenaja = true;
        createnaja = false;
    }//GEN-LAST:event_deleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void v_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v_txtActionPerformed
        System.out.println(v_txt.getSelectedItem().toString());
        if(v_txt.getSelectedIndex()==0){
           p_txt1.removeAllItems();;
           p_txt1.addItem(">>Choose<<");
        }else{
        for(int i =0;i<vendor.size();i++){
        System.out.print(vendor.get(i).getid());
        productlisttoorder.clear();
        DefaultTableModel model = (DefaultTableModel)product_table.getModel();
        while(model.getRowCount()>0){
                    model.removeRow(0);
        }
        sum = 0;
        price_txt.setText(""+sum);
        if(vendor.get(i).getname().equals(v_txt.getSelectedItem().toString())){
        fillcomboproduct(vendor.get(i).getid());
        System.err.println("eiei");
                       break;
            }
        }
        }
    }//GEN-LAST:event_v_txtActionPerformed

    private void p_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt1ActionPerformed
/*         boolean foundnull = false;
        if(deletenakub==true||adding == true){
           }else{
         findproduct(p_txt1.getSelectedItem().toString());
         boolean check = false;
         boolean confirm = false;
        if(p_txt1.getSelectedIndex()==0){ 
       }else{
           if(product.size()==0){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt1.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt1.setSelectedIndex(0);
                foundnull = true;
                }else{
               for(int i =0;i<amouth.length();i++){
                   if(Character.isDigit(amouth.charAt(i))==false){
                       checkint = false;
                       JOptionPane.showMessageDialog(null,"Please input integer.");
                       break;
                   }
               }
                }
               }while(checkint != true);
               if(foundnull==true){
               JOptionPane.showMessageDialog(null,"Denided Process!\nUnits can't empty.",null,JOptionPane.ERROR_MESSAGE);
               }else{
               if(Integer.parseInt(amouth)==0){
                JOptionPane.showMessageDialog(null,"You can't type zero unit for ordering!",null,JOptionPane.ERROR_MESSAGE);
                }else{
                  if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt1.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct((p_txt1.getSelectedItem().toString()));
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              price1 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt1.setEnabled(false);
               }else{
                   p_txt1.setSelectedIndex(0);
               }
           }else{ 
           for(int i =0;i<product.size();i++){
               if(p_txt1.getSelectedItem().toString().equals(product.get(i).getproduct())){
                   JOptionPane.showMessageDialog(null,"You were selected this product.\nPlease select another product");
                   check=true;
                   p_txt1.setSelectedIndex(0);
                   break;
       }
     }
           
               
           if(check==false){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt1.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt1.setSelectedIndex(0);
                foundnull = true;
                }else{
               for(int i =0;i<amouth.length();i++){
                   if(Character.isDigit(amouth.charAt(i))==false){
                       checkint = false;
                       JOptionPane.showMessageDialog(null,"Please input integer.");
                       break;
                   }
               }
               }
               }while(checkint != true);
               if(foundnull==true){
                   JOptionPane.showMessageDialog(null,"Denided Process!\nUnits can't empty.",null,JOptionPane.ERROR_MESSAGE);
               }else{
                   
               if(Integer.parseInt(amouth)==0){
                JOptionPane.showMessageDialog(null,"You can't type zero unit for ordering!",null,JOptionPane.ERROR_MESSAGE);
                }else{
                if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt1.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               System.out.print(amouth);
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct(p_txt1.getSelectedItem().toString());
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              productname = (p_txt1.getSelectedItem().toString());
              price1 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt1.setEnabled(false);
           }else{
             p_txt1.setSelectedIndex(0); 
           }
           }
        }
        }
           System.out.print(productname);
           }*/
    }//GEN-LAST:event_p_txt1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(productlisttoorder.isEmpty()){
            System.err.println("Array was Empty.");
        }else{
        for(Product_variable p:productlisttoorder){
            System.out.print(p.getid()+" ");
            System.out.print(p.getname()+" ");
            System.out.print(p.getprice()+" ");
            System.out.print(p.getunit()+"\n");
        }
        }  
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        double units = 0;
        if(p_txt1.getSelectedIndex()!=0){
            try{
        for(Product_variable p:productlist){
            if(p_txt1.getSelectedItem().toString().equals(p.getname())){
                try{
                units = Double.parseDouble(JOptionPane.showInputDialog(null,"กรุณากรอกจำนวนที่สั่ง\nหน่วย "+p.getunit_type()));
                        }catch(Exception e){
                            throw new NullPointerException();
                        }
                Product_variable pv = new Product_variable();
                pv.setid(p.getid());
                pv.setname(p.getname());
                pv.setprice(p.getprice());
                pv.setunit(units);
                pv.setunits_type(p.getunit_type());
                pv.settotal_price(units*p.getprice());
                sum = sum+pv.gettotal_price();
                productlisttoorder.add(pv);
                p_txt1.removeItemAt(p_txt1.getSelectedIndex());
                System.out.print(p.getname());
            }
        }
                DefaultTableModel model = (DefaultTableModel)product_table.getModel();
                while(model.getRowCount()>0){
                    model.removeRow(0);
                }
                price_txt.setText(""+sum);
                showProduct_list();
                p_txt1.setSelectedIndex(0);
            }catch(NullPointerException e){
                
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void price_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_price_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_price_txtActionPerformed

    private void product_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_product_tableMouseClicked
        int index = 0;
        if(!productlisttoorder.isEmpty()){
        if(doubleclick == product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString()){
        if(JOptionPane.showConfirmDialog(null, "คุณต้องการที่จะลบ "+product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString()+" ",null,JOptionPane.YES_NO_OPTION)==YES_OPTION){
        try{
        for(Product_variable p:productlisttoorder){
            index ++;
        System.err.println(product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString());
        System.err.println(p.getid());
        if(product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString().equals(p.getid())){
            sum =0;
            price_txt.setText(""+sum);
            productlisttoorder.remove(index-1);
            DefaultTableModel model = (DefaultTableModel)product_table.getModel();
                while(model.getRowCount()>0){
                    model.removeRow(0);
                }
                showProduct_list();
                for(Vendor_variable v:vendor){
                    if(v_txt.getSelectedItem().toString().equals(v.getname())){
                        System.err.println(v.getid());
                        fillcomboproduct(v.getid());
                    }
                }
                doubleclick = null;
                JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
        }
        }
        }catch(Exception e){
            System.err.println(e);
        }
        }else{
            doubleclick = null;
            JOptionPane.showMessageDialog(null,"ยกเลิกรายการ");
        }
        }else{
            doubleclick = product_table.getModel().getValueAt(product_table.getSelectedRow(),0).toString();
        }
        }else{
            System.err.println("ไม่มีอะไรเกิดขึ้น");
        }
    }//GEN-LAST:event_product_tableMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(Window_Activated ==0){
            JOptionPane.showMessageDialog(null,"หลังจากที่คุณได้เพิ่มวัตถุดิบที่จะสั่ง\nคุณสามารถลบได้โดยการดับเบิ้ลคลิ๊ก\nที่ตารางวัตถุดิบที่แสดง\nคุณสามารถดูหน้าต่างนี้ได้\n\n<โดยการกดปุ่ม ? ในตาราง>");
                    Window_Activated = 1;
        }else if(Window_Activated==1){
            JOptionPane.showMessageDialog(null,"คุณสามารถดูรายละเอียดการสั่งได้\nโดยการดับเบิ้ลคลิ๊กที่ตารางการสั่งสินค้า");
            Window_Activated = 2;
        }
    }//GEN-LAST:event_formWindowActivated

    private void help_product_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_help_product_btnActionPerformed
           JOptionPane.showMessageDialog(null,"หลังจากที่คุณได้เพิ่มวัตถุดิบที่จะสั่ง\nคุณสามารถลบได้โดยการดับเบิ้ลคลิ๊ก\nที่ตารางวัตถุดิบที่แสดง");
    }//GEN-LAST:event_help_product_btnActionPerformed

    private void help_product_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_help_product_btn1ActionPerformed
        if(createnaja==true){
        JOptionPane.showMessageDialog(null,"คุณสามารถดูรายละเอียดการสั่งได้\nโดยการดับเบิ้ลคลิ๊กที่ตารางการสั่งสินค้า");
        }else if(deletenaja==true){
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กที่ตารางการสั่ง\nเพื่อลบการสั่งสินค้าได้ครับ\n(หากการสั่งนั้นมีการรับสินค้าแล้วจะไม่สามารถลบได้)"); 
        }
    }//GEN-LAST:event_help_product_btn1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        for(Product_Order_Variable p :Product_Order_Receive_Array){
            System.out.println(p.getreceiveid());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(Product_Order_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product_Order_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product_Order_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product_Order_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Product_Order_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JTextField emp_txt;
    private javax.swing.JButton help_product_btn;
    private javax.swing.JButton help_product_btn1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable order_table;
    private javax.swing.JComboBox<String> p_txt1;
    private datechooser.beans.DateChooserCombo pay_date;
    private javax.swing.JTextField price_txt;
    private javax.swing.JTable product_table;
    private datechooser.beans.DateChooserCombo receive_date;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JComboBox<String> v_txt;
    // End of variables declaration//GEN-END:variables
}
