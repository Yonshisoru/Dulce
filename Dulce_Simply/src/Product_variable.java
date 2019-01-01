/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class Product_variable {
private String id = null;
private String v_id = null;
private String v_name = null;
private int price = 0;
private String name = null;
private double unit =0;
private int min=0;
private double total_price = 0;
private String unit_type = null;
private String Product_type = null;
public String getid(){
    return this.id;
}
public String getvid(){
    return this.v_id;
}
public String getvname(){
    return this.v_name;
}
public int getprice(){
    return this.price;
}
public String getname(){
    return this.name;
}
public double getunit(){
    return this.unit;
}
public String getunit_type(){
    return this.unit_type;
}
public int getmin(){
    return this.min;
}
public double gettotal_price(){
    return this.total_price;
}
public String getProduct_type(){
    return this.Product_type;
}
public void setid(String id){
    this.id = id;
}
public void setname(String name){
    this.name = name;
}
public void setvname(String vname){
    this.v_name =vname;
}
public void setvid(String vid){
    this.v_id =vid;
}
public void setprice(int price){
    this.price= price;
}
public void setunit(double unit){
    this.unit = unit;
}
public void setmin(int min){
    this.min = min;
}
public void setunits_type(String type){
    this.unit_type = type;
}
public void settotal_price(double price){
    this.total_price = price;
}
public void setProduct_type(String type){
    this.Product_type = type;
}
}
