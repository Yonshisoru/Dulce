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
    static private String employee_id;
    static private String firstname;
    static private String lastname ;
    static private String position;
    static private int position_id;
    static private int age;
    static private String gender;
    static private String phone;
    static private String email;
    static private String date;
    public Employee(String employee_id,String fname,String lname,String position,int age,String gender,String phone,String email,String date){
    this.employee_id = employee_id;
    this.firstname = fname;
    this.lastname = lname;
    this.position = position;
    this.age = age;
    this.gender = gender;
    this.phone = phone;
    this.email = email;
    this.date = date;
    }
    public Employee(){
    }
    public String getid (){
        return employee_id;
    }
    public void setid(String id){
        this.employee_id = id;
    }
    public String getposition(){
        return position;
    }
    public void setposition(String position){
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
    public int getage (){
        return age;
    }
    public void setage(int age){
        this.age = age;
    }
    public String getgender (){
        return gender;
    }
    public void setgender(String gender){
        this.gender = gender;
    }
    public String getphone (){
        return phone;
    }
    public void setphone(String phone){
        this.phone = phone;
    }
        public String getemail (){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }
     public String getdate (){
        return date;
    }
    public void setdate(String date){
        this.date = date;
    }
     public int getposition_id (){
        return position_id;
    }
    public void setposition_id(int position_id){
        this.position_id = position_id;
    }   
}