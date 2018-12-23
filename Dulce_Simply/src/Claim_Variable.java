/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class Claim_Variable {
private String claimid;
private String date;
private String receivedate;
private String cause;
private int productcount;
private String employeeid;
private String employeefname;
private String employeelname;
private String status;
private String orderid;
public String getclaimid(){
    return this.claimid;
}
public String getdate(){
    return this.date;
}
public String getcause(){
    return this.cause;
}
public String getreceivedate(){
    return this.receivedate;
}
public int getproductcount(){
    return this.productcount;
}
public String getempid(){
    return this.employeeid;
}
public String getempfname(){
    return this.employeefname;
}
public String getemplname(){
    return this.employeelname;
}
public String getorderid(){
    return this.orderid;
}
public String getstatus(){
    return this.status;
}
public void setclaimid(String id){
    this.claimid = id;
}
public void setdate(String date){
    this.date = date;
}
public void setcause(String cause){
    this.cause = cause;
}
public void setreceivedate(String date){
    this.receivedate = date;
}
public void setproductcount(int product){
    this.productcount = product;
}
public void setempid(String id){
    this.employeeid = id;
}
public void setempfname(String name){
    this.employeefname = name;
}
public void setemplname(String name){
    this.employeelname = name;
}
public void setstatus(String status){
    this.status= status;
}
public void setorderid(String orderid){
    this.orderid= orderid;
}
}
