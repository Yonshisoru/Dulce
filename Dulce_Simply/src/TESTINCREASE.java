/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.Date;

/**
 *
 * @author Yonshisoru
 */
public class TESTINCREASE {
    public static void main(String[] args){
        int max = Integer.MIN_VALUE;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        PreparedStatement pat = con.prepareStatement("Select * from LEAVE_EMP");
        ResultSet result = pat.executeQuery();
        if(result.next()){
        while(result.next()){
            System.out.print(result.getInt("L_ID"));
            if(result.getInt("L_ID")>max){
                max = result.getInt("L_ID");
            }
        }
        }else{
            max = 0;
        }
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = conn.prepareStatement("insert into LEAVE_EMP values(?,?,?,?,?,?)");
        pat.setInt(1,max+1);
        pat.setDate(2,java.sql.Date.valueOf("2018-05-01"));
        pat.setDate(3,java.sql.Date.valueOf("2018-06-01"));
        pat.setInt(4,0);
        pat.setString(5,"ง่วง");
        pat.setInt(6,9999);
        pat.executeUpdate();
    }catch(Exception e){
        e.printStackTrace();
    }
}
}
