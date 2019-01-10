
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
public class Claim_List_Panel extends javax.swing.JFrame {
//**Create ArrayList
    ArrayList<Stock_Variable>claimarray = new ArrayList<>();
//** Import data from another class
    Claim_Variable c = new Claim_Variable();
    Database d =new Database();
//-----------------------------Initilize variable---------------------------------------//
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    /**
     * Creates new form Claim_List_Panel
     */
    public Claim_List_Panel() {
        initComponents();
        showclaimtable();
        claimid_txt.setText(c.getclaimidview());
    }
    public Connection getcon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(d.url(),d.username(),d.password());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Didn';t connect");
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Stock_Variable>claim(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "Select C_L_NUMBER,PO_ID,PRO_ID,PRO_NAME,C_L_UNITS,C_L_CAUSE,C_L_STATUS FROM CLAIM_LIST NATURAL JOIN PRODUCT WHERE CL_ID = '"+c.getclaimidview()+"' AND C_L_DEL = 'N'";
        System.out.print(sql);
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Stock_Variable s = new Stock_Variable();
            s.c.setclaimlistnumber(rs.getInt("C_L_NUMBER"));
            s.setproductid(rs.getString("PRO_ID"));
            s.setproductname(rs.getString("PRO_NAME"));
            s.setorderid(rs.getString("PO_ID"));
            s.c.setclaimunits(rs.getDouble("C_L_UNITS"));
            s.c.setcause(rs.getString("C_L_CAUSE"));
            s.c.setstatus(rs.getString("C_L_STATUS"));
            claimarray.add(s);
        }
        }catch(Exception e){
            System.out.print(e);
        }
        return claimarray;
    }
    public void showclaimtable(){
        ArrayList<Stock_Variable>claimarray = claim();
        DefaultTableModel model = (DefaultTableModel)claimlisttable.getModel();
        Object[] row = new Object[6];
        for(int i =0;i<claimarray.size();i++){
            row[0]=claimarray.get(i).c.getclaimlistnumber();
            row[1]=claimarray.get(i).getproductname();
            row[2]=claimarray.get(i).c.getclaimunits();
            row[3]=claimarray.get(i).c.getcause();
            row[4]=claimarray.get(i).getorderid();
            row[5]=claimarray.get(i).c.getstatus();
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
        claimlisttable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        claimid_label = new javax.swing.JLabel();
        claimid_txt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(598, 449));
        setPreferredSize(new java.awt.Dimension(640, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        claimlisttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "Product", "Unit", "Cause", "OrderID", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(claimlisttable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 530, 310));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 100, 30));

        claimid_label.setText("ClaimID:");
        getContentPane().add(claimid_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        claimid_txt.setText(" ");
        getContentPane().add(claimid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 100, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Claim_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Claim_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Claim_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Claim_List_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Claim_List_Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel claimid_label;
    private javax.swing.JLabel claimid_txt;
    private javax.swing.JTable claimlisttable;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
