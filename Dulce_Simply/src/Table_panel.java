

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

class table_v{
    private String tablenumber;
    private String status;
    private JButton button;
    public String gettablenumber(){
        return tablenumber;
    }
    public String getstatus(){
        return status;
    }
    public JButton getbtn(){
        return button;
    }
    public void settablenumber(String number){
        this.tablenumber = number;
    }
    public void setstatus(String status){
        this.status = status;
    }
    public void setbutton(JButton button){
        this.button = button;
    }
}

public class Table_panel extends javax.swing.JFrame
{
    Database d = new Database();
    Table_variable tv = new Table_variable();
    static ArrayList<table_v> Table_Array = new ArrayList<>();
    static ArrayList<JButton> btn = new ArrayList<>();
    private static final String INITIAL_TEXT = "Nothing Pressed";
    private static final String ADDED_TEXT = " was Pressed";
    private JLabel positionLabel;
    private JButton resetButton;
    private static int gridSize = 1;
    int count = 0;
    boolean available = false;
    //*------------------------------------------------
    Connection con = null;
    Statement st = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    //-------------------------------------------------
    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
    private String username = "root";
    private String password ="";
    public Table_panel(){
        Table_Array.clear();
        btn.clear();
        createAndDisplayGUI();

    }
    public String url(){
        return url;
    }
    public String username(){
        return username;
    }
    public String password(){
        return password;
    }
    public void close(){
        this.setVisible(false);
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
    public void getTable(){
        count = 0;
        gridSize = 1;
        String sql = "SELECT T_ID,T_STATUS FROM TABLEZ WHERE T_DEL = 'N'";
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                count++;
                table_v t = new table_v();
                t.settablenumber(rs.getString("T_ID"));
                t.setstatus(rs.getString("T_STATUS"));
                Table_Array.add(t);
                if(count%5==0){
                    gridSize++;
                }
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){

        }
    }
    public JPanel setbuttonpanel(){
        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.setLayout(new GridLayout(gridSize, gridSize, 25, 25));
        for(table_v e:Table_Array){
            JButton button = new JButton(e.gettablenumber());
            button.setPreferredSize(new Dimension(25,25));
            if(e.getstatus().equals("Y")){
                button.setBackground(Color.RED);
            }else{
                button.setBackground(Color.GREEN);
            }
            button.setActionCommand(e.gettablenumber());
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae) {
                    if(tv.getview()==false||tv.getorder()==true){
                    JButton but = (JButton) ae.getSource();
                    for (table_v t : Table_Array) {
                        if (but.getText().equals(t.gettablenumber())) {
                            if(t.getstatus().equals("N")){
                            if(tv.getorder()==true){
                             JOptionPane.showMessageDialog(null,"โต๊ะนี้ยังไม่มีออเดอร์ที่ค้างอยู่ กรุณาทำรายการใหม่ด้วยครับ");   
                            }else{
                            but.setSelected(false);
                            if(tv.getview()==false){
                            //System.out.println(but.getText());
                            Table_variable g = new Table_variable();
                            g.setid(t.gettablenumber());
                            Customer_variable cv = new Customer_variable();
                            cv.setmaintenance(false);
                            Customer_Panel cp = new Customer_Panel();
                            cp.setVisible(true);
                            }
                            close();
                            break;
                            }
                            }else{
                            if(tv.getorder()==true){
                            Table_variable g = new Table_variable();
                            g.setid(t.gettablenumber());
                            Customer_View cp = new Customer_View();
                            cp.setVisible(true);
                            close();
                            break;
                            }else{
                               JOptionPane.showMessageDialog(null,"โต๊ะนี้ไม่พร้อมใช้งาน กรุณาทำรายการใหม่ด้วยครับ");
                                }
                            }
                        }else{
                        }
                    }
                }
                    }
            });
            buttonPanel.add(button);
            e.setbutton(button);
        }
        return buttonPanel;
    }
        private void formWindowClosed(java.awt.event.WindowEvent evt) {                                  
        Table_variable t = new Table_variable();
        t.setorder(false);
        t.setview(false);
    } 
    /*
 JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.setLayout(new GridLayout(gridSize, gridSize, 10, 10));
            for(table e:Table_Array){
                JButton button = new JButton(e.gettablenumber());
                if(e.getstatus().equals("T")){
                    button.setBackground(Color.RED);
                }else{
                    button.setBackground(Color.GREEN);
                }
                button.setActionCommand(e.gettablenumber());
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        JButton but = (JButton) ae.getSource();
                        positionLabel.setText(
                        but.getActionCommand() + ADDED_TEXT);
                        but.setBackground(Color.ORANGE);
                    }
                });
                buttonPanel.add(button);
                e.setbutton(button);
            }
     */
    private void createAndDisplayGUI()
    {
        getTable();
        this.setPreferredSize(new Dimension(600,600));
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10,30));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        /*JPanel contentPane2 = new JPanel();
        contentPane.setLayout(null);
        positionLabel = new JLabel(INITIAL_TEXT, JLabel.CENTER);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        contentPane2.add(positionLabel);
        contentPane.add(contentPane2);*/


        JPanel bottomPanel = new JPanel(new GridLayout(1,0,30,0));
        //leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel buttonLeftPanel = new JPanel();
        JLabel text1 = new JLabel("<html><u><b><font color=red>สถานะสีแดงคือโต๊ะที่ไม่พร้อมใช้งาน</font></b></u><br><u><b><font color=green>สถานะสีเขียวคือโต๊ะที่พร้อมใช้งาน</font></b></u></html>",SwingConstants.LEFT);
        //text1.setForeground(Color.RED);
        bottomPanel.add(text1);
        resetButton = new JButton("รีเฟรช");
        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                close();
                Table_Array.clear();
                btn.clear();
                new Table_panel();
                /*for(table t:Table_Array){
                    if(t.getstatus().equals("T")){
                        JButton eiei = t.getbtn();
                        eiei.setBackground(Color.RED);
                        t.setbutton(eiei);
                    }else{
                        JButton eiei = t.getbtn();
                        eiei.setBackground(Color.GREEN);
                        t.setbutton(eiei);
                    }
                    System.out.println(t.gettablenumber()+" "+t.getstatus()+" "+t.getbtn());
                }*/
                /*for(JButton eiei:btn){
                    System.out.println(eiei);
                    System.out.println(eiei.getBackground());
                    if(eiei.getText().equals("T")){
                        eiei.setBackground(Color.RED);
                    }else{
                        eiei.setBackground(Color.YELLOW);
                    }
                    System.out.println(eiei.getText());
                    System.out.println("---");
                }*/
            }
        });
        resetButton.setPreferredSize(new Dimension(100,40));
        buttonLeftPanel.add(resetButton);
        JButton closeButton = new JButton("ปิด");
        closeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                close();
            }
        });
        closeButton.setPreferredSize(new Dimension(100,40));
        buttonLeftPanel.add(closeButton);
        bottomPanel.add(buttonLeftPanel);
        bottomPanel.setBounds(500,300,20,20);
        JPanel toppanel = new JPanel();
        JLabel title = new JLabel("หน้าต่างเลือกโต๊ะ",SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Tekton Pro", 0, 36));
        toppanel.add(title);
        contentPane.add(title,BorderLayout.PAGE_START);
        contentPane.add(setbuttonpanel(),BorderLayout.CENTER);
        contentPane.add(bottomPanel,BorderLayout.PAGE_END);

        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Table_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Table_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Table_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Table_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        if (args.length > 0)
        {
            gridSize = Integer.parseInt(args[0]);
        }
        SwingUtilities
        /*java.awt.EventQueue*/.invokeLater(new Runnable()
        {
            public void run()
            {
                Table_variable tv = new Table_variable();
                Table_panel p = new Table_panel();
                tv.setframe(p);
                
            }
        });
    }
}