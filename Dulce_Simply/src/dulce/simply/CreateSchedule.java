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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Yonshisoru
 */
public class CreateSchedule extends javax.swing.JFrame {
    int max = Integer.MIN_VALUE;
    String eid = null;
    int oldsize = 0;
    String date;
    String output = null; 
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    Statement st = null;
    /**
     * Creates new form CreateSchedule
     */
    public CreateSchedule() {
        initComponents();
    showSchedule();
}
   public String id(){
          String sql  ="select SC_ID from SCHEDULE";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
    pat = con.prepareStatement(sql);
     rs = pat.executeQuery(sql);
    if(rs.next()){
    while(rs.next()){
        if(Integer.parseInt(rs.getString("SC_ID").substring(1,4))>max){
            max = Integer.parseInt(rs.getString("SC_ID").substring(1,4));
        }
    }
    }
    else{
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
    Schedule_showid.setText(output);
    con.close();
    pat.close();
    rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   } 



            /**
     * Creates new form Employee_Table
     */
    public ArrayList<Schedule>ScheduleList(){
        ArrayList<Schedule> Schedulelist = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select SC_ID,SC_DATE,SCS_ID,SCS_NAME,SC_EMPLIMIT,SC_EMPCUR,SC_LEAVE,SC_DEL FROM SCHEDULE NATURAL JOIN SC_SHIFT WHERE SC_DEL = 'N'";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = con.prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            /*int id = Integer.parseInt(rs.getString("SC_ID").substring(1,4));
            if(id>max){
                max = id;
            }*/
            Schedule e = new Schedule(rs.getString("SC_ID"),rs.getString("SC_DATE"),rs.getString("SCS_NAME"),rs.getInt("SC_EMPLIMIT"),rs.getInt("SC_EMPCUR"),rs.getInt("SC_EMPCUR"));
            Schedulelist.add(e);
        }/*
        max += 1;
        if(max<10){
            eid = "00"+(max);
        }else if(max<100){
                eid = "0"+(max);
        }else if(max<=100){
                eid = ""+(max);
        }
        Schedule_showid.setText("E"+(eid));*/
        con.close();
        pat.close();
        rs.close();
        Schedule_showid.setText(id());
        }catch(Exception e){
            e.printStackTrace();
        }
        return Schedulelist;
    }
       public void showSchedule(){
        ArrayList<Schedule> Schedule = ScheduleList();
        DefaultTableModel model = (DefaultTableModel)ScheduleTable.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<Schedule.size();i++){
            row[0]=Schedule.get(i).getSid();
            row[1]=Schedule.get(i).getdate();
            row[2]=Schedule.get(i).getperiod();
            row[3]=Schedule.get(i).getlimit();
            row[4]=Schedule.get(i).getcurrent();
            row[5]=Schedule.get(i).getleave();
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
        CreateSchedule = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Schedule_showid = new javax.swing.JTextField();
        MaxEmployee = new javax.swing.JComboBox<>();
        ChoosePeriod = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        Schedule_date = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ScheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Period", "Max", "Current", "Leave"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ScheduleTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ScheduleTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 460, 496));

        CreateSchedule.setText("Create");
        CreateSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateScheduleActionPerformed(evt);
            }
        });
        getContentPane().add(CreateSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 70, 40));

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 70, 40));

        Schedule_showid.setEnabled(false);
        Schedule_showid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Schedule_showidActionPerformed(evt);
            }
        });
        getContentPane().add(Schedule_showid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 170, 30));

        MaxEmployee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        getContentPane().add(MaxEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 170, 30));

        ChoosePeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08.00-15.30", "15.00-22.30" }));
        getContentPane().add(ChoosePeriod, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 170, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Max Employee:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Date:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 40, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Period:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 70, 40));

        Schedule_date.setCalendarPreferredSize(new java.awt.Dimension(280, 230));
        Schedule_date.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        getContentPane().add(Schedule_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 170, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Schedule_showidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Schedule_showidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Schedule_showidActionPerformed

    private void CreateScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateScheduleActionPerformed
        boolean createallow = false;
        int chooseperiod = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String gg = sdf.format(Schedule_date.getSelectedDate().getTime());
        System.out.print("SELECT SC_ID,SCS_ID FROM SCHEDULE WHERE SC_DATE ='"+(Integer.parseInt(gg.substring(0,4))-543)+"-"+gg.substring(gg.length()-5,gg.length()-3)+"-"+gg.substring(gg.length()-2,gg.length())+"' AND SC_DEL='N'");
        int count = 0;
        boolean firstperiod = false;
        boolean secondperiod = false;
        System.out.print((Integer.parseInt(gg.substring(0,4))/*-543*/)+"-"+gg.substring(gg.length()-5,gg.length()-3)+"-"+gg.substring(gg.length()-2,gg.length()));
        String sql  ="Insert into SCHEDULE(SC_ID,SC_DATE,SCS_ID,SC_EMPLIMIT,SC_DEL) VALUES (?,?,?,?,'N')";  
        String check = "SELECT SC_ID,SCS_ID FROM SCHEDULE WHERE SC_DATE ='"+(Integer.parseInt(gg.substring(0,4))/*-543*/)+"-"+gg.substring(gg.length()-5,gg.length()-3)+"-"+gg.substring(gg.length()-2,gg.length())+"' AND SC_DEL='N'";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = con.prepareStatement(check);
        rs =  pat.executeQuery();
        while(rs.next()){
            if(rs.getInt("SCS_ID")==1){
                count++;
                firstperiod = true;
            }else if(rs.getInt("SCS_ID")==2){
                count++;
                secondperiod = true; 
            }
        }
        if(ChoosePeriod.getSelectedItem().equals("08.00-15.30")){
        chooseperiod = 1;
        }else if(ChoosePeriod.getSelectedItem().equals("15.00-22.30")){
        chooseperiod = 2;
        }
        pat.close();
        rs.close();
            if(firstperiod==true&&secondperiod==true){
                JOptionPane.showMessageDialog(null, "Create all periods this day already!");
            }else if(firstperiod==true&&chooseperiod==1){
                JOptionPane.showMessageDialog(null, "Create first period this day already!");
            }else if(secondperiod==true&&chooseperiod==2){
                JOptionPane.showMessageDialog(null, "Create second period this day already!");
            }else{
        Class.forName("com.mysql.jdbc.Driver");
        pat = con.prepareStatement(sql);
        pat.setString(1, Schedule_showid.getText());
        String date = Schedule_date.getText();
        pat.setString(2, (Integer.parseInt(gg.substring(0,4))-543)+"-"+gg.substring(gg.length()-5,gg.length()-3)+"-"+gg.substring(gg.length()-2,gg.length()));
        if(ChoosePeriod.getSelectedItem().equals("08.00-15.30")){
            pat.setString(3,"1");
        }else if(ChoosePeriod.getSelectedItem().equals("15.00-22.30")){
            pat.setString(3,"2");
        }
        pat.setInt(4,Integer.parseInt(MaxEmployee.getSelectedItem().toString()));
        pat.executeUpdate();
        con.close();
        pat.close();
        rs.close();
        DefaultTableModel dm = (DefaultTableModel)ScheduleTable.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        showSchedule();  
            }
        }catch(Exception e){
    }
    }//GEN-LAST:event_CreateScheduleActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        max--;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="UPDATE SCHEDULE SET SC_DEL=? WHERE SC_ID= ?";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = con.prepareStatement(sql);
        pat.setString(1,"Y");
        pat.setString(2,ScheduleTable.getModel().getValueAt(ScheduleTable.getSelectedRow(),0).toString());
        pat.executeUpdate();
        con.close();
        }catch(Exception e){
            
        }finally{
            try{
          pat.close();
                  }catch(Exception e){   
                  }
        }
        this.setVisible(false);
        this.setVisible(true);
        DefaultTableModel dm = (DefaultTableModel)ScheduleTable.getModel();
        while(dm.getRowCount() > 0)
        {       
        dm.removeRow(0);
        }
        showSchedule();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CreateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateSchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ChoosePeriod;
    private javax.swing.JButton CreateSchedule;
    private javax.swing.JComboBox<String> MaxEmployee;
    private javax.swing.JTable ScheduleTable;
    private datechooser.beans.DateChooserCombo Schedule_date;
    private javax.swing.JTextField Schedule_showid;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
