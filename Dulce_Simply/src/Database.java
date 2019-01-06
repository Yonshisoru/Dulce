/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Yonshisoru
 */
public class Database {
//private String url = "jdbc:mysql://privatehosting.website:3306/u787124245_dulce";
private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
private String username = "root";
private String password ="";
public String url(){
    return url;
}
public String username(){
    return username;
}
public String password(){
    return password;
}
}
