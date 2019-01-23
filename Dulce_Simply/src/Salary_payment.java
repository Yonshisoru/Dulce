/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
   ArrayList<Salary_variable> worktime = new ArrayList<>();
   ArrayList<Employee> employee = new ArrayList<>();
   ArrayList<Salary_variable> salary = new ArrayList<>();
   ArrayList<Salary_variable> salaryv2 = new ArrayList<>();
 ArrayList<Salary_variable> salaryv3 = new ArrayList<>();
   ArrayList<String> Salaryid = new ArrayList<>();
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
    String old = null;
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
        getworktime();
        getemployee();
        getsalary();
        gettotaltime();
        showsalary();
        export_btn.setEnabled(false);
        //showSchedule();
        //test();
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
    public void getworktime(){
        String sql = "SELECT W_ID,EMP_ID,W_DATE,W_TOTALTIME,W_STATUS FROM WORKTIME WHERE W_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
            Salary_variable s = new Salary_variable();
            s.setid(rs.getString("W_ID"));
            s.e.setid(rs.getString("EMP_ID"));
            s.setdate(rs.getString("W_DATE"));
            s.settotal(rs.getDouble("W_TOTALTIME"));
            s.setstatus(rs.getString("W_STATUS"));
            worktime.add(s);
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void getemployee(){
        try{

        String sql  ="select EMP_ID,EMP_FNAME,EMP_LNAME,EMP_SALARY,EMP_ADDRESS,POS_NAME,EMP_AGE,EMP_GENDER,EMP_START,EMP_PHONE,EMP_EMAIL from EMPLOYEE NATURAL JOIN EMP_POSITION WHERE EMP_DEL = 'N' ORDER BY EMP_ID";         
        pat = getcon().prepareStatement(sql);
        rs = pat.executeQuery(sql);
        while(rs.next()){
            Employee e = new Employee(rs.getString("EMP_ID"),rs.getString("EMP_FNAME"),rs.getString("EMP_LNAME"),rs.getString("POS_NAME"),rs.getInt("EMP_AGE"),rs.getString("EMP_GENDER"),rs.getString("EMP_PHONE"),rs.getString("EMP_EMAIL"),rs.getString("EMP_START"));
            e.setaddress(rs.getString("EMP_ADDRESS"));
            e.setsalary(rs.getInt("EMP_SALARY"));
            employee.add(e);
        }
        rs.close();
        pat.close();
        getcon().close();
        
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    public void getsalary(){
       String sql = "SELECT SP_ID,EMP_ID,SP_DATE,SP_S_PEROID,SP_E_PEROID FROM SALARY_PAYMENT WHERE SP_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
            Salary_variable s = new Salary_variable();
            s.setid(rs.getString("SP_ID"));
            s.e.setid(rs.getString("EMP_ID"));
            s.setdate(rs.getString("SP_DATE"));
            s.setstart(rs.getString("SP_S_PEROID"));
            s.setend(rs.getString("SP_E_PEROID"));
            for(Salary_variable ss:worktime){
              Salary_variable sv = new Salary_variable();
                /* System.out.println(Integer.parseInt(ss.getdate().substring(8,10))>=Integer.parseInt(s.getstart().substring(8,10)));
                System.err.println(Integer.parseInt(ss.getdate().substring(8,10))<=Integer.parseInt(s.getend().substring(8,10)));
                System.out.println("--------");*/
                //System.out.println(Integer.parseInt(ss.getdate().substring(8,10))<=Integer.parseInt(s.getend().substring(8,10)));
                if(Integer.parseInt(ss.getdate().substring(8,10))>=Integer.parseInt(s.getstart().substring(8,10))&&
                    Integer.parseInt(ss.getdate().substring(8,10))<=Integer.parseInt(s.getend().substring(8,10)) &&
                      Integer.parseInt(ss.getdate().substring(5,7))==Integer.parseInt(s.getstart().substring(5,7))&&
                        Integer.parseInt(ss.getdate().substring(5,7))==Integer.parseInt(s.getend().substring(5,7))&&
                            Integer.parseInt(ss.getdate().substring(0,4))==Integer.parseInt(s.getstart().substring(0,4))&&
                                Integer.parseInt(ss.getdate().substring(0,4))==Integer.parseInt(s.getend().substring(0,4))&&
                     s.e.getid().equals(ss.e.getid())){
                    sv.setid(s.getid());
                    sv.setworkid(ss.getid());
                    salaryv2.add(sv);
                    //System.out.println(s.getid());
                    //System.out.println(ss.getid());
                }
            }
            salary.add(s);
            }
            rs.close();
            pat.close();
            for(Salary_variable s:salaryv2){
                boolean isFound = false;
                if(Salaryid.isEmpty()==true){
                    Salaryid.add(s.getid());
                }else{
                    for(String eiei:Salaryid){
                        if(eiei.equals(s.getid())){
                            isFound = true;
                            break;
                        }
                    }
                    if(isFound==false){
                        Salaryid.add(s.getid());
                    }
                }
            }
            for(String eiei:Salaryid){
                System.out.println(eiei);
            }
            getcon().close();
        }catch(Exception e){
            
        }
    }
    public void gettotaltime(){
            for(int i =0;i<Salaryid.size();i++){
                double total = 0.0;
                Salary_variable s = new Salary_variable();
               for(Salary_variable ss:salaryv2){
                   if(Salaryid.get(i).equals(ss.getid())){
                       for(Salary_variable sss:worktime){
                           if(ss.getworkid().equals(sss.getid())){
                               total += sss.gettotal();
                               System.out.println(i+"**"+sss.gettotal());
                               break;
                           }
                       }
                   }
            }
               System.err.println(i+" "+total);
               s.setid(Salaryid.get(i));
               s.setworktime(total);
                for(Salary_variable ssss:salary){
                   if(ssss.getid().equals(s.getid())){
                       s.e.setid(ssss.e.getid());
                       System.out.println(ssss.e.getid());
                       break;
                   }
               }
               salaryv3.add(s);
               System.err.println("##########");
        }
    }
    public void showsalary(){
       DefaultTableModel model = (DefaultTableModel)Show_salarypayment.getModel();
        Object[] row = new Object[8];
        for(int i=0;i<salaryv3.size();i++){
            row[0]=salaryv3.get(i).getid();
            row[6]=salaryv3.get(i).getworktime();
            for(Employee e:employee){
                if(e.getid().equals(salaryv3.get(i).e.getid())){
                  row[1]=e.getfname()+" "+e.getlname(); 
                  row[5] = e.getsalary();
                  row[7] = e.getsalary()*salaryv3.get(i).getworktime();
                  break;
                }
            }
            for(Salary_variable sss:salary){
                if(sss.getid().equals(salaryv3.get(i).getid())){
                    row[2]=sss.getdate();
                    row[3]=sss.getstart();
                    row[4]=sss.getend();
                    break;
                }
            }
            model.addRow(row);
        }
    }
   /*ArrayList<Salary_variable> Salary_payment = new ArrayList<>();
   public ArrayList<Salary_variable>Salary_payment_List(){
        //String change = "SELECT SP_ID,EMP_ID,EMP_FNAME,EMP_LNAME,POS_ID,POS_NAME,EMP_PHONE,EMP_EMAIL,EMP_ADDRESS,EMP_SALARY,SP_DATE,SP_S_PEROID,SP_E_PEROID,SUM(W_TOTALTIME),(EMP_SALARY*W_TOTALTIME) FROM ((SALARY_PAYMENT NATURAL JOIN EMPLOYEE)NATURAL JOIN WORKTIME)NATURAL JOIN EMP_POSITION WHERE W_STATUS = 'Y' AND EMP_DEL = 'N' AND W_DATE BETWEEN SP_S_PEROID AND SP_E_PEROID GROUP BY EMP_ID HAVING SUM(W_TOTALTIME) ORDER BY SP_ID";         
        String change = "SELECT SP_ID,EMP_FNAME,EMP_LNAME,SP_DATE,EMP_SALARY,SP_S_PEROID,SP_E_PEROID,W_TOTALTIME,EMP_SALARY,POS_NAME,EMP_EMAIL,EMP_PHONE,EMP_ADDRESS,EMP_SALARY*W_TOTALTIME FROM ((WORKTIME NATURAL JOIN EMPLOYEE)NATURAL JOIN salary_payment)NATURAL JOIN emp_position ";
        System.out.print(change);
        try{
        Class.forName("com.mysql.jdbc.Driver");     
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        /*con = DriverManager.getConnection(d.url(),d.username(),d.password());
        pat = con.prepareStatement(change);
        rs = pat.executeQuery(change);
        while(rs.next()){
            Salary_variable ep = new Salary_variable();
            ep.setid(rs.getString("SP_ID"));
            ep.e.setfname(rs.getString("EMP_FNAME"));
            ep.e.setlname(rs.getString("EMP_LNAME"));
            ep.setworktime(rs.getDouble("W_TOTALTIME"));
            ep.setdate(rs.getString("SP_DATE"));
            ep.setstart(rs.getString("SP_S_PEROID"));
            ep.setend(rs.getString("SP_E_PEROID"));
            ep.e.setsalary(rs.getInt("EMP_SALARY"));
            ep.e.setaddress(rs.getString("EMP_ADDRESS"));
            ep.e.setphone(rs.getString("EMP_PHONE"));
            ep.e.setemail(rs.getString("EMP_EMAIL"));
            ep.e.setposition(rs.getString("POS_NAME"));
            ep.settotal(rs.getDouble("EMP_SALARY*W_TOTALTIME"));
            Salary_payment.add(ep);
        }
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Salary_payment;
    }*/
   /*public void test(){z
        ArrayList<Salary_variable> Schedule = ScheduleList();
        System.out.print(Schedule.size());
        for(int i =0;i<Schedule.size();i++){
            System.out.print(Schedule.get(i).getid());
        }
   }*/
       /*private void showSchedule(){
        DefaultTableModel model = (DefaultTableModel)Show_salarypayment.getModel();
        Object[] row = new Object[8];
        for(int i=0;i<salary.size();i++){
            row[0]=Salary_payment.get(i).getid();
            row[1]=Salary_payment.get(i).e.getfname()+" "+Salary_payment.get(i).e.getlname();
            row[2]=Salary_payment.get(i).getdate();
            row[3]=Salary_payment.get(i).getstart();
            row[4]=Salary_payment.get(i).getend();
            row[5]=Salary_payment.get(i).e.getsalary();
            row[6]=Salary_payment.get(i).getworktime();
            row[7]=Salary_payment.get(i).gettotal();
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
        export_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Show_salarypayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสการจ่ายเงิน", "ชื่อ", "วันที่", "ช่วงเริ่มคิดเงินเดือน", "ช่วงสิ้นสุดคิดเงินเดือน", "เงินต่อชม", "เวลางานทั้งหมด", "จำนวนเงินสุทธิ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        getContentPane().add(Show_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 910, 440));

        jButton1.setText("ปิด");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 110, 40));

        export_btn.setText("Export to PDF");
        export_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_btnActionPerformed(evt);
            }
        });
        getContentPane().add(export_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 520, 110, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void export_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_btnActionPerformed
 try{
     for(Salary_variable s:salaryv3){
         if(s.getid().equals(sid)){
         for(Employee e:employee){
             if(s.e.getid().equals(e.getid())){
        Document doc = new Document();
        String name = e.getid()+"$"+sid+"$"+LocalDate.now()+".pdf";
        PdfWriter.getInstance(doc,new FileOutputStream("Salary_receipt/"+name));
        doc.open();
        doc.add(new Phrase(String.format("%38s","ID: "),Bold));
        doc.add(new Phrase(String.format("%s\n",sid),normal));
        doc.add(new Phrase(String.format("%38s","Date: "),Bold));
        doc.add(new Phrase(String.format("%s\n",LocalDate.now()),normal));
        doc.add(new Paragraph(String.format("\n%25s","Dulce Simply"),Topic));
        doc.add(new Paragraph(String.format("%s","------------------------------------------------\n\n"),Bold));
        doc.add(new Paragraph(String.format("Employee Details"),Bold));
        doc.add(new Phrase(String.format("  %s","Name: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e.getfname() +" "+e.getlname()),normal));
        doc.add(new Phrase(String.format("  %s","Position: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e.getposition()),normal));
        doc.add(new Phrase(String.format("  %s","Phone: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e.getphone()),normal));
        doc.add(new Phrase(String.format("  %s","Email: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e.getemail()),normal));
        doc.add(new Phrase(String.format("  %s","Address: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e.getaddress()),normal));
        doc.add(new Paragraph(String.format("\nSalary Details\n"),Bold));
        doc.add(new Phrase(String.format("  %s","Started Period: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",s_p),normal));
        doc.add(new Phrase(String.format("  %s","Ended Period: "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e_p),normal));
        doc.add(new Phrase(String.format("  %s","Salary Per Hour : "),Bold));
        doc.add(new Phrase(String.format("  %s\n",e.getsalary()),normal));
        doc.add(new Phrase(String.format("  %s","Total Time : "),Bold));
        doc.add(new Phrase(String.format("  %s\n",(Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),6).toString())),normal));
        doc.add(new Phrase(String.format("  %s","Total Salary : "),Bold));
        doc.add(new Phrase(String.format("  %s\n",(Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),7).toString())),normal));
        doc.add(new Paragraph(String.format("%s","------------------------------------------------"),Bold));
        doc.close();
        Desktop.getDesktop().open(new File("./Salary_receipt/"+name));
                     break;
             }
         }
         }
     }
}catch (DocumentException ex){
    Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE,null,ex);
}      catch (FileNotFoundException ex) {
           Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE, null, ex);
}catch(IOException ex){
        Logger.getLogger(Salary_payment.class.getName()).log(Level.SEVERE, null, ex);
}
    }//GEN-LAST:event_export_btnActionPerformed

    private void Show_salarypaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Show_salarypaymentMouseClicked
    sid= (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),0).toString());
    sdate=(Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),2).toString());
    name = (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),1).toString());
    s_p = (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),3).toString());
    e_p = (Show_salarypayment.getModel().getValueAt(Show_salarypayment.getSelectedRow(),4).toString());
    index = Show_salarypayment.rowAtPoint(evt.getPoint());
    export_btn.setEnabled(true);
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
    private javax.swing.JButton export_btn;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
