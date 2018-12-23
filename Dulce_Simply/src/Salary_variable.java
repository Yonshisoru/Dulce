/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class Salary_variable {
    Employee e= new Employee();
    private String id = null;
    private int type = 0;
    private String start = null;
    private String end = null;
    private String date = null;
    private String displayfname;
    private String displaylname;
    private int salary = 0;
    private double total = 0;
    private double worktime = 0;
    private double sum = 0;
    Salary_variable(String id,String fname,String lname,String date,int salary,String start,String end,double worktime,double sum){
        this.id = id;
        this.displayfname = fname;
        this.displaylname = lname;
        this.date = date;
        this.salary = salary;
        this.start = start;
        this.end = end;
        this.worktime = worktime;
        this.sum = sum;
    }
                /*ep.setid(rs.getString("SP_ID"));
            System.out.println(ep.getid());
            ep.setfname(rs.getString("EMP_FNAME"));
            ep.setlname(rs.getString("EMP_LNAME"));
            ep.setdate(rs.getString("SP_DATE"));
            ep.setsalary(rs.getInt("EMP_SALARY"));
            ep.setstart(rs.getString("SP_S_PEROID"));
            ep.setend(rs.getString("SP_E_PEROID"));
            ep.setworktime(rs.getDouble("W_TOTALTIME"));
            ep.setsum(rs.getDouble("(EMP_SALARY*W_TOTALTIME)"));*/
    public String getid(){
    return this.id;
}
    public void setid(String id){
    this.id = id;
}
    public int getsalary(){
    return this.salary;
}
    public void setsalary(int salary){
    this.salary = salary;
}
    public double getsum(){
    return this.sum;
}
    public void setsum(double sum){
    this.sum = sum;
}
    public double gettotal(){
    return this.total;
}
    public void settotal(double total){
    this.total = total;
}
    public double getworktime(){
    return this.worktime;
}
    public void setworktime(double worktime){
    this.worktime = worktime;
}
    public int gettype(){
    return this.type;
}
    public void settype(int type){
    this.type = type;
}
    public String getstart(){
    return this.start;
}
    public void setstart(String start){
    this.start = start;
}
    public String getend(){
    return this.end;
}
    public void setend(String end){
    this.end = end;
}
    public String getdate(){
    return this.date;
}
    public void setdate(String date){
    this.date = date;
}
        public String getfname(){
    return this.displayfname;
}
    public void setfname(String displayfname){
    this.displayfname = displayfname;
}
    public String getlname(){
    return this.displaylname;
}
    public void setlname(String displaylname){
    this.displaylname = displaylname;
}
    
}
