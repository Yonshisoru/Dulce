
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
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
public class Menu_deleted_panel extends javax.swing.JFrame {
    Database d = new Database();
    Employee e = new Employee();
    Main_variable m = new Main_variable();
    Product_variable p = new Product_variable();
    //----------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    //------------------------------
    ArrayList<Menu_variable> Menu_Array = new ArrayList<>();
    //---------------------------------
    int windowactive = 0;
    //----------------------------------
    String doubleclick = "";
    /**
     * Creates new form Menu_deleted_panel
     */
    public Menu_deleted_panel() {
        initComponents();
        MenuList();
        show_product();
    }
public Connection getcon(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(d.url(),d.username(),d.password());
    }catch(Exception e){
        System.err.println("Cannot connect to server");
        throw new RuntimeException(e);
    }
    return con;
}

   public void MenuList(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  ="select MENU_ID,MENU_NAME,MENU_PRICE,M_T_ID,M_T_NAME,MENU_DEL FROM MENU NATURAL JOIN MENU_TYPE ORDER BY MENU_ID";         
        /*con = DriverManager.getConnection("jdbc:mysql://localhost:3306/u787124245_dulce","root","");*/
       st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
           Menu_variable m = new Menu_variable();
            m.setid(rs.getString("MENU_ID"));
            m.setname(rs.getString("MENU_NAME"));
            m.setprice(rs.getInt("MENU_PRICE"));
            m.setcataid(rs.getString("M_T_ID"));
            m.setcataname(rs.getString("M_T_NAME"));
            m.setdelete_status(rs.getString("MENU_DEL"));
            Menu_Array.add(m);
        }
        rs.close();
        st.close();
        getcon().close();
        }catch(Exception e){
            System.out.print(e);
        }
}
    public void show_product(){
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<Menu_Array.size();i++){
            row[3]=Menu_Array.get(i).getcataname();
            row[0]=Menu_Array.get(i).getid();
            row[1]=Menu_Array.get(i).getname();
            row[2]=Menu_Array.get(i).getprice();
            if(Menu_Array.get(i).getdelete_status().equals("Y")){
            model.addRow(row);
            }
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
        menu_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 550));
        setPreferredSize(new java.awt.Dimension(700, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "รหัสเมนู", "ชื่อเมนู", "ราคา", "หมวดหมู่"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menu_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(menu_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 530, 370));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("ตารางเมนูที่ถูกลบ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jButton1.setText("รีเฟรช");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 100, 40));

        jButton2.setText("ปิด");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, 90, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(windowactive==0){
            JOptionPane.showMessageDialog(null,"คุณสามารถดับเบิ้ลคลิ๊กที่ตาราง\nเพื่อยกเลิกการลบเมนู");
            windowactive = 1;
        }
    }//GEN-LAST:event_formWindowOpened

    private void menu_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_tableMouseClicked
        if(doubleclick.equals(menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString())){
            if(JOptionPane.showConfirmDialog(null,"คุณต้องการที่จะกู้คืนเมนูรหัส "+menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString()+" หรือไม่ ",null,YES_NO_OPTION)==YES_OPTION){
            doubleclick = "";
            String id = menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString();
            String renewmenu = "UPDATE MENU SET MENU_DEL = 'N' WHERE MENU_ID = '"+id+"'";
            String renewingredient = "UPDATE INGREDIENT SET ING_DEL = 'N' WHERE MENU_ID = '"+id+"'";
            System.out.println(renewmenu);
            System.out.println(renewingredient);
            try{
                pat = getcon().prepareStatement(renewmenu);
                pat.executeUpdate(renewmenu);
                pat.close();
                    pat = getcon().prepareStatement(renewingredient);
                    pat.executeUpdate(renewingredient);
                    pat.close();
                    getcon().close();
            }catch(Exception e){
                System.err.println(e);
            }
        Menu_Array.clear();
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        MenuList();
        show_product();   
            JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น\nกู้คืนเมนูรหัส "+id+" สำเร็จ");
            }else{
            doubleclick = "";
            } 
        }else{
            doubleclick = menu_table.getModel().getValueAt(menu_table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_menu_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Menu_Array.clear();
        DefaultTableModel model = (DefaultTableModel)menu_table.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        MenuList();
        show_product();   
        JOptionPane.showMessageDialog(null,"ทำรายการเสร็จสิ้น");
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
            java.util.logging.Logger.getLogger(Menu_deleted_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_deleted_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_deleted_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_deleted_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_deleted_panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menu_table;
    // End of variables declaration//GEN-END:variables
}
