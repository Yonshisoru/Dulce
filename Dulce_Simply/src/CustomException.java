
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class CustomException{
    static Connection con = null;
    static Statement st = null;
    static PreparedStatement pat = null;
    static ResultSet rs = null;
    static public Connection getcon(){
    Database d = new Database();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(d.url(),d.username(),d.password());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Didn';t connect");
            throw new RuntimeException(e);
        }
    }
    static public void getTable(){
        String sql = "SELECT T_ID,T_STATUS FROM TABLEZ WHERE T_DEL = 'N'";
        System.out.println(sql);
        try{
            pat = getcon().prepareStatement(sql);
            rs = pat.executeQuery(sql);
            while(rs.next()){
                System.out.println("5");
            }
            rs.close();
            pat.close();
            getcon().close();
        }catch(Exception e){

        }
    }
    public static void main(String[] args){
        getTable();
    }
}
