
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
  private String id = null;
  static private boolean confirmorder = false;
  static private boolean editorder = false;
  Table_variable(){
      //this.tableid = "55";
  }
   public boolean getview(){
       return this.view;
   }
   public boolean getorder(){
       return this.confirmorder;
   }
   public boolean getedit(){
       return this.editorder;
   }
   public void setedit(boolean edit){
       this.editorder = edit;
   }
   public void setorder(Boolean order){
       this.confirmorder = order;
   }
   public JFrame getframe(){
       return this.mainframe;
   }
   public String getid(){
       return tableid;
   }
   public String gettableid(){
       return id;
   }
   public void setview(Boolean view){
       this.view = view;
   }
   public void setframe(JFrame frame){
       this.mainframe = frame;
   }
   public void setid(String id){
       //System.err.println(id);
       this.tableid = id;
   }
   public void settableid(String id){
       this.id = id;
   }
}
