/*123456789
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;
import dulce.simply.MySQLConnect;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CheckLogin extends MySQLConnect{
    private static String sql = "select * from employee where EMP_ID=? and EMP_PASSWORD=?";
    static private java.sql.ResultSet rs = null;
    static private PreparedStatement pat = null;
    static private PreparedStatement pat2 = null;
    public static void main(String[] args){
        try{
            Connection con = DriverManager.getConnection(getConnect(),getusername(),getpassword());
           //Mutiple query!!
            /*First query*/pat = con.prepareStatement("select * from employee where EMP_ID=? and EMP_PASSWORD=?");
            /*Second query*/pat2 = con.prepareStatement("select * from employee");
            pat.setString(1,"9999");
            pat.setString(2, "admin");
            rs = pat.executeQuery(); 
                 while(rs.next()){
                     System.out.print("Login Sucess\n");
                     System.out.println(rs.getString("EMP_FNAME")+" "+rs.getString("EMP_LNAME"));
                 }
            rs = pat2.executeQuery();
            while(rs.next()){
                for(int i =1;i<rs.getMetaData().getColumnCount()+1;i++){
                System.out.println(rs.getMetaData().getColumnName(i)+" = "+rs.getObject(i));
                }
            }
            
        }catch(SQLException e){
            System.out.print(e);
        }catch(NullPointerException e){
            System.out.print(e);
        }finally{
                try{
                    pat.close();
                    System.out.print("Pat is closed");
                }catch(SQLException e){
                    System.out.print(e);
                }
            }
                try{
                    pat2.close();
                  System.out.print("Pat2 is closed");
                }catch(SQLException e){
                    System.out.print(e);
        }
    }
}
