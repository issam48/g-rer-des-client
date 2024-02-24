/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerer_des_clients;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HUAWEI
 */
public class database {
   public static Connection connectDb(){
      try {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/neww","root","");
    return connect;
}catch(Exception e){e.printStackTrace();}
      return null;
      
    }  
}
