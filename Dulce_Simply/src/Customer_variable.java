/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Customer_variable {
    static boolean maintenance = true;
    private String orderid = null;
    private String tableid = null;
    private String orderdate = null;
    private double total = 0.0;
    private int units = 0;
    private String ord_paytype = null;
    private String ord_menu_id = null;
    private String ord_menu_status = null;
    private String table = null;
    
    public int getunits(){
        return units;
    }
    public void setunits(int units){
        this.units = units;
    }
      
    public boolean getmaintenance(){
        return maintenance;
    }
    public void setmaintenance(boolean b){
        this.maintenance = b;
    }
    public String getorderid(){
        return orderid;
    }
    public void setorderid(String id){
        this.orderid = id;
    }
    public String gettable(){
        return table;
    }
    public void settable(String table){
        this.table = table;
    }
    public String getorderdate(){
        return orderdate;
    }
    public void setorderdate(String date){
        this.orderdate = date;
    }
    public double gettotal(){
        return total;
    }
    public void settotal(double total){
        this.total = total;
    }
    public String getpaytype(){
        return ord_paytype;
    }
    public void setpaytype(String paytype){
        this.ord_paytype = paytype;
    }
    public String getorder_menu_id(){
        return ord_menu_id;
    }
    public void setorder_menu_id(String ord_menu_id){
        this.ord_menu_id = ord_menu_id;
    }
    public String getorder_menu_status(){
        return ord_menu_status;
    }
    public void setorder_menu_status(String ord_menu_status){
        this.ord_menu_status = ord_menu_status;
    }
}
