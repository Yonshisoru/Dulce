

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

class table{
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
public class Table_panel extends JFrame
{
    static ArrayList<table> Table_Array = new ArrayList<>();
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
    public void settable(String s){
        String sql = "UPDATE TABLEZ SET T_STATUS = 'Y' WHERE T_ID = '"+s+"'";
        System.out.println(sql);
        try{
            pat = getcon().prepareCall(sql);
            pat.executeUpdate(sql);
            pat.close();
            getcon().close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public Connection getcon(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url(),username(),password());
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
            pat = getcon().prepareCall(sql);
            rs = pat.executeQuery();
            while(rs.next()){
                count++;
                table t = new table();
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
    public Table_panel()
    {
        super("");
    }
    public JPanel setbuttonpanel(){
        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.setLayout(new GridLayout(gridSize, gridSize, 25, 25));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        for(table e:Table_Array){
            JButton button = new JButton(e.gettablenumber());
            button.setPreferredSize(new Dimension(50,50));
            if(e.getstatus().equals("Y")){
                button.setBackground(Color.RED);
            }else{
                button.setBackground(Color.GREEN);
            }
            button.setActionCommand(e.gettablenumber());
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae) {
                    JButton but = (JButton) ae.getSource();
                    but.setBackground(Color.RED);
                    for (table t : Table_Array) {
                        if (but.getText().equals(t.gettablenumber())) {
                            settable(but.getText());
                        }
                    }
                    JOptionPane.showMessageDialog(null,"EIEI");
                }
            });
            buttonPanel.add(button);
            e.setbutton(button);
        }
        return buttonPanel;
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

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        /*JPanel contentPane2 = new JPanel();
        contentPane.setLayout(null);
        positionLabel = new JLabel(INITIAL_TEXT, JLabel.CENTER);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        contentPane2.add(positionLabel);
        contentPane.add(contentPane2);*/


        JPanel bottomPanel = new JPanel();
        //leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel buttonLeftPanel = new JPanel();
        resetButton = new JButton("รีเฟรช");
        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                close();
                Table_Array.clear();
                new Table_panel().createAndDisplayGUI();
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
        buttonLeftPanel.add(resetButton);
        bottomPanel.add(buttonLeftPanel);
        bottomPanel.setBounds(500,300,20,20);

        contentPane.add(setbuttonpanel());
        contentPane.add(bottomPanel);

        setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            gridSize = Integer.parseInt(args[0]);
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Table_panel().createAndDisplayGUI();
            }
        });
    }
}