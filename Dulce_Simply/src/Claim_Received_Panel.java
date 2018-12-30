
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
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
public class Claim_Received_Panel extends javax.swing.JFrame {
    ArrayList<Claim_Variable> Claim_Product_Array = new ArrayList<>();
    ArrayList<Claim_Received_Variable> Claim_Receive_Array = new ArrayList<>();
    ArrayList<Claim_Received_Variable> Claim_Receive_List_Array = new ArrayList<>();
    ArrayList<Product_variable> Product_Array = new ArrayList<>();
//------------------------------------------------------------------
    Stock_Variable s = new Stock_Variable();
    Database d = new Database();
    Employee e = new Employee();
    Claim_Variable c=  new Claim_Variable();
    Claim_Received_Variable cr = new Claim_Received_Variable();
//-----------------------------Initilize variable---------------------------------------//
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
//----------------------------------------------------------------------------
    String Claim_Receive_List_id = null;
//--------------------------------------------------------------------------
    int max = 0;
//--------------------------------------------------------------------------
    boolean adding = false;
    /**
     * Creates new form Claim_receive_panel
     */
    public Claim_Received_Panel() {
        initComponents();
        show_productfromstock();
        Claimreceiveproduct();
        Adding_Claim_Receive_Array();
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
 public String getStock_id(){
    int count=0;
    String output = null;
    max = 0;
    String sql  ="select STOCK_NUMBER from STOCK WHERE STOCK_DEL = 'N'";
    try{
    Class.forName("com.mysql.jdbc.Driver");
    pat =getcon().prepareStatement(sql);
    rs = pat.executeQuery(sql);
    while(rs.next()){
        count++;
        if(Integer.parseInt(rs.getString("STOCK_NUMBER"))>max){
            max = Integer.parseInt(rs.getString("STOCK_NUMBER"));
        }
    }
    if(count==0){
            max = 0;
    }
    max += 1;
    output = ""+max;
    rs.close();
    pat.close();
    getcon().close();
            }catch(Exception e){
                e.printStackTrace();
            }
    return output;
   }
    public static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
        }
        return date != null;
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
        String sql = "SELECT CRL_NUMBER,CR_ID,CRL_UNITS,PRO_ID,PRO_NAME,CRL_CURRENT,CRL_STATUS FROM CLAIM_REC_LIST NATURAL JOIN PRODUCT WHERE CRL_DEL  = 'N' AND CRL_STATUS = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
        System.out.println(rs.getString("CRL_NUMBER"));
        Claim_Received_Variable cr = new Claim_Received_Variable();
        cr.setClaim_receive_list_id(rs.getString("CRL_NUMBER"));
        cr.setClaim_receive_id(rs.getString("CR_ID"));
        cr.setProduct_id(rs.getString("PRO_ID"));
        cr.setProduct_name(rs.getString("PRO_NAME"));
        cr.setClaim_receive_total_unit(rs.getDouble("CRL_UNITS"));
        cr.setClaim_receive_current_unit(rs.getDouble("CRL_CURRENT"));
        cr.setClaim_receive_status(rs.getString("CRL_STATUS"));
        Claim_Receive_List_Array.add(cr);
        }
        rs.close();
        st.close();
        getcon().close();
    }catch(Exception e){
        System.out.print(e);
    }
}
public void Adding_Claim_Receive_Array(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        String sql  = "SELECT CR_ID,CL_ID FROM CLAIM_RECEIVE WHERE CR_DEL = 'N'";
        st = getcon().createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()){
            Claim_Received_Variable c = new Claim_Received_Variable();
            c.setClaim_id(rs.getString("CL_ID"));
            c.setClaim_receive_id(rs.getString("CR_ID"));
            Claim_Receive_Array.add(c);
        }
        rs.close();
        st.close();
        getcon().close();
    }catch(Exception e){
        
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
        Claim_Received_Variable cr = new Claim_Received_Variable();
        cr.setClaim_receive_list_id(rs.getString("CRL_NUMBER"));
        cr.setClaim_receive_id(rs.getString("CR_ID"));
        cr.setClaim_receive_total_unit(rs.getDouble("CRL_UNITS"));
        cr.setClaim_receive_current_unit(rs.getDouble("CRL_CURRENT"));
        cr.setClaim_receive_status(rs.getString("CRL_STATUS"));
        Claim_Receive_List_Array.add(cr);
        }
        rs.close();
        st.close();
        getcon().close();
    }catch(Exception e){
        System.out.print(e);
    }
}
public void showClaim_Receive_List_Table(String Claim_receive_id){
    if(adding == true){
    Claim_Receive_List_Array.clear();
    Claimreceiveproduct();
    adding = false;
    }
    for(Claim_Received_Variable cr:Claim_Receive_List_Array){
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
    if(adding == true){
    Claim_Receive_List_Array.clear();
    Claimreceiveproduct();
    Claimproduct();
    adding = false;
    }
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
    public void show_claim_product(){
    DefaultTableModel model = (DefaultTableModel)Claim_Table.getModel();
    Object[] row = new Object[7];
    for(int i=0;i<Claim_Product_Array.size();i++){
            row[0]=Claim_Product_Array.get(i).getclaimid();
            row[1]=Claim_Product_Array.get(i).getdate();
            row[2]=Claim_Product_Array.get(i).getreceivedate();
            row[3]=Claim_Product_Array.get(i).getproductcount();
            row[4]=Claim_Product_Array.get(i).getempfname()+" "+Claim_Product_Array.get(i).getemplname();
            row[5]=Claim_Product_Array.get(i).getorderid();
            row[6]=Claim_Product_Array.get(i).getstatus();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 900));
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
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 780, 110, 40));

        jButton2.setText("รีเฟรช");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
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
            for(Claim_Received_Variable cr:Claim_Receive_Array){
                System.out.println(Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString());
                System.err.println(cr.getClaim_id());
                if(Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString().equals(cr.getClaim_id())){
                    Claim_Receive_id = cr.getClaim_receive_id();
                    System.out.println(cr.getClaim_receive_id());
                    break;
                }
            }
            }catch(Exception e){
                    
            }
            /*String sql = "SELECT CR_ID FROM CLAIM_RECEIVE WHERE CR_DEL = 'N' AND CL_ID = '"+model.getValueAt(Claim_Table.getSelectedRow(),0).toString()+"'";
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
        }*/
        showClaim_Receive_List_Table(Claim_Receive_id);
    }//GEN-LAST:event_Claim_TableMouseClicked

    private void Claim_Receive_List_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Claim_Receive_List_TableMouseClicked
    if(Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),0).toString().equals(Claim_Receive_List_id)){
            if(JOptionPane.showConfirmDialog(null,Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),0).toString()+"มีการรับสินค้าจากเคลม",null,JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE)==JOptionPane.OK_OPTION){
            String Claim_Receive_id = Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),0).toString();
            String Claim_Receive_list_id = Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),1).toString();
            String Product_id = null;
            String Stock_id = null;
            String Order_id = null;
            String getDate = null;
            String Claim_ID = null;
            String Claim_List_ID = null;
            Double max = Double.parseDouble(Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),3).toString());
            boolean CorrectlyUnit = false;
            boolean received = false;
            boolean allreceive = true;
            Double Claim_Receive_Units = 0.0;
            Double Claim_Receive_Current_Units = Double.parseDouble(Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),4).toString());
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = LocalDate.now().toString();
                try{
                Claim_Receive_Units = Double.parseDouble(JOptionPane.showInputDialog(null, ""));
                if(Claim_Receive_Units > (max-Claim_Receive_Current_Units) || Claim_Receive_Units<=0){
                    CorrectlyUnit = false;
                }else{
                    CorrectlyUnit = true;
                }
                System.out.print(Claim_Receive_Units);
                }catch(NumberFormatException e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null,"คุณกรอกจำนวนไม่ถูกต้อง\nกรุณาทำรายการใหม่ครับ");
                    throw new NumberFormatException();
                /*}catch(NullPointerException e){
                    throw new NullPointerException();*/
                }
            if(CorrectlyUnit == true){  
            try{
                for(Claim_Received_Variable cy:Claim_Receive_List_Array){
                    if(Claim_Receive_id.equals(cy.getClaim_receive_id())){
                        System.out.print(cy.getClaim_receive_id());
                        Product_id = cy.getProduct_id();
                        break;
                    }
                }
                /*for(Claim_Received_Variable c:Claim_Receive_List_Array){
                    if(Claim_Table.getModel().getValueAt(Claim_Table.getSelectedRow(),0).toString().equals(c.getClaim_receive_id())){
                        Claim_Receive_id = c.getClaim_receive_id();
                        Product_id = c.getProduct_id();
                        System.err.println(Claim_Receive_id);
                        System.err.println(Product_id);
                        break;
                    }
                }*/
                System.err.print(Claim_Receive_id);
                for(Claim_Received_Variable c:Claim_Receive_Array){
                    System.out.println(c.getClaim_receive_id());
                    System.err.println(Claim_Receive_id);
                    if(Claim_Receive_id.equals(c.getClaim_receive_id())){
                        Claim_ID = c.getClaim_id();
                        System.out.println("C--"+Claim_ID);
                        break;
                    }
                    //if(Claim_Receive_id.equals(c.))
                }
                for(Claim_Variable c:Claim_Product_Array){
                    if(Claim_ID.equals(c.getclaimid())){
                        Order_id = c.getorderid();
                        break;
                    }
                }
                System.out.println("O--"+Order_id);
                getDate = JOptionPane.showInputDialog(null,"กรุณากรอกวันที่หมดอายุของสินค้าด้วยครับ\n(ปีค.ศ-เดือน-วันที่)\nตัวอย่าง 2018-12-31",null,INFORMATION_MESSAGE);
                if(isValidFormat(getDate)==true){
                try{
                    Stock_id = getStock_id(); 
                    System.out.print(Stock_id);
                        String sql  = "INSERT STOCK VALUES('"+Integer.parseInt(Stock_id)+"','"+Product_id+"','"+getDate+"','"+today+"','"+Claim_Receive_Units+"','"+Order_id+"','N')";
                    System.err.println(sql);
                        pat = getcon().prepareStatement(sql);
                        pat.executeUpdate(sql);
                        pat.close();
                            String product = "UPDATE PRODUCT SET PRO_UNITS = PRO_UNITS+'"+Claim_Receive_Units+"' WHERE PRO_ID = '"+Product_id+"'";
                            System.err.println(product);
                            pat = getcon().prepareStatement(product);
                            pat.executeUpdate(product);
                            pat.close();
                                String receive = "UPDATE CLAIM_REC_LIST SET CRL_CURRENT = CRL_CURRENT+'"+Claim_Receive_Units+"' WHERE CRL_NUMBER = '"+Claim_Receive_list_id+"'";
                                System.err.println(receive);
                                pat = getcon().prepareStatement(receive);
                                pat.executeUpdate(receive);
                                    String checkunit = "SELECT CRL_CURRENT FROM CLAIM_REC_LIST WHERE CRL_NUMBER = '"+Claim_Receive_list_id+"'";
                                    System.err.println(checkunit);
                                    pat = getcon().prepareStatement(checkunit);
                                    rs = pat.executeQuery(checkunit);
                                    while(rs.next()){
                                        if(max == rs.getDouble("CRL_CURRENT")){
                                            received = true;
                                        }
                                    }
                                    rs.close();
                                    pat.close();
                                    getcon().close();
                                    if(received == true){
                                        String statusreceived = "UPDATE CLAIM_REC_LIST SET CRL_STATUS = 'Y' WHERE CRL_NUMBER = '"+Claim_Receive_list_id+"'";
                                        System.err.println(statusreceived);
                                        pat = getcon().prepareStatement(statusreceived);
                                        pat.executeUpdate(statusreceived);
                                        pat.close();
                                            String findclaimlist = "SELECT C_L_NUMBER FROM CLAIM_LIST WHERE CL_ID = '"+Claim_ID+"'";
                                            System.err.println(findclaimlist);
                                            pat = getcon().prepareStatement(findclaimlist);
                                            rs = pat.executeQuery(findclaimlist);
                                            while(rs.next()){
                                                Claim_List_ID = rs.getString("C_L_NUMBER");
                                            }
                                            rs.close();
                                            pat.close();
                                                String updateclaimliststatus = "UPDATE CLAIM_LIST SET C_L_STATUS = 'Y' WHERE C_L_NUMBER = '"+Claim_List_ID+"'";
                                                System.err.println(updateclaimliststatus);
                                                pat = getcon().prepareStatement(updateclaimliststatus);
                                                pat.executeUpdate(updateclaimliststatus);
                                                pat.close();
                                                getcon().close();
                                        }
                                            String checkclaimreceive = "SELECT CRL_STATUS FROM CLAIM_REC_LIST WHERE CR_ID = '"+Claim_Receive_id+"'";
                                            System.err.println(checkclaimreceive);
                                            pat = getcon().prepareStatement(checkclaimreceive);
                                            rs = pat.executeQuery();
                                            while(rs.next()){
                                                if(rs.getString("CRL_STATUS").equals("N")){
                                                    allreceive = false;
                                                }
                                            }
                                            rs.close();
                                            pat.close();
                                            getcon().close();
                                                if(allreceive==true){
                                                    String statusreceived = "UPDATE CLAIM_RECEIVE SET CR_STATUS = 'Y' WHERE CR_ID = '"+Claim_Receive_id+"'";
                                                    System.err.println(statusreceived);
                                                    pat = getcon().prepareStatement(statusreceived);
                                                    pat.executeUpdate(statusreceived);  
                                                    pat.close();
                                                        String statusclaim = "UPDATE CLAIM SET CL_STATUS = 'Y' WHERE CL_ID = '"+Claim_ID+"'";
                                                        System.err.println(statusclaim);
                                                        pat = getcon().prepareStatement(statusclaim);
                                                        pat.executeUpdate(statusclaim);  
                                                        pat.close();
                                                        getcon().close();
                                            }
                DefaultTableModel model = (DefaultTableModel)Claim_Receive_List_Table.getModel();
                while(model.getRowCount()>0){
                    model.removeRow(0);
                }
                adding = true;
                showClaim_Receive_List_Table(Claim_Receive_id);
                DefaultTableModel Claim_Table_Model = (DefaultTableModel)Claim_Table.getModel();
                while(Claim_Table_Model.getRowCount()>0){
                    Claim_Table_Model.removeRow(0);
                }
                adding = true;
                show_productfromstock();
                pat.close();
                getcon().close();
                
                }catch(Exception e){
                    throw new SQLException(e);
                }
                }else{
                    JOptionPane.showMessageDialog(null,"คุณกรอกวันที่ไม่ถูกต้อง\nกรุณาทำรายการใหม่ด้วยครับ",null,INFORMATION_MESSAGE); 
                }
            }catch(NumberFormatException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"คุณกรอกจำนวนไม่ถูกต้อง"
                        + "\nกรุณากรอกใหม่ครับ");
            }catch(SQLException e){
                System.out.println(e);
                System.err.println("SQL Query ไม่ถูกต้อง กรุณาลองใหม่อีกครั้งครับ");
            }catch(NullPointerException e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"คุณยังไม่ได้กรอกข้อมูล"
                        + "\nกรุณาทำรายการใหม่ครับ");  
            }
            Claim_Receive_List_id = null;
            }else{
                JOptionPane.showMessageDialog(null,"คุณกรอกจำนวนไม่ถูกต้อง\nกรุณาทำรายการใหม่ครับ\nRange: 0-"+(max-Claim_Receive_Current_Units));  
            }
        }else{
                JOptionPane.showMessageDialog(null,"ยกเลิกรายการ");
                Claim_Receive_List_id = null;
        }
        }else{
        Claim_Receive_List_id = Claim_Receive_List_Table.getModel().getValueAt(Claim_Receive_List_Table.getSelectedRow(),0).toString();
        }
    }//GEN-LAST:event_Claim_Receive_List_TableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel)Claim_Receive_List_Table.getModel();
        while(model.getRowCount()>0){
        model.removeRow(0);
        }
        DefaultTableModel Claim_Table_Model = (DefaultTableModel)Claim_Table.getModel();
        while(Claim_Table_Model.getRowCount()>0){
        Claim_Table_Model.removeRow(0);
        }
        show_productfromstock();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Claim_Received_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Claim_Received_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Claim_Received_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Claim_Received_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Claim_Received_Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Claim_Receive_List_Table;
    private javax.swing.JTable Claim_Table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
