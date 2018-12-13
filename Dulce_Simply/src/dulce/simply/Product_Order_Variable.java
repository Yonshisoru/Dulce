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
static private String view = "O001";
static private String receiveid = null;
private String id;
private int unit;
private String date;
private String receive_date;
private String pay_date;
private int price;
private int productunit;
private String product;
private String productid;
private int productprice; 
private String paystatus;
private String receivestatus;
private int current;
static String list_showid;
static String list_productid;
static String list_productname;
static int list_unit;
static int list_price;
static int list_current;
static boolean detect = false;
public int getlist_unit(){
    return this.list_unit;
}
public String getreceiveid(){
    return this.receiveid;
}
public void setreceiveid(String id){
    this.receiveid = id;
}
public boolean getdetect(){
    return this.detect;
}
public void setdetect(boolean b){
    this.detect = b;
}
public int getlist_price(){
    return this.list_price;
}
public int getlist_current(){
    return this.list_current;
}
public String getlist_showid(){
    return this.list_showid;
}
public String getlist_productname(){
    return this.list_productname;
}
public String getlist_productid(){
    return this.list_productid;
}
public String getid(){
    return id;
}
public String getview(){
    return view;
}
public int getunit(){
    return unit;
}
public int getcurrent(){
    return current;
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
public String getproductid(){
    return productid;
}
public String getpaystatus(){
    return paystatus;
}
public String getreceivestatus(){
    return receivestatus;
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
public void setcurrent(int current){
    this.current = current;
}
public void setpaystatus(String paystatus){
    this.paystatus = paystatus;
}
public void setreceivestatus(String receivestatus){
    this.receivestatus = receivestatus;
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
public void setproductid(String productid){
    this.productid = productid;
}
public void setproductprice(int productprice){
    this.productprice = productprice;
}
public void setproductunit(int productunit){
    this.productunit = productunit;
}
public void setview(String view){
    this.view = view;
}
public void setlist_showid(String id){
    this.list_showid = id;
}
public void setlist_product(String product){
    this.list_productname = product;
}
public void setlist_unit(int units){
    this.list_unit = units;
}
public void setlist_price(int price){
    this.list_price = price;
}
public void setlist_current(int current){
    this.list_current = current;
}
public void setlist_productid(String list_productid){
    this.list_productid = list_productid;
}
}
