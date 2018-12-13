/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Schedule_Enroll_timeshift extends javax.swing.JFrame {
    Database d = new Database();
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    Statement st = null;
    String year = null;
    String month = null;
    String day = null;
    int max = Integer.MIN_VALUE;
    String id = null;
    /**
     * Creates new form Enroll_timeshift
     */
    public Schedule_Enroll_timeshift() {
        initComponents();
        showSchedule();
        
    }
    public ArrayList<Schedule>ScheduleList(){
        /*System.out.print("select SC_ID,SC_DATE,SCS_ID,SCS_NAME,SC_EMPLIMIT,SC_EMPCUR,SC_LEAVE,SC_DEL FROM SCHEDULE NATURAL JOIN SC_SHIFT WHERE SC_DEL = 'N' AND SC_DATE BETWEEN "+year+"-"+month+"-"+day+" AND "+year+"-"+month+"-"+(day+5));*/
        ArrayList<Schedule> Schedulelist = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  = "select SC_ID,SC_DATE,SCS_ID,SCS_NAME,SC_EMPLIMIT,SC_EMPCUR,SC_LEAVE,SC_DEL FROM SCHEDULE NATURAL JOIN SC_SHIFT WHERE SC_DEL = 'N' AND SC_DATE BETWEEN '"+LocalDate.now().plusDays(1)+"' AND '"+LocalDate.now().plusDays(7)+"';" ;         
        System.out.print(sql);
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            Schedule e = new Schedule(rs.getString("SC_ID"),rs.getString("SC_DATE"),rs.getString("SCS_NAME"),rs.getInt("SC_EMPLIMIT"),rs.getInt("SC_EMPCUR"),rs.getInt("SC_LEAVE"));
            Schedulelist.add(e);
        }
        con.close();
        pat.close();
        rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Schedulelist;
    }
       public void showSchedule(){
        ArrayList<Schedule> Schedule = ScheduleList();
        DefaultTableModel model = (DefaultTableModel)ScheduleTable.getModel();
        Object[] row = new Object[5];
        for(int i=0;i<Schedule.size();i++){
            row[0]=Schedule.get(i).getdate();
            row[1]=Schedule.get(i).getperiod();
            row[2]=Schedule.get(i).getlimit();
            row[3]=Schedule.get(i).getcurrent();
            row[4]=Schedule.get(i).getleave();
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
        ScheduleTable = new javax.swing.JTable();
        Date_txt = new javax.swing.JTextField();
        Period_txt = new javax.swing.JTextField();
        Date = new javax.swing.JLabel();
        Period = new javax.swing.JLabel();
        M_Employee = new javax.swing.JLabel();
        M_E_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        C_E_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        L_E_txt = new javax.swing.JTextField();
        Close_btn = new javax.swing.JButton();
        Enroll_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Period", "Max", "Current", "Leave"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        ScheduleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScheduleTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ScheduleTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 460, 496));

        Date_txt.setText(" ");
        Date_txt.setEnabled(false);
        Date_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Date_txtActionPerformed(evt);
            }
        });
        getContentPane().add(Date_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 119, 32));

        Period_txt.setText(" ");
        Period_txt.setEnabled(false);
        getContentPane().add(Period_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 119, 34));

        Date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Date.setText("Date:");
        getContentPane().add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        Period.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Period.setText("Period:");
        getContentPane().add(Period, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        M_Employee.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        M_Employee.setText("Max Employee:");
        getContentPane().add(M_Employee, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        M_E_txt.setText(" ");
        M_E_txt.setEnabled(false);
        M_E_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                M_E_txtActionPerformed(evt);
            }
        });
        getContentPane().add(M_E_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 119, 34));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Current Employee:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        C_E_txt.setText(" ");
        C_E_txt.setEnabled(false);
        C_E_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_E_txtActionPerformed(evt);
            }
        });
        getContentPane().add(C_E_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 119, 34));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Leave Employee:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        L_E_txt.setText(" ");
        L_E_txt.setEnabled(false);
        L_E_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                L_E_txtActionPerformed(evt);
            }
        });
        getContentPane().add(L_E_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 119, 34));

        Close_btn.setText("Close");
        Close_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Close_btnActionPerformed(evt);
            }
        });
        getContentPane().add(Close_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 110, 50));

        Enroll_btn.setText("Enroll");
        Enroll_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enroll_btnActionPerformed(evt);
            }
        });
        getContentPane().add(Enroll_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Date_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Date_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Date_txtActionPerformed

    private void M_E_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_M_E_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_M_E_txtActionPerformed

    private void C_E_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_E_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_E_txtActionPerformed

    private void L_E_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_L_E_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_L_E_txtActionPerformed

    private void ScheduleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScheduleTableMouseClicked
       Date_txt.setText(ScheduleTable.getModel().getValueAt(ScheduleTable.getSelectedRow(),0).toString());
        Period_txt.setText(ScheduleTable.getModel().getValueAt(ScheduleTable.getSelectedRow(),1).toString());
        M_E_txt.setText(ScheduleTable.getModel().getValueAt(ScheduleTable.getSelectedRow(),2).toString());
        C_E_txt.setText(ScheduleTable.getModel().getValueAt(ScheduleTable.getSelectedRow(),3).toString());
        L_E_txt.setText(ScheduleTable.getModel().getValueAt(ScheduleTable.getSelectedRow(),4).toString());
    }//GEN-LAST:event_ScheduleTableMouseClicked

    private void Close_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Close_btnActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_Close_btnActionPerformed

    private void Enroll_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enroll_btnActionPerformed
        Employee em = new Employee();
        int count = 0;
        int period = 0;
        int current = 0;
        int checkmax = 0;
        int checkduplicated = 0;
        String ID = null;
        if(Period_txt.getText().equals("08.00-15.30")){
            period = 1;
        }else{
             period = 2;
        }
        String findid = "SELECT SC_ID,SC_EMPLIMIT,SC_EMPCUR FROM SCHEDULE WHERE SC_DATE ='"+Date_txt.getText()+"' AND SCS_ID ='"+ period+ "'";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(findid);
        rs = pat.executeQuery(findid);
        while(rs.next()){
          ID = rs.getString("SC_ID");
          checkmax = rs.getInt("SC_EMPLIMIT");
          current = rs.getInt("SC_EMPCUR");
        }
        rs.close();
        pat.close();
        String checkid = "SELECT SL_NUMBER FROM SCHEDULE_LIST";
        pat = con.prepareStatement(checkid);
        rs = pat.executeQuery(checkid);
        while(rs.next()){
            count++;
            System.out.print("SL_NUMBER");
            if((rs.getInt("SL_NUMBER"))>max){
               max = rs.getInt("SL_NUMBER");
               } 
            }
        if(count==0){
            max =0;
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
        }
        String checkduplicate = "SELECT SL_NUMBER FROM SCHEDULE_LIST NATURAL JOIN SCHEDULE WHERE SC_DATE = '"+Date_txt.getText()+"' AND EMP_ID = '"+em.getshowid()+"'";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(checkduplicate);
        rs = pat.executeQuery(checkduplicate);
        while(rs.next()){
            checkduplicated ++;
        }
        con.close();
        pat.close();
        rs.close();
        }catch(Exception e){
        }
        //System.out.print("SELECT SL_NUMBER FROM SCHEDULE_LIST NATURAL JOIN SCHEDULE WHERE SC_DATE = '"+Date_txt.getText()+"' AND EMP_ID = '"+em.getshowid()+"'");
        if(current<checkmax){
        if(checkduplicated==0){
        max++;
        String sql = "INSERT INTO SCHEDULE_LIST VALUE('"+max+"','"+ID+"','"+em.getshowid().toString()+"',"+"'N',"+"'NULL',"+"'N')";
        try{
            
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(sql);
        System.out.print("INSERT INTO SCHEDULE_LIST VALUE('"+max+"','"+ID+"','"+em.getshowid().toString()+"',"+"'N',"+"'NULL',"+"'N')");
        pat.executeUpdate(sql);
        pat.close();
        con.close();
        }catch(Exception e){
            
        }
        String updatecurrent = "UPDATE SCHEDULE SET SC_EMPCUR ='"+(current+1)+"' WHERE SC_ID = '"+ID+"';";
        try{
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(updatecurrent);
        pat.executeUpdate(updatecurrent);
        pat.close();
        JOptionPane.showMessageDialog(null, "Enroll successd");
        con.close();
        DefaultTableModel dm = (DefaultTableModel)ScheduleTable.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        showSchedule();  
       }catch(Exception e){
       }
        
                }else{
                JOptionPane.showMessageDialog(null,"You enrolled in this day already!");
                }    
        }else{
            JOptionPane.showMessageDialog(null,"This period was limited.\nYou can't enrolled to this period!");
        }
            
        
    }//GEN-LAST:event_Enroll_btnActionPerformed

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
            java.util.logging.Logger.getLogger(Schedule_Enroll_timeshift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Schedule_Enroll_timeshift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Schedule_Enroll_timeshift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Schedule_Enroll_timeshift.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Schedule_Enroll_timeshift().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField C_E_txt;
    private javax.swing.JButton Close_btn;
    private javax.swing.JLabel Date;
    private javax.swing.JTextField Date_txt;
    private javax.swing.JButton Enroll_btn;
    private javax.swing.JTextField L_E_txt;
    private javax.swing.JTextField M_E_txt;
    private javax.swing.JLabel M_Employee;
    private javax.swing.JLabel Period;
    private javax.swing.JTextField Period_txt;
    private javax.swing.JTable ScheduleTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
