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
public class Vendor_variable {
private String id = null;
private String name = null;
private String address = null;
private String phone = null;
private String email = null;
private String acc = null;
private String contact = null;
public String getid(){
    return this.id;
}
public String getname(){
    return this.name;
}
public String getaddress(){
    return this.address;
}
public String getphone(){
    return this.phone;
}
public String getemail(){
    return this.email;
}
public String getacc(){
    return this.acc;
}
public String getcontact(){
    return this.contact;
}
public void setid(String id){
    this.id = id;
}
public void setname(String name){
    this.name = name;
}
public void setaddress(String address){
    this.address =address;
}
public void setphone(String phone){
    this.phone = phone;
}
public void setemail(String email){
    this.email = email;
}
public void setacc(String acc){
    this.acc = acc;
}
public void setcontact(String contact){
    this.contact = contact;
}
}
