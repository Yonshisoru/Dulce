/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;

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
public class Employee_create extends javax.swing.JFrame {
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
    int max = 0;
    /**
     * Creates new form Employee_create
     */
    public Employee_create() {
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
   public ArrayList<Employee>EmployeeList(){
               ArrayList<Employee> EmployeeList = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select EMP_ID,EMP_FNAME,EMP_LNAME,POS_NAME,EMP_AGE,EMP_GENDER,EMP_START,EMP_PHONE,EMP_EMAIL,EMP_SALARY from EMPLOYEE NATURAL JOIN EMP_POSITION WHERE EMP_DEL = 'N' ORDER BY EMP_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
         con = DriverManager.getConnection(d.url(),d.username(),d.password());
       st = con.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Employee e = new Employee(rs.getString("EMP_ID"),rs.getString("EMP_FNAME"),rs.getString("EMP_LNAME"),rs.getString("POS_NAME"),rs.getInt("EMP_AGE"),rs.getString("EMP_GENDER"),rs.getString("EMP_PHONE"),rs.getString("EMP_EMAIL"),rs.getString("EMP_START"));
            e.setsalary(rs.getInt("EMP_SALARY"));
            e.setdate(rs.getString("EMP_START"));
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
        Object[] row = new Object[10];
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
            row[9]=EmployeeList.get(i).getdate();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1430, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emp_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Position", "Given name", "Family name", "Age", "Gender", "Phone number", "Email", "Salary", "Hired date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
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
        getContentPane().add(showid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 110, 30));
        getContentPane().add(email_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 170, 30));
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
        getContentPane().add(lname_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 170, 30));
        getContentPane().add(phone_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 170, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Password:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Gender:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Given name:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Email:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, -1, -1));

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        getContentPane().add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 100, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Age:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Position:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, -1, -1));

        jButton1.setText("Close");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 670, 120, 50));

        jButton2.setText("Create");
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

        getContentPane().add(position_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 130, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Address:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, -1, -1));

        salary_txt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
        getContentPane().add(salary_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 130, 30));
        getContentPane().add(password_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 130, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Family name:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Salary:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        address_txt.setColumns(20);
        address_txt.setRows(5);
        jScrollPane2.setViewportView(address_txt);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 270, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emp_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emp_tableMouseClicked

        /*if(mk.editenable()==1){  */
            /*}/*
        if(count==2){
            usingid = emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString();
            System.out.print(emp_table.getModel().getValueAt(emp_table.getSelectedRow(),0).toString());
            s.setVisible(true);
            count=0;
        }*/
    }//GEN-LAST:event_emp_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
    }//GEN-LAST:event_jButton2ActionPerformed

    private void age_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age_txtActionPerformed

    }//GEN-LAST:event_age_txtActionPerformed

    private void age_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_age_txtFocusGained

    }//GEN-LAST:event_age_txtFocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clear();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Employee_create.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_create.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_create.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_create.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_create().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea address_txt;
    private javax.swing.JTextField age_txt;
    private javax.swing.JTextField email_txt;
    private javax.swing.JTable emp_table;
    private javax.swing.JTextField fname_txt;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
