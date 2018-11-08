/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulce.simply;

/**
 *
 * @author Yonshisoru
 */
public interface Employeez {
    int employee_id = 0;
    String firstname = "test";
    String lastname = "test";
    int position = 0;
    public int getid();
    public void setid(int id);
    public int getposition();
    public void setposition(int position);
    public String getfname();
    public void setfname(String name);
    public void setlname(String name);
    public String getlname();
}
