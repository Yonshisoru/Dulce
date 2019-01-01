/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class Product_Order_Variable {
Employee e = new Employee();
Vendor_variable v = new Vendor_variable();
static String list_showid;
static String list_productid;
static String list_productname;
static private String view = "O011";
static private String receiveid = null;
static private String orderid = null;
static int list_unit;
static double list_price;
static int list_current;
static boolean detect = false;
private String Product_Order_Receive_id;
private String productname;
private String productid;
private String paystatus;
private String Product_Order_id;
private String receivestatus;
private String date;
private String receive_date;
private String pay_date;
private String Product_Order_Receive_List_Number;
private int unit;
private int productunit;
private int productprice; 
private int current;
private double price;
public int getlist_unit(){
    return this.list_unit;
}
public String getProduct_Order_Receive_List_Number(){
    return this.Product_Order_Receive_List_Number;
}
public String getProduct_Order_ID(){
    return this.Product_Order_id;
}
public String getreceiveid(){
    return this.receiveid;
}
public String getorderid(){
    return this.orderid;
}
public void setorderid(String id){
    this.orderid = id;
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
public double getlist_price(){
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
public String getProduct_Order_Receive_id(){
    return Product_Order_Receive_id;
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
public double getprice(){
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
    return productname;
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
public void setProduct_Order_Receive_id(String id){
    this.Product_Order_Receive_id = id;
}
public void setProduct_Order_ID(String id){
    this.Product_Order_id = id;
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
public void setprice(double price){
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
public void setproductname(String product){
    this.productname = product;
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
public void setlist_price(double price){
    this.list_price = price;
}
public void setlist_current(int current){
    this.list_current = current;
}
public void setlist_productid(String list_productid){
    this.list_productid = list_productid;
}
public void setProduct_Order_Receive_List_Number(String number){
    this.Product_Order_Receive_List_Number= number;
}
}
