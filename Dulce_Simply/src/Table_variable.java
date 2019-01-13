
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
  static boolean view = false;
  static JFrame mainframe = null;
   public boolean getview(){
       return this.view;
   }
   public JFrame getframe(){
       return this.mainframe;
   }
   public void setview(Boolean view){
       this.view = view;
   }
   public void setframe(JFrame frame){
       this.mainframe = frame;
   }
}
