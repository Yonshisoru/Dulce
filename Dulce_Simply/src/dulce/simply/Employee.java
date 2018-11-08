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
public class Employee {
    static private int employee_id = 0;
    static private String firstname = "test";
    static private String lastname = "test";
    static private int position = 0;
    public int getid (){
        return employee_id;
    }
    public void setid(int id){
        this.employee_id = id;
    }
    public int getposition(){
        return position;
    }
    public void setposition(int position){
        this.position = position;
    }
    public String getfname(){
        return firstname;
    }
    public void setfname(String name){
        this.firstname = name;
    }
    public void setlname(String name){
        this.lastname = name;
    }
    public String getlname(){
        return lastname;
    }
}