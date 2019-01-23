
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
public class Promotion extends javax.swing.JFrame {
//---------------------------------------------------
    ArrayList<Menu_variable> Menu_Array = new ArrayList<>();
    ArrayList<Menu_variable> Menu_List_Array = new ArrayList<>();
    ArrayList<Menu_variable> delete_Menu_List_Array = new ArrayList<>();
    ArrayList<Menu_variable> edit_Menu_List_Array = new ArrayList<>();
    ArrayList<Menu_variable> Promotion_Menu_List_Array = new ArrayList<>();
    ArrayList<Promotion_variable> Promotion_Array = new ArrayList<>();
//----------------------------------------------------  
    Database d = new Database();
    //----------------------
    boolean view = false;
    boolean create = true;
    boolean delete = false;
    boolean edit = false;
    //------------------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    //------------------------
    int max = 0;
    int doubleclick = 0;
    //------------------------
    String output = null;
    String Promotion_id = null;
    String doubleclickstring = "";
    String promotionid = null;
    String promotionname = null;
    /**
     * Creates new form Promotion
     */
    public Promotion() {
        initComponents();
        refresh_btn.setVisible(false);
        promotion_help_btn.setVisible(false);
        getPromotion();
        getPromotion_Table();
        getPromotion_ID();
        getMenu();
        setMenu_Combo();
        getMenu_List();
        print();
        for(Menu_variable m:Menu_List_Array){
            System.out.println(m.p.getmenunumber());
            System.out.println(m.p.getid());
            System.out.println(m.getid());
            System.out.println(m.p.getdiscount());
        }
    }
    public void unlock(){
        promotion_name_txt.setEnabled(true);
        menu_combo.setEnabled(true);
        menu_add_btn.setEnabled(true);
        startdate_txt.setEnabled(true);
        enddate_txt.setEnabled(true);
        setNowDate();
    }
    public void setNowDate(){
        String year = ""+(Integer.parseInt(LocalDate.now().toString().substring(0,4))+543);
        String month = LocalDate.now().toString().substring(5,7);
        String day = LocalDate.now().toString().substring(8,10);
        String now = month+"/"+day+"/"+year;
        System.err.println(LocalDate.now().toString());
        System.out.println(now);
        try{
        Calendar cal = Calendar.getInstance();
        java.util.Date date = new SimpleDateFormat("MM/dd/yy").parse(now);
        cal.setTime(date);
        startdate_txt.setSelectedDate(cal);
        enddate_txt.setSelectedDate(cal);
        }catch(Exception e){
            
        }
    }
    public void clear(){
        startdate_txt.setEnabled(true);
        enddate_txt.setEnabled(true);
        setNowDate();
        promotionid = null;
        promotionname = null;
        promotion_name_txt.setText("");
        Promotion_Menu_List_Array.clear();
        delete_Menu_List_Array.clear();
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        while(model.getRowCount()>0){
        model.removeRow(0);
        }
        promotion_table.clearSelection();
        promotionid_txt.setText("NAN");
        if(create==true){
            promotionid_txt.setText(Promotion_id);
        }
    }
    public void clearedit(){
         promotionid_txt.setText(promotionid);
         promotion_name_txt.setText(promotionname);  
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
public void refresh(){
    Promotion_Menu_List_Array.clear();
    Promotion_Menu_List_Array = edit_Menu_List_Array;
    DefaultTableModel menu = (DefaultTableModel)menu_table.getModel();
    while(menu.getRowCount()>0){
    menu.removeRow(0);
    }
    getPromotion_Menu_Table();
}
  public String getPromotion_ID(){
       int count=0;
       max = 0;
          String sql  ="select PN_ID from PROMOTION";
    try{
    pat = getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PN_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("PN_ID").substring(1,4));
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
    promotionid_txt.setText(output);
    Promotion_id = output;
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
    public String getPromotion_List_ID(){
       int count=0;
       max = 0;
          String sql  ="select PM_ID from PROMOTION_MENU";
    try{
    pat = getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("PM_ID"))>max){
            max = Integer.parseInt(rs.getString("PM_ID"));
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
  public void Startdate_Filled(String d){
        String year = ""+(Integer.parseInt(d.substring(0,4)));
        String month = d.substring(5,7);
        String day = d.substring(8,10);
        String now = month+"/"+day+"/"+year;
        try{
        Calendar cal = Calendar.getInstance();
        java.util.Date date = new SimpleDateFormat("MM/dd/yy").parse(now);
        cal.setTime(date);
        if(delete==true||view==true){
            startdate_txt.setEnabled(true);
        }
        startdate_txt.setSelectedDate(cal);
        if(delete==true||view==true){
            startdate_txt.setEnabled(false);
        }
        }catch(Exception e){
        System.out.print(e);
        }
  }
  public void Enddate_Filled(String d){
        String year = ""+(Integer.parseInt(d.substring(0,4)));
        String month = d.toString().substring(5,7);
        String day = d.toString().substring(8,10);
        String now = month+"/"+day+"/"+year;
        try{
        Calendar cal = Calendar.getInstance();
        java.util.Date date = new SimpleDateFormat("MM/dd/yy").parse(now);
        cal.setTime(date);
        if(delete==true||view==true){
            enddate_txt.setEnabled(true);
        }
        enddate_txt.setSelectedDate(cal);
        if(delete==true||view==true){
            enddate_txt.setEnabled(false);
        }
        }catch(Exception e){
        System.out.print(e);
       }
  }
  public void getMenu(){
      String getmenu = "SELECT MENU_ID,MENU_NAME,MENU_PRICE,M_T_ID FROM MENU WHERE MENU_DEL = 'N'";
      try{
          pat = getcon().prepareStatement(getmenu);
          rs = pat.executeQuery(getmenu);
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
    public void getPromotion(){
      String getmenu = "SELECT PN_ID,PN_NAME,PN_S_DATE,PN_E_DATE FROM PROMOTION WHERE PN_DEL = 'N'";
      try{
          pat = getcon().prepareStatement(getmenu);
          rs = pat.executeQuery(getmenu);
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
  public void getMenu_List(){
      String getmenu = "SELECT PM_ID,PN_ID,MENU_ID,PM_DISCOUNT FROM PROMOTION_MENU WHERE PM_DEL = 'N'";
      try{
          pat = getcon().prepareStatement(getmenu);
          rs = pat.executeQuery(getmenu);
          while(rs.next()){
              Menu_variable m = new Menu_variable();
              m.p.setmenunumber(rs.getString("PM_ID"));
              m.p.setid(rs.getString("PN_ID"));
              m.setid(rs.getString("MENU_ID"));
              int k = (int)(rs.getDouble("PM_DISCOUNT")*(double)100);
              m.p.setdiscount(k);
              Menu_List_Array.add(m);
          }
          rs.close();
          pat.close();
          getcon().close();
      }catch(Exception e){
          System.out.println(e);
      }
  }
  public void setMenu_Combo(){
      for(Menu_variable m:Menu_Array){
          menu_combo.addItem(m.getname());
      }
  }
  public void print(){
    for(Menu_variable m:Menu_Array){
        System.out.println(m.getid()+" "+m.getname());
    }
}
  public void getPromotion_Menu_Table(){
      DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
      Object[] row = new Object[5];
      for(int i =0;i<Promotion_Menu_List_Array.size();i++){
          row[0] = Promotion_Menu_List_Array.get(i).getid();
          row[1] = Promotion_Menu_List_Array.get(i).getname();
          row[2] = Promotion_Menu_List_Array.get(i).getprice();
          row[3] = Promotion_Menu_List_Array.get(i).p.getdiscount();
          row[4] = Promotion_Menu_List_Array.get(i).p.gettotal();
          model.addRow(row);
      }
  }
  public void getPromotion_Table(){
      ArrayList<Promotion_variable> promotion = Promotion_Array;
      DefaultTableModel model = (DefaultTableModel)promotion_table.getModel();
      Object[] row = new Object[4];
      for(int i =0;i<promotion.size();i++){
          row[0] = promotion.get(i).getid();
          row[1] = promotion.get(i).getname();
          row[2] = promotion.get(i).getstartdate();
          row[3] = promotion.get(i).getenddate();
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
        promotion_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        menu_table = new javax.swing.JTable();
        promotion_name_label = new javax.swing.JLabel();
        promotion_name_txt = new javax.swing.JTextField();
        promotionid_txt = new javax.swing.JTextField();
        promotionid_label = new javax.swing.JLabel();
        startdate_txt = new datechooser.beans.DateChooserCombo();
        enddate_txt = new datechooser.beans.DateChooserCombo();
        enddate_label = new javax.swing.JLabel();
        startdate_label = new javax.swing.JLabel();
        menu_label = new javax.swing.JLabel();
        menu_combo = new javax.swing.JComboBox<>();
        menu_add_btn = new javax.swing.JButton();
        create_radio = new javax.swing.JRadioButton();
        edit_radio = new javax.swing.JRadioButton();
        delete_radio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        close_btn = new javax.swing.JButton();
        submit_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        view_radio = new javax.swing.JRadioButton();
        menu_help_btn = new javax.swing.JButton();
        promotion_help_btn = new javax.swing.JButton();
        refresh_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1020, 590));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        promotion_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                promotion_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(promotion_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 47, -1, 410));

        menu_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเมนู", "ชื่อเมนู", "ราคา", "ส่วนลด %", "ราคาสุทธิ"
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
        jScrollPane2.setViewportView(menu_table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 490, 200));

        promotion_name_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        promotion_name_label.setText("ชื่อโปรโมชั่น:");
        getContentPane().add(promotion_name_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        promotion_name_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(promotion_name_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 190, 30));

        promotionid_txt.setEditable(false);
        promotionid_txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(promotionid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 70, 30));

        promotionid_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        promotionid_label.setText("รหัสโปรโมชั่น:");
        getContentPane().add(promotionid_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        startdate_txt.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        getContentPane().add(startdate_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        enddate_txt.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        getContentPane().add(enddate_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, -1, -1));

        enddate_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        enddate_label.setText("วันสิ้นสุด:");
        getContentPane().add(enddate_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, -1, -1));

        startdate_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        startdate_label.setText("วันเริ่มต้น:");
        getContentPane().add(startdate_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        menu_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        menu_label.setText("เมนู:");
        getContentPane().add(menu_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        menu_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ">>Choose Menu<<" }));
        getContentPane().add(menu_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 160, 30));

        menu_add_btn.setText("Add");
        menu_add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_add_btnActionPerformed(evt);
            }
        });
        getContentPane().add(menu_add_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 60, 30));

        buttonGroup1.add(create_radio);
        create_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        create_radio.setSelected(true);
        create_radio.setText("สร้าง");
        create_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_radioActionPerformed(evt);
            }
        });
        getContentPane().add(create_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 425, -1, -1));

        buttonGroup1.add(edit_radio);
        edit_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        edit_radio.setText("แก้ไข");
        edit_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_radioActionPerformed(evt);
            }
        });
        getContentPane().add(edit_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 425, -1, -1));

        buttonGroup1.add(delete_radio);
        delete_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        delete_radio.setText("ลบ");
        delete_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_radioActionPerformed(evt);
            }
        });
        getContentPane().add(delete_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 425, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("ฟังก์ชั่น");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        close_btn.setText("ปิด");
        close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_btnActionPerformed(evt);
            }
        });
        getContentPane().add(close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 110, 50));

        submit_btn.setText("ยืนยัน");
        submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submit_btnActionPerformed(evt);
            }
        });
        getContentPane().add(submit_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 110, 50));

        clear_btn.setText("เคลียร์");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        getContentPane().add(clear_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 110, 50));

        buttonGroup1.add(view_radio);
        view_radio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        view_radio.setText("ดู");
        view_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_radioActionPerformed(evt);
            }
        });
        getContentPane().add(view_radio, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 425, -1, -1));

        menu_help_btn.setText("?");
        menu_help_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_help_btnActionPerformed(evt);
            }
        });
        getContentPane().add(menu_help_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        promotion_help_btn.setText("?");
        promotion_help_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promotion_help_btnActionPerformed(evt);
            }
        });
        getContentPane().add(promotion_help_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 460, -1, -1));

        refresh_btn.setText("รีเฟรช");
        refresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh_btnActionPerformed(evt);
            }
        });
        getContentPane().add(refresh_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 70, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_add_btnActionPerformed
        String id = null;
        String stringdiscount = null;
        String stringdiscountwithoutspace = "";
        int discount = 0;
        String name = null;
        int price = 0;
        double total = 0.0;
        if(menu_combo.getSelectedIndex()==0){
            
        }else{
            for(Menu_variable m:Menu_Array){
                if(m.getname().equals(menu_combo.getSelectedItem().toString())){
                    id = m.getid();
                    name = m.getname();
                    price = m.getprice();
                    break;
                }
            }
            JOptionPane.showMessageDialog(null,"คุณได้เลือกเมนูรหัส "+id);
            try{
               stringdiscount = JOptionPane.showInputDialog(null,"กรุณากรอกส่วนลด(%)ในเมนูรหัส "+id+" ด้วยครับ\n\nกรอกเปอร์เซนต์เป็นจำนวนเต็ม 0-100\n\nหมายเหตุ:ถ้าหากเมนูไหนฟรีให้กรอกส่วนลด 100%");
               if(stringdiscount == null){
                   throw new NullPointerException();
               }else{
                   for(int i =1;i<=stringdiscount.length();i++){
                       if((stringdiscount.substring(i-1,i).contains(" ")==false)&&((Character.isDigit(stringdiscount.charAt(i-1))==true)||stringdiscount.substring(i-1,i).contains("-")==true)){
                           stringdiscountwithoutspace += stringdiscount.substring(i-1,i);
                       }
                   }
                   System.out.println(stringdiscountwithoutspace);
                   discount = Integer.parseInt(stringdiscountwithoutspace);
                   if(discount>100||discount<0){
                       throw new NumberFormatException();
                   }else{
                   Menu_variable m = new Menu_variable();
                   m.setid(id);
                   m.setname(name);
                   m.setprice(price);
                   m.p.setdiscount(discount);
                   System.out.println(price+"-"+((double)price*((double)discount/(double)100)));
                   m.p.settotal((double)price-((double)price*((double)discount/(double)100)));
                   Promotion_Menu_List_Array.add(m);
                   DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
                   while(model.getRowCount()>0){
                       model.removeRow(0);
                   }
                   getPromotion_Menu_Table();
                   menu_combo.setSelectedIndex(0);
                   JOptionPane.showMessageDialog(null,"เพิ่มเมนูเสร็จสิ้น");
                   }
               }
            }catch(NumberFormatException e){
                System.err.println(e);
                JOptionPane.showMessageDialog(null,"คุณกรอกข้อมูลไม่ถูกต้อง\nยกเลิกรายการ");
                menu_combo.setSelectedIndex(0);
            }
            catch(NullPointerException e){
                System.err.println(e);
                JOptionPane.showMessageDialog(null,"คุณยังไม่ได้กรอกข้อมูล\nยกเลิกรายการ");
                menu_combo.setSelectedIndex(0);
            }
            
        }
    }//GEN-LAST:event_menu_add_btnActionPerformed

    private void menu_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseClicked
        System.out.println(menu_table.getSelectedRow());
        if(doubleclick==menu_table.getSelectedRow()){
        if(JOptionPane.showConfirmDialog(null,"คุณต้องการลบเมนูรหัส "+menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString(),null,YES_NO_OPTION)==YES_OPTION){
            /*for(Menu_variable m:Promotion_Menu_List_Array){
                
            }*/
            System.out.println(Promotion_Menu_List_Array.get(menu_table.getSelectedRow()).getid()+" "+Promotion_Menu_List_Array.get(menu_table.getSelectedRow()).getname()+" "+Promotion_Menu_List_Array.get(menu_table.getSelectedRow()).getprice()+" "+Promotion_Menu_List_Array.get(menu_table.getSelectedRow()).p.getdiscount()+" "+Promotion_Menu_List_Array.get(menu_table.getSelectedRow()).p.gettotal());
            delete_Menu_List_Array.add(Promotion_Menu_List_Array.get(menu_table.getSelectedRow()));
            Promotion_Menu_List_Array.remove(menu_table.getSelectedRow()).getid();
            DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
            while(model.getRowCount()>0){
                model.removeRow(0);
            }
            getPromotion_Menu_Table();
            JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
        }else{
            JOptionPane.showMessageDialog(null,"ยกเลิกรายการ");
        }
        }else{
            doubleclick = menu_table.getSelectedRow();
        }
    }//GEN-LAST:event_menu_tableMouseClicked

    private void create_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_radioActionPerformed
        clear();
        create = true;
        edit = false;
        delete = false;
        view = false;
        refresh_btn.setVisible(false);
        unlock();
        submit_btn.setEnabled(true);
        clear_btn.setEnabled(true);
        menu_help_btn.setVisible(true);
        menu_table.setEnabled(true);
        promotion_help_btn.setVisible(false);
        promotionid_txt.setText(Promotion_id);
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางเมนู\nเพื่อลบเมนูของโปรโมชั่นได้ครับ\n\nคุณสามารถดูข้อความนี้ได้อีกครั้ง\nโดยกดที่ปุ่ม ? เหนือตารางเมนู");
    }//GEN-LAST:event_create_radioActionPerformed

    private void submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submit_btnActionPerformed
        promotionid = promotionid_txt.getText();
        promotionname = null;
        boolean creatation = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String promotionstartdate = sdf.format(startdate_txt.getSelectedDate().getTime());
        String promotionenddate = sdf.format(enddate_txt.getSelectedDate().getTime());
        if(create==true){
        try{
        promotionname = promotion_name_txt.getText();
        if(promotionname.equals("")){
            throw new NullPointerException();
        }
        }catch(NullPointerException e){
            creatation = false;
            JOptionPane.showMessageDialog(null,"คุณยังไม่ได้กรอกชื่อโปรโมชั่น\nกรุณาทำรายการใหม่ด้วยครับ");
            clear();
        }
        if(Promotion_Menu_List_Array.isEmpty()){
            creatation = false;
            JOptionPane.showMessageDialog(null,"คุณยังไม่ได้เพิ่มเมนูในโปรโมชั่น\nกรุณาทำรายการใหม่ด้วยครับ");
            clear();
        }
        if(creatation==true){
        String insertpromotion = "INSERT INTO PROMOTION VALUES('"+promotionid+"','"+promotionname+"','"+promotionstartdate+"','"+promotionenddate+"','N')";
        try{
            System.out.println(insertpromotion);
            pat = getcon().prepareStatement(insertpromotion);
            pat.executeUpdate(insertpromotion);
            pat.close();
            getcon().close();
                for(Menu_variable m:Promotion_Menu_List_Array){
                String insertpromotionlist = "INSERT INTO PROMOTION_MENU VALUE('"+getPromotion_List_ID()+"','"+promotionid+"','"+m.getid()+"','"+((double)m.p.getdiscount()/(double)100)+"','N')";
                    try{
                    System.out.println(insertpromotionlist);
                    pat = getcon().prepareStatement(insertpromotionlist);
                    pat.executeUpdate(insertpromotionlist);
                    pat.close();
                    getcon().close();
                    }catch(Exception e){
                        System.err.println(e);
                    }
                }
        clear();  
        Promotion_Array.clear();
        Promotion_Menu_List_Array.clear();
        Menu_List_Array.clear();
        DefaultTableModel menu = (DefaultTableModel)menu_table.getModel();
        while(menu.getRowCount()>0){
        menu.removeRow(0);
        }
        DefaultTableModel model = (DefaultTableModel)promotion_table.getModel();
        while(model.getRowCount()>0){
        model.removeRow(0);
            }
        getPromotion();
        getPromotion_Table();
        getPromotion_ID();
        getMenu_List();
        JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิน");
        }catch(Exception e){
            System.err.println(e);
        }
        }
        }else if(edit==true){
        if(Promotion_Menu_List_Array.size()==0){
            JOptionPane.showMessageDialog(null,"คุณไม่สามารถบันทึกโปรโมชั่นที่ไม่มีเมนูได้\nกรุณาลองใหม่อีกครั้งครับ",null,ERROR_MESSAGE);
            refresh();
        }else{
        try{
        promotionname = promotion_name_txt.getText();
        if(promotionname.equals("")){
            throw new NullPointerException();
        }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"คุณยังไม่ได้กรอกชื่อโปรโมชั่น\nกรุณาทำรายการใหม่ด้วยครับ");
        }
            String updatepromotion = "UPDATE PROMOTION SET PN_NAME = '"+promotionname+"',PN_S_DATE = '"+promotionstartdate+"',PN_E_DATE = '"+promotionenddate+"' WHERE PN_ID = '"+promotionid+"'";
            try{
                pat = getcon().prepareStatement(updatepromotion);
                pat.executeUpdate(updatepromotion);
                pat.close();
                getcon().close();
            }catch(Exception e){
                
            }
            if(Promotion_Menu_List_Array.size()>edit_Menu_List_Array.size()){
                for(Menu_variable m:Promotion_Menu_List_Array){
                    if(m.p.getmenunumber()==null){
                        String insertpromotion_menu = "INSERT INTO PROMOTION_MENU VALUES('"+getPromotion_List_ID()+"','"+promotionid+"','"+m.getid()+"','"+(double)m.p.getdiscount()/(double)100+"','N')";
                        System.err.println(insertpromotion_menu);
                        try{
                            pat = getcon().prepareStatement(insertpromotion_menu);
                            pat.executeUpdate(insertpromotion_menu);
                            pat.close();
                            getcon().close();
                        }catch(Exception e){
                            
                        }
                    }
                }
            }else if(Promotion_Menu_List_Array.size()<edit_Menu_List_Array.size()){
                for(Menu_variable m:delete_Menu_List_Array){
                            String deletepromotion_menu = "UPDATE PROMOTION_MENU SET PM_DEL = 'Y' WHERE PM_ID = '"+m.p.getmenunumber()+"'";
                            System.out.println(deletepromotion_menu);
                            try{
                                pat = getcon().prepareStatement(deletepromotion_menu);
                                pat.executeUpdate(deletepromotion_menu);
                                pat.close();
                                getcon().close();
                            }catch(Exception e){
                       
                            }
                }
            }
        Menu_List_Array.clear();
        Promotion_Array.clear();
        Promotion_Menu_List_Array.clear();
        edit_Menu_List_Array.clear();
        delete_Menu_List_Array.clear();
        clear();
        setNowDate();
        DefaultTableModel menu = (DefaultTableModel)menu_table.getModel();
        while(menu.getRowCount()>0){
        menu.removeRow(0);
        }
        DefaultTableModel model = (DefaultTableModel)promotion_table.getModel();
        while(model.getRowCount()>0){
        model.removeRow(0);
        }
        getMenu_List();
        getPromotion();
        getPromotion_Table();
        getPromotion_ID();
        JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิน");
        }
        }else if(delete==true){
            String deletepromotion = "UPDATE PROMOTION SET PN_DEL = 'Y' WHERE PN_ID = '"+promotionid+"'";
            String deletepromotionmenu = "UPDATE PROMOTION_MENU SET PM_DEL = 'Y' WHERE PN_ID = '"+promotionid+"'";
            try{
                pat = getcon().prepareStatement(deletepromotionmenu);
                pat.executeUpdate(deletepromotionmenu);
                pat.close();
                    pat = getcon().prepareStatement(deletepromotion);
                    pat.executeUpdate(deletepromotion);
                    pat.close();
                    getcon().close();
            }catch(Exception e){
                System.out.println(e);
            }
        Menu_List_Array.clear();
        Promotion_Array.clear();
        Promotion_Menu_List_Array.clear();
        edit_Menu_List_Array.clear();
        delete_Menu_List_Array.clear();
        clear();
        setNowDate();
        DefaultTableModel menu = (DefaultTableModel)menu_table.getModel();
        while(menu.getRowCount()>0){
        menu.removeRow(0);
        }
        DefaultTableModel model = (DefaultTableModel)promotion_table.getModel();
        while(model.getRowCount()>0){
        model.removeRow(0);
        }
        getMenu_List();
        getPromotion();
        getPromotion_Table();
        getPromotion_ID();
        }
    }//GEN-LAST:event_submit_btnActionPerformed

    private void view_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_radioActionPerformed
        view = true;
        delete = false;
        create = false;
        edit = false;
        clear();
        refresh_btn.setVisible(false);
        submit_btn.setEnabled(false);
        clear_btn.setEnabled(false);
        menu_help_btn.setVisible(false);
        menu_table.setEnabled(false);
        startdate_txt.setEnabled(false);
        enddate_txt.setEnabled(false);
        promotion_help_btn.setVisible(true);
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางโปรโมชั่น\nเพื่อดูรายละเอียดของโปรโมชั่นได้ครับ\n\nคุณสามารถดูข้อความนี้ได้อีกครั้ง\nโดยกดที่ปุ่ม ? ใต้ตารางโปรโมชั่น");
    }//GEN-LAST:event_view_radioActionPerformed

    private void promotion_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_promotion_tableMouseClicked

        if(doubleclickstring.equals(promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),0).toString())){
         promotionid = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),0).toString();
         promotionname = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),1).toString();
         String promotionstartdate = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),2).toString();
         String promotionenddate = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),3).toString();
         Promotion_Menu_List_Array.clear();
         if(edit==true||delete==true||view==true){
             JOptionPane.showMessageDialog(null,"คุณได้เลือกโปรโมชั่นรหัส "+promotionid);
             promotionid_txt.setText(promotionid);
             promotion_name_txt.setText(promotionname);
                for(Menu_variable m:Menu_List_Array){
                     for(Menu_variable mm:Menu_Array){  
                        if(m.getid().equals(mm.getid())&&m.p.getid().equals(promotionid)){
                        Menu_variable mv = new Menu_variable();
                        mv.setid(m.getid());
                        mv.setname(mm.getname());
                        mv.setprice(mm.getprice());
                        mv.p.setmenunumber(m.p.getmenunumber());
                        mv.p.setdiscount(m.p.getdiscount());
                        mv.p.settotal(mm.getprice()-(((double)mm.getprice()*((double)m.p.getdiscount())/(double)100)));
                        Promotion_Menu_List_Array.add(mv);
                        edit_Menu_List_Array.add(mv);
                        break;
                        }
                    }
                }
                        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
                        while(model.getRowCount()>0){
                            model.removeRow(0);
                        }
                        getPromotion_Menu_Table();
                        promotion_table.clearSelection();
                doubleclickstring = "";
        Startdate_Filled(promotionstartdate);
        Enddate_Filled(promotionenddate);
            }
        }else{
            doubleclickstring = promotion_table.getModel().getValueAt(promotion_table.getSelectedRow(),0).toString();
        }
        
    }//GEN-LAST:event_promotion_tableMouseClicked

    private void edit_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_radioActionPerformed
        view = false;
        delete = false;
        create = false;
        edit = true;
        refresh_btn.setVisible(true);
        clear();
        unlock();
        submit_btn.setEnabled(true);
        clear_btn.setEnabled(true);
        menu_help_btn.setVisible(true);
        menu_table.setEnabled(true);
        promotion_help_btn.setVisible(true);
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางโปรโมชั่น\nเพื่อดูรายละเอียดของโปรโมชั่นได้ครับ\n\nคุณสามารถดูข้อความนี้ได้อีกครั้ง\nโดยกดที่ปุ่ม ? ใต้ตารางโปรโมชั่น");
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางเมนู\nเพื่อลบเมนูของโปรโมชั่นได้ครับ\n\nคุณสามารถดูข้อความนี้ได้อีกครั้ง\nโดยกดที่ปุ่ม ? เหนือตารางเมนู");
    }//GEN-LAST:event_edit_radioActionPerformed

    private void delete_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_radioActionPerformed
        clear();
        view = false;
        delete = true;
        create = false;
        edit = false;
        refresh_btn.setVisible(false);
        promotion_name_txt.setEnabled(false);
        menu_combo.setEnabled(false);
        menu_add_btn.setEnabled(false);
        startdate_txt.setEnabled(false);
        enddate_txt.setEnabled(false);
        submit_btn.setEnabled(true);
        clear_btn.setEnabled(false);
        menu_help_btn.setVisible(false);
        menu_table.setEnabled(false);
        promotion_help_btn.setVisible(true);
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางโปรโมชั่น\nเพื่อดูรายละเอียดของโปรโมชั่นได้ครับ\n\nคุณสามารถดูข้อความนี้ได้อีกครั้ง\nโดยกดที่ปุ่ม ? ใต้ตารางโปรโมชั่น");
    }//GEN-LAST:event_delete_radioActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        clearedit();
    }//GEN-LAST:event_clear_btnActionPerformed

    private void refresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh_btnActionPerformed
        refresh();
    }//GEN-LAST:event_refresh_btnActionPerformed

    private void promotion_help_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promotion_help_btnActionPerformed
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางโปรโมชั่น\nเพื่อดูรายละเอียดของโปรโมชั่นได้ครับ");
    }//GEN-LAST:event_promotion_help_btnActionPerformed

    private void menu_help_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_help_btnActionPerformed
        JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางเมนู\nเพื่อลบเมนูของโปรโมชั่นได้ครับ");
    }//GEN-LAST:event_menu_help_btnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        int i =0;
        if(i==0){
            JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิลคลิ๊กที่ตารางเมนู\nเพื่อลบเมนูของโปรโมชั่นได้ครับ\n\nคุณสามารถดูข้อความนี้ได้อีกครั้ง\nโดยกดที่ปุ่ม ? เหนือตารางเมนู");
            i=-1;
        }
    }//GEN-LAST:event_formWindowOpened

    private void close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_btnActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_close_btnActionPerformed

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
            java.util.logging.Logger.getLogger(Promotion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Promotion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Promotion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Promotion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Promotion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clear_btn;
    private javax.swing.JButton close_btn;
    private javax.swing.JRadioButton create_radio;
    private javax.swing.JRadioButton delete_radio;
    private javax.swing.JRadioButton edit_radio;
    private javax.swing.JLabel enddate_label;
    private datechooser.beans.DateChooserCombo enddate_txt;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menu_add_btn;
    private javax.swing.JComboBox<String> menu_combo;
    private javax.swing.JButton menu_help_btn;
    private javax.swing.JLabel menu_label;
    private javax.swing.JTable menu_table;
    private javax.swing.JButton promotion_help_btn;
    private javax.swing.JLabel promotion_name_label;
    private javax.swing.JTextField promotion_name_txt;
    private javax.swing.JTable promotion_table;
    private javax.swing.JLabel promotionid_label;
    private javax.swing.JTextField promotionid_txt;
    private javax.swing.JButton refresh_btn;
    private javax.swing.JLabel startdate_label;
    private datechooser.beans.DateChooserCombo startdate_txt;
    private javax.swing.JButton submit_btn;
    private javax.swing.JRadioButton view_radio;
    // End of variables declaration//GEN-END:variables
}
