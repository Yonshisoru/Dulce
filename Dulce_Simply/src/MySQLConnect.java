/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Yonshisoru
 */
public class MySQLConnect {
     static private String url = "jdbc:mysql://";
     static private String hosting = "privatehosting.website";
     static private String port = "3306";
     static private String databasename = "u787124245_dulce";
     static private String username = "u787124245_gg";
     static private String password = "death123";
     private static final  Connection con = null;
    public static String getusername(){
        return username;
    }
    public static String getpassword(){
        return password;
    }
    public static String getConnect(){
        String returnVal = url+hosting+":"+port+"/"+databasename;
        return returnVal;
    }
}
