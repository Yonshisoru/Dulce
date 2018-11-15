package dulce.simply;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yonshisoru
 */
public class Schedule {
    private String id;
    private String date;
    private int periodid;
    private String period;
    private int limit;
    private int current;
    private int leave;
    private String status;
    private String cause;
    private int scheduleoflist;
    Schedule(String id,String date,String period,int limit,int current,int leave){
        this.id = id;
        this.date = date;
        this.period = period;
        this.limit = limit;
        this.current = current;
        this.leave = leave;
    }
    Schedule(){
        
    }
    public String getSid(){
        return id;
    }
    public void setSid(String id){
        this.id = id;
    }
    public String getdate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getcause(){
        return cause;
    }
    public void setcause(String cause){
        this.cause = cause;
    }
    public int getperiodid(){
        return periodid;
    }
    public void setperiodid(int id){
        this.periodid = id;
    }
    public String getperiod(){
        return period;
    }
    public void setperiod(String date){
        this.period = date;
    }
     public int getlimit(){
        return limit;
    }
    public void setlimit(int limit){
        this.limit = limit;
    }
    public int getcurrent(){
        return current;
    }
    public void setcurrent(int current){
        this.current = current;
    } 
     public int getleave(){
        return leave;
    }
    public void setleave(int leave){
        this.leave = leave;
    }
     public int getlistid(){
        return scheduleoflist;
    }
    public void setlistid(int id){
        this.scheduleoflist = id;
    }
    public String getstatus(){
        return status;
    }
    public void setstatus(String status){
        this.status = status;
    } 
}
