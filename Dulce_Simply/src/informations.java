/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class informations extends javax.swing.JFrame {

    /**
     * Creates new form informations
     */
    public informations() {
        Employee info = new Employee();
        initComponents();
        employeeid.setText(String.valueOf(info.getid()));
        employeeposition.setText(String.valueOf(info.getposition()));
        employeefirstname.setText(info.getfname());
        employeelastname.setText(info.getlname());
        employeeage.setText(String.valueOf(info.getage()));
        employeegender.setText(info.getgender());
        employeephone.setText(info.getphone());
        employeeemail.setText(info.getemail());
        employeedate.setText(info.getdate());
        employeehour.setText(String.valueOf(info.getduration()));
        employeeaddress.setText(info.getaddress());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        employeeid = new javax.swing.JTextField();
        employeefirstname = new javax.swing.JTextField();
        employeehour = new javax.swing.JTextField();
        employeephone = new javax.swing.JTextField();
        employeeaddress = new javax.swing.JTextField();
        employeeemail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        employeedate = new javax.swing.JTextField();
        employeeage = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        employeelastname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        employeegender = new javax.swing.JTextField();
        inf_close_button = new javax.swing.JToggleButton();
        jLabel11 = new javax.swing.JLabel();
        employeeposition = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Employee id:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Given name:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Current Worked Hours:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Phone:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Address:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Email:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, -1));

        employeeid.setEditable(false);
        employeeid.setEnabled(false);
        employeeid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeidActionPerformed(evt);
            }
        });
        jPanel1.add(employeeid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 130, -1));

        employeefirstname.setEditable(false);
        employeefirstname.setEnabled(false);
        employeefirstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeefirstnameActionPerformed(evt);
            }
        });
        jPanel1.add(employeefirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 130, -1));

        employeehour.setEditable(false);
        employeehour.setEnabled(false);
        employeehour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeehourActionPerformed(evt);
            }
        });
        jPanel1.add(employeehour, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 91, -1));

        employeephone.setEditable(false);
        employeephone.setEnabled(false);
        employeephone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeephoneActionPerformed(evt);
            }
        });
        jPanel1.add(employeephone, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 91, -1));

        employeeaddress.setEditable(false);
        employeeaddress.setEnabled(false);
        employeeaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeaddressActionPerformed(evt);
            }
        });
        jPanel1.add(employeeaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 293, 41));

        employeeemail.setEditable(false);
        employeeemail.setEnabled(false);
        employeeemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeemailActionPerformed(evt);
            }
        });
        jPanel1.add(employeeemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 123, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Hired date:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        employeedate.setEditable(false);
        employeedate.setEnabled(false);
        employeedate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeedateActionPerformed(evt);
            }
        });
        jPanel1.add(employeedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 91, -1));

        employeeage.setEditable(false);
        employeeage.setAlignmentX(0.6F);
        employeeage.setEnabled(false);
        employeeage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeageActionPerformed(evt);
            }
        });
        jPanel1.add(employeeage, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 27, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Age:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Surname:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        employeelastname.setEditable(false);
        employeelastname.setEnabled(false);
        employeelastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeelastnameActionPerformed(evt);
            }
        });
        jPanel1.add(employeelastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 100, 140, -1));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Gender:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 50, -1));

        employeegender.setEditable(false);
        employeegender.setEnabled(false);
        employeegender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeegenderActionPerformed(evt);
            }
        });
        jPanel1.add(employeegender, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 80, -1));

        inf_close_button.setSelected(true);
        inf_close_button.setText("Exit");
        inf_close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inf_close_buttonActionPerformed(evt);
            }
        });
        jPanel1.add(inf_close_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 307, 182, 44));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Position:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        employeeposition.setEditable(false);
        employeeposition.setEnabled(false);
        employeeposition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeepositionActionPerformed(evt);
            }
        });
        jPanel1.add(employeeposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeeidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeidActionPerformed

    private void employeefirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeefirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeefirstnameActionPerformed

    private void employeehourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeehourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeehourActionPerformed

    private void employeephoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeephoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeephoneActionPerformed

    private void employeeaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeaddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeaddressActionPerformed

    private void employeeemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeemailActionPerformed

    private void employeedateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeedateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeedateActionPerformed

    private void employeeageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeageActionPerformed

    private void employeelastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeelastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeelastnameActionPerformed

    private void employeegenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeegenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeegenderActionPerformed

    private void inf_close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inf_close_buttonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_inf_close_buttonActionPerformed

    private void employeepositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeepositionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeepositionActionPerformed

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
            java.util.logging.Logger.getLogger(informations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new informations().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeeaddress;
    private javax.swing.JTextField employeeage;
    private javax.swing.JTextField employeedate;
    private javax.swing.JTextField employeeemail;
    private javax.swing.JTextField employeefirstname;
    private javax.swing.JTextField employeegender;
    private javax.swing.JTextField employeehour;
    private javax.swing.JTextField employeeid;
    private javax.swing.JTextField employeelastname;
    private javax.swing.JTextField employeephone;
    private javax.swing.JTextField employeeposition;
    private javax.swing.JToggleButton inf_close_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}