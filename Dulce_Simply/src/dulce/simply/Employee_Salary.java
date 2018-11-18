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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Employee_Salary extends javax.swing.JFrame {
   Database d = new Database();
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    Statement st = null;
    String year = null;
    String month = null;
    String day = null;
    String start = null;
    String end = null;
    /**
     * Creates new form Payment
     */
    public Employee_Salary() {
        initComponents();
        showtime();
        showSchedule();
    }
    void showtime(){
         Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("YYYY-MM-dd");
        year = String.valueOf(Integer.parseInt(s.format(d).substring(0,4))-543);
        month = String.valueOf(Integer.parseInt(s.format(d).substring(5,7)));
        day = String.valueOf(Integer.parseInt(s.format(d).substring(s.format(d).length()-2,s.format(d).length())));
        start = "'"+year+"-01-01'";
        end = "'"+year+"-12-31'";
        System.out.print(year+month+day);
    }
   public ArrayList<Employee>ScheduleList(){
        String change = "SELECT EMP_ID,EMP_FNAME,EMP_LNAME,EMP_SALARY,SUM(W_TOTALTIME) FROm WORKTIME NATURAL JOIN EMPLOYEE WHERE W_STATUS = 'Y' AND W_DATE BETWEEN "+start+" AND "+end+" GROUP BY EMP_ID HAVING SUM(W_TOTALTIME) ORDER BY EMP_ID";         
       System.out.println(change);
        ArrayList<Employee> Schedulelist = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");     
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(change);
        rs = pat.executeQuery(change);
        while(rs.next()){
            Employee ep = new Employee();
            ep.setid(rs.getString("EMP_ID"));
            ep.setfname(rs.getString("EMP_FNAME"));
            ep.setlname(rs.getString("EMP_LNAME"));
            ep.setsalary(rs.getInt("EMP_SALARY"));
            ep.setworktime(rs.getDouble("SUM(W_TOTALTIME)"));
            ep.setsumsalary(((double)ep.getsalary())*((double)ep.getworktime()));
            Schedulelist.add(ep);
        }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
    if(pat != null)
        pat.close();
    if(rs != null)
        rs.close();
    if(con != null)
        con.close();
}
        return Schedulelist;
    }
       private void showSchedule(){
        ArrayList<Employee> Schedule = ScheduleList();
        DefaultTableModel model = (DefaultTableModel)Show_payment.getModel();
        Object[] row = new Object[5];
        for(int i=0;i<Schedule.size();i++){
            row[0]=Schedule.get(i).getid();
            row[1]=Schedule.get(i).getfname()+" "+Schedule.get(i).getlname();
            row[2]=Schedule.get(i).getsalary();
            row[3]=Schedule.get(i).getworktime();
            row[4]=Schedule.get(i).getsumsalary();
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
        Show_payment = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(730, 580));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Show_payment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Salary per hour", "Worktime", "Total"
            }
        ));
        jScrollPane1.setViewportView(Show_payment);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 665, 330));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 140, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Employee Total Salary");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 110, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
if(jComboBox1.getSelectedItem().equals("Total")){
        start = "'"+year+"-01-01'";
        end = "'"+year+"-12-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
}else if(jComboBox1.getSelectedItem().equals("January")){
            start = "'"+year+"-01-01'";
            end = "'"+year+"-01-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("February")){
            start = "'"+year+"-02-01'";
            end = "'"+year+"-02-28'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("March")){
            start = "'"+year+"-03-01'";
            end = "'"+year+"-03-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("April")){
            start = "'"+year+"-04-01'";
            end = "'"+year+"-04-30'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("May")){
            start = "'"+year+"-05-01'";
            end = "'"+year+"-05-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("June")){
             start = "'"+year+"-06-01'";
            end = "'"+year+"-06-30'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("July")){
            start = "'"+year+"-07-01'";
            end = "'"+year+"-07-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("August")){
            start = "'"+year+"-08-01'";
            end = "'"+year+"-08-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("September")){
            start = "'"+year+"-09-01'";
            end = "'"+year+"-09-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("October")){
            start = "'"+year+"-10-01'";
            end = "'"+year+"-10-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("November")){
            start = "'"+year+"-11-01'";
            end = "'"+year+"-11-30'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }else if(jComboBox1.getSelectedItem().equals("December")){
            start = "'"+year+"-12-01'";
            end = "'"+year+"'-12-31'";
            DefaultTableModel dk = (DefaultTableModel)Show_payment.getModel();
            while(dk.getRowCount() > 0)
            {       
            dk.removeRow(0);
            }
            showSchedule();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Employee_Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_Salary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Show_payment;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
