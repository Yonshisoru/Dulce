/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Salary_payment extends javax.swing.JFrame {
   Database d = new Database();
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    Statement st = null;
    String year = null;
    String month = null;
    String day = null;
    String s_p = null;
    String e_p = null;
    int index = 0;
    String sid = null;
    String sdate = null;
    String name = null;
    String phone = null;
    String email = null;
    String position = null;
    String address = null;
/********************************Font***************************************/
Font TopFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
Font Topic = new Font(Font.FontFamily.COURIER,24,Font.BOLD);
Font Bold = new Font(Font.FontFamily.COURIER,18,Font.BOLD);
Font normal = new Font(Font.FontFamily.COURIER,15,Font.NORMAL);
/***************************************************************************/
    String pdf = String.format("----------------------------------------------------------------------------------------------------------------------------------"
            + "\n%50s ",sdate);
    /**
     * Creates new form Payment
     */
    public Salary_payment() {
        initComponents();
        showSchedule();
        //test();
    }
   ArrayList<Salary_variable> Salary_payment = new ArrayList<>();
   Salary_variable ep;
   public ArrayList<Salary_variable>Salary_payment_List(){
        String change = "SELECT SP_ID,EMP_ID,EMP_FNAME,EMP_LNAME,POS_ID,POS_NAME,EMP_PHONE,EMP_EMAIL,EMP_ADDRESS,EMP_SALARY,SP_DATE,SP_S_PEROID,SP_E_PEROID,SUM(W_TOTALTIME),(EMP_SALARY*W_TOTALTIME) FROM ((SALARY_PAYMENT NATURAL JOIN EMPLOYEE)NATURAL JOIN WORKTIME)NATURAL JOIN EMP_POSITION WHERE W_STATUS = 'Y' AND EMP_DEL = 'N' AND W_DATE BETWEEN SP_S_PEROID AND SP_E_PEROID GROUP BY EMP_ID HAVING SUM(W_TOTALTIME) ORDER BY SP_ID";         
        System.out.print(change);
        try{
        Class.forName("com.mysql.jdbc.Driver");     
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(change);
        rs = pat.executeQuery(change);
        while(rs.next()){
            ep = new Salary_variable(rs.getString("SP_ID"),rs.getString("EMP_FNAME"),rs.getString("EMP_LNAME"),rs.getString("SP_DATE"),rs.getInt("EMP_SALARY"),rs.getString("SP_S_PEROID"),rs.getString("SP_E_PEROID"),rs.getDouble("SUM(W_TOTALTIME)"),rs.getInt("EMP_SALARY")*rs.getDouble("SUM(W_TOTALTIME)"));
            ep.e.setaddress(rs.getString("EMP_ADDRESS"));
            ep.e.setphone(rs.getString("EMP_PHONE"));
            ep.e.setemail(rs.getString("EMP_EMAIL"));
            ep.e.setposition(rs.getString("POS_NAME"));
            Salary_payment.add(ep);
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Salary_payment;
    }
   /*public void test(){z
        ArrayList<Salary_variable> Schedule = ScheduleList();
        System.out.print(Schedule.size());
        for(int i =0;i<Schedule.size();i++){
            System.out.print(Schedule.get(i).getid());
        }
   }*/
       private void showSchedule(){
        ArrayList<Salary_variable> Salary_payment = Salary_payment_List();
        DefaultTableModel model = (DefaultTableModel)Show_salarypayment.getModel();
        Object[] row = new Object[8];
        for(int i=0;i<Salary_payment.size();i++){
            row[0]=Salary_payment.get(i).getid();
            row[1]=Salary_payment.get(i).getfname()+" "+Salary_payment.get(i).getlname();
            row[2]=Salary_payment.get(i).getdate();
            row[3]=Salary_payment.get(i).getstart();
            row[4]=Salary_payment.get(i).getend();
            row[5]=Salary_payment.get(i).getsalary();
            row[6]=Salary_payment.get(i).getworktime();
            row[7]=Salary_payment.get(i).getsum();
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

        Show_payment = new javax.swing.JScrollPane();
        Show_salarypayment = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(930, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Show_salarypayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Date", "Started Period", "Ended Period", "Salary", "Worktime", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Show_salarypayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Show_salarypaymentMouseClicked(evt);
            }
        });
        Show_payment.setViewportView(Show_salarypayment);

        getContentPane().add(Show_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 780, 440));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 110, 40));

        jButton2.setText("Export to PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 110, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 try{
        Document doc = new Document();
        PdfWriter.getInstance(doc,new FileOutputStream("Salary_receipt/"+sid+" "+LocalDate.now()+".pdf"));
        doc.open();
        doc.add(new Phrase(String.format("%38s","ID: "),Bold));
        doc.add(new Phrase(String.format("%s\n",sid),normal));
        doc.add(new Phrase(String.format("%38s","Date: "),Bold));
        doc.add(new Phrase(String.format("%s\n",LocalDate.now()),normal));
        doc.add(new Paragraph(String.format("\n%25s","Dulce Simply"),Topic));
        doc.add(new Paragraph(String.format("%s","------------------------------------------------\n\n"),Bold));
        doc.add(new Paragraph(String.format("Employee Details"),Bold));
        doc.add(new Phrase(String.format("  %s","Name: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",name),normal));
        doc.add(new Phrase(String.format("  %s","Position: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",Salary_payment.get(index).e.getposition()),normal));
        doc.add(new Phrase(String.format("  %s","Phone: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",Salary_payment.get(index).e.getphone()),normal));
        doc.add(new Phrase(String.format("  %s","Email: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",Salary_payment.get(index).e.getemail()),normal));
        doc.add(new Phrase(String.format("  %s","Address: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",Salary_payment.get(index).e.getaddress()),normal));
        doc.add(new Paragraph(String.format("\nSalary Details\n"),Bold));
        doc.add(new Phrase(String.format("  %s","Started Period: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",s_p),normal));
        doc.add(new Phrase(String.format("  %s","Ended Period: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e_p),normal));
        doc.add(new Phrase(String.format("  %s","Salary Per Hour : "),Bold));
        doc.add(new Phrase(String.format("  %s\n",Salary_payment.get(index).getsalary()),normal));
        doc.add(new Phrase(String.format("  %s","Total Time : "),Bold));
        doc.add(new Phrase(String.format("  %s\n",(Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),6).toString())),normal));
        doc.add(new Phrase(String.format("  %s","Total Salary : "),Bold));
        doc.add(new Phrase(String.format("  %s\n",(Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),7).toString())),normal));
        doc.add(new Paragraph(String.format("%s","------------------------------------------------"),Bold));
        doc.close();
        JOptionPane.showMessageDialog(null, "test");
}catch (DocumentException ex){
    Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE,null,ex);
}      catch (FileNotFoundException ex) {
           Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Show_salarypaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Show_salarypaymentMouseClicked
    sid= (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),0).toString());
    sdate=(Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),2).toString());
    name = (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),1).toString());
    s_p = (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),3).toString());
    e_p = (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),4).toString());
    index = Show_salarypayment.rowAtPoint(evt.getPoint());
    System.out.print(name);
    }//GEN-LAST:event_Show_salarypaymentMouseClicked

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
            java.util.logging.Logger.getLogger(Salary_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salary_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salary_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salary_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salary_payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane Show_payment;
    private javax.swing.JTable Show_salarypayment;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
