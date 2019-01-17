
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Table_variable {
  static private boolean view = false;
  static private JFrame mainframe = null;
  static private String tableid = null;
  Table_variable(){
      this.tableid = "55";
  }
   public boolean getview(){
       return this.view;
   }
   public JFrame getframe(){
       return this.mainframe;
   }
   public String getid(){
       return tableid;
   }
   public void setview(Boolean view){
       this.view = view;
   }
   public void setframe(JFrame frame){
       this.mainframe = frame;
   }
   public void setid(String id){
       System.err.println(id);
       this.tableid = id;
   }
}
