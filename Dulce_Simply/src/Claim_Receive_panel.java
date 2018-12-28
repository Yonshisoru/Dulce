
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_OPTION;
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
public class Claim_Receive_panel extends javax.swing.JFrame {
    ArrayList<Claim_Variable> Claim_Product_Array = new ArrayList<>();
    ArrayList<Claim_Receive_Variable> Claim_Receive_Array = new ArrayList<>();
    ArrayList<Product_variable> Product_Array = new ArrayList<>();
//------------------------------------------------------------------
    Stock_Variable s = new Stock_Variable();
    Database d = new Database();
    Employee e = new Employee();
    Claim_Variable c=  new Claim_Variable();
    Claim_Receive_Variable cr = new Claim_Receive_Variable();
//-----------------------------Initilize variable---------------------------------------//
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
//----------------------------------------------------------------------------
    String Claim_Receive_List_id = null;
    /**
     * Creates new form Claim_receive_panel
     */
    public Claim_Receive_panel() {
        initComponents();
        show_productfromstock();
        Claimreceiveproduct();
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
public ArrayList<Claim_Variable> Claimproduct(){
        Claim_Product_Array.clear();
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select CL_ID,EMP_ID,EMP_FNAME,EMP_LNAME,CL_DATE,CL_REC_DATE,PO_ID,CL_STATUS,COUNT(C_L_NUMBER) FROM (CLAIM NATURAL JOIN CLAIM_LIST)NATURAL JOIN EMPLOYEE WHERE CL_DEL = 'N' AND CL_STATUS = 'N' GROUP BY CL_ID";
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Claim_Variable c = new Claim_Variable();
            c.setclaimid(rs.getString("CL_ID"));
            c.setdate(rs.getString("CL_DATE"));
            c.setreceivedate(rs.getString("CL_REC_DATE"));
            c.setproductcount(rs.getInt("COUNT(C_L_NUMBER)"));
            c.setempfname(rs.getString("EMP_FNAME"));
            c.setemplname(rs.getString("EMP_LNAME"));
            c.setorderid(rs.getString("PO_ID"));
            c.setstatus(rs.getString("CL_STATUS"));
            Claim_Product_Array.add(c);
        }
        rs.close();
        st.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
        return Claim_Product_Array;
}
public /*ArrayList<Claim_Receive_Variable>*/ void Claimreceiveproduct(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT CRL_NUMBER,CR_ID,CRL_UNITS,PRO_ID,PRO_NAME,CRL_CURRENT,CRL_STATUS FROM CLAIM_REC_LIST NATURAL JOIN PRODUCT WHERE CRL_DEL  = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
        System.out.println(rs.getString("CRL_NUMBER"));
        Claim_Receive_Variable cr = new Claim_Receive_Variable();
        cr.setClaim_receive_list_id(rs.getString("CRL_NUMBER"));
        cr.setClaim_receive_id(rs.getString("CR_ID"));
        cr.setProduct_name(rs.getString("PRO_NAME"));
        cr.setClaim_receive_total_unit(rs.getDouble("CRL_UNITS"));
        cr.setClaim_receive_current_unit(rs.getDouble("CRL_CURRENT"));
        cr.setClaim_receive_status(rs.getString("CRL_STATUS"));
        Claim_Receive_Array.add(cr);
        }
        rs.close();
        st.close();
        getcon().close();
    }catch(Exception e){
        System.out.print(e);
    }
}
public void productArray(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "SELECT CRL_NUMBER,CR_ID,CRL_UNITS,CRL_CURRENT,CRL_STATUS FROM CLAIM_REC_LIST WHERE CRL_DEL  = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
        System.out.println(rs.getString("CRL_NUMBER"));
        Claim_Receive_Variable cr = new Claim_Receive_Variable();
        cr.setClaim_receive_list_id(rs.getString("CRL_NUMBER"));
        cr.setClaim_receive_id(rs.getString("CR_ID"));
        cr.setClaim_receive_total_unit(rs.getDouble("CRL_UNITS"));
        cr.setClaim_receive_current_unit(rs.getDouble("CRL_CURRENT"));
        cr.setClaim_receive_status(rs.getString("CRL_STATUS"));
        Claim_Receive_Array.add(cr);
        }
        rs.close();
        st.close();
        getcon().close();
    }catch(Exception e){
        System.out.print(e);
    }
}
public void showClaim_Receive_List_Table(String Claim_receive_id){
    for(Claim_Receive_Variable cr:Claim_Receive_Array){
        if(cr.getClaim_receive_id().equals(Claim_receive_id)){
        DefaultTableModel model = (DefaultTableModel)Claim_Receive_List_Table.getModel();
        Object[] row = new Object[6];
        row[0] = cr.getClaim_receive_id();
        row[1] = cr.getClaim_receive_list_id();
        row[2] = cr.getProduct_name();
        row[3] = cr.getClaim_receive_total_unit();
        row[4] = cr.getClaim_receive_current_unit();
        row[5] = cr.getClaim_receive_status();
        model.addRow(row);
        }
    }
}
    public void show_productfromstock(){
        ArrayList<Claim_Variable>product = Claimproduct();
        DefaultTableModel model = (DefaultTableModel)Claim_Table.getModel();
        Object[] row = new Object[7];
        for(int i=0;i<product.size();i++){
            row[0]=product.get(i).getclaimid();
            row[1]=product.get(i).getdate();
            row[2]=product.get(i).getreceivedate();
            row[3]=product.get(i).getproductcount();
            row[4]=product.get(i).getempfname()+" "+product.get(i).getemplname();
            row[5]=product.get(i).getorderid();
            row[6]=product.get(i).getstatus();
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
        Claim_Receive_List_Table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Claim_Table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 900));
        setPreferredSize(new java.awt.Dimension(900, 850));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Claim_Receive_List_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสรับเคลมสินค้า", "รายการรับเคลมสินค้า", "ชื้ออุปกรณ์", "จำนวนส่งเคลมทั้งหมด", "จำนวนที่รับเคลมขณะนี้", "สถานะการรับเคลม"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Claim_Receive_List_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Claim_Receive_List_TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Claim_Receive_List_Table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 840, 290));

        jButton1.setText("ปิด");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 780, 110, 40));

        jButton2.setText("รีเฟรช");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 780, 110, 40));

        Claim_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสการเคลม", "วันที่ส่งเคลม", "วันที่รับสินค้า", "จำนวนที่ส่งเคลมทั้งหมด", "ชื่อพนักงาน", "รหัสการสั่ง", "สถานะการเคลม"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Claim_Table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Claim_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Claim_TableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Claim_Table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 840, 320));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("รายการรับสินค้าจากการเคลม");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("ตารางรับสินค้าจากการเคลม");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Claim_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Claim_TableMouseClicked
        DefaultTableModel ClaimReceiveListModel = (DefaultTableModel)Claim_Receive_List_Table.getModel();
        while(ClaimReceiveListModel.getRowCount()>0){
            ClaimReceiveListModel.removeRow(0);
        }
        String Claim_Receive_id = null;
        DefaultTableModel model = (DefaultTableModel)Claim_Table.getModel();
        System.out.println(model.getValueAt(Claim_Table.getSelectedRow(),0).toString());
        try{
            String sql = "SELECT CR_ID FROM CLAIM_RECEIVE WHERE CR_DEL = 'N' AND CL_ID = '"+model.getValueAt(Claim_Table.getSelectedRow(),0).toString()+"'";
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery();
            while(rs.next()){
                Claim_Receive_id = rs.getString("CR_ID");
                System.out.println(rs.getString("CR_ID"));
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){
            System.err.println(e);
        }
        showClaim_Receive_List_Table(Claim_Receive_id);
    }//GEN-LAST:event_Claim_TableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        for(Claim_Receive_Variable cr:Claim_Receive_Array){
            System.out.print(cr.getClaim_receive_list_id()+" ");
            System.out.print(cr.getClaim_receive_id()+" ");
            System.out.print(cr.getClaim_receive_total_unit()+" ");
            System.out.print(cr.getClaim_receive_current_unit()+" ");
            System.out.print(cr.getClaim_receive_status()+"\n");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void Claim_Receive_List_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Claim_Receive_List_TableMouseClicked
        if(Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString().equals(Claim_Receive_List_id)){
            if(JOptionPane.showConfirmDialog(null,Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString()+"มีการรับสินค้าจากเคลม",null,JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
            //Double Claim_Receive_Units = JOptionPane.showInputDialog(null, "");
            Claim_Receive_List_id = null;
        }else{
                JOptionPane.showMessageDialog(null,"ยกเลิกรายการ");
                Claim_Receive_List_id = null;
        }
        }else{
        Claim_Receive_List_id = Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_Claim_Receive_List_TableMouseClicked

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
            java.util.logging.Logger.getLogger(Claim_Receive_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Claim_Receive_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Claim_Receive_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Claim_Receive_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Claim_Receive_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Claim_Receive_List_Table;
    private javax.swing.JTable Claim_Table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
