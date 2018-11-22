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
public class Product_Order_Variable {
Employee e = new Employee();
Vendor_variable v = new Vendor_variable();
private String id;
private int unit;
private String date;
private String receive_date;
private String pay_date;
private int price;
private int productunit;
private String product;
private int productprice; 
public String getid(){
    return id;
}
public int getunit(){
    return unit;
}
public int getprice(){
    return price;
}
public String getrec_date(){
    return receive_date;
}
public String getpay_date(){
    return pay_date;
}
public String getdate(){
    return date;
}
public String getproduct(){
    return product;
}
public int getproductprice(){
    return productprice;
}
public int getproductunit(){
    return productunit;
}
public void setid(String id){
    this.id = id;
}
public void setunit(int unit){
    this.unit = unit;
}
public void setprice(int price){
    this.price = price;
}
public void setdate(String date){
    this.date = date;
}
public void setrec_date(String rec_date){
    this.receive_date = rec_date;
}
public void setpay_date(String pay_date){
    this.pay_date = pay_date;
}
public void setproduct(String product){
    this.product = product;
}
public void setproductprice(int productprice){
    this.productprice = productprice;
}
public void setproductunit(int productunit){
    this.productunit = productunit;
}
}
