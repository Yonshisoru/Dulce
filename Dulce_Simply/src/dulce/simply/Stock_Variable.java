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
public class Stock_Variable {
    private String stockid;
    private String productid;
    private String expdate;
    private String startdate;
    private String units;
    private String productname;
    public String getstockid(){
        return this.stockid;
    }
    public String getproductname(){
        return this.productname;
    }
    public String getproductid(){
        return this.productid;
    }
    public String getexpdate(){
        return this.expdate;
    }
    public String getstartdate(){
        return this.startdate;
    }
    public String getunits(){
        return this.units;
    }
    public void setstockid(String id){
        this.stockid = id;
    }
    public void setproductname(String productname){
        this.productname = productname;
    }
    public void setproductid(String id){
        this.productid = id;
    }
    public void setexpdate(String expdate){
        this.expdate = expdate;
    }
    public void setstartdate(String startdate){
        this.startdate = startdate;
    }
    public void setunits(String units){
        this.units = units;
    }
}
