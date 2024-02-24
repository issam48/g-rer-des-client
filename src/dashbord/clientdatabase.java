/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashbord;

/**
 *
 * @author HUAWEI
 */
public class clientdatabase {
    private int ClientID;
    private  String ClientName;
    private String Address;
    private String RC;
    private int NIF;
    
    public clientdatabase(int ClientID,String ClientName,String Address,int NIF,String RC){
        this.ClientID=ClientID;
        this.ClientName=ClientName;
        this.Address=Address;
         this.NIF=NIF;
        this.RC=RC;
    }
    public int getNclient(){
        return ClientID;
    }
    public String getName(){
        return ClientName;
    }
    public String getAdresse(){
        return Address;
    }
    public String getRC(){
        return RC;
    }
    public int getNIF(){
        return NIF;
    }
}
