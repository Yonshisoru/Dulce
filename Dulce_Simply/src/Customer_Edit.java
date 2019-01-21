
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
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
public class Customer_Edit extends javax.swing.JFrame {
Database d = new Database();
Table_variable t = new Table_variable();
Employee e = new Employee();
Customer_variable c= new Customer_variable();
//-------------------------------------------------
ArrayList<Stock_Variable>Stock_Array = new ArrayList<>();
ArrayList<Stock_Variable>Stock_Units = new ArrayList<>();
ArrayList<Product_variable>Menu_Ingredient_Array = new ArrayList<>();
ArrayList<Menu_variable>Menu_Array = new ArrayList<>();
ArrayList<Menu_variable>Menu_List_Array = new ArrayList<>();
ArrayList<Menu_variable>Promotion_List_Array = new ArrayList<>();
ArrayList<Menu_variable>Ordering_Array = new ArrayList<>();
ArrayList<Menu_variable>Order_List_Array = new ArrayList<>();
ArrayList<Menu_variable>edit_Order_List_Array = new ArrayList<>();
ArrayList<Menu_variable>adding_Order_List_Array = new ArrayList<>();
ArrayList<Promotion_variable>Promotion_Array = new ArrayList<>();
ArrayList<Promotion_variable>Promotion_Using_Array = new ArrayList<>();
ArrayList<Integer>Integer_Array= new ArrayList<>();
//-----------------------------------------------------------
Connection con = null;
PreparedStatement pat = null;
ResultSet rs = null;
//-------------------------------------------------------------
String menu_doubleclick = "";
String promotion_doubleclick = "";
String order_doubleclick = "";
String output = "";
String orderid = "";
//---------------------------------------------------------------
int count = 1;
int max = 0;
int totalmenu = 0;
//------------------------------------------------------------------
double totalprice =0;
double stock_units = 0.0;
//------------------------------------------------------------------
boolean pro_using = false;
    /**
     * Creates new form Customer_Edit
     */
    public Customer_Edit() {
        initComponents();
        getMenu_Catagory();
        getMenu();
        showmenu_table();
        getPromotion();
        getPromotion_Menu();
        showpromotion_table();
        getIngredient();
        getStock();
        if(c.getmaintenance()==true){
            t.setid("01");
        }else{
        }
        getorder();
        getorder_menu();
        table_number_txt.setText(t.getid());
        showorder_table();
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
public void deletefromorderlistarray(String promotion){
    boolean check = false;
    Menu_variable mm = null;
    /*for(Menu_variable m:edit_Order_List_Array){
        if(m.p.getname().equals(promotion)){
            Integer_Array.add(m.getmenu_number());
        }
    }*/
    //try{
    for(int i =edit_Order_List_Array.size()-1;i>=0;i--){
        if(edit_Order_List_Array.get(i).p.getname()==null){
            
        }else{
        if(edit_Order_List_Array.get(i).p.getname().equals(promotion)){
            System.out.println(edit_Order_List_Array.get(i).c.getorder_menu_id());
            totalprice -= edit_Order_List_Array.get(i).c.gettotal();
            edit_Order_List_Array.remove(i);
            /*if(adding_Order_List_Array!=null){
                adding_Order_List_Array.remove(i);
            }*/
            //adding_Order_List_Array
            count--;
        }
        }
    }
    //}catch(Exception e){
        //System.out.println(e);
    //}
                /*if(mv.p.getname().equals(promotion)){
                    check = true;
                    mm = mv;
                }
            }
            System.out.println(check);
            if(check==true){
                //edit_Order_List_Array.remove(mm);
                //System.out.println(edit_Order_List_Array.indexOf(m));
            System.out.println(m.getmenu_number());
            System.out.println(m.getname());
            System.out.println(m.p.getname());
            }*/
            //edit_Order_List_Array.remove(m);
}
public String getorderid(){
    int count=0;
    max = 0;
    String sql  ="select ORD_ID from ORDERING";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("ORD_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("ORD_ID").substring(1,4));
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
    rs.close();
    pat.close();
    getcon().close();
    }catch(Exception e){
                e.printStackTrace();
    }
    return output;
   }
public String getorderlistid(){
    int count=0;
    max = 0;
    String sql  ="select OM_ID from ORDER_MENU_LIST";
    try{
    pat = getcon().prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("OM_ID"))>max){
            max = Integer.parseInt(rs.getString("OM_ID"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    output =""+max;
    rs.close();
    pat.close();
    getcon().close();
    }catch(Exception e){
                e.printStackTrace();
    }
    return output;
   }
    public void getMenu_Catagory(){
        String sql = "Select M_T_ID,M_T_NAME FROM MENU_TYPE WHERE M_T_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Menu_variable m = new Menu_variable();
                m.setcataid(rs.getString("M_T_ID"));
                m.setcataname(rs.getString("M_T_NAME"));
                Menu_List_Array.add(m);
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
        System.out.println(sql);
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
                for(Menu_variable mv:Menu_List_Array){
                    if(mv.getcataid().equals(m.getcataid())){
                        m.setcataname(mv.getcataname());
                        break;
                    }
                }
                Menu_Array.add(m);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            
        }
    }
    public void getPromotion(){
        String sql = "Select PN_ID,PN_NAME,PN_S_DATE,PN_E_DATE FROM PROMOTION WHERE PN_DEL = 'N' AND PN_E_DATE > '"+LocalDate.now()+"' AND PN_S_DATE <= '"+LocalDate.now()+"'";
        System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Promotion_variable p = new Promotion_variable();
                p.setid(rs.getString("PN_ID"));
                p.setname(rs.getString("PN_NAME"));
                p.setstartdate(rs.getString("PN_S_DATE"));
                p.setenddate(rs.getString("PN_E_DATE"));
                Promotion_Array.add(p);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            
        }
    }
    public void getPromotion_Menu(){
        String sql = "Select PM_ID,PN_ID,MENU_ID,PM_DISCOUNT FROM PROMOTION_MENU WHERE PM_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                Menu_variable p = new Menu_variable();
                p.p.setmenunumber(rs.getString("PM_ID"));
                p.p.setid(rs.getString("PN_ID"));
                p.setid(rs.getString("MENU_ID"));
                int k = (int)(rs.getDouble("PM_DISCOUNT")*(double)100);
                p.p.setdiscount(k);
                Promotion_List_Array.add(p);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            
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
public void getorder(){
        String sql = "Select ORD_ID,EMP_ID,T_ID,ORD_DATE,ORD_TOTAL,ORD_PAYTYPE FROM ORDERING";
        //System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                //System.out.println(t.getid());
                //System.out.println(rs.getString("T_ID"));
                if(t.getid().equals(rs.getString("T_ID"))){
                    orderid = rs.getString("ORD_ID");
                    System.out.println(orderid);
                }
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
                if(orderid.equals(rs.getString("ORD_ID"))){
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
                if(rs.getString("OM_PROMOTION").equals("null")){
                   m.p.setname(""); 
                }else{
                   m.p.setname(rs.getString("OM_PROMOTION")); 
                }
                m.c.setorder_menu_status(rs.getString("OM_STATUS"));
                Order_List_Array.add(m);
                for(Menu_variable mm:Ordering_Array){
                    edit_Order_List_Array.clear();
                    if(mm.t.getid().equals(t.getid())&&mm.c.getpaytype().isEmpty()){
                        orderid = m.c.getorderid();
                        m.setmenu_number(count);
                        for(Menu_variable mmm:Order_List_Array){
                            edit_Order_List_Array.add(mmm);
                        }
                        count++;
                    }
                    break;
                }
                }
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            
        }
        for(Menu_variable mmm:edit_Order_List_Array){
            totalprice += mmm.c.gettotal();
        }
        totalprice_txt.setText(String.format("%.2f",totalprice));
        totalmenu_txt.setText(""+Order_List_Array.size());
    }
    public void showmenu_table(){
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        Object[] row = new Object[4];
        for(int i =0;i<Menu_Array.size();i++){
            row[0] = Menu_Array.get(i).getid();
            row[1] = Menu_Array.get(i).getname();
            row[2] = Menu_Array.get(i).getprice();
            row[3] = Menu_Array.get(i).getcataname();   
            model.addRow(row);
        }
    }
    public void showpromotion_table(){
        DefaultTableModel model = (DefaultTableModel)promotion_table.getModel();
        Object[] row = new Object[4];
        for(int i =0;i<Promotion_Array.size();i++){
            row[0] = Promotion_Array.get(i).getid();
            row[1] = Promotion_Array.get(i).getname();
            row[2] = Promotion_Array.get(i).getstartdate();
            row[3] = Promotion_Array.get(i).getenddate();   
            model.addRow(row);
        }
    }
    public void showorder_table(){
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        Object[] row = new Object[6];
                for(Menu_variable m:edit_Order_List_Array){
                    row[0] = m.getmenu_number();
                    row[1] = m.getname();
                    row[2] = m.c.getunits();
                    row[3] = m.c.gettotal();
                    row[4] = m.p.getname();
                    row[5] = m.c.getorder_menu_status();
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

        jScrollPane3 = new javax.swing.JScrollPane();
        menu_table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        promotion_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        order_table = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalprice_txt = new javax.swing.JLabel();
        totalmenu_txt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        table_number_txt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1230, 730));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu_table.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        menu_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเมนู", "ชื่อเมนู", "ราคา", "หมวดหมู่"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menu_table.getTableHeader().setResizingAllowed(false);
        menu_table.getTableHeader().setReorderingAllowed(false);
        menu_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_tableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_tableMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(menu_table);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 460, 230));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ตารางเมนู");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ตารางโปรโมชั่น");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, -1, -1));

        promotion_table.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        promotion_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสโปรโมชั่น", "ชื่อโปรโมชั่น", "วันเริ่มต้น", "วันสิ้นสุด"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        promotion_table.getTableHeader().setResizingAllowed(false);
        promotion_table.getTableHeader().setReorderingAllowed(false);
        promotion_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                promotion_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(promotion_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 460, 230));

        order_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รายการที่", "ชื่อ", "จำนวน", "ราคา", "หมายเหตุ", "สถานะ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        order_table.getTableHeader().setResizingAllowed(false);
        order_table.getTableHeader().setReorderingAllowed(false);
        order_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(order_table);
        if (order_table.getColumnModel().getColumnCount() > 0) {
            order_table.getColumnModel().getColumn(0).setResizable(false);
            order_table.getColumnModel().getColumn(0).setPreferredWidth(30);
            order_table.getColumnModel().getColumn(1).setResizable(false);
            order_table.getColumnModel().getColumn(2).setResizable(false);
            order_table.getColumnModel().getColumn(2).setPreferredWidth(30);
            order_table.getColumnModel().getColumn(3).setResizable(false);
            order_table.getColumnModel().getColumn(3).setPreferredWidth(50);
            order_table.getColumnModel().getColumn(4).setResizable(false);
            order_table.getColumnModel().getColumn(5).setResizable(false);
            order_table.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 640, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("ตารางออเดอร์");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("จำนวนรายการทั้งหมด:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("ราคารวม:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 540, -1, -1));

        totalprice_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalprice_txt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalprice_txt.setText("0.00");
        getContentPane().add(totalprice_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(785, 540, 100, -1));

        totalmenu_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalmenu_txt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalmenu_txt.setText("0");
        getContentPane().add(totalmenu_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 490, 90, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("รายการ");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 490, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("บาท");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 540, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("โต๊ะที่");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        table_number_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        table_number_txt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        table_number_txt.setText(" ");
        getContentPane().add(table_number_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 70, -1));

        jButton1.setText("ปิด");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 600, 140, 60));

        jButton2.setText("ยืนยัน");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 600, 140, 60));

        jButton3.setText("คืนค่า");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 140, 60));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jButton7.setText("?");
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 470, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseClicked
        if(menu_doubleclick.equals(menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString())){
            String menu_id = menu_doubleclick;
            String units = null;
            int menu_units = 0;
            try{
                units = JOptionPane.showInputDialog(null,"กรุณากรอกจำนวนของเมนูด้วยครับ");
                if(units == null){
                    throw new NullPointerException();
                }else{
                    menu_units = Integer.parseInt(units);
                    if(menu_units<0){
                        throw new NumberFormatException();
                    }
                }
                for(Menu_variable m:Menu_Array){
                    if(menu_id.equals(m.getid())){
                        //System.out.println(m.getid()+" "+m.getname()+" "+m.getprice());
                        Menu_variable b = new Menu_variable();
                        b.setmenu_number(count);
                        b.setid(m.getid());
                        b.setname(m.getname());
                        b.setprice(m.getprice());
                        b.c.setunits(menu_units);
                        b.c.settotal(m.getprice()*menu_units);
                        b.c.setorder_menu_status("N");
                        totalprice += b.p.gettotal();
                        edit_Order_List_Array.add(b);
                        adding_Order_List_Array.add(b);
                    }
                }
                DefaultTableModel model = (DefaultTableModel)order_table.getModel();
                while(model.getRowCount()>0){
                    model.removeRow(0);
                }
                showorder_table();
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null,"คุณยังไม่ได้กรอกจำนวน\nกรุณาทำรายการใหม่ด้วยครับ");
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"คุณกรอกจำนวนไม่ถูกต้อง\nกรุณาทำรายการใหม่ด้วยครับ");
            }
            count++;
            totalprice = 0;
            for(Menu_variable mmm:edit_Order_List_Array){
                totalprice += mmm.c.gettotal();
            }
            totalprice_txt.setText(String.format("%.2f",totalprice));
            totalmenu_txt.setText(""+edit_Order_List_Array.size());
            menu_doubleclick = "";
            menu_table.clearSelection();
        }else{
            menu_doubleclick = menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_menu_tableMouseClicked

    private void promotion_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_promotion_tableMouseClicked
        if(promotion_doubleclick.equals(promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),0).toString())){
            String promotionname = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),1).toString();
            boolean check = false;
            for(Menu_variable mp:edit_Order_List_Array){
                if(mp.p.getname().equals(promotionname)){
                    check=true;
                    break;
                }
            }
            if(check==false){
            for(Promotion_variable p:Promotion_Array){
                        Promotion_variable pp = new Promotion_variable();
                        pp.setid(p.getid());
                        pp.setname(p.getname());
                        Promotion_Using_Array.add(pp);
                        //System.out.print(p.getid()+" ");
                        //System.out.print(p.getname()+"\n");
                        for(Menu_variable mv:Promotion_List_Array){
                            if(promotion_doubleclick.equals(mv.p.getid())){
                                //System.out.print(mv.p.getmenunumber()+" ");
                                //System.out.print(mv.getid()+" ");
                                for(Menu_variable m:Menu_Array){
                                    if(mv.getid().equals(m.getid())){
                                        Menu_variable mm = new Menu_variable();
                                        mm.setmenu_number(count);
                                        mm.setid(m.getid());
                                        mm.setname(m.getname());
                                        mm.setprice(m.getprice());
                                        mm.p.setdiscount(mv.p.getdiscount());
                                        mm.p.setname(p.getname());
                                        mm.c.setunits(1);
                                        mm.c.settotal(m.getprice()*((double)mv.p.getdiscount()/100.0));
                                        mm.c.setorder_menu_status("N");
                                        edit_Order_List_Array.add(mm);
                                        adding_Order_List_Array.add(mm);
                                        //System.out.print(m.getname()+" ");
                                        //System.out.print(m.getprice()+" ");
                                        // System.out.print((double)mv.p.getdiscount()/100.0+" ");
                                        // System.out.print(m.getprice()*((double)mv.p.getdiscount()/100.0)+"\n");
                                        break;
                                    }
                                }
                                count++;
                            }
                        }
                        break;
                }
            DefaultTableModel model = (DefaultTableModel)order_table.getModel();
            while(model.getRowCount()>0){
                model.removeRow(0);
            }
            showorder_table();
            totalprice = 0;
            for(Menu_variable mmm:edit_Order_List_Array){
                totalprice += mmm.c.gettotal();
            }
            totalprice_txt.setText(String.format("%.2f",totalprice));
            totalmenu_txt.setText(""+edit_Order_List_Array.size());
            promotion_doubleclick = "";
            promotion_table.clearSelection();
            }else{
                JOptionPane.showMessageDialog(null,"คุณได้ใช้โปรโมชั่นนี้ไปแล้ว กรุณาทำรายการใหม่\n(ถ้าหากเมนูของโปรโมชั่นยังค้างอยู่ กรุณาลบโปรโมชั่นนั้นให้หมดด้วยครับ)");
            }
        }else{
            promotion_doubleclick = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_promotion_tableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        for(Menu_variable m:edit_Order_List_Array){
            System.out.print(m.getmenu_number()+" ");
            System.out.print(m.getid()+" ");
            System.out.print(m.getname()+" ");
            System.out.print(m.c.getunits()+" ");
            System.out.print(m.c.gettotal()+" ");
            System.out.print(m.p.getname()+"\n");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        count = 1;
        totalprice = 0;
        Order_List_Array.clear();
        edit_Order_List_Array.clear();
        adding_Order_List_Array.clear();
        getorder_menu();
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        while(model.getRowCount()>0){
        model.removeRow(0);
        }
        showorder_table();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        for(Menu_variable m:Order_List_Array){
            System.err.print(m.getid()+" ");
            System.err.print(m.getname()+" ");
            System.err.print(m.c.getunits()+" ");
            System.err.print(m.c.gettotal()+" ");
            System.err.print(m.p.getname()+"\n");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        for(Menu_variable m:adding_Order_List_Array){
            System.err.print(m.getid()+" ");
            System.err.print(m.getname()+" ");
            System.err.print(m.c.getunits()+" ");
            System.err.print(m.c.gettotal()+" ");
            System.err.print(m.p.getname()+"\n");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void order_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_tableMouseClicked
        if(order_doubleclick.equals(order_table.getValueAt(order_table.getSelectedRow(),0).toString())){
            if(order_table.getValueAt(order_table.getSelectedRow(),5).toString().equals("Y")){
                JOptionPane.showMessageDialog(null,"คุณไม่สามารถแก้ไขเมนูที่เสิร์ฟไปแล้วได้ กรุณาทำรายการใหม่ด้วยครับ",null,ERROR_MESSAGE);
            }else{
            if(JOptionPane.showConfirmDialog(null,"คุณต้องการที่จะลบเมนูรายการที่ "+(order_table.getSelectedRow()+1)+" ของโต๊ะ "+t.getid()+" หรือไม่",null,YES_NO_OPTION)==YES_OPTION){
                if(order_table.getValueAt(order_table.getSelectedRow(),4)==null){
                totalprice -= (double)order_table.getValueAt(order_table.getSelectedRow(),3);
                edit_Order_List_Array.remove(order_table.getSelectedRow());
                adding_Order_List_Array.remove(order_table.getSelectedRow());
                count--;
                }else{
                String promotion = order_table.getValueAt(order_table.getSelectedRow(),4).toString();
                deletefromorderlistarray(promotion);
                if(adding_Order_List_Array.isEmpty()==false){
                    for(int i = adding_Order_List_Array.size()-1;i>=0;i--){
                        if(adding_Order_List_Array.get(i).p.getname().equals(promotion)){
                            adding_Order_List_Array.remove(i);
                        }
                    }
                }
                }
                DefaultTableModel model = (DefaultTableModel)order_table.getModel();
                while(model.getRowCount()>0){
                model.removeRow(0);
                }       
                showorder_table();
                totalprice_txt.setText(String.format("%.2f",totalprice));
                totalmenu_txt.setText(""+edit_Order_List_Array.size());
            }
            order_doubleclick = "";
            }
        }else{
            order_doubleclick = order_table.getValueAt(order_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_order_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Table_panel t = new Table_panel();
        t.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(edit_Order_List_Array.isEmpty()){
            String sql = "UPDATE ORDERING SET ORD_DEL = 'Y' WHERE ORD_ID = '"+orderid+"'";
            String tablesql = "UPDATE TABLEZ SET T_STATUS = 'N' WHERE T_ID = '"+t.getid()+"'";
            System.out.println(sql);
            System.out.println(tablesql);
            try{
                pat = getcon().prepareStatement(sql);
                pat.executeUpdate(sql);
                pat.close();
                    pat = getcon().prepareStatement(tablesql);
                    pat.executeUpdate(tablesql);
                    pat.close();
                getcon().close();
            }catch(Exception e){
                System.out.println(e);
            }
            this.setVisible(false);
            Table_panel t = new Table_panel();
            t.setVisible(true);
        }else{
            boolean check = false;
            for(Menu_variable m:Order_List_Array){
                check = false;
                for(Menu_variable mm:edit_Order_List_Array){
                    if(m.c.getorder_menu_id().equals(mm.c.getorder_menu_id())){
                        check = true;
                        break;
                    }
                }
                    if(check==false){
                        String sql = "UPDATE ORDER_MENU_LIST SET OM_DEL = 'Y' WHERE OM_ID = '"+m.c.getorder_menu_id()+"'";
                        System.err.println(sql);
                        try{
                            pat = getcon().prepareStatement(sql);
                            pat.executeUpdate(sql);
                            pat.close();
                            getcon().close();
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    }
                }
            for(Menu_variable m:adding_Order_List_Array){
                 String sql = "INSERT INTO ORDER_MENU_LIST VALUES('"+getorderlistid()+"','"+orderid+"','"+m.getid()+"','"+m.c.getunits()+"','"+m.c.gettotal()+"','N','"+m.p.getname()+"','N')";
                 System.out.println(sql);
                 try{
                    pat = getcon().prepareStatement(sql);
                    pat.executeUpdate(sql);
                    pat.close();
                    getcon().close();
                 }catch(Exception e){
                    System.out.println(e);
                 }
            }
            JOptionPane.showMessageDialog(null,"แก้ไขออเดอร์เสร็จสิ้น");
            this.setVisible(false);
            Table_panel t = new Table_panel();
            t.setVisible(true);
                        /*for(Menu_variable m:Order_List_Array){
                            System.err.print(m.c.getorder_menu_id()+" ");
                            System.err.print(m.getid()+" ");
                            System.err.print(m.getname()+" ");
                            System.err.print(m.c.getunits()+" ");
                            System.err.print(m.c.gettotal()+" ");
                            System.err.print(m.p.getname()+" ");
                            System.err.print(m.c.getorder_menu_status()+"\n");
                        }
                        for(Menu_variable m:edit_Order_List_Array){
                            System.out.print(m.c.getorder_menu_id()+" ");
                            System.out.print(m.getid()+" ");
                            System.out.print(m.getname()+" ");
                            System.out.print(m.c.getunits()+" ");
                            System.out.print(m.c.gettotal()+" ");
                            System.out.print(m.p.getname()+" ");
                            System.out.print(m.c.getorder_menu_status()+"\n");
                        }*/
        }     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void menu_tableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_tableMouseEntered

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Table_panel t = new Table_panel();
        t.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Customer_Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_Edit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable menu_table;
    private javax.swing.JTable order_table;
    private javax.swing.JTable promotion_table;
    private javax.swing.JLabel table_number_txt;
    private javax.swing.JLabel totalmenu_txt;
    private javax.swing.JLabel totalprice_txt;
    // End of variables declaration//GEN-END:variables
}
