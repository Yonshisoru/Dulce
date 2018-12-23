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
private String id = null;
private String name = null;
private int price = 0;
private String cataname = null;
private String cataid = null;
public String getid(){
    return this.id;
}
public String getname(){
    return this.name;
}
public int getprice(){
    return this.price;
}
public String getcataname(){
    return this.cataname;
}
public String getcataid(){
    return this.cataid;
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
public void setcataname(String cataname){
    this.cataname = cataname;
}
public void setcataid(String cataid){
    this.cataid = cataid;
}
}
