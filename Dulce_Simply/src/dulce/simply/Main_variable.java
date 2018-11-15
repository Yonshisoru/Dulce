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
public class Main_variable {
static private int edit = 0;
static private int on = 0;
static private String id = null;
static private String name = null;
static private int leave = 0;
static private int currentedit = 0;
static private int currentleave = 0;
static private boolean change = false;
static private boolean leavenaja = false;
    public int editenable(){
        return this.edit;
    }
    public void seteditenable(int status){
        this.edit = status;
    }
    public int geton(){
        return this.on;
    }
    public void seton(int on){
        this.on = on;
    }
    public boolean getchange(){
        return this.change;
    }
    public void setchange(boolean change){
        this.change = change;
    }
    public boolean getleavenaja(){
        return this.leavenaja;
    }
    public void setleavenaja(boolean leave){
        this.leavenaja  = leave;
    }
    public int getleave(){
        return this.leave;
    }
    public void setleave(int leave){
        this.leave = leave;
    }
    public int getcurleave(){
        return this.currentleave;
    }
    public void setcurleave(int leave){
        this.currentleave = leave;
    }
    public int getcuredit(){
        return this.currentedit;
    }
    public void setcuredit(int currentedit){
        this.currentedit = currentedit;
    }
    public String getid(){
        return this.id;
    }
    public void setid(String id){
        this.id = id;
    }
    public String getname(){
        return this.name;
    }
    public void setname(String name){
        this.name = name;
    }
}
