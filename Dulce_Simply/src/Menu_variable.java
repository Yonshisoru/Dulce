/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class Menu_variable {
Customer_variable c = new Customer_variable();
Promotion_variable p = new Promotion_variable();
Employee e = new Employee();
Table_variable t = new Table_variable();
private String id = null;
private String name = null;
private int price = 0;
private int units = 0;
private int menu_number = 0;
private String cataname = null;
private String cataid = null;
static String Global_Menu_ID = null;
private String delete_status = null;
public String getid(){
    return this.id;
}
public String getGlobal_Menu_Id(){
    return this.Global_Menu_ID ;
}
public String getname(){
    return this.name;
}
public int getprice(){
    return this.price;
}
public int getunits(){
    return this.units;
}
public int getmenu_number(){
    return this.menu_number;
}
public String getcataname(){
    return this.cataname;
}
public String getcataid(){
    return this.cataid;
}
public String getdelete_status(){
    return this.delete_status;
}
public void setid(String id){
    this.id = id;
}
public void setname(String name){
    this.name = name;
}
public void setprice(int price){
    this.price = price;
}
public void setunits(int units){
    this.units = units;
}
public void setmenu_number(int number){
    this.menu_number= number;
}
public void setcataname(String cataname){
    this.cataname = cataname;
}
public void setcataid(String cataid){
    this.cataid = cataid;
}
public void setGlobal_Menu_ID(String id){
    this.Global_Menu_ID  = id;
}
public void setdelete_status(String status){
    this.delete_status  = status;
}
}
