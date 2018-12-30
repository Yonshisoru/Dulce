/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Claim_Received_Variable {
Product_variable p =new Product_variable();
    public String Claim_receive_list_id;
    public String Claim_receive_id;
    public String Product_id;
    public Double Claim_receive_total_unit;
    public Double Claim_receive_current_unit;
    public String Claim_receive_status;
    public String Product_name;
    public String Expired_date;
    public String Claim_id;
//-------------------------------------------------    
    public String getClaim_receive_list_id(){
        return this.Claim_receive_list_id;
    }
    public String getClaim_receive_id(){
        return this.Claim_receive_id;
    }
    public String getClaim_id(){
        return this.Claim_id;
    }
    public String getProduct_id(){
        return this.Product_id;
    }
    public Double getClaim_receive_total_unit(){
        return this.Claim_receive_total_unit;
    }
    public Double getClaim_receive_current_unit(){
        return this.Claim_receive_current_unit;
    }
    public String getClaim_receive_status(){
        return this.Claim_receive_status;
    }
    public String getProduct_name(){
        return this.Product_name;
    }
    public String getExpired_date(){
        return this.Expired_date;
    }
//--------------------------------------------------------
    public void setClaim_receive_list_id(String id){
       this.Claim_receive_list_id = id;
    }
    public void setClaim_receive_id(String id){
        this.Claim_receive_id = id;
    }
    public void setProduct_id(String id){
        this.Product_id = id;
    }
    public void setClaim_id(String id){
        this.Claim_id = id;
    }
    public void setClaim_receive_total_unit(Double unit){
        this.Claim_receive_total_unit = unit;
    }
    public void setClaim_receive_current_unit(Double unit){
        this.Claim_receive_current_unit = unit;
    }
    public void setClaim_receive_status(String status){
        this.Claim_receive_status = status;
    }
    public void setProduct_name(String name){
        this.Product_name = name;
    }
    public void setExpired_date(String date){
        this.Expired_date = Expired_date;
    }
}
