/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Promotion_variable {
    private String promotionid;
    private String promotionname;
    private String startdate;
    private String enddate;
    private int discount;
    private double total;
    private int menunumber;
    public String getid(){
        return promotionid;
    }
    public String getname(){
        return promotionname;
    }
    public int getdiscount(){
        return discount;
    }
    public double gettotal(){
        return total;
    }
    public int getmenunumber(){
        return menunumber;
    }
    public String getstartdate(){
        return startdate;
    }
    public String getenddate(){
        return enddate;
    }
    public void setid(String id){
        this.promotionid = id;
    }
    public void setname(String name){
        this.promotionname = name;
    }
    public void setdiscount(int discount){
        this.discount = discount;
    }
    public void settotal(double total){
        this.total = total;
    }
    public void setmenunumber(int number){
        this.menunumber = number;
    }
    public void setstartdate(String date){
        this.startdate = date;
    }
        public void setenddate(String date){
        this.enddate = date;
    }
}
