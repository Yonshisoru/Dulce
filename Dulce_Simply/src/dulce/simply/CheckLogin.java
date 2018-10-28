/*123
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CheckLogin extends MySQLConnect{
    private static String sql = "select * from employee where EMP_ID=? and EMP_PASSWORD=?";
    static private java.sql.PreparedStatement pat = null;
    static private java.sql.ResultSet rs = null;
    public static void main(String[] args){
        try{
            Connection con = DriverManager.getConnection(getConnect(),getusername(),getpassword());
            pat = con.prepareStatement("select * from employee where EMP_ID=? and EMP_PASSWORD=?");
            pat.setString(1,"9999");
            pat.setString(2, "admin");
            rs = pat.executeQuery(); 
                 while(rs.next()){
                     System.out.print("Login Sucess\n");
                     System.out.print(rs.getString("EMP_FNAME")+" "+rs.getString("EMP_LNAME"));
                 } 
        }catch(SQLException e){
            System.out.print(e);
        }catch(NullPointerException e){
            System.out.print(e);
        }
    }
}
