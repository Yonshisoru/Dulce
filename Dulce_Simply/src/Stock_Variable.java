/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Thanachot
 */
public class Stock_Variable {
    Claim_Variable c = new Claim_Variable();
    Product_variable p = new Product_variable();
    private int stocknumber;
    private String productid;
    private String productname;
    private String stockexpdate;
    private String stockstartdate;
    private double stockunits;
    private String orderid;
    private String detail;
    private double wasteunits;
    private double stockprice;
    public double getstockprice(){
       return stockprice;
    }
    public void setstockprice(double stockprice){
       this.stockprice = stockprice;
    }
    public double getwasteunits(){
       return wasteunits;
    }
    public void setwasteunits(double wasteunits){
       this.wasteunits = wasteunits;
    }
    public String getdetail(){
       return detail;
    }
    public void setdetail(String detail){
       this.detail = detail;
    }
    public int getstocknumber(){
        return this.stocknumber;
    }
    public String getproductid(){
        return this.productid;
    }
    public String getproductname(){
        return this.productname;
    }
    public String getstockexpdate(){
        return this.stockexpdate;
    }
    public String getstockstartdate(){
        return this.stockstartdate;
    }
    public double getstockunits(){
        return this.stockunits;
    }
    public String getorderid(){
        return this.orderid;
    }
    public void setstocknumber(int id){
        this.stocknumber = id;
    }
    public void setproductid(String id){
        this.productid = id;
    }
    public void setproductname(String date){
        this.productname = date;
    }
    public void setstockexpdate(String date){
        this.stockexpdate = date;
    }
    public void setstockstartdate(String date){
        this.stockstartdate = date;
    }
    public void setstockunits(double units){
        this.stockunits= units;
    }
    public void setorderid(String id){
        this.orderid = id;
    }
}
