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
public class Employee_panel extends javax.swing.JFrame {
    boolean editnaja = false;
    boolean createnaja = true;
    boolean deletenaja = false;
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
    String position = null;
    String password= null;
    String createid = null;
    int max = 0;
    /**
     * Creates new form Employee_create
     */
    public Employee_panel() {
        initComponents();
        show_employee();
        id();
        fillcombo();
    }
    public void clear(){
         age_txt.setText("");
         password_txt.setText("");
         fname_txt.setText("");
         lname_txt.setText("");
         gender.setSelectedIndex(0);
         phone_txt.setText("");
         email_txt.setText("");
         address_txt.setText("");
         salary_txt.setSelectedIndex(0);
         position_txt.setSelectedIndex(0);
    }
    public void lock(){
         age_txt.setEnabled(false);
         password_txt.setEnabled(false);
         fname_txt.setEnabled(false);
         lname_txt.setEnabled(false);
         gender.setEnabled(false);
         phone_txt.setEnabled(false);
         email_txt.setEnabled(false);
         address_txt.setEnabled(false);
         salary_txt.setEnabled(false);
         position_txt.setEnabled(false);
    }
    public void unlock(){
         age_txt.setEnabled(true);
         password_txt.setEnabled(true);
         fname_txt.setEnabled(true);
         lname_txt.setEnabled(true);
         gender.setEnabled(true);
         phone_txt.setEnabled(true);
         email_txt.setEnabled(true);
         address_txt.setEnabled(true);
         salary_txt.setEnabled(true);
         position_txt.setEnabled(true);
    }
  public String find(){
            String find = "SELECT POS_ID,POS_NAME FROM EMP_POSITION";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(find);
        rs = pat.executeQuery(find);
        while(rs.next()){
            if(position_txt.getSelectedItem().toString().equals(rs.getString("POS_NAME"))){
                position = rs.getString("POS_ID");
                System.out.print(position);
            }
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){ 
        } 
        return position;
  }
  public String id(){
       int count=0;
          String sql  ="select EMP_ID from EMPLOYEE";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(d.url(),d.username(),d.password());
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("EMP_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("EMP_ID").substring(1,4));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    if(max<10){
        output = "E00"+max;
    }else if(max<100){
        output = "E0"+max;
    }else{
        output = "E"+max;
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
    void fillcombo(){
      try{
          String sql = "SELECT POS_ID,POS_NAME,POS_DEL FROM EMP_POSITION WHERE POS_DEL = 'N' AND POS_ID > 01";
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            position_txt.addItem(rs.getString("POS_NAME"));
        }
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){
          
      }
  }
  void password(String id){
      try{
          String sql = "SELECT EMP_PASS FROM EMPLOYEE WHERE EMP_ID = '"+id+"'";
          System.out.print(sql);
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
        st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            password = rs.getString("EMP_PASS");
        }
        System.out.print(password);
        rs.close();
        st.close();
        con.close();
      }catch(Exception e){
          System.out.print(e);
      }
  }
   public ArrayList<Employee>EmployeeList(){
               ArrayList<Employee> EmployeeList = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select EMP_ID,EMP_FNAME,EMP_LNAME,POS_NAME,EMP_AGE,EMP_GENDER,EMP_START,EMP_PHONE,EMP_EMAIL,EMP_SALARY,EMP_ADDRESS from EMPLOYEE NATURAL JOIN EMP_POSITION WHERE EMP_DEL = 'N' ORDER BY EMP_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Employee e = new Employee(rs.getString("EMP_ID"),rs.getString("EMP_FNAME"),rs.getString("EMP_LNAME"),rs.getString("POS_NAME"),rs.getInt("EMP_AGE"),rs.getString("EMP_GENDER"),rs.getString("EMP_PHONE"),rs.getString("EMP_EMAIL"),rs.getString("EMP_START"));
            e.setsalary(rs.getInt("EMP_SALARY"));
            e.setdate(rs.getString("EMP_START"));
            e.setaddress(rs.getString("EMP_ADDRESS"));
            EmployeeList.add(e);
        }
        rs.close();
        st.close();
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return EmployeeList;
}
    public void show_employee(){
        DefaultTableModel model = (DefaultTableModel)emp_table.getModel();
        ArrayList<Employee>EmployeeList = EmployeeList();
        Object[] row = new Object[11];
        for(int i=0;i<EmployeeList.size();i++){
            row[0]=EmployeeList.get(i).getid();
            row[1]=EmployeeList.get(i).getposition();
            row[2]=EmployeeList.get(i).getfname();
            row[3]=EmployeeList.get(i).getlname();
            row[4]=EmployeeList.get(i).getage();
            row[5]=EmployeeList.get(i).getgender();
            row[6]=EmployeeList.get(i).getphone();
            row[7]=EmployeeList.get(i).getemail();
            row[8]=EmployeeList.get(i).getsalary();
            row[9]=EmployeeList.get(i).getaddress();
            row[10]=EmployeeList.get(i).getdate();
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
        emp_table = new javax.swing.JTable();
        showid_txt = new javax.swing.JTextField();
        email_txt = new javax.swing.JTextField();
        fname_txt = new javax.swing.JTextField();
        age_txt = new javax.swing.JTextField();
        lname_txt = new javax.swing.JTextField();
        phone_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        position_txt = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        salary_txt = new javax.swing.JComboBox<>();
        password_txt = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        address_txt = new javax.swing.JTextArea();
        delete = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        create = new javax.swing.JRadioButton();
        edit = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emp_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสพนักงาน", "ตำแหน่ง", "ชื่อ", "นามสกุล", "อายุ", "เพศ", "เบอร์โทรศัพท์", "อีเมลล์", "เงินต่อชั่วโมง", "ที่อยู่", "วันเริ่มทำงาน"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_table.getTableHeader().setReorderingAllowed(false);
        emp_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emp_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(emp_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 820, 680));

        showid_txt.setEditable(false);
        showid_txt.setEnabled(false);
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 110, 30));
        getContentPane().add(email_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 170, 30));
        getContentPane().add(fname_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 130, 30));

        age_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                age_txtFocusGained(evt);
            }
        });
        age_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                age_txtActionPerformed(evt);
            }
        });
        getContentPane().add(age_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 80, 30));
        getContentPane().add(lname_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 170, 30));
        getContentPane().add(phone_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, 170, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("รหัสผ่าน:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("รหัสพนักงาน:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("เพศ:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("ชื่อ:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("อีเมลล์:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, -1, -1));

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        getContentPane().add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("อายุ:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("เบอร์โทรศัพท์:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("ตำแหน่ง:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));

        jButton1.setText("ปิด");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 670, 120, 50));

        jButton2.setText("ยืนยัน");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 120, 50));

        jButton3.setText("เคลียร์");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 670, 120, 50));

        getContentPane().add(position_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("ที่อยู่:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        salary_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
        getContentPane().add(salary_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 130, 30));

        password_txt.setToolTipText("");
        password_txt.setEchoChar('*');
        password_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_txtActionPerformed(evt);
            }
        });
        getContentPane().add(password_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 130, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("นามสกุล:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("เงินต่อชั่วโมง:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        address_txt.setColumns(20);
        address_txt.setRows(5);
        jScrollPane2.setViewportView(address_txt);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 270, 110));

        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 570, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("ฟังก์ชั่น:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, -1, -1));

        create.setSelected(true);
        create.setEnabled(false);
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        getContentPane().add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, -1, -1));

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
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, -1, -1));

        jLabel13.setText("ลบ");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 573, -1, -1));

        jLabel14.setText("สร้าง");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 573, -1, -1));

        jLabel15.setText("แก้ไข");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 573, -1, -1));

        jCheckBox1.setText("แสดงรหัสผ่าน?");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, -1, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("สร้างตำแหน่งใหม่ คลิ๊กที่นี้");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 415, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emp_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emp_tableMouseClicked
        if(editnaja==true||deletenaja==true){
        showid_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString());
        password(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString());
        password_txt.setText(password);
        fname_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),2).toString());
        lname_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),3).toString());
        age_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),4).toString());
        if(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),5).toString().equals("Male")){ 
        gender.setSelectedIndex(0);
        }else{
        gender.setSelectedIndex(1);  
        }
        phone_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),6).toString());
        email_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),7).toString());
        salary_txt.setSelectedItem(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),8).toString());
        position_txt.setSelectedItem(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),1).toString());
        address_txt.setText(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),9).toString());
         
        /*if(mk.editenable()==1){  */
            /*}/*
        if(count==2){
            usingid = emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString();
            System.out.print(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString());
            s.setVisible(true);
            count=0;
        }*/
        }
    }//GEN-LAST:event_emp_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            System.out.print("create!!");
        String id = showid_txt.getText();
        //String pos = find();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        
        String fname = fname_txt.getText();
        String lname = lname_txt.getText();
        String password = password_txt.getText();
        String age = age_txt.getText();
        String address = address_txt.getText();
        String gendernaja = null;
        String date = ""+LocalDate.now().format(dtf);
        if(gender.getSelectedItem().equals("Male")){
            gendernaja = "Male";
        }else{
            gendernaja = "Female";
        }
        String phone = phone_txt.getText();
        String email = email_txt.getText();
        int salary = Integer.parseInt(salary_txt.getSelectedItem().toString());
        if(createnaja==true){
        String eiei = "INSERT INTO EMPLOYEE VALUE('"+id+"','"+find()+"','"+password+"','"+fname+"','"+lname+"','"+phone+"','"+address+"','"+email+"','"+salary+"','"+date+"','"+age+"','"+gendernaja+"','N')";  
        System.out.print(eiei);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(eiei);
        pat.executeUpdate(eiei);
        pat.close();
        con.close();
        }catch(Exception e){
            System.out.print(e);
        }
        DefaultTableModel dm = (DefaultTableModel)emp_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_employee();
        id();
        clear();
        }else if(editnaja==true){
            String edit = "UPDATE EMPLOYEE SET POS_ID = '"+find()+"',EMP_PASS = '"+password+"',EMP_FNAME = '"+fname+"',EMP_LNAME = '"+lname+"',EMP_PHONE = '"+phone+"',EMP_ADDRESS = '"+address+"',EMP_SALARY = '"+salary+"',EMP_AGE = '"+age+"',EMP_GENDER = '"+gendernaja+"' WHERE EMP_ID = '"+id+"'";  
            System.out.print(edit);
            try{
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(d.url(),d.username(),d.password());
                pat = con.prepareStatement(edit);
                pat.executeUpdate(edit);
                pat.close();
                clear();
                JOptionPane.showMessageDialog(null,"Update Success");
                con.close(); 
            }catch(Exception e){
                System.out.print(e);
            }
                  DefaultTableModel dm = (DefaultTableModel)emp_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_employee();  
        }else if(deletenaja==true){
            String delete = "UPDATE EMPLOYEE SET EMP_DEL = 'Y' WHERE EMP_ID ='"+id+"'";
            System.out.print(edit);
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
                  DefaultTableModel dm = (DefaultTableModel)emp_table.getModel();
        System.out.print(dm.getRowCount());
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        show_employee();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void age_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age_txtActionPerformed

    }//GEN-LAST:event_age_txtActionPerformed

    private void age_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_age_txtFocusGained

    }//GEN-LAST:event_age_txtFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
        showid_txt.setText(createid);
        emp_table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        unlock(); 
        clear();
        emp_table.getSelectionModel().clearSelection();
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
        emp_table.getSelectionModel().clearSelection();
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
        emp_table.getSelectionModel().clearSelection();
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

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(pass==false){
        password_txt.setEchoChar((char)0);
        pass=true;
        }else{
           password_txt.setEchoChar('*');
        pass=false; 
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void password_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_txtActionPerformed

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        Employee_type_panel e = new Employee_type_panel();
        e.setVisible(true);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        jLabel16.setForeground(Color.white);
        jLabel16.setCursor(new Cursor(HAND_CURSOR));
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        jLabel16.setForeground(Color.blue);
        jLabel16.setCursor(new Cursor(DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel16MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Employee_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea address_txt;
    private javax.swing.JTextField age_txt;
    private javax.swing.JRadioButton create;
    private javax.swing.JRadioButton delete;
    private javax.swing.JRadioButton edit;
    private javax.swing.JTextField email_txt;
    private javax.swing.JTable emp_table;
    private javax.swing.JTextField fname_txt;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lname_txt;
    private javax.swing.JPasswordField password_txt;
    private javax.swing.JTextField phone_txt;
    private javax.swing.JComboBox<String> position_txt;
    private javax.swing.JComboBox<String> salary_txt;
    private javax.swing.JTextField showid_txt;
    // End of variables declaration//GEN-END:variables
}
