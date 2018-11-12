/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Yonshisoru
 */
public class DulceSimply {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Connection con = null;
    PreparedStatement pat = null;
    ResultSet rs = null;
    Statement st = null;
              String checkid = "SELECT SL_NUMBER FROM SCHEDULE_LIST;";
        try{
        Class.forName("com.mysql.jdbc.Driver");    
        con = DriverManager.getConnection("jdbc:mysql://privatehosting.website:3306/u787124245_dulce","u787124245_gg","death123");
        pat = con.prepareStatement(checkid);
        rs = pat.executeQuery(checkid);
            while(rs.next()){
                System.out.print("EIEI");
                System.out.print(rs.getString("SL_NUMBER"));
               } 
        rs.close();
        pat.close();
        con.close();
        }catch(Exception e){
            
        }
    }
    
}
