/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Waste_variable {
    Stock_Variable s = new Stock_Variable();
    Menu_variable m = new Menu_variable();
   static private String globalid;
   static boolean getcreate = false;
   int count = 0;
   private String detail = null;
   private String id;
   private int total;
   private String date;
   private double totalprice;
   private String empid;
   private String emp_fname;
   private String emp_lname;
   public String getglobalid(){
       return globalid;
   }
   public void setglobalid(String globalid){
       this.globalid = globalid;
   }
   public String getid(){
       return id;
   }
   public void setid(String id){
       this.id = id;
   }
   public int gettotal(){
       return total;
   }
   public void settotal(int total){
       this.total = total;
   }
   public double gettotalprice(){
       return totalprice;
   }
   public void settotalprice(double totalprice){
       this.totalprice = totalprice;
   }
   public String getempid(){
       return empid;
   }
   public void setempid(String empid){
       this.empid = empid;
   }
   public String getemp_fname(){
       return emp_fname;
   }
   public void setemp_fname(String emp_fname){
       this.emp_fname = emp_fname;
   }
   public String getemp_lname(){
       return emp_lname;
   }
   public void setemp_lname(String emp_lname){
       this.emp_lname = emp_lname;
   }
   public String getdate(){
       return date;
   }
   public void setdate(String date){
       this.date = date;
   }
   public boolean getcreate(){
       return getcreate;
   }
   public void setgetcraete(boolean b){
       this.getcreate = b;
   }
   public int getcount(){
       return count;
   }
   public void setgetcount(int count){
       this.count = count;
   }
}
