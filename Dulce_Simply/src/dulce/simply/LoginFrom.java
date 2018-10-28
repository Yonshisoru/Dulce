/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

import javax.swing.Timer;


/**
 *
 * @author Yonshisoru
 */
public class LoginFrom extends javax.swing.JFrame {
            int logincheck = 0;
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
     Timer timer = new Timer(15,new ActionListener() {
        int i =0;
        @Override
        public void actionPerformed(ActionEvent e) {
           if(i==100&&i>=0){
               dispose();
               Main m = new Main();
               m.setVisible(true);
           }
           i++;
           processlogin_label.setText(String.valueOf(i)+" %");
          processlogin.setValue(i);
        }
        });
    public LoginFrom() {
        initComponents();
        //Set frame at center of screen
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jusername = new javax.swing.JTextField();
        Jpassword = new javax.swing.JPasswordField();
        Login = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        processlogin = new javax.swing.JProgressBar();
        processlogin_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dulce Simply");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(500, 350));
        setPreferredSize(new java.awt.Dimension(500, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jusername.setFont(new java.awt.Font("Georgia", 0, 10)); // NOI18N
        Jusername.setFocusTraversalPolicyProvider(true);
        Jusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JusernameActionPerformed(evt);
            }
        });
        getContentPane().add(Jusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 140, -1));

        Jpassword.setFont(new java.awt.Font("Georgia", 0, 10)); // NOI18N
        getContentPane().add(Jpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 140, -1));

        Login.setText("Login");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 69, 50));

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        getContentPane().add(Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 70, 50));

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        getContentPane().add(Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, 70, 50));

        jLabel1.setText("Username:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel2.setText("Password:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));
        getContentPane().add(processlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 295, 460, 10));

        processlogin_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        processlogin_label.setText("0%");
        processlogin_label.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().add(processlogin_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 276, 40, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        timer.stop();
        Exit.setEnabled(false);
        if(JOptionPane.showConfirmDialog(null,"Exit Confirm","System",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
                    JOptionPane.showMessageDialog(this, "Closing Software","System",INFORMATION_MESSAGE);
        this.setVisible(false);
        System.exit(0);
        }else{
            Exit.setEnabled(true);
            if(logincheck==1){
        timer.start();
        }
        }
    }//GEN-LAST:event_ExitActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed

        MySQLConnect check = new MySQLConnect();
        String sql  ="select * from employee where EMP_ID=? and EMP_PASSWORD=?";
        try{
                
        con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = con.prepareStatement(sql);
        pat.setString(1, Jusername.getText());
        pat.setString(2,Jpassword.getText());
        rs = pat.executeQuery();
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null, "Welcome "+rs.getString("EMP_NAME"));
            Jusername.setEnabled(false);
            Jpassword.setEnabled(false);
            Login.setEnabled(false);
            Reset.setEnabled(false);
            Exit.setLabel("Cancel");
            timer.start();
            
        }else{
            JOptionPane.showMessageDialog(null, "Invaild username or password");
        }
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,ex);
       }
    }//GEN-LAST:event_LoginActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        Jusername.setText("");
        Jpassword.setText("");
    }//GEN-LAST:event_ResetActionPerformed

    private void JusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JusernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JusernameActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit;
    private javax.swing.JPasswordField Jpassword;
    private javax.swing.JTextField Jusername;
    private javax.swing.JButton Login;
    private javax.swing.JButton Reset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar processlogin;
    private javax.swing.JLabel processlogin_label;
    // End of variables declaration//GEN-END:variables

}
