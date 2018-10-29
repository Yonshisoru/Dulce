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
    static private String phone = "test";
    static private String address  = "test";
    static private String email = "test";
    static private int salary =  0;
    static private String date = "test";
    static private int age = 0;
    static private String gender  = "test";
    static private int duration = 0;
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
    public String getphone (){
        return phone;
    }
    public void setphone(String phone){
        this.phone = phone;
    }
    public String getaddress(){
        return address;
    }
    public void setaddress(String address){
        this.address = address;
    }
    public String getemail (){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }
    public int getsalary (){
        return salary;
    }
    public void getsalary(int salary){
        this.salary = salary;
    }
    public String getdate(){
        return date;
    }
    public void setdate(String date){
        this.date = date;
    }
    public int getage(){
        return age;
    }
    public void setage(int age){
        this.age = age;
    }
    public String getgender(){
        return gender;
    }
    public void setgender(String gender){
        this.gender = gender;
    }
    public int getduration(){
        return duration;
    }
    public void setduration(int duration){
        this.duration = duration;
    }
    
}
