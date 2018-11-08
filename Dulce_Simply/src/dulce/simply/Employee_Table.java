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
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yonshisoru
 */
public class Employee_Table extends javax.swing.JFrame {
    Connection con = null;
    Statement pat = null;
    ResultSet rs = null;

    /**
     * Creates new form Employee_Table
     */
    public Employee_Table() {
        initComponents();
        show_employee();
    }
    public ArrayList<Employee>EmployeeList(){
        ArrayList<Employee> EmployeeList = new ArrayList<>();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select * from EMPLOYEE NATURAL JOIN EMP_POSITION";         
        con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = con.createStatement();
        rs = pat.executeQuery(sql);
        Employee employee;
        while(rs.next()){
            employee=new Employee(rs.getString("EMP_ID"),rs.getString("EMP_FNAME"),rs.getString("EMP_LNAME"),rs.getString("POS_NAME"),rs.getInt("EMP_AGE"),rs.getString("EMP_GENDER"),rs.getString("EMP_PHONE"),rs.getString("EMP_EMAIL"),rs.getString("EMP_START"));
            EmployeeList.add(employee);
        }
        con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return EmployeeList;
}
    public void show_employee(){
        ArrayList<Employee> list = EmployeeList();
        DefaultTableModel model = (DefaultTableModel)emp_table.getModel();
        Object[] row = new Object[9];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getid();
            row[1]=list.get(i).getposition();
            row[2]=list.get(i).getfname();
            row[3]=list.get(i).getlname();
            row[4]=list.get(i).getage();
            row[5]=list.get(i).getgender();
            row[6]=list.get(i).getphone();
            row[7]=list.get(i).getemail();
            row[8]=list.get(i).getdate();
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 550));
        setPreferredSize(new java.awt.Dimension(800, 570));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emp_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Position", "Given name", "Family name", "Age", "Gender", "Phone number", "Email", "Hired date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        emp_table.setColumnSelectionAllowed(true);
        emp_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(emp_table);
        emp_table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (emp_table.getColumnModel().getColumnCount() > 0) {
            emp_table.getColumnModel().getColumn(0).setMinWidth(30);
            emp_table.getColumnModel().getColumn(0).setMaxWidth(40);
            emp_table.getColumnModel().getColumn(4).setMinWidth(30);
            emp_table.getColumnModel().getColumn(4).setMaxWidth(40);
            emp_table.getColumnModel().getColumn(5).setMinWidth(50);
            emp_table.getColumnModel().getColumn(5).setMaxWidth(60);
            emp_table.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 860, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Employee");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(Employee_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_Table().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable emp_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
