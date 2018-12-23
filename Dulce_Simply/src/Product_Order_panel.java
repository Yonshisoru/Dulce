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
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Product_Order_panel extends javax.swing.JFrame {
    boolean viewnaja = false;
    boolean deletenakub = false;
    boolean createnaja = true;
    boolean deletenaja = false;
    boolean adding = false;
    boolean pass = false;
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    public String id = null;
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    String output = null;
    String menu = null;
    String password= null;
    String createid = null;
    String productname = null;
    String orderpaymentid = null;
    String receiveid = null;
    int max = 0;
    String oldpaydate = null;
    String oldrecdate = null;
    String checkview = null;
    int oldprice = 0;
    int currentprice = 0;
    String productid = null;
    int totalorder = 0;
    int productprice = 0;
    int sum = 0;
    int price1  =0;
    int price2 = 0;
    int price3 = 0;
    int price4 = 0;
    int price5 = 0;
    int price6 = 0;
    String empid = e.getshowid();
String year = ""+(Integer.parseInt(LocalDate.now().toString().substring(0,4))+543);
String month = LocalDate.now().toString().substring(5,7);
String day = LocalDate.now().toString().substring(8,10);
String now = month+"/"+day+"/"+year;
ArrayList<Product_Order_Variable> product = new ArrayList<Product_Order_Variable>();
ArrayList<Product_variable> product_id = new ArrayList<Product_variable>();
ArrayList<Employee> employee = new ArrayList<Employee>();
ArrayList<Vendor_variable> vendor = new ArrayList<Vendor_variable>();
    /**
     * Creates new form Employee_create
     */
    public Product_Order_panel() {
        initComponents();
        show_order();
        id();
        fillcombovendor();
        locked.setVisible(false);
        oldpaydate = pay_date.getText();
        oldrecdate = receive_date.getText();
        paydate.setText(oldpaydate);
        receivedate.setText(oldpaydate);
        emp_txt.setText(empid);
    }
    public void clear(){
        paydate.setText(oldpaydate);
        receivedate.setText(oldpaydate);
        v_txt.setSelectedIndex(0);
        oldpaydate = pay_date.getText();
        oldrecdate = receive_date.getText();
        emp_txt.setText(empid);
        setdate();
         showid_txt.setText(createid);
         price_txt.setText("");
         p_txt1.setEnabled(true);
         p_txt2.setEnabled(true);
         p_txt3.setEnabled(true);
         p_txt4.setEnabled(true);
         p_txt5.setEnabled(true);
         p_txt6.setEnabled(true);
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
        receivedate.setText(receive_date.getText());
        paydate.setText(pay_date.getText());
}catch(Exception e){
    System.out.print(e);
    }
}
    
    public void lock(){
        setdate();
        locked.setVisible(true);
        pay_date.setVisible(false);
        receive_date.setVisible(false);
         v_txt.setEnabled(false);
         price_txt.setEnabled(false);
         p_txt1.setEnabled(false);
         p_txt2.setEnabled(false);
         p_txt3.setEnabled(false);
         p_txt4.setEnabled(false);
         p_txt5.setEnabled(false);
         p_txt6.setEnabled(false);
         /*receive_date.setEnabled(false);
         pay_date.setEnabled(false);*/
    }
    public void unlock(){
        setdate();
        locked.setVisible(false);
        pay_date.setVisible(true);
        receive_date.setVisible(true);
         v_txt.setEnabled(true);
         price_txt.setEnabled(true);
         receive_date.setEnabled(true);
         pay_date.setEnabled(true);
         p_txt1.setEnabled(true);
         p_txt2.setEnabled(true);
         p_txt3.setEnabled(true);
         p_txt4.setEnabled(true);
         p_txt5.setEnabled(true);
         p_txt6.setEnabled(true);
    }
public String findorderpayment(String id){
            String find = "SELECT OP_NUMBER FROM ORDER_PAYMENT WHERE PO_ID = '"+id+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
                orderpaymentid = rs.getString("OP_NUMBER");
                System.out.print(orderpaymentid);
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return orderpaymentid;
  }

public String findreceive(String id){
            String find = "SELECT PR_ID FROM PRO_RECEIVE WHERE PO_ID = '"+id+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
                receiveid = rs.getString("PR_ID");
                System.out.print(receiveid);
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return receiveid;
  }
public String findempid(String id){
            String find = "SELECT EMP_ID FROM PRO_ORDER WHERE PO_ID = '"+id+"'";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
                empid = rs.getString("EMP_ID");
                System.out.print(empid);
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){ 
            System.out.print(e);
        } 
        return empid;
  }
public void delete(){
    deletenakub = true;
    p_txt1.removeAllItems();
         p_txt2.removeAllItems();
         p_txt3.removeAllItems();    
         p_txt4.removeAllItems();
         p_txt5.removeAllItems();
         p_txt6.removeAllItems();
         p_txt1.addItem(">>Choose<<");
         p_txt2.addItem(">>Choose<<");
         p_txt3.addItem(">>Choose<<");
         p_txt4.addItem(">>Choose<<");
         p_txt5.addItem(">>Choose<<");
         p_txt6.addItem(">>Choose<<");
         deletenakub=false;
}
  public String id(){
       int count=0;
       max = 0;
          String sql  ="select PO_ID from PRO_ORDER";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
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
    con.close();
    pat.close();
    rs.close();
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
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
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
    con.close();
    pat.close();
    rs.close();
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
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
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
    con.close();
    pat.close();
    rs.close();
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
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
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
    con.close();
    pat.close();
    rs.close();
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
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
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
    con.close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
    void fillcombovendor(){
      try{
          String sql = "SELECT V_ID,V_NAME FROM VENDOR WHERE V_DEL = 'N'";
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
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
        con.close();
      }catch(Exception e){
          
      }
  }
    void fillcomboproduct(String id){
      try{
          String sql = "SELECT PRO_ID,V_ID,PRO_NAME,PRO_PRICE FROM PRODUCT WHERE V_ID ='"+id+"' AND PRO_DEL = 'N'";
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            p_txt1.addItem(rs.getString("PRO_NAME"));
            p_txt2.addItem(rs.getString("PRO_NAME"));
            p_txt3.addItem(rs.getString("PRO_NAME"));
            p_txt4.addItem(rs.getString("PRO_NAME"));
            p_txt5.addItem(rs.getString("PRO_NAME"));
            p_txt6.addItem(rs.getString("PRO_NAME"));
            Product_variable p = new Product_variable();
            p.setid(rs.getString("PRO_ID"));
            p.setname(rs.getString("PRO_NAME"));
            p.setprice(Integer.parseInt(rs.getString("PRO_PRICE")));
            product_id.add(p);
        }
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){
          
      }
  }
   public ArrayList<Product_Order_Variable>Product_order_List(){
               ArrayList<Product_Order_Variable> Product_order_list = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select PO_ID,EMP_ID,EMP_FNAME,EMP_LNAME,V_ID,V_NAME,PO_DATE,PO_PRICE,PO_REC_DATE,PO_PAY_DATE,PO_UNITS,PR_STATUS,OP_STATUS FROM (((PRO_ORDER NATURAL JOIN EMPLOYEE)NATURAL JOIN VENDOR)NATURAL JOIN PRO_RECEIVE)NATURAL JOIN ORDER_PAYMENT WHERE PO_DEL = 'N' ORDER BY PO_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Product_Order_Variable p = new Product_Order_Variable();
            p.setid(rs.getString("PO_ID"));
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
            Product_order_list.add(p);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Product_order_list;
}
    public void show_order(){
        ArrayList<Product_Order_Variable>Product_order_list = Product_order_List();
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        Object[] row = new Object[10];
        for(int i=0;i<Product_order_list.size();i++){
            row[0]=Product_order_list.get(i).getdate();
            row[1]=Product_order_list.get(i).getid();
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

        locked = new javax.swing.JPanel();
        receivedate = new javax.swing.JTextField();
        paydate = new javax.swing.JTextField();
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
        p_txt6 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        p_txt2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        p_txt1 = new javax.swing.JComboBox<>();
        p_txt4 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        p_txt5 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        p_txt3 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        emp_txt = new javax.swing.JTextField();
        view = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1530, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        locked.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        receivedate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        receivedate.setText("jTextField2");
        receivedate.setEnabled(false);
        locked.add(receivedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 140, 30));

        paydate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paydate.setText("jTextField2");
        paydate.setEnabled(false);
        paydate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paydateActionPerformed(evt);
            }
        });
        locked.add(paydate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 140, 30));

        getContentPane().add(locked, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 160, 110));

        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "ID", "Total", "Price", "Receive date", "Pay date", "Employee", "Vendor", "Pay Status", "Receive Status"
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 900, 680));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Vendor:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

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

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Pay Date:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Function:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, -1));

        jLabel13.setText("Delete");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, -1, -1));

        jLabel14.setText("Create");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Employee:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, -1, -1));
        getContentPane().add(receive_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, -1, 30));
        getContentPane().add(pay_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, -1, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Receive Date:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, -1, -1));

        v_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        v_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                v_txtActionPerformed(evt);
            }
        });
        getContentPane().add(v_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 130, 30));

        price_txt.setEditable(false);
        price_txt.setEnabled(false);
        getContentPane().add(price_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 110, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Price:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, -1, -1));

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, -1, -1));

        p_txt6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt6ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 180, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Product:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Product:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        p_txt2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt2ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 180, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Product:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        p_txt1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt1ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 160, 30));

        p_txt4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt4ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 180, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Product:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, -1, -1));

        p_txt5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt5ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 160, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Product:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        p_txt3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose<<" }));
        p_txt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_txt3ActionPerformed(evt);
            }
        });
        getContentPane().add(p_txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 160, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Product:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        emp_txt.setEditable(false);
        emp_txt.setEnabled(false);
        getContentPane().add(emp_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 130, 30));

        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        getContentPane().add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, -1, -1));

        jLabel15.setText("View");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void order_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_tableMouseClicked
if(deletenaja==true){
        delete();
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
            }else if(productnaja==1){
                p_txt2.setSelectedItem(rs.getString("PRO_NAME"));
            }else if(productnaja==2){
                p_txt3.setSelectedItem(rs.getString("PRO_NAME"));
            }else if(productnaja==3){
                p_txt4.setSelectedItem(rs.getString("PRO_NAME"));
            }else if(productnaja==4){
                p_txt5.setSelectedItem(rs.getString("PRO_NAME"));
            }else if(productnaja==5){
                p_txt6.setSelectedItem(rs.getString("PRO_NAME"));
            }
            productnaja++;
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
            }
}
    }//GEN-LAST:event_order_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String orderid = showid_txt.getText();
        String receive = receive();
        String orderpayment = orderpayment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(createnaja==true){
        if(product.isEmpty()){
            JOptionPane.showMessageDialog(null,"Product was empty.\nPlease choose product for ordering.");
        }else{
        String id = showid_txt.getText();
        String emp = emp_txt.getText();
        String vendornaja = vendor.get(v_txt.getSelectedIndex()-1).getid();
        System.out.print(vendornaja);
        String price = price_txt.getText();
        String recdate = sdf.format(receive_date.getSelectedDate().getTime());
        String paydate = sdf.format(pay_date.getSelectedDate().getTime());
            String create = "INSERT INTO PRO_ORDER VALUE('"+orderid+"','"+LocalDate.now()+"','"+price+"','"+recdate+"','"+paydate+"','"+product.size()+"','"+emp+"','"+vendornaja+"','N')";  
            String createorder = "INSERT ORDER_PAYMENT VALUE('"+orderpayment+"','"+orderid+"','"+vendornaja+"','"+paydate+"','N','N')";
            String createreceive = "INSERT PRO_RECEIVE VALUE('"+receive+"','"+recdate+"','N','"+emp+"','"+orderid+"','N')";
            System.out.println(create);
            System.out.println(createorder);
            System.out.println(createreceive);
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(create);
        pat.executeUpdate(create);
        //JOptionPane.showMessageDialog(null,"Crerate order success!");
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(createorder);
        pat.executeUpdate(createorder);
        //JOptionPane.showMessageDialog(null,"Crerate orderpayment success!");
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
      try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(createreceive);
        pat.executeUpdate(createreceive);
        //JOptionPane.showMessageDialog(null,"Crerate orderreceive success!");
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
            for(int i =0;i<product.size();i++){
        findproduct(product.get(i).getproduct());
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
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        product.clear();
        delete();
        clear();
        showid_txt.setText(createid);
        order_table.getSelectionModel().clearSelection();
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
        view.setEnabled(true);
        showid_txt.setText(createid);
        delete.setSelected(false);
        view.setSelected(false);
        createnaja = true;
        deletenaja = false;
        viewnaja = false;
    }//GEN-LAST:event_createActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        clear();
        lock(); 
        order_table.getSelectionModel().clearSelection();
        System.out.print("delete!!");
        delete.setEnabled(false);
        create.setEnabled(true);
        view.setEnabled(true);
        create.setSelected(false);
        view.setSelected(false);
        deletenaja = true;
        createnaja = false;
        viewnaja = false;
    }//GEN-LAST:event_deleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void v_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_v_txtActionPerformed
        System.out.println(v_txt.getSelectedItem().toString());
        for(int i =0;i<vendor.size();i++){
            System.out.print(vendor.get(i).getid());
            if(vendor.get(i).getname().equals(v_txt.getSelectedItem().toString())){
                       fillcomboproduct(vendor.get(i).getid()); 
                       break;
            }
        }
    }//GEN-LAST:event_v_txtActionPerformed

    private void paydateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paydateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paydateActionPerformed

    private void p_txt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt6ActionPerformed
         boolean foundnull = false;
        if(deletenakub==true||adding == true){
           }else{
         findproduct(p_txt6.getSelectedItem().toString());
         boolean check = false;
         boolean confirm = false;
        if(p_txt6.getSelectedIndex()==0){ 
       }else{
           if(product.size()==0){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt6.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt6.setSelectedIndex(0);
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
                  if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt6.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct((p_txt6.getSelectedItem().toString()));
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              price6 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt6.setEnabled(false);
               }else{
                   p_txt6.setSelectedIndex(0);
               }
           }else{ 
           for(int i =0;i<product.size();i++){
               if(p_txt6.getSelectedItem().toString().equals(product.get(i).getproduct())){
                   JOptionPane.showMessageDialog(null,"You were selected this product.\nPlease select another product");
                   check=true;
                   p_txt6.setSelectedIndex(0);
                   break;
       }
     }      
           if(check==false){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt6.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt6.setSelectedIndex(0);
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
                if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt6.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               System.out.print(amouth);
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct(p_txt6.getSelectedItem().toString());
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              productname = (p_txt6.getSelectedItem().toString());
              price6 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt6.setEnabled(false);
           }else{
             p_txt6.setSelectedIndex(0); 
           }
           }
        }
        }
           System.out.print(productname);
           }
    }//GEN-LAST:event_p_txt6ActionPerformed

    private void p_txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt2ActionPerformed
         boolean foundnull = false;
        if(deletenakub==true||adding == true){
           }else{
         findproduct(p_txt2.getSelectedItem().toString());
         boolean check = false;
         boolean confirm = false;
        if(p_txt2.getSelectedIndex()==0){ 
       }else{
           if(product.size()==0){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt2.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt2.setSelectedIndex(0);
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
                  if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt2.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct((p_txt2.getSelectedItem().toString()));
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              price2 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt2.setEnabled(false);
               }else{
                   p_txt2.setSelectedIndex(0);
               }
           }else{ 
           for(int i =0;i<product.size();i++){
               if(p_txt2.getSelectedItem().toString().equals(product.get(i).getproduct())){
                   JOptionPane.showMessageDialog(null,"You were selected this product.\nPlease select another product");
                   check=true;
                   p_txt2.setSelectedIndex(0);
                   break;
       }
     }
           
               
           if(check==false){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt2.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt2.setSelectedIndex(0);
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
                if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt2.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               System.out.print(amouth);
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct(p_txt2.getSelectedItem().toString());
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              productname = (p_txt2.getSelectedItem().toString());
              price2 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt2.setEnabled(false);
           }else{
             p_txt2.setSelectedIndex(0); 
           }
           }
        }
        }
           System.out.print(productname);
           }
    }//GEN-LAST:event_p_txt2ActionPerformed

    private void p_txt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt1ActionPerformed
         boolean foundnull = false;
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
           }
    }//GEN-LAST:event_p_txt1ActionPerformed

    private void p_txt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt4ActionPerformed
          boolean foundnull = false;
        if(deletenakub==true||adding == true){
           }else{
         findproduct(p_txt4.getSelectedItem().toString());
         boolean check = false;
         boolean confirm = false;
        if(p_txt4.getSelectedIndex()==0){ 
       }else{
           if(product.size()==0){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt4.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt4.setSelectedIndex(0);
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
                  if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt4.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct((p_txt4.getSelectedItem().toString()));
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              price4 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt4.setEnabled(false);
               }else{
                   p_txt4.setSelectedIndex(0);
               }
           }else{ 
           for(int i =0;i<product.size();i++){
               if(p_txt4.getSelectedItem().toString().equals(product.get(i).getproduct())){
                   JOptionPane.showMessageDialog(null,"You were selected this product.\nPlease select another product");
                   check=true;
                   p_txt4.setSelectedIndex(0);
                   break;
       }
     }
           
               
           if(check==false){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt4.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt4.setSelectedIndex(0);
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
                if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt4.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               System.out.print(amouth);
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct(p_txt4.getSelectedItem().toString());
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              productname = (p_txt4.getSelectedItem().toString());
              price4 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt4.setEnabled(false);
           }else{
             p_txt4.setSelectedIndex(0); 
           }
           }
        }
        }
           System.out.print(productname);
           }
    }//GEN-LAST:event_p_txt4ActionPerformed

    private void p_txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt5ActionPerformed
          boolean foundnull = false;
        if(deletenakub==true||adding == true){
           }else{
         findproduct(p_txt5.getSelectedItem().toString());
         boolean check = false;
         boolean confirm = false;
        if(p_txt5.getSelectedIndex()==0){ 
       }else{
           if(product.size()==0){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt5.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt5.setSelectedIndex(0);
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
                  if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt5.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct((p_txt5.getSelectedItem().toString()));
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              price5 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt5.setEnabled(false);
               }else{
                   p_txt5.setSelectedIndex(0);
               }
           }else{ 
           for(int i =0;i<product.size();i++){
               if(p_txt5.getSelectedItem().toString().equals(product.get(i).getproduct())){
                   JOptionPane.showMessageDialog(null,"You were selected this product.\nPlease select another product");
                   check=true;
                   p_txt5.setSelectedIndex(0);
                   break;
       }
     }
           
               
           if(check==false){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt5.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt5.setSelectedIndex(0);
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
                if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt5.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               System.out.print(amouth);
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct(p_txt5.getSelectedItem().toString());
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              productname = (p_txt5.getSelectedItem().toString());
              price5 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt5.setEnabled(false);
           }else{
             p_txt5.setSelectedIndex(0); 
           }
           }
        }
        }
           System.out.print(productname);
           }
    }//GEN-LAST:event_p_txt5ActionPerformed

    private void p_txt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_txt3ActionPerformed
         boolean foundnull = false;
        if(deletenakub==true||adding == true){
           }else{
         findproduct(p_txt3.getSelectedItem().toString());
         boolean check = false;
         boolean confirm = false;
        if(p_txt3.getSelectedIndex()==0){ 
       }else{
           if(product.size()==0){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt3.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt3.setSelectedIndex(0);
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
                  if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt3.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct((p_txt3.getSelectedItem().toString()));
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              price3 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt3.setEnabled(false);
               }else{
                   p_txt3.setSelectedIndex(0);
               }
           }else{ 
           for(int i =0;i<product.size();i++){
               if(p_txt3.getSelectedItem().toString().equals(product.get(i).getproduct())){
                   JOptionPane.showMessageDialog(null,"You were selected this product.\nPlease select another product");
                   check=true;
                   p_txt3.setSelectedIndex(0);
                   break;
       }
     }
           
               
           if(check==false){
               boolean checkint = true;
               String amouth;
               do{
               JOptionPane.showMessageDialog(null,p_txt3.getSelectedItem().toString()+"'s cost is "+productprice+" Baht/unit.");
               amouth = JOptionPane.showInputDialog(null,"Please input amount of product");
                if(amouth  == null){  
                p_txt3.setSelectedIndex(0);
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
                if(JOptionPane.showConfirmDialog(null,"Product:"+p_txt3.getSelectedItem().toString()+"\nAmount:"+amouth+"\nPrice:"+productprice+"\nTotal:"+(productprice*Integer.parseInt(amouth)),"",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    confirm = true;
                }else{
                    confirm = false;
                }
               }
               }
               if(confirm==true){
               System.out.print(amouth);
               Product_Order_Variable p = new Product_Order_Variable();
               p.setproduct(p_txt3.getSelectedItem().toString());
               p.setproductunit(Integer.parseInt(amouth));
               product.add(p);
              productname = (p_txt3.getSelectedItem().toString());
              price3 = Integer.parseInt(amouth)*productprice;
              sum = price1+price2+price3+price4+price5+price6;
              System.out.print("current ="+currentprice+"old ="+oldprice+"sum ="+sum);
              price_txt.setText(""+sum);
              p_txt3.setEnabled(false);
           }else{
             p_txt3.setSelectedIndex(0); 
           }
           }
        }
        }
           System.out.print(productname);
           }
    }//GEN-LAST:event_p_txt3ActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed

        clear();
        lock(); 
        order_table.getSelectionModel().clearSelection();
        JOptionPane.showMessageDialog(null,"Double Click Row of Table for view details of Order!");
        delete.setEnabled(true);
        create.setEnabled(true);
        view.setEnabled(false);
        create.setSelected(false);
        delete.setSelected(false);
        deletenaja = false;
        createnaja = false;
        viewnaja = true;
    }//GEN-LAST:event_viewActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel locked;
    private javax.swing.JTable order_table;
    private javax.swing.JComboBox<String> p_txt1;
    private javax.swing.JComboBox<String> p_txt2;
    private javax.swing.JComboBox<String> p_txt3;
    private javax.swing.JComboBox<String> p_txt4;
    private javax.swing.JComboBox<String> p_txt5;
    private javax.swing.JComboBox<String> p_txt6;
    private datechooser.beans.DateChooserCombo pay_date;
    private javax.swing.JTextField paydate;
    private javax.swing.JTextField price_txt;
    private datechooser.beans.DateChooserCombo receive_date;
    private javax.swing.JTextField receivedate;
    private javax.swing.JTextField showid_txt;
    private javax.swing.JComboBox<String> v_txt;
    private javax.swing.JRadioButton view;
    // End of variables declaration//GEN-END:variables
}
