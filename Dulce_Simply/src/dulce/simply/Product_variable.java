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
public class Product_variable {
private String id = null;
private String v_id = null;
private String v_name = null;
private int price = 0;
private String name = null;
private int unit =0;
private int min=0;

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
public int getunit(){
    return this.unit;
}
public int getmin(){
    return this.min;
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
public void setunit(int unit){
    this.unit = unit;
}
public void setmin(int min){
    this.min = min;
}
}
