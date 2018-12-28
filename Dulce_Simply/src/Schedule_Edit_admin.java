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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Schedule_Edit_admin extends javax.swing.JFrame {
    Database d = new Database();
    Main_variable m = new Main_variable();
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    int id = 0;
    Statement st = null;
    String date = null;
    String period = null;
    int limit = 0;
    int cur = 0;
    int periodid = 0;
    int source = 0;
    String year;
    String month;
    String day;
String status = null;    /**
     * Creates new form Schedule_Edit
     */
    public Schedule_Edit_admin() {
        initComponents();
        emp_id.setText(m.getid());
        emp_name.setText(m.getname());
        showAvailableSchedule();
        showSchedule();
    }
   public ArrayList<Schedule>ScheduleList(){
        Employee ep = new Employee();
        System.out.print(m.getid());
        ArrayList<Schedule> Schedulelist = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select SC_ID,SC_DATE,SCS_ID,SCS_NAME,SC_EMPLIMIT,SC_EMPCUR,SC_LEAVE,SC_DEL,SL_NUMBER FROM (SCHEDULE NATURAL JOIN SC_SHIFT)NATURAL JOIN SCHEDULE_LIST WHERE SC_DATE > '"+LocalDate.now()+"'AND SC_DEL = 'N' AND EMP_ID ='"+m.getid()+"' ORDER BY SL_NUMBER;";         
        System.out.print(sql);
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            System.out.print(rs.getInt("SL_NUMBER"));
            Schedule e = new Schedule(rs.getString("SC_ID"),rs.getString("SC_DATE"),rs.getString("SCS_NAME"),rs.getInt("SC_EMPLIMIT"),rs.getInt("SC_EMPCUR"),rs.getInt("SC_LEAVE"));
            e.setlistid(rs.getInt("SL_NUMBER"));
            Schedulelist.add(e);
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Schedulelist;
    }
       private void showSchedule(){
        ArrayList<Schedule> Schedule = ScheduleList();
        DefaultTableModel model = (DefaultTableModel)ShowSchedule.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<Schedule.size();i++){
            row[0]=Schedule.get(i).getlistid();
            row[1]=Schedule.get(i).getdate();
            row[2]=Schedule.get(i).getperiod();
            model.addRow(row);
        }
    }
   public ArrayList<Schedule>AvailableSchedule(){
        ArrayList<Schedule> Availablelist = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select SC_ID,SC_DATE,SCS_ID,SCS_NAME,SC_EMPLIMIT,SC_EMPCUR,SC_LEAVE,SC_DEL FROM SCHEDULE NATURAL JOIN SC_SHIFT WHERE SC_DATE > '"+LocalDate.now()+"' AND SC_DEL = 'N';";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            Schedule e = new Schedule(rs.getString("SC_ID"),rs.getString("SC_DATE"),rs.getString("SCS_NAME"),rs.getInt("SC_EMPLIMIT"),rs.getInt("SC_EMPCUR"),rs.getInt("SC_LEAVE"));
            if(rs.getInt("SC_EMPCUR")<rs.getInt("SC_EMPLIMIT")){
                e.setstatus("Available");
            }else{
                e.setstatus("Limited");
            }
            Availablelist.add(e);
        }
        con.close();
        pat.close();
        rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Availablelist;
    }
       private void showAvailableSchedule(){
        ArrayList<Schedule> Schedule = AvailableSchedule();
        DefaultTableModel model = (DefaultTableModel)Available.getModel();
        Object[] row = new Object[5];
        for(int i=0;i<Schedule.size();i++){
            row[0]=Schedule.get(i).getdate();
            row[1]=Schedule.get(i).getperiod();
            row[2]=Schedule.get(i).getlimit();
            row[3]=Schedule.get(i).getcurrent();
            row[4]=Schedule.get(i).getstatus();
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
        ShowSchedule = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Available = new javax.swing.JTable();
        periodtochange = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        showid = new javax.swing.JTextField();
        showdate = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        emp_name = new javax.swing.JTextField();
        emp_id = new javax.swing.JTextField();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ShowSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Period"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ShowSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowScheduleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ShowSchedule);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 440, 200));

        jLabel1.setText("Date:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        jLabel2.setText("Period:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Enrolled Schedule");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Available Schedule");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, -1, -1));

        Available.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Period", "Max", "Current", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Available.setEnabled(false);
        Available.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AvailableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Available);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 440, 200));

        periodtochange.setEnabled(false);
        periodtochange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                periodtochangeActionPerformed(evt);
            }
        });
        getContentPane().add(periodtochange, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 150, 30));

        jLabel5.setText("ID:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        showid.setEnabled(false);
        showid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showidActionPerformed(evt);
            }
        });
        getContentPane().add(showid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 150, 30));

        showdate.setEnabled(false);
        showdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showdateActionPerformed(evt);
            }
        });
        getContentPane().add(showdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 150, 30));

        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 100, 50));

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 110, 50));

        jLabel6.setText("Employee Name:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 100, -1));

        jLabel7.setText("Employee ID:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 100, -1));

        emp_name.setEditable(false);
        getContentPane().add(emp_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 150, 30));

        emp_id.setEditable(false);
        getContentPane().add(emp_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowScheduleMouseClicked
        id = Integer.parseInt(ShowSchedule.getModel().getValueAt(ShowSchedule.getSelectedRow(),0).toString());
        showid.setText(ShowSchedule.getModel().getValueAt(ShowSchedule.getSelectedRow(),0).toString());
        showdate.setText(ShowSchedule.getModel().getValueAt(ShowSchedule.getSelectedRow(),1).toString());
        if(ShowSchedule.getModel().getValueAt(ShowSchedule.getSelectedRow(),2).toString().equals("08.00-15.30")){
            periodtochange.setText("15.00-22.30");
            periodid = 2;
            source = 1;
        }else{
            periodtochange.setText("08.00-15.30");
            periodid = 1;
            source = 2;
        }
        System.out.print(periodid);
    }//GEN-LAST:event_ShowScheduleMouseClicked

    private void AvailableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AvailableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AvailableMouseClicked

    private void periodtochangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_periodtochangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_periodtochangeActionPerformed

    private void showidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showidActionPerformed

    private void showdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showdateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        m.setchange(false);
        m.seteditenable(1);
        m.seton(0);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String check = "SELECT SC_ID,SC_EMPLIMIT,SC_EMPCUR FROM SCHEDULE NATURAL JOIN SC_SHIFT WHERE SC_DATE ='"+showdate.getText()+"' AND SCS_ID = '"+periodid+"'";
    String findid = "SELECT SC_ID,SC_EMPLIMIT,SC_EMPCUR FROM SCHEDULE NATURAL JOIN SC_SHIFT WHERE SC_DATE ='"+showdate.getText()+"' AND SCS_ID = '"+source+"'";
    String scheduleidafter = null;
    String scheduleidbefore = null;
    int beforecur = 0;
    int aftercur = 0;
    int status = 0;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(check);
        rs =  pat.executeQuery();
        while(rs.next()){
            scheduleidafter = rs.getString("SC_ID");
            /*System.out.println(rs.getInt("SC_EMPLIMIT"));
            System.out.println(rs.getInt("SC_EMPCUR"));*/
            if(rs.getInt("SC_EMPCUR")<rs.getInt("SC_EMPLIMIT")){
                status = 1;
            }else if(rs.getInt("SC_EMPCUR")>=rs.getInt("SC_EMPLIMIT")){
                status = 2;
            }
            aftercur = rs.getInt("SC_EMPCUR");
            System.out.print(scheduleidafter);
        }
        rs.close();
        pat.close();
        pat = con.prepareStatement(findid);
        rs =  pat.executeQuery();
                while(rs.next()){
            scheduleidbefore = rs.getString("SC_ID");
            /*System.out.println(rs.getInt("SC_EMPLIMIT"));
            System.out.println(rs.getInt("SC_EMPCUR"));*/
            beforecur = rs.getInt("SC_EMPCUR");
            System.out.print(scheduleidbefore);
        }
        con.close();
        }catch(Exception e){
            
        }
    if(status==2){
        JOptionPane.showMessageDialog(null, "You can't change your schedule to this period!");
    }else if(status==1){
        String before = "UPDATE SCHEDULE SET SC_EMPCUR = '"+(beforecur-1)+"' WHERE SC_ID ='"+scheduleidbefore+"'";
        String after = "UPDATE SCHEDULE SET SC_EMPCUR = '"+(aftercur+1)+"' WHERE SC_ID ='"+scheduleidafter+"'";
        String edit = "UPDATE SCHEDULE_LIST SET SC_ID ='"+scheduleidafter+"'WHERE SL_NUMBER ='"+id+"'";
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(before);
        pat.executeUpdate(before);
        pat.close();
        pat = con.prepareStatement(after);
        pat.executeUpdate(after);
        pat.close();
        pat = con.prepareStatement(edit);
        pat.executeUpdate(edit);
        pat.close();
        DefaultTableModel dm = (DefaultTableModel)Available.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        showAvailableSchedule();  
        DefaultTableModel dk = (DefaultTableModel)ShowSchedule.getModel();
        while(dk.getRowCount() > 0)
        {       
        dk.removeRow(0);
        }
        showSchedule();  
        con.close();
            }catch(Exception e){
                
            }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        m.setchange(false);
        m.seteditenable(1);
        m.seton(0);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        m.setchange(false);
        m.seteditenable(1);
        m.seton(0);
    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        m.setchange(false);
        m.seteditenable(1);
        m.seton(0);
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Schedule_Edit_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Schedule_Edit_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Schedule_Edit_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Schedule_Edit_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Schedule_Edit_admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Available;
    private javax.swing.JTable ShowSchedule;
    private javax.swing.JTextField emp_id;
    private javax.swing.JTextField emp_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField periodtochange;
    private javax.swing.JTextField showdate;
    private javax.swing.JTextField showid;
    // End of variables declaration//GEN-END:variables
}
